package io.netty.buffer;

import io.netty.util.Recycler;
import io.netty.util.internal.ObjectPool;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

abstract class PooledByteBuf<T> extends AbstractReferenceCountedByteBuf {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ByteBufAllocator allocator;
    PoolThreadCache cache;
    protected PoolChunk<T> chunk;
    protected long handle;
    protected int length;
    int maxLength;
    protected T memory;
    protected int offset;
    private final Recycler.EnhancedHandle<PooledByteBuf<T>> recyclerHandle;
    ByteBuffer tmpNioBuf;

    public final boolean isContiguous() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract ByteBuffer newInternalNioBuffer(T t);

    public final int nioBufferCount() {
        return 1;
    }

    public final ByteBuf unwrap() {
        return null;
    }

    protected PooledByteBuf(ObjectPool.Handle<? extends PooledByteBuf<T>> handle2, int i) {
        super(i);
        this.recyclerHandle = (Recycler.EnhancedHandle) handle2;
    }

    /* access modifiers changed from: package-private */
    public void init(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, int i, int i2, int i3, PoolThreadCache poolThreadCache) {
        init0(poolChunk, byteBuffer, j, i, i2, i3, poolThreadCache);
    }

    /* access modifiers changed from: package-private */
    public void initUnpooled(PoolChunk<T> poolChunk, int i) {
        init0(poolChunk, (ByteBuffer) null, 0, 0, i, i, (PoolThreadCache) null);
    }

    private void init0(PoolChunk<T> poolChunk, ByteBuffer byteBuffer, long j, int i, int i2, int i3, PoolThreadCache poolThreadCache) {
        poolChunk.incrementPinnedMemory(i3);
        this.chunk = poolChunk;
        this.memory = poolChunk.memory;
        this.tmpNioBuf = byteBuffer;
        this.allocator = poolChunk.arena.parent;
        this.cache = poolThreadCache;
        this.handle = j;
        this.offset = i;
        this.length = i2;
        this.maxLength = i3;
    }

    /* access modifiers changed from: package-private */
    public final void reuse(int i) {
        maxCapacity(i);
        resetRefCnt();
        setIndex0(0, 0);
        discardMarks();
    }

    public final int capacity() {
        return this.length;
    }

    public int maxFastWritableBytes() {
        return Math.min(this.maxLength, maxCapacity()) - this.writerIndex;
    }

    public final ByteBuf capacity(int i) {
        if (i == this.length) {
            ensureAccessible();
            return this;
        }
        checkNewCapacity(i);
        if (!this.chunk.unpooled) {
            if (i <= this.length) {
                int i2 = this.maxLength;
                if (i > (i2 >>> 1) && (i2 > 512 || i > i2 - 16)) {
                    this.length = i;
                    trimIndicesToCapacity(i);
                    return this;
                }
            } else if (i <= this.maxLength) {
                this.length = i;
                return this;
            }
        }
        this.chunk.arena.reallocate(this, i);
        return this;
    }

    public final ByteBufAllocator alloc() {
        return this.allocator;
    }

    public final ByteOrder order() {
        return ByteOrder.BIG_ENDIAN;
    }

    public final ByteBuf retainedDuplicate() {
        return PooledDuplicatedByteBuf.newInstance(this, this, readerIndex(), writerIndex());
    }

    public final ByteBuf retainedSlice() {
        int readerIndex = readerIndex();
        return retainedSlice(readerIndex, writerIndex() - readerIndex);
    }

    public final ByteBuf retainedSlice(int i, int i2) {
        return PooledSlicedByteBuf.newInstance(this, this, i, i2);
    }

    /* access modifiers changed from: protected */
    public final ByteBuffer internalNioBuffer() {
        ByteBuffer byteBuffer = this.tmpNioBuf;
        if (byteBuffer == null) {
            ByteBuffer newInternalNioBuffer = newInternalNioBuffer(this.memory);
            this.tmpNioBuf = newInternalNioBuffer;
            return newInternalNioBuffer;
        }
        byteBuffer.clear();
        return byteBuffer;
    }

    /* access modifiers changed from: protected */
    public final void deallocate() {
        long j = this.handle;
        if (j >= 0) {
            this.handle = -1;
            this.memory = null;
            this.chunk.arena.free(this.chunk, this.tmpNioBuf, j, this.maxLength, this.cache);
            this.tmpNioBuf = null;
            this.chunk = null;
            this.cache = null;
            this.recyclerHandle.unguardedRecycle(this);
        }
    }

    /* access modifiers changed from: protected */
    public final int idx(int i) {
        return this.offset + i;
    }

    /* access modifiers changed from: package-private */
    public final ByteBuffer _internalNioBuffer(int i, int i2, boolean z) {
        int idx = idx(i);
        ByteBuffer newInternalNioBuffer = z ? newInternalNioBuffer(this.memory) : internalNioBuffer();
        newInternalNioBuffer.limit(i2 + idx).position(idx);
        return newInternalNioBuffer;
    }

    /* access modifiers changed from: package-private */
    public ByteBuffer duplicateInternalNioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return _internalNioBuffer(i, i2, true);
    }

    public final ByteBuffer internalNioBuffer(int i, int i2) {
        checkIndex(i, i2);
        return _internalNioBuffer(i, i2, false);
    }

    public final ByteBuffer nioBuffer(int i, int i2) {
        return duplicateInternalNioBuffer(i, i2).slice();
    }

    public final ByteBuffer[] nioBuffers(int i, int i2) {
        return new ByteBuffer[]{nioBuffer(i, i2)};
    }

    public final int getBytes(int i, GatheringByteChannel gatheringByteChannel, int i2) throws IOException {
        return gatheringByteChannel.write(duplicateInternalNioBuffer(i, i2));
    }

    public final int readBytes(GatheringByteChannel gatheringByteChannel, int i) throws IOException {
        checkReadableBytes(i);
        int write = gatheringByteChannel.write(_internalNioBuffer(this.readerIndex, i, false));
        this.readerIndex += write;
        return write;
    }

    public final int getBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        return fileChannel.write(duplicateInternalNioBuffer(i, i2), j);
    }

    public final int readBytes(FileChannel fileChannel, long j, int i) throws IOException {
        checkReadableBytes(i);
        int write = fileChannel.write(_internalNioBuffer(this.readerIndex, i, false), j);
        this.readerIndex += write;
        return write;
    }

    public final int setBytes(int i, ScatteringByteChannel scatteringByteChannel, int i2) throws IOException {
        try {
            return scatteringByteChannel.read(internalNioBuffer(i, i2));
        } catch (ClosedChannelException unused) {
            return -1;
        }
    }

    public final int setBytes(int i, FileChannel fileChannel, long j, int i2) throws IOException {
        try {
            return fileChannel.read(internalNioBuffer(i, i2), j);
        } catch (ClosedChannelException unused) {
            return -1;
        }
    }
}
