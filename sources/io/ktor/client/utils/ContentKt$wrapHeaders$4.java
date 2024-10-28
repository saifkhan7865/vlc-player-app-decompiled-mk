package io.ktor.client.utils;

import io.ktor.http.ContentType;
import io.ktor.http.Headers;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"io/ktor/client/utils/ContentKt$wrapHeaders$4", "Lio/ktor/http/content/OutgoingContent$ByteArrayContent;", "contentLength", "", "getContentLength", "()Ljava/lang/Long;", "contentType", "Lio/ktor/http/ContentType;", "getContentType", "()Lio/ktor/http/ContentType;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "bytes", "", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Content.kt */
public final class ContentKt$wrapHeaders$4 extends OutgoingContent.ByteArrayContent {
    final /* synthetic */ OutgoingContent $this_wrapHeaders;
    private final Headers headers;

    ContentKt$wrapHeaders$4(Function1<? super Headers, ? extends Headers> function1, OutgoingContent outgoingContent) {
        this.$this_wrapHeaders = outgoingContent;
        this.headers = (Headers) function1.invoke(outgoingContent.getHeaders());
    }

    public Long getContentLength() {
        return this.$this_wrapHeaders.getContentLength();
    }

    public ContentType getContentType() {
        return this.$this_wrapHeaders.getContentType();
    }

    public HttpStatusCode getStatus() {
        return this.$this_wrapHeaders.getStatus();
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public byte[] bytes() {
        return ((OutgoingContent.ByteArrayContent) this.$this_wrapHeaders).bytes();
    }
}
