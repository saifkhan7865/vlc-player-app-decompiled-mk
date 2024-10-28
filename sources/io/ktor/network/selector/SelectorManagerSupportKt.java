package io.ktor.network.selector;

import java.io.IOException;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¨\u0006\u0006"}, d2 = {"selectableIsClosed", "", "selectableIsInvalid", "interestedOps", "", "flag", "ktor-network"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SelectorManagerSupport.kt */
public final class SelectorManagerSupportKt {
    /* access modifiers changed from: private */
    public static final Void selectableIsClosed() {
        throw new IOException("Selectable is already closed");
    }

    /* access modifiers changed from: private */
    public static final Void selectableIsInvalid(int i, int i2) {
        throw new IllegalStateException(("Selectable is invalid state: " + i + ", " + i2).toString());
    }
}
