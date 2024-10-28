package io.ktor.server.netty;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(d1 = {"\u0000\u0015\n\u0000\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0007*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001R\u0014\u0010\u0004\u001a\u00020\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"io/ktor/server/netty/NettyApplicationRequestHeaders$entries$1$1", "", "", "", "key", "getKey", "()Ljava/lang/String;", "value", "getValue", "()Ljava/util/List;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationRequestHeaders.kt */
public final class NettyApplicationRequestHeaders$entries$1$1 implements Map.Entry<String, List<? extends String>>, KMappedMarker {
    final /* synthetic */ String $it;
    final /* synthetic */ NettyApplicationRequestHeaders this$0;

    public /* bridge */ /* synthetic */ Object setValue(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public List<String> setValue(List<String> list) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    NettyApplicationRequestHeaders$entries$1$1(String str, NettyApplicationRequestHeaders nettyApplicationRequestHeaders) {
        this.$it = str;
        this.this$0 = nettyApplicationRequestHeaders;
    }

    public String getKey() {
        String str = this.$it;
        Intrinsics.checkNotNullExpressionValue(str, "it");
        return str;
    }

    public List<String> getValue() {
        List<String> all = this.this$0.headers.getAll(this.$it);
        Intrinsics.checkNotNullExpressionValue(all, "headers.getAll(it)");
        return all;
    }
}
