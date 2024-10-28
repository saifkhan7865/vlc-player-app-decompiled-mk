package io.ktor.network.tls;

import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSSocket", f = "TLSClientSessionJvm.kt", i = {0, 0, 1, 1}, l = {101, 58}, m = "appDataInputLoop", n = {"pipe", "$this$consume$iv$iv", "pipe", "$this$consume$iv$iv"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: TLSClientSessionJvm.kt */
final class TLSSocket$appDataInputLoop$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TLSSocket this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSSocket$appDataInputLoop$1(TLSSocket tLSSocket, Continuation<? super TLSSocket$appDataInputLoop$1> continuation) {
        super(continuation);
        this.this$0 = tLSSocket;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.appDataInputLoop((ByteWriteChannel) null, this);
    }
}
