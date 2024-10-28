package io.ktor.network.tls.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u000b\b\u0001\u0018\u0000 \r2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\rB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\f¨\u0006\u000e"}, d2 = {"Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "", "code", "", "(Ljava/lang/String;IB)V", "getCode", "()B", "ANON", "RSA", "DSA", "ECDSA", "ED25519", "ED448", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SignatureAlgorithm.kt */
public enum SignatureAlgorithm {
    ANON((byte) 0),
    RSA((byte) 1),
    DSA((byte) 2),
    ECDSA((byte) 3),
    ED25519((byte) 7),
    ED448((byte) 8);
    
    public static final Companion Companion = null;
    private final byte code;

    private SignatureAlgorithm(byte b) {
        this.code = b;
    }

    public final byte getCode() {
        return this.code;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/network/tls/extensions/SignatureAlgorithm$Companion;", "", "()V", "byCode", "Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SignatureAlgorithm.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SignatureAlgorithm byCode(byte b) {
            for (SignatureAlgorithm signatureAlgorithm : SignatureAlgorithm.values()) {
                if (signatureAlgorithm.getCode() == b) {
                    return signatureAlgorithm;
                }
            }
            return null;
        }
    }
}
