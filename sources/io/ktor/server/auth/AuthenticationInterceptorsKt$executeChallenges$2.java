package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.AuthenticationInterceptorsKt", f = "AuthenticationInterceptors.kt", i = {0, 0}, l = {150, 291}, m = "executeChallenges", n = {"$this$executeChallenges", "call"}, s = {"L$0", "L$1"})
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationInterceptorsKt$executeChallenges$2 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    AuthenticationInterceptorsKt$executeChallenges$2(Continuation<? super AuthenticationInterceptorsKt$executeChallenges$2> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return AuthenticationInterceptorsKt.executeChallenges((AuthenticationContext) null, (List<? extends Function3<? super AuthenticationProcedureChallenge, ? super ApplicationCall, ? super Continuation<? super Unit>, ? extends Object>>) null, (ApplicationCall) null, this);
    }
}
