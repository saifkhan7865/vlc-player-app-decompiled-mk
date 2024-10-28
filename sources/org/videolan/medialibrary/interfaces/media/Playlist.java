package org.videolan.medialibrary.interfaces.media;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.media.MediaLibraryItem;

public abstract class Playlist extends MediaLibraryItem {
    public static Parcelable.Creator<Playlist> CREATOR = new Parcelable.Creator<Playlist>() {
        public Playlist createFromParcel(Parcel parcel) {
            return MLServiceLocator.getAbstractPlaylist(parcel);
        }

        public Playlist[] newArray(int i) {
            return new Playlist[i];
        }
    };
    protected long mDuration;
    protected long mNbAudio;
    protected long mNbDurationUnknown;
    protected long mNbUnknown;
    protected long mNbVideo;
    protected int mTracksCount;

    public enum Type {
        All,
        Audio,
        Video,
        AudioOnly,
        VideoOnly
    }

    public abstract boolean add(long j, int i);

    public abstract boolean append(long j);

    public abstract boolean append(List<Long> list);

    public abstract boolean append(long[] jArr);

    public abstract boolean delete();

    public int getItemType() {
        return 16;
    }

    public abstract MediaWrapper[] getPagedTracks(int i, int i2, boolean z, boolean z2);

    public abstract int getRealTracksCount(boolean z, boolean z2);

    public abstract MediaWrapper[] getTracks(boolean z, boolean z2);

    public abstract boolean move(int i, int i2);

    public abstract boolean remove(int i);

    public abstract MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3);

    public abstract int searchTracksCount(String str);

    public boolean setFavorite(boolean z) {
        return false;
    }

    protected Playlist(long j, String str, int i, long j2, int i2, int i3, int i4, int i5, boolean z) {
        super(j, str);
        this.mTracksCount = i;
        this.mDuration = j2;
        this.mNbVideo = (long) i2;
        this.mNbAudio = (long) i3;
        this.mNbUnknown = (long) i4;
        this.mNbDurationUnknown = (long) i5;
        this.mFavorite = z;
    }

    public int getTracksCount() {
        return this.mTracksCount;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public long getNbVideo() {
        return this.mNbVideo;
    }

    public long getNbAudio() {
        return this.mNbAudio;
    }

    public long getNbUnknown() {
        return this.mNbUnknown;
    }

    public long getNbDurationUnknown() {
        return this.mNbDurationUnknown;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mTracksCount);
        parcel.writeInt(this.mFavorite ? 1 : 0);
    }

    public Playlist(Parcel parcel) {
        super(parcel);
        this.mTracksCount = parcel.readInt();
        this.mFavorite = parcel.readInt() != 1 ? false : true;
    }
}
