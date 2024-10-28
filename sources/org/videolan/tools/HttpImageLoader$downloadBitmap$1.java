package org.videolan.tools;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.tools.HttpImageLoader", f = "HttpImageLoader.kt", i = {0, 0, 1, 2, 3, 3, 3}, l = {98, 52, 54, 108}, m = "downloadBitmap", n = {"imageUrl", "$this$withLock_u24default$iv", "imageUrl", "imageUrl", "imageUrl", "it", "$this$withLock_u24default$iv"}, s = {"L$0", "L$1", "L$0", "L$0", "L$0", "L$2", "L$3"})
/* compiled from: HttpImageLoader.kt */
final class HttpImageLoader$downloadBitmap$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ HttpImageLoader this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpImageLoader$downloadBitmap$1(HttpImageLoader httpImageLoader, Continuation<? super HttpImageLoader$downloadBitmap$1> continuation) {
        super(continuation);
        this.this$0 = httpImageLoader;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.downloadBitmap((String) null, this);
    }
}
