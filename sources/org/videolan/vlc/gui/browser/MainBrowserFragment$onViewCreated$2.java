package org.videolan.vlc.gui.browser;

import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.EmptyLoadingStateView;
import org.videolan.vlc.gui.view.TitleListView;
import org.videolan.vlc.util.Permissions;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MainBrowserFragment.kt */
final class MainBrowserFragment$onViewCreated$2 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ MainBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainBrowserFragment$onViewCreated$2(MainBrowserFragment mainBrowserFragment) {
        super(1);
        this.this$0 = mainBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        EmptyLoadingStateView loading;
        EmptyLoadingState emptyLoadingState;
        Intrinsics.checkNotNull(bool);
        TitleListView titleListView = null;
        if (bool.booleanValue()) {
            TitleListView access$getLocalEntry$p = this.this$0.localEntry;
            if (access$getLocalEntry$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localEntry");
            } else {
                titleListView = access$getLocalEntry$p;
            }
            loading = titleListView.getLoading();
            emptyLoadingState = EmptyLoadingState.LOADING;
        } else {
            Permissions permissions = Permissions.INSTANCE;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            if (!permissions.canReadStorage(requireActivity)) {
                TitleListView access$getLocalEntry$p2 = this.this$0.localEntry;
                if (access$getLocalEntry$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("localEntry");
                } else {
                    titleListView = access$getLocalEntry$p2;
                }
                loading = titleListView.getLoading();
                emptyLoadingState = EmptyLoadingState.MISSING_PERMISSION;
            } else {
                return;
            }
        }
        loading.setState(emptyLoadingState);
    }
}
