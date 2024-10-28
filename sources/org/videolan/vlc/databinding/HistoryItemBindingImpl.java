package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.HistoryAdapter;

public class HistoryItemBindingImpl extends HistoryItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHolderOnImageClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.constraintLayout4, 9);
        sparseIntArray.put(R.id.song_marker, 10);
        sparseIntArray.put(R.id.mediaTypeBarrier, 11);
    }

    public HistoryItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private HistoryItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[9], objArr[1], objArr[11], objArr[3], objArr[2], objArr[6], objArr[8], objArr[7], objArr[10], objArr[5], objArr[4]);
        this.mDirtyFlags = -1;
        this.icon.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
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
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0165  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0185  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x019e  */
    /* JADX WARNING: Removed duplicated region for block: B:131:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0218  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x0223  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x022e  */
    /* JADX WARNING: Removed duplicated region for block: B:158:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r40 = this;
            r1 = r40
            monitor-enter(r40)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0234 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0234 }
            monitor-exit(r40)     // Catch:{ all -> 0x0234 }
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
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl r14 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x0045
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl r14 = new org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl
            r14.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r14
        L_0x0045:
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl r14 = r14.setValue(r8)
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnLongClickListenerImpl r15 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r15 != 0) goto L_0x0054
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnLongClickListenerImpl r15 = new org.videolan.vlc.databinding.HistoryItemBindingImpl$OnLongClickListenerImpl
            r15.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r15
        L_0x0054:
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnLongClickListenerImpl r15 = r15.setValue(r8)
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl1 r12 = r1.mHolderOnImageClickAndroidViewViewOnClickListener
            if (r12 != 0) goto L_0x0063
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl1 r12 = new org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl1
            r12.<init>()
            r1.mHolderOnImageClickAndroidViewViewOnClickListener = r12
        L_0x0063:
            org.videolan.vlc.databinding.HistoryItemBindingImpl$OnClickListenerImpl1 r8 = r12.setValue(r8)
            goto L_0x006c
        L_0x0068:
            r8 = r18
            r14 = r8
            r15 = r14
        L_0x006c:
            r12 = 144(0x90, double:7.1E-322)
            long r20 = r2 & r12
            r22 = 33554432(0x2000000, double:1.6578092E-316)
            r24 = 16777216(0x1000000, double:8.289046E-317)
            r26 = 8
            int r13 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r13 == 0) goto L_0x00e7
            if (r9 == 0) goto L_0x0091
            boolean r13 = r9.isPresent()
            java.lang.String r18 = r9.getTitle()
            java.lang.String r29 = r9.getDescription()
            r39 = r29
            r29 = r18
            r18 = r39
            goto L_0x0094
        L_0x0091:
            r29 = r18
            r13 = 0
        L_0x0094:
            int r30 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r30 == 0) goto L_0x009f
            if (r13 == 0) goto L_0x009d
            long r2 = r2 | r22
            goto L_0x009f
        L_0x009d:
            long r2 = r2 | r24
        L_0x009f:
            r20 = r13 ^ 1
            if (r13 == 0) goto L_0x00a6
            r21 = 8
            goto L_0x00a8
        L_0x00a6:
            r21 = 0
        L_0x00a8:
            boolean r30 = android.text.TextUtils.isEmpty(r18)
            r27 = 144(0x90, double:7.1E-322)
            long r31 = r2 & r27
            int r33 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r33 == 0) goto L_0x00bd
            if (r20 == 0) goto L_0x00b9
            r31 = 8192(0x2000, double:4.0474E-320)
            goto L_0x00bb
        L_0x00b9:
            r31 = 4096(0x1000, double:2.0237E-320)
        L_0x00bb:
            long r2 = r2 | r31
        L_0x00bd:
            long r31 = r2 & r27
            int r33 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r33 == 0) goto L_0x00ce
            if (r30 == 0) goto L_0x00c9
            r31 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x00cc
        L_0x00c9:
            r31 = 1048576(0x100000, double:5.180654E-318)
        L_0x00cc:
            long r2 = r2 | r31
        L_0x00ce:
            if (r20 == 0) goto L_0x00d3
            r20 = 0
            goto L_0x00d5
        L_0x00d3:
            r20 = 8
        L_0x00d5:
            if (r30 == 0) goto L_0x00da
            r30 = 8
            goto L_0x00dc
        L_0x00da:
            r30 = 0
        L_0x00dc:
            r35 = r18
            r36 = r20
            r37 = r21
            r34 = r29
            r38 = r30
            goto L_0x00f2
        L_0x00e7:
            r34 = r18
            r35 = r34
            r13 = 0
            r36 = 0
            r37 = 0
            r38 = 0
        L_0x00f2:
            r20 = 176(0xb0, double:8.7E-322)
            long r29 = r2 & r20
            int r18 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0107
            int r18 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0107
            if (r10 == 0) goto L_0x0103
            r29 = 512(0x200, double:2.53E-321)
            goto L_0x0105
        L_0x0103:
            r29 = 256(0x100, double:1.265E-321)
        L_0x0105:
            long r2 = r2 | r29
        L_0x0107:
            r29 = 208(0xd0, double:1.03E-321)
            long r31 = r2 & r29
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x011e
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x011e
            if (r11 == 0) goto L_0x0119
            r31 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x011c
        L_0x0119:
            r31 = 4194304(0x400000, double:2.0722615E-317)
        L_0x011c:
            long r2 = r2 | r31
        L_0x011e:
            r31 = 8520192(0x820200, double:4.209534E-317)
            long r31 = r2 & r31
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x013c
            if (r9 == 0) goto L_0x012d
            boolean r13 = r9.isPresent()
        L_0x012d:
            r27 = 144(0x90, double:7.1E-322)
            long r31 = r2 & r27
            int r18 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x013c
            if (r13 == 0) goto L_0x013a
            long r2 = r2 | r22
            goto L_0x013c
        L_0x013a:
            long r2 = r2 | r24
        L_0x013c:
            long r22 = r2 & r20
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x015c
            if (r10 == 0) goto L_0x0146
            r10 = r13
            goto L_0x0147
        L_0x0146:
            r10 = 0
        L_0x0147:
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0156
            if (r10 == 0) goto L_0x0151
            r22 = 524288(0x80000, double:2.590327E-318)
            goto L_0x0154
        L_0x0151:
            r22 = 262144(0x40000, double:1.295163E-318)
        L_0x0154:
            long r2 = r2 | r22
        L_0x0156:
            if (r10 == 0) goto L_0x0159
            goto L_0x015c
        L_0x0159:
            r10 = 8
            goto L_0x015d
        L_0x015c:
            r10 = 0
        L_0x015d:
            r18 = 145(0x91, double:7.16E-322)
            long r22 = r2 & r18
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x017e
            if (r0 == 0) goto L_0x0169
            r0 = r13
            goto L_0x016a
        L_0x0169:
            r0 = 0
        L_0x016a:
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0178
            if (r0 == 0) goto L_0x0174
            r22 = 32768(0x8000, double:1.61895E-319)
            goto L_0x0176
        L_0x0174:
            r22 = 16384(0x4000, double:8.0948E-320)
        L_0x0176:
            long r2 = r2 | r22
        L_0x0178:
            if (r0 == 0) goto L_0x017b
            goto L_0x017e
        L_0x017b:
            r0 = 8
            goto L_0x017f
        L_0x017e:
            r0 = 0
        L_0x017f:
            long r22 = r2 & r29
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x019e
            if (r11 == 0) goto L_0x0189
            r11 = r13
            goto L_0x018a
        L_0x0189:
            r11 = 0
        L_0x018a:
            int r18 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x0197
            if (r11 == 0) goto L_0x0193
            r22 = 2048(0x800, double:1.0118E-320)
            goto L_0x0195
        L_0x0193:
            r22 = 1024(0x400, double:5.06E-321)
        L_0x0195:
            long r2 = r2 | r22
        L_0x0197:
            if (r11 == 0) goto L_0x019b
            r26 = 0
        L_0x019b:
            r11 = r26
            goto L_0x019f
        L_0x019e:
            r11 = 0
        L_0x019f:
            r16 = 136(0x88, double:6.7E-322)
            long r16 = r2 & r16
            int r18 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r18 == 0) goto L_0x01b6
            android.widget.ImageView r12 = r1.icon
            r12.setOnClickListener(r8)
            androidx.constraintlayout.widget.ConstraintLayout r8 = r1.mboundView0
            r8.setOnClickListener(r14)
            androidx.constraintlayout.widget.ConstraintLayout r8 = r1.mboundView0
            r8.setOnLongClickListener(r15)
        L_0x01b6:
            r14 = 132(0x84, double:6.5E-322)
            long r14 = r14 & r2
            int r8 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            android.widget.ImageView r8 = r1.icon
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r8, r7)
        L_0x01c2:
            r7 = 144(0x90, double:7.1E-322)
            long r7 = r7 & r2
            int r12 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r12 == 0) goto L_0x0201
            android.widget.ImageView r7 = r1.icon
            r8 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r7, r9, r8, r8, r8)
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.mboundView0
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r7, r9)
            android.widget.ImageView r7 = r1.missingMedia
            r12 = r37
            r7.setVisibility(r12)
            android.view.View r7 = r1.missingOverlay
            r12 = r36
            r7.setVisibility(r12)
            android.widget.TextView r7 = r1.subtitle
            r7.setEnabled(r13)
            android.widget.TextView r7 = r1.subtitle
            r8 = r35
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r8)
            android.widget.TextView r7 = r1.subtitle
            r12 = r38
            r7.setVisibility(r12)
            android.widget.TextView r7 = r1.title
            r7.setEnabled(r13)
            android.widget.TextView r7 = r1.title
            r8 = r34
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r8)
        L_0x0201:
            r7 = 130(0x82, double:6.4E-322)
            long r7 = r7 & r2
            int r9 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r9 == 0) goto L_0x0211
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.mboundView0
            android.graphics.drawable.ColorDrawable r6 = androidx.databinding.adapters.Converters.convertColorToDrawable(r6)
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(r7, r6)
        L_0x0211:
            r6 = 145(0x91, double:7.16E-322)
            long r6 = r6 & r2
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 == 0) goto L_0x021d
            android.widget.ImageView r6 = r1.networkMedia
            r6.setVisibility(r0)
        L_0x021d:
            long r6 = r2 & r29
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0228
            android.widget.ImageView r0 = r1.otgMedia
            r0.setVisibility(r11)
        L_0x0228:
            long r2 = r2 & r20
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 == 0) goto L_0x0233
            android.widget.ImageView r0 = r1.sdMedia
            r0.setVisibility(r10)
        L_0x0233:
            return
        L_0x0234:
            r0 = move-exception
            monitor-exit(r40)     // Catch:{ all -> 0x0234 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.HistoryItemBindingImpl.executeBindings():void");
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
