package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import androidx.databinding.adapters.TextViewBindingAdapter;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.TalkbackUtil;
import org.videolan.vlc.util.KextensionsKt;

public class HeaderMediaListActivityBindingImpl extends HeaderMediaListActivityBinding {
    private static final ViewDataBinding.IncludedLayouts sIncludes = null;
    private static final SparseIntArray sViewsWithIds;
    private long mDirtyFlags;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        sViewsWithIds = sparseIntArray;
        sparseIntArray.put(R.id.appbar, 7);
        sparseIntArray.put(R.id.header_layout, 8);
        sparseIntArray.put(R.id.backgroundView, 9);
        sparseIntArray.put(R.id.barrier, 10);
        sparseIntArray.put(R.id.play_btn, 11);
        sparseIntArray.put(R.id.btn_add_playlist, 12);
        sparseIntArray.put(R.id.btn_shuffle, 13);
        sparseIntArray.put(R.id.btn_favorite, 14);
        sparseIntArray.put(R.id.main_toolbar, 15);
        sparseIntArray.put(R.id.swipeLayout, 16);
        sparseIntArray.put(R.id.songs, 17);
        sparseIntArray.put(R.id.browser_fast_scroller, 18);
        sparseIntArray.put(R.id.audio_player_tips, 19);
    }

    public HeaderMediaListActivityBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 20, sIncludes, sViewsWithIds));
    }

    private HeaderMediaListActivityBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[7], objArr[19], objArr[9], objArr[10], objArr[18], objArr[12], objArr[14], objArr[13], objArr[1], objArr[0], objArr[6], objArr[8], objArr[4], objArr[3], objArr[15], objArr[11], objArr[2], objArr[5], objArr[17], objArr[16]);
        this.mDirtyFlags = -1;
        this.collapsingToolbar.setTag((Object) null);
        this.coordinator.setTag((Object) null);
        this.duration.setTag((Object) null);
        this.headerListArtist.setTag((Object) null);
        this.headerListTitle.setTag((Object) null);
        this.playlistCover.setTag((Object) null);
        this.releaseDate.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 32;
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
        if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.topmargin == i) {
            setTopmargin((Integer) obj);
        } else if (BR.totalDuration == i) {
            setTotalDuration((Long) obj);
        } else if (BR.playlist == i) {
            setPlaylist((MediaLibraryItem) obj);
        } else if (BR.releaseYear != i) {
            return false;
        } else {
            setReleaseYear((String) obj);
        }
        return true;
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setTopmargin(Integer num) {
        this.mTopmargin = num;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.topmargin);
        super.requestRebind();
    }

    public void setTotalDuration(Long l) {
        this.mTotalDuration = l;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.totalDuration);
        super.requestRebind();
    }

    public void setPlaylist(MediaLibraryItem mediaLibraryItem) {
        this.mPlaylist = mediaLibraryItem;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.playlist);
        super.requestRebind();
    }

    public void setReleaseYear(String str) {
        this.mReleaseYear = str;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.releaseYear);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        String str2;
        int i;
        String str3;
        String str4;
        String str5;
        String str6;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        BitmapDrawable bitmapDrawable = this.mCover;
        Integer num = this.mTopmargin;
        Long l = this.mTotalDuration;
        MediaLibraryItem mediaLibraryItem = this.mPlaylist;
        String str7 = this.mReleaseYear;
        int safeUnbox = (j & 34) != 0 ? ViewDataBinding.safeUnbox(num) : 0;
        String str8 = null;
        if ((j & 36) != 0) {
            long safeUnbox2 = ViewDataBinding.safeUnbox(l);
            str2 = TalkbackUtil.INSTANCE.getDuration(getRoot().getContext(), safeUnbox2);
            str = Tools.millisToTextLarge(safeUnbox2);
        } else {
            str2 = null;
            str = null;
        }
        long j2 = j & 40;
        if (j2 != 0) {
            if (mediaLibraryItem != null) {
                str5 = mediaLibraryItem.getDescription();
                str6 = mediaLibraryItem.getTitle();
            } else {
                str6 = null;
                str5 = null;
            }
            str4 = TalkbackUtil.INSTANCE.getArtist(getRoot().getContext(), str5);
            boolean isEmpty = TextUtils.isEmpty(str5);
            String albumTitle = TalkbackUtil.INSTANCE.getAlbumTitle(getRoot().getContext(), str6);
            if (j2 != 0) {
                j |= isEmpty ? 128 : 64;
            }
            int i2 = isEmpty ? 8 : 0;
            str3 = albumTitle;
            i = i2;
        } else {
            str6 = null;
            str5 = null;
            str4 = null;
            str3 = null;
            i = 0;
        }
        long j3 = 48 & j;
        if (j3 != 0) {
            str8 = TalkbackUtil.INSTANCE.getReleaseDate(getRoot().getContext(), str7);
        }
        String str9 = str8;
        if ((j & 40) != 0) {
            this.collapsingToolbar.setTitle(str6);
            TextViewBindingAdapter.setText(this.headerListArtist, str5);
            this.headerListArtist.setVisibility(i);
            TextViewBindingAdapter.setText(this.headerListTitle, str6);
            if (getBuildSdkInt() >= 4) {
                this.headerListArtist.setContentDescription(str4);
                this.headerListTitle.setContentDescription(str3);
            }
        }
        if ((36 & j) != 0) {
            TextViewBindingAdapter.setText(this.duration, str);
            if (getBuildSdkInt() >= 4) {
                this.duration.setContentDescription(str2);
            }
        }
        if ((34 & j) != 0) {
            KextensionsKt.setLayoutMarginTop(this.playlistCover, safeUnbox);
        }
        if ((j & 33) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.playlistCover, bitmapDrawable);
        }
        if (j3 != 0) {
            TextViewBindingAdapter.setText(this.releaseDate, str7);
            if (getBuildSdkInt() >= 4) {
                this.releaseDate.setContentDescription(str9);
            }
        }
    }
}
