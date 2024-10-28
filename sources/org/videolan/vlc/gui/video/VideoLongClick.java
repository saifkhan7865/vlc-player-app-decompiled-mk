package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoLongClick;", "Lorg/videolan/vlc/gui/video/VideoAction;", "position", "", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(ILorg/videolan/medialibrary/media/MediaLibraryItem;)V", "getItem", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getPosition", "()I", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoGridFragment.kt */
public final class VideoLongClick extends VideoAction {
    private final MediaLibraryItem item;
    private final int position;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VideoLongClick(int i, MediaLibraryItem mediaLibraryItem) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        this.position = i;
        this.item = mediaLibraryItem;
    }

    public final MediaLibraryItem getItem() {
        return this.item;
    }

    public final int getPosition() {
        return this.position;
    }
}
