package org.videolan.vlc.databinding;

import android.graphics.drawable.Drawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.State;
import org.videolan.vlc.gui.dialogs.SubtitleItem;

public class SubtitleDownloadItemBindingImpl extends SubtitleDownloadItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.barrier, 5);
    }

    public SubtitleDownloadItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private SubtitleDownloadItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[5], objArr[3], objArr[2], objArr[4], objArr[1]);
        this.mDirtyFlags = -1;
        this.downloadSub.setTag((Object) null);
        this.language.setTag((Object) null);
        this.loading.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.subTitle.setTag((Object) null);
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
        if (BR.subtitleItem != i) {
            return false;
        }
        setSubtitleItem((SubtitleItem) obj);
        return true;
    }

    public void setSubtitleItem(SubtitleItem subtitleItem) {
        this.mSubtitleItem = subtitleItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.subtitleItem);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        Drawable drawable;
        int i;
        String str2;
        State state;
        String str3;
        String str4;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        SubtitleItem subtitleItem = this.mSubtitleItem;
        long j2 = j & 3;
        int i2 = 0;
        if (j2 != 0) {
            if (subtitleItem != null) {
                str3 = subtitleItem.getMovieReleaseName();
                state = subtitleItem.getState();
                str4 = subtitleItem.getSubLanguageID();
            } else {
                str4 = null;
                str3 = null;
                state = null;
            }
            str = str3 != null ? str3.trim() : null;
            boolean z = true;
            boolean z2 = state == State.Downloaded;
            if (state != State.Downloading) {
                z = false;
            }
            if (j2 != 0) {
                j |= z2 ? 32 : 16;
            }
            if ((j & 3) != 0) {
                j |= z ? 136 : 68;
            }
            String trim = str4 != null ? str4.trim() : null;
            Drawable drawable2 = AppCompatResources.getDrawable(this.downloadSub.getContext(), z2 ? R.drawable.ic_done : R.drawable.ic_download_subtitles);
            i = z ? 0 : 8;
            if (z) {
                i2 = 8;
            }
            String str5 = trim;
            drawable = drawable2;
            str2 = str5;
        } else {
            str2 = null;
            i = 0;
            drawable = null;
            str = null;
        }
        if ((j & 3) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.downloadSub, drawable);
            this.downloadSub.setVisibility(i2);
            TextViewBindingAdapter.setText(this.language, str2);
            this.loading.setVisibility(i);
            TextViewBindingAdapter.setText(this.subTitle, str);
        }
    }
}
