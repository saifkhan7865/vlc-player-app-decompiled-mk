package io.ktor.server.engine.internal;

import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.engine.EnginePipeline;
import io.ktor.server.engine.ShutDownUrl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\u001a\b\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u0012\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028@X\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0010"}, d2 = {"IOBridge", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lkotlinx/coroutines/Dispatchers;", "getIOBridge", "(Lkotlinx/coroutines/Dispatchers;)Lkotlinx/coroutines/CoroutineDispatcher;", "availableProcessorsBridge", "", "configureShutdownUrl", "", "environment", "Lio/ktor/server/application/ApplicationEnvironment;", "pipeline", "Lio/ktor/server/engine/EnginePipeline;", "printError", "message", "", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationUtilsJvm.kt */
public final class ApplicationUtilsJvmKt {
    public static final int availableProcessorsBridge() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static final CoroutineDispatcher getIOBridge(Dispatchers dispatchers) {
        Intrinsics.checkNotNullParameter(dispatchers, "<this>");
        return Dispatchers.getIO();
    }

    public static final void printError(Object obj) {
        System.err.print(obj);
    }

    public static final void configureShutdownUrl(ApplicationEnvironment applicationEnvironment, EnginePipeline enginePipeline) {
        String string;
        Intrinsics.checkNotNullParameter(applicationEnvironment, "environment");
        Intrinsics.checkNotNullParameter(enginePipeline, "pipeline");
        ApplicationConfigValue propertyOrNull = applicationEnvironment.getConfig().propertyOrNull("ktor.deployment.shutdown.url");
        if (propertyOrNull != null && (string = propertyOrNull.getString()) != null) {
            ApplicationPluginKt.install(enginePipeline, ShutDownUrl.EnginePlugin.INSTANCE, new ApplicationUtilsJvmKt$configureShutdownUrl$1(string));
        }
    }
}
