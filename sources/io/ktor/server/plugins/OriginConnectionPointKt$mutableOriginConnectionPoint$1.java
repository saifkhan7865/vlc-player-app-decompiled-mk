package io.ktor.server.plugins;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/server/plugins/MutableOriginConnectionPoint;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OriginConnectionPoint.kt */
final class OriginConnectionPointKt$mutableOriginConnectionPoint$1 extends Lambda implements Function0<MutableOriginConnectionPoint> {
    final /* synthetic */ ApplicationCall $this_mutableOriginConnectionPoint;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OriginConnectionPointKt$mutableOriginConnectionPoint$1(ApplicationCall applicationCall) {
        super(0);
        this.$this_mutableOriginConnectionPoint = applicationCall;
    }

    public final MutableOriginConnectionPoint invoke() {
        return new MutableOriginConnectionPoint(new OriginConnectionPoint(this.$this_mutableOriginConnectionPoint));
    }
}
