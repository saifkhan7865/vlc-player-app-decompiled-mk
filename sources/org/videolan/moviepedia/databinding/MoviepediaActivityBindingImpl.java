package org.videolan.moviepedia.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.moviepedia.BR;
import org.videolan.moviepedia.R;
import org.videolan.moviepedia.ui.MediaScrapingActivity;

public class MoviepediaActivityBindingImpl extends MoviepediaActivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHandlerOnBackAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.search_edit_layout, 2);
        sparseIntArray.put(R.id.search_edit_text, 3);
        sparseIntArray.put(R.id.next_results, 4);
    }

    public MoviepediaActivityBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 5, sIncludes, sViewsWithIds));
    }

    private MoviepediaActivityBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[4], objArr[2], objArr[3]);
        this.mDirtyFlags = -1;
        this.imageView8.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
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
        if (BR.handler != i) {
            return false;
        }
        setHandler((MediaScrapingActivity.ClickHandler) obj);
        return true;
    }

    public void setHandler(MediaScrapingActivity.ClickHandler clickHandler) {
        this.mHandler = clickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        OnClickListenerImpl onClickListenerImpl;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaScrapingActivity.ClickHandler clickHandler = this.mHandler;
        long j2 = j & 3;
        if (j2 == 0 || clickHandler == null) {
            onClickListenerImpl = null;
        } else {
            OnClickListenerImpl onClickListenerImpl2 = this.mHandlerOnBackAndroidViewViewOnClickListener;
            if (onClickListenerImpl2 == null) {
                onClickListenerImpl2 = new OnClickListenerImpl();
                this.mHandlerOnBackAndroidViewViewOnClickListener = onClickListenerImpl2;
            }
            onClickListenerImpl = onClickListenerImpl2.setValue(clickHandler);
        }
        if (j2 != 0) {
            this.imageView8.setOnClickListener(onClickListenerImpl);
        }
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private MediaScrapingActivity.ClickHandler value;

        public OnClickListenerImpl setValue(MediaScrapingActivity.ClickHandler clickHandler) {
            this.value = clickHandler;
            if (clickHandler == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onBack(view);
        }
    }
}
