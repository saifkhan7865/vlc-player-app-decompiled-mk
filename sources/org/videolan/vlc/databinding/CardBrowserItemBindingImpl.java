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
import org.videolan.vlc.gui.browser.BaseBrowserAdapter;

public class CardBrowserItemBindingImpl extends CardBrowserItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;
    private OnClickListenerImpl1 mHolderOnCheckBoxClickAndroidViewViewOnClickListener;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;
    private OnClickListenerImpl2 mHolderOnMoreClickAndroidViewViewOnClickListener;
    private final ConstraintLayout mboundView1;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.item_ban, 10);
        sparseIntArray.put(R.id.playing, 11);
    }

    public CardBrowserItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 12, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private CardBrowserItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[2], objArr[0], objArr[5], objArr[10], objArr[3], objArr[8], objArr[9], objArr[4], objArr[11], objArr[7], objArr[6]);
        this.mDirtyFlags = -1;
        this.browserCheckbox.setTag((Object) null);
        this.browserContainer.setTag((Object) null);
        this.dviIcon.setTag((Object) null);
        this.itemIcon.setTag((Object) null);
        this.itemMore.setTag((Object) null);
        ConstraintLayout constraintLayout = objArr[1];
        this.mboundView1 = constraintLayout;
        constraintLayout.setTag((Object) null);
        this.mlItemProgress.setTag((Object) null);
        this.mlItemSeen.setTag((Object) null);
        this.text.setTag((Object) null);
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
        if (BR.item == i) {
            setItem((MediaLibraryItem) obj);
        } else if (BR.filename == i) {
            setFilename((String) obj);
        } else if (BR.bgColor == i) {
            setBgColor(((Integer) obj).intValue());
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

    public void setCheckEnabled(boolean z) {
        this.mCheckEnabled = z;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.checkEnabled);
        super.requestRebind();
    }

    public void setPlayed(boolean z) {
        this.mPlayed = z;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.played);
        super.requestRebind();
    }

    public void setFavorite(boolean z) {
        this.mFavorite = z;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.favorite);
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

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setForceCoverHiding(boolean z) {
        this.mForceCoverHiding = z;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.forceCoverHiding);
        super.requestRebind();
    }

    public void setProtocol(String str) {
        this.mProtocol = str;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.protocol);
        super.requestRebind();
    }

    public void setHolder(BaseBrowserAdapter.ViewHolder viewHolder) {
        this.mHolder = viewHolder;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setHasContextMenu(boolean z) {
        this.mHasContextMenu = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        notifyPropertyChanged(BR.hasContextMenu);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:187:0x02f1  */
    /* JADX WARNING: Removed duplicated region for block: B:188:0x02f4  */
    /* JADX WARNING: Removed duplicated region for block: B:191:0x02fc  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0303  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x030b  */
    /* JADX WARNING: Removed duplicated region for block: B:200:0x0316  */
    /* JADX WARNING: Removed duplicated region for block: B:203:0x031f  */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0323  */
    /* JADX WARNING: Removed duplicated region for block: B:208:0x032a  */
    /* JADX WARNING: Removed duplicated region for block: B:219:0x0347  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x0352  */
    /* JADX WARNING: Removed duplicated region for block: B:223:0x035a  */
    /* JADX WARNING: Removed duplicated region for block: B:226:0x0364  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0374  */
    /* JADX WARNING: Removed duplicated region for block: B:237:0x03a8  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x03b5  */
    /* JADX WARNING: Removed duplicated region for block: B:243:0x03ca  */
    /* JADX WARNING: Removed duplicated region for block: B:246:0x03d7  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x03e2  */
    /* JADX WARNING: Removed duplicated region for block: B:252:0x03f0  */
    /* JADX WARNING: Removed duplicated region for block: B:255:0x03fe  */
    /* JADX WARNING: Removed duplicated region for block: B:258:0x040e  */
    /* JADX WARNING: Removed duplicated region for block: B:261:0x041c  */
    /* JADX WARNING: Removed duplicated region for block: B:264:0x0429  */
    /* JADX WARNING: Removed duplicated region for block: B:267:0x0434  */
    /* JADX WARNING: Removed duplicated region for block: B:270:0x0441  */
    /* JADX WARNING: Removed duplicated region for block: B:273:0x044f  */
    /* JADX WARNING: Removed duplicated region for block: B:282:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r71 = this;
            r1 = r71
            monitor-enter(r71)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0456 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0456 }
            monitor-exit(r71)     // Catch:{ all -> 0x0456 }
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r1.mItem
            java.lang.String r6 = r1.mFilename
            int r7 = r1.mBgColor
            boolean r8 = r1.mCheckEnabled
            boolean r9 = r1.mPlayed
            boolean r10 = r1.mFavorite
            int r11 = r1.mMax
            int r12 = r1.mProgress
            android.graphics.drawable.BitmapDrawable r13 = r1.mCover
            boolean r14 = r1.mForceCoverHiding
            java.lang.String r15 = r1.mProtocol
            org.videolan.vlc.gui.browser.BaseBrowserAdapter$ViewHolder r4 = r1.mHolder
            boolean r5 = r1.mHasContextMenu
            r18 = 8225(0x2021, double:4.0637E-320)
            long r20 = r2 & r18
            r22 = 17179869184(0x400000000, double:8.4879831639E-314)
            r24 = 536870912(0x20000000, double:2.652494739E-315)
            r26 = 268435456(0x10000000, double:1.32624737E-315)
            r28 = r11
            r29 = 4194304(0x400000, double:2.0722615E-317)
            r31 = 8193(0x2001, double:4.048E-320)
            r33 = 8
            r34 = 0
            r16 = 0
            int r35 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x00d8
            long r20 = r2 & r31
            int r35 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r35 == 0) goto L_0x00aa
            if (r0 == 0) goto L_0x0057
            java.lang.String r35 = r0.getDescription()
            int r36 = r0.getItemType()
            r11 = r36
            goto L_0x005a
        L_0x0057:
            r35 = r34
            r11 = 0
        L_0x005a:
            boolean r37 = android.text.TextUtils.isEmpty(r35)
            r38 = r7
            r7 = 128(0x80, float:1.794E-43)
            if (r11 != r7) goto L_0x0066
            r7 = 1
            goto L_0x0067
        L_0x0066:
            r7 = 0
        L_0x0067:
            int r39 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r39 == 0) goto L_0x007a
            if (r37 == 0) goto L_0x0073
            r20 = 8589934592(0x200000000, double:4.243991582E-314)
            goto L_0x0078
        L_0x0073:
            r20 = 4294967296(0x100000000, double:2.121995791E-314)
        L_0x0078:
            long r2 = r2 | r20
        L_0x007a:
            long r20 = r2 & r31
            int r39 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r39 == 0) goto L_0x0087
            if (r7 == 0) goto L_0x0085
            long r2 = r2 | r24
            goto L_0x0087
        L_0x0085:
            long r2 = r2 | r26
        L_0x0087:
            long r20 = r2 & r29
            int r39 = (r20 > r16 ? 1 : (r20 == r16 ? 0 : -1))
            if (r39 == 0) goto L_0x0099
            if (r7 == 0) goto L_0x0097
            r20 = 34359738368(0x800000000, double:1.69759663277E-313)
            long r2 = r2 | r20
            goto L_0x0099
        L_0x0097:
            long r2 = r2 | r22
        L_0x0099:
            if (r37 == 0) goto L_0x009e
            r20 = 8
            goto L_0x00a0
        L_0x009e:
            r20 = 0
        L_0x00a0:
            if (r7 == 0) goto L_0x00a5
            r21 = 0
            goto L_0x00a7
        L_0x00a5:
            r21 = 8
        L_0x00a7:
            r39 = r2
            goto L_0x00b6
        L_0x00aa:
            r38 = r7
            r39 = r2
            r35 = r34
            r7 = 0
            r11 = 0
            r20 = 0
            r21 = 0
        L_0x00b6:
            org.videolan.vlc.gui.helpers.TalkbackUtil r2 = org.videolan.vlc.gui.helpers.TalkbackUtil.INSTANCE
            android.view.View r3 = r71.getRoot()
            android.content.Context r3 = r3.getContext()
            java.lang.String r2 = r2.getDir(r3, r0, r10)
            r10 = r20
            r20 = r7
            r7 = r2
            r2 = r39
            r69 = r21
            r21 = r11
            r11 = r69
            r70 = r35
            r35 = r13
            r13 = r70
            goto L_0x00e5
        L_0x00d8:
            r38 = r7
            r35 = r13
            r7 = r34
            r13 = r7
            r10 = 0
            r11 = 0
            r20 = 0
            r21 = 0
        L_0x00e5:
            r39 = 8195(0x2003, double:4.049E-320)
            long r41 = r2 & r39
            r43 = 32768(0x8000, double:1.61895E-319)
            r16 = 0
            int r37 = (r41 > r16 ? 1 : (r41 == r16 ? 0 : -1))
            if (r37 == 0) goto L_0x0107
            if (r6 != 0) goto L_0x00f7
            r37 = 1
            goto L_0x00f9
        L_0x00f7:
            r37 = 0
        L_0x00f9:
            int r45 = (r41 > r16 ? 1 : (r41 == r16 ? 0 : -1))
            if (r45 == 0) goto L_0x0109
            if (r37 == 0) goto L_0x0102
            long r2 = r2 | r43
            goto L_0x0109
        L_0x0102:
            r41 = 16384(0x4000, double:8.0948E-320)
            long r2 = r2 | r41
            goto L_0x0109
        L_0x0107:
            r37 = 0
        L_0x0109:
            r41 = 8208(0x2010, double:4.0553E-320)
            long r45 = r2 & r41
            r47 = 134217728(0x8000000, double:6.63123685E-316)
            r49 = 67108864(0x4000000, double:3.31561842E-316)
            r16 = 0
            int r51 = (r45 > r16 ? 1 : (r45 == r16 ? 0 : -1))
            if (r51 == 0) goto L_0x0132
            int r51 = (r45 > r16 ? 1 : (r45 == r16 ? 0 : -1))
            if (r51 == 0) goto L_0x0124
            if (r9 == 0) goto L_0x0122
            long r2 = r2 | r47
            goto L_0x0124
        L_0x0122:
            long r2 = r2 | r49
        L_0x0124:
            if (r9 == 0) goto L_0x0129
            r45 = 0
            goto L_0x012b
        L_0x0129:
            r45 = 8
        L_0x012b:
            r69 = r45
            r45 = r6
            r6 = r69
            goto L_0x0135
        L_0x0132:
            r45 = r6
            r6 = 0
        L_0x0135:
            r51 = 8336(0x2090, double:4.1185E-320)
            long r53 = r2 & r51
            r16 = 0
            int r46 = (r53 > r16 ? 1 : (r53 == r16 ? 0 : -1))
            if (r46 == 0) goto L_0x0156
            if (r12 != 0) goto L_0x0144
            r46 = 1
            goto L_0x0146
        L_0x0144:
            r46 = 0
        L_0x0146:
            int r55 = (r53 > r16 ? 1 : (r53 == r16 ? 0 : -1))
            if (r55 == 0) goto L_0x0158
            if (r46 == 0) goto L_0x0150
            r53 = 33554432(0x2000000, double:1.6578092E-316)
            goto L_0x0153
        L_0x0150:
            r53 = 16777216(0x1000000, double:8.289046E-317)
        L_0x0153:
            long r2 = r2 | r53
            goto L_0x0158
        L_0x0156:
            r46 = 0
        L_0x0158:
            r53 = 8705(0x2201, double:4.301E-320)
            long r55 = r2 & r53
            r16 = 0
            int r57 = (r55 > r16 ? 1 : (r55 == r16 ? 0 : -1))
            if (r57 == 0) goto L_0x0170
            int r57 = (r55 > r16 ? 1 : (r55 == r16 ? 0 : -1))
            if (r57 == 0) goto L_0x0170
            if (r14 == 0) goto L_0x016e
            r55 = 8388608(0x800000, double:4.144523E-317)
            long r2 = r2 | r55
            goto L_0x0170
        L_0x016e:
            long r2 = r2 | r29
        L_0x0170:
            r55 = 9216(0x2400, double:4.5533E-320)
            long r57 = r2 & r55
            r16 = 0
            int r59 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r59 == 0) goto L_0x019b
            boolean r59 = android.text.TextUtils.isEmpty(r15)
            int r60 = (r57 > r16 ? 1 : (r57 == r16 ? 0 : -1))
            if (r60 == 0) goto L_0x018d
            if (r59 == 0) goto L_0x0188
            r57 = 524288(0x80000, double:2.590327E-318)
            goto L_0x018b
        L_0x0188:
            r57 = 262144(0x40000, double:1.295163E-318)
        L_0x018b:
            long r2 = r2 | r57
        L_0x018d:
            if (r59 == 0) goto L_0x0192
            r57 = 8
            goto L_0x0194
        L_0x0192:
            r57 = 0
        L_0x0194:
            r69 = r57
            r57 = r6
            r6 = r69
            goto L_0x019e
        L_0x019b:
            r57 = r6
            r6 = 0
        L_0x019e:
            r58 = 14337(0x3801, double:7.0834E-320)
            long r60 = r2 & r58
            r62 = 12288(0x3000, double:6.071E-320)
            r16 = 0
            int r64 = (r60 > r16 ? 1 : (r60 == r16 ? 0 : -1))
            if (r64 == 0) goto L_0x025f
            long r60 = r2 & r62
            int r64 = (r60 > r16 ? 1 : (r60 == r16 ? 0 : -1))
            if (r64 == 0) goto L_0x01bb
            if (r5 == 0) goto L_0x01b6
            r60 = 131072(0x20000, double:6.47582E-319)
            goto L_0x01b9
        L_0x01b6:
            r60 = 65536(0x10000, double:3.2379E-319)
        L_0x01b9:
            long r2 = r2 | r60
        L_0x01bb:
            long r60 = r2 & r58
            int r64 = (r60 > r16 ? 1 : (r60 == r16 ? 0 : -1))
            if (r64 == 0) goto L_0x01cc
            if (r5 == 0) goto L_0x01c7
            r60 = 2097152(0x200000, double:1.0361308E-317)
            goto L_0x01ca
        L_0x01c7:
            r60 = 1048576(0x100000, double:5.180654E-318)
        L_0x01ca:
            long r2 = r2 | r60
        L_0x01cc:
            r60 = 10240(0x2800, double:5.059E-320)
            long r60 = r2 & r60
            r16 = 0
            int r64 = (r60 > r16 ? 1 : (r60 == r16 ? 0 : -1))
            if (r64 == 0) goto L_0x0201
            if (r4 == 0) goto L_0x0201
            r60 = r12
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl r12 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r12 != 0) goto L_0x01e5
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl r12 = new org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl
            r12.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r12
        L_0x01e5:
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl r12 = r12.setValue(r4)
            r61 = r12
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl1 r12 = r1.mHolderOnCheckBoxClickAndroidViewViewOnClickListener
            if (r12 != 0) goto L_0x01f6
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl1 r12 = new org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl1
            r12.<init>()
            r1.mHolderOnCheckBoxClickAndroidViewViewOnClickListener = r12
        L_0x01f6:
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl1 r12 = r12.setValue(r4)
            r69 = r61
            r61 = r12
            r12 = r69
            goto L_0x0207
        L_0x0201:
            r60 = r12
            r12 = r34
            r61 = r12
        L_0x0207:
            r64 = r12
            if (r4 == 0) goto L_0x021b
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnLongClickListenerImpl r12 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r12 != 0) goto L_0x0216
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnLongClickListenerImpl r12 = new org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnLongClickListenerImpl
            r12.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r12
        L_0x0216:
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnLongClickListenerImpl r12 = r12.setValue(r4)
            goto L_0x021d
        L_0x021b:
            r12 = r34
        L_0x021d:
            r65 = 14336(0x3800, double:7.083E-320)
            long r65 = r2 & r65
            r16 = 0
            int r67 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r67 == 0) goto L_0x023b
            if (r4 == 0) goto L_0x023b
            r65 = r12
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl2 r12 = r1.mHolderOnMoreClickAndroidViewViewOnClickListener
            if (r12 != 0) goto L_0x0236
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl2 r12 = new org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl2
            r12.<init>()
            r1.mHolderOnMoreClickAndroidViewViewOnClickListener = r12
        L_0x0236:
            org.videolan.vlc.databinding.CardBrowserItemBindingImpl$OnClickListenerImpl2 r4 = r12.setValue(r4)
            goto L_0x023f
        L_0x023b:
            r65 = r12
            r4 = r34
        L_0x023f:
            long r66 = r2 & r62
            r16 = 0
            int r12 = (r66 > r16 ? 1 : (r66 == r16 ? 0 : -1))
            if (r12 == 0) goto L_0x0252
            if (r5 == 0) goto L_0x024b
            r12 = 0
            goto L_0x024d
        L_0x024b:
            r12 = 8
        L_0x024d:
            r68 = r12
            r12 = r64
            goto L_0x0256
        L_0x0252:
            r12 = r64
            r68 = 0
        L_0x0256:
            r64 = r6
            r6 = r61
            r61 = r4
            r4 = r65
            goto L_0x026b
        L_0x025f:
            r60 = r12
            r64 = r6
            r4 = r34
            r6 = r4
            r12 = r6
            r61 = r12
            r68 = 0
        L_0x026b:
            r65 = 5275648(0x508000, double:2.6065164E-317)
            long r65 = r2 & r65
            int r67 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r67 == 0) goto L_0x02d9
            long r43 = r2 & r43
            int r65 = (r43 > r16 ? 1 : (r43 == r16 ? 0 : -1))
            if (r65 == 0) goto L_0x0281
            if (r0 == 0) goto L_0x0281
            java.lang.String r43 = r0.getTitle()
            goto L_0x0283
        L_0x0281:
            r43 = r34
        L_0x0283:
            r65 = 5242880(0x500000, double:2.590327E-317)
            long r65 = r2 & r65
            int r44 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r44 == 0) goto L_0x02d4
            if (r0 == 0) goto L_0x0299
            int r20 = r0.getItemType()
            r21 = r4
            r44 = r15
            r15 = r20
            goto L_0x029f
        L_0x0299:
            r44 = r15
            r15 = r21
            r21 = r4
        L_0x029f:
            r4 = 128(0x80, float:1.794E-43)
            if (r15 != r4) goto L_0x02a5
            r4 = 1
            goto L_0x02a6
        L_0x02a5:
            r4 = 0
        L_0x02a6:
            long r65 = r2 & r31
            int r15 = (r65 > r16 ? 1 : (r65 == r16 ? 0 : -1))
            if (r15 == 0) goto L_0x02b3
            if (r4 == 0) goto L_0x02b1
            long r2 = r2 | r24
            goto L_0x02b3
        L_0x02b1:
            long r2 = r2 | r26
        L_0x02b3:
            long r24 = r2 & r29
            int r15 = (r24 > r16 ? 1 : (r24 == r16 ? 0 : -1))
            if (r15 == 0) goto L_0x02c2
            if (r4 == 0) goto L_0x02c0
            r22 = 34359738368(0x800000000, double:1.69759663277E-313)
        L_0x02c0:
            long r2 = r2 | r22
        L_0x02c2:
            long r22 = r2 & r29
            int r15 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r15 == 0) goto L_0x02d1
            if (r4 == 0) goto L_0x02cd
            r15 = 8
            goto L_0x02ce
        L_0x02cd:
            r15 = 0
        L_0x02ce:
            r20 = r4
            goto L_0x02e0
        L_0x02d1:
            r20 = r4
            goto L_0x02df
        L_0x02d4:
            r21 = r4
            r44 = r15
            goto L_0x02df
        L_0x02d9:
            r21 = r4
            r44 = r15
            r43 = r34
        L_0x02df:
            r15 = 0
        L_0x02e0:
            r22 = 16777216(0x1000000, double:8.289046E-317)
            long r22 = r2 & r22
            int r4 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x02f6
            long r22 = r2 & r41
            int r4 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x02f6
            if (r9 == 0) goto L_0x02f4
            long r2 = r2 | r47
            goto L_0x02f6
        L_0x02f4:
            long r2 = r2 | r49
        L_0x02f6:
            long r22 = r2 & r39
            int r4 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x0303
            if (r37 == 0) goto L_0x0300
            r45 = r43
        L_0x0300:
            r4 = r45
            goto L_0x0305
        L_0x0303:
            r4 = r34
        L_0x0305:
            long r22 = r2 & r58
            int r24 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x0316
            if (r5 == 0) goto L_0x030f
            r20 = 1
        L_0x030f:
            r69 = r20
            r20 = r9
            r9 = r69
            goto L_0x0319
        L_0x0316:
            r20 = r9
            r9 = 0
        L_0x0319:
            long r22 = r2 & r53
            int r24 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r24 == 0) goto L_0x0323
            if (r14 == 0) goto L_0x0324
            r15 = 4
            goto L_0x0324
        L_0x0323:
            r15 = 0
        L_0x0324:
            long r22 = r2 & r51
            int r14 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r14 == 0) goto L_0x0347
            if (r46 == 0) goto L_0x032e
            r20 = 1
        L_0x032e:
            int r14 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r14 == 0) goto L_0x033f
            if (r20 == 0) goto L_0x033a
            r22 = 2147483648(0x80000000, double:1.0609978955E-314)
            goto L_0x033d
        L_0x033a:
            r22 = 1073741824(0x40000000, double:5.304989477E-315)
        L_0x033d:
            long r2 = r2 | r22
        L_0x033f:
            if (r20 == 0) goto L_0x0342
            goto L_0x0344
        L_0x0342:
            r33 = 0
        L_0x0344:
            r14 = r33
            goto L_0x0348
        L_0x0347:
            r14 = 0
        L_0x0348:
            r22 = 8200(0x2008, double:4.0513E-320)
            long r22 = r2 & r22
            r16 = 0
            int r20 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r20 == 0) goto L_0x035a
            r20 = r4
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r4 = r1.browserCheckbox
            r4.setEnabled(r8)
            goto L_0x035c
        L_0x035a:
            r20 = r4
        L_0x035c:
            r22 = 10240(0x2800, double:5.059E-320)
            long r22 = r2 & r22
            int r4 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x036e
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r4 = r1.browserCheckbox
            r4.setOnClickListener(r6)
            com.google.android.material.card.MaterialCardView r4 = r1.browserContainer
            r4.setOnClickListener(r12)
        L_0x036e:
            long r22 = r2 & r31
            int r4 = (r22 > r16 ? 1 : (r22 == r16 ? 0 : -1))
            if (r4 == 0) goto L_0x038e
            org.videolan.vlc.gui.helpers.ThreeStatesCheckbox r4 = r1.browserCheckbox
            r4.setVisibility(r11)
            com.google.android.material.card.MaterialCardView r4 = r1.browserContainer
            org.videolan.vlc.util.AccessibilityHelperKt.mediaDescription(r4, r0)
            android.widget.ImageView r4 = r1.itemIcon
            r6 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.loadImage(r4, r0, r6, r6, r6)
            android.widget.TextView r0 = r1.text
            org.videolan.vlc.util.KextensionsKt.browserDescription(r0, r13)
            android.widget.TextView r0 = r1.text
            r0.setVisibility(r10)
        L_0x038e:
            long r10 = r2 & r18
            r12 = 0
            int r0 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x03a2
            int r0 = getBuildSdkInt()
            r4 = 4
            if (r0 < r4) goto L_0x03a2
            com.google.android.material.card.MaterialCardView r0 = r1.browserContainer
            r0.setContentDescription(r7)
        L_0x03a2:
            long r6 = r2 & r58
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x03af
            com.google.android.material.card.MaterialCardView r0 = r1.browserContainer
            r4 = r21
            androidx.databinding.adapters.ViewBindingAdapter.setOnLongClick(r0, r4, r9)
        L_0x03af:
            long r6 = r2 & r55
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x03c3
            android.widget.TextView r0 = r1.dviIcon
            r4 = r44
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r0, r4)
            android.widget.TextView r0 = r1.dviIcon
            r4 = r64
            r0.setVisibility(r4)
        L_0x03c3:
            r6 = 8448(0x2100, double:4.174E-320)
            long r6 = r6 & r2
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x03d1
            android.widget.ImageView r0 = r1.itemIcon
            r4 = r35
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r0, r4)
        L_0x03d1:
            long r6 = r2 & r53
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x03dc
            android.widget.ImageView r0 = r1.itemIcon
            r0.setVisibility(r15)
        L_0x03dc:
            long r6 = r2 & r62
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x03e9
            android.widget.ImageView r0 = r1.itemMore
            r4 = r68
            r0.setVisibility(r4)
        L_0x03e9:
            r6 = 14336(0x3800, double:7.083E-320)
            long r6 = r6 & r2
            int r0 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x03f7
            android.widget.ImageView r0 = r1.itemMore
            r4 = r61
            androidx.databinding.adapters.ViewBindingAdapter.setOnClick(r0, r4, r5)
        L_0x03f7:
            r4 = 8196(0x2004, double:4.0494E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0407
            androidx.constraintlayout.widget.ConstraintLayout r0 = r1.mboundView1
            android.graphics.drawable.ColorDrawable r4 = androidx.databinding.adapters.Converters.convertColorToDrawable(r38)
            androidx.databinding.adapters.ViewBindingAdapter.setBackground(r0, r4)
        L_0x0407:
            r4 = 8256(0x2040, double:4.079E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0415
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r4 = r28
            r0.setMax(r4)
        L_0x0415:
            r4 = 8320(0x2080, double:4.1106E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0423
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r4 = r60
            r0.setProgress(r4)
        L_0x0423:
            long r4 = r2 & r51
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x042e
            android.widget.ProgressBar r0 = r1.mlItemProgress
            r0.setVisibility(r14)
        L_0x042e:
            long r4 = r2 & r41
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x043b
            android.widget.ImageView r0 = r1.mlItemSeen
            r4 = r57
            r0.setVisibility(r4)
        L_0x043b:
            long r4 = r2 & r39
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0448
            android.widget.TextView r0 = r1.title
            r4 = r20
            org.videolan.vlc.util.KextensionsKt.asyncText(r0, r4)
        L_0x0448:
            r4 = 8192(0x2000, double:4.0474E-320)
            long r2 = r2 & r4
            int r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r0 == 0) goto L_0x0455
            android.widget.TextView r0 = r1.title
            r2 = 1
            org.videolan.vlc.gui.helpers.UiToolsKt.setEllipsizeModeByPref(r0, r2)
        L_0x0455:
            return
        L_0x0456:
            r0 = move-exception
            monitor-exit(r71)     // Catch:{ all -> 0x0456 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.databinding.CardBrowserItemBindingImpl.executeBindings():void");
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
            this.value.onMoreClick(view);
        }
    }
}
