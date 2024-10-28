package io.ktor.network.tls;

import java.security.SecureRandom;
import java.util.List;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Lio/ktor/network/tls/TLSConfig;", "", "random", "Ljava/security/SecureRandom;", "certificates", "", "Lio/ktor/network/tls/CertificateAndKey;", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "cipherSuites", "Lio/ktor/network/tls/CipherSuite;", "serverName", "", "(Ljava/security/SecureRandom;Ljava/util/List;Ljavax/net/ssl/X509TrustManager;Ljava/util/List;Ljava/lang/String;)V", "getCertificates", "()Ljava/util/List;", "getCipherSuites", "getRandom", "()Ljava/security/SecureRandom;", "getServerName", "()Ljava/lang/String;", "getTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSConfigJvm.kt */
public final class TLSConfig {
    private final List<CertificateAndKey> certificates;
    private final List<CipherSuite> cipherSuites;
    private final SecureRandom random;
    private final String serverName;
    private final X509TrustManager trustManager;

    public TLSConfig(SecureRandom secureRandom, List<CertificateAndKey> list, X509TrustManager x509TrustManager, List<CipherSuite> list2, String str) {
        Intrinsics.checkNotNullParameter(secureRandom, "random");
        Intrinsics.checkNotNullParameter(list, "certificates");
        Intrinsics.checkNotNullParameter(x509TrustManager, "trustManager");
        Intrinsics.checkNotNullParameter(list2, "cipherSuites");
        this.random = secureRandom;
        this.certificates = list;
        this.trustManager = x509TrustManager;
        this.cipherSuites = list2;
        this.serverName = str;
    }

    public final SecureRandom getRandom() {
        return this.random;
    }

    public final List<CertificateAndKey> getCertificates() {
        return this.certificates;
    }

    public final X509TrustManager getTrustManager() {
        return this.trustManager;
    }

    public final List<CipherSuite> getCipherSuites() {
        return this.cipherSuites;
    }

    public final String getServerName() {
        return this.serverName;
    }
}
