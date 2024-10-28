package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.DiffUtilAdapter", f = "DiffUtilAdapter.kt", i = {0}, l = {30}, m = "internalUpdate", n = {"this"}, s = {"L$0"})
/* compiled from: DiffUtilAdapter.kt */
final class DiffUtilAdapter$internalUpdate$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ DiffUtilAdapter<D, VH> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DiffUtilAdapter$internalUpdate$1(DiffUtilAdapter<D, VH> diffUtilAdapter, Continuation<? super DiffUtilAdapter$internalUpdate$1> continuation) {
        super(continuation);
        this.this$0 = diffUtilAdapter;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.internalUpdate((List) null, this);
    }
}
