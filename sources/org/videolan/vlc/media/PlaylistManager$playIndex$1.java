package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager", f = "PlaylistManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 3}, l = {505, 509, 567, 570}, m = "playIndex", n = {"this", "mw", "index", "flags", "forceResume", "forceRestart", "isVideoPlaying", "this", "mw", "index", "flags", "forceResume", "forceRestart", "isVideoPlaying", "this", "mw", "media", "this"}, s = {"L$0", "L$1", "I$0", "I$1", "Z$0", "Z$1", "I$2", "L$0", "L$1", "I$0", "I$1", "Z$0", "Z$1", "I$2", "L$0", "L$1", "L$2", "L$0"})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$playIndex$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    Object L$0;
    Object L$1;
    Object L$2;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$playIndex$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$playIndex$1> continuation) {
        super(continuation);
        this.this$0 = playlistManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.playIndex(0, 0, false, false, this);
    }
}
