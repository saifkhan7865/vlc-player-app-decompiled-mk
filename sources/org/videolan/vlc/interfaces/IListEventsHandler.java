package org.videolan.vlc.interfaces;

import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/interfaces/IListEventsHandler;", "", "onMove", "", "oldPosition", "", "newPosition", "onRemove", "position", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IListEventsHandler.kt */
public interface IListEventsHandler {
    void onMove(int i, int i2);

    void onRemove(int i, MediaLibraryItem mediaLibraryItem);

    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
