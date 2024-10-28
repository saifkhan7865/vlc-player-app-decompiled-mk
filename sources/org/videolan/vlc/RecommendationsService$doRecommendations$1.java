package org.videolan.vlc;

import android.app.NotificationManager;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.RecommendationsService$doRecommendations$1", f = "RecommendationsService.kt", i = {}, l = {101}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RecommendationsService.kt */
final class RecommendationsService$doRecommendations$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RecommendationsService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RecommendationsService$doRecommendations$1(RecommendationsService recommendationsService, Continuation<? super RecommendationsService$doRecommendations$1> continuation) {
        super(2, continuation);
        this.this$0 = recommendationsService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RecommendationsService$doRecommendations$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RecommendationsService$doRecommendations$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            NotificationManager access$getMNotificationManager$p = this.this$0.mNotificationManager;
            if (access$getMNotificationManager$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mNotificationManager");
                access$getMNotificationManager$p = null;
            }
            access$getMNotificationManager$p.cancelAll();
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new RecommendationsService$doRecommendations$1$videoList$1((Continuation<? super RecommendationsService$doRecommendations$1$videoList$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        MediaWrapper[] mediaWrapperArr = (MediaWrapper[]) obj;
        if (mediaWrapperArr == null || mediaWrapperArr.length == 0) {
            return Unit.INSTANCE;
        }
        Intrinsics.checkNotNull(mediaWrapperArr);
        int length = mediaWrapperArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.this$0.buildRecommendation(mediaWrapperArr[i2], i2, 3);
            if (i2 == 3) {
                break;
            }
        }
        return Unit.INSTANCE;
    }
}