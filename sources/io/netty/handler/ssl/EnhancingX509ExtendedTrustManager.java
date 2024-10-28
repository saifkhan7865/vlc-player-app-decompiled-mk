package io.netty.handler.ssl;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import java.net.Socket;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedTrustManager;
import javax.net.ssl.X509TrustManager;
import org.fusesource.jansi.AnsiRenderer;

final class EnhancingX509ExtendedTrustManager extends X509ExtendedTrustManager {
    private final X509ExtendedTrustManager wrapped;

    EnhancingX509ExtendedTrustManager(X509TrustManager x509TrustManager) {
        this.wrapped = NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) x509TrustManager);
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        this.wrapped.checkClientTrusted(x509CertificateArr, str, socket);
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, Socket socket) throws CertificateException {
        try {
            this.wrapped.checkServerTrusted(x509CertificateArr, str, socket);
        } catch (CertificateException e) {
            throwEnhancedCertificateException(x509CertificateArr, e);
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        this.wrapped.checkClientTrusted(x509CertificateArr, str, sSLEngine);
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str, SSLEngine sSLEngine) throws CertificateException {
        try {
            this.wrapped.checkServerTrusted(x509CertificateArr, str, sSLEngine);
        } catch (CertificateException e) {
            throwEnhancedCertificateException(x509CertificateArr, e);
        }
    }

    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        this.wrapped.checkClientTrusted(x509CertificateArr, str);
    }

    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
        try {
            this.wrapped.checkServerTrusted(x509CertificateArr, str);
        } catch (CertificateException e) {
            throwEnhancedCertificateException(x509CertificateArr, e);
        }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return this.wrapped.getAcceptedIssuers();
    }

    private static void throwEnhancedCertificateException(X509Certificate[] x509CertificateArr, CertificateException certificateException) throws CertificateException {
        String message = certificateException.getMessage();
        if (message != null && certificateException.getMessage().startsWith("No subject alternative DNS name matching")) {
            StringBuilder sb = new StringBuilder(64);
            for (X509Certificate subjectAlternativeNames : x509CertificateArr) {
                Collection<List<?>> subjectAlternativeNames2 = subjectAlternativeNames.getSubjectAlternativeNames();
                if (subjectAlternativeNames2 != null) {
                    for (List next : subjectAlternativeNames2) {
                        if (next.size() >= 2 && ((Integer) next.get(0)).intValue() == 2) {
                            sb.append((String) next.get(1));
                            sb.append(AnsiRenderer.CODE_LIST_SEPARATOR);
                        }
                    }
                }
            }
            if (sb.length() != 0) {
                sb.setLength(sb.length() - 1);
                throw new CertificateException(message + " Subject alternative DNS names in the certificate chain of " + x509CertificateArr.length + " certificate(s): " + sb, certificateException);
            }
        }
        throw certificateException;
    }
}
