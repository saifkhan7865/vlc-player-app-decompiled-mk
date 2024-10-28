package org.videolan.vlc.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.widget.RemoteViews;
import org.videolan.vlc.widget.utils.WidgetCacheEntry;
import org.videolan.vlc.widget.utils.WidgetType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Bitmap f$0;
    public final /* synthetic */ DisplayMetrics f$1;
    public final /* synthetic */ MiniPlayerAppWidgetProvider f$2;
    public final /* synthetic */ WidgetType f$3;
    public final /* synthetic */ WidgetCacheEntry f$4;
    public final /* synthetic */ RemoteViews f$5;
    public final /* synthetic */ Context f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ int f$8;

    public /* synthetic */ MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda0(Bitmap bitmap, DisplayMetrics displayMetrics, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, WidgetType widgetType, WidgetCacheEntry widgetCacheEntry, RemoteViews remoteViews, Context context, boolean z, int i) {
        this.f$0 = bitmap;
        this.f$1 = displayMetrics;
        this.f$2 = miniPlayerAppWidgetProvider;
        this.f$3 = widgetType;
        this.f$4 = widgetCacheEntry;
        this.f$5 = remoteViews;
        this.f$6 = context;
        this.f$7 = z;
        this.f$8 = i;
    }

    public final void run() {
        MiniPlayerAppWidgetProvider.layoutWidget$lambda$6$lambda$5(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
