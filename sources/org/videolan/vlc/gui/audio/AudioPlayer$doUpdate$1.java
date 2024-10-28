package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayer", f = "AudioPlayer.kt", i = {0, 1, 2, 3}, l = {384, 388, 389, 407, 415}, m = "doUpdate", n = {"this", "this", "this", "this"}, s = {"L$0", "L$0", "L$0", "L$0"})
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$doUpdate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$doUpdate$1(AudioPlayer audioPlayer, Continuation<? super AudioPlayer$doUpdate$1> continuation) {
        super(continuation);
        this.this$0 = audioPlayer;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.doUpdate(this);
    }
}
