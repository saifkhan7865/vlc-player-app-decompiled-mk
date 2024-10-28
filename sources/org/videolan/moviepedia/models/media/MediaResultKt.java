package org.videolan.moviepedia.models.media;

import android.net.Uri;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.models.identify.Images;
import org.videolan.moviepedia.models.identify.Poster;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002\u001a\f\u0010\u0003\u001a\u0004\u0018\u00010\u0004*\u00020\u0002¨\u0006\u0005"}, d2 = {"getImageUri", "Landroid/net/Uri;", "Lorg/videolan/moviepedia/models/media/MediaResult;", "getYear", "", "moviepedia_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaResult.kt */
public final class MediaResultKt {
    public static final Uri getImageUri(MediaResult mediaResult) {
        List<Poster> posters;
        Poster poster;
        Intrinsics.checkNotNullParameter(mediaResult, "<this>");
        Images images = mediaResult.getImages();
        if (images == null || (posters = images.getPosters()) == null || (poster = (Poster) CollectionsKt.firstOrNull(posters)) == null) {
            return null;
        }
        return Uri.parse(mediaResult.getImageEndpoint() + "img" + poster.getPath());
    }

    public static final String getYear(MediaResult mediaResult) {
        Intrinsics.checkNotNullParameter(mediaResult, "<this>");
        if (mediaResult.getDate() != null) {
            return new SimpleDateFormat("yyyy", Locale.getDefault()).format(mediaResult.getDate());
        }
        return null;
    }
}
