package org.videolan.moviepedia.repository;

import android.net.Uri;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import org.videolan.moviepedia.models.resolver.ResolverBatchResult;
import org.videolan.moviepedia.models.resolver.ResolverCasting;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.models.resolver.ResolverResult;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H¦@¢\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H¦@¢\u0006\u0002\u0010\u0007J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH¦@¢\u0006\u0002\u0010\u000fJ(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000e0\u0014H¦@¢\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0006H¦@¢\u0006\u0002\u0010\u0007¨\u0006\u0019"}, d2 = {"Lorg/videolan/moviepedia/repository/MediaResolverApi;", "", "()V", "getMedia", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "showId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaCast", "Lorg/videolan/moviepedia/models/resolver/ResolverCasting;", "resolverId", "searchMedia", "Lorg/videolan/moviepedia/models/resolver/ResolverResult;", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMediaBatch", "", "Lorg/videolan/moviepedia/models/resolver/ResolverBatchResult;", "filesToIndex", "Ljava/util/HashMap;", "", "(Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchTitle", "query", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaResolverApi.kt */
public abstract class MediaResolverApi {
    public abstract Object getMedia(String str, Continuation<? super ResolverMedia> continuation);

    public abstract Object getMediaCast(String str, Continuation<? super ResolverCasting> continuation);

    public abstract Object searchMedia(Uri uri, Continuation<? super ResolverResult> continuation);

    public abstract Object searchMediaBatch(HashMap<Long, Uri> hashMap, Continuation<? super List<? extends ResolverBatchResult>> continuation);

    public abstract Object searchTitle(String str, Continuation<? super ResolverResult> continuation);
}
