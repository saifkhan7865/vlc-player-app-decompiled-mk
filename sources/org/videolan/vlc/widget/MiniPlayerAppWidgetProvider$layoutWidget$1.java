package org.videolan.vlc.widget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import androidx.palette.graphics.Palette;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.widget.MiniPlayerAppWidgetProvider", f = "MiniPlayerAppWidgetProvider.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, l = {149, 179}, m = "layoutWidget", n = {"this", "context", "previewBitmap", "previewPalette", "widgetRepository", "appWidgetId", "forPreview", "previewPlaying", "partial", "this", "context", "previewBitmap", "widgetCacheEntry", "palette", "service", "oldType", "size", "appWidgetId", "forPreview", "previewPlaying", "partial", "foregroundColor", "backgroundColor", "playing", "colorChanged"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "Z$0", "Z$1", "I$1", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "I$0", "Z$0", "Z$1", "I$1", "I$2", "I$3", "I$4", "I$5"})
/* compiled from: MiniPlayerAppWidgetProvider.kt */
final class MiniPlayerAppWidgetProvider$layoutWidget$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    int I$2;
    int I$3;
    int I$4;
    int I$5;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ MiniPlayerAppWidgetProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MiniPlayerAppWidgetProvider$layoutWidget$1(MiniPlayerAppWidgetProvider miniPlayerAppWidgetProvider, Continuation<? super MiniPlayerAppWidgetProvider$layoutWidget$1> continuation) {
        super(continuation);
        this.this$0 = miniPlayerAppWidgetProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.layoutWidget((Context) null, 0, (Intent) null, false, (Bitmap) null, (Palette) null, false, this);
    }
}
