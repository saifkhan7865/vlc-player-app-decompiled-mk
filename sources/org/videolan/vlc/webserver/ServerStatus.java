package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/webserver/ServerStatus;", "", "(Ljava/lang/String;I)V", "NOT_INIT", "CONNECTING", "STOPPING", "STARTED", "STOPPED", "ERROR", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ServerStatus.kt */
public enum ServerStatus {
    NOT_INIT,
    CONNECTING,
    STOPPING,
    STARTED,
    STOPPED,
    ERROR;

    public static EnumEntries<ServerStatus> getEntries() {
        return $ENTRIES;
    }

    static {
        ServerStatus[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
