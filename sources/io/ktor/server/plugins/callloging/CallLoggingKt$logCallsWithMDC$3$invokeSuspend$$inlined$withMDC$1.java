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
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "io/ktor/server/plugins/callloging/MDCEntryUtilsKt$withMDC$2"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.plugins.callloging.CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1", f = "CallLogging.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MDCEntryUtils.kt */
public final class CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ApplicationCall $call$inlined;
    final /* synthetic */ Function1 $logSuccess$inlined;
    final /* synthetic */ List $mdcEntries;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1(List list, Continuation continuation, Function1 function1, ApplicationCall applicationCall) {
        super(2, continuation);
        this.$mdcEntries = list;
        this.$logSuccess$inlined = function1;
        this.$call$inlined = applicationCall;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1(this.$mdcEntries, continuation, this.$logSuccess$inlined, this.$call$inlined);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CallLoggingKt$logCallsWithMDC$3$invokeSuspend$$inlined$withMDC$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: finally extract failed */
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                Continuation continuation = this;
                this.$logSuccess$inlined.invoke(this.$call$inlined);
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                MDCEntryUtilsKt.cleanup(this.$mdcEntries);
                throw th;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
