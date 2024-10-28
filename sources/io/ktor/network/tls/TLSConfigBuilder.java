package io.ktor.network.tls;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010!\u001a\u00020\"R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R(\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006#"}, d2 = {"Lio/ktor/network/tls/TLSConfigBuilder;", "", "()V", "certificates", "", "Lio/ktor/network/tls/CertificateAndKey;", "getCertificates", "()Ljava/util/List;", "cipherSuites", "", "Lio/ktor/network/tls/CipherSuite;", "getCipherSuites", "setCipherSuites", "(Ljava/util/List;)V", "random", "Ljava/security/SecureRandom;", "getRandom", "()Ljava/security/SecureRandom;", "setRandom", "(Ljava/security/SecureRandom;)V", "serverName", "", "getServerName", "()Ljava/lang/String;", "setServerName", "(Ljava/lang/String;)V", "value", "Ljavax/net/ssl/TrustManager;", "trustManager", "getTrustManager", "()Ljavax/net/ssl/TrustManager;", "setTrustManager", "(Ljavax/net/ssl/TrustManager;)V", "build", "Lio/ktor/network/tls/TLSConfig;", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSConfigBuilder.kt */
public final class TLSConfigBuilder {
    private final List<CertificateAndKey> certificates = new ArrayList();
    private List<CipherSuite> cipherSuites = CIOCipherSuites.INSTANCE.getSupportedSuites();
    private SecureRandom random;
    private String serverName;
    private TrustManager trustManager;

    public final List<CertificateAndKey> getCertificates() {
        return this.certificates;
    }

    public final SecureRandom getRandom() {
        return this.random;
    }

    public final void setRandom(SecureRandom secureRandom) {
        this.random = secureRandom;
    }

    public final TrustManager getTrustManager() {
        return this.trustManager;
    }

    public final void setTrustManager(TrustManager trustManager2) {
        if (trustManager2 == null || (trustManager2 instanceof X509TrustManager)) {
            this.trustManager = trustManager2;
            return;
        }
        throw new IllegalStateException(("Failed to set [trustManager]: " + trustManager2 + ". Only [X509TrustManager] supported.").toString());
    }

    public final List<CipherSuite> getCipherSuites() {
        return this.cipherSuites;
    }

    public final void setCipherSuites(List<CipherSuite> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.cipherSuites = list;
    }

    public final String getServerName() {
        return this.serverName;
    }

    public final void setServerName(String str) {
        this.serverName = str;
    }

    public final TLSConfig build() {
        SecureRandom secureRandom = this.random;
        if (secureRandom == null) {
            secureRandom = new SecureRandom();
        }
        SecureRandom secureRandom2 = secureRandom;
        List<CertificateAndKey> list = this.certificates;
        TrustManager trustManager2 = this.trustManager;
        X509TrustManager x509TrustManager = trustManager2 instanceof X509TrustManager ? (X509TrustManager) trustManager2 : null;
        if (x509TrustManager == null) {
            x509TrustManager = TLSConfigBuilderKt.findTrustManager();
        }
        return new TLSConfig(secureRandom2, list, x509TrustManager, this.cipherSuites, this.serverName);
    }
}
