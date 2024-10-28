package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.browser.BaseBrowserAdapter;

public class BrowserItemBindingImpl extends BrowserItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl2 mHolderOnBanClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl1 mHolderOnCheckBoxClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl4 mHolderOnImageClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;
    private OnClickListenerImpl3 mHolderOnMoreClickAndroidViewViewOnClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.playing, 10);
    }

    public BrowserItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 11, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private BrowserItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[1], objArr[0], objArr[4], objArr[8], objArr[2], objArr[7], objArr[9], objArr[3], objArr[10], objArr[6], objArr[5]);
        this.mDirtyFlags = -1;
        this.browserCheckbox.setTag((Object) null);
        this.browserContainer.setTag((Object) null);
        this.dviIcon.setTag((Object) null);
        this.itemBan.setTag((Object) null);
        this.itemIcon.setTag((Object) null);
        this.itemMore.setTag((Object) null);
        this.mlItemProgress.setTag((Object) null);
        this.mlItemSeen.setTag((Object) null);
        this.text.setTag((Object) null);
        this.title.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 65536;
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
        } else if (BR.filename == i) {
            setFilename((String) obj);
        } else if (BR.bgColor == i) {
            setBgColor(((Integer) obj).intValue());
        } else if (BR.isBannedParent == i) {
            setIsBannedParent(((Boolean) obj).booleanValue());
        } else if (BR.isTv == i) {
            setIsTv(((Boolean) obj).booleanValue());
        } else if (BR.checkEnabled == i) {
            setCheckEnabled(((Boolean) obj).booleanValue());
        } else if (BR.played == i) {
            setPlayed(((Boolean) obj).booleanValue());
        } else if (BR.favorite == i) {
            setFavorite(((Boolean) obj).booleanValue());
        } else if (BR.max == i) {
            setMax(((Integer) obj).intValue());
        } else if (BR.progress == i) {
            setProgress(((Integer) obj).intValue());
        } else if (BR.isBanned == i) {
            setIsBanned(((Boolean) obj).booleanValue());
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.forceCoverHiding == i) {
            setForceCoverHiding(((Boolean) obj).booleanValue());
        } else if (BR.protocol == i) {
            setProtocol((String) obj);
        } else if (BR.holder == i) {
            setHolder((BaseBrowserAdapter.ViewHolder) obj);
        } else if (BR.hasContextMenu != i) {
            return false;
        } else {
            setHasContextMenu(((Boolean) obj).booleanValue());
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

    public void setFilename(String str) {
        this.mFilename = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.filename);
        super.requestRebind();
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.bgColor);
        super.requestRebind();
    }

    public void setIsBannedParent(boolean z) {
        this.mIsBannedParent = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.isBannedParent);
        super.requestRebind();
    }

    public void setIsTv(boolean z) {
        this.mIsTv = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.isTv);
        super.requestRebind();
    }

    public void setCheckEnabled(boolean z) {
        this.mCheckEnabled = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.checkEnabled);
        super.requestRebind();
    }

    public void setPlayed(boolean z) {
        this.mPlayed = z;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.played);
        super.requestRebind();
    }

    public void setFavorite(boolean z) {
        this.mFavorite = z;
        synchronized (this) {
            this.mDirtyFlags |= 128;
        }
        notifyPropertyChanged(BR.favorite);
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

    public void setIsBanned(boolean z) {
        this.mIsBanned = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.isBanned);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setForceCoverHiding(boolean z) {
        this.mForceCoverHiding = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(BR.forceCoverHiding);
        super.requestRebind();
    }

    public void setProtocol(String str) {
        this.mProtocol = str;
        synchronized (this) {
            this.mDirtyFlags |= 8192;
        }
        notifyPropertyChanged(BR.protocol);
        super.requestRebind();
    }

    public void setHolder(BaseBrowserAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 16384;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setHasContextMenu(boolean z) {
        this.mHasContextMenu = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.hasContextMenu);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:170:0x02d9  */
    /* JADX WARNING: Removed duplicated region for block: B:181:0x0306  */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x0311  */
    /* JADX WARNING: Removed duplicated region for block: B:231:0x03d3  */
    /* JADX WARNING: Removed duplicated region for block: B:232:0x03d9  */
    /* JADX WARNING: Removed duplicated region for block: B:236:0x03ed  */
    /* JADX WARNING: Removed duplicated region for block: B:247:0x0410  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x0419  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0423  */
    /* JADX WARNING: Removed duplicated region for block: B:256:0x042e  */
    /* JADX WARNING: Removed duplicated region for block: B:259:0x0437 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0442  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x044a  */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x0451  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0476  */
    /* JADX WARNING: Removed duplicated region for block: B:295:0x049a  */
    /* JADX WARNING: Removed duplicated region for block: B:302:0x04b1  */
    /* JADX WARNING: Removed duplicated region for block: B:313:0x04d8  */
    /* JADX WARNING: Removed duplicated region for block: B:316:0x04e2  */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x04e7  */
    /* JADX WARNING: Removed duplicated region for block: B:321:0x04ef  */
    /* JADX WARNING: Removed duplicated region for block: B:326:0x050c  */
    /* JADX WARNING: Removed duplicated region for block: B:329:0x051b  */
    /* JADX WARNING: Removed duplicated region for block: B:332:0x053b  */
    /* JADX WARNING: Removed duplicated region for block: B:335:0x0550  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:343:0x0573  */
    /* JADX WARNING: Removed duplicated region for block: B:346:0x0582  */
    /* JADX WARNING: Removed duplicated region for block: B:349:0x0598  */
    /* JADX WARNING: Removed duplicated region for block: B:352:0x05a5  */
    /* JADX WARNING: Removed duplicated region for block: B:355:0x05b0  */
    /* JADX WARNING: Removed duplicated region for block: B:358:0x05bf  */
    /* JADX WARNING: Removed duplicated region for block: B:361:0x05ce  */
    /* JADX WARNING: Removed duplicated region for block: B:364:0x05dd  */
    /* JADX WARNING: Removed duplicated region for block: B:367:0x05ea  */
    /* JADX WARNING: Removed duplicated region for block: B:370:0x05f5  */
    /* JADX WARNING: Removed duplicated region for block: B:373:0x0604  */
    /* JADX WARNING: Removed duplicated region for block: B:376:0x0614  */
    /* JADX WARNING: Removed duplicated region for block: B:379:0x061d  */
    /* JADX WARNING: Removed duplicated region for block: B:382:0x0628  */
    /* JADX WARNING: Removed duplicated region for block: B:385:0x0637  */
    /* JADX WARNING: Removed duplicated region for block: B:394:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r78 = this;
            r1 = r78
            monitor-enter(r78)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x063e }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x063e }
            monitor-exit(r78)     // Catch:{ all -> 0x063e }
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r1.mItem
            java.lang.String r6 = r1.mFilename
            int r7 = r1.mBgColor
            boolean r8 = r1.mIsBannedParent
            boolean r9 = r1.mIsTv
            boolean r10 = r1.mCheckEnabled
            boolean r11 = r1.mPlayed
            boolean r12 = r1.mFavorite
            int r13 = r1.mMax
            int r14 = r1.mProgress
            boolean r15 = r1.mIsBanned
            android.graphics.drawable.BitmapDrawable r4 = r1.mCover
            boolean r5 = r1.mForceCoverHiding
            r18 = r13
            java.lang.String r13 = r1.mProtocol
            r19 = r4
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$ViewHolder r4 = r1.mHolder
            r20 = r7
            boolean r7 = r1.mHasContextMenu
            r21 = 66689(0x10481, double:3.29487E-319)
            long r21 = r2 & r21
            r23 = r9
            r24 = 66561(0x10401, double:3.28855E-319)
            r26 = 65537(0x10001, double:3.23796E-319)
            r28 = 536870912(0x20000000, double:2.652494739E-315)
            r30 = 8
            r32 = 0
            r16 = 0
            int r33 = (r21 > r16 ? 1 : (r21 == r16 ? 0 : -1))
            if (r33 == 0) goto L_0x00cc
            long r21 = r2 & r26
            int r33 = (r21 > r16 ? 1 : (r21 == r16 ? 0 : -1))
            if (r33 == 0) goto L_0x0092
            if (r0 == 0) goto L_0x005d
            int r33 = r0.getItemType()
            r34 = r4
            r9 = r33
            r4 = 128(0x80, float:1.794E-43)
            goto L_0x0062
        L_0x005d:
            r34 = r4
            r4 = 128(0x80, float:1.794E-43)
            r9 = 0
        L_0x0062:
            if (r9 != r4) goto L_0x0066
            r4 = 1
            goto L_0x0067
        L_0x0066:
            r4 = 0
        L_0x0067:
            int r35 = (r21 > r16 ? 1 : (r21 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x007a
            if (r4 == 0) goto L_0x0073
            r21 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            goto L_0x0078
        L_0x0073:
            r21 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
        L_0x0078:
            long r2 = r2 | r21
        L_0x007a:
            long r21 = r2 & r28
            int r35 = (r21 > r16 ? 1 : (r21 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x008c
            if (r4 == 0) goto L_0x0085
            r21 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            goto L_0x008a
        L_0x0085:
            r21 = 140737488355328(0x800000000000, double:6.953355807835E-310)
        L_0x008a:
            long r2 = r2 | r21
        L_0x008c:
            if (r4 == 0) goto L_0x008f
            goto L_0x0096
        L_0x008f:
            r21 = 8
            goto L_0x0098
        L_0x0092:
            r34 = r4
            r4 = 0
            r9 = 0
        L_0x0096:
            r21 = 0
        L_0x0098:
            long r35 = r2 & r24
            r16 = 0
            int r22 = (r35 > r16 ? 1 : (r35 == r16 ? 0 : -1))
            if (r22 == 0) goto L_0x00c1
            if (r0 == 0) goto L_0x00a7
            java.lang.String r22 = r0.getDescription()
            goto L_0x00a9
        L_0x00a7:
            r22 = r32
        L_0x00a9:
            boolean r37 = android.text.TextUtils.isEmpty(r22)
            int r38 = (r35 > r16 ? 1 : (r35 == r16 ? 0 : -1))
            if (r38 == 0) goto L_0x00c5
            if (r37 == 0) goto L_0x00b9
            r35 = 70368744177664(0x400000000000, double:3.4766779039175E-310)
            goto L_0x00be
        L_0x00b9:
            r35 = 35184372088832(0x200000000000, double:1.73833895195875E-310)
        L_0x00be:
            long r2 = r2 | r35
            goto L_0x00c5
        L_0x00c1:
            r22 = r32
            r37 = 0
        L_0x00c5:
            r77 = r21
            r21 = r4
            r4 = r77
            goto L_0x00d6
        L_0x00cc:
            r34 = r4
            r22 = r32
            r4 = 0
            r9 = 0
            r21 = 0
            r37 = 0
        L_0x00d6:
            r35 = 65539(0x10003, double:3.23806E-319)
            long r38 = r2 & r35
            r40 = 262144(0x40000, double:1.295163E-318)
            r16 = 0
            int r42 = (r38 > r16 ? 1 : (r38 == r16 ? 0 : -1))
            if (r42 == 0) goto L_0x00fa
            if (r6 != 0) goto L_0x00e9
            r42 = 1
            goto L_0x00eb
        L_0x00e9:
            r42 = 0
        L_0x00eb:
            int r43 = (r38 > r16 ? 1 : (r38 == r16 ? 0 : -1))
            if (r43 == 0) goto L_0x00fc
            if (r42 == 0) goto L_0x00f4
            long r2 = r2 | r40
            goto L_0x00fc
        L_0x00f4:
            r38 = 131072(0x20000, double:6.47582E-319)
            long r2 = r2 | r38
            goto L_0x00fc
        L_0x00fa:
            r42 = 0
        L_0x00fc:
            r38 = 66585(0x10419, double:3.28974E-319)
            long r43 = r2 & r38
            r45 = 549755813888(0x8000000000, double:2.716154612436E-312)
            r16 = 0
            int r47 = (r43 > r16 ? 1 : (r43 == r16 ? 0 : -1))
            if (r47 == 0) goto L_0x011c
            int r47 = (r43 > r16 ? 1 : (r43 == r16 ? 0 : -1))
            if (r47 == 0) goto L_0x011c
            if (r8 == 0) goto L_0x011a
            r43 = 1099511627776(0x10000000000, double:5.43230922487E-312)
            long r2 = r2 | r43
            goto L_0x011c
        L_0x011a:
            long r2 = r2 | r45
        L_0x011c:
            r43 = 66592(0x10420, double:3.2901E-319)
            long r47 = r2 & r43
            r16 = 0
            int r49 = (r47 > r16 ? 1 : (r47 == r16 ? 0 : -1))
            if (r49 == 0) goto L_0x0136
            int r49 = (r47 > r16 ? 1 : (r47 == r16 ? 0 : -1))
            if (r49 == 0) goto L_0x0136
            if (r10 == 0) goto L_0x0131
            r47 = 268435456(0x10000000, double:1.32624737E-315)
            goto L_0x0134
        L_0x0131:
            r47 = 134217728(0x8000000, double:6.63123685E-316)
        L_0x0134:
            long r2 = r2 | r47
        L_0x0136:
            r47 = 65600(0x10040, double:3.24107E-319)
            long r49 = r2 & r47
            r16 = 0
            int r51 = (r49 > r16 ? 1 : (r49 == r16 ? 0 : -1))
            if (r51 == 0) goto L_0x0162
            int r51 = (r49 > r16 ? 1 : (r49 == r16 ? 0 : -1))
            if (r51 == 0) goto L_0x0154
            if (r11 == 0) goto L_0x014d
            r49 = 68719476736(0x1000000000, double:3.39519326554E-313)
            goto L_0x0152
        L_0x014d:
            r49 = 34359738368(0x800000000, double:1.69759663277E-313)
        L_0x0152:
            long r2 = r2 | r49
        L_0x0154:
            if (r11 == 0) goto L_0x0159
            r49 = 0
            goto L_0x015b
        L_0x0159:
            r49 = 8
        L_0x015b:
            r77 = r49
            r49 = r6
            r6 = r77
            goto L_0x0165
        L_0x0162:
            r49 = r6
            r6 = 0
        L_0x0165:
            r50 = 65665(0x10081, double:3.2443E-319)
            long r52 = r2 & r50
            r16 = 0
            int r54 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r54 == 0) goto L_0x0183
            r52 = r9
            org.videolan.vlc.gui.helpers.TalkbackUtil r9 = org.videolan.vlc.gui.helpers.TalkbackUtil.INSTANCE
            android.view.View r53 = r78.getRoot()
            r54 = r6
            android.content.Context r6 = r53.getContext()
            java.lang.String r6 = r9.getDir(r6, r0, r12)
            goto L_0x0189
        L_0x0183:
            r54 = r6
            r52 = r9
            r6 = r32
        L_0x0189:
            r55 = 66112(0x10240, double:3.26637E-319)
            long r57 = r2 & r55
            int r9 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x01ab
            if (r14 != 0) goto L_0x0196
            r9 = 1
            goto L_0x0197
        L_0x0196:
            r9 = 0
        L_0x0197:
            int r12 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01ac
            if (r9 == 0) goto L_0x01a3
            r57 = 4294967296(0x100000000, double:2.121995791E-314)
            goto L_0x01a8
        L_0x01a3:
            r57 = 2147483648(0x80000000, double:1.0609978955E-314)
        L_0x01a8:
            long r2 = r2 | r57
            goto L_0x01ac
        L_0x01ab:
            r9 = 0
        L_0x01ac:
            r57 = 66560(0x10400, double:3.2885E-319)
            long r57 = r2 & r57
            r59 = 17179869184(0x400000000, double:8.4879831639E-314)
            r61 = 8589934592(0x200000000, double:4.243991582E-314)
            r16 = 0
            int r12 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01d1
            long r57 = r2 & r45
            int r12 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x01ce
            if (r15 == 0) goto L_0x01cc
            long r2 = r2 | r59
            goto L_0x01ce
        L_0x01cc:
            long r2 = r2 | r61
        L_0x01ce:
            r12 = r15 ^ 1
            goto L_0x01d2
        L_0x01d1:
            r12 = 0
        L_0x01d2:
            r57 = 69633(0x11001, double:3.44033E-319)
            long r63 = r2 & r57
            r16 = 0
            int r53 = (r63 > r16 ? 1 : (r63 == r16 ? 0 : -1))
            if (r53 == 0) goto L_0x01eb
            int r53 = (r63 > r16 ? 1 : (r63 == r16 ? 0 : -1))
            if (r53 == 0) goto L_0x01eb
            if (r5 == 0) goto L_0x01e9
            r63 = 1073741824(0x40000000, double:5.304989477E-315)
            long r2 = r2 | r63
            goto L_0x01eb
        L_0x01e9:
            long r2 = r2 | r28
        L_0x01eb:
            r63 = 73728(0x12000, double:3.64265E-319)
            long r63 = r2 & r63
            r16 = 0
            int r53 = (r63 > r16 ? 1 : (r63 == r16 ? 0 : -1))
            if (r53 == 0) goto L_0x0217
            boolean r53 = android.text.TextUtils.isEmpty(r13)
            int r65 = (r63 > r16 ? 1 : (r63 == r16 ? 0 : -1))
            if (r65 == 0) goto L_0x0209
            if (r53 == 0) goto L_0x0204
            r63 = 16777216(0x1000000, double:8.289046E-317)
            goto L_0x0207
        L_0x0204:
            r63 = 8388608(0x800000, double:4.144523E-317)
        L_0x0207:
            long r2 = r2 | r63
        L_0x0209:
            if (r53 == 0) goto L_0x020e
            r53 = 8
            goto L_0x0210
        L_0x020e:
            r53 = 0
        L_0x0210:
            r77 = r53
            r53 = r12
            r12 = r77
            goto L_0x021a
        L_0x0217:
            r53 = r12
            r12 = 0
        L_0x021a:
            r63 = 114689(0x1c001, double:5.6664E-319)
            long r65 = r2 & r63
            r67 = 98304(0x18000, double:4.85686E-319)
            r16 = 0
            int r69 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r69 == 0) goto L_0x032a
            long r65 = r2 & r67
            int r69 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r69 == 0) goto L_0x0239
            if (r7 == 0) goto L_0x0234
            r65 = 1048576(0x100000, double:5.180654E-318)
            goto L_0x0237
        L_0x0234:
            r65 = 524288(0x80000, double:2.590327E-318)
        L_0x0237:
            long r2 = r2 | r65
        L_0x0239:
            long r65 = r2 & r63
            int r69 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r69 == 0) goto L_0x024a
            if (r7 == 0) goto L_0x0245
            r65 = 67108864(0x4000000, double:3.31561842E-316)
            goto L_0x0248
        L_0x0245:
            r65 = 33554432(0x2000000, double:1.6578092E-316)
        L_0x0248:
            long r2 = r2 | r65
        L_0x024a:
            r65 = 81920(0x14000, double:4.0474E-319)
            long r65 = r2 & r65
            r16 = 0
            int r69 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            r65 = r14
            if (r69 == 0) goto L_0x02b5
            if (r34 == 0) goto L_0x02a2
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl r14 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x0264
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl r14 = new org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl
            r14.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r14
        L_0x0264:
            r77 = r34
            r34 = r12
            r12 = r77
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl r14 = r14.setValue(r12)
            r66 = r14
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl1 r14 = r1.mHolderOnCheckBoxClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x027b
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl1 r14 = new org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl1
            r14.<init>()
            r1.mHolderOnCheckBoxClickAndroidViewViewOnClickListener = r14
        L_0x027b:
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl1 r14 = r14.setValue(r12)
            r69 = r14
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl2 r14 = r1.mHolderOnBanClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x028c
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl2 r14 = new org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl2
            r14.<init>()
            r1.mHolderOnBanClickAndroidViewViewOnClickListener = r14
        L_0x028c:
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl2 r14 = r14.setValue(r12)
            r70 = r14
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl4 r14 = r1.mHolderOnImageClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x029d
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl4 r14 = new org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl4
            r14.<init>()
            r1.mHolderOnImageClickAndroidViewViewOnClickListener = r14
        L_0x029d:
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl4 r14 = r14.setValue(r12)
            goto L_0x02b0
        L_0x02a2:
            r77 = r34
            r34 = r12
            r12 = r77
            r14 = r32
            r66 = r14
            r69 = r66
            r70 = r69
        L_0x02b0:
            if (r12 == 0) goto L_0x02c3
            r71 = 1
            goto L_0x02c5
        L_0x02b5:
            r77 = r34
            r34 = r12
            r12 = r77
            r14 = r32
            r66 = r14
            r69 = r66
            r70 = r69
        L_0x02c3:
            r71 = 0
        L_0x02c5:
            r72 = r14
            if (r12 == 0) goto L_0x02d9
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnLongClickListenerImpl r14 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r14 != 0) goto L_0x02d4
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnLongClickListenerImpl r14 = new org.videolan.vlc.databinding.BrowserItemBindingImpl$OnLongClickListenerImpl
            r14.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r14
        L_0x02d4:
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnLongClickListenerImpl r14 = r14.setValue(r12)
            goto L_0x02db
        L_0x02d9:
            r14 = r32
        L_0x02db:
            r73 = 114688(0x1c000, double:5.66634E-319)
            long r73 = r2 & r73
            r16 = 0
            int r75 = (r73 > r16 ? 1 : (r73 == r16 ? 0 : -1))
            if (r75 == 0) goto L_0x02fa
            if (r12 == 0) goto L_0x02fa
            r73 = r14
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl3 r14 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r14 != 0) goto L_0x02f5
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl3 r14 = new org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl3
            r14.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r14
        L_0x02f5:
            org.videolan.vlc.databinding.BrowserItemBindingImpl$OnClickListenerImpl3 r12 = r14.setValue(r12)
            goto L_0x02fe
        L_0x02fa:
            r73 = r14
            r12 = r32
        L_0x02fe:
            long r74 = r2 & r67
            r16 = 0
            int r14 = (r74 > r16 ? 1 : (r74 == r16 ? 0 : -1))
            if (r14 == 0) goto L_0x0311
            if (r7 == 0) goto L_0x030a
            r14 = 0
            goto L_0x030c
        L_0x030a:
            r14 = 8
        L_0x030c:
            r76 = r14
            r14 = r72
            goto L_0x0315
        L_0x0311:
            r14 = r72
            r76 = 0
        L_0x0315:
            r72 = r4
            r4 = r71
            r71 = r6
            r6 = r70
            r70 = r73
            r77 = r66
            r66 = r12
            r12 = r69
            r69 = r13
            r13 = r77
            goto L_0x0340
        L_0x032a:
            r34 = r12
            r65 = r14
            r72 = r4
            r71 = r6
            r69 = r13
            r6 = r32
            r12 = r6
            r13 = r12
            r14 = r13
            r66 = r14
            r70 = r66
            r4 = 0
            r76 = 0
        L_0x0340:
            r73 = 570687488(0x22040000, double:2.819570823E-315)
            long r73 = r2 & r73
            int r75 = (r73 > r16 ? 1 : (r73 == r16 ? 0 : -1))
            if (r75 == 0) goto L_0x03b9
            long r40 = r2 & r40
            int r73 = (r40 > r16 ? 1 : (r40 == r16 ? 0 : -1))
            if (r73 == 0) goto L_0x0356
            if (r0 == 0) goto L_0x0356
            java.lang.String r40 = r0.getTitle()
            goto L_0x0358
        L_0x0356:
            r40 = r32
        L_0x0358:
            r73 = 570425344(0x22000000, double:2.81827566E-315)
            long r73 = r2 & r73
            int r41 = (r73 > r16 ? 1 : (r73 == r16 ? 0 : -1))
            if (r41 == 0) goto L_0x03b4
            if (r0 == 0) goto L_0x036e
            int r21 = r0.getItemType()
            r31 = r6
            r41 = r14
            r14 = r21
            goto L_0x0374
        L_0x036e:
            r31 = r6
            r41 = r14
            r14 = r52
        L_0x0374:
            r6 = 128(0x80, float:1.794E-43)
            if (r14 != r6) goto L_0x037a
            r6 = 1
            goto L_0x037b
        L_0x037a:
            r6 = 0
        L_0x037b:
            long r73 = r2 & r26
            int r14 = (r73 > r16 ? 1 : (r73 == r16 ? 0 : -1))
            if (r14 == 0) goto L_0x0390
            if (r6 == 0) goto L_0x0389
            r73 = 4398046511104(0x40000000000, double:2.1729236899484E-311)
            goto L_0x038e
        L_0x0389:
            r73 = 2199023255552(0x20000000000, double:1.0864618449742E-311)
        L_0x038e:
            long r2 = r2 | r73
        L_0x0390:
            long r73 = r2 & r28
            int r14 = (r73 > r16 ? 1 : (r73 == r16 ? 0 : -1))
            if (r14 == 0) goto L_0x03a2
            if (r6 == 0) goto L_0x039b
            r73 = 281474976710656(0x1000000000000, double:1.390671161567E-309)
            goto L_0x03a0
        L_0x039b:
            r73 = 140737488355328(0x800000000000, double:6.953355807835E-310)
        L_0x03a0:
            long r2 = r2 | r73
        L_0x03a2:
            long r28 = r2 & r28
            int r14 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r14 == 0) goto L_0x03b1
            if (r6 == 0) goto L_0x03ad
            r14 = 8
            goto L_0x03ae
        L_0x03ad:
            r14 = 0
        L_0x03ae:
            r21 = r6
            goto L_0x03c0
        L_0x03b1:
            r21 = r6
            goto L_0x03bf
        L_0x03b4:
            r31 = r6
            r41 = r14
            goto L_0x03bf
        L_0x03b9:
            r31 = r6
            r41 = r14
            r40 = r32
        L_0x03bf:
            r14 = 0
        L_0x03c0:
            r28 = 2147483648(0x80000000, double:1.0609978955E-314)
            long r28 = r2 & r28
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x03e0
            long r28 = r2 & r47
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x03e0
            if (r11 == 0) goto L_0x03d9
            r28 = 68719476736(0x1000000000, double:3.39519326554E-313)
            goto L_0x03de
        L_0x03d9:
            r28 = 34359738368(0x800000000, double:1.69759663277E-313)
        L_0x03de:
            long r2 = r2 | r28
        L_0x03e0:
            r28 = 70918768427008(0x408010000000, double:3.50385271251555E-310)
            long r28 = r2 & r28
            r16 = 0
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x0408
            long r28 = r2 & r45
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x03fa
            if (r15 == 0) goto L_0x03f8
            long r2 = r2 | r59
            goto L_0x03fa
        L_0x03f8:
            long r2 = r2 | r61
        L_0x03fa:
            r28 = 70369012613120(0x400010000000, double:3.4766911663912E-310)
            long r28 = r2 & r28
            int r6 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r6 == 0) goto L_0x0408
            r6 = r15 ^ 1
            goto L_0x040a
        L_0x0408:
            r6 = r53
        L_0x040a:
            long r28 = r2 & r35
            int r52 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r52 == 0) goto L_0x0419
            if (r42 == 0) goto L_0x0414
            r49 = r40
        L_0x0414:
            r28 = r11
            r11 = r49
            goto L_0x041d
        L_0x0419:
            r28 = r11
            r11 = r32
        L_0x041d:
            long r52 = r2 & r63
            int r29 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x042e
            if (r7 == 0) goto L_0x0427
            r21 = 1
        L_0x0427:
            r77 = r21
            r21 = r14
            r14 = r77
            goto L_0x0431
        L_0x042e:
            r21 = r14
            r14 = 0
        L_0x0431:
            long r52 = r2 & r43
            int r29 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x043b
            if (r10 == 0) goto L_0x043b
            r10 = r6
            goto L_0x043c
        L_0x043b:
            r10 = 0
        L_0x043c:
            long r52 = r2 & r57
            int r29 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r29 == 0) goto L_0x044a
            if (r5 == 0) goto L_0x0447
            r5 = 4
            r21 = 4
        L_0x0447:
            r5 = r21
            goto L_0x044b
        L_0x044a:
            r5 = 0
        L_0x044b:
            long r52 = r2 & r55
            int r21 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r21 == 0) goto L_0x046d
            if (r9 == 0) goto L_0x0455
            r28 = 1
        L_0x0455:
            int r9 = (r52 > r16 ? 1 : (r52 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x0468
            if (r28 == 0) goto L_0x0461
            r52 = 17592186044416(0x100000000000, double:8.6916947597938E-311)
            goto L_0x0466
        L_0x0461:
            r52 = 8796093022208(0x80000000000, double:4.345847379897E-311)
        L_0x0466:
            long r2 = r2 | r52
        L_0x0468:
            if (r28 == 0) goto L_0x046d
            r9 = 8
            goto L_0x046e
        L_0x046d:
            r9 = 0
        L_0x046e:
            long r28 = r2 & r24
            r16 = 0
            int r21 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r21 == 0) goto L_0x049a
            if (r37 == 0) goto L_0x047b
            r21 = r6
            goto L_0x047d
        L_0x047b:
            r21 = 0
        L_0x047d:
            int r37 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r37 == 0) goto L_0x0490
            if (r21 == 0) goto L_0x0489
            r28 = 274877906944(0x4000000000, double:1.358077306218E-312)
            goto L_0x048e
        L_0x0489:
            r28 = 137438953472(0x2000000000, double:6.7903865311E-313)
        L_0x048e:
            long r2 = r2 | r28
        L_0x0490:
            if (r21 == 0) goto L_0x0493
            goto L_0x0495
        L_0x0493:
            r30 = 0
        L_0x0495:
            r21 = r11
            r11 = r30
            goto L_0x049d
        L_0x049a:
            r21 = r11
            r11 = 0
        L_0x049d:
            long r28 = r2 & r61
            r16 = 0
            int r30 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x04ab
            if (r0 == 0) goto L_0x04ab
            java.lang.String r22 = r0.getDescription()
        L_0x04ab:
            long r28 = r2 & r59
            int r30 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x04d8
            int r30 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x04c0
            if (r23 == 0) goto L_0x04bb
            r28 = 4194304(0x400000, double:2.0722615E-317)
            goto L_0x04be
        L_0x04bb:
            r28 = 2097152(0x200000, double:1.0361308E-317)
        L_0x04be:
            long r2 = r2 | r28
        L_0x04c0:
            r28 = r2
            android.widget.TextView r2 = r1.text
            android.content.res.Resources r2 = r2.getResources()
            if (r23 == 0) goto L_0x04cd
            int r3 = org.videolan.vlc.R.string.banned_tv
            goto L_0x04cf
        L_0x04cd:
            int r3 = org.videolan.vlc.R.string.banned
        L_0x04cf:
            java.lang.String r2 = r2.getString(r3)
            r23 = r2
            r2 = r28
            goto L_0x04da
        L_0x04d8:
            r23 = r32
        L_0x04da:
            long r28 = r2 & r45
            r16 = 0
            int r30 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r30 == 0) goto L_0x04e7
            if (r15 == 0) goto L_0x04e9
            r22 = r23
            goto L_0x04e9
        L_0x04e7:
            r22 = r32
        L_0x04e9:
            long r28 = r2 & r38
            int r15 = (r28 > r16 ? 1 : (r28 == r16 ? 0 : -1))
            if (r15 == 0) goto L_0x0502
            if (r8 == 0) goto L_0x0500
            android.widget.TextView r8 = r1.text
            android.content.res.Resources r8 = r8.getResources()
            int r15 = org.videolan.vlc.R.string.banned_parent
            java.lang.String r8 = r8.getString(r15)
            r32 = r8
            goto L_0x0502
        L_0x0500:
            r32 = r22
        L_0x0502:
            r8 = r32
            long r22 = r2 & r43
            r15 = 0
            int r17 = (r22 > r15 ? 1 : (r22 == r15 ? 0 : -1))
            if (r17 == 0) goto L_0x0511
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r15 = r1.browserCheckbox
            r15.setEnabled(r10)
        L_0x0511:
            r15 = 81920(0x14000, double:4.0474E-319)
            long r15 = r15 & r2
            r22 = 0
            int r10 = (r15 > r22 ? 1 : (r15 == r22 ? 0 : -1))
            if (r10 == 0) goto L_0x0533
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r10 = r1.browserCheckbox
            r10.setOnClickListener(r12)
            androidx.constraintlayout.widget.ConstraintLayout r10 = r1.browserContainer
            androidx.databinding.adapters.ViewBindingAdapter.setOnClick(r10, r13, r4)
            androidx.appcompat.widget.AppCompatImageView r4 = r1.itemBan
            r10 = r31
            r4.setOnClickListener(r10)
            android.widget.ImageView r4 = r1.itemIcon
            r10 = r41
            r4.setOnClickListener(r10)
        L_0x0533:
            long r12 = r2 & r26
            r15 = 0
            int r4 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r4 == 0) goto L_0x0548
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r4 = r1.browserCheckbox
            r10 = r72
            r4.setVisibility(r10)
            android.widget.ImageView r4 = r1.itemIcon
            r10 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r4, r0, r10, r10, r10)
        L_0x0548:
            r12 = 65540(0x10004, double:3.2381E-319)
            long r12 = r12 & r2
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0559
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.browserContainer
            android.graphics.drawable.ColorDrawable r4 = androidx.databinding.adapters.Converters.convertColorToDrawable(r20)
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(r0, r4)
        L_0x0559:
            long r12 = r2 & r50
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x056d
            int r0 = getBuildSdkInt()
            r4 = 4
            if (r0 < r4) goto L_0x056d
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.browserContainer
            r4 = r71
            r0.setContentDescription(r4)
        L_0x056d:
            long r12 = r2 & r63
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x057a
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.browserContainer
            r4 = r70
            androidx.databinding.adapters.ViewBindingAdapter.setOnLongClick(r0, r4, r14)
        L_0x057a:
            r12 = 73728(0x12000, double:3.64265E-319)
            long r12 = r12 & r2
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0590
            android.widget.TextView r0 = r1.dviIcon
            r4 = r69
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
            android.widget.TextView r0 = r1.dviIcon
            r4 = r34
            r0.setVisibility(r4)
        L_0x0590:
            r12 = 67584(0x10800, double:3.3391E-319)
            long r12 = r12 & r2
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x059f
            android.widget.ImageView r0 = r1.itemIcon
            r4 = r19
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r4)
        L_0x059f:
            long r12 = r2 & r57
            int r0 = (r12 > r15 ? 1 : (r12 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x05aa
            android.widget.ImageView r0 = r1.itemIcon
            r0.setVisibility(r5)
        L_0x05aa:
            long r4 = r2 & r67
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x05b7
            android.widget.ImageView r0 = r1.itemMore
            r14 = r76
            r0.setVisibility(r14)
        L_0x05b7:
            r4 = 114688(0x1c000, double:5.66634E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x05c6
            android.widget.ImageView r0 = r1.itemMore
            r12 = r66
            androidx.databinding.adapters.ViewBindingAdapter.setOnClick(r0, r12, r7)
        L_0x05c6:
            r4 = 65792(0x10100, double:3.25056E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x05d5
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r4 = r18
            r0.setMax(r4)
        L_0x05d5:
            r4 = 66048(0x10200, double:3.2632E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x05e4
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r4 = r65
            r0.setProgress(r4)
        L_0x05e4:
            long r4 = r2 & r55
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x05ef
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r0.setVisibility(r9)
        L_0x05ef:
            long r4 = r2 & r47
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x05fc
            android.widget.ImageView r0 = r1.mlItemSeen
            r4 = r54
            r0.setVisibility(r4)
        L_0x05fc:
            r4 = 66560(0x10400, double:3.2885E-319)
            long r4 = r4 & r2
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x060e
            android.widget.TextView r0 = r1.text
            r0.setEnabled(r6)
            android.widget.TextView r0 = r1.title
            r0.setEnabled(r6)
        L_0x060e:
            long r4 = r2 & r24
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0619
            android.widget.TextView r0 = r1.text
            r0.setVisibility(r11)
        L_0x0619:
            int r0 = (r28 > r15 ? 1 : (r28 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x0622
            android.widget.TextView r0 = r1.text
            org.videolan.vlc.util.KextensionsKt.browserDescription(r0, r8)
        L_0x0622:
            long r4 = r2 & r35
            int r0 = (r4 > r15 ? 1 : (r4 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x062f
            android.widget.TextView r0 = r1.title
            r4 = r21
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
        L_0x062f:
            r4 = 65536(0x10000, double:3.2379E-319)
            long r2 = r2 & r4
            int r0 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r0 == 0) goto L_0x063d
            android.widget.TextView r0 = r1.title
            r2 = 1
            org.videolan.vlc.gui.helpers.UiToolsKt.setEllipsizeModeByPref(r0, r2)
        L_0x063d:
            return
        L_0x063e:
            r0 = move-exception
            monitor-exit(r78)     // Catch:{ all -> 0x063e }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.BrowserItemBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private BaseBrowserAdapter.ViewHolder value;

        public OnClickListenerImpl setValue(BaseBrowserAdapter.ViewHolder viewHolder) {
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

    public static class OnClickListenerImpl1 implements View.OnClickListener {
        private BaseBrowserAdapter.ViewHolder value;

        public OnClickListenerImpl1 setValue(BaseBrowserAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onCheckBoxClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private BaseBrowserAdapter.ViewHolder value;

        public OnLongClickListenerImpl setValue(BaseBrowserAdapter.ViewHolder viewHolder) {
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

    public static class OnClickListenerImpl2 implements View.OnClickListener {
        private BaseBrowserAdapter.ViewHolder value;

        public OnClickListenerImpl2 setValue(BaseBrowserAdapter.ViewHolder viewHolder) {
            this.value = viewHolder;
            if (viewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onBanClick(view);
        }
    }

    public static class OnClickListenerImpl3 implements View.OnClickListener {
        private BaseBrowserAdapter.ViewHolder value;

        public OnClickListenerImpl3 setValue(BaseBrowserAdapter.ViewHolder viewHolder) {
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

    public static class OnClickListenerImpl4 implements View.OnClickListener {
        private BaseBrowserAdapter.ViewHolder value;

        public OnClickListenerImpl4 setValue(BaseBrowserAdapter.ViewHolder viewHolder) {
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
