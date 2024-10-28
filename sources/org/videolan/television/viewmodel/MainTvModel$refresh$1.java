package org.videolan.television.viewmodel;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$refresh$1", f = "MainTvModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$refresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$refresh$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$refresh$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvModel$refresh$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainTvModel$refresh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            this.this$0.updateNowPlaying();
            Job unused = this.this$0.updateVideos();
            Job unused2 = this.this$0.updateRecentlyPlayed();
            Job unused3 = this.this$0.updateRecentlyAdded();
            this.this$0.updateAudioCategories();
            this.this$0.historyActor.m1139trySendJP2dKIU(Unit.INSTANCE);
            this.this$0.updateActor.m1139trySendJP2dKIU(Unit.INSTANCE);
            Job unused4 = this.this$0.updatePlaylists();
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
