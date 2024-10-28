package org.videolan.moviepedia;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.MediaScraper$indexMedialib$1", f = "MediaScraper.kt", i = {0, 1, 1}, l = {56, 67}, m = "invokeSuspend", n = {"medias", "medias", "media"}, s = {"L$0", "L$0", "L$3"})
/* compiled from: MediaScraper.kt */
final class MediaScraper$indexMedialib$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScraper$indexMedialib$1(Context context, Continuation<? super MediaScraper$indexMedialib$1> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScraper$indexMedialib$1(this.$context, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScraper$indexMedialib$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00bf  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0118 A[SYNTHETIC] */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            r19 = this;
            r7 = r19
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r7.label
            r1 = 1
            r9 = 2
            r10 = 251(0xfb, float:3.52E-43)
            r11 = 0
            r3 = 1
            if (r0 == 0) goto L_0x003f
            if (r0 == r3) goto L_0x0035
            if (r0 != r9) goto L_0x002d
            java.lang.Object r0 = r7.L$3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0
            java.lang.Object r1 = r7.L$2
            java.util.Iterator r1 = (java.util.Iterator) r1
            java.lang.Object r2 = r7.L$1
            android.content.Context r2 = (android.content.Context) r2
            java.lang.Object r3 = r7.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r3
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ Exception -> 0x002a }
            goto L_0x0103
        L_0x002a:
            goto L_0x010c
        L_0x002d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            java.lang.Object r0 = r7.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper[]) r0
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ Exception -> 0x0122 }
            r3 = r20
            goto L_0x0097
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r20)
            org.videolan.medialibrary.interfaces.Medialibrary r12 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            r17 = 1000(0x3e8, float:1.401E-42)
            r18 = 0
            r13 = 0
            r14 = 0
            r15 = 1
            r16 = 0
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = r12.getPagedVideos(r13, r14, r15, r16, r17, r18)
            java.util.HashMap r4 = new java.util.HashMap
            r4.<init>()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r5 = r0.length
            r6 = 0
        L_0x005d:
            if (r6 >= r5) goto L_0x0083
            r12 = r0[r6]
            long r13 = r12.getMetaLong(r10)
            int r15 = (r13 > r1 ? 1 : (r13 == r1 ? 0 : -1))
            if (r15 == 0) goto L_0x0080
            r13 = r4
            java.util.Map r13 = (java.util.Map) r13
            long r14 = r12.getId()
            java.lang.Long r14 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r14)
            android.net.Uri r12 = r12.getUri()
            java.lang.String r15 = "getUri(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r12, r15)
            r13.put(r14, r12)
        L_0x0080:
            int r6 = r6 + 1
            goto L_0x005d
        L_0x0083:
            org.videolan.moviepedia.MediaScraper r5 = org.videolan.moviepedia.MediaScraper.INSTANCE     // Catch:{ Exception -> 0x0122 }
            org.videolan.moviepedia.repository.MediaResolverApi r5 = r5.getMediaResolverApi()     // Catch:{ Exception -> 0x0122 }
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6     // Catch:{ Exception -> 0x0122 }
            r7.L$0 = r0     // Catch:{ Exception -> 0x0122 }
            r7.label = r3     // Catch:{ Exception -> 0x0122 }
            java.lang.Object r3 = r5.searchMediaBatch(r4, r6)     // Catch:{ Exception -> 0x0122 }
            if (r3 != r8) goto L_0x0097
            return r8
        L_0x0097:
            java.util.List r3 = (java.util.List) r3     // Catch:{ Exception -> 0x0122 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r4 = r0.length
            r5 = 0
        L_0x009e:
            if (r5 >= r4) goto L_0x00ae
            r6 = r0[r5]
            if (r6 == 0) goto L_0x00ab
            boolean r6 = r6.setLongMeta(r10, r1)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
        L_0x00ab:
            int r5 = r5 + 1
            goto L_0x009e
        L_0x00ae:
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            android.content.Context r1 = r7.$context
            java.util.Iterator r2 = r3.iterator()
            r14 = r0
            r13 = r1
            r12 = r2
        L_0x00b9:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x0118
            java.lang.Object r0 = r12.next()
            org.videolan.moviepedia.models.resolver.ResolverBatchResult r0 = (org.videolan.moviepedia.models.resolver.ResolverBatchResult) r0
            org.videolan.moviepedia.models.resolver.ResolverMedia r3 = r0.getMedia()
            if (r3 == 0) goto L_0x00b9
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            int r1 = r14.length
            r2 = 0
        L_0x00d0:
            if (r2 >= r1) goto L_0x00e5
            r4 = r14[r2]
            long r5 = r4.getId()
            long r15 = r0.getId()
            int r17 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r17 != 0) goto L_0x00e2
            r15 = r4
            goto L_0x00e7
        L_0x00e2:
            int r2 = r2 + 1
            goto L_0x00d0
        L_0x00e5:
            r0 = 0
            r15 = r0
        L_0x00e7:
            org.videolan.moviepedia.MediaScraper r0 = org.videolan.moviepedia.MediaScraper.INSTANCE     // Catch:{ Exception -> 0x0107 }
            r7.L$0 = r14     // Catch:{ Exception -> 0x0107 }
            r7.L$1 = r13     // Catch:{ Exception -> 0x0107 }
            r7.L$2 = r12     // Catch:{ Exception -> 0x0107 }
            r7.L$3 = r15     // Catch:{ Exception -> 0x0107 }
            r7.label = r9     // Catch:{ Exception -> 0x0107 }
            r4 = 0
            r5 = 0
            r1 = r13
            r2 = r15
            r6 = r19
            java.lang.Object r0 = r0.saveMediaMetadata(r1, r2, r3, r4, r5, r6)     // Catch:{ Exception -> 0x0107 }
            if (r0 != r8) goto L_0x0100
            return r8
        L_0x0100:
            r1 = r12
            r2 = r13
            r3 = r14
        L_0x0103:
            r12 = r1
            r13 = r2
            r14 = r3
            goto L_0x00b9
        L_0x0107:
            r1 = r12
            r2 = r13
            r3 = r14
            r0 = r15
        L_0x010c:
            if (r0 == 0) goto L_0x0103
            r4 = 0
            boolean r0 = r0.setLongMeta(r10, r4)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
            goto L_0x0103
        L_0x0118:
            org.videolan.moviepedia.MediaScraper r0 = org.videolan.moviepedia.MediaScraper.INSTANCE
            android.content.Context r1 = r7.$context
            r0.removePersonOrphans(r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0122:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.MediaScraper$indexMedialib$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
