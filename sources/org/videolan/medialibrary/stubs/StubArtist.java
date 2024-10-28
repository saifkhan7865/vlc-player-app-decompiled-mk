package org.videolan.medialibrary.stubs;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class StubArtist extends Artist {
    private StubDataSource dt = StubDataSource.getInstance();

    public boolean setFavorite(boolean z) {
        return true;
    }

    public StubArtist(long j, String str, String str2, String str3, String str4, int i, int i2, int i3, boolean z) {
        super(j, str, str2, str3, str4, i, i2, i3, z);
    }

    public StubArtist(Parcel parcel) {
        super(parcel);
    }

    private ArrayList<String> getAlbumNames() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (MediaWrapper mediaWrapper : getTracks()) {
            if (!arrayList.contains(mediaWrapper.getAlbum())) {
                arrayList.add(mediaWrapper.getAlbum());
            }
        }
        return arrayList;
    }

    public Album[] getAlbums(int i, boolean z, boolean z2, boolean z3) {
        ArrayList<String> albumNames = getAlbumNames();
        ArrayList arrayList = new ArrayList();
        Iterator<Album> it = this.dt.mAlbums.iterator();
        while (it.hasNext()) {
            Album next = it.next();
            if (albumNames.contains(next.getTitle()) && next.retrieveAlbumArtist().getTitle().equals(getTitle())) {
                arrayList.add(next);
            }
        }
        return this.dt.sortAlbum(arrayList, i, z);
    }

    public Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return (Album[]) new ArrayList(Arrays.asList(getAlbums(i, z, z2, z3))).toArray(new Album[0]);
    }

    public Album[] searchAlbums(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList<String> albumNames = getAlbumNames();
        ArrayList arrayList = new ArrayList();
        Iterator<Album> it = this.dt.mAlbums.iterator();
        while (it.hasNext()) {
            Album next = it.next();
            if (albumNames.contains(next.getTitle()) && next.retrieveAlbumArtist().getTitle().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortAlbum(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public int searchAlbumsCount(String str) {
        ArrayList<String> albumNames = getAlbumNames();
        Iterator<Album> it = this.dt.mAlbums.iterator();
        int i = 0;
        while (it.hasNext()) {
            Album next = it.next();
            if (albumNames.contains(next.getDescription()) && next.retrieveAlbumArtist().getTitle().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public int searchTracksCount(String str) {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getArtist().equals(getTitle()) && next.getAlbumArtist().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }

    public MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getArtist().equals(getTitle()) && next.getAlbumArtist().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public MediaWrapper[] getTracks(int i, boolean z, boolean z2, boolean z3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getArtist().equals(getTitle()) || next.getAlbumArtist().equals(getTitle())) {
                arrayList.add(next);
            }
        }
        return this.dt.sortMedia(arrayList, i, z);
    }

    public MediaWrapper[] getPagedTracks(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getArtist().equals(getTitle()) || next.getAlbumArtist().equals(getTitle())) {
                arrayList.add(next);
            }
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public int getTracksCount() {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getArtist().equals(getTitle()) && next.getAlbumArtist().equals(getTitle())) {
                i++;
            }
        }
        return i;
    }
}
