package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class DialogPlaybackSpeedBindingImpl extends DialogPlaybackSpeedBinding {
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
        sparseIntArray.put(R.id.textView12, 1);
        sparseIntArray.put(R.id.textView13, 2);
        sparseIntArray.put(R.id.textView14, 3);
        sparseIntArray.put(R.id.playback_speed_value, 4);
        sparseIntArray.put(R.id.button_speed_minus, 5);
        sparseIntArray.put(R.id.button_speed_plus, 6);
        sparseIntArray.put(R.id.button_speed_08, 7);
        sparseIntArray.put(R.id.button_speed_1, 8);
        sparseIntArray.put(R.id.button_speed_125, 9);
        sparseIntArray.put(R.id.button_speed_15, 10);
        sparseIntArray.put(R.id.button_speed_2, 11);
        sparseIntArray.put(R.id.playback_speed_seek, 12);
        sparseIntArray.put(R.id.stream_warning, 13);
    }

    public DialogPlaybackSpeedBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private DialogPlaybackSpeedBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[7], objArr[8], objArr[9], objArr[10], objArr[11], objArr[5], objArr[6], objArr[12], objArr[4], objArr[13], objArr[1], objArr[2], objArr[3]);
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
