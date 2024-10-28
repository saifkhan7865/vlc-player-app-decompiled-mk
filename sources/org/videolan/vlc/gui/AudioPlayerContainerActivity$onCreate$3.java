package org.videolan.vlc.gui;

import androidx.activity.OnBackPressedCallback;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"org/videolan/vlc/gui/AudioPlayerContainerActivity$onCreate$3", "Landroidx/activity/OnBackPressedCallback;", "handleOnBackPressed", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
public final class AudioPlayerContainerActivity$onCreate$3 extends OnBackPressedCallback {
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioPlayerContainerActivity$onCreate$3(AudioPlayerContainerActivity audioPlayerContainerActivity) {
        super(true);
        this.this$0 = audioPlayerContainerActivity;
    }

    public void handleOnBackPressed() {
        if (!this.this$0.slideDownAudioPlayer()) {
            if (this.this$0.getSupportFragmentManager().getBackStackEntryCount() == 0) {
                this.this$0.finish();
            } else {
                this.this$0.getSupportFragmentManager().popBackStack();
            }
        }
    }
}
