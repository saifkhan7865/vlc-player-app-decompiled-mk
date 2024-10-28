package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import java.security.cert.Certificate;
import java.util.Map;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;

public abstract class OpenSslContext extends ReferenceCountedOpenSslContext {
    OpenSslContext(Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, ApplicationProtocolConfig applicationProtocolConfig, int i, Certificate[] certificateArr, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, Map.Entry<SslContextOption<?>, Object>... entryArr) throws SSLException {
        super(iterable, cipherSuiteFilter, toNegotiator(applicationProtocolConfig), i, certificateArr, clientAuth, strArr, z, z2, false, entryArr);
    }

    OpenSslContext(Iterable<String> iterable, CipherSuiteFilter cipherSuiteFilter, OpenSslApplicationProtocolNegotiator openSslApplicationProtocolNegotiator, int i, Certificate[] certificateArr, ClientAuth clientAuth, String[] strArr, boolean z, boolean z2, Map.Entry<SslContextOption<?>, Object>... entryArr) throws SSLException {
        super(iterable, cipherSuiteFilter, openSslApplicationProtocolNegotiator, i, certificateArr, clientAuth, strArr, z, z2, false, entryArr);
    }

    /* access modifiers changed from: package-private */
    public final SSLEngine newEngine0(ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        return new OpenSslEngine(this, byteBufAllocator, str, i, z);
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        super.finalize();
        OpenSsl.releaseIfNeeded(this);
    }
}
