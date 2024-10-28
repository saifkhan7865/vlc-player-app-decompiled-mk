package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommandLine.kt */
final class CommandLineKt$commandLineEnvironment$1 extends Lambda implements Function1<ApplicationEngineEnvironmentBuilder, Unit> {
    public static final CommandLineKt$commandLineEnvironment$1 INSTANCE = new CommandLineKt$commandLineEnvironment$1();

    CommandLineKt$commandLineEnvironment$1() {
        super(1);
    }

    public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        Intrinsics.checkNotNullParameter(applicationEngineEnvironmentBuilder, "$this$buildCommandLineEnvironment");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEngineEnvironmentBuilder) obj);
        return Unit.INSTANCE;
    }
}
