package org.videolan.television.ui.audioplayer;

import androidx.activity.OnBackPressedCallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.helpers.BookmarkListDelegate;
import org.videolan.vlc.gui.helpers.PlayerOptionsDelegate;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/television/ui/audioplayer/AudioPlayerActivity$onCreate$11", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerActivity.kt */
public final class AudioPlayerActivity$onCreate$11 extends OnBackPressedCallback {
    final /* synthetic */ AudioPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerActivity$onCreate$11(AudioPlayerActivity audioPlayerActivity) {
        super(true);
        this.this$0 = audioPlayerActivity;
    }

    public void handleOnBackPressed() {
        PlayerOptionsDelegate access$getOptionsDelegate$p = this.this$0.optionsDelegate;
        if (access$getOptionsDelegate$p == null || !access$getOptionsDelegate$p.isShowing()) {
            if (this.this$0.bookmarkListDelegate != null) {
                BookmarkListDelegate access$getBookmarkListDelegate$p = this.this$0.bookmarkListDelegate;
                BookmarkListDelegate bookmarkListDelegate = null;
                if (access$getBookmarkListDelegate$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
                    access$getBookmarkListDelegate$p = null;
                }
                if (access$getBookmarkListDelegate$p.getVisible()) {
                    BookmarkListDelegate access$getBookmarkListDelegate$p2 = this.this$0.bookmarkListDelegate;
                    if (access$getBookmarkListDelegate$p2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
                    } else {
                        bookmarkListDelegate = access$getBookmarkListDelegate$p2;
                    }
                    bookmarkListDelegate.hide();
                    return;
                }
            }
            this.this$0.finish();
            return;
        }
        PlayerOptionsDelegate access$getOptionsDelegate$p2 = this.this$0.optionsDelegate;
        if (access$getOptionsDelegate$p2 != null) {
            access$getOptionsDelegate$p2.hide();
        }
    }
}
