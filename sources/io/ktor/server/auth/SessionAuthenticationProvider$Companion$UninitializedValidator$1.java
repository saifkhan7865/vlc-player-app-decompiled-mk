package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.SessionAuthenticationProvider$Companion$UninitializedValidator$1", f = "SessionAuth.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SessionAuth.kt */
final class SessionAuthenticationProvider$Companion$UninitializedValidator$1 extends SuspendLambda implements Function3<ApplicationCall, Object, Continuation<?>, Object> {
    int label;

    SessionAuthenticationProvider$Companion$UninitializedValidator$1(Continuation<? super SessionAuthenticationProvider$Companion$UninitializedValidator$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(ApplicationCall applicationCall, Object obj, Continuation<?> continuation) {
        return new SessionAuthenticationProvider$Companion$UninitializedValidator$1(continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        throw new IllegalStateException("It should be a validator supplied to a session auth provider".toString());
    }
}
