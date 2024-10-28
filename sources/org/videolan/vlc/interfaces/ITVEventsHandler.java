package org.videolan.vlc.interfaces;

import android.view.View;
import kotlin.Metadata;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\r\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/interfaces/ITVEventsHandler;", "", "onClickAddToPlaylist", "", "v", "Landroid/view/View;", "position", "", "onClickAppend", "onClickMoveDown", "onClickMoveUp", "onClickPlay", "onClickPlayNext", "onClickRemove", "onFocusChanged", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ITVEventsHandler.kt */
public interface ITVEventsHandler {
    void onClickAddToPlaylist(View view, int i);

    void onClickAppend(View view, int i);

    void onClickMoveDown(View view, int i);

    void onClickMoveUp(View view, int i);

    void onClickPlay(View view, int i);

    void onClickPlayNext(View view, int i);

    void onClickRemove(View view, int i);

    void onFocusChanged(MediaLibraryItem mediaLibraryItem);
}
