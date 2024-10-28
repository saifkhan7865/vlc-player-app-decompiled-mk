package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationProvider;
import io.ktor.util.GenerateOnlyNonceManager;
import io.ktor.util.NonceManager;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.bouncycastle.cms.CMSAttributeTableGenerator;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001eB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000RK\u0010\u0007\u001a:\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\r\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\b¢\u0006\u0002\b\u0011X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000RU\u0010\u0016\u001aD\b\u0001\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000b\u0012\b\b\f\u0012\u0004\b\b(\u0015\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00180\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u00100\bX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0012\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lio/ktor/server/auth/DigestAuthenticationProvider;", "Lio/ktor/server/auth/AuthenticationProvider;", "config", "Lio/ktor/server/auth/DigestAuthenticationProvider$Config;", "(Lio/ktor/server/auth/DigestAuthenticationProvider$Config;)V", "algorithmName", "", "authenticationFunction", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/DigestCredential;", "Lkotlin/ParameterName;", "name", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "nonceManager", "Lio/ktor/util/NonceManager;", "realm", "userNameRealmPasswordDigestProvider", "userName", "", "onAuthenticate", "", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DigestAuth.kt */
public final class DigestAuthenticationProvider extends AuthenticationProvider {
    /* access modifiers changed from: private */
    public final String algorithmName;
    private final Function3<ApplicationCall, DigestCredential, Continuation<? super Principal>, Object> authenticationFunction;
    /* access modifiers changed from: private */
    public final NonceManager nonceManager;
    /* access modifiers changed from: private */
    public final String realm;
    /* access modifiers changed from: private */
    public final Function3<String, String, Continuation<? super byte[]>, Object> userNameRealmPasswordDigestProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DigestAuthenticationProvider(Config config) {
        super(config);
        Intrinsics.checkNotNullParameter(config, "config");
        this.realm = config.getRealm();
        this.algorithmName = config.getAlgorithmName();
        this.nonceManager = config.getNonceManager();
        this.userNameRealmPasswordDigestProvider = config.getDigestProvider$ktor_server_auth();
        this.authenticationFunction = config.getAuthenticationFunction$ktor_server_auth();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00f4  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0132  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0140  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x015a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object onAuthenticate(io.ktor.server.auth.AuthenticationContext r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r10 = this;
            boolean r0 = r12 instanceof io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$1
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$1 r0 = (io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$1 r0 = new io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$1
            r0.<init>(r10, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            if (r2 == 0) goto L_0x007c
            if (r2 == r5) goto L_0x005f
            if (r2 == r4) goto L_0x0046
            if (r2 != r3) goto L_0x003e
            java.lang.Object r11 = r0.L$2
            io.ktor.server.auth.DigestCredential r11 = (io.ktor.server.auth.DigestCredential) r11
            java.lang.Object r1 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r1 = (io.ktor.server.auth.AuthenticationContext) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.server.auth.DigestAuthenticationProvider r0 = (io.ktor.server.auth.DigestAuthenticationProvider) r0
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x012d
        L_0x003e:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L_0x0046:
            java.lang.Object r11 = r0.L$4
            io.ktor.server.auth.DigestCredential r11 = (io.ktor.server.auth.DigestCredential) r11
            java.lang.Object r2 = r0.L$3
            io.ktor.server.auth.DigestCredential r2 = (io.ktor.server.auth.DigestCredential) r2
            java.lang.Object r4 = r0.L$2
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            java.lang.Object r5 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r5 = (io.ktor.server.auth.AuthenticationContext) r5
            java.lang.Object r7 = r0.L$0
            io.ktor.server.auth.DigestAuthenticationProvider r7 = (io.ktor.server.auth.DigestAuthenticationProvider) r7
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x010d
        L_0x005f:
            java.lang.Object r11 = r0.L$5
            io.ktor.server.auth.DigestCredential r11 = (io.ktor.server.auth.DigestCredential) r11
            java.lang.Object r2 = r0.L$4
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            java.lang.Object r5 = r0.L$3
            io.ktor.server.auth.DigestCredential r5 = (io.ktor.server.auth.DigestCredential) r5
            java.lang.Object r7 = r0.L$2
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            java.lang.Object r8 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r8 = (io.ktor.server.auth.AuthenticationContext) r8
            java.lang.Object r9 = r0.L$0
            io.ktor.server.auth.DigestAuthenticationProvider r9 = (io.ktor.server.auth.DigestAuthenticationProvider) r9
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00ec
        L_0x007c:
            kotlin.ResultKt.throwOnFailure(r12)
            io.ktor.server.application.ApplicationCall r12 = r11.getCall()
            io.ktor.server.request.ApplicationRequest r2 = r12.getRequest()
            io.ktor.http.auth.HttpAuthHeader r2 = io.ktor.server.auth.HeadersKt.parseAuthorizationHeader((io.ktor.server.request.ApplicationRequest) r2)
            if (r2 == 0) goto L_0x00a4
            java.lang.String r7 = r2.getAuthScheme()
            java.lang.String r8 = "Digest"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r8)
            if (r7 == 0) goto L_0x00a4
            boolean r7 = r2 instanceof io.ktor.http.auth.HttpAuthHeader.Parameterized
            if (r7 == 0) goto L_0x00a4
            io.ktor.http.auth.HttpAuthHeader$Parameterized r2 = (io.ktor.http.auth.HttpAuthHeader.Parameterized) r2
            io.ktor.server.auth.DigestCredential r2 = io.ktor.server.auth.DigestAuthKt.toDigestCredential(r2)
            goto L_0x00a5
        L_0x00a4:
            r2 = r6
        L_0x00a5:
            io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$verify$1 r7 = new io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$verify$1
            r7.<init>(r12, r10, r6)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            if (r2 == 0) goto L_0x013c
            java.lang.String r8 = r2.getAlgorithm()
            if (r8 != 0) goto L_0x00b6
            java.lang.String r8 = "MD5"
        L_0x00b6:
            java.lang.String r9 = r10.algorithmName
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
            if (r8 == 0) goto L_0x0138
            java.lang.String r8 = r2.getRealm()
            java.lang.String r9 = r10.realm
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
            if (r8 == 0) goto L_0x0138
            io.ktor.util.NonceManager r8 = r10.nonceManager
            java.lang.String r9 = r2.getNonce()
            r0.L$0 = r10
            r0.L$1 = r11
            r0.L$2 = r12
            r0.L$3 = r2
            r0.L$4 = r7
            r0.L$5 = r2
            r0.label = r5
            java.lang.Object r5 = r8.verifyNonce(r9, r0)
            if (r5 != r1) goto L_0x00e5
            return r1
        L_0x00e5:
            r9 = r10
            r8 = r11
            r11 = r2
            r2 = r7
            r7 = r12
            r12 = r5
            r5 = r11
        L_0x00ec:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x0135
            r0.L$0 = r9
            r0.L$1 = r8
            r0.L$2 = r7
            r0.L$3 = r5
            r0.L$4 = r11
            r0.L$5 = r6
            r0.label = r4
            java.lang.Object r12 = r2.invoke(r11, r0)
            if (r12 != r1) goto L_0x0109
            return r1
        L_0x0109:
            r2 = r5
            r4 = r7
            r5 = r8
            r7 = r9
        L_0x010d:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x0132
            kotlin.jvm.functions.Function3<io.ktor.server.application.ApplicationCall, io.ktor.server.auth.DigestCredential, kotlin.coroutines.Continuation<? super io.ktor.server.auth.Principal>, java.lang.Object> r12 = r7.authenticationFunction
            r0.L$0 = r7
            r0.L$1 = r5
            r0.L$2 = r2
            r0.L$3 = r6
            r0.L$4 = r6
            r0.label = r3
            java.lang.Object r12 = r12.invoke(r4, r11, r0)
            if (r12 != r1) goto L_0x012a
            return r1
        L_0x012a:
            r11 = r2
            r1 = r5
            r0 = r7
        L_0x012d:
            io.ktor.server.auth.Principal r12 = (io.ktor.server.auth.Principal) r12
            r2 = r11
            r11 = r1
            goto L_0x013e
        L_0x0132:
            r11 = r5
            r9 = r7
            goto L_0x0139
        L_0x0135:
            r2 = r5
            r11 = r8
            goto L_0x0139
        L_0x0138:
            r9 = r10
        L_0x0139:
            r12 = r6
            r0 = r9
            goto L_0x013e
        L_0x013c:
            r0 = r10
            r12 = r6
        L_0x013e:
            if (r12 != 0) goto L_0x015a
            if (r2 != 0) goto L_0x0147
            io.ktor.server.auth.AuthenticationFailedCause$NoCredentials r12 = io.ktor.server.auth.AuthenticationFailedCause.NoCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r12 = (io.ktor.server.auth.AuthenticationFailedCause) r12
            goto L_0x014b
        L_0x0147:
            io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r12 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r12 = (io.ktor.server.auth.AuthenticationFailedCause) r12
        L_0x014b:
            java.lang.Object r1 = io.ktor.server.auth.DigestAuthKt.digestAuthenticationChallengeKey
            io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$2 r2 = new io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$2
            r2.<init>(r0, r6)
            kotlin.jvm.functions.Function3 r2 = (kotlin.jvm.functions.Function3) r2
            r11.challenge(r1, r12, r2)
            goto L_0x0161
        L_0x015a:
            java.lang.String r0 = r0.getName()
            r11.principal((java.lang.String) r0, (io.ktor.server.auth.Principal) r12)
        L_0x0161:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.DigestAuthenticationProvider.onAuthenticate(io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004JX\u0010\u0018\u001a\u00020&2H\u0010'\u001aD\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0002\u0012\u0004\b\b(\u001a\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\nø\u0001\u0000¢\u0006\u0002\u0010\u0016JN\u0010(\u001a\u00020&2>\u0010)\u001a:\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u0002\u0012\u0004\b\b(\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n¢\u0006\u0002\b\u0012ø\u0001\u0000¢\u0006\u0002\u0010\u0016R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004RW\u0010\t\u001a:\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\r\u0012\b\b\u0002\u0012\u0004\b\b(\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\n¢\u0006\u0002\b\u0012X\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016Ra\u0010\u0018\u001aD\b\u0001\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0002\u0012\u0004\b\b(\u0019\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\r\u0012\b\b\u0002\u0012\u0004\b\b(\u001a\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00110\nX\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u001c\u0010\u0014\"\u0004\b\u001d\u0010\u0016R\u001a\u0010\u001e\u001a\u00020\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u001a\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0007\"\u0004\b%\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006*"}, d2 = {"Lio/ktor/server/auth/DigestAuthenticationProvider$Config;", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "name", "", "(Ljava/lang/String;)V", "algorithmName", "getAlgorithmName", "()Ljava/lang/String;", "setAlgorithmName", "authenticationFunction", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/DigestCredential;", "Lkotlin/ParameterName;", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "getAuthenticationFunction$ktor_server_auth", "()Lkotlin/jvm/functions/Function3;", "setAuthenticationFunction$ktor_server_auth", "(Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "digestProvider", "userName", "realm", "", "getDigestProvider$ktor_server_auth", "setDigestProvider$ktor_server_auth", "nonceManager", "Lio/ktor/util/NonceManager;", "getNonceManager", "()Lio/ktor/util/NonceManager;", "setNonceManager", "(Lio/ktor/util/NonceManager;)V", "getRealm", "setRealm", "", "digest", "validate", "body", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: DigestAuth.kt */
    public static final class Config extends AuthenticationProvider.Config {
        private String algorithmName = "MD5";
        private Function3<? super ApplicationCall, ? super DigestCredential, ? super Continuation<? super Principal>, ? extends Object> authenticationFunction = new DigestAuthenticationProvider$Config$authenticationFunction$1((Continuation<? super DigestAuthenticationProvider$Config$authenticationFunction$1>) null);
        private Function3<? super String, ? super String, ? super Continuation<? super byte[]>, ? extends Object> digestProvider = new DigestAuthenticationProvider$Config$digestProvider$1(this, (Continuation<? super DigestAuthenticationProvider$Config$digestProvider$1>) null);
        private NonceManager nonceManager = GenerateOnlyNonceManager.INSTANCE;
        private String realm = "Ktor Server";

        public Config(String str) {
            super(str);
        }

        public final Function3<String, String, Continuation<? super byte[]>, Object> getDigestProvider$ktor_server_auth() {
            return this.digestProvider;
        }

        public final void setDigestProvider$ktor_server_auth(Function3<? super String, ? super String, ? super Continuation<? super byte[]>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.digestProvider = function3;
        }

        public final Function3<ApplicationCall, DigestCredential, Continuation<? super Principal>, Object> getAuthenticationFunction$ktor_server_auth() {
            return this.authenticationFunction;
        }

        public final void setAuthenticationFunction$ktor_server_auth(Function3<? super ApplicationCall, ? super DigestCredential, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.authenticationFunction = function3;
        }

        public final String getRealm() {
            return this.realm;
        }

        public final void setRealm(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.realm = str;
        }

        public final String getAlgorithmName() {
            return this.algorithmName;
        }

        public final void setAlgorithmName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.algorithmName = str;
        }

        public final NonceManager getNonceManager() {
            return this.nonceManager;
        }

        public final void setNonceManager(NonceManager nonceManager2) {
            Intrinsics.checkNotNullParameter(nonceManager2, "<set-?>");
            this.nonceManager = nonceManager2;
        }

        public final void validate(Function3<? super ApplicationCall, ? super DigestCredential, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "body");
            this.authenticationFunction = function3;
        }

        public final void digestProvider(Function3<? super String, ? super String, ? super Continuation<? super byte[]>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, CMSAttributeTableGenerator.DIGEST);
            this.digestProvider = function3;
        }
    }
}
