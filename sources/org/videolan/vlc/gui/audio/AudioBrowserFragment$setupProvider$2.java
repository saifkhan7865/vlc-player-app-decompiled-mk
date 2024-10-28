package org.videolan.vlc.gui.audio;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.databinding.AudioBrowserBinding;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;
import org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "loading", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioBrowserFragment.kt */
final class AudioBrowserFragment$setupProvider$2 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ int $index;
    final /* synthetic */ AudioBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioBrowserFragment$setupProvider$2(AudioBrowserFragment audioBrowserFragment, int i) {
        super(1);
        this.this$0 = audioBrowserFragment;
        this.$index = i;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        if (bool != null && this.this$0.getCurrentTab() == this.$index) {
            AudioBrowserFragment audioBrowserFragment = this.this$0;
            boolean booleanValue = bool.booleanValue();
            final AudioBrowserFragment audioBrowserFragment2 = this.this$0;
            audioBrowserFragment.setRefreshing(booleanValue, new Function1<Boolean, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke(((Boolean) obj).booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        audioBrowserFragment2.updateEmptyView();
                        return;
                    }
                    SwipeRefreshLayout swipeRefreshLayout = audioBrowserFragment2.getSwipeRefreshLayout();
                    RecyclerView.LayoutManager layoutManager = audioBrowserFragment2.getCurrentRV().getLayoutManager();
                    Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    swipeRefreshLayout.setEnabled(((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() <= 0);
                    AudioBrowserBinding access$getBinding$p = audioBrowserFragment2.binding;
                    if (access$getBinding$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p = null;
                    }
                    access$getBinding$p.songsFastScroller.setRecyclerView(audioBrowserFragment2.getCurrentRV(), ((AudioBrowserViewModel) audioBrowserFragment2.getViewModel()).getProviders()[audioBrowserFragment2.getCurrentTab()]);
                }
            });
        }
    }
}
