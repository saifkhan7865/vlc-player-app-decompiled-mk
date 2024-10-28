package io.netty.handler.ssl.util;

import io.netty.util.internal.ObjectUtil;
import java.security.KeyStore;
import javax.net.ssl.KeyManager;
import javax.net.ssl.ManagerFactoryParameters;

public final class KeyManagerFactoryWrapper extends SimpleKeyManagerFactory {
    private final KeyManager km;

    /* access modifiers changed from: protected */
    public void engineInit(KeyStore keyStore, char[] cArr) throws Exception {
    }

    /* access modifiers changed from: protected */
    public void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception {
    }

    public KeyManagerFactoryWrapper(KeyManager keyManager) {
        this.km = (KeyManager) ObjectUtil.checkNotNull(keyManager, "km");
    }

    /* access modifiers changed from: protected */
    public KeyManager[] engineGetKeyManagers() {
        return new KeyManager[]{this.km};
    }
}
