package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponseFunctionsKt;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineDispatcher;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u001a\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0000\u001ak\u0010\u0006\u001a\u00020\u0007*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0019\u0010\u000e\u001a\u0015\u0012\u0004\u0012\u00020\t\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u000f¢\u0006\u0002\b\u00112\u001d\u0010\u0012\u001a\u0019\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00040\u0013¢\u0006\u0002\b\u0011H@ø\u0001\u0000¢\u0006\u0002\u0010\u0014\u001a9\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH@ø\u0001\u0000¢\u0006\u0002\u0010\u001f\u001a'\u0010 \u001a\u00020\u0007*\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001d\u001a\u00020\u001eH@ø\u0001\u0000¢\u0006\u0002\u0010!\u001a\u0001\u0010\"\u001a\u00020\u0007*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\u0019\b\u0002\u0010%\u001a\u0013\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\u0002\b\u00112\"\u0010'\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070)\u0012\u0006\u0012\u0004\u0018\u00010*0\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010+\u001am\u0010\"\u001a\u00020\u0007*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u00042\"\u0010'\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020(\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070)\u0012\u0006\u0012\u0004\u0018\u00010*0\u0013H@ø\u0001\u0000¢\u0006\u0002\u0010,\u001a\u001d\u0010-\u001a\u00020\u0007*\u00020\t2\u0006\u0010.\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u0010/\u001aA\u00100\u001a\u00020\u0007*\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u00102\u0006\u0010#\u001a\u00020\u0004H@ø\u0001\u0000¢\u0006\u0002\u00101\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u00062"}, d2 = {"Logger", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "appendUrlParameters", "", "parameters", "oauth", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "client", "Lio/ktor/client/HttpClient;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "providerLookup", "Lkotlin/Function1;", "Lio/ktor/server/auth/OAuthServerSettings;", "Lkotlin/ExtensionFunctionType;", "urlProvider", "Lkotlin/Function2;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauth1RequestToken", "Lio/ktor/server/auth/AuthenticationFailedCause$InvalidCredentials;", "Lio/ktor/server/auth/OAuthAuthenticationProvider;", "authProviderName", "provider", "Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;", "token", "Lio/ktor/server/auth/OAuthCallback$TokenPair;", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/OAuthAuthenticationProvider;Ljava/lang/String;Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;Lio/ktor/server/auth/OAuthCallback$TokenPair;Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauth1a", "(Lio/ktor/server/auth/OAuthAuthenticationProvider;Ljava/lang/String;Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauthHandleCallback", "callbackUrl", "loginPageUrl", "configure", "Lio/ktor/client/request/HttpRequestBuilder;", "block", "Lio/ktor/server/auth/OAuthAccessTokenResponse;", "Lkotlin/coroutines/Continuation;", "", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lkotlinx/coroutines/CoroutineDispatcher;Lio/ktor/server/auth/OAuthServerSettings;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lkotlinx/coroutines/CoroutineDispatcher;Lio/ktor/server/auth/OAuthServerSettings;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauthHandleFail", "redirectUrl", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauthRespondRedirect", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lkotlinx/coroutines/CoroutineDispatcher;Lio/ktor/server/auth/OAuthServerSettings;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth.kt */
public final class OAuthKt {
    /* access modifiers changed from: private */
    public static final Logger Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.auth.oauth");

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: io.ktor.server.auth.AuthenticationContext} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth1a(io.ktor.server.auth.OAuthAuthenticationProvider r8, java.lang.String r9, io.ktor.server.auth.AuthenticationContext r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            boolean r0 = r11 instanceof io.ktor.server.auth.OAuthKt$oauth1a$1
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.server.auth.OAuthKt$oauth1a$1 r0 = (io.ktor.server.auth.OAuthKt$oauth1a$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.OAuthKt$oauth1a$1 r0 = new io.ktor.server.auth.OAuthKt$oauth1a$1
            r0.<init>(r11)
        L_0x0019:
            r6 = r0
            java.lang.Object r11 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            if (r1 == 0) goto L_0x0042
            if (r1 != r2) goto L_0x003a
            java.lang.Object r8 = r6.L$2
            io.ktor.server.auth.OAuthServerSettings r8 = (io.ktor.server.auth.OAuthServerSettings) r8
            java.lang.Object r9 = r6.L$1
            r10 = r9
            io.ktor.server.auth.AuthenticationContext r10 = (io.ktor.server.auth.AuthenticationContext) r10
            java.lang.Object r9 = r6.L$0
            io.ktor.server.auth.OAuthAuthenticationProvider r9 = (io.ktor.server.auth.OAuthAuthenticationProvider) r9
            kotlin.ResultKt.throwOnFailure(r11)
            r7 = r8
            r8 = r9
            goto L_0x007b
        L_0x003a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.server.application.ApplicationCall r11 = r10.getCall()
            kotlin.jvm.functions.Function1 r1 = r8.getProviderLookup$ktor_server_auth()
            java.lang.Object r1 = r1.invoke(r11)
            r7 = r1
            io.ktor.server.auth.OAuthServerSettings r7 = (io.ktor.server.auth.OAuthServerSettings) r7
            boolean r1 = r7 instanceof io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings
            if (r1 != 0) goto L_0x005b
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x005b:
            io.ktor.server.auth.OAuthCallback$TokenPair r4 = io.ktor.server.auth.OAuth1aKt.oauth1aHandleCallback(r11)
            if (r4 != 0) goto L_0x0066
            io.ktor.server.auth.AuthenticationFailedCause$NoCredentials r9 = io.ktor.server.auth.AuthenticationFailedCause.NoCredentials.INSTANCE
            io.ktor.server.auth.AuthenticationFailedCause r9 = (io.ktor.server.auth.AuthenticationFailedCause) r9
            goto L_0x007e
        L_0x0066:
            r3 = r7
            io.ktor.server.auth.OAuthServerSettings$OAuth1aServerSettings r3 = (io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings) r3
            r6.L$0 = r8
            r6.L$1 = r10
            r6.L$2 = r7
            r6.label = r2
            r1 = r8
            r2 = r9
            r5 = r10
            java.lang.Object r11 = oauth1RequestToken(r1, r2, r3, r4, r5, r6)
            if (r11 != r0) goto L_0x007b
            return r0
        L_0x007b:
            r9 = r11
            io.ktor.server.auth.AuthenticationFailedCause r9 = (io.ktor.server.auth.AuthenticationFailedCause) r9
        L_0x007e:
            if (r9 == 0) goto L_0x008f
            java.lang.Object r11 = io.ktor.server.auth.OAuthProcedureKt.getOAuthKey()
            io.ktor.server.auth.OAuthKt$oauth1a$2 r0 = new io.ktor.server.auth.OAuthKt$oauth1a$2
            r1 = 0
            r0.<init>(r8, r7, r10, r1)
            kotlin.jvm.functions.Function3 r0 = (kotlin.jvm.functions.Function3) r0
            r10.challenge(r11, r9, r0)
        L_0x008f:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthKt.oauth1a(io.ktor.server.auth.OAuthAuthenticationProvider, java.lang.String, io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r14v3, resolved type: io.ktor.server.auth.AuthenticationContext} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v5, resolved type: java.lang.String} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth1RequestToken(io.ktor.server.auth.OAuthAuthenticationProvider r10, java.lang.String r11, io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings r12, io.ktor.server.auth.OAuthCallback.TokenPair r13, io.ktor.server.auth.AuthenticationContext r14, kotlin.coroutines.Continuation<? super io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials> r15) {
        /*
            boolean r0 = r15 instanceof io.ktor.server.auth.OAuthKt$oauth1RequestToken$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.server.auth.OAuthKt$oauth1RequestToken$1 r0 = (io.ktor.server.auth.OAuthKt$oauth1RequestToken$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.OAuthKt$oauth1RequestToken$1 r0 = new io.ktor.server.auth.OAuthKt$oauth1RequestToken$1
            r0.<init>(r15)
        L_0x0019:
            r6 = r0
            java.lang.Object r15 = r6.result
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r9 = 0
            r2 = 1
            if (r1 == 0) goto L_0x003e
            if (r1 != r2) goto L_0x0036
            java.lang.Object r10 = r6.L$1
            r14 = r10
            io.ktor.server.auth.AuthenticationContext r14 = (io.ktor.server.auth.AuthenticationContext) r14
            java.lang.Object r10 = r6.L$0
            r11 = r10
            java.lang.String r11 = (java.lang.String) r11
            kotlin.ResultKt.throwOnFailure(r15)     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            goto L_0x0059
        L_0x0036:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x003e:
            kotlin.ResultKt.throwOnFailure(r15)
            io.ktor.client.HttpClient r1 = r10.getClient$ktor_server_auth()     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            r6.L$0 = r11     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            r6.L$1 = r14     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            r6.label = r2     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            r4 = 0
            r5 = 0
            r7 = 24
            r8 = 0
            r2 = r12
            r3 = r13
            java.lang.Object r15 = io.ktor.server.auth.OAuth1aKt.requestOAuth1aAccessToken$default(r1, r2, r3, r4, r5, r6, r7, r8)     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            if (r15 != r0) goto L_0x0059
            return r0
        L_0x0059:
            io.ktor.server.auth.OAuthAccessTokenResponse$OAuth1a r15 = (io.ktor.server.auth.OAuthAccessTokenResponse.OAuth1a) r15     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            io.ktor.server.auth.Principal r15 = (io.ktor.server.auth.Principal) r15     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            r14.principal((java.lang.String) r11, (io.ktor.server.auth.Principal) r15)     // Catch:{ MissingTokenException -> 0x0072, all -> 0x0061 }
            goto L_0x0074
        L_0x0061:
            java.lang.Object r10 = io.ktor.server.auth.OAuthProcedureKt.getOAuthKey()
            io.ktor.server.auth.AuthenticationFailedCause$Error r11 = new io.ktor.server.auth.AuthenticationFailedCause$Error
            java.lang.String r12 = "OAuth1a failed to get OAuth1 access token"
            r11.<init>(r12)
            io.ktor.server.auth.AuthenticationFailedCause r11 = (io.ktor.server.auth.AuthenticationFailedCause) r11
            r14.error(r10, r11)
            goto L_0x0074
        L_0x0072:
            io.ktor.server.auth.AuthenticationFailedCause$InvalidCredentials r9 = io.ktor.server.auth.AuthenticationFailedCause.InvalidCredentials.INSTANCE
        L_0x0074:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthKt.oauth1RequestToken(io.ktor.server.auth.OAuthAuthenticationProvider, java.lang.String, io.ktor.server.auth.OAuthServerSettings$OAuth1aServerSettings, io.ktor.server.auth.OAuthCallback$TokenPair, io.ktor.server.auth.AuthenticationContext, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r13v2, resolved type: kotlin.jvm.functions.Function2<? super io.ktor.server.application.ApplicationCall, ? super io.ktor.server.auth.OAuthServerSettings, java.lang.String>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: kotlin.jvm.functions.Function1<? super io.ktor.server.application.ApplicationCall, ? extends io.ktor.server.auth.OAuthServerSettings>} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v2, resolved type: kotlinx.coroutines.CoroutineDispatcher} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v2, resolved type: io.ktor.client.HttpClient} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0052  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0081 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Install and configure OAuth instead.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth(io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r9, io.ktor.client.HttpClient r10, kotlinx.coroutines.CoroutineDispatcher r11, kotlin.jvm.functions.Function1<? super io.ktor.server.application.ApplicationCall, ? extends io.ktor.server.auth.OAuthServerSettings> r12, kotlin.jvm.functions.Function2<? super io.ktor.server.application.ApplicationCall, ? super io.ktor.server.auth.OAuthServerSettings, java.lang.String> r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            boolean r0 = r14 instanceof io.ktor.server.auth.OAuthKt$oauth$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            io.ktor.server.auth.OAuthKt$oauth$1 r0 = (io.ktor.server.auth.OAuthKt$oauth$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.OAuthKt$oauth$1 r0 = new io.ktor.server.auth.OAuthKt$oauth$1
            r0.<init>(r14)
        L_0x0019:
            r14 = r0
            java.lang.Object r0 = r14.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r8 = 2
            r2 = 1
            if (r1 == 0) goto L_0x0052
            if (r1 == r2) goto L_0x0036
            if (r1 != r8) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0082
        L_0x002e:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x0036:
            java.lang.Object r9 = r14.L$4
            r13 = r9
            kotlin.jvm.functions.Function2 r13 = (kotlin.jvm.functions.Function2) r13
            java.lang.Object r9 = r14.L$3
            r12 = r9
            kotlin.jvm.functions.Function1 r12 = (kotlin.jvm.functions.Function1) r12
            java.lang.Object r9 = r14.L$2
            r11 = r9
            kotlinx.coroutines.CoroutineDispatcher r11 = (kotlinx.coroutines.CoroutineDispatcher) r11
            java.lang.Object r9 = r14.L$1
            r10 = r9
            io.ktor.client.HttpClient r10 = (io.ktor.client.HttpClient) r10
            java.lang.Object r9 = r14.L$0
            io.ktor.util.pipeline.PipelineContext r9 = (io.ktor.util.pipeline.PipelineContext) r9
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x006e
        L_0x0052:
            kotlin.ResultKt.throwOnFailure(r0)
            r14.L$0 = r9
            r14.L$1 = r10
            r14.L$2 = r11
            r14.L$3 = r12
            r14.L$4 = r13
            r14.label = r2
            r1 = r9
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            java.lang.Object r0 = io.ktor.server.auth.OAuth1aKt.oauth1a(r1, r2, r3, r4, r5, r6)
            if (r0 != r7) goto L_0x006e
            return r7
        L_0x006e:
            r0 = 0
            r14.L$0 = r0
            r14.L$1 = r0
            r14.L$2 = r0
            r14.L$3 = r0
            r14.L$4 = r0
            r14.label = r8
            java.lang.Object r9 = io.ktor.server.auth.OAuth2Kt.oauth2(r9, r10, r11, r12, r13, r14)
            if (r9 != r7) goto L_0x0082
            return r7
        L_0x0082:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthKt.oauth(io.ktor.util.pipeline.PipelineContext, io.ktor.client.HttpClient, kotlinx.coroutines.CoroutineDispatcher, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00cb A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Install and configure OAuth instead.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauthRespondRedirect(io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r14, io.ktor.client.HttpClient r15, kotlinx.coroutines.CoroutineDispatcher r16, io.ktor.server.auth.OAuthServerSettings r17, java.lang.String r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            r2 = r17
            r0 = r19
            boolean r1 = r0 instanceof io.ktor.server.auth.OAuthKt$oauthRespondRedirect$1
            if (r1 == 0) goto L_0x0018
            r1 = r0
            io.ktor.server.auth.OAuthKt$oauthRespondRedirect$1 r1 = (io.ktor.server.auth.OAuthKt$oauthRespondRedirect$1) r1
            int r3 = r1.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r0 = r1.label
            int r0 = r0 - r4
            r1.label = r0
            goto L_0x001d
        L_0x0018:
            io.ktor.server.auth.OAuthKt$oauthRespondRedirect$1 r1 = new io.ktor.server.auth.OAuthKt$oauthRespondRedirect$1
            r1.<init>(r0)
        L_0x001d:
            r9 = r1
            java.lang.Object r0 = r9.result
            java.lang.Object r12 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r3 = 3
            r4 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0059
            if (r1 == r6) goto L_0x0055
            if (r1 == r4) goto L_0x003e
            if (r1 != r3) goto L_0x0036
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00cc
        L_0x0036:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003e:
            java.lang.Object r1 = r9.L$3
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r9.L$2
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r2 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r2
            java.lang.Object r4 = r9.L$1
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            java.lang.Object r5 = r9.L$0
            io.ktor.server.auth.OAuthServerSettings r5 = (io.ktor.server.auth.OAuthServerSettings) r5
            kotlin.ResultKt.throwOnFailure(r0)
            r6 = r1
            r1 = r2
            r2 = r5
            goto L_0x00a6
        L_0x0055:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x007c
        L_0x0059:
            kotlin.ResultKt.throwOnFailure(r0)
            boolean r0 = r2 instanceof io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings
            if (r0 == 0) goto L_0x007f
            r7 = r16
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            io.ktor.server.auth.OAuthKt$oauthRespondRedirect$2 r8 = new io.ktor.server.auth.OAuthKt$oauthRespondRedirect$2
            r5 = 0
            r0 = r8
            r1 = r15
            r2 = r17
            r3 = r18
            r4 = r14
            r0.<init>(r1, r2, r3, r4, r5)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9.label = r6
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r9)
            if (r0 != r12) goto L_0x007c
            return r12
        L_0x007c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x007f:
            boolean r0 = r2 instanceof io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings
            if (r0 == 0) goto L_0x00cf
            java.lang.Object r0 = r14.getContext()
            io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
            r1 = r2
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r1 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r1
            io.ktor.util.NonceManager r5 = r1.getNonceManager()
            r9.L$0 = r2
            r9.L$1 = r0
            r9.L$2 = r1
            r6 = r18
            r9.L$3 = r6
            r9.label = r4
            java.lang.Object r4 = r5.newNonce(r9)
            if (r4 != r12) goto L_0x00a3
            return r12
        L_0x00a3:
            r13 = r4
            r4 = r0
            r0 = r13
        L_0x00a6:
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r2 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r2
            java.util.List r7 = r2.getDefaultScopes()
            kotlin.jvm.functions.Function1 r8 = r2.getAuthorizeUrlInterceptor()
            r0 = 0
            r9.L$0 = r0
            r9.L$1 = r0
            r9.L$2 = r0
            r9.L$3 = r0
            r9.label = r3
            r10 = 8
            r11 = 0
            r2 = r4
            r3 = r1
            r4 = r6
            r6 = r0
            java.lang.Object r0 = io.ktor.server.auth.OAuth2Kt.redirectAuthenticateOAuth2$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r0 != r12) goto L_0x00cc
            return r12
        L_0x00cc:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00cf:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthKt.oauthRespondRedirect(io.ktor.util.pipeline.PipelineContext, io.ktor.client.HttpClient, kotlinx.coroutines.CoroutineDispatcher, io.ktor.server.auth.OAuthServerSettings, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Install and configure OAuth instead.")
    public static final Object oauthHandleCallback(PipelineContext<Unit, ApplicationCall> pipelineContext, HttpClient httpClient, CoroutineDispatcher coroutineDispatcher, OAuthServerSettings oAuthServerSettings, String str, String str2, Function2<? super OAuthAccessTokenResponse, ? super Continuation<? super Unit>, ? extends Object> function2, Continuation<? super Unit> continuation) {
        Object oauthHandleCallback = oauthHandleCallback(pipelineContext, httpClient, coroutineDispatcher, oAuthServerSettings, str, str2, OAuthKt$oauthHandleCallback$2.INSTANCE, function2, continuation);
        return oauthHandleCallback == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? oauthHandleCallback : Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0113  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    @kotlin.Deprecated(level = kotlin.DeprecationLevel.ERROR, message = "Specifying an extra configuration function will be deprecated. Please provide it via OAuthServerSettings.")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauthHandleCallback(io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r21, io.ktor.client.HttpClient r22, kotlinx.coroutines.CoroutineDispatcher r23, io.ktor.server.auth.OAuthServerSettings r24, java.lang.String r25, java.lang.String r26, kotlin.jvm.functions.Function1<? super io.ktor.client.request.HttpRequestBuilder, kotlin.Unit> r27, kotlin.jvm.functions.Function2<? super io.ktor.server.auth.OAuthAccessTokenResponse, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            r0 = r23
            r2 = r24
            r1 = r29
            boolean r3 = r1 instanceof io.ktor.server.auth.OAuthKt$oauthHandleCallback$3
            if (r3 == 0) goto L_0x001a
            r3 = r1
            io.ktor.server.auth.OAuthKt$oauthHandleCallback$3 r3 = (io.ktor.server.auth.OAuthKt$oauthHandleCallback$3) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r1 = r3.label
            int r1 = r1 - r5
            r3.label = r1
            goto L_0x001f
        L_0x001a:
            io.ktor.server.auth.OAuthKt$oauthHandleCallback$3 r3 = new io.ktor.server.auth.OAuthKt$oauthHandleCallback$3
            r3.<init>(r1)
        L_0x001f:
            r8 = r3
            java.lang.Object r1 = r8.result
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r8.label
            r4 = 5
            r5 = 4
            r6 = 3
            r9 = 2
            r7 = 1
            if (r3 == 0) goto L_0x0083
            if (r3 == r7) goto L_0x007f
            if (r3 == r9) goto L_0x007b
            if (r3 == r6) goto L_0x004b
            if (r3 == r5) goto L_0x0046
            if (r3 != r4) goto L_0x003e
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0169
        L_0x003e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0141
        L_0x004b:
            java.lang.Object r0 = r8.L$7
            kotlin.jvm.functions.Function2 r0 = (kotlin.jvm.functions.Function2) r0
            java.lang.Object r2 = r8.L$6
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            java.lang.Object r3 = r8.L$5
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r6 = r8.L$4
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r8.L$3
            io.ktor.server.auth.OAuthServerSettings r7 = (io.ktor.server.auth.OAuthServerSettings) r7
            java.lang.Object r9 = r8.L$2
            kotlinx.coroutines.CoroutineDispatcher r9 = (kotlinx.coroutines.CoroutineDispatcher) r9
            java.lang.Object r11 = r8.L$1
            io.ktor.client.HttpClient r11 = (io.ktor.client.HttpClient) r11
            java.lang.Object r12 = r8.L$0
            io.ktor.util.pipeline.PipelineContext r12 = (io.ktor.util.pipeline.PipelineContext) r12
            kotlin.ResultKt.throwOnFailure(r1)
            r17 = r0
            r16 = r2
            r19 = r3
            r14 = r6
            r13 = r7
            r18 = r12
            r12 = r11
            goto L_0x010d
        L_0x007b:
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00cf
        L_0x007f:
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00ad
        L_0x0083:
            kotlin.ResultKt.throwOnFailure(r1)
            boolean r1 = r2 instanceof io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings
            if (r1 == 0) goto L_0x00d2
            java.lang.Object r1 = r21.getContext()
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            io.ktor.server.auth.OAuthCallback$TokenPair r3 = io.ktor.server.auth.OAuth1aKt.oauth1aHandleCallback(r1)
            if (r3 != 0) goto L_0x00b0
            java.lang.Object r0 = r21.getContext()
            r4 = r0
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            r8.label = r7
            r6 = 0
            r0 = 2
            r9 = 0
            r5 = r26
            r7 = r8
            r8 = r0
            java.lang.Object r0 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondRedirect$default((io.ktor.server.application.ApplicationCall) r4, (java.lang.String) r5, (boolean) r6, (kotlin.coroutines.Continuation) r7, (int) r8, (java.lang.Object) r9)
            if (r0 != r10) goto L_0x00ad
            return r10
        L_0x00ad:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00b0:
            r11 = r0
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            io.ktor.server.auth.OAuthKt$oauthHandleCallback$5 r12 = new io.ktor.server.auth.OAuthKt$oauthHandleCallback$5
            r7 = 0
            r0 = r12
            r1 = r22
            r2 = r24
            r4 = r28
            r5 = r21
            r6 = r26
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            kotlin.jvm.functions.Function2 r12 = (kotlin.jvm.functions.Function2) r12
            r8.label = r9
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r11, r12, r8)
            if (r0 != r10) goto L_0x00cf
            return r10
        L_0x00cf:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x00d2:
            boolean r1 = r2 instanceof io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings
            if (r1 == 0) goto L_0x016c
            java.lang.Object r1 = r21.getContext()
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            r3 = r21
            r8.L$0 = r3
            r7 = r22
            r8.L$1 = r7
            r8.L$2 = r0
            r8.L$3 = r2
            r9 = r25
            r8.L$4 = r9
            r11 = r26
            r8.L$5 = r11
            r12 = r27
            r8.L$6 = r12
            r13 = r28
            r8.L$7 = r13
            r8.label = r6
            java.lang.Object r1 = io.ktor.server.auth.OAuth2Kt.oauth2HandleCallback(r1, r8)
            if (r1 != r10) goto L_0x0101
            return r10
        L_0x0101:
            r18 = r3
            r14 = r9
            r19 = r11
            r16 = r12
            r17 = r13
            r9 = r0
            r13 = r2
            r12 = r7
        L_0x010d:
            r15 = r1
            io.ktor.server.auth.OAuthCallback$TokenSingle r15 = (io.ktor.server.auth.OAuthCallback.TokenSingle) r15
            r0 = 0
            if (r15 != 0) goto L_0x0144
            java.lang.Object r1 = r18.getContext()
            io.ktor.server.application.ApplicationCall r1 = (io.ktor.server.application.ApplicationCall) r1
            r8.L$0 = r0
            r8.L$1 = r0
            r8.L$2 = r0
            r8.L$3 = r0
            r8.L$4 = r0
            r8.L$5 = r0
            r8.L$6 = r0
            r8.L$7 = r0
            r8.label = r5
            r0 = 0
            r2 = 2
            r3 = 0
            r21 = r1
            r22 = r19
            r23 = r0
            r24 = r8
            r25 = r2
            r26 = r3
            java.lang.Object r0 = io.ktor.server.response.ApplicationResponseFunctionsKt.respondRedirect$default((io.ktor.server.application.ApplicationCall) r21, (java.lang.String) r22, (boolean) r23, (kotlin.coroutines.Continuation) r24, (int) r25, (java.lang.Object) r26)
            if (r0 != r10) goto L_0x0141
            return r10
        L_0x0141:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0144:
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            io.ktor.server.auth.OAuthKt$oauthHandleCallback$6 r1 = new io.ktor.server.auth.OAuthKt$oauthHandleCallback$6
            r20 = 0
            r11 = r1
            r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r8.L$0 = r0
            r8.L$1 = r0
            r8.L$2 = r0
            r8.L$3 = r0
            r8.L$4 = r0
            r8.L$5 = r0
            r8.L$6 = r0
            r8.L$7 = r0
            r8.label = r4
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r9, r1, r8)
            if (r0 != r10) goto L_0x0169
            return r10
        L_0x0169:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x016c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthKt.oauthHandleCallback(io.ktor.util.pipeline.PipelineContext, io.ktor.client.HttpClient, kotlinx.coroutines.CoroutineDispatcher, io.ktor.server.auth.OAuthServerSettings, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final Object oauthHandleFail(ApplicationCall applicationCall, String str, Continuation<? super Unit> continuation) {
        Object respondRedirect$default = ApplicationResponseFunctionsKt.respondRedirect$default(applicationCall, str, false, (Continuation) continuation, 2, (Object) null);
        return respondRedirect$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondRedirect$default : Unit.INSTANCE;
    }

    public static final String appendUrlParameters(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        Intrinsics.checkNotNullParameter(str2, "parameters");
        String str3 = "";
        if (str2.length() != 0 && !StringsKt.endsWith$default(str, "?", false, 2, (Object) null)) {
            str3 = StringsKt.contains$default((CharSequence) str, (CharSequence) "?", false, 2, (Object) null) ? "&" : "?";
        }
        return str + str3 + str2;
    }

    public static /* synthetic */ Object oauthHandleCallback$default(PipelineContext pipelineContext, HttpClient httpClient, CoroutineDispatcher coroutineDispatcher, OAuthServerSettings oAuthServerSettings, String str, String str2, Function1 function1, Function2 function2, Continuation continuation, int i, Object obj) {
        Function1 function12;
        if ((i & 32) != 0) {
            function12 = OAuthKt$oauthHandleCallback$4.INSTANCE;
        } else {
            function12 = function1;
        }
        return oauthHandleCallback(pipelineContext, httpClient, coroutineDispatcher, oAuthServerSettings, str, str2, function12, function2, continuation);
    }
}
