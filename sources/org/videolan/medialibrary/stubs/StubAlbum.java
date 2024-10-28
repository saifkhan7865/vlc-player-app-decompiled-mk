package org.videolan.medialibrary.stubs;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Iterator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class StubAlbum extends Album {
    private StubDataSource dt = StubDataSource.getInstance();

    public boolean setFavorite(boolean z) {
        return true;
    }

    public StubAlbum(long j, String str, int i, String str2, String str3, long j2, int i2, int i3, long j3, boolean z) {
        super(j, str, i, str2, str3, j2, i2, i3, j3, z);
    }

    public StubAlbum(Parcel parcel) {
        super(parcel);
    }

    public int getRealTracksCount() {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getAlbum().equals(getTitle())) {
                i++;
            }
        }
        return i;
    }

    public Artist retrieveAlbumArtist() {
        return Medialibrary.getInstance().getArtist(this.albumArtistId);
    }

    private ArrayList<MediaWrapper> getAlbumTracks() {
        ArrayList<MediaWrapper> arrayList = new ArrayList<>();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getAlbum().equals(getTitle()) && next.getAlbumArtist().equals(retrieveAlbumArtist().getTitle())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public MediaWrapper[] getTracks(int i, boolean z, boolean z2, boolean z3) {
        return this.dt.sortMedia(getAlbumTracks(), i, z);
    }

    public MediaWrapper[] getPagedTracks(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(getAlbumTracks(), i3, i2 + i3), i, z);
    }

    public MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getAlbum().equals(getTitle()) || Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z);
    }

    public int searchTracksCount(String str) {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getAlbum().equals(getTitle()) || Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }
}
