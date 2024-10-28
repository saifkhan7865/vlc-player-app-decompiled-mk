package io.ktor.server.response;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"appendIfAbsent", "", "Lio/ktor/server/response/ResponseHeaders;", "name", "", "value", "safeOnly", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ResponseHeaders.kt */
public final class ResponseHeadersKt {
    public static /* synthetic */ void appendIfAbsent$default(ResponseHeaders responseHeaders, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        appendIfAbsent(responseHeaders, str, str2, z);
    }

    public static final void appendIfAbsent(ResponseHeaders responseHeaders, String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(responseHeaders, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        if (!responseHeaders.contains(str)) {
            responseHeaders.append(str, str2, z);
        }
    }
}
