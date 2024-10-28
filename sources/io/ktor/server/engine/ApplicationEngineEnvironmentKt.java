package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¨\u0006\u0007"}, d2 = {"applicationEngineEnvironment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "builder", "Lkotlin/Function1;", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "", "Lkotlin/ExtensionFunctionType;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironment.kt */
public final class ApplicationEngineEnvironmentKt {
    public static final ApplicationEngineEnvironment applicationEngineEnvironment(Function1<? super ApplicationEngineEnvironmentBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builder");
        return new ApplicationEngineEnvironmentBuilder().build(function1);
    }
}
