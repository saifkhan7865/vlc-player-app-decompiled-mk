package io.netty.handler.ssl.util;

import io.netty.util.internal.ObjectUtil;
import java.security.KeyStore;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;

public final class TrustManagerFactoryWrapper extends SimpleTrustManagerFactory {
    private final TrustManager tm;

    /* access modifiers changed from: protected */
    public void engineInit(KeyStore keyStore) throws Exception {
    }

    /* access modifiers changed from: protected */
    public void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception {
    }

    public TrustManagerFactoryWrapper(TrustManager trustManager) {
        this.tm = (TrustManager) ObjectUtil.checkNotNull(trustManager, "tm");
    }

    /* access modifiers changed from: protected */
    public TrustManager[] engineGetTrustManagers() {
        return new TrustManager[]{this.tm};
    }
}
