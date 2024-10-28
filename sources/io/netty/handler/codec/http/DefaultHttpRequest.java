package io.netty.handler.codec.http;

import io.netty.util.internal.ObjectUtil;
import org.videolan.resources.Constants;

public class DefaultHttpRequest extends DefaultHttpMessage implements HttpRequest {
    private static final int HASH_CODE_PRIME = 31;
    private HttpMethod method;
    private String uri;

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod httpMethod, String str) {
        this(httpVersion, httpMethod, str, true);
    }

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod httpMethod, String str, boolean z) {
        super(httpVersion, z, false);
        this.method = (HttpMethod) ObjectUtil.checkNotNull(httpMethod, "method");
        this.uri = (String) ObjectUtil.checkNotNull(str, Constants.KEY_URI);
    }

    public DefaultHttpRequest(HttpVersion httpVersion, HttpMethod httpMethod, String str, HttpHeaders httpHeaders) {
        super(httpVersion, httpHeaders);
        this.method = (HttpMethod) ObjectUtil.checkNotNull(httpMethod, "method");
        this.uri = (String) ObjectUtil.checkNotNull(str, Constants.KEY_URI);
    }

    @Deprecated
    public HttpMethod getMethod() {
        return method();
    }

    public HttpMethod method() {
        return this.method;
    }

    @Deprecated
    public String getUri() {
        return uri();
    }

    public String uri() {
        return this.uri;
    }

    public HttpRequest setMethod(HttpMethod httpMethod) {
        this.method = (HttpMethod) ObjectUtil.checkNotNull(httpMethod, "method");
        return this;
    }

    public HttpRequest setUri(String str) {
        this.uri = (String) ObjectUtil.checkNotNull(str, Constants.KEY_URI);
        return this;
    }

    public HttpRequest setProtocolVersion(HttpVersion httpVersion) {
        super.setProtocolVersion(httpVersion);
        return this;
    }

    public int hashCode() {
        return ((((this.method.hashCode() + 31) * 31) + this.uri.hashCode()) * 31) + super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DefaultHttpRequest)) {
            return false;
        }
        DefaultHttpRequest defaultHttpRequest = (DefaultHttpRequest) obj;
        if (!method().equals(defaultHttpRequest.method()) || !uri().equalsIgnoreCase(defaultHttpRequest.uri()) || !super.equals(obj)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return HttpMessageUtil.appendRequest(new StringBuilder(256), this).toString();
    }
}
