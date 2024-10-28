package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientHandshake", f = "TLSClientHandshake.kt", i = {0}, l = {428}, m = "sendChangeCipherSpec", n = {"packet"}, s = {"L$0"})
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$sendChangeCipherSpec$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$sendChangeCipherSpec$1(TLSClientHandshake tLSClientHandshake, Continuation<? super TLSClientHandshake$sendChangeCipherSpec$1> continuation) {
        super(continuation);
        this.this$0 = tLSClientHandshake;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendChangeCipherSpec(this);
    }
}
