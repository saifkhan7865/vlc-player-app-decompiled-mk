package androidx.fragment.app;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.collection.ArrayMap;
import androidx.core.os.CancellationSignal;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewGroupCompat;
import androidx.fragment.app.FragmentAnim;
import androidx.fragment.app.SpecialEffectsController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0003*+,B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J(\u0010\t\u001a\u00020\u00062\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\f0\u000bj\b\u0012\u0004\u0012\u00020\f`\r2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u001e\u0010\u000f\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J$\u0010\u0014\u001a\u00020\u00062\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\f0\u00162\u0006\u0010\u000e\u001a\u00020\fH\u0002J@\u0010\u0018\u001a\u00020\u00062\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\u0006\u0010\u001d\u001a\u00020\u00132\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u001fH\u0002JL\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00130\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00112\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u001c2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010\b2\b\u0010$\u001a\u0004\u0018\u00010\bH\u0002J\u0016\u0010%\u001a\u00020\u00062\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0011H\u0002J&\u0010&\u001a\u00020\u0006*\u000e\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\f0'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00170)H\u0002¨\u0006-"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController;", "Landroidx/fragment/app/SpecialEffectsController;", "container", "Landroid/view/ViewGroup;", "(Landroid/view/ViewGroup;)V", "applyContainerChanges", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "captureTransitioningViews", "transitioningViews", "Ljava/util/ArrayList;", "Landroid/view/View;", "Lkotlin/collections/ArrayList;", "view", "executeOperations", "operations", "", "isPop", "", "findNamedViews", "namedViews", "", "", "startAnimations", "animationInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "awaitingContainerChanges", "", "startedAnyTransition", "startedTransitions", "", "startTransitions", "transitionInfos", "Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "firstOut", "lastIn", "syncAnimations", "retainMatchingViews", "Landroidx/collection/ArrayMap;", "names", "", "AnimationInfo", "SpecialEffectsInfo", "TransitionInfo", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DefaultSpecialEffectsController.kt */
public final class DefaultSpecialEffectsController extends SpecialEffectsController {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController(ViewGroup viewGroup) {
        super(viewGroup);
        Intrinsics.checkNotNullParameter(viewGroup, "container");
    }

    public void executeOperations(List<? extends SpecialEffectsController.Operation> list, boolean z) {
        SpecialEffectsController.Operation operation;
        Object obj;
        List<? extends SpecialEffectsController.Operation> list2 = list;
        boolean z2 = z;
        Intrinsics.checkNotNullParameter(list2, "operations");
        Iterator it = list2.iterator();
        while (true) {
            operation = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            SpecialEffectsController.Operation operation2 = (SpecialEffectsController.Operation) obj;
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.Companion;
            View view = operation2.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            if (companion.asOperationState(view) == SpecialEffectsController.Operation.State.VISIBLE && operation2.getFinalState() != SpecialEffectsController.Operation.State.VISIBLE) {
                break;
            }
        }
        SpecialEffectsController.Operation operation3 = (SpecialEffectsController.Operation) obj;
        ListIterator<? extends SpecialEffectsController.Operation> listIterator = list2.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            Object previous = listIterator.previous();
            SpecialEffectsController.Operation operation4 = (SpecialEffectsController.Operation) previous;
            SpecialEffectsController.Operation.State.Companion companion2 = SpecialEffectsController.Operation.State.Companion;
            View view2 = operation4.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view2, "operation.fragment.mView");
            if (companion2.asOperationState(view2) != SpecialEffectsController.Operation.State.VISIBLE && operation4.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                operation = previous;
                break;
            }
        }
        SpecialEffectsController.Operation operation5 = operation;
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Executing operations from " + operation3 + " to " + operation5);
        }
        List arrayList = new ArrayList();
        List arrayList2 = new ArrayList();
        List<SpecialEffectsController.Operation> mutableList = CollectionsKt.toMutableList(list2);
        syncAnimations(list);
        Iterator<? extends SpecialEffectsController.Operation> it2 = list.iterator();
        while (true) {
            boolean z3 = true;
            if (!it2.hasNext()) {
                break;
            }
            SpecialEffectsController.Operation operation6 = (SpecialEffectsController.Operation) it2.next();
            CancellationSignal cancellationSignal = new CancellationSignal();
            operation6.markStartedSpecialEffect(cancellationSignal);
            arrayList.add(new AnimationInfo(operation6, cancellationSignal, z2));
            CancellationSignal cancellationSignal2 = new CancellationSignal();
            operation6.markStartedSpecialEffect(cancellationSignal2);
            if (z2) {
                if (operation6 == operation3) {
                    arrayList2.add(new TransitionInfo(operation6, cancellationSignal2, z2, z3));
                    operation6.addCompletionListener(new DefaultSpecialEffectsController$$ExternalSyntheticLambda2(mutableList, operation6, this));
                }
            } else if (operation6 == operation5) {
                arrayList2.add(new TransitionInfo(operation6, cancellationSignal2, z2, z3));
                operation6.addCompletionListener(new DefaultSpecialEffectsController$$ExternalSyntheticLambda2(mutableList, operation6, this));
            }
            z3 = false;
            arrayList2.add(new TransitionInfo(operation6, cancellationSignal2, z2, z3));
            operation6.addCompletionListener(new DefaultSpecialEffectsController$$ExternalSyntheticLambda2(mutableList, operation6, this));
        }
        Map<SpecialEffectsController.Operation, Boolean> startTransitions = startTransitions(arrayList2, mutableList, z, operation3, operation5);
        startAnimations(arrayList, mutableList, startTransitions.containsValue(true), startTransitions);
        for (SpecialEffectsController.Operation applyContainerChanges : mutableList) {
            applyContainerChanges(applyContainerChanges);
        }
        mutableList.clear();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Completed executing operations from " + operation3 + " to " + operation5);
        }
    }

    /* access modifiers changed from: private */
    public static final void executeOperations$lambda$2(List list, SpecialEffectsController.Operation operation, DefaultSpecialEffectsController defaultSpecialEffectsController) {
        Intrinsics.checkNotNullParameter(list, "$awaitingContainerChanges");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        Intrinsics.checkNotNullParameter(defaultSpecialEffectsController, "this$0");
        if (list.contains(operation)) {
            list.remove(operation);
            defaultSpecialEffectsController.applyContainerChanges(operation);
        }
    }

    private final void syncAnimations(List<? extends SpecialEffectsController.Operation> list) {
        Fragment fragment = ((SpecialEffectsController.Operation) CollectionsKt.last(list)).getFragment();
        for (SpecialEffectsController.Operation operation : list) {
            operation.getFragment().mAnimationInfo.mEnterAnim = fragment.mAnimationInfo.mEnterAnim;
            operation.getFragment().mAnimationInfo.mExitAnim = fragment.mAnimationInfo.mExitAnim;
            operation.getFragment().mAnimationInfo.mPopEnterAnim = fragment.mAnimationInfo.mPopEnterAnim;
            operation.getFragment().mAnimationInfo.mPopExitAnim = fragment.mAnimationInfo.mPopExitAnim;
        }
    }

    private final void startAnimations(List<AnimationInfo> list, List<SpecialEffectsController.Operation> list2, boolean z, Map<SpecialEffectsController.Operation, Boolean> map) {
        Context context = getContainer().getContext();
        List<AnimationInfo> arrayList = new ArrayList<>();
        boolean z2 = false;
        for (AnimationInfo next : list) {
            if (next.isVisibilityUnchanged()) {
                next.completeSpecialEffect();
            } else {
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation = next.getAnimation(context);
                if (animation == null) {
                    next.completeSpecialEffect();
                } else {
                    Animator animator = animation.animator;
                    if (animator == null) {
                        arrayList.add(next);
                    } else {
                        SpecialEffectsController.Operation operation = next.getOperation();
                        Fragment fragment = operation.getFragment();
                        if (Intrinsics.areEqual((Object) map.get(operation), (Object) true)) {
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Ignoring Animator set on " + fragment + " as this Fragment was involved in a Transition.");
                            }
                            next.completeSpecialEffect();
                        } else {
                            boolean z3 = operation.getFinalState() == SpecialEffectsController.Operation.State.GONE;
                            List<SpecialEffectsController.Operation> list3 = list2;
                            if (z3) {
                                list3.remove(operation);
                            }
                            View view = fragment.mView;
                            getContainer().startViewTransition(view);
                            View view2 = view;
                            SpecialEffectsController.Operation operation2 = operation;
                            animator.addListener(new DefaultSpecialEffectsController$startAnimations$1(this, view2, z3, operation, next));
                            animator.setTarget(view2);
                            animator.start();
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animator from operation " + operation2 + " has started.");
                            }
                            next.getSignal().setOnCancelListener(new DefaultSpecialEffectsController$$ExternalSyntheticLambda0(animator, operation2));
                            z2 = true;
                        }
                    }
                }
            }
            Map<SpecialEffectsController.Operation, Boolean> map2 = map;
        }
        for (AnimationInfo animationInfo : arrayList) {
            SpecialEffectsController.Operation operation3 = animationInfo.getOperation();
            Fragment fragment2 = operation3.getFragment();
            if (z) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Transitions.");
                }
                animationInfo.completeSpecialEffect();
            } else if (z2) {
                if (FragmentManager.isLoggingEnabled(2)) {
                    Log.v(FragmentManager.TAG, "Ignoring Animation set on " + fragment2 + " as Animations cannot run alongside Animators.");
                }
                animationInfo.completeSpecialEffect();
            } else {
                View view3 = fragment2.mView;
                Intrinsics.checkNotNullExpressionValue(context, "context");
                FragmentAnim.AnimationOrAnimator animation2 = animationInfo.getAnimation(context);
                if (animation2 != null) {
                    Animation animation3 = animation2.animation;
                    if (animation3 != null) {
                        if (operation3.getFinalState() != SpecialEffectsController.Operation.State.REMOVED) {
                            view3.startAnimation(animation3);
                            animationInfo.completeSpecialEffect();
                        } else {
                            getContainer().startViewTransition(view3);
                            Animation endViewTransitionAnimation = new FragmentAnim.EndViewTransitionAnimation(animation3, getContainer(), view3);
                            endViewTransitionAnimation.setAnimationListener(new DefaultSpecialEffectsController$startAnimations$3(operation3, this, view3, animationInfo));
                            view3.startAnimation(endViewTransitionAnimation);
                            if (FragmentManager.isLoggingEnabled(2)) {
                                Log.v(FragmentManager.TAG, "Animation from operation " + operation3 + " has started.");
                            }
                        }
                        animationInfo.getSignal().setOnCancelListener(new DefaultSpecialEffectsController$$ExternalSyntheticLambda1(view3, this, animationInfo, operation3));
                    } else {
                        throw new IllegalStateException("Required value was null.".toString());
                    }
                } else {
                    throw new IllegalStateException("Required value was null.".toString());
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static final void startAnimations$lambda$3(Animator animator, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(operation, "$operation");
        animator.end();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Animator from operation " + operation + " has been canceled.");
        }
    }

    /* access modifiers changed from: private */
    public static final void startAnimations$lambda$4(View view, DefaultSpecialEffectsController defaultSpecialEffectsController, AnimationInfo animationInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(defaultSpecialEffectsController, "this$0");
        Intrinsics.checkNotNullParameter(animationInfo, "$animationInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        view.clearAnimation();
        defaultSpecialEffectsController.getContainer().endViewTransition(view);
        animationInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Animation from operation " + operation + " has been cancelled.");
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:136:0x04f3  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x050d  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0586  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0596  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x05ab  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x05bc  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.Map<androidx.fragment.app.SpecialEffectsController.Operation, java.lang.Boolean> startTransitions(java.util.List<androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo> r38, java.util.List<androidx.fragment.app.SpecialEffectsController.Operation> r39, boolean r40, androidx.fragment.app.SpecialEffectsController.Operation r41, androidx.fragment.app.SpecialEffectsController.Operation r42) {
        /*
            r37 = this;
            r0 = r37
            r1 = r40
            r2 = r41
            r3 = r42
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            r4.<init>()
            java.util.Map r4 = (java.util.Map) r4
            r5 = r38
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r7 = r5.iterator()
        L_0x001e:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x0035
            java.lang.Object r8 = r7.next()
            r9 = r8
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r9 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r9
            boolean r9 = r9.isVisibilityUnchanged()
            if (r9 != 0) goto L_0x001e
            r6.add(r8)
            goto L_0x001e
        L_0x0035:
            java.util.List r6 = (java.util.List) r6
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Collection r7 = (java.util.Collection) r7
            java.util.Iterator r6 = r6.iterator()
        L_0x0044:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x005b
            java.lang.Object r8 = r6.next()
            r9 = r8
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r9 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r9
            androidx.fragment.app.FragmentTransitionImpl r9 = r9.getHandlingImpl()
            if (r9 == 0) goto L_0x0044
            r7.add(r8)
            goto L_0x0044
        L_0x005b:
            java.util.List r7 = (java.util.List) r7
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Iterator r6 = r7.iterator()
            r15 = 0
        L_0x0064:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x00ac
            java.lang.Object r8 = r6.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r8 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r8
            androidx.fragment.app.FragmentTransitionImpl r9 = r8.getHandlingImpl()
            if (r15 == 0) goto L_0x00aa
            if (r9 != r15) goto L_0x0079
            goto L_0x00aa
        L_0x0079:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Mixing framework transitions and AndroidX transitions is not allowed. Fragment "
            r1.<init>(r2)
            androidx.fragment.app.SpecialEffectsController$Operation r2 = r8.getOperation()
            androidx.fragment.app.Fragment r2 = r2.getFragment()
            r1.append(r2)
            java.lang.String r2 = " returned Transition "
            r1.append(r2)
            java.lang.Object r2 = r8.getTransition()
            r1.append(r2)
            java.lang.String r2 = " which uses a different Transition type than other Fragments."
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            throw r2
        L_0x00aa:
            r15 = r9
            goto L_0x0064
        L_0x00ac:
            r6 = 0
            if (r15 != 0) goto L_0x00cf
            java.util.Iterator r1 = r38.iterator()
        L_0x00b3:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x00ce
            java.lang.Object r2 = r1.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r2 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r2
            androidx.fragment.app.SpecialEffectsController$Operation r3 = r2.getOperation()
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r6)
            r4.put(r3, r5)
            r2.completeSpecialEffect()
            goto L_0x00b3
        L_0x00ce:
            return r4
        L_0x00cf:
            android.view.View r14 = new android.view.View
            android.view.ViewGroup r8 = r37.getContainer()
            android.content.Context r8 = r8.getContext()
            r14.<init>(r8)
            android.graphics.Rect r13 = new android.graphics.Rect
            r13.<init>()
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            androidx.collection.ArrayMap r10 = new androidx.collection.ArrayMap
            r10.<init>()
            java.util.Iterator r16 = r38.iterator()
            r8 = 0
            r9 = 0
            r17 = 0
        L_0x00f8:
            boolean r18 = r16.hasNext()
            r19 = 2
            java.lang.String r6 = "FragmentManager"
            if (r18 == 0) goto L_0x0451
            java.lang.Object r18 = r16.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r18 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r18
            boolean r20 = r18.hasSharedElementTransition()
            if (r20 == 0) goto L_0x0432
            if (r2 == 0) goto L_0x0432
            if (r3 == 0) goto L_0x0432
            java.lang.Object r9 = r18.getSharedElementTransition()
            java.lang.Object r9 = r15.cloneTransition(r9)
            java.lang.Object r9 = r15.wrapTransitionInSet(r9)
            androidx.fragment.app.Fragment r18 = r42.getFragment()
            java.util.ArrayList r7 = r18.getSharedElementSourceNames()
            r18 = r8
            java.lang.String r8 = "lastIn.fragment.sharedElementSourceNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)
            androidx.fragment.app.Fragment r8 = r41.getFragment()
            java.util.ArrayList r8 = r8.getSharedElementSourceNames()
            r21 = r5
            java.lang.String r5 = "firstOut.fragment.sharedElementSourceNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r5)
            androidx.fragment.app.Fragment r5 = r41.getFragment()
            java.util.ArrayList r5 = r5.getSharedElementTargetNames()
            r22 = r4
            java.lang.String r4 = "firstOut.fragment.sharedElementTargetNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r4)
            int r4 = r5.size()
            r24 = r13
            r23 = r14
            r14 = 0
        L_0x0154:
            r13 = -1
            if (r14 >= r4) goto L_0x016f
            r25 = r4
            java.lang.Object r4 = r5.get(r14)
            int r4 = r7.indexOf(r4)
            if (r4 == r13) goto L_0x016a
            java.lang.Object r13 = r8.get(r14)
            r7.set(r4, r13)
        L_0x016a:
            int r14 = r14 + 1
            r4 = r25
            goto L_0x0154
        L_0x016f:
            androidx.fragment.app.Fragment r4 = r42.getFragment()
            java.util.ArrayList r4 = r4.getSharedElementTargetNames()
            java.lang.String r5 = "lastIn.fragment.sharedElementTargetNames"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            if (r1 != 0) goto L_0x0193
            androidx.fragment.app.Fragment r5 = r41.getFragment()
            androidx.core.app.SharedElementCallback r5 = r5.getExitTransitionCallback()
            androidx.fragment.app.Fragment r8 = r42.getFragment()
            androidx.core.app.SharedElementCallback r8 = r8.getEnterTransitionCallback()
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r8)
            goto L_0x01a7
        L_0x0193:
            androidx.fragment.app.Fragment r5 = r41.getFragment()
            androidx.core.app.SharedElementCallback r5 = r5.getEnterTransitionCallback()
            androidx.fragment.app.Fragment r8 = r42.getFragment()
            androidx.core.app.SharedElementCallback r8 = r8.getExitTransitionCallback()
            kotlin.Pair r5 = kotlin.TuplesKt.to(r5, r8)
        L_0x01a7:
            java.lang.Object r8 = r5.component1()
            androidx.core.app.SharedElementCallback r8 = (androidx.core.app.SharedElementCallback) r8
            java.lang.Object r5 = r5.component2()
            androidx.core.app.SharedElementCallback r5 = (androidx.core.app.SharedElementCallback) r5
            int r14 = r7.size()
            r13 = 0
        L_0x01b8:
            if (r13 >= r14) goto L_0x01df
            java.lang.Object r26 = r7.get(r13)
            r27 = r14
            r14 = r26
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r26 = r4.get(r13)
            r28 = r9
            r9 = r26
            java.lang.String r9 = (java.lang.String) r9
            r26 = r15
            r15 = r10
            java.util.Map r15 = (java.util.Map) r15
            r15.put(r14, r9)
            int r13 = r13 + 1
            r15 = r26
            r14 = r27
            r9 = r28
            goto L_0x01b8
        L_0x01df:
            r28 = r9
            r26 = r15
            boolean r9 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r19)
            if (r9 == 0) goto L_0x0235
            java.lang.String r9 = ">>> entering view names <<<"
            android.util.Log.v(r6, r9)
            java.util.Iterator r9 = r4.iterator()
        L_0x01f2:
            boolean r13 = r9.hasNext()
            java.lang.String r14 = "Name: "
            if (r13 == 0) goto L_0x0210
            java.lang.Object r13 = r9.next()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r14)
            r15.append(r13)
            java.lang.String r13 = r15.toString()
            android.util.Log.v(r6, r13)
            goto L_0x01f2
        L_0x0210:
            java.lang.String r9 = ">>> exiting view names <<<"
            android.util.Log.v(r6, r9)
            java.util.Iterator r9 = r7.iterator()
        L_0x0219:
            boolean r13 = r9.hasNext()
            if (r13 == 0) goto L_0x0235
            java.lang.Object r13 = r9.next()
            java.lang.String r13 = (java.lang.String) r13
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r15.<init>(r14)
            r15.append(r13)
            java.lang.String r13 = r15.toString()
            android.util.Log.v(r6, r13)
            goto L_0x0219
        L_0x0235:
            androidx.collection.ArrayMap r9 = new androidx.collection.ArrayMap
            r9.<init>()
            r13 = r9
            java.util.Map r13 = (java.util.Map) r13
            androidx.fragment.app.Fragment r14 = r41.getFragment()
            android.view.View r14 = r14.mView
            java.lang.String r15 = "firstOut.fragment.mView"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r15)
            r0.findNamedViews(r13, r14)
            r14 = r7
            java.util.Collection r14 = (java.util.Collection) r14
            r9.retainAll(r14)
            if (r8 == 0) goto L_0x02b0
            boolean r15 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r19)
            if (r15 == 0) goto L_0x026d
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r27 = r14
            java.lang.String r14 = "Executing exit callback for operation "
            r15.<init>(r14)
            r15.append(r2)
            java.lang.String r14 = r15.toString()
            android.util.Log.v(r6, r14)
            goto L_0x026f
        L_0x026d:
            r27 = r14
        L_0x026f:
            r14 = r7
            java.util.List r14 = (java.util.List) r14
            r8.onMapSharedElements(r14, r13)
            int r8 = r7.size()
            r13 = -1
            int r8 = r8 + r13
            if (r8 < 0) goto L_0x02bb
        L_0x027d:
            int r13 = r8 + -1
            java.lang.Object r8 = r7.get(r8)
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r14 = r9.get(r8)
            android.view.View r14 = (android.view.View) r14
            if (r14 != 0) goto L_0x0291
            r10.remove(r8)
            goto L_0x02ab
        L_0x0291:
            java.lang.String r15 = androidx.core.view.ViewCompat.getTransitionName(r14)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r15)
            if (r15 != 0) goto L_0x02ab
            java.lang.Object r8 = r10.remove(r8)
            java.lang.String r8 = (java.lang.String) r8
            r15 = r10
            java.util.Map r15 = (java.util.Map) r15
            java.lang.String r14 = androidx.core.view.ViewCompat.getTransitionName(r14)
            r15.put(r14, r8)
        L_0x02ab:
            if (r13 >= 0) goto L_0x02ae
            goto L_0x02bb
        L_0x02ae:
            r8 = r13
            goto L_0x027d
        L_0x02b0:
            r27 = r14
            java.util.Set r8 = r9.keySet()
            java.util.Collection r8 = (java.util.Collection) r8
            r10.retainAll(r8)
        L_0x02bb:
            androidx.collection.ArrayMap r8 = new androidx.collection.ArrayMap
            r8.<init>()
            r13 = r8
            java.util.Map r13 = (java.util.Map) r13
            androidx.fragment.app.Fragment r14 = r42.getFragment()
            android.view.View r14 = r14.mView
            java.lang.String r15 = "lastIn.fragment.mView"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r14, r15)
            r0.findNamedViews(r13, r14)
            r14 = r4
            java.util.Collection r14 = (java.util.Collection) r14
            r8.retainAll(r14)
            java.util.Collection r15 = r10.values()
            r8.retainAll(r15)
            if (r5 == 0) goto L_0x034b
            boolean r15 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r19)
            if (r15 == 0) goto L_0x02fa
            java.lang.StringBuilder r15 = new java.lang.StringBuilder
            r29 = r14
            java.lang.String r14 = "Executing enter callback for operation "
            r15.<init>(r14)
            r15.append(r3)
            java.lang.String r14 = r15.toString()
            android.util.Log.v(r6, r14)
            goto L_0x02fc
        L_0x02fa:
            r29 = r14
        L_0x02fc:
            r6 = r4
            java.util.List r6 = (java.util.List) r6
            r5.onMapSharedElements(r6, r13)
            int r5 = r4.size()
            r6 = -1
            int r5 = r5 + r6
            if (r5 < 0) goto L_0x0350
        L_0x030a:
            int r6 = r5 + -1
            java.lang.Object r5 = r4.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r13 = r8.get(r5)
            android.view.View r13 = (android.view.View) r13
            java.lang.String r14 = "name"
            if (r13 != 0) goto L_0x0329
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r14)
            java.lang.String r5 = androidx.fragment.app.FragmentTransition.findKeyForValue(r10, r5)
            if (r5 == 0) goto L_0x0346
            r10.remove(r5)
            goto L_0x0346
        L_0x0329:
            java.lang.String r15 = androidx.core.view.ViewCompat.getTransitionName(r13)
            boolean r15 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r15)
            if (r15 != 0) goto L_0x0346
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r14)
            java.lang.String r5 = androidx.fragment.app.FragmentTransition.findKeyForValue(r10, r5)
            if (r5 == 0) goto L_0x0346
            r14 = r10
            java.util.Map r14 = (java.util.Map) r14
            java.lang.String r13 = androidx.core.view.ViewCompat.getTransitionName(r13)
            r14.put(r5, r13)
        L_0x0346:
            if (r6 >= 0) goto L_0x0349
            goto L_0x0350
        L_0x0349:
            r5 = r6
            goto L_0x030a
        L_0x034b:
            r29 = r14
            androidx.fragment.app.FragmentTransition.retainValues(r10, r8)
        L_0x0350:
            java.util.Set r5 = r10.keySet()
            java.lang.String r6 = "sharedElementNameMapping.keys"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            java.util.Collection r5 = (java.util.Collection) r5
            r0.retainMatchingViews(r9, r5)
            java.util.Collection r5 = r10.values()
            java.lang.String r6 = "sharedElementNameMapping.values"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            r0.retainMatchingViews(r8, r5)
            boolean r5 = r10.isEmpty()
            if (r5 == 0) goto L_0x0386
            r12.clear()
            r11.clear()
            r8 = r18
            r5 = r21
            r4 = r22
            r14 = r23
            r13 = r24
            r15 = r26
            r6 = 0
            r9 = 0
            goto L_0x00f8
        L_0x0386:
            androidx.fragment.app.Fragment r5 = r42.getFragment()
            androidx.fragment.app.Fragment r6 = r41.getFragment()
            r13 = 1
            androidx.fragment.app.FragmentTransition.callSharedElementStartEnd(r5, r6, r1, r9, r13)
            android.view.ViewGroup r5 = r37.getContainer()
            android.view.View r5 = (android.view.View) r5
            androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda3 r6 = new androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda3
            r6.<init>(r3, r2, r1, r8)
            androidx.core.view.OneShotPreDrawListener.add(r5, r6)
            java.util.Collection r5 = r9.values()
            r12.addAll(r5)
            boolean r5 = r27.isEmpty()
            r5 = r5 ^ r13
            if (r5 == 0) goto L_0x03c5
            r5 = 0
            java.lang.Object r6 = r7.get(r5)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r5 = r9.get(r6)
            android.view.View r5 = (android.view.View) r5
            r7 = r26
            r6 = r28
            r7.setEpicenter((java.lang.Object) r6, (android.view.View) r5)
            r18 = r5
            goto L_0x03c9
        L_0x03c5:
            r7 = r26
            r6 = r28
        L_0x03c9:
            java.util.Collection r5 = r8.values()
            r11.addAll(r5)
            boolean r5 = r29.isEmpty()
            r9 = 1
            r5 = r5 ^ r9
            if (r5 == 0) goto L_0x03fc
            r5 = 0
            java.lang.Object r4 = r4.get(r5)
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r4 = r8.get(r4)
            android.view.View r4 = (android.view.View) r4
            if (r4 == 0) goto L_0x03fc
            android.view.ViewGroup r5 = r37.getContainer()
            android.view.View r5 = (android.view.View) r5
            androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda4 r8 = new androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda4
            r13 = r24
            r8.<init>(r7, r4, r13)
            androidx.core.view.OneShotPreDrawListener.add(r5, r8)
            r4 = r23
            r17 = 1
            goto L_0x0400
        L_0x03fc:
            r13 = r24
            r4 = r23
        L_0x0400:
            r7.setSharedElementTargets(r6, r4, r12)
            r5 = 0
            r14 = 0
            r15 = 0
            r19 = 0
            r8 = r7
            r9 = r6
            r23 = r10
            r10 = r15
            r24 = r11
            r11 = r19
            r15 = r12
            r12 = r5
            r5 = r13
            r13 = r14
            r14 = r6
            r1 = r15
            r15 = r24
            r8.scheduleRemoveTargets(r9, r10, r11, r12, r13, r14, r15)
            r8 = 1
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r8)
            r15 = r22
            r15.put(r2, r9)
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r8)
            r15.put(r3, r8)
            r12 = r1
            r14 = r4
            r13 = r5
            r9 = r6
            goto L_0x0442
        L_0x0432:
            r21 = r5
            r18 = r8
            r23 = r10
            r24 = r11
            r1 = r12
            r5 = r13
            r7 = r15
            r15 = r4
            r4 = r14
            r12 = r1
            r14 = r4
            r13 = r5
        L_0x0442:
            r4 = r15
            r8 = r18
            r5 = r21
            r10 = r23
            r11 = r24
            r6 = 0
            r1 = r40
            r15 = r7
            goto L_0x00f8
        L_0x0451:
            r21 = r5
            r18 = r8
            r23 = r10
            r24 = r11
            r1 = r12
            r5 = r13
            r7 = r15
            r15 = r4
            r4 = r14
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.Iterator r16 = r38.iterator()
            r12 = 0
            r13 = 0
        L_0x0469:
            boolean r8 = r16.hasNext()
            if (r8 == 0) goto L_0x05d4
            java.lang.Object r8 = r16.next()
            r22 = r8
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r22 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r22
            boolean r8 = r22.isVisibilityUnchanged()
            if (r8 == 0) goto L_0x048d
            androidx.fragment.app.SpecialEffectsController$Operation r8 = r22.getOperation()
            r10 = 0
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r10)
            r15.put(r8, r11)
            r22.completeSpecialEffect()
            goto L_0x0469
        L_0x048d:
            java.lang.Object r8 = r22.getTransition()
            java.lang.Object r11 = r7.cloneTransition(r8)
            androidx.fragment.app.SpecialEffectsController$Operation r10 = r22.getOperation()
            if (r9 == 0) goto L_0x04a1
            if (r10 == r2) goto L_0x049f
            if (r10 != r3) goto L_0x04a1
        L_0x049f:
            r8 = 1
            goto L_0x04a2
        L_0x04a1:
            r8 = 0
        L_0x04a2:
            if (r11 != 0) goto L_0x04b2
            if (r8 != 0) goto L_0x0469
            r8 = 0
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r8)
            r15.put(r10, r11)
            r22.completeSpecialEffect()
            goto L_0x0469
        L_0x04b2:
            r25 = r15
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            r26 = r9
            androidx.fragment.app.Fragment r9 = r10.getFragment()
            android.view.View r9 = r9.mView
            r38 = r12
            java.lang.String r12 = "operation.fragment.mView"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r12)
            r0.captureTransitioningViews(r15, r9)
            if (r8 == 0) goto L_0x04eb
            if (r10 != r2) goto L_0x04dc
            r12 = r1
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.Set r8 = kotlin.collections.CollectionsKt.toSet(r12)
            java.util.Collection r8 = (java.util.Collection) r8
            r15.removeAll(r8)
            goto L_0x04eb
        L_0x04dc:
            r12 = r24
            r8 = r12
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Set r8 = kotlin.collections.CollectionsKt.toSet(r8)
            java.util.Collection r8 = (java.util.Collection) r8
            r15.removeAll(r8)
            goto L_0x04ed
        L_0x04eb:
            r12 = r24
        L_0x04ed:
            boolean r8 = r15.isEmpty()
            if (r8 == 0) goto L_0x050d
            r7.addTarget(r11, r4)
            r33 = r38
            r8 = r39
            r9 = r10
            r34 = r12
            r35 = r13
            r36 = r14
            r31 = r18
            r0 = r25
            r30 = r26
            r18 = r4
            r12 = r11
            r4 = r15
            goto L_0x057e
        L_0x050d:
            r7.addTargets(r11, r15)
            r24 = 0
            r27 = 0
            r28 = 0
            r29 = 0
            r9 = r18
            r8 = r7
            r31 = r9
            r30 = r26
            r9 = r11
            r40 = r10
            r10 = r11
            r32 = r11
            r11 = r15
            r33 = r38
            r34 = r12
            r12 = r28
            r35 = r13
            r13 = r29
            r36 = r14
            r14 = r24
            r18 = r4
            r4 = r15
            r0 = r25
            r15 = r27
            r8.scheduleRemoveTargets(r9, r10, r11, r12, r13, r14, r15)
            androidx.fragment.app.SpecialEffectsController$Operation$State r8 = r40.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r9 = androidx.fragment.app.SpecialEffectsController.Operation.State.GONE
            if (r8 != r9) goto L_0x0578
            r8 = r39
            r9 = r40
            r8.remove(r9)
            java.util.ArrayList r10 = new java.util.ArrayList
            r15 = r4
            java.util.Collection r15 = (java.util.Collection) r15
            r10.<init>(r15)
            androidx.fragment.app.Fragment r11 = r9.getFragment()
            android.view.View r11 = r11.mView
            r10.remove(r11)
            androidx.fragment.app.Fragment r11 = r9.getFragment()
            android.view.View r11 = r11.mView
            r12 = r32
            r7.scheduleHideFragmentView(r12, r11, r10)
            android.view.ViewGroup r10 = r37.getContainer()
            android.view.View r10 = (android.view.View) r10
            androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda5 r11 = new androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda5
            r11.<init>(r4)
            androidx.core.view.OneShotPreDrawListener.add(r10, r11)
            goto L_0x057e
        L_0x0578:
            r8 = r39
            r9 = r40
            r12 = r32
        L_0x057e:
            androidx.fragment.app.SpecialEffectsController$Operation$State r10 = r9.getFinalState()
            androidx.fragment.app.SpecialEffectsController$Operation$State r11 = androidx.fragment.app.SpecialEffectsController.Operation.State.VISIBLE
            if (r10 != r11) goto L_0x0596
            r15 = r4
            java.util.Collection r15 = (java.util.Collection) r15
            r4 = r36
            r4.addAll(r15)
            if (r17 == 0) goto L_0x0593
            r7.setEpicenter((java.lang.Object) r12, (android.graphics.Rect) r5)
        L_0x0593:
            r10 = r31
            goto L_0x059d
        L_0x0596:
            r10 = r31
            r4 = r36
            r7.setEpicenter((java.lang.Object) r12, (android.view.View) r10)
        L_0x059d:
            r13 = 1
            java.lang.Boolean r11 = java.lang.Boolean.valueOf(r13)
            r0.put(r9, r11)
            boolean r9 = r22.isOverlapAllowed()
            if (r9 == 0) goto L_0x05bc
            r9 = r35
            r11 = 0
            java.lang.Object r9 = r7.mergeTransitionsTogether(r9, r12, r11)
            r15 = r0
            r14 = r4
            r13 = r9
            r4 = r18
            r9 = r30
            r12 = r33
            goto L_0x05cc
        L_0x05bc:
            r14 = r33
            r9 = r35
            r11 = 0
            java.lang.Object r12 = r7.mergeTransitionsTogether(r14, r12, r11)
            r15 = r0
            r14 = r4
            r13 = r9
            r4 = r18
            r9 = r30
        L_0x05cc:
            r24 = r34
            r0 = r37
            r18 = r10
            goto L_0x0469
        L_0x05d4:
            r4 = r14
            r0 = r15
            r34 = r24
            r15 = r9
            r14 = r12
            r9 = r13
            r13 = 1
            java.lang.Object r5 = r7.mergeTransitionsInSequence(r9, r14, r15)
            if (r5 != 0) goto L_0x05e3
            return r0
        L_0x05e3:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Collection r8 = (java.util.Collection) r8
            java.util.Iterator r9 = r21.iterator()
        L_0x05ee:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x0605
            java.lang.Object r10 = r9.next()
            r11 = r10
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r11 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r11
            boolean r11 = r11.isVisibilityUnchanged()
            if (r11 != 0) goto L_0x05ee
            r8.add(r10)
            goto L_0x05ee
        L_0x0605:
            java.util.List r8 = (java.util.List) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r8 = r8.iterator()
        L_0x060d:
            boolean r9 = r8.hasNext()
            if (r9 == 0) goto L_0x0676
            java.lang.Object r9 = r8.next()
            androidx.fragment.app.DefaultSpecialEffectsController$TransitionInfo r9 = (androidx.fragment.app.DefaultSpecialEffectsController.TransitionInfo) r9
            java.lang.Object r10 = r9.getTransition()
            androidx.fragment.app.SpecialEffectsController$Operation r11 = r9.getOperation()
            if (r15 == 0) goto L_0x0629
            if (r11 == r2) goto L_0x0627
            if (r11 != r3) goto L_0x0629
        L_0x0627:
            r12 = 1
            goto L_0x062a
        L_0x0629:
            r12 = 0
        L_0x062a:
            if (r10 != 0) goto L_0x062e
            if (r12 == 0) goto L_0x060d
        L_0x062e:
            android.view.ViewGroup r10 = r37.getContainer()
            android.view.View r10 = (android.view.View) r10
            boolean r10 = androidx.core.view.ViewCompat.isLaidOut(r10)
            if (r10 != 0) goto L_0x0661
            boolean r10 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r19)
            if (r10 == 0) goto L_0x065d
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r12 = "SpecialEffectsController: Container "
            r10.<init>(r12)
            android.view.ViewGroup r12 = r37.getContainer()
            r10.append(r12)
            java.lang.String r12 = " has not been laid out. Completing operation "
            r10.append(r12)
            r10.append(r11)
            java.lang.String r10 = r10.toString()
            android.util.Log.v(r6, r10)
        L_0x065d:
            r9.completeSpecialEffect()
            goto L_0x060d
        L_0x0661:
            androidx.fragment.app.SpecialEffectsController$Operation r10 = r9.getOperation()
            androidx.fragment.app.Fragment r10 = r10.getFragment()
            androidx.core.os.CancellationSignal r12 = r9.getSignal()
            androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda6 r14 = new androidx.fragment.app.DefaultSpecialEffectsController$$ExternalSyntheticLambda6
            r14.<init>(r9, r11)
            r7.setListenerForTransitionEnd(r10, r5, r12, r14)
            goto L_0x060d
        L_0x0676:
            android.view.ViewGroup r2 = r37.getContainer()
            android.view.View r2 = (android.view.View) r2
            boolean r2 = androidx.core.view.ViewCompat.isLaidOut(r2)
            if (r2 != 0) goto L_0x0683
            return r0
        L_0x0683:
            r14 = r4
            java.util.List r14 = (java.util.List) r14
            r2 = 4
            androidx.fragment.app.FragmentTransition.setViewVisibility(r14, r2)
            r2 = r34
            java.util.ArrayList r12 = r7.prepareSetNameOverridesReordered(r2)
            boolean r3 = androidx.fragment.app.FragmentManager.isLoggingEnabled(r19)
            if (r3 == 0) goto L_0x0707
            java.lang.String r3 = ">>>>> Beginning transition <<<<<"
            android.util.Log.v(r6, r3)
            java.lang.String r3 = ">>>>> SharedElementFirstOutViews <<<<<"
            android.util.Log.v(r6, r3)
            java.util.Iterator r3 = r1.iterator()
        L_0x06a4:
            boolean r4 = r3.hasNext()
            java.lang.String r8 = " Name: "
            java.lang.String r9 = "View: "
            if (r4 == 0) goto L_0x06d3
            java.lang.Object r4 = r3.next()
            java.lang.String r10 = "sharedElementFirstOutViews"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r10)
            android.view.View r4 = (android.view.View) r4
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r9)
            r10.append(r4)
            r10.append(r8)
            java.lang.String r4 = androidx.core.view.ViewCompat.getTransitionName(r4)
            r10.append(r4)
            java.lang.String r4 = r10.toString()
            android.util.Log.v(r6, r4)
            goto L_0x06a4
        L_0x06d3:
            java.lang.String r3 = ">>>>> SharedElementLastInViews <<<<<"
            android.util.Log.v(r6, r3)
            java.util.Iterator r3 = r2.iterator()
        L_0x06dc:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0707
            java.lang.Object r4 = r3.next()
            java.lang.String r10 = "sharedElementLastInViews"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r10)
            android.view.View r4 = (android.view.View) r4
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>(r9)
            r10.append(r4)
            r10.append(r8)
            java.lang.String r4 = androidx.core.view.ViewCompat.getTransitionName(r4)
            r10.append(r4)
            java.lang.String r4 = r10.toString()
            android.util.Log.v(r6, r4)
            goto L_0x06dc
        L_0x0707:
            android.view.ViewGroup r3 = r37.getContainer()
            r7.beginDelayedTransition(r3, r5)
            android.view.ViewGroup r3 = r37.getContainer()
            r9 = r3
            android.view.View r9 = (android.view.View) r9
            r13 = r23
            java.util.Map r13 = (java.util.Map) r13
            r8 = r7
            r10 = r1
            r11 = r2
            r8.setNameOverridesReordered(r9, r10, r11, r12, r13)
            r3 = 0
            androidx.fragment.app.FragmentTransition.setViewVisibility(r14, r3)
            r7.swapSharedElementTargets(r15, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.fragment.app.DefaultSpecialEffectsController.startTransitions(java.util.List, java.util.List, boolean, androidx.fragment.app.SpecialEffectsController$Operation, androidx.fragment.app.SpecialEffectsController$Operation):java.util.Map");
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$9(SpecialEffectsController.Operation operation, SpecialEffectsController.Operation operation2, boolean z, ArrayMap arrayMap) {
        Intrinsics.checkNotNullParameter(arrayMap, "$lastInViews");
        FragmentTransition.callSharedElementStartEnd(operation.getFragment(), operation2.getFragment(), z, arrayMap, false);
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$10(FragmentTransitionImpl fragmentTransitionImpl, View view, Rect rect) {
        Intrinsics.checkNotNullParameter(fragmentTransitionImpl, "$impl");
        Intrinsics.checkNotNullParameter(rect, "$lastInEpicenterRect");
        fragmentTransitionImpl.getBoundsOnScreen(view, rect);
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$11(ArrayList arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "$transitioningViews");
        FragmentTransition.setViewVisibility(arrayList, 4);
    }

    /* access modifiers changed from: private */
    public static final void startTransitions$lambda$14$lambda$13(TransitionInfo transitionInfo, SpecialEffectsController.Operation operation) {
        Intrinsics.checkNotNullParameter(transitionInfo, "$transitionInfo");
        Intrinsics.checkNotNullParameter(operation, "$operation");
        transitionInfo.completeSpecialEffect();
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(FragmentManager.TAG, "Transition for operation " + operation + " has completed");
        }
    }

    private final void retainMatchingViews(ArrayMap<String, View> arrayMap, Collection<String> collection) {
        Set<Map.Entry<String, View>> entrySet = arrayMap.entrySet();
        Intrinsics.checkNotNullExpressionValue(entrySet, "entries");
        CollectionsKt.retainAll(entrySet, new DefaultSpecialEffectsController$retainMatchingViews$1(collection));
    }

    private final void captureTransitioningViews(ArrayList<View> arrayList, View view) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!ViewGroupCompat.isTransitionGroup(viewGroup)) {
                int childCount = viewGroup.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt.getVisibility() == 0) {
                        Intrinsics.checkNotNullExpressionValue(childAt, "child");
                        captureTransitioningViews(arrayList, childAt);
                    }
                }
            } else if (!arrayList.contains(view)) {
                arrayList.add(view);
            }
        } else if (!arrayList.contains(view)) {
            arrayList.add(view);
        }
    }

    private final void findNamedViews(Map<String, View> map, View view) {
        String transitionName = ViewCompat.getTransitionName(view);
        if (transitionName != null) {
            map.put(transitionName, view);
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                if (childAt.getVisibility() == 0) {
                    Intrinsics.checkNotNullExpressionValue(childAt, "child");
                    findNamedViews(map, childAt);
                }
            }
        }
    }

    private final void applyContainerChanges(SpecialEffectsController.Operation operation) {
        View view = operation.getFragment().mView;
        SpecialEffectsController.Operation.State finalState = operation.getFinalState();
        Intrinsics.checkNotNullExpressionValue(view, "view");
        finalState.applyState(view);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fR\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;)V", "isVisibilityUnchanged", "", "()Z", "getOperation", "()Landroidx/fragment/app/SpecialEffectsController$Operation;", "getSignal", "()Landroidx/core/os/CancellationSignal;", "completeSpecialEffect", "", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DefaultSpecialEffectsController.kt */
    private static class SpecialEffectsInfo {
        private final SpecialEffectsController.Operation operation;
        private final CancellationSignal signal;

        public SpecialEffectsInfo(SpecialEffectsController.Operation operation2, CancellationSignal cancellationSignal) {
            Intrinsics.checkNotNullParameter(operation2, "operation");
            Intrinsics.checkNotNullParameter(cancellationSignal, "signal");
            this.operation = operation2;
            this.signal = cancellationSignal;
        }

        public final SpecialEffectsController.Operation getOperation() {
            return this.operation;
        }

        public final CancellationSignal getSignal() {
            return this.signal;
        }

        public final boolean isVisibilityUnchanged() {
            SpecialEffectsController.Operation.State.Companion companion = SpecialEffectsController.Operation.State.Companion;
            View view = this.operation.getFragment().mView;
            Intrinsics.checkNotNullExpressionValue(view, "operation.fragment.mView");
            SpecialEffectsController.Operation.State asOperationState = companion.asOperationState(view);
            SpecialEffectsController.Operation.State finalState = this.operation.getFinalState();
            return asOperationState == finalState || !(asOperationState == SpecialEffectsController.Operation.State.VISIBLE || finalState == SpecialEffectsController.Operation.State.VISIBLE);
        }

        public final void completeSpecialEffect() {
            this.operation.completeSpecialEffect(this.signal);
        }
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u000eR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$AnimationInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "isPop", "", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;Z)V", "animation", "Landroidx/fragment/app/FragmentAnim$AnimationOrAnimator;", "isAnimLoaded", "getAnimation", "context", "Landroid/content/Context;", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DefaultSpecialEffectsController.kt */
    private static final class AnimationInfo extends SpecialEffectsInfo {
        private FragmentAnim.AnimationOrAnimator animation;
        private boolean isAnimLoaded;
        private final boolean isPop;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public AnimationInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z) {
            super(operation, cancellationSignal);
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(cancellationSignal, "signal");
            this.isPop = z;
        }

        public final FragmentAnim.AnimationOrAnimator getAnimation(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            if (this.isAnimLoaded) {
                return this.animation;
            }
            FragmentAnim.AnimationOrAnimator loadAnimation = FragmentAnim.loadAnimation(context, getOperation().getFragment(), getOperation().getFinalState() == SpecialEffectsController.Operation.State.VISIBLE, this.isPop);
            this.animation = loadAnimation;
            this.isAnimLoaded = true;
            return loadAnimation;
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0014\u0010\f\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0011H\u0002J\u0006\u0010\u0016\u001a\u00020\u0007R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0010\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013¨\u0006\u0017"}, d2 = {"Landroidx/fragment/app/DefaultSpecialEffectsController$TransitionInfo;", "Landroidx/fragment/app/DefaultSpecialEffectsController$SpecialEffectsInfo;", "operation", "Landroidx/fragment/app/SpecialEffectsController$Operation;", "signal", "Landroidx/core/os/CancellationSignal;", "isPop", "", "providesSharedElementTransition", "(Landroidx/fragment/app/SpecialEffectsController$Operation;Landroidx/core/os/CancellationSignal;ZZ)V", "handlingImpl", "Landroidx/fragment/app/FragmentTransitionImpl;", "getHandlingImpl", "()Landroidx/fragment/app/FragmentTransitionImpl;", "isOverlapAllowed", "()Z", "sharedElementTransition", "", "getSharedElementTransition", "()Ljava/lang/Object;", "transition", "getTransition", "hasSharedElementTransition", "fragment_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DefaultSpecialEffectsController.kt */
    private static final class TransitionInfo extends SpecialEffectsInfo {
        private final boolean isOverlapAllowed;
        private final Object sharedElementTransition;
        private final Object transition;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TransitionInfo(SpecialEffectsController.Operation operation, CancellationSignal cancellationSignal, boolean z, boolean z2) {
            super(operation, cancellationSignal);
            Object obj;
            boolean z3;
            Object obj2;
            Intrinsics.checkNotNullParameter(operation, "operation");
            Intrinsics.checkNotNullParameter(cancellationSignal, "signal");
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                Fragment fragment = operation.getFragment();
                obj = z ? fragment.getReenterTransition() : fragment.getEnterTransition();
            } else {
                Fragment fragment2 = operation.getFragment();
                obj = z ? fragment2.getReturnTransition() : fragment2.getExitTransition();
            }
            this.transition = obj;
            if (operation.getFinalState() == SpecialEffectsController.Operation.State.VISIBLE) {
                z3 = z ? operation.getFragment().getAllowReturnTransitionOverlap() : operation.getFragment().getAllowEnterTransitionOverlap();
            } else {
                z3 = true;
            }
            this.isOverlapAllowed = z3;
            if (z2) {
                obj2 = z ? operation.getFragment().getSharedElementReturnTransition() : operation.getFragment().getSharedElementEnterTransition();
            } else {
                obj2 = null;
            }
            this.sharedElementTransition = obj2;
        }

        public final Object getTransition() {
            return this.transition;
        }

        public final boolean isOverlapAllowed() {
            return this.isOverlapAllowed;
        }

        public final Object getSharedElementTransition() {
            return this.sharedElementTransition;
        }

        public final boolean hasSharedElementTransition() {
            return this.sharedElementTransition != null;
        }

        public final FragmentTransitionImpl getHandlingImpl() {
            FragmentTransitionImpl handlingImpl = getHandlingImpl(this.transition);
            FragmentTransitionImpl handlingImpl2 = getHandlingImpl(this.sharedElementTransition);
            if (handlingImpl == null || handlingImpl2 == null || handlingImpl == handlingImpl2) {
                return handlingImpl == null ? handlingImpl2 : handlingImpl;
            }
            throw new IllegalArgumentException(("Mixing framework transitions and AndroidX transitions is not allowed. Fragment " + getOperation().getFragment() + " returned Transition " + this.transition + " which uses a different Transition  type than its shared element transition " + this.sharedElementTransition).toString());
        }

        private final FragmentTransitionImpl getHandlingImpl(Object obj) {
            if (obj == null) {
                return null;
            }
            if (FragmentTransition.PLATFORM_IMPL != null && FragmentTransition.PLATFORM_IMPL.canHandle(obj)) {
                return FragmentTransition.PLATFORM_IMPL;
            }
            if (FragmentTransition.SUPPORT_IMPL != null && FragmentTransition.SUPPORT_IMPL.canHandle(obj)) {
                return FragmentTransition.SUPPORT_IMPL;
            }
            throw new IllegalArgumentException("Transition " + obj + " for fragment " + getOperation().getFragment() + " is not a valid framework Transition or AndroidX Transition");
        }
    }
}
