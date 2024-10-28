package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;

public class PlayerOverlayTracksBindingImpl extends PlayerOverlayTracksBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final NestedScrollView mboundView0;

    public boolean setVariable(int i, Object obj) {
        return true;
    }

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(7);
        sIncludes = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"player_overlay_track_item", "player_overlay_track_item", "player_overlay_track_item"}, new int[]{2, 3, 4}, new int[]{R.layout.player_overlay_track_item, R.layout.player_overlay_track_item, R.layout.player_overlay_track_item});
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.tracks_separator_2, 5);
        sparseIntArray.put(R.id.tracks_separator_3, 6);
    }

    public PlayerOverlayTracksBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private PlayerOverlayTracksBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 3, objArr[2], objArr[1], objArr[3], objArr[5], objArr[6], objArr[4]);
        this.mDirtyFlags = -1;
        setContainedBinding(this.audioTracks);
        NestedScrollView nestedScrollView = objArr[0];
        this.mboundView0 = nestedScrollView;
        nestedScrollView.setTag((Object) null);
        this.playerOverlayTracks.setTag((Object) null);
        setContainedBinding(this.subtitleTracks);
        setContainedBinding(this.videoTracks);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        this.audioTracks.invalidateAll();
        this.subtitleTracks.invalidateAll();
        this.videoTracks.invalidateAll();
        requestRebind();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r6.subtitleTracks.hasPendingBindings() == false) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0025, code lost:
        if (r6.videoTracks.hasPendingBindings() == false) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0027, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0028, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        if (r6.audioTracks.hasPendingBindings() == false) goto L_0x0016;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean hasPendingBindings() {
        /*
            r6 = this;
            monitor-enter(r6)
            long r0 = r6.mDirtyFlags     // Catch:{ all -> 0x002a }
            r2 = 0
            r4 = 1
            int r5 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r5 == 0) goto L_0x000c
            monitor-exit(r6)     // Catch:{ all -> 0x002a }
            return r4
        L_0x000c:
            monitor-exit(r6)     // Catch:{ all -> 0x002a }
            org.videolan.vlc.databinding.PlayerOverlayTrackItemBinding r0 = r6.audioTracks
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0016
            return r4
        L_0x0016:
            org.videolan.vlc.databinding.PlayerOverlayTrackItemBinding r0 = r6.subtitleTracks
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x001f
            return r4
        L_0x001f:
            org.videolan.vlc.databinding.PlayerOverlayTrackItemBinding r0 = r6.videoTracks
            boolean r0 = r0.hasPendingBindings()
            if (r0 == 0) goto L_0x0028
            return r4
        L_0x0028:
            r0 = 0
            return r0
        L_0x002a:
            r0 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x002a }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.PlayerOverlayTracksBindingImpl.hasPendingBindings():boolean");
    }

    public void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.audioTracks.setLifecycleOwner(lifecycleOwner);
        this.subtitleTracks.setLifecycleOwner(lifecycleOwner);
        this.videoTracks.setLifecycleOwner(lifecycleOwner);
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i == 0) {
            return onChangeAudioTracks((PlayerOverlayTrackItemBinding) obj, i2);
        }
        if (i == 1) {
            return onChangeSubtitleTracks((PlayerOverlayTrackItemBinding) obj, i2);
        }
        if (i != 2) {
            return false;
        }
        return onChangeVideoTracks((PlayerOverlayTrackItemBinding) obj, i2);
    }

    private boolean onChangeAudioTracks(PlayerOverlayTrackItemBinding playerOverlayTrackItemBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeSubtitleTracks(PlayerOverlayTrackItemBinding playerOverlayTrackItemBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    private boolean onChangeVideoTracks(PlayerOverlayTrackItemBinding playerOverlayTrackItemBinding, int i) {
        if (i != BR._all) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        synchronized (this) {
            this.mDirtyFlags = 0;
        }
        executeBindingsOn(this.audioTracks);
        executeBindingsOn(this.subtitleTracks);
        executeBindingsOn(this.videoTracks);
    }
}
