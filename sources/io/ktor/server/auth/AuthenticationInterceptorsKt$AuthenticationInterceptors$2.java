package io.ktor.server.auth;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.application.RouteScopedPluginBuilder;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lio/ktor/server/application/RouteScopedPluginBuilder;", "Lio/ktor/server/auth/RouteAuthenticationConfig;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
final class AuthenticationInterceptorsKt$AuthenticationInterceptors$2 extends Lambda implements Function1<RouteScopedPluginBuilder<RouteAuthenticationConfig>, Unit> {
    public static final AuthenticationInterceptorsKt$AuthenticationInterceptors$2 INSTANCE = new AuthenticationInterceptorsKt$AuthenticationInterceptors$2();

    AuthenticationInterceptorsKt$AuthenticationInterceptors$2() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((RouteScopedPluginBuilder<RouteAuthenticationConfig>) (RouteScopedPluginBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(RouteScopedPluginBuilder<RouteAuthenticationConfig> routeScopedPluginBuilder) {
        Intrinsics.checkNotNullParameter(routeScopedPluginBuilder, "$this$createRouteScopedPlugin");
        List<AuthenticateProvidersRegistration> providers$ktor_server_auth = routeScopedPluginBuilder.getPluginConfig().getProviders$ktor_server_auth();
        AuthenticationConfig config$ktor_server_auth = ((Authentication) ApplicationPluginKt.plugin(routeScopedPluginBuilder.getApplication(), Authentication.Companion)).getConfig$ktor_server_auth();
        Collection collection = providers$ktor_server_auth;
        final Set access$findProviders = AuthenticationInterceptorsKt.findProviders(config$ktor_server_auth, collection, AuthenticationInterceptorsKt$AuthenticationInterceptors$2$requiredProviders$1.INSTANCE);
        Iterable iterable = access$findProviders;
        final Set minus = SetsKt.minus(AuthenticationInterceptorsKt.findProviders(config$ktor_server_auth, collection, AuthenticationInterceptorsKt$AuthenticationInterceptors$2$notRequiredProviders$1.INSTANCE), iterable);
        final Set minus2 = SetsKt.minus(AuthenticationInterceptorsKt.findProviders(config$ktor_server_auth, collection, AuthenticationInterceptorsKt$AuthenticationInterceptors$2$firstSuccessfulProviders$1.INSTANCE), iterable);
        final Set minus3 = SetsKt.minus(SetsKt.minus(AuthenticationInterceptorsKt.findProviders(config$ktor_server_auth, collection, AuthenticationInterceptorsKt$AuthenticationInterceptors$2$optionalProviders$1.INSTANCE), iterable), minus2);
        routeScopedPluginBuilder.on(AuthenticationHook.INSTANCE, new AnonymousClass1((Continuation<? super AnonymousClass1>) null));
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "call", "Lio/ktor/server/application/ApplicationCall;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.auth.AuthenticationInterceptorsKt$AuthenticationInterceptors$2$1", f = "AuthenticationInterceptors.kt", i = {0, 0, 0, 0, 2, 2, 2}, l = {81, 85, 102, 122}, m = "invokeSuspend", n = {"call", "authenticationContext", "provider", "count", "call", "authenticationContext", "provider"}, s = {"L$0", "L$1", "L$3", "I$0", "L$0", "L$1", "L$3"})
    /* renamed from: io.ktor.server.auth.AuthenticationInterceptorsKt$AuthenticationInterceptors$2$1  reason: invalid class name */
    /* compiled from: AuthenticationInterceptors.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ApplicationCall, Continuation<? super Unit>, Object> {
        int I$0;
        /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(access$findProviders, minus, minus3, minus2, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(ApplicationCall applicationCall, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(applicationCall, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:60:0x0246, code lost:
            r8 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER();
            r8.trace("Trying to authenticate " + io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r11.getRequest()) + " with " + r2.getName());
            r0.L$0 = r11;
            r0.L$1 = r15;
            r0.L$2 = r10;
            r0.L$3 = r2;
            r0.label = 3;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:61:0x027f, code lost:
            if (r2.onAuthenticate(r15, r0) != r1) goto L_0x0282;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:62:0x0281, code lost:
            return r1;
         */
        /* JADX WARNING: Removed duplicated region for block: B:102:0x00ce A[EDGE_INSN: B:102:0x00ce->B:32:0x00ce ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:105:0x0246 A[EDGE_INSN: B:105:0x0246->B:60:0x0246 ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:108:0x021f A[EDGE_INSN: B:108:0x021f->B:59:0x021f ?: BREAK  , SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0096  */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0133 A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:43:0x017d  */
        /* JADX WARNING: Removed duplicated region for block: B:99:0x00f6 A[EDGE_INSN: B:99:0x00f6->B:33:0x00f6 ?: BREAK  , SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object invokeSuspend(java.lang.Object r20) {
            /*
                r19 = this;
                r0 = r19
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                java.lang.String r3 = " for "
                java.lang.String r4 = "Skipping authentication provider "
                java.lang.String r5 = "Trying to authenticate "
                java.lang.String r6 = "Authentication failed for "
                java.lang.String r7 = "Authentication succeeded for "
                r8 = 4
                r9 = 3
                r10 = 2
                java.lang.String r12 = " with provider "
                r13 = 0
                r14 = 1
                if (r2 == 0) goto L_0x0062
                if (r2 == r14) goto L_0x004a
                if (r2 == r10) goto L_0x0045
                if (r2 == r9) goto L_0x0030
                if (r2 != r8) goto L_0x0028
                kotlin.ResultKt.throwOnFailure(r20)
                goto L_0x0374
            L_0x0028:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L_0x0030:
                java.lang.Object r2 = r0.L$3
                io.ktor.server.auth.AuthenticationProvider r2 = (io.ktor.server.auth.AuthenticationProvider) r2
                java.lang.Object r10 = r0.L$2
                java.util.Iterator r10 = (java.util.Iterator) r10
                java.lang.Object r15 = r0.L$1
                io.ktor.server.auth.AuthenticationContext r15 = (io.ktor.server.auth.AuthenticationContext) r15
                java.lang.Object r11 = r0.L$0
                io.ktor.server.application.ApplicationCall r11 = (io.ktor.server.application.ApplicationCall) r11
                kotlin.ResultKt.throwOnFailure(r20)
                goto L_0x0282
            L_0x0045:
                kotlin.ResultKt.throwOnFailure(r20)
                goto L_0x017a
            L_0x004a:
                int r2 = r0.I$0
                java.lang.Object r11 = r0.L$3
                io.ktor.server.auth.AuthenticationProvider r11 = (io.ktor.server.auth.AuthenticationProvider) r11
                java.lang.Object r15 = r0.L$2
                java.util.Iterator r15 = (java.util.Iterator) r15
                java.lang.Object r8 = r0.L$1
                io.ktor.server.auth.AuthenticationContext r8 = (io.ktor.server.auth.AuthenticationContext) r8
                java.lang.Object r9 = r0.L$0
                io.ktor.server.application.ApplicationCall r9 = (io.ktor.server.application.ApplicationCall) r9
                kotlin.ResultKt.throwOnFailure(r20)
                r13 = 1
                goto L_0x0134
            L_0x0062:
                kotlin.ResultKt.throwOnFailure(r20)
                java.lang.Object r2 = r0.L$0
                io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
                boolean r8 = io.ktor.server.application.ApplicationCallKt.isHandled(r2)
                if (r8 == 0) goto L_0x0072
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L_0x0072:
                io.ktor.server.auth.AuthenticationContext$Companion r8 = io.ktor.server.auth.AuthenticationContext.Companion
                io.ktor.server.auth.AuthenticationContext r8 = r8.from$ktor_server_auth(r2)
                java.lang.Class<io.ktor.server.auth.Principal> r9 = io.ktor.server.auth.Principal.class
                kotlin.reflect.KClass r9 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r9)
                io.ktor.server.auth.Principal r9 = r8.principal((java.lang.String) r13, r9)
                if (r9 == 0) goto L_0x0087
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L_0x0087:
                java.util.Set<io.ktor.server.auth.AuthenticationProvider> r9 = r4
                java.util.Iterator r9 = r9.iterator()
                r15 = r9
                r9 = r2
                r2 = 0
            L_0x0090:
                boolean r11 = r15.hasNext()
                if (r11 == 0) goto L_0x01a4
                java.lang.Object r11 = r15.next()
                io.ktor.server.auth.AuthenticationProvider r11 = (io.ktor.server.auth.AuthenticationProvider) r11
                java.util.List r16 = r11.getSkipWhen()
                r10 = r16
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                boolean r13 = r10 instanceof java.util.Collection
                if (r13 == 0) goto L_0x00b2
                r13 = r10
                java.util.Collection r13 = (java.util.Collection) r13
                boolean r13 = r13.isEmpty()
                if (r13 == 0) goto L_0x00b2
                goto L_0x00f6
            L_0x00b2:
                java.util.Iterator r10 = r10.iterator()
            L_0x00b6:
                boolean r13 = r10.hasNext()
                if (r13 == 0) goto L_0x00f6
                java.lang.Object r13 = r10.next()
                kotlin.jvm.functions.Function1 r13 = (kotlin.jvm.functions.Function1) r13
                java.lang.Object r13 = r13.invoke(r9)
                java.lang.Boolean r13 = (java.lang.Boolean) r13
                boolean r13 = r13.booleanValue()
                if (r13 == 0) goto L_0x00b6
                org.slf4j.Logger r10 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r13 = new java.lang.StringBuilder
                r13.<init>(r4)
                java.lang.String r11 = r11.getName()
                r13.append(r11)
                r13.append(r3)
                io.ktor.server.request.ApplicationRequest r11 = r9.getRequest()
                java.lang.String r11 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r11)
                r13.append(r11)
                java.lang.String r11 = r13.toString()
                r10.trace(r11)
                r10 = 2
                r13 = 0
                goto L_0x0090
            L_0x00f6:
                org.slf4j.Logger r10 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r13 = new java.lang.StringBuilder
                r13.<init>(r5)
                io.ktor.server.request.ApplicationRequest r18 = r9.getRequest()
                java.lang.String r14 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r18)
                r13.append(r14)
                java.lang.String r14 = " with required "
                r13.append(r14)
                java.lang.String r14 = r11.getName()
                r13.append(r14)
                java.lang.String r13 = r13.toString()
                r10.trace(r13)
                r10 = r0
                kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
                r0.L$0 = r9
                r0.L$1 = r8
                r0.L$2 = r15
                r0.L$3 = r11
                r0.I$0 = r2
                r13 = 1
                r0.label = r13
                java.lang.Object r10 = r11.onAuthenticate(r8, r10)
                if (r10 != r1) goto L_0x0134
                return r1
            L_0x0134:
                int r2 = r2 + r13
                io.ktor.server.auth.CombinedPrincipal r10 = r8.get_principal$ktor_server_auth()
                java.util.List r10 = r10.getPrincipals()
                int r10 = r10.size()
                if (r10 >= r2) goto L_0x017d
                org.slf4j.Logger r2 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>(r6)
                io.ktor.server.request.ApplicationRequest r4 = r9.getRequest()
                java.lang.String r4 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r4)
                r3.append(r4)
                r3.append(r12)
                r3.append(r11)
                java.lang.String r3 = r3.toString()
                r2.trace(r3)
                r2 = r0
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 0
                r0.L$0 = r3
                r0.L$1 = r3
                r0.L$2 = r3
                r0.L$3 = r3
                r10 = 2
                r0.label = r10
                java.lang.Object r2 = io.ktor.server.auth.AuthenticationInterceptorsKt.executeChallenges(r8, r9, r2)
                if (r2 != r1) goto L_0x017a
                return r1
            L_0x017a:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L_0x017d:
                r10 = 2
                org.slf4j.Logger r13 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r14 = new java.lang.StringBuilder
                r14.<init>(r7)
                io.ktor.server.request.ApplicationRequest r17 = r9.getRequest()
                java.lang.String r10 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r17)
                r14.append(r10)
                r14.append(r12)
                r14.append(r11)
                java.lang.String r10 = r14.toString()
                r13.trace(r10)
                r10 = 2
                r13 = 0
                r14 = 1
                goto L_0x0090
            L_0x01a4:
                java.util.Set<io.ktor.server.auth.AuthenticationProvider> r2 = r5
                java.util.Iterator r2 = r2.iterator()
                r10 = r2
                r15 = r8
                r11 = r9
            L_0x01ad:
                boolean r2 = r10.hasNext()
                if (r2 == 0) goto L_0x02da
                java.lang.Object r2 = r10.next()
                io.ktor.server.auth.AuthenticationProvider r2 = (io.ktor.server.auth.AuthenticationProvider) r2
                io.ktor.server.auth.CombinedPrincipal r8 = r15.get_principal$ktor_server_auth()
                java.util.List r8 = r8.getPrincipals()
                java.util.Collection r8 = (java.util.Collection) r8
                boolean r8 = r8.isEmpty()
                r9 = 1
                r8 = r8 ^ r9
                if (r8 == 0) goto L_0x01ef
                org.slf4j.Logger r2 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                java.lang.String r4 = "Authenticate for "
                r3.<init>(r4)
                io.ktor.server.request.ApplicationRequest r4 = r11.getRequest()
                java.lang.String r4 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r4)
                r3.append(r4)
                java.lang.String r4 = " succeed. Skipping other providers"
                r3.append(r4)
                java.lang.String r3 = r3.toString()
                r2.trace(r3)
                goto L_0x02da
            L_0x01ef:
                java.util.List r8 = r2.getSkipWhen()
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                boolean r9 = r8 instanceof java.util.Collection
                if (r9 == 0) goto L_0x0203
                r9 = r8
                java.util.Collection r9 = (java.util.Collection) r9
                boolean r9 = r9.isEmpty()
                if (r9 == 0) goto L_0x0203
                goto L_0x0246
            L_0x0203:
                java.util.Iterator r8 = r8.iterator()
            L_0x0207:
                boolean r9 = r8.hasNext()
                if (r9 == 0) goto L_0x0246
                java.lang.Object r9 = r8.next()
                kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
                java.lang.Object r9 = r9.invoke(r11)
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r9 = r9.booleanValue()
                if (r9 == 0) goto L_0x0207
                org.slf4j.Logger r8 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r9.<init>(r4)
                java.lang.String r2 = r2.getName()
                r9.append(r2)
                r9.append(r3)
                io.ktor.server.request.ApplicationRequest r2 = r11.getRequest()
                java.lang.String r2 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r2)
                r9.append(r2)
                java.lang.String r2 = r9.toString()
                r8.trace(r2)
                goto L_0x01ad
            L_0x0246:
                org.slf4j.Logger r8 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r9 = new java.lang.StringBuilder
                r9.<init>(r5)
                io.ktor.server.request.ApplicationRequest r13 = r11.getRequest()
                java.lang.String r13 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r13)
                r9.append(r13)
                java.lang.String r13 = " with "
                r9.append(r13)
                java.lang.String r13 = r2.getName()
                r9.append(r13)
                java.lang.String r9 = r9.toString()
                r8.trace(r9)
                r8 = r0
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r0.L$0 = r11
                r0.L$1 = r15
                r0.L$2 = r10
                r0.L$3 = r2
                r9 = 3
                r0.label = r9
                java.lang.Object r8 = r2.onAuthenticate(r15, r8)
                if (r8 != r1) goto L_0x0282
                return r1
            L_0x0282:
                io.ktor.server.auth.CombinedPrincipal r8 = r15.get_principal$ktor_server_auth()
                java.util.List r8 = r8.getPrincipals()
                java.util.Collection r8 = (java.util.Collection) r8
                boolean r8 = r8.isEmpty()
                r13 = 1
                r8 = r8 ^ r13
                if (r8 == 0) goto L_0x02b7
                org.slf4j.Logger r8 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r13 = new java.lang.StringBuilder
                r13.<init>(r7)
                io.ktor.server.request.ApplicationRequest r14 = r11.getRequest()
                java.lang.String r14 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r14)
                r13.append(r14)
                r13.append(r12)
                r13.append(r2)
                java.lang.String r2 = r13.toString()
                r8.trace(r2)
                goto L_0x01ad
            L_0x02b7:
                org.slf4j.Logger r8 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r13 = new java.lang.StringBuilder
                r13.<init>(r6)
                io.ktor.server.request.ApplicationRequest r14 = r11.getRequest()
                java.lang.String r14 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r14)
                r13.append(r14)
                r13.append(r12)
                r13.append(r2)
                java.lang.String r2 = r13.toString()
                r8.trace(r2)
                goto L_0x01ad
            L_0x02da:
                io.ktor.server.auth.CombinedPrincipal r2 = r15.get_principal$ktor_server_auth()
                java.util.List r2 = r2.getPrincipals()
                java.util.Collection r2 = (java.util.Collection) r2
                boolean r2 = r2.isEmpty()
                r13 = 1
                r2 = r2 ^ r13
                if (r2 == 0) goto L_0x02ef
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L_0x02ef:
                java.util.Set<io.ktor.server.auth.AuthenticationProvider> r2 = r6
                java.util.Collection r2 = (java.util.Collection) r2
                boolean r2 = r2.isEmpty()
                r2 = r2 ^ r13
                if (r2 == 0) goto L_0x030c
                java.util.Set<io.ktor.server.auth.AuthenticationProvider> r2 = r7
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x030c
                java.util.Set<io.ktor.server.auth.AuthenticationProvider> r2 = r4
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x030c
                r2 = 1
                goto L_0x030d
            L_0x030c:
                r2 = 0
            L_0x030d:
                java.util.List r3 = r15.getAllFailures()
                java.lang.Iterable r3 = (java.lang.Iterable) r3
                boolean r4 = r3 instanceof java.util.Collection
                if (r4 == 0) goto L_0x0321
                r4 = r3
                java.util.Collection r4 = (java.util.Collection) r4
                boolean r4 = r4.isEmpty()
                if (r4 == 0) goto L_0x0321
                goto L_0x033a
            L_0x0321:
                java.util.Iterator r3 = r3.iterator()
            L_0x0325:
                boolean r4 = r3.hasNext()
                if (r4 == 0) goto L_0x033a
                java.lang.Object r4 = r3.next()
                io.ktor.server.auth.AuthenticationFailedCause r4 = (io.ktor.server.auth.AuthenticationFailedCause) r4
                io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r5 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
                boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
                if (r4 == 0) goto L_0x0325
                r13 = 0
            L_0x033a:
                if (r2 == 0) goto L_0x035e
                if (r13 == 0) goto L_0x035e
                org.slf4j.Logger r1 = io.ktor.server.auth.AuthenticationInterceptorsKt.getLOGGER()
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "Authentication is optional and no credentials were provided for "
                r2.<init>(r3)
                io.ktor.server.request.ApplicationRequest r3 = r11.getRequest()
                java.lang.String r3 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r3)
                r2.append(r3)
                java.lang.String r2 = r2.toString()
                r1.trace(r2)
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            L_0x035e:
                r2 = r0
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r3 = 0
                r0.L$0 = r3
                r0.L$1 = r3
                r0.L$2 = r3
                r0.L$3 = r3
                r3 = 4
                r0.label = r3
                java.lang.Object r2 = io.ktor.server.auth.AuthenticationInterceptorsKt.executeChallenges(r15, r11, r2)
                if (r2 != r1) goto L_0x0374
                return r1
            L_0x0374:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.AuthenticationInterceptorsKt$AuthenticationInterceptors$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
