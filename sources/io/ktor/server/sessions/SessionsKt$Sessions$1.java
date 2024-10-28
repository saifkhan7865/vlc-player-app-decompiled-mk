package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Sessions.kt */
/* synthetic */ class SessionsKt$Sessions$1 extends FunctionReferenceImpl implements Function0<SessionsConfig> {
    public static final SessionsKt$Sessions$1 INSTANCE = new SessionsKt$Sessions$1();

    SessionsKt$Sessions$1() {
        super(0, SessionsConfig.class, "<init>", "<init>()V", 0);
    }

    public final SessionsConfig invoke() {
        return new SessionsConfig();
    }
}
