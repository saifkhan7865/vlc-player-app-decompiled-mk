package io.ktor.network.sockets;

import io.ktor.network.sockets.ASocket;
import io.ktor.network.sockets.DatagramReadWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/network/sockets/BoundDatagramSocket;", "Lio/ktor/network/sockets/ASocket;", "Lio/ktor/network/sockets/ABoundSocket;", "Lio/ktor/network/sockets/AReadable;", "Lio/ktor/network/sockets/DatagramReadWriteChannel;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Datagram.kt */
public interface BoundDatagramSocket extends ASocket, ABoundSocket, AReadable, DatagramReadWriteChannel {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Datagram.kt */
    public static final class DefaultImpls {
        public static void dispose(BoundDatagramSocket boundDatagramSocket) {
            ASocket.DefaultImpls.dispose(boundDatagramSocket);
        }

        public static Object receive(BoundDatagramSocket boundDatagramSocket, Continuation<? super Datagram> continuation) {
            return DatagramReadWriteChannel.DefaultImpls.receive(boundDatagramSocket, continuation);
        }

        public static Object send(BoundDatagramSocket boundDatagramSocket, Datagram datagram, Continuation<? super Unit> continuation) {
            Object send = DatagramReadWriteChannel.DefaultImpls.send(boundDatagramSocket, datagram, continuation);
            return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
        }
    }
}
