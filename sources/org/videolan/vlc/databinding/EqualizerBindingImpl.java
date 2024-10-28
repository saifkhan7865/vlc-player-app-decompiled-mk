package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.EqualizerFragment;

public class EqualizerBindingImpl extends EqualizerBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.equalizer_container, 4);
        sparseIntArray.put(R.id.eq_title, 5);
        sparseIntArray.put(R.id.equalizer_button, 6);
        sparseIntArray.put(R.id.equalizer_presets, 7);
        sparseIntArray.put(R.id.textView10, 8);
        sparseIntArray.put(R.id.textView11, 9);
        sparseIntArray.put(R.id.equalizer_preamp, 10);
        sparseIntArray.put(R.id.equalizer_bands, 11);
        sparseIntArray.put(R.id.snapBands, 12);
    }

    public EqualizerBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private EqualizerBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[5], objArr[11], objArr[6], objArr[4], objArr[1], objArr[10], objArr[7], objArr[2], objArr[3], objArr[12], objArr[8], objArr[9]);
        this.mDirtyFlags = -1;
        this.equalizerDelete.setTag((Object) null);
        this.equalizerRevert.setTag((Object) null);
        this.equalizerSave.setTag((Object) null);
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 16;
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
        if (BR.state != i) {
            return false;
        }
        setState((EqualizerFragment.EqualizerState) obj);
        return true;
    }

    public void setState(EqualizerFragment.EqualizerState equalizerState) {
        this.mState = equalizerState;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.state);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeStateSaveButtonVisibility((ObservableBoolean) obj, i2);
        }
        if (i == 1) {
            return onChangeStateDeleteButtonVisibility((ObservableBoolean) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeStateRevertButtonVisibility((ObservableBoolean) obj, i2);
    }

    private boolean onChangeStateSaveButtonVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeStateDeleteButtonVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeStateRevertButtonVisibility(ObservableBoolean observableBoolean, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r19 = this;
            r1 = r19
            monitor-enter(r19)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x008c }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x008c }
            monitor-exit(r19)     // Catch:{ all -> 0x008c }
            org.videolan.vlc.gui.audio.EqualizerFragment$EqualizerState r0 = r1.mState
            r6 = 31
            long r6 = r6 & r2
            r8 = 28
            r10 = 25
            r12 = 26
            r14 = 0
            int r15 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x006a
            long r6 = r2 & r10
            r15 = 0
            int r16 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x0033
            if (r0 == 0) goto L_0x0028
            androidx.databinding.ObservableBoolean r6 = r0.getSaveButtonVisibility()
            goto L_0x0029
        L_0x0028:
            r6 = r15
        L_0x0029:
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r6)
            if (r6 == 0) goto L_0x0033
            boolean r6 = r6.get()
            goto L_0x0034
        L_0x0033:
            r6 = 0
        L_0x0034:
            long r16 = r2 & r12
            int r7 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x004d
            if (r0 == 0) goto L_0x0041
            androidx.databinding.ObservableBoolean r7 = r0.getDeleteButtonVisibility()
            goto L_0x0042
        L_0x0041:
            r7 = r15
        L_0x0042:
            r14 = 1
            r1.updateRegistration((int) r14, (androidx.databinding.Observable) r7)
            if (r7 == 0) goto L_0x004d
            boolean r7 = r7.get()
            goto L_0x004e
        L_0x004d:
            r7 = 0
        L_0x004e:
            long r17 = r2 & r8
            int r14 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x0067
            if (r0 == 0) goto L_0x005a
            androidx.databinding.ObservableBoolean r15 = r0.getRevertButtonVisibility()
        L_0x005a:
            r0 = 2
            r1.updateRegistration((int) r0, (androidx.databinding.Observable) r15)
            if (r15 == 0) goto L_0x0067
            boolean r14 = r15.get()
            r0 = r14
            r14 = r7
            goto L_0x006d
        L_0x0067:
            r14 = r7
            r0 = 0
            goto L_0x006d
        L_0x006a:
            r0 = 0
            r6 = 0
            r14 = 0
        L_0x006d:
            long r12 = r12 & r2
            int r7 = (r12 > r4 ? 1 : (r12 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0077
            android.widget.Button r7 = r1.equalizerDelete
            r7.setEnabled(r14)
        L_0x0077:
            long r8 = r8 & r2
            int r7 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r7 == 0) goto L_0x0081
            android.widget.Button r7 = r1.equalizerRevert
            r7.setEnabled(r0)
        L_0x0081:
            long r2 = r2 & r10
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x008b
            android.widget.Button r0 = r1.equalizerSave
            r0.setEnabled(r6)
        L_0x008b:
            return
        L_0x008c:
            r0 = move-exception
            monitor-exit(r19)     // Catch:{ all -> 0x008c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.EqualizerBindingImpl.executeBindings():void");
    }
}
