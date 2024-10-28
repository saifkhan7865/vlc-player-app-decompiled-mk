package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager", f = "PlaylistManager.kt", i = {0}, l = {821}, m = "determinePrevAndNextIndices", n = {"this"}, s = {"L$0"})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$determinePrevAndNextIndices$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$determinePrevAndNextIndices$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$determinePrevAndNextIndices$1> continuation) {
        super(continuation);
        this.this$0 = playlistManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.determinePrevAndNextIndices(false, this);
    }
}
