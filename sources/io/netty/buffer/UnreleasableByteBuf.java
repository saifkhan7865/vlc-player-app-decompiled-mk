package io.netty.buffer;

import io.netty.util.internal.ObjectUtil;
import java.nio.ByteOrder;

final class UnreleasableByteBuf extends WrappedByteBuf {
    private SwappedByteBuf swappedBuf;

    public boolean release() {
        return false;
    }

    public boolean release(int i) {
        return false;
    }

    public ByteBuf retain() {
        return this;
    }

    public ByteBuf retain(int i) {
        return this;
    }

    public ByteBuf touch() {
        return this;
    }

    public ByteBuf touch(Object obj) {
        return this;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UnreleasableByteBuf(ByteBuf byteBuf) {
        super(byteBuf instanceof UnreleasableByteBuf ? byteBuf.unwrap() : byteBuf);
    }

    public ByteBuf order(ByteOrder byteOrder) {
        if (ObjectUtil.checkNotNull(byteOrder, "endianness") == order()) {
            return this;
        }
        SwappedByteBuf swappedByteBuf = this.swappedBuf;
        if (swappedByteBuf != null) {
            return swappedByteBuf;
        }
        SwappedByteBuf swappedByteBuf2 = new SwappedByteBuf(this);
        this.swappedBuf = swappedByteBuf2;
        return swappedByteBuf2;
    }

    public ByteBuf asReadOnly() {
        return this.buf.isReadOnly() ? this : new UnreleasableByteBuf(this.buf.asReadOnly());
    }

    public ByteBuf readSlice(int i) {
        return new UnreleasableByteBuf(this.buf.readSlice(i));
    }

    public ByteBuf readRetainedSlice(int i) {
        return readSlice(i);
    }

    public ByteBuf slice() {
        return new UnreleasableByteBuf(this.buf.slice());
    }

    public ByteBuf retainedSlice() {
        return slice();
    }

    public ByteBuf slice(int i, int i2) {
        return new UnreleasableByteBuf(this.buf.slice(i, i2));
    }

    public ByteBuf retainedSlice(int i, int i2) {
        return slice(i, i2);
    }

    public ByteBuf duplicate() {
        return new UnreleasableByteBuf(this.buf.duplicate());
    }

    public ByteBuf retainedDuplicate() {
        return duplicate();
    }
}
