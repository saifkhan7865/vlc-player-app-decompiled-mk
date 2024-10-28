package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$stop$job$1$1", f = "PlaylistManager.kt", i = {}, l = {399, 401, 402}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$stop$job$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $it;
    final /* synthetic */ boolean $video;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$stop$job$1$1(PlaylistManager playlistManager, boolean z, MediaWrapper mediaWrapper, Continuation<? super PlaylistManager$stop$job$1$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
        this.$video = z;
        this.$it = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaylistManager$stop$job$1$1(this.this$0, this.$video, this.$it, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$stop$job$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0083 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0025
            if (r1 == r4) goto L_0x0021
            if (r1 == r3) goto L_0x001d
            if (r1 != r2) goto L_0x0015
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0084
        L_0x0015:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x001d:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0064
        L_0x0021:
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x003c
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r7)
            org.videolan.vlc.media.PlaylistManager r7 = r6.this$0
            r1 = 0
            r5 = 0
            kotlinx.coroutines.Job r7 = org.videolan.vlc.media.PlaylistManager.saveMediaMeta$default(r7, r1, r4, r5)
            r1 = r6
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r6.label = r4
            java.lang.Object r7 = r7.join(r1)
            if (r7 != r0) goto L_0x003c
            return r0
        L_0x003c:
            org.videolan.resources.AndroidDevices r7 = org.videolan.resources.AndroidDevices.INSTANCE
            boolean r7 = r7.isAndroidTv()
            if (r7 == 0) goto L_0x0084
            boolean r7 = org.videolan.libvlc.util.AndroidUtil.isOOrLater
            if (r7 == 0) goto L_0x0084
            boolean r7 = r6.$video
            if (r7 == 0) goto L_0x0084
            org.videolan.vlc.media.PlaylistManager r7 = r6.this$0
            org.videolan.vlc.PlaybackService r7 = r7.getService()
            android.content.Context r7 = r7.getApplicationContext()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r6.$it
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r6.label = r3
            java.lang.Object r7 = org.videolan.vlc.util.TvChannelsKt.setResumeProgram(r7, r1, r4)
            if (r7 != r0) goto L_0x0064
            return r0
        L_0x0064:
            org.videolan.vlc.media.PlaylistManager r7 = r6.this$0
            org.videolan.vlc.PlaybackService r7 = r7.getService()
            androidx.lifecycle.LifecycleOwner r7 = (androidx.lifecycle.LifecycleOwner) r7
            org.videolan.vlc.media.PlaylistManager r1 = r6.this$0
            org.videolan.vlc.PlaybackService r1 = r1.getService()
            android.content.Context r1 = r1.getApplicationContext()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = r6.$it
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r6.label = r2
            java.lang.Object r7 = org.videolan.vlc.util.TvChannelsKt.updateNextProgramAfterThumbnailGeneration(r7, r1, r3, r4)
            if (r7 != r0) goto L_0x0084
            return r0
        L_0x0084:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager$stop$job$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
