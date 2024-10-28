package org.videolan.moviepedia.repository;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.IMoviepediaApiService;
import org.videolan.moviepedia.MoviepediaApiClient;
import org.videolan.moviepedia.models.body.ScrobbleBody;
import org.videolan.moviepedia.models.identify.IdentifyResult;
import org.videolan.moviepedia.models.identify.MoviepediaMedia;
import org.videolan.moviepedia.models.media.cast.CastResult;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\bH@¢\u0006\u0002\u0010\tJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0011J8\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\"\u0010\u0015\u001a\u001e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00100\u0016j\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u0010`\u0018H@¢\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\bH@¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/moviepedia/repository/MoviepediaApiRepository;", "Lorg/videolan/moviepedia/repository/MediaResolverApi;", "moviepediaApiService", "Lorg/videolan/moviepedia/IMoviepediaApiService;", "(Lorg/videolan/moviepedia/IMoviepediaApiService;)V", "getMedia", "Lorg/videolan/moviepedia/models/identify/MoviepediaMedia;", "showId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaCast", "Lorg/videolan/moviepedia/models/media/cast/CastResult;", "resolverId", "searchMedia", "Lorg/videolan/moviepedia/models/resolver/ResolverResult;", "uri", "Landroid/net/Uri;", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchMediaBatch", "", "Lorg/videolan/moviepedia/models/resolver/ResolverBatchResult;", "uris", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "(Ljava/util/HashMap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchTitle", "Lorg/videolan/moviepedia/models/identify/IdentifyResult;", "query", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MoviepediaApiRepository.kt */
public final class MoviepediaApiRepository extends MediaResolverApi {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final MoviepediaApiRepository instance = new MoviepediaApiRepository(MoviepediaApiClient.Companion.getInstance());
    private final IMoviepediaApiService moviepediaApiService;

    public MoviepediaApiRepository(IMoviepediaApiService iMoviepediaApiService) {
        Intrinsics.checkNotNullParameter(iMoviepediaApiService, "moviepediaApiService");
        this.moviepediaApiService = iMoviepediaApiService;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0094 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0095 A[PHI: r2 
      PHI: (r2v2 java.lang.Object) = (r2v5 java.lang.Object), (r2v1 java.lang.Object) binds: [B:19:0x0092, B:10:0x0030] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchMedia(android.net.Uri r25, kotlin.coroutines.Continuation<? super org.videolan.moviepedia.models.resolver.ResolverResult> r26) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r26
            boolean r3 = r2 instanceof org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$1 r3 = (org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$1 r3 = new org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$1
            r3.<init>(r0, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 2
            r7 = 1
            r8 = 0
            if (r5 == 0) goto L_0x0048
            if (r5 == r7) goto L_0x003c
            if (r5 != r6) goto L_0x0034
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0095
        L_0x0034:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x003c:
            java.lang.Object r1 = r3.L$1
            android.net.Uri r1 = (android.net.Uri) r1
            java.lang.Object r5 = r3.L$0
            org.videolan.moviepedia.repository.MoviepediaApiRepository r5 = (org.videolan.moviepedia.repository.MoviepediaApiRepository) r5
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0066
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r2)
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$hash$1 r5 = new org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMedia$hash$1
            r5.<init>(r1, r8)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r3.L$0 = r0
            r3.L$1 = r1
            r3.label = r7
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r2, r5, r3)
            if (r2 != r4) goto L_0x0065
            return r4
        L_0x0065:
            r5 = r0
        L_0x0066:
            r10 = r2
            java.lang.String r10 = (java.lang.String) r10
            java.lang.String r16 = r1.getLastPathSegment()
            org.videolan.moviepedia.models.body.ScrobbleBody r1 = new org.videolan.moviepedia.models.body.ScrobbleBody
            r22 = 4030(0xfbe, float:5.647E-42)
            r23 = 0
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            org.videolan.moviepedia.IMoviepediaApiService r2 = r5.moviepediaApiService
            r3.L$0 = r8
            r3.L$1 = r8
            r3.label = r6
            java.lang.Object r2 = r2.searchMedia(r1, r3)
            if (r2 != r4) goto L_0x0095
            return r4
        L_0x0095:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.repository.MoviepediaApiRepository.searchMedia(android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e9 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00ea A[PHI: r0 
      PHI: (r0v2 java.lang.Object) = (r0v9 java.lang.Object), (r0v1 java.lang.Object) binds: [B:23:0x00e7, B:10:0x002f] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object searchMediaBatch(java.util.HashMap<java.lang.Long, android.net.Uri> r27, kotlin.coroutines.Continuation<? super java.util.List<? extends org.videolan.moviepedia.models.resolver.ResolverBatchResult>> r28) {
        /*
            r26 = this;
            r0 = r28
            boolean r1 = r0 instanceof org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMediaBatch$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMediaBatch$1 r1 = (org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMediaBatch$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            r2 = r26
            goto L_0x001f
        L_0x0018:
            org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMediaBatch$1 r1 = new org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMediaBatch$1
            r2 = r26
            r1.<init>(r2, r0)
        L_0x001f:
            java.lang.Object r0 = r1.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0050
            if (r4 == r6) goto L_0x003c
            if (r4 != r5) goto L_0x0034
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00ea
        L_0x0034:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003c:
            java.lang.Object r4 = r1.L$3
            java.util.Map$Entry r4 = (java.util.Map.Entry) r4
            java.lang.Object r7 = r1.L$2
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r1.L$1
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            java.lang.Object r9 = r1.L$0
            org.videolan.moviepedia.repository.MoviepediaApiRepository r9 = (org.videolan.moviepedia.repository.MoviepediaApiRepository) r9
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0097
        L_0x0050:
            kotlin.ResultKt.throwOnFailure(r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4 = r27
            java.util.Map r4 = (java.util.Map) r4
            java.util.Set r4 = r4.entrySet()
            java.util.Iterator r4 = r4.iterator()
            r8 = r0
            r9 = r2
            r7 = r4
        L_0x0067:
            boolean r0 = r7.hasNext()
            r4 = 0
            if (r0 == 0) goto L_0x00d5
            java.lang.Object r0 = r7.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMediaBatch$2$hash$1 r11 = new org.videolan.moviepedia.repository.MoviepediaApiRepository$searchMediaBatch$2$hash$1
            r11.<init>(r0, r4)
            kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
            r1.L$0 = r9
            r1.L$1 = r8
            r1.L$2 = r7
            r1.L$3 = r0
            r1.label = r6
            java.lang.Object r4 = kotlinx.coroutines.BuildersKt.withContext(r10, r11, r1)
            if (r4 != r3) goto L_0x0092
            return r3
        L_0x0092:
            r25 = r4
            r4 = r0
            r0 = r25
        L_0x0097:
            r11 = r0
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r0 = r4.getValue()
            android.net.Uri r0 = (android.net.Uri) r0
            java.lang.String r17 = r0.getLastPathSegment()
            org.videolan.moviepedia.models.body.ScrobbleBody r0 = new org.videolan.moviepedia.models.body.ScrobbleBody
            r23 = 4030(0xfbe, float:5.647E-42)
            r24 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r15 = 0
            r16 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r21 = 0
            r22 = 0
            r10 = r0
            r10.<init>(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            org.videolan.moviepedia.models.body.ScrobbleBodyBatch r10 = new org.videolan.moviepedia.models.body.ScrobbleBodyBatch
            java.lang.Object r4 = r4.getKey()
            java.lang.Number r4 = (java.lang.Number) r4
            long r11 = r4.longValue()
            java.lang.String r4 = java.lang.String.valueOf(r11)
            r10.<init>(r4, r0)
            r8.add(r10)
            goto L_0x0067
        L_0x00d5:
            org.videolan.moviepedia.IMoviepediaApiService r0 = r9.moviepediaApiService
            java.util.List r8 = (java.util.List) r8
            r1.L$0 = r4
            r1.L$1 = r4
            r1.L$2 = r4
            r1.L$3 = r4
            r1.label = r5
            java.lang.Object r0 = r0.searchMediaBatch(r8, r1)
            if (r0 != r3) goto L_0x00ea
            return r3
        L_0x00ea:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.repository.MoviepediaApiRepository.searchMediaBatch(java.util.HashMap, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object searchTitle(String str, Continuation<? super IdentifyResult> continuation) {
        IMoviepediaApiService iMoviepediaApiService = this.moviepediaApiService;
        ScrobbleBody scrobbleBody = r2;
        ScrobbleBody scrobbleBody2 = new ScrobbleBody((String) null, (String) null, (String) null, (String) null, str, (String) null, str, (String) null, (String) null, (String) null, (String) null, (String) null, 4015, (DefaultConstructorMarker) null);
        return iMoviepediaApiService.searchMedia(scrobbleBody, continuation);
    }

    public Object getMedia(String str, Continuation<? super MoviepediaMedia> continuation) {
        return this.moviepediaApiService.getMedia(str, continuation);
    }

    public Object getMediaCast(String str, Continuation<? super CastResult> continuation) {
        return this.moviepediaApiService.getMediaCast(str, continuation);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lorg/videolan/moviepedia/repository/MoviepediaApiRepository$Companion;", "", "()V", "instance", "Lorg/videolan/moviepedia/repository/MoviepediaApiRepository;", "getInstance", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MoviepediaApiRepository.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MoviepediaApiRepository getInstance() {
            return MoviepediaApiRepository.instance;
        }
    }
}
