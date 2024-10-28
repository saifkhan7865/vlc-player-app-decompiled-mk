package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1", f = "StartActivity.kt", i = {0, 0, 0}, l = {346}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $id$inlined;
    final /* synthetic */ Context $this_getFromMl;
    final /* synthetic */ String $type$inlined;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ StartActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1(Context context, Continuation continuation, String str, String str2, StartActivity startActivity) {
        super(2, continuation);
        this.$this_getFromMl = context;
        this.$type$inlined = str;
        this.$id$inlined = str2;
        this.this$0 = startActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1 startActivity$resume$2$invokeSuspend$$inlined$getFromMl$1 = new StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1(this.$this_getFromMl, continuation, this.$type$inlined, this.$id$inlined, this.this$0);
        startActivity$resume$2$invokeSuspend$$inlined$getFromMl$1.L$0 = obj;
        return startActivity$resume$2$invokeSuspend$$inlined$getFromMl$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r17) {
        /*
            r16 = this;
            r0 = r16
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0028
            if (r2 != r3) goto L_0x0020
            java.lang.Object r1 = r0.L$2
            android.content.Context r1 = (android.content.Context) r1
            java.lang.Object r1 = r0.L$1
            org.videolan.medialibrary.interfaces.Medialibrary r1 = (org.videolan.medialibrary.interfaces.Medialibrary) r1
            java.lang.Object r1 = r0.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r17)
            r2 = r17
            goto L_0x0135
        L_0x0020:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r17)
            java.lang.Object r2 = r0.L$0
            r6 = r2
            kotlinx.coroutines.CoroutineScope r6 = (kotlinx.coroutines.CoroutineScope) r6
            org.videolan.medialibrary.interfaces.Medialibrary r2 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            java.lang.String r4 = "getInstance(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            boolean r4 = r2.isStarted()
            r5 = 0
            if (r4 == 0) goto L_0x00c5
            java.lang.String r1 = r0.$type$inlined
            int r3 = r1.hashCode()
            switch(r3) {
                case -1409097913: goto L_0x008c;
                case 92896879: goto L_0x0076;
                case 98240899: goto L_0x0060;
                case 1879474642: goto L_0x004a;
                default: goto L_0x0049;
            }
        L_0x0049:
            goto L_0x00a2
        L_0x004a:
            java.lang.String r3 = "playlist"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0053
            goto L_0x00a2
        L_0x0053:
            java.lang.String r1 = r0.$id$inlined
            long r3 = java.lang.Long.parseLong(r1)
            org.videolan.medialibrary.interfaces.media.Playlist r1 = r2.getPlaylist(r3, r5, r5)
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            goto L_0x00ae
        L_0x0060:
            java.lang.String r3 = "genre"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0069
            goto L_0x00a2
        L_0x0069:
            java.lang.String r1 = r0.$id$inlined
            long r3 = java.lang.Long.parseLong(r1)
            org.videolan.medialibrary.interfaces.media.Genre r1 = r2.getGenre(r3)
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            goto L_0x00ae
        L_0x0076:
            java.lang.String r3 = "album"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x007f
            goto L_0x00a2
        L_0x007f:
            java.lang.String r1 = r0.$id$inlined
            long r3 = java.lang.Long.parseLong(r1)
            org.videolan.medialibrary.interfaces.media.Album r1 = r2.getAlbum(r3)
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            goto L_0x00ae
        L_0x008c:
            java.lang.String r3 = "artist"
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L_0x0095
            goto L_0x00a2
        L_0x0095:
            java.lang.String r1 = r0.$id$inlined
            long r3 = java.lang.Long.parseLong(r1)
            org.videolan.medialibrary.interfaces.media.Artist r1 = r2.getArtist(r3)
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            goto L_0x00ae
        L_0x00a2:
            java.lang.String r1 = r0.$id$inlined
            long r3 = java.lang.Long.parseLong(r1)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r2.getMedia((long) r3)
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
        L_0x00ae:
            r4 = r1
            org.videolan.vlc.media.MediaUtils r2 = org.videolan.vlc.media.MediaUtils.INSTANCE
            org.videolan.vlc.StartActivity r1 = r0.this$0
            r3 = r1
            android.content.Context r3 = (android.content.Context) r3
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            r7 = 8
            r8 = 0
            r5 = 0
            r6 = 0
            org.videolan.vlc.media.MediaUtils.playTracks$default((org.videolan.vlc.media.MediaUtils) r2, (android.content.Context) r3, (org.videolan.medialibrary.media.MediaLibraryItem) r4, (int) r5, (boolean) r6, (int) r7, (java.lang.Object) r8)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L_0x0136
        L_0x00c5:
            org.videolan.tools.Settings r4 = org.videolan.tools.Settings.INSTANCE
            android.content.Context r7 = r0.$this_getFromMl
            java.lang.Object r4 = r4.getInstance(r7)
            android.content.SharedPreferences r4 = (android.content.SharedPreferences) r4
            java.lang.String r7 = "ml_scan"
            int r4 = r4.getInt(r7, r5)
            if (r4 != 0) goto L_0x00d9
            r11 = 1
            goto L_0x00da
        L_0x00d9:
            r11 = 0
        L_0x00da:
            android.content.Context r12 = r0.$this_getFromMl
            r0.L$0 = r6
            r0.L$1 = r2
            r0.L$2 = r12
            r0.I$0 = r11
            r0.label = r3
            r15 = r0
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
            kotlinx.coroutines.CancellableContinuationImpl r14 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.intercepted(r15)
            r14.<init>(r4, r3)
            r14.initCancellability()
            r3 = r14
            kotlinx.coroutines.CancellableContinuation r3 = (kotlinx.coroutines.CancellableContinuation) r3
            org.videolan.vlc.StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1$1 r13 = new org.videolan.vlc.StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1$1
            java.lang.String r8 = r0.$type$inlined
            java.lang.String r9 = r0.$id$inlined
            org.videolan.vlc.StartActivity r10 = r0.this$0
            r4 = r13
            r5 = r3
            r7 = r2
            r4.<init>(r5, r6, r7, r8, r9, r10)
            org.videolan.vlc.StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1$2 r4 = new org.videolan.vlc.StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1$2
            r4.<init>(r2, r13)
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
            r3.invokeOnCancellation(r4)
            org.videolan.medialibrary.interfaces.Medialibrary$OnMedialibraryReadyListener r13 = (org.videolan.medialibrary.interfaces.Medialibrary.OnMedialibraryReadyListener) r13
            r2.addOnMedialibraryReadyListener(r13)
            r13 = 24
            r2 = 0
            r8 = 0
            r9 = 0
            r3 = 0
            r4 = 0
            r7 = r12
            r10 = r11
            r11 = r3
            r12 = r4
            r3 = r14
            r14 = r2
            org.videolan.resources.util.ExtensionsKt.startMedialibrary$default(r7, r8, r9, r10, r11, r12, r13, r14)
            java.lang.Object r2 = r3.getResult()
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r2 != r3) goto L_0x0132
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r15)
        L_0x0132:
            if (r2 != r1) goto L_0x0135
            return r1
        L_0x0135:
            r1 = r2
        L_0x0136:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.StartActivity$resume$2$invokeSuspend$$inlined$getFromMl$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
