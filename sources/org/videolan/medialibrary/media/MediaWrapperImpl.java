package org.videolan.medialibrary.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import java.util.Locale;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class MediaWrapperImpl extends MediaWrapper {
    public static final String TAG = "VLC/MediaWrapperImpl";

    private native Bookmark nativeAddBookmark(Medialibrary medialibrary, long j, long j2);

    private native Bookmark[] nativeGetBookmarks(Medialibrary medialibrary, long j);

    private native long nativeGetMediaLongMetadata(Medialibrary medialibrary, long j, int i);

    private native long nativeGetMediaPlayCount(Medialibrary medialibrary, long j);

    private native String nativeGetMediaStringMetadata(Medialibrary medialibrary, long j, int i);

    private native boolean nativeMarkAsPlayed(Medialibrary medialibrary, long j);

    private native boolean nativeRemoveAllBookmarks(Medialibrary medialibrary, long j);

    private native boolean nativeRemoveBookmark(Medialibrary medialibrary, long j, long j2);

    private native boolean nativeRemoveFromHistory(Medialibrary medialibrary, long j);

    private native boolean nativeRemoveMediaThumbnail(Medialibrary medialibrary, long j);

    private native void nativeRequestThumbnail(Medialibrary medialibrary, long j, int i, int i2, int i3, float f);

    private native boolean nativeSetFavorite(Medialibrary medialibrary, long j, boolean z);

    private native void nativeSetMediaLongMetadata(Medialibrary medialibrary, long j, int i, long j2);

    private native boolean nativeSetMediaPlayCount(Medialibrary medialibrary, long j, long j2);

    private native void nativeSetMediaStringMetadata(Medialibrary medialibrary, long j, int i, String str);

    private native void nativeSetMediaThumbnail(Medialibrary medialibrary, long j, String str);

    private native void nativeSetMediaTitle(Medialibrary medialibrary, long j, String str);

    public MediaWrapperImpl(long j, String str, long j2, float f, long j3, int i, String str2, String str3, String str4, String str5, String str6, String str7, int i2, int i3, String str8, int i4, int i5, int i6, int i7, long j4, long j5, boolean z, boolean z2, int i8, boolean z3, long j6) {
        super(j, str, j2, f, j3, i, str2, str3, str4, str5, str6, str7, i2, i3, str8, i4, i5, i6, i7, j4, j5, z, z2, i8, z3, j6);
    }

    public MediaWrapperImpl(Uri uri, long j, float f, long j2, int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5, int i2, int i3, String str6, int i4, int i5, int i6, int i7, long j3, long j4, boolean z, long j5) {
        super(uri, j, f, j2, i, bitmap, str, str2, str3, str4, str5, i2, i3, str6, i4, i5, i6, i7, j3, j4, z, j5);
    }

    public MediaWrapperImpl(Uri uri) {
        super(uri);
    }

    public MediaWrapperImpl(IMedia iMedia) {
        super(iMedia);
    }

    public MediaWrapperImpl(Parcel parcel) {
        super(parcel);
    }

    public void rename(String str) {
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId != 0 && instance.isInitiated()) {
            nativeSetMediaTitle(instance, this.mId, str);
        }
    }

    public boolean removeFromHistory() {
        if (this.mId == 0) {
            return false;
        }
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeRemoveFromHistory(instance, this.mId);
        }
        return false;
    }

    public void setArtist(String str) {
        this.mArtist = str;
    }

    public String getReferenceArtist() {
        return this.mAlbumArtist == null ? this.mArtist : this.mAlbumArtist;
    }

    public String getArtist() {
        return this.mArtist;
    }

    public Boolean isArtistUnknown() {
        return Boolean.valueOf(this.mArtist == null);
    }

    public String getGenre() {
        if (this.mGenre == null) {
            return null;
        }
        if (this.mGenre.length() <= 1) {
            return this.mGenre;
        }
        return Character.toUpperCase(this.mGenre.charAt(0)) + this.mGenre.substring(1).toLowerCase(Locale.getDefault());
    }

    public String getCopyright() {
        return this.mCopyright;
    }

    public String getAlbum() {
        return this.mAlbum;
    }

    public String getAlbumArtist() {
        return this.mAlbumArtist;
    }

    public Boolean isAlbumUnknown() {
        return Boolean.valueOf(this.mAlbum == null);
    }

    public int getTrackNumber() {
        return this.mTrackNumber;
    }

    public int getDiscNumber() {
        return this.mDiscNumber;
    }

    public String getRating() {
        return this.mRating;
    }

    public String getDate() {
        return this.mDate;
    }

    public String getSettings() {
        return this.mSettings;
    }

    public String getNowPlaying() {
        return this.mNowPlaying;
    }

    public String getPublisher() {
        return this.mPublisher;
    }

    public String getEncodedBy() {
        return this.mEncodedBy;
    }

    public String getTrackID() {
        return this.mTrackID;
    }

    public String getArtworkURL() {
        return this.mArtworkURL;
    }

    public boolean isThumbnailGenerated() {
        return this.mThumbnailGenerated;
    }

    public String getArtworkMrl() {
        return this.mArtworkURL;
    }

    public void setArtworkURL(String str) {
        this.mArtworkURL = str;
    }

    public long getLastModified() {
        return this.mLastModified;
    }

    public void setLastModified(long j) {
        this.mLastModified = j;
    }

    public long getSeen() {
        return this.mSeen;
    }

    public void setSeen(long j) {
        this.mSeen = j;
    }

    public void addFlags(int i) {
        this.mFlags = i | this.mFlags;
    }

    public void setFlags(int i) {
        this.mFlags = i;
    }

    public int getFlags() {
        return this.mFlags;
    }

    public boolean hasFlag(int i) {
        return (i & this.mFlags) != 0;
    }

    public void removeFlags(int i) {
        this.mFlags = (i ^ -1) & this.mFlags;
    }

    public long getMetaLong(int i) {
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId == 0 || !instance.isInitiated()) {
            return 0;
        }
        return nativeGetMediaLongMetadata(instance, this.mId, i);
    }

    public String getMetaString(int i) {
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId == 0 || !instance.isInitiated()) {
            return null;
        }
        return nativeGetMediaStringMetadata(instance, this.mId, i);
    }

    public Bookmark[] getBookmarks() {
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId == 0 || !instance.isInitiated()) {
            return null;
        }
        return nativeGetBookmarks(instance, this.mId);
    }

    public Bookmark addBookmark(long j) {
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId == 0 || !instance.isInitiated()) {
            return null;
        }
        return nativeAddBookmark(instance, this.mId, j);
    }

    public boolean removeBookmark(long j) {
        Boolean bool;
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId == 0 || !instance.isInitiated()) {
            bool = null;
        } else {
            bool = Boolean.valueOf(nativeRemoveBookmark(instance, this.mId, j));
        }
        return bool.booleanValue();
    }

    public boolean removeAllBookmarks() {
        Medialibrary instance = Medialibrary.getInstance();
        return ((this.mId == 0 || !instance.isInitiated()) ? null : Boolean.valueOf(nativeRemoveAllBookmarks(instance, this.mId))).booleanValue();
    }

    public boolean setLongMeta(int i, long j) {
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId != 0 && instance.isInitiated()) {
            nativeSetMediaLongMetadata(instance, this.mId, i, j);
        }
        return this.mId != 0;
    }

    public boolean setStringMeta(int i, String str) {
        if (this.mId == 0) {
            return false;
        }
        Medialibrary instance = Medialibrary.getInstance();
        if (this.mId == 0 || !instance.isInitiated()) {
            return true;
        }
        nativeSetMediaStringMetadata(instance, this.mId, i, str);
        return true;
    }

    public void setThumbnail(String str) {
        if (this.mId != 0) {
            this.mArtworkURL = str;
            Medialibrary instance = Medialibrary.getInstance();
            if (this.mId != 0 && instance.isInitiated()) {
                nativeSetMediaThumbnail(instance, this.mId, Tools.encodeVLCMrl(str));
            }
        }
    }

    public boolean setPlayCount(long j) {
        if (this.mId == 0) {
            return false;
        }
        return nativeSetMediaPlayCount(Medialibrary.getInstance(), this.mId, j);
    }

    public long getPlayCount() {
        if (this.mId == 0) {
            return -1;
        }
        return nativeGetMediaPlayCount(Medialibrary.getInstance(), this.mId);
    }

    public void removeThumbnail() {
        if (this.mId != 0) {
            Medialibrary instance = Medialibrary.getInstance();
            if (this.mId != 0 && instance.isInitiated()) {
                nativeRemoveMediaThumbnail(instance, this.mId);
            }
        }
    }

    public void requestThumbnail(int i, float f) {
        if (this.mId != 0) {
            Medialibrary instance = Medialibrary.getInstance();
            if (instance.isInitiated()) {
                nativeRequestThumbnail(instance, this.mId, Medialibrary.ThumbnailSizeType.Thumbnail.ordinal(), i, 0, f);
            }
        }
    }

    public void requestBanner(int i, float f) {
        if (this.mId != 0) {
            Medialibrary instance = Medialibrary.getInstance();
            if (instance.isInitiated()) {
                nativeRequestThumbnail(instance, this.mId, Medialibrary.ThumbnailSizeType.Banner.ordinal(), i, 0, f);
            }
        }
    }

    public boolean markAsPlayed() {
        if (this.mId == 0) {
            return false;
        }
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeMarkAsPlayed(instance, this.mId);
        }
        return false;
    }

    public boolean setFavorite(boolean z) {
        if (this.mId == 0) {
            return false;
        }
        Medialibrary instance = Medialibrary.getInstance();
        if (instance.isInitiated()) {
            return nativeSetFavorite(instance, this.mId, z);
        }
        return false;
    }
}
