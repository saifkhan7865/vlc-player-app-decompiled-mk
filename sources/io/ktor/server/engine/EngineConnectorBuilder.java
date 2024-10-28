package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0016\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0006H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0014"}, d2 = {"Lio/ktor/server/engine/EngineConnectorBuilder;", "Lio/ktor/server/engine/EngineConnectorConfig;", "type", "Lio/ktor/server/engine/ConnectorType;", "(Lio/ktor/server/engine/ConnectorType;)V", "host", "", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "port", "", "getPort", "()I", "setPort", "(I)V", "getType", "()Lio/ktor/server/engine/ConnectorType;", "toString", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfig.kt */
public class EngineConnectorBuilder implements EngineConnectorConfig {
    private String host;
    private int port;
    private final ConnectorType type;

    public EngineConnectorBuilder() {
        this((ConnectorType) null, 1, (DefaultConstructorMarker) null);
    }

    public EngineConnectorBuilder(ConnectorType connectorType) {
        Intrinsics.checkNotNullParameter(connectorType, "type");
        this.type = connectorType;
        this.host = "0.0.0.0";
        this.port = 80;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ EngineConnectorBuilder(ConnectorType connectorType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? ConnectorType.Companion.getHTTP() : connectorType);
    }

    public ConnectorType getType() {
        return this.type;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.host = str;
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public String toString() {
        return getType().getName() + ' ' + getHost() + AbstractJsonLexerKt.COLON + getPort();
    }
}
