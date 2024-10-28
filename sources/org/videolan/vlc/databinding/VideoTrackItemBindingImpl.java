package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.resources.Constants;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.adapters.VlcTrack;
import org.videolan.vlc.gui.helpers.UiToolsKt;

public class VideoTrackItemBindingImpl extends VideoTrackItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public VideoTrackItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private VideoTrackItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[0], objArr[2]);
        this.mDirtyFlags = -1;
        this.imageView11.setTag((Object) null);
        this.trackContainer.setTag((Object) null);
        this.trackTitle.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
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
        if (BR.track == i) {
            setTrack((VlcTrack) obj);
        } else if (BR.contentDescription == i) {
            setContentDescription((String) obj);
        } else if (BR.selected != i) {
            return false;
        } else {
            setSelected((Boolean) obj);
        }
        return true;
    }

    public void setTrack(VlcTrack vlcTrack) {
        this.mTrack = vlcTrack;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.track);
        super.requestRebind();
    }

    public void setContentDescription(String str) {
        this.mContentDescription = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.contentDescription);
        super.requestRebind();
    }

    public void setSelected(Boolean bool) {
        this.mSelected = bool;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.selected);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        boolean z;
        int i;
        int i2;
        String str;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        VlcTrack vlcTrack = this.mTrack;
        String str2 = this.mContentDescription;
        Boolean bool = this.mSelected;
        long j3 = j & 9;
        if (j3 != 0) {
            String id = vlcTrack != null ? vlcTrack.getId() : null;
            z = id != null ? id.equals(Constants.GROUP_VIDEOS_NONE) : false;
            if (j3 != 0) {
                j = z ? j | 128 : j | 64;
            }
        } else {
            z = false;
        }
        long j4 = j & 12;
        if (j4 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (j4 != 0) {
                j |= safeUnbox ? 544 : 272;
            }
            i = safeUnbox ? getColorFromResource(this.trackTitle, R.color.white) : getColorFromResource(this.trackTitle, R.color.white_transparent_50);
            i2 = safeUnbox ? 0 : 4;
        } else {
            i2 = 0;
            i = 0;
        }
        String name = ((64 & j) == 0 || vlcTrack == null) ? null : vlcTrack.getName();
        long j5 = 9 & j;
        if (j5 != 0) {
            if (z) {
                name = this.trackTitle.getResources().getString(R.string.disable_track);
            }
            str = name;
            j2 = 12;
        } else {
            j2 = 12;
            str = null;
        }
        if ((j2 & j) != 0) {
            this.imageView11.setVisibility(i2);
            this.trackTitle.setTextColor(i);
            UiToolsKt.isSelected(this.trackTitle, bool);
        }
        if ((10 & j) != 0 && getBuildSdkInt() >= 4) {
            this.trackTitle.setContentDescription(str2);
        }
        if (j5 != 0) {
            TextViewBindingAdapter.setText(this.trackTitle, str);
        }
        if ((j & 8) != 0) {
            UiToolsKt.setEllipsizeModeByPref(this.trackTitle, true);
        }
    }
}
