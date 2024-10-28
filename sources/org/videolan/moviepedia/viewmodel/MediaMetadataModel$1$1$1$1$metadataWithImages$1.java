package org.videolan.moviepedia.viewmodel;

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
import org.videolan.moviepedia.repository.MediaMetadataRepository;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.viewmodel.MediaMetadataModel$1$1$1$1$metadataWithImages$1", f = "MediaMetadataModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$1$1$1$1$metadataWithImages$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaMetadataWithImages>, Object> {
    final /* synthetic */ MediaMetadataFull $mediaMetadataFull;
    int label;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$1$1$1$1$metadataWithImages$1(MediaMetadataModel mediaMetadataModel, MediaMetadataFull mediaMetadataFull, Continuation<? super MediaMetadataModel$1$1$1$1$metadataWithImages$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaMetadataModel;
        this.$mediaMetadataFull = mediaMetadataFull;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaMetadataModel$1$1$1$1$metadataWithImages$1(this.this$0, this.$mediaMetadataFull, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaMetadataWithImages> continuation) {
        return ((MediaMetadataModel$1$1$1$1$metadataWithImages$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaMetadataWithImages metadata = this.$mediaMetadataFull.getMetadata();
            Intrinsics.checkNotNull(metadata);
            String showId = metadata.getMetadata().getShowId();
            Intrinsics.checkNotNull(showId);
            MediaMetadataWithImages metadata2 = this.$mediaMetadataFull.getMetadata();
            Intrinsics.checkNotNull(metadata2);
            Integer season = metadata2.getMetadata().getSeason();
            Intrinsics.checkNotNull(season);
            int intValue = season.intValue();
            MediaMetadataWithImages metadata3 = this.$mediaMetadataFull.getMetadata();
            Intrinsics.checkNotNull(metadata3);
            Integer episode = metadata3.getMetadata().getEpisode();
            Intrinsics.checkNotNull(episode);
            return ((MediaMetadataRepository) MediaMetadataRepository.Companion.getInstance(this.this$0.context)).findNextEpisode(showId, intValue, episode.intValue());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
