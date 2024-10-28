package io.ktor.server.engine;

import io.ktor.server.engine.ApplicationEngine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0005*\u0002H\u0004H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "TEngine", "Lio/ktor/server/engine/ApplicationEngine;", "TConfiguration", "Lio/ktor/server/engine/ApplicationEngine$Configuration;", "invoke", "(Lio/ktor/server/engine/ApplicationEngine$Configuration;)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmbeddedServer.kt */
final class EmbeddedServerKt$embeddedServer$1 extends Lambda implements Function1<TConfiguration, Unit> {
    public static final EmbeddedServerKt$embeddedServer$1 INSTANCE = new EmbeddedServerKt$embeddedServer$1();

    EmbeddedServerKt$embeddedServer$1() {
        super(1);
    }

    public final void invoke(TConfiguration tconfiguration) {
        Intrinsics.checkNotNullParameter(tconfiguration, "$this$null");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEngine.Configuration) obj);
        return Unit.INSTANCE;
    }
}
