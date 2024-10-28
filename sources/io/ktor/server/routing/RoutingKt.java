package io.ktor.server.routing;

import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.util.AttributeKey;
import io.ktor.util.InternalAPI;
import io.ktor.util.KtorDsl;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.util.pipeline.Pipeline;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0011\u001a\u00020\u0012*\u00020\r2\u0017\u0010\u0013\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\u0002\b\u0016H\u0007\"\u0018\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\"\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0015\u0010\f\u001a\u00020\r*\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLOGGER", "()Lorg/slf4j/Logger;", "RoutingFailureStatusCode", "Lio/ktor/util/AttributeKey;", "Lio/ktor/http/HttpStatusCode;", "getRoutingFailureStatusCode$annotations", "()V", "getRoutingFailureStatusCode", "()Lio/ktor/util/AttributeKey;", "application", "Lio/ktor/server/application/Application;", "Lio/ktor/server/routing/Route;", "getApplication", "(Lio/ktor/server/routing/Route;)Lio/ktor/server/application/Application;", "routing", "Lio/ktor/server/routing/Routing;", "configuration", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Routing.kt */
public final class RoutingKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.routing.Routing");
    private static final AttributeKey<HttpStatusCode> RoutingFailureStatusCode = new AttributeKey<>("RoutingFailureStatusCode");

    @InternalAPI
    public static /* synthetic */ void getRoutingFailureStatusCode$annotations() {
    }

    public static final AttributeKey<HttpStatusCode> getRoutingFailureStatusCode() {
        return RoutingFailureStatusCode;
    }

    public static final Logger getLOGGER() {
        return LOGGER;
    }

    public static final Application getApplication(Route route) {
        Application application;
        Intrinsics.checkNotNullParameter(route, "<this>");
        if (route instanceof Routing) {
            return ((Routing) route).getApplication();
        }
        Route parent = route.getParent();
        if (parent != null && (application = getApplication(parent)) != null) {
            return application;
        }
        throw new UnsupportedOperationException("Cannot retrieve application from unattached routing entry");
    }

    @KtorDsl
    public static final Routing routing(Application application, Function1<? super Routing, Unit> function1) {
        Intrinsics.checkNotNullParameter(application, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configuration");
        Pipeline pipeline = application;
        Routing routing = (Routing) ApplicationPluginKt.pluginOrNull(pipeline, Routing.Plugin);
        if (routing == null) {
            return (Routing) ApplicationPluginKt.install(pipeline, Routing.Plugin, function1);
        }
        function1.invoke(routing);
        return routing;
    }
}
