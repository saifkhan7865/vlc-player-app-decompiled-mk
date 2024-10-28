package io.ktor.network.tls.certificates;

import io.ktor.http.ContentDisposition;
import io.ktor.network.tls.extensions.HashAlgorithm;
import io.ktor.network.tls.extensions.SignatureAlgorithm;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.security.KeyPair;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.List;
import javax.security.auth.x500.X500Principal;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001DB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u00109\u001a\u00020:H\u0000¢\u0006\u0002\b;J\u001e\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u000204J\u0016\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020CR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010(\u001a\u00020\u000bX.¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\u00020.X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u000204X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108¨\u0006E"}, d2 = {"Lio/ktor/network/tls/certificates/CertificateBuilder;", "", "()V", "daysValid", "", "getDaysValid", "()J", "setDaysValid", "(J)V", "domains", "", "", "getDomains", "()Ljava/util/List;", "setDomains", "(Ljava/util/List;)V", "hash", "Lio/ktor/network/tls/extensions/HashAlgorithm;", "getHash", "()Lio/ktor/network/tls/extensions/HashAlgorithm;", "setHash", "(Lio/ktor/network/tls/extensions/HashAlgorithm;)V", "ipAddresses", "Ljava/net/InetAddress;", "getIpAddresses", "setIpAddresses", "issuer", "Lio/ktor/network/tls/certificates/CertificateBuilder$CertificateIssuer;", "keySizeInBits", "", "getKeySizeInBits", "()I", "setKeySizeInBits", "(I)V", "keyType", "Lio/ktor/network/tls/certificates/KeyType;", "getKeyType", "()Lio/ktor/network/tls/certificates/KeyType;", "setKeyType", "(Lio/ktor/network/tls/certificates/KeyType;)V", "password", "getPassword", "()Ljava/lang/String;", "setPassword", "(Ljava/lang/String;)V", "sign", "Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "getSign", "()Lio/ktor/network/tls/extensions/SignatureAlgorithm;", "setSign", "(Lio/ktor/network/tls/extensions/SignatureAlgorithm;)V", "subject", "Ljavax/security/auth/x500/X500Principal;", "getSubject", "()Ljavax/security/auth/x500/X500Principal;", "setSubject", "(Ljavax/security/auth/x500/X500Principal;)V", "build", "Lio/ktor/network/tls/certificates/CertificateInfo;", "build$ktor_network_tls_certificates", "signWith", "", "issuerKeyPair", "Ljava/security/KeyPair;", "issuerKeyCertificate", "Ljava/security/cert/Certificate;", "issuerName", "Ljava/security/cert/X509Certificate;", "CertificateIssuer", "ktor-network-tls-certificates"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: builders.kt */
public final class CertificateBuilder {
    private long daysValid = 3;
    private List<String> domains = CollectionsKt.listOf("localhost");
    private HashAlgorithm hash = HashAlgorithm.SHA1;
    private List<? extends InetAddress> ipAddresses = CollectionsKt.listOf(Inet4Address.getByName("127.0.0.1"));
    private CertificateIssuer issuer;
    private int keySizeInBits = 1024;
    private KeyType keyType = KeyType.Server;
    public String password;
    private SignatureAlgorithm sign = SignatureAlgorithm.RSA;
    private X500Principal subject = CertificatesKt.getDEFAULT_PRINCIPAL();

    public final HashAlgorithm getHash() {
        return this.hash;
    }

    public final void setHash(HashAlgorithm hashAlgorithm) {
        Intrinsics.checkNotNullParameter(hashAlgorithm, "<set-?>");
        this.hash = hashAlgorithm;
    }

    public final SignatureAlgorithm getSign() {
        return this.sign;
    }

    public final void setSign(SignatureAlgorithm signatureAlgorithm) {
        Intrinsics.checkNotNullParameter(signatureAlgorithm, "<set-?>");
        this.sign = signatureAlgorithm;
    }

    public final String getPassword() {
        String str = this.password;
        if (str != null) {
            return str;
        }
        Intrinsics.throwUninitializedPropertyAccessException("password");
        return null;
    }

    public final void setPassword(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.password = str;
    }

    public final X500Principal getSubject() {
        return this.subject;
    }

    public final void setSubject(X500Principal x500Principal) {
        Intrinsics.checkNotNullParameter(x500Principal, "<set-?>");
        this.subject = x500Principal;
    }

    public final long getDaysValid() {
        return this.daysValid;
    }

    public final void setDaysValid(long j) {
        this.daysValid = j;
    }

    public final int getKeySizeInBits() {
        return this.keySizeInBits;
    }

    public final void setKeySizeInBits(int i) {
        this.keySizeInBits = i;
    }

    public final KeyType getKeyType() {
        return this.keyType;
    }

    public final void setKeyType(KeyType keyType2) {
        Intrinsics.checkNotNullParameter(keyType2, "<set-?>");
        this.keyType = keyType2;
    }

    public final List<String> getDomains() {
        return this.domains;
    }

    public final void setDomains(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.domains = list;
    }

    public final List<InetAddress> getIpAddresses() {
        return this.ipAddresses;
    }

    public final void setIpAddresses(List<? extends InetAddress> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.ipAddresses = list;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lio/ktor/network/tls/certificates/CertificateBuilder$CertificateIssuer;", "", "name", "Ljavax/security/auth/x500/X500Principal;", "keyPair", "Ljava/security/KeyPair;", "keyCertificate", "Ljava/security/cert/Certificate;", "(Ljavax/security/auth/x500/X500Principal;Ljava/security/KeyPair;Ljava/security/cert/Certificate;)V", "getKeyCertificate", "()Ljava/security/cert/Certificate;", "getKeyPair", "()Ljava/security/KeyPair;", "getName", "()Ljavax/security/auth/x500/X500Principal;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "ktor-network-tls-certificates"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: builders.kt */
    private static final class CertificateIssuer {
        private final Certificate keyCertificate;
        private final KeyPair keyPair;
        private final X500Principal name;

        public static /* synthetic */ CertificateIssuer copy$default(CertificateIssuer certificateIssuer, X500Principal x500Principal, KeyPair keyPair2, Certificate certificate, int i, Object obj) {
            if ((i & 1) != 0) {
                x500Principal = certificateIssuer.name;
            }
            if ((i & 2) != 0) {
                keyPair2 = certificateIssuer.keyPair;
            }
            if ((i & 4) != 0) {
                certificate = certificateIssuer.keyCertificate;
            }
            return certificateIssuer.copy(x500Principal, keyPair2, certificate);
        }

        public final X500Principal component1() {
            return this.name;
        }

        public final KeyPair component2() {
            return this.keyPair;
        }

        public final Certificate component3() {
            return this.keyCertificate;
        }

        public final CertificateIssuer copy(X500Principal x500Principal, KeyPair keyPair2, Certificate certificate) {
            Intrinsics.checkNotNullParameter(x500Principal, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(keyPair2, "keyPair");
            Intrinsics.checkNotNullParameter(certificate, "keyCertificate");
            return new CertificateIssuer(x500Principal, keyPair2, certificate);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CertificateIssuer)) {
                return false;
            }
            CertificateIssuer certificateIssuer = (CertificateIssuer) obj;
            return Intrinsics.areEqual((Object) this.name, (Object) certificateIssuer.name) && Intrinsics.areEqual((Object) this.keyPair, (Object) certificateIssuer.keyPair) && Intrinsics.areEqual((Object) this.keyCertificate, (Object) certificateIssuer.keyCertificate);
        }

        public int hashCode() {
            return (((this.name.hashCode() * 31) + this.keyPair.hashCode()) * 31) + this.keyCertificate.hashCode();
        }

        public String toString() {
            return "CertificateIssuer(name=" + this.name + ", keyPair=" + this.keyPair + ", keyCertificate=" + this.keyCertificate + ')';
        }

        public CertificateIssuer(X500Principal x500Principal, KeyPair keyPair2, Certificate certificate) {
            Intrinsics.checkNotNullParameter(x500Principal, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(keyPair2, "keyPair");
            Intrinsics.checkNotNullParameter(certificate, "keyCertificate");
            this.name = x500Principal;
            this.keyPair = keyPair2;
            this.keyCertificate = certificate;
        }

        public final X500Principal getName() {
            return this.name;
        }

        public final KeyPair getKeyPair() {
            return this.keyPair;
        }

        public final Certificate getKeyCertificate() {
            return this.keyCertificate;
        }
    }

    public final void signWith(KeyPair keyPair, X509Certificate x509Certificate) {
        Intrinsics.checkNotNullParameter(keyPair, "issuerKeyPair");
        Intrinsics.checkNotNullParameter(x509Certificate, "issuerKeyCertificate");
        X500Principal subjectX500Principal = x509Certificate.getSubjectX500Principal();
        Intrinsics.checkNotNullExpressionValue(subjectX500Principal, "issuerKeyCertificate.subjectX500Principal");
        this.issuer = new CertificateIssuer(subjectX500Principal, keyPair, x509Certificate);
    }

    public final void signWith(KeyPair keyPair, Certificate certificate, X500Principal x500Principal) {
        Intrinsics.checkNotNullParameter(keyPair, "issuerKeyPair");
        Intrinsics.checkNotNullParameter(certificate, "issuerKeyCertificate");
        Intrinsics.checkNotNullParameter(x500Principal, "issuerName");
        this.issuer = new CertificateIssuer(x500Principal, keyPair, certificate);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x003e, code lost:
        r2 = r2.getKeyPair();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final io.ktor.network.tls.certificates.CertificateInfo build$ktor_network_tls_certificates() {
        /*
            r18 = this;
            r0 = r18
            io.ktor.network.tls.extensions.HashAndSign r7 = new io.ktor.network.tls.extensions.HashAndSign
            io.ktor.network.tls.extensions.HashAlgorithm r2 = r0.hash
            io.ktor.network.tls.extensions.SignatureAlgorithm r3 = r0.sign
            r5 = 4
            r6 = 0
            r4 = 0
            r1 = r7
            r1.<init>(r2, r3, r4, r5, r6)
            java.lang.String r1 = r7.getName()
            java.lang.String r1 = io.ktor.network.tls.OIDKt.keysGenerationAlgorithm(r1)
            java.security.KeyPairGenerator r1 = java.security.KeyPairGenerator.getInstance(r1)
            int r2 = r0.keySizeInBits
            r1.initialize(r2)
            java.security.KeyPair r1 = r1.genKeyPair()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            io.ktor.network.tls.certificates.CertificateBuilder$CertificateIssuer r2 = r0.issuer
            if (r2 == 0) goto L_0x0031
            javax.security.auth.x500.X500Principal r2 = r2.getName()
            if (r2 != 0) goto L_0x0033
        L_0x0031:
            javax.security.auth.x500.X500Principal r2 = r0.subject
        L_0x0033:
            r9 = r2
            javax.security.auth.x500.X500Principal r8 = r0.subject
            java.security.PublicKey r10 = r1.getPublic()
            io.ktor.network.tls.certificates.CertificateBuilder$CertificateIssuer r2 = r0.issuer
            if (r2 == 0) goto L_0x0047
            java.security.KeyPair r2 = r2.getKeyPair()
            if (r2 != 0) goto L_0x0045
            goto L_0x0047
        L_0x0045:
            r11 = r2
            goto L_0x0048
        L_0x0047:
            r11 = r1
        L_0x0048:
            java.lang.String r12 = r7.getName()
            kotlin.time.Duration$Companion r2 = kotlin.time.Duration.Companion
            long r2 = r0.daysValid
            kotlin.time.DurationUnit r4 = kotlin.time.DurationUnit.DAYS
            long r13 = kotlin.time.DurationKt.toDuration((long) r2, (kotlin.time.DurationUnit) r4)
            io.ktor.network.tls.certificates.KeyType r15 = r0.keyType
            java.util.List<java.lang.String> r2 = r0.domains
            java.util.List<? extends java.net.InetAddress> r3 = r0.ipAddresses
            java.lang.String r4 = "public"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r4)
            r16 = r2
            r17 = r3
            java.security.cert.X509Certificate r2 = io.ktor.network.tls.certificates.CertificatesKt.m1473generateX509CertificateTu6dINM(r8, r9, r10, r11, r12, r13, r15, r16, r17)
            io.ktor.network.tls.certificates.CertificateInfo r3 = new io.ktor.network.tls.certificates.CertificateInfo
            java.security.cert.Certificate r2 = (java.security.cert.Certificate) r2
            java.lang.String r4 = r18.getPassword()
            io.ktor.network.tls.certificates.CertificateBuilder$CertificateIssuer r5 = r0.issuer
            if (r5 == 0) goto L_0x007a
            java.security.cert.Certificate r5 = r5.getKeyCertificate()
            goto L_0x007b
        L_0x007a:
            r5 = 0
        L_0x007b:
            r3.<init>(r2, r1, r4, r5)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.tls.certificates.CertificateBuilder.build$ktor_network_tls_certificates():io.ktor.network.tls.certificates.CertificateInfo");
    }
}
