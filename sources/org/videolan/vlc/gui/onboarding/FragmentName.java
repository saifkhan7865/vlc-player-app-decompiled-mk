package org.videolan.vlc.gui.onboarding;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/onboarding/FragmentName;", "", "(Ljava/lang/String;I)V", "WELCOME", "ASK_PERMISSION", "SCAN", "NO_PERMISSION", "NOTIFICATION_PERMISSION", "THEME", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OnboardingActivity.kt */
public enum FragmentName {
    WELCOME,
    ASK_PERMISSION,
    SCAN,
    NO_PERMISSION,
    NOTIFICATION_PERMISSION,
    THEME;

    public static EnumEntries<FragmentName> getEntries() {
        return $ENTRIES;
    }

    static {
        FragmentName[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
