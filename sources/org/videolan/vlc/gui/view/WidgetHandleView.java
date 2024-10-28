package org.videolan.vlc.gui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/view/WidgetHandleView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "paint", "Landroid/graphics/Paint;", "initialize", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetHandleView.kt */
public final class WidgetHandleView extends View {
    private final Paint paint = new Paint();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WidgetHandleView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WidgetHandleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WidgetHandleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    private final void initialize() {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = getContext().getTheme();
        Intrinsics.checkNotNullExpressionValue(theme, "getTheme(...)");
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        this.paint.setColor(typedValue.data);
        this.paint.setAntiAlias(true);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Canvas canvas2 = canvas;
        Intrinsics.checkNotNullParameter(canvas2, "canvas");
        float dp = (float) KotlinExtensionsKt.getDp(8);
        int width = getWidth();
        double height = (double) (getHeight() - KotlinExtensionsKt.getDp(16));
        Double.isNaN(height);
        double dp2 = (double) KotlinExtensionsKt.getDp(6);
        Double.isNaN(dp2);
        double d = (height * 3.56d) - dp2;
        float f = (float) width;
        double d2 = (double) f;
        Double.isNaN(d2);
        double d3 = (double) 2;
        Double.isNaN(d3);
        float f2 = (float) ((d2 - d) / d3);
        float height2 = ((float) getHeight()) - dp;
        this.paint.setStrokeWidth((float) KotlinExtensionsKt.getDp(3));
        this.paint.setStyle(Paint.Style.STROKE);
        float f3 = height2;
        canvas.drawRoundRect(f2, dp, f - f2, height2, (float) KotlinExtensionsKt.getDp(12), (float) KotlinExtensionsKt.getDp(12), this.paint);
        this.paint.setStyle(Paint.Style.FILL);
        float f4 = (float) 2;
        float f5 = (f3 / f4) + dp;
        canvas2.drawCircle(f2, f5, (float) KotlinExtensionsKt.getDp(6), this.paint);
        float f6 = (float) d;
        float f7 = (f6 / f4) + f2;
        canvas2.drawCircle(f7, dp, (float) KotlinExtensionsKt.getDp(6), this.paint);
        canvas2.drawCircle(f2 + f6, f5, (float) KotlinExtensionsKt.getDp(6), this.paint);
        canvas2.drawCircle(f7, f3, (float) KotlinExtensionsKt.getDp(6), this.paint);
        super.onDraw(canvas);
    }
}
