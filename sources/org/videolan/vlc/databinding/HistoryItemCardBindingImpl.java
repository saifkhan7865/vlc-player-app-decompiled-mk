package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.google.android.material.card.MaterialCardView;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.HistoryAdapter;

public class HistoryItemCardBindingImpl extends HistoryItemCardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHolderOnImageClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;
    private final MaterialCardView mboundView0;
    private final ConstraintLayout mboundView1;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.constraintLayout4, 10);
        sparseIntArray.put(R.id.song_marker, 11);
    }

    public HistoryItemCardBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private HistoryItemCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[10], objArr[2], objArr[7], objArr[3], objArr[4], objArr[6], objArr[5], objArr[11], objArr[9], objArr[8]);
        this.mDirtyFlags = -1;
        this.icon.setTag((Object) null);
        MaterialCardView materialCardView = objArr[0];
        this.mboundView0 = materialCardView;
        materialCardView.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.missingMedia.setTag((Object) null);
        this.missingOverlay.setTag((Object) null);
        this.networkMedia.setTag((Object) null);
        this.otgMedia.setTag((Object) null);
        this.sdMedia.setTag((Object) null);
        this.subtitle.setTag((Object) null);
        this.title.setTag((Object) null);
        setRootTag(view);
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
        if (BR.isNetwork == i) {
            setIsNetwork(((Boolean) obj).booleanValue());
        } else if (BR.bgColor == i) {
            setBgColor(((Integer) obj).intValue());
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.holder == i) {
            setHolder((HistoryAdapter.ViewHolder) obj);
        } else if (BR.media == i) {
            setMedia((MediaWrapper) obj);
        } else if (BR.isSD == i) {
            setIsSD(((Boolean) obj).booleanValue());
        } else if (BR.isOTG != i) {
            return false;
        } else {
            setIsOTG(((Boolean) obj).booleanValue());
        }
        return true;
    }

    public void setIsNetwork(boolean z) {
        this.mIsNetwork = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isNetwork);
        super.requestRebind();
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.bgColor);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setHolder(HistoryAdapter.ViewHolder viewHolder) {
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

    public void setIsSD(boolean z) {
        this.mIsSD = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.isSD);
        super.requestRebind();
    }

    public void setIsOTG(boolean z) {
        this.mIsOTG = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.isOTG);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x0178  */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x0181  */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0197  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01eb  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01f6  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0201  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x020d  */
    /* JADX WARNING: Removed duplicated region for block: B:152:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x013f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r37 = this;
            r1 = r37
            monitor-enter(r37)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0214 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0214 }
            monitor-exit(r37)     // Catch:{ all -> 0x0214 }
            boolean r0 = r1.mIsNetwork
            int r6 = r1.mBgColor
            android.graphics.drawable.BitmapDrawable r7 = r1.mCover
            org.videolan.vlc.gui.HistoryAdapter$ViewHolder r8 = r1.mHolder
            org.videolan.medialibrary.interfaces.media.MediaWrapper r9 = r1.mMedia
            boolean r10 = r1.mIsSD
            boolean r11 = r1.mIsOTG
            r12 = 145(0x91, double:7.16E-322)
            long r14 = r2 & r12
            int r16 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x002e
            int r16 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r16 == 0) goto L_0x002e
            if (r0 == 0) goto L_0x002a
            r14 = 131072(0x20000, double:6.47582E-319)
            goto L_0x002d
        L_0x002a:
            r14 = 65536(0x10000, double:3.2379E-319)
        L_0x002d:
            long r2 = r2 | r14
        L_0x002e:
            r14 = 136(0x88, double:6.7E-322)
            long r16 = r2 & r14
            r18 = 0
            int r19 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r19 == 0) goto L_0x0068
            if (r8 == 0) goto L_0x0068
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl r14 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x0045
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl r14 = new org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl
            r14.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r14
        L_0x0045:
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl r14 = r14.setValue(r8)
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnLongClickListenerImpl r15 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r15 != 0) goto L_0x0054
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnLongClickListenerImpl r15 = new org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnLongClickListenerImpl
            r15.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r15
        L_0x0054:
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnLongClickListenerImpl r15 = r15.setValue(r8)
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl1 r12 = r1.mHolderOnImageClickAndroidViewViewOnClickListener
            if (r12 != 0) goto L_0x0063
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl1 r12 = new org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl1
            r12.<init>()
            r1.mHolderOnImageClickAndroidViewViewOnClickListener = r12
        L_0x0063:
            org.videolan.vlc.databinding.HistoryItemCardBindingImpl$OnClickListenerImpl1 r8 = r12.setValue(r8)
            goto L_0x006c
        L_0x0068:
            r8 = r18
            r14 = r8
            r15 = r14
        L_0x006c:
            r12 = 144(0x90, double:7.1E-322)
            long r20 = r2 & r12
            r22 = 8388608(0x800000, double:4.144523E-317)
            r24 = 4194304(0x400000, double:2.0722615E-317)
            r26 = 8
            int r13 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00c3
            if (r9 == 0) goto L_0x008b
            boolean r13 = r9.isPresent()
            java.lang.String r18 = r9.getTitle()
            java.lang.String r29 = r9.getDescription()
            goto L_0x008e
        L_0x008b:
            r29 = r18
            r13 = 0
        L_0x008e:
            int r30 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r30 == 0) goto L_0x0099
            if (r13 == 0) goto L_0x0097
            long r2 = r2 | r22
            goto L_0x0099
        L_0x0097:
            long r2 = r2 | r24
        L_0x0099:
            r20 = r13 ^ 1
            if (r13 == 0) goto L_0x00a0
            r21 = 8
            goto L_0x00a2
        L_0x00a0:
            r21 = 0
        L_0x00a2:
            r27 = 144(0x90, double:7.1E-322)
            long r30 = r2 & r27
            int r32 = (r30 > r4 ? 1 : (r30 == r4 ? 0 : -1))
            if (r32 == 0) goto L_0x00b3
            if (r20 == 0) goto L_0x00af
            r30 = 8192(0x2000, double:4.0474E-320)
            goto L_0x00b1
        L_0x00af:
            r30 = 4096(0x1000, double:2.0237E-320)
        L_0x00b1:
            long r2 = r2 | r30
        L_0x00b3:
            if (r20 == 0) goto L_0x00b8
            r20 = 0
            goto L_0x00ba
        L_0x00b8:
            r20 = 8
        L_0x00ba:
            r33 = r18
            r35 = r20
            r36 = r21
            r34 = r29
            goto L_0x00cc
        L_0x00c3:
            r33 = r18
            r34 = r33
            r13 = 0
            r35 = 0
            r36 = 0
        L_0x00cc:
            r20 = 176(0xb0, double:8.7E-322)
            long r29 = r2 & r20
            int r18 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x00e1
            int r18 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x00e1
            if (r10 == 0) goto L_0x00dd
            r29 = 512(0x200, double:2.53E-321)
            goto L_0x00df
        L_0x00dd:
            r29 = 256(0x100, double:1.265E-321)
        L_0x00df:
            long r2 = r2 | r29
        L_0x00e1:
            r29 = 208(0xd0, double:1.03E-321)
            long r31 = r2 & r29
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x00f8
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x00f8
            if (r11 == 0) goto L_0x00f3
            r31 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x00f6
        L_0x00f3:
            r31 = 1048576(0x100000, double:5.180654E-318)
        L_0x00f6:
            long r2 = r2 | r31
        L_0x00f8:
            r31 = 2228736(0x220200, double:1.101142E-317)
            long r31 = r2 & r31
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0116
            if (r9 == 0) goto L_0x0107
            boolean r13 = r9.isPresent()
        L_0x0107:
            r27 = 144(0x90, double:7.1E-322)
            long r31 = r2 & r27
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0116
            if (r13 == 0) goto L_0x0114
            long r2 = r2 | r22
            goto L_0x0116
        L_0x0114:
            long r2 = r2 | r24
        L_0x0116:
            long r22 = r2 & r20
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0136
            if (r10 == 0) goto L_0x0120
            r10 = r13
            goto L_0x0121
        L_0x0120:
            r10 = 0
        L_0x0121:
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0130
            if (r10 == 0) goto L_0x012b
            r22 = 524288(0x80000, double:2.590327E-318)
            goto L_0x012e
        L_0x012b:
            r22 = 262144(0x40000, double:1.295163E-318)
        L_0x012e:
            long r2 = r2 | r22
        L_0x0130:
            if (r10 == 0) goto L_0x0133
            goto L_0x0136
        L_0x0133:
            r10 = 8
            goto L_0x0137
        L_0x0136:
            r10 = 0
        L_0x0137:
            r18 = 145(0x91, double:7.16E-322)
            long r22 = r2 & r18
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0158
            if (r0 == 0) goto L_0x0143
            r0 = r13
            goto L_0x0144
        L_0x0143:
            r0 = 0
        L_0x0144:
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0152
            if (r0 == 0) goto L_0x014e
            r22 = 32768(0x8000, double:1.61895E-319)
            goto L_0x0150
        L_0x014e:
            r22 = 16384(0x4000, double:8.0948E-320)
        L_0x0150:
            long r2 = r2 | r22
        L_0x0152:
            if (r0 == 0) goto L_0x0155
            goto L_0x0158
        L_0x0155:
            r0 = 8
            goto L_0x0159
        L_0x0158:
            r0 = 0
        L_0x0159:
            long r22 = r2 & r29
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0178
            if (r11 == 0) goto L_0x0163
            r11 = r13
            goto L_0x0164
        L_0x0163:
            r11 = 0
        L_0x0164:
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0171
            if (r11 == 0) goto L_0x016d
            r22 = 2048(0x800, double:1.0118E-320)
            goto L_0x016f
        L_0x016d:
            r22 = 1024(0x400, double:5.06E-321)
        L_0x016f:
            long r2 = r2 | r22
        L_0x0171:
            if (r11 == 0) goto L_0x0175
            r26 = 0
        L_0x0175:
            r11 = r26
            goto L_0x0179
        L_0x0178:
            r11 = 0
        L_0x0179:
            r16 = 136(0x88, double:6.7E-322)
            long r16 = r2 & r16
            int r18 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0190
            android.widget.ImageView r12 = r1.icon
            r12.setOnClickListener(r8)
            androidx.constraintlayout.widget.ConstraintLayout r8 = r1.mboundView1
            r8.setOnClickListener(r14)
            androidx.constraintlayout.widget.ConstraintLayout r8 = r1.mboundView1
            r8.setOnLongClickListener(r15)
        L_0x0190:
            r14 = 132(0x84, double:6.5E-322)
            long r14 = r14 & r2
            int r8 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x019c
            android.widget.ImageView r8 = r1.icon
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r8, r7)
        L_0x019c:
            r7 = 144(0x90, double:7.1E-322)
            long r7 = r7 & r2
            int r12 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x01d4
            android.widget.ImageView r7 = r1.icon
            r8 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r7, r9, r8, r8, r8)
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.mboundView1
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r7, r9)
            android.widget.ImageView r7 = r1.missingMedia
            r12 = r36
            r7.setVisibility(r12)
            android.view.View r7 = r1.missingOverlay
            r12 = r35
            r7.setVisibility(r12)
            android.widget.TextView r7 = r1.subtitle
            r8 = r34
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r8)
            android.widget.TextView r7 = r1.subtitle
            r7.setEnabled(r13)
            android.widget.TextView r7 = r1.title
            r8 = r33
            org.videolan.vlc.util.KextensionsKt.asyncText(r7, r8)
            android.widget.TextView r7 = r1.title
            r7.setEnabled(r13)
        L_0x01d4:
            r7 = 130(0x82, double:6.4E-322)
            long r7 = r7 & r2
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x01e4
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.mboundView1
            android.graphics.drawable.ColorDrawable r6 = androidx.databinding.adapters.Converters.convertColorToDrawable(r6)
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(r7, r6)
        L_0x01e4:
            r6 = 145(0x91, double:7.16E-322)
            long r6 = r6 & r2
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x01f0
            android.widget.ImageView r6 = r1.networkMedia
            r6.setVisibility(r0)
        L_0x01f0:
            long r6 = r2 & r29
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x01fb
            android.widget.ImageView r0 = r1.otgMedia
            r0.setVisibility(r11)
        L_0x01fb:
            long r6 = r2 & r20
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0206
            android.widget.ImageView r0 = r1.sdMedia
            r0.setVisibility(r10)
        L_0x0206:
            r6 = 128(0x80, double:6.32E-322)
            long r2 = r2 & r6
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0213
            android.widget.TextView r0 = r1.title
            r2 = 1
            org.videolan.vlc.gui.helpers.UiToolsKt.setEllipsizeModeByPref(r0, r2)
        L_0x0213:
            return
        L_0x0214:
            r0 = move-exception
            monitor-exit(r37)     // Catch:{ all -> 0x0214 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.HistoryItemCardBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private HistoryAdapter.ViewHolder value;

        public OnClickListenerImpl setValue(HistoryAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private HistoryAdapter.ViewHolder value;

        public OnLongClickListenerImpl setValue(HistoryAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View view) {
            return this.value.onLongClick(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private HistoryAdapter.ViewHolder value;

        public OnClickListenerImpl1 setValue(HistoryAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onImageClick(view);
        }
    }
}
