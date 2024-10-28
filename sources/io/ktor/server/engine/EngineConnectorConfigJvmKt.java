package io.ktor.server.engine;

import java.security.KeyStore;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001aY\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\b\b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u000e\b\b\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0017\u0010\u000b\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f¢\u0006\u0002\b\u000eH\bø\u0001\u0000\u001a\u0012\u0010\u000f\u001a\u00020\u0010*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0013"}, d2 = {"sslConnector", "", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "keyStore", "Ljava/security/KeyStore;", "keyAlias", "", "keyStorePassword", "Lkotlin/Function0;", "", "privateKeyPassword", "builder", "Lkotlin/Function1;", "Lio/ktor/server/engine/EngineSSLConnectorBuilder;", "Lkotlin/ExtensionFunctionType;", "withPort", "Lio/ktor/server/engine/EngineConnectorConfig;", "otherPort", "", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfigJvm.kt */
public final class EngineConnectorConfigJvmKt {
    public static final void sslConnector(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder, KeyStore keyStore, String str, Function0<char[]> function0, Function0<char[]> function02, Function1<? super EngineSSLConnectorBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(applicationEngineEnvironmentBuilder, "<this>");
        Intrinsics.checkNotNullParameter(keyStore, "keyStore");
        Intrinsics.checkNotNullParameter(str, "keyAlias");
        Intrinsics.checkNotNullParameter(function0, "keyStorePassword");
        Intrinsics.checkNotNullParameter(function02, "privateKeyPassword");
        Intrinsics.checkNotNullParameter(function1, "builder");
        List<EngineConnectorConfig> connectors = applicationEngineEnvironmentBuilder.getConnectors();
        EngineSSLConnectorBuilder engineSSLConnectorBuilder = new EngineSSLConnectorBuilder(keyStore, str, function0, function02);
        function1.invoke(engineSSLConnectorBuilder);
        connectors.add(engineSSLConnectorBuilder);
    }

    public static final EngineConnectorConfig withPort(EngineConnectorConfig engineConnectorConfig, int i) {
        Intrinsics.checkNotNullParameter(engineConnectorConfig, "<this>");
        if (engineConnectorConfig instanceof EngineSSLConnectorBuilder) {
            return new EngineConnectorConfigJvmKt$withPort$1(engineConnectorConfig, i);
        }
        return new EngineConnectorConfigJvmKt$withPort$2(engineConnectorConfig, i);
    }
}
