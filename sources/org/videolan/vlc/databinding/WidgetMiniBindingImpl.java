package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import android.widget.FrameLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;

public class WidgetMiniBindingImpl extends WidgetMiniBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final FrameLayout mboundView1;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(13);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"widget_content_full_player"}, new int[]{2}, new int[]{R.layout.widget_content_full_player});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.player_container_background, 3);
        sparseIntArray.put(R.id.cover_parent, 4);
        sparseIntArray.put(R.id.app_icon, 5);
        sparseIntArray.put(R.id.cover, 6);
        sparseIntArray.put(R.id.separator, 7);
        sparseIntArray.put(R.id.text_container, 8);
        sparseIntArray.put(R.id.songName, 9);
        sparseIntArray.put(R.id.artist, 10);
        sparseIntArray.put(R.id.app_name, 11);
        sparseIntArray.put(R.id.widget_configure, 12);
    }

    public WidgetMiniBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private WidgetMiniBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 1, objArr[5], objArr[11], objArr[10], objArr[2], objArr[6], objArr[4], objArr[3], objArr[7], objArr[9], objArr[8], objArr[12], objArr[0]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.controls);
        FrameLayout frameLayout = objArr[1];
        this.mboundView1 = frameLayout;
        frameLayout.setTag((Object) null);
        this.widgetContainer.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
        }
        this.controls.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0016, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.controls.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x0018 }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            org.videolan.vlc.databinding.WidgetContentFullPlayerBinding r0 = r6.controls
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            r0 = 0
            return r0
        L_0x0018:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x0018 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.WidgetMiniBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.controls.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            return false;
        }
        return onChangeControls((WidgetContentFullPlayerBinding) obj, i2);
    }

    private boolean onChangeControls(WidgetContentFullPlayerBinding widgetContentFullPlayerBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.controls);
    }
}
