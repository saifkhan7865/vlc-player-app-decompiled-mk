package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.PlaylistModel", f = "PlaylistModel.kt", i = {0}, l = {201}, m = "switchToVideo", n = {"$this$switchToVideo_u24lambda_u246"}, s = {"L$1"})
/* compiled from: PlaylistModel.kt */
final class PlaylistModel$switchToVideo$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaylistModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistModel$switchToVideo$1(PlaylistModel playlistModel, Continuation<? super PlaylistModel$switchToVideo$1> continuation) {
        super(continuation);
        this.this$0 = playlistModel;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.switchToVideo(this);
    }
}
