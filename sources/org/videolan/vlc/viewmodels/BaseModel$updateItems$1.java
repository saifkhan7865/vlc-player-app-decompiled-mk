package org.videolan.vlc.viewmodels;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.BaseModel", f = "BaseModel.kt", i = {}, l = {100}, m = "updateItems$suspendImpl", n = {}, s = {})
/* compiled from: BaseModel.kt */
final class BaseModel$updateItems$1<T extends MediaLibraryItem> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BaseModel<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseModel$updateItems$1(BaseModel<T> baseModel, Continuation<? super BaseModel$updateItems$1> continuation) {
        super(continuation);
        this.this$0 = baseModel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return BaseModel.updateItems$suspendImpl(this.this$0, (List) null, this);
    }
}
