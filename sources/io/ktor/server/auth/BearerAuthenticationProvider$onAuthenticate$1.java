package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.BearerAuthenticationProvider", f = "BearerAuth.kt", i = {0, 0}, l = {36}, m = "onAuthenticate", n = {"this", "context"}, s = {"L$0", "L$1"})
/* compiled from: BearerAuth.kt */
final class BearerAuthenticationProvider$onAuthenticate$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ BearerAuthenticationProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BearerAuthenticationProvider$onAuthenticate$1(BearerAuthenticationProvider bearerAuthenticationProvider, Continuation<? super BearerAuthenticationProvider$onAuthenticate$1> continuation) {
        super(continuation);
        this.this$0 = bearerAuthenticationProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.onAuthenticate((AuthenticationContext) null, this);
    }
}
