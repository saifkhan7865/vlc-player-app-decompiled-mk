package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.EngineContextCancellationHelperKt$stopServerOnCancellation$1", f = "EngineContextCancellationHelper.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: EngineContextCancellationHelper.kt */
final class EngineContextCancellationHelperKt$stopServerOnCancellation$1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
    final /* synthetic */ long $gracePeriodMillis;
    final /* synthetic */ ApplicationEngine $this_stopServerOnCancellation;
    final /* synthetic */ long $timeoutMillis;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EngineContextCancellationHelperKt$stopServerOnCancellation$1(ApplicationEngine applicationEngine, long j, long j2, Continuation<? super EngineContextCancellationHelperKt$stopServerOnCancellation$1> continuation) {
        super(1, continuation);
        this.$this_stopServerOnCancellation = applicationEngine;
        this.$gracePeriodMillis = j;
        this.$timeoutMillis = j2;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new EngineContextCancellationHelperKt$stopServerOnCancellation$1(this.$this_stopServerOnCancellation, this.$gracePeriodMillis, this.$timeoutMillis, continuation);
    }

    public final Object invoke(Continuation<? super Unit> continuation) {
        return ((EngineContextCancellationHelperKt$stopServerOnCancellation$1) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.$this_stopServerOnCancellation.stop(this.$gracePeriodMillis, this.$timeoutMillis);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
