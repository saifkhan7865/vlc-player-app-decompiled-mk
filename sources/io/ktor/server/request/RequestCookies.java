package io.ktor.server.request;

import io.ktor.http.ContentDisposition;
import io.ktor.http.CookieEncoding;
import io.ktor.http.CookieKt;
import io.ktor.util.collections.ConcurrentMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u000b\b\u0016\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000bH\u0014J\u001d\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\t2\b\b\u0002\u0010\u0015\u001a\u00020\bH\u0002R&\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\u0004\u0012\u00020\t0\u0006X\u0004¢\u0006\u0002\n\u0000R'\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u000b8FX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0016"}, d2 = {"Lio/ktor/server/request/RequestCookies;", "", "request", "Lio/ktor/server/request/ApplicationRequest;", "(Lio/ktor/server/request/ApplicationRequest;)V", "map", "Lio/ktor/util/collections/ConcurrentMap;", "Lkotlin/Pair;", "Lio/ktor/http/CookieEncoding;", "", "rawCookies", "", "getRawCookies", "()Ljava/util/Map;", "rawCookies$delegate", "Lkotlin/Lazy;", "getRequest", "()Lio/ktor/server/request/ApplicationRequest;", "fetchCookies", "get", "name", "encoding", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RequestCookies.kt */
public class RequestCookies {
    private final ConcurrentMap<Pair<CookieEncoding, String>, String> map = new ConcurrentMap<>(0, 1, (DefaultConstructorMarker) null);
    private final Lazy rawCookies$delegate = LazyKt.lazy(new RequestCookies$rawCookies$2(this));
    private final ApplicationRequest request;

    public RequestCookies(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "request");
        this.request = applicationRequest;
    }

    /* access modifiers changed from: protected */
    public final ApplicationRequest getRequest() {
        return this.request;
    }

    public final Map<String, String> getRawCookies() {
        return (Map) this.rawCookies$delegate.getValue();
    }

    public static /* synthetic */ String get$default(RequestCookies requestCookies, String str, CookieEncoding cookieEncoding, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                cookieEncoding = CookieEncoding.URI_ENCODING;
            }
            return requestCookies.get(str, cookieEncoding);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: get");
    }

    public final String get(String str, CookieEncoding cookieEncoding) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(cookieEncoding, "encoding");
        String str2 = getRawCookies().get(str);
        if (str2 == null) {
            return null;
        }
        return this.map.computeIfAbsent(TuplesKt.to(cookieEncoding, str), new RequestCookies$get$1(str2, cookieEncoding));
    }

    /* access modifiers changed from: protected */
    public Map<String, String> fetchCookies() {
        List<String> all = this.request.getHeaders().getAll("Cookie");
        if (all == null) {
            return MapsKt.emptyMap();
        }
        HashMap hashMap = new HashMap(all.size());
        for (String parseClientCookiesHeader$default : all) {
            hashMap.putAll(CookieKt.parseClientCookiesHeader$default(parseClientCookiesHeader$default, false, 2, (Object) null));
        }
        return hashMap;
    }
}
