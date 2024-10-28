package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager", f = "PlaylistManager.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {904, 917}, m = "expand", n = {"this", "expandedMedia", "updateHistory", "index", "stream", "this", "ml", "mrl", "child", "index", "stream", "i"}, s = {"L$0", "L$1", "Z$0", "I$0", "I$1", "L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2"})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$expand$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$expand$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$expand$1> continuation) {
        super(continuation);
        this.this$0 = playlistManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.expand(false, this);
    }
}
