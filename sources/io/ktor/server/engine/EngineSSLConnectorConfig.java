package io.ktor.server.engine;

import java.io.File;
import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u0004\u0018\u00010\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0012R\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR\u0014\u0010\u001b\u001a\u0004\u0018\u00010\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0016¨\u0006\u001d"}, d2 = {"Lio/ktor/server/engine/EngineSSLConnectorConfig;", "Lio/ktor/server/engine/EngineConnectorConfig;", "enabledProtocols", "", "", "getEnabledProtocols", "()Ljava/util/List;", "keyAlias", "getKeyAlias", "()Ljava/lang/String;", "keyStore", "Ljava/security/KeyStore;", "getKeyStore", "()Ljava/security/KeyStore;", "keyStorePassword", "Lkotlin/Function0;", "", "getKeyStorePassword", "()Lkotlin/jvm/functions/Function0;", "keyStorePath", "Ljava/io/File;", "getKeyStorePath", "()Ljava/io/File;", "privateKeyPassword", "getPrivateKeyPassword", "trustStore", "getTrustStore", "trustStorePath", "getTrustStorePath", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfigJvm.kt */
public interface EngineSSLConnectorConfig extends EngineConnectorConfig {
    List<String> getEnabledProtocols();

    String getKeyAlias();

    KeyStore getKeyStore();

    Function0<char[]> getKeyStorePassword();

    File getKeyStorePath();

    Function0<char[]> getPrivateKeyPassword();

    KeyStore getTrustStore();

    File getTrustStorePath();
}
