package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.databinding.VideoGridBinding;
import org.videolan.vlc.gui.view.AutoFitRecyclerView;
import org.videolan.vlc.gui.view.FastScroller;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$onCreate$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$onCreate$2(VideoGridFragment videoGridFragment) {
        super(0);
        this.this$0 = videoGridFragment;
    }

    public final void invoke() {
        this.this$0.updateEmptyView();
        if (this.this$0.binding != null) {
            VideoGridBinding access$getBinding$p = this.this$0.binding;
            VideoGridBinding videoGridBinding = null;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            FastScroller fastScroller = access$getBinding$p.fastScroller;
            VideoGridBinding access$getBinding$p2 = this.this$0.binding;
            if (access$getBinding$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                videoGridBinding = access$getBinding$p2;
            }
            AutoFitRecyclerView autoFitRecyclerView = videoGridBinding.videoGrid;
            Intrinsics.checkNotNullExpressionValue(autoFitRecyclerView, "videoGrid");
            fastScroller.setRecyclerView(autoFitRecyclerView, ((VideosViewModel) this.this$0.getViewModel()).getProvider());
        }
    }
}
