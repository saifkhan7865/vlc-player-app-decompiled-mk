package io.netty.handler.codec.http2;

public final class InboundHttp2ToHttpAdapterBuilder extends AbstractInboundHttp2ToHttpAdapterBuilder<InboundHttp2ToHttpAdapter, InboundHttp2ToHttpAdapterBuilder> {
    public InboundHttp2ToHttpAdapterBuilder(Http2Connection http2Connection) {
        super(http2Connection);
    }

    public InboundHttp2ToHttpAdapterBuilder maxContentLength(int i) {
        return (InboundHttp2ToHttpAdapterBuilder) super.maxContentLength(i);
    }

    public InboundHttp2ToHttpAdapterBuilder validateHttpHeaders(boolean z) {
        return (InboundHttp2ToHttpAdapterBuilder) super.validateHttpHeaders(z);
    }

    public InboundHttp2ToHttpAdapterBuilder propagateSettings(boolean z) {
        return (InboundHttp2ToHttpAdapterBuilder) super.propagateSettings(z);
    }

    public InboundHttp2ToHttpAdapter build() {
        return super.build();
    }

    /* access modifiers changed from: protected */
    public InboundHttp2ToHttpAdapter build(Http2Connection http2Connection, int i, boolean z, boolean z2) throws Exception {
        return new InboundHttp2ToHttpAdapter(http2Connection, i, z, z2);
    }
}
