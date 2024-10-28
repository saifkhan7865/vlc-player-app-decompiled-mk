package org.videolan.vlc.interfaces;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0001\u0006J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\b\u0010\u0005\u001a\u00020\u0003H&¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/interfaces/IPlaybackSettingsController;", "", "endPlaybackSetting", "", "showAudioDelaySetting", "showSubsDelaySetting", "DelayState", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: IPlaybackSettingsController.kt */
public interface IPlaybackSettingsController {
    void endPlaybackSetting();

    void showAudioDelaySetting();

    void showSubsDelaySetting();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/interfaces/IPlaybackSettingsController$DelayState;", "", "(Ljava/lang/String;I)V", "OFF", "AUDIO", "SUBS", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: IPlaybackSettingsController.kt */
    public enum DelayState {
        OFF,
        AUDIO,
        SUBS;

        public static EnumEntries<DelayState> getEntries() {
            return $ENTRIES;
        }

        static {
            DelayState[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }
}
