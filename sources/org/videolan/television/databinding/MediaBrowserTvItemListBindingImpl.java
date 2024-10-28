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

public class MediaBrowserTvItemListBindingImpl extends MediaBrowserTvItemListBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public MediaBrowserTvItemListBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 13, sIncludes, sViewsWithIds));
    }

    private MediaBrowserTvItemListBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[9], objArr[0], objArr[4], objArr[1], objArr[3], objArr[5], objArr[8], objArr[2], objArr[7], objArr[10], objArr[6], objArr[12], objArr[11]);
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
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01bd  */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01cc  */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01cf  */
    /* JADX WARNING: Removed duplicated region for block: B:120:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0219  */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0245  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0250  */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x026e  */
    /* JADX WARNING: Removed duplicated region for block: B:168:0x027e  */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0290  */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x029f  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x02ac  */
    /* JADX WARNING: Removed duplicated region for block: B:180:0x02ba  */
    /* JADX WARNING: Removed duplicated region for block: B:183:0x02c5  */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x02d5  */
    /* JADX WARNING: Removed duplicated region for block: B:189:0x02e2  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x02ef  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x02fc  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:201:0x031d  */
    /* JADX WARNING: Removed duplicated region for block: B:210:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x018a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r63 = this;
            r1 = r63
            monitor-enter(r63)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0325 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0325 }
            monitor-exit(r63)     // Catch:{ all -> 0x0325 }
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
            long r4 = r1.mSeen
            r18 = r10
            boolean r10 = r1.mShowSeen
            r19 = r8
            android.widget.ImageView$ScaleType r8 = r1.mScaleType
            r20 = r11
            boolean r11 = r1.mIsSD
            r21 = r12
            boolean r12 = r1.mIsOTG
            r22 = 131137(0x20041, double:6.47903E-319)
            long r24 = r2 & r22
            r26 = 131073(0x20001, double:6.47587E-319)
            r28 = 0
            r16 = 0
            int r29 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x0055
            long r24 = r2 & r26
            int r29 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x0055
            if (r0 == 0) goto L_0x0055
            java.lang.String r24 = r0.getTitle()
            r62 = r24
            r24 = r8
            r8 = r62
            goto L_0x0059
        L_0x0055:
            r24 = r8
            r8 = r28
        L_0x0059:
            r29 = 131078(0x20006, double:6.4761E-319)
            long r31 = r2 & r29
            r33 = 2621440(0x280000, double:1.2951634E-317)
            r16 = 0
            int r25 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r25 == 0) goto L_0x0075
            int r25 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r25 == 0) goto L_0x0075
            if (r6 == 0) goto L_0x0070
            long r2 = r2 | r33
            goto L_0x0075
        L_0x0070:
            r31 = 1310720(0x140000, double:6.475817E-318)
            long r2 = r2 | r31
        L_0x0075:
            r31 = 131076(0x20004, double:6.476E-319)
            long r35 = r2 & r31
            r37 = 34359738368(0x800000000, double:1.69759663277E-313)
            r39 = 17179869184(0x400000000, double:8.4879831639E-314)
            r25 = 8
            r41 = r8
            r16 = 0
            int r42 = (r35 > r16 ? 1 : (r35 == r16 ? 0 : -1))
            if (r42 == 0) goto L_0x00a5
            r42 = r7 ^ 1
            int r43 = (r35 > r16 ? 1 : (r35 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x009b
            if (r42 == 0) goto L_0x0099
            long r2 = r2 | r37
            goto L_0x009b
        L_0x0099:
            long r2 = r2 | r39
        L_0x009b:
            if (r42 == 0) goto L_0x00a0
            r35 = 0
            goto L_0x00a2
        L_0x00a0:
            r35 = 8
        L_0x00a2:
            r44 = r35
            goto L_0x00a9
        L_0x00a5:
            r42 = 0
            r44 = 0
        L_0x00a9:
            r35 = 131088(0x20010, double:6.4766E-319)
            long r45 = r2 & r35
            r43 = 1
            r16 = 0
            int r47 = (r45 > r16 ? 1 : (r45 == r16 ? 0 : -1))
            if (r47 == 0) goto L_0x00d6
            if (r9 <= 0) goto L_0x00bb
            r47 = 1
            goto L_0x00bd
        L_0x00bb:
            r47 = 0
        L_0x00bd:
            int r48 = (r45 > r16 ? 1 : (r45 == r16 ? 0 : -1))
            if (r48 == 0) goto L_0x00cc
            if (r47 == 0) goto L_0x00c7
            r45 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x00ca
        L_0x00c7:
            r45 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x00ca:
            long r2 = r2 | r45
        L_0x00cc:
            if (r47 == 0) goto L_0x00d1
            r45 = 0
            goto L_0x00d3
        L_0x00d1:
            r45 = 8
        L_0x00d3:
            r49 = r45
            goto L_0x00d8
        L_0x00d6:
            r49 = 0
        L_0x00d8:
            r45 = 131328(0x20100, double:6.48847E-319)
            long r47 = r2 & r45
            r16 = 0
            int r50 = (r47 > r16 ? 1 : (r47 == r16 ? 0 : -1))
            if (r50 == 0) goto L_0x0100
            boolean r50 = android.text.TextUtils.isEmpty(r13)
            int r51 = (r47 > r16 ? 1 : (r47 == r16 ? 0 : -1))
            if (r51 == 0) goto L_0x00f6
            if (r50 == 0) goto L_0x00f1
            r47 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x00f4
        L_0x00f1:
            r47 = 4194304(0x400000, double:2.0722615E-317)
        L_0x00f4:
            long r2 = r2 | r47
        L_0x00f6:
            if (r50 == 0) goto L_0x00fb
            r47 = 8
            goto L_0x00fd
        L_0x00fb:
            r47 = 0
        L_0x00fd:
            r8 = r47
            goto L_0x0101
        L_0x0100:
            r8 = 0
        L_0x0101:
            r50 = 131584(0x20200, double:6.5011E-319)
            long r52 = r2 & r50
            r16 = 0
            int r48 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r48 == 0) goto L_0x0132
            if (r14 == 0) goto L_0x0132
            r48 = r9
            org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnClickListenerImpl r9 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r9 != 0) goto L_0x011b
            org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnClickListenerImpl r9 = new org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnClickListenerImpl
            r9.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r9
        L_0x011b:
            org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnClickListenerImpl r28 = r9.setValue(r14)
            org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnLongClickListenerImpl r9 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r9 != 0) goto L_0x012a
            org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnLongClickListenerImpl r9 = new org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnLongClickListenerImpl
            r9.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r9
        L_0x012a:
            org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl$OnLongClickListenerImpl r9 = r9.setValue(r14)
            r14 = r9
            r9 = r28
            goto L_0x0137
        L_0x0132:
            r48 = r9
            r9 = r28
            r14 = r9
        L_0x0137:
            r52 = 143360(0x23000, double:7.08293E-319)
            long r54 = r2 & r52
            r56 = 268435456(0x10000000, double:1.32624737E-315)
            r16 = 0
            int r28 = (r54 > r16 ? 1 : (r54 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x015b
            int r28 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r28 != 0) goto L_0x014b
            r4 = 1
            goto L_0x014c
        L_0x014b:
            r4 = 0
        L_0x014c:
            int r5 = (r54 > r16 ? 1 : (r54 == r16 ? 0 : -1))
            if (r5 == 0) goto L_0x015c
            if (r4 == 0) goto L_0x0158
            r54 = 536870912(0x20000000, double:2.652494739E-315)
            long r2 = r2 | r54
            goto L_0x015c
        L_0x0158:
            long r2 = r2 | r56
            goto L_0x015c
        L_0x015b:
            r4 = 0
        L_0x015c:
            r54 = 163840(0x28000, double:8.09477E-319)
            long r58 = r2 & r54
            r16 = 0
            int r5 = (r58 > r16 ? 1 : (r58 == r16 ? 0 : -1))
            if (r5 == 0) goto L_0x017e
            int r5 = (r58 > r16 ? 1 : (r58 == r16 ? 0 : -1))
            if (r5 == 0) goto L_0x0178
            if (r11 == 0) goto L_0x0173
            r58 = 2147483648(0x80000000, double:1.0609978955E-314)
            goto L_0x0176
        L_0x0173:
            r58 = 1073741824(0x40000000, double:5.304989477E-315)
        L_0x0176:
            long r2 = r2 | r58
        L_0x0178:
            if (r11 == 0) goto L_0x017b
            goto L_0x017e
        L_0x017b:
            r5 = 8
            goto L_0x017f
        L_0x017e:
            r5 = 0
        L_0x017f:
            r58 = 196608(0x30000, double:9.71373E-319)
            long r60 = r2 & r58
            r16 = 0
            int r11 = (r60 > r16 ? 1 : (r60 == r16 ? 0 : -1))
            if (r11 == 0) goto L_0x01a3
            int r11 = (r60 > r16 ? 1 : (r60 == r16 ? 0 : -1))
            if (r11 == 0) goto L_0x019d
            if (r12 == 0) goto L_0x0196
            r60 = 137438953472(0x2000000000, double:6.7903865311E-313)
            goto L_0x019b
        L_0x0196:
            r60 = 68719476736(0x1000000000, double:3.39519326554E-313)
        L_0x019b:
            long r2 = r2 | r60
        L_0x019d:
            if (r12 == 0) goto L_0x01a0
            goto L_0x01a3
        L_0x01a0:
            r11 = 8
            goto L_0x01a4
        L_0x01a3:
            r11 = 0
        L_0x01a4:
            long r33 = r2 & r33
            r16 = 0
            int r12 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01c4
            r33 = 524288(0x80000, double:2.590327E-318)
            long r33 = r2 & r33
            int r12 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01c4
            r42 = r7 ^ 1
            long r33 = r2 & r31
            int r12 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01c4
            if (r42 == 0) goto L_0x01c2
            long r2 = r2 | r37
            goto L_0x01c4
        L_0x01c2:
            long r2 = r2 | r39
        L_0x01c4:
            long r33 = r2 & r56
            r16 = 0
            int r12 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01cf
            r10 = r10 ^ 1
            goto L_0x01d0
        L_0x01cf:
            r10 = 0
        L_0x01d0:
            long r33 = r2 & r29
            int r12 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x020f
            if (r6 == 0) goto L_0x01d9
            goto L_0x01db
        L_0x01d9:
            r42 = 0
        L_0x01db:
            if (r6 == 0) goto L_0x01de
            goto L_0x01df
        L_0x01de:
            r7 = 0
        L_0x01df:
            int r6 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x01f2
            if (r42 == 0) goto L_0x01eb
            r33 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L_0x01f0
        L_0x01eb:
            r33 = 4294967296(0x100000000, double:2.121995791E-314)
        L_0x01f0:
            long r2 = r2 | r33
        L_0x01f2:
            long r33 = r2 & r29
            int r6 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x0203
            if (r7 == 0) goto L_0x01fe
            r33 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x0201
        L_0x01fe:
            r33 = 16777216(0x1000000, double:8.289046E-317)
        L_0x0201:
            long r2 = r2 | r33
        L_0x0203:
            if (r42 == 0) goto L_0x0207
            r6 = 0
            goto L_0x0209
        L_0x0207:
            r6 = 8
        L_0x0209:
            if (r7 == 0) goto L_0x020c
            goto L_0x0210
        L_0x020c:
            r7 = 8
            goto L_0x0211
        L_0x020f:
            r6 = 0
        L_0x0210:
            r7 = 0
        L_0x0211:
            long r33 = r2 & r52
            r16 = 0
            int r12 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x0239
            if (r4 == 0) goto L_0x021c
            goto L_0x021e
        L_0x021c:
            r43 = r10
        L_0x021e:
            int r4 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x0231
            if (r43 == 0) goto L_0x022a
            r33 = 549755813888(0x8000000000, double:2.716154612436E-312)
            goto L_0x022f
        L_0x022a:
            r33 = 274877906944(0x4000000000, double:1.358077306218E-312)
        L_0x022f:
            long r2 = r2 | r33
        L_0x0231:
            if (r43 == 0) goto L_0x0234
            goto L_0x0236
        L_0x0234:
            r25 = 0
        L_0x0236:
            r4 = r25
            goto L_0x023a
        L_0x0239:
            r4 = 0
        L_0x023a:
            r33 = 132096(0x20400, double:6.5264E-319)
            long r33 = r2 & r33
            r16 = 0
            int r10 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r10 == 0) goto L_0x024a
            android.widget.TextView r10 = r1.badgeTV
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r10, r15)
        L_0x024a:
            long r26 = r2 & r26
            int r10 = (r26 > r16 ? 1 : (r26 == r16 ? 0 : -1))
            if (r10 == 0) goto L_0x0266
            org.videolan.television.ui.FocusableConstraintLayout r10 = r1.container
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r10, r0)
            androidx.appcompat.widget.AppCompatTextView r10 = r1.subtitle
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r10, r0)
            androidx.appcompat.widget.AppCompatTextView r10 = r1.title
            r12 = r41
            org.videolan.vlc.util.KextensionsKt.asyncText(r10, r12)
            androidx.appcompat.widget.AppCompatTextView r10 = r1.title
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r10, r0)
        L_0x0266:
            long r25 = r2 & r50
            r15 = 0
            int r10 = (r25 > r15 ? 1 : (r25 == r15 ? 0 : -1))
            if (r10 == 0) goto L_0x0278
            org.videolan.television.ui.FocusableConstraintLayout r10 = r1.container
            r10.setOnClickListener(r9)
            org.videolan.television.ui.FocusableConstraintLayout r9 = r1.container
            r9.setOnLongClickListener(r14)
        L_0x0278:
            long r9 = r2 & r45
            int r12 = (r9 > r15 ? 1 : (r9 == r15 ? 0 : -1))
            if (r12 == 0) goto L_0x0288
            android.widget.TextView r9 = r1.dviIconTv
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r9, r13)
            android.widget.TextView r9 = r1.dviIconTv
            r9.setVisibility(r8)
        L_0x0288:
            r8 = 147456(0x24000, double:7.2853E-319)
            long r8 = r8 & r2
            int r10 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
            if (r10 == 0) goto L_0x0297
            org.videolan.vlc.gui.view.FadableImageView r8 = r1.mediaCover
            r9 = r24
            r8.setScaleType(r9)
        L_0x0297:
            r8 = 131200(0x20080, double:6.48214E-319)
            long r8 = r8 & r2
            int r10 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
            if (r10 == 0) goto L_0x02a6
            org.videolan.vlc.gui.view.FadableImageView r8 = r1.mediaCover
            r9 = r21
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r8, r9)
        L_0x02a6:
            long r8 = r2 & r22
            int r10 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
            if (r10 == 0) goto L_0x02b4
            org.videolan.vlc.gui.view.FadableImageView r8 = r1.mediaCover
            r9 = r20
            r10 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r8, r0, r9, r10, r10)
        L_0x02b4:
            long r8 = r2 & r52
            int r0 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02bf
            android.widget.ImageView r0 = r1.mlItemSeen
            r0.setVisibility(r4)
        L_0x02bf:
            long r8 = r2 & r29
            int r0 = (r8 > r15 ? 1 : (r8 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02cf
            androidx.appcompat.widget.AppCompatImageView r0 = r1.networkMedia
            r0.setVisibility(r7)
            androidx.appcompat.widget.AppCompatImageView r0 = r1.networkMediaOff
            r0.setVisibility(r6)
        L_0x02cf:
            long r6 = r2 & r31
            int r0 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02dc
            android.view.View r0 = r1.networkOffOverlay
            r8 = r44
            r0.setVisibility(r8)
        L_0x02dc:
            long r6 = r2 & r58
            int r0 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02e7
            androidx.appcompat.widget.AppCompatImageView r0 = r1.otgMedia
            r0.setVisibility(r11)
        L_0x02e7:
            r6 = 131080(0x20008, double:6.4762E-319)
            long r6 = r6 & r2
            int r0 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x02f6
            android.widget.ProgressBar r0 = r1.progressBar
            r4 = r19
            r0.setMax(r4)
        L_0x02f6:
            long r6 = r2 & r35
            int r0 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x030a
            android.widget.ProgressBar r0 = r1.progressBar
            r4 = r48
            r0.setProgress(r4)
            android.widget.ProgressBar r0 = r1.progressBar
            r8 = r49
            r0.setVisibility(r8)
        L_0x030a:
            long r6 = r2 & r54
            int r0 = (r6 > r15 ? 1 : (r6 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0315
            androidx.appcompat.widget.AppCompatImageView r0 = r1.sdMedia
            r0.setVisibility(r5)
        L_0x0315:
            r4 = 131104(0x20020, double:6.4774E-319)
            long r2 = r2 & r4
            int r0 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0324
            androidx.appcompat.widget.AppCompatTextView r0 = r1.subtitle
            r2 = r18
            org.videolan.vlc.util.KextensionsKt.browserDescription(r0, r2)
        L_0x0324:
            return
        L_0x0325:
            r0 = move-exception
            monitor-exit(r63)     // Catch:{ all -> 0x0325 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl.executeBindings():void");
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
