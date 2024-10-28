package org.videolan.vlc.providers;

import kotlin.Metadata;
import org.videolan.libvlc.interfaces.IMedia;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"isStorage", "", "Lorg/videolan/libvlc/interfaces/IMedia;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageProvider.kt */
public final class StorageProviderKt {
    /* access modifiers changed from: private */
    public static final boolean isStorage(IMedia iMedia) {
        return iMedia.getType() == 2;
    }
}
