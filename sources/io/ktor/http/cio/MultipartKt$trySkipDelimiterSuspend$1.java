package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.MultipartKt", f = "Multipart.kt", i = {0}, l = {575}, m = "trySkipDelimiterSuspend", n = {"result"}, s = {"L$0"})
/* compiled from: Multipart.kt */
final class MultipartKt$trySkipDelimiterSuspend$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    MultipartKt$trySkipDelimiterSuspend$1(Continuation<? super MultipartKt$trySkipDelimiterSuspend$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return MultipartKt.trySkipDelimiterSuspend((ByteReadChannel) null, (ByteBuffer) null, this);
    }
}
