package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.libvlc.MediaPlayer;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$mediaplayerEventListener$1", f = "PlaylistManager.kt", i = {0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 4, 4, 4, 5, 5}, l = {1138, 1148, 1153, 1175, 1178, 1179}, m = "onEvent", n = {"this", "event", "this", "event", "mw", "this", "event", "mw", "this", "event", "this", "event", "it", "this", "event"}, s = {"L$0", "L$1", "L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$3", "L$0", "L$1"})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$mediaplayerEventListener$1$onEvent$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistManager$mediaplayerEventListener$1 this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$mediaplayerEventListener$1$onEvent$1(PlaylistManager$mediaplayerEventListener$1 playlistManager$mediaplayerEventListener$1, Continuation<? super PlaylistManager$mediaplayerEventListener$1$onEvent$1> continuation) {
        super(continuation);
        this.this$0 = playlistManager$mediaplayerEventListener$1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onEvent((MediaPlayer.Event) null, this);
    }
}
