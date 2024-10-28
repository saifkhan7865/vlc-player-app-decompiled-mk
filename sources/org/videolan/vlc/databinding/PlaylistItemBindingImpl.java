package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.generated.callback.OnClickListener;
import org.videolan.vlc.gui.audio.PlaylistAdapter;

public class PlaylistItemBindingImpl extends PlaylistItemBinding implements OnClickListener.Listener {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private final View.OnClickListener mCallback16;
    private long mDirtyFlags;
    private OnClickListenerImpl1 mHolderOnDeleteClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl3 mHolderOnMoreClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHolderOnMoveDownClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHolderOnMoveUpClickAndroidViewViewOnClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.playing, 10);
        sparseIntArray.put(R.id.stop_after, 11);
    }

    public PlaylistItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    private PlaylistItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[4], objArr[3], objArr[2], objArr[0], objArr[6], objArr[5], objArr[7], objArr[8], objArr[10], objArr[1], objArr[11], objArr[9]);
        this.mDirtyFlags = -1;
        this.audioItemSubtitle.setTag((Object) null);
        this.audioItemTitle.setTag((Object) null);
        this.coverImage.setTag((Object) null);
        this.itemContainer.setTag((Object) null);
        this.itemDelete.setTag((Object) null);
        this.itemMore.setTag((Object) null);
        this.itemMoveDown.setTag((Object) null);
        this.itemMoveUp.setTag((Object) null);
        this.selector.setTag((Object) null);
        this.tipsOverlay.setTag((Object) null);
        setRootTag(view);
        this.mCallback16 = new OnClickListener(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 128;
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
        if (BR.subTitle == i) {
            setSubTitle((String) obj);
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.masked == i) {
            setMasked((Boolean) obj);
        } else if (BR.holder == i) {
            setHolder((PlaylistAdapter.ViewHolder) obj);
        } else if (BR.media == i) {
            setMedia((MediaWrapper) obj);
        } else if (BR.scaleType == i) {
            setScaleType((ImageView.ScaleType) obj);
        } else if (BR.stopAfterThis != i) {
            return false;
        } else {
            setStopAfterThis((Boolean) obj);
        }
        return true;
    }

    public void setSubTitle(String str) {
        this.mSubTitle = str;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.subTitle);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setMasked(Boolean bool) {
        this.mMasked = bool;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.masked);
        super.requestRebind();
    }

    public void setHolder(PlaylistAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setMedia(MediaWrapper mediaWrapper) {
        this.mMedia = mediaWrapper;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.media);
        super.requestRebind();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.scaleType);
        super.requestRebind();
    }

    public void setStopAfterThis(Boolean bool) {
        this.mStopAfterThis = bool;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.stopAfterThis);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0122  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:95:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r33 = this;
            r1 = r33
            monitor-enter(r33)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x016f }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x016f }
            monitor-exit(r33)     // Catch:{ all -> 0x016f }
            java.lang.String r0 = r1.mSubTitle
            android.graphics.drawable.BitmapDrawable r6 = r1.mCover
            java.lang.Boolean r7 = r1.mMasked
            org.videolan.vlc.gui.audio.PlaylistAdapter$ViewHolder r8 = r1.mHolder
            org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = r1.mMedia
            android.widget.ImageView$ScaleType r10 = r1.mScaleType
            java.lang.Boolean r11 = r1.mStopAfterThis
            r12 = 193(0xc1, double:9.54E-322)
            long r14 = r2 & r12
            r16 = 512(0x200, double:2.53E-321)
            int r13 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x0033
            boolean r13 = android.text.TextUtils.isEmpty(r0)
            int r20 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x0034
            if (r13 == 0) goto L_0x002f
            long r2 = r2 | r16
            goto L_0x0034
        L_0x002f:
            r14 = 256(0x100, double:1.265E-321)
            long r2 = r2 | r14
            goto L_0x0034
        L_0x0033:
            r13 = 0
        L_0x0034:
            r14 = 132(0x84, double:6.5E-322)
            long r20 = r2 & r14
            r22 = 8
            int r23 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x0055
            boolean r7 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r7)
            int r23 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x004f
            if (r7 == 0) goto L_0x004b
            r20 = 8192(0x2000, double:4.0474E-320)
            goto L_0x004d
        L_0x004b:
            r20 = 4096(0x1000, double:2.0237E-320)
        L_0x004d:
            long r2 = r2 | r20
        L_0x004f:
            if (r7 == 0) goto L_0x0052
            goto L_0x0055
        L_0x0052:
            r7 = 8
            goto L_0x0056
        L_0x0055:
            r7 = 0
        L_0x0056:
            r20 = 136(0x88, double:6.7E-322)
            long r23 = r2 & r20
            r25 = 0
            int r26 = (r23 > r4 ? 1 : (r23 == r4 ? 0 : -1))
            if (r26 == 0) goto L_0x009f
            if (r8 == 0) goto L_0x009f
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl r14 = r1.mHolderOnMoveUpClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x006d
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl r14 = new org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl
            r14.<init>()
            r1.mHolderOnMoveUpClickAndroidViewViewOnClickListener = r14
        L_0x006d:
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl r14 = r14.setValue(r8)
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl1 r15 = r1.mHolderOnDeleteClickAndroidViewViewOnClickListener
            if (r15 != 0) goto L_0x007c
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl1 r15 = new org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl1
            r15.<init>()
            r1.mHolderOnDeleteClickAndroidViewViewOnClickListener = r15
        L_0x007c:
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl1 r15 = r15.setValue(r8)
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl2 r12 = r1.mHolderOnMoveDownClickAndroidViewViewOnClickListener
            if (r12 != 0) goto L_0x008b
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl2 r12 = new org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl2
            r12.<init>()
            r1.mHolderOnMoveDownClickAndroidViewViewOnClickListener = r12
        L_0x008b:
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl2 r12 = r12.setValue(r8)
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl3 r4 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x009a
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl3 r4 = new org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl3
            r4.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r4
        L_0x009a:
            org.videolan.vlc.databinding.PlaylistItemBindingImpl$OnClickListenerImpl3 r4 = r4.setValue(r8)
            goto L_0x00a4
        L_0x009f:
            r4 = r25
            r12 = r4
            r14 = r12
            r15 = r14
        L_0x00a4:
            r29 = 144(0x90, double:7.1E-322)
            long r31 = r2 & r29
            r27 = 0
            int r5 = (r31 > r27 ? 1 : (r31 == r27 ? 0 : -1))
            if (r5 == 0) goto L_0x00b4
            if (r9 == 0) goto L_0x00b4
            java.lang.String r25 = r9.getTitle()
        L_0x00b4:
            r5 = r25
            long r16 = r2 & r16
            r8 = 1
            int r25 = (r16 > r27 ? 1 : (r16 == r27 ? 0 : -1))
            if (r25 == 0) goto L_0x00c3
            boolean r11 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r11)
            r11 = r11 ^ r8
            goto L_0x00c4
        L_0x00c3:
            r11 = 0
        L_0x00c4:
            r16 = 193(0xc1, double:9.54E-322)
            long r31 = r2 & r16
            int r16 = (r31 > r27 ? 1 : (r31 == r27 ? 0 : -1))
            if (r16 == 0) goto L_0x00e5
            if (r13 == 0) goto L_0x00cf
            goto L_0x00d0
        L_0x00cf:
            r11 = 0
        L_0x00d0:
            int r13 = (r31 > r27 ? 1 : (r31 == r27 ? 0 : -1))
            if (r13 == 0) goto L_0x00dd
            if (r11 == 0) goto L_0x00d9
            r16 = 2048(0x800, double:1.0118E-320)
            goto L_0x00db
        L_0x00d9:
            r16 = 1024(0x400, double:5.06E-321)
        L_0x00db:
            long r2 = r2 | r16
        L_0x00dd:
            if (r11 == 0) goto L_0x00e0
            goto L_0x00e2
        L_0x00e0:
            r22 = 0
        L_0x00e2:
            r11 = r22
            goto L_0x00e6
        L_0x00e5:
            r11 = 0
        L_0x00e6:
            r16 = 129(0x81, double:6.37E-322)
            long r16 = r2 & r16
            r27 = 0
            int r13 = (r16 > r27 ? 1 : (r16 == r27 ? 0 : -1))
            if (r13 == 0) goto L_0x00f5
            android.widget.TextView r13 = r1.audioItemSubtitle
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r13, r0)
        L_0x00f5:
            r16 = 193(0xc1, double:9.54E-322)
            long r16 = r2 & r16
            int r0 = (r16 > r27 ? 1 : (r16 == r27 ? 0 : -1))
            if (r0 == 0) goto L_0x0102
            android.widget.TextView r0 = r1.audioItemSubtitle
            r0.setVisibility(r11)
        L_0x0102:
            long r16 = r2 & r29
            int r0 = (r16 > r27 ? 1 : (r16 == r27 ? 0 : -1))
            if (r0 == 0) goto L_0x0118
            android.widget.TextView r0 = r1.audioItemTitle
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r5)
            android.widget.ImageView r0 = r1.coverImage
            r5 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r0, r9, r5, r5, r8)
            android.view.View r0 = r1.selector
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r0, r9)
        L_0x0118:
            r16 = 128(0x80, double:6.32E-322)
            long r16 = r2 & r16
            r18 = 0
            int r0 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x012e
            android.widget.TextView r0 = r1.audioItemTitle
            org.videolan.vlc.gui.helpers.UiToolsKt.setEllipsizeModeByPref(r0, r8)
            android.view.View r0 = r1.selector
            android.view.View$OnClickListener r5 = r1.mCallback16
            r0.setOnClickListener(r5)
        L_0x012e:
            r8 = 160(0xa0, double:7.9E-322)
            long r8 = r8 & r2
            int r0 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x013a
            android.widget.ImageView r0 = r1.coverImage
            r0.setScaleType(r10)
        L_0x013a:
            r8 = 130(0x82, double:6.4E-322)
            long r8 = r8 & r2
            int r0 = (r8 > r18 ? 1 : (r8 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x0146
            android.widget.ImageView r0 = r1.coverImage
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r6)
        L_0x0146:
            long r5 = r2 & r20
            int r0 = (r5 > r18 ? 1 : (r5 == r18 ? 0 : -1))
            if (r0 == 0) goto L_0x0160
            androidx.appcompat.widget.AppCompatImageView r0 = r1.itemDelete
            r0.setOnClickListener(r15)
            android.widget.ImageButton r0 = r1.itemMore
            r0.setOnClickListener(r4)
            androidx.appcompat.widget.AppCompatImageView r0 = r1.itemMoveDown
            r0.setOnClickListener(r12)
            androidx.appcompat.widget.AppCompatImageView r0 = r1.itemMoveUp
            r0.setOnClickListener(r14)
        L_0x0160:
            r4 = 132(0x84, double:6.5E-322)
            long r2 = r2 & r4
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x016e
            android.view.View r0 = r1.tipsOverlay
            r0.setVisibility(r7)
        L_0x016e:
            return
        L_0x016f:
            r0 = move-exception
            monitor-exit(r33)     // Catch:{ all -> 0x016f }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.PlaylistItemBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private PlaylistAdapter.ViewHolder value;

        public OnClickListenerImpl setValue(PlaylistAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onMoveUpClick(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private PlaylistAdapter.ViewHolder value;

        public OnClickListenerImpl1 setValue(PlaylistAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onDeleteClick(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private PlaylistAdapter.ViewHolder value;

        public OnClickListenerImpl2 setValue(PlaylistAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onMoveDownClick(view);
        }
    }

    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private PlaylistAdapter.ViewHolder value;

        public OnClickListenerImpl3 setValue(PlaylistAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onMoreClick(view);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        MediaWrapper mediaWrapper = this.mMedia;
        PlaylistAdapter.ViewHolder viewHolder = this.mHolder;
        if (viewHolder != null) {
            viewHolder.onClick(view, mediaWrapper);
        }
    }
}
