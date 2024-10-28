package org.videolan.television.viewmodel;

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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$updateVideos$1$allTvshows$1", f = "MainTvModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$updateVideos$1$allTvshows$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Integer>, Object> {
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$updateVideos$1$allTvshows$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$updateVideos$1$allTvshows$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvModel$updateVideos$1$allTvshows$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Integer> continuation) {
        return ((MainTvModel$updateVideos$1$allTvshows$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return Boxing.boxInt(this.this$0.mediaMetadataRepository.getTvshowsCount());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
