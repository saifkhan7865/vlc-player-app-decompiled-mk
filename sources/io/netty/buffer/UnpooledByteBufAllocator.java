package io.netty.buffer;

import io.netty.util.internal.LongCounter;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.nio.ByteBuffer;

public final class UnpooledByteBufAllocator extends AbstractByteBufAllocator implements ByteBufAllocatorMetricProvider {
    public static final UnpooledByteBufAllocator DEFAULT = new UnpooledByteBufAllocator(PlatformDependent.directBufferPreferred());
    private final boolean disableLeakDetector;
    private final UnpooledByteBufAllocatorMetric metric;
    private final boolean noCleaner;

    public boolean isDirectBufferPooled() {
        return false;
    }

    public UnpooledByteBufAllocator(boolean z) {
        this(z, false);
    }

    public UnpooledByteBufAllocator(boolean z, boolean z2) {
        this(z, z2, PlatformDependent.useDirectBufferNoCleaner());
    }

    public UnpooledByteBufAllocator(boolean z, boolean z2, boolean z3) {
        super(z);
        this.metric = new UnpooledByteBufAllocatorMetric();
        this.disableLeakDetector = z2;
        this.noCleaner = z3 && PlatformDependent.hasUnsafe() && PlatformDependent.hasDirectBufferNoCleanerConstructor();
    }

    /* access modifiers changed from: protected */
    public ByteBuf newHeapBuffer(int i, int i2) {
        return PlatformDependent.hasUnsafe() ? new InstrumentedUnpooledUnsafeHeapByteBuf(this, i, i2) : new InstrumentedUnpooledHeapByteBuf(this, i, i2);
    }

    /* access modifiers changed from: protected */
    public ByteBuf newDirectBuffer(int i, int i2) {
        ByteBuf byteBuf;
        if (PlatformDependent.hasUnsafe()) {
            byteBuf = this.noCleaner ? new InstrumentedUnpooledUnsafeNoCleanerDirectByteBuf(this, i, i2) : new InstrumentedUnpooledUnsafeDirectByteBuf(this, i, i2);
        } else {
            byteBuf = new InstrumentedUnpooledDirectByteBuf(this, i, i2);
        }
        return this.disableLeakDetector ? byteBuf : toLeakAwareBuffer(byteBuf);
    }

    public CompositeByteBuf compositeHeapBuffer(int i) {
        CompositeByteBuf compositeByteBuf = new CompositeByteBuf(this, false, i);
        return this.disableLeakDetector ? compositeByteBuf : toLeakAwareBuffer(compositeByteBuf);
    }

    public CompositeByteBuf compositeDirectBuffer(int i) {
        CompositeByteBuf compositeByteBuf = new CompositeByteBuf(this, true, i);
        return this.disableLeakDetector ? compositeByteBuf : toLeakAwareBuffer(compositeByteBuf);
    }

    public ByteBufAllocatorMetric metric() {
        return this.metric;
    }

    /* access modifiers changed from: package-private */
    public void incrementDirect(int i) {
        this.metric.directCounter.add((long) i);
    }

    /* access modifiers changed from: package-private */
    public void decrementDirect(int i) {
        this.metric.directCounter.add((long) (-i));
    }

    /* access modifiers changed from: package-private */
    public void incrementHeap(int i) {
        this.metric.heapCounter.add((long) i);
    }

    /* access modifiers changed from: package-private */
    public void decrementHeap(int i) {
        this.metric.heapCounter.add((long) (-i));
    }

    private static final class InstrumentedUnpooledUnsafeHeapByteBuf extends UnpooledUnsafeHeapByteBuf {
        InstrumentedUnpooledUnsafeHeapByteBuf(UnpooledByteBufAllocator unpooledByteBufAllocator, int i, int i2) {
            super(unpooledByteBufAllocator, i, i2);
        }

        /* access modifiers changed from: protected */
        public byte[] allocateArray(int i) {
            byte[] allocateArray = super.allocateArray(i);
            ((UnpooledByteBufAllocator) alloc()).incrementHeap(allocateArray.length);
            return allocateArray;
        }

        /* access modifiers changed from: protected */
        public void freeArray(byte[] bArr) {
            int length = bArr.length;
            super.freeArray(bArr);
            ((UnpooledByteBufAllocator) alloc()).decrementHeap(length);
        }
    }

    private static final class InstrumentedUnpooledHeapByteBuf extends UnpooledHeapByteBuf {
        InstrumentedUnpooledHeapByteBuf(UnpooledByteBufAllocator unpooledByteBufAllocator, int i, int i2) {
            super((ByteBufAllocator) unpooledByteBufAllocator, i, i2);
        }

        /* access modifiers changed from: protected */
        public byte[] allocateArray(int i) {
            byte[] allocateArray = super.allocateArray(i);
            ((UnpooledByteBufAllocator) alloc()).incrementHeap(allocateArray.length);
            return allocateArray;
        }

        /* access modifiers changed from: protected */
        public void freeArray(byte[] bArr) {
            int length = bArr.length;
            super.freeArray(bArr);
            ((UnpooledByteBufAllocator) alloc()).decrementHeap(length);
        }
    }

    private static final class InstrumentedUnpooledUnsafeNoCleanerDirectByteBuf extends UnpooledUnsafeNoCleanerDirectByteBuf {
        InstrumentedUnpooledUnsafeNoCleanerDirectByteBuf(UnpooledByteBufAllocator unpooledByteBufAllocator, int i, int i2) {
            super(unpooledByteBufAllocator, i, i2);
        }

        /* access modifiers changed from: protected */
        public ByteBuffer allocateDirect(int i) {
            ByteBuffer allocateDirect = super.allocateDirect(i);
            ((UnpooledByteBufAllocator) alloc()).incrementDirect(allocateDirect.capacity());
            return allocateDirect;
        }

        /* access modifiers changed from: package-private */
        public ByteBuffer reallocateDirect(ByteBuffer byteBuffer, int i) {
            int capacity = byteBuffer.capacity();
            ByteBuffer reallocateDirect = super.reallocateDirect(byteBuffer, i);
            ((UnpooledByteBufAllocator) alloc()).incrementDirect(reallocateDirect.capacity() - capacity);
            return reallocateDirect;
        }

        /* access modifiers changed from: protected */
        public void freeDirect(ByteBuffer byteBuffer) {
            int capacity = byteBuffer.capacity();
            super.freeDirect(byteBuffer);
            ((UnpooledByteBufAllocator) alloc()).decrementDirect(capacity);
        }
    }

    private static final class InstrumentedUnpooledUnsafeDirectByteBuf extends UnpooledUnsafeDirectByteBuf {
        InstrumentedUnpooledUnsafeDirectByteBuf(UnpooledByteBufAllocator unpooledByteBufAllocator, int i, int i2) {
            super((ByteBufAllocator) unpooledByteBufAllocator, i, i2);
        }

        /* access modifiers changed from: protected */
        public ByteBuffer allocateDirect(int i) {
            ByteBuffer allocateDirect = super.allocateDirect(i);
            ((UnpooledByteBufAllocator) alloc()).incrementDirect(allocateDirect.capacity());
            return allocateDirect;
        }

        /* access modifiers changed from: protected */
        public void freeDirect(ByteBuffer byteBuffer) {
            int capacity = byteBuffer.capacity();
            super.freeDirect(byteBuffer);
            ((UnpooledByteBufAllocator) alloc()).decrementDirect(capacity);
        }
    }

    private static final class InstrumentedUnpooledDirectByteBuf extends UnpooledDirectByteBuf {
        InstrumentedUnpooledDirectByteBuf(UnpooledByteBufAllocator unpooledByteBufAllocator, int i, int i2) {
            super((ByteBufAllocator) unpooledByteBufAllocator, i, i2);
        }

        /* access modifiers changed from: protected */
        public ByteBuffer allocateDirect(int i) {
            ByteBuffer allocateDirect = super.allocateDirect(i);
            ((UnpooledByteBufAllocator) alloc()).incrementDirect(allocateDirect.capacity());
            return allocateDirect;
        }

        /* access modifiers changed from: protected */
        public void freeDirect(ByteBuffer byteBuffer) {
            int capacity = byteBuffer.capacity();
            super.freeDirect(byteBuffer);
            ((UnpooledByteBufAllocator) alloc()).decrementDirect(capacity);
        }
    }

    private static final class UnpooledByteBufAllocatorMetric implements ByteBufAllocatorMetric {
        final LongCounter directCounter;
        final LongCounter heapCounter;

        private UnpooledByteBufAllocatorMetric() {
            this.directCounter = PlatformDependent.newLongCounter();
            this.heapCounter = PlatformDependent.newLongCounter();
        }

        public long usedHeapMemory() {
            return this.heapCounter.value();
        }

        public long usedDirectMemory() {
            return this.directCounter.value();
        }

        public String toString() {
            return StringUtil.simpleClassName((Object) this) + "(usedHeapMemory: " + usedHeapMemory() + "; usedDirectMemory: " + usedDirectMemory() + ')';
        }
    }
}
