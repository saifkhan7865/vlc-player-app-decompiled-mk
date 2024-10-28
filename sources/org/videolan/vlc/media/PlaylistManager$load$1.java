package org.videolan.vlc.media;

import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager", f = "PlaylistManager.kt", i = {0, 0, 0, 0, 0, 1, 1, 2, 3}, l = {232, 267, 270, 271}, m = "load", n = {"this", "list", "position", "mlUpdate", "avoidErasingStop", "this", "mlUpdate", "this", "this"}, s = {"L$0", "L$1", "I$0", "Z$0", "Z$1", "L$0", "Z$0", "L$0", "L$0"})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$load$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$load$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$load$1> continuation) {
        super(continuation);
        this.this$0 = playlistManager;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.load((List<? extends MediaWrapper>) null, 0, false, false, this);
    }
}
