package io.ktor.server.routing;

import io.ktor.server.routing.RoutingResolveResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "path", "", "Lio/ktor/server/routing/RoutingResolveResult$Success;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: RoutingResolveTrace.kt */
final class RoutingResolveTrace$buildText$1$2 extends Lambda implements Function1<List<? extends RoutingResolveResult.Success>, CharSequence> {
    public static final RoutingResolveTrace$buildText$1$2 INSTANCE = new RoutingResolveTrace$buildText$1$2();

    RoutingResolveTrace$buildText$1$2() {
        super(1);
    }

    public final CharSequence invoke(List<RoutingResolveResult.Success> list) {
        Intrinsics.checkNotNullParameter(list, ArtworkProvider.PATH);
        return CollectionsKt.joinToString$default(list, " -> ", "  ", (CharSequence) null, 0, (CharSequence) null, AnonymousClass1.INSTANCE, 28, (Object) null);
    }
}
