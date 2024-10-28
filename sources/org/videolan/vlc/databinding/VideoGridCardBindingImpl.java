package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.video.VideoListAdapter;

public class VideoGridCardBindingImpl extends VideoGridCardBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
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
        sparseIntArray.put(R.id.ml_item_overlay, 17);
    }

    public VideoGridCardBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 18, sIncludes, sViewsWithIds));
    }

    private VideoGridCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[9], objArr[1], objArr[11], objArr[5], objArr[17], objArr[13], objArr[4], objArr[10], objArr[2], objArr[16], objArr[15], objArr[6], objArr[3], objArr[7], objArr[8], objArr[12], objArr[14]);
        this.mDirtyFlags = -1;
        this.absentMedia.setTag((Object) null);
        this.container.setTag((Object) null);
        this.itemMore.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.mediaFavorite.setTag((Object) null);
        this.mlItemProgress.setTag((Object) null);
        this.mlItemResolution.setTag((Object) null);
        this.mlItemSeen.setTag((Object) null);
        this.mlItemThumbnail.setTag((Object) null);
        this.mlItemTime.setTag((Object) null);
        this.mlItemTitle.setTag((Object) null);
        this.networkMedia.setTag((Object) null);
        this.networkOffOverlay.setTag((Object) null);
        this.otgMedia.setTag((Object) null);
        this.sdMedia.setTag((Object) null);
        this.selectedCheck.setTag((Object) null);
        this.thumbProgress.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
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
        } else if (BR.isNetwork == i) {
            setIsNetwork(((Boolean) obj).booleanValue());
        } else if (BR.bgColor == i) {
            setBgColor(((Integer) obj).intValue());
        } else if (BR.isPresent == i) {
            setIsPresent(((Boolean) obj).booleanValue());
        } else if (BR.showProgress == i) {
            setShowProgress(((Boolean) obj).booleanValue());
        } else if (BR.media == i) {
            setMedia((MediaLibraryItem) obj);
        } else if (BR.selected == i) {
            setSelected(((Boolean) obj).booleanValue());
        } else if (BR.resolution == i) {
            setResolution((String) obj);
        } else if (BR.max == i) {
            setMax(((Integer) obj).intValue());
        } else if (BR.progress == i) {
            setProgress(((Integer) obj).intValue());
        } else if (BR.showItemProgress == i) {
            setShowItemProgress(((Boolean) obj).booleanValue());
        } else if (BR.inSelection == i) {
            setInSelection(((Boolean) obj).booleanValue());
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.holder == i) {
            setHolder((VideoListAdapter.ViewHolder) obj);
        } else if (BR.seen == i) {
            setSeen(((Long) obj).longValue());
        } else if (BR.scaleType == i) {
            setScaleType((ImageView.ScaleType) obj);
        } else if (BR.isSD == i) {
            setIsSD(((Boolean) obj).booleanValue());
        } else if (BR.time == i) {
            setTime((String) obj);
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

    public void setIsNetwork(boolean z) {
        this.mIsNetwork = z;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.isNetwork);
        super.requestRebind();
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
    }

    public void setIsPresent(boolean z) {
        this.mIsPresent = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.isPresent);
        super.requestRebind();
    }

    public void setShowProgress(boolean z) {
        this.mShowProgress = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.showProgress);
        super.requestRebind();
    }

    public void setMedia(MediaLibraryItem mediaLibraryItem) {
        this.mMedia = mediaLibraryItem;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.media);
        super.requestRebind();
    }

    public void setSelected(boolean z) {
        this.mSelected = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.selected);
        super.requestRebind();
    }

    public void setResolution(String str) {
        this.mResolution = str;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.resolution);
        super.requestRebind();
    }

    public void setMax(int i) {
        this.mMax = i;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.max);
        super.requestRebind();
    }

    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    public void setShowItemProgress(boolean z) {
        this.mShowItemProgress = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.showItemProgress);
        super.requestRebind();
    }

    public void setInSelection(boolean z) {
        this.mInSelection = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.inSelection);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setHolder(VideoListAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setSeen(long j) {
        this.mSeen = j;
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        notifyPropertyChanged(BR.seen);
        super.requestRebind();
    }

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public void setIsSD(boolean z) {
        this.mIsSD = z;
        synchronized (this) {
            this.mDirtyFlags |= 65536;
        }
        notifyPropertyChanged(BR.isSD);
        super.requestRebind();
    }

    public void setTime(String str) {
        this.mTime = str;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
        }
        notifyPropertyChanged(BR.time);
        super.requestRebind();
    }

    public void setIsOTG(boolean z) {
        this.mIsOTG = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
        }
        notifyPropertyChanged(BR.isOTG);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x019a  */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x01a7 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0220  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x0224  */
    /* JADX WARNING: Removed duplicated region for block: B:145:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:159:0x0262  */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0265  */
    /* JADX WARNING: Removed duplicated region for block: B:164:0x0276  */
    /* JADX WARNING: Removed duplicated region for block: B:178:0x02a7  */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x02aa  */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x02b2  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x02d5  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x02fc  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006c  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0322  */
    /* JADX WARNING: Removed duplicated region for block: B:225:0x032d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x0351  */
    /* JADX WARNING: Removed duplicated region for block: B:241:0x035b  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0380  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x038b  */
    /* JADX WARNING: Removed duplicated region for block: B:257:0x038e  */
    /* JADX WARNING: Removed duplicated region for block: B:260:0x0396  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:271:0x03b5  */
    /* JADX WARNING: Removed duplicated region for block: B:274:0x03c2  */
    /* JADX WARNING: Removed duplicated region for block: B:277:0x03d7  */
    /* JADX WARNING: Removed duplicated region for block: B:280:0x03f8  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0403  */
    /* JADX WARNING: Removed duplicated region for block: B:286:0x0413  */
    /* JADX WARNING: Removed duplicated region for block: B:289:0x0429  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x0438  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x0447  */
    /* JADX WARNING: Removed duplicated region for block: B:298:0x0454  */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x045f  */
    /* JADX WARNING: Removed duplicated region for block: B:304:0x0475  */
    /* JADX WARNING: Removed duplicated region for block: B:307:0x0482  */
    /* JADX WARNING: Removed duplicated region for block: B:310:0x0491  */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x04a5  */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x04b2  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x04bf  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x04cc  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x04d9  */
    /* JADX WARNING: Removed duplicated region for block: B:334:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ee A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0176  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0185  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r74 = this;
            r1 = r74
            monitor-enter(r74)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x04e1 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x04e1 }
            monitor-exit(r74)     // Catch:{ all -> 0x04e1 }
            boolean r0 = r1.mIsFavorite
            boolean r6 = r1.mIsNetwork
            boolean r7 = r1.mIsPresent
            boolean r8 = r1.mShowProgress
            org.videolan.medialibrary.media.MediaLibraryItem r9 = r1.mMedia
            boolean r10 = r1.mSelected
            java.lang.String r11 = r1.mResolution
            int r12 = r1.mMax
            int r13 = r1.mProgress
            boolean r14 = r1.mShowItemProgress
            boolean r15 = r1.mInSelection
            android.graphics.drawable.BitmapDrawable r4 = r1.mCover
            org.videolan.vlc.gui.video.VideoListAdapter$ViewHolder r5 = r1.mHolder
            r18 = r14
            r19 = r15
            long r14 = r1.mSeen
            r20 = r4
            boolean r4 = r1.mIsSD
            r21 = r12
            java.lang.String r12 = r1.mTime
            r22 = r12
            boolean r12 = r1.mIsOTG
            r23 = 524289(0x80001, double:2.59033E-318)
            long r25 = r2 & r23
            r27 = 8
            r28 = r12
            r16 = 0
            int r29 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x005a
            int r29 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x0054
            if (r0 == 0) goto L_0x004f
            r25 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x0052
        L_0x004f:
            r25 = 4194304(0x400000, double:2.0722615E-317)
        L_0x0052:
            long r2 = r2 | r25
        L_0x0054:
            if (r0 == 0) goto L_0x0057
            goto L_0x005a
        L_0x0057:
            r0 = 8
            goto L_0x005b
        L_0x005a:
            r0 = 0
        L_0x005b:
            r25 = 524298(0x8000a, double:2.590376E-318)
            long r29 = r2 & r25
            r16 = 0
            int r31 = (r29 > r16 ? 1 : (r29 == r16 ? 0 : -1))
            if (r31 == 0) goto L_0x0075
            int r31 = (r29 > r16 ? 1 : (r29 == r16 ? 0 : -1))
            if (r31 == 0) goto L_0x0075
            if (r6 == 0) goto L_0x0070
            r29 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0073
        L_0x0070:
            r29 = 1048576(0x100000, double:5.180654E-318)
        L_0x0073:
            long r2 = r2 | r29
        L_0x0075:
            r29 = 526344(0x80808, double:2.600485E-318)
            long r31 = r2 & r29
            r33 = 549755813888(0x8000000000, double:2.716154612436E-312)
            r35 = 274877906944(0x4000000000, double:1.358077306218E-312)
            r37 = 72057594037927936(0x100000000000000, double:7.2911220195563975E-304)
            r39 = 144115188075855872(0x200000000000000, double:4.7783097267364807E-299)
            r41 = 524296(0x80008, double:2.590366E-318)
            r16 = 0
            int r43 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x00b9
            int r43 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x009c
            if (r7 == 0) goto L_0x009a
            long r2 = r2 | r39
            goto L_0x009c
        L_0x009a:
            long r2 = r2 | r37
        L_0x009c:
            long r31 = r2 & r41
            int r43 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x00b9
            r43 = r7 ^ 1
            int r44 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r44 == 0) goto L_0x00af
            if (r43 == 0) goto L_0x00ad
            long r2 = r2 | r33
            goto L_0x00af
        L_0x00ad:
            long r2 = r2 | r35
        L_0x00af:
            if (r43 == 0) goto L_0x00b4
            r31 = 0
            goto L_0x00b6
        L_0x00b4:
            r31 = 8
        L_0x00b6:
            r12 = r31
            goto L_0x00bc
        L_0x00b9:
            r12 = 0
            r43 = 0
        L_0x00bc:
            r44 = 524304(0x80010, double:2.590406E-318)
            long r46 = r2 & r44
            r16 = 0
            int r32 = (r46 > r16 ? 1 : (r46 == r16 ? 0 : -1))
            if (r32 == 0) goto L_0x00e0
            int r32 = (r46 > r16 ? 1 : (r46 == r16 ? 0 : -1))
            if (r32 == 0) goto L_0x00da
            if (r8 == 0) goto L_0x00d3
            r46 = 8796093022208(0x80000000000, double:4.345847379897E-311)
            goto L_0x00d8
        L_0x00d3:
            r46 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
        L_0x00d8:
            long r2 = r2 | r46
        L_0x00da:
            if (r8 == 0) goto L_0x00dd
            goto L_0x00e0
        L_0x00dd:
            r8 = 8
            goto L_0x00e1
        L_0x00e0:
            r8 = 0
        L_0x00e1:
            r46 = 524320(0x80020, double:2.590485E-318)
            long r48 = r2 & r46
            r32 = 0
            r16 = 0
            int r50 = (r48 > r16 ? 1 : (r48 == r16 ? 0 : -1))
            if (r50 == 0) goto L_0x00fb
            if (r9 == 0) goto L_0x00fb
            java.lang.String r48 = r9.getTitle()
            r73 = r48
            r48 = r8
            r8 = r73
            goto L_0x00ff
        L_0x00fb:
            r48 = r8
            r8 = r32
        L_0x00ff:
            r49 = 524360(0x80048, double:2.590683E-318)
            long r51 = r2 & r49
            r53 = 524352(0x80040, double:2.590643E-318)
            int r55 = (r51 > r16 ? 1 : (r51 == r16 ? 0 : -1))
            if (r55 == 0) goto L_0x014b
            long r51 = r2 & r53
            int r55 = (r51 > r16 ? 1 : (r51 == r16 ? 0 : -1))
            if (r55 == 0) goto L_0x011a
            if (r10 == 0) goto L_0x0116
            r51 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
            goto L_0x0118
        L_0x0116:
            r51 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
        L_0x0118:
            long r2 = r2 | r51
        L_0x011a:
            r51 = r10 ^ 1
            long r55 = r2 & r49
            r16 = 0
            int r52 = (r55 > r16 ? 1 : (r55 == r16 ? 0 : -1))
            if (r52 == 0) goto L_0x0133
            if (r51 == 0) goto L_0x012c
            r55 = 34359738368(0x800000000, double:1.69759663277E-313)
            goto L_0x0131
        L_0x012c:
            r55 = 17179869184(0x400000000, double:8.4879831639E-314)
        L_0x0131:
            long r2 = r2 | r55
        L_0x0133:
            long r55 = r2 & r53
            int r52 = (r55 > r16 ? 1 : (r55 == r16 ? 0 : -1))
            if (r52 == 0) goto L_0x0147
            if (r10 == 0) goto L_0x013e
            r52 = 0
            goto L_0x0140
        L_0x013e:
            r52 = 8
        L_0x0140:
            r73 = r52
            r52 = r0
            r0 = r73
            goto L_0x0150
        L_0x0147:
            r52 = r0
            r0 = 0
            goto L_0x0150
        L_0x014b:
            r52 = r0
            r0 = 0
            r51 = 0
        L_0x0150:
            r55 = 524416(0x80080, double:2.59096E-318)
            long r57 = r2 & r55
            int r59 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r59 == 0) goto L_0x0176
            boolean r59 = android.text.TextUtils.isEmpty(r11)
            int r60 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r60 == 0) goto L_0x016c
            if (r59 == 0) goto L_0x0167
            r57 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x016a
        L_0x0167:
            r57 = 16777216(0x1000000, double:8.289046E-317)
        L_0x016a:
            long r2 = r2 | r57
        L_0x016c:
            if (r59 == 0) goto L_0x0171
            r57 = 8
            goto L_0x0173
        L_0x0171:
            r57 = 0
        L_0x0173:
            r61 = r57
            goto L_0x0178
        L_0x0176:
            r61 = 0
        L_0x0178:
            r57 = 525824(0x80600, double:2.597916E-318)
            long r59 = r2 & r57
            r62 = 1
            r16 = 0
            int r63 = (r59 > r16 ? 1 : (r59 == r16 ? 0 : -1))
            if (r63 == 0) goto L_0x019a
            if (r13 != 0) goto L_0x018a
            r63 = 1
            goto L_0x018c
        L_0x018a:
            r63 = 0
        L_0x018c:
            int r64 = (r59 > r16 ? 1 : (r59 == r16 ? 0 : -1))
            if (r64 == 0) goto L_0x019c
            if (r63 == 0) goto L_0x0195
            r59 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            goto L_0x0197
        L_0x0195:
            r59 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
        L_0x0197:
            long r2 = r2 | r59
            goto L_0x019c
        L_0x019a:
            r63 = 0
        L_0x019c:
            r59 = 532480(0x82000, double:2.6308E-318)
            long r64 = r2 & r59
            r16 = 0
            int r66 = (r64 > r16 ? 1 : (r64 == r16 ? 0 : -1))
            if (r66 == 0) goto L_0x01e2
            if (r5 == 0) goto L_0x01e2
            r64 = r11
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl r11 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r11 != 0) goto L_0x01b6
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl r11 = new org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl
            r11.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r11
        L_0x01b6:
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl r32 = r11.setValue(r5)
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnLongClickListenerImpl r11 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r11 != 0) goto L_0x01c5
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnLongClickListenerImpl r11 = new org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnLongClickListenerImpl
            r11.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r11
        L_0x01c5:
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnLongClickListenerImpl r11 = r11.setValue(r5)
            r65 = r11
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl1 r11 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r11 != 0) goto L_0x01d6
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl1 r11 = new org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl1
            r11.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r11
        L_0x01d6:
            org.videolan.vlc.databinding.VideoGridCardBindingImpl$OnClickListenerImpl1 r5 = r11.setValue(r5)
            r11 = r5
            r5 = r32
            r32 = r13
            r13 = r65
            goto L_0x01ea
        L_0x01e2:
            r64 = r11
            r5 = r32
            r11 = r5
            r32 = r13
            r13 = r11
        L_0x01ea:
            r65 = 540672(0x84000, double:2.671275E-318)
            long r65 = r2 & r65
            r16 = 0
            int r67 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r67 == 0) goto L_0x020e
            int r67 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r67 != 0) goto L_0x01fb
            r14 = 1
            goto L_0x01fc
        L_0x01fb:
            r14 = 0
        L_0x01fc:
            int r15 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r15 == 0) goto L_0x0209
            if (r14 == 0) goto L_0x0205
            r65 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            goto L_0x0207
        L_0x0205:
            r65 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
        L_0x0207:
            long r2 = r2 | r65
        L_0x0209:
            if (r14 == 0) goto L_0x020e
            r14 = 8
            goto L_0x020f
        L_0x020e:
            r14 = 0
        L_0x020f:
            r65 = 589832(0x90008, double:2.914157E-318)
            long r67 = r2 & r65
            r15 = 0
            int r17 = (r67 > r15 ? 1 : (r67 == r15 ? 0 : -1))
            if (r17 == 0) goto L_0x0229
            int r69 = (r67 > r15 ? 1 : (r67 == r15 ? 0 : -1))
            if (r69 == 0) goto L_0x0229
            if (r4 == 0) goto L_0x0224
            r67 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x0227
        L_0x0224:
            r67 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x0227:
            long r2 = r2 | r67
        L_0x0229:
            r67 = 655360(0xa0000, double:3.23791E-318)
            long r67 = r2 & r67
            r15 = 0
            int r17 = (r67 > r15 ? 1 : (r67 == r15 ? 0 : -1))
            if (r17 == 0) goto L_0x0250
            boolean r69 = android.text.TextUtils.isEmpty(r22)
            int r70 = (r67 > r15 ? 1 : (r67 == r15 ? 0 : -1))
            if (r70 == 0) goto L_0x024b
            if (r69 == 0) goto L_0x0244
            r67 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
            goto L_0x0249
        L_0x0244:
            r67 = 1099511627776(0x10000000000, double:5.43230922487E-312)
        L_0x0249:
            long r2 = r2 | r67
        L_0x024b:
            if (r69 == 0) goto L_0x0250
            r15 = 8
            goto L_0x0251
        L_0x0250:
            r15 = 0
        L_0x0251:
            r67 = 786440(0xc0008, double:3.88553E-318)
            long r69 = r2 & r67
            r16 = 0
            int r71 = (r69 > r16 ? 1 : (r69 == r16 ? 0 : -1))
            if (r71 == 0) goto L_0x0269
            int r71 = (r69 > r16 ? 1 : (r69 == r16 ? 0 : -1))
            if (r71 == 0) goto L_0x0269
            if (r28 == 0) goto L_0x0265
            r69 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            goto L_0x0267
        L_0x0265:
            r69 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
        L_0x0267:
            long r2 = r2 | r69
        L_0x0269:
            r69 = 562984449474560(0x2000808200000, double:2.78151275628227E-309)
            long r69 = r2 & r69
            r16 = 0
            int r71 = (r69 > r16 ? 1 : (r69 == r16 ? 0 : -1))
            if (r71 == 0) goto L_0x029d
            long r69 = r2 & r29
            int r71 = (r69 > r16 ? 1 : (r69 == r16 ? 0 : -1))
            if (r71 == 0) goto L_0x0283
            if (r7 == 0) goto L_0x0281
            long r2 = r2 | r39
            goto L_0x0283
        L_0x0281:
            long r2 = r2 | r37
        L_0x0283:
            r37 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r37 = r2 & r37
            int r69 = (r37 > r16 ? 1 : (r37 == r16 ? 0 : -1))
            if (r69 == 0) goto L_0x029d
            r43 = r7 ^ 1
            long r37 = r2 & r41
            int r69 = (r37 > r16 ? 1 : (r37 == r16 ? 0 : -1))
            if (r69 == 0) goto L_0x029d
            if (r43 == 0) goto L_0x029b
            long r2 = r2 | r33
            goto L_0x029d
        L_0x029b:
            long r2 = r2 | r35
        L_0x029d:
            r33 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
            long r33 = r2 & r33
            r16 = 0
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02aa
            r18 = r18 ^ 1
            goto L_0x02ac
        L_0x02aa:
            r18 = 0
        L_0x02ac:
            long r33 = r2 & r25
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02cc
            if (r6 == 0) goto L_0x02b6
            r6 = r7
            goto L_0x02b7
        L_0x02b6:
            r6 = 0
        L_0x02b7:
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02c6
            if (r6 == 0) goto L_0x02c1
            r33 = 536870912(0x20000000, double:2.652494739E-315)
            goto L_0x02c4
        L_0x02c1:
            r33 = 268435456(0x10000000, double:1.32624737E-315)
        L_0x02c4:
            long r2 = r2 | r33
        L_0x02c6:
            if (r6 == 0) goto L_0x02c9
            goto L_0x02cc
        L_0x02c9:
            r6 = 8
            goto L_0x02cd
        L_0x02cc:
            r6 = 0
        L_0x02cd:
            long r33 = r2 & r65
            r16 = 0
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02f3
            if (r4 == 0) goto L_0x02d9
            r4 = r7
            goto L_0x02da
        L_0x02d9:
            r4 = 0
        L_0x02da:
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02ed
            if (r4 == 0) goto L_0x02e6
            r33 = 140737488355328(0x800000000000, double:6.953355807835E-310)
            goto L_0x02eb
        L_0x02e6:
            r33 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
        L_0x02eb:
            long r2 = r2 | r33
        L_0x02ed:
            if (r4 == 0) goto L_0x02f0
            goto L_0x02f3
        L_0x02f0:
            r4 = 8
            goto L_0x02f4
        L_0x02f3:
            r4 = 0
        L_0x02f4:
            long r33 = r2 & r49
            r16 = 0
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x0322
            if (r51 == 0) goto L_0x02ff
            goto L_0x0301
        L_0x02ff:
            r43 = 0
        L_0x0301:
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x0314
            if (r43 == 0) goto L_0x030d
            r33 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
            goto L_0x0312
        L_0x030d:
            r33 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
        L_0x0312:
            long r2 = r2 | r33
        L_0x0314:
            if (r43 == 0) goto L_0x0319
            r33 = 0
            goto L_0x031b
        L_0x0319:
            r33 = 8
        L_0x031b:
            r73 = r33
            r33 = r4
            r4 = r73
            goto L_0x0325
        L_0x0322:
            r33 = r4
            r4 = 0
        L_0x0325:
            long r34 = r2 & r67
            r16 = 0
            int r36 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r36 == 0) goto L_0x0351
            if (r28 == 0) goto L_0x0332
            r28 = r7
            goto L_0x0334
        L_0x0332:
            r28 = 0
        L_0x0334:
            int r36 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r36 == 0) goto L_0x0347
            if (r28 == 0) goto L_0x0340
            r34 = 137438953472(0x2000000000, double:6.7903865311E-313)
            goto L_0x0345
        L_0x0340:
            r34 = 68719476736(0x1000000000, double:3.39519326554E-313)
        L_0x0345:
            long r2 = r2 | r34
        L_0x0347:
            if (r28 == 0) goto L_0x034c
            r28 = 0
            goto L_0x034e
        L_0x034c:
            r28 = 8
        L_0x034e:
            r72 = r28
            goto L_0x0353
        L_0x0351:
            r72 = 0
        L_0x0353:
            long r34 = r2 & r57
            r16 = 0
            int r28 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x0380
            if (r63 == 0) goto L_0x035f
            r18 = 1
        L_0x035f:
            int r28 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x0372
            if (r18 == 0) goto L_0x036b
            r34 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L_0x0370
        L_0x036b:
            r34 = 4294967296(0x100000000, double:2.121995791E-314)
        L_0x0370:
            long r2 = r2 | r34
        L_0x0372:
            if (r18 == 0) goto L_0x0377
            r18 = 8
            goto L_0x0379
        L_0x0377:
            r18 = 0
        L_0x0379:
            r73 = r18
            r18 = r4
            r4 = r73
            goto L_0x0383
        L_0x0380:
            r18 = r4
            r4 = 0
        L_0x0383:
            long r34 = r2 & r39
            r16 = 0
            int r28 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x038e
            r19 = r19 ^ 1
            goto L_0x0390
        L_0x038e:
            r19 = 0
        L_0x0390:
            long r34 = r2 & r29
            int r28 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x03b5
            if (r7 == 0) goto L_0x0399
            goto L_0x039b
        L_0x0399:
            r19 = 0
        L_0x039b:
            int r28 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x03ac
            if (r19 == 0) goto L_0x03a7
            r34 = 2147483648(0x80000000, double:1.0609978955E-314)
            goto L_0x03aa
        L_0x03a7:
            r34 = 1073741824(0x40000000, double:5.304989477E-315)
        L_0x03aa:
            long r2 = r2 | r34
        L_0x03ac:
            if (r19 == 0) goto L_0x03b0
            r27 = 0
        L_0x03b0:
            r19 = r6
            r6 = r27
            goto L_0x03b8
        L_0x03b5:
            r19 = r6
            r6 = 0
        L_0x03b8:
            long r27 = r2 & r41
            r16 = 0
            int r34 = (r27 > r16 ? 1 : (r27 == r16 ? 0 : -1))
            r27 = r15
            if (r34 == 0) goto L_0x03d1
            android.widget.ImageView r15 = r1.absentMedia
            r15.setVisibility(r12)
            android.widget.TextView r12 = r1.mlItemTime
            r12.setEnabled(r7)
            android.widget.TextView r12 = r1.mlItemTitle
            r12.setEnabled(r7)
        L_0x03d1:
            long r34 = r2 & r59
            int r7 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r7 == 0) goto L_0x03f0
            com.google.android.material.card.MaterialCardView r7 = r1.container
            r7.setOnClickListener(r5)
            com.google.android.material.card.MaterialCardView r7 = r1.container
            r7.setOnLongClickListener(r13)
            android.widget.ImageView r7 = r1.itemMore
            r7.setOnClickListener(r11)
            androidx.constraintlayout.widget.ConstraintLayout r7 = r1.mboundView0
            r7.setOnClickListener(r5)
            androidx.constraintlayout.widget.ConstraintLayout r5 = r1.mboundView0
            r5.setOnLongClickListener(r13)
        L_0x03f0:
            long r11 = r2 & r29
            r15 = 0
            int r5 = (r11 > r15 ? 1 : (r11 == r15 ? 0 : -1))
            if (r5 == 0) goto L_0x03fd
            android.widget.ImageView r5 = r1.itemMore
            r5.setVisibility(r6)
        L_0x03fd:
            long r5 = r2 & r53
            int r7 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r7 == 0) goto L_0x040d
            androidx.constraintlayout.widget.ConstraintLayout r5 = r1.mboundView0
            r5.setSelected(r10)
            android.widget.ImageView r5 = r1.selectedCheck
            r5.setVisibility(r0)
        L_0x040d:
            long r5 = r2 & r46
            int r0 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0423
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView0
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r0, r9)
            android.widget.ImageView r0 = r1.mlItemThumbnail
            r5 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r0, r9, r5, r5, r5)
            android.widget.TextView r0 = r1.mlItemTitle
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r8)
        L_0x0423:
            long r5 = r2 & r23
            int r0 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0430
            android.widget.ImageView r0 = r1.mediaFavorite
            r12 = r52
            r0.setVisibility(r12)
        L_0x0430:
            r5 = 524544(0x80100, double:2.59159E-318)
            long r5 = r5 & r2
            int r0 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x043f
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r5 = r21
            r0.setMax(r5)
        L_0x043f:
            r5 = 524800(0x80200, double:2.592857E-318)
            long r5 = r5 & r2
            int r0 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x044e
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r5 = r32
            r0.setProgress(r5)
        L_0x044e:
            long r5 = r2 & r57
            int r0 = (r5 > r15 ? 1 : (r5 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0459
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r0.setVisibility(r4)
        L_0x0459:
            long r4 = r2 & r55
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x046d
            android.widget.TextView r0 = r1.mlItemResolution
            r4 = r64
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
            android.widget.TextView r0 = r1.mlItemResolution
            r4 = r61
            r0.setVisibility(r4)
        L_0x046d:
            r4 = 540672(0x84000, double:2.671275E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x047a
            android.widget.ImageView r0 = r1.mlItemSeen
            r0.setVisibility(r14)
        L_0x047a:
            r4 = 528384(0x81000, double:2.610564E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0489
            android.widget.ImageView r0 = r1.mlItemThumbnail
            r4 = r20
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r4)
        L_0x0489:
            r4 = 655360(0xa0000, double:3.23791E-318)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x049f
            android.widget.TextView r0 = r1.mlItemTime
            r4 = r22
            org.videolan.vlc.util.KextensionsKt.presenceDescription(r0, r4)
            android.widget.TextView r0 = r1.mlItemTime
            r4 = r27
            r0.setVisibility(r4)
        L_0x049f:
            long r4 = r2 & r25
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x04ac
            android.widget.ImageView r0 = r1.networkMedia
            r6 = r19
            r0.setVisibility(r6)
        L_0x04ac:
            long r4 = r2 & r49
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x04b9
            android.view.View r0 = r1.networkOffOverlay
            r4 = r18
            r0.setVisibility(r4)
        L_0x04b9:
            long r4 = r2 & r67
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x04c6
            android.widget.ImageView r0 = r1.otgMedia
            r4 = r72
            r0.setVisibility(r4)
        L_0x04c6:
            long r4 = r2 & r65
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x04d3
            android.widget.ImageView r0 = r1.sdMedia
            r4 = r33
            r0.setVisibility(r4)
        L_0x04d3:
            long r2 = r2 & r44
            int r0 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x04e0
            android.widget.ProgressBar r0 = r1.thumbProgress
            r8 = r48
            r0.setVisibility(r8)
        L_0x04e0:
            return
        L_0x04e1:
            r0 = move-exception
            monitor-exit(r74)     // Catch:{ all -> 0x04e1 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.VideoGridCardBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private VideoListAdapter.ViewHolder value;

        public OnClickListenerImpl setValue(VideoListAdapter.ViewHolder viewHolder) {
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
        private VideoListAdapter.ViewHolder value;

        public OnLongClickListenerImpl setValue(VideoListAdapter.ViewHolder viewHolder) {
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
        private VideoListAdapter.ViewHolder value;

        public OnClickListenerImpl1 setValue(VideoListAdapter.ViewHolder viewHolder) {
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
}
