package org.videolan.vlc.widget;

import android.content.Context;
import android.widget.RemoteViews;
import org.videolan.vlc.widget.utils.WidgetCacheEntry;
import org.videolan.vlc.widget.utils.WidgetType;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda5 implements Runnable {
    public final /* synthetic */ WidgetType f$0;
    public final /* synthetic */ WidgetCacheEntry f$1;
    public final /* synthetic */ Context f$2;
    public final /* synthetic */ float f$3;
    public final /* synthetic */ RemoteViews f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ MiniPlayerAppWidgetProvider f$6;
    public final /* synthetic */ boolean f$7;
    public final /* synthetic */ int f$8;

    public /* synthetic */ MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda5(WidgetType widgetType, WidgetCacheEntry widgetCacheEntry, Context context, float f, RemoteViews remoteViews, boolean z, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, boolean z2, int i) {
        this.f$0 = widgetType;
        this.f$1 = widgetCacheEntry;
        this.f$2 = context;
        this.f$3 = f;
        this.f$4 = remoteViews;
        this.f$5 = z;
        this.f$6 = miniPlayerAppWidgetProvider;
        this.f$7 = z2;
        this.f$8 = i;
    }

    public final void run() {
        MiniPlayerAppWidgetProvider.layoutWidget$lambda$12$lambda$11(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8);
    }
}
