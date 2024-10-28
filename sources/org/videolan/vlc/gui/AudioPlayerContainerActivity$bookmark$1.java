package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
final class AudioPlayerContainerActivity$bookmark$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerContainerActivity$bookmark$1(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        super(0);
        this.this$0 = audioPlayerContainerActivity;
    }

    public final void invoke() {
        this.this$0.getAudioPlayer().showBookmarks();
    }
}
