package io.ktor.server.sessions;

import io.ktor.util.CryptoKt;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001¨\u0006\u0002"}, d2 = {"generateSessionId", "", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionIdProvider.kt */
public final class SessionIdProviderKt {
    public static final String generateSessionId() {
        return CryptoKt.generateNonce() + CryptoKt.generateNonce();
    }
}
