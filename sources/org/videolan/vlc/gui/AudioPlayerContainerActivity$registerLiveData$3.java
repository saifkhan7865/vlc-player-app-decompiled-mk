package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.DiscoveryError;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.UiTools;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/DiscoveryError;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
final class AudioPlayerContainerActivity$registerLiveData$3 extends Lambda implements Function1<DiscoveryError, Unit> {
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerContainerActivity$registerLiveData$3(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        super(1);
        this.this$0 = audioPlayerContainerActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((DiscoveryError) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(DiscoveryError discoveryError) {
        UiTools uiTools = UiTools.INSTANCE;
        AudioPlayerContainerActivity audioPlayerContainerActivity = this.this$0;
        String string = audioPlayerContainerActivity.getString(R.string.discovery_failed, new Object[]{discoveryError.getEntryPoint()});
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        uiTools.snacker(audioPlayerContainerActivity, string);
    }
}
