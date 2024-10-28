package org.videolan.medialibrary.media;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class Storage extends MediaLibraryItem {
    public static Parcelable.Creator<Storage> CREATOR = new Parcelable.Creator<Storage>() {
        public Storage createFromParcel(Parcel parcel) {
            return new Storage(parcel);
        }

        public Storage[] newArray(int i) {
            return new Storage[i];
        }
    };
    String description;
    Uri uri;

    public int getItemType() {
        return 128;
    }

    public int getTracksCount() {
        return 1;
    }

    public boolean setFavorite(boolean z) {
        return false;
    }

    public MediaWrapper[] getTracks() {
        return Medialibrary.EMPTY_COLLECTION;
    }

    public Storage(Uri uri2) {
        this.uri = uri2;
        this.mTitle = uri2.getLastPathSegment();
    }

    public String getName() {
        return Uri.decode(this.mTitle);
    }

    public void setName(String str) {
        this.mTitle = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getDescription() {
        return this.description;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.uri, i);
        parcel.writeString(this.description);
    }

    private Storage(Parcel parcel) {
        super(parcel);
        this.uri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.description = parcel.readString();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Storage) && TextUtils.equals(this.mTitle, ((Storage) obj).getTitle());
    }
}
