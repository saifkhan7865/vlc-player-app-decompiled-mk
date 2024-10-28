package org.videolan.vlc.gui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0002J\u0010\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0014R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/gui/view/HalfCircleView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "isLeft", "", "paint", "Landroid/graphics/Paint;", "initAttributes", "", "initialize", "onDraw", "canvas", "Landroid/graphics/Canvas;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HalfCircleView.kt */
public final class HalfCircleView extends View {
    private boolean isLeft = true;
    private final Paint paint = new Paint();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HalfCircleView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HalfCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, 0);
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HalfCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, 0);
        initialize();
    }

    private final void initAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.HalfCircleView, 0, i);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            this.isLeft = obtainStyledAttributes.getBoolean(R.styleable.HalfCircleView_is_left, true);
        } catch (Exception unused) {
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
    }

    private final void initialize() {
        this.paint.setColor(ContextCompat.getColor(getContext(), R.color.blacktransparent));
        this.paint.setAntiAlias(true);
        if (Settings.INSTANCE.getShowTvUi()) {
            setBackground(ContextCompat.getDrawable(getContext(), this.isLeft ? R.drawable.half_circle_tv_left : R.drawable.half_circle_tv_right));
        } else {
            setBackground((Drawable) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        if (!Settings.INSTANCE.getShowTvUi()) {
            canvas.drawCircle((float) (this.isLeft ? -getWidth() : getWidth() * 2), (float) (getHeight() / 2), ((float) getWidth()) * ((float) 2), this.paint);
        }
        super.onDraw(canvas);
    }
}
