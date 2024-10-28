package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.DiffUtilAdapter;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \r*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001\rB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\bH\u0016¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/util/MediaItemDiffCallback;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "()V", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaItemDiffCallback.kt */
public final class MediaItemDiffCallback<T extends MediaLibraryItem> extends DiffUtilAdapter.DiffCallback<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "VLC/MediaItemDiffCallback";

    public boolean areContentsTheSame(int i, int i2) {
        return true;
    }

    public int getOldListSize() {
        return getOldList().size();
    }

    public int getNewListSize() {
        return getNewList().size();
    }

    public boolean areItemsTheSame(int i, int i2) {
        MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) getOldList().get(i);
        MediaLibraryItem mediaLibraryItem2 = (MediaLibraryItem) getNewList().get(i2);
        return mediaLibraryItem == mediaLibraryItem2 && mediaLibraryItem.equals(mediaLibraryItem2);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/util/MediaItemDiffCallback$Companion;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaItemDiffCallback.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
