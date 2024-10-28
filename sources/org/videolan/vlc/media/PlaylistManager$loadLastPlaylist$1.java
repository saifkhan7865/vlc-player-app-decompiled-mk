package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$loadLastPlaylist$1", f = "PlaylistManager.kt", i = {}, l = {319, 332}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$loadLastPlaylist$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $audio;
    final /* synthetic */ String[] $locations;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$loadLastPlaylist$1(PlaylistManager playlistManager, boolean z, String[] strArr, Continuation<? super PlaylistManager$loadLastPlaylist$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
        this.$audio = z;
        this.$locations = strArr;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$loadLastPlaylist$1(this.this$0, this.$audio, this.$locations, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$loadLastPlaylist$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:40:0x00d9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 2
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x0020
            if (r1 == r3) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00d0
        L_0x0014:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x001c:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x003f
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r12)
            kotlinx.coroutines.CoroutineDispatcher r12 = kotlinx.coroutines.Dispatchers.getDefault()
            kotlin.coroutines.CoroutineContext r12 = (kotlin.coroutines.CoroutineContext) r12
            org.videolan.vlc.media.PlaylistManager$loadLastPlaylist$1$playList$1 r1 = new org.videolan.vlc.media.PlaylistManager$loadLastPlaylist$1$playList$1
            java.lang.String[] r5 = r11.$locations
            r6 = 0
            r1.<init>(r5, r6)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r5 = r11
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r11.label = r3
            java.lang.Object r12 = kotlinx.coroutines.BuildersKt.withContext(r12, r1, r5)
            if (r12 != r0) goto L_0x003f
            return r0
        L_0x003f:
            java.util.ArrayList r12 = (java.util.ArrayList) r12
            org.videolan.vlc.media.PlaylistManager r1 = r11.this$0
            android.content.SharedPreferences r3 = r1.getSettings()
            boolean r5 = r11.$audio
            if (r5 == 0) goto L_0x004e
            java.lang.String r5 = "audio_shuffling"
            goto L_0x0050
        L_0x004e:
            java.lang.String r5 = "media_shuffling"
        L_0x0050:
            boolean r3 = r3.getBoolean(r5, r4)
            r1.setShuffling(r3)
            org.videolan.vlc.media.PlaylistManager r1 = r11.this$0
            android.content.SharedPreferences r1 = r1.getSettings()
            boolean r3 = r11.$audio
            if (r3 == 0) goto L_0x0064
            java.lang.String r3 = "position_in_audio_list"
            goto L_0x0066
        L_0x0064:
            java.lang.String r3 = "position_in_media_list"
        L_0x0066:
            int r1 = r1.getInt(r3, r4)
            int r7 = java.lang.Math.max(r4, r1)
            org.videolan.vlc.media.PlaylistManager r1 = r11.this$0
            android.content.SharedPreferences r3 = r1.getSettings()
            boolean r5 = r11.$audio
            if (r5 == 0) goto L_0x007b
            java.lang.String r5 = "position_in_song"
            goto L_0x007d
        L_0x007b:
            java.lang.String r5 = "position_in_media"
        L_0x007d:
            r8 = -1
            long r5 = r3.getLong(r5, r8)
            r1.setSavedTime(r5)
            boolean r1 = r11.$audio
            if (r1 != 0) goto L_0x00a8
            int r1 = r12.size()
            if (r7 >= r1) goto L_0x00a8
            org.videolan.vlc.media.PlaylistManager r1 = r11.this$0
            android.content.SharedPreferences r1 = r1.getSettings()
            java.lang.String r3 = "VideoPaused"
            boolean r1 = r1.getBoolean(r3, r4)
            if (r1 == 0) goto L_0x00a8
            java.lang.Object r1 = r12.get(r7)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            r3 = 4
            r1.addFlags(r3)
        L_0x00a8:
            boolean r1 = r11.$audio
            if (r1 == 0) goto L_0x00bd
            int r1 = r12.size()
            if (r7 >= r1) goto L_0x00bd
            java.lang.Object r1 = r12.get(r7)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            r3 = 8
            r1.addFlags(r3)
        L_0x00bd:
            org.videolan.vlc.media.PlaylistManager r5 = r11.this$0
            r6 = r12
            java.util.List r6 = (java.util.List) r6
            r10 = r11
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r11.label = r2
            r8 = 1
            r9 = 1
            java.lang.Object r12 = r5.load(r6, r7, r8, r9, r10)
            if (r12 != r0) goto L_0x00d0
            return r0
        L_0x00d0:
            org.videolan.vlc.media.PlaylistManager r12 = r11.this$0
            r12.loadingLastPlaylist = r4
            boolean r12 = r11.$audio
            if (r12 != 0) goto L_0x00ff
            org.videolan.vlc.media.PlaylistManager r12 = r11.this$0
            android.content.SharedPreferences r12 = r12.getSettings()
            org.videolan.vlc.media.PlaylistManager r0 = r11.this$0
            org.videolan.vlc.media.PlayerController r0 = r0.getPlayer()
            float r0 = r0.getRate()
            java.lang.String r1 = "VideoSpeed"
            float r12 = r12.getFloat(r1, r0)
            r0 = 1065353216(0x3f800000, float:1.0)
            int r0 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r0 != 0) goto L_0x00f6
            goto L_0x00ff
        L_0x00f6:
            org.videolan.vlc.media.PlaylistManager r0 = r11.this$0
            org.videolan.vlc.media.PlayerController r0 = r0.getPlayer()
            r0.setRate(r12, r4)
        L_0x00ff:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager$loadLastPlaylist$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
