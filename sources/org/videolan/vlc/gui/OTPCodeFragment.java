package org.videolan.vlc.gui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.view.ActionMode;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.OtpCodeBinding;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\b\u0010\b\u001a\u00020\tH\u0002J\u001c\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J\u0012\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0012\u0010\u001c\u001a\u00020\t2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u001a\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00172\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/videolan/vlc/gui/OTPCodeFragment;", "Lorg/videolan/vlc/gui/BaseFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/OtpCodeBinding;", "code", "", "getTitle", "manageCodeViews", "", "onActionItemClicked", "", "mode", "Landroidx/appcompat/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyActionMode", "onViewCreated", "view", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: OTPCodeFragment.kt */
public final class OTPCodeFragment extends BaseFragment {
    private OtpCodeBinding binding;
    /* access modifiers changed from: private */
    public String code;

    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return false;
    }

    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return false;
    }

    public void onDestroyActionMode(ActionMode actionMode) {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(requireActivity(), R.layout.otp_code);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        this.binding = (OtpCodeBinding) contentView;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new OTPCodeFragment$onCreate$1(this, (Continuation<? super OTPCodeFragment$onCreate$1>) null), 3, (Object) null);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        OtpCodeBinding inflate = OtpCodeBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        View root = inflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public String getTitle() {
        return "";
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        KotlinExtensionsKt.setGone(requireActivity().findViewById(R.id.fab));
        KotlinExtensionsKt.setGone(requireActivity().findViewById(R.id.sliding_tabs));
        OtpCodeBinding otpCodeBinding = this.binding;
        if (otpCodeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            otpCodeBinding = null;
        }
        otpCodeBinding.cancelButton.setOnClickListener(new OTPCodeFragment$$ExternalSyntheticLambda0(this));
    }

    /* access modifiers changed from: private */
    public static final void onViewCreated$lambda$0(OTPCodeFragment oTPCodeFragment, View view) {
        Intrinsics.checkNotNullParameter(oTPCodeFragment, "this$0");
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(oTPCodeFragment), (CoroutineContext) null, (CoroutineStart) null, new OTPCodeFragment$onViewCreated$1$1((Continuation<? super OTPCodeFragment$onViewCreated$1$1>) null), 3, (Object) null);
        oTPCodeFragment.requireActivity().finish();
    }

    /* access modifiers changed from: private */
    public final void manageCodeViews() {
        String str = this.code;
        if (str != null) {
            OtpCodeBinding otpCodeBinding = this.binding;
            OtpCodeBinding otpCodeBinding2 = null;
            if (otpCodeBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                otpCodeBinding = null;
            }
            TextView textView = otpCodeBinding.code1;
            String substring = str.substring(0, 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            textView.setText(substring);
            OtpCodeBinding otpCodeBinding3 = this.binding;
            if (otpCodeBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                otpCodeBinding3 = null;
            }
            TextView textView2 = otpCodeBinding3.code2;
            String substring2 = str.substring(1, 2);
            Intrinsics.checkNotNullExpressionValue(substring2, "substring(...)");
            textView2.setText(substring2);
            OtpCodeBinding otpCodeBinding4 = this.binding;
            if (otpCodeBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                otpCodeBinding4 = null;
            }
            TextView textView3 = otpCodeBinding4.code3;
            String substring3 = str.substring(2, 3);
            Intrinsics.checkNotNullExpressionValue(substring3, "substring(...)");
            textView3.setText(substring3);
            OtpCodeBinding otpCodeBinding5 = this.binding;
            if (otpCodeBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                otpCodeBinding2 = otpCodeBinding5;
            }
            TextView textView4 = otpCodeBinding2.code4;
            String substring4 = str.substring(3, 4);
            Intrinsics.checkNotNullExpressionValue(substring4, "substring(...)");
            textView4.setText(substring4);
        }
    }
}
