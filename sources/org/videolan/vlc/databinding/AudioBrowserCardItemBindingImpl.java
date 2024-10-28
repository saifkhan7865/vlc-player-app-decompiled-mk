package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioBrowserAdapter;

public class AudioBrowserCardItemBindingImpl extends AudioBrowserCardItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;
    private OnClickListenerImpl1 mHolderOnMainActionClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl2 mHolderOnMoreClickAndroidViewViewOnClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.playing, 15);
    }

    public AudioBrowserCardItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 16, sIncludes, sViewsWithIds));
    }

    private AudioBrowserCardItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[0], objArr[13], objArr[4], objArr[2], objArr[1], objArr[10], objArr[3], objArr[8], objArr[9], objArr[5], objArr[7], objArr[15], objArr[6], objArr[14], objArr[12], objArr[11]);
        this.mDirtyFlags = -1;
        this.container.setTag((Object) null);
        this.itemMore.setTag((Object) null);
        this.mainActionButton.setTag((Object) null);
        this.mediaCover.setTag((Object) null);
        this.mediaCoverContainer.setTag((Object) null);
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
            this.mDirtyFlags = 8192;
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
        } else if (BR.bgColor == i) {
            setBgColor(((Integer) obj).intValue());
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
            setHolder((AudioBrowserAdapter.MediaItemCardViewHolder) obj);
        } else if (BR.scaleType == i) {
            setScaleType((ImageView.ScaleType) obj);
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

    public void setBgColor(int i) {
        this.mBgColor = i;
    }

    public void setIsNetwork(boolean z) {
        this.mIsNetwork = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.isNetwork);
        super.requestRebind();
    }

    public void setIsPresent(boolean z) {
        this.mIsPresent = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.isPresent);
        super.requestRebind();
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.selected);
        super.requestRebind();
    }

    public void setInSelection(boolean z) {
        this.mInSelection = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.inSelection);
        super.requestRebind();
    }

    public void setImageWidth(int i) {
        this.mImageWidth = i;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.imageWidth);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setHolder(AudioBrowserAdapter.MediaItemCardViewHolder mediaItemCardViewHolder) {
        this.mHolder = mediaItemCardViewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.scaleType);
        super.requestRebind();
    }

    public void setIsSD(boolean z) {
        this.mIsSD = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.isSD);
        super.requestRebind();
    }

    public void setIsOTG(boolean z) {
        this.mIsOTG = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(BR.isOTG);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01a3  */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x01c0  */
    /* JADX WARNING: Removed duplicated region for block: B:127:0x01c9  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01fb  */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x021c  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0238  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x0244  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x025d  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x026c  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x0279  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0286  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x0298  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x02a3  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x02b1  */
    /* JADX WARNING: Removed duplicated region for block: B:172:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f2 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0179  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r51 = this;
            r1 = r51
            monitor-enter(r51)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x02b8 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x02b8 }
            monitor-exit(r51)     // Catch:{ all -> 0x02b8 }
            boolean r0 = r1.mIsFavorite
            org.videolan.medialibrary.media.MediaLibraryItem r6 = r1.mItem
            boolean r7 = r1.mIsNetwork
            boolean r8 = r1.mIsPresent
            boolean r9 = r1.mSelected
            boolean r10 = r1.mInSelection
            int r11 = r1.mImageWidth
            android.graphics.drawable.BitmapDrawable r12 = r1.mCover
            org.videolan.vlc.gui.audio.AudioBrowserAdapter$MediaItemCardViewHolder r13 = r1.mHolder
            android.widget.ImageView$ScaleType r14 = r1.mScaleType
            boolean r15 = r1.mIsSD
            boolean r4 = r1.mIsOTG
            r18 = 8193(0x2001, double:4.048E-320)
            long r20 = r2 & r18
            r16 = 0
            int r23 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r23 == 0) goto L_0x0041
            int r23 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r23 == 0) goto L_0x003b
            if (r0 == 0) goto L_0x0036
            r20 = 131072(0x20000, double:6.47582E-319)
            goto L_0x0039
        L_0x0036:
            r20 = 65536(0x10000, double:3.2379E-319)
        L_0x0039:
            long r2 = r2 | r20
        L_0x003b:
            if (r0 == 0) goto L_0x003e
            goto L_0x0041
        L_0x003e:
            r0 = 8
            goto L_0x0042
        L_0x0041:
            r0 = 0
        L_0x0042:
            r20 = 8322(0x2082, double:4.1116E-320)
            long r23 = r2 & r20
            r25 = 8194(0x2002, double:4.0484E-320)
            r27 = 0
            r16 = 0
            int r28 = (r23 > r16 ? 1 : (r23 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x005f
            long r23 = r2 & r25
            int r28 = (r23 > r16 ? 1 : (r23 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x005f
            if (r6 == 0) goto L_0x005f
            java.lang.String r23 = r6.getTitle()
            r5 = r23
            goto L_0x0061
        L_0x005f:
            r5 = r27
        L_0x0061:
            r28 = 8200(0x2008, double:4.0513E-320)
            long r30 = r2 & r28
            int r24 = (r30 > r16 ? 1 : (r30 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x007d
            int r24 = (r30 > r16 ? 1 : (r30 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x0077
            if (r7 == 0) goto L_0x0073
            r30 = 32768(0x8000, double:1.61895E-319)
            goto L_0x0075
        L_0x0073:
            r30 = 16384(0x4000, double:8.0948E-320)
        L_0x0075:
            long r2 = r2 | r30
        L_0x0077:
            if (r7 == 0) goto L_0x007a
            goto L_0x007d
        L_0x007a:
            r7 = 8
            goto L_0x007e
        L_0x007d:
            r7 = 0
        L_0x007e:
            r30 = 8272(0x2050, double:4.087E-320)
            long r32 = r2 & r30
            r34 = 536870912(0x20000000, double:2.652494739E-315)
            r36 = 8208(0x2010, double:4.0553E-320)
            r16 = 0
            int r24 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x00bc
            int r24 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x009b
            if (r8 == 0) goto L_0x0096
            long r2 = r2 | r34
            goto L_0x009b
        L_0x0096:
            r32 = 268435456(0x10000000, double:1.32624737E-315)
            long r2 = r2 | r32
        L_0x009b:
            long r32 = r2 & r36
            int r24 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x00bc
            r24 = r8 ^ 1
            int r38 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r38 == 0) goto L_0x00b2
            if (r24 == 0) goto L_0x00ad
            r32 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x00b0
        L_0x00ad:
            r32 = 4194304(0x400000, double:2.0722615E-317)
        L_0x00b0:
            long r2 = r2 | r32
        L_0x00b2:
            if (r24 == 0) goto L_0x00b7
            r24 = 0
            goto L_0x00b9
        L_0x00b7:
            r24 = 8
        L_0x00b9:
            r39 = r24
            goto L_0x00be
        L_0x00bc:
            r39 = 0
        L_0x00be:
            r32 = 8224(0x2020, double:4.063E-320)
            long r40 = r2 & r32
            r16 = 0
            int r24 = (r40 > r16 ? 1 : (r40 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x00e5
            int r24 = (r40 > r16 ? 1 : (r40 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x00d7
            if (r9 == 0) goto L_0x00d2
            r40 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x00d5
        L_0x00d2:
            r40 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x00d5:
            long r2 = r2 | r40
        L_0x00d7:
            if (r9 == 0) goto L_0x00dc
            r24 = 0
            goto L_0x00de
        L_0x00dc:
            r24 = 8
        L_0x00de:
            r50 = r24
            r24 = r7
            r7 = r50
            goto L_0x00e8
        L_0x00e5:
            r24 = r7
            r7 = 0
        L_0x00e8:
            r40 = 8704(0x2200, double:4.3003E-320)
            long r42 = r2 & r40
            r16 = 0
            int r38 = (r42 > r16 ? 1 : (r42 == r16 ? 0 : -1))
            if (r38 == 0) goto L_0x0144
            if (r13 == 0) goto L_0x0144
            r38 = r0
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl r0 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r0 != 0) goto L_0x0101
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl r0 = new org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl
            r0.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r0
        L_0x0101:
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl r27 = r0.setValue(r13)
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnLongClickListenerImpl r0 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r0 != 0) goto L_0x0110
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnLongClickListenerImpl r0 = new org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnLongClickListenerImpl
            r0.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r0
        L_0x0110:
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnLongClickListenerImpl r0 = r0.setValue(r13)
            r42 = r0
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl1 r0 = r1.mHolderOnMainActionClickAndroidViewViewOnClickListener
            if (r0 != 0) goto L_0x0121
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl1 r0 = new org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl1
            r0.<init>()
            r1.mHolderOnMainActionClickAndroidViewViewOnClickListener = r0
        L_0x0121:
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl1 r0 = r0.setValue(r13)
            r43 = r0
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl2 r0 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r0 != 0) goto L_0x0132
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl2 r0 = new org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl2
            r0.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r0
        L_0x0132:
            org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl$OnClickListenerImpl2 r0 = r0.setValue(r13)
            r13 = r27
            r27 = r11
            r11 = r0
            r0 = r43
            r50 = r42
            r42 = r12
            r12 = r50
            goto L_0x014f
        L_0x0144:
            r38 = r0
            r42 = r12
            r0 = r27
            r12 = r0
            r13 = r12
            r27 = r11
            r11 = r13
        L_0x014f:
            r43 = 10240(0x2800, double:5.059E-320)
            long r45 = r2 & r43
            r16 = 0
            int r47 = (r45 > r16 ? 1 : (r45 == r16 ? 0 : -1))
            if (r47 == 0) goto L_0x016e
            int r47 = (r45 > r16 ? 1 : (r45 == r16 ? 0 : -1))
            if (r47 == 0) goto L_0x0168
            if (r15 == 0) goto L_0x0163
            r45 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0166
        L_0x0163:
            r45 = 1048576(0x100000, double:5.180654E-318)
        L_0x0166:
            long r2 = r2 | r45
        L_0x0168:
            if (r15 == 0) goto L_0x016b
            goto L_0x016e
        L_0x016b:
            r15 = 8
            goto L_0x016f
        L_0x016e:
            r15 = 0
        L_0x016f:
            r45 = 12288(0x3000, double:6.071E-320)
            long r47 = r2 & r45
            r16 = 0
            int r49 = (r47 > r16 ? 1 : (r47 == r16 ? 0 : -1))
            if (r49 == 0) goto L_0x018e
            int r49 = (r47 > r16 ? 1 : (r47 == r16 ? 0 : -1))
            if (r49 == 0) goto L_0x0188
            if (r4 == 0) goto L_0x0183
            r47 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x0186
        L_0x0183:
            r47 = 16777216(0x1000000, double:8.289046E-317)
        L_0x0186:
            long r2 = r2 | r47
        L_0x0188:
            if (r4 == 0) goto L_0x018b
            goto L_0x018e
        L_0x018b:
            r4 = 8
            goto L_0x018f
        L_0x018e:
            r4 = 0
        L_0x018f:
            long r34 = r2 & r34
            r47 = r15
            r15 = 1
            r16 = 0
            int r48 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r48 == 0) goto L_0x019c
            r10 = r10 ^ r15
            goto L_0x019d
        L_0x019c:
            r10 = 0
        L_0x019d:
            long r34 = r2 & r30
            int r48 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r48 == 0) goto L_0x01c0
            if (r8 == 0) goto L_0x01a6
            goto L_0x01a7
        L_0x01a6:
            r10 = 0
        L_0x01a7:
            int r8 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r8 == 0) goto L_0x01b6
            if (r10 == 0) goto L_0x01b1
            r34 = 524288(0x80000, double:2.590327E-318)
            goto L_0x01b4
        L_0x01b1:
            r34 = 262144(0x40000, double:1.295163E-318)
        L_0x01b4:
            long r2 = r2 | r34
        L_0x01b6:
            if (r10 == 0) goto L_0x01bb
            r22 = 0
            goto L_0x01bd
        L_0x01bb:
            r22 = 8
        L_0x01bd:
            r8 = r22
            goto L_0x01c1
        L_0x01c0:
            r8 = 0
        L_0x01c1:
            long r32 = r2 & r32
            r16 = 0
            int r10 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r10 == 0) goto L_0x01f3
            androidx.constraintlayout.widget.ConstraintLayout r10 = r1.container
            r10.setSelected(r9)
            androidx.constraintlayout.widget.ConstraintLayout r10 = r1.container
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r9)
            org.videolan.vlc.gui.helpers.UiToolsKt.selectedPadding(r10, r15)
            com.google.android.material.card.MaterialCardView r10 = r1.mediaCoverContainer
            java.lang.Boolean r15 = java.lang.Boolean.valueOf(r9)
            org.videolan.vlc.gui.helpers.UiToolsKt.selectedElevation(r10, r15)
            android.widget.ImageView r10 = r1.mlItemOverlay
            r10.setVisibility(r7)
            androidx.appcompat.widget.AppCompatImageView r10 = r1.selectedCheck
            r10.setVisibility(r7)
            androidx.appcompat.widget.AppCompatImageView r7 = r1.selectedCheck
            java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
            org.videolan.vlc.gui.helpers.UiToolsKt.selectedElevation(r7, r9)
        L_0x01f3:
            long r9 = r2 & r25
            r15 = 0
            int r7 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r7 == 0) goto L_0x0214
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.container
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r7, r6)
            android.widget.TextView r7 = r1.subtitle
            org.videolan.vlc.util.KextensionsKt.asyncTextItem(r7, r6)
            android.widget.TextView r7 = r1.subtitle
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r7, r6)
            android.widget.TextView r7 = r1.title
            org.videolan.vlc.util.KextensionsKt.asyncText(r7, r5)
            android.widget.TextView r5 = r1.title
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r5, r6)
        L_0x0214:
            long r9 = r2 & r40
            r15 = 0
            int r5 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r5 == 0) goto L_0x0230
            androidx.constraintlayout.widget.ConstraintLayout r5 = r1.container
            r5.setOnClickListener(r13)
            androidx.constraintlayout.widget.ConstraintLayout r5 = r1.container
            r5.setOnLongClickListener(r12)
            androidx.appcompat.widget.AppCompatImageView r5 = r1.itemMore
            r5.setOnClickListener(r11)
            org.videolan.vlc.gui.view.FadableImageView r5 = r1.mainActionButton
            r5.setOnClickListener(r0)
        L_0x0230:
            long r9 = r2 & r30
            r11 = 0
            int r0 = (r9 > r11 ? 1 : (r9 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x023d
            androidx.appcompat.widget.AppCompatImageView r0 = r1.itemMore
            r0.setVisibility(r8)
        L_0x023d:
            r7 = 9216(0x2400, double:4.5533E-320)
            long r7 = r7 & r2
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0249
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r0.setScaleType(r14)
        L_0x0249:
            r7 = 8448(0x2100, double:4.174E-320)
            long r7 = r7 & r2
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0257
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r5 = r42
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r5)
        L_0x0257:
            long r7 = r2 & r20
            int r0 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0266
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r5 = r27
            r7 = 1
            r8 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r0, r6, r5, r8, r7)
        L_0x0266:
            long r5 = r2 & r18
            int r0 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0273
            android.widget.ImageView r0 = r1.mediaFavorite
            r5 = r38
            r0.setVisibility(r5)
        L_0x0273:
            long r5 = r2 & r28
            int r0 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0280
            android.widget.ImageView r0 = r1.networkMedia
            r7 = r24
            r0.setVisibility(r7)
        L_0x0280:
            long r5 = r2 & r36
            int r0 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x0292
            android.widget.ImageView r0 = r1.networkMediaOff
            r5 = r39
            r0.setVisibility(r5)
            android.view.View r0 = r1.networkOffOverlay
            r0.setVisibility(r5)
        L_0x0292:
            long r5 = r2 & r45
            int r0 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x029d
            android.widget.ImageView r0 = r1.otgMedia
            r0.setVisibility(r4)
        L_0x029d:
            long r4 = r2 & r43
            int r0 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x02aa
            android.widget.ImageView r0 = r1.sdMedia
            r15 = r47
            r0.setVisibility(r15)
        L_0x02aa:
            r4 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 & r4
            int r0 = (r2 > r11 ? 1 : (r2 == r11 ? 0 : -1))
            if (r0 == 0) goto L_0x02b7
            android.widget.TextView r0 = r1.title
            r2 = 1
            org.videolan.vlc.gui.helpers.UiToolsKt.setEllipsizeModeByPref(r0, r2)
        L_0x02b7:
            return
        L_0x02b8:
            r0 = move-exception
            monitor-exit(r51)     // Catch:{ all -> 0x02b8 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private AudioBrowserAdapter.MediaItemCardViewHolder value;

        public OnClickListenerImpl setValue(AudioBrowserAdapter.MediaItemCardViewHolder mediaItemCardViewHolder) {
            this.value = mediaItemCardViewHolder;
            if (mediaItemCardViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private AudioBrowserAdapter.MediaItemCardViewHolder value;

        public OnLongClickListenerImpl setValue(AudioBrowserAdapter.MediaItemCardViewHolder mediaItemCardViewHolder) {
            this.value = mediaItemCardViewHolder;
            if (mediaItemCardViewHolder == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View view) {
            return this.value.onLongClick(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private AudioBrowserAdapter.MediaItemCardViewHolder value;

        public OnClickListenerImpl1 setValue(AudioBrowserAdapter.MediaItemCardViewHolder mediaItemCardViewHolder) {
            this.value = mediaItemCardViewHolder;
            if (mediaItemCardViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onMainActionClick(view);
        }
    }

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private AudioBrowserAdapter.MediaItemCardViewHolder value;

        public OnClickListenerImpl2 setValue(AudioBrowserAdapter.MediaItemCardViewHolder mediaItemCardViewHolder) {
            this.value = mediaItemCardViewHolder;
            if (mediaItemCardViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onMoreClick(view);
        }
    }
}
