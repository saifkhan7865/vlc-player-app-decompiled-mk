package org.videolan.medialibrary.stubs;

import android.os.Parcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;

public class StubPlaylist extends Playlist {
    private StubDataSource dt = StubDataSource.getInstance();
    private ArrayList<Long> mTracksId = new ArrayList<>();

    public StubPlaylist(long j, String str, int i, long j2, int i2, int i3, int i4, int i5, boolean z) {
        super(j, str, i, j2, i2, i3, i4, i5, z);
    }

    public StubPlaylist(Parcel parcel) {
        super(parcel);
    }

    public MediaWrapper[] getTracks() {
        return getTracks(true, false);
    }

    public MediaWrapper[] getTracks(boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (this.mTracksId.contains(Long.valueOf(next.getId()))) {
                arrayList.add(next);
            }
        }
        return (MediaWrapper[]) arrayList.toArray(new MediaWrapper[0]);
    }

    public MediaWrapper[] getPagedTracks(int i, int i2, boolean z, boolean z2) {
        return (MediaWrapper[]) this.dt.secureSublist(new ArrayList(Arrays.asList(getTracks())), i2, i + i2).toArray(new MediaWrapper[0]);
    }

    public int getRealTracksCount(boolean z, boolean z2) {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (this.mTracksId.contains(Long.valueOf(it.next().getId()))) {
                i++;
            }
        }
        return i;
    }

    public boolean append(long j) {
        this.mTracksId.add(Long.valueOf(j));
        this.mTracksCount++;
        return true;
    }

    public boolean append(long[] jArr) {
        for (long append : jArr) {
            append(append);
        }
        return true;
    }

    public boolean append(List<Long> list) {
        for (Long longValue : list) {
            append(longValue.longValue());
        }
        return true;
    }

    public boolean add(long j, int i) {
        this.mTracksId.add(i, Long.valueOf(j));
        return true;
    }

    public boolean move(int i, int i2) {
        Long l = this.mTracksId.get(i);
        l.longValue();
        this.mTracksId.remove(i);
        this.mTracksId.add(i2, l);
        return true;
    }

    public boolean remove(int i) {
        this.mTracksId.remove(i);
        this.mTracksCount--;
        return true;
    }

    public boolean delete() {
        for (int i = 0; i < this.dt.mPlaylists.size(); i++) {
            if (this.dt.mPlaylists.get(i).getId() == getId()) {
                this.dt.mPlaylists.remove(i);
                return true;
            }
        }
        return false;
    }

    public MediaWrapper[] searchTracks(String str, int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (this.mTracksId.contains(Long.valueOf(next.getId())) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                arrayList.add(next);
            }
        }
        return (MediaWrapper[]) this.dt.secureSublist(new ArrayList(Arrays.asList(this.dt.sortMedia(arrayList, i, z))), i3, i2 + i3).toArray(new MediaWrapper[0]);
    }

    public int searchTracksCount(String str) {
        Iterator<MediaWrapper> it = this.dt.mAudioMediaWrappers.iterator();
        int i = 0;
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (this.mTracksId.contains(Long.valueOf(next.getId())) && Tools.hasSubString(next.getTitle(), str).booleanValue()) {
                i++;
            }
        }
        return i;
    }
}
