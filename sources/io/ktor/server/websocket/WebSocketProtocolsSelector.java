package io.ktor.server.websocket;

import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.server.routing.RouteSelector;
import io.ktor.server.routing.RouteSelectorEvaluation;
import io.ktor.server.routing.RoutingResolveContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lio/ktor/server/websocket/WebSocketProtocolsSelector;", "Lio/ktor/server/routing/RouteSelector;", "requiredProtocol", "", "(Ljava/lang/String;)V", "getRequiredProtocol", "()Ljava/lang/String;", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Routing.kt */
final class WebSocketProtocolsSelector extends RouteSelector {
    private final String requiredProtocol;

    public final String getRequiredProtocol() {
        return this.requiredProtocol;
    }

    public WebSocketProtocolsSelector(String str) {
        Intrinsics.checkNotNullParameter(str, "requiredProtocol");
        this.requiredProtocol = str;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        String str = routingResolveContext.getCall().getRequest().getHeaders().get(HttpHeaders.INSTANCE.getSecWebSocketProtocol());
        if (str == null) {
            WebSocketsKt.getLOGGER().trace("Skipping WebSocket plugin because no Sec-WebSocket-Protocol header provided.");
            return RouteSelectorEvaluation.Companion.getFailedParameter();
        }
        Iterable<HeaderValue> parseHeaderValue = HttpHeaderValueParserKt.parseHeaderValue(str);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parseHeaderValue, 10));
        for (HeaderValue value : parseHeaderValue) {
            arrayList.add(value.getValue());
        }
        if (((List) arrayList).contains(this.requiredProtocol)) {
            return RouteSelectorEvaluation.Companion.getConstant();
        }
        Logger logger = WebSocketsKt.getLOGGER();
        logger.trace("Skipping WebSocket plugin because no Sec-WebSocket-Protocol header " + str + " is not matching " + this.requiredProtocol + '.');
        return RouteSelectorEvaluation.Companion.getFailedParameter();
    }
}
