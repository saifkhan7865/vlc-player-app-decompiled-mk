package org.videolan.television.ui;

import androidx.leanback.widget.Action;
import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.SparseArrayObjectAdapter;
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
import org.videolan.moviepedia.database.models.MediaMetadataKt;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$buildDetails$2", f = "MediaScrapingTvshowDetailsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
final class MediaScrapingTvshowDetailsFragment$buildDetails$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MediaScrapingTvshowDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowDetailsFragment$buildDetails$2(MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment, Continuation<? super MediaScrapingTvshowDetailsFragment$buildDetails$2> continuation) {
        super(2, continuation);
        this.this$0 = mediaScrapingTvshowDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingTvshowDetailsFragment$buildDetails$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingTvshowDetailsFragment$buildDetails$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.actionsAdapter = new SparseArrayObjectAdapter();
            MediaMetadataWithImages access$getFirstResumableEpisode = this.this$0.getFirstResumableEpisode();
            SparseArrayObjectAdapter access$getActionsAdapter$p = this.this$0.actionsAdapter;
            SparseArrayObjectAdapter sparseArrayObjectAdapter = null;
            if (access$getActionsAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("actionsAdapter");
                access$getActionsAdapter$p = null;
            }
            if (access$getFirstResumableEpisode == null) {
                str = this.this$0.getResources().getString(R.string.resume);
            } else {
                str = this.this$0.getResources().getString(R.string.resume_episode, new Object[]{MediaMetadataKt.tvEpisodeSubtitle(access$getFirstResumableEpisode)});
            }
            access$getActionsAdapter$p.set(1, new Action(1, str));
            SparseArrayObjectAdapter access$getActionsAdapter$p2 = this.this$0.actionsAdapter;
            if (access$getActionsAdapter$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("actionsAdapter");
                access$getActionsAdapter$p2 = null;
            }
            access$getActionsAdapter$p2.set(2, new Action(2, this.this$0.getResources().getString(R.string.start_over)));
            DetailsOverviewRow access$getDetailsOverview$p = this.this$0.detailsOverview;
            if (access$getDetailsOverview$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("detailsOverview");
                access$getDetailsOverview$p = null;
            }
            SparseArrayObjectAdapter access$getActionsAdapter$p3 = this.this$0.actionsAdapter;
            if (access$getActionsAdapter$p3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("actionsAdapter");
            } else {
                sparseArrayObjectAdapter = access$getActionsAdapter$p3;
            }
            access$getDetailsOverview$p.setActionsAdapter(sparseArrayObjectAdapter);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
