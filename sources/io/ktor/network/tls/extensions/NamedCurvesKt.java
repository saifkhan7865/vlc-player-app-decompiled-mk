package io.ktor.network.tls.extensions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"SupportedNamedCurves", "", "Lio/ktor/network/tls/extensions/NamedCurve;", "getSupportedNamedCurves", "()Ljava/util/List;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: NamedCurves.kt */
public final class NamedCurvesKt {
    private static final List<NamedCurve> SupportedNamedCurves = CollectionsKt.listOf(NamedCurve.secp256r1, NamedCurve.secp384r1);

    public static final List<NamedCurve> getSupportedNamedCurves() {
        return SupportedNamedCurves;
    }
}
