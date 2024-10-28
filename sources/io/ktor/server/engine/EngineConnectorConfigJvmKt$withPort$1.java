package io.ktor.server.engine;

import java.io.File;
import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u0000C\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u001a\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003X\u0005¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\u0004X\u0005¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0004X\u0005¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0012\u0010\f\u001a\u00020\rX\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u001aX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0018\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0005¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u0014R\u0014\u0010\u001f\u001a\u0004\u0018\u00010\rX\u0005¢\u0006\u0006\u001a\u0004\b \u0010\u000fR\u0014\u0010!\u001a\u0004\u0018\u00010\u0016X\u0005¢\u0006\u0006\u001a\u0004\b\"\u0010\u0018R\u0012\u0010#\u001a\u00020$X\u0005¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006'"}, d2 = {"io/ktor/server/engine/EngineConnectorConfigJvmKt$withPort$1", "Lio/ktor/server/engine/EngineSSLConnectorConfig;", "enabledProtocols", "", "", "getEnabledProtocols", "()Ljava/util/List;", "host", "getHost", "()Ljava/lang/String;", "keyAlias", "getKeyAlias", "keyStore", "Ljava/security/KeyStore;", "getKeyStore", "()Ljava/security/KeyStore;", "keyStorePassword", "Lkotlin/Function0;", "", "getKeyStorePassword", "()Lkotlin/jvm/functions/Function0;", "keyStorePath", "Ljava/io/File;", "getKeyStorePath", "()Ljava/io/File;", "port", "", "getPort", "()I", "privateKeyPassword", "getPrivateKeyPassword", "trustStore", "getTrustStore", "trustStorePath", "getTrustStorePath", "type", "Lio/ktor/server/engine/ConnectorType;", "getType", "()Lio/ktor/server/engine/ConnectorType;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfigJvm.kt */
public final class EngineConnectorConfigJvmKt$withPort$1 implements EngineSSLConnectorConfig {
    private final /* synthetic */ EngineSSLConnectorBuilder $$delegate_0;
    private final int port;

    public List<String> getEnabledProtocols() {
        return this.$$delegate_0.getEnabledProtocols();
    }

    public String getHost() {
        return this.$$delegate_0.getHost();
    }

    public String getKeyAlias() {
        return this.$$delegate_0.getKeyAlias();
    }

    public KeyStore getKeyStore() {
        return this.$$delegate_0.getKeyStore();
    }

    public Function0<char[]> getKeyStorePassword() {
        return this.$$delegate_0.getKeyStorePassword();
    }

    public File getKeyStorePath() {
        return this.$$delegate_0.getKeyStorePath();
    }

    public Function0<char[]> getPrivateKeyPassword() {
        return this.$$delegate_0.getPrivateKeyPassword();
    }

    public KeyStore getTrustStore() {
        return this.$$delegate_0.getTrustStore();
    }

    public File getTrustStorePath() {
        return this.$$delegate_0.getTrustStorePath();
    }

    public ConnectorType getType() {
        return this.$$delegate_0.getType();
    }

    EngineConnectorConfigJvmKt$withPort$1(EngineConnectorConfig engineConnectorConfig, int i) {
        this.$$delegate_0 = (EngineSSLConnectorBuilder) engineConnectorConfig;
        this.port = i;
    }

    public int getPort() {
        return this.port;
    }
}
