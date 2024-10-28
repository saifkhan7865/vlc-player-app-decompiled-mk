package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$hideSearchRunnable$2 extends Lambda implements Function0<Runnable> {
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$hideSearchRunnable$2(AudioPlayer audioPlayer) {
        super(0);
        this.this$0 = audioPlayer;
    }

    public final Runnable invoke() {
        return new AudioPlayer$hideSearchRunnable$2$invoke$$inlined$Runnable$1(this.this$0);
    }
}
