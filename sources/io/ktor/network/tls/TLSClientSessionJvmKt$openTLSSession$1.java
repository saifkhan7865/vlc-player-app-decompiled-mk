package io.ktor.network.tls;

import io.ktor.network.sockets.Socket;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.TLSClientSessionJvmKt", f = "TLSClientSessionJvm.kt", i = {0, 0, 0}, l = {26}, m = "openTLSSession", n = {"socket", "context", "handshake"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: TLSClientSessionJvm.kt */
final class TLSClientSessionJvmKt$openTLSSession$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    TLSClientSessionJvmKt$openTLSSession$1(Continuation<? super TLSClientSessionJvmKt$openTLSSession$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return TLSClientSessionJvmKt.openTLSSession((Socket) null, (ByteReadChannel) null, (ByteWriteChannel) null, (TLSConfig) null, (CoroutineContext) null, this);
    }
}
