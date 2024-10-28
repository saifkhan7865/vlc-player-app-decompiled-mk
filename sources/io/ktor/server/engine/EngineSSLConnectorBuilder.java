package io.ktor.server.engine;

import java.io.File;
import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\u0018\u00002\u00020\u00012\u00020\u0002B1\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\u000bR\"\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001b\"\u0004\b+\u0010\u001dR\u001c\u0010,\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u0017\"\u0004\b.\u0010\u0019R\u001c\u0010/\u001a\u0004\u0018\u00010\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010!\"\u0004\b1\u0010#¨\u00062"}, d2 = {"Lio/ktor/server/engine/EngineSSLConnectorBuilder;", "Lio/ktor/server/engine/EngineConnectorBuilder;", "Lio/ktor/server/engine/EngineSSLConnectorConfig;", "keyStore", "Ljava/security/KeyStore;", "keyAlias", "", "keyStorePassword", "Lkotlin/Function0;", "", "privateKeyPassword", "(Ljava/security/KeyStore;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "enabledProtocols", "", "getEnabledProtocols", "()Ljava/util/List;", "setEnabledProtocols", "(Ljava/util/List;)V", "getKeyAlias", "()Ljava/lang/String;", "setKeyAlias", "(Ljava/lang/String;)V", "getKeyStore", "()Ljava/security/KeyStore;", "setKeyStore", "(Ljava/security/KeyStore;)V", "getKeyStorePassword", "()Lkotlin/jvm/functions/Function0;", "setKeyStorePassword", "(Lkotlin/jvm/functions/Function0;)V", "keyStorePath", "Ljava/io/File;", "getKeyStorePath", "()Ljava/io/File;", "setKeyStorePath", "(Ljava/io/File;)V", "port", "", "getPort", "()I", "setPort", "(I)V", "getPrivateKeyPassword", "setPrivateKeyPassword", "trustStore", "getTrustStore", "setTrustStore", "trustStorePath", "getTrustStorePath", "setTrustStorePath", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfigJvm.kt */
public final class EngineSSLConnectorBuilder extends EngineConnectorBuilder implements EngineSSLConnectorConfig {
    private List<String> enabledProtocols;
    private String keyAlias;
    private KeyStore keyStore;
    private Function0<char[]> keyStorePassword;
    private File keyStorePath;
    private int port = 443;
    private Function0<char[]> privateKeyPassword;
    private KeyStore trustStore;
    private File trustStorePath;

    public KeyStore getKeyStore() {
        return this.keyStore;
    }

    public void setKeyStore(KeyStore keyStore2) {
        Intrinsics.checkNotNullParameter(keyStore2, "<set-?>");
        this.keyStore = keyStore2;
    }

    public String getKeyAlias() {
        return this.keyAlias;
    }

    public void setKeyAlias(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.keyAlias = str;
    }

    public Function0<char[]> getKeyStorePassword() {
        return this.keyStorePassword;
    }

    public void setKeyStorePassword(Function0<char[]> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.keyStorePassword = function0;
    }

    public Function0<char[]> getPrivateKeyPassword() {
        return this.privateKeyPassword;
    }

    public void setPrivateKeyPassword(Function0<char[]> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.privateKeyPassword = function0;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public EngineSSLConnectorBuilder(KeyStore keyStore2, String str, Function0<char[]> function0, Function0<char[]> function02) {
        super(ConnectorType.Companion.getHTTPS());
        Intrinsics.checkNotNullParameter(keyStore2, "keyStore");
        Intrinsics.checkNotNullParameter(str, "keyAlias");
        Intrinsics.checkNotNullParameter(function0, "keyStorePassword");
        Intrinsics.checkNotNullParameter(function02, "privateKeyPassword");
        this.keyStore = keyStore2;
        this.keyAlias = str;
        this.keyStorePassword = function0;
        this.privateKeyPassword = function02;
    }

    public File getKeyStorePath() {
        return this.keyStorePath;
    }

    public void setKeyStorePath(File file) {
        this.keyStorePath = file;
    }

    public KeyStore getTrustStore() {
        return this.trustStore;
    }

    public void setTrustStore(KeyStore keyStore2) {
        this.trustStore = keyStore2;
    }

    public File getTrustStorePath() {
        return this.trustStorePath;
    }

    public void setTrustStorePath(File file) {
        this.trustStorePath = file;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public List<String> getEnabledProtocols() {
        return this.enabledProtocols;
    }

    public void setEnabledProtocols(List<String> list) {
        this.enabledProtocols = list;
    }
}
