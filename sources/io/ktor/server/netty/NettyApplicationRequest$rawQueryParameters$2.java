package io.ktor.server.netty;

import io.ktor.http.Parameters;
import io.ktor.http.QueryKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/http/Parameters;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationRequest.kt */
final class NettyApplicationRequest$rawQueryParameters$2 extends Lambda implements Function0<Parameters> {
    final /* synthetic */ NettyApplicationRequest this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NettyApplicationRequest$rawQueryParameters$2(NettyApplicationRequest nettyApplicationRequest) {
        super(0);
        this.this$0 = nettyApplicationRequest;
    }

    public final Parameters invoke() {
        Integer valueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) this.this$0.getUri(), '?', 0, false, 6, (Object) null));
        if (valueOf.intValue() == -1) {
            valueOf = null;
        }
        if (valueOf == null) {
            return Parameters.Companion.getEmpty();
        }
        return QueryKt.parseQueryString$default(this.this$0.getUri(), valueOf.intValue() + 1, 0, false, 4, (Object) null);
    }
}
