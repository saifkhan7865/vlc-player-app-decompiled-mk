package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.AuthenticationInterceptorsKt", f = "AuthenticationInterceptors.kt", i = {0, 0, 1, 1, 2}, l = {129, 131, 291}, m = "executeChallenges", n = {"$this$executeChallenges", "call", "$this$executeChallenges", "call", "$this$executeChallenges"}, s = {"L$0", "L$1", "L$0", "L$1", "L$0"})
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationInterceptorsKt$executeChallenges$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    AuthenticationInterceptorsKt$executeChallenges$1(Continuation<? super AuthenticationInterceptorsKt$executeChallenges$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AuthenticationInterceptorsKt.executeChallenges((AuthenticationContext) null, (ApplicationCall) null, this);
    }
}
