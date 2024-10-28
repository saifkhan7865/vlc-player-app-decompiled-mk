package org.videolan.vlc.gui.browser;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.TitleListView;
import org.videolan.vlc.viewmodels.browser.BrowserModel;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "list", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainBrowserFragment.kt */
final class MainBrowserFragment$onViewCreated$4 extends Lambda implements Function1<List<MediaLibraryItem>, Unit> {
    final /* synthetic */ BaseBrowserAdapter $favoritesAdapter;
    final /* synthetic */ MainBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainBrowserFragment$onViewCreated$4(MainBrowserFragment mainBrowserFragment, BaseBrowserAdapter baseBrowserAdapter) {
        super(1);
        this.this$0 = mainBrowserFragment;
        this.$favoritesAdapter = baseBrowserAdapter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaLibraryItem>) (List) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(List<MediaLibraryItem> list) {
        EmptyLoadingState emptyLoadingState;
        MainBrowserFragment mainBrowserFragment = this.this$0;
        BaseBrowserAdapter baseBrowserAdapter = this.$favoritesAdapter;
        BrowserModel browserModel = null;
        if (list.isEmpty()) {
            TitleListView access$getFavoritesEntry$p = mainBrowserFragment.favoritesEntry;
            if (access$getFavoritesEntry$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
                access$getFavoritesEntry$p = null;
            }
            KotlinExtensionsKt.setGone(access$getFavoritesEntry$p);
        } else {
            TitleListView access$getFavoritesEntry$p2 = mainBrowserFragment.favoritesEntry;
            if (access$getFavoritesEntry$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
                access$getFavoritesEntry$p2 = null;
            }
            KotlinExtensionsKt.setVisible(access$getFavoritesEntry$p2);
        }
        Intrinsics.checkNotNull(list);
        baseBrowserAdapter.update(list);
        TitleListView access$getFavoritesEntry$p3 = mainBrowserFragment.favoritesEntry;
        if (access$getFavoritesEntry$p3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("favoritesEntry");
            access$getFavoritesEntry$p3 = null;
        }
        EmptyLoadingStateView loading = access$getFavoritesEntry$p3.getLoading();
        Intrinsics.checkNotNull(list);
        if (!list.isEmpty()) {
            emptyLoadingState = EmptyLoadingState.NONE;
        } else {
            BrowserModel access$getLocalViewModel$p = mainBrowserFragment.localViewModel;
            if (access$getLocalViewModel$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localViewModel");
            } else {
                browserModel = access$getLocalViewModel$p;
            }
            if (Intrinsics.areEqual((Object) browserModel.getLoading().getValue(), (Object) true)) {
                emptyLoadingState = EmptyLoadingState.LOADING;
            } else {
                emptyLoadingState = EmptyLoadingState.EMPTY;
            }
        }
        loading.setState(emptyLoadingState);
    }
}
