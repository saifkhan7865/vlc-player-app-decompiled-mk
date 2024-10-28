package io.ktor.server.netty.cio;

import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.server.netty.NettyApplicationResponse;
import io.netty.channel.ChannelFuture;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.cio.NettyHttpResponsePipeline", f = "NettyHttpResponsePipeline.kt", i = {0, 0, 1, 1, 2, 2}, l = {247, 248, 249}, m = "respondWithBodyAndTrailerMessage", n = {"this", "call", "this", "call", "this", "call"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* compiled from: NettyHttpResponsePipeline.kt */
final class NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NettyHttpResponsePipeline this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1(NettyHttpResponsePipeline nettyHttpResponsePipeline, Continuation<? super NettyHttpResponsePipeline$respondWithBodyAndTrailerMessage$1> continuation) {
        super(continuation);
        this.this$0 = nettyHttpResponsePipeline;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.respondWithBodyAndTrailerMessage((NettyApplicationCall) null, (NettyApplicationResponse) null, 0, (ChannelFuture) null, this);
    }
}
