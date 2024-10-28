package androidx.leanback.transition;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.app.FragmentUtil$$ExternalSyntheticApiModelOutline0;

class Scale extends Transition {
    private static final String PROPNAME_SCALE = "android:leanback:scale";

    private void captureValues(TransitionValues transitionValues) {
        FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues).put(PROPNAME_SCALE, Float.valueOf(FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues).getScaleX()));
    }

    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        if (transitionValues == null || transitionValues2 == null) {
            return null;
        }
        float floatValue = ((Float) FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues).get(PROPNAME_SCALE)).floatValue();
        float floatValue2 = ((Float) FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues2).get(PROPNAME_SCALE)).floatValue();
        final View m = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues);
        m.setScaleX(floatValue);
        m.setScaleY(floatValue);
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{floatValue, floatValue2});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                m.setScaleX(floatValue);
                m.setScaleY(floatValue);
            }
        });
        return ofFloat;
    }
}
