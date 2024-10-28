package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0005*\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "TEngine", "Lio/ktor/server/engine/ApplicationEngine;", "TConfiguration", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddedServer.kt */
final class EmbeddedServerKt$embeddedServer$environment$1 extends Lambda implements Function1<ApplicationEngineEnvironmentBuilder, Unit> {
    final /* synthetic */ EngineConnectorConfig[] $connectors;
    final /* synthetic */ Function1<Application, Unit> $module;
    final /* synthetic */ CoroutineContext $parentCoroutineContext;
    final /* synthetic */ CoroutineScope $this_embeddedServer;
    final /* synthetic */ List<String> $watchPaths;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EmbeddedServerKt$embeddedServer$environment$1(CoroutineScope coroutineScope, CoroutineContext coroutineContext, List<String> list, Function1<? super Application, Unit> function1, EngineConnectorConfig[] engineConnectorConfigArr) {
        super(1);
        this.$this_embeddedServer = coroutineScope;
        this.$parentCoroutineContext = coroutineContext;
        this.$watchPaths = list;
        this.$module = function1;
        this.$connectors = engineConnectorConfigArr;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEngineEnvironmentBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        Intrinsics.checkNotNullParameter(applicationEngineEnvironmentBuilder, "$this$applicationEngineEnvironment");
        applicationEngineEnvironmentBuilder.setParentCoroutineContext(this.$this_embeddedServer.getCoroutineContext().plus(this.$parentCoroutineContext));
        applicationEngineEnvironmentBuilder.setLog(KtorSimpleLoggerJvmKt.KtorSimpleLogger("ktor.application"));
        applicationEngineEnvironmentBuilder.setWatchPaths(this.$watchPaths);
        applicationEngineEnvironmentBuilder.module(this.$module);
        CollectionsKt.addAll(applicationEngineEnvironmentBuilder.getConnectors(), (T[]) this.$connectors);
    }
}
