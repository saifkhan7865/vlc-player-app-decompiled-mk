package org.videolan.television.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.television.BR;
import org.videolan.television.ui.MediaScrapingTvItemAdapter;

public class MovieBrowserTvItemBindingImpl extends MovieBrowserTvItemBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds = null;
    private long mDirtyFlags;
    private OnClickListenerImpl mHolderOnClickAndroidViewViewOnClickListener;
    private OnLongClickListenerImpl mHolderOnLongClickAndroidViewViewOnLongClickListener;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    public MovieBrowserTvItemBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private MovieBrowserTvItemBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[4], objArr[0], objArr[3], objArr[1], objArr[2], objArr[5], objArr[7], objArr[6]);
        this.mDirtyFlags = -1;
        this.badgeTV.setTag((Object) null);
        this.container.setTag((Object) null);
        this.dviIconTv.setTag((Object) null);
        this.mediaCover.setTag((Object) null);
        this.mlItemSeen.setTag((Object) null);
        this.progressBar.setTag((Object) null);
        this.subtitle.setTag((Object) null);
        this.title.setTag((Object) null);
        View view2 = view;
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
        if (BR.item == i) {
            setItem((MediaMetadataWithImages) obj);
        } else if (BR.max == i) {
            setMax(((Integer) obj).intValue());
        } else if (BR.progress == i) {
            setProgress(((Integer) obj).intValue());
        } else if (BR.description == i) {
            setDescription((String) obj);
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.protocol == i) {
            setProtocol((String) obj);
        } else if (BR.holder == i) {
            setHolder((MediaScrapingTvItemAdapter.MovieItemTVViewHolder) obj);
        } else if (BR.badge == i) {
            setBadge((String) obj);
        } else if (BR.isSquare == i) {
            setIsSquare((Boolean) obj);
        } else if (BR.seen == i) {
            setSeen(((Long) obj).longValue());
        } else if (BR.scaleType != i) {
            return false;
        } else {
            setScaleType((ImageView.ScaleType) obj);
        }
        return true;
    }

    public void setItem(MediaMetadataWithImages mediaMetadataWithImages) {
        this.mItem = mediaMetadataWithImages;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.item);
        super.requestRebind();
    }

    public void setMax(int i) {
        this.mMax = i;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.max);
        super.requestRebind();
    }

    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    public void setDescription(String str) {
        this.mDescription = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.description);
        super.requestRebind();
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setProtocol(String str) {
        this.mProtocol = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.protocol);
        super.requestRebind();
    }

    public void setHolder(MediaScrapingTvItemAdapter.MovieItemTVViewHolder movieItemTVViewHolder) {
        this.mHolder = movieItemTVViewHolder;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.holder);
        super.requestRebind();
    }

    public void setBadge(String str) {
        this.mBadge = str;
        synchronized (this) {
            this.mDirtyFlags |= 128;
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
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.seen);
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

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:106:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0096  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00d0  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:73:0x0108  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x013d  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x015e  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x016a  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x018a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r39 = this;
            r1 = r39
            monitor-enter(r39)
            long r2 = r1.mDirtyFlags     // Catch:{ all -> 0x0192 }
            r4 = 0
            r1.mDirtyFlags = r4     // Catch:{ all -> 0x0192 }
            monitor-exit(r39)     // Catch:{ all -> 0x0192 }
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r0 = r1.mItem
            int r6 = r1.mMax
            int r7 = r1.mProgress
            java.lang.String r8 = r1.mDescription
            android.graphics.drawable.BitmapDrawable r9 = r1.mCover
            java.lang.String r10 = r1.mProtocol
            org.videolan.television.ui.MediaScrapingTvItemAdapter$MovieItemTVViewHolder r11 = r1.mHolder
            java.lang.String r12 = r1.mBadge
            long r13 = r1.mSeen
            android.widget.ImageView$ScaleType r15 = r1.mScaleType
            r16 = 2049(0x801, double:1.0123E-320)
            long r18 = r2 & r16
            r20 = 0
            int r21 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1))
            if (r21 == 0) goto L_0x0040
            if (r0 == 0) goto L_0x002f
            org.videolan.moviepedia.database.models.MediaMetadata r18 = r0.getMetadata()
            goto L_0x0031
        L_0x002f:
            r18 = r20
        L_0x0031:
            if (r18 == 0) goto L_0x0040
            java.lang.String r19 = r18.getCurrentPoster()
            java.lang.String r18 = r18.getTitle()
            r22 = r18
            r23 = r19
            goto L_0x0044
        L_0x0040:
            r22 = r20
            r23 = r22
        L_0x0044:
            r18 = 2052(0x804, double:1.014E-320)
            long r24 = r2 & r18
            r21 = 1
            r26 = 8
            r27 = r8
            int r28 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r28 == 0) goto L_0x0071
            if (r7 <= 0) goto L_0x0057
            r28 = 1
            goto L_0x0059
        L_0x0057:
            r28 = 0
        L_0x0059:
            int r29 = (r24 > r4 ? 1 : (r24 == r4 ? 0 : -1))
            if (r29 == 0) goto L_0x0067
            if (r28 == 0) goto L_0x0063
            r24 = 32768(0x8000, double:1.61895E-319)
            goto L_0x0065
        L_0x0063:
            r24 = 16384(0x4000, double:8.0948E-320)
        L_0x0065:
            long r2 = r2 | r24
        L_0x0067:
            if (r28 == 0) goto L_0x006c
            r24 = 0
            goto L_0x006e
        L_0x006c:
            r24 = 8
        L_0x006e:
            r30 = r24
            goto L_0x0073
        L_0x0071:
            r30 = 0
        L_0x0073:
            r24 = 2080(0x820, double:1.0277E-320)
            long r28 = r2 & r24
            int r31 = (r28 > r4 ? 1 : (r28 == r4 ? 0 : -1))
            if (r31 == 0) goto L_0x0096
            boolean r31 = android.text.TextUtils.isEmpty(r10)
            int r32 = (r28 > r4 ? 1 : (r28 == r4 ? 0 : -1))
            if (r32 == 0) goto L_0x008c
            if (r31 == 0) goto L_0x0088
            r28 = 8192(0x2000, double:4.0474E-320)
            goto L_0x008a
        L_0x0088:
            r28 = 4096(0x1000, double:2.0237E-320)
        L_0x008a:
            long r2 = r2 | r28
        L_0x008c:
            if (r31 == 0) goto L_0x0091
            r28 = 8
            goto L_0x0093
        L_0x0091:
            r28 = 0
        L_0x0093:
            r8 = r28
            goto L_0x0097
        L_0x0096:
            r8 = 0
        L_0x0097:
            r31 = 2112(0x840, double:1.0435E-320)
            long r33 = r2 & r31
            int r29 = (r33 > r4 ? 1 : (r33 == r4 ? 0 : -1))
            if (r29 == 0) goto L_0x00c3
            if (r11 == 0) goto L_0x00c3
            org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnClickListenerImpl r4 = r1.mHolderOnClickAndroidViewViewOnClickListener
            if (r4 != 0) goto L_0x00ac
            org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnClickListenerImpl r4 = new org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnClickListenerImpl
            r4.<init>()
            r1.mHolderOnClickAndroidViewViewOnClickListener = r4
        L_0x00ac:
            org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnClickListenerImpl r20 = r4.setValue(r11)
            org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnLongClickListenerImpl r4 = r1.mHolderOnLongClickAndroidViewViewOnLongClickListener
            if (r4 != 0) goto L_0x00bb
            org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnLongClickListenerImpl r4 = new org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnLongClickListenerImpl
            r4.<init>()
            r1.mHolderOnLongClickAndroidViewViewOnLongClickListener = r4
        L_0x00bb:
            org.videolan.television.databinding.MovieBrowserTvItemBindingImpl$OnLongClickListenerImpl r4 = r4.setValue(r11)
            r5 = r4
            r4 = r20
            goto L_0x00c6
        L_0x00c3:
            r4 = r20
            r5 = r4
        L_0x00c6:
            r35 = 2560(0xa00, double:1.265E-320)
            long r37 = r2 & r35
            r33 = 0
            int r11 = (r37 > r33 ? 1 : (r37 == r33 ? 0 : -1))
            if (r11 == 0) goto L_0x00ed
            int r11 = (r13 > r33 ? 1 : (r13 == r33 ? 0 : -1))
            if (r11 != 0) goto L_0x00d5
            goto L_0x00d7
        L_0x00d5:
            r21 = 0
        L_0x00d7:
            int r11 = (r37 > r33 ? 1 : (r37 == r33 ? 0 : -1))
            if (r11 == 0) goto L_0x00e5
            if (r21 == 0) goto L_0x00e1
            r13 = 131072(0x20000, double:6.47582E-319)
            goto L_0x00e4
        L_0x00e1:
            r13 = 65536(0x10000, double:3.2379E-319)
        L_0x00e4:
            long r2 = r2 | r13
        L_0x00e5:
            if (r21 == 0) goto L_0x00e8
            goto L_0x00ea
        L_0x00e8:
            r26 = 0
        L_0x00ea:
            r11 = r26
            goto L_0x00ee
        L_0x00ed:
            r11 = 0
        L_0x00ee:
            r13 = 3072(0xc00, double:1.518E-320)
            long r13 = r13 & r2
            r20 = 2176(0x880, double:1.075E-320)
            long r20 = r2 & r20
            r33 = 0
            int r26 = (r20 > r33 ? 1 : (r20 == r33 ? 0 : -1))
            r20 = r7
            if (r26 == 0) goto L_0x0102
            android.widget.TextView r7 = r1.badgeTV
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r7, r12)
        L_0x0102:
            long r31 = r2 & r31
            int r7 = (r31 > r33 ? 1 : (r31 == r33 ? 0 : -1))
            if (r7 == 0) goto L_0x0112
            org.videolan.television.ui.FocusableConstraintLayout r7 = r1.container
            r7.setOnClickListener(r4)
            org.videolan.television.ui.FocusableConstraintLayout r4 = r1.container
            r4.setOnLongClickListener(r5)
        L_0x0112:
            long r4 = r2 & r24
            int r7 = (r4 > r33 ? 1 : (r4 == r33 ? 0 : -1))
            if (r7 == 0) goto L_0x0122
            android.widget.TextView r4 = r1.dviIconTv
            androidx.databinding.adapters.TextViewBindingAdapter.setText(r4, r10)
            android.widget.TextView r4 = r1.dviIconTv
            r4.setVisibility(r8)
        L_0x0122:
            r4 = 2064(0x810, double:1.0198E-320)
            long r4 = r4 & r2
            int r7 = (r4 > r33 ? 1 : (r4 == r33 ? 0 : -1))
            if (r7 == 0) goto L_0x012e
            org.videolan.vlc.gui.view.FadableImageView r4 = r1.mediaCover
            androidx.databinding.adapters.ImageViewBindingAdapter.setImageDrawable(r4, r9)
        L_0x012e:
            int r4 = (r13 > r33 ? 1 : (r13 == r33 ? 0 : -1))
            if (r4 == 0) goto L_0x0137
            org.videolan.vlc.gui.view.FadableImageView r4 = r1.mediaCover
            r4.setScaleType(r15)
        L_0x0137:
            long r4 = r2 & r16
            int r7 = (r4 > r33 ? 1 : (r4 == r33 ? 0 : -1))
            if (r7 == 0) goto L_0x0156
            org.videolan.vlc.gui.view.FadableImageView r4 = r1.mediaCover
            r5 = r23
            r7 = 0
            org.videolan.vlc.gui.helpers.ImageLoaderKt.downloadIcon((android.view.View) r4, (java.lang.String) r5, (boolean) r7)
            androidx.appcompat.widget.AppCompatTextView r4 = r1.subtitle
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r4, r0)
            androidx.appcompat.widget.AppCompatTextView r4 = r1.title
            r5 = r22
            org.videolan.vlc.util.KextensionsKt.asyncText(r4, r5)
            androidx.appcompat.widget.AppCompatTextView r4 = r1.title
            org.videolan.vlc.gui.helpers.ImageLoaderKt.placeHolderView(r4, r0)
        L_0x0156:
            long r4 = r2 & r35
            r7 = 0
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0163
            android.widget.ImageView r0 = r1.mlItemSeen
            r0.setVisibility(r11)
        L_0x0163:
            r4 = 2050(0x802, double:1.013E-320)
            long r4 = r4 & r2
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x016f
            android.widget.ProgressBar r0 = r1.progressBar
            r0.setMax(r6)
        L_0x016f:
            long r4 = r2 & r18
            int r0 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0183
            android.widget.ProgressBar r0 = r1.progressBar
            r4 = r20
            r0.setProgress(r4)
            android.widget.ProgressBar r0 = r1.progressBar
            r4 = r30
            r0.setVisibility(r4)
        L_0x0183:
            r4 = 2056(0x808, double:1.016E-320)
            long r2 = r2 & r4
            int r0 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r0 == 0) goto L_0x0191
            androidx.appcompat.widget.AppCompatTextView r0 = r1.subtitle
            r2 = r27
            org.videolan.vlc.util.KextensionsKt.asyncText(r0, r2)
        L_0x0191:
            return
        L_0x0192:
            r0 = move-exception
            monitor-exit(r39)     // Catch:{ all -> 0x0192 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.databinding.MovieBrowserTvItemBindingImpl.executeBindings():void");
    }

    public static class OnClickListenerImpl implements View.OnClickListener {
        private MediaScrapingTvItemAdapter.MovieItemTVViewHolder value;

        public OnClickListenerImpl setValue(MediaScrapingTvItemAdapter.MovieItemTVViewHolder movieItemTVViewHolder) {
            this.value = movieItemTVViewHolder;
            if (movieItemTVViewHolder == null) {
                return null;
            }
            return this;
        }

        public void onClick(View view) {
            this.value.onClick(view);
        }
    }

    public static class OnLongClickListenerImpl implements View.OnLongClickListener {
        private MediaScrapingTvItemAdapter.MovieItemTVViewHolder value;

        public OnLongClickListenerImpl setValue(MediaScrapingTvItemAdapter.MovieItemTVViewHolder movieItemTVViewHolder) {
            this.value = movieItemTVViewHolder;
            if (movieItemTVViewHolder == null) {
                return null;
            }
            return this;
        }

        public boolean onLongClick(View view) {
            return this.value.onLongClick(view);
        }
    }
}
