package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.libvlc.interfaces.IMedia;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlayerController", f = "PlayerController.kt", i = {0}, l = {81}, m = "startPlayback$vlc_android_release", n = {"this"}, s = {"L$0"})
/* compiled from: PlayerController.kt */
final class PlayerController$startPlayback$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlayerController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerController$startPlayback$1(PlayerController playerController, Continuation<? super PlayerController$startPlayback$1> continuation) {
        super(continuation);
        this.this$0 = playerController;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.startPlayback$vlc_android_release((IMedia) null, (MediaPlayerEventListener) null, 0, this);
    }
}
