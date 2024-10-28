package io.ktor.server.application.debug;

import io.ktor.util.debug.plugins.PluginTraceElement;
import io.ktor.util.debug.plugins.PluginsTrace;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "trace", "Lio/ktor/util/debug/plugins/PluginsTrace;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Utils.kt */
final class UtilsKt$ijDebugReportHandlerStarted$2 extends Lambda implements Function1<PluginsTrace, Unit> {
    final /* synthetic */ String $handler;
    final /* synthetic */ String $pluginName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    UtilsKt$ijDebugReportHandlerStarted$2(String str, String str2) {
        super(1);
        this.$pluginName = str;
        this.$handler = str2;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PluginsTrace) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PluginsTrace pluginsTrace) {
        Intrinsics.checkNotNullParameter(pluginsTrace, "trace");
        pluginsTrace.getEventOrder().add(new PluginTraceElement(this.$pluginName, this.$handler, PluginTraceElement.PluginEvent.STARTED));
    }
}
