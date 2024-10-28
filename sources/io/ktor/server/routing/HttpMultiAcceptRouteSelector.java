package io.ktor.server.routing;

import io.ktor.http.BadContentTypeFormatException;
import io.ktor.http.ContentType;
import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.Parameters;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\t\u0010\u0014\u001a\u00020\u0013HÖ\u0001J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lio/ktor/server/routing/HttpMultiAcceptRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "contentTypes", "", "Lio/ktor/http/ContentType;", "(Ljava/util/List;)V", "getContentTypes", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class HttpMultiAcceptRouteSelector extends RouteSelector {
    private final List<ContentType> contentTypes;

    public static /* synthetic */ HttpMultiAcceptRouteSelector copy$default(HttpMultiAcceptRouteSelector httpMultiAcceptRouteSelector, List<ContentType> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = httpMultiAcceptRouteSelector.contentTypes;
        }
        return httpMultiAcceptRouteSelector.copy(list);
    }

    public final List<ContentType> component1() {
        return this.contentTypes;
    }

    public final HttpMultiAcceptRouteSelector copy(List<ContentType> list) {
        Intrinsics.checkNotNullParameter(list, "contentTypes");
        return new HttpMultiAcceptRouteSelector(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof HttpMultiAcceptRouteSelector) && Intrinsics.areEqual((Object) this.contentTypes, (Object) ((HttpMultiAcceptRouteSelector) obj).contentTypes);
    }

    public int hashCode() {
        return this.contentTypes.hashCode();
    }

    public final List<ContentType> getContentTypes() {
        return this.contentTypes;
    }

    public HttpMultiAcceptRouteSelector(List<ContentType> list) {
        Intrinsics.checkNotNullParameter(list, "contentTypes");
        this.contentTypes = list;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Object obj;
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        String str = routingResolveContext.getCall().getRequest().getHeaders().get(HttpHeaders.INSTANCE.getAccept());
        try {
            List<HeaderValue> parseAndSortContentTypeHeader = HttpHeaderValueParserKt.parseAndSortContentTypeHeader(str);
            if (parseAndSortContentTypeHeader.isEmpty()) {
                return RouteSelectorEvaluation.Companion.getMissing();
            }
            Iterator it = parseAndSortContentTypeHeader.iterator();
            loop0:
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                HeaderValue headerValue = (HeaderValue) obj;
                Iterable<ContentType> iterable = this.contentTypes;
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    for (ContentType match : iterable) {
                        if (match.match(headerValue.getValue())) {
                            break loop0;
                        }
                    }
                    continue;
                }
            }
            HeaderValue headerValue2 = (HeaderValue) obj;
            if (headerValue2 != null) {
                return new RouteSelectorEvaluation.Success(headerValue2.getQuality(), (Parameters) null, 0, 6, (DefaultConstructorMarker) null);
            }
            return RouteSelectorEvaluation.Companion.getFailedParameter();
        } catch (BadContentTypeFormatException e) {
            throw new BadRequestException("Illegal Accept header format: " + str, e);
        }
    }

    public String toString() {
        return "(contentTypes:" + this.contentTypes + ')';
    }
}
