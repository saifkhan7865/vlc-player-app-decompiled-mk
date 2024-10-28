package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class DialogWidgetExplanationBindingImpl extends DialogWidgetExplanationBinding {
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
        sparseIntArray.put(R.id.step1, 1);
        sparseIntArray.put(R.id.step2, 2);
        sparseIntArray.put(R.id.step3, 3);
        sparseIntArray.put(R.id.title, 4);
        sparseIntArray.put(R.id.textView32, 5);
        sparseIntArray.put(R.id.widget_sizes, 6);
        sparseIntArray.put(R.id.widget_resize_text, 7);
        sparseIntArray.put(R.id.widget_resize, 8);
        sparseIntArray.put(R.id.widget_resize_handle, 9);
        sparseIntArray.put(R.id.resizeLongTapIcon, 10);
        sparseIntArray.put(R.id.imageView19, 11);
        sparseIntArray.put(R.id.widget_explanation_step3_text, 12);
        sparseIntArray.put(R.id.widget_next_button, 13);
    }

    public DialogWidgetExplanationBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private DialogWidgetExplanationBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[11], objArr[10], objArr[1], objArr[2], objArr[3], objArr[5], objArr[4], objArr[12], objArr[13], objArr[8], objArr[9], objArr[7], objArr[6]);
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
