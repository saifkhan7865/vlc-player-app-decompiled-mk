package io.ktor.server.auth;

import androidx.core.app.NotificationCompat;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/ktor/http/auth/HttpAuthHeader;", "call", "Lio/ktor/server/application/ApplicationCall;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BearerAuth.kt */
final class BearerAuthenticationProvider$Config$getAuthHeader$1 extends Lambda implements Function1<ApplicationCall, HttpAuthHeader> {
    public static final BearerAuthenticationProvider$Config$getAuthHeader$1 INSTANCE = new BearerAuthenticationProvider$Config$getAuthHeader$1();

    BearerAuthenticationProvider$Config$getAuthHeader$1() {
        super(1);
    }

    public final HttpAuthHeader invoke(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        return HeadersKt.parseAuthorizationHeader(applicationCall.getRequest());
    }
}
