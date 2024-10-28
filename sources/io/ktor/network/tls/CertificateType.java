package io.ktor.network.tls;

import com.google.common.base.Ascii;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u000f\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006¨\u0006\u0013"}, d2 = {"Lio/ktor/network/tls/CertificateType;", "", "()V", "DSS", "", "getDSS", "()B", "DSS_EPHEMERAL_DH_RESERVED", "getDSS_EPHEMERAL_DH_RESERVED", "DSS_FIXED_DH", "getDSS_FIXED_DH", "FORTEZZA_DMS_RESERVED", "getFORTEZZA_DMS_RESERVED", "RSA", "getRSA", "RSA_EPHEMERAL_DH_RESERVED", "getRSA_EPHEMERAL_DH_RESERVED", "RSA_FIXED_DH", "getRSA_FIXED_DH", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CertificateType.kt */
public final class CertificateType {
    private static final byte DSS = 2;
    private static final byte DSS_EPHEMERAL_DH_RESERVED = 6;
    private static final byte DSS_FIXED_DH = 4;
    private static final byte FORTEZZA_DMS_RESERVED = Ascii.DC4;
    public static final CertificateType INSTANCE = new CertificateType();
    private static final byte RSA = 1;
    private static final byte RSA_EPHEMERAL_DH_RESERVED = 5;
    private static final byte RSA_FIXED_DH = 3;

    private CertificateType() {
    }

    public final byte getRSA() {
        return RSA;
    }

    public final byte getDSS() {
        return DSS;
    }

    public final byte getRSA_FIXED_DH() {
        return RSA_FIXED_DH;
    }

    public final byte getDSS_FIXED_DH() {
        return DSS_FIXED_DH;
    }

    public final byte getRSA_EPHEMERAL_DH_RESERVED() {
        return RSA_EPHEMERAL_DH_RESERVED;
    }

    public final byte getDSS_EPHEMERAL_DH_RESERVED() {
        return DSS_EPHEMERAL_DH_RESERVED;
    }

    public final byte getFORTEZZA_DMS_RESERVED() {
        return FORTEZZA_DMS_RESERVED;
    }
}
