package io.ktor.server.engine;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a)\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0007"}, d2 = {"connector", "", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "builder", "Lkotlin/Function1;", "Lio/ktor/server/engine/EngineConnectorBuilder;", "Lkotlin/ExtensionFunctionType;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineConnectorConfig.kt */
public final class EngineConnectorConfigKt {
    public static final void connector(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder, Function1<? super EngineConnectorBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(applicationEngineEnvironmentBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builder");
        List<EngineConnectorConfig> connectors = applicationEngineEnvironmentBuilder.getConnectors();
        EngineConnectorBuilder engineConnectorBuilder = new EngineConnectorBuilder((ConnectorType) null, 1, (DefaultConstructorMarker) null);
        function1.invoke(engineConnectorBuilder);
        connectors.add(engineConnectorBuilder);
    }
}
