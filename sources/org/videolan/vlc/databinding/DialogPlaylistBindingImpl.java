package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;

public class DialogPlaylistBindingImpl extends DialogPlaylistBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final LinearLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.textView9, 5);
        sparseIntArray.put(R.id.dialog_playlist_name, 6);
        sparseIntArray.put(R.id.selected_playlist_count, 7);
        sparseIntArray.put(R.id.replaceSwitch, 8);
        sparseIntArray.put(R.id.dialog_list_container, 9);
        sparseIntArray.put(16908298, 10);
        sparseIntArray.put(16908292, 11);
    }

    public DialogPlaylistBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private DialogPlaylistBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[9], objArr[1], objArr[6], objArr[4], objArr[11], objArr[10], objArr[2], objArr[3], objArr[8], objArr[7], objArr[5]);
        this.mDirtyFlags = -1;
        this.dialogPlaylistCreate.setTag((Object) null);
        this.dialogPlaylistSave.setTag((Object) null);
        LinearLayout linearLayout = objArr[0];
        this.mboundView0 = linearLayout;
        linearLayout.setTag((Object) null);
        this.medias.setTag((Object) null);
        this.progressBar2.setTag((Object) null);
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
        if (BR.filesText == i) {
            setFilesText((String) obj);
        } else if (BR.isLoading != i) {
            return false;
        } else {
            setIsLoading((Boolean) obj);
        }
        return true;
    }

    public void setFilesText(String str) {
        this.mFilesText = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.filesText);
        super.requestRebind();
    }

    public void setIsLoading(Boolean bool) {
        this.mIsLoading = bool;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.isLoading);
        super.requestRebind();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: boolean} */
    /* JADX WARNING: type inference failed for: r10v0 */
    /* JADX WARNING: type inference failed for: r10v2 */
    /* JADX WARNING: type inference failed for: r10v3 */
    /* JADX WARNING: type inference failed for: r10v4 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r13 = this;
            monitor-enter(r13)
            long r0 = r13.mDirtyFlags     // Catch:{ all -> 0x0068 }
            r2 = 0
            r13.mDirtyFlags = r2     // Catch:{ all -> 0x0068 }
            monitor-exit(r13)     // Catch:{ all -> 0x0068 }
            java.lang.String r4 = r13.mFilesText
            java.lang.Boolean r5 = r13.mIsLoading
            r6 = 6
            long r8 = r0 & r6
            r10 = 0
            int r11 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r11 == 0) goto L_0x0040
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            int r11 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r11 == 0) goto L_0x0025
            if (r5 == 0) goto L_0x0022
            r8 = 80
            goto L_0x0024
        L_0x0022:
            r8 = 40
        L_0x0024:
            long r0 = r0 | r8
        L_0x0025:
            r8 = 8
            if (r5 == 0) goto L_0x002c
            r9 = 8
            goto L_0x002d
        L_0x002c:
            r9 = 0
        L_0x002d:
            r11 = r5 ^ 1
            if (r5 == 0) goto L_0x0032
            goto L_0x0034
        L_0x0032:
            r10 = 8
        L_0x0034:
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r11)
            boolean r5 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r5)
            r12 = r10
            r10 = r5
            r5 = r12
            goto L_0x0042
        L_0x0040:
            r5 = 0
            r9 = 0
        L_0x0042:
            long r6 = r6 & r0
            int r8 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r8 == 0) goto L_0x005b
            android.widget.Button r6 = r13.dialogPlaylistCreate
            r6.setEnabled(r10)
            android.widget.Button r6 = r13.dialogPlaylistSave
            r6.setEnabled(r10)
            android.widget.TextView r6 = r13.medias
            r6.setVisibility(r9)
            android.widget.ProgressBar r6 = r13.progressBar2
            r6.setVisibility(r5)
        L_0x005b:
            r5 = 5
            long r0 = r0 & r5
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x0067
            android.widget.TextView r0 = r13.medias
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x0067:
            return
        L_0x0068:
            r0 = move-exception
            monitor-exit(r13)     // Catch:{ all -> 0x0068 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.DialogPlaylistBindingImpl.executeBindings():void");
    }
}
