package org.videolan.vlc.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda2 implements Runnable {
    public final /* synthetic */ RemoteViews f$0;
    public final /* synthetic */ Bitmap f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ MiniPlayerAppWidgetProvider f$3;
    public final /* synthetic */ Context f$4;
    public final /* synthetic */ boolean f$5;
    public final /* synthetic */ int f$6;

    public /* synthetic */ MiniPlayerAppWidgetProvider$$ExternalSyntheticLambda2(RemoteViews remoteViews, Bitmap bitmap, boolean z, MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Context context, boolean z2, int i) {
        this.f$0 = remoteViews;
        this.f$1 = bitmap;
        this.f$2 = z;
        this.f$3 = miniPlayerAppWidgetProvider;
        this.f$4 = context;
        this.f$5 = z2;
        this.f$6 = i;
    }

    public final void run() {
        MiniPlayerAppWidgetProvider.layoutWidget$lambda$12$lambda$11$lambda$9$lambda$8(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6);
    }
}
