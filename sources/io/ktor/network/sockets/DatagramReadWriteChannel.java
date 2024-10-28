package io.ktor.network.sockets;

import io.ktor.network.sockets.DatagramReadChannel;
import io.ktor.network.sockets.DatagramWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/network/sockets/DatagramReadWriteChannel;", "Lio/ktor/network/sockets/DatagramReadChannel;", "Lio/ktor/network/sockets/DatagramWriteChannel;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Datagram.kt */
public interface DatagramReadWriteChannel extends DatagramReadChannel, DatagramWriteChannel {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Datagram.kt */
    public static final class DefaultImpls {
        public static Object receive(DatagramReadWriteChannel datagramReadWriteChannel, Continuation<? super Datagram> continuation) {
            return DatagramReadChannel.DefaultImpls.receive(datagramReadWriteChannel, continuation);
        }

        public static Object send(DatagramReadWriteChannel datagramReadWriteChannel, Datagram datagram, Continuation<? super Unit> continuation) {
            Object send = DatagramWriteChannel.DefaultImpls.send(datagramReadWriteChannel, datagram, continuation);
            return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
        }
    }
}
