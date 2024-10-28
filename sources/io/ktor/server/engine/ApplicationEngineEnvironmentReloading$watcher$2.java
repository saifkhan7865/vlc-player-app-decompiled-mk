package io.ktor.server.engine;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import java.nio.file.WatchService;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/nio/file/WatchService;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentReloading.kt */
final class ApplicationEngineEnvironmentReloading$watcher$2 extends Lambda implements Function0<WatchService> {
    public static final ApplicationEngineEnvironmentReloading$watcher$2 INSTANCE = new ApplicationEngineEnvironmentReloading$watcher$2();

    ApplicationEngineEnvironmentReloading$watcher$2() {
        super(0);
    }

    public final WatchService invoke() {
        try {
            return NioPathKt$$ExternalSyntheticApiModelOutline0.m().newWatchService();
        } catch (NoClassDefFoundError unused) {
            return null;
        }
    }
}
