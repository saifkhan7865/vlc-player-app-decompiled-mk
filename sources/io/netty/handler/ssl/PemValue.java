package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;

class PemValue extends AbstractReferenceCounted implements PemEncoded {
    private final ByteBuf content;
    private final boolean sensitive;

    PemValue(ByteBuf byteBuf, boolean z) {
        this.content = (ByteBuf) ObjectUtil.checkNotNull(byteBuf, "content");
        this.sensitive = z;
    }

    public boolean isSensitive() {
        return this.sensitive;
    }

    public ByteBuf content() {
        int refCnt = refCnt();
        if (refCnt > 0) {
            return this.content;
        }
        throw new IllegalReferenceCountException(refCnt);
    }

    public PemValue copy() {
        return replace(this.content.copy());
    }

    public PemValue duplicate() {
        return replace(this.content.duplicate());
    }

    public PemValue retainedDuplicate() {
        return replace(this.content.retainedDuplicate());
    }

    public PemValue replace(ByteBuf byteBuf) {
        return new PemValue(byteBuf, this.sensitive);
    }

    public PemValue touch() {
        return (PemValue) super.touch();
    }

    public PemValue touch(Object obj) {
        this.content.touch(obj);
        return this;
    }

    public PemValue retain() {
        return (PemValue) super.retain();
    }

    public PemValue retain(int i) {
        return (PemValue) super.retain(i);
    }

    /* access modifiers changed from: protected */
    public void deallocate() {
        if (this.sensitive) {
            SslUtils.zeroout(this.content);
        }
        this.content.release();
    }
}
