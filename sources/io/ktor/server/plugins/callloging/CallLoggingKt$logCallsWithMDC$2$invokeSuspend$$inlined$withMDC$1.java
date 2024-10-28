package io.ktor.server.plugins.callloging;

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
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "io/ktor/server/plugins/callloging/MDCEntryUtilsKt$withMDC$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1", f = "CallLogging.kt", i = {}, l = {21}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MDCEntryUtils.kt */
public final class CallLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block;
    final /* synthetic */ List $mdcEntries;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CallLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1(Function1 function1, List list, Continuation continuation) {
        super(2, continuation);
        this.$block = function1;
        this.$mdcEntries = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CallLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1(this.$block, this.$mdcEntries, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallLoggingKt$logCallsWithMDC$2$invokeSuspend$$inlined$withMDC$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Function1 function1 = this.$block;
            this.label = 1;
            if (function1.invoke(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            try {
                ResultKt.throwOnFailure(obj);
            } catch (Throwable th) {
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MDCEntryUtilsKt.cleanup(this.$mdcEntries);
        return Unit.INSTANCE;
    }
}
