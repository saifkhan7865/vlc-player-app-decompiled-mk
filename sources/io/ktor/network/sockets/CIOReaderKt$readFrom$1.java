package io.ktor.network.sockets;

import io.ktor.utils.io.ByteWriteChannel;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.sockets.CIOReaderKt", f = "CIOReader.kt", i = {0}, l = {135}, m = "readFrom", n = {"count"}, s = {"L$0"})
/* compiled from: CIOReader.kt */
final class CIOReaderKt$readFrom$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    CIOReaderKt$readFrom$1(Continuation<? super CIOReaderKt$readFrom$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return CIOReaderKt.readFrom((ByteWriteChannel) null, (ReadableByteChannel) null, this);
    }
}
