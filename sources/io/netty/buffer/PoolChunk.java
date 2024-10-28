package io.netty.buffer;

import io.netty.util.internal.LongCounter;
import io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.ReentrantLock;

final class PoolChunk<T> implements PoolChunkMetric {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int BITMAP_IDX_BIT_LENGTH = 32;
    private static final int INUSED_BIT_LENGTH = 1;
    static final int IS_SUBPAGE_SHIFT = 32;
    static final int IS_USED_SHIFT = 33;
    static final int RUN_OFFSET_SHIFT = 49;
    private static final int SIZE_BIT_LENGTH = 15;
    static final int SIZE_SHIFT = 34;
    private static final int SUBPAGE_BIT_LENGTH = 1;
    final PoolArena<T> arena;
    final Object base;
    private final Deque<ByteBuffer> cachedNioBuffers;
    private final int chunkSize;
    int freeBytes;
    final T memory;
    PoolChunk<T> next;
    private final int pageShifts;
    private final int pageSize;
    PoolChunkList<T> parent;
    private final LongCounter pinnedBytes = PlatformDependent.newLongCounter();
    PoolChunk<T> prev;
    private final IntPriorityQueue[] runsAvail;
    private final ReentrantLock runsAvailLock;
    private final LongLongHashMap runsAvailMap;
    private final PoolSubpage<T>[] subpages;
    final boolean unpooled = true;

    static int bitmapIdx(long j) {
        return (int) j;
    }

    static boolean isSubpage(long j) {
        return ((j >> 32) & 1) == 1;
    }

    static boolean isUsed(long j) {
        return ((j >> 33) & 1) == 1;
    }

    private static int lastPage(int i, int i2) {
        return (i + i2) - 1;
    }

    static int runOffset(long j) {
        return (int) (j >> 49);
    }

    static int runPages(long j) {
        return (int) ((j >> 34) & 32767);
    }

    private static long toRunHandle(int i, int i2, int i3) {
        return (((long) i2) << 34) | (((long) i) << 49) | (((long) i3) << 33);
    }

    PoolChunk(PoolArena<T> poolArena, Object obj, T t, int i, int i2, int i3, int i4) {
        this.arena = poolArena;
        this.base = obj;
        this.memory = t;
        this.pageSize = i;
        this.pageShifts = i2;
        this.chunkSize = i3;
        this.freeBytes = i3;
        this.runsAvail = newRunsAvailqueueArray(i4);
        this.runsAvailLock = new ReentrantLock();
        this.runsAvailMap = new LongLongHashMap(-1);
        int i5 = i3 >> i2;
        this.subpages = new PoolSubpage[i5];
        insertAvailRun(0, i5, ((long) i5) << 34);
        this.cachedNioBuffers = new ArrayDeque(8);
    }

    PoolChunk(PoolArena<T> poolArena, Object obj, T t, int i) {
        this.arena = poolArena;
        this.base = obj;
        this.memory = t;
        this.pageSize = 0;
        this.pageShifts = 0;
        this.runsAvailMap = null;
        this.runsAvail = null;
        this.runsAvailLock = null;
        this.subpages = null;
        this.chunkSize = i;
        this.cachedNioBuffers = null;
    }

    private static IntPriorityQueue[] newRunsAvailqueueArray(int i) {
        IntPriorityQueue[] intPriorityQueueArr = new IntPriorityQueue[i];
        for (int i2 = 0; i2 < i; i2++) {
            intPriorityQueueArr[i2] = new IntPriorityQueue();
        }
        return intPriorityQueueArr;
    }

    private void insertAvailRun(int i, int i2, long j) {
        this.runsAvail[this.arena.pages2pageIdxFloor(i2)].offer((int) (j >> 32));
        insertAvailRun0(i, j);
        if (i2 > 1) {
            insertAvailRun0(lastPage(i, i2), j);
        }
    }

    private void insertAvailRun0(int i, long j) {
        this.runsAvailMap.put((long) i, j);
    }

    private void removeAvailRun(long j) {
        this.runsAvail[this.arena.pages2pageIdxFloor(runPages(j))].remove((int) (j >> 32));
        removeAvailRun0(j);
    }

    private void removeAvailRun0(long j) {
        int runOffset = runOffset(j);
        int runPages = runPages(j);
        this.runsAvailMap.remove((long) runOffset);
        if (runPages > 1) {
            this.runsAvailMap.remove((long) lastPage(runOffset, runPages));
        }
    }

    private long getAvailRunByOffset(int i) {
        return this.runsAvailMap.get((long) i);
    }

    public int usage() {
        int i;
        if (this.unpooled) {
            i = this.freeBytes;
        } else {
            this.runsAvailLock.lock();
            try {
                i = this.freeBytes;
            } finally {
                this.runsAvailLock.unlock();
            }
        }
        return usage(i);
    }

    private int usage(int i) {
        if (i == 0) {
            return 100;
        }
        int i2 = (int) ((((long) i) * 100) / ((long) this.chunkSize));
        if (i2 == 0) {
            return 99;
        }
        return 100 - i2;
    }

    /* access modifiers changed from: package-private */
    public boolean allocate(PooledByteBuf<T> pooledByteBuf, int i, int i2, PoolThreadCache poolThreadCache) {
        long j;
        int i3 = i2;
        if (i3 <= this.arena.smallMaxSizeIdx) {
            PoolSubpage<T> poolSubpage = this.arena.smallSubpagePools[i3];
            poolSubpage.lock();
            try {
                PoolSubpage<T> poolSubpage2 = poolSubpage.next;
                if (poolSubpage2 != poolSubpage) {
                    poolSubpage2.chunk.initBufWithSubpage(pooledByteBuf, (ByteBuffer) null, poolSubpage2.allocate(), i, poolThreadCache);
                    return true;
                }
                long allocateSubpage = allocateSubpage(i3, poolSubpage);
                if (allocateSubpage < 0) {
                    poolSubpage.unlock();
                    return false;
                }
                poolSubpage.unlock();
                j = allocateSubpage;
            } finally {
                poolSubpage.unlock();
            }
        } else {
            long allocateRun = allocateRun(this.arena.sizeIdx2size(i3));
            if (allocateRun < 0) {
                return false;
            }
            j = allocateRun;
        }
        Deque<ByteBuffer> deque = this.cachedNioBuffers;
        initBuf(pooledByteBuf, deque != null ? deque.pollLast() : null, j, i, poolThreadCache);
        return true;
    }

    /* JADX INFO: finally extract failed */
    private long allocateRun(int i) {
        int i2 = i >> this.pageShifts;
        int pages2pageIdx = this.arena.pages2pageIdx(i2);
        this.runsAvailLock.lock();
        try {
            int runFirstBestFit = runFirstBestFit(pages2pageIdx);
            if (runFirstBestFit == -1) {
                this.runsAvailLock.unlock();
                return -1;
            }
            long poll = ((long) this.runsAvail[runFirstBestFit].poll()) << 32;
            removeAvailRun0(poll);
            long splitLargeRun = splitLargeRun(poll, i2);
            this.freeBytes -= runSize(this.pageShifts, splitLargeRun);
            this.runsAvailLock.unlock();
            return splitLargeRun;
        } catch (Throwable th) {
            this.runsAvailLock.unlock();
            throw th;
        }
    }

    private int calculateRunSize(int i) {
        int i2;
        int i3 = 1 << (this.pageShifts - 4);
        int sizeIdx2size = this.arena.sizeIdx2size(i);
        int i4 = 0;
        do {
            i4 += this.pageSize;
            i2 = i4 / sizeIdx2size;
            if (i2 >= i3 || i4 == i2 * sizeIdx2size) {
            }
            i4 += this.pageSize;
            i2 = i4 / sizeIdx2size;
            break;
        } while (i4 == i2 * sizeIdx2size);
        while (i2 > i3) {
            i4 -= this.pageSize;
            i2 = i4 / sizeIdx2size;
        }
        return i4;
    }

    private int runFirstBestFit(int i) {
        if (this.freeBytes == this.chunkSize) {
            return this.arena.nPSizes - 1;
        }
        while (i < this.arena.nPSizes) {
            IntPriorityQueue intPriorityQueue = this.runsAvail[i];
            if (intPriorityQueue != null && !intPriorityQueue.isEmpty()) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private long splitLargeRun(long j, int i) {
        int runPages = runPages(j) - i;
        if (runPages <= 0) {
            return j | 8589934592L;
        }
        int runOffset = runOffset(j);
        int i2 = runOffset + i;
        insertAvailRun(i2, runPages, toRunHandle(i2, runPages, 0));
        return toRunHandle(runOffset, i, 1);
    }

    private long allocateSubpage(int i, PoolSubpage<T> poolSubpage) {
        long allocateRun = allocateRun(calculateRunSize(i));
        if (allocateRun < 0) {
            return -1;
        }
        int runOffset = runOffset(allocateRun);
        int sizeIdx2size = this.arena.sizeIdx2size(i);
        int i2 = this.pageShifts;
        PoolSubpage<T> poolSubpage2 = new PoolSubpage<>(poolSubpage, this, i2, runOffset, runSize(i2, allocateRun), sizeIdx2size);
        this.subpages[runOffset] = poolSubpage2;
        return poolSubpage2.allocate();
    }

    /* access modifiers changed from: package-private */
    public void free(long j, int i, ByteBuffer byteBuffer) {
        Deque<ByteBuffer> deque;
        if (isSubpage(j)) {
            int runOffset = runOffset(j);
            PoolSubpage<T> poolSubpage = this.subpages[runOffset];
            PoolSubpage<T> poolSubpage2 = poolSubpage.chunk.arena.smallSubpagePools[poolSubpage.headIndex];
            poolSubpage2.lock();
            try {
                if (!poolSubpage.free(poolSubpage2, bitmapIdx(j))) {
                    this.subpages[runOffset] = null;
                    poolSubpage2.unlock();
                } else {
                    return;
                }
            } finally {
                poolSubpage2.unlock();
            }
        }
        int runSize = runSize(this.pageShifts, j);
        this.runsAvailLock.lock();
        try {
            long collapseRuns = collapseRuns(j) & -12884901889L;
            insertAvailRun(runOffset(collapseRuns), runPages(collapseRuns), collapseRuns);
            this.freeBytes += runSize;
            if (byteBuffer != null && (deque = this.cachedNioBuffers) != null && deque.size() < PooledByteBufAllocator.DEFAULT_MAX_CACHED_BYTEBUFFERS_PER_CHUNK) {
                this.cachedNioBuffers.offer(byteBuffer);
            }
        } finally {
            this.runsAvailLock.unlock();
        }
    }

    private long collapseRuns(long j) {
        return collapseNext(collapsePast(j));
    }

    private long collapsePast(long j) {
        while (true) {
            int runOffset = runOffset(j);
            int runPages = runPages(j);
            long availRunByOffset = getAvailRunByOffset(runOffset - 1);
            if (availRunByOffset == -1) {
                return j;
            }
            int runOffset2 = runOffset(availRunByOffset);
            int runPages2 = runPages(availRunByOffset);
            if (availRunByOffset == j || runOffset2 + runPages2 != runOffset) {
                return j;
            }
            removeAvailRun(availRunByOffset);
            j = toRunHandle(runOffset2, runPages2 + runPages, 0);
        }
        return j;
    }

    private long collapseNext(long j) {
        while (true) {
            int runOffset = runOffset(j);
            int runPages = runPages(j);
            int i = runOffset + runPages;
            long availRunByOffset = getAvailRunByOffset(i);
            if (availRunByOffset == -1) {
                return j;
            }
            int runOffset2 = runOffset(availRunByOffset);
            int runPages2 = runPages(availRunByOffset);
            if (availRunByOffset == j || i != runOffset2) {
                return j;
            }
            removeAvailRun(availRunByOffset);
            j = toRunHandle(runOffset, runPages + runPages2, 0);
        }
        return j;
    }

    /* access modifiers changed from: package-private */
    public void initBuf(PooledByteBuf<T> pooledByteBuf, ByteBuffer byteBuffer, long j, int i, PoolThreadCache poolThreadCache) {
        if (isSubpage(j)) {
            initBufWithSubpage(pooledByteBuf, byteBuffer, j, i, poolThreadCache);
            return;
        }
        PooledByteBuf<T> pooledByteBuf2 = pooledByteBuf;
        ByteBuffer byteBuffer2 = byteBuffer;
        long j2 = j;
        int i2 = i;
        pooledByteBuf2.init(this, byteBuffer2, j2, runOffset(j) << this.pageShifts, i2, runSize(this.pageShifts, j), this.arena.parent.threadCache());
    }

    /* access modifiers changed from: package-private */
    public void initBufWithSubpage(PooledByteBuf<T> pooledByteBuf, ByteBuffer byteBuffer, long j, int i, PoolThreadCache poolThreadCache) {
        int runOffset = runOffset(j);
        int bitmapIdx = bitmapIdx(j);
        PoolSubpage<T> poolSubpage = this.subpages[runOffset];
        pooledByteBuf.init(this, byteBuffer, j, (runOffset << this.pageShifts) + (bitmapIdx * poolSubpage.elemSize), i, poolSubpage.elemSize, poolThreadCache);
    }

    /* access modifiers changed from: package-private */
    public void incrementPinnedMemory(int i) {
        this.pinnedBytes.add((long) i);
    }

    /* access modifiers changed from: package-private */
    public void decrementPinnedMemory(int i) {
        this.pinnedBytes.add((long) (-i));
    }

    public int chunkSize() {
        return this.chunkSize;
    }

    public int freeBytes() {
        if (this.unpooled) {
            return this.freeBytes;
        }
        this.runsAvailLock.lock();
        try {
            return this.freeBytes;
        } finally {
            this.runsAvailLock.unlock();
        }
    }

    public int pinnedBytes() {
        return (int) this.pinnedBytes.value();
    }

    public String toString() {
        int i;
        if (this.unpooled) {
            i = this.freeBytes;
        } else {
            this.runsAvailLock.lock();
            try {
                i = this.freeBytes;
            } finally {
                this.runsAvailLock.unlock();
            }
        }
        return "Chunk(" + Integer.toHexString(System.identityHashCode(this)) + ": " + usage(i) + "%, " + (this.chunkSize - i) + '/' + this.chunkSize + ')';
    }

    /* access modifiers changed from: package-private */
    public void destroy() {
        this.arena.destroyChunk(this);
    }

    static int runSize(int i, long j) {
        return runPages(j) << i;
    }

    static boolean isRun(long j) {
        return !isSubpage(j);
    }
}
