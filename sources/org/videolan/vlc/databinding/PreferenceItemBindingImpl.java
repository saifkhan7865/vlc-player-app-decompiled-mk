package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.gui.preferences.search.PreferenceItem;
import org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter;
import org.videolan.vlc.gui.preferences.search.PreferenceItemAdapterKt;

public class PreferenceItemBindingImpl extends PreferenceItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback13;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.separator, 4);
    }

    public PreferenceItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private PreferenceItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[3], objArr[4], objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        this.categoryText.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.subtitle.setTag((Object) null);
        this.textView16.setTag((Object) null);
        setRootTag(view);
        this.mCallback13 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 64;
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
        if (BR.handler == i) {
            setHandler((PreferenceItemAdapter.ClickHandler) obj);
        } else if (BR.item == i) {
            setItem((PreferenceItem) obj);
        } else if (BR.category == i) {
            setCategory((String) obj);
        } else if (BR.title == i) {
            setTitle((String) obj);
        } else if (BR.query == i) {
            setQuery((String) obj);
        } else if (BR.description != i) {
            return false;
        } else {
            setDescription((String) obj);
        }
        return true;
    }

    public void setHandler(PreferenceItemAdapter.ClickHandler clickHandler) {
        this.mHandler = clickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    public void setItem(PreferenceItem preferenceItem) {
        this.mItem = preferenceItem;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setCategory(String str) {
        this.mCategory = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.category);
        super.requestRebind();
    }

    public void setTitle(String str) {
        this.mTitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.title);
        super.requestRebind();
    }

    public void setQuery(String str) {
        this.mQuery = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.query);
        super.requestRebind();
    }

    public void setDescription(String str) {
        this.mDescription = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.description);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        PreferenceItemAdapter.ClickHandler clickHandler = this.mHandler;
        PreferenceItem preferenceItem = this.mItem;
        String str = this.mCategory;
        String str2 = this.mTitle;
        String str3 = this.mQuery;
        String str4 = this.mDescription;
        long j2 = 88 & j;
        long j3 = 112 & j;
        if ((68 & j) != 0) {
            TextViewBindingAdapter.setText(this.categoryText, str);
        }
        if ((j & 64) != 0) {
            this.mboundView0.setOnClickListener(this.mCallback13);
        }
        if (j3 != 0) {
            PreferenceItemAdapterKt.searchText(this.subtitle, str4, str3);
        }
        if (j2 != 0) {
            PreferenceItemAdapterKt.searchText(this.textView16, str2, str3);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        PreferenceItemAdapter.ClickHandler clickHandler = this.mHandler;
        PreferenceItem preferenceItem = this.mItem;
        if (clickHandler != null) {
            clickHandler.onClick(preferenceItem);
        }
    }
}
