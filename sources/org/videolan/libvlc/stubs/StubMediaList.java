package org.videolan.libvlc.stubs;

import android.os.Handler;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IMediaList;

public class StubMediaList extends StubVLCObject<IMediaList.Event> implements IMediaList {
    public int getCount() {
        return 0;
    }

    public IMedia getMediaAt(int i) {
        return null;
    }

    public boolean isLocked() {
        return false;
    }

    public void setEventListener(IMediaList.EventListener eventListener, Handler handler) {
    }
}
