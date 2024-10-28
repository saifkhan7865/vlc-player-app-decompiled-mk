package io.ktor.http.cio;

import io.ktor.http.cio.MultipartEvent;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.CIOMultipartDataBase", f = "CIOMultipartDataBase.kt", i = {0, 0, 0, 0, 0}, l = {151}, m = "withTempFile", n = {"part", "headers", "buffer", "tmp", "out"}, s = {"L$0", "L$1", "L$2", "L$3", "L$6"})
/* compiled from: CIOMultipartDataBase.kt */
final class CIOMultipartDataBase$withTempFile$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CIOMultipartDataBase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOMultipartDataBase$withTempFile$1(CIOMultipartDataBase cIOMultipartDataBase, Continuation<? super CIOMultipartDataBase$withTempFile$1> continuation) {
        super(continuation);
        this.this$0 = cIOMultipartDataBase;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.withTempFile((MultipartEvent.MultipartPart) null, (HttpHeadersMap) null, (ByteBuffer) null, this);
    }
}
