package io.ktor.network.tls;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\b\u0001\u0018\u0000 \u00112\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0011B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0012"}, d2 = {"Lio/ktor/network/tls/TLSHandshakeType;", "", "code", "", "(Ljava/lang/String;II)V", "getCode", "()I", "HelloRequest", "ClientHello", "ServerHello", "Certificate", "ServerKeyExchange", "CertificateRequest", "ServerDone", "CertificateVerify", "ClientKeyExchange", "Finished", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSHandshakeType.kt */
public enum TLSHandshakeType {
    HelloRequest(0),
    ClientHello(1),
    ServerHello(2),
    Certificate(11),
    ServerKeyExchange(12),
    CertificateRequest(13),
    ServerDone(14),
    CertificateVerify(15),
    ClientKeyExchange(16),
    Finished(20);
    
    public static final Companion Companion = null;
    /* access modifiers changed from: private */
    public static final TLSHandshakeType[] byCode = null;
    private final int code;

    private TLSHandshakeType(int i) {
        this.code = i;
    }

    public final int getCode() {
        return this.code;
    }

    static {
        TLSHandshakeType tLSHandshakeType;
        Companion = new Companion((DefaultConstructorMarker) null);
        TLSHandshakeType[] tLSHandshakeTypeArr = new TLSHandshakeType[256];
        for (int i = 0; i < 256; i++) {
            TLSHandshakeType[] values = values();
            int length = values.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    tLSHandshakeType = null;
                    break;
                }
                tLSHandshakeType = values[i2];
                if (tLSHandshakeType.code == i) {
                    break;
                }
                i2++;
            }
            tLSHandshakeTypeArr[i] = tLSHandshakeType;
        }
        byCode = tLSHandshakeTypeArr;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0018\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/network/tls/TLSHandshakeType$Companion;", "", "()V", "byCode", "", "Lio/ktor/network/tls/TLSHandshakeType;", "[Lio/ktor/network/tls/TLSHandshakeType;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: TLSHandshakeType.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TLSHandshakeType byCode(int i) {
            TLSHandshakeType tLSHandshakeType = (i < 0 || i >= 256) ? null : TLSHandshakeType.byCode[i];
            if (tLSHandshakeType != null) {
                return tLSHandshakeType;
            }
            throw new IllegalArgumentException("Invalid TLS handshake type code: " + i);
        }
    }
}
