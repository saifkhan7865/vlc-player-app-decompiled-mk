package io.ktor.network.tls;

import io.ktor.network.sockets.Connection;
import io.ktor.network.sockets.Socket;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSCommonKt", f = "TLSCommon.kt", i = {0}, l = {38}, m = "tls", n = {"$this$tls"}, s = {"L$0"})
/* compiled from: TLSCommon.kt */
final class TLSCommonKt$tls$3 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    TLSCommonKt$tls$3(Continuation<? super TLSCommonKt$tls$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TLSCommonKt.tls((Connection) null, (CoroutineContext) null, (TLSConfig) null, (Continuation<? super Socket>) this);
    }
}
