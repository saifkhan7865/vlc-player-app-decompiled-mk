package io.ktor.server.response;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.UnsafeHeaderException;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052\b\b\u0002\u0010\u000e\u001a\u00020\u000fJ\u0011\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0005H\u0002J\u0018\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H$J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\f\u001a\u00020\u0005H\u0002J\u000e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u0014H$J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\u0006\u0010\f\u001a\u00020\u0005H$J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00142\u0006\u0010\f\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0017"}, d2 = {"Lio/ktor/server/response/ResponseHeaders;", "", "()V", "managedByEngineHeaders", "", "", "getManagedByEngineHeaders", "()Ljava/util/Set;", "allValues", "Lio/ktor/http/Headers;", "append", "", "name", "value", "safeOnly", "", "contains", "engineAppendHeader", "get", "getEngineHeaderNames", "", "getEngineHeaderValues", "values", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ResponseHeaders.kt */
public abstract class ResponseHeaders {
    private final Set<String> managedByEngineHeaders = SetsKt.emptySet();

    /* access modifiers changed from: protected */
    public abstract void engineAppendHeader(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract List<String> getEngineHeaderNames();

    /* access modifiers changed from: protected */
    public abstract List<String> getEngineHeaderValues(String str);

    /* access modifiers changed from: protected */
    public Set<String> getManagedByEngineHeaders() {
        return this.managedByEngineHeaders;
    }

    public final boolean contains(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return get(str) != null;
    }

    public String get(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return (String) CollectionsKt.firstOrNull(getEngineHeaderValues(str));
    }

    public final List<String> values(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return getEngineHeaderValues(str);
    }

    public final Headers allValues() {
        Headers.Companion companion = Headers.Companion;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        for (String str : CollectionsKt.toSet(getEngineHeaderNames())) {
            headersBuilder.appendAll(str, getEngineHeaderValues(str));
        }
        return headersBuilder.build();
    }

    public static /* synthetic */ void append$default(ResponseHeaders responseHeaders, String str, String str2, boolean z, int i, Object obj) {
        if (obj == null) {
            if ((i & 4) != 0) {
                z = true;
            }
            responseHeaders.append(str, str2, z);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: append");
    }

    public final void append(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        if (!getManagedByEngineHeaders().contains(str)) {
            if (!z || !HttpHeaders.INSTANCE.isUnsafe(str)) {
                HttpHeaders.INSTANCE.checkHeaderName(str);
                HttpHeaders.INSTANCE.checkHeaderValue(str2);
                engineAppendHeader(str, str2);
                return;
            }
            throw new UnsafeHeaderException(str);
        }
    }
}
