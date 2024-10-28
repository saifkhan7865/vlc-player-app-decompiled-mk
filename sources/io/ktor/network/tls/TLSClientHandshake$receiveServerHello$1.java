package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientHandshake", f = "TLSClientHandshake.kt", i = {}, l = {209}, m = "receiveServerHello", n = {}, s = {})
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$receiveServerHello$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$receiveServerHello$1(TLSClientHandshake tLSClientHandshake, Continuation<? super TLSClientHandshake$receiveServerHello$1> continuation) {
        super(continuation);
        this.this$0 = tLSClientHandshake;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.receiveServerHello(this);
    }
}
