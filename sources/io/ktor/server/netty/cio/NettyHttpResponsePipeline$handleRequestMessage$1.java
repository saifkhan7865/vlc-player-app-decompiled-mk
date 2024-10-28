package io.ktor.server.netty.cio;

import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.server.netty.NettyApplicationResponse;
import io.netty.channel.ChannelFuture;
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
@DebugMetadata(c = "io.ktor.server.netty.cio.NettyHttpResponsePipeline$handleRequestMessage$1", f = "NettyHttpResponsePipeline.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NettyHttpResponsePipeline.kt */
final class NettyHttpResponsePipeline$handleRequestMessage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $bodySize;
    final /* synthetic */ NettyApplicationCall $call;
    final /* synthetic */ ChannelFuture $requestMessageFuture;
    final /* synthetic */ NettyApplicationResponse $response;
    int label;
    final /* synthetic */ NettyHttpResponsePipeline this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttpResponsePipeline$handleRequestMessage$1(NettyHttpResponsePipeline nettyHttpResponsePipeline, NettyApplicationCall nettyApplicationCall, NettyApplicationResponse nettyApplicationResponse, int i, ChannelFuture channelFuture, Continuation<? super NettyHttpResponsePipeline$handleRequestMessage$1> continuation) {
        super(2, continuation);
        this.this$0 = nettyHttpResponsePipeline;
        this.$call = nettyApplicationCall;
        this.$response = nettyApplicationResponse;
        this.$bodySize = i;
        this.$requestMessageFuture = channelFuture;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new NettyHttpResponsePipeline$handleRequestMessage$1(this.this$0, this.$call, this.$response, this.$bodySize, this.$requestMessageFuture, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((NettyHttpResponsePipeline$handleRequestMessage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NettyHttpResponsePipeline nettyHttpResponsePipeline = this.this$0;
            NettyApplicationCall nettyApplicationCall = this.$call;
            this.label = 1;
            if (nettyHttpResponsePipeline.respondWithBodyAndTrailerMessage(nettyApplicationCall, this.$response, this.$bodySize, this.$requestMessageFuture, this) == coroutine_suspended) {
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
