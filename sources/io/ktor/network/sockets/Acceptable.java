package io.ktor.network.sockets;

import io.ktor.network.sockets.ASocket;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002J\u0011\u0010\u0003\u001a\u00028\u0000H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006\u0005"}, d2 = {"Lio/ktor/network/sockets/Acceptable;", "S", "Lio/ktor/network/sockets/ASocket;", "accept", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Sockets.kt */
public interface Acceptable<S extends ASocket> extends ASocket {
    Object accept(Continuation<? super S> continuation);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Sockets.kt */
    public static final class DefaultImpls {
        public static <S extends ASocket> void dispose(Acceptable<? extends S> acceptable) {
            ASocket.DefaultImpls.dispose(acceptable);
        }
    }
}
