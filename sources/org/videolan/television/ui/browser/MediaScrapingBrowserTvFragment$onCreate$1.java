package org.videolan.television.ui.browser;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "items", "Landroidx/paging/PagedList;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingBrowserTvFragment.kt */
final class MediaScrapingBrowserTvFragment$onCreate$1 extends Lambda implements Function1<PagedList<MediaMetadataWithImages>, Unit> {
    final /* synthetic */ MediaScrapingBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingBrowserTvFragment$onCreate$1(MediaScrapingBrowserTvFragment mediaScrapingBrowserTvFragment) {
        super(1);
        this.this$0 = mediaScrapingBrowserTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PagedList<MediaMetadataWithImages>) (PagedList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PagedList<MediaMetadataWithImages> pagedList) {
        this.this$0.getBinding().emptyLoading.post(new MediaScrapingBrowserTvFragment$onCreate$1$$ExternalSyntheticLambda0(this.this$0, pagedList));
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003e, code lost:
        if (((org.videolan.television.viewmodel.MediaScrapingBrowserViewModel) r5).getSort() == 0) goto L_0x0040;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void invoke$lambda$0(org.videolan.television.ui.browser.MediaScrapingBrowserTvFragment r4, androidx.paging.PagedList r5) {
        /*
            java.lang.String r0 = "this$0"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r4.submitList(r5)
            org.videolan.television.databinding.SongBrowserBinding r0 = r4.getBinding()
            org.videolan.vlc.gui.view.EmptyLoadingStateView r0 = r0.emptyLoading
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L_0x001a
            org.videolan.vlc.gui.view.EmptyLoadingState r5 = org.videolan.vlc.gui.view.EmptyLoadingState.EMPTY
            goto L_0x001c
        L_0x001a:
            org.videolan.vlc.gui.view.EmptyLoadingState r5 = org.videolan.vlc.gui.view.EmptyLoadingState.NONE
        L_0x001c:
            r0.setState(r5)
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r5 = r4.getViewModel()
            java.lang.String r0 = "null cannot be cast to non-null type org.videolan.television.viewmodel.MediaScrapingBrowserViewModel"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            org.videolan.television.viewmodel.MediaScrapingBrowserViewModel r5 = (org.videolan.television.viewmodel.MediaScrapingBrowserViewModel) r5
            int r5 = r5.getSort()
            r1 = 1
            if (r5 == r1) goto L_0x0040
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r5 = r4.getViewModel()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5, r0)
            org.videolan.television.viewmodel.MediaScrapingBrowserViewModel r5 = (org.videolan.television.viewmodel.MediaScrapingBrowserViewModel) r5
            int r5 = r5.getSort()
            if (r5 != 0) goto L_0x0042
        L_0x0040:
            r1 = 9
        L_0x0042:
            org.videolan.television.databinding.SongBrowserBinding r5 = r4.getBinding()
            androidx.recyclerview.widget.RecyclerView r5 = r5.headerList
            androidx.recyclerview.widget.GridLayoutManager r2 = new androidx.recyclerview.widget.GridLayoutManager
            androidx.fragment.app.FragmentActivity r3 = r4.requireActivity()
            android.content.Context r3 = (android.content.Context) r3
            r2.<init>(r3, r1)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r2 = (androidx.recyclerview.widget.RecyclerView.LayoutManager) r2
            r5.setLayoutManager(r2)
            org.videolan.television.ui.MediaHeaderAdapter r5 = r4.getHeaderAdapter()
            org.videolan.vlc.viewmodels.tv.TvBrowserModel r4 = r4.getViewModel()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r0)
            org.videolan.television.viewmodel.MediaScrapingBrowserViewModel r4 = (org.videolan.television.viewmodel.MediaScrapingBrowserViewModel) r4
            int r4 = r4.getSort()
            r5.setSortType(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.MediaScrapingBrowserTvFragment$onCreate$1.invoke$lambda$0(org.videolan.television.ui.browser.MediaScrapingBrowserTvFragment, androidx.paging.PagedList):void");
    }
}
