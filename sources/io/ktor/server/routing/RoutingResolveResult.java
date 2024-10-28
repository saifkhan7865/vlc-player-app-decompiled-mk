package io.ktor.server.routing;

import io.ktor.http.HttpStatusCode;
import io.ktor.http.Parameters;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/routing/RoutingResolveResult;", "", "route", "Lio/ktor/server/routing/Route;", "(Lio/ktor/server/routing/Route;)V", "parameters", "Lio/ktor/http/Parameters;", "getParameters", "()Lio/ktor/http/Parameters;", "getRoute", "()Lio/ktor/server/routing/Route;", "Failure", "Success", "Lio/ktor/server/routing/RoutingResolveResult$Failure;", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingResolveResult.kt */
public abstract class RoutingResolveResult {
    private final Route route;

    public /* synthetic */ RoutingResolveResult(Route route2, DefaultConstructorMarker defaultConstructorMarker) {
        this(route2);
    }

    public abstract Parameters getParameters();

    private RoutingResolveResult(Route route2) {
        this.route = route2;
    }

    public final Route getRoute() {
        return this.route;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lio/ktor/server/routing/RoutingResolveResult$Success;", "Lio/ktor/server/routing/RoutingResolveResult;", "route", "Lio/ktor/server/routing/Route;", "parameters", "Lio/ktor/http/Parameters;", "(Lio/ktor/server/routing/Route;Lio/ktor/http/Parameters;)V", "quality", "", "(Lio/ktor/server/routing/Route;Lio/ktor/http/Parameters;D)V", "getParameters", "()Lio/ktor/http/Parameters;", "getQuality$ktor_server_core", "()D", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RoutingResolveResult.kt */
    public static final class Success extends RoutingResolveResult {
        private final Parameters parameters;
        private final double quality;

        public Parameters getParameters() {
            return this.parameters;
        }

        public final double getQuality$ktor_server_core() {
            return this.quality;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Success(Route route, Parameters parameters2, double d) {
            super(route, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(parameters2, "parameters");
            this.parameters = parameters2;
            this.quality = d;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @Deprecated(message = "This will become internal in future releases.")
        public Success(Route route, Parameters parameters2) {
            this(route, parameters2, 0.0d);
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(parameters2, "parameters");
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder("SUCCESS");
            if (getParameters().isEmpty()) {
                str = "";
            } else {
                str = "; " + getParameters();
            }
            sb.append(str);
            sb.append(" @ ");
            sb.append(getRoute());
            return sb.toString();
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0005H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lio/ktor/server/routing/RoutingResolveResult$Failure;", "Lio/ktor/server/routing/RoutingResolveResult;", "route", "Lio/ktor/server/routing/Route;", "reason", "", "(Lio/ktor/server/routing/Route;Ljava/lang/String;)V", "errorStatusCode", "Lio/ktor/http/HttpStatusCode;", "(Lio/ktor/server/routing/Route;Ljava/lang/String;Lio/ktor/http/HttpStatusCode;)V", "getErrorStatusCode", "()Lio/ktor/http/HttpStatusCode;", "parameters", "", "getParameters", "()Ljava/lang/Void;", "getReason", "()Ljava/lang/String;", "toString", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RoutingResolveResult.kt */
    public static final class Failure extends RoutingResolveResult {
        private final HttpStatusCode errorStatusCode;
        private final String reason;

        public final String getReason() {
            return this.reason;
        }

        public final HttpStatusCode getErrorStatusCode() {
            return this.errorStatusCode;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Failure(Route route, String str, HttpStatusCode httpStatusCode) {
            super(route, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(str, "reason");
            Intrinsics.checkNotNullParameter(httpStatusCode, "errorStatusCode");
            this.reason = str;
            this.errorStatusCode = httpStatusCode;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @Deprecated(message = "This will become internal in future releases.")
        public Failure(Route route, String str) {
            this(route, str, HttpStatusCode.Companion.getNotFound());
            Intrinsics.checkNotNullParameter(route, "route");
            Intrinsics.checkNotNullParameter(str, "reason");
        }

        public Void getParameters() {
            throw new UnsupportedOperationException("Parameters are available only when routing resolve succeeds");
        }

        public String toString() {
            return "FAILURE \"" + this.reason + "\" @ " + getRoute();
        }
    }
}
