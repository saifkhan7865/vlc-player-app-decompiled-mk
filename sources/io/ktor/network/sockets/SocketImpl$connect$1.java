package io.ktor.network.sockets;

import java.net.SocketAddress;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.SocketImpl", f = "SocketImpl.kt", i = {0, 1}, l = {47, 65}, m = "connect$ktor_network", n = {"this", "this"}, s = {"L$0", "L$0"})
/* compiled from: SocketImpl.kt */
final class SocketImpl$connect$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SocketImpl<S> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SocketImpl$connect$1(SocketImpl<? extends S> socketImpl, Continuation<? super SocketImpl$connect$1> continuation) {
        super(continuation);
        this.this$0 = socketImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.connect$ktor_network((SocketAddress) null, this);
    }
}
