package androidx.databinding.adapters;

import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.databinding.library.baseAdapters.R;

public class ViewBindingAdapter {
    public static final int FADING_EDGE_HORIZONTAL = 1;
    public static final int FADING_EDGE_NONE = 0;
    public static final int FADING_EDGE_VERTICAL = 2;

    public interface OnViewAttachedToWindow {
        void onViewAttachedToWindow(View view);
    }

    public interface OnViewDetachedFromWindow {
        void onViewDetachedFromWindow(View view);
    }

    private static int pixelsToDimensionPixelSize(float f) {
        int i = (int) (0.5f + f);
        if (i != 0) {
            return i;
        }
        if (f == 0.0f) {
            return 0;
        }
        return f > 0.0f ? 1 : -1;
    }

    public static void setPadding(View view, float f) {
        int pixelsToDimensionPixelSize = pixelsToDimensionPixelSize(f);
        view.setPadding(pixelsToDimensionPixelSize, pixelsToDimensionPixelSize, pixelsToDimensionPixelSize, pixelsToDimensionPixelSize);
    }

    public static void setPaddingBottom(View view, float f) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), pixelsToDimensionPixelSize(f));
    }

    public static void setPaddingEnd(View view, float f) {
        view.setPaddingRelative(view.getPaddingStart(), view.getPaddingTop(), pixelsToDimensionPixelSize(f), view.getPaddingBottom());
    }

    public static void setPaddingLeft(View view, float f) {
        view.setPadding(pixelsToDimensionPixelSize(f), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setPaddingRight(View view, float f) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), pixelsToDimensionPixelSize(f), view.getPaddingBottom());
    }

    public static void setPaddingStart(View view, float f) {
        view.setPaddingRelative(pixelsToDimensionPixelSize(f), view.getPaddingTop(), view.getPaddingEnd(), view.getPaddingBottom());
    }

    public static void setPaddingTop(View view, float f) {
        view.setPadding(view.getPaddingLeft(), pixelsToDimensionPixelSize(f), view.getPaddingRight(), view.getPaddingBottom());
    }

    public static void setRequiresFadingEdge(View view, int i) {
        boolean z = false;
        boolean z2 = (i & 2) != 0;
        if ((i & 1) != 0) {
            z = true;
        }
        view.setVerticalFadingEdgeEnabled(z2);
        view.setHorizontalFadingEdgeEnabled(z);
    }

    public static void setClickListener(View view, View.OnClickListener onClickListener, boolean z) {
        view.setOnClickListener(onClickListener);
        view.setClickable(z);
    }

    public static void setOnClick(View view, View.OnClickListener onClickListener, boolean z) {
        view.setOnClickListener(onClickListener);
        view.setClickable(z);
    }

    public static void setOnLongClickListener(View view, View.OnLongClickListener onLongClickListener, boolean z) {
        view.setOnLongClickListener(onLongClickListener);
        view.setLongClickable(z);
    }

    public static void setOnLongClick(View view, View.OnLongClickListener onLongClickListener, boolean z) {
        view.setOnLongClickListener(onLongClickListener);
        view.setLongClickable(z);
    }

    public static void setOnAttachStateChangeListener(View view, final OnViewDetachedFromWindow onViewDetachedFromWindow, final OnViewAttachedToWindow onViewAttachedToWindow) {
        AnonymousClass1 r2;
        if (onViewDetachedFromWindow == null && onViewAttachedToWindow == null) {
            r2 = null;
        } else {
            r2 = new View.OnAttachStateChangeListener() {
                public void onViewAttachedToWindow(View view) {
                    OnViewAttachedToWindow onViewAttachedToWindow = OnViewAttachedToWindow.this;
                    if (onViewAttachedToWindow != null) {
                        onViewAttachedToWindow.onViewAttachedToWindow(view);
                    }
                }

                public void onViewDetachedFromWindow(View view) {
                    OnViewDetachedFromWindow onViewDetachedFromWindow = onViewDetachedFromWindow;
                    if (onViewDetachedFromWindow != null) {
                        onViewDetachedFromWindow.onViewDetachedFromWindow(view);
                    }
                }
            };
        }
        View.OnAttachStateChangeListener onAttachStateChangeListener = (View.OnAttachStateChangeListener) ListenerUtil.trackListener(view, r2, R.id.onAttachStateChangeListener);
        if (onAttachStateChangeListener != null) {
            view.removeOnAttachStateChangeListener(onAttachStateChangeListener);
        }
        if (r2 != null) {
            view.addOnAttachStateChangeListener(r2);
        }
    }

    public static void setOnLayoutChangeListener(View view, View.OnLayoutChangeListener onLayoutChangeListener, View.OnLayoutChangeListener onLayoutChangeListener2) {
        if (onLayoutChangeListener != null) {
            view.removeOnLayoutChangeListener(onLayoutChangeListener);
        }
        if (onLayoutChangeListener2 != null) {
            view.addOnLayoutChangeListener(onLayoutChangeListener2);
        }
    }

    public static void setBackground(View view, Drawable drawable) {
        view.setBackground(drawable);
    }
}
