package io.ktor.server.plugins.callloging;

import io.ktor.events.Events;
import io.ktor.server.application.Application;
import io.ktor.server.application.DefaultApplicationEventsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/application/Application;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallLogging.kt */
final class CallLoggingKt$setupLogging$1 extends Lambda implements Function1<Application, Unit> {
    final /* synthetic */ Events $events;
    final /* synthetic */ Function1<String, Unit> $log;
    final /* synthetic */ Function1<Application, Unit> $started;
    final /* synthetic */ Function1<Application, Unit> $starting;
    final /* synthetic */ Ref.ObjectRef<Function1<Application, Unit>> $stopped;
    final /* synthetic */ Function1<Application, Unit> $stopping;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallLoggingKt$setupLogging$1(Function1<? super String, Unit> function1, Events events, Function1<? super Application, Unit> function12, Function1<? super Application, Unit> function13, Function1<? super Application, Unit> function14, Ref.ObjectRef<Function1<Application, Unit>> objectRef) {
        super(1);
        this.$log = function1;
        this.$events = events;
        this.$starting = function12;
        this.$started = function13;
        this.$stopping = function14;
        this.$stopped = objectRef;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Application) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Application application) {
        Intrinsics.checkNotNullParameter(application, "it");
        Function1<String, Unit> function1 = this.$log;
        function1.invoke("Application stopped: " + application);
        this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStarting(), this.$starting);
        this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStarted(), this.$started);
        this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStopping(), this.$stopping);
        this.$events.unsubscribe(DefaultApplicationEventsKt.getApplicationStopped(), (Function1) this.$stopped.element);
    }
}
