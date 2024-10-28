package org.videolan.vlc.media;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager", f = "PlaylistManager.kt", i = {0}, l = {997}, m = "append", n = {"this"}, s = {"L$0"})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$append$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$append$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$append$1> continuation) {
        super(continuation);
        this.this$0 = playlistManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.append((List<? extends MediaWrapper>) null, 0, this);
    }
}
