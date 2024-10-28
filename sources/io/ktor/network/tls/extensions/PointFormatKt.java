package io.ktor.network.tls.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"SupportedPointFormats", "", "Lio/ktor/network/tls/extensions/PointFormat;", "getSupportedPointFormats", "()Ljava/util/List;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PointFormat.kt */
public final class PointFormatKt {
    private static final List<PointFormat> SupportedPointFormats = CollectionsKt.listOf(PointFormat.UNCOMPRESSED, PointFormat.ANSIX962_COMPRESSED_PRIME, PointFormat.ANSIX962_COMPRESSED_CHAR2);

    public static final List<PointFormat> getSupportedPointFormats() {
        return SupportedPointFormats;
    }
}
