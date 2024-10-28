package org.videolan.television.ui.browser;

import android.net.Uri;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.ui.FileTvItemAdapter;
import org.videolan.vlc.viewmodels.browser.IPathOperationDelegate;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserTvFragment.kt */
final class FileBrowserTvFragment$provideAdapter$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ FileTvItemAdapter $fileTvItemAdapter;
    final /* synthetic */ FileBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserTvFragment$provideAdapter$1(FileBrowserTvFragment fileBrowserTvFragment, FileTvItemAdapter fileTvItemAdapter) {
        super(0);
        this.this$0 = fileBrowserTvFragment;
        this.$fileTvItemAdapter = fileTvItemAdapter;
    }

    public final void invoke() {
        Uri uri;
        String uri2;
        TvBrowserModel viewModel = this.this$0.getViewModel();
        Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.IPathOperationDelegate");
        MediaLibraryItem source = ((IPathOperationDelegate) viewModel).getSource();
        Integer num = null;
        if (source != null) {
            if (CollectionsKt.contains(this.$fileTvItemAdapter.getDataset(), source)) {
                num = Integer.valueOf(CollectionsKt.indexOf(this.$fileTvItemAdapter.getDataset(), source));
            } else {
                FileTvItemAdapter fileTvItemAdapter = this.$fileTvItemAdapter;
                Integer num2 = null;
                for (MediaWrapper mediaWrapper : this.$fileTvItemAdapter.getDataset()) {
                    MediaWrapper mediaWrapper2 = source instanceof MediaWrapper ? (MediaWrapper) source : null;
                    if (!(mediaWrapper2 == null || (uri = mediaWrapper2.getUri()) == null || (uri2 = uri.toString()) == null)) {
                        Intrinsics.checkNotNull(uri2);
                        String uri3 = mediaWrapper.getUri().toString();
                        Intrinsics.checkNotNullExpressionValue(uri3, "toString(...)");
                        if (StringsKt.startsWith$default(uri2, uri3, false, 2, (Object) null)) {
                            num2 = Integer.valueOf(fileTvItemAdapter.getDataset().indexOf(mediaWrapper));
                        }
                    }
                }
                num = num2;
            }
        }
        if (num != null) {
            RecyclerView.LayoutManager layoutManager = this.this$0.getBinding().list.getLayoutManager();
            Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
            linearLayoutManager.scrollToPosition(num.intValue());
            View childAt = linearLayoutManager.getChildAt(num.intValue());
            if (childAt != null) {
                FileBrowserTvFragment fileBrowserTvFragment = this.this$0;
                childAt.requestFocus();
                TvBrowserModel viewModel2 = fileBrowserTvFragment.getViewModel();
                Intrinsics.checkNotNull(viewModel2, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.IPathOperationDelegate");
                ((IPathOperationDelegate) viewModel2).consumeSource();
            }
        }
    }
}
