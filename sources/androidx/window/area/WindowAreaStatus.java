package androidx.window.area;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/window/area/WindowAreaStatus;", "", "mDescription", "", "(Ljava/lang/String;)V", "toString", "Companion", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WindowAreaStatus.kt */
public final class WindowAreaStatus {
    public static final WindowAreaStatus AVAILABLE = new WindowAreaStatus("AVAILABLE");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final WindowAreaStatus UNAVAILABLE = new WindowAreaStatus("UNAVAILABLE");
    public static final WindowAreaStatus UNSUPPORTED = new WindowAreaStatus("UNSUPPORTED");
    private final String mDescription;

    private WindowAreaStatus(String str) {
        this.mDescription = str;
    }

    public String toString() {
        return this.mDescription;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/window/area/WindowAreaStatus$Companion;", "", "()V", "AVAILABLE", "Landroidx/window/area/WindowAreaStatus;", "UNAVAILABLE", "UNSUPPORTED", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WindowAreaStatus.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
