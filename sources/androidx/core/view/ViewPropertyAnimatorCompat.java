package androidx.core.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.os.Build;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import java.lang.ref.WeakReference;

public final class ViewPropertyAnimatorCompat {
    static final int LISTENER_TAG_ID = 2113929216;
    Runnable mEndAction = null;
    int mOldLayerType = -1;
    Runnable mStartAction = null;
    private final WeakReference<View> mView;

    ViewPropertyAnimatorCompat(View view) {
        this.mView = new WeakReference<>(view);
    }

    static class ViewPropertyAnimatorListenerApi14 implements ViewPropertyAnimatorListener {
        boolean mAnimEndCalled;
        ViewPropertyAnimatorCompat mVpa;

        ViewPropertyAnimatorListenerApi14(ViewPropertyAnimatorCompat viewPropertyAnimatorCompat) {
            this.mVpa = viewPropertyAnimatorCompat;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v6, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: androidx.core.view.ViewPropertyAnimatorListener} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onAnimationStart(android.view.View r4) {
            /*
                r3 = this;
                r0 = 0
                r3.mAnimEndCalled = r0
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                int r0 = r0.mOldLayerType
                r1 = -1
                r2 = 0
                if (r0 <= r1) goto L_0x000f
                r0 = 2
                r4.setLayerType(r0, r2)
            L_0x000f:
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                java.lang.Runnable r0 = r0.mStartAction
                if (r0 == 0) goto L_0x0020
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                java.lang.Runnable r0 = r0.mStartAction
                androidx.core.view.ViewPropertyAnimatorCompat r1 = r3.mVpa
                r1.mStartAction = r2
                r0.run()
            L_0x0020:
                r0 = 2113929216(0x7e000000, float:4.2535296E37)
                java.lang.Object r0 = r4.getTag(r0)
                boolean r1 = r0 instanceof androidx.core.view.ViewPropertyAnimatorListener
                if (r1 == 0) goto L_0x002d
                r2 = r0
                androidx.core.view.ViewPropertyAnimatorListener r2 = (androidx.core.view.ViewPropertyAnimatorListener) r2
            L_0x002d:
                if (r2 == 0) goto L_0x0032
                r2.onAnimationStart(r4)
            L_0x0032:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.ViewPropertyAnimatorCompat.ViewPropertyAnimatorListenerApi14.onAnimationStart(android.view.View):void");
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: androidx.core.view.ViewPropertyAnimatorListener} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onAnimationEnd(android.view.View r4) {
            /*
                r3 = this;
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                int r0 = r0.mOldLayerType
                r1 = 0
                r2 = -1
                if (r0 <= r2) goto L_0x0013
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                int r0 = r0.mOldLayerType
                r4.setLayerType(r0, r1)
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                r0.mOldLayerType = r2
            L_0x0013:
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                java.lang.Runnable r0 = r0.mEndAction
                if (r0 == 0) goto L_0x0024
                androidx.core.view.ViewPropertyAnimatorCompat r0 = r3.mVpa
                java.lang.Runnable r0 = r0.mEndAction
                androidx.core.view.ViewPropertyAnimatorCompat r2 = r3.mVpa
                r2.mEndAction = r1
                r0.run()
            L_0x0024:
                r0 = 2113929216(0x7e000000, float:4.2535296E37)
                java.lang.Object r0 = r4.getTag(r0)
                boolean r2 = r0 instanceof androidx.core.view.ViewPropertyAnimatorListener
                if (r2 == 0) goto L_0x0031
                r1 = r0
                androidx.core.view.ViewPropertyAnimatorListener r1 = (androidx.core.view.ViewPropertyAnimatorListener) r1
            L_0x0031:
                if (r1 == 0) goto L_0x0036
                r1.onAnimationEnd(r4)
            L_0x0036:
                r4 = 1
                r3.mAnimEndCalled = r4
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.core.view.ViewPropertyAnimatorCompat.ViewPropertyAnimatorListenerApi14.onAnimationEnd(android.view.View):void");
        }

        public void onAnimationCancel(View view) {
            Object tag = view.getTag(ViewPropertyAnimatorCompat.LISTENER_TAG_ID);
            ViewPropertyAnimatorListener viewPropertyAnimatorListener = tag instanceof ViewPropertyAnimatorListener ? (ViewPropertyAnimatorListener) tag : null;
            if (viewPropertyAnimatorListener != null) {
                viewPropertyAnimatorListener.onAnimationCancel(view);
            }
        }
    }

    public ViewPropertyAnimatorCompat setDuration(long j) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().setDuration(j);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alpha(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().alpha(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat alphaBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().alphaBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationX(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().translationX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationY(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().translationY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withEndAction(Runnable runnable) {
        View view = (View) this.mView.get();
        if (view != null) {
            Api16Impl.withEndAction(view.animate(), runnable);
        }
        return this;
    }

    public long getDuration() {
        View view = (View) this.mView.get();
        if (view != null) {
            return view.animate().getDuration();
        }
        return 0;
    }

    public ViewPropertyAnimatorCompat setInterpolator(Interpolator interpolator) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().setInterpolator(interpolator);
        }
        return this;
    }

    public Interpolator getInterpolator() {
        View view = (View) this.mView.get();
        if (view == null || Build.VERSION.SDK_INT < 18) {
            return null;
        }
        return Api18Impl.getInterpolator(view.animate());
    }

    public ViewPropertyAnimatorCompat setStartDelay(long j) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().setStartDelay(j);
        }
        return this;
    }

    public long getStartDelay() {
        View view = (View) this.mView.get();
        if (view != null) {
            return view.animate().getStartDelay();
        }
        return 0;
    }

    public ViewPropertyAnimatorCompat rotation(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().rotation(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().rotationBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationX(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().rotationX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationXBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().rotationXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationY(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().rotationY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat rotationYBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().rotationYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleX(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().scaleX(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleXBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().scaleXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleY(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().scaleY(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat scaleYBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().scaleYBy(f);
        }
        return this;
    }

    public void cancel() {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().cancel();
        }
    }

    public ViewPropertyAnimatorCompat x(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().x(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat xBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().xBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat y(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().y(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat yBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().yBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationXBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().translationXBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationYBy(float f) {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().translationYBy(f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZBy(float f) {
        View view = (View) this.mView.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            Api21Impl.translationZBy(view.animate(), f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat translationZ(float f) {
        View view = (View) this.mView.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            Api21Impl.translationZ(view.animate(), f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat z(float f) {
        View view = (View) this.mView.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            Api21Impl.z(view.animate(), f);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat zBy(float f) {
        View view = (View) this.mView.get();
        if (view != null && Build.VERSION.SDK_INT >= 21) {
            Api21Impl.zBy(view.animate(), f);
        }
        return this;
    }

    public void start() {
        View view = (View) this.mView.get();
        if (view != null) {
            view.animate().start();
        }
    }

    public ViewPropertyAnimatorCompat withLayer() {
        View view = (View) this.mView.get();
        if (view != null) {
            Api16Impl.withLayer(view.animate());
        }
        return this;
    }

    public ViewPropertyAnimatorCompat withStartAction(Runnable runnable) {
        View view = (View) this.mView.get();
        if (view != null) {
            Api16Impl.withStartAction(view.animate(), runnable);
        }
        return this;
    }

    public ViewPropertyAnimatorCompat setListener(ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        View view = (View) this.mView.get();
        if (view != null) {
            setListenerInternal(view, viewPropertyAnimatorListener);
        }
        return this;
    }

    private void setListenerInternal(final View view, final ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new AnimatorListenerAdapter() {
                public void onAnimationCancel(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationCancel(view);
                }

                public void onAnimationEnd(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationEnd(view);
                }

                public void onAnimationStart(Animator animator) {
                    viewPropertyAnimatorListener.onAnimationStart(view);
                }
            });
        } else {
            view.animate().setListener((Animator.AnimatorListener) null);
        }
    }

    public ViewPropertyAnimatorCompat setUpdateListener(ViewPropertyAnimatorUpdateListener viewPropertyAnimatorUpdateListener) {
        View view = (View) this.mView.get();
        if (view != null && Build.VERSION.SDK_INT >= 19) {
            Api19Impl.setUpdateListener(view.animate(), viewPropertyAnimatorUpdateListener != null ? new ViewPropertyAnimatorCompat$$ExternalSyntheticLambda0(viewPropertyAnimatorUpdateListener, view) : null);
        }
        return this;
    }

    static class Api16Impl {
        private Api16Impl() {
        }

        static ViewPropertyAnimator withEndAction(ViewPropertyAnimator viewPropertyAnimator, Runnable runnable) {
            return viewPropertyAnimator.withEndAction(runnable);
        }

        static ViewPropertyAnimator withLayer(ViewPropertyAnimator viewPropertyAnimator) {
            return viewPropertyAnimator.withLayer();
        }

        static ViewPropertyAnimator withStartAction(ViewPropertyAnimator viewPropertyAnimator, Runnable runnable) {
            return viewPropertyAnimator.withStartAction(runnable);
        }
    }

    static class Api18Impl {
        private Api18Impl() {
        }

        static Interpolator getInterpolator(ViewPropertyAnimator viewPropertyAnimator) {
            return (Interpolator) viewPropertyAnimator.getInterpolator();
        }
    }

    static class Api21Impl {
        private Api21Impl() {
        }

        static ViewPropertyAnimator translationZBy(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.translationZBy(f);
        }

        static ViewPropertyAnimator translationZ(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.translationZ(f);
        }

        static ViewPropertyAnimator z(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.z(f);
        }

        static ViewPropertyAnimator zBy(ViewPropertyAnimator viewPropertyAnimator, float f) {
            return viewPropertyAnimator.zBy(f);
        }
    }

    static class Api19Impl {
        private Api19Impl() {
        }

        static ViewPropertyAnimator setUpdateListener(ViewPropertyAnimator viewPropertyAnimator, ValueAnimator.AnimatorUpdateListener animatorUpdateListener) {
            return viewPropertyAnimator.setUpdateListener(animatorUpdateListener);
        }
    }
}
