package org.videolan.vlc;

import android.support.v4.media.MediaMetadataCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Landroid/support/v4/media/MediaMetadataCompat;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$updateMetadataInternal$bob$1", f = "PlaybackService.kt", i = {0, 0}, l = {2044}, m = "invokeSuspend", n = {"bob", "carMode"}, s = {"L$0", "Z$0"})
/* compiled from: PlaybackService.kt */
final class PlaybackService$updateMetadataInternal$bob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super MediaMetadataCompat>, Object> {
    final /* synthetic */ String $chapterTitle;
    final /* synthetic */ PlaybackService $ctx;
    final /* synthetic */ String $displayMsg;
    final /* synthetic */ long $length;
    final /* synthetic */ MediaWrapper $media;
    Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$updateMetadataInternal$bob$1(PlaybackService playbackService, MediaWrapper mediaWrapper, String str, String str2, PlaybackService playbackService2, long j, Continuation<? super PlaybackService$updateMetadataInternal$bob$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
        this.$media = mediaWrapper;
        this.$chapterTitle = str;
        this.$displayMsg = str2;
        this.$ctx = playbackService2;
        this.$length = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$updateMetadataInternal$bob$1(this.this$0, this.$media, this.$chapterTitle, this.$displayMsg, this.$ctx, this.$length, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super MediaMetadataCompat> continuation) {
        return ((PlaybackService$updateMetadataInternal$bob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x018c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x001f
            if (r1 != r3) goto L_0x0017
            boolean r0 = r14.Z$0
            java.lang.Object r1 = r14.L$0
            android.support.v4.media.MediaMetadataCompat$Builder r1 = (android.support.v4.media.MediaMetadataCompat.Builder) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x014b
        L_0x0017:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r15)
            org.videolan.vlc.PlaybackService r15 = r14.this$0
            boolean r15 = r15.isCarMode()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r14.$media
            java.lang.String r1 = r1.getNowPlaying()
            if (r1 != 0) goto L_0x0036
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r14.$media
            java.lang.String r1 = r1.getTitle()
        L_0x0036:
            org.videolan.vlc.PlaybackService r4 = r14.this$0
            android.content.SharedPreferences r4 = r4.getSettings$vlc_android_release()
            java.lang.String r5 = "lockscreen_cover"
            boolean r4 = r4.getBoolean(r5, r3)
            android.support.v4.media.MediaMetadataCompat$Builder r5 = new android.support.v4.media.MediaMetadataCompat$Builder
            r5.<init>()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = r14.$media
            org.videolan.vlc.PlaybackService r7 = r14.$ctx
            long r8 = r14.$length
            java.lang.String r10 = "android.media.metadata.TITLE"
            r5.putString(r10, r1)
            org.videolan.vlc.media.MediaSessionBrowser$Companion r10 = org.videolan.vlc.media.MediaSessionBrowser.Companion
            r11 = r6
            org.videolan.medialibrary.media.MediaLibraryItem r11 = (org.videolan.medialibrary.media.MediaLibraryItem) r11
            java.lang.String r10 = r10.generateMediaId(r11)
            java.lang.String r11 = "android.media.metadata.MEDIA_ID"
            r5.putString(r11, r10)
            org.videolan.vlc.media.MediaUtils r10 = org.videolan.vlc.media.MediaUtils.INSTANCE
            android.content.Context r7 = (android.content.Context) r7
            java.lang.String r10 = r10.getMediaGenre(r7, r6)
            java.lang.String r11 = "android.media.metadata.GENRE"
            r5.putString(r11, r10)
            int r10 = r6.getTrackNumber()
            long r10 = (long) r10
            java.lang.String r12 = "android.media.metadata.TRACK_NUMBER"
            r5.putLong(r12, r10)
            org.videolan.vlc.media.MediaUtils r10 = org.videolan.vlc.media.MediaUtils.INSTANCE
            java.lang.String r10 = r10.getMediaArtist(r7, r6)
            java.lang.String r11 = "android.media.metadata.ARTIST"
            r5.putString(r11, r10)
            org.videolan.vlc.media.MediaUtils r10 = org.videolan.vlc.media.MediaUtils.INSTANCE
            java.lang.String r10 = r10.getMediaReferenceArtist(r7, r6)
            java.lang.String r11 = "android.media.metadata.ALBUM_ARTIST"
            r5.putString(r11, r10)
            org.videolan.vlc.media.MediaUtils r10 = org.videolan.vlc.media.MediaUtils.INSTANCE
            java.lang.String r6 = r10.getMediaAlbum(r7, r6)
            java.lang.String r7 = "android.media.metadata.ALBUM"
            r5.putString(r7, r6)
            r6 = 0
            int r10 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r10 == 0) goto L_0x009f
            goto L_0x00a1
        L_0x009f:
            r8 = -1
        L_0x00a1:
            java.lang.String r6 = "android.media.metadata.DURATION"
            r5.putLong(r6, r8)
            if (r15 == 0) goto L_0x00e5
            java.lang.String r6 = r14.$chapterTitle
            if (r6 != 0) goto L_0x00ad
            goto L_0x00ae
        L_0x00ad:
            r1 = r6
        L_0x00ae:
            java.lang.String r6 = "android.media.metadata.DISPLAY_TITLE"
            r5.putString(r6, r1)
            java.lang.String r1 = r14.$displayMsg
            if (r1 != 0) goto L_0x00cf
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            org.videolan.vlc.PlaybackService r6 = r14.$ctx
            android.content.Context r6 = (android.content.Context) r6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r14.$media
            org.videolan.vlc.PlaybackService r8 = r14.this$0
            int r8 = r8.getCurrentMediaPosition()
            org.videolan.vlc.PlaybackService r9 = r14.this$0
            int r9 = r9.getMediaListSize()
            java.lang.String r1 = r1.getDisplaySubtitle(r6, r7, r8, r9)
        L_0x00cf:
            java.lang.String r6 = "android.media.metadata.DISPLAY_SUBTITLE"
            r5.putString(r6, r1)
            org.videolan.vlc.media.MediaUtils r1 = org.videolan.vlc.media.MediaUtils.INSTANCE
            org.videolan.vlc.PlaybackService r6 = r14.$ctx
            android.content.Context r6 = (android.content.Context) r6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = r14.$media
            java.lang.String r1 = r1.getMediaAlbum(r6, r7)
            java.lang.String r6 = "android.media.metadata.DISPLAY_DESCRIPTION"
            r5.putString(r6, r1)
        L_0x00e5:
            org.videolan.vlc.util.Permissions r1 = org.videolan.vlc.util.Permissions.INSTANCE
            org.videolan.vlc.PlaybackService r6 = r14.$ctx
            android.content.Context r6 = (android.content.Context) r6
            boolean r1 = r1.canReadStorage(r6)
            if (r1 == 0) goto L_0x01c2
            if (r4 == 0) goto L_0x01c2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r14.$media
            java.lang.String r1 = r1.getArtworkMrl()
            boolean r1 = org.videolan.vlc.util.BrowserutilsKt.isSchemeHttpOrHttps(r1)
            if (r1 == 0) goto L_0x0125
            org.videolan.vlc.ArtworkProvider$Companion r0 = org.videolan.vlc.ArtworkProvider.Companion
            org.videolan.vlc.PlaybackService r1 = r14.$ctx
            android.content.Context r1 = (android.content.Context) r1
            android.net.Uri$Builder r3 = new android.net.Uri$Builder
            r3.<init>()
            java.lang.String r4 = "remote"
            android.net.Uri$Builder r3 = r3.appendPath(r4)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r14.$media
            java.lang.String r4 = r4.getArtworkMrl()
            java.lang.String r6 = "path"
            android.net.Uri$Builder r3 = r3.appendQueryParameter(r6, r4)
            android.net.Uri r3 = r3.build()
            android.net.Uri r0 = r0.buildUri(r1, r3)
            goto L_0x0181
        L_0x0125:
            org.videolan.vlc.PlaybackService r1 = r14.this$0
            android.content.Context r1 = (android.content.Context) r1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r14.$media
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            org.videolan.vlc.PlaybackService$updateMetadataInternal$bob$1$invokeSuspend$$inlined$getFromMl$1 r7 = new org.videolan.vlc.PlaybackService$updateMetadataInternal$bob$1$invokeSuspend$$inlined$getFromMl$1
            r7.<init>(r1, r2, r4)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r1 = r14
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r14.L$0 = r5
            r14.Z$0 = r15
            r14.label = r3
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r6, r7, r1)
            if (r1 != r0) goto L_0x0148
            return r0
        L_0x0148:
            r0 = r15
            r15 = r1
            r1 = r5
        L_0x014b:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
            org.videolan.vlc.media.MediaSessionBrowser$Companion r3 = org.videolan.vlc.media.MediaSessionBrowser.Companion
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
            r4 = r15
            org.videolan.medialibrary.media.MediaLibraryItem r4 = (org.videolan.medialibrary.media.MediaLibraryItem) r4
            java.lang.String r3 = r3.generateMediaId(r4)
            org.videolan.vlc.PlaybackService r4 = r14.this$0
            java.util.Map r4 = r4.artworkMap
            if (r4 != 0) goto L_0x0167
            java.lang.String r4 = "artworkMap"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r4 = r2
        L_0x0167:
            java.lang.Object r3 = r4.get(r3)
            android.net.Uri r3 = (android.net.Uri) r3
            if (r3 != 0) goto L_0x017e
            org.videolan.vlc.ArtworkProvider$Companion r3 = org.videolan.vlc.ArtworkProvider.Companion
            org.videolan.vlc.PlaybackService r4 = r14.$ctx
            android.content.Context r4 = (android.content.Context) r4
            android.net.Uri r15 = r3.buildMediaUri(r4, r15)
            r5 = r1
            r13 = r0
            r0 = r15
            r15 = r13
            goto L_0x0181
        L_0x017e:
            r15 = r0
            r5 = r1
            r0 = r3
        L_0x0181:
            java.lang.String r1 = "android.media.metadata.ALBUM_ART_URI"
            java.lang.String r0 = r0.toString()
            r5.putString(r1, r0)
            if (r15 != 0) goto L_0x01c2
            org.videolan.vlc.gui.helpers.AudioUtil r15 = org.videolan.vlc.gui.helpers.AudioUtil.INSTANCE
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r14.$media
            java.lang.String r0 = r0.getArtworkMrl()
            java.lang.String r0 = android.net.Uri.decode(r0)
            r1 = 512(0x200, float:7.175E-43)
            android.graphics.Bitmap r15 = r15.readCoverBitmap(r0, r1)
            if (r15 == 0) goto L_0x01a4
            android.graphics.Bitmap$Config r2 = r15.getConfig()
        L_0x01a4:
            java.lang.String r0 = "android.media.metadata.ALBUM_ART"
            if (r2 == 0) goto L_0x01b5
            android.graphics.Bitmap$Config r1 = r15.getConfig()
            r2 = 0
            android.graphics.Bitmap r15 = r15.copy(r1, r2)
            r5.putBitmap(r0, r15)
            goto L_0x01c2
        L_0x01b5:
            org.videolan.vlc.PlaybackService r15 = r14.$ctx
            android.content.Context r15 = (android.content.Context) r15
            int r2 = org.videolan.vlc.R.drawable.ic_no_media
            android.graphics.Bitmap r15 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable(r15, r2, r1, r1)
            r5.putBitmap(r0, r15)
        L_0x01c2:
            android.support.v4.media.MediaMetadataCompat r15 = r5.build()
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService$updateMetadataInternal$bob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
