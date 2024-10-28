package org.videolan.vlc.gui.video;

import androidx.fragment.app.FragmentActivity;
import androidx.paging.InitialPagedList;
import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.viewmodels.mobile.VideosViewModel;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoGridFragment$setDataObservers$2", f = "VideoGridFragment.kt", i = {}, l = {708}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoGridFragment.kt */
final class VideoGridFragment$setDataObservers$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoGridFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoGridFragment$setDataObservers$2(VideoGridFragment videoGridFragment, Continuation<? super VideoGridFragment$setDataObservers$2> continuation) {
        super(2, continuation);
        this.this$0 = videoGridFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoGridFragment$setDataObservers$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoGridFragment$setDataObservers$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new VideoGridFragment$setDataObservers$2$invokeSuspend$$inlined$waitForML$1((Continuation) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final VideoGridFragment videoGridFragment = this.this$0;
        ((VideosViewModel) this.this$0.getViewModel()).getProvider().getPagedList().observe(this.this$0, new VideoGridFragmentKt$sam$androidx_lifecycle_Observer$0(new Function1<PagedList<? extends MediaLibraryItem>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((PagedList<? extends MediaLibraryItem>) (PagedList) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(PagedList<? extends MediaLibraryItem> pagedList) {
                FragmentActivity activity;
                CharSequence filterQuery;
                VideoListAdapter videoListAdapter = null;
                PagedList<? extends MediaLibraryItem> pagedList2 = pagedList == null ? null : pagedList;
                if (pagedList2 != null) {
                    VideoListAdapter access$getVideoListAdapter$p = videoGridFragment.videoListAdapter;
                    if (access$getVideoListAdapter$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("videoListAdapter");
                    } else {
                        videoListAdapter = access$getVideoListAdapter$p;
                    }
                    videoListAdapter.submitList(pagedList2);
                }
                videoGridFragment.updateEmptyView();
                videoGridFragment.restoreMultiSelectHelper();
                if (!(pagedList instanceof InitialPagedList) && (activity = videoGridFragment.getActivity()) != null && !activity.isFinishing() && ((VideosViewModel) videoGridFragment.getViewModel()).getGroup() != null && pagedList.size() < 2 && ((filterQuery = ((VideosViewModel) videoGridFragment.getViewModel()).getFilterQuery()) == null || filterQuery.length() == 0)) {
                    videoGridFragment.requireActivity().finish();
                }
                videoGridFragment.setFabPlayVisibility(true);
            }
        }));
        return Unit.INSTANCE;
    }
}
