package io.ktor.server.auth;

import io.ktor.http.Url;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001bB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0019H@ø\u0001\u0000¢\u0006\u0002\u0010\u001aRK\u0010\u0005\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006¢\u0006\u0002\b\u000fX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R<\u0010\u0011\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0012\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006¢\u0006\u0002\b\u000fX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0015X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001c"}, d2 = {"Lio/ktor/server/auth/FormAuthenticationProvider;", "Lio/ktor/server/auth/AuthenticationProvider;", "config", "Lio/ktor/server/auth/FormAuthenticationProvider$Config;", "(Lio/ktor/server/auth/FormAuthenticationProvider$Config;)V", "authenticationFunction", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/UserPasswordCredential;", "Lkotlin/ParameterName;", "name", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "challengeFunction", "Lio/ktor/server/auth/FormAuthChallengeContext;", "", "passwordParamName", "", "userParamName", "onAuthenticate", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FormAuth.kt */
public final class FormAuthenticationProvider extends AuthenticationProvider {
    private final Function3<ApplicationCall, UserPasswordCredential, Continuation<? super Principal>, Object> authenticationFunction;
    /* access modifiers changed from: private */
    public final Function3<FormAuthChallengeContext, UserPasswordCredential, Continuation<? super Unit>, Object> challengeFunction;
    private final String passwordParamName;
    private final String userParamName;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FormAuthenticationProvider(Config config) {
        super(config);
        Intrinsics.checkNotNullParameter(config, "config");
        this.userParamName = config.getUserParamName();
        this.passwordParamName = config.getPasswordParamName();
        this.challengeFunction = config.getChallengeFunction$ktor_server_auth();
        this.authenticationFunction = config.getAuthenticationFunction$ktor_server_auth();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00be  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00c1 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00e6  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00e9  */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object onAuthenticate(io.ktor.server.auth.AuthenticationContext r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof io.ktor.server.auth.FormAuthenticationProvider$onAuthenticate$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.server.auth.FormAuthenticationProvider$onAuthenticate$1 r0 = (io.ktor.server.auth.FormAuthenticationProvider$onAuthenticate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.FormAuthenticationProvider$onAuthenticate$1 r0 = new io.ktor.server.auth.FormAuthenticationProvider$onAuthenticate$1
            r0.<init>(r9, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x005d
            if (r2 == r4) goto L_0x0043
            if (r2 != r3) goto L_0x003b
            java.lang.Object r10 = r0.L$2
            io.ktor.server.auth.UserPasswordCredential r10 = (io.ktor.server.auth.UserPasswordCredential) r10
            java.lang.Object r1 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r1 = (io.ktor.server.auth.AuthenticationContext) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.server.auth.FormAuthenticationProvider r0 = (io.ktor.server.auth.FormAuthenticationProvider) r0
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00e0
        L_0x003b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x0043:
            java.lang.Object r10 = r0.L$2
            io.ktor.server.application.ApplicationCall r10 = (io.ktor.server.application.ApplicationCall) r10
            java.lang.Object r2 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r2 = (io.ktor.server.auth.AuthenticationContext) r2
            java.lang.Object r4 = r0.L$0
            io.ktor.server.auth.FormAuthenticationProvider r4 = (io.ktor.server.auth.FormAuthenticationProvider) r4
            kotlin.ResultKt.throwOnFailure(r11)     // Catch:{ all -> 0x0057 }
            r8 = r11
            r11 = r10
            r10 = r2
            r2 = r8
            goto L_0x008d
        L_0x0057:
            r11 = move-exception
            r8 = r11
            r11 = r10
            r10 = r2
            r2 = r8
            goto L_0x0098
        L_0x005d:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.server.application.ApplicationCall r11 = r10.getCall()
            kotlin.Result$Companion r2 = kotlin.Result.Companion     // Catch:{ all -> 0x0096 }
            r2 = r9
            io.ktor.server.auth.FormAuthenticationProvider r2 = (io.ktor.server.auth.FormAuthenticationProvider) r2     // Catch:{ all -> 0x0096 }
            java.lang.Class<io.ktor.http.Parameters> r2 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r2 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r2)     // Catch:{ all -> 0x0096 }
            java.lang.reflect.Type r6 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r2)     // Catch:{ all -> 0x0096 }
            java.lang.Class<io.ktor.http.Parameters> r7 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r7 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r7)     // Catch:{ all -> 0x0096 }
            io.ktor.util.reflect.TypeInfo r2 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r6, r7, r2)     // Catch:{ all -> 0x0096 }
            r0.L$0 = r9     // Catch:{ all -> 0x0096 }
            r0.L$1 = r10     // Catch:{ all -> 0x0096 }
            r0.L$2 = r11     // Catch:{ all -> 0x0096 }
            r0.label = r4     // Catch:{ all -> 0x0096 }
            java.lang.Object r2 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r11, r2, r0)     // Catch:{ all -> 0x0096 }
            if (r2 != r1) goto L_0x008c
            return r1
        L_0x008c:
            r4 = r9
        L_0x008d:
            io.ktor.http.Parameters r2 = (io.ktor.http.Parameters) r2     // Catch:{ all -> 0x0094 }
            java.lang.Object r2 = kotlin.Result.m1774constructorimpl(r2)     // Catch:{ all -> 0x0094 }
            goto L_0x00a2
        L_0x0094:
            r2 = move-exception
            goto L_0x0098
        L_0x0096:
            r2 = move-exception
            r4 = r9
        L_0x0098:
            kotlin.Result$Companion r6 = kotlin.Result.Companion
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r2)
            java.lang.Object r2 = kotlin.Result.m1774constructorimpl(r2)
        L_0x00a2:
            boolean r6 = kotlin.Result.m1780isFailureimpl(r2)
            if (r6 == 0) goto L_0x00a9
            r2 = r5
        L_0x00a9:
            io.ktor.http.Parameters r2 = (io.ktor.http.Parameters) r2
            if (r2 == 0) goto L_0x00b4
            java.lang.String r6 = r4.userParamName
            java.lang.String r6 = r2.get(r6)
            goto L_0x00b5
        L_0x00b4:
            r6 = r5
        L_0x00b5:
            if (r2 == 0) goto L_0x00be
            java.lang.String r7 = r4.passwordParamName
            java.lang.String r2 = r2.get(r7)
            goto L_0x00bf
        L_0x00be:
            r2 = r5
        L_0x00bf:
            if (r6 == 0) goto L_0x00c9
            if (r2 == 0) goto L_0x00c9
            io.ktor.server.auth.UserPasswordCredential r7 = new io.ktor.server.auth.UserPasswordCredential
            r7.<init>(r6, r2)
            goto L_0x00ca
        L_0x00c9:
            r7 = r5
        L_0x00ca:
            if (r7 == 0) goto L_0x00e6
            kotlin.jvm.functions.Function3<io.ktor.server.application.ApplicationCall, io.ktor.server.auth.UserPasswordCredential, kotlin.coroutines.Continuation<? super io.ktor.server.auth.Principal>, java.lang.Object> r2 = r4.authenticationFunction
            r0.L$0 = r4
            r0.L$1 = r10
            r0.L$2 = r7
            r0.label = r3
            java.lang.Object r11 = r2.invoke(r11, r7, r0)
            if (r11 != r1) goto L_0x00dd
            return r1
        L_0x00dd:
            r1 = r10
            r0 = r4
            r10 = r7
        L_0x00e0:
            io.ktor.server.auth.Principal r11 = (io.ktor.server.auth.Principal) r11
            r7 = r10
            r4 = r0
            r10 = r1
            goto L_0x00e7
        L_0x00e6:
            r11 = r5
        L_0x00e7:
            if (r11 == 0) goto L_0x00f3
            java.lang.String r0 = r4.getName()
            r10.principal((java.lang.String) r0, (io.ktor.server.auth.Principal) r11)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        L_0x00f3:
            if (r7 != 0) goto L_0x00fa
            io.ktor.server.auth.AuthenticationFailedCause$NoCredentials r11 = io.ktor.server.auth.AuthenticationFailedCause.NoCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r11 = (io.ktor.server.auth.AuthenticationFailedCause) r11
            goto L_0x00fe
        L_0x00fa:
            io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r11 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r11 = (io.ktor.server.auth.AuthenticationFailedCause) r11
        L_0x00fe:
            java.lang.Object r0 = io.ktor.server.auth.FormAuthKt.formAuthenticationChallengeKey
            io.ktor.server.auth.FormAuthenticationProvider$onAuthenticate$2 r1 = new io.ktor.server.auth.FormAuthenticationProvider$onAuthenticate$2
            r1.<init>(r4, r7, r5)
            kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1
            r10.challenge(r0, r11, r1)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.FormAuthenticationProvider.onAuthenticate(io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\r\u0010 \u001a\u00020!H\u0000¢\u0006\u0002\b\"J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020%J?\u0010#\u001a\u00020\u00162/\u0010&\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u000e\u0010#\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0003J?\u0010(\u001a\u00020\u00162/\u0010)\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u0012RW\u0010\u0005\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\u0002\u0012\u0004\b\b(\n\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\u0002\b\u000eX\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012RH\u0010\u0014\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0015\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\u0002\b\u000eX\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u0017\u0010\u0010\"\u0004\b\u0018\u0010\u0012R\u001a\u0010\u0019\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u0004R\u001a\u0010\u001d\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lio/ktor/server/auth/FormAuthenticationProvider$Config;", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "name", "", "(Ljava/lang/String;)V", "authenticationFunction", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/UserPasswordCredential;", "Lkotlin/ParameterName;", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "getAuthenticationFunction$ktor_server_auth", "()Lkotlin/jvm/functions/Function3;", "setAuthenticationFunction$ktor_server_auth", "(Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "challengeFunction", "Lio/ktor/server/auth/FormAuthChallengeContext;", "", "getChallengeFunction$ktor_server_auth", "setChallengeFunction$ktor_server_auth", "passwordParamName", "getPasswordParamName", "()Ljava/lang/String;", "setPasswordParamName", "userParamName", "getUserParamName", "setUserParamName", "build", "Lio/ktor/server/auth/FormAuthenticationProvider;", "build$ktor_server_auth", "challenge", "redirect", "Lio/ktor/http/Url;", "function", "redirectUrl", "validate", "body", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: FormAuth.kt */
    public static final class Config extends AuthenticationProvider.Config {
        private Function3<? super ApplicationCall, ? super UserPasswordCredential, ? super Continuation<? super Principal>, ? extends Object> authenticationFunction = new FormAuthenticationProvider$Config$authenticationFunction$1((Continuation<? super FormAuthenticationProvider$Config$authenticationFunction$1>) null);
        private Function3<? super FormAuthChallengeContext, ? super UserPasswordCredential, ? super Continuation<? super Unit>, ? extends Object> challengeFunction = new FormAuthenticationProvider$Config$challengeFunction$1((Continuation<? super FormAuthenticationProvider$Config$challengeFunction$1>) null);
        private String passwordParamName = "password";
        private String userParamName = "user";

        public Config(String str) {
            super(str);
        }

        public final Function3<ApplicationCall, UserPasswordCredential, Continuation<? super Principal>, Object> getAuthenticationFunction$ktor_server_auth() {
            return this.authenticationFunction;
        }

        public final void setAuthenticationFunction$ktor_server_auth(Function3<? super ApplicationCall, ? super UserPasswordCredential, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.authenticationFunction = function3;
        }

        public final Function3<FormAuthChallengeContext, UserPasswordCredential, Continuation<? super Unit>, Object> getChallengeFunction$ktor_server_auth() {
            return this.challengeFunction;
        }

        public final void setChallengeFunction$ktor_server_auth(Function3<? super FormAuthChallengeContext, ? super UserPasswordCredential, ? super Continuation<? super Unit>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.challengeFunction = function3;
        }

        public final String getUserParamName() {
            return this.userParamName;
        }

        public final void setUserParamName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.userParamName = str;
        }

        public final String getPasswordParamName() {
            return this.passwordParamName;
        }

        public final void setPasswordParamName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.passwordParamName = str;
        }

        public final void challenge(Function3<? super FormAuthChallengeContext, ? super UserPasswordCredential, ? super Continuation<? super Unit>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "function");
            this.challengeFunction = function3;
        }

        public final void challenge(String str) {
            Intrinsics.checkNotNullParameter(str, "redirectUrl");
            challenge((Function3<? super FormAuthChallengeContext, ? super UserPasswordCredential, ? super Continuation<? super Unit>, ? extends Object>) new FormAuthenticationProvider$Config$challenge$1(str, (Continuation<? super FormAuthenticationProvider$Config$challenge$1>) null));
        }

        public final void challenge(Url url) {
            Intrinsics.checkNotNullParameter(url, "redirect");
            challenge(url.toString());
        }

        public final void validate(Function3<? super ApplicationCall, ? super UserPasswordCredential, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "body");
            this.authenticationFunction = function3;
        }

        public final FormAuthenticationProvider build$ktor_server_auth() {
            return new FormAuthenticationProvider(this);
        }
    }
}
