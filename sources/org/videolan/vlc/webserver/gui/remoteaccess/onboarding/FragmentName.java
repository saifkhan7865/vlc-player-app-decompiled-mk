package org.videolan.vlc.webserver.gui.remoteaccess.onboarding;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/onboarding/FragmentName;", "", "(Ljava/lang/String;I)V", "WELCOME", "HOW", "SSL", "OTP", "CONTENT", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOnboardingActivity.kt */
public enum FragmentName {
    WELCOME,
    HOW,
    SSL,
    OTP,
    CONTENT;

    public static EnumEntries<FragmentName> getEntries() {
        return $ENTRIES;
    }

    static {
        FragmentName[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
