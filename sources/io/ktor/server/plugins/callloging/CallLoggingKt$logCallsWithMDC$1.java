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
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.slf4j.MDCContext;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u0004\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005H@"}, d2 = {"<anonymous>", "", "call", "Lio/ktor/server/application/ApplicationCall;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$1", f = "CallLogging.kt", i = {}, l = {116}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CallLogging.kt */
final class CallLoggingKt$logCallsWithMDC$1 extends SuspendLambda implements Function3<ApplicationCall, Function1<? super Continuation<? super Unit>, ? extends Object>, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<MDCEntry> $entries;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallLoggingKt$logCallsWithMDC$1(List<MDCEntry> list, Continuation<? super CallLoggingKt$logCallsWithMDC$1> continuation) {
        super(3, continuation);
        this.$entries = list;
    }

    public final Object invoke(ApplicationCall applicationCall, Function1<? super Continuation<? super Unit>, ? extends Object> function1, Continuation<? super Unit> continuation) {
        CallLoggingKt$logCallsWithMDC$1 callLoggingKt$logCallsWithMDC$1 = new CallLoggingKt$logCallsWithMDC$1(this.$entries, continuation);
        callLoggingKt$logCallsWithMDC$1.L$0 = applicationCall;
        callLoggingKt$logCallsWithMDC$1.L$1 = function1;
        return callLoggingKt$logCallsWithMDC$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            List<MDCEntry> list = this.$entries;
            this.L$0 = null;
            this.label = 1;
            if (BuildersKt.withContext(new MDCContext(MDCEntryUtilsKt.setup(list, (ApplicationCall) this.L$0)), new CallLoggingKt$logCallsWithMDC$1$invokeSuspend$$inlined$withMDC$1((Function1) this.L$1, list, (Continuation) null), this) == coroutine_suspended) {
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
