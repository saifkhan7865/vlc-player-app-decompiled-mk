package io.ktor.network.sockets;

import io.ktor.network.sockets.ReadWriteSocket;
import kotlin.Metadata;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/network/sockets/Socket;", "Lio/ktor/network/sockets/ReadWriteSocket;", "Lio/ktor/network/sockets/ABoundSocket;", "Lio/ktor/network/sockets/AConnectedSocket;", "Lkotlinx/coroutines/CoroutineScope;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Sockets.kt */
public interface Socket extends ReadWriteSocket, ABoundSocket, AConnectedSocket, CoroutineScope {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Sockets.kt */
    public static final class DefaultImpls {
        public static void dispose(Socket socket) {
            ReadWriteSocket.DefaultImpls.dispose(socket);
        }
    }
}
