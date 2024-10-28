package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatDialog;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.BR;
import org.videolan.vlc.gui.DialogActivity;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J&\u0010 \u001a\u0004\u0018\u00010\u001b2\u0006\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010%\u001a\u00020\u0018H\u0016R\u001c\u0010\u0007\u001a\u00028\u0001X.¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0012\u0010\r\u001a\u00020\u000eX¤\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u00028\u0000X.¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006&"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/VlcDialog;", "T", "Lorg/videolan/libvlc/Dialog;", "B", "Landroidx/databinding/ViewDataBinding;", "Landroidx/fragment/app/DialogFragment;", "()V", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "layout", "", "getLayout", "()I", "vlcDialog", "getVlcDialog", "()Lorg/videolan/libvlc/Dialog;", "setVlcDialog", "(Lorg/videolan/libvlc/Dialog;)V", "Lorg/videolan/libvlc/Dialog;", "dismiss", "", "onCancel", "v", "Landroid/view/View;", "onCreateDialog", "Landroid/app/Dialog;", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroy", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VlcDialog.kt */
public abstract class VlcDialog<T extends Dialog, B extends ViewDataBinding> extends DialogFragment {
    protected B binding;
    public T vlcDialog;

    /* access modifiers changed from: protected */
    public abstract int getLayout();

    public final T getVlcDialog() {
        T t = this.vlcDialog;
        if (t != null) {
            return t;
        }
        Intrinsics.throwUninitializedPropertyAccessException(DialogActivity.KEY_DIALOG);
        return null;
    }

    public final void setVlcDialog(T t) {
        Intrinsics.checkNotNullParameter(t, "<set-?>");
        this.vlcDialog = t;
    }

    /* access modifiers changed from: protected */
    public final B getBinding() {
        B b = this.binding;
        if (b != null) {
            return b;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setBinding(B b) {
        Intrinsics.checkNotNullParameter(b, "<set-?>");
        this.binding = b;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, getLayout(), viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        getBinding().setVariable(BR.dialog, getVlcDialog());
        getBinding().setVariable(BR.handler, this);
        return getBinding().getRoot();
    }

    public android.app.Dialog onCreateDialog(Bundle bundle) {
        setRetainInstance(true);
        AppCompatDialog appCompatDialog = new AppCompatDialog(requireActivity(), getTheme());
        appCompatDialog.setCancelable(true);
        appCompatDialog.setCanceledOnTouchOutside(true);
        if (this.vlcDialog != null) {
            getVlcDialog().setContext(this);
            appCompatDialog.setTitle((CharSequence) getVlcDialog().getTitle());
        }
        return appCompatDialog;
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.vlcDialog != null) {
            getVlcDialog().dismiss();
        }
        FragmentActivity activity = getActivity();
        DialogActivity dialogActivity = activity instanceof DialogActivity ? (DialogActivity) activity : null;
        if (dialogActivity != null) {
            dialogActivity.finish();
        }
    }

    public void onCancel(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        dismiss();
    }

    public void dismiss() {
        if (isResumed()) {
            super.dismiss();
        }
    }
}
