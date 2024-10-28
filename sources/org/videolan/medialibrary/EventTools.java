package org.videolan.medialibrary;

import androidx.lifecycle.LiveData;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

public final class EventTools {
    private static EventTools sInstance;
    public final LiveData<MediaWrapper> lastThumb = new SingleEvent();

    public static EventTools getInstance() {
        if (sInstance == null) {
            sInstance = new EventTools();
        }
        return sInstance;
    }
}
