package io.ktor.network.tls;

import io.ktor.utils.io.core.BytePacketBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientHandshake", f = "TLSClientHandshake.kt", i = {0}, l = {478}, m = "sendHandshakeRecord", n = {"element"}, s = {"L$0"})
/* compiled from: TLSClientHandshake.kt */
final class TLSClientHandshake$sendHandshakeRecord$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ TLSClientHandshake this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TLSClientHandshake$sendHandshakeRecord$1(TLSClientHandshake tLSClientHandshake, Continuation<? super TLSClientHandshake$sendHandshakeRecord$1> continuation) {
        super(continuation);
        this.this$0 = tLSClientHandshake;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.sendHandshakeRecord((TLSHandshakeType) null, (Function1<? super BytePacketBuilder, Unit>) null, this);
    }
}
