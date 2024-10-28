package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.view.EmptyLoadingState;
import org.videolan.vlc.gui.view.TitleListView;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MLStorageBrowserFragment.kt */
final class MLStorageBrowserFragment$onViewCreated$2 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ MLStorageBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MLStorageBrowserFragment$onViewCreated$2(MLStorageBrowserFragment mLStorageBrowserFragment) {
        super(1);
        this.this$0 = mLStorageBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        Intrinsics.checkNotNull(bool);
        if (bool.booleanValue()) {
            TitleListView access$getLocalEntry$p = this.this$0.localEntry;
            if (access$getLocalEntry$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localEntry");
                access$getLocalEntry$p = null;
            }
            access$getLocalEntry$p.getLoading().setState(EmptyLoadingState.LOADING);
        }
    }
}
