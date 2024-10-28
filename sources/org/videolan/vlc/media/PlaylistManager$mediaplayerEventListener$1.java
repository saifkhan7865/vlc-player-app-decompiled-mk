package org.videolan.vlc.media;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H@¢\u0006\u0002\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"org/videolan/vlc/media/PlaylistManager$mediaplayerEventListener$1", "Lorg/videolan/vlc/media/MediaPlayerEventListener;", "lastTimeMetaSaved", "", "onEvent", "", "event", "Lorg/videolan/libvlc/MediaPlayer$Event;", "(Lorg/videolan/libvlc/MediaPlayer$Event;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistManager.kt */
public final class PlaylistManager$mediaplayerEventListener$1 implements MediaPlayerEventListener {
    private long lastTimeMetaSaved;
    final /* synthetic */ PlaylistManager this$0;

    PlaylistManager$mediaplayerEventListener$1(PlaylistManager playlistManager) {
        this.this$0 = playlistManager;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0298, code lost:
        r0 = r2;
        r13 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x029a, code lost:
        r2 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x029b, code lost:
        org.videolan.vlc.media.PlaylistManager.next$default(r2.this$0, false, 1, (java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02f4, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r7) == false) goto L_0x02f6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0363, code lost:
        r14 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0365, code lost:
        if (r14 != null) goto L_0x036a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0369, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0370, code lost:
        if (r2.this$0.newMedia == false) goto L_0x0438;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x0372, code lost:
        r2.this$0.loadMediaMeta(r14);
        r14.setLength(r2.this$0.getPlayer().getLength());
        r9 = r2.this$0;
        r0.L$0 = r2;
        r0.L$1 = r13;
        r0.L$2 = r14;
        r0.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x0392, code lost:
        if (org.videolan.vlc.media.PlaylistManager.saveMediaList$default(r9, false, r0, 1, (java.lang.Object) null) != r1) goto L_0x0395;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x0394, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x0395, code lost:
        r11 = r14;
        r14 = r13;
        r13 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x0398, code lost:
        org.videolan.vlc.media.PlaylistManager.savePosition$default(r2.this$0, false, false, 3, (java.lang.Object) null);
        org.videolan.vlc.media.PlaylistManager.saveCurrentMedia$default(r2.this$0, false, 1, (java.lang.Object) null);
        r2.this$0.newMedia = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x03b1, code lost:
        if (r2.this$0.getPlayer().getHasRenderer() != false) goto L_0x03bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x03bd, code lost:
        if (r2.this$0.getPlayer().isVideoPlaying() != false) goto L_0x03cc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x03bf, code lost:
        org.videolan.vlc.media.PlaylistManager.Companion.getShowAudioPlayer().setValue(kotlin.coroutines.jvm.internal.Boxing.boxBoolean(true));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x03cc, code lost:
        r3 = r2.this$0;
        r0.L$0 = r2;
        r0.L$1 = r14;
        r0.L$2 = r13;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x03da, code lost:
        if (r3.savePlaycount(r13, r0) != r1) goto L_0x03dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x03dc, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x03dd, code lost:
        r1 = r14;
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x03eb, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13.getTitle(), (java.lang.Object) r13.getFileName()) != false) goto L_0x0431;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x03f1, code lost:
        if (r13.getType() != 6) goto L_0x0436;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x03f3, code lost:
        r14 = r13.getTitle();
        r2 = r0.this$0.getPlayer().getMediaplayer().getMedia();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0405, code lost:
        if (r2 == null) goto L_0x040c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0407, code lost:
        r2 = r2.getMeta(0, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x040c, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0411, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r2) == false) goto L_0x0431;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0413, code lost:
        r14 = r13.getArtist();
        r2 = r0.this$0.getPlayer().getMediaplayer().getMedia();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0425, code lost:
        if (r2 == null) goto L_0x042b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0427, code lost:
        r7 = r2.getMeta(1, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x042f, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r7) != false) goto L_0x0436;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0431, code lost:
        r0.this$0.refreshTrackMeta(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0436, code lost:
        r2 = r0;
        r13 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0438, code lost:
        org.videolan.vlc.media.PlaylistManager.Companion.getPlayingState().setValue(kotlin.coroutines.jvm.internal.Boxing.boxBoolean(true));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0445, code lost:
        r2.this$0.getService().onMediaPlayerEvent(r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x0450, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x023d, code lost:
        if (r2.this$0.hasNext() != false) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x023f, code lost:
        r14 = r2.this$0.getCurrentMedia();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0245, code lost:
        if (r14 == null) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0247, code lost:
        r3 = r2.this$0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x024f, code lost:
        if (org.videolan.resources.AndroidDevices.INSTANCE.isAndroidTv() == false) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0253, code lost:
        if (org.videolan.libvlc.util.AndroidUtil.isOOrLater == false) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0259, code lost:
        if (r3.isAudioList$vlc_android_release() != false) goto L_0x029b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x025b, code lost:
        r5 = r3.getService().getApplicationContext();
        r0.L$0 = r2;
        r0.L$1 = r13;
        r0.L$2 = r3;
        r0.L$3 = r14;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0272, code lost:
        if (org.videolan.vlc.util.TvChannelsKt.setResumeProgram(r5, r14, r0) != r1) goto L_0x0275;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0274, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0275, code lost:
        r11 = r3;
        r3 = r13;
        r13 = r14;
        r14 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0279, code lost:
        r14 = r14.getService().getApplicationContext();
        r0.L$0 = r2;
        r0.L$1 = r3;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0295, code lost:
        if (org.videolan.vlc.util.TvChannelsKt.updateNextProgramAfterThumbnailGeneration(r14.getService(), r14, r13, r0) != r1) goto L_0x0298;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0297, code lost:
        return r1;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0056  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0164  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0171  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x01a0  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object onEvent(org.videolan.libvlc.MediaPlayer.Event r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r12 = this;
            boolean r0 = r14 instanceof org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$1 r0 = (org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$1 r0 = new org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$1
            r0.<init>(r12, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 6
            r5 = 3
            r6 = 1
            r7 = 0
            r8 = 0
            switch(r2) {
                case 0: goto L_0x0094;
                case 1: goto L_0x0087;
                case 2: goto L_0x0074;
                case 3: goto L_0x0063;
                case 4: goto L_0x0056;
                case 5: goto L_0x003f;
                case 6: goto L_0x0032;
                default: goto L_0x002a;
            }
        L_0x002a:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0032:
            java.lang.Object r13 = r0.L$1
            org.videolan.libvlc.MediaPlayer$Event r13 = (org.videolan.libvlc.MediaPlayer.Event) r13
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1 r0 = (org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1) r0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x029a
        L_0x003f:
            java.lang.Object r13 = r0.L$3
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            java.lang.Object r2 = r0.L$2
            org.videolan.vlc.media.PlaylistManager r2 = (org.videolan.vlc.media.PlaylistManager) r2
            java.lang.Object r3 = r0.L$1
            org.videolan.libvlc.MediaPlayer$Event r3 = (org.videolan.libvlc.MediaPlayer.Event) r3
            java.lang.Object r5 = r0.L$0
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1 r5 = (org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1) r5
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r2
            r2 = r5
            goto L_0x0279
        L_0x0056:
            java.lang.Object r13 = r0.L$1
            org.videolan.libvlc.MediaPlayer$Event r13 = (org.videolan.libvlc.MediaPlayer.Event) r13
            java.lang.Object r2 = r0.L$0
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1 r2 = (org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0237
        L_0x0063:
            java.lang.Object r13 = r0.L$2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            java.lang.Object r1 = r0.L$1
            org.videolan.libvlc.MediaPlayer$Event r1 = (org.videolan.libvlc.MediaPlayer.Event) r1
            java.lang.Object r0 = r0.L$0
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1 r0 = (org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1) r0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x03df
        L_0x0074:
            java.lang.Object r13 = r0.L$2
            org.videolan.medialibrary.interfaces.media.MediaWrapper r13 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r13
            java.lang.Object r2 = r0.L$1
            org.videolan.libvlc.MediaPlayer$Event r2 = (org.videolan.libvlc.MediaPlayer.Event) r2
            java.lang.Object r3 = r0.L$0
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1 r3 = (org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1) r3
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r2
            r2 = r3
            goto L_0x0398
        L_0x0087:
            java.lang.Object r13 = r0.L$1
            org.videolan.libvlc.MediaPlayer$Event r13 = (org.videolan.libvlc.MediaPlayer.Event) r13
            java.lang.Object r2 = r0.L$0
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1 r2 = (org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0363
        L_0x0094:
            kotlin.ResultKt.throwOnFailure(r14)
            int r14 = r13.type
            r2 = 260(0x104, float:3.64E-43)
            if (r14 == r2) goto L_0x0346
            r2 = 269(0x10d, float:3.77E-43)
            if (r14 == r2) goto L_0x02fa
            r2 = 278(0x116, float:3.9E-43)
            if (r14 == r2) goto L_0x02a2
            switch(r14) {
                case 265: goto L_0x01a7;
                case 266: goto L_0x012a;
                case 267: goto L_0x00aa;
                default: goto L_0x00a8;
            }
        L_0x00a8:
            goto L_0x0343
        L_0x00aa:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            androidx.lifecycle.MutableLiveData r14 = r14.getAbRepeat()
            java.lang.Object r14 = r14.getValue()
            org.videolan.vlc.media.ABRepeat r14 = (org.videolan.vlc.media.ABRepeat) r14
            if (r14 == 0) goto L_0x00fa
            org.videolan.vlc.media.PlaylistManager r0 = r12.this$0
            long r1 = r14.getStop()
            r3 = -1
            int r9 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r9 == 0) goto L_0x00df
            org.videolan.vlc.media.PlayerController r1 = r0.getPlayer()
            long r1 = r1.getCurrentTime()
            long r3 = r14.getStop()
            int r9 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r9 <= 0) goto L_0x00df
            org.videolan.vlc.PlaybackService r1 = r0.getService()
            long r2 = r14.getStart()
            r1.setTime(r2, r8)
        L_0x00df:
            org.videolan.vlc.media.PlayerController r1 = r0.getPlayer()
            long r1 = r1.getCurrentTime()
            long r3 = r14.getStart()
            int r9 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r9 >= 0) goto L_0x00fa
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            long r1 = r14.getStart()
            r0.setTime(r1, r8)
        L_0x00fa:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlayerController r14 = r14.getPlayer()
            long r0 = r14.getCurrentTime()
            r14 = 10
            long r2 = (long) r14
            long r0 = r0 % r2
            r2 = 0
            int r14 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r14 != 0) goto L_0x0113
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlaylistManager.savePosition$default(r14, r8, r8, r5, r7)
        L_0x0113:
            long r0 = java.lang.System.currentTimeMillis()
            long r2 = r12.lastTimeMetaSaved
            long r2 = r0 - r2
            r4 = 5000(0x1388, double:2.4703E-320)
            int r14 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r14 <= 0) goto L_0x0343
            r12.lastTimeMetaSaved = r0
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlaylistManager.saveMediaMeta$default(r14, r8, r6, r7)
            goto L_0x0343
        L_0x012a:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0     // Catch:{ NullPointerException -> 0x0137 }
            org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = r14.getCurrentMedia()     // Catch:{ NullPointerException -> 0x0137 }
            if (r14 == 0) goto L_0x0138
            java.lang.String r14 = r14.getLocation()     // Catch:{ NullPointerException -> 0x0137 }
            goto L_0x0139
        L_0x0137:
        L_0x0138:
            r14 = r7
        L_0x0139:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Invalid location "
            r0.<init>(r1)
            r0.append(r14)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "VLC/PlaylistManager"
            android.util.Log.w(r1, r0)
            org.videolan.vlc.media.PlaylistManager r0 = r12.this$0
            org.videolan.vlc.PlaybackService r0 = r0.getService()
            if (r14 == 0) goto L_0x0171
            android.net.Uri r1 = android.net.Uri.parse(r14)
            java.lang.String r1 = r1.getScheme()
            java.lang.String r2 = "missing"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x0171
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.PlaybackService r14 = r14.getService()
            int r1 = org.videolan.vlc.R.string.missing_location
            java.lang.String r14 = r14.getString(r1)
            goto L_0x0185
        L_0x0171:
            org.videolan.vlc.media.PlaylistManager r1 = r12.this$0
            org.videolan.vlc.PlaybackService r1 = r1.getService()
            int r2 = org.videolan.vlc.R.string.invalid_location
            if (r14 != 0) goto L_0x017d
            java.lang.String r14 = ""
        L_0x017d:
            java.lang.Object[] r3 = new java.lang.Object[r6]
            r3[r8] = r14
            java.lang.String r14 = r1.getString(r2, r3)
        L_0x0185:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)
            r0.showToast(r14, r8, r6)
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            int r14 = r14.getCurrentIndex()
            org.videolan.vlc.media.PlaylistManager r0 = r12.this$0
            int r0 = r0.nextIndex
            if (r14 == r0) goto L_0x01a0
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlaylistManager.next$default(r14, r8, r6, r7)
            goto L_0x0343
        L_0x01a0:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlaylistManager.stop$default(r14, r8, r8, r5, r7)
            goto L_0x0343
        L_0x01a7:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            r14.clearABRepeat()
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = r14.getCurrentMedia()
            if (r14 == 0) goto L_0x01b9
            r2 = 32
            r14.addFlags(r2)
        L_0x01b9:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            int r14 = r14.getCurrentIndex()
            org.videolan.vlc.media.PlaylistManager r2 = r12.this$0
            int r2 = r2.nextIndex
            if (r14 == r2) goto L_0x0200
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r14.getCurrentMedia()
            if (r2 == 0) goto L_0x01d4
            android.net.Uri r2 = r2.getUri()
            goto L_0x01d5
        L_0x01d4:
            r2 = r7
        L_0x01d5:
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r14.endReachedFor = r2
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            r14.saveMediaMeta(r6)
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            boolean r14 = r14.isBenchmark()
            if (r14 == 0) goto L_0x01f2
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlayerController r14 = r14.getPlayer()
            r14.setPreviousStats()
        L_0x01f2:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            int r14 = r14.nextIndex
            r2 = -1
            if (r14 != r2) goto L_0x0200
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlaylistManager.savePosition$default(r14, r6, r8, r3, r7)
        L_0x0200:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            int r14 = r14.getStopAfter()
            org.videolan.vlc.media.PlaylistManager r2 = r12.this$0
            int r2 = r2.getCurrentIndex()
            if (r14 != r2) goto L_0x0215
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlaylistManager.stop$default(r14, r8, r8, r5, r7)
            goto L_0x0343
        L_0x0215:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            boolean r14 = r14.isBenchmark()
            if (r14 == 0) goto L_0x0226
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlayerController r14 = r14.getPlayer()
            r14.setCurrentStats()
        L_0x0226:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            r0.L$0 = r12
            r0.L$1 = r13
            r2 = 4
            r0.label = r2
            java.lang.Object r14 = r14.determinePrevAndNextIndices(r6, r0)
            if (r14 != r1) goto L_0x0236
            return r1
        L_0x0236:
            r2 = r12
        L_0x0237:
            org.videolan.vlc.media.PlaylistManager r14 = r2.this$0
            boolean r14 = r14.hasNext()
            if (r14 != 0) goto L_0x029b
            org.videolan.vlc.media.PlaylistManager r14 = r2.this$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = r14.getCurrentMedia()
            if (r14 == 0) goto L_0x029b
            org.videolan.vlc.media.PlaylistManager r3 = r2.this$0
            org.videolan.resources.AndroidDevices r5 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r5 = r5.isAndroidTv()
            if (r5 == 0) goto L_0x029b
            boolean r5 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            if (r5 == 0) goto L_0x029b
            boolean r5 = r3.isAudioList$vlc_android_release()
            if (r5 != 0) goto L_0x029b
            org.videolan.vlc.PlaybackService r5 = r3.getService()
            android.content.Context r5 = r5.getApplicationContext()
            r0.L$0 = r2
            r0.L$1 = r13
            r0.L$2 = r3
            r0.L$3 = r14
            r9 = 5
            r0.label = r9
            java.lang.Object r5 = org.videolan.vlc.util.TvChannelsKt.setResumeProgram(r5, r14, r0)
            if (r5 != r1) goto L_0x0275
            return r1
        L_0x0275:
            r11 = r3
            r3 = r13
            r13 = r14
            r14 = r11
        L_0x0279:
            org.videolan.vlc.PlaybackService r5 = r14.getService()
            androidx.lifecycle.LifecycleOwner r5 = (androidx.lifecycle.LifecycleOwner) r5
            org.videolan.vlc.PlaybackService r14 = r14.getService()
            android.content.Context r14 = r14.getApplicationContext()
            r0.L$0 = r2
            r0.L$1 = r3
            r0.L$2 = r7
            r0.L$3 = r7
            r0.label = r4
            java.lang.Object r13 = org.videolan.vlc.util.TvChannelsKt.updateNextProgramAfterThumbnailGeneration(r5, r14, r13, r0)
            if (r13 != r1) goto L_0x0298
            return r1
        L_0x0298:
            r0 = r2
            r13 = r3
        L_0x029a:
            r2 = r0
        L_0x029b:
            org.videolan.vlc.media.PlaylistManager r14 = r2.this$0
            org.videolan.vlc.media.PlaylistManager.next$default(r14, r8, r6, r7)
            goto L_0x0445
        L_0x02a2:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = r14.getCurrentMedia()
            if (r14 == 0) goto L_0x0343
            org.videolan.vlc.media.PlaylistManager r0 = r12.this$0
            org.videolan.vlc.media.PlayerController r1 = r0.getPlayer()
            boolean r1 = r1.isPlaying()
            if (r1 == 0) goto L_0x0343
            int r1 = r14.getType()
            if (r1 != r4) goto L_0x0343
            java.lang.String r1 = r14.getTitle()
            org.videolan.vlc.media.PlayerController r2 = r0.getPlayer()
            org.videolan.libvlc.MediaPlayer r2 = r2.getMediaplayer()
            org.videolan.libvlc.interfaces.IMedia r2 = r2.getMedia()
            if (r2 == 0) goto L_0x02d3
            java.lang.String r2 = r2.getMeta(r8, r6)
            goto L_0x02d4
        L_0x02d3:
            r2 = r7
        L_0x02d4:
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r2)
            if (r1 == 0) goto L_0x02f6
            java.lang.String r1 = r14.getArtist()
            org.videolan.vlc.media.PlayerController r2 = r0.getPlayer()
            org.videolan.libvlc.MediaPlayer r2 = r2.getMediaplayer()
            org.videolan.libvlc.interfaces.IMedia r2 = r2.getMedia()
            if (r2 == 0) goto L_0x02f0
            java.lang.String r7 = r2.getMeta(r6, r6)
        L_0x02f0:
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1, (java.lang.Object) r7)
            if (r1 != 0) goto L_0x0343
        L_0x02f6:
            r0.refreshTrackMeta(r14)
            goto L_0x0343
        L_0x02fa:
            boolean r14 = r13.getSeekable()
            r0 = 1065353216(0x3f800000, float:1.0)
            if (r14 == 0) goto L_0x033a
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            android.content.SharedPreferences r14 = r14.getSettings()
            org.videolan.vlc.media.PlaylistManager r1 = r12.this$0
            org.videolan.vlc.media.PlayerController r1 = r1.getPlayer()
            boolean r1 = r1.isVideoPlaying()
            if (r1 == 0) goto L_0x0317
            java.lang.String r1 = "playback_speed_video"
            goto L_0x0319
        L_0x0317:
            java.lang.String r1 = "playback_speed"
        L_0x0319:
            boolean r14 = r14.getBoolean(r1, r8)
            if (r14 == 0) goto L_0x033a
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            android.content.SharedPreferences r14 = r14.getSettings()
            org.videolan.vlc.media.PlaylistManager r1 = r12.this$0
            org.videolan.vlc.media.PlayerController r1 = r1.getPlayer()
            boolean r1 = r1.isVideoPlaying()
            if (r1 == 0) goto L_0x0334
            java.lang.String r1 = "playback_rate_video"
            goto L_0x0336
        L_0x0334:
            java.lang.String r1 = "playback_rate"
        L_0x0336:
            float r0 = r14.getFloat(r1, r0)
        L_0x033a:
            org.videolan.vlc.media.PlaylistManager r14 = r12.this$0
            org.videolan.vlc.media.PlayerController r14 = r14.getPlayer()
            r14.setRate(r0, r8)
        L_0x0343:
            r2 = r12
            goto L_0x0445
        L_0x0346:
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14
            org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1 r2 = new org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1$onEvent$mw$1
            org.videolan.vlc.media.PlaylistManager r9 = r12.this$0
            r2.<init>(r9, r7)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r12
            r0.L$1 = r13
            r0.label = r6
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r14, r2, r0)
            if (r14 != r1) goto L_0x0362
            return r1
        L_0x0362:
            r2 = r12
        L_0x0363:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r14 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r14
            if (r14 != 0) goto L_0x036a
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x036a:
            org.videolan.vlc.media.PlaylistManager r9 = r2.this$0
            boolean r9 = r9.newMedia
            if (r9 == 0) goto L_0x0438
            org.videolan.vlc.media.PlaylistManager r9 = r2.this$0
            r9.loadMediaMeta(r14)
            org.videolan.vlc.media.PlaylistManager r9 = r2.this$0
            org.videolan.vlc.media.PlayerController r9 = r9.getPlayer()
            long r9 = r9.getLength()
            r14.setLength(r9)
            org.videolan.vlc.media.PlaylistManager r9 = r2.this$0
            r0.L$0 = r2
            r0.L$1 = r13
            r0.L$2 = r14
            r0.label = r3
            java.lang.Object r3 = org.videolan.vlc.media.PlaylistManager.saveMediaList$default(r9, r8, r0, r6, r7)
            if (r3 != r1) goto L_0x0395
            return r1
        L_0x0395:
            r11 = r14
            r14 = r13
            r13 = r11
        L_0x0398:
            org.videolan.vlc.media.PlaylistManager r3 = r2.this$0
            org.videolan.vlc.media.PlaylistManager.savePosition$default(r3, r8, r8, r5, r7)
            org.videolan.vlc.media.PlaylistManager r3 = r2.this$0
            org.videolan.vlc.media.PlaylistManager.saveCurrentMedia$default(r3, r8, r6, r7)
            org.videolan.vlc.media.PlaylistManager r3 = r2.this$0
            r3.newMedia = r8
            org.videolan.vlc.media.PlaylistManager r3 = r2.this$0
            org.videolan.vlc.media.PlayerController r3 = r3.getPlayer()
            boolean r3 = r3.getHasRenderer()
            if (r3 != 0) goto L_0x03bf
            org.videolan.vlc.media.PlaylistManager r3 = r2.this$0
            org.videolan.vlc.media.PlayerController r3 = r3.getPlayer()
            boolean r3 = r3.isVideoPlaying()
            if (r3 != 0) goto L_0x03cc
        L_0x03bf:
            org.videolan.vlc.media.PlaylistManager$Companion r3 = org.videolan.vlc.media.PlaylistManager.Companion
            androidx.lifecycle.MutableLiveData r3 = r3.getShowAudioPlayer()
            java.lang.Boolean r9 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            r3.setValue(r9)
        L_0x03cc:
            org.videolan.vlc.media.PlaylistManager r3 = r2.this$0
            r0.L$0 = r2
            r0.L$1 = r14
            r0.L$2 = r13
            r0.label = r5
            java.lang.Object r0 = r3.savePlaycount(r13, r0)
            if (r0 != r1) goto L_0x03dd
            return r1
        L_0x03dd:
            r1 = r14
            r0 = r2
        L_0x03df:
            java.lang.String r14 = r13.getTitle()
            java.lang.String r2 = r13.getFileName()
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r2)
            if (r14 != 0) goto L_0x0431
            int r14 = r13.getType()
            if (r14 != r4) goto L_0x0436
            java.lang.String r14 = r13.getTitle()
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            org.videolan.vlc.media.PlayerController r2 = r2.getPlayer()
            org.videolan.libvlc.MediaPlayer r2 = r2.getMediaplayer()
            org.videolan.libvlc.interfaces.IMedia r2 = r2.getMedia()
            if (r2 == 0) goto L_0x040c
            java.lang.String r2 = r2.getMeta(r8, r6)
            goto L_0x040d
        L_0x040c:
            r2 = r7
        L_0x040d:
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r2)
            if (r14 == 0) goto L_0x0431
            java.lang.String r14 = r13.getArtist()
            org.videolan.vlc.media.PlaylistManager r2 = r0.this$0
            org.videolan.vlc.media.PlayerController r2 = r2.getPlayer()
            org.videolan.libvlc.MediaPlayer r2 = r2.getMediaplayer()
            org.videolan.libvlc.interfaces.IMedia r2 = r2.getMedia()
            if (r2 == 0) goto L_0x042b
            java.lang.String r7 = r2.getMeta(r6, r6)
        L_0x042b:
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r7)
            if (r14 != 0) goto L_0x0436
        L_0x0431:
            org.videolan.vlc.media.PlaylistManager r14 = r0.this$0
            r14.refreshTrackMeta(r13)
        L_0x0436:
            r2 = r0
            r13 = r1
        L_0x0438:
            org.videolan.vlc.media.PlaylistManager$Companion r14 = org.videolan.vlc.media.PlaylistManager.Companion
            androidx.lifecycle.MutableLiveData r14 = r14.getPlayingState()
            java.lang.Boolean r0 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r6)
            r14.setValue(r0)
        L_0x0445:
            org.videolan.vlc.media.PlaylistManager r14 = r2.this$0
            org.videolan.vlc.PlaybackService r14 = r14.getService()
            r14.onMediaPlayerEvent(r13)
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1.onEvent(org.videolan.libvlc.MediaPlayer$Event, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
