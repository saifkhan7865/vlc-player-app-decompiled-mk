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

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "challenge", "Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$authHeader$1$1", f = "BearerAuth.kt", i = {0}, l = {119}, m = "invokeSuspend", n = {"challenge"}, s = {"L$0"})
/* compiled from: BearerAuth.kt */
final class BearerAuthenticationProvider$onAuthenticate$authHeader$1$1 extends SuspendLambda implements Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ BearerAuthenticationProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BearerAuthenticationProvider$onAuthenticate$authHeader$1$1(BearerAuthenticationProvider bearerAuthenticationProvider, Continuation<? super BearerAuthenticationProvider$onAuthenticate$authHeader$1$1> continuation) {
        super(3, continuation);
        this.this$0 = bearerAuthenticationProvider;
    }

    public final Object invoke(AuthenticationProcedureChallenge authenticationProcedureChallenge, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        BearerAuthenticationProvider$onAuthenticate$authHeader$1$1 bearerAuthenticationProvider$onAuthenticate$authHeader$1$1 = new BearerAuthenticationProvider$onAuthenticate$authHeader$1$1(this.this$0, continuation);
        bearerAuthenticationProvider$onAuthenticate$authHeader$1$1.L$0 = authenticationProcedureChallenge;
        bearerAuthenticationProvider$onAuthenticate$authHeader$1$1.L$1 = applicationCall;
        return bearerAuthenticationProvider$onAuthenticate$authHeader$1$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        AuthenticationProcedureChallenge authenticationProcedureChallenge;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AuthenticationProcedureChallenge authenticationProcedureChallenge2 = (AuthenticationProcedureChallenge) this.L$0;
            ApplicationCall applicationCall = (ApplicationCall) this.L$1;
            UnauthorizedResponse unauthorizedResponse = new UnauthorizedResponse(HttpAuthHeader.Companion.bearerAuthChallenge(this.this$0.defaultScheme, this.this$0.realm));
            if (!(unauthorizedResponse instanceof OutgoingContent) && !(unauthorizedResponse instanceof byte[])) {
                ApplicationResponse response = applicationCall.getResponse();
                KType typeOf = Reflection.typeOf(UnauthorizedResponse.class);
                ResponseTypeKt.setResponseType(response, TypeInfoJvmKt.typeInfoImpl(TypesJVMKt.getJavaType(typeOf), Reflection.getOrCreateKotlinClass(UnauthorizedResponse.class), typeOf));
            }
            this.L$0 = authenticationProcedureChallenge2;
            this.label = 1;
            if (applicationCall.getResponse().getPipeline().execute(applicationCall, unauthorizedResponse, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            authenticationProcedureChallenge = authenticationProcedureChallenge2;
        } else if (i == 1) {
            authenticationProcedureChallenge = (AuthenticationProcedureChallenge) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        authenticationProcedureChallenge.complete();
        return Unit.INSTANCE;
    }
}
