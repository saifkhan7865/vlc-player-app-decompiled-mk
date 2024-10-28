package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "challenge", "Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$2", f = "DigestAuth.kt", i = {0, 1}, l = {78, 275}, m = "invokeSuspend", n = {"challenge", "challenge"}, s = {"L$0", "L$0"})
/* compiled from: DigestAuth.kt */
final class DigestAuthenticationProvider$onAuthenticate$2 extends SuspendLambda implements Function3<AuthenticationProcedureChallenge, ApplicationCall, Continuation<? super Unit>, Object> {
    int I$0;
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    int label;
    final /* synthetic */ DigestAuthenticationProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DigestAuthenticationProvider$onAuthenticate$2(DigestAuthenticationProvider digestAuthenticationProvider, Continuation<? super DigestAuthenticationProvider$onAuthenticate$2> continuation) {
        super(3, continuation);
        this.this$0 = digestAuthenticationProvider;
    }

    public final Object invoke(AuthenticationProcedureChallenge authenticationProcedureChallenge, ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
        DigestAuthenticationProvider$onAuthenticate$2 digestAuthenticationProvider$onAuthenticate$2 = new DigestAuthenticationProvider$onAuthenticate$2(this.this$0, continuation);
        digestAuthenticationProvider$onAuthenticate$2.L$0 = authenticationProcedureChallenge;
        digestAuthenticationProvider$onAuthenticate$2.L$1 = applicationCall;
        return digestAuthenticationProvider$onAuthenticate$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v1, resolved type: io.ktor.server.application.ApplicationCall} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r23) {
        /*
            r22 = this;
            r0 = r22
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x004c
            if (r2 == r4) goto L_0x0021
            if (r2 != r3) goto L_0x0019
            java.lang.Object r1 = r0.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r1 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r1
            kotlin.ResultKt.throwOnFailure(r23)
            goto L_0x00f1
        L_0x0019:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0021:
            int r2 = r0.I$0
            java.lang.Object r4 = r0.L$6
            io.ktor.http.auth.HttpAuthHeader[] r4 = (io.ktor.http.auth.HttpAuthHeader[]) r4
            java.lang.Object r5 = r0.L$5
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r6 = r0.L$4
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r0.L$3
            io.ktor.http.auth.HttpAuthHeader$Companion r7 = (io.ktor.http.auth.HttpAuthHeader.Companion) r7
            java.lang.Object r8 = r0.L$2
            io.ktor.http.auth.HttpAuthHeader[] r8 = (io.ktor.http.auth.HttpAuthHeader[]) r8
            java.lang.Object r9 = r0.L$1
            io.ktor.server.application.ApplicationCall r9 = (io.ktor.server.application.ApplicationCall) r9
            java.lang.Object r10 = r0.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r10 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r10
            kotlin.ResultKt.throwOnFailure(r23)
            r12 = r2
            r19 = r5
            r14 = r6
            r13 = r7
            r2 = r10
            r5 = r4
            r4 = r23
            goto L_0x0090
        L_0x004c:
            kotlin.ResultKt.throwOnFailure(r23)
            java.lang.Object r2 = r0.L$0
            io.ktor.server.auth.AuthenticationProcedureChallenge r2 = (io.ktor.server.auth.AuthenticationProcedureChallenge) r2
            java.lang.Object r5 = r0.L$1
            r9 = r5
            io.ktor.server.application.ApplicationCall r9 = (io.ktor.server.application.ApplicationCall) r9
            io.ktor.http.auth.HttpAuthHeader[] r5 = new io.ktor.http.auth.HttpAuthHeader[r4]
            io.ktor.http.auth.HttpAuthHeader$Companion r7 = io.ktor.http.auth.HttpAuthHeader.Companion
            io.ktor.server.auth.DigestAuthenticationProvider r6 = r0.this$0
            java.lang.String r6 = r6.realm
            io.ktor.server.auth.DigestAuthenticationProvider r8 = r0.this$0
            java.lang.String r8 = r8.algorithmName
            io.ktor.server.auth.DigestAuthenticationProvider r10 = r0.this$0
            io.ktor.util.NonceManager r10 = r10.nonceManager
            r11 = r0
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r0.L$0 = r2
            r0.L$1 = r9
            r0.L$2 = r5
            r0.L$3 = r7
            r0.L$4 = r6
            r0.L$5 = r8
            r0.L$6 = r5
            r12 = 0
            r0.I$0 = r12
            r0.label = r4
            java.lang.Object r4 = r10.newNonce(r11)
            if (r4 != r1) goto L_0x008b
            return r1
        L_0x008b:
            r14 = r6
            r13 = r7
            r19 = r8
            r8 = r5
        L_0x0090:
            r15 = r4
            java.lang.String r15 = (java.lang.String) r15
            r20 = 28
            r21 = 0
            r16 = 0
            r17 = 0
            r18 = 0
            io.ktor.http.auth.HttpAuthHeader$Parameterized r4 = io.ktor.http.auth.HttpAuthHeader.Companion.digestAuthChallenge$default(r13, r14, r15, r16, r17, r18, r19, r20, r21)
            r5[r12] = r4
            io.ktor.server.auth.UnauthorizedResponse r4 = new io.ktor.server.auth.UnauthorizedResponse
            r4.<init>(r8)
            boolean r5 = r4 instanceof io.ktor.http.content.OutgoingContent
            if (r5 != 0) goto L_0x00cb
            boolean r5 = r4 instanceof byte[]
            if (r5 != 0) goto L_0x00cb
            io.ktor.server.response.ApplicationResponse r5 = r9.getResponse()
            java.lang.Class<io.ktor.server.auth.UnauthorizedResponse> r6 = io.ktor.server.auth.UnauthorizedResponse.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r7 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.server.auth.UnauthorizedResponse> r8 = io.ktor.server.auth.UnauthorizedResponse.class
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r7, r8, r6)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r5, r6)
        L_0x00cb:
            io.ktor.server.response.ApplicationResponse r5 = r9.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r5 = r5.getPipeline()
            java.lang.Object r4 = (java.lang.Object) r4
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r0.L$0 = r2
            r7 = 0
            r0.L$1 = r7
            r0.L$2 = r7
            r0.L$3 = r7
            r0.L$4 = r7
            r0.L$5 = r7
            r0.L$6 = r7
            r0.label = r3
            java.lang.Object r3 = r5.execute(r9, r4, r6)
            if (r3 != r1) goto L_0x00f0
            return r1
        L_0x00f0:
            r1 = r2
        L_0x00f1:
            r1.complete()
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
