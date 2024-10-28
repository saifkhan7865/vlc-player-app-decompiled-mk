package org.videolan.vlc.widget.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00042\u0006\u0010\u0006\u001a\u00020\u0007J \u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002J \u0010\r\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J \u0010\u0010\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0002J\"\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u00122\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\tJ\u0014\u0010\u0013\u001a\u00020\t*\u00020\u00072\u0006\u0010\u0014\u001a\u00020\tH\u0002¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/widget/utils/WidgetSizeUtil;", "", "()V", "getAppWidgetManager", "Landroid/appwidget/AppWidgetManager;", "kotlin.jvm.PlatformType", "context", "Landroid/content/Context;", "getWidgetHeight", "", "isPortrait", "", "widgetId", "getWidgetSizeInDp", "key", "", "getWidgetWidth", "getWidgetsSize", "Lkotlin/Pair;", "dip", "value", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetSizeUtil.kt */
public final class WidgetSizeUtil {
    public static final WidgetSizeUtil INSTANCE = new WidgetSizeUtil();

    private WidgetSizeUtil() {
    }

    public final Pair<Integer, Integer> getWidgetsSize(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        boolean z = true;
        if (context.getResources().getConfiguration().orientation != 1) {
            z = false;
        }
        return TuplesKt.to(Integer.valueOf(getWidgetWidth(context, z, i)), Integer.valueOf(getWidgetHeight(context, z, i)));
    }

    public final AppWidgetManager getAppWidgetManager(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return AppWidgetManager.getInstance(context);
    }

    private final int getWidgetWidth(Context context, boolean z, int i) {
        if (z) {
            return getWidgetSizeInDp(context, i, "appWidgetMinWidth");
        }
        return getWidgetSizeInDp(context, i, "appWidgetMaxWidth");
    }

    private final int getWidgetHeight(Context context, boolean z, int i) {
        if (z) {
            return getWidgetSizeInDp(context, i, "appWidgetMaxHeight");
        }
        return getWidgetSizeInDp(context, i, "appWidgetMinHeight");
    }

    private final int getWidgetSizeInDp(Context context, int i, String str) {
        return getAppWidgetManager(context).getAppWidgetOptions(i).getInt(str, 0);
    }

    private final int dip(Context context, int i) {
        return (int) (((float) i) * context.getResources().getDisplayMetrics().density);
    }
}
