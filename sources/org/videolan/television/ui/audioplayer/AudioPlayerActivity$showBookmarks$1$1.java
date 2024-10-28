package org.videolan.television.ui.audioplayer;

import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.television.databinding.TvAudioPlayerBinding;
import org.videolan.vlc.gui.helpers.BookmarkListDelegate;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerActivity.kt */
final class AudioPlayerActivity$showBookmarks$1$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ AudioPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerActivity$showBookmarks$1$1(AudioPlayerActivity audioPlayerActivity) {
        super(0);
        this.this$0 = audioPlayerActivity;
    }

    public final void invoke() {
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
                access$getBookmarkListDelegate$p2 = null;
            }
            access$getBookmarkListDelegate$p2.getRootView().requestFocus();
        }
        TvAudioPlayerBinding access$getBinding$p = this.this$0.binding;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        RecyclerView recyclerView = access$getBinding$p.playlist;
        BookmarkListDelegate access$getBookmarkListDelegate$p3 = this.this$0.bookmarkListDelegate;
        if (access$getBookmarkListDelegate$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
            access$getBookmarkListDelegate$p3 = null;
        }
        recyclerView.setDescendantFocusability(access$getBookmarkListDelegate$p3.getVisible() ? 393216 : 262144);
        TvAudioPlayerBinding access$getBinding$p2 = this.this$0.binding;
        if (access$getBinding$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p2 = null;
        }
        RecyclerView recyclerView2 = access$getBinding$p2.playlist;
        BookmarkListDelegate access$getBookmarkListDelegate$p4 = this.this$0.bookmarkListDelegate;
        if (access$getBookmarkListDelegate$p4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
            access$getBookmarkListDelegate$p4 = null;
        }
        recyclerView2.setFocusable(!access$getBookmarkListDelegate$p4.getVisible());
        TvAudioPlayerBinding access$getBinding$p3 = this.this$0.binding;
        if (access$getBinding$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p3 = null;
        }
        LinearLayout linearLayout = access$getBinding$p3.sleepQuickAction;
        BookmarkListDelegate access$getBookmarkListDelegate$p5 = this.this$0.bookmarkListDelegate;
        if (access$getBookmarkListDelegate$p5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
            access$getBookmarkListDelegate$p5 = null;
        }
        linearLayout.setFocusable(!access$getBookmarkListDelegate$p5.getVisible());
        TvAudioPlayerBinding access$getBinding$p4 = this.this$0.binding;
        if (access$getBinding$p4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p4 = null;
        }
        LinearLayout linearLayout2 = access$getBinding$p4.playbackSpeedQuickAction;
        BookmarkListDelegate access$getBookmarkListDelegate$p6 = this.this$0.bookmarkListDelegate;
        if (access$getBookmarkListDelegate$p6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("bookmarkListDelegate");
        } else {
            bookmarkListDelegate = access$getBookmarkListDelegate$p6;
        }
        linearLayout2.setFocusable(!bookmarkListDelegate.getVisible());
    }
}
