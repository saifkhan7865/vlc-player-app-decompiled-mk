package org.videolan.moviepedia.provider;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.viewmodel.Season;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "season", "Lorg/videolan/moviepedia/viewmodel/Season;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvshowProvider.kt */
final class MediaScrapingTvshowProvider$getFirstResumableEpisode$2 extends Lambda implements Function1<Season, MediaMetadataWithImages> {
    final /* synthetic */ Medialibrary $medialibrary;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowProvider$getFirstResumableEpisode$2(Medialibrary medialibrary) {
        super(1);
        this.$medialibrary = medialibrary;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: org.videolan.moviepedia.database.models.MediaMetadataWithImages} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v6, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v8, resolved type: org.videolan.medialibrary.interfaces.media.MediaWrapper} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.videolan.moviepedia.database.models.MediaMetadataWithImages invoke(org.videolan.moviepedia.viewmodel.Season r8) {
        /*
            r7 = this;
            java.lang.String r0 = "season"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.util.ArrayList r0 = r8.getEpisodes()
            java.util.List r0 = (java.util.List) r0
            int r1 = r0.size()
            r2 = 1
            if (r1 <= r2) goto L_0x001c
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getFirstResumableEpisode$2$invoke$$inlined$sortBy$1 r1 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getFirstResumableEpisode$2$invoke$$inlined$sortBy$1
            r1.<init>()
            java.util.Comparator r1 = (java.util.Comparator) r1
            kotlin.collections.CollectionsKt.sortWith(r0, r1)
        L_0x001c:
            java.util.ArrayList r8 = r8.getEpisodes()
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            kotlin.sequences.Sequence r8 = kotlin.collections.CollectionsKt.asSequence(r8)
            org.videolan.medialibrary.interfaces.Medialibrary r0 = r7.$medialibrary
            java.util.Iterator r8 = r8.iterator()
        L_0x002c:
            boolean r1 = r8.hasNext()
            r2 = 0
            if (r1 == 0) goto L_0x0068
            java.lang.Object r1 = r8.next()
            r3 = r1
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r3 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r3.getMedia()
            if (r4 != 0) goto L_0x0057
            org.videolan.moviepedia.database.models.MediaMetadata r4 = r3.getMetadata()
            java.lang.Long r4 = r4.getMlId()
            if (r4 == 0) goto L_0x0054
            java.lang.Number r4 = (java.lang.Number) r4
            long r4 = r4.longValue()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r0.getMedia((long) r4)
        L_0x0054:
            r3.setMedia(r2)
        L_0x0057:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r3.getMedia()
            if (r2 == 0) goto L_0x002c
            long r2 = r2.getSeen()
            r4 = 1
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 >= 0) goto L_0x002c
            r2 = r1
        L_0x0068:
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r2 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r2
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getFirstResumableEpisode$2.invoke(org.videolan.moviepedia.viewmodel.Season):org.videolan.moviepedia.database.models.MediaMetadataWithImages");
    }
}
