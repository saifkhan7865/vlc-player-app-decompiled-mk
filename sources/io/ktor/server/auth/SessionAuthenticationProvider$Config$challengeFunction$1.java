package io.ktor.server.auth;

import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ResponseTypeKt;
import io.ktor.util.reflect.TypeInfoJvmKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KType;
import kotlin.reflect.TypesJVMKt;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u0001H\u0002H@"}, d2 = {"<anonymous>", "", "T", "", "Lio/ktor/server/auth/SessionChallengeContext;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.SessionAuthenticationProvider$Config$challengeFunction$1", f = "SessionAuth.kt", i = {}, l = {167}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SessionAuth.kt */
final class SessionAuthenticationProvider$Config$challengeFunction$1 extends SuspendLambda implements Function3<SessionChallengeContext, T, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;

    SessionAuthenticationProvider$Config$challengeFunction$1(Continuation<? super SessionAuthenticationProvider$Config$challengeFunction$1> continuation) {
        super(3, continuation);
    }

    public final Object invoke(SessionChallengeContext sessionChallengeContext, T t, Continuation<? super Unit> continuation) {
        SessionAuthenticationProvider$Config$challengeFunction$1 sessionAuthenticationProvider$Config$challengeFunction$1 = new SessionAuthenticationProvider$Config$challengeFunction$1(continuation);
        sessionAuthenticationProvider$Config$challengeFunction$1.L$0 = sessionChallengeContext;
        return sessionAuthenticationProvider$Config$challengeFunction$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ApplicationCall call = ((SessionChallengeContext) this.L$0).getCall();
            UnauthorizedResponse unauthorizedResponse = new UnauthorizedResponse(new HttpAuthHeader[0]);
            if (!(unauthorizedResponse instanceof OutgoingContent) && !(unauthorizedResponse instanceof byte[])) {
                ApplicationResponse response = call.getResponse();
                KType typeOf = Reflection.typeOf(UnauthorizedResponse.class);
                ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(UnauthorizedResponse.class), typeOf));
            }
            this.label = 1;
            if (call.getResponse().getPipeline().execute(call, unauthorizedResponse, this) == coroutine_suspended) {
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
