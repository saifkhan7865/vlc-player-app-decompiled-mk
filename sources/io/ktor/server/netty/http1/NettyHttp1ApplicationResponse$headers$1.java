package io.ktor.server.netty.http1;

import io.ktor.http.ContentDisposition;
import io.ktor.server.response.ResponseHeaders;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0014J\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u0005H\u0002J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0014J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u000b"}, d2 = {"io/ktor/server/netty/http1/NettyHttp1ApplicationResponse$headers$1", "Lio/ktor/server/response/ResponseHeaders;", "engineAppendHeader", "", "name", "", "value", "get", "getEngineHeaderNames", "", "getEngineHeaderValues", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp1ApplicationResponse.kt */
public final class NettyHttp1ApplicationResponse$headers$1 extends ResponseHeaders {
    final /* synthetic */ NettyHttp1ApplicationResponse this$0;

    NettyHttp1ApplicationResponse$headers$1(NettyHttp1ApplicationResponse nettyHttp1ApplicationResponse) {
        this.this$0 = nettyHttp1ApplicationResponse;
    }

    /* access modifiers changed from: protected */
    public void engineAppendHeader(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        if (!this.this$0.getResponseMessageSent()) {
            this.this$0.responseHeaders.add(str, (Object) str2);
        } else if (this.this$0.getResponseReady$ktor_server_netty().isCancelled()) {
            throw new CancellationException("Call execution has been cancelled");
        } else {
            throw new UnsupportedOperationException("Headers can no longer be set because response was already completed");
        }
    }

    public String get(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return this.this$0.responseHeaders.get(str);
    }

    /* access modifiers changed from: protected */
    public List<String> getEngineHeaderNames() {
        Iterable<Map.Entry> access$getResponseHeaders$p = this.this$0.responseHeaders;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(access$getResponseHeaders$p, 10));
        for (Map.Entry key : access$getResponseHeaders$p) {
            arrayList.add((String) key.getKey());
        }
        return (List) arrayList;
    }

    /* access modifiers changed from: protected */
    public List<String> getEngineHeaderValues(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        List<String> all = this.this$0.responseHeaders.getAll(str);
        return all == null ? CollectionsKt.emptyList() : all;
    }
}
