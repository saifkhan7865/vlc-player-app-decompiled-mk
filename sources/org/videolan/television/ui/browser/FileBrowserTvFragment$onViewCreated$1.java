package org.videolan.television.ui.browser;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "items", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserTvFragment.kt */
final class FileBrowserTvFragment$onViewCreated$1 extends Lambda implements Function1<List<MediaLibraryItem>, Unit> {
    final /* synthetic */ FileBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserTvFragment$onViewCreated$1(FileBrowserTvFragment fileBrowserTvFragment) {
        super(1);
        this.this$0 = fileBrowserTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaLibraryItem>) (List) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0068, code lost:
        if (((org.videolan.vlc.viewmodels.browser.BrowserModel) r5).getSort() == 0) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r4.this$0
            org.videolan.television.databinding.SongBrowserBinding r0 = r0.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r0 = r0.list
            androidx.recyclerview.widget.RecyclerView$LayoutManager r0 = r0.getLayoutManager()
            java.lang.String r1 = "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            androidx.recyclerview.widget.LinearLayoutManager r0 = (androidx.recyclerview.widget.LinearLayoutManager) r0
            android.view.View r1 = r0.getFocusedChild()
            org.videolan.television.ui.browser.FileBrowserTvFragment r2 = r4.this$0
            r2.submitList(r5)
            org.videolan.television.ui.browser.FileBrowserTvFragment r2 = r4.this$0
            org.videolan.television.databinding.SongBrowserBinding r2 = r2.getBinding()
            org.videolan.television.ui.FocusableRecyclerView r2 = r2.list
            org.videolan.television.ui.browser.FileBrowserTvFragment$onViewCreated$1$$ExternalSyntheticLambda0 r3 = new org.videolan.television.ui.browser.FileBrowserTvFragment$onViewCreated$1$$ExternalSyntheticLambda0
            r3.<init>(r0, r1)
            r2.post(r3)
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r4.this$0
            org.videolan.television.databinding.SongBrowserBinding r0 = r0.getBinding()
            org.videolan.vlc.gui.view.EmptyLoadingStateView r0 = r0.emptyLoading
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0040
            org.videolan.vlc.gui.view.EmptyLoadingState r5 = org.videolan.vlc.gui.view.EmptyLoadingState.EMPTY
            goto L_0x0042
        L_0x0040:
            org.videolan.vlc.gui.view.EmptyLoadingState r5 = org.videolan.vlc.gui.view.EmptyLoadingState.NONE
        L_0x0042:
            r0.setState(r5)
            org.videolan.television.ui.browser.FileBrowserTvFragment r5 = r4.this$0
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r5 = r5.getViewModel()
            java.lang.String r0 = "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            org.videolan.vlc.viewmodels.browser.BrowserModel r5 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r5
            int r5 = r5.getSort()
            r1 = 1
            if (r5 == r1) goto L_0x006a
            org.videolan.television.ui.browser.FileBrowserTvFragment r5 = r4.this$0
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r5 = r5.getViewModel()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            org.videolan.vlc.viewmodels.browser.BrowserModel r5 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r5
            int r5 = r5.getSort()
            if (r5 != 0) goto L_0x006c
        L_0x006a:
            r1 = 9
        L_0x006c:
            org.videolan.television.ui.browser.FileBrowserTvFragment r5 = r4.this$0
            org.videolan.television.databinding.SongBrowserBinding r5 = r5.getBinding()
            androidx.recyclerview.widget.RecyclerView r5 = r5.headerList
            androidx.recyclerview.widget.GridLayoutManager r2 = new androidx.recyclerview.widget.GridLayoutManager
            org.videolan.television.ui.browser.FileBrowserTvFragment r3 = r4.this$0
            androidx.fragment.app.FragmentActivity r3 = r3.requireActivity()
            android.content.Context r3 = (android.content.Context) r3
            r2.<init>(r3, r1)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r2 = (androidx.recyclerview.widget.RecyclerView.LayoutManager) r2
            r5.setLayoutManager(r2)
            org.videolan.television.ui.browser.FileBrowserTvFragment r5 = r4.this$0
            org.videolan.television.ui.MediaHeaderAdapter r5 = r5.getHeaderAdapter()
            org.videolan.television.ui.browser.FileBrowserTvFragment r1 = r4.this$0
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r1 = r1.getViewModel()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r0)
            org.videolan.vlc.viewmodels.browser.BrowserModel r1 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r1
            int r0 = r1.getSort()
            r5.setSortType(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.FileBrowserTvFragment$onViewCreated$1.invoke(java.util.List):void");
    }

    /* access modifiers changed from: private */
    public static final void invoke$lambda$0(LinearLayoutManager linearLayoutManager, View view) {
        Intrinsics.checkNotNullParameter(linearLayoutManager, "$lm");
        int childCount = linearLayoutManager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            if (Intrinsics.areEqual((Object) linearLayoutManager.getChildAt(i), (Object) view)) {
                View childAt = linearLayoutManager.getChildAt(i);
                if (childAt != null) {
                    childAt.requestFocus();
                }
                View childAt2 = linearLayoutManager.getChildAt(i);
                Intrinsics.checkNotNull(childAt2);
                linearLayoutManager.scrollToPosition(linearLayoutManager.getPosition(childAt2));
            }
        }
    }
}
