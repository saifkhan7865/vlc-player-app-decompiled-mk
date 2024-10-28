package org.videolan.moviepedia.viewmodel;

import android.content.Context;
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
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$1$1", f = "MediaMetadataModel.kt", i = {1}, l = {69, 187}, m = "invokeSuspend", n = {"metadataWithImages"}, s = {"L$0"})
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaMetadataFull $mediaMetadataFull;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$1$1$1$1(MediaMetadataModel mediaMetadataModel, MediaMetadataFull mediaMetadataFull, Continuation<? super MediaMetadataModel$1$1$1$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaMetadataModel;
        this.$mediaMetadataFull = mediaMetadataFull;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaMetadataModel$1$1$1$1(this.this$0, this.$mediaMetadataFull, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaMetadataModel$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MediaMetadataWithImages mediaMetadataWithImages;
        MediaMetadataModel mediaMetadataModel;
        MediaMetadata metadata;
        Long mlId;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new MediaMetadataModel$1$1$1$1$metadataWithImages$1(this.this$0, this.$mediaMetadataFull, (Continuation<? super MediaMetadataModel$1$1$1$1$metadataWithImages$1>) null), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            mediaMetadataModel = (MediaMetadataModel) this.L$1;
            mediaMetadataWithImages = (MediaMetadataWithImages) this.L$0;
            ResultKt.throwOnFailure(obj);
            mediaMetadataWithImages.setMedia((MediaWrapper) obj);
            mediaMetadataModel.getNextEpisode().postValue(mediaMetadataWithImages);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        mediaMetadataWithImages = (MediaMetadataWithImages) obj;
        if (!(mediaMetadataWithImages == null || (metadata = mediaMetadataWithImages.getMetadata()) == null || (mlId = metadata.getMlId()) == null)) {
            MediaMetadataModel mediaMetadataModel2 = this.this$0;
            long longValue = mlId.longValue();
            Context access$getContext$p = mediaMetadataModel2.context;
            this.L$0 = mediaMetadataWithImages;
            this.L$1 = mediaMetadataModel2;
            this.label = 2;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new MediaMetadataModel$1$1$1$1$invokeSuspend$lambda$1$$inlined$getFromMl$1(access$getContext$p, (Continuation) null, longValue), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            mediaMetadataModel = mediaMetadataModel2;
            mediaMetadataWithImages.setMedia((MediaWrapper) obj);
            mediaMetadataModel.getNextEpisode().postValue(mediaMetadataWithImages);
        }
        return Unit.INSTANCE;
    }
}
