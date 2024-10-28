package io.netty.buffer;

import io.netty.buffer.PoolArena;
import io.netty.util.Recycler;
import io.netty.util.internal.MathUtil;
import io.netty.util.internal.ObjectPool;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

final class PoolThreadCache {
    private static final int INTEGER_SIZE_MINUS_ONE = 31;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PoolThreadCache.class);
    private int allocations;
    final PoolArena<ByteBuffer> directArena;
    private final FreeOnFinalize freeOnFinalize;
    private final int freeSweepAllocationThreshold;
    private final AtomicBoolean freed = new AtomicBoolean();
    final PoolArena<byte[]> heapArena;
    private final MemoryRegionCache<ByteBuffer>[] normalDirectCaches;
    private final MemoryRegionCache<byte[]>[] normalHeapCaches;
    private final MemoryRegionCache<ByteBuffer>[] smallSubPageDirectCaches;
    private final MemoryRegionCache<byte[]>[] smallSubPageHeapCaches;

    PoolThreadCache(PoolArena<byte[]> poolArena, PoolArena<ByteBuffer> poolArena2, int i, int i2, int i3, int i4, boolean z) {
        ObjectUtil.checkPositiveOrZero(i3, "maxCachedBufferCapacity");
        this.freeSweepAllocationThreshold = i4;
        this.heapArena = poolArena;
        this.directArena = poolArena2;
        FreeOnFinalize freeOnFinalize2 = null;
        if (poolArena2 != null) {
            this.smallSubPageDirectCaches = createSubPageCaches(i, poolArena2.numSmallSubpagePools);
            this.normalDirectCaches = createNormalCaches(i2, i3, poolArena2);
            poolArena2.numThreadCaches.getAndIncrement();
        } else {
            this.smallSubPageDirectCaches = null;
            this.normalDirectCaches = null;
        }
        if (poolArena != null) {
            this.smallSubPageHeapCaches = createSubPageCaches(i, poolArena.numSmallSubpagePools);
            this.normalHeapCaches = createNormalCaches(i2, i3, poolArena);
            poolArena.numThreadCaches.getAndIncrement();
        } else {
            this.smallSubPageHeapCaches = null;
            this.normalHeapCaches = null;
        }
        if (!(this.smallSubPageDirectCaches == null && this.normalDirectCaches == null && this.smallSubPageHeapCaches == null && this.normalHeapCaches == null) && i4 < 1) {
            throw new IllegalArgumentException("freeSweepAllocationThreshold: " + i4 + " (expected: > 0)");
        }
        this.freeOnFinalize = z ? new FreeOnFinalize(this, (AnonymousClass1) null) : freeOnFinalize2;
    }

    private static <T> MemoryRegionCache<T>[] createSubPageCaches(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        MemoryRegionCache<T>[] memoryRegionCacheArr = new MemoryRegionCache[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            memoryRegionCacheArr[i3] = new SubPageMemoryRegionCache(i);
        }
        return memoryRegionCacheArr;
    }

    private static <T> MemoryRegionCache<T>[] createNormalCaches(int i, int i2, PoolArena<T> poolArena) {
        if (i <= 0 || i2 <= 0) {
            return null;
        }
        int min = Math.min(poolArena.chunkSize, i2);
        ArrayList arrayList = new ArrayList();
        int i3 = poolArena.numSmallSubpagePools;
        while (i3 < poolArena.nSizes && poolArena.sizeIdx2size(i3) <= min) {
            arrayList.add(new NormalMemoryRegionCache(i));
            i3++;
        }
        return (MemoryRegionCache[]) arrayList.toArray(new MemoryRegionCache[0]);
    }

    static int log2(int i) {
        return 31 - Integer.numberOfLeadingZeros(i);
    }

    /* access modifiers changed from: package-private */
    public boolean allocateSmall(PoolArena<?> poolArena, PooledByteBuf<?> pooledByteBuf, int i, int i2) {
        return allocate(cacheForSmall(poolArena, i2), pooledByteBuf, i);
    }

    /* access modifiers changed from: package-private */
    public boolean allocateNormal(PoolArena<?> poolArena, PooledByteBuf<?> pooledByteBuf, int i, int i2) {
        return allocate(cacheForNormal(poolArena, i2), pooledByteBuf, i);
    }

    private boolean allocate(MemoryRegionCache<?> memoryRegionCache, PooledByteBuf pooledByteBuf, int i) {
        if (memoryRegionCache == null) {
            return false;
        }
        boolean allocate = memoryRegionCache.allocate(pooledByteBuf, i, this);
        int i2 = this.allocations + 1;
        this.allocations = i2;
        if (i2 >= this.freeSweepAllocationThreshold) {
            this.allocations = 0;
            trim();
        }
        return allocate;
    }

    /* access modifiers changed from: package-private */
    public boolean add(PoolArena<?> poolArena, PoolChunk poolChunk, ByteBuffer byteBuffer, long j, int i, PoolArena.SizeClass sizeClass) {
        MemoryRegionCache<?> cache = cache(poolArena, poolArena.size2SizeIdx(i), sizeClass);
        if (cache != null && !this.freed.get()) {
            return cache.add(poolChunk, byteBuffer, j, i);
        }
        return false;
    }

    /* renamed from: io.netty.buffer.PoolThreadCache$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$buffer$PoolArena$SizeClass;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                io.netty.buffer.PoolArena$SizeClass[] r0 = io.netty.buffer.PoolArena.SizeClass.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$buffer$PoolArena$SizeClass = r0
                io.netty.buffer.PoolArena$SizeClass r1 = io.netty.buffer.PoolArena.SizeClass.Normal     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$buffer$PoolArena$SizeClass     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.buffer.PoolArena$SizeClass r1 = io.netty.buffer.PoolArena.SizeClass.Small     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.buffer.PoolThreadCache.AnonymousClass1.<clinit>():void");
        }
    }

    private MemoryRegionCache<?> cache(PoolArena<?> poolArena, int i, PoolArena.SizeClass sizeClass) {
        int i2 = AnonymousClass1.$SwitchMap$io$netty$buffer$PoolArena$SizeClass[sizeClass.ordinal()];
        if (i2 == 1) {
            return cacheForNormal(poolArena, i);
        }
        if (i2 == 2) {
            return cacheForSmall(poolArena, i);
        }
        throw new Error();
    }

    /* access modifiers changed from: package-private */
    public void free(boolean z) {
        if (this.freed.compareAndSet(false, true)) {
            int free = free((MemoryRegionCache<?>[]) this.smallSubPageDirectCaches, z) + free((MemoryRegionCache<?>[]) this.normalDirectCaches, z) + free((MemoryRegionCache<?>[]) this.smallSubPageHeapCaches, z) + free((MemoryRegionCache<?>[]) this.normalHeapCaches, z);
            if (free > 0) {
                InternalLogger internalLogger = logger;
                if (internalLogger.isDebugEnabled()) {
                    internalLogger.debug("Freed {} thread-local buffer(s) from thread: {}", Integer.valueOf(free), Thread.currentThread().getName());
                }
            }
            PoolArena<ByteBuffer> poolArena = this.directArena;
            if (poolArena != null) {
                poolArena.numThreadCaches.getAndDecrement();
            }
            PoolArena<byte[]> poolArena2 = this.heapArena;
            if (poolArena2 != null) {
                poolArena2.numThreadCaches.getAndDecrement();
                return;
            }
            return;
        }
        checkCacheMayLeak(this.smallSubPageDirectCaches, "SmallSubPageDirectCaches");
        checkCacheMayLeak(this.normalDirectCaches, "NormalDirectCaches");
        checkCacheMayLeak(this.smallSubPageHeapCaches, "SmallSubPageHeapCaches");
        checkCacheMayLeak(this.normalHeapCaches, "NormalHeapCaches");
    }

    private static void checkCacheMayLeak(MemoryRegionCache<?>[] memoryRegionCacheArr, String str) {
        for (MemoryRegionCache<?> access$100 : memoryRegionCacheArr) {
            if (!access$100.queue.isEmpty()) {
                logger.debug("{} memory may leak.", (Object) str);
                return;
            }
        }
    }

    private static int free(MemoryRegionCache<?>[] memoryRegionCacheArr, boolean z) {
        if (memoryRegionCacheArr == null) {
            return 0;
        }
        int i = 0;
        for (MemoryRegionCache<?> free : memoryRegionCacheArr) {
            i += free(free, z);
        }
        return i;
    }

    private static int free(MemoryRegionCache<?> memoryRegionCache, boolean z) {
        if (memoryRegionCache == null) {
            return 0;
        }
        return memoryRegionCache.free(z);
    }

    /* access modifiers changed from: package-private */
    public void trim() {
        trim((MemoryRegionCache<?>[]) this.smallSubPageDirectCaches);
        trim((MemoryRegionCache<?>[]) this.normalDirectCaches);
        trim((MemoryRegionCache<?>[]) this.smallSubPageHeapCaches);
        trim((MemoryRegionCache<?>[]) this.normalHeapCaches);
    }

    private static void trim(MemoryRegionCache<?>[] memoryRegionCacheArr) {
        if (memoryRegionCacheArr != null) {
            for (MemoryRegionCache<?> trim : memoryRegionCacheArr) {
                trim(trim);
            }
        }
    }

    private static void trim(MemoryRegionCache<?> memoryRegionCache) {
        if (memoryRegionCache != null) {
            memoryRegionCache.trim();
        }
    }

    private MemoryRegionCache<?> cacheForSmall(PoolArena<?> poolArena, int i) {
        if (poolArena.isDirect()) {
            return cache(this.smallSubPageDirectCaches, i);
        }
        return cache(this.smallSubPageHeapCaches, i);
    }

    private MemoryRegionCache<?> cacheForNormal(PoolArena<?> poolArena, int i) {
        int i2 = i - poolArena.numSmallSubpagePools;
        if (poolArena.isDirect()) {
            return cache(this.normalDirectCaches, i2);
        }
        return cache(this.normalHeapCaches, i2);
    }

    private static <T> MemoryRegionCache<T> cache(MemoryRegionCache<T>[] memoryRegionCacheArr, int i) {
        if (memoryRegionCacheArr == null || i > memoryRegionCacheArr.length - 1) {
            return null;
        }
        return memoryRegionCacheArr[i];
    }

    private static final class SubPageMemoryRegionCache<T> extends MemoryRegionCache<T> {
        SubPageMemoryRegionCache(int i) {
            super(i, PoolArena.SizeClass.Small);
        }

        /* access modifiers changed from: protected */
        public void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            poolChunk.initBufWithSubpage(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
        }
    }

    private static final class NormalMemoryRegionCache<T> extends MemoryRegionCache<T> {
        NormalMemoryRegionCache(int i) {
            super(i, PoolArena.SizeClass.Normal);
        }

        /* access modifiers changed from: protected */
        public void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            poolChunk.initBuf(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
        }
    }

    private static abstract class MemoryRegionCache<T> {
        private static final ObjectPool<Entry> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<Entry>() {
            public Entry newObject(ObjectPool.Handle<Entry> handle) {
                return new Entry(handle);
            }
        });
        private int allocations;
        /* access modifiers changed from: private */
        public final Queue<Entry<T>> queue;
        private final int size;
        private final PoolArena.SizeClass sizeClass;

        /* access modifiers changed from: protected */
        public abstract void initBuf(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache);

        MemoryRegionCache(int i, PoolArena.SizeClass sizeClass2) {
            int safeFindNextPositivePowerOfTwo = MathUtil.safeFindNextPositivePowerOfTwo(i);
            this.size = safeFindNextPositivePowerOfTwo;
            this.queue = PlatformDependent.newFixedMpscQueue(safeFindNextPositivePowerOfTwo);
            this.sizeClass = sizeClass2;
        }

        public final boolean add(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, int i) {
            Entry newEntry = newEntry(poolChunk, byteBuffer, j, i);
            boolean offer = this.queue.offer(newEntry);
            if (!offer) {
                newEntry.unguardedRecycle();
            }
            return offer;
        }

        public final boolean allocate(PooledByteBuf<T> pooledByteBuf, int i, PoolThreadCache poolThreadCache) {
            Entry poll = this.queue.poll();
            if (poll == null) {
                return false;
            }
            initBuf(poll.chunk, poll.nioBuffer, poll.handle, pooledByteBuf, i, poolThreadCache);
            poll.unguardedRecycle();
            this.allocations++;
            return true;
        }

        public final int free(boolean z) {
            return free(Integer.MAX_VALUE, z);
        }

        private int free(int i, boolean z) {
            int i2 = 0;
            while (i2 < i) {
                Entry poll = this.queue.poll();
                if (poll == null) {
                    break;
                }
                freeEntry(poll, z);
                i2++;
            }
            return i2;
        }

        public final void trim() {
            int i = this.size - this.allocations;
            this.allocations = 0;
            if (i > 0) {
                free(i, false);
            }
        }

        private void freeEntry(Entry entry, boolean z) {
            PoolChunk<T> poolChunk = entry.chunk;
            long j = entry.handle;
            ByteBuffer byteBuffer = entry.nioBuffer;
            int i = entry.normCapacity;
            if (!z) {
                entry.recycle();
            }
            poolChunk.arena.freeChunk(poolChunk, j, i, this.sizeClass, byteBuffer, z);
        }

        static final class Entry<T> {
            PoolChunk<T> chunk;
            long handle = -1;
            ByteBuffer nioBuffer;
            int normCapacity;
            final Recycler.EnhancedHandle<Entry<?>> recyclerHandle;

            Entry(ObjectPool.Handle<Entry<?>> handle2) {
                this.recyclerHandle = (Recycler.EnhancedHandle) handle2;
            }

            /* access modifiers changed from: package-private */
            public void recycle() {
                this.chunk = null;
                this.nioBuffer = null;
                this.handle = -1;
                this.recyclerHandle.recycle(this);
            }

            /* access modifiers changed from: package-private */
            public void unguardedRecycle() {
                this.chunk = null;
                this.nioBuffer = null;
                this.handle = -1;
                this.recyclerHandle.unguardedRecycle(this);
            }
        }

        private static Entry newEntry(PoolChunk<?> poolChunk, ByteBuffer byteBuffer, long j, int i) {
            Entry entry = RECYCLER.get();
            entry.chunk = poolChunk;
            entry.nioBuffer = byteBuffer;
            entry.handle = j;
            entry.normCapacity = i;
            return entry;
        }
    }

    private static final class FreeOnFinalize {
        private final PoolThreadCache cache;

        /* synthetic */ FreeOnFinalize(PoolThreadCache poolThreadCache, AnonymousClass1 r2) {
            this(poolThreadCache);
        }

        private FreeOnFinalize(PoolThreadCache poolThreadCache) {
            this.cache = poolThreadCache;
        }

        /* access modifiers changed from: protected */
        public void finalize() throws Throwable {
            try {
                super.finalize();
            } finally {
                this.cache.free(true);
            }
        }
    }
}
