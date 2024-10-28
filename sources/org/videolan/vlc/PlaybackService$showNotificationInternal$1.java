package org.videolan.vlc;

import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$showNotificationInternal$1", f = "PlaybackService.kt", i = {}, l = {1014}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$showNotificationInternal$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $coverOnLockscreen;
    final /* synthetic */ PlaybackService $ctx;
    final /* synthetic */ MediaMetadataCompat $metaData;
    final /* synthetic */ MediaWrapper $mw;
    final /* synthetic */ boolean $playing;
    final /* synthetic */ boolean $seekInCompactView;
    final /* synthetic */ MediaSessionCompat.Token $sessionToken;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$showNotificationInternal$1(PlaybackService playbackService, MediaMetadataCompat mediaMetadataCompat, MediaWrapper mediaWrapper, boolean z, PlaybackService playbackService2, boolean z2, boolean z3, MediaSessionCompat.Token token, Continuation<? super PlaybackService$showNotificationInternal$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
        this.$metaData = mediaMetadataCompat;
        this.$mw = mediaWrapper;
        this.$coverOnLockscreen = z;
        this.$ctx = playbackService2;
        this.$playing = z2;
        this.$seekInCompactView = z3;
        this.$sessionToken = token;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$showNotificationInternal$1(this.this$0, this.$metaData, this.$mw, this.$coverOnLockscreen, this.$ctx, this.$playing, this.$seekInCompactView, this.$sessionToken, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$showNotificationInternal$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:0x01d0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x01d1, code lost:
        r21 = "Failed to display notification";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x01df, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x01e0, code lost:
        r3 = "Failed to display notification";
        r2 = "VLC/PlaybackService";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x01eb, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x01ec, code lost:
        r3 = "Failed to display notification";
        r2 = "VLC/PlaybackService";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x01f7, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x01f8, code lost:
        r3 = "Failed to display notification";
        r2 = "VLC/PlaybackService";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x01d0 A[ExcHandler: ArrayIndexOutOfBoundsException (e java.lang.ArrayIndexOutOfBoundsException), Splitter:B:13:0x0040] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0118 A[Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x011b A[Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r25) {
        /*
            r24 = this;
            r1 = r24
            java.lang.String r2 = "Failed to display notification"
            java.lang.String r3 = "VLC/PlaybackService"
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r1.label
            r5 = 1
            if (r4 == 0) goto L_0x001d
            if (r4 != r5) goto L_0x0015
            kotlin.ResultKt.throwOnFailure(r25)
            goto L_0x002e
        L_0x0015:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x001d:
            kotlin.ResultKt.throwOnFailure(r25)
            r4 = r1
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r1.label = r5
            r5 = 100
            java.lang.Object r4 = kotlinx.coroutines.DelayKt.delay(r5, r4)
            if (r4 != r0) goto L_0x002e
            return r0
        L_0x002e:
            org.videolan.vlc.PlaybackService r0 = r1.this$0
            boolean r0 = r0.isPlayingPopup()
            if (r0 != 0) goto L_0x0205
            org.videolan.vlc.PlaybackService r0 = r1.this$0
            boolean r0 = r0.notificationShowing
            if (r0 != 0) goto L_0x0040
            goto L_0x0205
        L_0x0040:
            android.support.v4.media.MediaMetadataCompat r0 = r1.$metaData     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            if (r0 != 0) goto L_0x004b
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r1.$mw     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            java.lang.String r0 = r0.getTitle()     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            goto L_0x0051
        L_0x004b:
            java.lang.String r4 = "android.media.metadata.TITLE"
            java.lang.String r0 = r0.getString(r4)     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
        L_0x0051:
            r7 = r0
            android.support.v4.media.MediaMetadataCompat r0 = r1.$metaData     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            if (r0 != 0) goto L_0x005d
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r1.$mw     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            java.lang.String r0 = r0.getArtist()     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            goto L_0x0063
        L_0x005d:
            java.lang.String r4 = "android.media.metadata.ALBUM_ARTIST"
            java.lang.String r0 = r0.getString(r4)     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
        L_0x0063:
            r8 = r0
            android.support.v4.media.MediaMetadataCompat r0 = r1.$metaData     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            if (r0 != 0) goto L_0x006f
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = r1.$mw     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            java.lang.String r0 = r0.getAlbum()     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            goto L_0x0075
        L_0x006f:
            java.lang.String r4 = "android.media.metadata.ALBUM"
            java.lang.String r0 = r0.getString(r4)     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
        L_0x0075:
            r9 = r0
            boolean r0 = r1.$coverOnLockscreen     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            r20 = 0
            if (r0 == 0) goto L_0x0087
            android.support.v4.media.MediaMetadataCompat r0 = r1.$metaData     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            if (r0 == 0) goto L_0x0087
            java.lang.String r4 = "android.media.metadata.ALBUM_ART"
            android.graphics.Bitmap r0 = r0.getBitmap(r4)     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            goto L_0x0089
        L_0x0087:
            r0 = r20
        L_0x0089:
            boolean r4 = r1.$coverOnLockscreen     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            if (r4 == 0) goto L_0x00a1
            if (r0 != 0) goto L_0x00a1
            org.videolan.vlc.gui.helpers.AudioUtil r0 = org.videolan.vlc.gui.helpers.AudioUtil.INSTANCE     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = r1.$mw     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            java.lang.String r4 = r4.getArtworkMrl()     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            java.lang.String r4 = android.net.Uri.decode(r4)     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            r5 = 256(0x100, float:3.59E-43)
            android.graphics.Bitmap r0 = r0.readCoverBitmap(r4, r5)     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
        L_0x00a1:
            if (r0 == 0) goto L_0x00ac
            boolean r4 = r0.isRecycled()     // Catch:{ IllegalArgumentException -> 0x01f7, IllegalStateException -> 0x01eb, RuntimeException -> 0x01df, ArrayIndexOutOfBoundsException -> 0x01d0 }
            if (r4 == 0) goto L_0x00aa
            goto L_0x00ac
        L_0x00aa:
            r10 = r0
            goto L_0x00bc
        L_0x00ac:
            org.videolan.vlc.PlaybackService r0 = r1.$ctx     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            r10 = r0
            android.content.Context r10 = (android.content.Context) r10     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            int r11 = org.videolan.vlc.R.drawable.ic_no_media     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            r14 = 6
            r15 = 0
            r12 = 0
            r13 = 0
            android.graphics.Bitmap r0 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r10, r11, r12, r13, r14, r15)     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            goto L_0x00aa
        L_0x00bc:
            org.videolan.vlc.PlaybackService r0 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.vlc.gui.helpers.NotificationHelper r4 = org.videolan.vlc.gui.helpers.NotificationHelper.INSTANCE     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.vlc.PlaybackService r5 = r1.$ctx     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            android.content.Context r5 = (android.content.Context) r5     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.vlc.PlaybackService r6 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            boolean r6 = r6.canSwitchToVideo()     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            boolean r11 = r1.$playing     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.vlc.PlaybackService r12 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            boolean r12 = r12.isPausable()     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.vlc.PlaybackService r13 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            boolean r13 = r13.isSeekable()     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.vlc.PlaybackService r14 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            float r14 = r14.getSpeed()     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            org.videolan.vlc.PlaybackService r15 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            boolean r15 = r15.isPodcastMode()     // Catch:{ IllegalArgumentException -> 0x01c6, IllegalStateException -> 0x01bc, RuntimeException -> 0x01b2, ArrayIndexOutOfBoundsException -> 0x01d0 }
            r21 = r2
            boolean r2 = r1.$seekInCompactView     // Catch:{ IllegalArgumentException -> 0x01b0, IllegalStateException -> 0x01ae, RuntimeException -> 0x01ac, ArrayIndexOutOfBoundsException -> 0x01aa }
            r22 = r3
            org.videolan.vlc.PlaybackService r3 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.util.FlagSet r17 = r3.getEnabledActions$vlc_android_release()     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.support.v4.media.session.MediaSessionCompat$Token r3 = r1.$sessionToken     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r25 = r0
            org.videolan.vlc.PlaybackService r0 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.app.PendingIntent r19 = r0.getSessionPendingIntent()     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r16 = r2
            r18 = r3
            android.app.Notification r0 = r4.createPlaybackNotification(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r2 = r25
            r2.notification = r0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.PlaybackService r0 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            boolean r0 = r0.isPlayingPopup()     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            if (r0 == 0) goto L_0x011b
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            return r0
        L_0x011b:
            boolean r0 = org.videolan.libvlc.util.AndroidUtil.isLolliPopOrLater     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            java.lang.String r2 = "notification"
            r3 = 3
            if (r0 == 0) goto L_0x0163
            boolean r0 = r1.$playing     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            if (r0 != 0) goto L_0x0163
            org.videolan.vlc.PlaybackService r0 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.util.VLCAudioFocusHelper r0 = r0.getAudioFocusHelper()     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            boolean r0 = r0.getLossTransient$vlc_android_release()     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            if (r0 == 0) goto L_0x0133
            goto L_0x0163
        L_0x0133:
            org.videolan.vlc.PlaybackService r0 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            boolean r0 = r0.isForeground     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            if (r0 == 0) goto L_0x0149
            org.videolan.vlc.PlaybackService r0 = r1.$ctx     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.app.Service r0 = (android.app.Service) r0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r4 = 2
            androidx.core.app.ServiceCompat.stopForeground(r0, r4)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.PlaybackService r0 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r4 = 0
            r0.isForeground = r4     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
        L_0x0149:
            org.videolan.vlc.PlaybackService r0 = r1.$ctx     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            androidx.core.app.NotificationManagerCompat r0 = androidx.core.app.NotificationManagerCompat.from(r0)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.PlaybackService r4 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.app.Notification r4 = r4.notification     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            if (r4 != 0) goto L_0x015e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r4 = r20
        L_0x015e:
            r0.notify(r3, r4)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            goto L_0x0202
        L_0x0163:
            org.videolan.vlc.PlaybackService r0 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            boolean r0 = r0.isForeground     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            if (r0 != 0) goto L_0x0188
            org.videolan.vlc.PlaybackService r0 = r1.$ctx     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.content.Intent r2 = new android.content.Intent     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.PlaybackService r3 = r1.$ctx     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.content.Context r3 = (android.content.Context) r3     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            java.lang.Class<org.videolan.vlc.PlaybackService> r4 = org.videolan.vlc.PlaybackService.class
            r2.<init>(r3, r4)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.PlaybackService$showNotificationInternal$1$1 r3 = new org.videolan.vlc.PlaybackService$showNotificationInternal$1$1     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.PlaybackService r4 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r3.<init>(r4)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            kotlin.jvm.functions.Function0 r3 = (kotlin.jvm.functions.Function0) r3     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.resources.util.ExtensionsKt.launchForeground(r0, r2, r3)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            goto L_0x0202
        L_0x0188:
            org.videolan.vlc.PlaybackService r0 = r1.$ctx     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.content.Context r0 = (android.content.Context) r0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            androidx.core.app.NotificationManagerCompat r0 = androidx.core.app.NotificationManagerCompat.from(r0)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            org.videolan.vlc.PlaybackService r4 = r1.this$0     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            android.app.Notification r4 = r4.notification     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            if (r4 != 0) goto L_0x019d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            r4 = r20
        L_0x019d:
            r0.notify(r3, r4)     // Catch:{ IllegalArgumentException -> 0x01a8, IllegalStateException -> 0x01a6, RuntimeException -> 0x01a4, ArrayIndexOutOfBoundsException -> 0x01a2 }
            goto L_0x0202
        L_0x01a2:
            r0 = move-exception
            goto L_0x01d5
        L_0x01a4:
            r0 = move-exception
            goto L_0x01b7
        L_0x01a6:
            r0 = move-exception
            goto L_0x01c1
        L_0x01a8:
            r0 = move-exception
            goto L_0x01cb
        L_0x01aa:
            r0 = move-exception
            goto L_0x01d3
        L_0x01ac:
            r0 = move-exception
            goto L_0x01b5
        L_0x01ae:
            r0 = move-exception
            goto L_0x01bf
        L_0x01b0:
            r0 = move-exception
            goto L_0x01c9
        L_0x01b2:
            r0 = move-exception
            r21 = r2
        L_0x01b5:
            r22 = r3
        L_0x01b7:
            r3 = r21
            r2 = r22
            goto L_0x01e5
        L_0x01bc:
            r0 = move-exception
            r21 = r2
        L_0x01bf:
            r22 = r3
        L_0x01c1:
            r3 = r21
            r2 = r22
            goto L_0x01f1
        L_0x01c6:
            r0 = move-exception
            r21 = r2
        L_0x01c9:
            r22 = r3
        L_0x01cb:
            r3 = r21
            r2 = r22
            goto L_0x01fd
        L_0x01d0:
            r0 = move-exception
            r21 = r2
        L_0x01d3:
            r22 = r3
        L_0x01d5:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r3 = r21
            r2 = r22
            android.util.Log.e(r2, r3, r0)
            goto L_0x0202
        L_0x01df:
            r0 = move-exception
            r23 = r3
            r3 = r2
            r2 = r23
        L_0x01e5:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r2, r3, r0)
            goto L_0x0202
        L_0x01eb:
            r0 = move-exception
            r23 = r3
            r3 = r2
            r2 = r23
        L_0x01f1:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r2, r3, r0)
            goto L_0x0202
        L_0x01f7:
            r0 = move-exception
            r23 = r3
            r3 = r2
            r2 = r23
        L_0x01fd:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r2, r3, r0)
        L_0x0202:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0205:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService$showNotificationInternal$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
