package org.videolan.vlc.gui.preferences.widgets;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/gui/preferences/widgets/SavedColors;", "", "colors", "", "", "(Ljava/util/List;)V", "getColors", "()Ljava/util/List;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferencesWidgets.kt */
public final class SavedColors {
    private final List<Integer> colors;

    public SavedColors(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "colors");
        this.colors = list;
    }

    public final List<Integer> getColors() {
        return this.colors;
    }
}
