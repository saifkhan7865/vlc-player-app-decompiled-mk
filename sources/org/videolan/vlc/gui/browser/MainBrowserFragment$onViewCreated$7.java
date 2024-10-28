package org.videolan.vlc.gui.browser;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.TitleListView;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "list", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainBrowserFragment.kt */
final class MainBrowserFragment$onViewCreated$7 extends Lambda implements Function1<List<MediaLibraryItem>, Unit> {
    final /* synthetic */ BaseBrowserAdapter $networkAdapter;
    final /* synthetic */ MainBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainBrowserFragment$onViewCreated$7(BaseBrowserAdapter baseBrowserAdapter, MainBrowserFragment mainBrowserFragment) {
        super(1);
        this.$networkAdapter = baseBrowserAdapter;
        this.this$0 = mainBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaLibraryItem>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaLibraryItem> list) {
        if (list != null) {
            BaseBrowserAdapter baseBrowserAdapter = this.$networkAdapter;
            MainBrowserFragment mainBrowserFragment = this.this$0;
            baseBrowserAdapter.update(list);
            TitleListView access$getNetworkEntry$p = mainBrowserFragment.networkEntry;
            TitleListView titleListView = null;
            if (access$getNetworkEntry$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
                access$getNetworkEntry$p = null;
            }
            mainBrowserFragment.updateNetworkEmptyView(access$getNetworkEntry$p.getLoading());
            BrowserModel access$getNetworkViewModel$p = mainBrowserFragment.networkViewModel;
            if (access$getNetworkViewModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkViewModel");
                access$getNetworkViewModel$p = null;
            }
            if (Intrinsics.areEqual((Object) access$getNetworkViewModel$p.getLoading().getValue(), (Object) false)) {
                TitleListView access$getNetworkEntry$p2 = mainBrowserFragment.networkEntry;
                if (access$getNetworkEntry$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
                } else {
                    titleListView = access$getNetworkEntry$p2;
                }
                titleListView.getLoading().setState(list.isEmpty() ? EmptyLoadingState.EMPTY : EmptyLoadingState.NONE);
            }
        }
    }
}
