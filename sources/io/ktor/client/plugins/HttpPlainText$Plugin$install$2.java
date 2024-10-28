package io.ktor.client.plugins;

import io.ktor.client.call.HttpClientCall;
import io.ktor.client.statement.HttpResponseContainer;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.ByteReadPacket;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponseContainer;", "Lio/ktor/client/call/HttpClientCall;", "<name for destructuring parameter 0>"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpPlainText$Plugin$install$2", f = "HttpPlainText.kt", i = {0, 0}, l = {136, 138}, m = "invokeSuspend", n = {"$this$intercept", "info"}, s = {"L$0", "L$1"})
/* compiled from: HttpPlainText.kt */
final class HttpPlainText$Plugin$install$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponseContainer, HttpClientCall>, HttpResponseContainer, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpPlainText $plugin;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpPlainText$Plugin$install$2(HttpPlainText httpPlainText, Continuation<? super HttpPlainText$Plugin$install$2> continuation) {
        super(3, continuation);
        this.$plugin = httpPlainText;
    }

    public final Object invoke(PipelineContext<HttpResponseContainer, HttpClientCall> pipelineContext, HttpResponseContainer httpResponseContainer, Continuation<? super Unit> continuation) {
        HttpPlainText$Plugin$install$2 httpPlainText$Plugin$install$2 = new HttpPlainText$Plugin$install$2(this.$plugin, continuation);
        httpPlainText$Plugin$install$2.L$0 = pipelineContext;
        httpPlainText$Plugin$install$2.L$1 = httpResponseContainer;
        return httpPlainText$Plugin$install$2.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PipelineContext pipelineContext;
        TypeInfo typeInfo;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext2 = (PipelineContext) this.L$0;
            HttpResponseContainer httpResponseContainer = (HttpResponseContainer) this.L$1;
            TypeInfo component1 = httpResponseContainer.component1();
            Object component2 = httpResponseContainer.component2();
            if (!Intrinsics.areEqual((Object) component1.getType(), (Object) Reflection.getOrCreateKotlinClass(String.class)) || !(component2 instanceof ByteReadChannel)) {
                return Unit.INSTANCE;
            }
            this.L$0 = pipelineContext2;
            this.L$1 = component1;
            this.label = 1;
            Object readRemaining$default = ByteReadChannel.DefaultImpls.readRemaining$default((ByteReadChannel) component2, 0, this, 1, (Object) null);
            if (readRemaining$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            pipelineContext = pipelineContext2;
            obj = readRemaining$default;
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
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        if (pipelineContext.proceedWith(new HttpResponseContainer(typeInfo, this.$plugin.read$ktor_client_core((HttpClientCall) pipelineContext.getContext(), (ByteReadPacket) obj)), this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
