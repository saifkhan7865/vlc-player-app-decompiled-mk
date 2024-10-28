package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.AudioPlayerAnimator", f = "AudioPlayerAnimator.kt", i = {0, 0}, l = {225, 228}, m = "updateBackground", n = {"this", "activity"}, s = {"L$0", "L$1"})
/* compiled from: AudioPlayerAnimator.kt */
final class AudioPlayerAnimator$updateBackground$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AudioPlayerAnimator this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerAnimator$updateBackground$1(AudioPlayerAnimator audioPlayerAnimator, Continuation<? super AudioPlayerAnimator$updateBackground$1> continuation) {
        super(continuation);
        this.this$0 = audioPlayerAnimator;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.updateBackground(this);
    }
}
