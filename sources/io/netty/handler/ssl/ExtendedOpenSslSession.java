package io.netty.handler.ssl;

import io.netty.util.internal.EmptyArrays;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.ExtendedSSLSession;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSessionBindingEvent;
import javax.net.ssl.SSLSessionBindingListener;
import javax.security.cert.X509Certificate;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

abstract class ExtendedOpenSslSession extends ExtendedSSLSession implements OpenSslSession {
    private static final String[] LOCAL_SUPPORTED_SIGNATURE_ALGORITHMS = {"SHA512withRSA", "SHA512withECDSA", "SHA384withRSA", "SHA384withECDSA", "SHA256withRSA", "SHA256withECDSA", "SHA224withRSA", "SHA224withECDSA", "SHA1withRSA", "SHA1withECDSA", "RSASSA-PSS"};
    private final OpenSslSession wrapped;

    public abstract List getRequestedServerNames();

    ExtendedOpenSslSession(OpenSslSession openSslSession) {
        this.wrapped = openSslSession;
    }

    public List<byte[]> getStatusResponses() {
        return Collections.emptyList();
    }

    public OpenSslSessionId sessionId() {
        return this.wrapped.sessionId();
    }

    public void setSessionId(OpenSslSessionId openSslSessionId) {
        this.wrapped.setSessionId(openSslSessionId);
    }

    public final void setLocalCertificate(Certificate[] certificateArr) {
        this.wrapped.setLocalCertificate(certificateArr);
    }

    public String[] getPeerSupportedSignatureAlgorithms() {
        return EmptyArrays.EMPTY_STRINGS;
    }

    public final void tryExpandApplicationBufferSize(int i) {
        this.wrapped.tryExpandApplicationBufferSize(i);
    }

    public final String[] getLocalSupportedSignatureAlgorithms() {
        return (String[]) LOCAL_SUPPORTED_SIGNATURE_ALGORITHMS.clone();
    }

    public final byte[] getId() {
        return this.wrapped.getId();
    }

    public final OpenSslSessionContext getSessionContext() {
        return this.wrapped.getSessionContext();
    }

    public final long getCreationTime() {
        return this.wrapped.getCreationTime();
    }

    public final long getLastAccessedTime() {
        return this.wrapped.getLastAccessedTime();
    }

    public final void invalidate() {
        this.wrapped.invalidate();
    }

    public final boolean isValid() {
        return this.wrapped.isValid();
    }

    public final void putValue(String str, Object obj) {
        if (obj instanceof SSLSessionBindingListener) {
            obj = new SSLSessionBindingListenerDecorator((SSLSessionBindingListener) obj);
        }
        this.wrapped.putValue(str, obj);
    }

    public final Object getValue(String str) {
        Object value = this.wrapped.getValue(str);
        return value instanceof SSLSessionBindingListenerDecorator ? ((SSLSessionBindingListenerDecorator) value).delegate : value;
    }

    public final void removeValue(String str) {
        this.wrapped.removeValue(str);
    }

    public final String[] getValueNames() {
        return this.wrapped.getValueNames();
    }

    public final Certificate[] getPeerCertificates() throws SSLPeerUnverifiedException {
        return this.wrapped.getPeerCertificates();
    }

    public final Certificate[] getLocalCertificates() {
        return this.wrapped.getLocalCertificates();
    }

    public final X509Certificate[] getPeerCertificateChain() throws SSLPeerUnverifiedException {
        return this.wrapped.getPeerCertificateChain();
    }

    public final Principal getPeerPrincipal() throws SSLPeerUnverifiedException {
        return this.wrapped.getPeerPrincipal();
    }

    public final Principal getLocalPrincipal() {
        return this.wrapped.getLocalPrincipal();
    }

    public final String getCipherSuite() {
        return this.wrapped.getCipherSuite();
    }

    public String getProtocol() {
        return this.wrapped.getProtocol();
    }

    public final String getPeerHost() {
        return this.wrapped.getPeerHost();
    }

    public final int getPeerPort() {
        return this.wrapped.getPeerPort();
    }

    public final int getPacketBufferSize() {
        return this.wrapped.getPacketBufferSize();
    }

    public final int getApplicationBufferSize() {
        return this.wrapped.getApplicationBufferSize();
    }

    private final class SSLSessionBindingListenerDecorator implements SSLSessionBindingListener {
        final SSLSessionBindingListener delegate;

        SSLSessionBindingListenerDecorator(SSLSessionBindingListener sSLSessionBindingListener) {
            this.delegate = sSLSessionBindingListener;
        }

        public void valueBound(SSLSessionBindingEvent sSLSessionBindingEvent) {
            this.delegate.valueBound(new SSLSessionBindingEvent(ExtendedOpenSslSession.this, sSLSessionBindingEvent.getName()));
        }

        public void valueUnbound(SSLSessionBindingEvent sSLSessionBindingEvent) {
            this.delegate.valueUnbound(new SSLSessionBindingEvent(ExtendedOpenSslSession.this, sSLSessionBindingEvent.getName()));
        }
    }

    public void handshakeFinished(byte[] bArr, String str, String str2, byte[] bArr2, byte[][] bArr3, long j, long j2) throws SSLException {
        this.wrapped.handshakeFinished(bArr, str, str2, bArr2, bArr3, j, j2);
    }

    public String toString() {
        return "ExtendedOpenSslSession{wrapped=" + this.wrapped + AbstractJsonLexerKt.END_OBJ;
    }
}
