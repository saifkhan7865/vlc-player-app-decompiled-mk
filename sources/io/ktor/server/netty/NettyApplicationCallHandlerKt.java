package io.ktor.server.netty;

import io.ktor.http.HttpHeaders;
import io.ktor.server.netty.http1.NettyHttp1ApplicationRequest;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010 \n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0002\u001a\u00020\u0003*\b\u0012\u0004\u0012\u00020\u00010\u0004H\u0000\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0006H\u0002\u001a\f\u0010\u0007\u001a\u00020\u0003*\u00020\bH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"CHUNKED_VALUE", "", "hasValidTransferEncoding", "", "", "isSeparator", "", "isValid", "Lio/ktor/server/netty/http1/NettyHttp1ApplicationRequest;", "ktor-server-netty"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationCallHandler.kt */
public final class NettyApplicationCallHandlerKt {
    private static final String CHUNKED_VALUE = "chunked";

    private static final boolean isSeparator(char c) {
        return c == ' ' || c == ',';
    }

    public static final boolean isValid(NettyHttp1ApplicationRequest nettyHttp1ApplicationRequest) {
        List<String> all;
        Intrinsics.checkNotNullParameter(nettyHttp1ApplicationRequest, "<this>");
        if (nettyHttp1ApplicationRequest.getHttpRequest().decoderResult().isFailure()) {
            return false;
        }
        if (nettyHttp1ApplicationRequest.getHeaders().contains(HttpHeaders.INSTANCE.getTransferEncoding()) && (all = nettyHttp1ApplicationRequest.getHeaders().getAll(HttpHeaders.INSTANCE.getTransferEncoding())) != null && !hasValidTransferEncoding(all)) {
            return false;
        }
        return true;
    }

    public static final boolean hasValidTransferEncoding(List<String> list) {
        int i;
        Intrinsics.checkNotNullParameter(list, "<this>");
        int i2 = 0;
        for (Object next : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) next;
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, "chunked", 0, false, 6, (Object) null);
            if (indexOf$default != -1 && ((indexOf$default <= 0 || isSeparator(str.charAt(indexOf$default - 1))) && (((i = indexOf$default + 7) >= str.length() || isSeparator(str.charAt(i))) && (i2 != CollectionsKt.getLastIndex(list) || i < str.length())))) {
                return false;
            }
            i2 = i3;
        }
        return true;
    }
}
