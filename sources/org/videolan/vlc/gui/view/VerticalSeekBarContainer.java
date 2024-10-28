package org.videolan.vlc.gui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.google.android.material.slider.Slider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B%\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0002J\u0018\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007H\u0014J(\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0014R\u0016\u0010\t\u001a\u0004\u0018\u00010\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/gui/view/VerticalSeekBarContainer;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "childSlider", "Lcom/google/android/material/slider/Slider;", "getChildSlider", "()Lcom/google/android/material/slider/Slider;", "applyViewRotation", "", "w", "h", "onMeasure", "widthMeasureSpec", "heightMeasureSpec", "onSizeChanged", "oldw", "oldh", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VerticalSeekBarContainer.kt */
public final class VerticalSeekBarContainer extends FrameLayout {
    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VerticalSeekBarContainer(Context context) {
        this(context, (AttributeSet) null, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public VerticalSeekBarContainer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerticalSeekBarContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ VerticalSeekBarContainer(Context context, AttributeSet attributeSet, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i2 & 2) != 0 ? null : attributeSet, (i2 & 4) != 0 ? 0 : i);
    }

    private final Slider getChildSlider() {
        KeyEvent.Callback childAt = getChildCount() > 0 ? getChildAt(0) : null;
        if (childAt instanceof Slider) {
            return (Slider) childAt;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        Slider childSlider = getChildSlider();
        if (childSlider != null) {
            childSlider.measure(View.MeasureSpec.makeMeasureSpec(Math.max(0, i2 - (getPaddingTop() + getPaddingBottom())), 1073741824), View.MeasureSpec.makeMeasureSpec(Math.max(0, i - (getPaddingLeft() + getPaddingRight())), Integer.MIN_VALUE));
        }
        applyViewRotation(i, i2);
        super.onSizeChanged(i, i2, i3, i4);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        Slider childSlider = getChildSlider();
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        if (childSlider == null || mode == 1073741824) {
            super.onMeasure(i, i2);
            return;
        }
        int measuredHeight = childSlider.getMeasuredHeight();
        int measuredWidth = childSlider.getMeasuredWidth();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        childSlider.measure(View.MeasureSpec.makeMeasureSpec(RangesKt.coerceAtLeast(size2 - paddingTop, 0), mode2), View.MeasureSpec.makeMeasureSpec(RangesKt.coerceAtLeast(size - paddingLeft, 0), mode));
        setMeasuredDimension(View.resolveSizeAndState(measuredHeight + paddingLeft, i, 0), View.resolveSizeAndState(measuredWidth + paddingTop, i2, 0));
    }

    private final void applyViewRotation(int i, int i2) {
        Slider childSlider = getChildSlider();
        setLayoutDirection(0);
        if (childSlider != null) {
            int measuredWidth = childSlider.getMeasuredWidth();
            int measuredHeight = childSlider.getMeasuredHeight();
            int paddingLeft = getPaddingLeft() + getPaddingRight();
            ViewGroup.LayoutParams layoutParams = childSlider.getLayoutParams();
            layoutParams.width = Math.max(0, i2 - (getPaddingTop() + getPaddingBottom()));
            layoutParams.height = -2;
            childSlider.setLayoutParams(layoutParams);
            childSlider.setPivotX(0.0f);
            childSlider.setPivotY(0.0f);
            childSlider.setRotation(270.0f);
            childSlider.setTranslationX(((float) (Math.max(0, i - paddingLeft) - measuredHeight)) / 2.0f);
            childSlider.setTranslationY((float) measuredWidth);
        }
    }
}
