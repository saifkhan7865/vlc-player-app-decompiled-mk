package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayer.kt */
final class AudioPlayer$ctxReceiver$1$onCtxAction$1$2 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MediaWrapper $mw;
    final /* synthetic */ int $position;
    final /* synthetic */ AudioPlayer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayer$ctxReceiver$1$onCtxAction$1$2(AudioPlayer audioPlayer, int i, MediaWrapper mediaWrapper) {
        super(0);
        this.this$0 = audioPlayer;
        this.$position = i;
        this.$mw = mediaWrapper;
    }

    public final void invoke() {
        this.this$0.getPlaylistModel().insertMedia(this.$position, this.$mw);
    }
}
