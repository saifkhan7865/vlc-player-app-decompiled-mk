package io.ktor.server.application.debug;

import io.ktor.util.debug.ContextUtilsKt;
import io.ktor.util.debug.plugins.PluginsTrace;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u001a!\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u001a!\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\u0005\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"ijDebugReportHandlerFinished", "", "pluginName", "", "handler", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ijDebugReportHandlerStarted", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Utils.kt */
public final class UtilsKt {
    public static final Object ijDebugReportHandlerStarted(String str, String str2, Continuation<? super Unit> continuation) {
        Object useContextElementInDebugMode = ContextUtilsKt.useContextElementInDebugMode(PluginsTrace.Key, new UtilsKt$ijDebugReportHandlerStarted$2(str, str2), continuation);
        return useContextElementInDebugMode == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? useContextElementInDebugMode : Unit.INSTANCE;
    }

    public static final Object ijDebugReportHandlerFinished(String str, String str2, Continuation<? super Unit> continuation) {
        Object useContextElementInDebugMode = ContextUtilsKt.useContextElementInDebugMode(PluginsTrace.Key, new UtilsKt$ijDebugReportHandlerFinished$2(str, str2), continuation);
        return useContextElementInDebugMode == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? useContextElementInDebugMode : Unit.INSTANCE;
    }
}
