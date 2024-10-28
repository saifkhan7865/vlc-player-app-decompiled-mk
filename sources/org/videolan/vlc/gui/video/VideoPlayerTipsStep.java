package org.videolan.vlc.gui.video;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0006\u0010\f\u001a\u00020\u0000R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\tj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/video/VideoPlayerTipsStep;", "", "titleText", "", "descriptionText", "(Ljava/lang/String;III)V", "getDescriptionText", "()I", "setDescriptionText", "(I)V", "getTitleText", "setTitleText", "next", "CONTROLS", "BRIGHTNESS", "VOLUME", "PAUSE", "SEEK_TAP", "SEEK", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoTipsDelegate.kt */
public enum VideoPlayerTipsStep {
    CONTROLS(R.string.tips_player_controls, R.string.tips_player_controls_description),
    BRIGHTNESS(R.string.brightness, R.string.tips_swipe),
    VOLUME(R.string.volume, R.string.tips_swipe),
    PAUSE(R.string.pause, R.string.pause_description),
    SEEK_TAP(R.string.seek_tap, R.string.seek_tap_description),
    SEEK(R.string.seek, R.string.tips_swipe_horizontal);
    
    private int descriptionText;
    private int titleText;

    public static EnumEntries<VideoPlayerTipsStep> getEntries() {
        return $ENTRIES;
    }

    private VideoPlayerTipsStep(int i, int i2) {
        this.titleText = i;
        this.descriptionText = i2;
    }

    public final int getDescriptionText() {
        return this.descriptionText;
    }

    public final int getTitleText() {
        return this.titleText;
    }

    public final void setDescriptionText(int i) {
        this.descriptionText = i;
    }

    public final void setTitleText(int i) {
        this.titleText = i;
    }

    static {
        VideoPlayerTipsStep[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final VideoPlayerTipsStep next() {
        return values()[ordinal() + 1];
    }
}
