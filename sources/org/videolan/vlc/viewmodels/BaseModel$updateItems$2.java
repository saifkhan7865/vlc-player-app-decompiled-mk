package org.videolan.vlc.viewmodels;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.BaseModel$updateItems$2", f = "BaseModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BaseModel.kt */
final class BaseModel$updateItems$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<T>>, Object> {
    final /* synthetic */ List<T> $mediaList;
    int label;
    final /* synthetic */ BaseModel<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseModel$updateItems$2(BaseModel<T> baseModel, List<? extends T> list, Continuation<? super BaseModel$updateItems$2> continuation) {
        super(2, continuation);
        this.this$0 = baseModel;
        this.$mediaList = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseModel$updateItems$2(this.this$0, this.$mediaList, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<T>> continuation) {
        return ((BaseModel$updateItems$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            List value = this.this$0.getDataset().getValue();
            ListIterator listIterator = value.listIterator();
            while (listIterator.hasNext()) {
                MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) listIterator.next();
                Iterator<T> it = this.$mediaList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MediaLibraryItem mediaLibraryItem2 = (MediaLibraryItem) it.next();
                    if (mediaLibraryItem.equals(mediaLibraryItem2)) {
                        listIterator.set(mediaLibraryItem2);
                        break;
                    }
                }
            }
            return value;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
