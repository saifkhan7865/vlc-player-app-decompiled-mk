package io.ktor.network.tls;

import io.ktor.network.tls.extensions.HashAlgorithm;
import io.ktor.network.tls.extensions.SignatureAlgorithm;
import io.netty.handler.ssl.Ciphers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006¨\u0006\u0017"}, d2 = {"Lio/ktor/network/tls/CIOCipherSuites;", "", "()V", "ECDHE_ECDSA_AES128_SHA256", "Lio/ktor/network/tls/CipherSuite;", "getECDHE_ECDSA_AES128_SHA256", "()Lio/ktor/network/tls/CipherSuite;", "ECDHE_ECDSA_AES256_SHA384", "getECDHE_ECDSA_AES256_SHA384", "ECDHE_RSA_AES128_SHA256", "getECDHE_RSA_AES128_SHA256", "ECDHE_RSA_AES256_SHA384", "getECDHE_RSA_AES256_SHA384", "SupportedSuites", "", "getSupportedSuites", "()Ljava/util/List;", "TLS_RSA_WITH_AES128_CBC_SHA", "getTLS_RSA_WITH_AES128_CBC_SHA", "TLS_RSA_WITH_AES256_CBC_SHA", "getTLS_RSA_WITH_AES256_CBC_SHA", "TLS_RSA_WITH_AES_128_GCM_SHA256", "getTLS_RSA_WITH_AES_128_GCM_SHA256", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CipherSuites.kt */
public final class CIOCipherSuites {
    private static final CipherSuite ECDHE_ECDSA_AES128_SHA256;
    private static final CipherSuite ECDHE_ECDSA_AES256_SHA384;
    private static final CipherSuite ECDHE_RSA_AES128_SHA256;
    private static final CipherSuite ECDHE_RSA_AES256_SHA384;
    public static final CIOCipherSuites INSTANCE = new CIOCipherSuites();
    private static final List<CipherSuite> SupportedSuites;
    private static final CipherSuite TLS_RSA_WITH_AES128_CBC_SHA;
    private static final CipherSuite TLS_RSA_WITH_AES256_CBC_SHA;
    private static final CipherSuite TLS_RSA_WITH_AES_128_GCM_SHA256;

    private CIOCipherSuites() {
    }

    static {
        CipherSuite cipherSuite = new CipherSuite(156, Ciphers.TLS_RSA_WITH_AES_128_GCM_SHA256, "AES128-GCM-SHA256", SecretExchangeType.RSA, "AES/GCM/NoPadding", 128, 4, 12, 16, "AEAD", 0, HashAlgorithm.SHA256, SignatureAlgorithm.RSA, (CipherType) null, 8192, (DefaultConstructorMarker) null);
        TLS_RSA_WITH_AES_128_GCM_SHA256 = cipherSuite;
        CipherSuite cipherSuite2 = new CipherSuite(-16340, Ciphers.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384, "ECDHE-ECDSA-AES256-GCM-SHA384", SecretExchangeType.ECDHE, "AES/GCM/NoPadding", 256, 4, 12, 16, "AEAD", 0, HashAlgorithm.SHA384, SignatureAlgorithm.ECDSA, (CipherType) null, 8192, (DefaultConstructorMarker) null);
        ECDHE_ECDSA_AES256_SHA384 = cipherSuite2;
        CipherSuite cipherSuite3 = new CipherSuite(-16341, Ciphers.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, "ECDHE-ECDSA-AES128-GCM-SHA256", SecretExchangeType.ECDHE, "AES/GCM/NoPadding", 128, 4, 12, 16, "AEAD", 0, HashAlgorithm.SHA256, SignatureAlgorithm.ECDSA, (CipherType) null, 8192, (DefaultConstructorMarker) null);
        ECDHE_ECDSA_AES128_SHA256 = cipherSuite3;
        CipherSuite cipherSuite4 = new CipherSuite(-16336, Ciphers.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384, "ECDHE-RSA-AES256-GCM-SHA384", SecretExchangeType.ECDHE, "AES/GCM/NoPadding", 256, 4, 12, 16, "AEAD", 0, HashAlgorithm.SHA384, SignatureAlgorithm.RSA, (CipherType) null, 8192, (DefaultConstructorMarker) null);
        ECDHE_RSA_AES256_SHA384 = cipherSuite4;
        CipherSuite cipherSuite5 = new CipherSuite(-16337, Ciphers.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256, "ECDHE-RSA-AES128-GCM-SHA256", SecretExchangeType.ECDHE, "AES/GCM/NoPadding", 128, 4, 12, 16, "AEAD", 0, HashAlgorithm.SHA256, SignatureAlgorithm.RSA, (CipherType) null, 8192, (DefaultConstructorMarker) null);
        ECDHE_RSA_AES128_SHA256 = cipherSuite5;
        CipherSuite cipherSuite6 = new CipherSuite(53, Ciphers.TLS_RSA_WITH_AES_256_CBC_SHA, "AES-256-CBC-SHA", SecretExchangeType.RSA, "AES/CBC/NoPadding", 256, 16, 48, 20, "HmacSHA1", 160, HashAlgorithm.SHA256, SignatureAlgorithm.RSA, CipherType.CBC);
        TLS_RSA_WITH_AES256_CBC_SHA = cipherSuite6;
        CipherSuite cipherSuite7 = new CipherSuite(47, Ciphers.TLS_RSA_WITH_AES_128_CBC_SHA, "AES-128-CBC-SHA", SecretExchangeType.RSA, "AES/CBC/NoPadding", 128, 16, 48, 20, "HmacSHA1", 160, HashAlgorithm.SHA256, SignatureAlgorithm.RSA, CipherType.CBC);
        TLS_RSA_WITH_AES128_CBC_SHA = cipherSuite7;
        CipherSuite[] cipherSuiteArr = {cipherSuite2, cipherSuite4, cipherSuite3, cipherSuite5, cipherSuite, cipherSuite6, cipherSuite7};
        Collection arrayList = new ArrayList();
        for (Object next : CollectionsKt.listOf(cipherSuiteArr)) {
            if (CipherSuitesJvmKt.isSupported((CipherSuite) next)) {
                arrayList.add(next);
            }
        }
        SupportedSuites = (List) arrayList;
    }

    public final CipherSuite getTLS_RSA_WITH_AES_128_GCM_SHA256() {
        return TLS_RSA_WITH_AES_128_GCM_SHA256;
    }

    public final CipherSuite getECDHE_ECDSA_AES256_SHA384() {
        return ECDHE_ECDSA_AES256_SHA384;
    }

    public final CipherSuite getECDHE_ECDSA_AES128_SHA256() {
        return ECDHE_ECDSA_AES128_SHA256;
    }

    public final CipherSuite getECDHE_RSA_AES256_SHA384() {
        return ECDHE_RSA_AES256_SHA384;
    }

    public final CipherSuite getECDHE_RSA_AES128_SHA256() {
        return ECDHE_RSA_AES128_SHA256;
    }

    public final CipherSuite getTLS_RSA_WITH_AES256_CBC_SHA() {
        return TLS_RSA_WITH_AES256_CBC_SHA;
    }

    public final CipherSuite getTLS_RSA_WITH_AES128_CBC_SHA() {
        return TLS_RSA_WITH_AES128_CBC_SHA;
    }

    public final List<CipherSuite> getSupportedSuites() {
        return SupportedSuites;
    }
}
