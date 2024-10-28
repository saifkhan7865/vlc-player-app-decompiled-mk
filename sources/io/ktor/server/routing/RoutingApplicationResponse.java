package io.ktor.server.routing;

import androidx.core.app.NotificationCompat;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.server.response.ResponseCookies;
import io.ktor.server.response.ResponseHeaders;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007J\u0011\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0001J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0001J\u0011\u0010\u001e\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u001fH\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u0006!"}, d2 = {"Lio/ktor/server/routing/RoutingApplicationResponse;", "Lio/ktor/server/response/ApplicationResponse;", "call", "Lio/ktor/server/routing/RoutingApplicationCall;", "pipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "engineResponse", "(Lio/ktor/server/routing/RoutingApplicationCall;Lio/ktor/server/response/ApplicationSendPipeline;Lio/ktor/server/response/ApplicationResponse;)V", "getCall", "()Lio/ktor/server/routing/RoutingApplicationCall;", "cookies", "Lio/ktor/server/response/ResponseCookies;", "getCookies", "()Lio/ktor/server/response/ResponseCookies;", "getEngineResponse", "()Lio/ktor/server/response/ApplicationResponse;", "headers", "Lio/ktor/server/response/ResponseHeaders;", "getHeaders", "()Lio/ktor/server/response/ResponseHeaders;", "isCommitted", "", "()Z", "isSent", "getPipeline", "()Lio/ktor/server/response/ApplicationSendPipeline;", "push", "", "builder", "Lio/ktor/server/response/ResponsePushBuilder;", "status", "Lio/ktor/http/HttpStatusCode;", "value", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingApplicationCall.kt */
public final class RoutingApplicationResponse implements ApplicationResponse {
    private final RoutingApplicationCall call;
    private final ApplicationResponse engineResponse;
    private final ApplicationSendPipeline pipeline;

    public ResponseCookies getCookies() {
        return this.engineResponse.getCookies();
    }

    public ResponseHeaders getHeaders() {
        return this.engineResponse.getHeaders();
    }

    public boolean isCommitted() {
        return this.engineResponse.isCommitted();
    }

    public boolean isSent() {
        return this.engineResponse.isSent();
    }

    @UseHttp2Push
    public void push(ResponsePushBuilder responsePushBuilder) {
        Intrinsics.checkNotNullParameter(responsePushBuilder, "builder");
        this.engineResponse.push(responsePushBuilder);
    }

    public HttpStatusCode status() {
        return this.engineResponse.status();
    }

    public void status(HttpStatusCode httpStatusCode) {
        Intrinsics.checkNotNullParameter(httpStatusCode, "value");
        this.engineResponse.status(httpStatusCode);
    }

    public RoutingApplicationResponse(RoutingApplicationCall routingApplicationCall, ApplicationSendPipeline applicationSendPipeline, ApplicationResponse applicationResponse) {
        Intrinsics.checkNotNullParameter(routingApplicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(applicationSendPipeline, "pipeline");
        Intrinsics.checkNotNullParameter(applicationResponse, "engineResponse");
        this.call = routingApplicationCall;
        this.pipeline = applicationSendPipeline;
        this.engineResponse = applicationResponse;
    }

    public RoutingApplicationCall getCall() {
        return this.call;
    }

    public ApplicationSendPipeline getPipeline() {
        return this.pipeline;
    }

    public final ApplicationResponse getEngineResponse() {
        return this.engineResponse;
    }
}
