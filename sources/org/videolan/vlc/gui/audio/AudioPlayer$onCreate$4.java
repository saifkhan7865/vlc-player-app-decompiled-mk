package org.videolan.vlc.gui.audio;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H@"}, d2 = {"<anonymous>", "", "it", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayer$onCreate$4", f = "AudioPlayer.kt", i = {0}, l = {144, 146}, m = "invokeSuspend", n = {"it"}, s = {"L$0"})
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$onCreate$4 extends SuspendLambda implements Function2<List<MediaWrapper>, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$onCreate$4(AudioPlayer audioPlayer, Continuation<? super AudioPlayer$onCreate$4> continuation) {
        super(2, continuation);
        this.this$0 = audioPlayer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AudioPlayer$onCreate$4 audioPlayer$onCreate$4 = new AudioPlayer$onCreate$4(this.this$0, continuation);
        audioPlayer$onCreate$4.L$0 = obj;
        return audioPlayer$onCreate$4;
    }

    public final Object invoke(List<MediaWrapper> list, Continuation<? super Unit> continuation) {
        return ((AudioPlayer$onCreate$4) create(list, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v10, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0022
            if (r1 == r3) goto L_0x001a
            if (r1 != r2) goto L_0x0012
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x005f
        L_0x0012:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x001a:
            java.lang.Object r1 = r5.L$0
            java.util.List r1 = (java.util.List) r1
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x003a
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.Object r6 = r5.L$0
            r1 = r6
            java.util.List r1 = (java.util.List) r1
            org.videolan.vlc.gui.audio.AudioPlayer r6 = r5.this$0
            r4 = r5
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5.L$0 = r1
            r5.label = r3
            java.lang.Object r6 = r6.doUpdate(r4)
            if (r6 != r0) goto L_0x003a
            return r0
        L_0x003a:
            org.videolan.vlc.gui.audio.AudioPlayer r6 = r5.this$0
            org.videolan.vlc.gui.audio.PlaylistAdapter r6 = r6.playlistAdapter
            r3 = 0
            if (r6 != 0) goto L_0x0049
            java.lang.String r6 = "playlistAdapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r6 = r3
        L_0x0049:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r6.update(r1)
            r6 = r5
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r5.L$0 = r3
            r5.label = r2
            r1 = 50
            java.lang.Object r6 = kotlinx.coroutines.DelayKt.delay(r1, r6)
            if (r6 != r0) goto L_0x005f
            return r0
        L_0x005f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.AudioPlayer$onCreate$4.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
