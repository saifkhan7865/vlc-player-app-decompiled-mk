package io.ktor.server.netty.http2;

import io.netty.handler.codec.http2.Http2DataFrame;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lio/netty/handler/codec/http2/Http2DataFrame;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.netty.http2.NettyHttp2ApplicationRequest$contentActor$1", f = "NettyHttp2ApplicationRequest.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: NettyHttp2ApplicationRequest.kt */
final class NettyHttp2ApplicationRequest$contentActor$1 extends SuspendLambda implements Function2<ActorScope<Http2DataFrame>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ NettyHttp2ApplicationRequest this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttp2ApplicationRequest$contentActor$1(NettyHttp2ApplicationRequest nettyHttp2ApplicationRequest, Continuation<? super NettyHttp2ApplicationRequest$contentActor$1> continuation) {
        super(2, continuation);
        this.this$0 = nettyHttp2ApplicationRequest;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        NettyHttp2ApplicationRequest$contentActor$1 nettyHttp2ApplicationRequest$contentActor$1 = new NettyHttp2ApplicationRequest$contentActor$1(this.this$0, continuation);
        nettyHttp2ApplicationRequest$contentActor$1.L$0 = obj;
        return nettyHttp2ApplicationRequest$contentActor$1;
    }

    public final Object invoke(ActorScope<Http2DataFrame> actorScope, Continuation<? super Unit> continuation) {
        return ((NettyHttp2ApplicationRequest$contentActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (HttpFrameAdapterKt.http2frameLoop((ActorScope) this.L$0, this.this$0.getContentByteChannel(), this) == coroutine_suspended) {
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
