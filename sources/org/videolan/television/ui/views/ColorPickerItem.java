package org.videolan.television.ui.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0014R$\u0010\n\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u001b\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001a\u001a\u0004\b\u001c\u0010\u0018¨\u0006\""}, d2 = {"Lorg/videolan/television/ui/views/ColorPickerItem;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "color", "getColor", "()I", "setColor", "(I)V", "", "currentlySelected", "getCurrentlySelected", "()Z", "setCurrentlySelected", "(Z)V", "outerPaint", "Landroid/graphics/Paint;", "getOuterPaint", "()Landroid/graphics/Paint;", "outerPaint$delegate", "Lkotlin/Lazy;", "paint", "getPaint", "paint$delegate", "onDraw", "", "canvas", "Landroid/graphics/Canvas;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerItem.kt */
public final class ColorPickerItem extends FrameLayout {
    private int color;
    private boolean currentlySelected;
    private final Lazy outerPaint$delegate;
    private final Lazy paint$delegate;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ColorPickerItem(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public ColorPickerItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ColorPickerItem(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ColorPickerItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        setWillNotDraw(false);
        this.paint$delegate = LazyKt.lazy(ColorPickerItem$paint$2.INSTANCE);
        this.outerPaint$delegate = LazyKt.lazy(new ColorPickerItem$outerPaint$2(context));
    }

    public final boolean getCurrentlySelected() {
        return this.currentlySelected;
    }

    public final void setCurrentlySelected(boolean z) {
        this.currentlySelected = z;
        if (z) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageDrawable(ContextCompat.getDrawable(imageView.getContext(), R.drawable.ic_check));
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            imageView.setLayoutParams(layoutParams);
            imageView.setPadding(KotlinExtensionsKt.getDp(2), KotlinExtensionsKt.getDp(2), KotlinExtensionsKt.getDp(2), KotlinExtensionsKt.getDp(2));
            imageView.setBackground(ContextCompat.getDrawable(imageView.getContext(), R.drawable.round_black_transparent_50));
            addView(imageView);
            return;
        }
        removeAllViews();
    }

    private final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    private final Paint getOuterPaint() {
        return (Paint) this.outerPaint$delegate.getValue();
    }

    public final int getColor() {
        return this.color;
    }

    public final void setColor(int i) {
        this.color = i;
        getPaint().setColor(i);
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        super.onDraw(canvas);
        float f = (float) 2;
        canvas.drawCircle(((float) getWidth()) / f, ((float) getHeight()) / f, (((float) getWidth()) / f) - ((float) KotlinExtensionsKt.getDp(4)), getOuterPaint());
        canvas.drawCircle(((float) getWidth()) / f, ((float) getHeight()) / f, (((float) getWidth()) / f) - ((float) KotlinExtensionsKt.getDp(5)), getPaint());
    }
}
