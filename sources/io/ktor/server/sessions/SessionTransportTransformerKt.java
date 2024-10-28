package io.ktor.server.sessions;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001\u001a\u0018\u0010\u0005\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0001¨\u0006\u0007"}, d2 = {"transformRead", "", "", "Lio/ktor/server/sessions/SessionTransportTransformer;", "cookieValue", "transformWrite", "value", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTransportTransformer.kt */
public final class SessionTransportTransformerKt {
    public static final String transformRead(List<? extends SessionTransportTransformer> list, String str) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        if (str == null) {
            return null;
        }
        for (T transformRead : CollectionsKt.asReversed(list)) {
            str = transformRead.transformRead(str);
            if (str == null) {
                return null;
            }
        }
        return str;
    }

    public static final String transformWrite(List<? extends SessionTransportTransformer> list, String str) {
        Intrinsics.checkNotNullParameter(list, "<this>");
        Intrinsics.checkNotNullParameter(str, "value");
        for (SessionTransportTransformer transformWrite : list) {
            str = transformWrite.transformWrite(str);
        }
        return str;
    }
}
