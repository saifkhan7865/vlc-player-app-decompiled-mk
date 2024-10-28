package io.ktor.server.routing;

import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Parameters;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingApplicationCall.kt */
final class RoutingApplicationCall$parameters$2 extends Lambda implements Function0<Parameters> {
    final /* synthetic */ Parameters $parameters;
    final /* synthetic */ RoutingApplicationCall this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RoutingApplicationCall$parameters$2(RoutingApplicationCall routingApplicationCall, Parameters parameters) {
        super(0);
        this.this$0 = routingApplicationCall;
        this.$parameters = parameters;
    }

    public final Parameters invoke() {
        Parameters.Companion companion = Parameters.Companion;
        RoutingApplicationCall routingApplicationCall = this.this$0;
        Parameters parameters = this.$parameters;
        ParametersBuilder ParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, (Object) null);
        ParametersBuilder$default.appendAll(routingApplicationCall.getEngineCall().getParameters());
        ParametersBuilder$default.appendMissing(parameters);
        return ParametersBuilder$default.build();
    }
}
