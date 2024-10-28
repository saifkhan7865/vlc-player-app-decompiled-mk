package org.videolan.libvlc.interfaces;

import org.videolan.libvlc.interfaces.AbstractVLCEvent;

public interface IVLCObject<T extends AbstractVLCEvent> {
    ILibVLC getLibVLC();

    boolean isReleased();

    void release();

    boolean retain();
}
