package io.ktor.client.plugins.api;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponse;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.http.content.NullBody;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function5;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.api.TransformResponseBodyHook$install$1", f = "KtorCallContexts.kt", i = {0, 0}, l = {108, 115}, m = "invokeSuspend", n = {"$this$intercept", "typeInfo"}, s = {"L$0", "L$1"})
/* compiled from: KtorCallContexts.kt */
final class TransformResponseBodyHook$install$1 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function5<TransformResponseBodyContext, HttpResponse, ByteReadChannel, TypeInfo, Continuation<Object>, Object> $handler;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TransformResponseBodyHook$install$1(Function5<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super Continuation<Object>, ? extends Object> function5, Continuation<? super TransformResponseBodyHook$install$1> continuation) {
        super(3, continuation);
        this.$handler = function5;
    }

    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        TransformResponseBodyHook$install$1 transformResponseBodyHook$install$1 = new TransformResponseBodyHook$install$1(this.$handler, continuation);
        transformResponseBodyHook$install$1.L$0 = pipelineContext;
        return transformResponseBodyHook$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PipelineContext pipelineContext;
        TypeInfo typeInfo;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
            HttpResponseContainer httpResponseContainer = (HttpResponseContainer) pipelineContext2.getSubject();
            TypeInfo component1 = httpResponseContainer.component1();
            Object component2 = httpResponseContainer.component2();
            if (!(component2 instanceof ByteReadChannel)) {
                return Unit.INSTANCE;
            }
            Function5<TransformResponseBodyContext, HttpResponse, ByteReadChannel, TypeInfo, Continuation<Object>, Object> function5 = this.$handler;
            TransformResponseBodyContext transformResponseBodyContext = new TransformResponseBodyContext();
            HttpResponse response = ((HttpClientCall) pipelineContext2.getContext()).getResponse();
            this.L$0 = pipelineContext2;
            this.L$1 = component1;
            this.label = 1;
            Object invoke = function5.invoke(transformResponseBodyContext, response, component2, component1, this);
            if (invoke == coroutine_suspended) {
                return coroutine_suspended;
            }
            pipelineContext = pipelineContext2;
            obj = invoke;
            typeInfo = component1;
        } else if (i == 1) {
            typeInfo = (TypeInfo) this.L$1;
            pipelineContext = (PipelineContext) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (obj == null) {
            return Unit.INSTANCE;
        }
        if ((obj instanceof NullBody) || typeInfo.getType().isInstance(obj)) {
            this.L$0 = null;
            this.L$1 = null;
            this.label = 2;
            if (pipelineContext.proceedWith(new HttpResponseContainer(typeInfo, obj), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("transformResponseBody returned " + obj + " but expected value of type " + typeInfo);
    }
}
