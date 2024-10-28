package io.ktor.server.plugins;

import io.ktor.http.HttpMethod;
import io.ktor.http.RequestConnectionPoint;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/HttpMethod;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: OriginConnectionPoint.kt */
final class MutableOriginConnectionPoint$method$2 extends Lambda implements Function0<HttpMethod> {
    final /* synthetic */ RequestConnectionPoint $delegate;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MutableOriginConnectionPoint$method$2(RequestConnectionPoint requestConnectionPoint) {
        super(0);
        this.$delegate = requestConnectionPoint;
    }

    public final HttpMethod invoke() {
        return this.$delegate.getMethod();
    }
}
