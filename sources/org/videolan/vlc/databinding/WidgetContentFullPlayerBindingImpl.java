package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class WidgetContentFullPlayerBindingImpl extends WidgetContentFullPlayerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

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
        sparseIntArray.put(R.id.widget_left_space, 1);
        sparseIntArray.put(R.id.backward, 2);
        sparseIntArray.put(R.id.seek_rewind, 3);
        sparseIntArray.put(R.id.seek_rewind_text, 4);
        sparseIntArray.put(R.id.play_pause, 5);
        sparseIntArray.put(R.id.progress_round, 6);
        sparseIntArray.put(R.id.seek_forward, 7);
        sparseIntArray.put(R.id.seek_forward_text, 8);
        sparseIntArray.put(R.id.forward, 9);
        sparseIntArray.put(R.id.widget_right_space, 10);
    }

    public WidgetContentFullPlayerBindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 11, sIncludes, sViewsWithIds));
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private WidgetContentFullPlayerBindingImpl(androidx.databinding.DataBindingComponent r18, android.view.View[] r19, java.lang.Object[] r20) {
        /*
            r17 = this;
            r14 = r17
            r15 = r19
            r16 = 0
            r2 = r15[r16]
            r0 = 2
            r0 = r20[r0]
            r4 = r0
            android.widget.ImageButton r4 = (android.widget.ImageButton) r4
            r0 = 9
            r0 = r20[r0]
            r5 = r0
            android.widget.ImageButton r5 = (android.widget.ImageButton) r5
            r0 = 5
            r0 = r20[r0]
            r6 = r0
            android.widget.ImageButton r6 = (android.widget.ImageButton) r6
            r0 = 6
            r0 = r20[r0]
            r7 = r0
            android.widget.ImageView r7 = (android.widget.ImageView) r7
            r0 = 7
            r0 = r20[r0]
            r8 = r0
            android.widget.ImageButton r8 = (android.widget.ImageButton) r8
            r0 = 8
            r0 = r20[r0]
            r9 = r0
            android.widget.TextView r9 = (android.widget.TextView) r9
            r0 = 3
            r0 = r20[r0]
            r10 = r0
            android.widget.ImageButton r10 = (android.widget.ImageButton) r10
            r0 = 4
            r0 = r20[r0]
            r11 = r0
            android.widget.TextView r11 = (android.widget.TextView) r11
            r0 = 1
            r0 = r20[r0]
            r12 = r0
            android.widget.RelativeLayout r12 = (android.widget.RelativeLayout) r12
            r0 = 10
            r0 = r20[r0]
            r13 = r0
            android.widget.RelativeLayout r13 = (android.widget.RelativeLayout) r13
            r3 = 0
            r0 = r17
            r1 = r18
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r0 = -1
            r14.mDirtyFlags = r0
            r0 = r20[r16]
            android.widget.LinearLayout r0 = (android.widget.LinearLayout) r0
            r14.mboundView0 = r0
            r1 = 0
            r0.setTag(r1)
            r14.setRootTag((android.view.View[]) r15)
            r17.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.WidgetContentFullPlayerBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View[], java.lang.Object[]):void");
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
