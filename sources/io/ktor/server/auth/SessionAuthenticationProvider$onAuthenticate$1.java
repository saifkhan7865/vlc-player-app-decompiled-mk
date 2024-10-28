package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.SessionAuthenticationProvider", f = "SessionAuth.kt", i = {0, 0, 0}, l = {34}, m = "onAuthenticate", n = {"this", "context", "session"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: SessionAuth.kt */
final class SessionAuthenticationProvider$onAuthenticate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ SessionAuthenticationProvider<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionAuthenticationProvider$onAuthenticate$1(SessionAuthenticationProvider<T> sessionAuthenticationProvider, Continuation<? super SessionAuthenticationProvider$onAuthenticate$1> continuation) {
        super(continuation);
        this.this$0 = sessionAuthenticationProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onAuthenticate((AuthenticationContext) null, this);
    }
}
