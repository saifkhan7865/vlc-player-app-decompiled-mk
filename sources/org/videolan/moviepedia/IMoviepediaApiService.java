package org.videolan.moviepedia;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.videolan.moviepedia.models.body.ScrobbleBody;
import org.videolan.moviepedia.models.body.ScrobbleBodyBatch;
import org.videolan.moviepedia.models.identify.IdentifyBatchResult;
import org.videolan.moviepedia.models.identify.IdentifyResult;
import org.videolan.moviepedia.models.identify.MoviepediaMedia;
import org.videolan.moviepedia.models.media.cast.CastResult;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u0004\u001a\u00020\u0005H§@¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000b\u001a\u00020\fH§@¢\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u000e\b\u0001\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00110\u000fH§@¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/videolan/moviepedia/IMoviepediaApiService;", "", "getMedia", "Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "mediaId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaCast", "Lorg/videolan/moviepedia/models/media/cast/CastResult;", "searchMedia", "Lorg/videolan/moviepedia/models/identify/IdentifyResult;", "body", "Lorg/videolan/moviepedia/models/body/ScrobbleBody;", "(Lorg/videolan/moviepedia/models/body/ScrobbleBody;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMediaBatch", "", "Lorg/videolan/moviepedia/models/identify/IdentifyBatchResult;", "Lorg/videolan/moviepedia/models/body/ScrobbleBodyBatch;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IMoviepediaApiService.kt */
public interface IMoviepediaApiService {
    @GET("media/{media}")
    Object getMedia(@Path("media") String str, Continuation<? super MoviepediaMedia> continuation);

    @GET("media/{media}/cast")
    Object getMediaCast(@Path("media") String str, Continuation<? super CastResult> continuation);

    @POST("search-media/identify")
    Object searchMedia(@Body ScrobbleBody scrobbleBody, Continuation<? super IdentifyResult> continuation);

    @POST("search-media/batchidentify")
    Object searchMediaBatch(@Body List<ScrobbleBodyBatch> list, Continuation<? super List<IdentifyBatchResult>> continuation);
}
