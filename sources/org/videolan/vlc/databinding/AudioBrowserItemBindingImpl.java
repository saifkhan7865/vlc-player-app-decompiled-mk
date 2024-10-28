package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;

public class AudioBrowserItemBindingImpl extends AudioBrowserItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHolderOnImageClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;
    private OnClickListenerImpl1 mHolderOnMoreClickAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.playing, 14);
        sparseIntArray.put(R.id.linearLayout6, 15);
        sparseIntArray.put(R.id.selector_image, 16);
    }

    public AudioBrowserItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private AudioBrowserItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[12], objArr[1], objArr[15], objArr[2], objArr[5], objArr[3], objArr[6], objArr[9], objArr[4], objArr[8], objArr[14], objArr[7], objArr[13], objArr[16], objArr[11], objArr[10]);
        this.mDirtyFlags = -1;
        this.itemMore.setTag((Object) null);
        this.itemMove.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.mediaCover.setTag((Object) null);
        this.mediaFavorite.setTag((Object) null);
        this.mlItemOverlay.setTag((Object) null);
        this.networkMedia.setTag((Object) null);
        this.networkMediaOff.setTag((Object) null);
        this.networkOffOverlay.setTag((Object) null);
        this.otgMedia.setTag((Object) null);
        this.sdMedia.setTag((Object) null);
        this.selectedCheck.setTag((Object) null);
        this.subtitle.setTag((Object) null);
        this.title.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
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
        if (BR.isFavorite == i) {
            setIsFavorite(((Boolean) obj).booleanValue());
        } else if (BR.item == i) {
            setItem((MediaLibraryItem) obj);
        } else if (BR.isNetwork == i) {
            setIsNetwork(((Boolean) obj).booleanValue());
        } else if (BR.isPresent == i) {
            setIsPresent(((Boolean) obj).booleanValue());
        } else if (BR.selected == i) {
            setSelected(((Boolean) obj).booleanValue());
        } else if (BR.inSelection == i) {
            setInSelection(((Boolean) obj).booleanValue());
        } else if (BR.imageWidth == i) {
            setImageWidth(((Integer) obj).intValue());
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.holder == i) {
            setHolder((AudioBrowserAdapter.MediaItemViewHolder) obj);
        } else if (BR.isSD == i) {
            setIsSD(((Boolean) obj).booleanValue());
        } else if (BR.isOTG != i) {
            return false;
        } else {
            setIsOTG(((Boolean) obj).booleanValue());
        }
        return true;
    }

    public void setIsFavorite(boolean z) {
        this.mIsFavorite = z;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.isFavorite);
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

    public void setIsNetwork(boolean z) {
        this.mIsNetwork = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.isNetwork);
        super.requestRebind();
    }

    public void setIsPresent(boolean z) {
        this.mIsPresent = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.isPresent);
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

    public void setInSelection(boolean z) {
        this.mInSelection = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.inSelection);
        super.requestRebind();
    }

    public void setImageWidth(int i) {
        this.mImageWidth = i;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.imageWidth);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setHolder(AudioBrowserAdapter.MediaItemViewHolder mediaItemViewHolder) {
        this.mHolder = mediaItemViewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setIsSD(boolean z) {
        this.mIsSD = z;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.isSD);
        super.requestRebind();
    }

    public void setIsOTG(boolean z) {
        this.mIsOTG = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.isOTG);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0199  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01b8  */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x01bb  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01c2  */
    /* JADX WARNING: Removed duplicated region for block: B:137:0x01e3  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0209  */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0214  */
    /* JADX WARNING: Removed duplicated region for block: B:146:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:149:0x024d  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x025a  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0268  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:161:0x0282  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0294  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x029f  */
    /* JADX WARNING: Removed duplicated region for block: B:176:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00d9  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00e3  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0179  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r51 = this;
            r1 = r51
            monitor-enter(r51)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x02a5 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x02a5 }
            monitor-exit(r51)     // Catch:{ all -> 0x02a5 }
            boolean r0 = r1.mIsFavorite
            org.videolan.medialibrary.media.MediaLibraryItem r6 = r1.mItem
            boolean r7 = r1.mIsNetwork
            boolean r8 = r1.mIsPresent
            boolean r9 = r1.mSelected
            boolean r10 = r1.mInSelection
            int r11 = r1.mImageWidth
            android.graphics.drawable.BitmapDrawable r12 = r1.mCover
            org.videolan.vlc.gui.audio.AudioBrowserAdapter$MediaItemViewHolder r13 = r1.mHolder
            boolean r14 = r1.mIsSD
            boolean r15 = r1.mIsOTG
            r16 = 2049(0x801, double:1.0123E-320)
            long r18 = r2 & r16
            r20 = 8
            r21 = r11
            int r22 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x0040
            int r22 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r22 == 0) goto L_0x003a
            if (r0 == 0) goto L_0x0036
            r18 = 32768(0x8000, double:1.61895E-319)
            goto L_0x0038
        L_0x0036:
            r18 = 16384(0x4000, double:8.0948E-320)
        L_0x0038:
            long r2 = r2 | r18
        L_0x003a:
            if (r0 == 0) goto L_0x003d
            goto L_0x0040
        L_0x003d:
            r0 = 8
            goto L_0x0041
        L_0x0040:
            r0 = 0
        L_0x0041:
            r18 = 2114(0x842, double:1.0445E-320)
            long r22 = r2 & r18
            r24 = 2050(0x802, double:1.013E-320)
            r26 = 0
            int r27 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r27 == 0) goto L_0x005c
            long r22 = r2 & r24
            int r27 = (r22 > r4 ? 1 : (r22 == r4 ? 0 : -1))
            if (r27 == 0) goto L_0x005c
            if (r6 == 0) goto L_0x005c
            java.lang.String r22 = r6.getTitle()
            r11 = r22
            goto L_0x005e
        L_0x005c:
            r11 = r26
        L_0x005e:
            r27 = 2052(0x804, double:1.014E-320)
            long r29 = r2 & r27
            int r23 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x0079
            int r23 = (r29 > r4 ? 1 : (r29 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x0073
            if (r7 == 0) goto L_0x006f
            r29 = 8192(0x2000, double:4.0474E-320)
            goto L_0x0071
        L_0x006f:
            r29 = 4096(0x1000, double:2.0237E-320)
        L_0x0071:
            long r2 = r2 | r29
        L_0x0073:
            if (r7 == 0) goto L_0x0076
            goto L_0x0079
        L_0x0076:
            r7 = 8
            goto L_0x007a
        L_0x0079:
            r7 = 0
        L_0x007a:
            r29 = 2088(0x828, double:1.0316E-320)
            long r31 = r2 & r29
            r33 = 536870912(0x20000000, double:2.652494739E-315)
            r35 = 2056(0x808, double:1.016E-320)
            int r23 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x00b6
            int r23 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x0095
            if (r8 == 0) goto L_0x0090
            long r2 = r2 | r33
            goto L_0x0095
        L_0x0090:
            r31 = 268435456(0x10000000, double:1.32624737E-315)
            long r2 = r2 | r31
        L_0x0095:
            long r31 = r2 & r35
            int r23 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x00b6
            r23 = r8 ^ 1
            int r37 = (r31 > r4 ? 1 : (r31 == r4 ? 0 : -1))
            if (r37 == 0) goto L_0x00ac
            if (r23 == 0) goto L_0x00a7
            r31 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x00aa
        L_0x00a7:
            r31 = 4194304(0x400000, double:2.0722615E-317)
        L_0x00aa:
            long r2 = r2 | r31
        L_0x00ac:
            if (r23 == 0) goto L_0x00b1
            r23 = 0
            goto L_0x00b3
        L_0x00b1:
            r23 = 8
        L_0x00b3:
            r38 = r23
            goto L_0x00b8
        L_0x00b6:
            r38 = 0
        L_0x00b8:
            r31 = 2064(0x810, double:1.0198E-320)
            long r39 = r2 & r31
            int r23 = (r39 > r4 ? 1 : (r39 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x00d9
            int r23 = (r39 > r4 ? 1 : (r39 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x00cf
            if (r9 == 0) goto L_0x00ca
            r39 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x00cd
        L_0x00ca:
            r39 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x00cd:
            long r2 = r2 | r39
        L_0x00cf:
            if (r9 == 0) goto L_0x00d4
            r23 = 0
            goto L_0x00d6
        L_0x00d4:
            r23 = 8
        L_0x00d6:
            r41 = r23
            goto L_0x00db
        L_0x00d9:
            r41 = 0
        L_0x00db:
            r39 = 2304(0x900, double:1.1383E-320)
            long r42 = r2 & r39
            int r23 = (r42 > r4 ? 1 : (r42 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x0162
            if (r13 == 0) goto L_0x0132
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl r4 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x00f0
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl r4 = new org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl
            r4.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r4
        L_0x00f0:
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl r4 = r4.setValue(r13)
            boolean r5 = r13.getCanBeReordered()
            android.view.View$OnTouchListener r23 = r13.getOnTouchListener()
            r26 = r4
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnLongClickListenerImpl r4 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r4 != 0) goto L_0x0109
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnLongClickListenerImpl r4 = new org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnLongClickListenerImpl
            r4.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r4
        L_0x0109:
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnLongClickListenerImpl r4 = r4.setValue(r13)
            r37 = r4
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl1 r4 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x011a
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl1 r4 = new org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl1
            r4.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r4
        L_0x011a:
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl1 r4 = r4.setValue(r13)
            r46 = r4
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl2 r4 = r1.mHolderOnImageClickAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x012b
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl2 r4 = new org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl2
            r4.<init>()
            r1.mHolderOnImageClickAndroidViewViewOnClickListener = r4
        L_0x012b:
            org.videolan.vlc.databinding.AudioBrowserItemBindingImpl$OnClickListenerImpl2 r4 = r4.setValue(r13)
            r44 = 0
            goto L_0x013d
        L_0x0132:
            r44 = r4
            r4 = r26
            r23 = r4
            r37 = r23
            r46 = r37
            r5 = 0
        L_0x013d:
            int r13 = (r42 > r44 ? 1 : (r42 == r44 ? 0 : -1))
            if (r13 == 0) goto L_0x014c
            if (r5 == 0) goto L_0x0147
            r42 = 131072(0x20000, double:6.47582E-319)
            goto L_0x014a
        L_0x0147:
            r42 = 65536(0x10000, double:3.2379E-319)
        L_0x014a:
            long r2 = r2 | r42
        L_0x014c:
            if (r5 == 0) goto L_0x0150
            r5 = 0
            goto L_0x0152
        L_0x0150:
            r5 = 8
        L_0x0152:
            r13 = r23
            r23 = r7
            r7 = r37
            r37 = r12
            r12 = r4
            r4 = r26
            r26 = r0
            r0 = r46
            goto L_0x016f
        L_0x0162:
            r23 = r7
            r37 = r12
            r4 = r26
            r7 = r4
            r12 = r7
            r13 = r12
            r5 = 0
            r26 = r0
            r0 = r13
        L_0x016f:
            r42 = 2560(0xa00, double:1.265E-320)
            long r46 = r2 & r42
            r44 = 0
            int r48 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r48 == 0) goto L_0x018e
            int r48 = (r46 > r44 ? 1 : (r46 == r44 ? 0 : -1))
            if (r48 == 0) goto L_0x0188
            if (r14 == 0) goto L_0x0183
            r46 = 524288(0x80000, double:2.590327E-318)
            goto L_0x0186
        L_0x0183:
            r46 = 262144(0x40000, double:1.295163E-318)
        L_0x0186:
            long r2 = r2 | r46
        L_0x0188:
            if (r14 == 0) goto L_0x018b
            goto L_0x018e
        L_0x018b:
            r14 = 8
            goto L_0x018f
        L_0x018e:
            r14 = 0
        L_0x018f:
            r46 = 3072(0xc00, double:1.518E-320)
            long r48 = r2 & r46
            r44 = 0
            int r50 = (r48 > r44 ? 1 : (r48 == r44 ? 0 : -1))
            if (r50 == 0) goto L_0x01af
            int r50 = (r48 > r44 ? 1 : (r48 == r44 ? 0 : -1))
            if (r50 == 0) goto L_0x01a8
            if (r15 == 0) goto L_0x01a3
            r48 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x01a6
        L_0x01a3:
            r48 = 16777216(0x1000000, double:8.289046E-317)
        L_0x01a6:
            long r2 = r2 | r48
        L_0x01a8:
            if (r15 == 0) goto L_0x01ac
            r20 = 0
        L_0x01ac:
            r15 = r20
            goto L_0x01b0
        L_0x01af:
            r15 = 0
        L_0x01b0:
            long r33 = r2 & r33
            r44 = 0
            int r20 = (r33 > r44 ? 1 : (r33 == r44 ? 0 : -1))
            if (r20 == 0) goto L_0x01bb
            r10 = r10 ^ 1
            goto L_0x01bc
        L_0x01bb:
            r10 = 0
        L_0x01bc:
            long r33 = r2 & r29
            int r20 = (r33 > r44 ? 1 : (r33 == r44 ? 0 : -1))
            if (r20 == 0) goto L_0x01da
            if (r8 == 0) goto L_0x01c5
            goto L_0x01c6
        L_0x01c5:
            r10 = 0
        L_0x01c6:
            int r8 = (r33 > r44 ? 1 : (r33 == r44 ? 0 : -1))
            if (r8 == 0) goto L_0x01d5
            if (r10 == 0) goto L_0x01d0
            r33 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x01d3
        L_0x01d0:
            r33 = 1048576(0x100000, double:5.180654E-318)
        L_0x01d3:
            long r2 = r2 | r33
        L_0x01d5:
            if (r10 == 0) goto L_0x01d8
            goto L_0x01da
        L_0x01d8:
            r8 = 4
            goto L_0x01db
        L_0x01da:
            r8 = 0
        L_0x01db:
            long r33 = r2 & r39
            r39 = 0
            int r10 = (r33 > r39 ? 1 : (r33 == r39 ? 0 : -1))
            if (r10 == 0) goto L_0x0201
            android.widget.ImageView r10 = r1.itemMore
            r10.setOnClickListener(r0)
            android.widget.ImageView r0 = r1.itemMove
            r0.setVisibility(r5)
            android.widget.ImageView r0 = r1.itemMove
            r0.setOnTouchListener(r13)
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView0
            r0.setOnClickListener(r4)
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView0
            r0.setOnLongClickListener(r7)
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r0.setOnClickListener(r12)
        L_0x0201:
            long r4 = r2 & r29
            r12 = 0
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x020e
            android.widget.ImageView r0 = r1.itemMore
            r0.setVisibility(r8)
        L_0x020e:
            long r4 = r2 & r31
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0225
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView0
            r0.setSelected(r9)
            android.widget.ImageView r0 = r1.mlItemOverlay
            r4 = r41
            r0.setVisibility(r4)
            android.widget.ImageView r0 = r1.selectedCheck
            r0.setVisibility(r4)
        L_0x0225:
            long r4 = r2 & r24
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0244
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView0
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r0, r6)
            android.widget.TextView r0 = r1.subtitle
            org.videolan.vlc.util.KextensionsKt.asyncTextItem(r0, r6)
            android.widget.TextView r0 = r1.subtitle
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r0, r6)
            android.widget.TextView r0 = r1.title
            org.videolan.vlc.util.KextensionsKt.asyncText(r0, r11)
            android.widget.TextView r0 = r1.title
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r0, r6)
        L_0x0244:
            r4 = 2176(0x880, double:1.075E-320)
            long r4 = r4 & r2
            r7 = 0
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0254
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r4 = r37
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r4)
        L_0x0254:
            long r4 = r2 & r18
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0262
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r4 = r21
            r5 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r0, r6, r4, r5, r5)
        L_0x0262:
            long r4 = r2 & r16
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x026f
            android.widget.ImageView r0 = r1.mediaFavorite
            r11 = r26
            r0.setVisibility(r11)
        L_0x026f:
            long r4 = r2 & r27
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x027c
            android.widget.ImageView r0 = r1.networkMedia
            r4 = r23
            r0.setVisibility(r4)
        L_0x027c:
            long r4 = r2 & r35
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x028e
            android.widget.ImageView r0 = r1.networkMediaOff
            r4 = r38
            r0.setVisibility(r4)
            android.view.View r0 = r1.networkOffOverlay
            r0.setVisibility(r4)
        L_0x028e:
            long r4 = r2 & r46
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0299
            android.widget.ImageView r0 = r1.otgMedia
            r0.setVisibility(r15)
        L_0x0299:
            long r2 = r2 & r42
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x02a4
            android.widget.ImageView r0 = r1.sdMedia
            r0.setVisibility(r14)
        L_0x02a4:
            return
        L_0x02a5:
            r0 = move-exception
            monitor-exit(r51)     // Catch:{ all -> 0x02a5 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.AudioBrowserItemBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private AudioBrowserAdapter.MediaItemViewHolder value;

        public OnClickListenerImpl setValue(AudioBrowserAdapter.MediaItemViewHolder mediaItemViewHolder) {
            this.value = mediaItemViewHolder;
            if (mediaItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private AudioBrowserAdapter.MediaItemViewHolder value;

        public OnLongClickListenerImpl setValue(AudioBrowserAdapter.MediaItemViewHolder mediaItemViewHolder) {
            this.value = mediaItemViewHolder;
            if (mediaItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View view) {
            return this.value.onLongClick(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private AudioBrowserAdapter.MediaItemViewHolder value;

        public OnClickListenerImpl1 setValue(AudioBrowserAdapter.MediaItemViewHolder mediaItemViewHolder) {
            this.value = mediaItemViewHolder;
            if (mediaItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onMoreClick(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private AudioBrowserAdapter.MediaItemViewHolder value;

        public OnClickListenerImpl2 setValue(AudioBrowserAdapter.MediaItemViewHolder mediaItemViewHolder) {
            this.value = mediaItemViewHolder;
            if (mediaItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onImageClick(view);
        }
    }
}
