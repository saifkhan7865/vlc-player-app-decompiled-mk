package io.ktor.server.routing;

import io.ktor.http.Parameters;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationReceivePipeline;
import io.ktor.server.response.ApplicationSendPipeline;
import io.ktor.util.Attributes;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B5\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\b\u0010)\u001a\u00020*H\u0016R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0003\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001b\u0010\f\u001a\u00020\r8VX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\u001f\u001a\u00020 X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020$X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006+"}, d2 = {"Lio/ktor/server/routing/RoutingApplicationCall;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlinx/coroutines/CoroutineScope;", "engineCall", "route", "Lio/ktor/server/routing/Route;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "receivePipeline", "Lio/ktor/server/request/ApplicationReceivePipeline;", "responsePipeline", "Lio/ktor/server/response/ApplicationSendPipeline;", "parameters", "Lio/ktor/http/Parameters;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/server/routing/Route;Lkotlin/coroutines/CoroutineContext;Lio/ktor/server/request/ApplicationReceivePipeline;Lio/ktor/server/response/ApplicationSendPipeline;Lio/ktor/http/Parameters;)V", "application", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "attributes", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getEngineCall", "()Lio/ktor/server/application/ApplicationCall;", "getParameters", "()Lio/ktor/http/Parameters;", "parameters$delegate", "Lkotlin/Lazy;", "request", "Lio/ktor/server/routing/RoutingApplicationRequest;", "getRequest", "()Lio/ktor/server/routing/RoutingApplicationRequest;", "response", "Lio/ktor/server/routing/RoutingApplicationResponse;", "getResponse", "()Lio/ktor/server/routing/RoutingApplicationResponse;", "getRoute", "()Lio/ktor/server/routing/Route;", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingApplicationCall.kt */
public final class RoutingApplicationCall implements ApplicationCall, CoroutineScope {
    private final CoroutineContext coroutineContext;
    private final ApplicationCall engineCall;
    private final Lazy parameters$delegate;
    private final RoutingApplicationRequest request;
    private final RoutingApplicationResponse response;
    private final Route route;

    public RoutingApplicationCall(ApplicationCall applicationCall, Route route2, CoroutineContext coroutineContext2, ApplicationReceivePipeline applicationReceivePipeline, ApplicationSendPipeline applicationSendPipeline, Parameters parameters) {
        Intrinsics.checkNotNullParameter(applicationCall, "engineCall");
        Intrinsics.checkNotNullParameter(route2, "route");
        Intrinsics.checkNotNullParameter(coroutineContext2, "coroutineContext");
        Intrinsics.checkNotNullParameter(applicationReceivePipeline, "receivePipeline");
        Intrinsics.checkNotNullParameter(applicationSendPipeline, "responsePipeline");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        this.engineCall = applicationCall;
        this.route = route2;
        this.coroutineContext = coroutineContext2;
        this.request = new RoutingApplicationRequest(this, applicationReceivePipeline, applicationCall.getRequest());
        this.response = new RoutingApplicationResponse(this, applicationSendPipeline, applicationCall.getResponse());
        this.parameters$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new RoutingApplicationCall$parameters$2(this, parameters));
    }

    public final ApplicationCall getEngineCall() {
        return this.engineCall;
    }

    public final Route getRoute() {
        return this.route;
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public Application getApplication() {
        return this.engineCall.getApplication();
    }

    public Attributes getAttributes() {
        return this.engineCall.getAttributes();
    }

    public RoutingApplicationRequest getRequest() {
        return this.request;
    }

    public RoutingApplicationResponse getResponse() {
        return this.response;
    }

    public Parameters getParameters() {
        return (Parameters) this.parameters$delegate.getValue();
    }

    public String toString() {
        return "RoutingApplicationCall(route=" + this.route + ')';
    }
}
