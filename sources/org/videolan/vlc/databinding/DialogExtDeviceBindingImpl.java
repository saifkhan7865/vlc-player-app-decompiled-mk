package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.ExtDeviceHandler;

public class DialogExtDeviceBindingImpl extends DialogExtDeviceBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHandlerBrowseAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHandlerCancelAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHandlerScanAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.ext_device_summary, 4);
    }

    public DialogExtDeviceBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private DialogExtDeviceBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[1], objArr[2], objArr[4]);
        this.mDirtyFlags = -1;
        this.extDeviceCancel.setTag((Object) null);
        this.extDeviceOpen.setTag((Object) null);
        this.extDeviceScan.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.handler != i) {
            return false;
        }
        setHandler((ExtDeviceHandler) obj);
        return true;
    }

    public void setHandler(ExtDeviceHandler extDeviceHandler) {
        this.mHandler = extDeviceHandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        OnClickListenerImpl1 onClickListenerImpl1;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl2 onClickListenerImpl2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ExtDeviceHandler extDeviceHandler = this.mHandler;
        long j2 = j & 3;
        if (j2 == 0 || extDeviceHandler == null) {
            onClickListenerImpl = null;
            onClickListenerImpl2 = null;
            onClickListenerImpl1 = null;
        } else {
            OnClickListenerImpl onClickListenerImpl3 = this.mHandlerBrowseAndroidViewViewOnClickListener;
            if (onClickListenerImpl3 == null) {
                onClickListenerImpl3 = new OnClickListenerImpl();
                this.mHandlerBrowseAndroidViewViewOnClickListener = onClickListenerImpl3;
            }
            onClickListenerImpl = onClickListenerImpl3.setValue(extDeviceHandler);
            OnClickListenerImpl1 onClickListenerImpl12 = this.mHandlerCancelAndroidViewViewOnClickListener;
            if (onClickListenerImpl12 == null) {
                onClickListenerImpl12 = new OnClickListenerImpl1();
                this.mHandlerCancelAndroidViewViewOnClickListener = onClickListenerImpl12;
            }
            onClickListenerImpl1 = onClickListenerImpl12.setValue(extDeviceHandler);
            OnClickListenerImpl2 onClickListenerImpl22 = this.mHandlerScanAndroidViewViewOnClickListener;
            if (onClickListenerImpl22 == null) {
                onClickListenerImpl22 = new OnClickListenerImpl2();
                this.mHandlerScanAndroidViewViewOnClickListener = onClickListenerImpl22;
            }
            onClickListenerImpl2 = onClickListenerImpl22.setValue(extDeviceHandler);
        }
        if (j2 != 0) {
            this.extDeviceCancel.setOnClickListener(onClickListenerImpl1);
            this.extDeviceOpen.setOnClickListener(onClickListenerImpl);
            this.extDeviceScan.setOnClickListener(onClickListenerImpl2);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private ExtDeviceHandler value;

        public OnClickListenerImpl setValue(ExtDeviceHandler extDeviceHandler) {
            this.value = extDeviceHandler;
            if (extDeviceHandler == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.browse(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private ExtDeviceHandler value;

        public OnClickListenerImpl1 setValue(ExtDeviceHandler extDeviceHandler) {
            this.value = extDeviceHandler;
            if (extDeviceHandler == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.cancel(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private ExtDeviceHandler value;

        public OnClickListenerImpl2 setValue(ExtDeviceHandler extDeviceHandler) {
            this.value = extDeviceHandler;
            if (extDeviceHandler == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.scan(view);
        }
    }
}
