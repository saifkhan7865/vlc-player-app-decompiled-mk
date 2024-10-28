package io.netty.handler.codec.http;

import io.netty.handler.codec.DecoderResult;
import io.netty.util.internal.ObjectUtil;

public class DefaultHttpObject implements HttpObject {
    private static final int HASH_CODE_PRIME = 31;
    private DecoderResult decoderResult = DecoderResult.SUCCESS;

    protected DefaultHttpObject() {
    }

    public DecoderResult decoderResult() {
        return this.decoderResult;
    }

    @Deprecated
    public DecoderResult getDecoderResult() {
        return decoderResult();
    }

    public void setDecoderResult(DecoderResult decoderResult2) {
        this.decoderResult = (DecoderResult) ObjectUtil.checkNotNull(decoderResult2, "decoderResult");
    }

    public int hashCode() {
        return 31 + this.decoderResult.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttpObject)) {
            return false;
        }
        return decoderResult().equals(((DefaultHttpObject) obj).decoderResult());
    }
}
