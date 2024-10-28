package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.helpers.PlayerKeyListenerDelegate;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/gui/helpers/PlayerKeyListenerDelegate;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
final class AudioPlayerContainerActivity$playerKeyListenerDelegate$2 extends Lambda implements Function0<PlayerKeyListenerDelegate> {
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerContainerActivity$playerKeyListenerDelegate$2(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        super(0);
        this.this$0 = audioPlayerContainerActivity;
    }

    public final PlayerKeyListenerDelegate invoke() {
        return new PlayerKeyListenerDelegate(this.this$0);
    }
}
