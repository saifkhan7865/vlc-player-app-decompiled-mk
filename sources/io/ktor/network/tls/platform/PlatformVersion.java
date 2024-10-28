package io.ktor.network.tls.platform;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0000\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Lio/ktor/network/tls/platform/PlatformVersion;", "", "major", "", "minor", "", "(Ljava/lang/String;I)V", "getMajor", "()Ljava/lang/String;", "getMinor", "()I", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PlatformVersion.kt */
public final class PlatformVersion {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final PlatformVersion MINIMAL_SUPPORTED = new PlatformVersion("1.6.0", 0);
    private final String major;
    private final int minor;

    public PlatformVersion(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "major");
        this.major = str;
        this.minor = i;
    }

    public final String getMajor() {
        return this.major;
    }

    public final int getMinor() {
        return this.minor;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/ktor/network/tls/platform/PlatformVersion$Companion;", "", "()V", "MINIMAL_SUPPORTED", "Lio/ktor/network/tls/platform/PlatformVersion;", "invoke", "rawVersion", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PlatformVersion.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlatformVersion invoke(String str) {
            Intrinsics.checkNotNullParameter(str, "rawVersion");
            try {
                List split$default = StringsKt.split$default((CharSequence) str, new char[]{'-', '_'}, false, 0, 6, (Object) null);
                if (split$default.size() == 2) {
                    return new PlatformVersion((String) split$default.get(0), Integer.parseInt((String) split$default.get(1)));
                }
                return new PlatformVersion(str, -1);
            } catch (Throwable unused) {
                return PlatformVersion.MINIMAL_SUPPORTED;
            }
        }
    }
}
