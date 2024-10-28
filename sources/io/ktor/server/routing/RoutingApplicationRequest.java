package io.ktor.server.routing;

import androidx.core.app.NotificationCompat;
import io.ktor.http.Headers;
import io.ktor.http.Parameters;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.request.ApplicationRequest;
import io.ktor.server.request.RequestCookies;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007J\t\u0010 \u001a\u00020!H\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0015X\u0005¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX\u0005¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0012\u0010\u001e\u001a\u00020\u001bX\u0005¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001d¨\u0006\""}, d2 = {"Lio/ktor/server/routing/RoutingApplicationRequest;", "Lio/ktor/server/request/ApplicationRequest;", "call", "Lio/ktor/server/routing/RoutingApplicationCall;", "pipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "engineRequest", "(Lio/ktor/server/routing/RoutingApplicationCall;Lio/ktor/server/request/ApplicationReceivePipeline;Lio/ktor/server/request/ApplicationRequest;)V", "getCall", "()Lio/ktor/server/routing/RoutingApplicationCall;", "cookies", "Lio/ktor/server/request/RequestCookies;", "getCookies", "()Lio/ktor/server/request/RequestCookies;", "getEngineRequest", "()Lio/ktor/server/request/ApplicationRequest;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "local", "Lio/ktor/http/RequestConnectionPoint;", "getLocal", "()Lio/ktor/http/RequestConnectionPoint;", "getPipeline", "()Lio/ktor/server/request/ApplicationReceivePipeline;", "queryParameters", "Lio/ktor/http/Parameters;", "getQueryParameters", "()Lio/ktor/http/Parameters;", "rawQueryParameters", "getRawQueryParameters", "receiveChannel", "Lio/ktor/utils/io/ByteReadChannel;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingApplicationCall.kt */
public final class RoutingApplicationRequest implements ApplicationRequest {
    private final RoutingApplicationCall call;
    private final ApplicationRequest engineRequest;
    private final ApplicationReceivePipeline pipeline;

    public RequestCookies getCookies() {
        return this.engineRequest.getCookies();
    }

    public Headers getHeaders() {
        return this.engineRequest.getHeaders();
    }

    public RequestConnectionPoint getLocal() {
        return this.engineRequest.getLocal();
    }

    public Parameters getQueryParameters() {
        return this.engineRequest.getQueryParameters();
    }

    public Parameters getRawQueryParameters() {
        return this.engineRequest.getRawQueryParameters();
    }

    public ByteReadChannel receiveChannel() {
        return this.engineRequest.receiveChannel();
    }

    public RoutingApplicationRequest(RoutingApplicationCall routingApplicationCall, ApplicationReceivePipeline applicationReceivePipeline, ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(routingApplicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(applicationReceivePipeline, "pipeline");
        Intrinsics.checkNotNullParameter(applicationRequest, "engineRequest");
        this.call = routingApplicationCall;
        this.pipeline = applicationReceivePipeline;
        this.engineRequest = applicationRequest;
    }

    public RoutingApplicationCall getCall() {
        return this.call;
    }

    public ApplicationReceivePipeline getPipeline() {
        return this.pipeline;
    }

    public final ApplicationRequest getEngineRequest() {
        return this.engineRequest;
    }
}
