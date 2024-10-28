package org.videolan.television.viewmodel;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel", f = "MainTvModel.kt", i = {}, l = {340}, m = "updateHistory", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$updateHistory$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$updateHistory$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$updateHistory$1> continuation) {
        super(continuation);
        this.this$0 = mainTvModel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateHistory(this);
    }
}
