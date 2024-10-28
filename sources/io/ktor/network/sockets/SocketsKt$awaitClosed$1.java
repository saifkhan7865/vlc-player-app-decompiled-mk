package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.SocketsKt", f = "Sockets.kt", i = {0}, l = {38}, m = "awaitClosed", n = {"$this$awaitClosed"}, s = {"L$0"})
/* compiled from: Sockets.kt */
final class SocketsKt$awaitClosed$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    SocketsKt$awaitClosed$1(Continuation<? super SocketsKt$awaitClosed$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SocketsKt.awaitClosed((ASocket) null, this);
    }
}
