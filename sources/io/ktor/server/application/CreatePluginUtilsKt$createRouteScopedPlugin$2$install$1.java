package io.ktor.server.application;

import io.ktor.server.config.ApplicationConfig;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "PluginConfigT", "", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePluginUtils.kt */
final class CreatePluginUtilsKt$createRouteScopedPlugin$2$install$1 extends Lambda implements Function0<PluginConfigT> {
    final /* synthetic */ ApplicationConfig $config;
    final /* synthetic */ Function1<ApplicationConfig, PluginConfigT> $createConfiguration;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CreatePluginUtilsKt$createRouteScopedPlugin$2$install$1(Function1<? super ApplicationConfig, ? extends PluginConfigT> function1, ApplicationConfig applicationConfig) {
        super(0);
        this.$createConfiguration = function1;
        this.$config = applicationConfig;
    }

    public final PluginConfigT invoke() {
        return this.$createConfiguration.invoke(this.$config);
    }
}
