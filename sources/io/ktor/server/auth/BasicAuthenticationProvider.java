package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationProvider;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001!B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH@ø\u0001\u0000¢\u0006\u0002\u0010 RQ\u0010\u0005\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006¢\u0006\u0002\b\u000fX\u0004ø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0013\u001a\n\u0018\u00010\u0014j\u0004\u0018\u0001`\u0015X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b\u0002\u0004\n\u0002\b\u0019¨\u0006\""}, d2 = {"Lio/ktor/server/auth/BasicAuthenticationProvider;", "Lio/ktor/server/auth/AuthenticationProvider;", "config", "Lio/ktor/server/auth/BasicAuthenticationProvider$Config;", "(Lio/ktor/server/auth/BasicAuthenticationProvider$Config;)V", "authenticationFunction", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/UserPasswordCredential;", "Lkotlin/ParameterName;", "name", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "getAuthenticationFunction$ktor_server_auth", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "charset", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "getCharset$ktor_server_auth", "()Ljava/nio/charset/Charset;", "realm", "", "getRealm$ktor_server_auth", "()Ljava/lang/String;", "onAuthenticate", "", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BasicAuth.kt */
public final class BasicAuthenticationProvider extends AuthenticationProvider {
    private final Function3<ApplicationCall, UserPasswordCredential, Continuation<? super Principal>, Object> authenticationFunction;
    private final Charset charset;
    private final String realm;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BasicAuthenticationProvider(Config config) {
        super(config);
        Intrinsics.checkNotNullParameter(config, "config");
        this.realm = config.getRealm();
        this.charset = config.getCharset();
        this.authenticationFunction = config.getAuthenticationFunction$ktor_server_auth();
    }

    public final String getRealm$ktor_server_auth() {
        return this.realm;
    }

    public final Charset getCharset$ktor_server_auth() {
        return this.charset;
    }

    public final Function3<ApplicationCall, UserPasswordCredential, Continuation<? super Principal>, Object> getAuthenticationFunction$ktor_server_auth() {
        return this.authenticationFunction;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object onAuthenticate(io.ktor.server.auth.AuthenticationContext r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.server.auth.BasicAuthenticationProvider$onAuthenticate$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.auth.BasicAuthenticationProvider$onAuthenticate$1 r0 = (io.ktor.server.auth.BasicAuthenticationProvider$onAuthenticate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.BasicAuthenticationProvider$onAuthenticate$1 r0 = new io.ktor.server.auth.BasicAuthenticationProvider$onAuthenticate$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003f
            if (r2 != r3) goto L_0x0037
            java.lang.Object r7 = r0.L$2
            io.ktor.server.auth.UserPasswordCredential r7 = (io.ktor.server.auth.UserPasswordCredential) r7
            java.lang.Object r1 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r1 = (io.ktor.server.auth.AuthenticationContext) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.server.auth.BasicAuthenticationProvider r0 = (io.ktor.server.auth.BasicAuthenticationProvider) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0066
        L_0x0037:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ktor.server.application.ApplicationCall r8 = r7.getCall()
            io.ktor.server.request.ApplicationRequest r2 = r8.getRequest()
            java.nio.charset.Charset r5 = r6.charset
            io.ktor.server.auth.UserPasswordCredential r2 = io.ktor.server.auth.BasicAuthKt.basicAuthenticationCredentials(r2, r5)
            if (r2 == 0) goto L_0x006b
            kotlin.jvm.functions.Function3<io.ktor.server.application.ApplicationCall, io.ktor.server.auth.UserPasswordCredential, kotlin.coroutines.Continuation<? super io.ktor.server.auth.Principal>, java.lang.Object> r5 = r6.authenticationFunction
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r2
            r0.label = r3
            java.lang.Object r8 = r5.invoke(r8, r2, r0)
            if (r8 != r1) goto L_0x0063
            return r1
        L_0x0063:
            r0 = r6
            r1 = r7
            r7 = r2
        L_0x0066:
            io.ktor.server.auth.Principal r8 = (io.ktor.server.auth.Principal) r8
            r2 = r7
            r7 = r1
            goto L_0x006d
        L_0x006b:
            r0 = r6
            r8 = r4
        L_0x006d:
            if (r2 != 0) goto L_0x0074
            io.ktor.server.auth.AuthenticationFailedCause$NoCredentials r1 = io.ktor.server.auth.AuthenticationFailedCause.NoCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r1 = (io.ktor.server.auth.AuthenticationFailedCause) r1
            goto L_0x007c
        L_0x0074:
            if (r8 != 0) goto L_0x007b
            io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r1 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r1 = (io.ktor.server.auth.AuthenticationFailedCause) r1
            goto L_0x007c
        L_0x007b:
            r1 = r4
        L_0x007c:
            if (r1 == 0) goto L_0x008c
            java.lang.Object r2 = io.ktor.server.auth.BasicAuthKt.basicAuthenticationChallengeKey
            io.ktor.server.auth.BasicAuthenticationProvider$onAuthenticate$2 r3 = new io.ktor.server.auth.BasicAuthenticationProvider$onAuthenticate$2
            r3.<init>(r0, r4)
            kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
            r7.challenge(r2, r1, r3)
        L_0x008c:
            if (r8 == 0) goto L_0x0095
            java.lang.String r0 = r0.getName()
            r7.principal((java.lang.String) r0, (io.ktor.server.auth.Principal) r8)
        L_0x0095:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.BasicAuthenticationProvider.onAuthenticate(io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0011\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J?\u0010 \u001a\u00020!2/\u0010\"\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u0012RW\u0010\u0005\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\u0002\u0012\u0004\b\b(\n\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0006¢\u0006\u0002\b\u000eX\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R4\u0010\u0017\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u00162\u000e\u0010\u0014\u001a\n\u0018\u00010\u0015j\u0004\u0018\u0001`\u0016@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"Lio/ktor/server/auth/BasicAuthenticationProvider$Config;", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "name", "", "(Ljava/lang/String;)V", "authenticationFunction", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/UserPasswordCredential;", "Lkotlin/ParameterName;", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "getAuthenticationFunction$ktor_server_auth", "()Lkotlin/jvm/functions/Function3;", "setAuthenticationFunction$ktor_server_auth", "(Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "value", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "charset", "getCharset", "()Ljava/nio/charset/Charset;", "setCharset", "(Ljava/nio/charset/Charset;)V", "realm", "getRealm", "()Ljava/lang/String;", "setRealm", "validate", "", "body", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BasicAuth.kt */
    public static final class Config extends AuthenticationProvider.Config {
        private Function3<? super ApplicationCall, ? super UserPasswordCredential, ? super Continuation<? super Principal>, ? extends Object> authenticationFunction = new BasicAuthenticationProvider$Config$authenticationFunction$1((Continuation<? super BasicAuthenticationProvider$Config$authenticationFunction$1>) null);
        private Charset charset = Charsets.UTF_8;
        private String realm = "Ktor Server";

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

        public final String getRealm() {
            return this.realm;
        }

        public final void setRealm(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.realm = str;
        }

        public final Charset getCharset() {
            return this.charset;
        }

        public final void setCharset(Charset charset2) {
            if (charset2 == null || Intrinsics.areEqual((Object) charset2, (Object) Charsets.UTF_8)) {
                this.charset = charset2;
                return;
            }
            throw new IllegalArgumentException("Basic Authentication charset can be either UTF-8 or null");
        }

        public final void validate(Function3<? super ApplicationCall, ? super UserPasswordCredential, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "body");
            this.authenticationFunction = function3;
        }
    }
}
