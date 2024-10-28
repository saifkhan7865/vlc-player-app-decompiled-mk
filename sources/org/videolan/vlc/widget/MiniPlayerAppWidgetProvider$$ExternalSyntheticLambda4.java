package org.videolan.vlc.widget;

import android.content.Context;
import android.widget.RemoteViews;
import org.videolan.vlc.widget.utils.WidgetCacheEntry;
import org.videolan.vlc.widget.utils.WidgetType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda4 implements Runnable {
    public final /* synthetic */ MiniPlayerAppWidgetProvider f$0;
    public final /* synthetic */ int f$1;
    public final /* synthetic */ WidgetCacheEntry f$2;
    public final /* synthetic */ Context f$3;
    public final /* synthetic */ WidgetType f$4;
    public final /* synthetic */ RemoteViews f$5;
    public final /* synthetic */ boolean f$6;

    public /* synthetic */ MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda4(MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, int i, WidgetCacheEntry widgetCacheEntry, Context context, WidgetType widgetType, RemoteViews remoteViews, boolean z) {
        this.f$0 = miniPlayerAppWidgetProvider;
        this.f$1 = i;
        this.f$2 = widgetCacheEntry;
        this.f$3 = context;
        this.f$4 = widgetType;
        this.f$5 = remoteViews;
        this.f$6 = z;
    }

    public final void run() {
        MiniPlayerAppWidgetProvider.layoutWidget$lambda$6(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
