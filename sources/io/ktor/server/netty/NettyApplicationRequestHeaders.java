package io.ktor.server.netty;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpRequest;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0011\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J \u0010\u000f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00120\u00110\u0010H\u0016J(\u0010\u0013\u001a\u00020\u00142\u001e\u0010\u0015\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u0012\u0012\u0004\u0012\u00020\u00140\u0016H\u0016J\u0013\u0010\u0017\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00122\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\u0006H\u0016J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u0010H\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lio/ktor/server/netty/NettyApplicationRequestHeaders;", "Lio/ktor/http/Headers;", "request", "Lio/netty/handler/codec/http/HttpRequest;", "(Lio/netty/handler/codec/http/HttpRequest;)V", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "headers", "Lio/netty/handler/codec/http/HttpHeaders;", "contains", "name", "", "value", "entries", "", "", "", "forEach", "", "body", "Lkotlin/Function2;", "get", "getAll", "isEmpty", "names", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationRequestHeaders.kt */
public final class NettyApplicationRequestHeaders implements Headers {
    /* access modifiers changed from: private */
    public final HttpHeaders headers;

    public boolean getCaseInsensitiveName() {
        return true;
    }

    public NettyApplicationRequestHeaders(HttpRequest httpRequest) {
        Intrinsics.checkNotNullParameter(httpRequest, "request");
        HttpHeaders headers2 = httpRequest.headers();
        Intrinsics.checkNotNullExpressionValue(headers2, "request.headers()");
        this.headers = headers2;
    }

    public String get(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return this.headers.get(str);
    }

    public boolean contains(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return this.headers.contains(str);
    }

    public boolean contains(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        return this.headers.contains(str, str2, true);
    }

    public List<String> getAll(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        List<String> all = this.headers.getAll(str);
        Intrinsics.checkNotNullExpressionValue(all, "it");
        if (!all.isEmpty()) {
            return all;
        }
        return null;
    }

    public void forEach(Function2<? super String, ? super List<String>, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "body");
        Set<String> names = this.headers.names();
        Intrinsics.checkNotNullExpressionValue(names, "names");
        for (String str : names) {
            Intrinsics.checkNotNullExpressionValue(str, "it");
            List<String> all = this.headers.getAll(str);
            Intrinsics.checkNotNullExpressionValue(all, "headers.getAll(it)");
            function2.invoke(str, all);
        }
    }

    public Set<Map.Entry<String, List<String>>> entries() {
        Set<String> names = this.headers.names();
        Intrinsics.checkNotNullExpressionValue(names, "names");
        Collection linkedHashSet = new LinkedHashSet(names.size());
        for (String nettyApplicationRequestHeaders$entries$1$1 : names) {
            linkedHashSet.add(new NettyApplicationRequestHeaders$entries$1$1(nettyApplicationRequestHeaders$entries$1$1, this));
        }
        return (Set) linkedHashSet;
    }

    public boolean isEmpty() {
        return this.headers.isEmpty();
    }

    public Set<String> names() {
        Set<String> names = this.headers.names();
        Intrinsics.checkNotNullExpressionValue(names, "headers.names()");
        return names;
    }
}
