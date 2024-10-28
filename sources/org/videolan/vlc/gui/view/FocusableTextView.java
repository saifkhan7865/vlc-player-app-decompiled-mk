package org.videolan.vlc.gui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0019B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0011\u001a\u00020\u0012H\u0002J\"\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014R\u001b\u0010\u000b\u001a\u00020\f8FX\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/gui/view/FocusableTextView;", "Landroidx/appcompat/widget/AppCompatTextView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "paint", "Landroid/graphics/Paint;", "getPaint", "()Landroid/graphics/Paint;", "paint$delegate", "Lkotlin/Lazy;", "initialize", "", "onFocusChanged", "focused", "", "direction", "previouslyFocusedRect", "Landroid/graphics/Rect;", "FocusDrawable", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusableTextView.kt */
public class FocusableTextView extends AppCompatTextView {
    private final Lazy paint$delegate = LazyKt.lazy(new FocusableTextView$paint$2(this));

    /* access modifiers changed from: protected */
    public void onFocusChanged(boolean z, int i, Rect rect) {
    }

    public final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FocusableTextView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FocusableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FocusableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    private final void initialize() {
        setBackground(new FocusDrawable());
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\u000eH\u0017J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\u000eH\u0016J\u0012\u0010\u0015\u001a\u00020\n2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/view/FocusableTextView$FocusDrawable;", "Landroid/graphics/drawable/Drawable;", "(Lorg/videolan/vlc/gui/view/FocusableTextView;)V", "pressed", "", "getPressed", "()Z", "setPressed", "(Z)V", "draw", "", "canvas", "Landroid/graphics/Canvas;", "getOpacity", "", "isStateful", "onStateChange", "states", "", "setAlpha", "i", "setColorFilter", "colorFilter", "Landroid/graphics/ColorFilter;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FocusableTextView.kt */
    public final class FocusDrawable extends Drawable {
        private boolean pressed;

        @Deprecated(message = "Deprecated in Java", replaceWith = @ReplaceWith(expression = "PixelFormat.OPAQUE", imports = {"android.graphics.PixelFormat"}))
        public int getOpacity() {
            return -1;
        }

        public boolean isStateful() {
            return true;
        }

        public void setAlpha(int i) {
        }

        public void setColorFilter(ColorFilter colorFilter) {
        }

        public FocusDrawable() {
        }

        public final boolean getPressed() {
            return this.pressed;
        }

        public final void setPressed(boolean z) {
            this.pressed = z;
        }

        /* access modifiers changed from: protected */
        public boolean onStateChange(int[] iArr) {
            Intrinsics.checkNotNullParameter(iArr, "states");
            invalidateSelf();
            return true;
        }

        public void draw(Canvas canvas) {
            Integer num;
            int i;
            Intrinsics.checkNotNullParameter(canvas, "canvas");
            int[] state = getState();
            Intrinsics.checkNotNullExpressionValue(state, "getState(...)");
            int length = state.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    num = null;
                    break;
                }
                i = state[i2];
                if (i == 16842919 || i == 16842908) {
                    num = Integer.valueOf(i);
                } else {
                    i2++;
                }
            }
            num = Integer.valueOf(i);
            if (num != null) {
                Rect rect = new Rect();
                FocusableTextView.this.getPaint().getTextBounds(FocusableTextView.this.getText().toString(), 0, FocusableTextView.this.getText().length(), rect);
                if ((FocusableTextView.this.getGravity() & 5) != 1) {
                    canvas.drawRoundRect(((float) FocusableTextView.this.getWidth()) - ((((float) rect.width()) + ((float) FocusableTextView.this.getPaddingLeft())) + ((float) FocusableTextView.this.getPaddingRight())), 0.0f, (float) FocusableTextView.this.getWidth(), (float) getBounds().height(), (float) KotlinExtensionsKt.getDp(4), (float) KotlinExtensionsKt.getDp(4), FocusableTextView.this.getPaint());
                    return;
                }
                canvas.drawRoundRect((float) rect.left, 0.0f, ((float) rect.right) + ((float) FocusableTextView.this.getPaddingLeft()) + ((float) FocusableTextView.this.getPaddingRight()), (float) getBounds().height(), (float) KotlinExtensionsKt.getDp(4), (float) KotlinExtensionsKt.getDp(4), FocusableTextView.this.getPaint());
            }
        }
    }
}
