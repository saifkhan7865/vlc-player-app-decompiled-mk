package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.gui.SimpleAdapter;

public class SimpleItemBindingImpl extends SimpleItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private final View.OnClickListener mCallback14;
    private long mDirtyFlags;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public SimpleItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 6, sIncludes, sViewsWithIds));
    }

    private SimpleItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[2], objArr[5], objArr[4], objArr[3]);
        this.mDirtyFlags = -1;
        this.imageView8.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.mlItemOverlay.setTag((Object) null);
        this.selectedCheck.setTag((Object) null);
        this.subtitle.setTag((Object) null);
        this.textView16.setTag((Object) null);
        setRootTag(view);
        this.mCallback14 = new OnClickListener(this, 1);
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
            setHandler((SimpleAdapter.ClickHandler) obj);
        } else if (BR.item == i) {
            setItem((MediaLibraryItem) obj);
        } else if (BR.position == i) {
            setPosition(((Integer) obj).intValue());
        } else if (BR.imageWidth == i) {
            setImageWidth(((Integer) obj).intValue());
        } else if (BR.selected == i) {
            setSelected(((Boolean) obj).booleanValue());
        } else if (BR.cover != i) {
            return false;
        } else {
            setCover((BitmapDrawable) obj);
        }
        return true;
    }

    public void setHandler(SimpleAdapter.ClickHandler clickHandler) {
        this.mHandler = clickHandler;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.handler);
        super.requestRebind();
    }

    public void setItem(MediaLibraryItem mediaLibraryItem) {
        this.mItem = mediaLibraryItem;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setPosition(int i) {
        this.mPosition = i;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.position);
        super.requestRebind();
    }

    public void setImageWidth(int i) {
        this.mImageWidth = i;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.imageWidth);
        super.requestRebind();
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.selected);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0058  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r20 = this;
            r1 = r20
            monitor-enter(r20)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x009d }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x009d }
            monitor-exit(r20)     // Catch:{ all -> 0x009d }
            org.videolan.vlc.gui.SimpleAdapter$ClickHandler r0 = r1.mHandler
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r1.mItem
            int r6 = r1.mPosition
            int r6 = r1.mImageWidth
            boolean r7 = r1.mSelected
            android.graphics.drawable.BitmapDrawable r8 = r1.mCover
            r9 = 74
            long r11 = r2 & r9
            r13 = 66
            int r15 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0031
            long r11 = r2 & r13
            int r15 = (r11 > r4 ? 1 : (r11 == r4 ? 0 : -1))
            if (r15 == 0) goto L_0x0031
            if (r0 == 0) goto L_0x0031
            java.lang.String r11 = r0.getTitle()
            java.lang.String r12 = r0.getDescription()
            goto L_0x0033
        L_0x0031:
            r11 = 0
            r12 = r11
        L_0x0033:
            r15 = 80
            long r17 = r2 & r15
            r13 = 0
            int r14 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x004f
            int r14 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r14 == 0) goto L_0x0049
            if (r7 == 0) goto L_0x0045
            r17 = 256(0x100, double:1.265E-321)
            goto L_0x0047
        L_0x0045:
            r17 = 128(0x80, double:6.32E-322)
        L_0x0047:
            long r2 = r2 | r17
        L_0x0049:
            if (r7 == 0) goto L_0x004c
            goto L_0x004f
        L_0x004c:
            r14 = 8
            goto L_0x0050
        L_0x004f:
            r14 = 0
        L_0x0050:
            r17 = 96
            long r17 = r2 & r17
            int r19 = (r17 > r4 ? 1 : (r17 == r4 ? 0 : -1))
            if (r19 == 0) goto L_0x005d
            android.widget.ImageView r15 = r1.imageView8
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r15, r8)
        L_0x005d:
            long r9 = r9 & r2
            int r8 = (r9 > r4 ? 1 : (r9 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x0067
            android.widget.ImageView r8 = r1.imageView8
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r8, r0, r6, r13, r13)
        L_0x0067:
            r8 = 80
            long r8 = r8 & r2
            int r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x007d
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView0
            r0.setSelected(r7)
            android.widget.ImageView r0 = r1.mlItemOverlay
            r0.setVisibility(r14)
            android.widget.ImageView r0 = r1.selectedCheck
            r0.setVisibility(r14)
        L_0x007d:
            r6 = 64
            long r6 = r6 & r2
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x008b
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView0
            android.view.View$OnClickListener r6 = r1.mCallback14
            r0.setOnClickListener(r6)
        L_0x008b:
            r6 = 66
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x009c
            android.widget.TextView r0 = r1.subtitle
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r12)
            android.widget.TextView r0 = r1.textView16
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r11)
        L_0x009c:
            return
        L_0x009d:
            r0 = move-exception
            monitor-exit(r20)     // Catch:{ all -> 0x009d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.SimpleItemBindingImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int i, View view) {
        SimpleAdapter.ClickHandler clickHandler = this.mHandler;
        MediaLibraryItem mediaLibraryItem = this.mItem;
        int i2 = this.mPosition;
        if (clickHandler != null) {
            clickHandler.onClick(mediaLibraryItem, i2);
        }
    }
}
