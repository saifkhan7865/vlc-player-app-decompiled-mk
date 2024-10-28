package org.videolan.vlc.gui.audio;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Runnable.kt */
public final class AudioPlayer$hideSearchRunnable$2$invoke$$inlined$Runnable$1 implements Runnable {
    final /* synthetic */ AudioPlayer this$0;

    public AudioPlayer$hideSearchRunnable$2$invoke$$inlined$Runnable$1(AudioPlayer audioPlayer) {
        this.this$0 = audioPlayer;
    }

    public final void run() {
        boolean unused = this.this$0.hideSearchField();
        this.this$0.getPlaylistModel().filter((CharSequence) null);
    }
}
