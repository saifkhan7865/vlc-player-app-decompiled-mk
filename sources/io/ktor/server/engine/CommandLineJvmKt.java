package io.ktor.server.engine;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0019\b\u0002\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"commandLineEnvironment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "args", "", "", "environmentBuilder", "Lkotlin/Function1;", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "", "Lkotlin/ExtensionFunctionType;", "([Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/engine/ApplicationEngineEnvironment;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommandLineJvm.kt */
public final class CommandLineJvmKt {
    public static /* synthetic */ ApplicationEngineEnvironment commandLineEnvironment$default(String[] strArr, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            function1 = CommandLineJvmKt$commandLineEnvironment$1.INSTANCE;
        }
        return commandLineEnvironment(strArr, function1);
    }

    public static final ApplicationEngineEnvironment commandLineEnvironment(String[] strArr, Function1<? super ApplicationEngineEnvironmentBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(strArr, "args");
        Intrinsics.checkNotNullParameter(function1, "environmentBuilder");
        return CommandLineKt.buildCommandLineEnvironment(strArr, function1);
    }
}
