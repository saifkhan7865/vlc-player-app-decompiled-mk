package io.netty.handler.ssl.util;

import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.PlatformDependent;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Provider;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;
import javax.net.ssl.X509TrustManager;

public abstract class SimpleTrustManagerFactory extends TrustManagerFactory {
    private static final FastThreadLocal<SimpleTrustManagerFactorySpi> CURRENT_SPI = new FastThreadLocal<SimpleTrustManagerFactorySpi>() {
        /* access modifiers changed from: protected */
        public SimpleTrustManagerFactorySpi initialValue() {
            return new SimpleTrustManagerFactorySpi();
        }
    };
    private static final Provider PROVIDER = new Provider("", 0.0d, "") {
        private static final long serialVersionUID = -2680540247105807895L;
    };

    /* access modifiers changed from: protected */
    public abstract TrustManager[] engineGetTrustManagers();

    /* access modifiers changed from: protected */
    public abstract void engineInit(KeyStore keyStore) throws Exception;

    /* access modifiers changed from: protected */
    public abstract void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception;

    protected SimpleTrustManagerFactory() {
        this("");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected SimpleTrustManagerFactory(java.lang.String r4) {
        /*
            r3 = this;
            io.netty.util.concurrent.FastThreadLocal<io.netty.handler.ssl.util.SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi> r0 = CURRENT_SPI
            java.lang.Object r1 = r0.get()
            javax.net.ssl.TrustManagerFactorySpi r1 = (javax.net.ssl.TrustManagerFactorySpi) r1
            java.security.Provider r2 = PROVIDER
            r3.<init>(r1, r2, r4)
            java.lang.Object r1 = r0.get()
            io.netty.handler.ssl.util.SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi r1 = (io.netty.handler.ssl.util.SimpleTrustManagerFactory.SimpleTrustManagerFactorySpi) r1
            r1.init(r3)
            r0.remove()
            java.lang.String r0 = "name"
            io.netty.util.internal.ObjectUtil.checkNotNull(r4, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.util.SimpleTrustManagerFactory.<init>(java.lang.String):void");
    }

    static final class SimpleTrustManagerFactorySpi extends TrustManagerFactorySpi {
        private SimpleTrustManagerFactory parent;
        private volatile TrustManager[] trustManagers;

        SimpleTrustManagerFactorySpi() {
        }

        /* access modifiers changed from: package-private */
        public void init(SimpleTrustManagerFactory simpleTrustManagerFactory) {
            this.parent = simpleTrustManagerFactory;
        }

        /* access modifiers changed from: protected */
        public void engineInit(KeyStore keyStore) throws KeyStoreException {
            try {
                this.parent.engineInit(keyStore);
            } catch (KeyStoreException e) {
                throw e;
            } catch (Exception e2) {
                throw new KeyStoreException(e2);
            }
        }

        /* access modifiers changed from: protected */
        public void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
            try {
                this.parent.engineInit(managerFactoryParameters);
            } catch (InvalidAlgorithmParameterException e) {
                throw e;
            } catch (Exception e2) {
                throw new InvalidAlgorithmParameterException(e2);
            }
        }

        /* access modifiers changed from: protected */
        public TrustManager[] engineGetTrustManagers() {
            TrustManager[] trustManagerArr = this.trustManagers;
            if (trustManagerArr == null) {
                trustManagerArr = this.parent.engineGetTrustManagers();
                if (PlatformDependent.javaVersion() >= 7) {
                    wrapIfNeeded(trustManagerArr);
                }
                this.trustManagers = trustManagerArr;
            }
            return (TrustManager[]) trustManagerArr.clone();
        }

        private static void wrapIfNeeded(TrustManager[] trustManagerArr) {
            for (int i = 0; i < trustManagerArr.length; i++) {
                X509TrustManager x509TrustManager = trustManagerArr[i];
                if ((x509TrustManager instanceof X509TrustManager) && !NioPathKt$$ExternalSyntheticApiModelOutline0.m$2((Object) x509TrustManager)) {
                    trustManagerArr[i] = new X509TrustManagerWrapper(x509TrustManager);
                }
            }
        }
    }
}
