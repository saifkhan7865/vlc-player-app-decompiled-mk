package org.videolan.vlc.widget.utils;

import androidx.palette.graphics.Palette;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lorg/videolan/vlc/widget/utils/WidgetCache;", "", "()V", "entries", "", "Lorg/videolan/vlc/widget/utils/WidgetCacheEntry;", "addEntry", "widget", "Lorg/videolan/vlc/mediadb/models/Widget;", "clear", "", "getEntry", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetCache.kt */
public final class WidgetCache {
    public static final WidgetCache INSTANCE = new WidgetCache();
    private static final List<WidgetCacheEntry> entries = new ArrayList();

    private WidgetCache() {
    }

    public final WidgetCacheEntry getEntry(Widget widget) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        for (WidgetCacheEntry widgetCacheEntry : entries) {
            if (widgetCacheEntry.getWidget().getWidgetId() == widget.getWidgetId()) {
                return widgetCacheEntry;
            }
        }
        return null;
    }

    public final WidgetCacheEntry addEntry(Widget widget) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        WidgetCacheEntry widgetCacheEntry = new WidgetCacheEntry(widget, (MediaWrapper) null, (String) null, (Palette) null, (Integer) null, (Boolean) null, true, 62, (DefaultConstructorMarker) null);
        entries.add(widgetCacheEntry);
        return widgetCacheEntry;
    }

    public final void clear(Widget widget) {
        Intrinsics.checkNotNullParameter(widget, "widget");
        WidgetCacheEntry widgetCacheEntry = null;
        for (WidgetCacheEntry widgetCacheEntry2 : entries) {
            if (widgetCacheEntry2.getWidget().getWidgetId() == widget.getWidgetId()) {
                widgetCacheEntry = widgetCacheEntry2;
            }
        }
        if (widgetCacheEntry != null) {
            entries.remove(widgetCacheEntry);
        }
    }
}
