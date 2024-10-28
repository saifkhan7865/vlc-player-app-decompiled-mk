package org.videolan.vlc.media;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.interfaces.IMedia;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0002\u001a\u00020\u0003*\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0005H\u0002¢\u0006\u0002\u0010\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"NO_LENGTH_PROGRESS_MAX", "", "contains", "", "", "Lorg/videolan/libvlc/interfaces/IMedia$Slave;", "item", "([Lorg/videolan/libvlc/interfaces/IMedia$Slave;Lorg/videolan/libvlc/interfaces/IMedia$Slave;)Z", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerController.kt */
public final class PlayerControllerKt {
    public static final int NO_LENGTH_PROGRESS_MAX = 1000;

    /* access modifiers changed from: private */
    public static final boolean contains(IMedia.Slave[] slaveArr, IMedia.Slave slave) {
        if (slaveArr == null) {
            return false;
        }
        Iterator it = ArrayIteratorKt.iterator(slaveArr);
        while (it.hasNext()) {
            if (Intrinsics.areEqual((Object) ((IMedia.Slave) it.next()).uri, (Object) slave.uri)) {
                return true;
            }
        }
        return false;
    }
}
