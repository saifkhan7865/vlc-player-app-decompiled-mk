package io.ktor.network.tls;

import io.ktor.utils.io.ByteReadChannel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.network.tls.ParserKt", f = "Parser.kt", i = {}, l = {138}, m = "readTLSVersion", n = {}, s = {})
/* compiled from: Parser.kt */
final class ParserKt$readTLSVersion$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    ParserKt$readTLSVersion$1(Continuation<? super ParserKt$readTLSVersion$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ParserKt.readTLSVersion((ByteReadChannel) null, this);
    }
}
