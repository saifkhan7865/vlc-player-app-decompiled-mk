package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class SendCrashActivityBindingImpl extends SendCrashActivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView15, 1);
        sparseIntArray.put(R.id.imageView5, 2);
        sparseIntArray.put(R.id.crashFirstStepContainer, 3);
        sparseIntArray.put(R.id.textView21, 4);
        sparseIntArray.put(R.id.reportBugButton, 5);
        sparseIntArray.put(R.id.reportCrashButton, 6);
        sparseIntArray.put(R.id.crashSecondStepContainer, 7);
        sparseIntArray.put(R.id.textView18, 8);
        sparseIntArray.put(R.id.textView19, 9);
        sparseIntArray.put(R.id.includeMedialibSwitch, 10);
        sparseIntArray.put(R.id.send_crash_button, 11);
        sparseIntArray.put(R.id.sendCrashProgress, 12);
    }

    public SendCrashActivityBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private SendCrashActivityBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[7], objArr[2], objArr[10], objArr[5], objArr[6], objArr[11], objArr[12], objArr[1], objArr[8], objArr[9], objArr[4]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 1;
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

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
    }
}
