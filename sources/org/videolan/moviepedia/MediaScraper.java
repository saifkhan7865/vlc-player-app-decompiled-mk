package org.videolan.moviepedia;

import android.content.Context;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.database.models.MediaPersonJoin;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.repository.MediaPersonRepository;
import org.videolan.moviepedia.repository.MediaResolverApi;
import org.videolan.moviepedia.repository.PersonRepository;
import org.videolan.resources.interfaces.IndexingListener;
import org.videolan.tools.AppScope;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H@¢\u0006\u0002\u0010\u0016J<\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0011\u001a\u00020\u001dH@¢\u0006\u0002\u0010\u001eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001b\u0010\u0007\u001a\u00020\b8FX\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001f"}, d2 = {"Lorg/videolan/moviepedia/MediaScraper;", "", "()V", "indexListener", "Lorg/videolan/resources/interfaces/IndexingListener;", "getIndexListener", "()Lorg/videolan/resources/interfaces/IndexingListener;", "mediaResolverApi", "Lorg/videolan/moviepedia/repository/MediaResolverApi;", "getMediaResolverApi", "()Lorg/videolan/moviepedia/repository/MediaResolverApi;", "mediaResolverApi$delegate", "Lkotlin/Lazy;", "indexMedialib", "Lkotlinx/coroutines/Job;", "context", "Landroid/content/Context;", "removePersonOrphans", "", "retrieveCasting", "mediaMetadata", "Lorg/videolan/moviepedia/database/models/MediaMetadata;", "(Landroid/content/Context;Lorg/videolan/moviepedia/database/models/MediaMetadata;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveMediaMetadata", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "item", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "retrieveCast", "", "(Landroid/content/Context;Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;Lorg/videolan/moviepedia/models/resolver/ResolverMedia;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScraper.kt */
public final class MediaScraper {
    public static final MediaScraper INSTANCE = new MediaScraper();
    private static final IndexingListener indexListener = new MediaScraper$indexListener$1();
    private static final Lazy mediaResolverApi$delegate = LazyKt.lazy(MediaScraper$mediaResolverApi$2.INSTANCE);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScraper.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                org.videolan.moviepedia.models.resolver.ResolverMediaType[] r0 = org.videolan.moviepedia.models.resolver.ResolverMediaType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                org.videolan.moviepedia.models.resolver.ResolverMediaType r1 = org.videolan.moviepedia.models.resolver.ResolverMediaType.TV_EPISODE     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                org.videolan.moviepedia.models.resolver.ResolverMediaType r1 = org.videolan.moviepedia.models.resolver.ResolverMediaType.MOVIE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.MediaScraper.WhenMappings.<clinit>():void");
        }
    }

    private MediaScraper() {
    }

    public final MediaResolverApi getMediaResolverApi() {
        return (MediaResolverApi) mediaResolverApi$delegate.getValue();
    }

    public final Job indexMedialib(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BuildersKt__Builders_commonKt.launch$default(AppScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new MediaScraper$indexMedialib$1(context, (Continuation<? super MediaScraper$indexMedialib$1>) null), 2, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void removePersonOrphans(Context context) {
        List<Person> all = ((PersonRepository) PersonRepository.Companion.getInstance(context)).getAll();
        List<MediaPersonJoin> all2 = ((MediaPersonRepository) MediaPersonRepository.Companion.getInstance(context)).getAll();
        Collection arrayList = new ArrayList();
        for (Object next : all) {
            Person person = (Person) next;
            Iterable iterable = all2;
            boolean z = false;
            if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                Iterator it = iterable.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (Intrinsics.areEqual((Object) person.getMoviepediaId(), (Object) ((MediaPersonJoin) it.next()).getPersonId())) {
                            z = true;
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            if (!z) {
                arrayList.add(next);
            }
        }
        ((PersonRepository) PersonRepository.Companion.getInstance(context)).deleteAll((List) arrayList);
    }

    public static /* synthetic */ Object saveMediaMetadata$default(MediaScraper mediaScraper, Context context, MediaWrapper mediaWrapper, ResolverMedia resolverMedia, boolean z, boolean z2, Continuation continuation, int i, Object obj) {
        return mediaScraper.saveMediaMetadata(context, mediaWrapper, resolverMedia, (i & 8) != 0 ? true : z, (i & 16) != 0 ? true : z2, continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x026a A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x013d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0184  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x01d9  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x01de  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x022e  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x02ae  */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x02be  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x02e0  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x02e3  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object saveMediaMetadata(android.content.Context r36, org.videolan.medialibrary.interfaces.media.MediaWrapper r37, org.videolan.moviepedia.models.resolver.ResolverMedia r38, boolean r39, boolean r40, kotlin.coroutines.Continuation<? super kotlin.Unit> r41) {
        /*
            r35 = this;
            r0 = r35
            r1 = r36
            r2 = r41
            boolean r3 = r2 instanceof org.videolan.moviepedia.MediaScraper$saveMediaMetadata$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.moviepedia.MediaScraper$saveMediaMetadata$1 r3 = (org.videolan.moviepedia.MediaScraper$saveMediaMetadata$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.moviepedia.MediaScraper$saveMediaMetadata$1 r3 = new org.videolan.moviepedia.MediaScraper$saveMediaMetadata$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r11 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r3.label
            r12 = 3
            r5 = 2
            r13 = 1
            if (r4 == 0) goto L_0x0096
            if (r4 == r13) goto L_0x006a
            if (r4 == r5) goto L_0x0049
            if (r4 != r12) goto L_0x0041
            boolean r1 = r3.Z$0
            java.lang.Object r4 = r3.L$1
            android.content.Context r4 = (android.content.Context) r4
            java.lang.Object r3 = r3.L$0
            org.videolan.moviepedia.MediaScraper r3 = (org.videolan.moviepedia.MediaScraper) r3
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x02dc
        L_0x0041:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0049:
            boolean r1 = r3.Z$1
            boolean r4 = r3.Z$0
            java.lang.Object r5 = r3.L$5
            org.videolan.moviepedia.repository.MediaMetadataRepository r5 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r5
            java.lang.Object r6 = r3.L$4
            org.videolan.moviepedia.database.models.MediaMetadataType r6 = (org.videolan.moviepedia.database.models.MediaMetadataType) r6
            java.lang.Object r7 = r3.L$3
            org.videolan.moviepedia.models.resolver.ResolverMedia r7 = (org.videolan.moviepedia.models.resolver.ResolverMedia) r7
            java.lang.Object r8 = r3.L$2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            java.lang.Object r9 = r3.L$1
            android.content.Context r9 = (android.content.Context) r9
            java.lang.Object r10 = r3.L$0
            org.videolan.moviepedia.MediaScraper r10 = (org.videolan.moviepedia.MediaScraper) r10
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x014b
        L_0x006a:
            boolean r1 = r3.Z$1
            boolean r4 = r3.Z$0
            java.lang.Object r6 = r3.L$5
            org.videolan.moviepedia.repository.MediaMetadataRepository r6 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r6
            java.lang.Object r7 = r3.L$4
            org.videolan.moviepedia.database.models.MediaMetadataType r7 = (org.videolan.moviepedia.database.models.MediaMetadataType) r7
            java.lang.Object r8 = r3.L$3
            org.videolan.moviepedia.models.resolver.ResolverMedia r8 = (org.videolan.moviepedia.models.resolver.ResolverMedia) r8
            java.lang.Object r9 = r3.L$2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r9
            java.lang.Object r10 = r3.L$1
            android.content.Context r10 = (android.content.Context) r10
            java.lang.Object r15 = r3.L$0
            org.videolan.moviepedia.MediaScraper r15 = (org.videolan.moviepedia.MediaScraper) r15
            kotlin.ResultKt.throwOnFailure(r2)
            r34 = r9
            r9 = r1
            r1 = r10
            r10 = r4
            r4 = r15
            r15 = r8
            r8 = r6
            r6 = r2
            r2 = r34
            goto L_0x0111
        L_0x0096:
            kotlin.ResultKt.throwOnFailure(r2)
            org.videolan.moviepedia.models.resolver.ResolverMediaType r2 = r38.mediaType()
            int[] r4 = org.videolan.moviepedia.MediaScraper.WhenMappings.$EnumSwitchMapping$0
            int r2 = r2.ordinal()
            r2 = r4[r2]
            if (r2 == r13) goto L_0x00af
            if (r2 == r5) goto L_0x00ac
            org.videolan.moviepedia.database.models.MediaMetadataType r2 = org.videolan.moviepedia.database.models.MediaMetadataType.TV_SHOW
            goto L_0x00b1
        L_0x00ac:
            org.videolan.moviepedia.database.models.MediaMetadataType r2 = org.videolan.moviepedia.database.models.MediaMetadataType.MOVIE
            goto L_0x00b1
        L_0x00af:
            org.videolan.moviepedia.database.models.MediaMetadataType r2 = org.videolan.moviepedia.database.models.MediaMetadataType.TV_EPISODE
        L_0x00b1:
            org.videolan.moviepedia.repository.MediaMetadataRepository$Companion r4 = org.videolan.moviepedia.repository.MediaMetadataRepository.Companion
            java.lang.Object r4 = r4.getInstance(r1)
            org.videolan.moviepedia.repository.MediaMetadataRepository r4 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r4
            org.videolan.moviepedia.models.resolver.ResolverMediaType r6 = r38.mediaType()
            int[] r7 = org.videolan.moviepedia.MediaScraper.WhenMappings.$EnumSwitchMapping$0
            int r6 = r6.ordinal()
            r6 = r7[r6]
            if (r6 != r13) goto L_0x0169
            java.lang.String r6 = r38.showId()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r6 = r4.getTvshow(r6)
            if (r6 == 0) goto L_0x00dc
            org.videolan.moviepedia.database.models.MediaMetadata r6 = r6.getMetadata()
            if (r6 == 0) goto L_0x00dc
            java.lang.String r6 = r6.getMoviepediaId()
            goto L_0x00dd
        L_0x00dc:
            r6 = 0
        L_0x00dd:
            if (r6 != 0) goto L_0x0159
            org.videolan.moviepedia.repository.MediaResolverApi r6 = r35.getMediaResolverApi()
            java.lang.String r7 = r38.showId()
            r3.L$0 = r0
            r3.L$1 = r1
            r8 = r37
            r3.L$2 = r8
            r9 = r38
            r3.L$3 = r9
            r3.L$4 = r2
            r3.L$5 = r4
            r10 = r39
            r3.Z$0 = r10
            r15 = r40
            r3.Z$1 = r15
            r3.label = r13
            java.lang.Object r6 = r6.getMedia(r7, r3)
            if (r6 != r11) goto L_0x0108
            return r11
        L_0x0108:
            r7 = r2
            r2 = r8
            r8 = r4
            r4 = r0
            r34 = r15
            r15 = r9
            r9 = r34
        L_0x0111:
            r16 = r6
            org.videolan.moviepedia.models.resolver.ResolverMedia r16 = (org.videolan.moviepedia.models.resolver.ResolverMedia) r16
            r3.L$0 = r4
            r3.L$1 = r1
            r3.L$2 = r2
            r3.L$3 = r15
            r3.L$4 = r7
            r3.L$5 = r8
            r3.Z$0 = r10
            r3.Z$1 = r9
            r3.label = r5
            r6 = 0
            r17 = r4
            r5 = r1
            r18 = r7
            r7 = r16
            r16 = r8
            r8 = r10
            r19 = r9
            r20 = r10
            r10 = r3
            java.lang.Object r4 = r4.saveMediaMetadata(r5, r6, r7, r8, r9, r10)
            if (r4 != r11) goto L_0x013e
            return r11
        L_0x013e:
            r9 = r1
            r8 = r2
            r7 = r15
            r5 = r16
            r10 = r17
            r6 = r18
            r1 = r19
            r4 = r20
        L_0x014b:
            java.lang.String r2 = r7.showId()
            r15 = r1
            r1 = r10
            r10 = r4
            r4 = r5
            r34 = r6
            r6 = r2
            r2 = r34
            goto L_0x0164
        L_0x0159:
            r8 = r37
            r9 = r38
            r10 = r39
            r15 = r40
            r7 = r9
            r9 = r1
            r1 = r0
        L_0x0164:
            r19 = r2
            r29 = r6
            goto L_0x0178
        L_0x0169:
            r8 = r37
            r9 = r38
            r10 = r39
            r15 = r40
            r19 = r2
            r7 = r9
            r29 = 0
            r9 = r1
            r1 = r0
        L_0x0178:
            java.util.List r2 = org.videolan.tools.LocaleUtilsKt.getLocaleLanguages(r9)
            org.videolan.moviepedia.database.models.MediaMetadata r5 = new org.videolan.moviepedia.database.models.MediaMetadata
            java.lang.String r17 = r7.mediaId()
            if (r8 == 0) goto L_0x018f
            long r20 = r8.getId()
            java.lang.Long r6 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r20)
            r18 = r6
            goto L_0x0191
        L_0x018f:
            r18 = 0
        L_0x0191:
            java.lang.String r20 = r7.title()
            java.lang.String r21 = r7.summary()
            java.lang.String r22 = r7.genres()
            java.util.Date r23 = r7.date()
            java.lang.String r24 = r7.countries()
            java.lang.Integer r25 = r7.season()
            java.lang.Integer r26 = r7.episode()
            android.net.Uri r6 = r7.imageUri(r2)
            java.lang.String r27 = java.lang.String.valueOf(r6)
            android.net.Uri r6 = r7.backdropUri(r2)
            java.lang.String r28 = java.lang.String.valueOf(r6)
            r32 = 16384(0x4000, float:2.2959E-41)
            r33 = 0
            r30 = 0
            r31 = 0
            r16 = r5
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33)
            r6 = r15
            if (r8 == 0) goto L_0x01d6
            long r14 = r8.getId()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r8 = r4.getMetadata(r14)
            goto L_0x01d7
        L_0x01d6:
            r8 = 0
        L_0x01d7:
            if (r8 == 0) goto L_0x01de
            java.util.List r8 = r8.getImages()
            goto L_0x01df
        L_0x01de:
            r8 = 0
        L_0x01df:
            r4.addMetadataImmediate(r5)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.List r15 = r7.getBackdrops(r2)
            if (r15 == 0) goto L_0x0226
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.Iterator r15 = r15.iterator()
        L_0x01f3:
            boolean r16 = r15.hasNext()
            if (r16 == 0) goto L_0x0226
            java.lang.Object r16 = r15.next()
            org.videolan.moviepedia.models.resolver.ResolverImage r16 = (org.videolan.moviepedia.models.resolver.ResolverImage) r16
            org.videolan.moviepedia.database.models.MediaImage r12 = new org.videolan.moviepedia.database.models.MediaImage
            java.lang.String r13 = r16.path()
            java.lang.String r13 = r7.getImageUriFromPath(r13)
            java.lang.String r0 = r5.getMoviepediaId()
            r36 = r15
            org.videolan.moviepedia.database.models.MediaImageType r15 = org.videolan.moviepedia.database.models.MediaImageType.BACKDROP
            r19 = r11
            java.lang.String r11 = r16.language()
            r12.<init>(r13, r0, r15, r11)
            r14.add(r12)
            r0 = r35
            r15 = r36
            r11 = r19
            r12 = 3
            r13 = 1
            goto L_0x01f3
        L_0x0226:
            r19 = r11
            java.util.List r0 = r7.getPosters(r2)
            if (r0 == 0) goto L_0x025b
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0234:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x025b
            java.lang.Object r2 = r0.next()
            org.videolan.moviepedia.models.resolver.ResolverImage r2 = (org.videolan.moviepedia.models.resolver.ResolverImage) r2
            org.videolan.moviepedia.database.models.MediaImage r11 = new org.videolan.moviepedia.database.models.MediaImage
            java.lang.String r12 = r2.path()
            java.lang.String r12 = r7.getImageUriFromPath(r12)
            java.lang.String r13 = r5.getMoviepediaId()
            org.videolan.moviepedia.database.models.MediaImageType r15 = org.videolan.moviepedia.database.models.MediaImageType.POSTER
            java.lang.String r2 = r2.language()
            r11.<init>(r12, r13, r15, r2)
            r14.add(r11)
            goto L_0x0234
        L_0x025b:
            if (r8 == 0) goto L_0x02b7
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r2 = r8.iterator()
        L_0x026a:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x02b2
            java.lang.Object r7 = r2.next()
            r8 = r7
            org.videolan.moviepedia.database.models.MediaImage r8 = (org.videolan.moviepedia.database.models.MediaImage) r8
            r11 = r14
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            boolean r12 = r11 instanceof java.util.Collection
            r13 = 0
            if (r12 == 0) goto L_0x028a
            r12 = r11
            java.util.Collection r12 = (java.util.Collection) r12
            boolean r12 = r12.isEmpty()
            if (r12 == 0) goto L_0x028a
        L_0x0288:
            r8 = 1
            goto L_0x02aa
        L_0x028a:
            java.util.Iterator r11 = r11.iterator()
        L_0x028e:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0288
            java.lang.Object r12 = r11.next()
            org.videolan.moviepedia.database.models.MediaImage r12 = (org.videolan.moviepedia.database.models.MediaImage) r12
            java.lang.String r15 = r8.getUrl()
            java.lang.String r12 = r12.getUrl()
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r12)
            if (r12 == 0) goto L_0x028e
            r8 = 1
            r13 = 1
        L_0x02aa:
            r11 = r13 ^ 1
            if (r11 == 0) goto L_0x026a
            r0.add(r7)
            goto L_0x026a
        L_0x02b2:
            java.util.List r0 = (java.util.List) r0
            r4.deleteImages(r0)
        L_0x02b7:
            java.util.List r14 = (java.util.List) r14
            r4.addImagesImmediate(r14)
            if (r10 == 0) goto L_0x02e0
            r3.L$0 = r1
            r3.L$1 = r9
            r0 = 0
            r3.L$2 = r0
            r3.L$3 = r0
            r3.L$4 = r0
            r3.L$5 = r0
            r3.Z$0 = r6
            r0 = 3
            r3.label = r0
            java.lang.Object r0 = r1.retrieveCasting(r9, r5, r3)
            r2 = r19
            if (r0 != r2) goto L_0x02d9
            return r2
        L_0x02d9:
            r3 = r1
            r1 = r6
            r4 = r9
        L_0x02dc:
            r15 = r1
            r1 = r3
            r9 = r4
            goto L_0x02e1
        L_0x02e0:
            r15 = r6
        L_0x02e1:
            if (r15 == 0) goto L_0x02e6
            r1.removePersonOrphans(r9)
        L_0x02e6:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.MediaScraper.saveMediaMetadata(android.content.Context, org.videolan.medialibrary.interfaces.media.MediaWrapper, org.videolan.moviepedia.models.resolver.ResolverMedia, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0084 A[LOOP:0: B:17:0x007e->B:19:0x0084, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c1 A[LOOP:1: B:21:0x00bb->B:23:0x00c1, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00fe A[LOOP:2: B:25:0x00f8->B:27:0x00fe, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x013b A[LOOP:3: B:29:0x0135->B:31:0x013b, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0178 A[LOOP:4: B:33:0x0172->B:35:0x0178, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object retrieveCasting(android.content.Context r10, org.videolan.moviepedia.database.models.MediaMetadata r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof org.videolan.moviepedia.MediaScraper$retrieveCasting$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            org.videolan.moviepedia.MediaScraper$retrieveCasting$1 r0 = (org.videolan.moviepedia.MediaScraper$retrieveCasting$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            org.videolan.moviepedia.MediaScraper$retrieveCasting$1 r0 = new org.videolan.moviepedia.MediaScraper$retrieveCasting$1
            r0.<init>(r9, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            java.lang.Object r10 = r0.L$3
            java.util.ArrayList r10 = (java.util.ArrayList) r10
            java.lang.Object r11 = r0.L$2
            org.videolan.moviepedia.repository.PersonRepository r11 = (org.videolan.moviepedia.repository.PersonRepository) r11
            java.lang.Object r1 = r0.L$1
            org.videolan.moviepedia.database.models.MediaMetadata r1 = (org.videolan.moviepedia.database.models.MediaMetadata) r1
            java.lang.Object r0 = r0.L$0
            android.content.Context r0 = (android.content.Context) r0
            kotlin.ResultKt.throwOnFailure(r12)
            r8 = r12
            r12 = r11
            r11 = r1
            r1 = r8
            goto L_0x0072
        L_0x003e:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r12)
            org.videolan.moviepedia.repository.PersonRepository$Companion r12 = org.videolan.moviepedia.repository.PersonRepository.Companion
            java.lang.Object r12 = r12.getInstance(r10)
            org.videolan.moviepedia.repository.PersonRepository r12 = (org.videolan.moviepedia.repository.PersonRepository) r12
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            org.videolan.moviepedia.repository.MediaResolverApi r4 = r9.getMediaResolverApi()
            java.lang.String r5 = r11.getMoviepediaId()
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r2
            r0.label = r3
            java.lang.Object r0 = r4.getMediaCast(r5, r0)
            if (r0 != r1) goto L_0x006f
            return r1
        L_0x006f:
            r1 = r0
            r0 = r10
            r10 = r2
        L_0x0072:
            org.videolan.moviepedia.models.resolver.ResolverCasting r1 = (org.videolan.moviepedia.models.resolver.ResolverCasting) r1
            java.util.List r2 = r1.actors()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x007e:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x00b1
            java.lang.Object r4 = r2.next()
            org.videolan.moviepedia.models.resolver.ResolverPerson r4 = (org.videolan.moviepedia.models.resolver.ResolverPerson) r4
            org.videolan.moviepedia.database.models.Person r5 = new org.videolan.moviepedia.database.models.Person
            java.lang.String r6 = r4.personId()
            java.lang.String r7 = r4.name()
            java.lang.String r4 = r4.image()
            r5.<init>(r6, r7, r4)
            r12.addPersonImmediate(r5)
            org.videolan.moviepedia.database.models.MediaPersonJoin r4 = new org.videolan.moviepedia.database.models.MediaPersonJoin
            java.lang.String r6 = r11.getMoviepediaId()
            java.lang.String r5 = r5.getMoviepediaId()
            org.videolan.moviepedia.database.models.PersonType r7 = org.videolan.moviepedia.database.models.PersonType.ACTOR
            r4.<init>(r6, r5, r7)
            r10.add(r4)
            goto L_0x007e
        L_0x00b1:
            java.util.List r2 = r1.directors()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x00bb:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x00ee
            java.lang.Object r4 = r2.next()
            org.videolan.moviepedia.models.resolver.ResolverPerson r4 = (org.videolan.moviepedia.models.resolver.ResolverPerson) r4
            org.videolan.moviepedia.database.models.Person r5 = new org.videolan.moviepedia.database.models.Person
            java.lang.String r6 = r4.personId()
            java.lang.String r7 = r4.name()
            java.lang.String r4 = r4.image()
            r5.<init>(r6, r7, r4)
            r12.addPersonImmediate(r5)
            org.videolan.moviepedia.database.models.MediaPersonJoin r4 = new org.videolan.moviepedia.database.models.MediaPersonJoin
            java.lang.String r6 = r11.getMoviepediaId()
            java.lang.String r5 = r5.getMoviepediaId()
            org.videolan.moviepedia.database.models.PersonType r7 = org.videolan.moviepedia.database.models.PersonType.DIRECTOR
            r4.<init>(r6, r5, r7)
            r10.add(r4)
            goto L_0x00bb
        L_0x00ee:
            java.util.List r2 = r1.writers()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x00f8:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x012b
            java.lang.Object r4 = r2.next()
            org.videolan.moviepedia.models.resolver.ResolverPerson r4 = (org.videolan.moviepedia.models.resolver.ResolverPerson) r4
            org.videolan.moviepedia.database.models.Person r5 = new org.videolan.moviepedia.database.models.Person
            java.lang.String r6 = r4.personId()
            java.lang.String r7 = r4.name()
            java.lang.String r4 = r4.image()
            r5.<init>(r6, r7, r4)
            r12.addPersonImmediate(r5)
            org.videolan.moviepedia.database.models.MediaPersonJoin r4 = new org.videolan.moviepedia.database.models.MediaPersonJoin
            java.lang.String r6 = r11.getMoviepediaId()
            java.lang.String r5 = r5.getMoviepediaId()
            org.videolan.moviepedia.database.models.PersonType r7 = org.videolan.moviepedia.database.models.PersonType.WRITER
            r4.<init>(r6, r5, r7)
            r10.add(r4)
            goto L_0x00f8
        L_0x012b:
            java.util.List r2 = r1.musicians()
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.Iterator r2 = r2.iterator()
        L_0x0135:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x0168
            java.lang.Object r4 = r2.next()
            org.videolan.moviepedia.models.resolver.ResolverPerson r4 = (org.videolan.moviepedia.models.resolver.ResolverPerson) r4
            org.videolan.moviepedia.database.models.Person r5 = new org.videolan.moviepedia.database.models.Person
            java.lang.String r6 = r4.personId()
            java.lang.String r7 = r4.name()
            java.lang.String r4 = r4.image()
            r5.<init>(r6, r7, r4)
            r12.addPersonImmediate(r5)
            org.videolan.moviepedia.database.models.MediaPersonJoin r4 = new org.videolan.moviepedia.database.models.MediaPersonJoin
            java.lang.String r6 = r11.getMoviepediaId()
            java.lang.String r5 = r5.getMoviepediaId()
            org.videolan.moviepedia.database.models.PersonType r7 = org.videolan.moviepedia.database.models.PersonType.MUSICIAN
            r4.<init>(r6, r5, r7)
            r10.add(r4)
            goto L_0x0135
        L_0x0168:
            java.util.List r1 = r1.producers()
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r1 = r1.iterator()
        L_0x0172:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x01a5
            java.lang.Object r2 = r1.next()
            org.videolan.moviepedia.models.resolver.ResolverPerson r2 = (org.videolan.moviepedia.models.resolver.ResolverPerson) r2
            org.videolan.moviepedia.database.models.Person r4 = new org.videolan.moviepedia.database.models.Person
            java.lang.String r5 = r2.personId()
            java.lang.String r6 = r2.name()
            java.lang.String r2 = r2.image()
            r4.<init>(r5, r6, r2)
            r12.addPersonImmediate(r4)
            org.videolan.moviepedia.database.models.MediaPersonJoin r2 = new org.videolan.moviepedia.database.models.MediaPersonJoin
            java.lang.String r5 = r11.getMoviepediaId()
            java.lang.String r4 = r4.getMoviepediaId()
            org.videolan.moviepedia.database.models.PersonType r6 = org.videolan.moviepedia.database.models.PersonType.PRODUCER
            r2.<init>(r5, r4, r6)
            r10.add(r2)
            goto L_0x0172
        L_0x01a5:
            org.videolan.moviepedia.repository.MediaPersonRepository$Companion r12 = org.videolan.moviepedia.repository.MediaPersonRepository.Companion
            java.lang.Object r12 = r12.getInstance(r0)
            org.videolan.moviepedia.repository.MediaPersonRepository r12 = (org.videolan.moviepedia.repository.MediaPersonRepository) r12
            java.lang.String r1 = r11.getMoviepediaId()
            r12.removeAllFor(r1)
            org.videolan.moviepedia.repository.MediaPersonRepository$Companion r12 = org.videolan.moviepedia.repository.MediaPersonRepository.Companion
            java.lang.Object r12 = r12.getInstance(r0)
            org.videolan.moviepedia.repository.MediaPersonRepository r12 = (org.videolan.moviepedia.repository.MediaPersonRepository) r12
            java.util.List r10 = (java.util.List) r10
            r12.addPersons(r10)
            r11.setHasCast(r3)
            org.videolan.moviepedia.repository.MediaMetadataRepository$Companion r10 = org.videolan.moviepedia.repository.MediaMetadataRepository.Companion
            java.lang.Object r10 = r10.getInstance(r0)
            org.videolan.moviepedia.repository.MediaMetadataRepository r10 = (org.videolan.moviepedia.repository.MediaMetadataRepository) r10
            r10.addMetadataImmediate(r11)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.MediaScraper.retrieveCasting(android.content.Context, org.videolan.moviepedia.database.models.MediaMetadata, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final IndexingListener getIndexListener() {
        return indexListener;
    }
}
