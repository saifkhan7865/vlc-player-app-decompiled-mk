package io.ktor.client.call;

import io.ktor.http.Url;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/client/call/UnsupportedUpgradeProtocolException;", "Ljava/lang/IllegalArgumentException;", "Lkotlin/IllegalArgumentException;", "url", "Lio/ktor/http/Url;", "(Lio/ktor/http/Url;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: utils.kt */
public final class UnsupportedUpgradeProtocolException extends IllegalArgumentException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UnsupportedUpgradeProtocolException(Url url) {
        super("Unsupported upgrade protocol exception: " + url);
        Intrinsics.checkNotNullParameter(url, RtspHeaders.Values.URL);
    }
}
