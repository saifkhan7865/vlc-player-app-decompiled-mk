package org.videolan.libvlc.interfaces;

import android.content.Context;

public interface ILibVLC extends IVLCObject<Event> {
    Context getAppContext();

    public static class Event extends AbstractVLCEvent {
        protected Event(int i) {
            super(i);
        }
    }
}
