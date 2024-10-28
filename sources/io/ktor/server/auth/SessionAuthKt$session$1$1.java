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
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H@"}, d2 = {"<anonymous>", "Lio/ktor/server/auth/Principal;", "T", "Lio/ktor/server/application/ApplicationCall;", "session"}, k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "io.ktor.server.auth.SessionAuthKt$session$1$1", f = "SessionAuth.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SessionAuth.kt */
public final class SessionAuthKt$session$1$1 extends SuspendLambda implements Function3<ApplicationCall, T, Continuation<? super Principal>, Object> {
    /* synthetic */ Object L$0;
    int label;

    public SessionAuthKt$session$1$1(Continuation<? super SessionAuthKt$session$1$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(ApplicationCall applicationCall, T t, Continuation<? super Principal> continuation) {
        Intrinsics.needClassReification();
        SessionAuthKt$session$1$1 sessionAuthKt$session$1$1 = new SessionAuthKt$session$1$1(continuation);
        sessionAuthKt$session$1$1.L$0 = t;
        return sessionAuthKt$session$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return (Principal) this.L$0;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
