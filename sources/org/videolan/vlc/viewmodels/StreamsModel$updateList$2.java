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
import org.videolan.vlc.util.DummyMediaWrapperProvider;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.StreamsModel$updateList$2", f = "StreamsModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: StreamsModel.kt */
final class StreamsModel$updateList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<MediaWrapper>>, Object> {
    int label;
    final /* synthetic */ StreamsModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StreamsModel$updateList$2(StreamsModel streamsModel, Continuation<? super StreamsModel$updateList$2> continuation) {
        super(2, continuation);
        this.this$0 = streamsModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new StreamsModel$updateList$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaWrapper>> continuation) {
        return ((StreamsModel$updateList$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper[] history = this.this$0.getMedialibrary().history(2);
            Intrinsics.checkNotNullExpressionValue(history, "history(...)");
            List mutableList = ArraysKt.toMutableList((T[]) (Object[]) history);
            StreamsModel streamsModel = this.this$0;
            MediaWrapper deletingMedia = streamsModel.getDeletingMedia();
            if (deletingMedia != null) {
                streamsModel.remove(deletingMedia);
            }
            if (streamsModel.showDummy) {
                mutableList.add(0, DummyMediaWrapperProvider.INSTANCE.getDummyMediaWrapper(-1));
            }
            return mutableList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
