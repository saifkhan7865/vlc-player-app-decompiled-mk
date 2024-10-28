package org.videolan.vlc.gui.video;

import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.video.VideoListAdapter;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoListAdapter$fillView$1", f = "VideoListAdapter.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoListAdapter.kt */
final class VideoListAdapter$fillView$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ VideoListAdapter.ViewHolder $holder;
    final /* synthetic */ MediaLibraryItem $item;
    int label;
    final /* synthetic */ VideoListAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoListAdapter$fillView$1(MediaLibraryItem mediaLibraryItem, VideoListAdapter.ViewHolder viewHolder, VideoListAdapter videoListAdapter, Continuation<? super VideoListAdapter$fillView$1> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
        this.$holder = viewHolder;
        this.this$0 = videoListAdapter;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoListAdapter$fillView$1(this.$item, this.$holder, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoListAdapter$fillView$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int mediaCount = ((VideoGroup) this.$item).mediaCount();
            boolean z = true;
            this.$holder.getBinding().setVariable(BR.time, mediaCount < 2 ? null : ((VideoGroup) this.$item).getPresentCount() == ((VideoGroup) this.$item).mediaCount() ? this.$holder.itemView.getContext().getResources().getQuantityString(R.plurals.videos_quantity, mediaCount, new Object[]{Boxing.boxInt(mediaCount)}) : ((VideoGroup) this.$item).getPresentCount() == 0 ? this.$holder.itemView.getContext().getResources().getString(R.string.no_video) : KextensionsKt.getPresenceDescription(this.$item));
            this.$holder.getTitle().setText(((VideoGroup) this.$item).getTitle());
            if (!this.this$0.isListMode()) {
                this.$holder.getBinding().setVariable(BR.resolution, (Object) null);
            }
            this.$holder.getBinding().setVariable(BR.seen, Boxing.boxLong((((VideoGroup) this.$item).getPresentSeen() != ((VideoGroup) this.$item).getPresentCount() || ((VideoGroup) this.$item).getPresentCount() == 0) ? 0 : 1));
            this.$holder.getBinding().setVariable(BR.max, Boxing.boxInt(0));
            ViewDataBinding binding = this.$holder.getBinding();
            int i = BR.isPresent;
            if (((VideoGroup) this.$item).getPresentCount() <= 0) {
                z = false;
            }
            binding.setVariable(i, Boxing.boxBoolean(z));
            this.$holder.getBinding().setVariable(BR.isFavorite, Boxing.boxBoolean(((VideoGroup) this.$item).isFavorite()));
            this.$holder.getBinding().setVariable(BR.media, this.$item);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
