package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.application.DefaultApplicationEventsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.ShutDownUrl$doShutdown$2", f = "ShutDownUrl.kt", i = {}, l = {36}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ShutDownUrl.kt */
final class ShutDownUrl$doShutdown$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Application $application;
    final /* synthetic */ ApplicationEnvironment $environment;
    final /* synthetic */ int $exitCode;
    final /* synthetic */ CompletableDeferred $latch;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ShutDownUrl$doShutdown$2(CompletableDeferred completableDeferred, ApplicationEnvironment applicationEnvironment, Application application, int i, Continuation<? super ShutDownUrl$doShutdown$2> continuation) {
        super(2, continuation);
        this.$latch = completableDeferred;
        this.$environment = applicationEnvironment;
        this.$application = application;
        this.$exitCode = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ShutDownUrl$doShutdown$2(this.$latch, this.$environment, this.$application, this.$exitCode, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ShutDownUrl$doShutdown$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (this.$latch.join(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$environment.getMonitor().raise(DefaultApplicationEventsKt.getApplicationStopPreparing(), this.$environment);
        ApplicationEnvironment applicationEnvironment = this.$environment;
        if (applicationEnvironment instanceof ApplicationEngineEnvironment) {
            ((ApplicationEngineEnvironment) applicationEnvironment).stop();
        } else {
            this.$application.dispose();
        }
        System.exit(this.$exitCode);
        throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
    }
}
