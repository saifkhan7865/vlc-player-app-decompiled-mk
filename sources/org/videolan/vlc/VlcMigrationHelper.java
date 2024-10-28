package org.videolan.vlc;

import java.util.ArrayList;
import java.util.List;
import org.videolan.libvlc.interfaces.IMedia;

public class VlcMigrationHelper {
    public static List<IMedia.Track> getMediaTracks(IMedia iMedia) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < iMedia.getTrackCount(); i++) {
            arrayList.add(iMedia.getTrack(i));
        }
        return arrayList;
    }
}
