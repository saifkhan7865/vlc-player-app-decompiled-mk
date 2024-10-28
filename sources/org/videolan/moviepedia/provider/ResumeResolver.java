package org.videolan.moviepedia.provider;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.resources.interfaces.IMediaContentResolver;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0007\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H@¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lorg/videolan/moviepedia/provider/ResumeResolver;", "Lorg/videolan/resources/interfaces/IMediaContentResolver;", "()V", "prefix", "", "getPrefix", "()Ljava/lang/String;", "getList", "Lkotlin/Pair;", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "", "context", "Landroid/content/Context;", "id", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvshowProvider.kt */
final class ResumeResolver implements IMediaContentResolver {
    public static final ResumeResolver INSTANCE = new ResumeResolver();
    private static final String prefix = Constants.CONTENT_RESUME;

    private ResumeResolver() {
    }

    public String getPrefix() {
        return prefix;
    }

    public Object getList(Context context, String str, Continuation<? super Pair<? extends List<? extends MediaWrapper>, Integer>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new ResumeResolver$getList$2(new MediaScrapingTvshowProvider(context), str, (Continuation<? super ResumeResolver$getList$2>) null), continuation);
    }
}
