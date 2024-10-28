package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public class PlayerHudRightBindingImpl extends PlayerHudRightBinding {
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
        sparseIntArray.put(R.id.clock, 1);
        sparseIntArray.put(R.id.player_overlay_navmenu, 2);
        sparseIntArray.put(R.id.player_screenshot, 3);
        sparseIntArray.put(R.id.player_overlay_title, 4);
        sparseIntArray.put(R.id.playlist_toggle, 5);
        sparseIntArray.put(R.id.video_secondary_display, 6);
        sparseIntArray.put(R.id.video_renderer, 7);
        sparseIntArray.put(R.id.quick_actions_container, 8);
        sparseIntArray.put(R.id.playback_speed_quick_action, 9);
        sparseIntArray.put(R.id.sleep_quick_action, 10);
        sparseIntArray.put(R.id.spu_delay_quick_action, 11);
        sparseIntArray.put(R.id.audio_delay_quick_action, 12);
        sparseIntArray.put(R.id.icon_barrier, 13);
    }

    public PlayerHudRightBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 14, sIncludes, sViewsWithIds));
    }

    private PlayerHudRightBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[12], objArr[1], objArr[0], objArr[13], objArr[9], objArr[2], objArr[4], objArr[3], objArr[5], objArr[8], objArr[10], objArr[11], objArr[7], objArr[6]);
        this.mDirtyFlags = -1;
        this.hudRightOverlay.setTag((Object) null);
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
