package org.videolan.libvlc.stubs;

import org.videolan.libvlc.interfaces.AbstractVLCEvent;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.interfaces.IVLCObject;

public class StubVLCObject<T extends AbstractVLCEvent> implements IVLCObject<T> {
    public ILibVLC getLibVLC() {
        return null;
    }

    public boolean isReleased() {
        return false;
    }

    public void release() {
    }

    public boolean retain() {
        return false;
    }
}
