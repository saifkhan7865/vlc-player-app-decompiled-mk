package io.ktor.websocket;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u0006\u0012\u0002\b\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/websocket/WebSocketExtension;", "ConfigType", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketExtension.kt */
final class WebSocketExtensionsConfig$install$2 extends Lambda implements Function0<WebSocketExtension<?>> {
    final /* synthetic */ Function1<ConfigType, Unit> $config;
    final /* synthetic */ WebSocketExtensionFactory<ConfigType, ?> $extension;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebSocketExtensionsConfig$install$2(WebSocketExtensionFactory<ConfigType, ?> webSocketExtensionFactory, Function1<? super ConfigType, Unit> function1) {
        super(0);
        this.$extension = webSocketExtensionFactory;
        this.$config = function1;
    }

    public final WebSocketExtension<?> invoke() {
        return this.$extension.install(this.$config);
    }
}
