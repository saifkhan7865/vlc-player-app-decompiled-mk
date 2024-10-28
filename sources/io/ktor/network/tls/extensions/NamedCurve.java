package io.ktor.network.tls.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.asn1.BERTags;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\b\n\u0002\b \b\u0001\u0018\u0000 $2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001$B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#¨\u0006%"}, d2 = {"Lio/ktor/network/tls/extensions/NamedCurve;", "", "code", "", "fieldSize", "", "(Ljava/lang/String;ISI)V", "getCode", "()S", "getFieldSize", "()I", "sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", "secp224r1", "secp256k1", "secp256r1", "secp384r1", "secp521r1", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NamedCurves.kt */
public enum NamedCurve {
    sect163k1(1, 163),
    sect163r1(2, 163),
    sect163r2(3, 163),
    sect193r1(4, 193),
    sect193r2(5, 193),
    sect233k1(6, 233),
    sect233r1(7, 233),
    sect239k1(8, 239),
    sect283k1(9, 283),
    sect283r1(10, 283),
    sect409k1(11, 409),
    sect409r1(12, 409),
    sect571k1(13, 571),
    sect571r1(14, 571),
    secp160k1(15, 160),
    secp160r1(16, 160),
    secp160r2(17, 160),
    secp192k1(18, 192),
    secp192r1(19, 192),
    secp224k1(20, BERTags.FLAGS),
    secp224r1(21, BERTags.FLAGS),
    secp256k1(22, 256),
    secp256r1(23, 256),
    secp384r1(24, KyberEngine.KyberPolyBytes),
    secp521r1(25, 521);
    
    public static final Companion Companion = null;
    private final short code;
    private final int fieldSize;

    private NamedCurve(short s, int i) {
        this.code = s;
        this.fieldSize = i;
    }

    public final short getCode() {
        return this.code;
    }

    public final int getFieldSize() {
        return this.fieldSize;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\n\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/network/tls/extensions/NamedCurve$Companion;", "", "()V", "fromCode", "Lio/ktor/network/tls/extensions/NamedCurve;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NamedCurves.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final NamedCurve fromCode(short s) {
            for (NamedCurve namedCurve : NamedCurve.values()) {
                if (namedCurve.getCode() == s) {
                    return namedCurve;
                }
            }
            return null;
        }
    }
}
