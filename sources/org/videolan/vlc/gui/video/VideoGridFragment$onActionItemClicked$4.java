package org.videolan.vlc.gui.video;

import androidx.fragment.app.FragmentActivity;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.media.MediaUtilsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$4", f = "VideoGridFragment.kt", i = {}, l = {453}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$onActionItemClicked$4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ArrayList<Folder> $selection;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$onActionItemClicked$4(VideoGridFragment videoGridFragment, ArrayList<Folder> arrayList, Continuation<? super VideoGridFragment$onActionItemClicked$4> continuation) {
        super(2, continuation);
        this.this$0 = videoGridFragment;
        this.$selection = arrayList;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoGridFragment$onActionItemClicked$4(this.this$0, this.$selection, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoGridFragment$onActionItemClicked$4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$4$1", f = "VideoGridFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.gui.video.VideoGridFragment$onActionItemClicked$4$1  reason: invalid class name */
    /* compiled from: VideoGridFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaWrapper>>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(arrayList, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MediaWrapper>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return MediaUtilsKt.getAll$default((List) arrayList, 0, 0, false, false, 15, (Object) null);
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        UiTools uiTools;
        FragmentActivity fragmentActivity;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            uiTools = UiTools.INSTANCE;
            FragmentActivity requireActivity = this.this$0.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            final ArrayList<Folder> arrayList = this.$selection;
            this.L$0 = uiTools;
            this.L$1 = requireActivity;
            this.label = 1;
            Object withContext = BuildersKt.withContext(Dispatchers.getDefault(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            fragmentActivity = requireActivity;
            obj = withContext;
        } else if (i == 1) {
            fragmentActivity = (FragmentActivity) this.L$1;
            uiTools = (UiTools) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        uiTools.addToPlaylist(fragmentActivity, (List) obj);
        return Unit.INSTANCE;
    }
}
