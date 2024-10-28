package io.ktor.server.auth;

import io.ktor.server.response.ApplicationResponseFunctionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0002H@"}, d2 = {"<anonymous>", "", "T", "", "Lio/ktor/server/auth/SessionChallengeContext;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.SessionAuthenticationProvider$Config$challenge$1", f = "SessionAuth.kt", i = {}, l = {77}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SessionAuth.kt */
final class SessionAuthenticationProvider$Config$challenge$1 extends SuspendLambda implements Function3<SessionChallengeContext, T, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $redirectUrl;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SessionAuthenticationProvider$Config$challenge$1(String str, Continuation<? super SessionAuthenticationProvider$Config$challenge$1> continuation) {
        super(3, continuation);
        this.$redirectUrl = str;
    }

    public final Object invoke(SessionChallengeContext sessionChallengeContext, T t, Continuation<? super Unit> continuation) {
        SessionAuthenticationProvider$Config$challenge$1 sessionAuthenticationProvider$Config$challenge$1 = new SessionAuthenticationProvider$Config$challenge$1(this.$redirectUrl, continuation);
        sessionAuthenticationProvider$Config$challenge$1.L$0 = sessionChallengeContext;
        return sessionAuthenticationProvider$Config$challenge$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (ApplicationResponseFunctionsKt.respondRedirect$default(((SessionChallengeContext) this.L$0).getCall(), this.$redirectUrl, false, (Continuation) this, 2, (Object) null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
