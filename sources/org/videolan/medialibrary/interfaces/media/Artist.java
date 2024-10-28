package org.videolan.medialibrary.interfaces.media;

import android.os.Parcel;
import android.os.Parcelable;
import org.videolan.libvlc.util.VLCUtil;
import org.videolan.medialibrary.MLContextTools;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.R;
import org.videolan.medialibrary.media.MediaLibraryItem;

public abstract class Artist extends MediaLibraryItem {
    public static Parcelable.Creator<Artist> CREATOR = new Parcelable.Creator<Artist>() {
        public Artist createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractArtist(parcel);
        }

        public Artist[] newArray(int i) {
            return new Artist[i];
        }
    };
    private int albumsCount;
    private String artworkMrl;
    private String musicBrainzId;
    private int presentTracksCount;
    private String shortBio;
    private int tracksCount;

    public static class SpecialRes {
        public static String UNKNOWN_ARTIST = MLContextTools.getInstance().getContext().getString(R.string.unknown_artist);
        public static String VARIOUS_ARTISTS = MLContextTools.getInstance().getContext().getString(R.string.various_artists);
    }

    public abstract Album[] getAlbums(int i, boolean z, boolean z2, boolean z3);

    public int getItemType() {
        return 4;
    }

    public abstract Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract MediaWrapper[] getPagedTracks(int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract MediaWrapper[] getTracks(int i, boolean z, boolean z2, boolean z3);

    public abstract Album[] searchAlbums(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int searchAlbumsCount(String str);

    public abstract MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int searchTracksCount(String str);

    public Artist(long j, String str, String str2, String str3, String str4, int i, int i2, int i3, boolean z) {
        super(j, str);
        this.shortBio = str2;
        this.artworkMrl = str3 != null ? VLCUtil.UriFromMrl(str3).getPath() : null;
        this.musicBrainzId = str4;
        this.tracksCount = i2;
        this.albumsCount = i;
        this.presentTracksCount = i3;
        this.mFavorite = z;
        if (j == 1) {
            this.mTitle = SpecialRes.UNKNOWN_ARTIST;
        } else if (j == 2) {
            this.mTitle = SpecialRes.VARIOUS_ARTISTS;
        }
    }

    public String getShortBio() {
        return this.shortBio;
    }

    public String getArtworkMrl() {
        return this.artworkMrl;
    }

    public String getMusicBrainzId() {
        return this.musicBrainzId;
    }

    public void setShortBio(String str) {
        this.shortBio = str;
    }

    public void setArtworkMrl(String str) {
        this.artworkMrl = str;
    }

    public Album[] getAlbums() {
        return getAlbums(1, false, true, false);
    }

    public int getTracksCount() {
        return this.tracksCount;
    }

    public int getAlbumsCount() {
        return this.albumsCount;
    }

    public int getPresentTracksCount() {
        return this.presentTracksCount;
    }

    public MediaWrapper[] getTracks() {
        return getTracks(9, false, true, false);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.shortBio);
        parcel.writeString(this.artworkMrl);
        parcel.writeString(this.musicBrainzId);
        parcel.writeInt(this.tracksCount);
        parcel.writeInt(this.albumsCount);
        parcel.writeInt(this.mFavorite ? 1 : 0);
    }

    public Artist(Parcel parcel) {
        super(parcel);
        this.shortBio = parcel.readString();
        this.artworkMrl = parcel.readString();
        this.musicBrainzId = parcel.readString();
        this.tracksCount = parcel.readInt();
        this.albumsCount = parcel.readInt();
        this.mFavorite = parcel.readInt() != 1 ? false : true;
    }
}
