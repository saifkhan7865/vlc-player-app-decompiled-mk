package org.videolan.medialibrary.stubs;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.util.SparseArray;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Bookmark;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class StubMediaWrapper extends MediaWrapper {
    private SparseArray<Long> mMetaLong = new SparseArray<>();
    private SparseArray<String> mMetaString = new SparseArray<>();

    public Bookmark addBookmark(long j) {
        return null;
    }

    public long getPlayCount() {
        return 1;
    }

    public boolean markAsPlayed() {
        return true;
    }

    public boolean removeAllBookmarks() {
        return false;
    }

    public boolean removeBookmark(long j) {
        return false;
    }

    public boolean removeFromHistory() {
        return true;
    }

    public void removeThumbnail() {
    }

    public void requestBanner(int i, float f) {
    }

    public void requestThumbnail(int i, float f) {
    }

    public boolean setFavorite(boolean z) {
        return true;
    }

    public boolean setPlayCount(long j) {
        return true;
    }

    public void setThumbnail(String str) {
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StubMediaWrapper(long j, String str, long j2, float f, long j3, int i, String str2, String str3, String str4, String str5, String str6, String str7, int i2, int i3, String str8, int i4, int i5, int i6, int i7, long j4, long j5, boolean z, boolean z2, int i8, boolean z3, long j6) {
        super(j, str, j2, f, j3, i, str2, str3, str4, str5, str6, str7, i2, i3, str8, i4, i5, i6, i7, j4, j5, z, z2, i8, z3, j6);
        int i9 = i;
        String str9 = str4;
        String str10 = str6;
        StringBuilder sb = new StringBuilder();
        boolean z4 = true;
        if (i9 == 1) {
            boolean z5 = !str9.equals(Artist.SpecialRes.VARIOUS_ARTISTS) && !str9.equals(Artist.SpecialRes.UNKNOWN_ARTIST) && !str4.isEmpty();
            z4 = (str10.equals(Album.SpecialRes.UNKNOWN_ALBUM) || str4.isEmpty()) ? false : z4;
            if (z5) {
                sb.append(str9);
                if (z4) {
                    sb.append(" - ");
                }
            }
            if (z4) {
                sb.append(str10);
            }
        } else if (i9 == 0) {
            Tools.setMediaDescription(this);
        }
        if (sb.length() > 0) {
            this.mDescription = sb.toString();
        } else {
            this.mDescription = "";
        }
    }

    public StubMediaWrapper(Uri uri, long j, float f, long j2, int i, Bitmap bitmap, String str, String str2, String str3, String str4, String str5, int i2, int i3, String str6, int i4, int i5, int i6, int i7, long j3, long j4, boolean z, long j5) {
        super(uri, j, f, j2, i, bitmap, str, str2, str3, str4, str5, i2, i3, str6, i4, i5, i6, i7, j3, j4, z, j5);
    }

    public StubMediaWrapper(Uri uri) {
        super(uri);
    }

    public StubMediaWrapper(IMedia iMedia) {
        super(iMedia);
    }

    public StubMediaWrapper(Parcel parcel) {
        super(parcel);
    }

    public void rename(String str) {
        this.mTitle = str;
    }

    public long getMetaLong(int i) {
        return this.mMetaLong.get(i).longValue();
    }

    public String getMetaString(int i) {
        return this.mMetaString.get(i);
    }

    public Bookmark[] getBookmarks() {
        return new Bookmark[0];
    }

    public boolean setLongMeta(int i, long j) {
        this.mMetaLong.setValueAt(i, Long.valueOf(j));
        return true;
    }

    public boolean setStringMeta(int i, String str) {
        this.mMetaString.setValueAt(i, str);
        return true;
    }
}
