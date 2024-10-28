package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", i = {0, 0, 0, 0, 1}, l = {115, 117}, m = "parsePart", n = {"boundaryPrefixed", "input", "output", "limit", "headers"}, s = {"L$0", "L$1", "L$2", "J$0", "L$0"})
/* compiled from: Multipart.kt */
final class MultipartKt$parsePart$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    MultipartKt$parsePart$1(Continuation<? super MultipartKt$parsePart$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MultipartKt.parsePart((ByteBuffer) null, (ByteReadChannel) null, (ByteWriteChannel) null, 0, this);
    }
}
