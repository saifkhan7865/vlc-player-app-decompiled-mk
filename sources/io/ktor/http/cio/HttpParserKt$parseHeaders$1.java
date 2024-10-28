package io.ktor.http.cio;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.HttpParserKt", f = "HttpParser.kt", i = {0}, l = {86}, m = "parseHeaders", n = {"builder"}, s = {"L$0"})
/* compiled from: HttpParser.kt */
final class HttpParserKt$parseHeaders$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    HttpParserKt$parseHeaders$1(Continuation<? super HttpParserKt$parseHeaders$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return HttpParserKt.parseHeaders((ByteReadChannel) null, this);
    }
}
