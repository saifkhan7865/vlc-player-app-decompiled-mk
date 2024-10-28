package io.ktor.server.engine;

import io.ktor.utils.io.ByteReadChannel;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.engine.DefaultTransformKt", f = "DefaultTransform.kt", i = {0}, l = {110}, m = "readText", n = {"charset"}, s = {"L$0"})
/* compiled from: DefaultTransform.kt */
final class DefaultTransformKt$readText$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    DefaultTransformKt$readText$1(Continuation<? super DefaultTransformKt$readText$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DefaultTransformKt.readText((ByteReadChannel) null, (Charset) null, this);
    }
}
