package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B%\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000f\u001a\u00020\u0000R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/gui/audio/AudioPlayerTipsStep;", "", "titleText", "", "descriptionText", "descriptionTextTablet", "(Ljava/lang/String;IIII)V", "getDescriptionText", "()I", "setDescriptionText", "(I)V", "getDescriptionTextTablet", "setDescriptionTextTablet", "getTitleText", "setTitleText", "next", "SWIPE_NEXT", "TAP_PLAYLIST", "HOLD_STOP", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioTipsDelegate.kt */
public enum AudioPlayerTipsStep {
    SWIPE_NEXT(R.string.previous_next_song, R.string.tips_swipe_horizontal, R.string.tap_to_previous_next),
    TAP_PLAYLIST(R.string.tips_playlist, R.string.tap, R.string.tap),
    HOLD_STOP(R.string.stop, R.string.hold_to_stop, R.string.hold_to_stop);
    
    private int descriptionText;
    private int descriptionTextTablet;
    private int titleText;

    public static EnumEntries<AudioPlayerTipsStep> getEntries() {
        return $ENTRIES;
    }

    private AudioPlayerTipsStep(int i, int i2, int i3) {
        this.titleText = i;
        this.descriptionText = i2;
        this.descriptionTextTablet = i3;
    }

    public final int getDescriptionText() {
        return this.descriptionText;
    }

    public final int getDescriptionTextTablet() {
        return this.descriptionTextTablet;
    }

    public final int getTitleText() {
        return this.titleText;
    }

    public final void setDescriptionText(int i) {
        this.descriptionText = i;
    }

    public final void setDescriptionTextTablet(int i) {
        this.descriptionTextTablet = i;
    }

    public final void setTitleText(int i) {
        this.titleText = i;
    }

    static {
        AudioPlayerTipsStep[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public final AudioPlayerTipsStep next() {
        return values()[ordinal() + 1];
    }
}
