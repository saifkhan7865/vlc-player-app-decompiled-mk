package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.ChunkedTransferEncodingKt", f = "ChunkedTransferEncoding.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 4, 4}, l = {181, 137, 185, 188, 142}, m = "encodeChunked", n = {"output", "input", "$this$read_u24default$iv", "output", "input", "$this$read_u24default$iv", "buffer$iv", "output", "input", "$this$read_u24default$iv", "buffer$iv", "bytesRead$iv", "output", "input", "cause$iv", "output", "input"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "I$0", "L$0", "L$1", "L$2", "L$0", "L$1"})
/* compiled from: ChunkedTransferEncoding.kt */
final class ChunkedTransferEncodingKt$encodeChunked$3 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    ChunkedTransferEncodingKt$encodeChunked$3(Continuation<? super ChunkedTransferEncodingKt$encodeChunked$3> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChunkedTransferEncodingKt.encodeChunked((ByteWriteChannel) null, (ByteReadChannel) null, (Continuation<? super Unit>) this);
    }
}
