package org.videolan.vlc;

import android.content.Context;
import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1", f = "ArtworkProvider.kt", i = {}, l = {644, 646, 648, 335}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getPlayAllImage$bitmap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ long $id;
    final /* synthetic */ boolean $shuffle;
    final /* synthetic */ String $type;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getPlayAllImage$bitmap$1(String str, Context context, long j, boolean z, Continuation<? super ArtworkProvider$getPlayAllImage$bitmap$1> continuation) {
        super(2, continuation);
        this.$type = str;
        this.$ctx = context;
        this.$id = j;
        this.$shuffle = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtworkProvider$getPlayAllImage$bitmap$1(this.$type, this.$ctx, this.$id, this.$shuffle, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((ArtworkProvider$getPlayAllImage$bitmap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0146  */
    /* JADX WARNING: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r6 = r16
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.label
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            java.lang.String r8 = "playlist"
            r5 = 0
            if (r0 == 0) goto L_0x003b
            if (r0 == r4) goto L_0x0035
            if (r0 == r3) goto L_0x002e
            if (r0 == r2) goto L_0x0028
            if (r0 != r1) goto L_0x0020
            kotlin.ResultKt.throwOnFailure(r17)
            r0 = r17
            goto L_0x0141
        L_0x0020:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r17)
            r0 = r17
            goto L_0x0078
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r17)
            r0 = r17
            goto L_0x00cb
        L_0x0035:
            kotlin.ResultKt.throwOnFailure(r17)
            r0 = r17
            goto L_0x00a0
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r17)
            java.lang.String r0 = r6.$type
            int r9 = r0.hashCode()
            r10 = -1409097913(0xffffffffac02df47, float:-1.8598055E-12)
            if (r9 == r10) goto L_0x00a4
            r3 = 98240899(0x5db0983, float:2.0598155E-35)
            if (r9 == r3) goto L_0x007b
            r3 = 1879474642(0x700681d2, float:1.6651174E29)
            if (r9 == r3) goto L_0x0054
            goto L_0x00ac
        L_0x0054:
            boolean r0 = r0.equals(r8)
            if (r0 != 0) goto L_0x005b
            goto L_0x00ac
        L_0x005b:
            android.content.Context r0 = r6.$ctx
            long r3 = r6.$id
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1$invokeSuspend$$inlined$getFromMl$3 r10 = new org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1$invokeSuspend$$inlined$getFromMl$3
            r10.<init>(r0, r5, r3)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r0 = r6
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            r6.label = r2
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r0)
            if (r0 != r7) goto L_0x0078
            return r7
        L_0x0078:
            java.util.List r0 = (java.util.List) r0
            goto L_0x00a2
        L_0x007b:
            java.lang.String r2 = "genre"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L_0x00ac
            android.content.Context r0 = r6.$ctx
            long r2 = r6.$id
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1$invokeSuspend$$inlined$getFromMl$1 r10 = new org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1$invokeSuspend$$inlined$getFromMl$1
            r10.<init>(r0, r5, r2)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r0 = r6
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            r6.label = r4
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r0)
            if (r0 != r7) goto L_0x00a0
            return r7
        L_0x00a0:
            java.util.List r0 = (java.util.List) r0
        L_0x00a2:
            r2 = r0
            goto L_0x00ce
        L_0x00a4:
            java.lang.String r2 = "artist"
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x00ae
        L_0x00ac:
            r2 = r5
            goto L_0x00ce
        L_0x00ae:
            android.content.Context r0 = r6.$ctx
            long r9 = r6.$id
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1$invokeSuspend$$inlined$getFromMl$2 r4 = new org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1$invokeSuspend$$inlined$getFromMl$2
            r4.<init>(r0, r5, r9)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r0 = r6
            kotlin.coroutines.Continuation r0 = (kotlin.coroutines.Continuation) r0
            r6.label = r3
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r2, r4, r0)
            if (r0 != r7) goto L_0x00cb
            return r7
        L_0x00cb:
            java.util.List r0 = (java.util.List) r0
            goto L_0x00a2
        L_0x00ce:
            if (r2 == 0) goto L_0x0144
            java.lang.String r0 = r6.$type
            boolean r3 = r6.$shuffle
            android.content.Context r9 = r6.$ctx
            long r14 = r6.$id
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r8)
            if (r4 == 0) goto L_0x00e3
            r17 = r2
            r4 = r5
            r1 = r14
            goto L_0x0101
        L_0x00e3:
            if (r3 == 0) goto L_0x00f4
            int r10 = org.videolan.vlc.R.drawable.ic_auto_shuffle_circle
            r13 = 6
            r4 = 0
            r11 = 0
            r12 = 0
            r17 = r2
            r1 = r14
            r14 = r4
            android.graphics.Bitmap r4 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r9, r10, r11, r12, r13, r14)
            goto L_0x0101
        L_0x00f4:
            r17 = r2
            r1 = r14
            int r10 = org.videolan.vlc.R.drawable.ic_auto_playall_circle
            r13 = 6
            r14 = 0
            r11 = 0
            r12 = 0
            android.graphics.Bitmap r4 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r9, r10, r11, r12, r13, r14)
        L_0x0101:
            if (r3 == 0) goto L_0x0114
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r0)
            java.lang.String r0 = "_shuffle"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
        L_0x0114:
            org.videolan.vlc.util.ThumbnailsProvider r3 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r0)
            r0 = 58
            r5.append(r0)
            r5.append(r1)
            java.lang.String r0 = "_256"
            r5.append(r0)
            java.lang.String r1 = r5.toString()
            r0 = 4
            r6.label = r0
            r5 = 256(0x100, float:3.59E-43)
            r0 = r3
            r2 = r17
            r3 = r5
            r5 = r16
            java.lang.Object r0 = r0.getPlaylistOrGenreImage(r1, r2, r3, r4, r5)
            if (r0 != r7) goto L_0x0141
            return r7
        L_0x0141:
            r5 = r0
            android.graphics.Bitmap r5 = (android.graphics.Bitmap) r5
        L_0x0144:
            if (r5 != 0) goto L_0x0167
            java.lang.String r0 = r6.$type
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r8)
            if (r0 == 0) goto L_0x015b
            android.content.Context r7 = r6.$ctx
            int r8 = org.videolan.vlc.R.drawable.ic_auto_playlist_unknown
            r11 = 6
            r12 = 0
            r9 = 0
            r10 = 0
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r7, r8, r9, r10, r11, r12)
            goto L_0x0167
        L_0x015b:
            android.content.Context r7 = r6.$ctx
            int r8 = org.videolan.vlc.R.drawable.ic_auto_playall
            r11 = 6
            r12 = 0
            r9 = 0
            r10 = 0
            android.graphics.Bitmap r5 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r7, r8, r9, r10, r11, r12)
        L_0x0167:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider$getPlayAllImage$bitmap$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
