package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H@"}, d2 = {"<anonymous>", "", "T", "", "challenge", "Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$2", f = "SessionAuth.kt", i = {0, 0}, l = {46}, m = "invokeSuspend", n = {"challenge", "call"}, s = {"L$0", "L$1"})
/* compiled from: SessionAuth.kt */
final class SessionAuthenticationProvider$onAuthenticate$2 extends SuspendLambda implements Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object> {
    final /* synthetic */ Principal $principal;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ SessionAuthenticationProvider<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionAuthenticationProvider$onAuthenticate$2(SessionAuthenticationProvider<T> sessionAuthenticationProvider, Principal principal, Continuation<? super SessionAuthenticationProvider$onAuthenticate$2> continuation) {
        super(3, continuation);
        this.this$0 = sessionAuthenticationProvider;
        this.$principal = principal;
    }

    public final Object invoke(AuthenticationProcedureChallenge authenticationProcedureChallenge, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        SessionAuthenticationProvider$onAuthenticate$2 sessionAuthenticationProvider$onAuthenticate$2 = new SessionAuthenticationProvider$onAuthenticate$2(this.this$0, this.$principal, continuation);
        sessionAuthenticationProvider$onAuthenticate$2.L$0 = authenticationProcedureChallenge;
        sessionAuthenticationProvider$onAuthenticate$2.L$1 = applicationCall;
        return sessionAuthenticationProvider$onAuthenticate$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: io.ktor.server.auth.AuthenticationProcedureChallenge} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            java.lang.Object r0 = r6.L$1
            io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
            java.lang.Object r1 = r6.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r1 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0046
        L_0x0017:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x001f:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            r1 = r7
            io.ktor.server.auth.AuthenticationProcedureChallenge r1 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r1
            java.lang.Object r7 = r6.L$1
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            io.ktor.server.auth.SessionAuthenticationProvider<T> r3 = r6.this$0
            kotlin.jvm.functions.Function3 r3 = r3.challengeFunction
            io.ktor.server.auth.SessionChallengeContext r4 = new io.ktor.server.auth.SessionChallengeContext
            r4.<init>(r7)
            io.ktor.server.auth.Principal r5 = r6.$principal
            r6.L$0 = r1
            r6.L$1 = r7
            r6.label = r2
            java.lang.Object r2 = r3.invoke(r4, r5, r6)
            if (r2 != r0) goto L_0x0045
            return r0
        L_0x0045:
            r0 = r7
        L_0x0046:
            boolean r7 = r1.getCompleted()
            if (r7 != 0) goto L_0x0059
            io.ktor.server.response.ApplicationResponse r7 = r0.getResponse()
            io.ktor.http.HttpStatusCode r7 = r7.status()
            if (r7 == 0) goto L_0x0059
            r1.complete()
        L_0x0059:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
