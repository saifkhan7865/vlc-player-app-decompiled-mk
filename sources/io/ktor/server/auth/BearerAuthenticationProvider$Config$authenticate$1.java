package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/ApplicationCall;", "it", "Lio/ktor/server/auth/BearerTokenCredential;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.BearerAuthenticationProvider$Config$authenticate$1", f = "BearerAuth.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BearerAuth.kt */
final class BearerAuthenticationProvider$Config$authenticate$1 extends SuspendLambda implements Function3<ApplicationCall, BearerTokenCredential, Continuation<?>, Object> {
    int label;

    BearerAuthenticationProvider$Config$authenticate$1(Continuation<? super BearerAuthenticationProvider$Config$authenticate$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(ApplicationCall applicationCall, BearerTokenCredential bearerTokenCredential, Continuation<?> continuation) {
        return new BearerAuthenticationProvider$Config$authenticate$1(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        throw new NotImplementedError("Bearer auth authenticate function is not specified. Use bearer { authenticate { ... } } to fix.");
    }
}
