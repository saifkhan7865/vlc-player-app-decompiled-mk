package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.TalkbackUtil;
import org.videolan.vlc.gui.helpers.UiToolsKt;

public class MrlCardItemBindingImpl extends MrlCardItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final MaterialCardView mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.container, 2);
        sparseIntArray.put(R.id.imageView10, 3);
        sparseIntArray.put(R.id.mrl_item_uri, 4);
        sparseIntArray.put(R.id.mrl_ctx, 5);
    }

    public MrlCardItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private MrlCardItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[3], objArr[5], objArr[1], objArr[4]);
        this.mDirtyFlags = -1;
        MaterialCardView materialCardView = objArr[0];
        this.mboundView0 = materialCardView;
        materialCardView.setTag((Object) null);
        this.mrlItemTitle.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (BR.item != i) {
            return false;
        }
        setItem((MediaWrapper) obj);
        return true;
    }

    public void setItem(MediaWrapper mediaWrapper) {
        this.mItem = mediaWrapper;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        long j2 = 3 & j;
        String stream = j2 != 0 ? TalkbackUtil.INSTANCE.getStream(getRoot().getContext(), this.mItem) : null;
        if (j2 != 0 && getBuildSdkInt() >= 4) {
            this.mboundView0.setContentDescription(stream);
        }
        if ((j & 2) != 0) {
            UiToolsKt.setEllipsizeModeByPref(this.mrlItemTitle, true);
        }
    }
}
