package org.videolan.vlc.webserver.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.webserver.R;

public class RemoteAccessShareActivityBindingImpl extends RemoteAccessShareActivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

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
        sparseIntArray.put(R.id.licenses, 1);
        sparseIntArray.put(R.id.status_title, 2);
        sparseIntArray.put(R.id.server_status, 3);
        sparseIntArray.put(R.id.status_button, 4);
        sparseIntArray.put(R.id.links_title, 5);
        sparseIntArray.put(R.id.links_grid, 6);
        sparseIntArray.put(R.id.remote_access_qr_code, 7);
        sparseIntArray.put(R.id.connection_title, 8);
        sparseIntArray.put(R.id.connection_list, 9);
    }

    public RemoteAccessShareActivityBindingImpl(DataBindingComponent dataBindingComponent, View[] viewArr) {
        this(dataBindingComponent, viewArr, mapBindings(dataBindingComponent, viewArr, 10, sIncludes, sViewsWithIds));
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private RemoteAccessShareActivityBindingImpl(androidx.databinding.DataBindingComponent r17, android.view.View[] r18, java.lang.Object[] r19) {
        /*
            r16 = this;
            r14 = r16
            r15 = r18
            r0 = 0
            r2 = r15[r0]
            r1 = 9
            r1 = r19[r1]
            r4 = r1
            androidx.recyclerview.widget.RecyclerView r4 = (androidx.recyclerview.widget.RecyclerView) r4
            r1 = 8
            r1 = r19[r1]
            r5 = r1
            android.widget.TextView r5 = (android.widget.TextView) r5
            r0 = r19[r0]
            r6 = r0
            androidx.coordinatorlayout.widget.CoordinatorLayout r6 = (androidx.coordinatorlayout.widget.CoordinatorLayout) r6
            r0 = 1
            r0 = r19[r0]
            r7 = r0
            androidx.core.widget.NestedScrollView r7 = (androidx.core.widget.NestedScrollView) r7
            r0 = 6
            r0 = r19[r0]
            r8 = r0
            androidx.gridlayout.widget.GridLayout r8 = (androidx.gridlayout.widget.GridLayout) r8
            r0 = 5
            r0 = r19[r0]
            r9 = r0
            android.widget.TextView r9 = (android.widget.TextView) r9
            r0 = 7
            r0 = r19[r0]
            r10 = r0
            android.widget.ImageView r10 = (android.widget.ImageView) r10
            r0 = 3
            r0 = r19[r0]
            r11 = r0
            android.widget.TextView r11 = (android.widget.TextView) r11
            r0 = 4
            r0 = r19[r0]
            r12 = r0
            android.widget.Button r12 = (android.widget.Button) r12
            r0 = 2
            r0 = r19[r0]
            r13 = r0
            android.widget.TextView r13 = (android.widget.TextView) r13
            r3 = 0
            r0 = r16
            r1 = r17
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r0 = -1
            r14.mDirtyFlags = r0
            androidx.coordinatorlayout.widget.CoordinatorLayout r0 = r14.coordinator
            r1 = 0
            r0.setTag(r1)
            r14.setRootTag((android.view.View[]) r15)
            r16.invalidateAll()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBindingImpl.<init>(androidx.databinding.DataBindingComponent, android.view.View[], java.lang.Object[]):void");
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
