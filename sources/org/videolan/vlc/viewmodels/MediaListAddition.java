package org.videolan.vlc.viewmodels;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/viewmodels/MediaListAddition;", "Lorg/videolan/vlc/viewmodels/Update;", "mediaList", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Ljava/util/List;)V", "getMediaList", "()Ljava/util/List;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
public final class MediaListAddition extends Update {
    private final List<MediaLibraryItem> mediaList;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaListAddition(List<? extends MediaLibraryItem> list) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(list, "mediaList");
        this.mediaList = list;
    }

    public final List<MediaLibraryItem> getMediaList() {
        return this.mediaList;
    }
}
