package io.ktor.network.tls;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a\b\u0010\u0000\u001a\u00020\u0001H\u0002\u001a%\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n\u001a/\u0010\u000b\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007¢\u0006\u0002\b\u0012\u001a\u0012\u0010\u0013\u001a\u00020\u0003*\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004¨\u0006\u0015"}, d2 = {"findTrustManager", "Ljavax/net/ssl/X509TrustManager;", "addCertificateChain", "", "Lio/ktor/network/tls/TLSConfigBuilder;", "chain", "", "Ljava/security/cert/X509Certificate;", "key", "Ljava/security/PrivateKey;", "(Lio/ktor/network/tls/TLSConfigBuilder;[Ljava/security/cert/X509Certificate;Ljava/security/PrivateKey;)V", "addKeyStore", "store", "Ljava/security/KeyStore;", "password", "", "alias", "", "addKeyStoreNullablePassword", "takeFrom", "other", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TLSConfigBuilder.kt */
public final class TLSConfigBuilderKt {
    public static final void takeFrom(TLSConfigBuilder tLSConfigBuilder, TLSConfigBuilder tLSConfigBuilder2) {
        Intrinsics.checkNotNullParameter(tLSConfigBuilder, "<this>");
        Intrinsics.checkNotNullParameter(tLSConfigBuilder2, "other");
        CollectionsKt.addAll(tLSConfigBuilder.getCertificates(), tLSConfigBuilder2.getCertificates());
        tLSConfigBuilder.setRandom(tLSConfigBuilder2.getRandom());
        tLSConfigBuilder.setCipherSuites(tLSConfigBuilder2.getCipherSuites());
        tLSConfigBuilder.setServerName(tLSConfigBuilder2.getServerName());
        tLSConfigBuilder.setTrustManager(tLSConfigBuilder2.getTrustManager());
    }

    public static final void addCertificateChain(TLSConfigBuilder tLSConfigBuilder, X509Certificate[] x509CertificateArr, PrivateKey privateKey) {
        Intrinsics.checkNotNullParameter(tLSConfigBuilder, "<this>");
        Intrinsics.checkNotNullParameter(x509CertificateArr, "chain");
        Intrinsics.checkNotNullParameter(privateKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        tLSConfigBuilder.getCertificates().add(new CertificateAndKey(x509CertificateArr, privateKey));
    }

    public static /* synthetic */ void addKeyStoreNullablePassword$default(TLSConfigBuilder tLSConfigBuilder, KeyStore keyStore, char[] cArr, String str, int i, Object obj) {
        if ((i & 4) != 0) {
            str = null;
        }
        addKeyStoreNullablePassword(tLSConfigBuilder, keyStore, cArr, str);
    }

    public static final void addKeyStoreNullablePassword(TLSConfigBuilder tLSConfigBuilder, KeyStore keyStore, char[] cArr, String str) {
        List<String> list;
        Intrinsics.checkNotNullParameter(tLSConfigBuilder, "<this>");
        Intrinsics.checkNotNullParameter(keyStore, "store");
        String defaultAlgorithm = KeyManagerFactory.getDefaultAlgorithm();
        Intrinsics.checkNotNull(defaultAlgorithm);
        KeyManagerFactory instance = KeyManagerFactory.getInstance(defaultAlgorithm);
        Intrinsics.checkNotNull(instance);
        instance.init(keyStore, cArr);
        KeyManager[] keyManagers = instance.getKeyManagers();
        Intrinsics.checkNotNullExpressionValue(keyManagers, "keyManagerFactory.keyManagers");
        Collection arrayList = new ArrayList();
        for (Object obj : (Object[]) keyManagers) {
            if (obj instanceof X509KeyManager) {
                arrayList.add(obj);
            }
        }
        List<X509KeyManager> list2 = (List) arrayList;
        if (str == null || (list = CollectionsKt.listOf(str)) == null) {
            Enumeration<String> aliases = keyStore.aliases();
            Intrinsics.checkNotNull(aliases);
            ArrayList<T> list3 = Collections.list(aliases);
            Intrinsics.checkNotNullExpressionValue(list3, "list(this)");
            list = list3;
        }
        for (String str2 : list) {
            Certificate[] certificateChain = keyStore.getCertificateChain(str2);
            if (certificateChain != null) {
                int length = certificateChain.length;
                int i = 0;
                while (i < length) {
                    if (certificateChain[i] instanceof X509Certificate) {
                        i++;
                    } else {
                        throw new IllegalStateException(("Fail to add key store " + keyStore + ". Only X509 certificate format supported.").toString());
                    }
                }
                for (X509KeyManager privateKey : list2) {
                    PrivateKey privateKey2 = privateKey.getPrivateKey(str2);
                    if (privateKey2 != null) {
                        Collection arrayList2 = new ArrayList(certificateChain.length);
                        for (Certificate certificate : certificateChain) {
                            Intrinsics.checkNotNull(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                            arrayList2.add((X509Certificate) certificate);
                        }
                        addCertificateChain(tLSConfigBuilder, (X509Certificate[]) ((List) arrayList2).toArray(new X509Certificate[0]), privateKey2);
                    }
                }
                throw new NoPrivateKeyException(str2, keyStore);
            }
            throw new IllegalStateException(("Fail to get the certificate chain for this alias: " + str2).toString());
        }
    }

    /* access modifiers changed from: private */
    public static final X509TrustManager findTrustManager() {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        Intrinsics.checkNotNull(instance);
        instance.init((KeyStore) null);
        TrustManager[] trustManagers = instance.getTrustManagers();
        Intrinsics.checkNotNull(trustManagers);
        Collection arrayList = new ArrayList();
        for (TrustManager trustManager : trustManagers) {
            if (trustManager instanceof X509TrustManager) {
                arrayList.add(trustManager);
            }
        }
        return (X509TrustManager) CollectionsKt.first((List) arrayList);
    }
}
