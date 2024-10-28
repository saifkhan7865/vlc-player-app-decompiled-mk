package io.ktor.server.netty.cio;

import io.ktor.http.HttpStatusCode;
import io.ktor.server.netty.NettyApplicationResponse;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"UNFLUSHED_LIMIT", "", "isUpgradeResponse", "", "Lio/ktor/server/netty/NettyApplicationResponse;", "ktor-server-netty"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttpResponsePipeline.kt */
public final class NettyHttpResponsePipelineKt {
    private static final int UNFLUSHED_LIMIT = 65536;

    /* access modifiers changed from: private */
    public static final boolean isUpgradeResponse(NettyApplicationResponse nettyApplicationResponse) {
        HttpStatusCode status = nettyApplicationResponse.status();
        return status != null && status.getValue() == HttpStatusCode.Companion.getSwitchingProtocols().getValue();
    }
}
