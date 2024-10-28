package io.ktor.http.cio;

import io.ktor.utils.io.ByteWriteChannel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.ChunkedTransferEncodingKt", f = "ChunkedTransferEncoding.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 2, 2, 3, 3}, l = {167, 168, 170, 171}, m = "writeChunk-yRinSxo", n = {"$this$writeChunk_u2dyRinSxo", "memory", "startIndex", "endIndex", "size", "$this$writeChunk_u2dyRinSxo", "memory", "startIndex", "endIndex", "size", "$this$writeChunk_u2dyRinSxo", "size", "$this$writeChunk_u2dyRinSxo", "size"}, s = {"L$0", "L$1", "I$0", "I$1", "I$2", "L$0", "L$1", "I$0", "I$1", "I$2", "L$0", "I$0", "L$0", "I$0"})
/* compiled from: ChunkedTransferEncoding.kt */
final class ChunkedTransferEncodingKt$writeChunk$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    ChunkedTransferEncodingKt$writeChunk$1(Continuation<? super ChunkedTransferEncodingKt$writeChunk$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChunkedTransferEncodingKt.m139writeChunkyRinSxo((ByteWriteChannel) null, (ByteBuffer) null, 0, 0, this);
    }
}
