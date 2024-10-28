package org.videolan.vlc.viewmodels;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.HistoryModel$updateList$2", f = "HistoryModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HistoryModel.kt */
final class HistoryModel$updateList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaWrapper>>, Object> {
    int label;
    final /* synthetic */ HistoryModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HistoryModel$updateList$2(HistoryModel historyModel, Continuation<? super HistoryModel$updateList$2> continuation) {
        super(2, continuation);
        this.this$0 = historyModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HistoryModel$updateList$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaWrapper>> continuation) {
        return ((HistoryModel$updateList$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper[] history = this.this$0.getMedialibrary().history(1);
            Intrinsics.checkNotNullExpressionValue(history, "history(...)");
            return ArraysKt.toMutableList((T[]) (Object[]) history);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
