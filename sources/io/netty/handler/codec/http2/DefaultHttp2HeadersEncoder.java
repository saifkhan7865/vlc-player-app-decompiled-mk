package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http2.Http2HeadersEncoder;
import io.netty.util.internal.ObjectUtil;

public class DefaultHttp2HeadersEncoder implements Http2HeadersEncoder, Http2HeadersEncoder.Configuration {
    private final HpackEncoder hpackEncoder;
    private final Http2HeadersEncoder.SensitivityDetector sensitivityDetector;
    private ByteBuf tableSizeChangeOutput;

    public Http2HeadersEncoder.Configuration configuration() {
        return this;
    }

    public DefaultHttp2HeadersEncoder() {
        this(NEVER_SENSITIVE);
    }

    public DefaultHttp2HeadersEncoder(Http2HeadersEncoder.SensitivityDetector sensitivityDetector2) {
        this(sensitivityDetector2, new HpackEncoder());
    }

    public DefaultHttp2HeadersEncoder(Http2HeadersEncoder.SensitivityDetector sensitivityDetector2, boolean z) {
        this(sensitivityDetector2, new HpackEncoder(z));
    }

    public DefaultHttp2HeadersEncoder(Http2HeadersEncoder.SensitivityDetector sensitivityDetector2, boolean z, int i) {
        this(sensitivityDetector2, z, i, 512);
    }

    public DefaultHttp2HeadersEncoder(Http2HeadersEncoder.SensitivityDetector sensitivityDetector2, boolean z, int i, int i2) {
        this(sensitivityDetector2, new HpackEncoder(z, i, i2));
    }

    DefaultHttp2HeadersEncoder(Http2HeadersEncoder.SensitivityDetector sensitivityDetector2, HpackEncoder hpackEncoder2) {
        this.sensitivityDetector = (Http2HeadersEncoder.SensitivityDetector) ObjectUtil.checkNotNull(sensitivityDetector2, "sensitiveDetector");
        this.hpackEncoder = (HpackEncoder) ObjectUtil.checkNotNull(hpackEncoder2, "hpackEncoder");
    }

    public void encodeHeaders(int i, Http2Headers http2Headers, ByteBuf byteBuf) throws Http2Exception {
        try {
            ByteBuf byteBuf2 = this.tableSizeChangeOutput;
            if (byteBuf2 != null && byteBuf2.isReadable()) {
                byteBuf.writeBytes(this.tableSizeChangeOutput);
                this.tableSizeChangeOutput.clear();
            }
            this.hpackEncoder.encodeHeaders(i, byteBuf, http2Headers, this.sensitivityDetector);
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, th, "Failed encoding headers block: %s", th.getMessage());
        }
    }

    public void maxHeaderTableSize(long j) throws Http2Exception {
        if (this.tableSizeChangeOutput == null) {
            this.tableSizeChangeOutput = Unpooled.buffer();
        }
        this.hpackEncoder.setMaxHeaderTableSize(this.tableSizeChangeOutput, j);
    }

    public long maxHeaderTableSize() {
        return this.hpackEncoder.getMaxHeaderTableSize();
    }

    public void maxHeaderListSize(long j) throws Http2Exception {
        this.hpackEncoder.setMaxHeaderListSize(j);
    }

    public long maxHeaderListSize() {
        return this.hpackEncoder.getMaxHeaderListSize();
    }
}
