package io.ktor.server.netty;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.NettyApplicationResponse", f = "NettyApplicationResponse.kt", i = {0}, l = {37}, m = "respondOutgoingContent$suspendImpl", n = {"$this"}, s = {"L$0"})
/* compiled from: NettyApplicationResponse.kt */
final class NettyApplicationResponse$respondOutgoingContent$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NettyApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyApplicationResponse$respondOutgoingContent$1(NettyApplicationResponse nettyApplicationResponse, Continuation<? super NettyApplicationResponse$respondOutgoingContent$1> continuation) {
        super(continuation);
        this.this$0 = nettyApplicationResponse;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return NettyApplicationResponse.respondOutgoingContent$suspendImpl(this.this$0, (OutgoingContent) null, this);
    }
}
