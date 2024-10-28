package org.videolan.vlc.gui.audio;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.databinding.AudioBrowserBinding;
import org.videolan.vlc.gui.view.SwipeRefreshLayout;
import org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioBrowserFragment$onUpdateFinished$1", f = "AudioBrowserFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioBrowserFragment.kt */
final class AudioBrowserFragment$onUpdateFinished$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ RecyclerView.Adapter<?> $adapter;
    int label;
    final /* synthetic */ AudioBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioBrowserFragment$onUpdateFinished$1(RecyclerView.Adapter<?> adapter, AudioBrowserFragment audioBrowserFragment, Continuation<? super AudioBrowserFragment$onUpdateFinished$1> continuation) {
        super(2, continuation);
        this.$adapter = adapter;
        this.this$0 = audioBrowserFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioBrowserFragment$onUpdateFinished$1(this.$adapter, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioBrowserFragment$onUpdateFinished$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            AudioBrowserBinding audioBrowserBinding = null;
            boolean z = true;
            if (this.$adapter == this.this$0.getCurrentAdapter()) {
                SwipeRefreshLayout swipeRefreshLayout = this.this$0.getSwipeRefreshLayout();
                RecyclerView.LayoutManager layoutManager = this.this$0.getCurrentRV().getLayoutManager();
                Intrinsics.checkNotNull(layoutManager, "null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                if (((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() > 0) {
                    z = false;
                }
                swipeRefreshLayout.setEnabled(z);
                AudioBrowserBinding access$getBinding$p = this.this$0.binding;
                if (access$getBinding$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    audioBrowserBinding = access$getBinding$p;
                }
                audioBrowserBinding.songsFastScroller.setRecyclerView(this.this$0.getCurrentRV(), ((AudioBrowserViewModel) this.this$0.getViewModel()).getProviders()[this.this$0.getCurrentTab()]);
            } else {
                AudioBrowserFragment.setFabPlayShuffleAllVisibility$default(this.this$0, false, 1, (Object) null);
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
