package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "body"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.hooks.ReceiveRequestBytes$install$1", f = "CommonHooks.kt", i = {}, l = {128}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CommonHooks.kt */
final class ReceiveRequestBytes$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<ApplicationCall, ByteReadChannel, ByteReadChannel> $handler;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ReceiveRequestBytes$install$1(Function2<? super ApplicationCall, ? super ByteReadChannel, ? extends ByteReadChannel> function2, Continuation<? super ReceiveRequestBytes$install$1> continuation) {
        super(3, continuation);
        this.$handler = function2;
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        ReceiveRequestBytes$install$1 receiveRequestBytes$install$1 = new ReceiveRequestBytes$install$1(this.$handler, continuation);
        receiveRequestBytes$install$1.L$0 = pipelineContext;
        receiveRequestBytes$install$1.L$1 = obj;
        return receiveRequestBytes$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            if (!(obj2 instanceof ByteReadChannel)) {
                return Unit.INSTANCE;
            }
            this.L$0 = null;
            this.label = 1;
            if (pipelineContext.proceedWith(this.$handler.invoke((ApplicationCall) pipelineContext.getContext(), obj2), this) == coroutine_suspended) {
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
