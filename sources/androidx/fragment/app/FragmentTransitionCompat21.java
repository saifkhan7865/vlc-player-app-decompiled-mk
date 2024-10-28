package androidx.fragment.app;

import android.graphics.Rect;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.car.app.CarContext$$ExternalSyntheticApiModelOutline0;
import androidx.core.os.CancellationSignal;
import androidx.leanback.app.FragmentUtil$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;
import java.util.List;

class FragmentTransitionCompat21 extends FragmentTransitionImpl {
    FragmentTransitionCompat21() {
    }

    public boolean canHandle(Object obj) {
        return FragmentUtil$$ExternalSyntheticApiModelOutline0.m$1(obj);
    }

    public Object cloneTransition(Object obj) {
        if (obj != null) {
            return FragmentUtil$$ExternalSyntheticApiModelOutline0.m(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
        return null;
    }

    public Object wrapTransitionInSet(Object obj) {
        if (obj == null) {
            return null;
        }
        TransitionSet transitionSet = new TransitionSet();
        TransitionSet unused = transitionSet.addTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        return transitionSet;
    }

    public void setSharedElementTargets(Object obj, View view, ArrayList<View> arrayList) {
        TransitionSet m = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(obj);
        List m2 = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(m);
        m2.clear();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            bfsAddViewChildren(m2, arrayList.get(i));
        }
        m2.add(view);
        arrayList.add(view);
        addTargets(m, arrayList);
    }

    public void setEpicenter(Object obj, View view) {
        if (view != null) {
            Transition m = CarContext$$ExternalSyntheticApiModelOutline0.m(obj);
            final Rect rect = new Rect();
            getBoundsOnScreen(view, rect);
            m.setEpicenterCallback(new Transition.EpicenterCallback() {
                public Rect onGetEpicenter(Transition transition) {
                    return rect;
                }
            });
        }
    }

    public void addTargets(Object obj, ArrayList<View> arrayList) {
        Transition m = CarContext$$ExternalSyntheticApiModelOutline0.m(obj);
        if (m != null) {
            int i = 0;
            if (FragmentUtil$$ExternalSyntheticApiModelOutline0.m((Object) m)) {
                TransitionSet m2 = FragmentUtil$$ExternalSyntheticApiModelOutline0.m((Object) m);
                int m3 = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(m2);
                while (i < m3) {
                    addTargets(FragmentUtil$$ExternalSyntheticApiModelOutline0.m(m2, i), arrayList);
                    i++;
                }
            } else if (!hasSimpleTarget(m) && isNullOrEmpty(m.getTargets())) {
                int size = arrayList.size();
                while (i < size) {
                    Transition unused = m.addTarget(arrayList.get(i));
                    i++;
                }
            }
        }
    }

    private static boolean hasSimpleTarget(Transition transition) {
        return !isNullOrEmpty(transition.getTargetIds()) || !isNullOrEmpty(FragmentUtil$$ExternalSyntheticApiModelOutline0.m(transition)) || !isNullOrEmpty(transition.getTargetTypes());
    }

    public Object mergeTransitionsTogether(Object obj, Object obj2, Object obj3) {
        TransitionSet transitionSet = new TransitionSet();
        if (obj != null) {
            TransitionSet unused = transitionSet.addTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
        }
        if (obj2 != null) {
            TransitionSet unused2 = transitionSet.addTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj2));
        }
        if (obj3 != null) {
            TransitionSet unused3 = transitionSet.addTransition(CarContext$$ExternalSyntheticApiModelOutline0.m(obj3));
        }
        return transitionSet;
    }

    public void scheduleHideFragmentView(Object obj, final View view, final ArrayList<View> arrayList) {
        Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).addListener(new Transition.TransitionListener() {
            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }

            public void onTransitionStart(Transition transition) {
                Transition unused = transition.removeListener(this);
                Transition unused2 = transition.addListener(this);
            }

            public void onTransitionEnd(Transition transition) {
                Transition unused = transition.removeListener(this);
                view.setVisibility(8);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    ((View) arrayList.get(i)).setVisibility(0);
                }
            }
        });
    }

    public Object mergeTransitionsInSequence(Object obj, Object obj2, Object obj3) {
        Transition m = CarContext$$ExternalSyntheticApiModelOutline0.m(obj);
        Transition m2 = CarContext$$ExternalSyntheticApiModelOutline0.m(obj2);
        Transition m3 = CarContext$$ExternalSyntheticApiModelOutline0.m(obj3);
        if (m != null && m2 != null) {
            m = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(new TransitionSet().addTransition(m).addTransition(m2), 1);
        } else if (m == null) {
            m = m2 != null ? m2 : null;
        }
        if (m3 == null) {
            return m;
        }
        TransitionSet transitionSet = new TransitionSet();
        if (m != null) {
            TransitionSet unused = transitionSet.addTransition(m);
        }
        TransitionSet unused2 = transitionSet.addTransition(m3);
        return transitionSet;
    }

    public void beginDelayedTransition(ViewGroup viewGroup, Object obj) {
        TransitionManager.beginDelayedTransition(viewGroup, CarContext$$ExternalSyntheticApiModelOutline0.m(obj));
    }

    public void scheduleRemoveTargets(Object obj, Object obj2, ArrayList<View> arrayList, Object obj3, ArrayList<View> arrayList2, Object obj4, ArrayList<View> arrayList3) {
        final Object obj5 = obj2;
        final ArrayList<View> arrayList4 = arrayList;
        final Object obj6 = obj3;
        final ArrayList<View> arrayList5 = arrayList2;
        final Object obj7 = obj4;
        final ArrayList<View> arrayList6 = arrayList3;
        Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).addListener(new Transition.TransitionListener() {
            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }

            public void onTransitionStart(Transition transition) {
                Object obj = obj5;
                if (obj != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj, arrayList4, (ArrayList<View>) null);
                }
                Object obj2 = obj6;
                if (obj2 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj2, arrayList5, (ArrayList<View>) null);
                }
                Object obj3 = obj7;
                if (obj3 != null) {
                    FragmentTransitionCompat21.this.replaceTargets(obj3, arrayList6, (ArrayList<View>) null);
                }
            }

            public void onTransitionEnd(Transition transition) {
                Transition unused = transition.removeListener(this);
            }
        });
    }

    public void setListenerForTransitionEnd(Fragment fragment, Object obj, CancellationSignal cancellationSignal, final Runnable runnable) {
        Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).addListener(new Transition.TransitionListener() {
            public void onTransitionCancel(Transition transition) {
            }

            public void onTransitionPause(Transition transition) {
            }

            public void onTransitionResume(Transition transition) {
            }

            public void onTransitionStart(Transition transition) {
            }

            public void onTransitionEnd(Transition transition) {
                runnable.run();
            }
        });
    }

    public void swapSharedElementTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        TransitionSet m = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(obj);
        if (m != null) {
            FragmentUtil$$ExternalSyntheticApiModelOutline0.m(m).clear();
            FragmentUtil$$ExternalSyntheticApiModelOutline0.m(m).addAll(arrayList2);
            replaceTargets(m, arrayList, arrayList2);
        }
    }

    public void replaceTargets(Object obj, ArrayList<View> arrayList, ArrayList<View> arrayList2) {
        List m$2;
        int i;
        Transition m = CarContext$$ExternalSyntheticApiModelOutline0.m(obj);
        int i2 = 0;
        if (FragmentUtil$$ExternalSyntheticApiModelOutline0.m((Object) m)) {
            TransitionSet m2 = FragmentUtil$$ExternalSyntheticApiModelOutline0.m((Object) m);
            int m3 = FragmentUtil$$ExternalSyntheticApiModelOutline0.m(m2);
            while (i2 < m3) {
                replaceTargets(FragmentUtil$$ExternalSyntheticApiModelOutline0.m(m2, i2), arrayList, arrayList2);
                i2++;
            }
        } else if (!hasSimpleTarget(m) && (m$2 = m.getTargets()) != null && m$2.size() == arrayList.size() && m$2.containsAll(arrayList)) {
            if (arrayList2 == null) {
                i = 0;
            } else {
                i = arrayList2.size();
            }
            while (i2 < i) {
                Transition unused = m.addTarget(arrayList2.get(i2));
                i2++;
            }
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                Transition unused2 = m.removeTarget(arrayList.get(size));
            }
        }
    }

    public void addTarget(Object obj, View view) {
        if (obj != null) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).addTarget(view);
        }
    }

    public void removeTarget(Object obj, View view) {
        if (obj != null) {
            Transition unused = CarContext$$ExternalSyntheticApiModelOutline0.m(obj).removeTarget(view);
        }
    }

    public void setEpicenter(Object obj, final Rect rect) {
        if (obj != null) {
            CarContext$$ExternalSyntheticApiModelOutline0.m(obj).setEpicenterCallback(new Transition.EpicenterCallback() {
                public Rect onGetEpicenter(Transition transition) {
                    Rect rect = rect;
                    if (rect == null || rect.isEmpty()) {
                        return null;
                    }
                    return rect;
                }
            });
        }
    }
}
