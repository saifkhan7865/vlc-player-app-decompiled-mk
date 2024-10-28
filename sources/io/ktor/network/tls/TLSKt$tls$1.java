package io.ktor.network.tls;

import io.ktor.network.sockets.Socket;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSKt", f = "TLS.kt", i = {0, 0, 0}, l = {23}, m = "tls", n = {"$this$tls", "reader", "writer"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: TLS.kt */
final class TLSKt$tls$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    TLSKt$tls$1(Continuation<? super TLSKt$tls$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TLSKt.tls((Socket) null, (CoroutineContext) null, (TLSConfig) null, (Continuation<? super Socket>) this);
    }
}
