package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationCall;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.slf4j.MDCContext;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$3", f = "CallLogging.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CallLogging.kt */
final class CallLoggingKt$logCallsWithMDC$3 extends SuspendLambda implements Function2<ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<MDCEntry> $entries;
    final /* synthetic */ Function1<ApplicationCall, Unit> $logSuccess;
    /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallLoggingKt$logCallsWithMDC$3(List<MDCEntry> list, Function1<? super ApplicationCall, Unit> function1, Continuation<? super CallLoggingKt$logCallsWithMDC$3> continuation) {
        super(2, continuation);
        this.$entries = list;
        this.$logSuccess = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CallLoggingKt$logCallsWithMDC$3 callLoggingKt$logCallsWithMDC$3 = new CallLoggingKt$logCallsWithMDC$3(this.$entries, this.$logSuccess, continuation);
        callLoggingKt$logCallsWithMDC$3.L$0 = obj;
        return callLoggingKt$logCallsWithMDC$3;
    }

    public final Object invoke(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        return ((CallLoggingKt$logCallsWithMDC$3) create(applicationCall, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ApplicationCall applicationCall = (ApplicationCall) this.L$0;
            List<MDCEntry> list = this.$entries;
            Function1<ApplicationCall, Unit> function1 = this.$logSuccess;
            this.label = 1;
            if (BuildersKt.withContext(new MDCContext(MDCEntryUtilsKt.setup(list, applicationCall)), new CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1(list, (Continuation) null, function1, applicationCall), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
