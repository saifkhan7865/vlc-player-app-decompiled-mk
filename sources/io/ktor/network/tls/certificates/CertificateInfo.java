package io.ktor.network.tls.certificates;

import java.security.KeyPair;
import java.security.cert.Certificate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lio/ktor/network/tls/certificates/CertificateInfo;", "", "certificate", "Ljava/security/cert/Certificate;", "keys", "Ljava/security/KeyPair;", "password", "", "issuerCertificate", "(Ljava/security/cert/Certificate;Ljava/security/KeyPair;Ljava/lang/String;Ljava/security/cert/Certificate;)V", "getCertificate", "()Ljava/security/cert/Certificate;", "getIssuerCertificate", "getKeys", "()Ljava/security/KeyPair;", "getPassword", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "ktor-network-tls-certificates"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: builders.kt */
public final class CertificateInfo {
    private final Certificate certificate;
    private final Certificate issuerCertificate;
    private final KeyPair keys;
    private final String password;

    public static /* synthetic */ CertificateInfo copy$default(CertificateInfo certificateInfo, Certificate certificate2, KeyPair keyPair, String str, Certificate certificate3, int i, Object obj) {
        if ((i & 1) != 0) {
            certificate2 = certificateInfo.certificate;
        }
        if ((i & 2) != 0) {
            keyPair = certificateInfo.keys;
        }
        if ((i & 4) != 0) {
            str = certificateInfo.password;
        }
        if ((i & 8) != 0) {
            certificate3 = certificateInfo.issuerCertificate;
        }
        return certificateInfo.copy(certificate2, keyPair, str, certificate3);
    }

    public final Certificate component1() {
        return this.certificate;
    }

    public final KeyPair component2() {
        return this.keys;
    }

    public final String component3() {
        return this.password;
    }

    public final Certificate component4() {
        return this.issuerCertificate;
    }

    public final CertificateInfo copy(Certificate certificate2, KeyPair keyPair, String str, Certificate certificate3) {
        Intrinsics.checkNotNullParameter(certificate2, "certificate");
        Intrinsics.checkNotNullParameter(keyPair, "keys");
        Intrinsics.checkNotNullParameter(str, "password");
        return new CertificateInfo(certificate2, keyPair, str, certificate3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CertificateInfo)) {
            return false;
        }
        CertificateInfo certificateInfo = (CertificateInfo) obj;
        return Intrinsics.areEqual((Object) this.certificate, (Object) certificateInfo.certificate) && Intrinsics.areEqual((Object) this.keys, (Object) certificateInfo.keys) && Intrinsics.areEqual((Object) this.password, (Object) certificateInfo.password) && Intrinsics.areEqual((Object) this.issuerCertificate, (Object) certificateInfo.issuerCertificate);
    }

    public int hashCode() {
        int hashCode = ((((this.certificate.hashCode() * 31) + this.keys.hashCode()) * 31) + this.password.hashCode()) * 31;
        Certificate certificate2 = this.issuerCertificate;
        return hashCode + (certificate2 == null ? 0 : certificate2.hashCode());
    }

    public String toString() {
        return "CertificateInfo(certificate=" + this.certificate + ", keys=" + this.keys + ", password=" + this.password + ", issuerCertificate=" + this.issuerCertificate + ')';
    }

    public CertificateInfo(Certificate certificate2, KeyPair keyPair, String str, Certificate certificate3) {
        Intrinsics.checkNotNullParameter(certificate2, "certificate");
        Intrinsics.checkNotNullParameter(keyPair, "keys");
        Intrinsics.checkNotNullParameter(str, "password");
        this.certificate = certificate2;
        this.keys = keyPair;
        this.password = str;
        this.issuerCertificate = certificate3;
    }

    public final Certificate getCertificate() {
        return this.certificate;
    }

    public final KeyPair getKeys() {
        return this.keys;
    }

    public final String getPassword() {
        return this.password;
    }

    public final Certificate getIssuerCertificate() {
        return this.issuerCertificate;
    }
}
