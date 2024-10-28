package io.ktor.network.tls;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/network/tls/CertificateAndKey;", "", "certificateChain", "", "Ljava/security/cert/X509Certificate;", "key", "Ljava/security/PrivateKey;", "([Ljava/security/cert/X509Certificate;Ljava/security/PrivateKey;)V", "getCertificateChain", "()[Ljava/security/cert/X509Certificate;", "[Ljava/security/cert/X509Certificate;", "getKey", "()Ljava/security/PrivateKey;", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSConfigJvm.kt */
public final class CertificateAndKey {
    private final X509Certificate[] certificateChain;
    private final PrivateKey key;

    public CertificateAndKey(X509Certificate[] x509CertificateArr, PrivateKey privateKey) {
        Intrinsics.checkNotNullParameter(x509CertificateArr, "certificateChain");
        Intrinsics.checkNotNullParameter(privateKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        this.certificateChain = x509CertificateArr;
        this.key = privateKey;
    }

    public final X509Certificate[] getCertificateChain() {
        return this.certificateChain;
    }

    public final PrivateKey getKey() {
        return this.key;
    }
}
