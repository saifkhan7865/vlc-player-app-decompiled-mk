package io.ktor.server.auth;

import io.ktor.http.auth.HttpAuthHeader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/http/auth/HttpAuthHeader;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ForbiddenResponse.kt */
final class ForbiddenResponse$headers$1 extends Lambda implements Function1<HttpAuthHeader, CharSequence> {
    public static final ForbiddenResponse$headers$1 INSTANCE = new ForbiddenResponse$headers$1();

    ForbiddenResponse$headers$1() {
        super(1);
    }

    public final CharSequence invoke(HttpAuthHeader httpAuthHeader) {
        Intrinsics.checkNotNullParameter(httpAuthHeader, "it");
        return httpAuthHeader.render();
    }
}
