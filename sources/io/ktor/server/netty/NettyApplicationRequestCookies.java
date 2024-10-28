package io.ktor.server.netty;

import io.ktor.server.request.ApplicationRequest;
import io.ktor.server.request.RequestCookies;
import io.netty.handler.codec.http.cookie.Cookie;
import io.netty.handler.codec.http.cookie.ServerCookieDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006H\u0014¨\u0006\b"}, d2 = {"Lio/ktor/server/netty/NettyApplicationRequestCookies;", "Lio/ktor/server/request/RequestCookies;", "request", "Lio/ktor/server/request/ApplicationRequest;", "(Lio/ktor/server/request/ApplicationRequest;)V", "fetchCookies", "", "", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationRequestCookies.kt */
public final class NettyApplicationRequestCookies extends RequestCookies {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyApplicationRequestCookies(ApplicationRequest applicationRequest) {
        super(applicationRequest);
        Intrinsics.checkNotNullParameter(applicationRequest, "request");
    }

    /* access modifiers changed from: protected */
    public Map<String, String> fetchCookies() {
        List<String> all = getRequest().getHeaders().getAll("Cookie");
        if (all == null) {
            return MapsKt.emptyMap();
        }
        HashMap hashMap = new HashMap(all.size());
        for (String decode : all) {
            Set<Cookie> decode2 = ServerCookieDecoder.LAX.decode(decode);
            Intrinsics.checkNotNullExpressionValue(decode2, "LAX.decode(cookieHeader)");
            for (Cookie cookie : decode2) {
                hashMap.put(cookie.name(), cookie.value());
            }
            Map map = hashMap;
        }
        return hashMap;
    }
}
