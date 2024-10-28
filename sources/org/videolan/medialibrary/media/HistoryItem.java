package org.videolan.medialibrary.media;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.Date;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class HistoryItem extends MediaLibraryItem {
    public static Parcelable.Creator<HistoryItem> CREATOR = new Parcelable.Creator<HistoryItem>() {
        public HistoryItem createFromParcel(Parcel parcel) {
            return new HistoryItem(parcel);
        }

        public HistoryItem[] newArray(int i) {
            return new HistoryItem[i];
        }
    };
    private long insertionDate;
    private String mrl;
    private String title;

    public int getItemType() {
        return 512;
    }

    public int getTracksCount() {
        return 1;
    }

    public boolean setFavorite(boolean z) {
        return false;
    }

    public HistoryItem(String str, String str2, long j, boolean z) {
        this.mrl = str;
        this.title = str2;
        this.mFavorite = z;
        this.insertionDate = j;
    }

    public MediaWrapper getMedia() {
        MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(Uri.parse(this.mrl));
        abstractMediaWrapper.setTitle(this.title);
        abstractMediaWrapper.setType(6);
        return abstractMediaWrapper;
    }

    public MediaWrapper[] getTracks() {
        return new MediaWrapper[]{getMedia()};
    }

    public String getMrl() {
        return this.mrl;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isFavorite() {
        return this.mFavorite;
    }

    public long getInsertionDate() {
        return this.insertionDate;
    }

    public Date getDate() {
        return new Date(this.insertionDate);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mrl);
        parcel.writeByte(this.mFavorite ? (byte) 1 : 0);
        parcel.writeLong(this.insertionDate);
    }

    private HistoryItem(Parcel parcel) {
        super(parcel);
        this.mrl = parcel.readString();
        this.mFavorite = parcel.readByte() != 0;
        this.insertionDate = parcel.readLong();
    }
}
