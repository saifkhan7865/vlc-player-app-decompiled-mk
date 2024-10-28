package io.ktor.network.sockets;

import io.ktor.network.selector.SelectorManager;
import io.ktor.network.sockets.SocketOptions;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.ConnectUtilsJvmKt", f = "ConnectUtilsJvm.kt", i = {0}, l = {21}, m = "connect", n = {"result$iv"}, s = {"L$0"})
/* compiled from: ConnectUtilsJvm.kt */
final class ConnectUtilsJvmKt$connect$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    ConnectUtilsJvmKt$connect$1(Continuation<? super ConnectUtilsJvmKt$connect$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ConnectUtilsJvmKt.connect((SelectorManager) null, (SocketAddress) null, (SocketOptions.TCPClientSocketOptions) null, this);
    }
}
