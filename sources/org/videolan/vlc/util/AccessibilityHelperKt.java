package org.videolan.vlc.util;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.databinding.BindingAdapter;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.HistoryItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.R;
import org.videolan.vlc.gui.helpers.TalkbackUtil;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0007\u001a\n\u0010\u0006\u001a\u00020\u0007*\u00020\b¨\u0006\t"}, d2 = {"mediaDescription", "", "v", "Landroid/view/View;", "media", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "isTalkbackIsEnabled", "", "Landroid/app/Activity;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: AccessibilityHelper.kt */
public final class AccessibilityHelperKt {
    public static final boolean isTalkbackIsEnabled(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "<this>");
        AccessibilityManager accessibilityManager = (AccessibilityManager) activity.getSystemService("accessibility");
        if (accessibilityManager != null) {
            return accessibilityManager.isTouchExplorationEnabled();
        }
        return false;
    }

    @BindingAdapter({"mediaContentDescription"})
    public static final void mediaDescription(View view, MediaLibraryItem mediaLibraryItem) {
        CharSequence charSequence;
        String str;
        Intrinsics.checkNotNullParameter(view, "v");
        if (mediaLibraryItem != null) {
            if (mediaLibraryItem instanceof VideoGroup) {
                TalkbackUtil talkbackUtil = TalkbackUtil.INSTANCE;
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                charSequence = talkbackUtil.getVideoGroup(context, (VideoGroup) mediaLibraryItem);
            } else if (mediaLibraryItem instanceof Album) {
                TalkbackUtil talkbackUtil2 = TalkbackUtil.INSTANCE;
                Context context2 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
                charSequence = talkbackUtil2.getAlbum(context2, (Album) mediaLibraryItem);
            } else if (mediaLibraryItem instanceof Artist) {
                TalkbackUtil talkbackUtil3 = TalkbackUtil.INSTANCE;
                Context context3 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context3, "getContext(...)");
                charSequence = talkbackUtil3.getArtist(context3, (Artist) mediaLibraryItem);
            } else if (mediaLibraryItem instanceof Folder) {
                TalkbackUtil talkbackUtil4 = TalkbackUtil.INSTANCE;
                Context context4 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context4, "getContext(...)");
                charSequence = talkbackUtil4.getFolder(context4, (Folder) mediaLibraryItem);
            } else if (mediaLibraryItem instanceof Genre) {
                TalkbackUtil talkbackUtil5 = TalkbackUtil.INSTANCE;
                Context context5 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context5, "getContext(...)");
                charSequence = talkbackUtil5.getGenre(context5, (Genre) mediaLibraryItem);
            } else if (mediaLibraryItem instanceof HistoryItem) {
                charSequence = view.getContext().getString(R.string.talkback_history_item);
            } else if (mediaLibraryItem instanceof Playlist) {
                TalkbackUtil talkbackUtil6 = TalkbackUtil.INSTANCE;
                Context context6 = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context6, "getContext(...)");
                charSequence = talkbackUtil6.getPlaylist(context6, (Playlist) mediaLibraryItem);
            } else if (mediaLibraryItem instanceof MediaWrapper) {
                MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
                int type = mediaWrapper.getType();
                if (type == -1) {
                    str = TalkbackUtil.INSTANCE.getAll(mediaLibraryItem);
                } else if (type == 0) {
                    TalkbackUtil talkbackUtil7 = TalkbackUtil.INSTANCE;
                    Context context7 = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context7, "getContext(...)");
                    str = talkbackUtil7.getVideo(context7, mediaWrapper);
                } else if (type == 1) {
                    TalkbackUtil talkbackUtil8 = TalkbackUtil.INSTANCE;
                    Context context8 = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context8, "getContext(...)");
                    str = talkbackUtil8.getAudioTrack(context8, mediaWrapper);
                } else if (type == 3 || type == 4 || type == 5) {
                    TalkbackUtil talkbackUtil9 = TalkbackUtil.INSTANCE;
                    Context context9 = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context9, "getContext(...)");
                    str = talkbackUtil9.getDir(context9, mediaLibraryItem, false);
                } else if (type == 6) {
                    TalkbackUtil talkbackUtil10 = TalkbackUtil.INSTANCE;
                    Context context10 = view.getContext();
                    Intrinsics.checkNotNullExpressionValue(context10, "getContext(...)");
                    str = talkbackUtil10.getStream(context10, mediaWrapper);
                } else {
                    throw new NotImplementedError("Media type not found: " + mediaWrapper.getType());
                }
                charSequence = str;
            } else {
                throw new NotImplementedError("Unknown item type");
            }
            view.setContentDescription(charSequence);
        }
    }
}
