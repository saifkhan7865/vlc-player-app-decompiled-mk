package org.videolan.medialibrary.stubs;

import android.os.Parcel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public class StubFolder extends Folder {
    private StubDataSource dt = StubDataSource.getInstance();

    public StubFolder(long j, String str, String str2, int i, boolean z) {
        super(j, str, str2, i, z);
    }

    public StubFolder(Parcel parcel) {
        super(parcel);
    }

    private boolean isParentFolder(String str, String str2) {
        if (!str2.contains(str)) {
            return false;
        }
        return new File(str2).getParent().equals(str);
    }

    public MediaWrapper[] media(int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        ArrayList<MediaWrapper> arrayList;
        ArrayList arrayList2 = new ArrayList();
        if (i == TYPE_FOLDER_VIDEO) {
            arrayList = this.dt.mVideoMediaWrappers;
        } else if (i != TYPE_FOLDER_AUDIO) {
            return null;
        } else {
            arrayList = this.dt.mAudioMediaWrappers;
        }
        Iterator<MediaWrapper> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (isParentFolder(this.mMrl, next.getUri().getPath())) {
                arrayList2.add(next);
            }
        }
        return (MediaWrapper[]) this.dt.secureSublist(new ArrayList(Arrays.asList(this.dt.sortMedia(arrayList2, i2, z))), i4, i3 + i4).toArray(new MediaWrapper[0]);
    }

    public int mediaCount(int i) {
        ArrayList<MediaWrapper> arrayList;
        int i2 = 0;
        if (i == TYPE_FOLDER_VIDEO) {
            arrayList = this.dt.mVideoMediaWrappers;
        } else {
            if (i == TYPE_FOLDER_AUDIO) {
                arrayList = this.dt.mAudioMediaWrappers;
            }
            return i2;
        }
        Iterator<MediaWrapper> it = arrayList.iterator();
        while (it.hasNext()) {
            if (isParentFolder(this.mMrl, it.next().getUri().getPath())) {
                i2++;
            }
        }
        return i2;
    }

    public Folder[] subfolders(int i, boolean z, boolean z2, boolean z3, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        Iterator<Folder> it = this.dt.mFolders.iterator();
        while (it.hasNext()) {
            Folder next = it.next();
            if (isParentFolder(this.mMrl, next.mMrl)) {
                arrayList.add(next);
            }
        }
        return (Folder[]) this.dt.secureSublist(new ArrayList(Arrays.asList(this.dt.sortFolder(arrayList, i, z))), i3, i2 + i3).toArray(new Folder[0]);
    }

    public int subfoldersCount(int i) {
        Iterator<Folder> it = this.dt.mFolders.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            if (isParentFolder(this.mMrl, it.next().mMrl)) {
                i2++;
            }
        }
        return i2;
    }

    public MediaWrapper[] searchTracks(String str, int i, int i2, boolean z, boolean z2, boolean z3, int i3, int i4) {
        ArrayList<MediaWrapper> arrayList;
        ArrayList arrayList2 = new ArrayList();
        if (i == TYPE_FOLDER_VIDEO) {
            arrayList = this.dt.mVideoMediaWrappers;
        } else if (i != TYPE_FOLDER_AUDIO) {
            return null;
        } else {
            arrayList = this.dt.mAudioMediaWrappers;
        }
        Iterator<MediaWrapper> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue() && isParentFolder(this.mMrl, next.getUri().getPath())) {
                arrayList2.add(next);
            }
        }
        return (MediaWrapper[]) this.dt.secureSublist(new ArrayList(Arrays.asList(this.dt.sortMedia(arrayList2, i2, z))), i4, i3 + i4).toArray(new MediaWrapper[0]);
    }

    public int searchTracksCount(String str, int i) {
        ArrayList<MediaWrapper> arrayList;
        int i2 = 0;
        if (i == TYPE_FOLDER_VIDEO) {
            arrayList = this.dt.mVideoMediaWrappers;
        } else {
            if (i == TYPE_FOLDER_AUDIO) {
                arrayList = this.dt.mAudioMediaWrappers;
            }
            return i2;
        }
        Iterator<MediaWrapper> it = arrayList.iterator();
        while (it.hasNext()) {
            MediaWrapper next = it.next();
            if (Tools.hasSubString(next.getTitle(), str).booleanValue() && isParentFolder(this.mMrl, next.getUri().getPath())) {
                i2++;
            }
        }
        return i2;
    }

    public boolean equals(Object obj) {
        boolean equals = super.equals(obj);
        return (equals || !(obj instanceof Folder)) ? equals : ((Folder) obj).mMrl.equals(this.mMrl);
    }
}
