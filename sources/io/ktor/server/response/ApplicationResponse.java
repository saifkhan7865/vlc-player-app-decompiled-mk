package io.ktor.server.response;

import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H'J\n\u0010\u001a\u001a\u0004\u0018\u00010\u001bH&J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001c\u001a\u00020\u001bH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001d"}, d2 = {"Lio/ktor/server/response/ApplicationResponse;", "", "call", "Lio/ktor/server/application/ApplicationCall;", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "cookies", "Lio/ktor/server/response/ResponseCookies;", "getCookies", "()Lio/ktor/server/response/ResponseCookies;", "headers", "Lio/ktor/server/response/ResponseHeaders;", "getHeaders", "()Lio/ktor/server/response/ResponseHeaders;", "isCommitted", "", "()Z", "isSent", "pipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "getPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "push", "", "builder", "Lio/ktor/server/response/ResponsePushBuilder;", "status", "Lio/ktor/http/HttpStatusCode;", "value", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationResponse.kt */
public interface ApplicationResponse {
    ApplicationCall getCall();

    ResponseCookies getCookies();

    ResponseHeaders getHeaders();

    ApplicationSendPipeline getPipeline();

    boolean isCommitted();

    boolean isSent();

    @UseHttp2Push
    void push(ResponsePushBuilder responsePushBuilder);

    HttpStatusCode status();

    void status(HttpStatusCode httpStatusCode);
}
