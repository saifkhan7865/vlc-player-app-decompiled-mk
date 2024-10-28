package io.ktor.server.plugins.callloging;

import io.ktor.server.application.Application;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/application/Application;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CallLogging.kt */
final class CallLoggingKt$setupLogging$stopping$1 extends Lambda implements Function1<Application, Unit> {
    final /* synthetic */ Function1<String, Unit> $log;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallLoggingKt$setupLogging$stopping$1(Function1<? super String, Unit> function1) {
        super(1);
        this.$log = function1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Application) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Application application) {
        Intrinsics.checkNotNullParameter(application, "it");
        Function1<String, Unit> function1 = this.$log;
        function1.invoke("Application stopping: " + application);
    }
}
