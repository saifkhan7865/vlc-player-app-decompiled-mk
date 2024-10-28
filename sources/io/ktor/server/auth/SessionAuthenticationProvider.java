package io.ktor.server.auth;

import io.ktor.http.Url;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.AuthenticationProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001c*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002\u001c\u001dB\u0015\b\u0002\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bR<\u0010\u0007\u001a+\b\u0001\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\fX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011RK\u0010\u0012\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0013\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00170\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b¢\u0006\u0002\b\fX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/server/auth/SessionAuthenticationProvider;", "T", "", "Lio/ktor/server/auth/AuthenticationProvider;", "config", "Lio/ktor/server/auth/SessionAuthenticationProvider$Config;", "(Lio/ktor/server/auth/SessionAuthenticationProvider$Config;)V", "challengeFunction", "Lkotlin/Function3;", "Lio/ktor/server/auth/SessionChallengeContext;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "type", "Lkotlin/reflect/KClass;", "getType", "()Lkotlin/reflect/KClass;", "validator", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "name", "credentials", "Lio/ktor/server/auth/Principal;", "onAuthenticate", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Config", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionAuth.kt */
public final class SessionAuthenticationProvider<T> extends AuthenticationProvider {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Function3<ApplicationCall, Object, Continuation<? super Principal>, Object> UninitializedValidator = new SessionAuthenticationProvider$Companion$UninitializedValidator$1((Continuation<? super SessionAuthenticationProvider$Companion$UninitializedValidator$1>) null);
    /* access modifiers changed from: private */
    public final Function3<SessionChallengeContext, T, Continuation<? super Unit>, Object> challengeFunction;
    private final KClass<T> type;
    private final Function3<ApplicationCall, T, Continuation<? super Principal>, Object> validator;

    public /* synthetic */ SessionAuthenticationProvider(Config config, DefaultConstructorMarker defaultConstructorMarker) {
        this(config);
    }

    private SessionAuthenticationProvider(Config<T> config) {
        super(config);
        this.type = config.getType$ktor_server_auth();
        this.challengeFunction = config.getChallengeFunction$ktor_server_auth();
        this.validator = config.getValidator$ktor_server_auth();
    }

    public final KClass<T> getType() {
        return this.type;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006d  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object onAuthenticate(io.ktor.server.auth.AuthenticationContext r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$1 r0 = (io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$1 r0 = new io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 != r4) goto L_0x0035
            java.lang.Object r7 = r0.L$2
            java.lang.Object r1 = r0.L$1
            io.ktor.server.auth.AuthenticationContext r1 = (io.ktor.server.auth.AuthenticationContext) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.server.auth.SessionAuthenticationProvider r0 = (io.ktor.server.auth.SessionAuthenticationProvider) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0064
        L_0x0035:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ktor.server.application.ApplicationCall r8 = r7.getCall()
            io.ktor.server.sessions.CurrentSession r2 = io.ktor.server.sessions.SessionDataKt.getSessions(r8)
            kotlin.reflect.KClass<T> r5 = r6.type
            java.lang.Object r2 = io.ktor.server.sessions.SessionDataKt.get(r2, r5)
            if (r2 == 0) goto L_0x0069
            kotlin.jvm.functions.Function3<io.ktor.server.application.ApplicationCall, T, kotlin.coroutines.Continuation<? super io.ktor.server.auth.Principal>, java.lang.Object> r5 = r6.validator
            r0.L$0 = r6
            r0.L$1 = r7
            r0.L$2 = r2
            r0.label = r4
            java.lang.Object r8 = r5.invoke(r8, r2, r0)
            if (r8 != r1) goto L_0x0061
            return r1
        L_0x0061:
            r0 = r6
            r1 = r7
            r7 = r2
        L_0x0064:
            io.ktor.server.auth.Principal r8 = (io.ktor.server.auth.Principal) r8
            r2 = r7
            r7 = r1
            goto L_0x006b
        L_0x0069:
            r0 = r6
            r8 = r3
        L_0x006b:
            if (r8 == 0) goto L_0x0075
            java.lang.String r0 = r0.getName()
            r7.principal((java.lang.String) r0, (io.ktor.server.auth.Principal) r8)
            goto L_0x008c
        L_0x0075:
            if (r2 != 0) goto L_0x007c
            io.ktor.server.auth.AuthenticationFailedCause$NoCredentials r1 = io.ktor.server.auth.AuthenticationFailedCause.NoCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r1 = (io.ktor.server.auth.AuthenticationFailedCause) r1
            goto L_0x0080
        L_0x007c:
            io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r1 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r1 = (io.ktor.server.auth.AuthenticationFailedCause) r1
        L_0x0080:
            io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$2 r2 = new io.ktor.server.auth.SessionAuthenticationProvider$onAuthenticate$2
            r2.<init>(r0, r8, r3)
            kotlin.jvm.functions.Function3 r2 = (kotlin.jvm.functions.Function3) r2
            java.lang.String r8 = "SessionAuth"
            r7.challenge(r8, r1, r2)
        L_0x008c:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.SessionAuthenticationProvider.onAuthenticate(io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000*\b\b\u0001\u0010\u0001*\u00020\u00022\u00020\u0003B\u001f\b\u0001\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00010\u001eH\u0001J\u000e\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020!J?\u0010\u001f\u001a\u00020\r2/\u0010\"\u001a+\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u0012J\u000e\u0010\u001f\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0005J?\u0010$\u001a\u00020\r2/\u0010\"\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00028\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n¢\u0006\u0002\b\u000eø\u0001\u0000¢\u0006\u0002\u0010\u0012J\b\u0010%\u001a\u00020\rH\u0002RH\u0010\t\u001a+\b\u0001\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00018\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n¢\u0006\u0002\b\u000eX\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015RW\u0010\u0016\u001a:\b\u0001\u0012\u0004\u0012\u00020\u0017\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0018\u0012\b\b\u0004\u0012\u0004\b\b(\u0019\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00020\n¢\u0006\u0002\b\u000eX\u000eø\u0001\u0000¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u001b\u0010\u0010\"\u0004\b\u001c\u0010\u0012\u0002\u0004\n\u0002\b\u0019¨\u0006&"}, d2 = {"Lio/ktor/server/auth/SessionAuthenticationProvider$Config;", "T", "", "Lio/ktor/server/auth/AuthenticationProvider$Config;", "name", "", "type", "Lkotlin/reflect/KClass;", "(Ljava/lang/String;Lkotlin/reflect/KClass;)V", "challengeFunction", "Lkotlin/Function3;", "Lio/ktor/server/auth/SessionChallengeContext;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "getChallengeFunction$ktor_server_auth", "()Lkotlin/jvm/functions/Function3;", "setChallengeFunction$ktor_server_auth", "(Lkotlin/jvm/functions/Function3;)V", "Lkotlin/jvm/functions/Function3;", "getType$ktor_server_auth", "()Lkotlin/reflect/KClass;", "validator", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "credentials", "Lio/ktor/server/auth/Principal;", "getValidator$ktor_server_auth", "setValidator$ktor_server_auth", "buildProvider", "Lio/ktor/server/auth/SessionAuthenticationProvider;", "challenge", "redirect", "Lio/ktor/http/Url;", "block", "redirectUrl", "validate", "verifyConfiguration", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionAuth.kt */
    public static final class Config<T> extends AuthenticationProvider.Config {
        private Function3<? super SessionChallengeContext, ? super T, ? super Continuation<? super Unit>, ? extends Object> challengeFunction = new SessionAuthenticationProvider$Config$challengeFunction$1((Continuation<? super SessionAuthenticationProvider$Config$challengeFunction$1>) null);
        private final KClass<T> type;
        private Function3<? super ApplicationCall, ? super T, ? super Continuation<? super Principal>, ? extends Object> validator = SessionAuthenticationProvider.UninitializedValidator;

        public final KClass<T> getType$ktor_server_auth() {
            return this.type;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Config(String str, KClass<T> kClass) {
            super(str);
            Intrinsics.checkNotNullParameter(kClass, "type");
            this.type = kClass;
        }

        public final Function3<ApplicationCall, T, Continuation<? super Principal>, Object> getValidator$ktor_server_auth() {
            return this.validator;
        }

        public final void setValidator$ktor_server_auth(Function3<? super ApplicationCall, ? super T, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.validator = function3;
        }

        public final Function3<SessionChallengeContext, T, Continuation<? super Unit>, Object> getChallengeFunction$ktor_server_auth() {
            return this.challengeFunction;
        }

        public final void setChallengeFunction$ktor_server_auth(Function3<? super SessionChallengeContext, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "<set-?>");
            this.challengeFunction = function3;
        }

        public final void challenge(Function3<? super SessionChallengeContext, ? super T, ? super Continuation<? super Unit>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "block");
            this.challengeFunction = function3;
        }

        public final void challenge(String str) {
            Intrinsics.checkNotNullParameter(str, "redirectUrl");
            challenge(new SessionAuthenticationProvider$Config$challenge$1(str, (Continuation<? super SessionAuthenticationProvider$Config$challenge$1>) null));
        }

        public final void challenge(Url url) {
            Intrinsics.checkNotNullParameter(url, "redirect");
            challenge(url.toString());
        }

        public final void validate(Function3<? super ApplicationCall, ? super T, ? super Continuation<? super Principal>, ? extends Object> function3) {
            Intrinsics.checkNotNullParameter(function3, "block");
            if (this.validator == SessionAuthenticationProvider.UninitializedValidator) {
                this.validator = function3;
                return;
            }
            throw new IllegalStateException("Only one validator could be registered".toString());
        }

        private final void verifyConfiguration() {
            if (this.validator == SessionAuthenticationProvider.UninitializedValidator) {
                throw new IllegalStateException("It should be a validator supplied to a session auth provider".toString());
            }
        }

        public final SessionAuthenticationProvider<T> buildProvider() {
            verifyConfiguration();
            return new SessionAuthenticationProvider<>(this, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R<\u0010\u0003\u001a+\b\u0001\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0001\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004¢\u0006\u0002\b\bX\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/ktor/server/auth/SessionAuthenticationProvider$Companion;", "", "()V", "UninitializedValidator", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/coroutines/Continuation;", "Lio/ktor/server/auth/Principal;", "Lkotlin/ExtensionFunctionType;", "Lkotlin/jvm/functions/Function3;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionAuth.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
