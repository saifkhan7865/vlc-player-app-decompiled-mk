package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;

class UnpooledUnsafeNoCleanerDirectByteBuf extends UnpooledUnsafeDirectByteBuf {
    UnpooledUnsafeNoCleanerDirectByteBuf(ByteBufAllocator byteBufAllocator, int i, int i2) {
        super(byteBufAllocator, i, i2);
    }

    /* access modifiers changed from: protected */
    public ByteBuffer allocateDirect(int i) {
        return PlatformDependent.allocateDirectNoCleaner(i);
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer reallocateDirect(ByteBuffer byteBuffer, int i) {
        return PlatformDependent.reallocateDirectNoCleaner(byteBuffer, i);
    }

    /* access modifiers changed from: protected */
    public void freeDirect(ByteBuffer byteBuffer) {
        PlatformDependent.freeDirectNoCleaner(byteBuffer);
    }

    public ByteBuf capacity(int i) {
        checkNewCapacity(i);
        if (i == capacity()) {
            return this;
        }
        trimIndicesToCapacity(i);
        setByteBuffer(reallocateDirect(this.buffer, i), false);
        return this;
    }
}
