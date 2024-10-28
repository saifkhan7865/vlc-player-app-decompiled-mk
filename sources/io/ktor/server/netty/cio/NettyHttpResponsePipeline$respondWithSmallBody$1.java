package io.ktor.server.netty.cio;

import io.ktor.server.netty.NettyApplicationCall;
import io.ktor.server.netty.NettyApplicationResponse;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.cio.NettyHttpResponsePipeline", f = "NettyHttpResponsePipeline.kt", i = {0, 0, 0, 0, 0, 0}, l = {276}, m = "respondWithSmallBody", n = {"this", "call", "response", "buffer", "size", "start"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1"})
/* compiled from: NettyHttpResponsePipeline.kt */
final class NettyHttpResponsePipeline$respondWithSmallBody$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NettyHttpResponsePipeline this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttpResponsePipeline$respondWithSmallBody$1(NettyHttpResponsePipeline nettyHttpResponsePipeline, Continuation<? super NettyHttpResponsePipeline$respondWithSmallBody$1> continuation) {
        super(continuation);
        this.this$0 = nettyHttpResponsePipeline;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.respondWithSmallBody((NettyApplicationCall) null, (NettyApplicationResponse) null, 0, this);
    }
}
