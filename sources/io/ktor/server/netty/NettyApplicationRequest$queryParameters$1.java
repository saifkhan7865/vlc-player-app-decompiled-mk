package io.ktor.server.netty;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Parameters;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.QueryStringDecoder;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010'\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0010 \n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001JF\u0010\b\u001a@\u0012<\u0012:\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b\u0012(\u0012&\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b \f*\u0012\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u000e0\r0\n0\tH\u0016J6\u0010\u000f\u001a(\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b\u0018\u0001 \f*\u0012\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b\u0018\u00010\u000e0\r2\u0006\u0010\u0010\u001a\u00020\u000bH\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\u0016\u0010\u0012\u001a\u0010\u0012\f\u0012\n \f*\u0004\u0018\u00010\u000b0\u000b0\tH\u0016R\u0014\u0010\u0002\u001a\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"io/ktor/server/netty/NettyApplicationRequest$queryParameters$1", "Lio/ktor/http/Parameters;", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "decoder", "Lio/netty/handler/codec/http/QueryStringDecoder;", "entries", "", "", "", "kotlin.jvm.PlatformType", "", "", "getAll", "name", "isEmpty", "names", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationRequest.kt */
public final class NettyApplicationRequest$queryParameters$1 implements Parameters {
    private final QueryStringDecoder decoder;

    public boolean getCaseInsensitiveName() {
        return true;
    }

    NettyApplicationRequest$queryParameters$1(NettyApplicationRequest nettyApplicationRequest) {
        this.decoder = new QueryStringDecoder(nettyApplicationRequest.getUri(), HttpConstants.DEFAULT_CHARSET, true, 1024, true);
    }

    public boolean contains(String str) {
        return Parameters.DefaultImpls.contains(this, str);
    }

    public boolean contains(String str, String str2) {
        return Parameters.DefaultImpls.contains(this, str, str2);
    }

    public void forEach(Function2<? super String, ? super List<String>, Unit> function2) {
        Parameters.DefaultImpls.forEach(this, function2);
    }

    public String get(String str) {
        return Parameters.DefaultImpls.get(this, str);
    }

    public List<String> getAll(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        return this.decoder.parameters().get(str);
    }

    public Set<String> names() {
        return this.decoder.parameters().keySet();
    }

    public Set<Map.Entry<String, List<String>>> entries() {
        return this.decoder.parameters().entrySet();
    }

    public boolean isEmpty() {
        return this.decoder.parameters().isEmpty();
    }
}
