package org.videolan.vlc.gui.dialogs;

import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/gui/dialogs/SelectChapterDialog$initChapterList$1", "Landroidx/recyclerview/widget/LinearLayoutManager;", "onLayoutCompleted", "", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SelectChapterDialog.kt */
public final class SelectChapterDialog$initChapterList$1 extends LinearLayoutManager {
    final /* synthetic */ PlaybackService $svc;
    final /* synthetic */ SelectChapterDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SelectChapterDialog$initChapterList$1(PlaybackService playbackService, SelectChapterDialog selectChapterDialog, FragmentActivity fragmentActivity) {
        super(fragmentActivity, 1, false);
        this.$svc = playbackService;
        this.this$0 = selectChapterDialog;
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        int chapterIdx = this.$svc.getChapterIdx();
        SelectChapterDialog selectChapterDialog = this.this$0;
        View findViewByPosition = findViewByPosition(chapterIdx);
        if (findViewByPosition != null) {
            NestedScrollView access$getNestedScrollView$p = selectChapterDialog.nestedScrollView;
            if (access$getNestedScrollView$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nestedScrollView");
                access$getNestedScrollView$p = null;
            }
            access$getNestedScrollView$p.smoothScrollTo(0, (int) findViewByPosition.getY());
            findViewByPosition.requestFocusFromTouch();
        }
    }
}
