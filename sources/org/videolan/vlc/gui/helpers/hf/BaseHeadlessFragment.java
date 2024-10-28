package org.videolan.vlc.gui.helpers.hf;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentViewModelLazyKt;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0015\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0004J\u0012\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f*\u00060\u0010j\u0002`\u0011H\u0004R\u001b\u0010\u0003\u001a\u00020\u00048DX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, d2 = {"Lorg/videolan/vlc/gui/helpers/hf/BaseHeadlessFragment;", "Landroidx/fragment/app/Fragment;", "()V", "model", "Lorg/videolan/vlc/gui/helpers/hf/PermissionViewmodel;", "getModel", "()Lorg/videolan/vlc/gui/helpers/hf/PermissionViewmodel;", "model$delegate", "Lkotlin/Lazy;", "exit", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "granted", "", "", "Lorg/videolan/vlc/gui/helpers/hf/PermissionResults;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseHeadlessFragment.kt */
public class BaseHeadlessFragment extends Fragment {
    private final Lazy model$delegate;

    public BaseHeadlessFragment() {
        Fragment fragment = this;
        this.model$delegate = FragmentViewModelLazyKt.createViewModelLazy(fragment, Reflection.getOrCreateKotlinClass(PermissionViewmodel.class), new BaseHeadlessFragment$special$$inlined$activityViewModels$default$1(fragment), new BaseHeadlessFragment$special$$inlined$activityViewModels$default$2((Function0) null, fragment), new BaseHeadlessFragment$special$$inlined$activityViewModels$default$3(fragment));
    }

    /* access modifiers changed from: protected */
    public final PermissionViewmodel getModel() {
        return (PermissionViewmodel) this.model$delegate.getValue();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
    }

    /* access modifiers changed from: protected */
    public final void exit() {
        setRetainInstance(false);
        FragmentActivity activity = getActivity();
        if (activity != null && !activity.isFinishing()) {
            activity.getSupportFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
        }
    }

    /* access modifiers changed from: protected */
    public final boolean granted(int[] iArr) {
        Intrinsics.checkNotNullParameter(iArr, "<this>");
        return ((iArr.length == 0) ^ true) && iArr[0] == 0;
    }
}
