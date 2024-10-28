package io.ktor.server.auth;

import androidx.core.app.NotificationCompat;
import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/auth/SessionChallengeContext;", "", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)V", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionAuth.kt */
public final class SessionChallengeContext {
    private final ApplicationCall call;

    public SessionChallengeContext(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        this.call = applicationCall;
    }

    public final ApplicationCall getCall() {
        return this.call;
    }
}
