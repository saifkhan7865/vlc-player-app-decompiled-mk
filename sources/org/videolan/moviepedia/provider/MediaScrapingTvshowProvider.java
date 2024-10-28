package org.videolan.moviepedia.provider;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.repository.MediaMetadataRepository;
import org.videolan.moviepedia.viewmodel.Season;
import org.videolan.resources.interfaces.IMediaContentResolver;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\nJ\u001c\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006J\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00062\u0006\u0010\u0010\u001a\u00020\u0007H@¢\u0006\u0002\u0010\u0011J \u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0007J\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0006J\u001c\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\f0\u00062\u0006\u0010\b\u001a\u00020\tH@¢\u0006\u0002\u0010\nJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\t2\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/videolan/moviepedia/provider/MediaScrapingTvshowProvider;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getAllEpisodesForShow", "", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllMedias", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "seasons", "Lorg/videolan/moviepedia/viewmodel/Season;", "getAllSeasons", "tvShow", "(Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFirstResumableEpisode", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "mediaMetadataEpisodes", "getResumeMedias", "getResumeMediasById", "getShowIdForEpisode", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvshowProvider.kt */
public final class MediaScrapingTvshowProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Context context;

    public MediaScrapingTvshowProvider(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
    }

    public final MediaMetadataWithImages getFirstResumableEpisode(Medialibrary medialibrary, List<MediaMetadataWithImages> list) {
        Object obj;
        Intrinsics.checkNotNullParameter(medialibrary, "medialibrary");
        Intrinsics.checkNotNullParameter(list, "mediaMetadataEpisodes");
        ArrayList arrayList = new ArrayList();
        for (MediaMetadataWithImages mediaMetadataWithImages : list) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                int seasonNumber = ((Season) obj).getSeasonNumber();
                Integer season = mediaMetadataWithImages.getMetadata().getSeason();
                if (season != null && seasonNumber == season.intValue()) {
                    break;
                }
            }
            Season season2 = (Season) obj;
            if (season2 == null) {
                Integer season3 = mediaMetadataWithImages.getMetadata().getSeason();
                season2 = new Season(season3 != null ? season3.intValue() : 0, (ArrayList) null, 2, (DefaultConstructorMarker) null);
                arrayList.add(season2);
            }
            season2.getEpisodes().add(mediaMetadataWithImages);
        }
        return (MediaMetadataWithImages) SequencesKt.firstOrNull(SequencesKt.mapNotNull(CollectionsKt.asSequence(arrayList), new MediaScrapingTvshowProvider$getFirstResumableEpisode$2(medialibrary)));
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x00ff A[LOOP:2: B:38:0x00ff->B:59:0x00ff, LOOP_END, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0152 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x011b A[EDGE_INSN: B:61:0x011b->B:44:0x011b ?: BREAK  , SYNTHETIC] */
    public final java.lang.Object getAllSeasons(org.videolan.moviepedia.database.models.MediaMetadataWithImages r14, kotlin.coroutines.Continuation<? super java.util.List<org.videolan.moviepedia.viewmodel.Season>> r15) {
        /*
            r13 = this;
            boolean r0 = r15 instanceof org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$1 r0 = (org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$1 r0 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$1
            r0.<init>(r13, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0048
            if (r2 != r3) goto L_0x0040
            java.lang.Object r14 = r0.L$4
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r14 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r14
            java.lang.Object r2 = r0.L$3
            java.util.Iterator r2 = (java.util.Iterator) r2
            java.lang.Object r5 = r0.L$2
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r0.L$1
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            java.lang.Object r7 = r0.L$0
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider r7 = (org.videolan.moviepedia.provider.MediaScrapingTvshowProvider) r7
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0149
        L_0x0040:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r15)
            throw r14
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r15)
            org.videolan.moviepedia.repository.MediaMetadataRepository$Companion r15 = org.videolan.moviepedia.repository.MediaMetadataRepository.Companion
            android.content.Context r2 = r13.context
            java.lang.Object r15 = r15.getInstance(r2)
            org.videolan.moviepedia.repository.MediaMetadataRepository r15 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r15
            org.videolan.moviepedia.database.models.MediaMetadata r14 = r14.getMetadata()
            java.lang.String r14 = r14.getMoviepediaId()
            java.util.List r14 = r15.getTvShowEpisodes(r14)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.Iterator r14 = r14.iterator()
        L_0x006c:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto L_0x00c8
            java.lang.Object r2 = r14.next()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r2 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r2
            r5 = r15
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
        L_0x007f:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00a2
            java.lang.Object r6 = r5.next()
            r7 = r6
            org.videolan.moviepedia.viewmodel.Season r7 = (org.videolan.moviepedia.viewmodel.Season) r7
            int r7 = r7.getSeasonNumber()
            org.videolan.moviepedia.database.models.MediaMetadata r8 = r2.getMetadata()
            java.lang.Integer r8 = r8.getSeason()
            if (r8 != 0) goto L_0x009b
            goto L_0x007f
        L_0x009b:
            int r8 = r8.intValue()
            if (r7 != r8) goto L_0x007f
            goto L_0x00a3
        L_0x00a2:
            r6 = r4
        L_0x00a3:
            org.videolan.moviepedia.viewmodel.Season r6 = (org.videolan.moviepedia.viewmodel.Season) r6
            if (r6 != 0) goto L_0x00c0
            org.videolan.moviepedia.viewmodel.Season r6 = new org.videolan.moviepedia.viewmodel.Season
            org.videolan.moviepedia.database.models.MediaMetadata r5 = r2.getMetadata()
            java.lang.Integer r5 = r5.getSeason()
            if (r5 == 0) goto L_0x00b8
            int r5 = r5.intValue()
            goto L_0x00b9
        L_0x00b8:
            r5 = 0
        L_0x00b9:
            r7 = 2
            r6.<init>(r5, r4, r7, r4)
            r15.add(r6)
        L_0x00c0:
            java.util.ArrayList r5 = r6.getEpisodes()
            r5.add(r2)
            goto L_0x006c
        L_0x00c8:
            r14 = r15
            java.lang.Iterable r14 = (java.lang.Iterable) r14
            java.util.Iterator r14 = r14.iterator()
            r2 = r13
        L_0x00d0:
            boolean r5 = r14.hasNext()
            if (r5 == 0) goto L_0x0155
            java.lang.Object r5 = r14.next()
            org.videolan.moviepedia.viewmodel.Season r5 = (org.videolan.moviepedia.viewmodel.Season) r5
            java.util.ArrayList r6 = r5.getEpisodes()
            java.util.List r6 = (java.util.List) r6
            int r7 = r6.size()
            if (r7 <= r3) goto L_0x00f2
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$lambda$9$$inlined$sortBy$1 r7 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$lambda$9$$inlined$sortBy$1
            r7.<init>()
            java.util.Comparator r7 = (java.util.Comparator) r7
            kotlin.collections.CollectionsKt.sortWith(r6, r7)
        L_0x00f2:
            java.util.ArrayList r5 = r5.getEpisodes()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.Iterator r5 = r5.iterator()
            r12 = r5
            r5 = r2
            r2 = r12
        L_0x00ff:
            boolean r6 = r2.hasNext()
            if (r6 == 0) goto L_0x0152
            java.lang.Object r6 = r2.next()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r6 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r6.getMedia()
            if (r7 != 0) goto L_0x00ff
            org.videolan.moviepedia.database.models.MediaMetadata r7 = r6.getMetadata()
            java.lang.Long r7 = r7.getMlId()
            if (r7 == 0) goto L_0x00ff
            java.lang.Number r7 = (java.lang.Number) r7
            long r7 = r7.longValue()
            android.content.Context r9 = r5.context
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$lambda$9$lambda$8$lambda$7$$inlined$getFromMl$1 r11 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllSeasons$lambda$9$lambda$8$lambda$7$$inlined$getFromMl$1
            r11.<init>(r9, r4, r7)
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r0.L$0 = r5
            r0.L$1 = r15
            r0.L$2 = r14
            r0.L$3 = r2
            r0.L$4 = r6
            r0.label = r3
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r10, r11, r0)
            if (r7 != r1) goto L_0x0143
            return r1
        L_0x0143:
            r12 = r5
            r5 = r14
            r14 = r6
            r6 = r15
            r15 = r7
            r7 = r12
        L_0x0149:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
            r14.setMedia(r15)
            r14 = r5
            r15 = r6
            r5 = r7
            goto L_0x00ff
        L_0x0152:
            r2 = r5
            goto L_0x00d0
        L_0x0155:
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.provider.MediaScrapingTvshowProvider.getAllSeasons(org.videolan.moviepedia.database.models.MediaMetadataWithImages, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final String getShowIdForEpisode(String str) {
        MediaMetadata metadata;
        String showId;
        MediaMetadataWithImages tvshow;
        MediaMetadata metadata2;
        Intrinsics.checkNotNullParameter(str, "id");
        MediaMetadataRepository mediaMetadataRepository = (MediaMetadataRepository) MediaMetadataRepository.Companion.getInstance(this.context);
        MediaMetadataWithImages mediaById = mediaMetadataRepository.getMediaById(str);
        if (mediaById == null || (metadata = mediaById.getMetadata()) == null || (showId = metadata.getShowId()) == null || (tvshow = mediaMetadataRepository.getTvshow(showId)) == null || (metadata2 = tvshow.getMetadata()) == null) {
            return null;
        }
        return metadata2.getMoviepediaId();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getResumeMediasById(java.lang.String r5, kotlin.coroutines.Continuation<? super java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getResumeMediasById$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getResumeMediasById$1 r0 = (org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getResumeMediasById$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getResumeMediasById$1 r0 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getResumeMediasById$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider r5 = (org.videolan.moviepedia.provider.MediaScrapingTvshowProvider) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x005a
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            org.videolan.moviepedia.repository.MediaMetadataRepository$Companion r6 = org.videolan.moviepedia.repository.MediaMetadataRepository.Companion
            android.content.Context r2 = r4.context
            java.lang.Object r6 = r6.getInstance(r2)
            org.videolan.moviepedia.repository.MediaMetadataRepository r6 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r6
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r5 = r6.getTvshow(r5)
            if (r5 == 0) goto L_0x0061
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r4.getAllSeasons(r5, r0)
            if (r6 != r1) goto L_0x0059
            return r1
        L_0x0059:
            r5 = r4
        L_0x005a:
            java.util.List r6 = (java.util.List) r6
            java.util.List r5 = r5.getResumeMedias(r6)
            return r5
        L_0x0061:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.provider.MediaScrapingTvshowProvider.getResumeMediasById(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<MediaWrapper> getResumeMedias(List<Season> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            boolean z = false;
            for (Season episodes : list) {
                for (MediaMetadataWithImages media : episodes.getEpisodes()) {
                    MediaWrapper media2 = media.getMedia();
                    if (media2 != null && (media2.getSeen() < 1 || z)) {
                        arrayList.add(media2);
                        z = true;
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0061 A[LOOP:0: B:18:0x005b->B:20:0x0061, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getAllEpisodesForShow(java.lang.String r5, kotlin.coroutines.Continuation<? super java.util.List<org.videolan.moviepedia.database.models.MediaMetadataWithImages>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllEpisodesForShow$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllEpisodesForShow$1 r0 = (org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllEpisodesForShow$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllEpisodesForShow$1 r0 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider$getAllEpisodesForShow$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004e
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            org.videolan.moviepedia.repository.MediaMetadataRepository$Companion r6 = org.videolan.moviepedia.repository.MediaMetadataRepository.Companion
            android.content.Context r2 = r4.context
            java.lang.Object r6 = r6.getInstance(r2)
            org.videolan.moviepedia.repository.MediaMetadataRepository r6 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r6
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r5 = r6.getTvshow(r5)
            if (r5 == 0) goto L_0x0074
            r0.label = r3
            java.lang.Object r6 = r4.getAllSeasons(r5, r0)
            if (r6 != r1) goto L_0x004e
            return r1
        L_0x004e:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r6 = r6.iterator()
        L_0x005b:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L_0x0071
            java.lang.Object r0 = r6.next()
            org.videolan.moviepedia.viewmodel.Season r0 = (org.videolan.moviepedia.viewmodel.Season) r0
            java.util.ArrayList r0 = r0.getEpisodes()
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            kotlin.collections.CollectionsKt.addAll(r5, r0)
            goto L_0x005b
        L_0x0071:
            java.util.List r5 = (java.util.List) r5
            goto L_0x0078
        L_0x0074:
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0078:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.provider.MediaScrapingTvshowProvider.getAllEpisodesForShow(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final List<MediaWrapper> getAllMedias(List<Season> list) {
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        Collection arrayList = new ArrayList();
        for (Season episodes : list) {
            CollectionsKt.addAll(arrayList, episodes.getEpisodes());
        }
        Collection arrayList2 = new ArrayList();
        for (MediaMetadataWithImages media : (List) arrayList) {
            MediaWrapper media2 = media.getMedia();
            if (media2 != null) {
                arrayList2.add(media2);
            }
        }
        return (List) arrayList2;
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¨\u0006\u0006"}, d2 = {"Lorg/videolan/moviepedia/provider/MediaScrapingTvshowProvider$Companion;", "", "()V", "getProviders", "", "Lorg/videolan/resources/interfaces/IMediaContentResolver;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingTvshowProvider.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<IMediaContentResolver> getProviders() {
            return CollectionsKt.listOf(ResumeResolver.INSTANCE, TvShowResolver.INSTANCE);
        }
    }
}
