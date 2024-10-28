package org.videolan.vlc.gui.view;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.gui.view.AudioMediaSwitcher;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016¨\u0006\u000e"}, d2 = {"org/videolan/vlc/gui/view/AudioMediaSwitcher$viewSwitchListener$1", "Lorg/videolan/vlc/gui/view/ViewSwitchListener;", "onBackSwitched", "", "onSwitched", "position", "", "onSwitching", "progress", "", "onTouchClick", "onTouchDown", "onTouchLongClick", "onTouchUp", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioMediaSwitcher.kt */
public final class AudioMediaSwitcher$viewSwitchListener$1 implements ViewSwitchListener {
    final /* synthetic */ AudioMediaSwitcher this$0;

    public void onBackSwitched() {
    }

    AudioMediaSwitcher$viewSwitchListener$1(AudioMediaSwitcher audioMediaSwitcher) {
        this.this$0 = audioMediaSwitcher;
    }

    public void onSwitching(float f) {
        AudioMediaSwitcher.AudioMediaSwitcherListener access$getAudioMediaSwitcherListener$p = this.this$0.audioMediaSwitcherListener;
        if (access$getAudioMediaSwitcherListener$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            access$getAudioMediaSwitcherListener$p = null;
        }
        access$getAudioMediaSwitcherListener$p.onMediaSwitching();
    }

    public void onSwitched(int i) {
        AudioMediaSwitcher.AudioMediaSwitcherListener access$getAudioMediaSwitcherListener$p = this.this$0.audioMediaSwitcherListener;
        if (access$getAudioMediaSwitcherListener$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            access$getAudioMediaSwitcherListener$p = null;
        }
        AudioMediaSwitcher audioMediaSwitcher = this.this$0;
        if (audioMediaSwitcher.previousPosition == i) {
            access$getAudioMediaSwitcherListener$p.onMediaSwitched(2);
        } else if (i == 0 && audioMediaSwitcher.hasPrevious) {
            access$getAudioMediaSwitcherListener$p.onMediaSwitched(1);
        } else if (i == 1 && !audioMediaSwitcher.hasPrevious) {
            access$getAudioMediaSwitcherListener$p.onMediaSwitched(3);
        } else if (i == 2) {
            access$getAudioMediaSwitcherListener$p.onMediaSwitched(3);
        }
        audioMediaSwitcher.previousPosition = i;
    }

    public void onTouchDown() {
        AudioMediaSwitcher.AudioMediaSwitcherListener access$getAudioMediaSwitcherListener$p = this.this$0.audioMediaSwitcherListener;
        if (access$getAudioMediaSwitcherListener$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            access$getAudioMediaSwitcherListener$p = null;
        }
        access$getAudioMediaSwitcherListener$p.onTouchDown();
    }

    public void onTouchUp() {
        AudioMediaSwitcher.AudioMediaSwitcherListener access$getAudioMediaSwitcherListener$p = this.this$0.audioMediaSwitcherListener;
        if (access$getAudioMediaSwitcherListener$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            access$getAudioMediaSwitcherListener$p = null;
        }
        access$getAudioMediaSwitcherListener$p.onTouchUp();
    }

    public void onTouchClick() {
        AudioMediaSwitcher.AudioMediaSwitcherListener access$getAudioMediaSwitcherListener$p = this.this$0.audioMediaSwitcherListener;
        if (access$getAudioMediaSwitcherListener$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            access$getAudioMediaSwitcherListener$p = null;
        }
        access$getAudioMediaSwitcherListener$p.onTouchClick();
    }

    public void onTouchLongClick() {
        AudioMediaSwitcher.AudioMediaSwitcherListener access$getAudioMediaSwitcherListener$p = this.this$0.audioMediaSwitcherListener;
        if (access$getAudioMediaSwitcherListener$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("audioMediaSwitcherListener");
            access$getAudioMediaSwitcherListener$p = null;
        }
        access$getAudioMediaSwitcherListener$p.onTouchLongClick();
    }
}
