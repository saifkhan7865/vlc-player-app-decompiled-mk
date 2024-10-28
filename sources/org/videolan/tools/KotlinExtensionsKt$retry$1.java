package org.videolan.tools;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.tools.KotlinExtensionsKt", f = "KotlinExtensions.kt", i = {0, 0, 1, 1}, l = {110, 111, 113}, m = "retry", n = {"block", "delayTime", "block", "delayTime"}, s = {"L$0", "J$0", "L$0", "J$0"})
/* compiled from: KotlinExtensions.kt */
final class KotlinExtensionsKt$retry$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    long J$0;
    Object L$0;
    int label;
    /* synthetic */ Object result;

    KotlinExtensionsKt$retry$1(Continuation<? super KotlinExtensionsKt$retry$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return KotlinExtensionsKt.retry(0, 0, (Function1<? super Continuation<? super Boolean>, ? extends Object>) null, this);
    }
}
