package org.videolan.moviepedia.models.resolver;

import android.net.Uri;
import java.util.Date;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\b\u001a\u00020\u0007H&J\n\u0010\t\u001a\u0004\u0018\u00010\nH&J\u000f\u0010\u000b\u001a\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\rJ\b\u0010\u000e\u001a\u00020\u0007H&J\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\n\u0010\u0011\u001a\u0004\u0018\u00010\u0007H&J\u0010\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H&J\u001e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00062\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\u0018\u0010\u0015\u001a\u0004\u0018\u00010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H&J\b\u0010\u0016\u001a\u00020\u0007H&J\b\u0010\u0017\u001a\u00020\u0018H&J\u000f\u0010\u0019\u001a\u0004\u0018\u00010\fH&¢\u0006\u0002\u0010\rJ\b\u0010\u001a\u001a\u00020\u0007H&J\b\u0010\u001b\u001a\u00020\u0007H&J\b\u0010\u001c\u001a\u00020\u0007H&J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0007H&¨\u0006\u001e"}, d2 = {"Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "", "()V", "backdropUri", "Landroid/net/Uri;", "languages", "", "", "countries", "date", "Ljava/util/Date;", "episode", "", "()Ljava/lang/Integer;", "genres", "getBackdrops", "Lorg/videolan/moviepedia/models/resolver/ResolverImage;", "getCardSubtitle", "getImageUriFromPath", "path", "getPosters", "imageUri", "mediaId", "mediaType", "Lorg/videolan/moviepedia/models/resolver/ResolverMediaType;", "season", "showId", "summary", "title", "year", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ResolverDataModel.kt */
public abstract class ResolverMedia {
    public abstract Uri backdropUri(List<String> list);

    public abstract String countries();

    public abstract Date date();

    public abstract Integer episode();

    public abstract String genres();

    public abstract List<ResolverImage> getBackdrops(List<String> list);

    public abstract String getCardSubtitle();

    public abstract String getImageUriFromPath(String str);

    public abstract List<ResolverImage> getPosters(List<String> list);

    public abstract Uri imageUri(List<String> list);

    public abstract String mediaId();

    public abstract ResolverMediaType mediaType();

    public abstract Integer season();

    public abstract String showId();

    public abstract String summary();

    public abstract String title();

    public abstract String year();
}
