package org.videolan.vlc.gui.video;

import android.widget.ImageView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.helpers.BookmarkListDelegate;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoPlayerOverlayDelegate.kt */
final class VideoPlayerOverlayDelegate$showOverlayTimeout$1$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ VideoPlayerOverlayDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerOverlayDelegate$showOverlayTimeout$1$1(VideoPlayerOverlayDelegate videoPlayerOverlayDelegate) {
        super(0);
        this.this$0 = videoPlayerOverlayDelegate;
    }

    public final void invoke() {
        BookmarkListDelegate access$getBookmarkListDelegate$p;
        ImageView addBookmarButton;
        if (this.this$0.overlayTimeout != -1) {
            this.this$0.player.getHandler().sendMessageDelayed(this.this$0.player.getHandler().obtainMessage(1), (long) this.this$0.overlayTimeout);
        }
        this.this$0.getHudBinding().playerOverlayPlay.sendAccessibilityEvent(8);
        if (this.this$0.isBookmarkShown()) {
            try {
                if (AccessibilityHelperKt.isTalkbackIsEnabled(this.this$0.player) && (access$getBookmarkListDelegate$p = this.this$0.bookmarkListDelegate) != null && (addBookmarButton = access$getBookmarkListDelegate$p.getAddBookmarButton()) != null) {
                    addBookmarButton.sendAccessibilityEvent(8);
                }
            } catch (Exception unused) {
            }
        }
    }
}
