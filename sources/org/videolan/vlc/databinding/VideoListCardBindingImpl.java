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

public class VideoListCardBindingImpl extends VideoListCardBinding {
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
        sparseIntArray.put(R.id.ml_item_overlay, 16);
    }

    public VideoListCardBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 17, sIncludes, sViewsWithIds));
    }

    private VideoListCardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[13], objArr[12], objArr[16], objArr[9], objArr[8], objArr[2], objArr[14], objArr[11], objArr[4], objArr[7], objArr[3], objArr[6], objArr[5], objArr[15], objArr[10]);
        this.mDirtyFlags = -1;
        this.container.setTag((Object) null);
        this.itemMore.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.mediaFavorite.setTag((Object) null);
        this.mlItemProgress.setTag((Object) null);
        this.mlItemSeen.setTag((Object) null);
        this.mlItemThumbnail.setTag((Object) null);
        this.mlItemTime.setTag((Object) null);
        this.mlItemTitle.setTag((Object) null);
        this.networkMedia.setTag((Object) null);
        this.networkMediaOff.setTag((Object) null);
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
        if (BR.isFavorite == i) {
            setIsFavorite(((Boolean) obj).booleanValue());
        } else if (BR.isNetwork == i) {
            setIsNetwork(((Boolean) obj).booleanValue());
        } else if (BR.isPresent == i) {
            setIsPresent(((Boolean) obj).booleanValue());
        } else if (BR.showProgress == i) {
            setShowProgress(((Boolean) obj).booleanValue());
        } else if (BR.media == i) {
            setMedia((MediaLibraryItem) obj);
        } else if (BR.selected == i) {
            setSelected(((Boolean) obj).booleanValue());
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

    public void setIsPresent(boolean z) {
        this.mIsPresent = z;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.isPresent);
        super.requestRebind();
    }

    public void setShowProgress(boolean z) {
        this.mShowProgress = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.showProgress);
        super.requestRebind();
    }

    public void setMedia(MediaLibraryItem mediaLibraryItem) {
        this.mMedia = mediaLibraryItem;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.media);
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

    public void setMax(int i) {
        this.mMax = i;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.max);
        super.requestRebind();
    }

    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    public void setShowItemProgress(boolean z) {
        this.mShowItemProgress = z;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.showItemProgress);
        super.requestRebind();
    }

    public void setInSelection(boolean z) {
        this.mInSelection = z;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.inSelection);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setHolder(VideoListAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.holder);
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

    public void setScaleType(ImageView.ScaleType scaleType) {
        this.mScaleType = scaleType;
    }

    public void setIsSD(boolean z) {
        this.mIsSD = z;
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        notifyPropertyChanged(BR.isSD);
        super.requestRebind();
    }

    public void setTime(String str) {
        this.mTime = str;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.time);
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
    /* JADX WARNING: Removed duplicated region for block: B:102:0x0184 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:134:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01fd  */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x020d  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0241  */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x0255  */
    /* JADX WARNING: Removed duplicated region for block: B:172:0x0284  */
    /* JADX WARNING: Removed duplicated region for block: B:173:0x0287  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x028f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:192:0x02b7  */
    /* JADX WARNING: Removed duplicated region for block: B:195:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x02ea  */
    /* JADX WARNING: Removed duplicated region for block: B:221:0x0310  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x031b  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x033d  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:251:0x0364  */
    /* JADX WARNING: Removed duplicated region for block: B:254:0x036f  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x0372  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x037a  */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x03a0  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x03ad  */
    /* JADX WARNING: Removed duplicated region for block: B:276:0x03ce  */
    /* JADX WARNING: Removed duplicated region for block: B:279:0x03d9  */
    /* JADX WARNING: Removed duplicated region for block: B:282:0x03f1  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0403  */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x0410  */
    /* JADX WARNING: Removed duplicated region for block: B:291:0x041f  */
    /* JADX WARNING: Removed duplicated region for block: B:294:0x042a  */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0435  */
    /* JADX WARNING: Removed duplicated region for block: B:300:0x0442  */
    /* JADX WARNING: Removed duplicated region for block: B:303:0x044f  */
    /* JADX WARNING: Removed duplicated region for block: B:306:0x0461  */
    /* JADX WARNING: Removed duplicated region for block: B:309:0x0475  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x008c  */
    /* JADX WARNING: Removed duplicated region for block: B:312:0x0489  */
    /* JADX WARNING: Removed duplicated region for block: B:315:0x0496  */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x04a3  */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x04b0  */
    /* JADX WARNING: Removed duplicated region for block: B:330:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0108 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x011f  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0153  */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0177  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r73 = this;
            r1 = r73
            monitor-enter(r73)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x04b8 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x04b8 }
            monitor-exit(r73)     // Catch:{ all -> 0x04b8 }
            boolean r0 = r1.mIsFavorite
            boolean r6 = r1.mIsNetwork
            boolean r7 = r1.mIsPresent
            boolean r8 = r1.mShowProgress
            org.videolan.medialibrary.media.MediaLibraryItem r9 = r1.mMedia
            boolean r10 = r1.mSelected
            int r11 = r1.mMax
            int r12 = r1.mProgress
            boolean r13 = r1.mShowItemProgress
            boolean r14 = r1.mInSelection
            android.graphics.drawable.BitmapDrawable r15 = r1.mCover
            org.videolan.vlc.gui.video.VideoListAdapter$ViewHolder r4 = r1.mHolder
            r5 = r14
            r18 = r15
            long r14 = r1.mSeen
            r19 = r11
            boolean r11 = r1.mIsSD
            r20 = r5
            java.lang.String r5 = r1.mTime
            r21 = r13
            boolean r13 = r1.mIsOTG
            r22 = 131073(0x20001, double:6.47587E-319)
            long r24 = r2 & r22
            r26 = 8
            r27 = r13
            r16 = 0
            int r28 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x0057
            int r28 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r28 == 0) goto L_0x0051
            if (r0 == 0) goto L_0x004c
            r24 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x004f
        L_0x004c:
            r24 = 1048576(0x100000, double:5.180654E-318)
        L_0x004f:
            long r2 = r2 | r24
        L_0x0051:
            if (r0 == 0) goto L_0x0054
            goto L_0x0057
        L_0x0054:
            r0 = 8
            goto L_0x0058
        L_0x0057:
            r0 = 0
        L_0x0058:
            r24 = 131078(0x20006, double:6.4761E-319)
            long r28 = r2 & r24
            r16 = 0
            int r30 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x008c
            int r30 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x0072
            if (r6 == 0) goto L_0x006d
            r28 = 524288(0x80000, double:2.590327E-318)
            goto L_0x0070
        L_0x006d:
            r28 = 262144(0x40000, double:1.295163E-318)
        L_0x0070:
            long r2 = r2 | r28
        L_0x0072:
            r28 = r6 ^ 1
            long r29 = r2 & r24
            r16 = 0
            int r31 = (r29 > r16 ? 1 : (r29 == r16 ? 0 : -1))
            if (r31 == 0) goto L_0x008e
            if (r28 == 0) goto L_0x0084
            r29 = 140737488355328(0x800000000000, double:6.953355807835E-310)
            goto L_0x0089
        L_0x0084:
            r29 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
        L_0x0089:
            long r2 = r2 | r29
            goto L_0x008e
        L_0x008c:
            r28 = 0
        L_0x008e:
            r29 = 131588(0x20204, double:6.5013E-319)
            long r31 = r2 & r29
            r33 = 34359738368(0x800000000, double:1.69759663277E-313)
            r35 = 17179869184(0x400000000, double:8.4879831639E-314)
            r37 = 18014398509481984(0x40000000000000, double:1.7800590868057611E-307)
            r39 = 36028797018963968(0x80000000000000, double:2.8480945388892178E-306)
            r41 = 131076(0x20004, double:6.476E-319)
            r16 = 0
            int r43 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x00d2
            int r43 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x00b5
            if (r7 == 0) goto L_0x00b3
            long r2 = r2 | r39
            goto L_0x00b5
        L_0x00b3:
            long r2 = r2 | r37
        L_0x00b5:
            long r31 = r2 & r41
            int r43 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x00d2
            r43 = r7 ^ 1
            int r44 = (r31 > r16 ? 1 : (r31 == r16 ? 0 : -1))
            if (r44 == 0) goto L_0x00c8
            if (r43 == 0) goto L_0x00c6
            long r2 = r2 | r33
            goto L_0x00c8
        L_0x00c6:
            long r2 = r2 | r35
        L_0x00c8:
            if (r43 == 0) goto L_0x00cd
            r31 = 0
            goto L_0x00cf
        L_0x00cd:
            r31 = 8
        L_0x00cf:
            r45 = r31
            goto L_0x00d6
        L_0x00d2:
            r43 = 0
            r45 = 0
        L_0x00d6:
            r31 = 131080(0x20008, double:6.4762E-319)
            long r46 = r2 & r31
            r16 = 0
            int r44 = (r46 > r16 ? 1 : (r46 == r16 ? 0 : -1))
            if (r44 == 0) goto L_0x00fa
            int r44 = (r46 > r16 ? 1 : (r46 == r16 ? 0 : -1))
            if (r44 == 0) goto L_0x00f4
            if (r8 == 0) goto L_0x00ed
            r46 = 549755813888(0x8000000000, double:2.716154612436E-312)
            goto L_0x00f2
        L_0x00ed:
            r46 = 274877906944(0x4000000000, double:1.358077306218E-312)
        L_0x00f2:
            long r2 = r2 | r46
        L_0x00f4:
            if (r8 == 0) goto L_0x00f7
            goto L_0x00fa
        L_0x00f7:
            r8 = 8
            goto L_0x00fb
        L_0x00fa:
            r8 = 0
        L_0x00fb:
            r46 = 131088(0x20010, double:6.4766E-319)
            long r48 = r2 & r46
            r44 = 0
            r16 = 0
            int r50 = (r48 > r16 ? 1 : (r48 == r16 ? 0 : -1))
            if (r50 == 0) goto L_0x0111
            if (r9 == 0) goto L_0x0111
            java.lang.String r48 = r9.getTitle()
            r51 = r48
            goto L_0x0113
        L_0x0111:
            r51 = r44
        L_0x0113:
            r48 = 131108(0x20024, double:6.4776E-319)
            long r52 = r2 & r48
            r54 = 131104(0x20020, double:6.4774E-319)
            int r50 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r50 == 0) goto L_0x0153
            long r52 = r2 & r54
            int r50 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r50 == 0) goto L_0x012e
            if (r10 == 0) goto L_0x012a
            r52 = 2251799813685248(0x8000000000000, double:1.1125369292536007E-308)
            goto L_0x012c
        L_0x012a:
            r52 = 1125899906842624(0x4000000000000, double:5.562684646268003E-309)
        L_0x012c:
            long r2 = r2 | r52
        L_0x012e:
            r50 = r10 ^ 1
            long r52 = r2 & r48
            r16 = 0
            int r56 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r56 == 0) goto L_0x0143
            if (r50 == 0) goto L_0x013e
            r52 = 536870912(0x20000000, double:2.652494739E-315)
            goto L_0x0141
        L_0x013e:
            r52 = 268435456(0x10000000, double:1.32624737E-315)
        L_0x0141:
            long r2 = r2 | r52
        L_0x0143:
            long r52 = r2 & r54
            int r56 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r56 == 0) goto L_0x0155
            if (r10 == 0) goto L_0x014e
            r52 = 0
            goto L_0x0150
        L_0x014e:
            r52 = 8
        L_0x0150:
            r57 = r52
            goto L_0x0157
        L_0x0153:
            r50 = 0
        L_0x0155:
            r57 = 0
        L_0x0157:
            r52 = 131456(0x20180, double:6.4948E-319)
            long r58 = r2 & r52
            r56 = 1
            int r60 = (r58 > r16 ? 1 : (r58 == r16 ? 0 : -1))
            if (r60 == 0) goto L_0x0177
            if (r12 != 0) goto L_0x0167
            r60 = 1
            goto L_0x0169
        L_0x0167:
            r60 = 0
        L_0x0169:
            int r61 = (r58 > r16 ? 1 : (r58 == r16 ? 0 : -1))
            if (r61 == 0) goto L_0x0179
            if (r60 == 0) goto L_0x0172
            r58 = 562949953421312(0x2000000000000, double:2.781342323134002E-309)
            goto L_0x0174
        L_0x0172:
            r58 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
        L_0x0174:
            long r2 = r2 | r58
            goto L_0x0179
        L_0x0177:
            r60 = 0
        L_0x0179:
            r58 = 133120(0x20800, double:6.577E-319)
            long r61 = r2 & r58
            r16 = 0
            int r63 = (r61 > r16 ? 1 : (r61 == r16 ? 0 : -1))
            if (r63 == 0) goto L_0x01bd
            if (r4 == 0) goto L_0x01bd
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl r13 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r13 != 0) goto L_0x0191
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl r13 = new org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl
            r13.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r13
        L_0x0191:
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl r44 = r13.setValue(r4)
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnLongClickListenerImpl r13 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r13 != 0) goto L_0x01a0
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnLongClickListenerImpl r13 = new org.videolan.vlc.databinding.VideoListCardBindingImpl$OnLongClickListenerImpl
            r13.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r13
        L_0x01a0:
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnLongClickListenerImpl r13 = r13.setValue(r4)
            r62 = r13
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl1 r13 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r13 != 0) goto L_0x01b1
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl1 r13 = new org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl1
            r13.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r13
        L_0x01b1:
            org.videolan.vlc.databinding.VideoListCardBindingImpl$OnClickListenerImpl1 r4 = r13.setValue(r4)
            r13 = r4
            r4 = r44
            r44 = r8
            r8 = r62
            goto L_0x01c3
        L_0x01bd:
            r4 = r44
            r13 = r4
            r44 = r8
            r8 = r13
        L_0x01c3:
            r62 = 135168(0x21000, double:6.6782E-319)
            long r64 = r2 & r62
            r16 = 0
            int r66 = (r64 > r16 ? 1 : (r64 == r16 ? 0 : -1))
            if (r66 == 0) goto L_0x01e7
            int r66 = (r14 > r16 ? 1 : (r14 == r16 ? 0 : -1))
            if (r66 != 0) goto L_0x01d4
            r14 = 1
            goto L_0x01d5
        L_0x01d4:
            r14 = 0
        L_0x01d5:
            int r15 = (r64 > r16 ? 1 : (r64 == r16 ? 0 : -1))
            if (r15 == 0) goto L_0x01e2
            if (r14 == 0) goto L_0x01de
            r64 = 9007199254740992(0x20000000000000, double:4.450147717014403E-308)
            goto L_0x01e0
        L_0x01de:
            r64 = 4503599627370496(0x10000000000000, double:2.2250738585072014E-308)
        L_0x01e0:
            long r2 = r2 | r64
        L_0x01e2:
            if (r14 == 0) goto L_0x01e7
            r14 = 8
            goto L_0x01e8
        L_0x01e7:
            r14 = 0
        L_0x01e8:
            r64 = 147460(0x24004, double:7.2855E-319)
            long r66 = r2 & r64
            r15 = 0
            int r17 = (r66 > r15 ? 1 : (r66 == r15 ? 0 : -1))
            if (r17 == 0) goto L_0x0202
            int r68 = (r66 > r15 ? 1 : (r66 == r15 ? 0 : -1))
            if (r68 == 0) goto L_0x0202
            if (r11 == 0) goto L_0x01fd
            r66 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x0200
        L_0x01fd:
            r66 = 4194304(0x400000, double:2.0722615E-317)
        L_0x0200:
            long r2 = r2 | r66
        L_0x0202:
            r66 = 163840(0x28000, double:8.09477E-319)
            long r66 = r2 & r66
            r15 = 0
            int r17 = (r66 > r15 ? 1 : (r66 == r15 ? 0 : -1))
            if (r17 == 0) goto L_0x0229
            boolean r68 = android.text.TextUtils.isEmpty(r5)
            int r69 = (r66 > r15 ? 1 : (r66 == r15 ? 0 : -1))
            if (r69 == 0) goto L_0x0224
            if (r68 == 0) goto L_0x021d
            r66 = 137438953472(0x2000000000, double:6.7903865311E-313)
            goto L_0x0222
        L_0x021d:
            r66 = 68719476736(0x1000000000, double:3.39519326554E-313)
        L_0x0222:
            long r2 = r2 | r66
        L_0x0224:
            if (r68 == 0) goto L_0x0229
            r15 = 8
            goto L_0x022a
        L_0x0229:
            r15 = 0
        L_0x022a:
            r66 = 196612(0x30004, double:9.7139E-319)
            long r68 = r2 & r66
            r16 = 0
            int r70 = (r68 > r16 ? 1 : (r68 == r16 ? 0 : -1))
            if (r70 == 0) goto L_0x0248
            int r70 = (r68 > r16 ? 1 : (r68 == r16 ? 0 : -1))
            if (r70 == 0) goto L_0x0248
            if (r27 == 0) goto L_0x0241
            r68 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
            goto L_0x0246
        L_0x0241:
            r68 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
        L_0x0246:
            long r2 = r2 | r68
        L_0x0248:
            r68 = 105553662050304(0x600020880000, double:5.2150438211792E-310)
            long r68 = r2 & r68
            r16 = 0
            int r70 = (r68 > r16 ? 1 : (r68 == r16 ? 0 : -1))
            if (r70 == 0) goto L_0x027a
            long r68 = r2 & r29
            int r70 = (r68 > r16 ? 1 : (r68 == r16 ? 0 : -1))
            if (r70 == 0) goto L_0x0262
            if (r7 == 0) goto L_0x0260
            long r2 = r2 | r39
            goto L_0x0262
        L_0x0260:
            long r2 = r2 | r37
        L_0x0262:
            r37 = 536870912(0x20000000, double:2.652494739E-315)
            long r37 = r2 & r37
            int r68 = (r37 > r16 ? 1 : (r37 == r16 ? 0 : -1))
            if (r68 == 0) goto L_0x027a
            r43 = r7 ^ 1
            long r37 = r2 & r41
            int r68 = (r37 > r16 ? 1 : (r37 == r16 ? 0 : -1))
            if (r68 == 0) goto L_0x027a
            if (r43 == 0) goto L_0x0278
            long r2 = r2 | r33
            goto L_0x027a
        L_0x0278:
            long r2 = r2 | r35
        L_0x027a:
            r33 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            long r33 = r2 & r33
            r16 = 0
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x0287
            r21 = r21 ^ 1
            goto L_0x0289
        L_0x0287:
            r21 = 0
        L_0x0289:
            long r33 = r2 & r24
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02b7
            if (r6 == 0) goto L_0x0293
            r6 = r7
            goto L_0x0294
        L_0x0293:
            r6 = 0
        L_0x0294:
            if (r28 == 0) goto L_0x0299
            r28 = 1
            goto L_0x029b
        L_0x0299:
            r28 = r7
        L_0x029b:
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02aa
            if (r6 == 0) goto L_0x02a5
            r33 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x02a8
        L_0x02a5:
            r33 = 16777216(0x1000000, double:8.289046E-317)
        L_0x02a8:
            long r2 = r2 | r33
        L_0x02aa:
            if (r6 == 0) goto L_0x02ae
            r6 = 0
            goto L_0x02b0
        L_0x02ae:
            r6 = 8
        L_0x02b0:
            r72 = r28
            r28 = r5
            r5 = r72
            goto L_0x02bb
        L_0x02b7:
            r28 = r5
            r5 = 0
            r6 = 0
        L_0x02bb:
            long r33 = r2 & r64
            r16 = 0
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02e1
            if (r11 == 0) goto L_0x02c7
            r11 = r7
            goto L_0x02c8
        L_0x02c7:
            r11 = 0
        L_0x02c8:
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x02db
            if (r11 == 0) goto L_0x02d4
            r33 = 8796093022208(0x80000000000, double:4.345847379897E-311)
            goto L_0x02d9
        L_0x02d4:
            r33 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
        L_0x02d9:
            long r2 = r2 | r33
        L_0x02db:
            if (r11 == 0) goto L_0x02de
            goto L_0x02e1
        L_0x02de:
            r11 = 8
            goto L_0x02e2
        L_0x02e1:
            r11 = 0
        L_0x02e2:
            long r33 = r2 & r48
            r16 = 0
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x0310
            if (r50 == 0) goto L_0x02ed
            goto L_0x02ef
        L_0x02ed:
            r43 = 0
        L_0x02ef:
            int r35 = (r33 > r16 ? 1 : (r33 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x0302
            if (r43 == 0) goto L_0x02fb
            r33 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
            goto L_0x0300
        L_0x02fb:
            r33 = 1099511627776(0x10000000000, double:5.43230922487E-312)
        L_0x0300:
            long r2 = r2 | r33
        L_0x0302:
            if (r43 == 0) goto L_0x0307
            r33 = 0
            goto L_0x0309
        L_0x0307:
            r33 = 8
        L_0x0309:
            r72 = r33
            r33 = r11
            r11 = r72
            goto L_0x0313
        L_0x0310:
            r33 = r11
            r11 = 0
        L_0x0313:
            long r34 = r2 & r66
            r16 = 0
            int r36 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r36 == 0) goto L_0x033d
            if (r27 == 0) goto L_0x0320
            r27 = r7
            goto L_0x0322
        L_0x0320:
            r27 = 0
        L_0x0322:
            int r36 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r36 == 0) goto L_0x0333
            if (r27 == 0) goto L_0x032e
            r34 = 2147483648(0x80000000, double:1.0609978955E-314)
            goto L_0x0331
        L_0x032e:
            r34 = 1073741824(0x40000000, double:5.304989477E-315)
        L_0x0331:
            long r2 = r2 | r34
        L_0x0333:
            if (r27 == 0) goto L_0x0338
            r27 = 0
            goto L_0x033a
        L_0x0338:
            r27 = 8
        L_0x033a:
            r71 = r27
            goto L_0x033f
        L_0x033d:
            r71 = 0
        L_0x033f:
            long r34 = r2 & r52
            r16 = 0
            int r27 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r27 == 0) goto L_0x0364
            if (r60 == 0) goto L_0x034b
            r21 = 1
        L_0x034b:
            int r27 = (r34 > r16 ? 1 : (r34 == r16 ? 0 : -1))
            if (r27 == 0) goto L_0x035a
            if (r21 == 0) goto L_0x0355
            r34 = 134217728(0x8000000, double:6.63123685E-316)
            goto L_0x0358
        L_0x0355:
            r34 = 67108864(0x4000000, double:3.31561842E-316)
        L_0x0358:
            long r2 = r2 | r34
        L_0x035a:
            if (r21 == 0) goto L_0x035d
            goto L_0x035f
        L_0x035d:
            r26 = 0
        L_0x035f:
            r21 = r11
            r11 = r26
            goto L_0x0367
        L_0x0364:
            r21 = r11
            r11 = 0
        L_0x0367:
            long r26 = r2 & r39
            r16 = 0
            int r34 = (r26 > r16 ? 1 : (r26 == r16 ? 0 : -1))
            if (r34 == 0) goto L_0x0372
            r20 = r20 ^ 1
            goto L_0x0374
        L_0x0372:
            r20 = 0
        L_0x0374:
            long r26 = r2 & r29
            int r34 = (r26 > r16 ? 1 : (r26 == r16 ? 0 : -1))
            if (r34 == 0) goto L_0x03a0
            if (r7 == 0) goto L_0x037d
            goto L_0x037f
        L_0x037d:
            r20 = 0
        L_0x037f:
            int r34 = (r26 > r16 ? 1 : (r26 == r16 ? 0 : -1))
            if (r34 == 0) goto L_0x0392
            if (r20 == 0) goto L_0x038b
            r26 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L_0x0390
        L_0x038b:
            r26 = 4294967296(0x100000000, double:2.121995791E-314)
        L_0x0390:
            long r2 = r2 | r26
        L_0x0392:
            if (r20 == 0) goto L_0x0397
            r20 = 0
            goto L_0x0399
        L_0x0397:
            r20 = 4
        L_0x0399:
            r72 = r20
            r20 = r7
            r7 = r72
            goto L_0x03a3
        L_0x03a0:
            r20 = r7
            r7 = 0
        L_0x03a3:
            long r26 = r2 & r58
            r16 = 0
            int r34 = (r26 > r16 ? 1 : (r26 == r16 ? 0 : -1))
            r26 = r15
            if (r34 == 0) goto L_0x03c6
            com.google.android.material.card.MaterialCardView r15 = r1.container
            r15.setOnClickListener(r4)
            com.google.android.material.card.MaterialCardView r15 = r1.container
            r15.setOnLongClickListener(r8)
            android.widget.ImageView r15 = r1.itemMore
            r15.setOnClickListener(r13)
            androidx.constraintlayout.widget.ConstraintLayout r13 = r1.mboundView0
            r13.setOnClickListener(r4)
            androidx.constraintlayout.widget.ConstraintLayout r4 = r1.mboundView0
            r4.setOnLongClickListener(r8)
        L_0x03c6:
            long r29 = r2 & r29
            r15 = 0
            int r4 = (r29 > r15 ? 1 : (r29 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x03d3
            android.widget.ImageView r4 = r1.itemMore
            r4.setVisibility(r7)
        L_0x03d3:
            long r7 = r2 & r46
            int r4 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x03eb
            androidx.constraintlayout.widget.ConstraintLayout r4 = r1.mboundView0
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r4, r9)
            android.widget.ImageView r4 = r1.mlItemThumbnail
            r7 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r4, r9, r7, r7, r7)
            android.widget.TextView r4 = r1.mlItemTitle
            r7 = r51
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r7)
        L_0x03eb:
            long r7 = r2 & r54
            int r4 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x03fd
            androidx.constraintlayout.widget.ConstraintLayout r4 = r1.mboundView0
            r4.setSelected(r10)
            android.widget.ImageView r4 = r1.selectedCheck
            r13 = r57
            r4.setVisibility(r13)
        L_0x03fd:
            long r7 = r2 & r22
            int r4 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x0408
            android.widget.ImageView r4 = r1.mediaFavorite
            r4.setVisibility(r0)
        L_0x0408:
            r7 = 131136(0x20040, double:6.479E-319)
            long r7 = r7 & r2
            int r0 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0417
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r4 = r19
            r0.setMax(r4)
        L_0x0417:
            r7 = 131200(0x20080, double:6.48214E-319)
            long r7 = r7 & r2
            int r0 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0424
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r0.setProgress(r12)
        L_0x0424:
            long r7 = r2 & r52
            int r0 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x042f
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r0.setVisibility(r11)
        L_0x042f:
            long r7 = r2 & r62
            int r0 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x043a
            android.widget.ImageView r0 = r1.mlItemSeen
            r0.setVisibility(r14)
        L_0x043a:
            r7 = 132096(0x20400, double:6.5264E-319)
            long r7 = r7 & r2
            int r0 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0449
            android.widget.ImageView r0 = r1.mlItemThumbnail
            r4 = r18
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r4)
        L_0x0449:
            long r7 = r2 & r24
            int r0 = (r7 > r15 ? 1 : (r7 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0459
            android.widget.TextView r0 = r1.mlItemTime
            r0.setEnabled(r5)
            android.widget.ImageView r0 = r1.networkMedia
            r0.setVisibility(r6)
        L_0x0459:
            r4 = 163840(0x28000, double:8.09477E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x046f
            android.widget.TextView r0 = r1.mlItemTime
            r4 = r26
            r0.setVisibility(r4)
            android.widget.TextView r0 = r1.mlItemTime
            r4 = r28
            org.videolan.vlc.util.KextensionsKt.presenceDescription(r0, r4)
        L_0x046f:
            long r4 = r2 & r41
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0483
            android.widget.TextView r0 = r1.mlItemTitle
            r4 = r20
            r0.setEnabled(r4)
            android.widget.ImageView r0 = r1.networkMediaOff
            r13 = r45
            r0.setVisibility(r13)
        L_0x0483:
            long r4 = r2 & r48
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0490
            android.view.View r0 = r1.networkOffOverlay
            r4 = r21
            r0.setVisibility(r4)
        L_0x0490:
            long r4 = r2 & r66
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x049d
            android.widget.ImageView r0 = r1.otgMedia
            r4 = r71
            r0.setVisibility(r4)
        L_0x049d:
            long r4 = r2 & r64
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x04aa
            android.widget.ImageView r0 = r1.sdMedia
            r11 = r33
            r0.setVisibility(r11)
        L_0x04aa:
            long r2 = r2 & r31
            int r0 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x04b7
            android.widget.ProgressBar r0 = r1.thumbProgress
            r8 = r44
            r0.setVisibility(r8)
        L_0x04b7:
            return
        L_0x04b8:
            r0 = move-exception
            monitor-exit(r73)     // Catch:{ all -> 0x04b8 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.VideoListCardBindingImpl.executeBindings():void");
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
