package io.ktor.network.sockets;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.DatagramSendChannel", f = "DatagramSendChannel.kt", i = {0, 0, 0}, l = {95}, m = "sendSuspend", n = {"this", "buffer", "address"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: DatagramSendChannel.kt */
final class DatagramSendChannel$sendSuspend$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DatagramSendChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatagramSendChannel$sendSuspend$1(DatagramSendChannel datagramSendChannel, Continuation<? super DatagramSendChannel$sendSuspend$1> continuation) {
        super(continuation);
        this.this$0 = datagramSendChannel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendSuspend((ByteBuffer) null, (SocketAddress) null, this);
    }
}
