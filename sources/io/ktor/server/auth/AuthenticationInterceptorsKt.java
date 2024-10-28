package io.ktor.server.auth;

import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.server.application.RouteScopedPlugin;
import io.ktor.server.routing.Route;
import io.ktor.util.AttributeKey;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u001e\n\u0002\b\u0002\u001aJ\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u0018\b\u0002\u0010\u000f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0010\"\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\u0010\u0018\u001aL\u0010\r\u001a\u00020\u000e*\u00020\u000e2\u0018\b\u0002\u0010\u000f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00110\u0010\"\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00160\u0015¢\u0006\u0002\b\u0017¢\u0006\u0002\u0010\u001b\u001a\u001d\u0010\u001c\u001a\u00020\u0016*\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH@ø\u0001\u0000¢\u0006\u0002\u0010 \u001aM\u0010\u001c\u001a\u00020\u001a*\u00020\u001d2.\u0010!\u001a*\u0012&\u0012$\b\u0001\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160%\u0012\u0006\u0012\u0004\u0018\u00010&0#0\"2\u0006\u0010\u001e\u001a\u00020\u001fH@ø\u0001\u0000¢\u0006\u0002\u0010'\u001a\u0016\u0010(\u001a\u00020)*\u00020*2\b\u0010+\u001a\u0004\u0018\u00010\u0011H\u0002\u001a4\u0010,\u001a\b\u0012\u0004\u0012\u00020)0-*\u00020*2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020.2\u0012\u0010/\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001a0\u0015H\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0018\u0010\b\u001a\u00060\tj\u0002`\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u0002\u0004\n\u0002\b\u0019¨\u00060"}, d2 = {"AuthenticateProvidersKey", "Lio/ktor/util/AttributeKey;", "Lio/ktor/server/auth/AuthenticateProvidersRegistration;", "AuthenticationInterceptors", "Lio/ktor/server/application/RouteScopedPlugin;", "Lio/ktor/server/auth/RouteAuthenticationConfig;", "getAuthenticationInterceptors", "()Lio/ktor/server/application/RouteScopedPlugin;", "LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLOGGER", "()Lorg/slf4j/Logger;", "authenticate", "Lio/ktor/server/routing/Route;", "configurations", "", "", "strategy", "Lio/ktor/server/auth/AuthenticationStrategy;", "build", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/routing/Route;[Ljava/lang/String;Lio/ktor/server/auth/AuthenticationStrategy;Lkotlin/jvm/functions/Function1;)Lio/ktor/server/routing/Route;", "optional", "", "(Lio/ktor/server/routing/Route;[Ljava/lang/String;ZLkotlin/jvm/functions/Function1;)Lio/ktor/server/routing/Route;", "executeChallenges", "Lio/ktor/server/auth/AuthenticationContext;", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/auth/AuthenticationContext;Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "challenges", "", "Lkotlin/Function3;", "Lio/ktor/server/auth/AuthenticationProcedureChallenge;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/server/auth/AuthenticationContext;Ljava/util/List;Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findProvider", "Lio/ktor/server/auth/AuthenticationProvider;", "Lio/ktor/server/auth/AuthenticationConfig;", "configurationName", "findProviders", "", "", "filter", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationInterceptors.kt */
public final class AuthenticationInterceptorsKt {
    /* access modifiers changed from: private */
    public static final AttributeKey<AuthenticateProvidersRegistration> AuthenticateProvidersKey = new AttributeKey<>("AuthenticateProviderNamesKey");
    private static final RouteScopedPlugin<RouteAuthenticationConfig> AuthenticationInterceptors = CreatePluginUtilsKt.createRouteScopedPlugin("AuthenticationInterceptors", AuthenticationInterceptorsKt$AuthenticationInterceptors$1.INSTANCE, AuthenticationInterceptorsKt$AuthenticationInterceptors$2.INSTANCE);
    private static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.server.auth.Authentication");

    public static final Logger getLOGGER() {
        return LOGGER;
    }

    public static final RouteScopedPlugin<RouteAuthenticationConfig> getAuthenticationInterceptors() {
        return AuthenticationInterceptors;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: io.ktor.server.application.ApplicationCall} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object executeChallenges(io.ktor.server.auth.AuthenticationContext r8, io.ktor.server.application.ApplicationCall r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$1 r0 = (io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$1 r0 = new io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0059
            if (r2 == r5) goto L_0x004c
            if (r2 == r4) goto L_0x003d
            if (r2 != r3) goto L_0x0035
            java.lang.Object r8 = r0.L$0
            io.ktor.server.auth.AuthenticationContext r8 = (io.ktor.server.auth.AuthenticationContext) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x012a
        L_0x0035:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x003d:
            java.lang.Object r8 = r0.L$1
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8
            java.lang.Object r9 = r0.L$0
            io.ktor.server.auth.AuthenticationContext r9 = (io.ktor.server.auth.AuthenticationContext) r9
            kotlin.ResultKt.throwOnFailure(r10)
            r7 = r9
            r9 = r8
            r8 = r7
            goto L_0x0091
        L_0x004c:
            java.lang.Object r8 = r0.L$1
            r9 = r8
            io.ktor.server.application.ApplicationCall r9 = (io.ktor.server.application.ApplicationCall) r9
            java.lang.Object r8 = r0.L$0
            io.ktor.server.auth.AuthenticationContext r8 = (io.ktor.server.auth.AuthenticationContext) r8
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0071
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.server.auth.AuthenticationProcedureChallenge r10 = r8.getChallenge()
            java.util.List r10 = r10.getChallenges$ktor_server_auth()
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r5
            java.lang.Object r10 = executeChallenges(r8, r10, r9, r0)
            if (r10 != r1) goto L_0x0071
            return r1
        L_0x0071:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x007c
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x007c:
            io.ktor.server.auth.AuthenticationProcedureChallenge r10 = r8.getChallenge()
            java.util.List r10 = r10.getErrorChallenges$ktor_server_auth()
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r10 = executeChallenges(r8, r10, r9, r0)
            if (r10 != r1) goto L_0x0091
            return r1
        L_0x0091:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x009c
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x009c:
            java.util.List r10 = r8.getAllErrors()
            java.util.Iterator r10 = r10.iterator()
        L_0x00a4:
            boolean r2 = r10.hasNext()
            if (r2 == 0) goto L_0x0134
            java.lang.Object r2 = r10.next()
            io.ktor.server.auth.AuthenticationFailedCause$Error r2 = (io.ktor.server.auth.AuthenticationFailedCause.Error) r2
            io.ktor.server.auth.AuthenticationProcedureChallenge r4 = r8.getChallenge()
            boolean r4 = r4.getCompleted()
            if (r4 != 0) goto L_0x00a4
            org.slf4j.Logger r10 = LOGGER
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Authentication failed for "
            r4.<init>(r5)
            io.ktor.server.request.ApplicationRequest r5 = r9.getRequest()
            java.lang.String r5 = io.ktor.server.request.ApplicationRequestPropertiesKt.getUri(r5)
            r4.append(r5)
            java.lang.String r5 = " with error "
            r4.append(r5)
            java.lang.String r2 = r2.getMessage()
            r4.append(r2)
            java.lang.String r2 = r4.toString()
            r10.trace(r2)
            boolean r10 = io.ktor.server.application.ApplicationCallKt.isHandled(r9)
            if (r10 != 0) goto L_0x012a
            io.ktor.server.auth.UnauthorizedResponse r10 = new io.ktor.server.auth.UnauthorizedResponse
            r2 = 0
            io.ktor.http.auth.HttpAuthHeader[] r2 = new io.ktor.http.auth.HttpAuthHeader[r2]
            r10.<init>(r2)
            boolean r2 = r10 instanceof io.ktor.http.content.OutgoingContent
            if (r2 != 0) goto L_0x0112
            boolean r2 = r10 instanceof byte[]
            if (r2 != 0) goto L_0x0112
            io.ktor.server.response.ApplicationResponse r2 = r9.getResponse()
            java.lang.Class<io.ktor.server.auth.UnauthorizedResponse> r4 = io.ktor.server.auth.UnauthorizedResponse.class
            kotlin.reflect.KType r4 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r4)
            java.lang.reflect.Type r5 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r4)
            java.lang.Class<io.ktor.server.auth.UnauthorizedResponse> r6 = io.ktor.server.auth.UnauthorizedResponse.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            io.ktor.util.reflect.TypeInfo r4 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r5, r6, r4)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r2, r4)
        L_0x0112:
            io.ktor.server.response.ApplicationResponse r2 = r9.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r2 = r2.getPipeline()
            java.lang.Object r10 = (java.lang.Object) r10
            r0.L$0 = r8
            r4 = 0
            r0.L$1 = r4
            r0.label = r3
            java.lang.Object r9 = r2.execute(r9, r10, r0)
            if (r9 != r1) goto L_0x012a
            return r1
        L_0x012a:
            io.ktor.server.auth.AuthenticationProcedureChallenge r8 = r8.getChallenge()
            r8.complete()
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x0134:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.AuthenticationInterceptorsKt.executeChallenges(io.ktor.server.auth.AuthenticationContext, io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object executeChallenges(io.ktor.server.auth.AuthenticationContext r7, java.util.List<? extends kotlin.jvm.functions.Function3<? super io.ktor.server.auth.AuthenticationProcedureChallenge, ? super io.ktor.server.application.ApplicationCall, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object>> r8, io.ktor.server.application.ApplicationCall r9, kotlin.coroutines.Continuation<? super java.lang.Boolean> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$2
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$2 r0 = (io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$2) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$2 r0 = new io.ktor.server.auth.AuthenticationInterceptorsKt$executeChallenges$2
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004a
            if (r2 == r5) goto L_0x0037
            if (r2 != r4) goto L_0x002f
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00ce
        L_0x002f:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0037:
            java.lang.Object r7 = r0.L$2
            java.util.Iterator r7 = (java.util.Iterator) r7
            java.lang.Object r8 = r0.L$1
            io.ktor.server.application.ApplicationCall r8 = (io.ktor.server.application.ApplicationCall) r8
            java.lang.Object r9 = r0.L$0
            io.ktor.server.auth.AuthenticationContext r9 = (io.ktor.server.auth.AuthenticationContext) r9
            kotlin.ResultKt.throwOnFailure(r10)
            r6 = r9
            r9 = r8
            r8 = r6
            goto L_0x0073
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r10)
            java.util.Iterator r8 = r8.iterator()
            r6 = r8
            r8 = r7
            r7 = r6
        L_0x0054:
            boolean r10 = r7.hasNext()
            if (r10 == 0) goto L_0x00d3
            java.lang.Object r10 = r7.next()
            kotlin.jvm.functions.Function3 r10 = (kotlin.jvm.functions.Function3) r10
            io.ktor.server.auth.AuthenticationProcedureChallenge r2 = r8.getChallenge()
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r7
            r0.label = r5
            java.lang.Object r10 = r10.invoke(r2, r9, r0)
            if (r10 != r1) goto L_0x0073
            return r1
        L_0x0073:
            io.ktor.server.auth.AuthenticationProcedureChallenge r10 = r8.getChallenge()
            boolean r10 = r10.getCompleted()
            if (r10 == 0) goto L_0x0054
            boolean r7 = io.ktor.server.application.ApplicationCallKt.isHandled(r9)
            if (r7 != 0) goto L_0x00ce
            org.slf4j.Logger r7 = LOGGER
            java.lang.String r8 = "Responding unauthorized because call is not handled."
            r7.trace(r8)
            io.ktor.server.auth.UnauthorizedResponse r7 = new io.ktor.server.auth.UnauthorizedResponse
            io.ktor.http.auth.HttpAuthHeader[] r8 = new io.ktor.http.auth.HttpAuthHeader[r3]
            r7.<init>(r8)
            boolean r8 = r7 instanceof io.ktor.http.content.OutgoingContent
            if (r8 != 0) goto L_0x00b4
            boolean r8 = r7 instanceof byte[]
            if (r8 != 0) goto L_0x00b4
            io.ktor.server.response.ApplicationResponse r8 = r9.getResponse()
            java.lang.Class<io.ktor.server.auth.UnauthorizedResponse> r10 = io.ktor.server.auth.UnauthorizedResponse.class
            kotlin.reflect.KType r10 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r10)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r10)
            java.lang.Class<io.ktor.server.auth.UnauthorizedResponse> r3 = io.ktor.server.auth.UnauthorizedResponse.class
            kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
            io.ktor.util.reflect.TypeInfo r10 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r3, r10)
            io.ktor.server.response.ResponseTypeKt.setResponseType(r8, r10)
        L_0x00b4:
            io.ktor.server.response.ApplicationResponse r8 = r9.getResponse()
            io.ktor.server.response.ApplicationSendPipeline r8 = r8.getPipeline()
            java.lang.Object r7 = (java.lang.Object) r7
            r10 = 0
            r0.L$0 = r10
            r0.L$1 = r10
            r0.L$2 = r10
            r0.label = r4
            java.lang.Object r7 = r8.execute(r9, r7, r0)
            if (r7 != r1) goto L_0x00ce
            return r1
        L_0x00ce:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
            return r7
        L_0x00d3:
            java.lang.Boolean r7 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.AuthenticationInterceptorsKt.executeChallenges(io.ktor.server.auth.AuthenticationContext, java.util.List, io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Set<AuthenticationProvider> findProviders(AuthenticationConfig authenticationConfig, Collection<AuthenticateProvidersRegistration> collection, Function1<? super AuthenticationStrategy, Boolean> function1) {
        Collection arrayList = new ArrayList();
        for (Object next : collection) {
            if (function1.invoke(((AuthenticateProvidersRegistration) next).getStrategy()).booleanValue()) {
                arrayList.add(next);
            }
        }
        Collection arrayList2 = new ArrayList();
        for (AuthenticateProvidersRegistration names : (List) arrayList) {
            Iterable<String> names2 = names.getNames();
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(names2, 10));
            for (String findProvider : names2) {
                arrayList3.add(findProvider(authenticationConfig, findProvider));
            }
            CollectionsKt.addAll(arrayList2, (List) arrayList3);
        }
        return CollectionsKt.toSet((List) arrayList2);
    }

    private static final AuthenticationProvider findProvider(AuthenticationConfig authenticationConfig, String str) {
        String str2;
        AuthenticationProvider authenticationProvider = authenticationConfig.getProviders$ktor_server_auth().get(str);
        if (authenticationProvider != null) {
            return authenticationProvider;
        }
        StringBuilder sb = new StringBuilder();
        if (str == null) {
            str2 = "Default authentication configuration was not found. ";
        } else {
            str2 = "Authentication configuration with the name " + str + " was not found. ";
        }
        sb.append(str2);
        sb.append("Make sure that you install Authentication plugin before you use it in Routing");
        throw new IllegalArgumentException(sb.toString());
    }

    public static /* synthetic */ Route authenticate$default(Route route, String[] strArr, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            strArr = new String[]{null};
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return authenticate(route, strArr, z, (Function1<? super Route, Unit>) function1);
    }

    public static final Route authenticate(Route route, String[] strArr, boolean z, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "configurations");
        Intrinsics.checkNotNullParameter(function1, "build");
        return authenticate(route, (String[]) Arrays.copyOf(strArr, strArr.length), z ? AuthenticationStrategy.Optional : AuthenticationStrategy.FirstSuccessful, function1);
    }

    public static /* synthetic */ Route authenticate$default(Route route, String[] strArr, AuthenticationStrategy authenticationStrategy, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            strArr = new String[]{null};
        }
        return authenticate(route, strArr, authenticationStrategy, (Function1<? super Route, Unit>) function1);
    }

    public static final Route authenticate(Route route, String[] strArr, AuthenticationStrategy authenticationStrategy, Function1<? super Route, Unit> function1) {
        Intrinsics.checkNotNullParameter(route, "<this>");
        Intrinsics.checkNotNullParameter(strArr, "configurations");
        Intrinsics.checkNotNullParameter(authenticationStrategy, "strategy");
        Intrinsics.checkNotNullParameter(function1, "build");
        if (!(strArr.length == 0)) {
            List list = CollectionsKt.toList(ArraysKt.distinct((T[]) strArr));
            Route createChild = route.createChild(new AuthenticationRouteSelector(list));
            createChild.getAttributes().put(AuthenticateProvidersKey, new AuthenticateProvidersRegistration(list, authenticationStrategy));
            ApplicationPluginKt.install(createChild, AuthenticationInterceptors, new AuthenticationInterceptorsKt$authenticate$2(CollectionsKt.reversed(SequencesKt.toList(SequencesKt.mapNotNull(SequencesKt.generateSequence(createChild, AuthenticationInterceptorsKt$authenticate$allConfigurations$1.INSTANCE), AuthenticationInterceptorsKt$authenticate$allConfigurations$2.INSTANCE)))));
            function1.invoke(createChild);
            return createChild;
        }
        throw new IllegalArgumentException("At least one configuration name or null for default need to be provided".toString());
    }
}
