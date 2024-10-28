package io.ktor.server.netty.http2;

import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.http2.NettyHttp2ApplicationResponse", f = "NettyHttp2ApplicationResponse.kt", i = {0, 0}, l = {51}, m = "respondOutgoingContent", n = {"this", "content"}, s = {"L$0", "L$1"})
/* compiled from: NettyHttp2ApplicationResponse.kt */
final class NettyHttp2ApplicationResponse$respondOutgoingContent$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ NettyHttp2ApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttp2ApplicationResponse$respondOutgoingContent$1(NettyHttp2ApplicationResponse nettyHttp2ApplicationResponse, Continuation<? super NettyHttp2ApplicationResponse$respondOutgoingContent$1> continuation) {
        super(continuation);
        this.this$0 = nettyHttp2ApplicationResponse;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.respondOutgoingContent((OutgoingContent) null, this);
    }
}
