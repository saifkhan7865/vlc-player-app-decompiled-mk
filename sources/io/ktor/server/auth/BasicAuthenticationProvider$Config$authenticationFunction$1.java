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

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/ApplicationCall;", "it", "Lio/ktor/server/auth/UserPasswordCredential;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.BasicAuthenticationProvider$Config$authenticationFunction$1", f = "BasicAuth.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BasicAuth.kt */
final class BasicAuthenticationProvider$Config$authenticationFunction$1 extends SuspendLambda implements Function3<ApplicationCall, UserPasswordCredential, Continuation<?>, Object> {
    int label;

    BasicAuthenticationProvider$Config$authenticationFunction$1(Continuation<? super BasicAuthenticationProvider$Config$authenticationFunction$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(ApplicationCall applicationCall, UserPasswordCredential userPasswordCredential, Continuation<?> continuation) {
        return new BasicAuthenticationProvider$Config$authenticationFunction$1(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        throw new NotImplementedError("Basic auth validate function is not specified. Use basic { validate { ... } } to fix.");
    }
}
