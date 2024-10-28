package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.FilterDelegate", f = "FilterDelegate.kt", i = {}, l = {20}, m = "filter", n = {}, s = {})
/* compiled from: FilterDelegate.kt */
final class FilterDelegate$filter$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FilterDelegate<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FilterDelegate$filter$1(FilterDelegate<T> filterDelegate, Continuation<? super FilterDelegate$filter$1> continuation) {
        super(continuation);
        this.this$0 = filterDelegate;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.filter((CharSequence) null, this);
    }
}
