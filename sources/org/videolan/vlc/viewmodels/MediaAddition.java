package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/viewmodels/MediaAddition;", "Lorg/videolan/vlc/viewmodels/Update;", "media", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "getMedia", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
public final class MediaAddition extends Update {
    private final MediaLibraryItem media;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaAddition(MediaLibraryItem mediaLibraryItem) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "media");
        this.media = mediaLibraryItem;
    }

    public final MediaLibraryItem getMedia() {
        return this.media;
    }
}
