package io.ktor.server.websocket;

import io.ktor.server.application.ApplicationEnvironment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/application/ApplicationEnvironment;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSockets.kt */
final class WebSockets$Plugin$install$1$1 extends Lambda implements Function1<ApplicationEnvironment, Unit> {
    final /* synthetic */ WebSockets $webSockets;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebSockets$Plugin$install$1$1(WebSockets webSockets) {
        super(1);
        this.$webSockets = webSockets;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEnvironment) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApplicationEnvironment applicationEnvironment) {
        Intrinsics.checkNotNullParameter(applicationEnvironment, "it");
        WebSocketsKt.getLOGGER().trace("Shutdown WebSockets due to application stop");
        this.$webSockets.shutdown();
    }
}
