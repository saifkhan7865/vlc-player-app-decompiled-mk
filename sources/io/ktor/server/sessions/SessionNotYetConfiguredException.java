package io.ktor.server.sessions;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/server/sessions/SessionNotYetConfiguredException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "()V", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionData.kt */
public final class SessionNotYetConfiguredException extends IllegalStateException {
    public SessionNotYetConfiguredException() {
        super("Sessions are not yet ready: you are asking it to early before the Sessions plugin.");
    }
}
