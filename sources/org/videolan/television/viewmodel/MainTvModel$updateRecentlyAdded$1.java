package org.videolan.television.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$updateRecentlyAdded$1", f = "MainTvModel.kt", i = {}, l = {186}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$updateRecentlyAdded$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$updateRecentlyAdded$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$updateRecentlyAdded$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainTvModel$updateRecentlyAdded$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainTvModel$updateRecentlyAdded$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$updateRecentlyAdded$1$1", f = "MainTvModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.television.viewmodel.MainTvModel$updateRecentlyAdded$1$1  reason: invalid class name */
    /* compiled from: MainTvModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super LiveData<List<? extends MediaMetadataWithImages>>>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(mainTvModel, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super LiveData<List<MediaMetadataWithImages>>> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                return mainTvModel.mediaMetadataRepository.getRecentlyAdded();
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        MediatorLiveData<List<MediaMetadataWithImages>> mediatorLiveData;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MediatorLiveData<List<MediaMetadataWithImages>> recentlyAdded = this.this$0.getRecentlyAdded();
            final MainTvModel mainTvModel = this.this$0;
            this.L$0 = recentlyAdded;
            this.label = 1;
            Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            mediatorLiveData = recentlyAdded;
            obj = withContext;
        } else if (i == 1) {
            mediatorLiveData = (MediatorLiveData) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final MainTvModel mainTvModel2 = this.this$0;
        mediatorLiveData.addSource((LiveData) obj, new MainTvModelKt$sam$androidx_lifecycle_Observer$0(new Function1<List<? extends MediaMetadataWithImages>, Unit>() {
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((List<MediaMetadataWithImages>) (List) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(List<MediaMetadataWithImages> list) {
                mainTvModel2.getRecentlyAdded().setValue(list);
            }
        }));
        return Unit.INSTANCE;
    }
}
