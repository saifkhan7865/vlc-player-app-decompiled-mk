package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class DialogTimePickerBindingImpl extends DialogTimePickerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

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
        sparseIntArray.put(R.id.tim_pic_title, 1);
        sparseIntArray.put(R.id.tim_pic_timetojump, 2);
        sparseIntArray.put(R.id.tim_pic_delete, 3);
        sparseIntArray.put(R.id.linearLayout2, 4);
        sparseIntArray.put(R.id.tim_pic_1, 5);
        sparseIntArray.put(R.id.tim_pic_2, 6);
        sparseIntArray.put(R.id.tim_pic_3, 7);
        sparseIntArray.put(R.id.linearLayout3, 8);
        sparseIntArray.put(R.id.tim_pic_4, 9);
        sparseIntArray.put(R.id.tim_pic_5, 10);
        sparseIntArray.put(R.id.tim_pic_6, 11);
        sparseIntArray.put(R.id.linearLayout4, 12);
        sparseIntArray.put(R.id.tim_pic_7, 13);
        sparseIntArray.put(R.id.tim_pic_8, 14);
        sparseIntArray.put(R.id.tim_pic_9, 15);
        sparseIntArray.put(R.id.linearLayout5, 16);
        sparseIntArray.put(R.id.tim_pic_00, 17);
        sparseIntArray.put(R.id.tim_pic_0, 18);
        sparseIntArray.put(R.id.tim_pic_30, 19);
        sparseIntArray.put(R.id.tim_pic_wait_checkbox, 20);
        sparseIntArray.put(R.id.tim_pic_reset_checkbox, 21);
        sparseIntArray.put(R.id.tim_pic_delete_current, 22);
        sparseIntArray.put(R.id.tim_pic_ok, 23);
    }

    public DialogTimePickerBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 24, sIncludes, sViewsWithIds));
    }

    private DialogTimePickerBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[4], objArr[8], objArr[12], objArr[16], objArr[18], objArr[17], objArr[5], objArr[6], objArr[7], objArr[19], objArr[9], objArr[10], objArr[11], objArr[13], objArr[14], objArr[15], objArr[3], objArr[22], objArr[23], objArr[21], objArr[2], objArr[1], objArr[20]);
        this.mDirtyFlags = -1;
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
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
