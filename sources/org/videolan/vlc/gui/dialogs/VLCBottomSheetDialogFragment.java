package org.videolan.vlc.gui.dialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;
import android.widget.FrameLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.AndroidDevices;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.util.AccessibilityHelperKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0012H&J$\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\b\u0001\u0010\u0018\u001a\u00020\u0012J\b\u0010\u0019\u001a\u00020\u0010H&J\b\u0010\u001a\u001a\u00020\fH&J\u0010\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u00020\u000e2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0012\u0010!\u001a\u00020\"2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010#\u001a\u00020\u000e2\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\u000eH\u0016J\b\u0010'\u001a\u00020\u000eH\u0016J\b\u0010(\u001a\u00020\u000eH\u0016J\u001a\u0010)\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0016J\u0010\u0010*\u001a\u00020\u000e2\u0006\u0010+\u001a\u00020\u0012H\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "onDismissListener", "Landroid/content/DialogInterface$OnDismissListener;", "getOnDismissListener", "()Landroid/content/DialogInterface$OnDismissListener;", "setOnDismissListener", "(Landroid/content/DialogInterface$OnDismissListener;)V", "videoRemoteJob", "Lkotlinx/coroutines/Job;", "allowRemote", "", "applyOverscanMargin", "", "view", "Landroid/view/View;", "getDefaultState", "", "inflate", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "layout", "initialFocusedView", "needToManageOrientation", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onResume", "onStart", "onStop", "onViewCreated", "simulateKeyPress", "key", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VLCBottomSheetDialogFragment.kt */
public abstract class VLCBottomSheetDialogFragment extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final MutableLiveData<Boolean> shouldInterceptRemote = new MutableLiveData<>(false);
    private DialogInterface.OnDismissListener onDismissListener;
    private Job videoRemoteJob;

    public boolean allowRemote() {
        return false;
    }

    public abstract int getDefaultState();

    public abstract View initialFocusedView();

    public abstract boolean needToManageOrientation();

    public void onCreate(Bundle bundle) {
        if (Settings.INSTANCE.getShowTvUi()) {
            requireActivity().setTheme(R.style.Theme_VLC_Black_BottomSheet);
        }
        super.onCreate(bundle);
    }

    public final DialogInterface.OnDismissListener getOnDismissListener() {
        return this.onDismissListener;
    }

    public final void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
    }

    public final View inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        return layoutInflater.inflate(i, viewGroup, false);
    }

    public void onStart() {
        if (allowRemote()) {
            shouldInterceptRemote.postValue(true);
            this.videoRemoteJob = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new VLCBottomSheetDialogFragment$onStart$1(this, (Continuation<? super VLCBottomSheetDialogFragment$onStart$1>) null), 3, (Object) null);
        }
        super.onStart();
    }

    public void onStop() {
        if (allowRemote()) {
            shouldInterceptRemote.postValue(false);
        }
        Job job = this.videoRemoteJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        super.onStop();
    }

    /* access modifiers changed from: private */
    public final void applyOverscanMargin(View view) {
        view.setPadding(view.getPaddingLeft(), view.getPaddingTop(), view.getPaddingRight(), view.getPaddingBottom() + requireActivity().getResources().getDimensionPixelSize(org.videolan.resources.R.dimen.tv_overscan_vertical));
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new VLCBottomSheetDialogFragment$onViewCreated$1(this, view, (Continuation<? super VLCBottomSheetDialogFragment$onViewCreated$1>) null));
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog onCreateDialog = super.onCreateDialog(bundle);
        Intrinsics.checkNotNull(onCreateDialog, "null cannot be cast to non-null type com.google.android.material.bottomsheet.BottomSheetDialog");
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) onCreateDialog;
        if (AndroidDevices.INSTANCE.isChromeBook()) {
            bottomSheetDialog.setOnShowListener(new VLCBottomSheetDialogFragment$$ExternalSyntheticLambda0(bottomSheetDialog));
        }
        return bottomSheetDialog;
    }

    /* access modifiers changed from: private */
    public static final void onCreateDialog$lambda$1(BottomSheetDialog bottomSheetDialog, DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(bottomSheetDialog, "$bottomSheetDialog");
        FrameLayout frameLayout = (FrameLayout) bottomSheetDialog.findViewById(com.google.android.material.R.id.design_bottom_sheet);
        if (frameLayout != null) {
            BottomSheetBehavior.from(frameLayout).setDraggable(false);
        }
    }

    /* access modifiers changed from: private */
    public final void simulateKeyPress(int i) {
        View view = getView();
        if (view != null) {
            BaseInputConnection baseInputConnection = new BaseInputConnection(view, true);
            KeyEvent keyEvent = new KeyEvent(0, i);
            KeyEvent keyEvent2 = new KeyEvent(1, i);
            baseInputConnection.sendKeyEvent(keyEvent);
            baseInputConnection.sendKeyEvent(keyEvent2);
        }
    }

    public void onDismiss(DialogInterface dialogInterface) {
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        super.onDismiss(dialogInterface);
        DialogInterface.OnDismissListener onDismissListener2 = this.onDismissListener;
        if (onDismissListener2 != null) {
            onDismissListener2.onDismiss(dialogInterface);
        }
    }

    public void onResume() {
        super.onResume();
        initialFocusedView().setFocusable(true);
        initialFocusedView().setFocusableInTouchMode(true);
        initialFocusedView().requestFocus();
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        if (AccessibilityHelperKt.isTalkbackIsEnabled(requireActivity)) {
            initialFocusedView().sendAccessibilityEvent(8);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        if (!needToManageOrientation()) {
            super.onConfigurationChanged(configuration);
            return;
        }
        if (isAdded()) {
            dismiss();
        }
        super.onConfigurationChanged(configuration);
        if (isAdded()) {
            show(getParentFragmentManager().beginTransaction().setReorderingAllowed(false), getTag());
        }
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001f\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment$Companion;", "", "()V", "shouldInterceptRemote", "Landroidx/lifecycle/MutableLiveData;", "", "kotlin.jvm.PlatformType", "getShouldInterceptRemote", "()Landroidx/lifecycle/MutableLiveData;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VLCBottomSheetDialogFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MutableLiveData<Boolean> getShouldInterceptRemote() {
            return VLCBottomSheetDialogFragment.shouldInterceptRemote;
        }
    }
}
