package io.netty.handler.ssl;

import io.netty.handler.ssl.JdkApplicationProtocolNegotiator;
import j$.util.function.BiConsumer$CC;
import j$.util.function.BiFunction$CC;
import java.nio.ByteBuffer;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;

class JdkAlpnSslEngine extends JdkSslEngine {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final AlpnSelector alpnSelector;
    private final JdkApplicationProtocolNegotiator.ProtocolSelectionListener selectionListener;

    /* access modifiers changed from: package-private */
    public void setNegotiatedApplicationProtocol(String str) {
    }

    final class AlpnSelector implements BiFunction<SSLEngine, List<String>, String> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean called;
        private final JdkApplicationProtocolNegotiator.ProtocolSelector selector;

        public /* synthetic */ BiFunction andThen(Function function) {
            return BiFunction$CC.$default$andThen(this, function);
        }

        static {
            Class<JdkAlpnSslEngine> cls = JdkAlpnSslEngine.class;
        }

        AlpnSelector(JdkApplicationProtocolNegotiator.ProtocolSelector protocolSelector) {
            this.selector = protocolSelector;
        }

        public String apply(SSLEngine sSLEngine, List<String> list) {
            this.called = true;
            try {
                String select = this.selector.select(list);
                return select == null ? "" : select;
            } catch (Exception unused) {
                return null;
            }
        }

        /* access modifiers changed from: package-private */
        public void checkUnsupported() {
            if (!this.called && JdkAlpnSslEngine.this.getApplicationProtocol().isEmpty()) {
                this.selector.unsupported();
            }
        }
    }

    JdkAlpnSslEngine(SSLEngine sSLEngine, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z, BiConsumer<SSLEngine, AlpnSelector> biConsumer, BiConsumer<SSLEngine, List<String>> biConsumer2) {
        super(sSLEngine);
        if (z) {
            this.selectionListener = null;
            AlpnSelector alpnSelector2 = new AlpnSelector(jdkApplicationProtocolNegotiator.protocolSelectorFactory().newSelector(this, new LinkedHashSet(jdkApplicationProtocolNegotiator.protocols())));
            this.alpnSelector = alpnSelector2;
            biConsumer.accept(sSLEngine, alpnSelector2);
            return;
        }
        this.selectionListener = jdkApplicationProtocolNegotiator.protocolListenerFactory().newListener(this, jdkApplicationProtocolNegotiator.protocols());
        this.alpnSelector = null;
        biConsumer2.accept(sSLEngine, jdkApplicationProtocolNegotiator.protocols());
    }

    JdkAlpnSslEngine(SSLEngine sSLEngine, JdkApplicationProtocolNegotiator jdkApplicationProtocolNegotiator, boolean z) {
        this(sSLEngine, jdkApplicationProtocolNegotiator, z, new BiConsumer<SSLEngine, AlpnSelector>() {
            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }

            public void accept(SSLEngine sSLEngine, AlpnSelector alpnSelector) {
                JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(sSLEngine, alpnSelector);
            }
        }, new BiConsumer<SSLEngine, List<String>>() {
            public /* synthetic */ BiConsumer andThen(BiConsumer biConsumer) {
                return BiConsumer$CC.$default$andThen(this, biConsumer);
            }

            public void accept(SSLEngine sSLEngine, List<String> list) {
                JdkAlpnSslUtils.setApplicationProtocols(sSLEngine, list);
            }
        });
    }

    private SSLEngineResult verifyProtocolSelection(SSLEngineResult sSLEngineResult) throws SSLException {
        if (sSLEngineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
            AlpnSelector alpnSelector2 = this.alpnSelector;
            if (alpnSelector2 == null) {
                try {
                    String applicationProtocol = getApplicationProtocol();
                    if (applicationProtocol.isEmpty()) {
                        this.selectionListener.unsupported();
                    } else {
                        this.selectionListener.selected(applicationProtocol);
                    }
                } catch (Throwable th) {
                    throw SslUtils.toSSLHandshakeException(th);
                }
            } else {
                alpnSelector2.checkUnsupported();
            }
        }
        return sSLEngineResult;
    }

    public SSLEngineResult wrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        return verifyProtocolSelection(super.wrap(byteBuffer, byteBuffer2));
    }

    public SSLEngineResult wrap(ByteBuffer[] byteBufferArr, ByteBuffer byteBuffer) throws SSLException {
        return verifyProtocolSelection(super.wrap(byteBufferArr, byteBuffer));
    }

    public SSLEngineResult wrap(ByteBuffer[] byteBufferArr, int i, int i2, ByteBuffer byteBuffer) throws SSLException {
        return verifyProtocolSelection(super.wrap(byteBufferArr, i, i2, byteBuffer));
    }

    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        return verifyProtocolSelection(super.unwrap(byteBuffer, byteBuffer2));
    }

    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) throws SSLException {
        return verifyProtocolSelection(super.unwrap(byteBuffer, byteBufferArr));
    }

    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i, int i2) throws SSLException {
        return verifyProtocolSelection(super.unwrap(byteBuffer, byteBufferArr, i, i2));
    }

    public String getNegotiatedApplicationProtocol() {
        String applicationProtocol = getApplicationProtocol();
        if (applicationProtocol == null) {
            return null;
        }
        if (applicationProtocol.isEmpty()) {
            return null;
        }
        return applicationProtocol;
    }

    public String getApplicationProtocol() {
        return JdkAlpnSslUtils.getApplicationProtocol(getWrappedEngine());
    }

    public String getHandshakeApplicationProtocol() {
        return JdkAlpnSslUtils.getHandshakeApplicationProtocol(getWrappedEngine());
    }

    public void setHandshakeApplicationProtocolSelector(BiFunction<SSLEngine, List<String>, String> biFunction) {
        JdkAlpnSslUtils.setHandshakeApplicationProtocolSelector(getWrappedEngine(), biFunction);
    }

    public BiFunction<SSLEngine, List<String>, String> getHandshakeApplicationProtocolSelector() {
        return JdkAlpnSslUtils.getHandshakeApplicationProtocolSelector(getWrappedEngine());
    }
}
