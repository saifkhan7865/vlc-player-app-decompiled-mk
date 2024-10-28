package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.TitleListView;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainBrowserFragment.kt */
final class MainBrowserFragment$onViewCreated$8 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ MainBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainBrowserFragment$onViewCreated$8(MainBrowserFragment mainBrowserFragment) {
        super(1);
        this.this$0 = mainBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        Intrinsics.checkNotNull(bool);
        TitleListView titleListView = null;
        if (bool.booleanValue()) {
            TitleListView access$getNetworkEntry$p = this.this$0.networkEntry;
            if (access$getNetworkEntry$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
                access$getNetworkEntry$p = null;
            }
            access$getNetworkEntry$p.getLoading().setState(EmptyLoadingState.LOADING);
        }
        MainBrowserFragment mainBrowserFragment = this.this$0;
        TitleListView access$getNetworkEntry$p2 = mainBrowserFragment.networkEntry;
        if (access$getNetworkEntry$p2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("networkEntry");
        } else {
            titleListView = access$getNetworkEntry$p2;
        }
        mainBrowserFragment.updateNetworkEmptyView(titleListView.getLoading());
    }
}
