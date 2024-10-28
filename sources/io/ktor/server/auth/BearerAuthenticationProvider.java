package io.ktor.server.auth;

import io.ktor.http.auth.AuthScheme;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001eB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010\u001dRK\u0010\u0005\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0006¢\u0006\u0002\b\u000fX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00150\u0014X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0012X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00120\u0018X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u001f"}, d2 = {"Lio/ktor/server/auth/BearerAuthenticationProvider;", "Lio/ktor/server/auth/AuthenticationProvider;", "config", "Lio/ktor/server/auth/BearerAuthenticationProvider$Config;", "(Lio/ktor/server/auth/BearerAuthenticationProvider$Config;)V", "authenticate", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/BearerTokenCredential;", "Lkotlin/ParameterName;", "name", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "defaultScheme", "", "getAuthHeader", "Lkotlin/Function1;", "Lio/ktor/http/auth/HttpAuthHeader;", "realm", "schemesLowerCase", "", "onAuthenticate", "", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Config", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BearerAuth.kt */
public final class BearerAuthenticationProvider extends AuthenticationProvider {
    private final Function3<ApplicationCall, BearerTokenCredential, Continuation<? super Principal>, Object> authenticate;
    /* access modifiers changed from: private */
    public final String defaultScheme;
    private final Function1<ApplicationCall, HttpAuthHeader> getAuthHeader;
    /* access modifiers changed from: private */
    public final String realm;
    private final Set<String> schemesLowerCase;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BearerAuthenticationProvider(Config config) {
        super(config);
        Intrinsics.checkNotNullParameter(config, "config");
        this.realm = config.getRealm();
        this.defaultScheme = config.getDefaultScheme$ktor_server_auth();
        Iterable<String> additionalSchemes$ktor_server_auth = config.getAdditionalSchemes$ktor_server_auth();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(additionalSchemes$ktor_server_auth, 10));
        for (String lowerCase : additionalSchemes$ktor_server_auth) {
            String lowerCase2 = lowerCase.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            arrayList.add(lowerCase2);
        }
        Set set = CollectionsKt.toSet((List) arrayList);
        String lowerCase3 = config.getDefaultScheme$ktor_server_auth().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase3, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        this.schemesLowerCase = SetsKt.plus(set, lowerCase3);
        this.authenticate = config.getAuthenticate$ktor_server_auth();
        this.getAuthHeader = config.getGetAuthHeader$ktor_server_auth();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object onAuthenticate(io.ktor.server.auth.AuthenticationContext r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$1 r0 = (io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$1 r0 = new io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$1
            r0.<init>(r7, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x003c
            if (r2 != r3) goto L_0x0034
            java.lang.Object r8 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r8 = (io.ktor.server.auth.AuthenticationContext) r8
            java.lang.Object r0 = r0.L$0
            io.ktor.server.auth.BearerAuthenticationProvider r0 = (io.ktor.server.auth.BearerAuthenticationProvider) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00a7
        L_0x0034:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlin.jvm.functions.Function1<io.ktor.server.application.ApplicationCall, io.ktor.http.auth.HttpAuthHeader> r9 = r7.getAuthHeader
            io.ktor.server.application.ApplicationCall r2 = r8.getCall()
            java.lang.Object r9 = r9.invoke(r2)
            io.ktor.http.auth.HttpAuthHeader r9 = (io.ktor.http.auth.HttpAuthHeader) r9
            if (r9 != 0) goto L_0x0065
            r9 = r7
            io.ktor.server.auth.BearerAuthenticationProvider r9 = (io.ktor.server.auth.BearerAuthenticationProvider) r9
            java.lang.Object r9 = io.ktor.server.auth.BearerAuthKt.challengeKey
            io.ktor.server.auth.AuthenticationFailedCause$NoCredentials r0 = io.ktor.server.auth.AuthenticationFailedCause.NoCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r0 = (io.ktor.server.auth.AuthenticationFailedCause) r0
            io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$authHeader$1$1 r1 = new io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$authHeader$1$1
            r1.<init>(r7, r4)
            kotlin.jvm.functions.Function3 r1 = (kotlin.jvm.functions.Function3) r1
            r8.challenge(r9, r0, r1)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0065:
            boolean r2 = r9 instanceof io.ktor.http.auth.HttpAuthHeader.Single
            if (r2 == 0) goto L_0x006c
            io.ktor.http.auth.HttpAuthHeader$Single r9 = (io.ktor.http.auth.HttpAuthHeader.Single) r9
            goto L_0x006d
        L_0x006c:
            r9 = r4
        L_0x006d:
            if (r9 == 0) goto L_0x00b1
            java.util.Set<java.lang.String> r2 = r7.schemesLowerCase
            java.lang.String r5 = r9.getAuthScheme()
            java.util.Locale r6 = java.util.Locale.ROOT
            java.lang.String r5 = r5.toLowerCase(r6)
            java.lang.String r6 = "this as java.lang.String).toLowerCase(Locale.ROOT)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            boolean r2 = r2.contains(r5)
            if (r2 == 0) goto L_0x0087
            goto L_0x0088
        L_0x0087:
            r9 = r4
        L_0x0088:
            if (r9 == 0) goto L_0x00b1
            kotlin.jvm.functions.Function3<io.ktor.server.application.ApplicationCall, io.ktor.server.auth.BearerTokenCredential, kotlin.coroutines.Continuation<? super io.ktor.server.auth.Principal>, java.lang.Object> r2 = r7.authenticate
            io.ktor.server.application.ApplicationCall r5 = r8.getCall()
            io.ktor.server.auth.BearerTokenCredential r6 = new io.ktor.server.auth.BearerTokenCredential
            java.lang.String r9 = r9.getBlob()
            r6.<init>(r9)
            r0.L$0 = r7
            r0.L$1 = r8
            r0.label = r3
            java.lang.Object r9 = r2.invoke(r5, r6, r0)
            if (r9 != r1) goto L_0x00a6
            return r1
        L_0x00a6:
            r0 = r7
        L_0x00a7:
            io.ktor.server.auth.Principal r9 = (io.ktor.server.auth.Principal) r9
            if (r9 == 0) goto L_0x00b2
            r8.principal((io.ktor.server.auth.Principal) r9)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x00b1:
            r0 = r7
        L_0x00b2:
            r9 = r0
            io.ktor.server.auth.BearerAuthenticationProvider r9 = (io.ktor.server.auth.BearerAuthenticationProvider) r9
            java.lang.Object r9 = io.ktor.server.auth.BearerAuthKt.challengeKey
            io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r1 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r1 = (io.ktor.server.auth.AuthenticationFailedCause) r1
            io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$principal$3$1 r2 = new io.ktor.server.auth.BearerAuthenticationProvider$onAuthenticate$principal$3$1
            r2.<init>(r0, r4)
            kotlin.jvm.functions.Function3 r2 = (kotlin.jvm.functions.Function3) r2
            r8.challenge(r9, r1, r2)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.BearerAuthenticationProvider.onAuthenticate(io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010(\u001a\u00020)2\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010 0\u001fJ)\u0010*\u001a\u00020)2\b\b\u0002\u0010\u001a\u001a\u00020\u00032\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030+\"\u00020\u0003¢\u0006\u0002\u0010,J?\u0010\u000b\u001a\u00020)2/\u0010\u000b\u001a+\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00130\f¢\u0006\u0002\b\u0014ø\u0001\u0000¢\u0006\u0002\u0010\u0018J\r\u0010-\u001a\u00020.H\u0000¢\u0006\u0002\b/R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nRW\u0010\u000b\u001a:\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0002\u0012\u0004\b\b(\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00130\f¢\u0006\u0002\b\u0014X\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0019\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001a\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u0004R(\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010 0\u001fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u001c\"\u0004\b'\u0010\u0004\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"Lio/ktor/server/auth/BearerAuthenticationProvider$Config;", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "name", "", "(Ljava/lang/String;)V", "additionalSchemes", "", "getAdditionalSchemes$ktor_server_auth", "()Ljava/util/Set;", "setAdditionalSchemes$ktor_server_auth", "(Ljava/util/Set;)V", "authenticate", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/server/auth/BearerTokenCredential;", "Lkotlin/ParameterName;", "credentials", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "", "Lkotlin/ExtensionFunctionType;", "getAuthenticate$ktor_server_auth", "()Lkotlin/jvm/functions/Function3;", "setAuthenticate$ktor_server_auth", "(Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "defaultScheme", "getDefaultScheme$ktor_server_auth", "()Ljava/lang/String;", "setDefaultScheme$ktor_server_auth", "getAuthHeader", "Lkotlin/Function1;", "Lio/ktor/http/auth/HttpAuthHeader;", "getGetAuthHeader$ktor_server_auth", "()Lkotlin/jvm/functions/Function1;", "setGetAuthHeader$ktor_server_auth", "(Lkotlin/jvm/functions/Function1;)V", "realm", "getRealm", "setRealm", "authHeader", "", "authSchemes", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "build", "Lio/ktor/server/auth/BearerAuthenticationProvider;", "build$ktor_server_auth", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: BearerAuth.kt */
    public static final class Config extends AuthenticationProvider.Config {
        private Set<String> additionalSchemes = SetsKt.emptySet();
        private Function3<? super ApplicationCall, ? super BearerTokenCredential, ? super Continuation<? super Principal>, ? extends Object> authenticate = new BearerAuthenticationProvider$Config$authenticate$1((Continuation<? super BearerAuthenticationProvider$Config$authenticate$1>) null);
        private String defaultScheme = AuthScheme.Bearer;
        private Function1<? super ApplicationCall, ? extends HttpAuthHeader> getAuthHeader = BearerAuthenticationProvider$Config$getAuthHeader$1.INSTANCE;
        private String realm;

        public Config(String str) {
            super(str);
        }

        public final Function3<ApplicationCall, BearerTokenCredential, Continuation<? super Principal>, Object> getAuthenticate$ktor_server_auth() {
            return this.authenticate;
        }

        public final void setAuthenticate$ktor_server_auth(Function3<? super ApplicationCall, ? super BearerTokenCredential, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.authenticate = function3;
        }

        public final Function1<ApplicationCall, HttpAuthHeader> getGetAuthHeader$ktor_server_auth() {
            return this.getAuthHeader;
        }

        public final void setGetAuthHeader$ktor_server_auth(Function1<? super ApplicationCall, ? extends HttpAuthHeader> function1) {
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            this.getAuthHeader = function1;
        }

        public final String getDefaultScheme$ktor_server_auth() {
            return this.defaultScheme;
        }

        public final void setDefaultScheme$ktor_server_auth(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.defaultScheme = str;
        }

        public final Set<String> getAdditionalSchemes$ktor_server_auth() {
            return this.additionalSchemes;
        }

        public final void setAdditionalSchemes$ktor_server_auth(Set<String> set) {
            Intrinsics.checkNotNullParameter(set, "<set-?>");
            this.additionalSchemes = set;
        }

        public final String getRealm() {
            return this.realm;
        }

        public final void setRealm(String str) {
            this.realm = str;
        }

        public final void authenticate(Function3<? super ApplicationCall, ? super BearerTokenCredential, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "authenticate");
            this.authenticate = function3;
        }

        public final void authHeader(Function1<? super ApplicationCall, ? extends HttpAuthHeader> function1) {
            Intrinsics.checkNotNullParameter(function1, "getAuthHeader");
            this.getAuthHeader = function1;
        }

        public static /* synthetic */ void authSchemes$default(Config config, String str, String[] strArr, int i, Object obj) {
            if ((i & 1) != 0) {
                str = AuthScheme.Bearer;
            }
            config.authSchemes(str, strArr);
        }

        public final void authSchemes(String str, String... strArr) {
            Intrinsics.checkNotNullParameter(str, "defaultScheme");
            Intrinsics.checkNotNullParameter(strArr, "additionalSchemes");
            this.defaultScheme = str;
            this.additionalSchemes = ArraysKt.toSet((T[]) strArr);
        }

        public final BearerAuthenticationProvider build$ktor_server_auth() {
            return new BearerAuthenticationProvider(this);
        }
    }
}
