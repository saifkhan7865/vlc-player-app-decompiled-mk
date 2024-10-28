package io.netty.handler.codec.http;

import io.netty.util.internal.ObjectUtil;

public abstract class DefaultHttpMessage extends DefaultHttpObject implements HttpMessage {
    private static final int HASH_CODE_PRIME = 31;
    private final HttpHeaders headers;
    private HttpVersion version;

    protected DefaultHttpMessage(HttpVersion httpVersion) {
        this(httpVersion, true, false);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    protected DefaultHttpMessage(HttpVersion httpVersion, boolean z, boolean z2) {
        this(httpVersion, z2 ? new CombinedHttpHeaders(z) : new DefaultHttpHeaders(z));
    }

    protected DefaultHttpMessage(HttpVersion httpVersion, HttpHeaders httpHeaders) {
        this.version = (HttpVersion) ObjectUtil.checkNotNull(httpVersion, "version");
        this.headers = (HttpHeaders) ObjectUtil.checkNotNull(httpHeaders, "headers");
    }

    public HttpHeaders headers() {
        return this.headers;
    }

    @Deprecated
    public HttpVersion getProtocolVersion() {
        return protocolVersion();
    }

    public HttpVersion protocolVersion() {
        return this.version;
    }

    public int hashCode() {
        return ((((this.headers.hashCode() + 31) * 31) + this.version.hashCode()) * 31) + super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttpMessage)) {
            return false;
        }
        DefaultHttpMessage defaultHttpMessage = (DefaultHttpMessage) obj;
        if (!headers().equals(defaultHttpMessage.headers()) || !protocolVersion().equals(defaultHttpMessage.protocolVersion()) || !super.equals(obj)) {
            return false;
        }
        return true;
    }

    public HttpMessage setProtocolVersion(HttpVersion httpVersion) {
        this.version = (HttpVersion) ObjectUtil.checkNotNull(httpVersion, "version");
        return this;
    }
}
