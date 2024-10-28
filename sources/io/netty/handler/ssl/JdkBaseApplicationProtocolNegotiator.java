package io.netty.handler.ssl;

import io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import io.netty.util.internal.ObjectUtil;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLHandshakeException;

class JdkBaseApplicationProtocolNegotiator implements JdkApplicationProtocolNegotiator {
    static final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory FAIL_SELECTION_LISTENER_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory() {
        public JdkApplicationProtocolNegotiator.ProtocolSelectionListener newListener(SSLEngine sSLEngine, List<String> list) {
            return new FailProtocolSelectionListener((JdkSslEngine) sSLEngine, list);
        }
    };
    static final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory FAIL_SELECTOR_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectorFactory() {
        public JdkApplicationProtocolNegotiator.ProtocolSelector newSelector(SSLEngine sSLEngine, Set<String> set) {
            return new FailProtocolSelector((JdkSslEngine) sSLEngine, set);
        }
    };
    static final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory NO_FAIL_SELECTION_LISTENER_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory() {
        public JdkApplicationProtocolNegotiator.ProtocolSelectionListener newListener(SSLEngine sSLEngine, List<String> list) {
            return new NoFailProtocolSelectionListener((JdkSslEngine) sSLEngine, list);
        }
    };
    static final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory NO_FAIL_SELECTOR_FACTORY = new JdkApplicationProtocolNegotiator.ProtocolSelectorFactory() {
        public JdkApplicationProtocolNegotiator.ProtocolSelector newSelector(SSLEngine sSLEngine, Set<String> set) {
            return new NoFailProtocolSelector((JdkSslEngine) sSLEngine, set);
        }
    };
    private final JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory listenerFactory;
    private final List<String> protocols;
    private final JdkApplicationProtocolNegotiator.ProtocolSelectorFactory selectorFactory;
    private final JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory;

    JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory sslEngineWrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, Iterable<String> iterable) {
        this(sslEngineWrapperFactory, protocolSelectorFactory, protocolSelectionListenerFactory, ApplicationProtocolUtil.toList(iterable));
    }

    JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory sslEngineWrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, String... strArr) {
        this(sslEngineWrapperFactory, protocolSelectorFactory, protocolSelectionListenerFactory, ApplicationProtocolUtil.toList(strArr));
    }

    private JdkBaseApplicationProtocolNegotiator(JdkApplicationProtocolNegotiator.SslEngineWrapperFactory sslEngineWrapperFactory, JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory, JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolSelectionListenerFactory, List<String> list) {
        this.wrapperFactory = (JdkApplicationProtocolNegotiator.SslEngineWrapperFactory) ObjectUtil.checkNotNull(sslEngineWrapperFactory, "wrapperFactory");
        this.selectorFactory = (JdkApplicationProtocolNegotiator.ProtocolSelectorFactory) ObjectUtil.checkNotNull(protocolSelectorFactory, "selectorFactory");
        this.listenerFactory = (JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory) ObjectUtil.checkNotNull(protocolSelectionListenerFactory, "listenerFactory");
        this.protocols = Collections.unmodifiableList((List) ObjectUtil.checkNotNull(list, "protocols"));
    }

    public List<String> protocols() {
        return this.protocols;
    }

    public JdkApplicationProtocolNegotiator.ProtocolSelectorFactory protocolSelectorFactory() {
        return this.selectorFactory;
    }

    public JdkApplicationProtocolNegotiator.ProtocolSelectionListenerFactory protocolListenerFactory() {
        return this.listenerFactory;
    }

    public JdkApplicationProtocolNegotiator.SslEngineWrapperFactory wrapperFactory() {
        return this.wrapperFactory;
    }

    static class NoFailProtocolSelector implements JdkApplicationProtocolNegotiator.ProtocolSelector {
        private final JdkSslEngine engineWrapper;
        private final Set<String> supportedProtocols;

        NoFailProtocolSelector(JdkSslEngine jdkSslEngine, Set<String> set) {
            this.engineWrapper = jdkSslEngine;
            this.supportedProtocols = set;
        }

        public void unsupported() {
            this.engineWrapper.setNegotiatedApplicationProtocol((String) null);
        }

        public String select(List<String> list) throws Exception {
            for (String next : this.supportedProtocols) {
                if (list.contains(next)) {
                    this.engineWrapper.setNegotiatedApplicationProtocol(next);
                    return next;
                }
            }
            return noSelectMatchFound();
        }

        public String noSelectMatchFound() throws Exception {
            this.engineWrapper.setNegotiatedApplicationProtocol((String) null);
            return null;
        }
    }

    private static final class FailProtocolSelector extends NoFailProtocolSelector {
        FailProtocolSelector(JdkSslEngine jdkSslEngine, Set<String> set) {
            super(jdkSslEngine, set);
        }

        public String noSelectMatchFound() throws Exception {
            throw new SSLHandshakeException("Selected protocol is not supported");
        }
    }

    private static class NoFailProtocolSelectionListener implements JdkApplicationProtocolNegotiator.ProtocolSelectionListener {
        private final JdkSslEngine engineWrapper;
        private final List<String> supportedProtocols;

        /* access modifiers changed from: protected */
        public void noSelectedMatchFound(String str) throws Exception {
        }

        NoFailProtocolSelectionListener(JdkSslEngine jdkSslEngine, List<String> list) {
            this.engineWrapper = jdkSslEngine;
            this.supportedProtocols = list;
        }

        public void unsupported() {
            this.engineWrapper.setNegotiatedApplicationProtocol((String) null);
        }

        public void selected(String str) throws Exception {
            if (this.supportedProtocols.contains(str)) {
                this.engineWrapper.setNegotiatedApplicationProtocol(str);
            } else {
                noSelectedMatchFound(str);
            }
        }
    }

    private static final class FailProtocolSelectionListener extends NoFailProtocolSelectionListener {
        FailProtocolSelectionListener(JdkSslEngine jdkSslEngine, List<String> list) {
            super(jdkSslEngine, list);
        }

        /* access modifiers changed from: protected */
        public void noSelectedMatchFound(String str) throws Exception {
            throw new SSLHandshakeException("No compatible protocols found");
        }
    }
}
