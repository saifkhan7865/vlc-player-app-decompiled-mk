package io.ktor.server.engine;

import io.ktor.server.application.DefaultApplicationEventsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"SHUTDOWN_HOOK_DISABLED", "", "addShutdownHook", "", "Lio/ktor/server/engine/ApplicationEngine;", "stop", "Lkotlin/Function0;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ShutdownHookJvm.kt */
public final class ShutdownHookJvmKt {
    private static final boolean SHUTDOWN_HOOK_DISABLED = Intrinsics.areEqual((Object) System.getProperty("io.ktor.server.engine.ShutdownHook", "true"), (Object) "false");

    public static final void addShutdownHook(ApplicationEngine applicationEngine, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(applicationEngine, "<this>");
        Intrinsics.checkNotNullParameter(function0, "stop");
        if (!SHUTDOWN_HOOK_DISABLED) {
            applicationEngine.getEnvironment().getMonitor().subscribe(DefaultApplicationEventsKt.getApplicationStarting(), new ShutdownHookJvmKt$addShutdownHook$1(applicationEngine, new ShutdownHook(function0)));
        }
    }
}
