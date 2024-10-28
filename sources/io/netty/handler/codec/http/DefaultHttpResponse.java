package io.netty.handler.codec.http;

import androidx.core.app.NotificationCompat;
import io.netty.util.internal.ObjectUtil;

public class DefaultHttpResponse extends DefaultHttpMessage implements HttpResponse {
    private HttpResponseStatus status;

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus) {
        this(httpVersion, httpResponseStatus, true, false);
    }

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus, boolean z) {
        this(httpVersion, httpResponseStatus, z, false);
    }

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus, boolean z, boolean z2) {
        super(httpVersion, z, z2);
        this.status = (HttpResponseStatus) ObjectUtil.checkNotNull(httpResponseStatus, NotificationCompat.CATEGORY_STATUS);
    }

    public DefaultHttpResponse(HttpVersion httpVersion, HttpResponseStatus httpResponseStatus, HttpHeaders httpHeaders) {
        super(httpVersion, httpHeaders);
        this.status = (HttpResponseStatus) ObjectUtil.checkNotNull(httpResponseStatus, NotificationCompat.CATEGORY_STATUS);
    }

    @Deprecated
    public HttpResponseStatus getStatus() {
        return status();
    }

    public HttpResponseStatus status() {
        return this.status;
    }

    public HttpResponse setStatus(HttpResponseStatus httpResponseStatus) {
        this.status = (HttpResponseStatus) ObjectUtil.checkNotNull(httpResponseStatus, NotificationCompat.CATEGORY_STATUS);
        return this;
    }

    public HttpResponse setProtocolVersion(HttpVersion httpVersion) {
        super.setProtocolVersion(httpVersion);
        return this;
    }

    public String toString() {
        return HttpMessageUtil.appendResponse(new StringBuilder(256), this).toString();
    }

    public int hashCode() {
        return ((this.status.hashCode() + 31) * 31) + super.hashCode();
    }

    public boolean equals(Object obj) {
        if ((obj instanceof DefaultHttpResponse) && this.status.equals(((DefaultHttpResponse) obj).status()) && super.equals(obj)) {
            return true;
        }
        return false;
    }
}
