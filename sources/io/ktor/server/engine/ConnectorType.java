package io.ktor.server.engine;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lio/ktor/server/engine/ConnectorType;", "", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfig.kt */
public final class ConnectorType {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final ConnectorType HTTP = new ConnectorType("HTTP");
    /* access modifiers changed from: private */
    public static final ConnectorType HTTPS = new ConnectorType("HTTPS");
    private final String name;

    public static /* synthetic */ ConnectorType copy$default(ConnectorType connectorType, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = connectorType.name;
        }
        return connectorType.copy(str);
    }

    public final String component1() {
        return this.name;
    }

    public final ConnectorType copy(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return new ConnectorType(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ConnectorType) && Intrinsics.areEqual((Object) this.name, (Object) ((ConnectorType) obj).name);
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public String toString() {
        return "ConnectorType(name=" + this.name + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\t"}, d2 = {"Lio/ktor/server/engine/ConnectorType$Companion;", "", "()V", "HTTP", "Lio/ktor/server/engine/ConnectorType;", "getHTTP", "()Lio/ktor/server/engine/ConnectorType;", "HTTPS", "getHTTPS", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: EngineConnectorConfig.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ConnectorType getHTTP() {
            return ConnectorType.HTTP;
        }

        public final ConnectorType getHTTPS() {
            return ConnectorType.HTTPS;
        }
    }

    public ConnectorType(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        this.name = str;
    }

    public final String getName() {
        return this.name;
    }
}
