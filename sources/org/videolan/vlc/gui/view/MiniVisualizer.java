package org.videolan.vlc.gui.view;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.chip.Chip$$ExternalSyntheticApiModelOutline1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\u0018\u001a\u00020\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0017H\u0014J\u0006\u0010\u001a\u001a\u00020\u0017J\u0006\u0010\u001b\u001a\u00020\u0017R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000R\u0016\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0014X.¢\u0006\u0004\n\u0002\u0010\u0015¨\u0006\u001c"}, d2 = {"Lorg/videolan/vlc/gui/view/MiniVisualizer;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "animator", "Landroid/animation/AnimatorSet;", "mainColor", "stopSet", "visualizer1", "Landroid/view/View;", "visualizer2", "visualizer3", "visualizers", "", "[Landroid/view/View;", "initAttributes", "", "initViews", "onDetachedFromWindow", "start", "stop", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MiniVisualizer.kt */
public final class MiniVisualizer extends LinearLayout {
    private AnimatorSet animator;
    private int mainColor = ViewCompat.MEASURED_STATE_MASK;
    private AnimatorSet stopSet;
    private View visualizer1;
    private View visualizer2;
    private View visualizer3;
    private View[] visualizers;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MiniVisualizer(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initViews();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MiniVisualizer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, 0);
        initViews();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MiniVisualizer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, i);
        initViews();
    }

    private final void initAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.MiniVisualizer, 0, i);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            this.mainColor = obtainStyledAttributes.getInt(R.styleable.MiniVisualizer_bar_color, R.color.black);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            Integer.valueOf(Log.w("", e.getMessage(), e));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
    }

    private final void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mini_visualizer, this, true);
        View findViewById = findViewById(R.id.visualizer1);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.visualizer1 = findViewById;
        View findViewById2 = findViewById(R.id.visualizer2);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.visualizer2 = findViewById2;
        View findViewById3 = findViewById(R.id.visualizer3);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.visualizer3 = findViewById3;
        View view = this.visualizer1;
        View view2 = null;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualizer1");
            view = null;
        }
        View view3 = this.visualizer2;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualizer2");
            view3 = null;
        }
        View view4 = this.visualizer3;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("visualizer3");
        } else {
            view2 = view4;
        }
        View[] viewArr = {view, view3, view2};
        this.visualizers = viewArr;
        for (View view5 : viewArr) {
            view5.setBackgroundColor(this.mainColor);
            view5.setScaleY(0.1f);
            view5.getViewTreeObserver().addOnGlobalLayoutListener(new MiniVisualizer$initViews$1$1(view5));
        }
    }

    public final void start() {
        AnimatorSet animatorSet = null;
        if (this.animator == null) {
            View view = this.visualizer1;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("visualizer1");
                view = null;
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.7f, 0.3f, 0.9f, 0.7f, 0.7f, 0.7f, 0.4f, 0.6f, 0.8f, 0.6f, 0.3f, 0.2f, 0.1f, 0.9f, 0.1f, 0.5f, 0.2f, 0.3f, 0.1f, 0.7f, 0.6f, 0.5f, 0.8f, 0.3f, 0.8f, 0.1f});
            ofFloat.setRepeatCount(-1);
            View view2 = this.visualizer2;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("visualizer2");
                view2 = null;
            }
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "scaleY", new float[]{0.2f, 0.8f, 0.7f, 0.8f, 0.8f, 0.3f, 0.5f, 0.4f, 0.8f, 0.3f, 0.7f, 0.9f, 0.5f, 0.8f, 0.1f, 0.3f, 0.2f, 0.5f, 0.2f, 0.7f, 0.3f, 0.4f, 0.1f, 0.5f, 0.7f, 0.2f});
            ofFloat2.setRepeatCount(-1);
            View view3 = this.visualizer3;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("visualizer3");
                view3 = null;
            }
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view3, "scaleY", new float[]{0.3f, 0.1f, 0.3f, 0.3f, 0.3f, 0.7f, 0.7f, 0.9f, 0.3f, 0.7f, 0.0f, 0.9f, 0.3f, 0.2f, 0.4f, 0.8f, 0.5f, 1.0f, 0.2f, 0.4f, 1.0f, 0.3f, 0.2f, 0.5f, 0.7f, 0.5f});
            ofFloat3.setRepeatCount(-1);
            AnimatorSet animatorSet2 = new AnimatorSet();
            this.animator = animatorSet2;
            animatorSet2.playTogether(new Animator[]{ofFloat, ofFloat2, ofFloat3});
            AnimatorSet animatorSet3 = this.animator;
            if (animatorSet3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animator");
                animatorSet3 = null;
            }
            animatorSet3.setDuration(3000);
            AnimatorSet animatorSet4 = this.animator;
            if (animatorSet4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animator");
                animatorSet4 = null;
            }
            animatorSet4.setInterpolator(new LinearInterpolator());
            AnimatorSet animatorSet5 = this.animator;
            if (animatorSet5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animator");
            } else {
                animatorSet = animatorSet5;
            }
            animatorSet.start();
        } else if (Build.VERSION.SDK_INT < 19) {
            AnimatorSet animatorSet6 = this.animator;
            if (animatorSet6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animator");
                animatorSet6 = null;
            }
            if (!animatorSet6.isStarted()) {
                AnimatorSet animatorSet7 = this.animator;
                if (animatorSet7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("animator");
                } else {
                    animatorSet = animatorSet7;
                }
                animatorSet.start();
            }
        } else {
            AnimatorSet animatorSet8 = this.animator;
            if (animatorSet8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animator");
                animatorSet8 = null;
            }
            if (animatorSet8.isPaused()) {
                AnimatorSet animatorSet9 = this.animator;
                if (animatorSet9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("animator");
                } else {
                    animatorSet = animatorSet9;
                }
                animatorSet.resume();
            }
        }
    }

    public final void stop() {
        AnimatorSet animatorSet = this.animator;
        AnimatorSet animatorSet2 = null;
        if (animatorSet != null) {
            if (animatorSet == null) {
                Intrinsics.throwUninitializedPropertyAccessException("animator");
                animatorSet = null;
            }
            if (animatorSet.isRunning()) {
                AnimatorSet animatorSet3 = this.animator;
                if (animatorSet3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("animator");
                    animatorSet3 = null;
                }
                if (animatorSet3.isStarted()) {
                    if (Build.VERSION.SDK_INT < 19) {
                        AnimatorSet animatorSet4 = this.animator;
                        if (animatorSet4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("animator");
                            animatorSet4 = null;
                        }
                        animatorSet4.end();
                    } else {
                        AnimatorSet animatorSet5 = this.animator;
                        if (animatorSet5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("animator");
                            animatorSet5 = null;
                        }
                        Chip$$ExternalSyntheticApiModelOutline1.m(animatorSet5);
                    }
                }
            }
        }
        AnimatorSet animatorSet6 = this.stopSet;
        if (animatorSet6 == null) {
            View view = this.visualizer1;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("visualizer1");
                view = null;
            }
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleY", new float[]{0.1f});
            View view2 = this.visualizer2;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("visualizer2");
                view2 = null;
            }
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view2, "scaleY", new float[]{0.1f});
            View view3 = this.visualizer3;
            if (view3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("visualizer3");
                view3 = null;
            }
            ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view3, "scaleY", new float[]{0.1f});
            AnimatorSet animatorSet7 = new AnimatorSet();
            this.stopSet = animatorSet7;
            animatorSet7.playTogether(new Animator[]{ofFloat3, ofFloat2, ofFloat});
            AnimatorSet animatorSet8 = this.stopSet;
            if (animatorSet8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stopSet");
                animatorSet8 = null;
            }
            animatorSet8.setDuration(400);
            AnimatorSet animatorSet9 = this.stopSet;
            if (animatorSet9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stopSet");
            } else {
                animatorSet2 = animatorSet9;
            }
            animatorSet2.start();
            return;
        }
        if (animatorSet6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stopSet");
            animatorSet6 = null;
        }
        if (!animatorSet6.isStarted()) {
            AnimatorSet animatorSet10 = this.stopSet;
            if (animatorSet10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stopSet");
            } else {
                animatorSet2 = animatorSet10;
            }
            animatorSet2.start();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }
}
