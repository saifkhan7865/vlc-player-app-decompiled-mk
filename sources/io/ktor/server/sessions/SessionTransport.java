package io.ktor.server.sessions;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0007H&¨\u0006\n"}, d2 = {"Lio/ktor/server/sessions/SessionTransport;", "", "clear", "", "call", "Lio/ktor/server/application/ApplicationCall;", "receive", "", "send", "value", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTransport.kt */
public interface SessionTransport {
    void clear(ApplicationCall applicationCall);

    String receive(ApplicationCall applicationCall);

    void send(ApplicationCall applicationCall, String str);
}
