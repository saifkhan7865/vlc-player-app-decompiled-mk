package org.videolan.moviepedia.provider;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.provider.TvShowResolver$getList$2", f = "MediaScrapingTvshowProvider.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvshowProvider.kt */
final class TvShowResolver$getList$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaMetadataWithImages>>, Object> {
    final /* synthetic */ String $moviepediaId;
    final /* synthetic */ MediaScrapingTvshowProvider $provider;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvShowResolver$getList$2(MediaScrapingTvshowProvider mediaScrapingTvshowProvider, String str, Continuation<? super TvShowResolver$getList$2> continuation) {
        super(2, continuation);
        this.$provider = mediaScrapingTvshowProvider;
        this.$moviepediaId = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TvShowResolver$getList$2(this.$provider, this.$moviepediaId, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<MediaMetadataWithImages>> continuation) {
        return ((TvShowResolver$getList$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String showIdForEpisode = this.$provider.getShowIdForEpisode(this.$moviepediaId);
            if (showIdForEpisode == null) {
                return null;
            }
            MediaScrapingTvshowProvider mediaScrapingTvshowProvider = this.$provider;
            this.label = 1;
            obj = mediaScrapingTvshowProvider.getAllEpisodesForShow(showIdForEpisode, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return (List) obj;
    }
}
