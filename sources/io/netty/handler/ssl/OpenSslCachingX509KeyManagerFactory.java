package io.netty.handler.ssl;

import io.netty.util.internal.ObjectUtil;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.X509KeyManager;

public final class OpenSslCachingX509KeyManagerFactory extends KeyManagerFactory {
    private final int maxCachedEntries;

    public OpenSslCachingX509KeyManagerFactory(KeyManagerFactory keyManagerFactory) {
        this(keyManagerFactory, 1024);
    }

    public OpenSslCachingX509KeyManagerFactory(final KeyManagerFactory keyManagerFactory, int i) {
        super(new KeyManagerFactorySpi() {
            /* access modifiers changed from: protected */
            public void engineInit(KeyStore keyStore, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
                keyManagerFactory.init(keyStore, cArr);
            }

            /* access modifiers changed from: protected */
            public void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
                keyManagerFactory.init(managerFactoryParameters);
            }

            /* access modifiers changed from: protected */
            public KeyManager[] engineGetKeyManagers() {
                return keyManagerFactory.getKeyManagers();
            }
        }, keyManagerFactory.getProvider(), keyManagerFactory.getAlgorithm());
        this.maxCachedEntries = ObjectUtil.checkPositive(i, "maxCachedEntries");
    }

    /* access modifiers changed from: package-private */
    public OpenSslKeyMaterialProvider newProvider(String str) {
        X509KeyManager chooseX509KeyManager = ReferenceCountedOpenSslContext.chooseX509KeyManager(getKeyManagers());
        if ("sun.security.ssl.X509KeyManagerImpl".equals(chooseX509KeyManager.getClass().getName())) {
            return new OpenSslKeyMaterialProvider(chooseX509KeyManager, str);
        }
        return new OpenSslCachingKeyMaterialProvider(ReferenceCountedOpenSslContext.chooseX509KeyManager(getKeyManagers()), str, this.maxCachedEntries);
    }
}
