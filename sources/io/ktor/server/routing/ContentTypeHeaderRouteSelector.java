package io.ktor.server.routing;

import io.ktor.http.ContentType;
import io.ktor.http.HeaderValue;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.Parameters;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.server.routing.RouteSelectorEvaluation;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lio/ktor/server/routing/ContentTypeHeaderRouteSelector;", "Lio/ktor/server/routing/RouteSelector;", "contentType", "Lio/ktor/http/ContentType;", "(Lio/ktor/http/ContentType;)V", "getContentType", "()Lio/ktor/http/ContentType;", "failedEvaluation", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "component1", "copy", "equals", "", "other", "", "evaluate", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "context", "Lio/ktor/server/routing/RoutingResolveContext;", "segmentIndex", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public final class ContentTypeHeaderRouteSelector extends RouteSelector {
    private final ContentType contentType;
    private final RouteSelectorEvaluation.Failure failedEvaluation = new RouteSelectorEvaluation.Failure(0.01d, HttpStatusCode.Companion.getUnsupportedMediaType());

    public static /* synthetic */ ContentTypeHeaderRouteSelector copy$default(ContentTypeHeaderRouteSelector contentTypeHeaderRouteSelector, ContentType contentType2, int i, Object obj) {
        if ((i & 1) != 0) {
            contentType2 = contentTypeHeaderRouteSelector.contentType;
        }
        return contentTypeHeaderRouteSelector.copy(contentType2);
    }

    public final ContentType component1() {
        return this.contentType;
    }

    public final ContentTypeHeaderRouteSelector copy(ContentType contentType2) {
        Intrinsics.checkNotNullParameter(contentType2, CMSAttributeTableGenerator.CONTENT_TYPE);
        return new ContentTypeHeaderRouteSelector(contentType2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ContentTypeHeaderRouteSelector) && Intrinsics.areEqual((Object) this.contentType, (Object) ((ContentTypeHeaderRouteSelector) obj).contentType);
    }

    public int hashCode() {
        return this.contentType.hashCode();
    }

    public final ContentType getContentType() {
        return this.contentType;
    }

    public ContentTypeHeaderRouteSelector(ContentType contentType2) {
        Intrinsics.checkNotNullParameter(contentType2, CMSAttributeTableGenerator.CONTENT_TYPE);
        this.contentType = contentType2;
    }

    public RouteSelectorEvaluation evaluate(RoutingResolveContext routingResolveContext, int i) {
        Object obj;
        Intrinsics.checkNotNullParameter(routingResolveContext, "context");
        Iterator it = HttpHeaderValueParserKt.parseAndSortContentTypeHeader(ApplicationRequestPropertiesKt.header(routingResolveContext.getCall().getRequest(), HttpHeaders.INSTANCE.getContentType())).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (ContentType.Companion.parse(((HeaderValue) obj).getValue()).match(this.contentType)) {
                break;
            }
        }
        HeaderValue headerValue = (HeaderValue) obj;
        if (headerValue == null) {
            return this.failedEvaluation;
        }
        return new RouteSelectorEvaluation.Success(headerValue.getQuality(), (Parameters) null, 0, 6, (DefaultConstructorMarker) null);
    }

    public String toString() {
        return "(contentType = " + this.contentType + ')';
    }
}
