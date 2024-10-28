package org.videolan.vlc.gui.video;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.vlc.media.MediaUtilsKt;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$5", f = "VideoGridFragment.kt", i = {}, l = {455}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$onActionItemClicked$5 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList<Folder> $selection;
    int label;
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$onActionItemClicked$5(VideoGridFragment videoGridFragment, ArrayList<Folder> arrayList, Continuation<? super VideoGridFragment$onActionItemClicked$5> continuation) {
        super(2, continuation);
        this.this$0 = videoGridFragment;
        this.$selection = arrayList;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoGridFragment$onActionItemClicked$5(this.this$0, this.$selection, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoGridFragment$onActionItemClicked$5) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (((VideosViewModel) this.this$0.getViewModel()).changeFavorite(MediaUtilsKt.getAll$default((List) this.$selection, 0, 0, false, false, 15, (Object) null), true, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
