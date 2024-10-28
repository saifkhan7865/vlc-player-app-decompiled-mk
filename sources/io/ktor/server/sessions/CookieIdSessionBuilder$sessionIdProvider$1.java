package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "S", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionsBuilder.kt */
final class CookieIdSessionBuilder$sessionIdProvider$1 extends Lambda implements Function0<String> {
    public static final CookieIdSessionBuilder$sessionIdProvider$1 INSTANCE = new CookieIdSessionBuilder$sessionIdProvider$1();

    CookieIdSessionBuilder$sessionIdProvider$1() {
        super(0);
    }

    public final String invoke() {
        return SessionIdProviderKt.generateSessionId();
    }
}
