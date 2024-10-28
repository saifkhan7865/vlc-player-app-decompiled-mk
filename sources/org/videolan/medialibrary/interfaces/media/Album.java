package org.videolan.medialibrary.interfaces.media;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.videolan.libvlc.util.VLCUtil;
import org.videolan.medialibrary.MLContextTools;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.R;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.media.MediaLibraryItem;

public abstract class Album extends MediaLibraryItem {
    public static Parcelable.Creator<Album> CREATOR = new Parcelable.Creator<Album>() {
        public Album createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractAlbum(parcel);
        }

        public Album[] newArray(int i) {
            return new Album[i];
        }
    };
    protected String albumArtist;
    protected long albumArtistId;
    protected String artworkMrl;
    protected long duration;
    public int mPresentTracksCount;
    protected int mTracksCount;
    protected int releaseYear;

    public static class SpecialRes {
        public static String UNKNOWN_ALBUM = MLContextTools.getInstance().getContext().getString(R.string.unknown_album);
    }

    public int getItemType() {
        return 2;
    }

    public abstract MediaWrapper[] getPagedTracks(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int getRealTracksCount();

    public abstract MediaWrapper[] getTracks(int i, boolean z, boolean z2, boolean z3);

    public abstract Artist retrieveAlbumArtist();

    public abstract MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int searchTracksCount(String str);

    public Album(long j, String str, int i, String str2, String str3, long j2, int i2, int i3, long j3, boolean z) {
        super(j, str);
        this.releaseYear = i;
        String str4 = null;
        this.artworkMrl = str2 != null ? VLCUtil.UriFromMrl(str2).getPath() : null;
        this.albumArtist = str3 != null ? str3.trim() : str4;
        this.albumArtistId = j2;
        this.mTracksCount = i2;
        this.mPresentTracksCount = i3;
        this.duration = j3;
        this.mFavorite = z;
        if (TextUtils.isEmpty(str)) {
            this.mTitle = SpecialRes.UNKNOWN_ALBUM;
        }
        if (j2 == 1) {
            this.albumArtist = Artist.SpecialRes.UNKNOWN_ARTIST;
        } else if (j2 == 2) {
            this.albumArtist = Artist.SpecialRes.VARIOUS_ARTISTS;
        }
    }

    protected Album(Parcel parcel) {
        super(parcel);
        this.releaseYear = parcel.readInt();
        this.artworkMrl = parcel.readString();
        this.albumArtist = parcel.readString();
        this.albumArtistId = parcel.readLong();
        this.mTracksCount = parcel.readInt();
        this.mPresentTracksCount = parcel.readInt();
        this.duration = parcel.readLong();
        this.mFavorite = parcel.readInt() != 1 ? false : true;
        this.mPresentTracksCount = parcel.readInt();
    }

    public long getId() {
        return this.mId;
    }

    public String getDescription() {
        return this.albumArtist;
    }

    public int getReleaseYear() {
        return this.releaseYear;
    }

    public String getArtworkMrl() {
        return this.artworkMrl;
    }

    public int getTracksCount() {
        return this.mTracksCount;
    }

    public long getDuration() {
        return this.duration;
    }

    public MediaWrapper[] getTracks() {
        return getTracks(0, false, true, false);
    }

    public String getAlbumArtist() {
        return this.albumArtist;
    }

    public int getPresentTracksCount() {
        return this.mPresentTracksCount;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.releaseYear);
        parcel.writeString(this.artworkMrl);
        parcel.writeString(this.albumArtist);
        parcel.writeLong(this.albumArtistId);
        parcel.writeInt(this.mTracksCount);
        parcel.writeInt(this.mPresentTracksCount);
        parcel.writeLong(this.duration);
        parcel.writeInt(this.mFavorite ? 1 : 0);
        parcel.writeInt(this.mTracksCount);
    }
}
