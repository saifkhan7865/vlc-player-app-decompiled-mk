package io.ktor.server.http.content;

import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/http/content/StaticContentConfig;", "Ljava/net/URL;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SinglePageApplication.kt */
final class SinglePageApplicationKt$singlePageApplication$2 extends Lambda implements Function1<StaticContentConfig<URL>, Unit> {
    final /* synthetic */ SPAConfig $config;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SinglePageApplicationKt$singlePageApplication$2(SPAConfig sPAConfig) {
        super(1);
        this.$config = sPAConfig;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((StaticContentConfig<URL>) (StaticContentConfig) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(StaticContentConfig<URL> staticContentConfig) {
        Intrinsics.checkNotNullParameter(staticContentConfig, "$this$staticResources");
        staticContentConfig.m1475default(this.$config.getDefaultPage());
        for (Function1 singlePageApplicationKt$singlePageApplication$2$1$1 : this.$config.getIgnoredFiles$ktor_server_core()) {
            staticContentConfig.exclude(new SinglePageApplicationKt$singlePageApplication$2$1$1(singlePageApplicationKt$singlePageApplication$2$1$1));
        }
    }
}
