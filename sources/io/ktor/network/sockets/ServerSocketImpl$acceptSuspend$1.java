package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.ServerSocketImpl", f = "ServerSocketImpl.kt", i = {0}, l = {41}, m = "acceptSuspend", n = {"this"}, s = {"L$0"})
/* compiled from: ServerSocketImpl.kt */
final class ServerSocketImpl$acceptSuspend$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ServerSocketImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ServerSocketImpl$acceptSuspend$1(ServerSocketImpl serverSocketImpl, Continuation<? super ServerSocketImpl$acceptSuspend$1> continuation) {
        super(continuation);
        this.this$0 = serverSocketImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.acceptSuspend(this);
    }
}
