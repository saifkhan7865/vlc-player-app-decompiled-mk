package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http2.Http2HeadersDecoder;
import io.netty.util.internal.ObjectUtil;

public class DefaultHttp2HeadersDecoder implements Http2HeadersDecoder, Http2HeadersDecoder.Configuration {
    private static final float HEADERS_COUNT_WEIGHT_HISTORICAL = 0.8f;
    private static final float HEADERS_COUNT_WEIGHT_NEW = 0.2f;
    private float headerArraySizeAccumulator;
    private final HpackDecoder hpackDecoder;
    private long maxHeaderListSizeGoAway;
    private final boolean validateHeaderValues;
    private final boolean validateHeaders;

    public Http2HeadersDecoder.Configuration configuration() {
        return this;
    }

    public DefaultHttp2HeadersDecoder() {
        this(true);
    }

    public DefaultHttp2HeadersDecoder(boolean z) {
        this(z, 8192);
    }

    public DefaultHttp2HeadersDecoder(boolean z, boolean z2) {
        this(z, z2, 8192);
    }

    public DefaultHttp2HeadersDecoder(boolean z, long j) {
        this(z, false, new HpackDecoder(j));
    }

    public DefaultHttp2HeadersDecoder(boolean z, boolean z2, long j) {
        this(z, z2, new HpackDecoder(j));
    }

    public DefaultHttp2HeadersDecoder(boolean z, long j, @Deprecated int i) {
        this(z, false, new HpackDecoder(j));
    }

    DefaultHttp2HeadersDecoder(boolean z, boolean z2, HpackDecoder hpackDecoder2) {
        this.headerArraySizeAccumulator = 8.0f;
        this.hpackDecoder = (HpackDecoder) ObjectUtil.checkNotNull(hpackDecoder2, "hpackDecoder");
        this.validateHeaders = z;
        this.validateHeaderValues = z2;
        this.maxHeaderListSizeGoAway = Http2CodecUtil.calculateMaxHeaderListSizeGoAway(hpackDecoder2.getMaxHeaderListSize());
    }

    public void maxHeaderTableSize(long j) throws Http2Exception {
        this.hpackDecoder.setMaxHeaderTableSize(j);
    }

    public long maxHeaderTableSize() {
        return this.hpackDecoder.getMaxHeaderTableSize();
    }

    public void maxHeaderListSize(long j, long j2) throws Http2Exception {
        if (j2 < j || j2 < 0) {
            throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, "Header List Size GO_AWAY %d must be non-negative and >= %d", Long.valueOf(j2), Long.valueOf(j));
        }
        this.hpackDecoder.setMaxHeaderListSize(j);
        this.maxHeaderListSizeGoAway = j2;
    }

    public long maxHeaderListSize() {
        return this.hpackDecoder.getMaxHeaderListSize();
    }

    public long maxHeaderListSizeGoAway() {
        return this.maxHeaderListSizeGoAway;
    }

    public Http2Headers decodeHeaders(int i, ByteBuf byteBuf) throws Http2Exception {
        try {
            Http2Headers newHeaders = newHeaders();
            this.hpackDecoder.decode(i, byteBuf, newHeaders, this.validateHeaders);
            this.headerArraySizeAccumulator = (((float) newHeaders.size()) * 0.2f) + (this.headerArraySizeAccumulator * HEADERS_COUNT_WEIGHT_HISTORICAL);
            return newHeaders;
        } catch (Http2Exception e) {
            throw e;
        } catch (Throwable th) {
            throw Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, th, "Error decoding headers: %s", th.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    public final int numberOfHeadersGuess() {
        return (int) this.headerArraySizeAccumulator;
    }

    /* access modifiers changed from: protected */
    public final boolean validateHeaders() {
        return this.validateHeaders;
    }

    /* access modifiers changed from: protected */
    public boolean validateHeaderValues() {
        return this.validateHeaderValues;
    }

    /* access modifiers changed from: protected */
    public Http2Headers newHeaders() {
        return new DefaultHttp2Headers(this.validateHeaders, this.validateHeaderValues, (int) this.headerArraySizeAccumulator);
    }
}
