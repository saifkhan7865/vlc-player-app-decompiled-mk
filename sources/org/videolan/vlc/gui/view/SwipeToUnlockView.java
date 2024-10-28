package org.videolan.vlc.gui.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.text.SpannableString;
import android.text.style.MaskFilterSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\tH\u0002J\b\u0010'\u001a\u00020\u001aH\u0002J\u001a\u0010(\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u001a\u0010,\u001a\u00020\u00112\u0006\u0010)\u001a\u00020\t2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\u0012\u0010-\u001a\u00020\u00112\b\u0010*\u001a\u0004\u0018\u00010.H\u0016J\u0010\u0010/\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\tH\u0002J\u0014\u00100\u001a\u00020\u001a2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019J\u0014\u00102\u001a\u00020\u001a2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019J\u0014\u00103\u001a\u00020\u001a2\f\u00101\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019J\u0010\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\tH\u0016J\b\u00106\u001a\u00020\u001aH\u0002J\b\u00107\u001a\u00020\u001aH\u0002R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R$\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X.¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X.¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X.¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X.¢\u0006\u0002\n\u0000R\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\t0\"X\u0004¢\u0006\u0004\n\u0002\u0010#R\u000e\u0010$\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lorg/videolan/vlc/gui/view/SwipeToUnlockView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "currentText", "", "extremum", "guideline", "Landroidx/constraintlayout/widget/Guideline;", "value", "", "isDPADAllowed", "()Z", "setDPADAllowed", "(Z)V", "keyAnimation", "Landroid/animation/ValueAnimator;", "onStartTouching", "Lkotlin/Function0;", "", "onStopTouching", "onUnlock", "swipeIcon", "Landroid/widget/ImageView;", "swipeText", "Landroid/widget/TextView;", "tvAcceptedKeys", "", "[Ljava/lang/Integer;", "unlocking", "animateBack", "currentX", "initialize", "onKeyDown", "keyCode", "event", "Landroid/view/KeyEvent;", "onKeyUp", "onTouchEvent", "Landroid/view/MotionEvent;", "playStep", "setOnStartTouchingListener", "listener", "setOnStopTouchingListener", "setOnUnlockListener", "setVisibility", "visibility", "unlock", "updateText", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SwipeToUnlockView.kt */
public final class SwipeToUnlockView extends ConstraintLayout {
    private String currentText;
    private int extremum;
    private Guideline guideline;
    private boolean isDPADAllowed = true;
    private ValueAnimator keyAnimation;
    private Function0<Unit> onStartTouching;
    private Function0<Unit> onStopTouching;
    private Function0<Unit> onUnlock;
    private ImageView swipeIcon;
    private TextView swipeText;
    private final Integer[] tvAcceptedKeys = {23, 20, 21, 22, 19, 66, 160};
    private boolean unlocking;

    public final boolean isDPADAllowed() {
        return this.isDPADAllowed;
    }

    public final void setDPADAllowed(boolean z) {
        this.isDPADAllowed = z;
        updateText();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeToUnlockView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeToUnlockView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeToUnlockView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    public final void setOnStartTouchingListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.onStartTouching = function0;
    }

    public final void setOnStopTouchingListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.onStopTouching = function0;
    }

    public final void setOnUnlockListener(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "listener");
        this.onUnlock = function0;
    }

    public void setVisibility(int i) {
        if (i == 0) {
            this.unlocking = false;
            int i2 = this.extremum;
            if (i2 != 0) {
                playStep(i2);
            }
            requestFocus();
        }
        super.setVisibility(i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.unlocking) {
            return super.onTouchEvent(motionEvent);
        }
        if (motionEvent == null) {
            return super.onTouchEvent(motionEvent);
        }
        int coerceAtMost = RangesKt.coerceAtMost(RangesKt.coerceAtLeast((int) motionEvent.getX(), this.extremum), getWidth() - this.extremum);
        if (getLayoutDirection() == 1) {
            coerceAtMost = getWidth() - coerceAtMost;
        }
        int action = motionEvent.getAction();
        Function0<Unit> function0 = null;
        if (action == 0) {
            Function0<Unit> function02 = this.onStartTouching;
            if (function02 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onStartTouching");
            } else {
                function0 = function02;
            }
            function0.invoke();
            return true;
        } else if (action == 1) {
            animateBack(coerceAtMost);
            Function0<Unit> function03 = this.onStopTouching;
            if (function03 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onStopTouching");
            } else {
                function0 = function03;
            }
            function0.invoke();
            return true;
        } else if (action != 2) {
            return true;
        } else {
            if (coerceAtMost >= getWidth() - this.extremum) {
                unlock();
            }
            playStep(coerceAtMost);
            return true;
        }
    }

    private final void animateBack(int i) {
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{i, this.extremum});
        ofInt.setDuration(250);
        ofInt.addUpdateListener(new SwipeToUnlockView$$ExternalSyntheticLambda1(this));
        ofInt.start();
        TextView textView = this.swipeText;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeText");
            textView = null;
        }
        textView.setAlpha(1.0f);
    }

    /* access modifiers changed from: private */
    public static final void animateBack$lambda$2(SwipeToUnlockView swipeToUnlockView, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(swipeToUnlockView, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        swipeToUnlockView.playStep(((Integer) animatedValue).intValue());
    }

    private final void playStep(int i) {
        Guideline guideline2 = this.guideline;
        TextView textView = null;
        if (guideline2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("guideline");
            guideline2 = null;
        }
        guideline2.setGuidelineBegin(i);
        float width = (((float) i) - ((float) this.extremum)) / ((float) (getWidth() - this.extremum));
        TextView textView2 = this.swipeText;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeText");
            textView2 = null;
        }
        textView2.setAlpha(1.0f - width);
        String str = this.currentText;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentText");
            str = null;
        }
        SpannableString spannableString = new SpannableString(str);
        if (width > 0.0f) {
            spannableString.setSpan(new MaskFilterSpan(new BlurMaskFilter(width * ((float) 10), BlurMaskFilter.Blur.NORMAL)), 0, spannableString.length(), 33);
        }
        TextView textView3 = this.swipeText;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeText");
        } else {
            textView = textView3;
        }
        textView.setText(spannableString);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.isDPADAllowed) {
            ValueAnimator valueAnimator = null;
            if (ArraysKt.contains((T[]) this.tvAcceptedKeys, keyEvent != null ? Integer.valueOf(keyEvent.getKeyCode()) : null) && !this.unlocking) {
                Function0<Unit> function0 = this.onStartTouching;
                if (function0 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onStartTouching");
                    function0 = null;
                }
                function0.invoke();
                ValueAnimator valueAnimator2 = this.keyAnimation;
                if (valueAnimator2 != null) {
                    if (valueAnimator2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
                        valueAnimator2 = null;
                    }
                    if (valueAnimator2.isRunning()) {
                        return true;
                    }
                }
                ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{this.extremum, getWidth() - this.extremum});
                Intrinsics.checkNotNullExpressionValue(ofInt, "ofInt(...)");
                this.keyAnimation = ofInt;
                if (ofInt == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
                    ofInt = null;
                }
                ofInt.setInterpolator(new AccelerateInterpolator());
                ValueAnimator valueAnimator3 = this.keyAnimation;
                if (valueAnimator3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
                    valueAnimator3 = null;
                }
                valueAnimator3.setDuration(2000);
                ValueAnimator valueAnimator4 = this.keyAnimation;
                if (valueAnimator4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
                    valueAnimator4 = null;
                }
                valueAnimator4.addUpdateListener(new SwipeToUnlockView$$ExternalSyntheticLambda0(this));
                ValueAnimator valueAnimator5 = this.keyAnimation;
                if (valueAnimator5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
                } else {
                    valueAnimator = valueAnimator5;
                }
                valueAnimator.start();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* access modifiers changed from: private */
    public static final void onKeyDown$lambda$4(SwipeToUnlockView swipeToUnlockView, ValueAnimator valueAnimator) {
        Intrinsics.checkNotNullParameter(swipeToUnlockView, "this$0");
        Intrinsics.checkNotNullParameter(valueAnimator, "animator");
        Object animatedValue = valueAnimator.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        swipeToUnlockView.playStep(((Integer) animatedValue).intValue());
        if (Intrinsics.areEqual(valueAnimator.getAnimatedValue(), (Object) Integer.valueOf(swipeToUnlockView.getWidth() - swipeToUnlockView.extremum))) {
            swipeToUnlockView.unlock();
        }
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        ValueAnimator valueAnimator = null;
        if (!ArraysKt.contains((T[]) this.tvAcceptedKeys, keyEvent != null ? Integer.valueOf(keyEvent.getKeyCode()) : null)) {
            return super.onKeyUp(i, keyEvent);
        }
        Function0<Unit> function0 = this.onStopTouching;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onStopTouching");
            function0 = null;
        }
        function0.invoke();
        ValueAnimator valueAnimator2 = this.keyAnimation;
        if (valueAnimator2 == null) {
            return true;
        }
        if (valueAnimator2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
            valueAnimator2 = null;
        }
        if (!valueAnimator2.isRunning()) {
            return true;
        }
        ValueAnimator valueAnimator3 = this.keyAnimation;
        if (valueAnimator3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
            valueAnimator3 = null;
        }
        Object animatedValue = valueAnimator3.getAnimatedValue();
        Intrinsics.checkNotNull(animatedValue, "null cannot be cast to non-null type kotlin.Int");
        animateBack(((Integer) animatedValue).intValue());
        ValueAnimator valueAnimator4 = this.keyAnimation;
        if (valueAnimator4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
            valueAnimator4 = null;
        }
        valueAnimator4.removeAllUpdateListeners();
        ValueAnimator valueAnimator5 = this.keyAnimation;
        if (valueAnimator5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("keyAnimation");
        } else {
            valueAnimator = valueAnimator5;
        }
        valueAnimator.cancel();
        return true;
    }

    private final void unlock() {
        this.unlocking = true;
        Function0<Unit> function0 = this.onUnlock;
        Guideline guideline2 = null;
        if (function0 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onUnlock");
            function0 = null;
        }
        function0.invoke();
        KotlinExtensionsKt.setGone(this);
        Guideline guideline3 = this.guideline;
        if (guideline3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("guideline");
        } else {
            guideline2 = guideline3;
        }
        guideline2.setGuidelineBegin(this.extremum);
    }

    private final void initialize() {
        LayoutInflater.from(getContext()).inflate(R.layout.swipe_to_unlock, this, true);
        View findViewById = findViewById(R.id.swipe_guideline);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
        this.guideline = (Guideline) findViewById;
        View findViewById2 = findViewById(R.id.swipe_icon);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
        this.swipeIcon = (ImageView) findViewById2;
        View findViewById3 = findViewById(R.id.swipe_text);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
        this.swipeText = (TextView) findViewById3;
        ImageView imageView = this.swipeIcon;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeIcon");
            imageView = null;
        }
        imageView.addOnLayoutChangeListener(new SwipeToUnlockView$$ExternalSyntheticLambda2(this));
        setFocusable(true);
        updateText();
    }

    /* access modifiers changed from: private */
    public static final void initialize$lambda$5(SwipeToUnlockView swipeToUnlockView, View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Intrinsics.checkNotNullParameter(swipeToUnlockView, "this$0");
        swipeToUnlockView.extremum = (view.getWidth() / 2) + KotlinExtensionsKt.getDp(4);
    }

    private final void updateText() {
        int i;
        Context context;
        if (!this.isDPADAllowed || !AndroidDevices.INSTANCE.isTv()) {
            context = getContext();
            i = R.string.swipe_unlock;
        } else {
            context = getContext();
            i = R.string.swipe_unlock_no_touch;
        }
        String string = context.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.currentText = string;
        TextView textView = this.swipeText;
        String str = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("swipeText");
            textView = null;
        }
        String str2 = this.currentText;
        if (str2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentText");
        } else {
            str = str2;
        }
        textView.setText(str);
    }
}
