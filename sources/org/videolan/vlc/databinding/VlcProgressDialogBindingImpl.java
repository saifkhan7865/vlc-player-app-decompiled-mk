package org.videolan.vlc.databinding;

import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.libvlc.Dialog;
import org.videolan.vlc.BR;
import org.videolan.vlc.gui.dialogs.VlcProgressDialog;

public class VlcProgressDialogBindingImpl extends VlcProgressDialogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mHandlerOnCancelAndroidViewViewOnClickListener;
    private final ScrollView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public VlcProgressDialogBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private VlcProgressDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        this.cancel.setTag((Object) null);
        ScrollView scrollView = objArr[0];
        this.mboundView0 = scrollView;
        scrollView.setTag((Object) null);
        this.progress.setTag((Object) null);
        this.text.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 4;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, Object obj) {
        if (BR.handler == i) {
            setHandler((VlcProgressDialog) obj);
        } else if (BR.dialog != i) {
            return false;
        } else {
            setDialog((Dialog.ProgressDialog) obj);
        }
        return true;
    }

    public void setHandler(VlcProgressDialog vlcProgressDialog) {
        this.mHandler = vlcProgressDialog;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    public void setDialog(Dialog.ProgressDialog progressDialog) {
        this.mDialog = progressDialog;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.dialog);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        OnClickListenerImpl onClickListenerImpl;
        boolean z;
        String str;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        VlcProgressDialog vlcProgressDialog = this.mHandler;
        Dialog.ProgressDialog progressDialog = this.mDialog;
        String str2 = null;
        if ((j & 5) == 0 || vlcProgressDialog == null) {
            onClickListenerImpl = null;
        } else {
            OnClickListenerImpl onClickListenerImpl2 = this.mHandlerOnCancelAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mHandlerOnCancelAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(vlcProgressDialog);
        }
        long j2 = j & 6;
        int i = 0;
        if (j2 != 0) {
            if (progressDialog != null) {
                boolean isIndeterminate = progressDialog.isIndeterminate();
                String cancelText = progressDialog.getCancelText();
                str = progressDialog.getText();
                z = isIndeterminate;
                str2 = cancelText;
            } else {
                str = null;
                z = false;
            }
            boolean isEmpty = TextUtils.isEmpty(str2);
            if (j2 != 0) {
                j |= isEmpty ? 16 : 8;
            }
            if (isEmpty) {
                i = 8;
            }
            str2 = str;
        } else {
            z = false;
        }
        if ((5 & j) != 0) {
            this.cancel.setOnClickListener(onClickListenerImpl);
        }
        if ((j & 6) != 0) {
            this.cancel.setVisibility(i);
            this.progress.setIndeterminate(z);
            TextViewBindingAdapter.setText(this.text, str2);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private VlcProgressDialog value;

        public OnClickListenerImpl setValue(VlcProgressDialog vlcProgressDialog) {
            this.value = vlcProgressDialog;
            if (vlcProgressDialog == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onCancel(view);
        }
    }
}
