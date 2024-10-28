package io.ktor.server.http.content;

import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\b\u001a\u00020\tH\u0016R\u0014\u0010\u0005\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/ktor/server/http/content/HttpStatusCodeContent;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "value", "Lio/ktor/http/HttpStatusCode;", "(Lio/ktor/http/HttpStatusCode;)V", "status", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpStatusCodeContent.kt */
public final class HttpStatusCodeContent extends OutgoingContent.NoContent {
    private final HttpStatusCode value;

    public HttpStatusCodeContent(HttpStatusCode httpStatusCode) {
        Intrinsics.checkNotNullParameter(httpStatusCode, "value");
        this.value = httpStatusCode;
    }

    public HttpStatusCode getStatus() {
        return this.value;
    }

    public String toString() {
        return "HttpStatusCodeContent(" + this.value + ')';
    }
}
