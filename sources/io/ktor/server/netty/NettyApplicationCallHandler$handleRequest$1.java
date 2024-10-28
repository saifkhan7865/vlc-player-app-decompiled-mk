package io.ktor.server.netty;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.engine.DefaultEnginePipelineKt;
import io.ktor.server.netty.http1.NettyHttp1ApplicationCall;
import io.ktor.util.debug.ContextUtilsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.NettyApplicationCallHandler$handleRequest$1", f = "NettyApplicationCallHandler.kt", i = {}, l = {40, 119, 46}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NettyApplicationCallHandler.kt */
final class NettyApplicationCallHandler$handleRequest$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ApplicationCall $call;
    int label;
    final /* synthetic */ NettyApplicationCallHandler this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyApplicationCallHandler$handleRequest$1(ApplicationCall applicationCall, NettyApplicationCallHandler nettyApplicationCallHandler, Continuation<? super NettyApplicationCallHandler$handleRequest$1> continuation) {
        super(2, continuation);
        this.$call = applicationCall;
        this.this$0 = nettyApplicationCallHandler;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NettyApplicationCallHandler$handleRequest$1(this.$call, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NettyApplicationCallHandler$handleRequest$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    try {
                        ResultKt.throwOnFailure(obj);
                    } catch (Exception e) {
                        this.label = 3;
                        if (DefaultEnginePipelineKt.handleFailure(this.$call, e, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else if (i != 3) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
            }
            ResultKt.throwOnFailure(obj);
        } else {
            ResultKt.throwOnFailure(obj);
            ApplicationCall applicationCall = this.$call;
            if (!(applicationCall instanceof NettyHttp1ApplicationCall) || NettyApplicationCallHandlerKt.isValid(((NettyHttp1ApplicationCall) applicationCall).getRequest())) {
                this.label = 2;
                if (ContextUtilsKt.initContextInDebugMode(new NettyApplicationCallHandler$handleRequest$1$invokeSuspend$$inlined$execute$1(this.this$0.enginePipeline, this.$call, (Continuation) null), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                this.label = 1;
                if (this.this$0.respondError400BadRequest((NettyHttp1ApplicationCall) this.$call, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        }
        return Unit.INSTANCE;
    }
}
