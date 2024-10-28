package io.netty.handler.ssl.util;

import io.netty.util.internal.ObjectUtil;
import java.net.Socket;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;

final class X509KeyManagerWrapper extends X509ExtendedKeyManager {
    private final X509KeyManager delegate;

    X509KeyManagerWrapper(X509KeyManager x509KeyManager) {
        this.delegate = (X509KeyManager) ObjectUtil.checkNotNull(x509KeyManager, "delegate");
    }

    public String[] getClientAliases(String str, Principal[] principalArr) {
        return this.delegate.getClientAliases(str, principalArr);
    }

    public String chooseClientAlias(String[] strArr, Principal[] principalArr, Socket socket) {
        return this.delegate.chooseClientAlias(strArr, principalArr, socket);
    }

    public String[] getServerAliases(String str, Principal[] principalArr) {
        return this.delegate.getServerAliases(str, principalArr);
    }

    public String chooseServerAlias(String str, Principal[] principalArr, Socket socket) {
        return this.delegate.chooseServerAlias(str, principalArr, socket);
    }

    public X509Certificate[] getCertificateChain(String str) {
        return this.delegate.getCertificateChain(str);
    }

    public PrivateKey getPrivateKey(String str) {
        return this.delegate.getPrivateKey(str);
    }

    public String chooseEngineClientAlias(String[] strArr, Principal[] principalArr, SSLEngine sSLEngine) {
        return this.delegate.chooseClientAlias(strArr, principalArr, (Socket) null);
    }

    public String chooseEngineServerAlias(String str, Principal[] principalArr, SSLEngine sSLEngine) {
        return this.delegate.chooseServerAlias(str, principalArr, (Socket) null);
    }
}
