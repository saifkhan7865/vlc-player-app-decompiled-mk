package io.ktor.http.cio;

import io.ktor.http.cio.internals.CharArrayBuilder;
import io.ktor.http.cio.internals.MutableRange;
import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.HttpParserKt", f = "HttpParser.kt", i = {0, 0, 0, 0}, l = {101}, m = "parseHeaders", n = {"input", "builder", "range", "headers"}, s = {"L$0", "L$1", "L$2", "L$3"})
/* compiled from: HttpParser.kt */
final class HttpParserKt$parseHeaders$2 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    HttpParserKt$parseHeaders$2(Continuation<? super HttpParserKt$parseHeaders$2> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpParserKt.parseHeaders((ByteReadChannel) null, (CharArrayBuilder) null, (MutableRange) null, this);
    }
}
