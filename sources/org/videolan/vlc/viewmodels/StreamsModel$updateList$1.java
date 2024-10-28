package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.StreamsModel", f = "StreamsModel.kt", i = {}, l = {59}, m = "updateList", n = {}, s = {})
/* compiled from: StreamsModel.kt */
final class StreamsModel$updateList$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ StreamsModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StreamsModel$updateList$1(StreamsModel streamsModel, Continuation<? super StreamsModel$updateList$1> continuation) {
        super(continuation);
        this.this$0 = streamsModel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateList(this);
    }
}
