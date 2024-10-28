package io.ktor.server.plugins.cors.routing;

import io.ktor.server.application.RouteScopedPluginBuilder;
import io.ktor.server.plugins.cors.CORSConfig;
import io.ktor.server.plugins.cors.CORSKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/RouteScopedPluginBuilder;", "Lio/ktor/server/plugins/cors/CORSConfig;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CORS.kt */
final class CORSKt$CORS$2 extends Lambda implements Function1<RouteScopedPluginBuilder<CORSConfig>, Unit> {
    public static final CORSKt$CORS$2 INSTANCE = new CORSKt$CORS$2();

    CORSKt$CORS$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RouteScopedPluginBuilder<CORSConfig>) (RouteScopedPluginBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(RouteScopedPluginBuilder<CORSConfig> routeScopedPluginBuilder) {
        Intrinsics.checkNotNullParameter(routeScopedPluginBuilder, "$this$createRouteScopedPlugin");
        CORSKt.buildPlugin(routeScopedPluginBuilder);
    }
}
