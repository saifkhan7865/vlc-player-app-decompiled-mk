package org.videolan.vlc.widget.utils;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lorg/videolan/vlc/widget/utils/WidgetType;", "", "layout", "", "(Ljava/lang/String;II)V", "getLayout", "()I", "PILL", "MINI", "MICRO", "MACRO", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetUtils.kt */
public enum WidgetType {
    PILL(R.layout.widget_pill),
    MINI(R.layout.widget_mini),
    MICRO(R.layout.widget_micro),
    MACRO(R.layout.widget_macro);
    
    private final int layout;

    public static EnumEntries<WidgetType> getEntries() {
        return $ENTRIES;
    }

    private WidgetType(int i) {
        this.layout = i;
    }

    public final int getLayout() {
        return this.layout;
    }

    static {
        WidgetType[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }
}
