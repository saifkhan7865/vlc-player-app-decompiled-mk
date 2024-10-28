package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u001e\b\u0001\u0018\u0000  2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001 B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001f¨\u0006!"}, d2 = {"Lio/ktor/network/tls/TLSAlertType;", "", "code", "", "(Ljava/lang/String;II)V", "getCode", "()I", "DecryptionFailed_RESERVED", "CloseNotify", "UnexpectedMessage", "BadRecordMac", "RecordOverflow", "DecompressionFailure", "HandshakeFailure", "NoCertificate_RESERVED", "BadCertificate", "UnsupportedCertificate", "CertificateRevoked", "CertificateExpired", "CertificateUnknown", "IllegalParameter", "UnknownCa", "AccessDenied", "DecodeError", "DecryptError", "ExportRestriction_RESERVED", "ProtocolVersion", "InsufficientSecurity", "InternalError", "UserCanceled", "NoRenegotiation", "UnsupportedExtension", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSAlert.kt */
public enum TLSAlertType {
    DecryptionFailed_RESERVED(21),
    CloseNotify(0),
    UnexpectedMessage(10),
    BadRecordMac(20),
    RecordOverflow(22),
    DecompressionFailure(30),
    HandshakeFailure(40),
    NoCertificate_RESERVED(41),
    BadCertificate(42),
    UnsupportedCertificate(43),
    CertificateRevoked(44),
    CertificateExpired(45),
    CertificateUnknown(46),
    IllegalParameter(47),
    UnknownCa(48),
    AccessDenied(49),
    DecodeError(50),
    DecryptError(51),
    ExportRestriction_RESERVED(60),
    ProtocolVersion(70),
    InsufficientSecurity(71),
    InternalError(80),
    UserCanceled(90),
    NoRenegotiation(100),
    UnsupportedExtension(110);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final TLSAlertType[] byCode = null;
    private final int code;

    private TLSAlertType(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    static {
        TLSAlertType tLSAlertType;
        Companion = new Companion((DefaultConstructorMarker) null);
        TLSAlertType[] tLSAlertTypeArr = new TLSAlertType[256];
        for (int i = 0; i < 256; i++) {
            TLSAlertType[] values = values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    tLSAlertType = null;
                    break;
                }
                tLSAlertType = values[i2];
                if (tLSAlertType.code == i) {
                    break;
                }
                i2++;
            }
            tLSAlertTypeArr[i] = tLSAlertType;
        }
        byCode = tLSAlertTypeArr;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/network/tls/TLSAlertType$Companion;", "", "()V", "byCode", "", "Lio/ktor/network/tls/TLSAlertType;", "[Lio/ktor/network/tls/TLSAlertType;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSAlert.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TLSAlertType byCode(int i) {
            TLSAlertType tLSAlertType = (i < 0 || i >= 256) ? null : TLSAlertType.byCode[i];
            if (tLSAlertType != null) {
                return tLSAlertType;
            }
            throw new IllegalArgumentException("Invalid TLS record type code: " + i);
        }
    }
}
