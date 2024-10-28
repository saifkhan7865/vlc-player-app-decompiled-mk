package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.engine.ApplicationEngine;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;

@Metadata(d1 = {"\u0000\\\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aV\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f¢\u0006\u0002\u0010\r\u001a\u0001\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f¢\u0006\u0002\u0010\u0016\u001a\u0001\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u00020\u00172\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\u0014\b\u0002\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u001a0\u0019\"\u00020\u001a2\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f¢\u0006\u0002\u0010\u001d\u001a\u0001\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u00020\u00172\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u00062\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u000e\b\u0002\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\f¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"embeddedServer", "TEngine", "Lio/ktor/server/engine/ApplicationEngine;", "TConfiguration", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "factory", "Lio/ktor/server/engine/ApplicationEngineFactory;", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/engine/ApplicationEngineFactory;Lio/ktor/server/engine/ApplicationEngineEnvironment;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/engine/ApplicationEngine;", "port", "", "host", "", "watchPaths", "", "module", "Lio/ktor/server/application/Application;", "(Lio/ktor/server/engine/ApplicationEngineFactory;ILjava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/engine/ApplicationEngine;", "Lkotlinx/coroutines/CoroutineScope;", "connectors", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "parentCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/CoroutineScope;Lio/ktor/server/engine/ApplicationEngineFactory;[Lio/ktor/server/engine/EngineConnectorConfig;Ljava/util/List;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/engine/ApplicationEngine;", "(Lkotlinx/coroutines/CoroutineScope;Lio/ktor/server/engine/ApplicationEngineFactory;ILjava/lang/String;Ljava/util/List;Lkotlin/coroutines/CoroutineContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/engine/ApplicationEngine;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddedServer.kt */
public final class EmbeddedServerKt {
    public static /* synthetic */ ApplicationEngine embeddedServer$default(ApplicationEngineFactory applicationEngineFactory, int i, String str, List list, Function1 function1, Function1 function12, int i2, Object obj) {
        int i3 = (i2 & 2) != 0 ? 80 : i;
        if ((i2 & 4) != 0) {
            str = "0.0.0.0";
        }
        String str2 = str;
        if ((i2 & 8) != 0) {
            list = CollectionsKt.listOf(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH());
        }
        List list2 = list;
        if ((i2 & 16) != 0) {
            function1 = EmbeddedServerKt$embeddedServer$1.INSTANCE;
        }
        return embeddedServer(applicationEngineFactory, i3, str2, list2, function1, function12);
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, int i, String str, List<String> list, Function1<? super TConfiguration, Unit> function1, Function1<? super Application, Unit> function12) {
        Intrinsics.checkNotNullParameter(applicationEngineFactory, "factory");
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(list, "watchPaths");
        Intrinsics.checkNotNullParameter(function1, "configure");
        Intrinsics.checkNotNullParameter(function12, "module");
        return embeddedServer(GlobalScope.INSTANCE, applicationEngineFactory, i, str, list, EmptyCoroutineContext.INSTANCE, function1, function12);
    }

    public static /* synthetic */ ApplicationEngine embeddedServer$default(CoroutineScope coroutineScope, ApplicationEngineFactory applicationEngineFactory, int i, String str, List list, CoroutineContext coroutineContext, Function1 function1, Function1 function12, int i2, Object obj) {
        Function1 function13;
        int i3 = (i2 & 2) != 0 ? 80 : i;
        String str2 = (i2 & 4) != 0 ? "0.0.0.0" : str;
        List listOf = (i2 & 8) != 0 ? CollectionsKt.listOf(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH()) : list;
        CoroutineContext coroutineContext2 = (i2 & 16) != 0 ? EmptyCoroutineContext.INSTANCE : coroutineContext;
        if ((i2 & 32) != 0) {
            function13 = EmbeddedServerKt$embeddedServer$2.INSTANCE;
        } else {
            function13 = function1;
        }
        return embeddedServer(coroutineScope, applicationEngineFactory, i3, str2, listOf, coroutineContext2, function13, function12);
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(CoroutineScope coroutineScope, ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, int i, String str, List<String> list, CoroutineContext coroutineContext, Function1<? super TConfiguration, Unit> function1, Function1<? super Application, Unit> function12) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(applicationEngineFactory, "factory");
        Intrinsics.checkNotNullParameter(str, "host");
        Intrinsics.checkNotNullParameter(list, "watchPaths");
        Intrinsics.checkNotNullParameter(coroutineContext, "parentCoroutineContext");
        Intrinsics.checkNotNullParameter(function1, "configure");
        Intrinsics.checkNotNullParameter(function12, "module");
        EngineConnectorBuilder engineConnectorBuilder = new EngineConnectorBuilder((ConnectorType) null, 1, (DefaultConstructorMarker) null);
        engineConnectorBuilder.setPort(i);
        engineConnectorBuilder.setHost(str);
        Unit unit = Unit.INSTANCE;
        return embeddedServer(coroutineScope, applicationEngineFactory, (EngineConnectorConfig[]) Arrays.copyOf(new EngineConnectorConfig[]{engineConnectorBuilder}, 1), list, coroutineContext, function1, function12);
    }

    public static /* synthetic */ ApplicationEngine embeddedServer$default(CoroutineScope coroutineScope, ApplicationEngineFactory applicationEngineFactory, EngineConnectorConfig[] engineConnectorConfigArr, List list, CoroutineContext coroutineContext, Function1 function1, Function1 function12, int i, Object obj) {
        if ((i & 2) != 0) {
            engineConnectorConfigArr = (EngineConnectorConfig[]) new EngineConnectorBuilder[]{new EngineConnectorBuilder((ConnectorType) null, 1, (DefaultConstructorMarker) null)};
        }
        EngineConnectorConfig[] engineConnectorConfigArr2 = engineConnectorConfigArr;
        if ((i & 4) != 0) {
            list = CollectionsKt.listOf(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH());
        }
        List list2 = list;
        if ((i & 8) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineContext coroutineContext2 = coroutineContext;
        if ((i & 16) != 0) {
            function1 = EmbeddedServerKt$embeddedServer$3.INSTANCE;
        }
        return embeddedServer(coroutineScope, applicationEngineFactory, engineConnectorConfigArr2, list2, coroutineContext2, function1, function12);
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(CoroutineScope coroutineScope, ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, EngineConnectorConfig[] engineConnectorConfigArr, List<String> list, CoroutineContext coroutineContext, Function1<? super TConfiguration, Unit> function1, Function1<? super Application, Unit> function12) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(applicationEngineFactory, "factory");
        Intrinsics.checkNotNullParameter(engineConnectorConfigArr, "connectors");
        Intrinsics.checkNotNullParameter(list, "watchPaths");
        Intrinsics.checkNotNullParameter(coroutineContext, "parentCoroutineContext");
        Intrinsics.checkNotNullParameter(function1, "configure");
        Intrinsics.checkNotNullParameter(function12, "module");
        return embeddedServer(applicationEngineFactory, ApplicationEngineEnvironmentKt.applicationEngineEnvironment(new EmbeddedServerKt$embeddedServer$environment$1(coroutineScope, coroutineContext, list, function12, engineConnectorConfigArr)), function1);
    }

    public static /* synthetic */ ApplicationEngine embeddedServer$default(ApplicationEngineFactory applicationEngineFactory, ApplicationEngineEnvironment applicationEngineEnvironment, Function1 function1, int i, Object obj) {
        if ((i & 4) != 0) {
            function1 = EmbeddedServerKt$embeddedServer$4.INSTANCE;
        }
        return embeddedServer(applicationEngineFactory, applicationEngineEnvironment, function1);
    }

    public static final <TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> TEngine embeddedServer(ApplicationEngineFactory<? extends TEngine, TConfiguration> applicationEngineFactory, ApplicationEngineEnvironment applicationEngineEnvironment, Function1<? super TConfiguration, Unit> function1) {
        Intrinsics.checkNotNullParameter(applicationEngineFactory, "factory");
        Intrinsics.checkNotNullParameter(applicationEngineEnvironment, "environment");
        Intrinsics.checkNotNullParameter(function1, "configure");
        return applicationEngineFactory.create(applicationEngineEnvironment, function1);
    }
}
