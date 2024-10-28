package androidx.leanback.transition;

import android.animation.TimeInterpolator;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.transition.AutoTransition;
import android.transition.ChangeTransform;
import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import androidx.car.app.CarContext$$ExternalSyntheticApiModelOutline0;
import androidx.core.os.BundleKt$$ExternalSyntheticApiModelOutline0;
import androidx.leanback.app.FragmentUtil$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;

public final class TransitionHelper {
    public static final int FADE_IN = 1;
    public static final int FADE_OUT = 2;

    public static boolean systemSupportsEntranceTransitions() {
        return Build.VERSION.SDK_INT >= 21;
    }

    private static class TransitionStub {
        ArrayList<TransitionListener> mTransitionListeners;

        TransitionStub() {
        }
    }

    public static Object getSharedElementEnterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementEnterTransition();
        }
        return null;
    }

    public static void setSharedElementEnterTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setSharedElementEnterTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static Object getSharedElementReturnTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementReturnTransition();
        }
        return null;
    }

    public static void setSharedElementReturnTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setSharedElementReturnTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static Object getSharedElementExitTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementExitTransition();
        }
        return null;
    }

    public static Object getSharedElementReenterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getSharedElementReenterTransition();
        }
        return null;
    }

    public static Object getEnterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getEnterTransition();
        }
        return null;
    }

    public static void setEnterTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setEnterTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static Object getReturnTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getReturnTransition();
        }
        return null;
    }

    public static void setReturnTransition(Window window, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            window.setReturnTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static Object getExitTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getExitTransition();
        }
        return null;
    }

    public static Object getReenterTransition(Window window) {
        if (Build.VERSION.SDK_INT >= 21) {
            return window.getReenterTransition();
        }
        return null;
    }

    public static Object createScene(ViewGroup viewGroup, Runnable runnable) {
        if (Build.VERSION.SDK_INT < 19) {
            return runnable;
        }
        Scene scene = new Scene(viewGroup);
        scene.setEnterAction(runnable);
        return scene;
    }

    public static Object createChangeBounds(boolean z) {
        if (Build.VERSION.SDK_INT < 19) {
            return new TransitionStub();
        }
        CustomChangeBounds customChangeBounds = new CustomChangeBounds();
        customChangeBounds.setReparent(z);
        return customChangeBounds;
    }

    public static Object createChangeTransform() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ChangeTransform();
        }
        return new TransitionStub();
    }

    public static void setChangeBoundsStartDelay(Object obj, View view, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setStartDelay(view, i);
        }
    }

    public static void setChangeBoundsStartDelay(Object obj, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setStartDelay(i, i2);
        }
    }

    public static void setChangeBoundsStartDelay(Object obj, String str, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setStartDelay(str, i);
        }
    }

    public static void setChangeBoundsDefaultStartDelay(Object obj, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            ((CustomChangeBounds) obj).setDefaultStartDelay(i);
        }
    }

    public static Object createTransitionSet(boolean z) {
        if (Build.VERSION.SDK_INT < 19) {
            return new TransitionStub();
        }
        TransitionSet transitionSet = new TransitionSet();
        FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transitionSet, z ? 1 : 0);
        return transitionSet;
    }

    public static Object createSlide(int i) {
        if (Build.VERSION.SDK_INT < 19) {
            return new TransitionStub();
        }
        SlideKitkat slideKitkat = new SlideKitkat();
        slideKitkat.setSlideEdge(i);
        return slideKitkat;
    }

    public static Object createScale() {
        if (Build.VERSION.SDK_INT >= 21) {
            return new ChangeTransform();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            return new Scale();
        }
        return new TransitionStub();
    }

    public static void addTransition(Object obj, Object obj2) {
        if (Build.VERSION.SDK_INT >= 19) {
            TransitionSet unused = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(obj).addTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj2));
        }
    }

    public static void exclude(Object obj, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).excludeTarget(i, z);
        }
    }

    public static void exclude(Object obj, View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).excludeTarget(view, z);
        }
    }

    public static void excludeChildren(Object obj, int i, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).excludeChildren(i, z);
        }
    }

    public static void excludeChildren(Object obj, View view, boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).excludeChildren(view, z);
        }
    }

    public static void include(Object obj, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).addTarget(i);
        }
    }

    public static void include(Object obj, View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).addTarget(view);
        }
    }

    public static void setStartDelay(Object obj, long j) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).setStartDelay(j);
        }
    }

    public static void setDuration(Object obj, long j) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).setDuration(j);
        }
    }

    public static Object createAutoTransition() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new AutoTransition();
        }
        return new TransitionStub();
    }

    public static Object createFadeTransition(int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            return new Fade(i);
        }
        return new TransitionStub();
    }

    public static void addTransitionListener(Object obj, final TransitionListener transitionListener) {
        if (transitionListener != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                Transition m = CarContext$$ExternalSyntheticApiModelOutline0.m(obj);
                transitionListener.mImpl = new Transition.TransitionListener() {
                    public void onTransitionStart(Transition transition) {
                        transitionListener.onTransitionStart(transition);
                    }

                    public void onTransitionResume(Transition transition) {
                        transitionListener.onTransitionResume(transition);
                    }

                    public void onTransitionPause(Transition transition) {
                        transitionListener.onTransitionPause(transition);
                    }

                    public void onTransitionEnd(Transition transition) {
                        transitionListener.onTransitionEnd(transition);
                    }

                    public void onTransitionCancel(Transition transition) {
                        transitionListener.onTransitionCancel(transition);
                    }
                };
                Transition unused = m.addListener(BundleKt$$ExternalSyntheticApiModelOutline0.m(transitionListener.mImpl));
                return;
            }
            TransitionStub transitionStub = (TransitionStub) obj;
            if (transitionStub.mTransitionListeners == null) {
                transitionStub.mTransitionListeners = new ArrayList<>();
            }
            transitionStub.mTransitionListeners.add(transitionListener);
        }
    }

    public static void removeTransitionListener(Object obj, TransitionListener transitionListener) {
        if (Build.VERSION.SDK_INT < 19) {
            TransitionStub transitionStub = (TransitionStub) obj;
            if (transitionStub.mTransitionListeners != null) {
                transitionStub.mTransitionListeners.remove(transitionListener);
            }
        } else if (transitionListener != null && transitionListener.mImpl != null) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).removeListener(BundleKt$$ExternalSyntheticApiModelOutline0.m(transitionListener.mImpl));
            transitionListener.mImpl = null;
        }
    }

    public static void runTransition(Object obj, Object obj2) {
        if (Build.VERSION.SDK_INT >= 19) {
            TransitionManager.go(FragmentUtil$$ExternalSyntheticApiModelOutline0.m(obj), CarContext$$ExternalSyntheticApiModelOutline0.m(obj2));
            return;
        }
        TransitionStub transitionStub = (TransitionStub) obj2;
        if (!(transitionStub == null || transitionStub.mTransitionListeners == null)) {
            int size = transitionStub.mTransitionListeners.size();
            for (int i = 0; i < size; i++) {
                transitionStub.mTransitionListeners.get(i).onTransitionStart(obj2);
            }
        }
        Runnable runnable = (Runnable) obj;
        if (runnable != null) {
            runnable.run();
        }
        if (transitionStub != null && transitionStub.mTransitionListeners != null) {
            int size2 = transitionStub.mTransitionListeners.size();
            for (int i2 = 0; i2 < size2; i2++) {
                transitionStub.mTransitionListeners.get(i2).onTransitionEnd(obj2);
            }
        }
    }

    public static void setInterpolator(Object obj, Object obj2) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).setInterpolator((TimeInterpolator) obj2);
        }
    }

    public static void addTarget(Object obj, View view) {
        if (Build.VERSION.SDK_INT >= 19) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).addTarget(view);
        }
    }

    public static Object createDefaultInterpolator(Context context) {
        if (Build.VERSION.SDK_INT >= 21) {
            return AnimationUtils.loadInterpolator(context, AndroidResources.FAST_OUT_LINEAR_IN);
        }
        return null;
    }

    public static Object loadTransition(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 19) {
            return TransitionInflater.from(context).inflateTransition(i);
        }
        return new TransitionStub();
    }

    public static void setEnterTransition(Fragment fragment, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            fragment.setEnterTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static void setExitTransition(Fragment fragment, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            fragment.setExitTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static void setSharedElementEnterTransition(Fragment fragment, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            fragment.setSharedElementEnterTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static void addSharedElement(FragmentTransaction fragmentTransaction, View view, String str) {
        if (Build.VERSION.SDK_INT >= 21) {
            FragmentTransaction unused = fragmentTransaction.addSharedElement(view, str);
        }
    }

    public static Object createFadeAndShortSlide(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return new FadeAndShortSlide(i);
        }
        return new TransitionStub();
    }

    public static Object createFadeAndShortSlide(int i, float f) {
        if (Build.VERSION.SDK_INT < 21) {
            return new TransitionStub();
        }
        FadeAndShortSlide fadeAndShortSlide = new FadeAndShortSlide(i);
        fadeAndShortSlide.setDistance(f);
        return fadeAndShortSlide;
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        if (Build.VERSION.SDK_INT >= 21) {
            TransitionManager.beginDelayedTransition(viewGroup, CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
    }

    public static void setTransitionGroup(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 21) {
            viewGroup.setTransitionGroup(z);
        }
    }

    public static void setEpicenterCallback(Object obj, final TransitionEpicenterCallback transitionEpicenterCallback) {
        if (Build.VERSION.SDK_INT < 21) {
            return;
        }
        if (transitionEpicenterCallback == null) {
            CarContext$$ExternalSyntheticApiModelOutline0.m(obj).setEpicenterCallback((Transition.EpicenterCallback) null);
        } else {
            CarContext$$ExternalSyntheticApiModelOutline0.m(obj).setEpicenterCallback(new Transition.EpicenterCallback() {
                public Rect onGetEpicenter(Transition transition) {
                    return transitionEpicenterCallback.onGetEpicenter(transition);
                }
            });
        }
    }

    private TransitionHelper() {
    }
}
