package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.ScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.libvlc.Dialog;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.VlcLoginDialog;

public class VlcLoginDialogBindingImpl extends VlcLoginDialogBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl1 mHandlerOnCancelAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHandlerOnLoginAndroidViewViewOnClickListener;
    private final ScrollView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.login_container, 7);
        sparseIntArray.put(R.id.password_container, 8);
        sparseIntArray.put(R.id.password, 9);
    }

    public VlcLoginDialogBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 10, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private VlcLoginDialogBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[6], objArr[5], objArr[2], objArr[7], objArr[9], objArr[8], objArr[3], objArr[1], objArr[4]);
        this.mDirtyFlags = -1;
        this.action.setTag((Object) null);
        this.cancel.setTag((Object) null);
        this.login.setTag((Object) null);
        ScrollView scrollView = objArr[0];
        this.mboundView0 = scrollView;
        scrollView.setTag((Object) null);
        this.store.setTag((Object) null);
        this.text.setTag((Object) null);
        this.warning.setTag((Object) null);
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
            setHandler((VlcLoginDialog) obj);
        } else if (BR.dialog != i) {
            return false;
        } else {
            setDialog((Dialog.LoginDialog) obj);
        }
        return true;
    }

    public void setHandler(VlcLoginDialog vlcLoginDialog) {
        this.mHandler = vlcLoginDialog;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    public void setDialog(Dialog.LoginDialog loginDialog) {
        this.mDialog = loginDialog;
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
        OnClickListenerImpl1 onClickListenerImpl1;
        int i;
        String str;
        String str2;
        boolean z2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        VlcLoginDialog vlcLoginDialog = this.mHandler;
        Dialog.LoginDialog loginDialog = this.mDialog;
        String str3 = null;
        int i2 = 0;
        if ((j & 5) == 0 || vlcLoginDialog == null) {
            onClickListenerImpl1 = null;
            onClickListenerImpl = null;
            z = false;
        } else {
            z = vlcLoginDialog.store();
            OnClickListenerImpl onClickListenerImpl2 = this.mHandlerOnLoginAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mHandlerOnLoginAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(vlcLoginDialog);
            OnClickListenerImpl1 onClickListenerImpl12 = this.mHandlerOnCancelAndroidViewViewOnClickListener;
            if (onClickListenerImpl12 == null) {
                onClickListenerImpl12 = new OnClickListenerImpl1();
                this.mHandlerOnCancelAndroidViewViewOnClickListener = onClickListenerImpl12;
            }
            onClickListenerImpl1 = onClickListenerImpl12.setValue(vlcLoginDialog);
        }
        long j2 = j & 6;
        if (j2 != 0) {
            if (loginDialog != null) {
                z2 = loginDialog.asksStore();
                str2 = loginDialog.getDefaultUsername();
                str = loginDialog.getText();
            } else {
                str = null;
                str2 = null;
                z2 = false;
            }
            if (j2 != 0) {
                j |= z2 ? 272 : 136;
            }
            int i3 = z2 ? 0 : 8;
            boolean z3 = z2 && !AndroidUtil.isMarshMallowOrLater;
            if ((j & 6) != 0) {
                j |= z3 ? 64 : 32;
            }
            if (!z3) {
                i2 = 8;
            }
            i = i2;
            i2 = i3;
            str3 = str2;
        } else {
            str = null;
            i = 0;
        }
        if ((5 & j) != 0) {
            this.action.setOnClickListener(onClickListenerImpl);
            this.cancel.setOnClickListener(onClickListenerImpl1);
            CompoundButtonBindingAdapter.setChecked(this.store, z);
        }
        if ((j & 6) != 0) {
            TextViewBindingAdapter.setText(this.login, str3);
            this.store.setVisibility(i2);
            TextViewBindingAdapter.setText(this.text, str);
            this.warning.setVisibility(i);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private VlcLoginDialog value;

        public OnClickListenerImpl setValue(VlcLoginDialog vlcLoginDialog) {
            this.value = vlcLoginDialog;
            if (vlcLoginDialog == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onLogin(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private VlcLoginDialog value;

        public OnClickListenerImpl1 setValue(VlcLoginDialog vlcLoginDialog) {
            this.value = vlcLoginDialog;
            if (vlcLoginDialog == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onCancel(view);
        }
    }
}
