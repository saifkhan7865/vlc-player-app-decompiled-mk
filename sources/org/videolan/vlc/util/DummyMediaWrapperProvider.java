package org.videolan.vlc.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.MLServiceLocator;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/util/DummyMediaWrapperProvider;", "", "()V", "getDummyMediaWrapper", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "id", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DummyMediaWrapperProvider.kt */
public final class DummyMediaWrapperProvider {
    public static final DummyMediaWrapperProvider INSTANCE = new DummyMediaWrapperProvider();

    private DummyMediaWrapperProvider() {
    }

    public final MediaWrapper getDummyMediaWrapper(long j) {
        if (j < 0) {
            MediaWrapper abstractMediaWrapper = MLServiceLocator.getAbstractMediaWrapper(j, "dummy://Mrl", -1, -1.0f, 18820, 0, "", "", "", "", "", "", 416, 304, "", 0, -2, 0, 0, 1509466228, 0, true, false, 1970, true, 1683711438317L);
            Intrinsics.checkNotNullExpressionValue(abstractMediaWrapper, "getAbstractMediaWrapper(...)");
            return abstractMediaWrapper;
        }
        throw new IllegalArgumentException("Dummy MediaWrapper id must be < 0");
    }
}
