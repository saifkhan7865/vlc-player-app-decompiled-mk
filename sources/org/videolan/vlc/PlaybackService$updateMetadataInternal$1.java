package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService", f = "PlaybackService.kt", i = {0}, l = {1140}, m = "updateMetadataInternal", n = {"this"}, s = {"L$0"})
/* compiled from: PlaybackService.kt */
final class PlaybackService$updateMetadataInternal$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$updateMetadataInternal$1(PlaybackService playbackService, Continuation<? super PlaybackService$updateMetadataInternal$1> continuation) {
        super(continuation);
        this.this$0 = playbackService;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateMetadataInternal(this);
    }
}
