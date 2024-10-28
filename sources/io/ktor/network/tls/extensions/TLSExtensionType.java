package io.ktor.network.tls.extensions;

import io.ktor.network.tls.TLSException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\n\n\u0002\b\u000e\b\u0001\u0018\u0000 \u00102\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0010B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0011"}, d2 = {"Lio/ktor/network/tls/extensions/TLSExtensionType;", "", "code", "", "(Ljava/lang/String;IS)V", "getCode", "()S", "SERVER_NAME", "MAX_FRAGMENT_LENGTH", "CLIENT_CERTIFICATE_URL", "TRUSTED_CA_KEYS", "TRUNCATED_HMAC", "STATUS_REQUEST", "ELLIPTIC_CURVES", "EC_POINT_FORMAT", "SIGNATURE_ALGORITHMS", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSExtension.kt */
public enum TLSExtensionType {
    SERVER_NAME(0),
    MAX_FRAGMENT_LENGTH(1),
    CLIENT_CERTIFICATE_URL(2),
    TRUSTED_CA_KEYS(3),
    TRUNCATED_HMAC(4),
    STATUS_REQUEST(5),
    ELLIPTIC_CURVES(10),
    EC_POINT_FORMAT(11),
    SIGNATURE_ALGORITHMS(13);
    
    public static final Companion Companion = null;
    private final short code;

    private TLSExtensionType(short s) {
        this.code = s;
    }

    public final short getCode() {
        return this.code;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/network/tls/extensions/TLSExtensionType$Companion;", "", "()V", "byCode", "Lio/ktor/network/tls/extensions/TLSExtensionType;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSExtension.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TLSExtensionType byCode(int i) {
            TLSExtensionType tLSExtensionType;
            TLSExtensionType[] values = TLSExtensionType.values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    tLSExtensionType = null;
                    break;
                }
                tLSExtensionType = values[i2];
                if (tLSExtensionType.getCode() == ((short) i)) {
                    break;
                }
                i2++;
            }
            if (tLSExtensionType != null) {
                return tLSExtensionType;
            }
            throw new TLSException("Unknown server hello extension type: " + i, (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }
}
