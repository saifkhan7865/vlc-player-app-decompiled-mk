package io.ktor.network.tls.extensions;

import io.ktor.network.tls.TLSException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.bouncycastle.pqc.jcajce.spec.McElieceCCA2KeyGenParameterSpec;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\b\u0001\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0015B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0016"}, d2 = {"Lio/ktor/network/tls/extensions/HashAlgorithm;", "", "code", "", "openSSLName", "", "macName", "(Ljava/lang/String;IBLjava/lang/String;Ljava/lang/String;)V", "getCode", "()B", "getMacName", "()Ljava/lang/String;", "getOpenSSLName", "NONE", "MD5", "SHA1", "SHA224", "SHA256", "SHA384", "SHA512", "INTRINSIC", "Companion", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SignatureAlgorithm.kt */
public enum HashAlgorithm {
    NONE((byte) 0, "", ""),
    MD5((byte) 1, "MD5", "HmacMD5"),
    SHA1((byte) 2, McElieceCCA2KeyGenParameterSpec.SHA1, "HmacSHA1"),
    SHA224((byte) 3, McElieceCCA2KeyGenParameterSpec.SHA224, "HmacSHA224"),
    SHA256((byte) 4, "SHA-256", "HmacSHA256"),
    SHA384((byte) 5, McElieceCCA2KeyGenParameterSpec.SHA384, "HmacSHA384"),
    SHA512((byte) 6, "SHA-512", "HmacSHA512"),
    INTRINSIC((byte) 8, "INTRINSIC", "Intrinsic");
    
    public static final Companion Companion = null;
    private final byte code;
    private final String macName;
    private final String openSSLName;

    private HashAlgorithm(byte b, String str, String str2) {
        this.code = b;
        this.openSSLName = str;
        this.macName = str2;
    }

    public final byte getCode() {
        return this.code;
    }

    public final String getMacName() {
        return this.macName;
    }

    public final String getOpenSSLName() {
        return this.openSSLName;
    }

    static {
        Companion = new Companion((DefaultConstructorMarker) null);
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/network/tls/extensions/HashAlgorithm$Companion;", "", "()V", "byCode", "Lio/ktor/network/tls/extensions/HashAlgorithm;", "code", "", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SignatureAlgorithm.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HashAlgorithm byCode(byte b) {
            HashAlgorithm hashAlgorithm;
            HashAlgorithm[] values = HashAlgorithm.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    hashAlgorithm = null;
                    break;
                }
                hashAlgorithm = values[i];
                if (hashAlgorithm.getCode() == b) {
                    break;
                }
                i++;
            }
            if (hashAlgorithm != null) {
                return hashAlgorithm;
            }
            throw new TLSException("Unknown hash algorithm: " + b, (Throwable) null, 2, (DefaultConstructorMarker) null);
        }
    }
}
