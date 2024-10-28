package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/PinStep;", "", "(Ljava/lang/String;I)V", "ENTER_EXISTING", "INVALID", "ENTER_NEW", "RE_ENTER", "NO_MATCH", "LOGIN_SUCCESS", "EXIT", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PinCodeActivity.kt */
public enum PinStep {
    ENTER_EXISTING,
    INVALID,
    ENTER_NEW,
    RE_ENTER,
    NO_MATCH,
    LOGIN_SUCCESS,
    EXIT;

    public static EnumEntries<PinStep> getEntries() {
        return $ENTRIES;
    }

    static {
        PinStep[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
