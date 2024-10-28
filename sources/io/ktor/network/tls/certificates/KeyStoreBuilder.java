package io.ktor.network.tls.certificates;

import io.ktor.util.CharsetKt;
import java.io.InputStream;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\r\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ'\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000b0\u000e¢\u0006\u0002\b\u0010R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lio/ktor/network/tls/certificates/KeyStoreBuilder;", "", "()V", "certificates", "", "", "Lio/ktor/network/tls/certificates/CertificateInfo;", "build", "Ljava/security/KeyStore;", "build$ktor_network_tls_certificates", "certificate", "", "alias", "block", "Lkotlin/Function1;", "Lio/ktor/network/tls/certificates/CertificateBuilder;", "Lkotlin/ExtensionFunctionType;", "ktor-network-tls-certificates"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: builders.kt */
public final class KeyStoreBuilder {
    private final Map<String, CertificateInfo> certificates = new LinkedHashMap();

    public final void certificate(String str, Function1<? super CertificateBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, "alias");
        Intrinsics.checkNotNullParameter(function1, "block");
        Map<String, CertificateInfo> map = this.certificates;
        CertificateBuilder certificateBuilder = new CertificateBuilder();
        function1.invoke(certificateBuilder);
        map.put(str, certificateBuilder.build$ktor_network_tls_certificates());
    }

    public final KeyStore build$ktor_network_tls_certificates() {
        KeyStore instance = KeyStore.getInstance("JKS");
        Intrinsics.checkNotNull(instance);
        instance.load((InputStream) null, (char[]) null);
        for (Map.Entry next : this.certificates.entrySet()) {
            CertificateInfo certificateInfo = (CertificateInfo) next.getValue();
            Certificate component1 = certificateInfo.component1();
            KeyPair component2 = certificateInfo.component2();
            String component3 = certificateInfo.component3();
            Certificate[] certificateArr = {component1, certificateInfo.component4()};
            instance.setKeyEntry((String) next.getKey(), component2.getPrivate(), CharsetKt.toCharArray(component3), (Certificate[]) CollectionsKt.listOfNotNull((T[]) certificateArr).toArray(new Certificate[0]));
        }
        return instance;
    }
}
