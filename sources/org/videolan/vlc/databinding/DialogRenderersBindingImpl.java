package org.videolan.vlc.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.libvlc.RendererItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.gui.dialogs.RenderersDialog;

public class DialogRenderersBindingImpl extends DialogRenderersBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback12;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.renderers_title, 2);
        sparseIntArray.put(R.id.renderers_list, 3);
    }

    public DialogRenderersBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 4, sIncludes, sViewsWithIds));
    }

    private DialogRenderersBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[3], objArr[2]);
        this.mDirtyFlags = -1;
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.renderersDisconnect.setTag((Object) null);
        setRootTag(view);
        this.mCallback12 = new OnClickListener(this, 1);
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
        if (BR.holder != i) {
            return false;
        }
        setHolder((RenderersDialog.RendererClickhandler) obj);
        return true;
    }

    public void setHolder(RenderersDialog.RendererClickhandler rendererClickhandler) {
        this.mHolder = rendererClickhandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        RenderersDialog.RendererClickhandler rendererClickhandler = this.mHolder;
        if ((j & 2) != 0) {
            this.renderersDisconnect.setOnClickListener(this.mCallback12);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        RenderersDialog.RendererClickhandler rendererClickhandler = this.mHolder;
        if (rendererClickhandler != null) {
            RendererItem rendererItem = null;
            rendererClickhandler.connect((RendererItem) null);
        }
    }
}
