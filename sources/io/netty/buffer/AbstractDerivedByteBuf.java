package io.netty.buffer;

import java.nio.ByteBuffer;

@Deprecated
public abstract class AbstractDerivedByteBuf extends AbstractByteBuf {
    protected AbstractDerivedByteBuf(int i) {
        super(i);
    }

    /* access modifiers changed from: package-private */
    public final boolean isAccessible() {
        return isAccessible0();
    }

    /* access modifiers changed from: package-private */
    public boolean isAccessible0() {
        return unwrap().isAccessible();
    }

    public final int refCnt() {
        return refCnt0();
    }

    /* access modifiers changed from: package-private */
    public int refCnt0() {
        return unwrap().refCnt();
    }

    public final ByteBuf retain() {
        return retain0();
    }

    /* access modifiers changed from: package-private */
    public ByteBuf retain0() {
        unwrap().retain();
        return this;
    }

    public final ByteBuf retain(int i) {
        return retain0(i);
    }

    /* access modifiers changed from: package-private */
    public ByteBuf retain0(int i) {
        unwrap().retain(i);
        return this;
    }

    public final ByteBuf touch() {
        return touch0();
    }

    /* access modifiers changed from: package-private */
    public ByteBuf touch0() {
        unwrap().touch();
        return this;
    }

    public final ByteBuf touch(Object obj) {
        return touch0(obj);
    }

    /* access modifiers changed from: package-private */
    public ByteBuf touch0(Object obj) {
        unwrap().touch(obj);
        return this;
    }

    public final boolean release() {
        return release0();
    }

    /* access modifiers changed from: package-private */
    public boolean release0() {
        return unwrap().release();
    }

    public final boolean release(int i) {
        return release0(i);
    }

    /* access modifiers changed from: package-private */
    public boolean release0(int i) {
        return unwrap().release(i);
    }

    public boolean isReadOnly() {
        return unwrap().isReadOnly();
    }

    public ByteBuffer internalNioBuffer(int i, int i2) {
        return nioBuffer(i, i2);
    }

    public ByteBuffer nioBuffer(int i, int i2) {
        return unwrap().nioBuffer(i, i2);
    }

    public boolean isContiguous() {
        return unwrap().isContiguous();
    }
}
