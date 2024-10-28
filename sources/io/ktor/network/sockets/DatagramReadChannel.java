package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.channels.ReceiveChannel;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0011\u0010\u0007\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010\bR\u0018\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, d2 = {"Lio/ktor/network/sockets/DatagramReadChannel;", "", "incoming", "Lkotlinx/coroutines/channels/ReceiveChannel;", "Lio/ktor/network/sockets/Datagram;", "getIncoming", "()Lkotlinx/coroutines/channels/ReceiveChannel;", "receive", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Datagram.kt */
public interface DatagramReadChannel {
    ReceiveChannel<Datagram> getIncoming();

    Object receive(Continuation<? super Datagram> continuation);

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Datagram.kt */
    public static final class DefaultImpls {
        public static Object receive(DatagramReadChannel datagramReadChannel, Continuation<? super Datagram> continuation) {
            return datagramReadChannel.getIncoming().receive(continuation);
        }
    }
}
