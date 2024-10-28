package io.ktor.network.tls;

import com.google.android.material.internal.ViewUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lio/ktor/network/tls/TLSVersion;", "", "code", "", "(Ljava/lang/String;II)V", "getCode", "()I", "SSL3", "TLS10", "TLS11", "TLS12", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSVersion.kt */
public enum TLSVersion {
    SSL3(ViewUtils.EDGE_TO_EDGE_FLAGS),
    TLS10(769),
    TLS11(770),
    TLS12(771);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final TLSVersion[] byOrdinal = null;
    private final int code;

    private TLSVersion(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
        byOrdinal = values();
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\n"}, d2 = {"Lio/ktor/network/tls/TLSVersion$Companion;", "", "()V", "byOrdinal", "", "Lio/ktor/network/tls/TLSVersion;", "[Lio/ktor/network/tls/TLSVersion;", "byCode", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSVersion.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TLSVersion byCode(int i) {
            if (768 <= i && i < 772) {
                return TLSVersion.byOrdinal[i - ViewUtils.EDGE_TO_EDGE_FLAGS];
            }
            throw new IllegalArgumentException("Invalid TLS version code " + i);
        }
    }
}
