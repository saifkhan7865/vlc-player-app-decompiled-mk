package io.ktor.server.engine.internal;

import io.ktor.server.engine.ShutDownUrl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/engine/ShutDownUrl$Config;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationUtilsJvm.kt */
final class ApplicationUtilsJvmKt$configureShutdownUrl$1 extends Lambda implements Function1<ShutDownUrl.Config, Unit> {
    final /* synthetic */ String $url;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ApplicationUtilsJvmKt$configureShutdownUrl$1(String str) {
        super(1);
        this.$url = str;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ShutDownUrl.Config) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ShutDownUrl.Config config) {
        Intrinsics.checkNotNullParameter(config, "$this$install");
        config.setShutDownUrl(this.$url);
    }
}
