package org.videolan.tools;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.tools.KotlinExtensionsKt", f = "KotlinExtensions.kt", i = {0}, l = {120}, m = "awaitAppIsForegroung", n = {"activityManager"}, s = {"L$0"})
/* compiled from: KotlinExtensions.kt */
final class KotlinExtensionsKt$awaitAppIsForegroung$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    int label;
    /* synthetic */ Object result;

    KotlinExtensionsKt$awaitAppIsForegroung$1(Continuation<? super KotlinExtensionsKt$awaitAppIsForegroung$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return KotlinExtensionsKt.awaitAppIsForegroung((Context) null, this);
    }
}
