package io.ktor.server.sessions;

import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.RouteScopedPlugin;
import io.ktor.util.AttributeKey;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.List;
import kotlin.Metadata;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"$\u0010\u0005\u001a\u0012\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u00070\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLOGGER", "()Lorg/slf4j/Logger;", "SessionProvidersKey", "Lio/ktor/util/AttributeKey;", "", "Lio/ktor/server/sessions/SessionProvider;", "getSessionProvidersKey", "()Lio/ktor/util/AttributeKey;", "Sessions", "Lio/ktor/server/application/RouteScopedPlugin;", "Lio/ktor/server/sessions/SessionsConfig;", "getSessions", "()Lio/ktor/server/application/RouteScopedPlugin;", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Sessions.kt */
public final class SessionsKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.server.sessions.Sessions");
    private static final AttributeKey<List<SessionProvider<?>>> SessionProvidersKey = new AttributeKey<>("SessionProvidersKey");
    private static final RouteScopedPlugin<SessionsConfig> Sessions = CreatePluginUtilsKt.createRouteScopedPlugin("Sessions", SessionsKt$Sessions$1.INSTANCE, SessionsKt$Sessions$2.INSTANCE);

    public static final AttributeKey<List<SessionProvider<?>>> getSessionProvidersKey() {
        return SessionProvidersKey;
    }

    public static final Logger getLOGGER() {
        return LOGGER;
    }

    public static final RouteScopedPlugin<SessionsConfig> getSessions() {
        return Sessions;
    }
}
