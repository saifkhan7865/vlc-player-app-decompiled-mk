package io.ktor.server.auth;

import androidx.core.app.FrameMetricsAggregator;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentType;
import io.ktor.http.HttpMethod;
import io.ktor.http.HttpUrlEncodedKt;
import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLParserKt;
import io.ktor.http.URLProtocol;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.OAuth2Exception;
import io.ktor.server.auth.OAuthAccessTokenResponse;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import io.ktor.server.response.ApplicationResponseFunctionsKt;
import io.ktor.util.NonceManager;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.fusesource.jansi.AnsiRenderer;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000 \u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a´\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00062\b\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u001a\b\u0002\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00170\u00162\u0019\b\u0002\u0010\u0018\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019¢\u0006\u0002\b\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\u001e2\b\b\u0002\u0010\"\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010#\u001aN\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010&\u001a\u00020'2\u001b\b\u0002\u0010\u0018\u001a\u0015\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u0019¢\u0006\u0002\b\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010(\u001a\u0018\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\u0004H\u0002\u001a)\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020/2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010$\u001a\u00020%H@ø\u0001\u0000¢\u0006\u0002\u00100\u001ak\u00101\u001a\u00020\u001b*\u000e\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u000203022\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00104\u001a\u0002052\u0019\u00106\u001a\u0015\u0012\u0004\u0012\u000203\u0012\u0006\u0012\u0004\u0018\u0001070\u0019¢\u0006\u0002\b\u001c2\u001d\u00108\u001a\u0019\u0012\u0004\u0012\u000203\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u000609¢\u0006\u0002\b\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010:\u001a\u0017\u0010;\u001a\u0004\u0018\u00010'*\u000203H@ø\u0001\u0000¢\u0006\u0002\u0010<\u001ar\u0010=\u001a\u00020\u001b*\u0002032\u0006\u0010$\u001a\u00020%2\u0006\u0010>\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\u001a\b\u0002\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00170\u00162\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00060\u00162\u0017\u0010@\u001a\u0013\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u001b0\u0019¢\u0006\u0002\b\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010B\u001av\u0010=\u001a\u00020\u001b*\u0002032\u0006\u0010C\u001a\u00020\u00062\u0006\u0010>\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00062\f\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00060\u00162\u0018\u0010,\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00170\u00162\u0017\u0010@\u001a\u0013\u0012\u0004\u0012\u00020A\u0012\u0004\u0012\u00020\u001b0\u0019¢\u0006\u0002\b\u001cH@ø\u0001\u0000¢\u0006\u0002\u0010D\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006E"}, d2 = {"Logger", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "decodeContent", "Lio/ktor/http/Parameters;", "content", "", "contentType", "Lio/ktor/http/ContentType;", "oauth2RequestAccessToken", "Lio/ktor/server/auth/OAuthAccessTokenResponse$OAuth2;", "client", "Lio/ktor/client/HttpClient;", "method", "Lio/ktor/http/HttpMethod;", "usedRedirectUrl", "baseUrl", "clientId", "clientSecret", "state", "code", "extraParameters", "", "Lkotlin/Pair;", "configure", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "Lkotlin/ExtensionFunctionType;", "useBasicAuth", "", "nonceManager", "Lio/ktor/util/NonceManager;", "passParamsInURL", "grantType", "(Lio/ktor/client/HttpClient;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;ZLio/ktor/util/NonceManager;ZLjava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "settings", "Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;", "callbackResponse", "Lio/ktor/server/auth/OAuthCallback$TokenSingle;", "(Lio/ktor/client/HttpClient;Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;Ljava/lang/String;Lio/ktor/server/auth/OAuthCallback$TokenSingle;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "throwOAuthError", "", "errorCode", "parameters", "verifyWithOAuth2", "credential", "Lio/ktor/server/auth/UserPasswordCredential;", "(Lio/ktor/server/auth/UserPasswordCredential;Lio/ktor/client/HttpClient;Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauth2", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "providerLookup", "Lio/ktor/server/auth/OAuthServerSettings;", "urlProvider", "Lkotlin/Function2;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "oauth2HandleCallback", "(Lio/ktor/server/application/ApplicationCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "redirectAuthenticateOAuth2", "callbackRedirectUrl", "scopes", "interceptor", "Lio/ktor/http/URLBuilder;", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authenticateUrl", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth2.kt */
public final class OAuth2Kt {
    /* access modifiers changed from: private */
    public static final Logger Logger = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.auth.oauth");

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x010e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth2(io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r18, io.ktor.client.HttpClient r19, kotlinx.coroutines.CoroutineDispatcher r20, kotlin.jvm.functions.Function1<? super io.ktor.server.application.ApplicationCall, ? extends io.ktor.server.auth.OAuthServerSettings> r21, kotlin.jvm.functions.Function2<? super io.ktor.server.application.ApplicationCall, ? super io.ktor.server.auth.OAuthServerSettings, java.lang.String> r22, kotlin.coroutines.Continuation<? super kotlin.Unit> r23) {
        /*
            r0 = r23
            boolean r1 = r0 instanceof io.ktor.server.auth.OAuth2Kt$oauth2$1
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.server.auth.OAuth2Kt$oauth2$1 r1 = (io.ktor.server.auth.OAuth2Kt$oauth2$1) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.server.auth.OAuth2Kt$oauth2$1 r1 = new io.ktor.server.auth.OAuth2Kt$oauth2$1
            r1.<init>(r0)
        L_0x001b:
            r9 = r1
            java.lang.Object r0 = r9.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r9.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x0079
            if (r2 == r6) goto L_0x005c
            if (r2 == r5) goto L_0x0045
            if (r2 == r4) goto L_0x0040
            if (r2 != r3) goto L_0x0038
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x0134
        L_0x0038:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x010f
        L_0x0045:
            java.lang.Object r2 = r9.L$3
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r9.L$2
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r3 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r3
            java.lang.Object r5 = r9.L$1
            io.ktor.server.application.ApplicationCall r5 = (io.ktor.server.application.ApplicationCall) r5
            java.lang.Object r6 = r9.L$0
            io.ktor.server.auth.OAuthServerSettings r6 = (io.ktor.server.auth.OAuthServerSettings) r6
            kotlin.ResultKt.throwOnFailure(r0)
            r13 = r2
            r2 = r5
            goto L_0x00eb
        L_0x005c:
            java.lang.Object r2 = r9.L$4
            io.ktor.server.auth.OAuthServerSettings r2 = (io.ktor.server.auth.OAuthServerSettings) r2
            java.lang.Object r6 = r9.L$3
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            java.lang.Object r8 = r9.L$2
            kotlinx.coroutines.CoroutineDispatcher r8 = (kotlinx.coroutines.CoroutineDispatcher) r8
            java.lang.Object r10 = r9.L$1
            io.ktor.client.HttpClient r10 = (io.ktor.client.HttpClient) r10
            java.lang.Object r11 = r9.L$0
            io.ktor.util.pipeline.PipelineContext r11 = (io.ktor.util.pipeline.PipelineContext) r11
            kotlin.ResultKt.throwOnFailure(r0)
            r12 = r6
            r15 = r11
            r6 = r2
            r11 = r8
        L_0x0077:
            r2 = r10
            goto L_0x00b3
        L_0x0079:
            kotlin.ResultKt.throwOnFailure(r0)
            java.lang.Object r0 = r18.getContext()
            io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
            r2 = r21
            java.lang.Object r0 = r2.invoke(r0)
            io.ktor.server.auth.OAuthServerSettings r0 = (io.ktor.server.auth.OAuthServerSettings) r0
            boolean r2 = r0 instanceof io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings
            if (r2 == 0) goto L_0x0137
            java.lang.Object r2 = r18.getContext()
            io.ktor.server.application.ApplicationCall r2 = (io.ktor.server.application.ApplicationCall) r2
            r8 = r18
            r9.L$0 = r8
            r10 = r19
            r9.L$1 = r10
            r11 = r20
            r9.L$2 = r11
            r12 = r22
            r9.L$3 = r12
            r9.L$4 = r0
            r9.label = r6
            java.lang.Object r2 = oauth2HandleCallback(r2, r9)
            if (r2 != r1) goto L_0x00af
            return r1
        L_0x00af:
            r6 = r0
            r0 = r2
            r15 = r8
            goto L_0x0077
        L_0x00b3:
            r14 = r0
            io.ktor.server.auth.OAuthCallback$TokenSingle r14 = (io.ktor.server.auth.OAuthCallback.TokenSingle) r14
            java.lang.Object r0 = r15.getContext()
            io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
            java.lang.Object r0 = r12.invoke(r0, r6)
            r13 = r0
            java.lang.String r13 = (java.lang.String) r13
            if (r14 != 0) goto L_0x0112
            java.lang.Object r0 = r15.getContext()
            io.ktor.server.application.ApplicationCall r0 = (io.ktor.server.application.ApplicationCall) r0
            r2 = r6
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r2 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r2
            io.ktor.util.NonceManager r3 = r2.getNonceManager()
            r9.L$0 = r6
            r9.L$1 = r0
            r9.L$2 = r2
            r9.L$3 = r13
            r9.L$4 = r7
            r9.label = r5
            java.lang.Object r3 = r3.newNonce(r9)
            if (r3 != r1) goto L_0x00e5
            return r1
        L_0x00e5:
            r17 = r2
            r2 = r0
            r0 = r3
            r3 = r17
        L_0x00eb:
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings r6 = (io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings) r6
            java.util.List r0 = r6.getDefaultScopes()
            kotlin.jvm.functions.Function1 r8 = r6.getAuthorizeUrlInterceptor()
            r9.L$0 = r7
            r9.L$1 = r7
            r9.L$2 = r7
            r9.L$3 = r7
            r9.label = r4
            r6 = 0
            r10 = 8
            r11 = 0
            r4 = r13
            r7 = r0
            java.lang.Object r0 = redirectAuthenticateOAuth2$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            if (r0 != r1) goto L_0x010f
            return r1
        L_0x010f:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0112:
            r0 = r11
            kotlin.coroutines.CoroutineContext r0 = (kotlin.coroutines.CoroutineContext) r0
            io.ktor.server.auth.OAuth2Kt$oauth2$2 r4 = new io.ktor.server.auth.OAuth2Kt$oauth2$2
            r16 = 0
            r10 = r4
            r11 = r2
            r12 = r6
            r10.<init>(r11, r12, r13, r14, r15, r16)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r9.L$0 = r7
            r9.L$1 = r7
            r9.L$2 = r7
            r9.L$3 = r7
            r9.L$4 = r7
            r9.label = r3
            java.lang.Object r0 = kotlinx.coroutines.BuildersKt.withContext(r0, r4, r9)
            if (r0 != r1) goto L_0x0134
            return r1
        L_0x0134:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0137:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuth2Kt.oauth2(io.ktor.util.pipeline.PipelineContext, io.ktor.client.HttpClient, kotlinx.coroutines.CoroutineDispatcher, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth2HandleCallback(io.ktor.server.application.ApplicationCall r5, kotlin.coroutines.Continuation<? super io.ktor.server.auth.OAuthCallback.TokenSingle> r6) {
        /*
            boolean r0 = r6 instanceof io.ktor.server.auth.OAuth2Kt$oauth2HandleCallback$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.server.auth.OAuth2Kt$oauth2HandleCallback$1 r0 = (io.ktor.server.auth.OAuth2Kt$oauth2HandleCallback$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.server.auth.OAuth2Kt$oauth2HandleCallback$1 r0 = new io.ktor.server.auth.OAuth2Kt$oauth2HandleCallback$1
            r0.<init>(r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0066
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            io.ktor.server.request.ApplicationRequest r6 = r5.getRequest()
            io.ktor.http.ContentType r6 = io.ktor.server.request.ApplicationRequestPropertiesKt.contentType(r6)
            io.ktor.http.ContentType$Application r2 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r2 = r2.getFormUrlEncoded()
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r2)
            if (r6 == 0) goto L_0x008c
            java.lang.Class<io.ktor.http.Parameters> r6 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.http.Parameters> r4 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r4)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r4, r6)
            r0.label = r3
            java.lang.Object r6 = io.ktor.server.request.ApplicationReceiveFunctionsKt.receiveNullable(r5, r6, r0)
            if (r6 != r1) goto L_0x0066
            return r1
        L_0x0066:
            if (r6 == 0) goto L_0x006b
            io.ktor.http.Parameters r6 = (io.ktor.http.Parameters) r6
            goto L_0x0090
        L_0x006b:
            io.ktor.server.plugins.CannotTransformContentToTypeException r5 = new io.ktor.server.plugins.CannotTransformContentToTypeException
            java.lang.Class<io.ktor.http.Parameters> r6 = io.ktor.http.Parameters.class
            kotlin.reflect.KType r6 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r6)
            java.lang.reflect.Type r0 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r6)
            java.lang.Class<io.ktor.http.Parameters> r1 = io.ktor.http.Parameters.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            io.ktor.util.reflect.TypeInfo r6 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r0, r1, r6)
            kotlin.reflect.KType r6 = r6.getKotlinType()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r5.<init>(r6)
            throw r5
        L_0x008c:
            io.ktor.http.Parameters r6 = r5.getParameters()
        L_0x0090:
            java.lang.String r5 = "code"
            java.lang.String r5 = r6.get(r5)
            java.lang.String r0 = "state"
            java.lang.String r6 = r6.get(r0)
            if (r5 == 0) goto L_0x00a6
            if (r6 == 0) goto L_0x00a6
            io.ktor.server.auth.OAuthCallback$TokenSingle r0 = new io.ktor.server.auth.OAuthCallback$TokenSingle
            r0.<init>(r5, r6)
            goto L_0x00a7
        L_0x00a6:
            r0 = 0
        L_0x00a7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuth2Kt.oauth2HandleCallback(io.ktor.server.application.ApplicationCall, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object redirectAuthenticateOAuth2$default(ApplicationCall applicationCall, OAuthServerSettings.OAuth2ServerSettings oAuth2ServerSettings, String str, String str2, List list, List list2, Function1 function1, Continuation continuation, int i, Object obj) {
        List list3;
        List emptyList = (i & 8) != 0 ? CollectionsKt.emptyList() : list;
        if ((i & 16) != 0) {
            list3 = CollectionsKt.emptyList();
        } else {
            list3 = list2;
        }
        return redirectAuthenticateOAuth2(applicationCall, oAuth2ServerSettings, str, str2, emptyList, list3, function1, continuation);
    }

    public static final Object redirectAuthenticateOAuth2(ApplicationCall applicationCall, OAuthServerSettings.OAuth2ServerSettings oAuth2ServerSettings, String str, String str2, List<Pair<String, String>> list, List<String> list2, Function1<? super URLBuilder, Unit> function1, Continuation<? super Unit> continuation) {
        Object redirectAuthenticateOAuth2 = redirectAuthenticateOAuth2(applicationCall, oAuth2ServerSettings.getAuthorizeUrl(), str, oAuth2ServerSettings.getClientId(), str2, list2, list, function1, continuation);
        return redirectAuthenticateOAuth2 == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? redirectAuthenticateOAuth2 : Unit.INSTANCE;
    }

    public static /* synthetic */ Object oauth2RequestAccessToken$default(HttpClient httpClient, OAuthServerSettings.OAuth2ServerSettings oAuth2ServerSettings, String str, OAuthCallback.TokenSingle tokenSingle, Function1 function1, Continuation continuation, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = null;
        }
        return oauth2RequestAccessToken(httpClient, oAuth2ServerSettings, str, tokenSingle, function1, continuation);
    }

    public static final Object oauth2RequestAccessToken(HttpClient httpClient, OAuthServerSettings.OAuth2ServerSettings oAuth2ServerSettings, String str, OAuthCallback.TokenSingle tokenSingle, Function1<? super HttpRequestBuilder, Unit> function1, Continuation<? super OAuthAccessTokenResponse.OAuth2> continuation) {
        Function1<HttpRequestBuilder, Unit> function12;
        Function1<? super HttpRequestBuilder, Unit> function13 = function1;
        if (function13 == null) {
            function12 = oAuth2ServerSettings.getAccessTokenInterceptor();
            OAuthServerSettings.OAuth2ServerSettings oAuth2ServerSettings2 = oAuth2ServerSettings;
        } else {
            function12 = new OAuth2Kt$oauth2RequestAccessToken$interceptor$1(oAuth2ServerSettings, function13);
        }
        return oauth2RequestAccessToken$default(httpClient, oAuth2ServerSettings.getRequestMethod(), str, oAuth2ServerSettings.getAccessTokenUrl(), oAuth2ServerSettings.getClientId(), oAuth2ServerSettings.getClientSecret(), tokenSingle.getState(), tokenSingle.getToken(), oAuth2ServerSettings.getExtraTokenParameters(), function12, oAuth2ServerSettings.getAccessTokenRequiresBasicAuth(), oAuth2ServerSettings.getNonceManager(), oAuth2ServerSettings.getPassParamsInURL(), (String) null, continuation, 8192, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final Object redirectAuthenticateOAuth2(ApplicationCall applicationCall, String str, String str2, String str3, String str4, List<String> list, List<Pair<String, String>> list2, Function1<? super URLBuilder, Unit> function1, Continuation<? super Unit> continuation) {
        URLBuilder uRLBuilder = new URLBuilder((URLProtocol) null, (String) null, 0, (String) null, (String) null, (List) null, (Parameters) null, (String) null, false, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null);
        String str5 = str;
        URLParserKt.takeFrom(uRLBuilder, str);
        ParametersBuilder parameters = uRLBuilder.getParameters();
        parameters.append(OAuth2RequestParameters.ClientId, str3);
        String str6 = str2;
        parameters.append(OAuth2RequestParameters.RedirectUri, str2);
        if (!list.isEmpty()) {
            parameters.append(OAuth2RequestParameters.Scope, CollectionsKt.joinToString$default(list, AnsiRenderer.CODE_TEXT_SEPARATOR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        }
        parameters.append(OAuth2RequestParameters.State, str4);
        parameters.append(OAuth2RequestParameters.ResponseType, OAuth2RequestParameters.Code);
        for (Pair pair : list2) {
            parameters.append((String) pair.component1(), (String) pair.component2());
        }
        function1.invoke(uRLBuilder);
        Object respondRedirect$default = ApplicationResponseFunctionsKt.respondRedirect$default(applicationCall, uRLBuilder.buildString(), false, (Continuation) continuation, 2, (Object) null);
        return respondRedirect$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondRedirect$default : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x03ce A[SYNTHETIC, Splitter:B:107:0x03ce] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x0415  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0105  */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x02c8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x02c9  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x02e2 A[Catch:{ IOException -> 0x03fa, all -> 0x03e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object oauth2RequestAccessToken(io.ktor.client.HttpClient r24, io.ktor.http.HttpMethod r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, java.lang.String r31, java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> r32, kotlin.jvm.functions.Function1<? super io.ktor.client.request.HttpRequestBuilder, kotlin.Unit> r33, boolean r34, io.ktor.util.NonceManager r35, boolean r36, java.lang.String r37, kotlin.coroutines.Continuation<? super io.ktor.server.auth.OAuthAccessTokenResponse.OAuth2> r38) {
        /*
            r0 = r30
            r1 = r38
            boolean r2 = r1 instanceof io.ktor.server.auth.OAuth2Kt$oauth2RequestAccessToken$2
            if (r2 == 0) goto L_0x0018
            r2 = r1
            io.ktor.server.auth.OAuth2Kt$oauth2RequestAccessToken$2 r2 = (io.ktor.server.auth.OAuth2Kt$oauth2RequestAccessToken$2) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x0018
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            goto L_0x001d
        L_0x0018:
            io.ktor.server.auth.OAuth2Kt$oauth2RequestAccessToken$2 r2 = new io.ktor.server.auth.OAuth2Kt$oauth2RequestAccessToken$2
            r2.<init>(r1)
        L_0x001d:
            java.lang.Object r1 = r2.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r2.label
            java.lang.String r5 = ""
            r6 = 3
            r7 = 2
            r8 = 1
            if (r4 == 0) goto L_0x00af
            if (r4 == r8) goto L_0x0063
            if (r4 == r7) goto L_0x0051
            if (r4 != r6) goto L_0x0049
            java.lang.Object r0 = r2.L$2
            io.ktor.client.statement.HttpResponse r0 = (io.ktor.client.statement.HttpResponse) r0
            java.lang.Object r3 = r2.L$1
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r1)
            r4 = r2
            r16 = r5
            r2 = r0
            r0 = r1
            r1 = 0
            goto L_0x02cf
        L_0x0049:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0051:
            java.lang.Object r0 = r2.L$1
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r4 = r2.L$0
            java.lang.String r4 = (java.lang.String) r4
            kotlin.ResultKt.throwOnFailure(r1)
            r14 = r0
            r0 = r1
            r16 = r5
            r1 = 0
            goto L_0x02b6
        L_0x0063:
            boolean r0 = r2.Z$1
            boolean r4 = r2.Z$0
            java.lang.Object r10 = r2.L$10
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r2.L$9
            kotlin.jvm.functions.Function1 r11 = (kotlin.jvm.functions.Function1) r11
            java.lang.Object r12 = r2.L$8
            java.util.List r12 = (java.util.List) r12
            java.lang.Object r13 = r2.L$7
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r14 = r2.L$6
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r15 = r2.L$5
            java.lang.String r15 = (java.lang.String) r15
            java.lang.Object r6 = r2.L$4
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r2.L$3
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r9 = r2.L$2
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r8 = r2.L$1
            io.ktor.http.HttpMethod r8 = (io.ktor.http.HttpMethod) r8
            r24 = r0
            java.lang.Object r0 = r2.L$0
            io.ktor.client.HttpClient r0 = (io.ktor.client.HttpClient) r0
            kotlin.ResultKt.throwOnFailure(r1)
            r16 = r1
            r1 = r0
            r0 = r24
            r21 = r9
            r9 = r6
            r6 = r8
            r8 = r7
            r7 = r21
            r22 = r15
            r15 = r10
            r10 = r22
            r23 = r13
            r13 = r11
            r11 = r23
            goto L_0x00fd
        L_0x00af:
            kotlin.ResultKt.throwOnFailure(r1)
            r1 = r24
            if (r0 != 0) goto L_0x00b8
            r4 = r5
            goto L_0x00b9
        L_0x00b8:
            r4 = r0
        L_0x00b9:
            r2.L$0 = r1
            r6 = r25
            r2.L$1 = r6
            r7 = r26
            r2.L$2 = r7
            r8 = r27
            r2.L$3 = r8
            r9 = r28
            r2.L$4 = r9
            r10 = r29
            r2.L$5 = r10
            r2.L$6 = r0
            r11 = r31
            r2.L$7 = r11
            r12 = r32
            r2.L$8 = r12
            r13 = r33
            r2.L$9 = r13
            r14 = r37
            r2.L$10 = r14
            r15 = r34
            r2.Z$0 = r15
            r0 = r36
            r2.Z$1 = r0
            r0 = 1
            r2.label = r0
            r0 = r35
            java.lang.Object r0 = r0.verifyNonce(r4, r2)
            if (r0 != r3) goto L_0x00f5
            return r3
        L_0x00f5:
            r16 = r0
            r4 = r15
            r0 = r36
            r15 = r14
            r14 = r30
        L_0x00fd:
            java.lang.Boolean r16 = (java.lang.Boolean) r16
            boolean r16 = r16.booleanValue()
            if (r16 == 0) goto L_0x0415
            r16 = r5
            io.ktor.client.request.HttpRequestBuilder r5 = new io.ktor.client.request.HttpRequestBuilder
            r5.<init>()
            r17 = r3
            io.ktor.http.URLBuilder r3 = r5.getUrl()
            io.ktor.http.URLParserKt.takeFrom(r3, r8)
            r3 = 0
            r25 = r1
            r18 = r2
            r24 = r8
            r2 = 1
            r8 = 0
            io.ktor.http.ParametersBuilder r1 = io.ktor.http.ParametersKt.ParametersBuilder$default(r3, r2, r8)
            java.lang.String r2 = "client_id"
            r1.append(r2, r9)
            java.lang.String r2 = "client_secret"
            r1.append(r2, r10)
            java.lang.String r2 = "grant_type"
            r1.append(r2, r15)
            if (r14 == 0) goto L_0x0138
            java.lang.String r2 = "state"
            r1.append(r2, r14)
        L_0x0138:
            if (r11 == 0) goto L_0x013f
            java.lang.String r2 = "code"
            r1.append(r2, r11)
        L_0x013f:
            if (r7 == 0) goto L_0x0146
            java.lang.String r2 = "redirect_uri"
            r1.append(r2, r7)
        L_0x0146:
            java.lang.Iterable r12 = (java.lang.Iterable) r12
            java.util.Iterator r2 = r12.iterator()
        L_0x014c:
            boolean r7 = r2.hasNext()
            if (r7 == 0) goto L_0x0168
            java.lang.Object r7 = r2.next()
            kotlin.Pair r7 = (kotlin.Pair) r7
            java.lang.Object r8 = r7.component1()
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r7 = r7.component2()
            java.lang.String r7 = (java.lang.String) r7
            r1.append(r8, r7)
            goto L_0x014c
        L_0x0168:
            io.ktor.http.Parameters r1 = r1.build()
            io.ktor.http.HttpMethod$Companion r2 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r2 = r2.getGet()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x0186
            io.ktor.http.URLBuilder r0 = r5.getUrl()
            io.ktor.http.ParametersBuilder r0 = r0.getParameters()
            io.ktor.util.StringValues r1 = (io.ktor.util.StringValues) r1
            r0.appendAll(r1)
            goto L_0x01e6
        L_0x0186:
            io.ktor.http.HttpMethod$Companion r2 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r2 = r2.getPost()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r2)
            if (r2 == 0) goto L_0x03fc
            if (r0 == 0) goto L_0x01a2
            io.ktor.http.URLBuilder r0 = r5.getUrl()
            io.ktor.http.ParametersBuilder r0 = r0.getParameters()
            io.ktor.util.StringValues r1 = (io.ktor.util.StringValues) r1
            r0.appendAll(r1)
            goto L_0x01e6
        L_0x01a2:
            io.ktor.http.content.TextContent r0 = new io.ktor.http.content.TextContent
            java.lang.String r1 = io.ktor.http.HttpUrlEncodedKt.formUrlEncode((io.ktor.http.Parameters) r1)
            io.ktor.http.ContentType$Application r2 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r2 = r2.getFormUrlEncoded()
            r7 = 4
            r8 = 0
            r11 = 0
            r26 = r0
            r27 = r1
            r28 = r2
            r29 = r11
            r30 = r7
            r31 = r8
            r26.<init>(r27, r28, r29, r30, r31)
            boolean r1 = r0 instanceof io.ktor.http.content.OutgoingContent
            if (r1 == 0) goto L_0x01cc
            r5.setBody(r0)
            r1 = 0
            r5.setBodyType(r1)
            goto L_0x01e6
        L_0x01cc:
            r5.setBody(r0)
            java.lang.Class<io.ktor.http.content.TextContent> r0 = io.ktor.http.content.TextContent.class
            kotlin.reflect.KType r0 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r0)
            java.lang.reflect.Type r1 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r0)
            java.lang.Class<io.ktor.http.content.TextContent> r2 = io.ktor.http.content.TextContent.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            io.ktor.util.reflect.TypeInfo r0 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r1, r2, r0)
            r5.setBodyType(r0)
        L_0x01e6:
            r5.setMethod(r6)
            r0 = r5
            io.ktor.http.HttpMessageBuilder r0 = (io.ktor.http.HttpMessageBuilder) r0
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getAccept()
            r2 = 2
            io.ktor.http.ContentType[] r6 = new io.ktor.http.ContentType[r2]
            io.ktor.http.ContentType$Application r2 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r2 = r2.getFormUrlEncoded()
            r6[r3] = r2
            io.ktor.http.ContentType$Application r2 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r2 = r2.getJson()
            r7 = 1
            r6[r7] = r2
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r6)
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.lang.String r6 = ","
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r7 = 62
            r8 = 0
            r11 = 0
            r12 = 0
            r15 = 0
            r19 = 0
            r20 = 0
            r26 = r2
            r27 = r6
            r28 = r11
            r29 = r12
            r30 = r15
            r31 = r19
            r32 = r20
            r33 = r7
            r34 = r8
            java.lang.String r2 = kotlin.collections.CollectionsKt.joinToString$default(r26, r27, r28, r29, r30, r31, r32, r33, r34)
            io.ktor.client.request.UtilsKt.header(r0, r1, r2)
            if (r4 == 0) goto L_0x0284
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getAuthorization()
            io.ktor.http.auth.HttpAuthHeader$Single r2 = new io.ktor.http.auth.HttpAuthHeader$Single
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r9)
            r6 = 58
            r4.append(r6)
            r4.append(r10)
            java.lang.String r4 = r4.toString()
            java.nio.charset.Charset r6 = kotlin.text.Charsets.ISO_8859_1
            java.nio.charset.Charset r7 = kotlin.text.Charsets.UTF_8
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r6, (java.lang.Object) r7)
            if (r7 == 0) goto L_0x0260
            byte[] r3 = kotlin.text.StringsKt.encodeToByteArray(r4)
            goto L_0x0274
        L_0x0260:
            java.nio.charset.CharsetEncoder r6 = r6.newEncoder()
            java.lang.String r7 = "charset.newEncoder()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            r7 = r4
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            int r4 = r4.length()
            byte[] r3 = io.ktor.utils.io.charsets.CharsetJVMKt.encodeToByteArray(r6, r7, r3, r4)
        L_0x0274:
            java.lang.String r3 = io.ktor.util.Base64Kt.encodeBase64((byte[]) r3)
            java.lang.String r4 = "Basic"
            r2.<init>(r4, r3)
            java.lang.String r2 = r2.render()
            io.ktor.client.request.UtilsKt.header(r0, r1, r2)
        L_0x0284:
            r13.invoke(r5)
            io.ktor.client.statement.HttpStatement r0 = new io.ktor.client.statement.HttpStatement
            r1 = r25
            r0.<init>(r5, r1)
            r7 = r24
            r2 = r18
            r2.L$0 = r7
            r2.L$1 = r14
            r1 = 0
            r2.L$2 = r1
            r2.L$3 = r1
            r2.L$4 = r1
            r2.L$5 = r1
            r2.L$6 = r1
            r2.L$7 = r1
            r2.L$8 = r1
            r2.L$9 = r1
            r2.L$10 = r1
            r3 = 2
            r2.label = r3
            java.lang.Object r0 = r0.execute(r2)
            r3 = r17
            if (r0 != r3) goto L_0x02b5
            return r3
        L_0x02b5:
            r4 = r7
        L_0x02b6:
            io.ktor.client.statement.HttpResponse r0 = (io.ktor.client.statement.HttpResponse) r0
            r2.L$0 = r4
            r2.L$1 = r14
            r2.L$2 = r0
            r5 = 3
            r2.label = r5
            r5 = 1
            java.lang.Object r2 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r0, r1, r2, r5, r1)
            if (r2 != r3) goto L_0x02c9
            return r3
        L_0x02c9:
            r3 = r14
            r21 = r2
            r2 = r0
            r0 = r21
        L_0x02cf:
            r5 = r0
            java.lang.String r5 = (java.lang.String) r5
            io.ktor.http.HttpStatusCode r0 = r2.getStatus()     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            io.ktor.http.HttpStatusCode$Companion r6 = io.ktor.http.HttpStatusCode.Companion     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            io.ktor.http.HttpStatusCode r6 = r6.getNotFound()     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r6)     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            if (r0 != 0) goto L_0x03ce
            io.ktor.http.Headers r0 = r2.getHeaders()     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            io.ktor.http.HttpHeaders r6 = io.ktor.http.HttpHeaders.INSTANCE     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            java.lang.String r6 = r6.getContentType()     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            java.lang.String r0 = r0.get(r6)     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            if (r0 == 0) goto L_0x02fa
            io.ktor.http.ContentType$Companion r6 = io.ktor.http.ContentType.Companion     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            io.ktor.http.ContentType r0 = r6.parse(r0)     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            if (r0 != 0) goto L_0x0300
        L_0x02fa:
            io.ktor.http.ContentType$Companion r0 = io.ktor.http.ContentType.Companion     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            io.ktor.http.ContentType r0 = r0.getAny()     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
        L_0x0300:
            kotlin.Pair r6 = new kotlin.Pair     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            r6.<init>(r0, r5)     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            java.lang.Object r0 = r6.component1()
            io.ktor.http.ContentType r0 = (io.ktor.http.ContentType) r0
            java.lang.Object r5 = r6.component2()
            java.lang.String r5 = (java.lang.String) r5
            kotlin.Result$Companion r6 = kotlin.Result.Companion
            kotlin.Result$Companion r6 = kotlin.Result.Companion     // Catch:{ all -> 0x031e }
            io.ktor.http.Parameters r0 = decodeContent(r5, r0)     // Catch:{ all -> 0x031e }
            java.lang.Object r0 = kotlin.Result.m1774constructorimpl(r0)     // Catch:{ all -> 0x031e }
            goto L_0x0329
        L_0x031e:
            r0 = move-exception
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            java.lang.Object r0 = kotlin.Result.m1774constructorimpl(r0)
        L_0x0329:
            boolean r5 = kotlin.Result.m1781isSuccessimpl(r0)
            if (r5 == 0) goto L_0x033f
            kotlin.Result$Companion r5 = kotlin.Result.Companion
            r5 = r0
            io.ktor.http.Parameters r5 = (io.ktor.http.Parameters) r5
            java.lang.String r6 = "error"
            java.lang.String r5 = r5.get(r6)
            java.lang.Object r5 = kotlin.Result.m1774constructorimpl(r5)
            goto L_0x0343
        L_0x033f:
            java.lang.Object r5 = kotlin.Result.m1774constructorimpl(r0)
        L_0x0343:
            boolean r6 = kotlin.Result.m1780isFailureimpl(r5)
            if (r6 == 0) goto L_0x034b
            r9 = r1
            goto L_0x034c
        L_0x034b:
            r9 = r5
        L_0x034c:
            java.lang.String r9 = (java.lang.String) r9
            if (r9 != 0) goto L_0x03c0
            io.ktor.http.HttpStatusCode r1 = r2.getStatus()
            boolean r1 = io.ktor.http.HttpStatusCodeKt.isSuccess(r1)
            if (r1 == 0) goto L_0x03a0
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.http.Parameters r0 = (io.ktor.http.Parameters) r0
            java.lang.String r1 = "access_token"
            java.lang.String r1 = r0.get(r1)
            if (r1 == 0) goto L_0x039a
            java.lang.String r2 = "token_type"
            java.lang.String r2 = r0.get(r2)
            if (r2 != 0) goto L_0x0371
            r2 = r16
        L_0x0371:
            java.lang.String r4 = "expires_in"
            java.lang.String r4 = r0.get(r4)
            if (r4 == 0) goto L_0x037e
            long r4 = java.lang.Long.parseLong(r4)
            goto L_0x0380
        L_0x037e:
            r4 = 0
        L_0x0380:
            java.lang.String r6 = "refresh_token"
            java.lang.String r6 = r0.get(r6)
            io.ktor.server.auth.OAuthAccessTokenResponse$OAuth2 r7 = new io.ktor.server.auth.OAuthAccessTokenResponse$OAuth2
            r24 = r7
            r25 = r1
            r26 = r2
            r27 = r4
            r29 = r6
            r30 = r0
            r31 = r3
            r24.<init>(r25, r26, r27, r29, r30, r31)
            return r7
        L_0x039a:
            io.ktor.server.auth.OAuth2Exception$MissingAccessToken r0 = new io.ktor.server.auth.OAuth2Exception$MissingAccessToken
            r0.<init>()
            throw r0
        L_0x03a0:
            java.io.IOException r0 = new java.io.IOException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r3 = "Access token query failed with http status "
            r1.<init>(r3)
            io.ktor.http.HttpStatusCode r2 = r2.getStatus()
            r1.append(r2)
            java.lang.String r2 = " for the page "
            r1.append(r2)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x03c0:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.http.Parameters r0 = (io.ktor.http.Parameters) r0
            throwOAuthError(r9, r0)
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException
            r0.<init>()
            throw r0
        L_0x03ce:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            r1.<init>()     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            java.lang.String r2 = "Access token query failed with http status 404 for the page "
            r1.append(r2)     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            r1.append(r4)     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            java.lang.String r1 = r1.toString()     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            r0.<init>(r1)     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
            throw r0     // Catch:{ IOException -> 0x03fa, all -> 0x03e5 }
        L_0x03e5:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Failed to acquire request token due to wrong content: "
            r2.<init>(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x03fa:
            r0 = move-exception
            throw r0
        L_0x03fc:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Method "
            r1.<init>(r2)
            r1.append(r6)
            java.lang.String r2 = " is not supported. Use GET or POST"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x0415:
            io.ktor.server.auth.OAuth2Exception$InvalidNonce r0 = new io.ktor.server.auth.OAuth2Exception$InvalidNonce
            r0.<init>()
            goto L_0x041c
        L_0x041b:
            throw r0
        L_0x041c:
            goto L_0x041b
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuth2Kt.oauth2RequestAccessToken(io.ktor.client.HttpClient, io.ktor.http.HttpMethod, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, kotlin.jvm.functions.Function1, boolean, io.ktor.util.NonceManager, boolean, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object oauth2RequestAccessToken$default(HttpClient httpClient, HttpMethod httpMethod, String str, String str2, String str3, String str4, String str5, String str6, List list, Function1 function1, boolean z, NonceManager nonceManager, boolean z2, String str7, Continuation continuation, int i, Object obj) {
        int i2 = i;
        return oauth2RequestAccessToken(httpClient, httpMethod, str, str2, str3, str4, str5, str6, (i2 & 256) != 0 ? CollectionsKt.emptyList() : list, (i2 & 512) != 0 ? OAuth2Kt$oauth2RequestAccessToken$3.INSTANCE : function1, (i2 & 1024) != 0 ? false : z, nonceManager, (i2 & 4096) != 0 ? false : z2, (i2 & 8192) != 0 ? OAuthGrantTypes.AuthorizationCode : str7, continuation);
    }

    private static final Parameters decodeContent(String str, ContentType contentType) {
        String content;
        if (contentType.match(ContentType.Application.INSTANCE.getFormUrlEncoded())) {
            return HttpUrlEncodedKt.parseUrlEncodedParameters$default(str, (Charset) null, 0, 3, (Object) null);
        }
        if (contentType.match(ContentType.Application.INSTANCE.getJson())) {
            Parameters.Companion companion = Parameters.Companion;
            ParametersBuilder ParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, (Object) null);
            for (Map.Entry entry : ((Map) Json.Default.decodeFromString(JsonObject.Companion.serializer(), str)).entrySet()) {
                String str2 = (String) entry.getKey();
                JsonElement jsonElement = (JsonElement) entry.getValue();
                JsonPrimitive jsonPrimitive = jsonElement instanceof JsonPrimitive ? (JsonPrimitive) jsonElement : null;
                if (!(jsonPrimitive == null || (content = jsonPrimitive.getContent()) == null)) {
                    ParametersBuilder$default.append(str2, content);
                }
            }
            return ParametersBuilder$default.build();
        }
        if (StringsKt.startsWith$default(str, "{", false, 2, (Object) null)) {
            CharSequence charSequence = str;
            if (StringsKt.endsWith$default(StringsKt.trim(charSequence).toString(), "}", false, 2, (Object) null)) {
                return decodeContent(StringsKt.trim(charSequence).toString(), ContentType.Application.INSTANCE.getJson());
            }
        }
        if (new Regex("([a-zA-Z\\d_-]+=[^=&]+&?)+").matches(str)) {
            return decodeContent(str, ContentType.Application.INSTANCE.getFormUrlEncoded());
        }
        throw new IOException("unsupported content type " + contentType);
    }

    public static final Object verifyWithOAuth2(UserPasswordCredential userPasswordCredential, HttpClient httpClient, OAuthServerSettings.OAuth2ServerSettings oAuth2ServerSettings, Continuation<? super OAuthAccessTokenResponse.OAuth2> continuation) {
        return oauth2RequestAccessToken(httpClient, HttpMethod.Companion.getPost(), (String) null, oAuth2ServerSettings.getAccessTokenUrl(), oAuth2ServerSettings.getClientId(), oAuth2ServerSettings.getClientSecret(), (String) null, (String) null, CollectionsKt.listOf(TuplesKt.to(OAuth2RequestParameters.UserName, userPasswordCredential.getName()), TuplesKt.to("password", userPasswordCredential.getPassword())), oAuth2ServerSettings.getAccessTokenInterceptor(), true, oAuth2ServerSettings.getNonceManager(), oAuth2ServerSettings.getPassParamsInURL(), "password", continuation);
    }

    private static final Void throwOAuthError(String str, Parameters parameters) {
        Throwable th;
        String str2 = parameters.get(OAuth2ResponseParameters.ErrorDescription);
        if (str2 == null) {
            str2 = "OAuth2 Server responded with " + str;
        }
        if (Intrinsics.areEqual((Object) str, (Object) "invalid_grant")) {
            th = new OAuth2Exception.InvalidGrant(str2);
        } else {
            th = new OAuth2Exception.UnknownException(str2, str);
        }
        throw th;
    }
}
