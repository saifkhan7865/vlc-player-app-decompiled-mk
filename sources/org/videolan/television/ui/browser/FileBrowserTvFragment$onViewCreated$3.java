package org.videolan.television.ui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.viewmodels.browser.BrowserModel;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserTvFragment.kt */
final class FileBrowserTvFragment$onViewCreated$3 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ FileBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserTvFragment$onViewCreated$3(FileBrowserTvFragment fileBrowserTvFragment) {
        super(1);
        this.this$0 = fileBrowserTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            this.this$0.getBinding().emptyLoading.setState(EmptyLoadingState.LOADING);
        }
        if (!bool.booleanValue()) {
            TvBrowserModel viewModel = this.this$0.getViewModel();
            Intrinsics.checkNotNull(viewModel, "null cannot be cast to non-null type org.videolan.vlc.viewmodels.browser.BrowserModel");
            if (((BrowserModel) viewModel).getDataset().isEmpty()) {
                this.this$0.getBinding().emptyLoading.setState(EmptyLoadingState.EMPTY);
            }
        }
    }
}
