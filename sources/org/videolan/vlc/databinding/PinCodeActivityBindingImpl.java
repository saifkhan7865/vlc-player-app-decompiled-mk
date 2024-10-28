package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class PinCodeActivityBindingImpl extends PinCodeActivityBinding {
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
        sparseIntArray.put(R.id.pin_code_reason, 1);
        sparseIntArray.put(R.id.pin_code_title, 2);
        sparseIntArray.put(R.id.sucess_title, 3);
        sparseIntArray.put(R.id.imageView18, 4);
        sparseIntArray.put(R.id.pin_group, 5);
        sparseIntArray.put(R.id.sucess_group, 6);
        sparseIntArray.put(R.id.pin_code_parent1, 7);
        sparseIntArray.put(R.id.pin_code1, 8);
        sparseIntArray.put(R.id.pin_code_parent2, 9);
        sparseIntArray.put(R.id.pin_code2, 10);
        sparseIntArray.put(R.id.pin_code_parent3, 11);
        sparseIntArray.put(R.id.pin_code3, 12);
        sparseIntArray.put(R.id.pin_code_parent4, 13);
        sparseIntArray.put(R.id.pin_code4, 14);
        sparseIntArray.put(R.id.keyboard_grid, 15);
        sparseIntArray.put(R.id.keyboard_button_1, 16);
        sparseIntArray.put(R.id.keyboard_button_2, 17);
        sparseIntArray.put(R.id.keyboard_button_3, 18);
        sparseIntArray.put(R.id.keyboard_button_4, 19);
        sparseIntArray.put(R.id.keyboard_button_5, 20);
        sparseIntArray.put(R.id.keyboard_button_6, 21);
        sparseIntArray.put(R.id.keyboard_button_7, 22);
        sparseIntArray.put(R.id.keyboard_button_8, 23);
        sparseIntArray.put(R.id.keyboard_button_9, 24);
        sparseIntArray.put(R.id.keyboard_button_0, 25);
        sparseIntArray.put(R.id.keyboard_button_clear, 26);
        sparseIntArray.put(R.id.cancel_button, 27);
        sparseIntArray.put(R.id.next_button, 28);
    }

    public PinCodeActivityBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 29, sIncludes, sViewsWithIds));
    }

    private PinCodeActivityBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[27], objArr[4], objArr[25], objArr[16], objArr[17], objArr[18], objArr[19], objArr[20], objArr[21], objArr[22], objArr[23], objArr[24], objArr[26], objArr[15], objArr[28], objArr[8], objArr[10], objArr[12], objArr[14], objArr[7], objArr[9], objArr[11], objArr[13], objArr[1], objArr[2], objArr[5], objArr[6], objArr[3]);
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
