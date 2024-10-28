package org.videolan.vlc.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.ModelsHelper;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001\"\b\b\u0000\u0010\u0004*\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "T", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
final class BaseModel$sections$2 extends Lambda implements Function0<LiveData<List<MediaLibraryItem>>> {
    final /* synthetic */ BaseModel<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseModel$sections$2(BaseModel<T> baseModel) {
        super(0);
        this.this$0 = baseModel;
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u00022\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0001H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "T", "it"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.BaseModel$sections$2$1", f = "BaseModel.kt", i = {}, l = {53}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.BaseModel$sections$2$1  reason: invalid class name */
    /* compiled from: BaseModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<List<T>, Continuation<? super List<MediaLibraryItem>>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(baseModel, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(List<T> list, Continuation<? super List<MediaLibraryItem>> continuation) {
            return ((AnonymousClass1) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                List list = (List) this.L$0;
                ModelsHelper modelsHelper = ModelsHelper.INSTANCE;
                int sort = baseModel.getSort();
                Intrinsics.checkNotNull(list);
                this.label = 1;
                obj = modelsHelper.generateSections(sort, CollectionsKt.toList(list), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return obj;
        }
    }

    public final LiveData<List<MediaLibraryItem>> invoke() {
        final BaseModel<T> baseModel = this.this$0;
        return KextensionsKt.map(ViewModelKt.getViewModelScope(this.this$0), this.this$0.getDataset(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }
}
