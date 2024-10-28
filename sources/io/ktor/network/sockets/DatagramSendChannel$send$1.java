package io.ktor.network.sockets;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.DatagramSendChannel", f = "DatagramSendChannel.kt", i = {0, 0, 0, 1}, l = {160, 76}, m = "send", n = {"this", "element", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$2", "L$0"})
/* compiled from: DatagramSendChannel.kt */
final class DatagramSendChannel$send$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DatagramSendChannel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatagramSendChannel$send$1(DatagramSendChannel datagramSendChannel, Continuation<? super DatagramSendChannel$send$1> continuation) {
        super(continuation);
        this.this$0 = datagramSendChannel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.send((Datagram) null, (Continuation<? super Unit>) this);
    }
}
