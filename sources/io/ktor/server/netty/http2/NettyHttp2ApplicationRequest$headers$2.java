package io.ktor.server.netty.http2;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Headers;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp2ApplicationRequest.kt */
final class NettyHttp2ApplicationRequest$headers$2 extends Lambda implements Function0<Headers> {
    final /* synthetic */ NettyHttp2ApplicationRequest this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyHttp2ApplicationRequest$headers$2(NettyHttp2ApplicationRequest nettyHttp2ApplicationRequest) {
        super(0);
        this.this$0 = nettyHttp2ApplicationRequest;
    }

    public final Headers invoke() {
        Headers.Companion companion = Headers.Companion;
        NettyHttp2ApplicationRequest nettyHttp2ApplicationRequest = this.this$0;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        for (Map.Entry entry : nettyHttp2ApplicationRequest.getNettyHeaders()) {
            Intrinsics.checkNotNullExpressionValue(entry, "(name, value)");
            CharSequence charSequence = (CharSequence) entry.getKey();
            CharSequence charSequence2 = (CharSequence) entry.getValue();
            Intrinsics.checkNotNullExpressionValue(charSequence, ContentDisposition.Parameters.Name);
            if (charSequence.length() > 0 && charSequence.charAt(0) != ':') {
                headersBuilder.append(charSequence.toString(), charSequence2.toString());
            }
        }
        return headersBuilder.build();
    }
}
