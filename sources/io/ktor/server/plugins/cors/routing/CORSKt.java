package io.ktor.server.plugins.cors.routing;

import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.RouteScopedPlugin;
import io.ktor.server.plugins.cors.CORSConfig;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"CORS", "Lio/ktor/server/application/RouteScopedPlugin;", "Lio/ktor/server/plugins/cors/CORSConfig;", "getCORS", "()Lio/ktor/server/application/RouteScopedPlugin;", "ktor-server-cors"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CORS.kt */
public final class CORSKt {
    private static final RouteScopedPlugin<CORSConfig> CORS = CreatePluginUtilsKt.createRouteScopedPlugin("CORS", CORSKt$CORS$1.INSTANCE, CORSKt$CORS$2.INSTANCE);

    public static final RouteScopedPlugin<CORSConfig> getCORS() {
        return CORS;
    }
}
