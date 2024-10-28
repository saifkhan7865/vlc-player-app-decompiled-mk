package io.ktor.http.cio;

import io.ktor.http.cio.MultipartEvent;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.http.cio.CIOMultipartDataBase", f = "CIOMultipartDataBase.kt", i = {0, 0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3}, l = {72, 78, 89, 92, 107}, m = "partToData", n = {"this", "part", "part", "headers", "this", "part", "headers", "buffer", "this", "part", "headers", "buffer"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3"})
/* compiled from: CIOMultipartDataBase.kt */
final class CIOMultipartDataBase$partToData$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ CIOMultipartDataBase this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CIOMultipartDataBase$partToData$1(CIOMultipartDataBase cIOMultipartDataBase, Continuation<? super CIOMultipartDataBase$partToData$1> continuation) {
        super(continuation);
        this.this$0 = cIOMultipartDataBase;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.partToData((MultipartEvent.MultipartPart) null, this);
    }
}
