package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import java.util.List;
import javax.net.ssl.SSLEngine;

@Deprecated
public final class JdkAlpnApplicationProtocolNegotiator extends JdkBaseApplicationProtocolNegotiator {
    private static final JdkApplicationProtocolNegotiator.SslEngineWrapperFactory ALPN_WRAPPER;
    private static final boolean AVAILABLE;

    public /* bridge */ /* synthetic */ JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolListenerFactory() {
        return super.protocolListenerFactory();
    }

    public /* bridge */ /* synthetic */ JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory() {
        return super.protocolSelectorFactory();
    }

    public /* bridge */ /* synthetic */ List protocols() {
        return super.protocols();
    }

    public /* bridge */ /* synthetic */ JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory() {
        return super.wrapperFactory();
    }

    static {
        boolean z = Conscrypt.isAvailable() || JdkAlpnSslUtils.supportsAlpn() || JettyAlpnSslEngine.isAvailable() || BouncyCastle.isAvailable();
        AVAILABLE = z;
        ALPN_WRAPPER = z ? new AlpnWrapper() : new FailureWrapper();
    }

    public JdkAlpnApplicationProtocolNegotiator(Iterable<String> iterable) {
        this(false, iterable);
    }

    public JdkAlpnApplicationProtocolNegotiator(String... strArr) {
        this(false, strArr);
    }

    public JdkAlpnApplicationProtocolNegotiator(boolean z, Iterable<String> iterable) {
        this(z, z, iterable);
    }

    public JdkAlpnApplicationProtocolNegotiator(boolean z, String... strArr) {
        this(z, z, strArr);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JdkAlpnApplicationProtocolNegotiator(boolean z, boolean z2, Iterable<String> iterable) {
        this(z2 ? FAIL_SELECTOR_FACTORY : NO_FAIL_SELECTOR_FACTORY, z ? FAIL_SELECTION_LISTENER_FACTORY : NO_FAIL_SELECTION_LISTENER_FACTORY, iterable);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public JdkAlpnApplicationProtocolNegotiator(boolean z, boolean z2, String... strArr) {
        this(z2 ? FAIL_SELECTOR_FACTORY : NO_FAIL_SELECTOR_FACTORY, z ? FAIL_SELECTION_LISTENER_FACTORY : NO_FAIL_SELECTION_LISTENER_FACTORY, strArr);
    }

    public JdkAlpnApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, Iterable<String> iterable) {
        super(ALPN_WRAPPER, protocolSelectorFactory, protocolSelectionListenerFactory, iterable);
    }

    public JdkAlpnApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, String... strArr) {
        super(ALPN_WRAPPER, protocolSelectorFactory, protocolSelectionListenerFactory, strArr);
    }

    private static final class FailureWrapper extends JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory {
        private FailureWrapper() {
        }

        public SSLEngine wrapSslEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
            throw new RuntimeException("ALPN unsupported. Is your classpath configured correctly? For Conscrypt, add the appropriate Conscrypt JAR to classpath and set the security provider. For Jetty-ALPN, see https://www.eclipse.org/jetty/documentation/current/alpn-chapter.html#alpn-starting");
        }
    }

    private static final class AlpnWrapper extends JdkApplicationProtocolNegotiator.AllocatorAwareSslEngineWrapperFactory {
        private AlpnWrapper() {
        }

        public SSLEngine wrapSslEngine(SSLEngine sSLEngine, ByteBufAllocator byteBufAllocator, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
            if (Conscrypt.isEngineSupported(sSLEngine)) {
                if (z) {
                    return ConscryptAlpnSslEngine.newServerEngine(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator);
                }
                return ConscryptAlpnSslEngine.newClientEngine(sSLEngine, byteBufAllocator, jdkApplicationProtocolNegotiator);
            } else if (BouncyCastle.isInUse(sSLEngine)) {
                return new BouncyCastleAlpnSslEngine(sSLEngine, jdkApplicationProtocolNegotiator, z);
            } else {
                if (JdkAlpnSslUtils.supportsAlpn()) {
                    return new JdkAlpnSslEngine(sSLEngine, jdkApplicationProtocolNegotiator, z);
                }
                if (!JettyAlpnSslEngine.isAvailable()) {
                    throw new UnsupportedOperationException("ALPN not supported. Unable to wrap SSLEngine of type '" + sSLEngine.getClass().getName() + "')");
                } else if (z) {
                    return JettyAlpnSslEngine.newServerEngine(sSLEngine, jdkApplicationProtocolNegotiator);
                } else {
                    return JettyAlpnSslEngine.newClientEngine(sSLEngine, jdkApplicationProtocolNegotiator);
                }
            }
        }
    }

    static boolean isAlpnSupported() {
        return AVAILABLE;
    }
}
