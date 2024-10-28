package io.ktor.server.netty;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.NettyApplicationCall", f = "NettyApplicationCall.kt", i = {0}, l = {83}, m = "finishSuspend", n = {"this"}, s = {"L$0"})
/* compiled from: NettyApplicationCall.kt */
final class NettyApplicationCall$finishSuspend$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NettyApplicationCall this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyApplicationCall$finishSuspend$1(NettyApplicationCall nettyApplicationCall, Continuation<? super NettyApplicationCall$finishSuspend$1> continuation) {
        super(continuation);
        this.this$0 = nettyApplicationCall;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.finishSuspend(this);
    }
}
