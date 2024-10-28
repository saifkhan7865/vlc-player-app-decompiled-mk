package io.ktor.server.engine;

import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.ApplicationEngine.Configuration;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00042\u00020\u0005J.\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0017\u0010\t\u001a\u0013\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\fH&¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineFactory;", "TEngine", "Lio/ktor/server/engine/ApplicationEngine;", "TConfiguration", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "", "create", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "configure", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/engine/ApplicationEngineEnvironment;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/engine/ApplicationEngine;", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddedServer.kt */
public interface ApplicationEngineFactory<TEngine extends ApplicationEngine, TConfiguration extends ApplicationEngine.Configuration> {
    TEngine create(ApplicationEngineEnvironment applicationEngineEnvironment, Function1<? super TConfiguration, Unit> function1);
}
