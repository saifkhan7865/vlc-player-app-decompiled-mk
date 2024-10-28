package io.ktor.client.plugins.websocket;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLProtocolKt;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.websocket.WebSockets$Plugin$install$1", f = "WebSockets.kt", i = {}, l = {169}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: WebSockets.kt */
final class WebSockets$Plugin$install$1 extends SuspendLambda implements Function3<PipelineContext<Object, HttpRequestBuilder>, Object, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $extensionsSupported;
    final /* synthetic */ WebSockets $plugin;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebSockets$Plugin$install$1(boolean z, WebSockets webSockets, Continuation<? super WebSockets$Plugin$install$1> continuation) {
        super(3, continuation);
        this.$extensionsSupported = z;
        this.$plugin = webSockets;
    }

    public final Object invoke(PipelineContext<Object, HttpRequestBuilder> pipelineContext, Object obj, Continuation<? super Unit> continuation) {
        WebSockets$Plugin$install$1 webSockets$Plugin$install$1 = new WebSockets$Plugin$install$1(this.$extensionsSupported, this.$plugin, continuation);
        webSockets$Plugin$install$1.L$0 = pipelineContext;
        return webSockets$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PipelineContext pipelineContext = (PipelineContext) this.L$0;
            if (!URLProtocolKt.isWebsocket(((HttpRequestBuilder) pipelineContext.getContext()).getUrl().getProtocol())) {
                Logger logger = WebSocketsKt.getLOGGER();
                logger.trace("Skipping WebSocket plugin for non-websocket request: " + ((HttpRequestBuilder) pipelineContext.getContext()).getUrl());
                return Unit.INSTANCE;
            }
            Logger logger2 = WebSocketsKt.getLOGGER();
            logger2.trace("Sending WebSocket request " + ((HttpRequestBuilder) pipelineContext.getContext()).getUrl());
            ((HttpRequestBuilder) pipelineContext.getContext()).setCapability(WebSocketCapability.INSTANCE, Unit.INSTANCE);
            if (this.$extensionsSupported) {
                this.$plugin.installExtensions((HttpRequestBuilder) pipelineContext.getContext());
            }
            this.label = 1;
            if (pipelineContext.proceedWith(new WebSocketContent(), this) == coroutine_suspended) {
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
