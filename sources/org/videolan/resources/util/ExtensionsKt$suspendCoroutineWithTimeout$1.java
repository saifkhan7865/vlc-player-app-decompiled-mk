package org.videolan.resources.util;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt", f = "Extensions.kt", i = {0}, l = {286}, m = "suspendCoroutineWithTimeout", n = {"finalValue"}, s = {"L$0"})
/* compiled from: Extensions.kt */
final class ExtensionsKt$suspendCoroutineWithTimeout$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    ExtensionsKt$suspendCoroutineWithTimeout$1(Continuation<? super ExtensionsKt$suspendCoroutineWithTimeout$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ExtensionsKt.suspendCoroutineWithTimeout(0, (Function1) null, this);
    }
}
