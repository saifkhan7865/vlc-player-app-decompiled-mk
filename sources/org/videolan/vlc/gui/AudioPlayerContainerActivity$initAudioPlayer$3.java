package org.videolan.vlc.gui;

import android.view.View;
import android.widget.FrameLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.BottomNavigationBehavior;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"org/videolan/vlc/gui/AudioPlayerContainerActivity$initAudioPlayer$3", "Lcom/google/android/material/bottomsheet/BottomSheetBehavior$BottomSheetCallback;", "onSlide", "", "bottomSheet", "Landroid/view/View;", "slideOffset", "", "onStateChanged", "newState", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioPlayerContainerActivity.kt */
public final class AudioPlayerContainerActivity$initAudioPlayer$3 extends BottomSheetBehavior.BottomSheetCallback {
    final /* synthetic */ BottomNavigationBehavior<View> $bottomBehavior;
    final /* synthetic */ AudioPlayerContainerActivity this$0;

    AudioPlayerContainerActivity$initAudioPlayer$3(AudioPlayerContainerActivity audioPlayerContainerActivity, BottomNavigationBehavior<View> bottomNavigationBehavior) {
        this.this$0 = audioPlayerContainerActivity;
        this.$bottomBehavior = bottomNavigationBehavior;
    }

    public void onSlide(View view, float f) {
        AudioPlayerContainerActivity audioPlayerContainerActivity;
        BottomNavigationView access$getBottomBar$p;
        Intrinsics.checkNotNullParameter(view, "bottomSheet");
        this.this$0.getAudioPlayer().onSlide(f);
        float min = Math.min(1.0f, Math.max(0.0f, f));
        BottomNavigationBehavior<View> bottomNavigationBehavior = this.$bottomBehavior;
        if (bottomNavigationBehavior != null && (access$getBottomBar$p = audioPlayerContainerActivity.bottomBar) != null) {
            FrameLayout access$getAudioPlayerContainer$p = (audioPlayerContainerActivity = this.this$0).audioPlayerContainer;
            if (access$getAudioPlayerContainer$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                access$getAudioPlayerContainer$p = null;
            }
            bottomNavigationBehavior.translate(access$getBottomBar$p, Math.min((min * ((float) access$getAudioPlayerContainer$p.getHeight())) / ((float) 2), (float) access$getBottomBar$p.getHeight()) - ((float) (UiTools.INSTANCE.isTablet(audioPlayerContainerActivity) ? audioPlayerContainerActivity.topInset : 0)));
        }
    }

    public void onStateChanged(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "bottomSheet");
        this.this$0.onPlayerStateChanged(view, i);
        this.this$0.hideStatusIfNeeded(i);
        this.this$0.getAudioPlayer().onStateChanged(i);
        if (i == 4 || i == 5) {
            this.this$0.removeTipViewIfDisplayed();
        }
        this.this$0.updateFragmentMargins(i);
        AudioPlayerContainerActivity audioPlayerContainerActivity = this.this$0;
        audioPlayerContainerActivity.applyMarginToProgressBar(audioPlayerContainerActivity.getPlayerBehavior().getPeekHeight());
        this.this$0.setContentBottomPadding();
        if (AccessibilityHelperKt.isTalkbackIsEnabled(this.this$0)) {
            int state = this.this$0.getPlayerBehavior().getState();
            FrameLayout frameLayout = null;
            if (state == 3) {
                FrameLayout access$getAudioPlayerContainer$p = this.this$0.audioPlayerContainer;
                if (access$getAudioPlayerContainer$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                } else {
                    frameLayout = access$getAudioPlayerContainer$p;
                }
                frameLayout.announceForAccessibility(this.this$0.getString(R.string.talkback_audio_player_opened));
            } else if (state == 4) {
                FrameLayout access$getAudioPlayerContainer$p2 = this.this$0.audioPlayerContainer;
                if (access$getAudioPlayerContainer$p2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                } else {
                    frameLayout = access$getAudioPlayerContainer$p2;
                }
                frameLayout.announceForAccessibility(this.this$0.getString(R.string.talkback_audio_player_collapsed));
            } else if (state == 5) {
                FrameLayout access$getAudioPlayerContainer$p3 = this.this$0.audioPlayerContainer;
                if (access$getAudioPlayerContainer$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("audioPlayerContainer");
                } else {
                    frameLayout = access$getAudioPlayerContainer$p3;
                }
                frameLayout.announceForAccessibility(this.this$0.getString(R.string.talkback_audio_player_closed));
            }
        }
    }
}
