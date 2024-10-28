package io.ktor.network.sockets;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.DatagramSocketImpl", f = "DatagramSocketImpl.kt", i = {0, 0}, l = {88}, m = "receiveSuspend", n = {"this", "buffer"}, s = {"L$0", "L$1"})
/* compiled from: DatagramSocketImpl.kt */
final class DatagramSocketImpl$receiveSuspend$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DatagramSocketImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DatagramSocketImpl$receiveSuspend$1(DatagramSocketImpl datagramSocketImpl, Continuation<? super DatagramSocketImpl$receiveSuspend$1> continuation) {
        super(continuation);
        this.this$0 = datagramSocketImpl;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.receiveSuspend((ByteBuffer) null, this);
    }
}
