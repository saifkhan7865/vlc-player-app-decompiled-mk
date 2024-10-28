package org.videolan.libvlc;

import org.videolan.libvlc.interfaces.AbstractVLCEvent;
import org.videolan.libvlc.interfaces.ILibVLC;

public class MediaDiscoverer extends VLCObject<Event> {
    private static final String TAG = "LibVLC/MediaDiscoverer";
    private MediaList mMediaList = null;

    public interface EventListener extends AbstractVLCEvent.Listener<Event> {
    }

    private static native Description[] nativeList(ILibVLC iLibVLC, int i);

    private native void nativeNew(ILibVLC iLibVLC, String str);

    private native void nativeRelease();

    private native boolean nativeStart();

    private native void nativeStop();

    public /* bridge */ /* synthetic */ long getInstance() {
        return super.getInstance();
    }

    public /* bridge */ /* synthetic */ ILibVLC getLibVLC() {
        return super.getLibVLC();
    }

    public /* bridge */ /* synthetic */ boolean isReleased() {
        return super.isReleased();
    }

    public static class Event extends AbstractVLCEvent {
        public static final int Ended = 1281;
        public static final int Started = 1280;

        protected Event(int i) {
            super(i);
        }
    }

    public static class Description {
        public final int category;
        public final String longName;
        public final String name;

        public static class Category {
            public static final int Devices = 0;
            public static final int Lan = 1;
            public static final int LocalDirs = 3;
            public static final int Podcasts = 2;
        }

        private Description(String str, String str2, int i) {
            this.name = str;
            this.longName = str2;
            this.category = i;
        }
    }

    private static Description createDescriptionFromNative(String str, String str2, int i) {
        return new Description(str, str2, i);
    }

    public MediaDiscoverer(ILibVLC iLibVLC, String str) {
        super(iLibVLC);
        nativeNew(iLibVLC, str);
    }

    public boolean start() {
        if (!isReleased()) {
            return nativeStart();
        }
        throw new IllegalStateException("MediaDiscoverer is released");
    }

    public void stop() {
        if (!isReleased()) {
            nativeStop();
            return;
        }
        throw new IllegalStateException("MediaDiscoverer is released");
    }

    public void setEventListener(EventListener eventListener) {
        super.setEventListener(eventListener);
    }

    /* access modifiers changed from: protected */
    public Event onEventNative(int i, long j, long j2, float f, String str) {
        if (i == 1280 || i == 1281) {
            return new Event(i);
        }
        return null;
    }

    public MediaList getMediaList() {
        MediaList mediaList;
        synchronized (this) {
            MediaList mediaList2 = this.mMediaList;
            if (mediaList2 != null) {
                mediaList2.retain();
                MediaList mediaList3 = this.mMediaList;
                return mediaList3;
            }
            MediaList mediaList4 = new MediaList(this);
            synchronized (this) {
                this.mMediaList = mediaList4;
                mediaList4.retain();
                mediaList = this.mMediaList;
            }
            return mediaList;
        }
    }

    /* access modifiers changed from: protected */
    public void onReleaseNative() {
        MediaList mediaList = this.mMediaList;
        if (mediaList != null) {
            mediaList.release();
        }
        nativeRelease();
    }

    public static Description[] list(ILibVLC iLibVLC, int i) {
        return nativeList(iLibVLC, i);
    }
}
