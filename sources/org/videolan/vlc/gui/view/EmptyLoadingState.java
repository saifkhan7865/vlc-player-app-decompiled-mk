package org.videolan.vlc.gui.view;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/view/EmptyLoadingState;", "", "(Ljava/lang/String;I)V", "LOADING", "EMPTY", "EMPTY_SEARCH", "NONE", "MISSING_PERMISSION", "EMPTY_FAVORITES", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EmptyLoadingStateView.kt */
public enum EmptyLoadingState {
    LOADING,
    EMPTY,
    EMPTY_SEARCH,
    NONE,
    MISSING_PERMISSION,
    EMPTY_FAVORITES;

    public static EnumEntries<EmptyLoadingState> getEntries() {
        return $ENTRIES;
    }

    static {
        EmptyLoadingState[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
