package io.ktor.serialization;

import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.serialization.ContentConverterKt", f = "ContentConverter.kt", i = {0, 0}, l = {123}, m = "deserialize", n = {"body", "typeInfo"}, s = {"L$0", "L$1"})
/* compiled from: ContentConverter.kt */
final class ContentConverterKt$deserialize$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    ContentConverterKt$deserialize$1(Continuation<? super ContentConverterKt$deserialize$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ContentConverterKt.deserialize((List<? extends ContentConverter>) null, (ByteReadChannel) null, (TypeInfo) null, (Charset) null, this);
    }
}
