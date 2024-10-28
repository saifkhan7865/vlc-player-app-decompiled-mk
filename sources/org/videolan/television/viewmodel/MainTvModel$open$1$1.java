package org.videolan.television.viewmodel;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.television.ui.DetailsActivity;
import org.videolan.television.ui.MediaItemDetails;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$open$1$1", f = "MainTvModel.kt", i = {}, l = {340}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$open$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ long $it;
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$open$1$1(MainTvModel mainTvModel, long j, FragmentActivity fragmentActivity, Continuation<? super MainTvModel$open$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
        this.$it = j;
        this.$activity = fragmentActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvModel$open$1$1(this.this$0, this.$it, this.$activity, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainTvModel$open$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Context context = this.this$0.getContext();
            long j = this.$it;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new MainTvModel$open$1$1$invokeSuspend$$inlined$getFromMl$1(context, (Continuation) null, j), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        FragmentActivity fragmentActivity = this.$activity;
        MediaWrapper mediaWrapper = (MediaWrapper) obj;
        Intent intent = new Intent(fragmentActivity, DetailsActivity.class);
        intent.putExtra("media", mediaWrapper);
        intent.putExtra("item", new MediaItemDetails(mediaWrapper.getTitle(), mediaWrapper.getArtist(), mediaWrapper.getAlbum(), mediaWrapper.getLocation(), mediaWrapper.getArtworkURL()));
        fragmentActivity.startActivity(intent);
        return Unit.INSTANCE;
    }
}
