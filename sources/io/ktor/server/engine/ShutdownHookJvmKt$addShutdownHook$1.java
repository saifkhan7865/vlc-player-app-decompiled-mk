package io.ktor.server.engine;

import io.ktor.events.EventDefinition;
import io.ktor.events.Events;
import io.ktor.server.application.Application;
import io.ktor.server.application.DefaultApplicationEventsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/application/Application;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ShutdownHookJvm.kt */
final class ShutdownHookJvmKt$addShutdownHook$1 extends Lambda implements Function1<Application, Unit> {
    final /* synthetic */ ShutdownHook $hook;
    final /* synthetic */ ApplicationEngine $this_addShutdownHook;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ShutdownHookJvmKt$addShutdownHook$1(ApplicationEngine applicationEngine, ShutdownHook shutdownHook) {
        super(1);
        this.$this_addShutdownHook = applicationEngine;
        this.$hook = shutdownHook;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Application) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Application application) {
        Intrinsics.checkNotNullParameter(application, "it");
        Events monitor = this.$this_addShutdownHook.getEnvironment().getMonitor();
        EventDefinition<Application> applicationStopping = DefaultApplicationEventsKt.getApplicationStopping();
        final ShutdownHook shutdownHook = this.$hook;
        monitor.subscribe(applicationStopping, new Function1<Application, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Application) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(Application application) {
                Intrinsics.checkNotNullParameter(application, "it");
                try {
                    Runtime.getRuntime().removeShutdownHook(shutdownHook);
                } catch (IllegalStateException unused) {
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(this.$hook);
    }
}
