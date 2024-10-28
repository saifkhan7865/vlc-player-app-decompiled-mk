package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCompletedCalls$1", f = "CallLogging.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CallLogging.kt */
final class CallLoggingKt$logCompletedCalls$1 extends SuspendLambda implements Function2<ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<ApplicationCall, Unit> $logSuccess;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallLoggingKt$logCompletedCalls$1(Function1<? super ApplicationCall, Unit> function1, Continuation<? super CallLoggingKt$logCompletedCalls$1> continuation) {
        super(2, continuation);
        this.$logSuccess = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CallLoggingKt$logCompletedCalls$1 callLoggingKt$logCompletedCalls$1 = new CallLoggingKt$logCompletedCalls$1(this.$logSuccess, continuation);
        callLoggingKt$logCompletedCalls$1.L$0 = obj;
        return callLoggingKt$logCompletedCalls$1;
    }

    public final Object invoke(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        return ((CallLoggingKt$logCompletedCalls$1) create(applicationCall, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$logSuccess.invoke((ApplicationCall) this.L$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
