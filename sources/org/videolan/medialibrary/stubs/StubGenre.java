package org.videolan.medialibrary.stubs;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class StubGenre extends Genre {
    private StubDataSource dt = StubDataSource.getInstance();

    public boolean setFavorite(boolean z) {
        return true;
    }

    public StubGenre(long j, String str, int i, int i2, boolean z) {
        super(j, str, i, i2, z);
    }

    public StubGenre(Parcel parcel) {
        super(parcel);
    }

    public Album[] getAlbums(int i, boolean z, boolean z2, boolean z3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getGenre().equals(getTitle())) {
                Iterator<Album> it2 = this.dt.mAlbums.iterator();
                while (it2.hasNext()) {
                    Album next2 = it2.next();
                    if (next2.getTitle().equals(next.getAlbum()) && !arrayList.contains(next2)) {
                        arrayList.add(next2);
                    }
                }
            }
        }
        return this.dt.sortAlbum(arrayList, i, z);
    }

    public Album[] getPagedAlbums(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        return (Album[]) this.dt.secureSublist(new ArrayList(Arrays.asList(getAlbums(i, z, z2, z3))), i3, i2 + i3).toArray(new Album[0]);
    }

    public int getAlbumsCount() {
        return getAlbums().length;
    }

    public Album[] searchAlbums(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getGenre().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                Iterator<Album> it2 = this.dt.mAlbums.iterator();
                while (it2.hasNext()) {
                    Album next2 = it2.next();
                    if (next2.getTitle().equals(next.getAlbum()) && !arrayList.contains(next2)) {
                        arrayList.add(next2);
                    }
                }
            }
        }
        return (Album[]) this.dt.secureSublist(new ArrayList(Arrays.asList(this.dt.sortAlbum(arrayList, i, z))), i3, i2 + i3).toArray(new Album[0]);
    }

    public int searchAlbumsCount(String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getGenre().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                Iterator<Album> it2 = this.dt.mAlbums.iterator();
                while (it2.hasNext()) {
                    Album next2 = it2.next();
                    if (next2.getTitle().equals(next.getAlbum()) && !arrayList.contains(next2)) {
                        arrayList.add(next2);
                    }
                }
            }
        }
        return arrayList.size();
    }

    public Artist[] getArtists(int i, boolean z, boolean z2, boolean z3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getGenre().equals(getTitle())) {
                Iterator<Artist> it2 = this.dt.mArtists.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Artist next2 = it2.next();
                    if ((next2.getTitle().equals(next.getArtist()) || next2.getTitle().equals(next.getAlbumArtist())) && !arrayList.contains(next2)) {
                        arrayList.add(next2);
                        break;
                    }
                }
            }
        }
        return this.dt.sortArtist(arrayList, i, z);
    }

    public MediaWrapper[] getTracks(boolean z, int i, boolean z2, boolean z3, boolean z4) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getGenre().equals(getTitle())) {
                arrayList.add(next);
            }
        }
        return this.dt.sortMedia(arrayList, i, z2);
    }

    public MediaWrapper[] getPagedTracks(boolean z, int i, boolean z2, boolean z3, boolean z4, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (next.getGenre().equals(getTitle())) {
                arrayList.add(next);
            }
        }
        StubDataSource stubDataSource = this.dt;
        return stubDataSource.sortMedia(stubDataSource.secureSublist(arrayList, i3, i2 + i3), i, z2);
    }

    public int getTracksCount() {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().getGenre().equals(getTitle())) {
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
            if (next.getGenre().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
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
            if (next.getGenre().equals(getTitle()) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }
}
