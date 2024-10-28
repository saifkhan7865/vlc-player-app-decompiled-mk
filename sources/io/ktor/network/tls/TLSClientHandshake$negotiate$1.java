package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientHandshake", f = "TLSClientHandshake.kt", i = {0, 0, 0, 1, 1, 1, 2, 2, 2, 3, 3}, l = {162, 163, 166, 167}, m = "negotiate", n = {"this", "$this$use$iv", "closed$iv", "this", "$this$use$iv", "closed$iv", "this", "$this$use$iv", "closed$iv", "$this$use$iv", "closed$iv"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "L$0", "L$1", "I$0", "L$0", "I$0"})
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$negotiate$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$negotiate$1(TLSClientHandshake tLSClientHandshake, Continuation<? super TLSClientHandshake$negotiate$1> continuation) {
        super(continuation);
        this.this$0 = tLSClientHandshake;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.negotiate(this);
    }
}
