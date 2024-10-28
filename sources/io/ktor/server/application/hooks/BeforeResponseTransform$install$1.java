package io.ktor.server.application.hooks;

import io.ktor.server.application.ApplicationCall;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfoJvmKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "body"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.application.hooks.BeforeResponseTransform$install$1", f = "CommonHooks.kt", i = {}, l = {149}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CommonHooks.kt */
final class BeforeResponseTransform$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, ApplicationCall>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function3<ApplicationCall, T, Continuation<Object>, Object> $handler;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BeforeResponseTransform<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BeforeResponseTransform$install$1(BeforeResponseTransform<T> beforeResponseTransform, Function3<? super ApplicationCall, ? super T, ? super Continuation<Object>, ? extends Object> function3, Continuation<? super BeforeResponseTransform$install$1> continuation) {
        super(3, continuation);
        this.this$0 = beforeResponseTransform;
        this.$handler = function3;
    }

    public final Object invoke(PipelineContext<Object, ApplicationCall> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        BeforeResponseTransform$install$1 beforeResponseTransform$install$1 = new BeforeResponseTransform$install$1(this.this$0, this.$handler, continuation);
        beforeResponseTransform$install$1.L$0 = pipelineContext;
        beforeResponseTransform$install$1.L$1 = obj;
        return beforeResponseTransform$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PipelineContext pipelineContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
            Object obj2 = this.L$1;
            if (TypeInfoJvmKt.instanceOf(obj2, this.this$0.clazz)) {
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type T of io.ktor.server.application.hooks.BeforeResponseTransform");
                this.L$0 = pipelineContext2;
                this.label = 1;
                Object invoke = this.$handler.invoke((ApplicationCall) pipelineContext2.getContext(), obj2, this);
                if (invoke == coroutine_suspended) {
                    return coroutine_suspended;
                }
                pipelineContext = pipelineContext2;
                obj = invoke;
            }
            return Unit.INSTANCE;
        } else if (i == 1) {
            pipelineContext = (PipelineContext) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        pipelineContext.setSubject(obj);
        return Unit.INSTANCE;
    }
}
