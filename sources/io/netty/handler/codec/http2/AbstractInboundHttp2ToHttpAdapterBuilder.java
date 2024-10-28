package io.netty.handler.codec.http2;

import io.netty.handler.codec.http2.AbstractInboundHttp2ToHttpAdapterBuilder;
import io.netty.handler.codec.http2.InboundHttp2ToHttpAdapter;
import io.netty.util.internal.ObjectUtil;

public abstract class AbstractInboundHttp2ToHttpAdapterBuilder<T extends InboundHttp2ToHttpAdapter, B extends AbstractInboundHttp2ToHttpAdapterBuilder<T, B>> {
    private final Http2Connection connection;
    private int maxContentLength;
    private boolean propagateSettings;
    private boolean validateHttpHeaders;

    /* access modifiers changed from: protected */
    public abstract T build(Http2Connection http2Connection, int i, boolean z, boolean z2) throws Exception;

    /* access modifiers changed from: protected */
    public final B self() {
        return this;
    }

    protected AbstractInboundHttp2ToHttpAdapterBuilder(Http2Connection http2Connection) {
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
    }

    /* access modifiers changed from: protected */
    public Http2Connection connection() {
        return this.connection;
    }

    /* access modifiers changed from: protected */
    public int maxContentLength() {
        return this.maxContentLength;
    }

    /* access modifiers changed from: protected */
    public B maxContentLength(int i) {
        this.maxContentLength = i;
        return self();
    }

    /* access modifiers changed from: protected */
    public boolean isValidateHttpHeaders() {
        return this.validateHttpHeaders;
    }

    /* access modifiers changed from: protected */
    public B validateHttpHeaders(boolean z) {
        this.validateHttpHeaders = z;
        return self();
    }

    /* access modifiers changed from: protected */
    public boolean isPropagateSettings() {
        return this.propagateSettings;
    }

    /* access modifiers changed from: protected */
    public B propagateSettings(boolean z) {
        this.propagateSettings = z;
        return self();
    }

    /* access modifiers changed from: protected */
    public T build() {
        try {
            T build = build(connection(), maxContentLength(), isValidateHttpHeaders(), isPropagateSettings());
            this.connection.addListener(build);
            return build;
        } catch (Throwable th) {
            throw new IllegalStateException("failed to create a new InboundHttp2ToHttpAdapter", th);
        }
    }
}
