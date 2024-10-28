package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.audio.AudioPlaylistTipsDelegate;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/gui/audio/AudioPlaylistTipsDelegate;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
final class AudioPlayerContainerActivity$playlistTipsDelegate$2 extends Lambda implements Function0<AudioPlaylistTipsDelegate> {
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerContainerActivity$playlistTipsDelegate$2(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        super(0);
        this.this$0 = audioPlayerContainerActivity;
    }

    public final AudioPlaylistTipsDelegate invoke() {
        return new AudioPlaylistTipsDelegate(this.this$0);
    }
}
