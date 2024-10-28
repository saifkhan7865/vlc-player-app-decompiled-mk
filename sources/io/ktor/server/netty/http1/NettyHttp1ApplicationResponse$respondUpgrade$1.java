package io.ktor.server.netty.http1;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.http1.NettyHttp1ApplicationResponse", f = "NettyHttp1ApplicationResponse.kt", i = {0, 0, 0, 0, 1, 1, 2}, l = {100, 108, 109}, m = "respondUpgrade", n = {"this", "bodyHandler", "upgradedReadChannel", "upgradedWriteChannel", "this", "job", "this"}, s = {"L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$0"})
/* compiled from: NettyHttp1ApplicationResponse.kt */
final class NettyHttp1ApplicationResponse$respondUpgrade$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NettyHttp1ApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttp1ApplicationResponse$respondUpgrade$1(NettyHttp1ApplicationResponse nettyHttp1ApplicationResponse, Continuation<? super NettyHttp1ApplicationResponse$respondUpgrade$1> continuation) {
        super(continuation);
        this.this$0 = nettyHttp1ApplicationResponse;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.respondUpgrade((OutgoingContent.ProtocolUpgrade) null, this);
    }
}
