package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.libvlc.RendererItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.gui.dialogs.RenderersDialog;

public class ItemRendererBindingImpl extends ItemRendererBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback15;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.renderer_icon, 2);
    }

    public ItemRendererBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 3, sIncludes, sViewsWithIds));
    }

    private ItemRendererBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.rendererName.setTag((Object) null);
        setRootTag(view);
        this.mCallback15 = new OnClickListener(this, 1);
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
        if (BR.renderer == i) {
            setRenderer((RendererItem) obj);
        } else if (BR.clicHandler != i) {
            return false;
        } else {
            setClicHandler((RenderersDialog.RendererClickhandler) obj);
        }
        return true;
    }

    public void setRenderer(RendererItem rendererItem) {
        this.mRenderer = rendererItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.renderer);
        super.requestRebind();
    }

    public void setClicHandler(RenderersDialog.RendererClickhandler rendererClickhandler) {
        this.mClicHandler = rendererClickhandler;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.clicHandler);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        RendererItem rendererItem = this.mRenderer;
        RenderersDialog.RendererClickhandler rendererClickhandler = this.mClicHandler;
        long j2 = 5 & j;
        String str = (j2 == 0 || rendererItem == null) ? null : rendererItem.displayName;
        if ((j & 4) != 0) {
            this.rendererName.setOnClickListener(this.mCallback15);
        }
        if (j2 != 0) {
            TextViewBindingAdapter.setText(this.rendererName, str);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        RendererItem rendererItem = this.mRenderer;
        RenderersDialog.RendererClickhandler rendererClickhandler = this.mClicHandler;
        if (rendererClickhandler != null) {
            rendererClickhandler.connect(rendererItem);
        }
    }
}
