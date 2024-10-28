package io.ktor.server.plugins;

import io.ktor.http.RequestConnectionPoint;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OriginConnectionPoint.kt */
final class MutableOriginConnectionPoint$remoteAddress$2 extends Lambda implements Function0<String> {
    final /* synthetic */ RequestConnectionPoint $delegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MutableOriginConnectionPoint$remoteAddress$2(RequestConnectionPoint requestConnectionPoint) {
        super(0);
        this.$delegate = requestConnectionPoint;
    }

    public final String invoke() {
        return this.$delegate.getRemoteAddress();
    }
}
