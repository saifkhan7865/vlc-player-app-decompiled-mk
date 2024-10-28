package io.ktor.server.plugins.callloging;

import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.application.PluginBuilder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0000¨\u0006\u0004"}, d2 = {"setupMDCProvider", "", "Lio/ktor/server/application/PluginBuilder;", "Lio/ktor/server/plugins/callloging/CallLoggingConfig;", "ktor-server-call-logging"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: MDCProvider.kt */
public final class MDCProviderKt {
    public static final void setupMDCProvider(PluginBuilder<CallLoggingConfig> pluginBuilder) {
        Intrinsics.checkNotNullParameter(pluginBuilder, "<this>");
        ApplicationPluginKt.getPluginRegistry(pluginBuilder.getApplication()).put(KtorMDCProvider.Companion.getKey(), new KtorMDCProvider(pluginBuilder.getPluginConfig().getMdcEntries$ktor_server_call_logging()));
    }
}
