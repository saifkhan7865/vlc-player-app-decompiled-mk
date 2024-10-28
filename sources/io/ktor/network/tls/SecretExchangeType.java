package io.ktor.network.tls;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lio/ktor/network/tls/SecretExchangeType;", "", "jvmName", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getJvmName", "()Ljava/lang/String;", "ECDHE", "RSA", "ktor-network-tls"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CipherSuites.kt */
public enum SecretExchangeType {
    ECDHE("ECDHE_ECDSA"),
    RSA("RSA");
    
    private final String jvmName;

    private SecretExchangeType(String str) {
        this.jvmName = str;
    }

    public final String getJvmName() {
        return this.jvmName;
    }
}