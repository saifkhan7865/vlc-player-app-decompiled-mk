package io.netty.handler.ssl;

import io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.util.LinkedHashSet;
import java.util.List;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import org.eclipse.jetty.alpn.ALPN;

abstract class JettyAlpnSslEngine extends JdkSslEngine {
    private static final boolean available = initAvailable();

    static boolean isAvailable() {
        return available;
    }

    private static boolean initAvailable() {
        if (PlatformDependent.javaVersion() > 8) {
            return false;
        }
        try {
            Class.forName("sun.security.ssl.ALPNExtension", true, (ClassLoader) null);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    static JettyAlpnSslEngine newClientEngine(SSLEngine sSLEngine, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
        return new ClientEngine(sSLEngine, jdkApplicationProtocolNegotiator);
    }

    static JettyAlpnSslEngine newServerEngine(SSLEngine sSLEngine, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
        return new ServerEngine(sSLEngine, jdkApplicationProtocolNegotiator);
    }

    private JettyAlpnSslEngine(SSLEngine sSLEngine) {
        super(sSLEngine);
    }

    private static final class ClientEngine extends JettyAlpnSslEngine {
        ClientEngine(SSLEngine sSLEngine, final JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
            super(sSLEngine);
            ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator, "applicationNegotiator");
            final JdkApplicationProtocolNegotiator.ProtocolSelectionListener protocolSelectionListener = (JdkApplicationProtocolNegotiator.ProtocolSelectionListener) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolListenerFactory().newListener(this, jdkApplicationProtocolNegotiator.protocols()), "protocolListener");
            ALPN.put(sSLEngine, (ALPN.Provider) new ALPN.ClientProvider() {
                public List<String> protocols() {
                    return jdkApplicationProtocolNegotiator.protocols();
                }

                public void selected(String str) throws SSLException {
                    try {
                        protocolSelectionListener.selected(str);
                    } catch (Throwable th) {
                        throw SslUtils.toSSLHandshakeException(th);
                    }
                }

                public void unsupported() {
                    protocolSelectionListener.unsupported();
                }
            });
        }

        public void closeInbound() throws SSLException {
            try {
                ALPN.remove(getWrappedEngine());
            } finally {
                JettyAlpnSslEngine.super.closeInbound();
            }
        }

        public void closeOutbound() {
            try {
                ALPN.remove(getWrappedEngine());
            } finally {
                JettyAlpnSslEngine.super.closeOutbound();
            }
        }
    }

    private static final class ServerEngine extends JettyAlpnSslEngine {
        ServerEngine(SSLEngine sSLEngine, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator) {
            super(sSLEngine);
            ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator, "applicationNegotiator");
            final JdkApplicationProtocolNegotiator.ProtocolSelector protocolSelector = (JdkApplicationProtocolNegotiator.ProtocolSelector) ObjectUtil.checkNotNull(jdkApplicationProtocolNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(jdkApplicationProtocolNegotiator.protocols())), "protocolSelector");
            ALPN.put(sSLEngine, (ALPN.Provider) new ALPN.ServerProvider() {
                public String select(List<String> list) throws SSLException {
                    try {
                        return protocolSelector.select(list);
                    } catch (Throwable th) {
                        throw SslUtils.toSSLHandshakeException(th);
                    }
                }

                public void unsupported() {
                    protocolSelector.unsupported();
                }
            });
        }

        public void closeInbound() throws SSLException {
            try {
                ALPN.remove(getWrappedEngine());
            } finally {
                JettyAlpnSslEngine.super.closeInbound();
            }
        }

        public void closeOutbound() {
            try {
                ALPN.remove(getWrappedEngine());
            } finally {
                JettyAlpnSslEngine.super.closeOutbound();
            }
        }
    }
}