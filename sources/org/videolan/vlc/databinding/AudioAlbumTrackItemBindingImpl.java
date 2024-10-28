package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.audio.AudioAlbumTracksAdapter;

public class AudioAlbumTrackItemBindingImpl extends AudioAlbumTrackItemBinding {
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
        sparseIntArray.put(R.id.playing, 11);
    }

    public AudioAlbumTrackItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private AudioAlbumTrackItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[9], objArr[8], objArr[4], objArr[5], objArr[6], objArr[7], objArr[11], objArr[10], objArr[3], objArr[2], objArr[1]);
        this.mDirtyFlags = -1;
        this.itemMore.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[0];
        this.mboundView0 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.mediaAbsent.setTag((Object) null);
        this.mediaFavorite.setTag((Object) null);
        this.mediaNetwork.setTag((Object) null);
        this.mediaSd.setTag((Object) null);
        this.otgMedia.setTag((Object) null);
        this.selectedCheck.setTag((Object) null);
        this.subtitle.setTag((Object) null);
        this.title.setTag((Object) null);
        this.trackNumber.setTag((Object) null);
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
            setItem((MediaWrapper) obj);
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
            setHolder((AudioAlbumTracksAdapter.TrackItemViewHolder) obj);
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

    public void setItem(MediaWrapper mediaWrapper) {
        this.mItem = mediaWrapper;
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
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
    }

    public void setHolder(AudioAlbumTracksAdapter.TrackItemViewHolder trackItemViewHolder) {
        this.mHolder = trackItemViewHolder;
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
    /* JADX WARNING: Removed duplicated region for block: B:108:0x019c  */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x01a5  */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x01af  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01ca  */
    /* JADX WARNING: Removed duplicated region for block: B:129:0x01d3  */
    /* JADX WARNING: Removed duplicated region for block: B:132:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:135:0x01f3  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:141:0x020f  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x021a  */
    /* JADX WARNING: Removed duplicated region for block: B:147:0x0225  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0230  */
    /* JADX WARNING: Removed duplicated region for block: B:153:0x023b  */
    /* JADX WARNING: Removed duplicated region for block: B:165:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0117 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x0166  */
    /* JADX WARNING: Removed duplicated region for block: B:99:0x0185  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r47 = this;
            r1 = r47
            monitor-enter(r47)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0258 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0258 }
            monitor-exit(r47)     // Catch:{ all -> 0x0258 }
            boolean r0 = r1.mIsFavorite
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = r1.mItem
            boolean r7 = r1.mIsNetwork
            boolean r8 = r1.mIsPresent
            boolean r9 = r1.mSelected
            boolean r10 = r1.mInSelection
            org.videolan.vlc.gui.audio.AudioAlbumTracksAdapter$TrackItemViewHolder r11 = r1.mHolder
            boolean r12 = r1.mIsSD
            boolean r13 = r1.mIsOTG
            r14 = 2049(0x801, double:1.0123E-320)
            long r16 = r2 & r14
            r18 = 8
            r19 = 0
            int r20 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x003c
            int r20 = (r16 > r4 ? 1 : (r16 == r4 ? 0 : -1))
            if (r20 == 0) goto L_0x0036
            if (r0 == 0) goto L_0x0032
            r16 = 32768(0x8000, double:1.61895E-319)
            goto L_0x0034
        L_0x0032:
            r16 = 16384(0x4000, double:8.0948E-320)
        L_0x0034:
            long r2 = r2 | r16
        L_0x0036:
            if (r0 == 0) goto L_0x0039
            goto L_0x003c
        L_0x0039:
            r0 = 8
            goto L_0x003d
        L_0x003c:
            r0 = 0
        L_0x003d:
            r16 = 2050(0x802, double:1.013E-320)
            long r20 = r2 & r16
            r22 = 0
            int r23 = (r20 > r4 ? 1 : (r20 == r4 ? 0 : -1))
            if (r23 == 0) goto L_0x006f
            if (r6 == 0) goto L_0x004e
            java.lang.String r20 = r6.getTitle()
            goto L_0x0050
        L_0x004e:
            r20 = r22
        L_0x0050:
            org.videolan.vlc.gui.helpers.TalkbackUtil r14 = org.videolan.vlc.gui.helpers.TalkbackUtil.INSTANCE
            android.view.View r15 = r47.getRoot()
            android.content.Context r15 = r15.getContext()
            java.lang.String r14 = r14.getTimeAndArtist(r15, r6)
            org.videolan.vlc.gui.helpers.TalkbackUtil r15 = org.videolan.vlc.gui.helpers.TalkbackUtil.INSTANCE
            android.view.View r21 = r47.getRoot()
            android.content.Context r4 = r21.getContext()
            java.lang.String r4 = r15.getTrackNumber(r4, r6)
            r5 = r20
            goto L_0x0073
        L_0x006f:
            r4 = r22
            r5 = r4
            r14 = r5
        L_0x0073:
            r20 = 3628(0xe2c, double:1.7925E-320)
            long r20 = r2 & r20
            r26 = 134217728(0x8000000, double:6.63123685E-316)
            r28 = 2060(0x80c, double:1.018E-320)
            r30 = 2056(0x808, double:1.016E-320)
            r32 = 3080(0xc08, double:1.5217E-320)
            r34 = 2088(0x828, double:1.0316E-320)
            r24 = 0
            int r15 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x00e2
            long r20 = r2 & r34
            int r15 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x0098
            if (r8 == 0) goto L_0x0093
            long r2 = r2 | r26
            goto L_0x0098
        L_0x0093:
            r20 = 67108864(0x4000000, double:3.31561842E-316)
            long r2 = r2 | r20
        L_0x0098:
            long r20 = r2 & r28
            int r15 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x00b4
            r7 = r7 & r8
            int r15 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x00ae
            if (r7 == 0) goto L_0x00a9
            r20 = 131072(0x20000, double:6.47582E-319)
            goto L_0x00ac
        L_0x00a9:
            r20 = 65536(0x10000, double:3.2379E-319)
        L_0x00ac:
            long r2 = r2 | r20
        L_0x00ae:
            if (r7 == 0) goto L_0x00b1
            goto L_0x00b4
        L_0x00b1:
            r7 = 8
            goto L_0x00b5
        L_0x00b4:
            r7 = 0
        L_0x00b5:
            long r20 = r2 & r32
            r24 = 0
            int r15 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x00e0
            r15 = r8 ^ 1
            long r20 = r2 & r30
            int r36 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r36 == 0) goto L_0x00ce
            if (r15 == 0) goto L_0x00ca
            r20 = 8192(0x2000, double:4.0474E-320)
            goto L_0x00cc
        L_0x00ca:
            r20 = 4096(0x1000, double:2.0237E-320)
        L_0x00cc:
            long r2 = r2 | r20
        L_0x00ce:
            long r20 = r2 & r30
            int r36 = (r20 > r24 ? 1 : (r20 == r24 ? 0 : -1))
            if (r36 == 0) goto L_0x00de
            if (r15 == 0) goto L_0x00d9
            r20 = 0
            goto L_0x00db
        L_0x00d9:
            r20 = 4
        L_0x00db:
            r6 = r20
            goto L_0x00e5
        L_0x00de:
            r6 = 0
            goto L_0x00e5
        L_0x00e0:
            r6 = 0
            goto L_0x00e4
        L_0x00e2:
            r6 = 0
            r7 = 0
        L_0x00e4:
            r15 = 0
        L_0x00e5:
            r36 = 2064(0x810, double:1.0198E-320)
            long r38 = r2 & r36
            int r21 = (r38 > r24 ? 1 : (r38 == r24 ? 0 : -1))
            if (r21 == 0) goto L_0x010a
            int r21 = (r38 > r24 ? 1 : (r38 == r24 ? 0 : -1))
            if (r21 == 0) goto L_0x00fc
            if (r9 == 0) goto L_0x00f7
            r38 = 8388608(0x800000, double:4.144523E-317)
            goto L_0x00fa
        L_0x00f7:
            r38 = 4194304(0x400000, double:2.0722615E-317)
        L_0x00fa:
            long r2 = r2 | r38
        L_0x00fc:
            if (r9 == 0) goto L_0x0101
            r21 = 0
            goto L_0x0103
        L_0x0101:
            r21 = 8
        L_0x0103:
            r46 = r21
            r21 = r5
            r5 = r46
            goto L_0x010d
        L_0x010a:
            r21 = r5
            r5 = 0
        L_0x010d:
            r38 = 2304(0x900, double:1.1383E-320)
            long r40 = r2 & r38
            r24 = 0
            int r42 = (r40 > r24 ? 1 : (r40 == r24 ? 0 : -1))
            if (r42 == 0) goto L_0x0154
            if (r11 == 0) goto L_0x0154
            r40 = r4
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl r4 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x0126
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl r4 = new org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl
            r4.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r4
        L_0x0126:
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl r22 = r4.setValue(r11)
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnLongClickListenerImpl r4 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r4 != 0) goto L_0x0135
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnLongClickListenerImpl r4 = new org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnLongClickListenerImpl
            r4.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r4
        L_0x0135:
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnLongClickListenerImpl r4 = r4.setValue(r11)
            r41 = r4
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl1 r4 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x0146
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl1 r4 = new org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl1
            r4.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r4
        L_0x0146:
            org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl$OnClickListenerImpl1 r4 = r4.setValue(r11)
            r11 = r4
            r4 = r41
            r46 = r22
            r22 = r14
            r14 = r46
            goto L_0x015c
        L_0x0154:
            r40 = r4
            r4 = r22
            r11 = r4
            r22 = r14
            r14 = r11
        L_0x015c:
            r41 = 2568(0xa08, double:1.269E-320)
            long r43 = r2 & r41
            r24 = 0
            int r45 = (r43 > r24 ? 1 : (r43 == r24 ? 0 : -1))
            if (r45 == 0) goto L_0x017c
            r12 = r12 & r8
            int r45 = (r43 > r24 ? 1 : (r43 == r24 ? 0 : -1))
            if (r45 == 0) goto L_0x0176
            if (r12 == 0) goto L_0x0171
            r43 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x0174
        L_0x0171:
            r43 = 16777216(0x1000000, double:8.289046E-317)
        L_0x0174:
            long r2 = r2 | r43
        L_0x0176:
            if (r12 == 0) goto L_0x0179
            goto L_0x017c
        L_0x0179:
            r12 = 8
            goto L_0x017d
        L_0x017c:
            r12 = 0
        L_0x017d:
            long r43 = r2 & r32
            r24 = 0
            int r45 = (r43 > r24 ? 1 : (r43 == r24 ? 0 : -1))
            if (r45 == 0) goto L_0x019c
            r13 = r13 & r15
            int r15 = (r43 > r24 ? 1 : (r43 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x0195
            if (r13 == 0) goto L_0x0190
            r43 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x0193
        L_0x0190:
            r43 = 1048576(0x100000, double:5.180654E-318)
        L_0x0193:
            long r2 = r2 | r43
        L_0x0195:
            if (r13 == 0) goto L_0x0199
            r18 = 0
        L_0x0199:
            r13 = r18
            goto L_0x019d
        L_0x019c:
            r13 = 0
        L_0x019d:
            long r26 = r2 & r26
            r24 = 0
            int r15 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x01a8
            r10 = r10 ^ 1
            goto L_0x01a9
        L_0x01a8:
            r10 = 0
        L_0x01a9:
            long r26 = r2 & r34
            int r15 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1))
            if (r15 == 0) goto L_0x01ca
            if (r8 == 0) goto L_0x01b2
            goto L_0x01b3
        L_0x01b2:
            r10 = 0
        L_0x01b3:
            int r8 = (r26 > r24 ? 1 : (r26 == r24 ? 0 : -1))
            if (r8 == 0) goto L_0x01c2
            if (r10 == 0) goto L_0x01bd
            r26 = 524288(0x80000, double:2.590327E-318)
            goto L_0x01c0
        L_0x01bd:
            r26 = 262144(0x40000, double:1.295163E-318)
        L_0x01c0:
            long r2 = r2 | r26
        L_0x01c2:
            if (r10 == 0) goto L_0x01c5
            goto L_0x01c7
        L_0x01c5:
            r19 = 4
        L_0x01c7:
            r8 = r19
            goto L_0x01cb
        L_0x01ca:
            r8 = 0
        L_0x01cb:
            long r18 = r2 & r38
            r24 = 0
            int r10 = (r18 > r24 ? 1 : (r18 == r24 ? 0 : -1))
            if (r10 == 0) goto L_0x01e2
            android.widget.ImageView r10 = r1.itemMore
            r10.setOnClickListener(r11)
            androidx.constraintlayout.widget.ConstraintLayout r10 = r1.mboundView0
            r10.setOnClickListener(r14)
            androidx.constraintlayout.widget.ConstraintLayout r10 = r1.mboundView0
            r10.setOnLongClickListener(r4)
        L_0x01e2:
            long r10 = r2 & r34
            int r4 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            if (r4 == 0) goto L_0x01ed
            android.widget.ImageView r4 = r1.itemMore
            r4.setVisibility(r8)
        L_0x01ed:
            long r10 = r2 & r36
            int r4 = (r10 > r24 ? 1 : (r10 == r24 ? 0 : -1))
            if (r4 == 0) goto L_0x01fd
            androidx.constraintlayout.widget.ConstraintLayout r4 = r1.mboundView0
            r4.setSelected(r9)
            android.widget.ImageView r4 = r1.selectedCheck
            r4.setVisibility(r5)
        L_0x01fd:
            long r4 = r2 & r30
            int r8 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r8 == 0) goto L_0x0208
            android.widget.ImageView r4 = r1.mediaAbsent
            r4.setVisibility(r6)
        L_0x0208:
            r4 = 2049(0x801, double:1.0123E-320)
            long r4 = r4 & r2
            int r6 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r6 == 0) goto L_0x0214
            android.widget.ImageView r4 = r1.mediaFavorite
            r4.setVisibility(r0)
        L_0x0214:
            long r4 = r2 & r28
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x021f
            android.widget.ImageView r0 = r1.mediaNetwork
            r0.setVisibility(r7)
        L_0x021f:
            long r4 = r2 & r41
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x022a
            android.widget.ImageView r0 = r1.mediaSd
            r0.setVisibility(r12)
        L_0x022a:
            long r4 = r2 & r32
            int r0 = (r4 > r24 ? 1 : (r4 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x0235
            android.widget.ImageView r0 = r1.otgMedia
            r0.setVisibility(r13)
        L_0x0235:
            long r2 = r2 & r16
            int r0 = (r2 > r24 ? 1 : (r2 == r24 ? 0 : -1))
            if (r0 == 0) goto L_0x0257
            int r0 = getBuildSdkInt()
            r2 = 4
            if (r0 < r2) goto L_0x0250
            android.widget.TextView r0 = r1.subtitle
            r14 = r22
            r0.setContentDescription(r14)
            android.widget.TextView r0 = r1.trackNumber
            r4 = r40
            r0.setContentDescription(r4)
        L_0x0250:
            android.widget.TextView r0 = r1.title
            r2 = r21
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r2)
        L_0x0257:
            return
        L_0x0258:
            r0 = move-exception
            monitor-exit(r47)     // Catch:{ all -> 0x0258 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private AudioAlbumTracksAdapter.TrackItemViewHolder value;

        public OnClickListenerImpl setValue(AudioAlbumTracksAdapter.TrackItemViewHolder trackItemViewHolder) {
            this.value = trackItemViewHolder;
            if (trackItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private AudioAlbumTracksAdapter.TrackItemViewHolder value;

        public OnLongClickListenerImpl setValue(AudioAlbumTracksAdapter.TrackItemViewHolder trackItemViewHolder) {
            this.value = trackItemViewHolder;
            if (trackItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View view) {
            return this.value.onLongClick(view);
        }
    }

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private AudioAlbumTracksAdapter.TrackItemViewHolder value;

        public OnClickListenerImpl1 setValue(AudioAlbumTracksAdapter.TrackItemViewHolder trackItemViewHolder) {
            this.value = trackItemViewHolder;
            if (trackItemViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onMoreClick(view);
        }
    }
}
