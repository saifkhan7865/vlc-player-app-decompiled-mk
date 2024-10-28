package org.videolan.vlc.databinding;

import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.session.PlaybackStateCompat;
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

public class InfoActivityBindingImpl extends InfoActivityBinding {
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
        sparseIntArray.put(R.id.appbar, 17);
        sparseIntArray.put(R.id.info_subtitles, 18);
        sparseIntArray.put(R.id.main_toolbar, 19);
        sparseIntArray.put(R.id.container, 20);
        sparseIntArray.put(R.id.guideline9, 21);
        sparseIntArray.put(R.id.guideline10, 22);
        sparseIntArray.put(R.id.ariane, 23);
        sparseIntArray.put(R.id.file_size_views, 24);
        sparseIntArray.put(R.id.fab, 25);
        sparseIntArray.put(R.id.audio_player_tips, 26);
    }

    public InfoActivityBindingImpl(DataBindingComponent dataBindingComponent, View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 27, sIncludes, sViewsWithIds));
    }

    private InfoActivityBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, objArr[17], objArr[23], objArr[26], objArr[1], objArr[20], objArr[0], objArr[6], objArr[5], objArr[13], objArr[14], objArr[15], objArr[25], objArr[24], objArr[22], objArr[21], objArr[4], objArr[18], objArr[7], objArr[8], objArr[9], objArr[16], objArr[19], objArr[3], objArr[2], objArr[10], objArr[11], objArr[12]);
        this.mDirtyFlags = -1;
        this.collapsingToolbar.setTag((Object) null);
        this.coordinator.setTag((Object) null);
        this.directoryNotScannedButton.setTag((Object) null);
        this.directoryNotScannedText.setTag((Object) null);
        this.extraIcon.setTag((Object) null);
        this.extraTitle.setTag((Object) null);
        this.extraValue.setTag((Object) null);
        this.imageProgress.setTag((Object) null);
        this.lengthIcon.setTag((Object) null);
        this.lengthTitle.setTag((Object) null);
        this.lengthValue.setTag((Object) null);
        this.list.setTag((Object) null);
        this.mlItemResolution.setTag((Object) null);
        this.playlistCover.setTag((Object) null);
        this.sizeIcon.setTag((Object) null);
        this.sizeTitle.setTag((Object) null);
        this.sizeValue.setTag((Object) null);
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
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
        } else if (BR.extraValueText == i) {
            setExtraValueText((String) obj);
        } else if (BR.extraTitleText == i) {
            setExtraTitleText((String) obj);
        } else if (BR.sizeTitleText == i) {
            setSizeTitleText((String) obj);
        } else if (BR.length == i) {
            setLength((Long) obj);
        } else if (BR.resolution == i) {
            setResolution((String) obj);
        } else if (BR.progress == i) {
            setProgress(((Integer) obj).intValue());
        } else if (BR.path == i) {
            setPath((String) obj);
        } else if (BR.cover == i) {
            setCover((BitmapDrawable) obj);
        } else if (BR.sizeValueText == i) {
            setSizeValueText((String) obj);
        } else if (BR.sizeValueContentDescription == i) {
            setSizeValueContentDescription((String) obj);
        } else if (BR.scanned != i) {
            return false;
        } else {
            setScanned(((Boolean) obj).booleanValue());
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

    public void setExtraValueText(String str) {
        this.mExtraValueText = str;
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        notifyPropertyChanged(BR.extraValueText);
        super.requestRebind();
    }

    public void setExtraTitleText(String str) {
        this.mExtraTitleText = str;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(BR.extraTitleText);
        super.requestRebind();
    }

    public void setSizeTitleText(String str) {
        this.mSizeTitleText = str;
        synchronized (this) {
            this.mDirtyFlags |= 8;
        }
        notifyPropertyChanged(BR.sizeTitleText);
        super.requestRebind();
    }

    public void setLength(Long l) {
        this.mLength = l;
        synchronized (this) {
            this.mDirtyFlags |= 16;
        }
        notifyPropertyChanged(BR.length);
        super.requestRebind();
    }

    public void setResolution(String str) {
        this.mResolution = str;
        synchronized (this) {
            this.mDirtyFlags |= 32;
        }
        notifyPropertyChanged(BR.resolution);
        super.requestRebind();
    }

    public void setProgress(int i) {
        this.mProgress = i;
        synchronized (this) {
            this.mDirtyFlags |= 64;
        }
        notifyPropertyChanged(BR.progress);
        super.requestRebind();
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public void setCover(BitmapDrawable bitmapDrawable) {
        this.mCover = bitmapDrawable;
        synchronized (this) {
            this.mDirtyFlags |= 256;
        }
        notifyPropertyChanged(BR.cover);
        super.requestRebind();
    }

    public void setSizeValueText(String str) {
        this.mSizeValueText = str;
        synchronized (this) {
            this.mDirtyFlags |= 512;
        }
        notifyPropertyChanged(BR.sizeValueText);
        super.requestRebind();
    }

    public void setSizeValueContentDescription(String str) {
        this.mSizeValueContentDescription = str;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        }
        notifyPropertyChanged(BR.sizeValueContentDescription);
        super.requestRebind();
    }

    public void setScanned(boolean z) {
        this.mScanned = z;
        synchronized (this) {
            this.mDirtyFlags |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.scanned);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        String str;
        int i;
        int i2;
        String str2;
        int i3;
        int i4;
        String str3;
        String str4;
        String str5;
        int i5;
        String str6;
        int i6;
        BitmapDrawable bitmapDrawable;
        int i7;
        int i8;
        String str7;
        int i9;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        MediaLibraryItem mediaLibraryItem = this.mItem;
        String str8 = this.mExtraValueText;
        String str9 = this.mExtraTitleText;
        String str10 = this.mSizeTitleText;
        Long l = this.mLength;
        String str11 = this.mResolution;
        int i10 = this.mProgress;
        BitmapDrawable bitmapDrawable2 = this.mCover;
        String str12 = this.mSizeValueText;
        String str13 = this.mSizeValueContentDescription;
        boolean z = this.mScanned;
        long j2 = j & 4097;
        boolean z2 = true;
        int i11 = 0;
        if (j2 != 0) {
            if (mediaLibraryItem != null) {
                str7 = mediaLibraryItem.getTitle();
                i9 = mediaLibraryItem.getItemType();
            } else {
                i9 = 0;
                str7 = null;
            }
            boolean z3 = i9 == 32;
            if (j2 != 0) {
                j |= z3 ? 67108864 : 33554432;
            }
            i = z3 ? 0 : 4;
            str = str7;
        } else {
            i = 0;
            str = null;
        }
        long j3 = j & 4100;
        if (j3 != 0) {
            boolean isEmpty = TextUtils.isEmpty(str9);
            if (j3 != 0) {
                j |= isEmpty ? 65536 : PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
            }
            i2 = isEmpty ? 8 : 0;
        } else {
            i2 = 0;
        }
        long j4 = j & 4104;
        if (j4 != 0) {
            boolean isEmpty2 = TextUtils.isEmpty(str10);
            if (j4 != 0) {
                j |= isEmpty2 ? PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
            }
            str2 = str13;
            i3 = isEmpty2 ? 8 : 0;
        } else {
            str2 = str13;
            i3 = 0;
        }
        long j5 = j & 4112;
        if (j5 != 0) {
            str3 = str12;
            i4 = i3;
            long safeUnbox = ViewDataBinding.safeUnbox(l);
            boolean z4 = l == null;
            if (j5 != 0) {
                j |= z4 ? PlaybackStateCompat.ACTION_SET_PLAYBACK_SPEED : PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
            }
            String millisToTextLarge = Tools.millisToTextLarge(safeUnbox);
            long j6 = j;
            str4 = TalkbackUtil.INSTANCE.millisToString(getRoot().getContext(), safeUnbox);
            i5 = z4 ? 8 : 0;
            str5 = millisToTextLarge;
            j = j6;
        } else {
            str3 = str12;
            i4 = i3;
            i5 = 0;
            str5 = null;
            str4 = null;
        }
        long j7 = j & 4128;
        if (j7 != 0) {
            boolean z5 = str11 == null;
            if (j7 != 0) {
                j |= z5 ? 16777216 : 8388608;
            }
            int i12 = z5 ? 8 : 0;
            str6 = str10;
            i6 = i12;
        } else {
            str6 = str10;
            i6 = 0;
        }
        long j8 = j & 4160;
        if (j8 != 0) {
            if (i10 <= 0) {
                z2 = false;
            }
            if (j8 != 0) {
                j |= z2 ? PlaybackStateCompat.ACTION_SET_REPEAT_MODE : PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
            }
            int i13 = z2 ? 0 : 8;
            bitmapDrawable = bitmapDrawable2;
            i7 = i13;
        } else {
            bitmapDrawable = bitmapDrawable2;
            i7 = 0;
        }
        long j9 = j & 6144;
        if (j9 != 0) {
            if (j9 != 0) {
                j |= z ? 16384 : 8192;
            }
            if (z) {
                i11 = 8;
            }
            i8 = i11;
        } else {
            i8 = 0;
        }
        int i14 = i6;
        if ((j & 4097) != 0) {
            this.collapsingToolbar.setTitle(str);
            this.list.setVisibility(i);
        }
        if ((j & 6144) != 0) {
            this.directoryNotScannedButton.setVisibility(i8);
            this.directoryNotScannedText.setVisibility(i8);
        }
        if ((j & 4100) != 0) {
            this.extraIcon.setVisibility(i2);
            TextViewBindingAdapter.setText(this.extraTitle, str9);
        }
        if ((4098 & j) != 0) {
            TextViewBindingAdapter.setText(this.extraValue, str8);
        }
        if ((j & 4160) != 0) {
            this.imageProgress.setProgress(i10);
            this.imageProgress.setVisibility(i7);
        }
        if ((j & 4112) != 0) {
            this.lengthIcon.setVisibility(i5);
            this.lengthTitle.setVisibility(i5);
            TextViewBindingAdapter.setText(this.lengthValue, str5);
            if (getBuildSdkInt() >= 4) {
                this.lengthValue.setContentDescription(str4);
            }
        }
        if ((j & 4128) != 0) {
            TextViewBindingAdapter.setText(this.mlItemResolution, str11);
            this.mlItemResolution.setVisibility(i14);
        }
        if ((4352 & j) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.playlistCover, bitmapDrawable);
        }
        if ((j & 4104) != 0) {
            this.sizeIcon.setVisibility(i4);
            TextViewBindingAdapter.setText(this.sizeTitle, str6);
        }
        if ((4608 & j) != 0) {
            TextViewBindingAdapter.setText(this.sizeValue, str3);
        }
        if ((j & 5120) != 0 && getBuildSdkInt() >= 4) {
            this.sizeValue.setContentDescription(str2);
        }
    }
}
