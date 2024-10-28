package org.videolan.television.ui.browser;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001e\u0010\u0002\u001a\u001a\u0012\u0006\b\u0001\u0012\u00020\u0004 \u0005*\f\u0012\u0006\b\u0001\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "items", "Landroidx/paging/PagedList;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserTvFragment.kt */
final class MediaBrowserTvFragment$onCreate$1 extends Lambda implements Function1<PagedList<? extends MediaLibraryItem>, Unit> {
    final /* synthetic */ MediaBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaBrowserTvFragment$onCreate$1(MediaBrowserTvFragment mediaBrowserTvFragment) {
        super(1);
        this.this$0 = mediaBrowserTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PagedList<? extends MediaLibraryItem>) (PagedList) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0041, code lost:
        if (((org.videolan.television.viewmodel.MediaBrowserViewModel) r5).getSort() == 0) goto L_0x0043;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(androidx.paging.PagedList<? extends org.videolan.medialibrary.media.MediaLibraryItem> r5) {
        /*
            r4 = this;
            org.videolan.television.ui.browser.MediaBrowserTvFragment r0 = r4.this$0
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r0.submitList(r5)
            org.videolan.television.ui.browser.MediaBrowserTvFragment r0 = r4.this$0
            org.videolan.television.databinding.SongBrowserBinding r0 = r0.getBinding()
            org.videolan.vlc.gui.view.EmptyLoadingStateView r0 = r0.emptyLoading
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x0019
            org.videolan.vlc.gui.view.EmptyLoadingState r5 = org.videolan.vlc.gui.view.EmptyLoadingState.EMPTY
            goto L_0x001b
        L_0x0019:
            org.videolan.vlc.gui.view.EmptyLoadingState r5 = org.videolan.vlc.gui.view.EmptyLoadingState.NONE
        L_0x001b:
            r0.setState(r5)
            org.videolan.television.ui.browser.MediaBrowserTvFragment r5 = r4.this$0
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r5 = r5.getViewModel()
            java.lang.String r0 = "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaBrowserViewModel"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            org.videolan.television.viewmodel.MediaBrowserViewModel r5 = (org.videolan.television.viewmodel.MediaBrowserViewModel) r5
            int r5 = r5.getSort()
            r1 = 1
            if (r5 == r1) goto L_0x0043
            org.videolan.television.ui.browser.MediaBrowserTvFragment r5 = r4.this$0
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r5 = r5.getViewModel()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            org.videolan.television.viewmodel.MediaBrowserViewModel r5 = (org.videolan.television.viewmodel.MediaBrowserViewModel) r5
            int r5 = r5.getSort()
            if (r5 != 0) goto L_0x0045
        L_0x0043:
            r1 = 9
        L_0x0045:
            org.videolan.television.ui.browser.MediaBrowserTvFragment r5 = r4.this$0
            org.videolan.television.databinding.SongBrowserBinding r5 = r5.getBinding()
            androidx.recyclerview.widget.RecyclerView r5 = r5.headerList
            androidx.recyclerview.widget.GridLayoutManager r2 = new androidx.recyclerview.widget.GridLayoutManager
            org.videolan.television.ui.browser.MediaBrowserTvFragment r3 = r4.this$0
            androidx.fragment.app.FragmentActivity r3 = r3.requireActivity()
            android.content.Context r3 = (android.content.Context) r3
            r2.<init>(r3, r1)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r2 = (androidx.recyclerview.widget.RecyclerView.LayoutManager) r2
            r5.setLayoutManager(r2)
            org.videolan.television.ui.browser.MediaBrowserTvFragment r5 = r4.this$0
            org.videolan.television.ui.MediaHeaderAdapter r5 = r5.getHeaderAdapter()
            org.videolan.television.ui.browser.MediaBrowserTvFragment r1 = r4.this$0
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r1 = r1.getViewModel()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r0)
            org.videolan.television.viewmodel.MediaBrowserViewModel r1 = (org.videolan.television.viewmodel.MediaBrowserViewModel) r1
            int r0 = r1.getSort()
            r5.setSortType(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.MediaBrowserTvFragment$onCreate$1.invoke(androidx.paging.PagedList):void");
    }
}
