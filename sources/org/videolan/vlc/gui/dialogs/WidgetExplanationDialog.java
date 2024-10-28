package org.videolan.vlc.gui.dialogs;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogWidgetExplanationBinding;
import org.videolan.vlc.util.LifecycleAwareScheduler;
import org.videolan.vlc.util.SchedulerCallback;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0012\u0010\u001d\u001a\u00020\u001c2\b\b\u0001\u0010\u001e\u001a\u00020\u000bH\u0002J\b\u0010\u001f\u001a\u00020\u000bH\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\u0012\u0010$\u001a\u00020\u001c2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J$\u0010'\u001a\u00020!2\u0006\u0010(\u001a\u00020)2\b\u0010*\u001a\u0004\u0018\u00010+2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016J\u0018\u0010,\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020&H\u0016J\u001a\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010&H\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001aX\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/WidgetExplanationDialog;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Lorg/videolan/vlc/util/SchedulerCallback;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogWidgetExplanationBinding;", "getBinding$vlc_android_release", "()Lorg/videolan/vlc/databinding/DialogWidgetExplanationBinding;", "setBinding$vlc_android_release", "(Lorg/videolan/vlc/databinding/DialogWidgetExplanationBinding;)V", "currentDrawable", "", "getCurrentDrawable", "()I", "setCurrentDrawable", "(I)V", "currentStep", "resizeAnimation", "Landroid/animation/AnimatorSet;", "scheduler", "Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "getScheduler", "()Lorg/videolan/vlc/util/LifecycleAwareScheduler;", "setScheduler", "(Lorg/videolan/vlc/util/LifecycleAwareScheduler;)V", "sizeDrawables", "", "animateLongTap", "", "displaySizeImage", "drawable", "getDefaultState", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onTaskTriggered", "id", "", "data", "onViewCreated", "view", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetExplanationDialog.kt */
public final class WidgetExplanationDialog extends VLCBottomSheetDialogFragment implements SchedulerCallback {
    public DialogWidgetExplanationBinding binding;
    private int currentDrawable = R.drawable.vlc_widget_macro;
    private int currentStep = 1;
    /* access modifiers changed from: private */
    public AnimatorSet resizeAnimation;
    public LifecycleAwareScheduler scheduler;
    private final List<Integer> sizeDrawables = CollectionsKt.listOf(Integer.valueOf(R.drawable.vlc_widget_mini), Integer.valueOf(R.drawable.vlc_widget_micro), Integer.valueOf(R.drawable.vlc_widget_pill), Integer.valueOf(R.drawable.vlc_widget_macro));

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    public void onTaskCancelled(String str) {
        SchedulerCallback.DefaultImpls.onTaskCancelled(this, str);
    }

    public final DialogWidgetExplanationBinding getBinding$vlc_android_release() {
        DialogWidgetExplanationBinding dialogWidgetExplanationBinding = this.binding;
        if (dialogWidgetExplanationBinding != null) {
            return dialogWidgetExplanationBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding$vlc_android_release(DialogWidgetExplanationBinding dialogWidgetExplanationBinding) {
        Intrinsics.checkNotNullParameter(dialogWidgetExplanationBinding, "<set-?>");
        this.binding = dialogWidgetExplanationBinding;
    }

    public final LifecycleAwareScheduler getScheduler() {
        LifecycleAwareScheduler lifecycleAwareScheduler = this.scheduler;
        if (lifecycleAwareScheduler != null) {
            return lifecycleAwareScheduler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scheduler");
        return null;
    }

    public final void setScheduler(LifecycleAwareScheduler lifecycleAwareScheduler) {
        Intrinsics.checkNotNullParameter(lifecycleAwareScheduler, "<set-?>");
        this.scheduler = lifecycleAwareScheduler;
    }

    public final int getCurrentDrawable() {
        return this.currentDrawable;
    }

    public final void setCurrentDrawable(int i) {
        this.currentDrawable = i;
    }

    public View initialFocusedView() {
        TextView textView = getBinding$vlc_android_release().title;
        Intrinsics.checkNotNullExpressionValue(textView, "title");
        return textView;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setScheduler(new LifecycleAwareScheduler(this));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        DialogWidgetExplanationBinding inflate = DialogWidgetExplanationBinding.inflate(getLayoutInflater(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding$vlc_android_release(inflate);
        View root = getBinding$vlc_android_release().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        LifecycleAwareScheduler.scheduleAtFixedRate$default(getScheduler(), "refresh", 2000, (Bundle) null, 4, (Object) null);
        displaySizeImage(((Number) CollectionsKt.listOf(Integer.valueOf(R.drawable.vlc_widget_mini), Integer.valueOf(R.drawable.vlc_widget_micro), Integer.valueOf(R.drawable.vlc_widget_pill), Integer.valueOf(R.drawable.vlc_widget_macro)).get(0)).intValue());
        getBinding$vlc_android_release().widgetNextButton.setOnClickListener(new WidgetExplanationDialog$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(WidgetExplanationDialog widgetExplanationDialog, View view) {
        Intrinsics.checkNotNullParameter(widgetExplanationDialog, "this$0");
        int i = widgetExplanationDialog.currentStep;
        if (i == 1) {
            KotlinExtensionsKt.setGone(widgetExplanationDialog.getBinding$vlc_android_release().step1);
            KotlinExtensionsKt.setVisible(widgetExplanationDialog.getBinding$vlc_android_release().step2);
            KotlinExtensionsKt.setGone(widgetExplanationDialog.getBinding$vlc_android_release().step3);
            widgetExplanationDialog.animateLongTap();
            widgetExplanationDialog.getScheduler().cancelAction("refresh");
        } else if (i != 2) {
            widgetExplanationDialog.dismiss();
        } else {
            KotlinExtensionsKt.setGone(widgetExplanationDialog.getBinding$vlc_android_release().step1);
            KotlinExtensionsKt.setGone(widgetExplanationDialog.getBinding$vlc_android_release().step2);
            KotlinExtensionsKt.setVisible(widgetExplanationDialog.getBinding$vlc_android_release().step3);
            widgetExplanationDialog.getBinding$vlc_android_release().widgetNextButton.setText(widgetExplanationDialog.getString(R.string.close));
            AnimatorSet animatorSet = widgetExplanationDialog.resizeAnimation;
            if (animatorSet == null) {
                Intrinsics.throwUninitializedPropertyAccessException("resizeAnimation");
                animatorSet = null;
            }
            animatorSet.cancel();
        }
        widgetExplanationDialog.currentStep++;
    }

    public void onTaskTriggered(String str, Bundle bundle) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(bundle, "data");
        if (Intrinsics.areEqual((Object) str, (Object) "refresh")) {
            int intValue = this.sizeDrawables.get(SettingsKt.coerceInOrDefault(this.sizeDrawables.indexOf(Integer.valueOf(this.currentDrawable)) + 1, 0, this.sizeDrawables.size() - 1, 0)).intValue();
            this.currentDrawable = intValue;
            displaySizeImage(intValue);
        }
    }

    private final void displaySizeImage(int i) {
        getBinding$vlc_android_release().widgetSizes.setImageDrawable(ContextCompat.getDrawable(requireActivity(), i));
    }

    private final void animateLongTap() {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(getBinding$vlc_android_release().resizeLongTapIcon, View.ALPHA, new float[]{1.0f});
        ofFloat.setDuration(200);
        ofFloat.setStartDelay(2500);
        PropertyValuesHolder ofFloat2 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat2, "ofFloat(...)");
        PropertyValuesHolder ofFloat3 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{0.9f});
        Intrinsics.checkNotNullExpressionValue(ofFloat3, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(getBinding$vlc_android_release().resizeLongTapIcon, new PropertyValuesHolder[]{ofFloat2, ofFloat3});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder.setDuration(800);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(getBinding$vlc_android_release().widgetResizeHandle, View.ALPHA, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat4, "ofFloat(...)");
        ofFloat4.setDuration(200);
        ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getBinding$vlc_android_release().widgetResize.getWidth(), KotlinExtensionsKt.getDp(128)});
        ofInt.setDuration(1500);
        ofInt.setInterpolator(new DecelerateInterpolator());
        PropertyValuesHolder ofFloat5 = PropertyValuesHolder.ofFloat(View.SCALE_X, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat5, "ofFloat(...)");
        PropertyValuesHolder ofFloat6 = PropertyValuesHolder.ofFloat(View.SCALE_Y, new float[]{1.0f});
        Intrinsics.checkNotNullExpressionValue(ofFloat6, "ofFloat(...)");
        ObjectAnimator ofPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(getBinding$vlc_android_release().resizeLongTapIcon, new PropertyValuesHolder[]{ofFloat5, ofFloat6});
        Intrinsics.checkNotNullExpressionValue(ofPropertyValuesHolder2, "ofPropertyValuesHolder(...)");
        ofPropertyValuesHolder2.setDuration(300);
        ObjectAnimator ofFloat7 = ObjectAnimator.ofFloat(getBinding$vlc_android_release().resizeLongTapIcon, View.ALPHA, new float[]{0.0f});
        ofFloat7.setDuration(200);
        AnimatorSet animatorSet = new AnimatorSet();
        this.resizeAnimation = animatorSet;
        animatorSet.playSequentially(new Animator[]{ofFloat, ofPropertyValuesHolder, ofFloat4, ofPropertyValuesHolder2, ofFloat7, ofInt});
        AnimatorSet animatorSet2 = this.resizeAnimation;
        AnimatorSet animatorSet3 = null;
        if (animatorSet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resizeAnimation");
            animatorSet2 = null;
        }
        animatorSet2.addListener(new WidgetExplanationDialog$animateLongTap$$inlined$doOnEnd$1(this));
        AnimatorSet animatorSet4 = this.resizeAnimation;
        if (animatorSet4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("resizeAnimation");
        } else {
            animatorSet3 = animatorSet4;
        }
        animatorSet3.start();
    }
}
