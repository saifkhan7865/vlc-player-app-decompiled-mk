package io.ktor.network.tls;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSSocket", f = "TLSClientSessionJvm.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1}, l = {76, 80}, m = "appDataOutputLoop", n = {"this", "pipe", "$this$useInstance$iv", "instance$iv", "buffer", "this", "pipe", "$this$useInstance$iv", "instance$iv", "buffer"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$0", "L$1", "L$2", "L$3", "L$4"})
/* compiled from: TLSClientSessionJvm.kt */
final class TLSSocket$appDataOutputLoop$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TLSSocket this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSSocket$appDataOutputLoop$1(TLSSocket tLSSocket, Continuation<? super TLSSocket$appDataOutputLoop$1> continuation) {
        super(continuation);
        this.this$0 = tLSSocket;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.appDataOutputLoop((ByteReadChannel) null, this);
    }
}
