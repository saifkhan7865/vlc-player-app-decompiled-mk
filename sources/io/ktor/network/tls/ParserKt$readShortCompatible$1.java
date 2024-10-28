package io.ktor.network.tls;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.ParserKt", f = "Parser.kt", i = {0, 1}, l = {147, 148}, m = "readShortCompatible", n = {"$this$readShortCompatible", "first"}, s = {"L$0", "I$0"})
/* compiled from: Parser.kt */
final class ParserKt$readShortCompatible$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;

    ParserKt$readShortCompatible$1(Continuation<? super ParserKt$readShortCompatible$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ParserKt.readShortCompatible((ByteReadChannel) null, this);
    }
}
