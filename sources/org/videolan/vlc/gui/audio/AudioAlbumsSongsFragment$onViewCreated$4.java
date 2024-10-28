package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.BaseFragment;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.viewmodels.mobile.AlbumSongsViewModel;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "loading", "", "kotlin.jvm.PlatformType", "invoke", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioAlbumsSongsFragment.kt */
final class AudioAlbumsSongsFragment$onViewCreated$4 extends Lambda implements Function1<Boolean, Unit> {
    final /* synthetic */ AudioAlbumsSongsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioAlbumsSongsFragment$onViewCreated$4(AudioAlbumsSongsFragment audioAlbumsSongsFragment) {
        super(1);
        this.this$0 = audioAlbumsSongsFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Boolean) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Boolean bool) {
        if (!bool.booleanValue()) {
            FastScroller access$getFastScroller$p = this.this$0.fastScroller;
            if (access$getFastScroller$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fastScroller");
                access$getFastScroller$p = null;
            }
            access$getFastScroller$p.setRecyclerView(this.this$0.getCurrentRV(), ((AlbumSongsViewModel) this.this$0.getViewModel()).getProviders()[this.this$0.getCurrentTab()]);
        }
        Intrinsics.checkNotNull(bool);
        BaseFragment.setRefreshing$default(this.this$0, bool.booleanValue(), (Function1) null, 2, (Object) null);
    }
}
