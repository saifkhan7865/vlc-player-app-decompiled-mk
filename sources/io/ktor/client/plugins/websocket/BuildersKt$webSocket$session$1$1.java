package io.ktor.client.plugins.websocket;

import io.ktor.http.URLBuilder;
import io.ktor.http.URLProtocol;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/http/URLBuilder;", "it", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: builders.kt */
final class BuildersKt$webSocket$session$1$1 extends Lambda implements Function2<URLBuilder, URLBuilder, Unit> {
    public static final BuildersKt$webSocket$session$1$1 INSTANCE = new BuildersKt$webSocket$session$1$1();

    BuildersKt$webSocket$session$1$1() {
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((URLBuilder) obj, (URLBuilder) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(URLBuilder uRLBuilder, URLBuilder uRLBuilder2) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "$this$url");
        Intrinsics.checkNotNullParameter(uRLBuilder2, "it");
        uRLBuilder.setProtocol(URLProtocol.Companion.getWS());
    }
}
