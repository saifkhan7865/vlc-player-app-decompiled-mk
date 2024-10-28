package androidx.leanback.transition;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import androidx.core.view.GravityCompat;
import androidx.leanback.R;
import androidx.leanback.app.FragmentUtil$$ExternalSyntheticApiModelOutline0;

public class FadeAndShortSlide extends Visibility {
    private static final String PROPNAME_SCREEN_POSITION = "android:fadeAndShortSlideTransition:screenPosition";
    static final CalculateSlide sCalculateBottom = new CalculateSlide() {
        public float getGoneY(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
            return view.getTranslationY() + fadeAndShortSlide.getVerticalDistance(viewGroup);
        }
    };
    static final CalculateSlide sCalculateEnd = new CalculateSlide() {
        public float getGoneX(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
            if (viewGroup.getLayoutDirection() == 1) {
                return view.getTranslationX() - fadeAndShortSlide.getHorizontalDistance(viewGroup);
            }
            return view.getTranslationX() + fadeAndShortSlide.getHorizontalDistance(viewGroup);
        }
    };
    static final CalculateSlide sCalculateStart = new CalculateSlide() {
        public float getGoneX(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
            if (viewGroup.getLayoutDirection() == 1) {
                return view.getTranslationX() + fadeAndShortSlide.getHorizontalDistance(viewGroup);
            }
            return view.getTranslationX() - fadeAndShortSlide.getHorizontalDistance(viewGroup);
        }
    };
    static final CalculateSlide sCalculateStartEnd = new CalculateSlide() {
        public float getGoneX(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
            int i;
            int width = iArr[0] + (view.getWidth() / 2);
            viewGroup.getLocationOnScreen(iArr);
            Rect m = fadeAndShortSlide.getEpicenter();
            if (m == null) {
                i = iArr[0] + (viewGroup.getWidth() / 2);
            } else {
                i = m.centerX();
            }
            if (width < i) {
                return view.getTranslationX() - fadeAndShortSlide.getHorizontalDistance(viewGroup);
            }
            return view.getTranslationX() + fadeAndShortSlide.getHorizontalDistance(viewGroup);
        }
    };
    static final CalculateSlide sCalculateTop = new CalculateSlide() {
        public float getGoneY(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
            return view.getTranslationY() - fadeAndShortSlide.getVerticalDistance(viewGroup);
        }
    };
    private static final TimeInterpolator sDecelerate = new DecelerateInterpolator();
    private float mDistance;
    private Visibility mFade;
    private CalculateSlide mSlideCalculator;
    final CalculateSlide sCalculateTopBottom;

    private static abstract class CalculateSlide {
        CalculateSlide() {
        }

        /* access modifiers changed from: package-private */
        public float getGoneX(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
            return view.getTranslationX();
        }

        /* access modifiers changed from: package-private */
        public float getGoneY(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
            return view.getTranslationY();
        }
    }

    /* access modifiers changed from: package-private */
    public float getHorizontalDistance(ViewGroup viewGroup) {
        float f = this.mDistance;
        return f >= 0.0f ? f : (float) (viewGroup.getWidth() / 4);
    }

    /* access modifiers changed from: package-private */
    public float getVerticalDistance(ViewGroup viewGroup) {
        float f = this.mDistance;
        return f >= 0.0f ? f : (float) (viewGroup.getHeight() / 4);
    }

    public FadeAndShortSlide() {
        this(GravityCompat.START);
    }

    public FadeAndShortSlide(int i) {
        this.mFade = new Fade();
        this.mDistance = -1.0f;
        this.sCalculateTopBottom = new CalculateSlide() {
            public float getGoneY(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
                int i;
                int height = iArr[1] + (view.getHeight() / 2);
                viewGroup.getLocationOnScreen(iArr);
                Rect m = FadeAndShortSlide.this.getEpicenter();
                if (m == null) {
                    i = iArr[1] + (viewGroup.getHeight() / 2);
                } else {
                    i = m.centerY();
                }
                if (height < i) {
                    return view.getTranslationY() - fadeAndShortSlide.getVerticalDistance(viewGroup);
                }
                return view.getTranslationY() + fadeAndShortSlide.getVerticalDistance(viewGroup);
            }
        };
        setSlideEdge(i);
    }

    public FadeAndShortSlide(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mFade = new Fade();
        this.mDistance = -1.0f;
        this.sCalculateTopBottom = new CalculateSlide() {
            public float getGoneY(FadeAndShortSlide fadeAndShortSlide, ViewGroup viewGroup, View view, int[] iArr) {
                int i;
                int height = iArr[1] + (view.getHeight() / 2);
                viewGroup.getLocationOnScreen(iArr);
                Rect m = FadeAndShortSlide.this.getEpicenter();
                if (m == null) {
                    i = iArr[1] + (viewGroup.getHeight() / 2);
                } else {
                    i = m.centerY();
                }
                if (height < i) {
                    return view.getTranslationY() - fadeAndShortSlide.getVerticalDistance(viewGroup);
                }
                return view.getTranslationY() + fadeAndShortSlide.getVerticalDistance(viewGroup);
            }
        };
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.lbSlide);
        setSlideEdge(obtainStyledAttributes.getInt(R.styleable.lbSlide_lb_slideEdge, GravityCompat.START));
        obtainStyledAttributes.recycle();
    }

    public void setEpicenterCallback(Transition.EpicenterCallback epicenterCallback) {
        this.mFade.setEpicenterCallback(epicenterCallback);
        super.setEpicenterCallback(epicenterCallback);
    }

    private void captureValues(TransitionValues transitionValues) {
        int[] iArr = new int[2];
        FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues).getLocationOnScreen(iArr);
        FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues).put(PROPNAME_SCREEN_POSITION, iArr);
    }

    public void captureStartValues(TransitionValues transitionValues) {
        this.mFade.captureStartValues(transitionValues);
        super.captureStartValues(transitionValues);
        captureValues(transitionValues);
    }

    public void captureEndValues(TransitionValues transitionValues) {
        this.mFade.captureEndValues(transitionValues);
        super.captureEndValues(transitionValues);
        captureValues(transitionValues);
    }

    public void setSlideEdge(int i) {
        if (i == 48) {
            this.mSlideCalculator = sCalculateTop;
        } else if (i == 80) {
            this.mSlideCalculator = sCalculateBottom;
        } else if (i == 112) {
            this.mSlideCalculator = this.sCalculateTopBottom;
        } else if (i == 8388611) {
            this.mSlideCalculator = sCalculateStart;
        } else if (i == 8388613) {
            this.mSlideCalculator = sCalculateEnd;
        } else if (i == 8388615) {
            this.mSlideCalculator = sCalculateStartEnd;
        } else {
            throw new IllegalArgumentException("Invalid slide direction");
        }
    }

    public Animator onAppear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup viewGroup2 = viewGroup;
        ViewGroup viewGroup3 = view;
        TransitionValues transitionValues3 = transitionValues2;
        if (transitionValues3 == null || viewGroup2 == viewGroup3) {
            return null;
        }
        int[] iArr = (int[]) FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues2).get(PROPNAME_SCREEN_POSITION);
        int i = iArr[0];
        int i2 = iArr[1];
        float translationX = view.getTranslationX();
        View view2 = view;
        TransitionValues transitionValues4 = transitionValues2;
        Animator createAnimation = TranslationAnimationCreator.createAnimation(view2, transitionValues4, i, i2, this.mSlideCalculator.getGoneX(this, viewGroup, viewGroup3, iArr), this.mSlideCalculator.getGoneY(this, viewGroup, viewGroup3, iArr), translationX, view.getTranslationY(), sDecelerate, this);
        Animator m$1 = this.mFade.onAppear(viewGroup, viewGroup3, transitionValues, transitionValues3);
        if (createAnimation == null) {
            return m$1;
        }
        if (m$1 == null) {
            return createAnimation;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createAnimation).with(m$1);
        return animatorSet;
    }

    public Animator onDisappear(ViewGroup viewGroup, View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewGroup viewGroup2 = viewGroup;
        ViewGroup viewGroup3 = view;
        TransitionValues transitionValues3 = transitionValues;
        if (transitionValues3 == null || viewGroup2 == viewGroup3) {
            return null;
        }
        int[] iArr = (int[]) FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionValues).get(PROPNAME_SCREEN_POSITION);
        int i = iArr[0];
        int i2 = iArr[1];
        float translationX = view.getTranslationX();
        float goneX = this.mSlideCalculator.getGoneX(this, viewGroup, viewGroup3, iArr);
        Animator createAnimation = TranslationAnimationCreator.createAnimation(view, transitionValues, i, i2, translationX, view.getTranslationY(), goneX, this.mSlideCalculator.getGoneY(this, viewGroup, viewGroup3, iArr), sDecelerate, this);
        Animator m = this.mFade.onDisappear(viewGroup, viewGroup3, transitionValues3, transitionValues2);
        if (createAnimation == null) {
            return m;
        }
        if (m == null) {
            return createAnimation;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(createAnimation).with(m);
        return animatorSet;
    }

    public Transition addListener(Transition.TransitionListener transitionListener) {
        Transition unused = this.mFade.addListener(transitionListener);
        return super.addListener(transitionListener);
    }

    public Transition removeListener(Transition.TransitionListener transitionListener) {
        Transition unused = this.mFade.removeListener(transitionListener);
        return super.removeListener(transitionListener);
    }

    public float getDistance() {
        return this.mDistance;
    }

    public void setDistance(float f) {
        this.mDistance = f;
    }

    public Transition clone() {
        FadeAndShortSlide fadeAndShortSlide = (FadeAndShortSlide) super.clone();
        fadeAndShortSlide.mFade = FragmentUtil$$ExternalSyntheticApiModelOutline0.m((Object) this.mFade.clone());
        return fadeAndShortSlide;
    }
}
