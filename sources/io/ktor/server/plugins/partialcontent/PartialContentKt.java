package io.ktor.server.plugins.partialcontent;

import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.RouteScopedPlugin;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0018\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLOGGER", "()Lorg/slf4j/Logger;", "PartialContent", "Lio/ktor/server/application/RouteScopedPlugin;", "Lio/ktor/server/plugins/partialcontent/PartialContentConfig;", "getPartialContent", "()Lio/ktor/server/application/RouteScopedPlugin;", "ktor-server-partial-content"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartialContent.kt */
public final class PartialContentKt {
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.server.plugins.partialcontent.PartialContent");
    private static final RouteScopedPlugin<PartialContentConfig> PartialContent = CreatePluginUtilsKt.createRouteScopedPlugin("PartialContent", PartialContentKt$PartialContent$1.INSTANCE, PartialContentKt$PartialContent$2.INSTANCE);

    public static final Logger getLOGGER() {
        return LOGGER;
    }

    public static final RouteScopedPlugin<PartialContentConfig> getPartialContent() {
        return PartialContent;
    }
}
