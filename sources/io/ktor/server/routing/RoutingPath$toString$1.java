package io.ktor.server.routing;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/routing/RoutingPathSegment;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingPath.kt */
final class RoutingPath$toString$1 extends Lambda implements Function1<RoutingPathSegment, CharSequence> {
    public static final RoutingPath$toString$1 INSTANCE = new RoutingPath$toString$1();

    RoutingPath$toString$1() {
        super(1);
    }

    public final CharSequence invoke(RoutingPathSegment routingPathSegment) {
        Intrinsics.checkNotNullParameter(routingPathSegment, "it");
        return routingPathSegment.getValue();
    }
}
