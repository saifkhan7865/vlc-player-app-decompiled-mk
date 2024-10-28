package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationRouteSelector$toString$1 extends Lambda implements Function1<String, CharSequence> {
    public static final AuthenticationRouteSelector$toString$1 INSTANCE = new AuthenticationRouteSelector$toString$1();

    AuthenticationRouteSelector$toString$1() {
        super(1);
    }

    public final CharSequence invoke(String str) {
        if (str == null) {
            str = "\"default\"";
        }
        return str;
    }
}
