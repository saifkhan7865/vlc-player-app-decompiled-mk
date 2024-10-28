package org.videolan.moviepedia.models.identify;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.moviepedia.models.resolver.ResolverMediaType;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.util.TextUtils;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a\n\u0010\u0006\u001a\u00020\u0005*\u00020\u0002\u001a \u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0004*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a\u001a\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a\u0012\u0010\n\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0005\u001a \u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u0004*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a\n\u0010\u000e\u001a\u00020\u000f*\u00020\u0010¨\u0006\u0011"}, d2 = {"getBackdropUri", "Landroid/net/Uri;", "Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "languages", "", "", "getShow", "retrieveBackdrops", "Lorg/videolan/moviepedia/models/identify/Backdrop;", "retrieveImageUri", "retrieveImageUriFromPath", "path", "retrievePosters", "Lorg/videolan/moviepedia/models/identify/Poster;", "toResolverClass", "Lorg/videolan/moviepedia/models/resolver/ResolverMediaType;", "Lorg/videolan/moviepedia/models/identify/MediaType;", "moviepedia_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: IdentifyResult.kt */
public final class IdentifyResultKt {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IdentifyResult.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                org.videolan.moviepedia.models.identify.MediaType[] r0 = org.videolan.moviepedia.models.identify.MediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.moviepedia.models.identify.MediaType r1 = org.videolan.moviepedia.models.identify.MediaType.MOVIE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.moviepedia.models.identify.MediaType r1 = org.videolan.moviepedia.models.identify.MediaType.TV_EPISODE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                org.videolan.moviepedia.models.identify.MediaType r1 = org.videolan.moviepedia.models.identify.MediaType.TV_SHOW     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.models.identify.IdentifyResultKt.WhenMappings.<clinit>():void");
        }
    }

    public static final ResolverMediaType toResolverClass(MediaType mediaType) {
        Intrinsics.checkNotNullParameter(mediaType, "<this>");
        int i = WhenMappings.$EnumSwitchMapping$0[mediaType.ordinal()];
        if (i == 1) {
            return ResolverMediaType.MOVIE;
        }
        if (i == 2) {
            return ResolverMediaType.TV_EPISODE;
        }
        if (i != 3) {
            return ResolverMediaType.TV_SEASON;
        }
        return ResolverMediaType.TV_SHOW;
    }

    public static final String getShow(MoviepediaMedia moviepediaMedia) {
        Intrinsics.checkNotNullParameter(moviepediaMedia, "<this>");
        TextUtils textUtils = TextUtils.INSTANCE;
        String showTitle = moviepediaMedia.getShowTitle();
        return textUtils.separatedStringArgs(showTitle, "S" + StringsKt.padStart(String.valueOf(moviepediaMedia.getSeason()), 2, '0') + 'E' + StringsKt.padStart(String.valueOf(moviepediaMedia.getEpisode()), 2, '0'));
    }

    public static final List<Poster> retrievePosters(MoviepediaMedia moviepediaMedia, List<String> list) {
        List<Poster> posters;
        Intrinsics.checkNotNullParameter(moviepediaMedia, "<this>");
        Intrinsics.checkNotNullParameter(list, "languages");
        Images images = moviepediaMedia.getImages();
        if (images == null || (posters = images.getPosters()) == null) {
            return null;
        }
        return CollectionsKt.sortedWith(posters, new IdentifyResultKt$$ExternalSyntheticLambda1(list));
    }

    /* access modifiers changed from: private */
    public static final int retrievePosters$lambda$0(List list, Poster poster, Poster poster2) {
        Intrinsics.checkNotNullParameter(list, "$languages");
        return -(list.indexOf(poster.getLanguage()) - list.indexOf(poster2.getLanguage()));
    }

    public static final Uri retrieveImageUri(MoviepediaMedia moviepediaMedia, List<String> list) {
        Poster poster;
        Intrinsics.checkNotNullParameter(moviepediaMedia, "<this>");
        Intrinsics.checkNotNullParameter(list, "languages");
        List<Poster> posters = moviepediaMedia.getPosters(list);
        if (posters == null || (poster = (Poster) CollectionsKt.firstOrNull(posters)) == null) {
            return null;
        }
        return Uri.parse(moviepediaMedia.getImageEndpoint() + "img" + poster.path());
    }

    public static final List<Backdrop> retrieveBackdrops(MoviepediaMedia moviepediaMedia, List<String> list) {
        List<Backdrop> backdrops;
        Intrinsics.checkNotNullParameter(moviepediaMedia, "<this>");
        Intrinsics.checkNotNullParameter(list, "languages");
        Images images = moviepediaMedia.getImages();
        if (images == null || (backdrops = images.getBackdrops()) == null) {
            return null;
        }
        return CollectionsKt.sortedWith(backdrops, new IdentifyResultKt$$ExternalSyntheticLambda0(list));
    }

    /* access modifiers changed from: private */
    public static final int retrieveBackdrops$lambda$2(List list, Backdrop backdrop, Backdrop backdrop2) {
        Intrinsics.checkNotNullParameter(list, "$languages");
        return -(list.indexOf(backdrop.getLanguage()) - list.indexOf(backdrop2.getLanguage()));
    }

    public static final Uri getBackdropUri(MoviepediaMedia moviepediaMedia, List<String> list) {
        Backdrop backdrop;
        Intrinsics.checkNotNullParameter(moviepediaMedia, "<this>");
        Intrinsics.checkNotNullParameter(list, "languages");
        List<Backdrop> backdrops = moviepediaMedia.getBackdrops(list);
        if (backdrops == null || (backdrop = (Backdrop) CollectionsKt.firstOrNull(backdrops)) == null) {
            return null;
        }
        return Uri.parse(moviepediaMedia.getImageEndpoint() + "img" + backdrop.path());
    }

    public static final String retrieveImageUriFromPath(MoviepediaMedia moviepediaMedia, String str) {
        Intrinsics.checkNotNullParameter(moviepediaMedia, "<this>");
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return moviepediaMedia.getImageEndpoint() + "img" + str;
    }
}
