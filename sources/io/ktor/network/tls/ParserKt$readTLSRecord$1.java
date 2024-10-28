package io.ktor.network.tls;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.ParserKt", f = "Parser.kt", i = {0, 1, 1, 2, 2, 2, 3, 3}, l = {19, 20, 22, 25}, m = "readTLSRecord", n = {"$this$readTLSRecord", "$this$readTLSRecord", "type", "$this$readTLSRecord", "type", "version", "type", "version"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1"})
/* compiled from: Parser.kt */
final class ParserKt$readTLSRecord$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    ParserKt$readTLSRecord$1(Continuation<? super ParserKt$readTLSRecord$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ParserKt.readTLSRecord((ByteReadChannel) null, this);
    }
}
