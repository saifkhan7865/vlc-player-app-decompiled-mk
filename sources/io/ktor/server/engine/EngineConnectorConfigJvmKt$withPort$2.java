package io.ktor.server.engine;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X\u0005¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"io/ktor/server/engine/EngineConnectorConfigJvmKt$withPort$2", "Lio/ktor/server/engine/EngineConnectorConfig;", "host", "", "getHost", "()Ljava/lang/String;", "port", "", "getPort", "()I", "type", "Lio/ktor/server/engine/ConnectorType;", "getType", "()Lio/ktor/server/engine/ConnectorType;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfigJvm.kt */
public final class EngineConnectorConfigJvmKt$withPort$2 implements EngineConnectorConfig {
    private final /* synthetic */ EngineConnectorConfig $$delegate_0;
    private final int port;

    public String getHost() {
        return this.$$delegate_0.getHost();
    }

    public ConnectorType getType() {
        return this.$$delegate_0.getType();
    }

    EngineConnectorConfigJvmKt$withPort$2(EngineConnectorConfig engineConnectorConfig, int i) {
        this.$$delegate_0 = engineConnectorConfig;
        this.port = i;
    }

    public int getPort() {
        return this.port;
    }
}
