package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.CharsetUtil;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.internal.ObjectUtil;
import java.security.PrivateKey;

public final class PemPrivateKey extends AbstractReferenceCounted implements PrivateKey, PemEncoded {
    private static final byte[] BEGIN_PRIVATE_KEY = "-----BEGIN PRIVATE KEY-----\n".getBytes(CharsetUtil.US_ASCII);
    private static final byte[] END_PRIVATE_KEY = "\n-----END PRIVATE KEY-----\n".getBytes(CharsetUtil.US_ASCII);
    private static final String PKCS8_FORMAT = "PKCS#8";
    private static final long serialVersionUID = 7978017465645018936L;
    private final ByteBuf content;

    public boolean isSensitive() {
        return true;
    }

    static PemEncoded toPEM(ByteBufAllocator byteBufAllocator, boolean z, PrivateKey privateKey) {
        if (privateKey instanceof PemEncoded) {
            return ((PemEncoded) privateKey).retain();
        }
        byte[] encoded = privateKey.getEncoded();
        if (encoded != null) {
            return toPEM(byteBufAllocator, z, encoded);
        }
        throw new IllegalArgumentException(privateKey.getClass().getName() + " does not support encoding");
    }

    static PemEncoded toPEM(ByteBufAllocator byteBufAllocator, boolean z, byte[] bArr) {
        ByteBuf directBuffer;
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(bArr);
        try {
            wrappedBuffer = SslUtils.toBase64(byteBufAllocator, wrappedBuffer);
            try {
                byte[] bArr2 = BEGIN_PRIVATE_KEY;
                int length = bArr2.length + wrappedBuffer.readableBytes();
                byte[] bArr3 = END_PRIVATE_KEY;
                int length2 = length + bArr3.length;
                directBuffer = z ? byteBufAllocator.directBuffer(length2) : byteBufAllocator.buffer(length2);
                directBuffer.writeBytes(bArr2);
                directBuffer.writeBytes(wrappedBuffer);
                directBuffer.writeBytes(bArr3);
                PemValue pemValue = new PemValue(directBuffer, true);
                SslUtils.zerooutAndRelease(wrappedBuffer);
                return pemValue;
            } catch (Throwable th) {
                SslUtils.zerooutAndRelease(wrappedBuffer);
                throw th;
            }
        } finally {
            SslUtils.zerooutAndRelease(wrappedBuffer);
        }
    }

    public static PemPrivateKey valueOf(byte[] bArr) {
        return valueOf(Unpooled.wrappedBuffer(bArr));
    }

    public static PemPrivateKey valueOf(ByteBuf byteBuf) {
        return new PemPrivateKey(byteBuf);
    }

    private PemPrivateKey(ByteBuf byteBuf) {
        this.content = (ByteBuf) ObjectUtil.checkNotNull(byteBuf, "content");
    }

    public ByteBuf content() {
        int refCnt = refCnt();
        if (refCnt > 0) {
            return this.content;
        }
        throw new IllegalReferenceCountException(refCnt);
    }

    public PemPrivateKey copy() {
        return replace(this.content.copy());
    }

    public PemPrivateKey duplicate() {
        return replace(this.content.duplicate());
    }

    public PemPrivateKey retainedDuplicate() {
        return replace(this.content.retainedDuplicate());
    }

    public PemPrivateKey replace(ByteBuf byteBuf) {
        return new PemPrivateKey(byteBuf);
    }

    public PemPrivateKey touch() {
        this.content.touch();
        return this;
    }

    public PemPrivateKey touch(Object obj) {
        this.content.touch(obj);
        return this;
    }

    public PemPrivateKey retain() {
        return (PemPrivateKey) super.retain();
    }

    public PemPrivateKey retain(int i) {
        return (PemPrivateKey) super.retain(i);
    }

    /* access modifiers changed from: protected */
    public void deallocate() {
        SslUtils.zerooutAndRelease(this.content);
    }

    public byte[] getEncoded() {
        throw new UnsupportedOperationException();
    }

    public String getAlgorithm() {
        throw new UnsupportedOperationException();
    }

    public String getFormat() {
        return PKCS8_FORMAT;
    }

    public void destroy() {
        release(refCnt());
    }

    public boolean isDestroyed() {
        return refCnt() == 0;
    }
}
