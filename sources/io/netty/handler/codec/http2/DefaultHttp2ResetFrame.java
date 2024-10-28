package io.netty.handler.codec.http2;

import io.ktor.server.auth.OAuth2ResponseParameters;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;

public final class DefaultHttp2ResetFrame extends AbstractHttp2StreamFrame implements Http2ResetFrame {
    private final long errorCode;

    public DefaultHttp2ResetFrame(Http2Error http2Error) {
        this.errorCode = ((Http2Error) ObjectUtil.checkNotNull(http2Error, OAuth2ResponseParameters.Error)).code();
    }

    public DefaultHttp2ResetFrame(long j) {
        this.errorCode = j;
    }

    public DefaultHttp2ResetFrame stream(Http2FrameStream http2FrameStream) {
        super.stream(http2FrameStream);
        return this;
    }

    public String name() {
        return "RST_STREAM";
    }

    public long errorCode() {
        return this.errorCode;
    }

    public String toString() {
        return StringUtil.simpleClassName((Object) this) + "(stream=" + stream() + ", errorCode=" + this.errorCode + ')';
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttp2ResetFrame)) {
            return false;
        }
        DefaultHttp2ResetFrame defaultHttp2ResetFrame = (DefaultHttp2ResetFrame) obj;
        if (!super.equals(obj) || this.errorCode != defaultHttp2ResetFrame.errorCode) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        long j = this.errorCode;
        return (super.hashCode() * 31) + ((int) (j ^ (j >>> 32)));
    }
}
