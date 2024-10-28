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
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider$getPlaylistImage$bitmap$1", f = "ArtworkProvider.kt", i = {}, l = {644, 310}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getPlaylistImage$bitmap$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ Context $ctx;
    final /* synthetic */ Integer $fallbackIcon;
    final /* synthetic */ long $id;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getPlaylistImage$bitmap$1(Context context, Integer num, long j, Continuation<? super ArtworkProvider$getPlaylistImage$bitmap$1> continuation) {
        super(2, continuation);
        this.$ctx = context;
        this.$fallbackIcon = num;
        this.$id = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ArtworkProvider$getPlaylistImage$bitmap$1(this.$ctx, this.$fallbackIcon, this.$id, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((ArtworkProvider$getPlaylistImage$bitmap$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x001f
            if (r1 == r4) goto L_0x001b
            if (r1 != r3) goto L_0x0013
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x006b
        L_0x0013:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x003f
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r13)
            android.content.Context r13 = r12.$ctx
            long r5 = r12.$id
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.ArtworkProvider$getPlaylistImage$bitmap$1$invokeSuspend$$inlined$getFromMl$1 r7 = new org.videolan.vlc.ArtworkProvider$getPlaylistImage$bitmap$1$invokeSuspend$$inlined$getFromMl$1
            r7.<init>(r13, r2, r5)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r13 = r12
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r12.label = r4
            java.lang.Object r13 = kotlinx.coroutines.BuildersKt.withContext(r1, r7, r13)
            if (r13 != r0) goto L_0x003f
            return r0
        L_0x003f:
            r6 = r13
            java.util.List r6 = (java.util.List) r6
            if (r6 == 0) goto L_0x006e
            long r1 = r12.$id
            org.videolan.vlc.util.ThumbnailsProvider r4 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r5 = "playlist:"
            r13.<init>(r5)
            r13.append(r1)
            java.lang.String r1 = "_256"
            r13.append(r1)
            java.lang.String r5 = r13.toString()
            r12.label = r3
            r7 = 256(0x100, float:3.59E-43)
            r8 = 0
            r10 = 8
            r11 = 0
            r9 = r12
            java.lang.Object r13 = org.videolan.vlc.util.ThumbnailsProvider.getPlaylistOrGenreImage$default(r4, r5, r6, r7, r8, r9, r10, r11)
            if (r13 != r0) goto L_0x006b
            return r0
        L_0x006b:
            r2 = r13
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
        L_0x006e:
            if (r2 != 0) goto L_0x0086
            android.content.Context r3 = r12.$ctx
            java.lang.Integer r13 = r12.$fallbackIcon
            if (r13 == 0) goto L_0x007b
            int r13 = r13.intValue()
            goto L_0x007d
        L_0x007b:
            int r13 = org.videolan.vlc.R.drawable.ic_auto_playlist
        L_0x007d:
            r4 = r13
            r7 = 6
            r8 = 0
            r5 = 0
            r6 = 0
            android.graphics.Bitmap r2 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r3, r4, r5, r6, r7, r8)
        L_0x0086:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ArtworkProvider$getPlaylistImage$bitmap$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
