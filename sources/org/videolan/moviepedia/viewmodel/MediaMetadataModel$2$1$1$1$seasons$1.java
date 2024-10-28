package org.videolan.moviepedia.viewmodel;

import java.util.List;
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
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.provider.MediaScrapingTvshowProvider;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/moviepedia/viewmodel/Season;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.viewmodel.MediaMetadataModel$2$1$1$1$seasons$1", f = "MediaMetadataModel.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$2$1$1$1$seasons$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Season>>, Object> {
    final /* synthetic */ MediaMetadataFull $mediaMetadataFull;
    int label;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$2$1$1$1$seasons$1(MediaMetadataModel mediaMetadataModel, MediaMetadataFull mediaMetadataFull, Continuation<? super MediaMetadataModel$2$1$1$1$seasons$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaMetadataModel;
        this.$mediaMetadataFull = mediaMetadataFull;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaMetadataModel$2$1$1$1$seasons$1(this.this$0, this.$mediaMetadataFull, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<Season>> continuation) {
        return ((MediaMetadataModel$2$1$1$1$seasons$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MediaScrapingTvshowProvider provider = this.this$0.getProvider();
            MediaMetadataWithImages metadata = this.$mediaMetadataFull.getMetadata();
            Intrinsics.checkNotNull(metadata);
            this.label = 1;
            obj = provider.getAllSeasons(metadata, this);
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
