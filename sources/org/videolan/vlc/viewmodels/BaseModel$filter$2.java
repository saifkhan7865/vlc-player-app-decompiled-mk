package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.util.FilterDelegate;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/util/FilterDelegate;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
final class BaseModel$filter$2 extends Lambda implements Function0<FilterDelegate<T>> {
    final /* synthetic */ BaseModel<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseModel$filter$2(BaseModel<T> baseModel) {
        super(0);
        this.this$0 = baseModel;
    }

    public final FilterDelegate<T> invoke() {
        return new FilterDelegate<>(this.this$0.getDataset());
    }
}
