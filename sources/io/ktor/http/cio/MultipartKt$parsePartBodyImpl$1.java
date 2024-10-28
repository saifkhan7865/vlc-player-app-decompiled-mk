package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", i = {0, 1}, l = {175, 177}, m = "parsePartBodyImpl", n = {"output", "output"}, s = {"L$0", "L$0"})
/* compiled from: Multipart.kt */
final class MultipartKt$parsePartBodyImpl$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    MultipartKt$parsePartBodyImpl$1(Continuation<? super MultipartKt$parsePartBodyImpl$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MultipartKt.parsePartBodyImpl((ByteBuffer) null, (ByteReadChannel) null, (ByteWriteChannel) null, (HttpHeadersMap) null, 0, this);
    }
}
