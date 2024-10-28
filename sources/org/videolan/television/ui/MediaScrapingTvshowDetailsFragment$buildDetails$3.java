package org.videolan.television.ui;

import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.ArrayObjectAdapter;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$buildDetails$3", f = "MediaScrapingTvshowDetailsFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
final class MediaScrapingTvshowDetailsFragment$buildDetails$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    int label;
    final /* synthetic */ MediaScrapingTvshowDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowDetailsFragment$buildDetails$3(FragmentActivity fragmentActivity, MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment, Continuation<? super MediaScrapingTvshowDetailsFragment$buildDetails$3> continuation) {
        super(2, continuation);
        this.$activity = fragmentActivity;
        this.this$0 = mediaScrapingTvshowDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingTvshowDetailsFragment$buildDetails$3(this.$activity, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingTvshowDetailsFragment$buildDetails$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.$activity.isFinishing()) {
                return Unit.INSTANCE;
            }
            MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment = this.this$0;
            ArrayObjectAdapter access$getRowsAdapter$p = mediaScrapingTvshowDetailsFragment.rowsAdapter;
            if (access$getRowsAdapter$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rowsAdapter");
                access$getRowsAdapter$p = null;
            }
            mediaScrapingTvshowDetailsFragment.setAdapter(access$getRowsAdapter$p);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
