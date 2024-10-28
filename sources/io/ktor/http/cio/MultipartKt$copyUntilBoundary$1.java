package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function2;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}, l = {368, 371}, m = "copyUntilBoundary", n = {"name", "boundaryPrefixed", "input", "writeFully", "buffer", "limit", "copied", "name", "boundaryPrefixed", "input", "writeFully", "buffer", "limit", "copied", "rc"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "J$1", "L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "J$1", "I$0"})
/* compiled from: Multipart.kt */
final class MultipartKt$copyUntilBoundary$1 extends ContinuationImpl {
    int I$0;
    long J$0;
    long J$1;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;

    MultipartKt$copyUntilBoundary$1(Continuation<? super MultipartKt$copyUntilBoundary$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MultipartKt.copyUntilBoundary((String) null, (ByteBuffer) null, (ByteReadChannel) null, (Function2<? super ByteBuffer, ? super Continuation<? super Unit>, ? extends Object>) null, 0, this);
    }
}
