package org.videolan.moviepedia.provider;

import kotlin.Metadata;
import org.videolan.resources.Constants;
import org.videolan.resources.interfaces.IMediaContentResolver;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J2\u0010\u0007\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0004\u0012\u00020\u000b\u0018\u00010\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0004H@¢\u0006\u0002\u0010\u000fR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lorg/videolan/moviepedia/provider/TvShowResolver;", "Lorg/videolan/resources/interfaces/IMediaContentResolver;", "()V", "prefix", "", "getPrefix", "()Ljava/lang/String;", "getList", "Lkotlin/Pair;", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "", "context", "Landroid/content/Context;", "id", "(Landroid/content/Context;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingTvshowProvider.kt */
final class TvShowResolver implements IMediaContentResolver {
    public static final TvShowResolver INSTANCE = new TvShowResolver();
    private static final String prefix = Constants.CONTENT_EPISODE;

    private TvShowResolver() {
    }

    public String getPrefix() {
        return prefix;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object getList(android.content.Context r6, java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>, java.lang.Integer>> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof org.videolan.moviepedia.provider.TvShowResolver$getList$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            org.videolan.moviepedia.provider.TvShowResolver$getList$1 r0 = (org.videolan.moviepedia.provider.TvShowResolver$getList$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            org.videolan.moviepedia.provider.TvShowResolver$getList$1 r0 = new org.videolan.moviepedia.provider.TvShowResolver$getList$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r6 = r0.L$0
            java.lang.String r6 = (java.lang.String) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0060
        L_0x002f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.moviepedia.provider.MediaScrapingTvshowProvider r8 = new org.videolan.moviepedia.provider.MediaScrapingTvshowProvider
            r8.<init>(r6)
            java.lang.String r6 = r5.getPrefix()
            r2 = 2
            java.lang.String r6 = kotlin.text.StringsKt.substringAfter$default((java.lang.String) r7, (java.lang.String) r6, (java.lang.String) r4, (int) r2, (java.lang.Object) r4)
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            org.videolan.moviepedia.provider.TvShowResolver$getList$2 r2 = new org.videolan.moviepedia.provider.TvShowResolver$getList$2
            r2.<init>(r8, r6, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r7, r2, r0)
            if (r8 != r1) goto L_0x0060
            return r1
        L_0x0060:
            java.util.List r8 = (java.util.List) r8
            if (r8 == 0) goto L_0x00b7
            r7 = r8
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r7 = r7.iterator()
        L_0x0072:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0088
            java.lang.Object r1 = r7.next()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r1 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            if (r1 == 0) goto L_0x0072
            r0.add(r1)
            goto L_0x0072
        L_0x0088:
            java.util.List r0 = (java.util.List) r0
            java.util.Iterator r7 = r8.iterator()
            r8 = 0
        L_0x008f:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x00ad
            java.lang.Object r1 = r7.next()
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r1 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r1
            org.videolan.moviepedia.database.models.MediaMetadata r1 = r1.getMetadata()
            java.lang.String r1 = r1.getMoviepediaId()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r6)
            if (r1 == 0) goto L_0x00aa
            goto L_0x00ae
        L_0x00aa:
            int r8 = r8 + 1
            goto L_0x008f
        L_0x00ad:
            r8 = -1
        L_0x00ae:
            java.lang.Integer r6 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r8)
            kotlin.Pair r4 = new kotlin.Pair
            r4.<init>(r0, r6)
        L_0x00b7:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.provider.TvShowResolver.getList(android.content.Context, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
