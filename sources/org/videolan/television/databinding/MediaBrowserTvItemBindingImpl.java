package org.videolan.television.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.BR;
import org.videolan.television.ui.MediaTvItemAdapter;

public class MediaBrowserTvItemBindingImpl extends MediaBrowserTvItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public MediaBrowserTvItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private MediaBrowserTvItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[9], objArr[0], objArr[8], objArr[1], objArr[3], objArr[4], objArr[7], objArr[2], objArr[6], objArr[10], objArr[5], objArr[12], objArr[11]);
        this.mDirtyFlags = -1;
        this.badgeTV.setTag((Object) null);
        this.container.setTag((Object) null);
        this.dviIconTv.setTag((Object) null);
        this.mediaCover.setTag((Object) null);
        this.mlItemSeen.setTag((Object) null);
        this.networkMedia.setTag((Object) null);
        this.networkMediaOff.setTag((Object) null);
        this.networkOffOverlay.setTag((Object) null);
        this.otgMedia.setTag((Object) null);
        this.progressBar.setTag((Object) null);
        this.sdMedia.setTag((Object) null);
        this.subtitle.setTag((Object) null);
        this.title.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
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
        if (BR.item == i) {
            setItem((MediaLibraryItem) obj);
        } else if (BR.isNetwork == i) {
            setIsNetwork(((Boolean) obj).booleanValue());
        } else if (BR.isPresent == i) {
            setIsPresent(((Boolean) obj).booleanValue());
        } else if (BR.max == i) {
            setMax(((Integer) obj).intValue());
        } else if (BR.progress == i) {
            setProgress(((Integer) obj).intValue());
        } else if (BR.description == i) {
            setDescription((String) obj);
        } else if (BR.imageWidth == i) {
            setImageWidth(((Integer) obj).intValue());
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.protocol == i) {
            setProtocol((String) obj);
        } else if (BR.holder == i) {
            setHolder((MediaTvItemAdapter.AbstractMediaItemViewHolder) obj);
        } else if (BR.badge == i) {
            setBadge((String) obj);
        } else if (BR.isSquare == i) {
            setIsSquare((Boolean) obj);
        } else if (BR.seen == i) {
            setSeen(((Long) obj).longValue());
        } else if (BR.showSeen == i) {
            setShowSeen(((Boolean) obj).booleanValue());
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

    public void setItem(MediaLibraryItem mediaLibraryItem) {
        this.mItem = mediaLibraryItem;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setIsNetwork(boolean z) {
        this.mIsNetwork = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.isNetwork);
        super.requestRebind();
    }

    public void setIsPresent(boolean z) {
        this.mIsPresent = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.isPresent);
        super.requestRebind();
    }

    public void setMax(int i) {
        this.mMax = i;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.max);
        super.requestRebind();
    }

    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.progress);
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

    public void setProtocol(String str) {
        this.mProtocol = str;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.protocol);
        super.requestRebind();
    }

    public void setHolder(MediaTvItemAdapter.AbstractMediaItemViewHolder abstractMediaItemViewHolder) {
        this.mHolder = abstractMediaItemViewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setBadge(String str) {
        this.mBadge = str;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.badge);
        super.requestRebind();
    }

    public void setIsSquare(Boolean bool) {
        this.mIsSquare = bool;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.isSquare);
        super.requestRebind();
    }

    public void setSeen(long j) {
        this.mSeen = j;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(BR.seen);
        super.requestRebind();
    }

    public void setShowSeen(boolean z) {
        this.mShowSeen = z;
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        notifyPropertyChanged(BR.showSeen);
        super.requestRebind();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        notifyPropertyChanged(BR.scaleType);
        super.requestRebind();
    }

    public void setIsSD(boolean z) {
        this.mIsSD = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.isSD);
        super.requestRebind();
    }

    public void setIsOTG(boolean z) {
        this.mIsOTG = z;
        synchronized (this) {
            this.mDirtyFlags |= 65536;
        }
        notifyPropertyChanged(BR.isOTG);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0186  */
    /* JADX WARNING: Removed duplicated region for block: B:113:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:114:0x01ac  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01b3  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01d4  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x01f1  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0208  */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x0221  */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x022b  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:157:0x024f  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x025e  */
    /* JADX WARNING: Removed duplicated region for block: B:163:0x026b  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0275  */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x027c  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0292  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x029d  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x02af  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x02bc  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x02db  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x02e8  */
    /* JADX WARNING: Removed duplicated region for block: B:200:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r58 = this;
            r1 = r58
            monitor-enter(r58)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x02f0 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x02f0 }
            monitor-exit(r58)     // Catch:{ all -> 0x02f0 }
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r1.mItem
            boolean r6 = r1.mIsNetwork
            boolean r7 = r1.mIsPresent
            int r8 = r1.mMax
            int r9 = r1.mProgress
            java.lang.String r10 = r1.mDescription
            int r11 = r1.mImageWidth
            android.graphics.drawable.BitmapDrawable r12 = r1.mCover
            java.lang.String r13 = r1.mProtocol
            org.videolan.television.ui.MediaTvItemAdapter$AbstractMediaItemViewHolder r14 = r1.mHolder
            java.lang.String r15 = r1.mBadge
            java.lang.Boolean r4 = r1.mIsSquare
            r5 = r10
            r18 = r11
            long r10 = r1.mSeen
            r19 = r5
            boolean r5 = r1.mShowSeen
            r20 = r8
            android.widget.ImageView$ScaleType r8 = r1.mScaleType
            r21 = r12
            boolean r12 = r1.mIsSD
            r22 = r8
            boolean r8 = r1.mIsOTG
            r23 = 131137(0x20041, double:6.47903E-319)
            long r25 = r2 & r23
            r27 = 131073(0x20001, double:6.47587E-319)
            r29 = 0
            r16 = 0
            int r30 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x0056
            long r25 = r2 & r27
            int r30 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x0056
            if (r0 == 0) goto L_0x0056
            java.lang.String r25 = r0.getTitle()
            r31 = r25
            goto L_0x0058
        L_0x0056:
            r31 = r29
        L_0x0058:
            r25 = 131078(0x20006, double:6.4761E-319)
            long r32 = r2 & r25
            int r30 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x0070
            int r30 = (r32 > r16 ? 1 : (r32 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x0070
            if (r6 == 0) goto L_0x006b
            r32 = 524288(0x80000, double:2.590327E-318)
            goto L_0x006e
        L_0x006b:
            r32 = 262144(0x40000, double:1.295163E-318)
        L_0x006e:
            long r2 = r2 | r32
        L_0x0070:
            r32 = 131076(0x20004, double:6.476E-319)
            long r34 = r2 & r32
            r30 = 8
            r36 = r0
            r16 = 0
            int r37 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r37 == 0) goto L_0x009c
            r37 = r7 ^ 1
            int r38 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r38 == 0) goto L_0x0092
            if (r37 == 0) goto L_0x008d
            r34 = 2147483648(0x80000000, double:1.0609978955E-314)
            goto L_0x0090
        L_0x008d:
            r34 = 1073741824(0x40000000, double:5.304989477E-315)
        L_0x0090:
            long r2 = r2 | r34
        L_0x0092:
            if (r37 == 0) goto L_0x0097
            r34 = 0
            goto L_0x0099
        L_0x0097:
            r34 = 8
        L_0x0099:
            r39 = r34
            goto L_0x009e
        L_0x009c:
            r39 = 0
        L_0x009e:
            r34 = 131088(0x20010, double:6.4766E-319)
            long r37 = r2 & r34
            r16 = 0
            int r40 = (r37 > r16 ? 1 : (r37 == r16 ? 0 : -1))
            if (r40 == 0) goto L_0x00c9
            if (r9 <= 0) goto L_0x00ae
            r40 = 1
            goto L_0x00b0
        L_0x00ae:
            r40 = 0
        L_0x00b0:
            int r41 = (r37 > r16 ? 1 : (r37 == r16 ? 0 : -1))
            if (r41 == 0) goto L_0x00bf
            if (r40 == 0) goto L_0x00ba
            r37 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x00bd
        L_0x00ba:
            r37 = 16777216(0x1000000, double:8.289046E-317)
        L_0x00bd:
            long r2 = r2 | r37
        L_0x00bf:
            if (r40 == 0) goto L_0x00c4
            r37 = 0
            goto L_0x00c6
        L_0x00c4:
            r37 = 8
        L_0x00c6:
            r42 = r37
            goto L_0x00cb
        L_0x00c9:
            r42 = 0
        L_0x00cb:
            r37 = 131328(0x20100, double:6.48847E-319)
            long r40 = r2 & r37
            r16 = 0
            int r43 = (r40 > r16 ? 1 : (r40 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x00f3
            boolean r43 = android.text.TextUtils.isEmpty(r13)
            int r44 = (r40 > r16 ? 1 : (r40 == r16 ? 0 : -1))
            if (r44 == 0) goto L_0x00e9
            if (r43 == 0) goto L_0x00e4
            r40 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x00e7
        L_0x00e4:
            r40 = 1048576(0x100000, double:5.180654E-318)
        L_0x00e7:
            long r2 = r2 | r40
        L_0x00e9:
            if (r43 == 0) goto L_0x00ee
            r40 = 8
            goto L_0x00f0
        L_0x00ee:
            r40 = 0
        L_0x00f0:
            r45 = r40
            goto L_0x00f5
        L_0x00f3:
            r45 = 0
        L_0x00f5:
            r40 = 131584(0x20200, double:6.5011E-319)
            long r43 = r2 & r40
            r16 = 0
            int r46 = (r43 > r16 ? 1 : (r43 == r16 ? 0 : -1))
            if (r46 == 0) goto L_0x0123
            if (r14 == 0) goto L_0x0123
            org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnClickListenerImpl r0 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r0 != 0) goto L_0x010d
            org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnClickListenerImpl r0 = new org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnClickListenerImpl
            r0.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r0
        L_0x010d:
            org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnClickListenerImpl r29 = r0.setValue(r14)
            org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnLongClickListenerImpl r0 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r0 != 0) goto L_0x011c
            org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnLongClickListenerImpl r0 = new org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnLongClickListenerImpl
            r0.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r0
        L_0x011c:
            org.videolan.television.databinding.MediaBrowserTvItemBindingImpl$OnLongClickListenerImpl r0 = r0.setValue(r14)
            r14 = r29
            goto L_0x0126
        L_0x0123:
            r0 = r29
            r14 = r0
        L_0x0126:
            r46 = 133120(0x20800, double:6.577E-319)
            long r48 = r2 & r46
            r16 = 0
            int r29 = (r48 > r16 ? 1 : (r48 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x0136
            boolean r4 = androidx.databinding.ViewDataBinding.safeUnbox((java.lang.Boolean) r4)
            goto L_0x0137
        L_0x0136:
            r4 = 0
        L_0x0137:
            r48 = 143360(0x23000, double:7.08293E-319)
            long r50 = r2 & r48
            r52 = 67108864(0x4000000, double:3.31561842E-316)
            int r29 = (r50 > r16 ? 1 : (r50 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x0159
            int r29 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r29 != 0) goto L_0x0149
            r10 = 1
            goto L_0x014a
        L_0x0149:
            r10 = 0
        L_0x014a:
            int r11 = (r50 > r16 ? 1 : (r50 == r16 ? 0 : -1))
            if (r11 == 0) goto L_0x015a
            if (r10 == 0) goto L_0x0156
            r50 = 134217728(0x8000000, double:6.63123685E-316)
            long r2 = r2 | r50
            goto L_0x015a
        L_0x0156:
            long r2 = r2 | r52
            goto L_0x015a
        L_0x0159:
            r10 = 0
        L_0x015a:
            r50 = 163840(0x28000, double:8.09477E-319)
            long r54 = r2 & r50
            r16 = 0
            int r11 = (r54 > r16 ? 1 : (r54 == r16 ? 0 : -1))
            if (r11 == 0) goto L_0x017a
            int r11 = (r54 > r16 ? 1 : (r54 == r16 ? 0 : -1))
            if (r11 == 0) goto L_0x0174
            if (r12 == 0) goto L_0x016f
            r54 = 536870912(0x20000000, double:2.652494739E-315)
            goto L_0x0172
        L_0x016f:
            r54 = 268435456(0x10000000, double:1.32624737E-315)
        L_0x0172:
            long r2 = r2 | r54
        L_0x0174:
            if (r12 == 0) goto L_0x0177
            goto L_0x017a
        L_0x0177:
            r11 = 8
            goto L_0x017b
        L_0x017a:
            r11 = 0
        L_0x017b:
            r54 = 196608(0x30000, double:9.71373E-319)
            long r56 = r2 & r54
            r16 = 0
            int r12 = (r56 > r16 ? 1 : (r56 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x019f
            int r12 = (r56 > r16 ? 1 : (r56 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x0199
            if (r8 == 0) goto L_0x0192
            r56 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L_0x0197
        L_0x0192:
            r56 = 4294967296(0x100000000, double:2.121995791E-314)
        L_0x0197:
            long r2 = r2 | r56
        L_0x0199:
            if (r8 == 0) goto L_0x019c
            goto L_0x019f
        L_0x019c:
            r8 = 8
            goto L_0x01a0
        L_0x019f:
            r8 = 0
        L_0x01a0:
            long r52 = r2 & r52
            r16 = 0
            int r12 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01ac
            r12 = 1
            r5 = r5 ^ r12
            r12 = r5
            goto L_0x01ad
        L_0x01ac:
            r12 = 0
        L_0x01ad:
            long r52 = r2 & r25
            int r5 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r5 == 0) goto L_0x01cb
            if (r6 == 0) goto L_0x01b6
            goto L_0x01b7
        L_0x01b6:
            r7 = 0
        L_0x01b7:
            int r5 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r5 == 0) goto L_0x01c5
            if (r7 == 0) goto L_0x01c1
            r5 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x01c4
        L_0x01c1:
            r5 = 4194304(0x400000, double:2.0722615E-317)
        L_0x01c4:
            long r2 = r2 | r5
        L_0x01c5:
            if (r7 == 0) goto L_0x01c8
            goto L_0x01cb
        L_0x01c8:
            r5 = 8
            goto L_0x01cc
        L_0x01cb:
            r5 = 0
        L_0x01cc:
            long r6 = r2 & r48
            r16 = 0
            int r29 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x01f1
            if (r10 == 0) goto L_0x01d7
            r12 = 1
        L_0x01d7:
            int r10 = (r6 > r16 ? 1 : (r6 == r16 ? 0 : -1))
            if (r10 == 0) goto L_0x01e9
            if (r12 == 0) goto L_0x01e3
            r6 = 34359738368(0x800000000, double:1.69759663277E-313)
            goto L_0x01e8
        L_0x01e3:
            r6 = 17179869184(0x400000000, double:8.4879831639E-314)
        L_0x01e8:
            long r2 = r2 | r6
        L_0x01e9:
            if (r12 == 0) goto L_0x01ec
            goto L_0x01ee
        L_0x01ec:
            r30 = 0
        L_0x01ee:
            r6 = r30
            goto L_0x01f2
        L_0x01f1:
            r6 = 0
        L_0x01f2:
            r29 = 132096(0x20400, double:6.5264E-319)
            long r29 = r2 & r29
            r16 = 0
            int r7 = (r29 > r16 ? 1 : (r29 == r16 ? 0 : -1))
            if (r7 == 0) goto L_0x0202
            android.widget.TextView r7 = r1.badgeTV
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r15)
        L_0x0202:
            long r27 = r2 & r27
            int r7 = (r27 > r16 ? 1 : (r27 == r16 ? 0 : -1))
            if (r7 == 0) goto L_0x0221
            org.videolan.television.ui.FocusableConstraintLayout r7 = r1.container
            r10 = r36
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r7, r10)
            androidx.appcompat.widget.AppCompatTextView r7 = r1.subtitle
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r7, r10)
            androidx.appcompat.widget.AppCompatTextView r7 = r1.title
            r12 = r31
            org.videolan.vlc.util.KextensionsKt.asyncText(r7, r12)
            androidx.appcompat.widget.AppCompatTextView r7 = r1.title
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r7, r10)
            goto L_0x0223
        L_0x0221:
            r10 = r36
        L_0x0223:
            long r27 = r2 & r40
            r15 = 0
            int r7 = (r27 > r15 ? 1 : (r27 == r15 ? 0 : -1))
            if (r7 == 0) goto L_0x0235
            org.videolan.television.ui.FocusableConstraintLayout r7 = r1.container
            r7.setOnClickListener(r14)
            org.videolan.television.ui.FocusableConstraintLayout r7 = r1.container
            r7.setOnLongClickListener(r0)
        L_0x0235:
            long r27 = r2 & r37
            int r0 = (r27 > r15 ? 1 : (r27 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0247
            android.widget.TextView r0 = r1.dviIconTv
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r13)
            android.widget.TextView r0 = r1.dviIconTv
            r7 = r45
            r0.setVisibility(r7)
        L_0x0247:
            r12 = 147456(0x24000, double:7.2853E-319)
            long r12 = r12 & r2
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0256
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r7 = r22
            r0.setScaleType(r7)
        L_0x0256:
            r12 = 131200(0x20080, double:6.48214E-319)
            long r12 = r12 & r2
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0265
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r7 = r21
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r7)
        L_0x0265:
            long r12 = r2 & r23
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0275
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            r7 = r18
            r12 = 1
            r13 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r0, r10, r7, r12, r13)
            goto L_0x0276
        L_0x0275:
            r13 = 0
        L_0x0276:
            long r17 = r2 & r46
            int r0 = (r17 > r15 ? 1 : (r17 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0281
            org.videolan.vlc.gui.view.FadableImageView r0 = r1.mediaCover
            org.videolan.vlc.gui.helpers.ImageLoaderKt.constraintRatio(r0, r4, r13)
        L_0x0281:
            long r12 = r2 & r48
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x028c
            androidx.appcompat.widget.AppCompatImageView r0 = r1.mlItemSeen
            r0.setVisibility(r6)
        L_0x028c:
            long r6 = r2 & r25
            int r0 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0297
            androidx.appcompat.widget.AppCompatImageView r0 = r1.networkMedia
            r0.setVisibility(r5)
        L_0x0297:
            long r4 = r2 & r32
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02a9
            androidx.appcompat.widget.AppCompatImageView r0 = r1.networkMediaOff
            r4 = r39
            r0.setVisibility(r4)
            android.view.View r0 = r1.networkOffOverlay
            r0.setVisibility(r4)
        L_0x02a9:
            long r4 = r2 & r54
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02b4
            androidx.appcompat.widget.AppCompatImageView r0 = r1.otgMedia
            r0.setVisibility(r8)
        L_0x02b4:
            r4 = 131080(0x20008, double:6.4762E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02c3
            android.widget.ProgressBar r0 = r1.progressBar
            r4 = r20
            r0.setMax(r4)
        L_0x02c3:
            long r4 = r2 & r34
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02d5
            android.widget.ProgressBar r0 = r1.progressBar
            r0.setProgress(r9)
            android.widget.ProgressBar r0 = r1.progressBar
            r4 = r42
            r0.setVisibility(r4)
        L_0x02d5:
            long r4 = r2 & r50
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02e0
            androidx.appcompat.widget.AppCompatImageView r0 = r1.sdMedia
            r0.setVisibility(r11)
        L_0x02e0:
            r4 = 131104(0x20020, double:6.4774E-319)
            long r2 = r2 & r4
            int r0 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02ef
            androidx.appcompat.widget.AppCompatTextView r0 = r1.subtitle
            r2 = r19
            org.videolan.vlc.util.KextensionsKt.browserDescription(r0, r2)
        L_0x02ef:
            return
        L_0x02f0:
            r0 = move-exception
            monitor-exit(r58)     // Catch:{ all -> 0x02f0 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.databinding.MediaBrowserTvItemBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private MediaTvItemAdapter.AbstractMediaItemViewHolder value;

        public OnClickListenerImpl setValue(MediaTvItemAdapter.AbstractMediaItemViewHolder abstractMediaItemViewHolder) {
            this.value = abstractMediaItemViewHolder;
            if (abstractMediaItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private MediaTvItemAdapter.AbstractMediaItemViewHolder value;

        public OnLongClickListenerImpl setValue(MediaTvItemAdapter.AbstractMediaItemViewHolder abstractMediaItemViewHolder) {
            this.value = abstractMediaItemViewHolder;
            if (abstractMediaItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View view) {
            return this.value.onLongClick(view);
        }
    }
}
