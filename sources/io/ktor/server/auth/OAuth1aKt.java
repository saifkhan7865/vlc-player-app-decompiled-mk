package io.ktor.server.auth;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.client.HttpClient;
import io.ktor.http.CodecsKt;
import io.ktor.http.HeaderValueParam;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpMethod;
import io.ktor.http.auth.AuthScheme;
import io.ktor.http.auth.HeaderValueEncoding;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.OAuthAccessTokenResponse;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.server.auth.OAuthServerSettings;
import io.ktor.server.response.ApplicationResponseFunctionsKt;
import io.ktor.util.CryptoKt;
import io.ktor.util.TextKt;
import io.ktor.util.pipeline.PipelineContext;
import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import j$.util.Base64;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.Typography;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\u001am\u0010\u000e\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0019\u0010\n\u001a\u0015\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\b\t2\u001d\u0010\r\u001a\u0019\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0002\b\tH@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0015\u0010\u0011\u001a\u0004\u0018\u00010\u0010*\u00020\u0002H\u0000¢\u0006\u0004\b\u0011\u0010\u0012\u001aQ\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\f2\u001a\b\u0002\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00180\u0017H@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001b\u001aa\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\f2\u001a\b\u0002\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00180\u0017H@ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010 \u001a'\u0010\"\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a'\u0010\"\u001a\u00020\u0001*\u00020\u00022\u0006\u0010$\u001a\u00020\f2\u0006\u0010!\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0004\b\"\u0010%\u001aK\u0010)\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010&\u001a\u00020\u00102\b\b\u0002\u0010\u0016\u001a\u00020\f2\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0'H@ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a~\u0010)\u001a\u00020(2\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020\f2\b\b\u0002\u0010\u0016\u001a\u00020\f2\u0014\b\u0002\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0'2\u0019\u0010.\u001a\u0015\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\tH@ø\u0001\u0000¢\u0006\u0004\b)\u0010/\u001a1\u00103\u001a\u0002022\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\b\b\u0002\u00101\u001a\u000200H\u0007¢\u0006\u0004\b3\u00104\u001a1\u00105\u001a\u0002022\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\b\b\u0002\u00101\u001a\u000200H\u0002¢\u0006\u0004\b5\u00104\u001a1\u00106\u001a\u0002022\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\b\b\u0002\u00101\u001a\u000200H\u0001¢\u0006\u0004\b6\u00104\u001a1\u00107\u001a\u0002022\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010+\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\b\b\u0002\u00101\u001a\u000200H\u0002¢\u0006\u0004\b7\u00104\u001aE\u0010<\u001a\u000202*\u0002022\u0006\u00109\u001a\u0002082\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010:\u001a\u00020\f2\u0018\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00180\u0017H\u0007¢\u0006\u0004\b<\u0010=\u001aE\u0010>\u001a\u000202*\u0002022\u0006\u00109\u001a\u0002082\u0006\u0010\u001d\u001a\u00020\f2\u0006\u0010:\u001a\u00020\f2\u0018\u0010;\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u00180\u0017H\u0002¢\u0006\u0004\b>\u0010=\u001a5\u0010A\u001a\u00020\f2\u0006\u0010?\u001a\u0002022\u0006\u00109\u001a\u0002082\u0006\u0010\u001d\u001a\u00020\f2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020@0\u0017H\u0007¢\u0006\u0004\bA\u0010B\u001a5\u0010C\u001a\u00020\f2\u0006\u0010?\u001a\u0002022\u0006\u00109\u001a\u0002082\u0006\u0010\u001d\u001a\u00020\f2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020@0\u0017H\u0000¢\u0006\u0004\bC\u0010B\u001a\u001b\u0010D\u001a\u00020\f*\u00020\f2\u0006\u0010:\u001a\u00020\fH\u0002¢\u0006\u0004\bD\u0010E\u001a\u001d\u0010F\u001a\u00020\f2\f\u0010;\u001a\b\u0012\u0004\u0012\u00020@0\u0017H\u0002¢\u0006\u0004\bF\u0010G\u0002\u0004\n\u0002\b\u0019¨\u0006H"}, d2 = {"Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/server/application/ApplicationCall;", "Lio/ktor/client/HttpClient;", "client", "Lkotlinx/coroutines/CoroutineDispatcher;", "dispatcher", "Lkotlin/Function1;", "Lio/ktor/server/auth/OAuthServerSettings;", "Lkotlin/ExtensionFunctionType;", "providerLookup", "Lkotlin/Function2;", "", "urlProvider", "oauth1a", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lkotlinx/coroutines/CoroutineDispatcher;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/server/auth/OAuthCallback$TokenPair;", "oauth1aHandleCallback", "(Lio/ktor/server/application/ApplicationCall;)Lio/ktor/server/auth/OAuthCallback$TokenPair;", "Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;", "settings", "callbackUrl", "nonce", "", "Lkotlin/Pair;", "extraParameters", "simpleOAuth1aStep1", "(Lio/ktor/client/HttpClient;Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "secretKey", "baseUrl", "callback", "consumerKey", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestToken", "redirectAuthenticateOAuth1a", "(Lio/ktor/server/application/ApplicationCall;Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;Lio/ktor/server/auth/OAuthCallback$TokenPair;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "authenticateUrl", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "callbackResponse", "", "Lio/ktor/server/auth/OAuthAccessTokenResponse$OAuth1a;", "requestOAuth1aAccessToken", "(Lio/ktor/client/HttpClient;Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;Lio/ktor/server/auth/OAuthCallback$TokenPair;Ljava/lang/String;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "token", "verifier", "Lio/ktor/client/request/HttpRequestBuilder;", "accessTokenInterceptor", "(Lio/ktor/client/HttpClient;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "j$/time/LocalDateTime", "timestamp", "Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "createObtainRequestTokenHeader", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lj$/time/LocalDateTime;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "createObtainRequestTokenHeaderInternal", "createUpgradeRequestTokenHeader", "createUpgradeRequestTokenHeaderInternal", "Lio/ktor/http/HttpMethod;", "method", "key", "parameters", "sign", "(Lio/ktor/http/auth/HttpAuthHeader$Parameterized;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)Lio/ktor/http/auth/HttpAuthHeader$Parameterized;", "signInternal", "header", "Lio/ktor/http/HeaderValueParam;", "signatureBaseString", "(Lio/ktor/http/auth/HttpAuthHeader$Parameterized;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/util/List;)Ljava/lang/String;", "signatureBaseStringInternal", "hmacSha1", "(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "parametersString", "(Ljava/util/List;)Ljava/lang/String;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0})
/* compiled from: OAuth1a.kt */
public final class OAuth1aKt {
    public static final OAuthCallback.TokenPair oauth1aHandleCallback(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        String str = applicationCall.getParameters().get(HttpAuthHeader.Parameters.OAuthToken);
        String str2 = applicationCall.getParameters().get(HttpAuthHeader.Parameters.OAuthVerifier);
        if (str == null || str2 == null) {
            return null;
        }
        return new OAuthCallback.TokenPair(str, str2);
    }

    public static /* synthetic */ Object simpleOAuth1aStep1$default(HttpClient httpClient, OAuthServerSettings.OAuth1aServerSettings oAuth1aServerSettings, String str, String str2, List list, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            str2 = CryptoKt.generateNonce();
        }
        String str3 = str2;
        if ((i & 16) != 0) {
            list = CollectionsKt.emptyList();
        }
        return simpleOAuth1aStep1(httpClient, oAuth1aServerSettings, str, str3, list, continuation);
    }

    public static final Object simpleOAuth1aStep1(HttpClient httpClient, OAuthServerSettings.OAuth1aServerSettings oAuth1aServerSettings, String str, String str2, List<Pair<String, String>> list, Continuation<? super OAuthCallback.TokenPair> continuation) {
        return simpleOAuth1aStep1(httpClient, oAuth1aServerSettings.getConsumerSecret() + Typography.amp, oAuth1aServerSettings.getRequestTokenUrl(), str, oAuth1aServerSettings.getConsumerKey(), str2, list, continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00be A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00d1 A[Catch:{ all -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0109 A[Catch:{ all -> 0x011b }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object simpleOAuth1aStep1(io.ktor.client.HttpClient r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> r21, kotlin.coroutines.Continuation<? super io.ktor.server.auth.OAuthCallback.TokenPair> r22) {
        /*
            r0 = r17
            r1 = r22
            java.lang.String r2 = "Bad response: "
            boolean r3 = r1 instanceof io.ktor.server.auth.OAuth1aKt$simpleOAuth1aStep1$2
            if (r3 == 0) goto L_0x001a
            r3 = r1
            io.ktor.server.auth.OAuth1aKt$simpleOAuth1aStep1$2 r3 = (io.ktor.server.auth.OAuth1aKt$simpleOAuth1aStep1$2) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r1 = r3.label
            int r1 = r1 - r5
            r3.label = r1
            goto L_0x001f
        L_0x001a:
            io.ktor.server.auth.OAuth1aKt$simpleOAuth1aStep1$2 r3 = new io.ktor.server.auth.OAuth1aKt$simpleOAuth1aStep1$2
            r3.<init>(r1)
        L_0x001f:
            java.lang.Object r1 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 2
            r7 = 1
            r8 = 0
            if (r5 == 0) goto L_0x0045
            if (r5 == r7) goto L_0x0041
            if (r5 != r6) goto L_0x0039
            java.lang.Object r0 = r3.L$0
            io.ktor.client.statement.HttpResponse r0 = (io.ktor.client.statement.HttpResponse) r0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00bf
        L_0x0039:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x00b1
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r1)
            r13 = 8
            r14 = 0
            r12 = 0
            r9 = r18
            r10 = r19
            r11 = r20
            io.ktor.http.auth.HttpAuthHeader$Parameterized r1 = createObtainRequestTokenHeaderInternal$default(r9, r10, r11, r12, r13, r14)
            io.ktor.http.HttpMethod$Companion r5 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r5 = r5.getPost()
            r9 = r16
            r10 = r21
            io.ktor.http.auth.HttpAuthHeader$Parameterized r1 = signInternal(r1, r5, r0, r9, r10)
            java.lang.String r5 = io.ktor.http.HttpUrlEncodedKt.formUrlEncode((java.util.List<kotlin.Pair<java.lang.String, java.lang.String>>) r21)
            java.lang.String r0 = io.ktor.server.auth.OAuthKt.appendUrlParameters(r0, r5)
            io.ktor.client.request.HttpRequestBuilder r5 = new io.ktor.client.request.HttpRequestBuilder
            r5.<init>()
            io.ktor.client.request.HttpRequestKt.url((io.ktor.client.request.HttpRequestBuilder) r5, (java.lang.String) r0)
            r0 = r5
            io.ktor.http.HttpMessageBuilder r0 = (io.ktor.http.HttpMessageBuilder) r0
            io.ktor.http.HttpHeaders r9 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r9 = r9.getAuthorization()
            io.ktor.http.auth.HeaderValueEncoding r10 = io.ktor.http.auth.HeaderValueEncoding.URI_ENCODE
            java.lang.String r1 = r1.render(r10)
            io.ktor.client.request.UtilsKt.header(r0, r9, r1)
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getAccept()
            io.ktor.http.ContentType$Companion r9 = io.ktor.http.ContentType.Companion
            io.ktor.http.ContentType r9 = r9.getAny()
            java.lang.String r9 = r9.toString()
            io.ktor.client.request.UtilsKt.header(r0, r1, r9)
            io.ktor.http.HttpMethod$Companion r0 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r0 = r0.getPost()
            r5.setMethod(r0)
            io.ktor.client.statement.HttpStatement r0 = new io.ktor.client.statement.HttpStatement
            r1 = r15
            r0.<init>(r5, r15)
            r3.label = r7
            java.lang.Object r1 = r0.execute(r3)
            if (r1 != r4) goto L_0x00b1
            return r4
        L_0x00b1:
            r0 = r1
            io.ktor.client.statement.HttpResponse r0 = (io.ktor.client.statement.HttpResponse) r0
            r3.L$0 = r0
            r3.label = r6
            java.lang.Object r1 = io.ktor.client.statement.HttpResponseKt.bodyAsText$default(r0, r8, r3, r7, r8)
            if (r1 != r4) goto L_0x00bf
            return r4
        L_0x00bf:
            java.lang.String r1 = (java.lang.String) r1
            io.ktor.http.HttpStatusCode r3 = r0.getStatus()     // Catch:{ all -> 0x011b }
            io.ktor.http.HttpStatusCode$Companion r4 = io.ktor.http.HttpStatusCode.Companion     // Catch:{ all -> 0x011b }
            io.ktor.http.HttpStatusCode r4 = r4.getOK()     // Catch:{ all -> 0x011b }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)     // Catch:{ all -> 0x011b }
            if (r3 == 0) goto L_0x0109
            r0 = 0
            r2 = 3
            io.ktor.http.Parameters r0 = io.ktor.http.HttpUrlEncodedKt.parseUrlEncodedParameters$default(r1, r8, r0, r2, r8)     // Catch:{ all -> 0x011b }
            java.lang.String r2 = "oauth_callback_confirmed"
            java.lang.String r2 = r0.get(r2)     // Catch:{ all -> 0x011b }
            java.lang.String r3 = "true"
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)     // Catch:{ all -> 0x011b }
            if (r2 == 0) goto L_0x00fd
            io.ktor.server.auth.OAuthCallback$TokenPair r2 = new io.ktor.server.auth.OAuthCallback$TokenPair     // Catch:{ all -> 0x011b }
            java.lang.String r3 = "oauth_token"
            java.lang.String r3 = r0.get(r3)     // Catch:{ all -> 0x011b }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch:{ all -> 0x011b }
            java.lang.String r4 = "oauth_token_secret"
            java.lang.String r0 = r0.get(r4)     // Catch:{ all -> 0x011b }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch:{ all -> 0x011b }
            r2.<init>(r3, r0)     // Catch:{ all -> 0x011b }
            return r2
        L_0x00fd:
            java.lang.String r0 = "Response parameter oauth_callback_confirmed should be true"
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x011b }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x011b }
            r2.<init>(r0)     // Catch:{ all -> 0x011b }
            throw r2     // Catch:{ all -> 0x011b }
        L_0x0109:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x011b }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x011b }
            r4.<init>(r2)     // Catch:{ all -> 0x011b }
            r4.append(r0)     // Catch:{ all -> 0x011b }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x011b }
            r3.<init>(r0)     // Catch:{ all -> 0x011b }
            throw r3     // Catch:{ all -> 0x011b }
        L_0x011b:
            r0 = move-exception
            java.io.IOException r2 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Failed to acquire request token due to "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuth1aKt.simpleOAuth1aStep1(io.ktor.client.HttpClient, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object simpleOAuth1aStep1$default(HttpClient httpClient, String str, String str2, String str3, String str4, String str5, List list, Continuation continuation, int i, Object obj) {
        List list2;
        String generateNonce = (i & 32) != 0 ? CryptoKt.generateNonce() : str5;
        if ((i & 64) != 0) {
            list2 = CollectionsKt.emptyList();
        } else {
            list2 = list;
        }
        return simpleOAuth1aStep1(httpClient, str, str2, str3, str4, generateNonce, list2, continuation);
    }

    public static final Object redirectAuthenticateOAuth1a(ApplicationCall applicationCall, OAuthServerSettings.OAuth1aServerSettings oAuth1aServerSettings, OAuthCallback.TokenPair tokenPair, Continuation<? super Unit> continuation) {
        Object redirectAuthenticateOAuth1a = redirectAuthenticateOAuth1a(applicationCall, oAuth1aServerSettings.getAuthorizeUrl(), tokenPair.getToken(), continuation);
        return redirectAuthenticateOAuth1a == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? redirectAuthenticateOAuth1a : Unit.INSTANCE;
    }

    public static final Object redirectAuthenticateOAuth1a(ApplicationCall applicationCall, String str, String str2, Continuation<? super Unit> continuation) {
        Object respondRedirect$default = ApplicationResponseFunctionsKt.respondRedirect$default(applicationCall, OAuthKt.appendUrlParameters(str, "oauth_token=" + CodecsKt.encodeURLParameter$default(str2, false, 1, (Object) null)), false, (Continuation) continuation, 2, (Object) null);
        return respondRedirect$default == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondRedirect$default : Unit.INSTANCE;
    }

    public static /* synthetic */ Object requestOAuth1aAccessToken$default(HttpClient httpClient, OAuthServerSettings.OAuth1aServerSettings oAuth1aServerSettings, OAuthCallback.TokenPair tokenPair, String str, Map map, Continuation continuation, int i, Object obj) {
        if ((i & 8) != 0) {
            str = CryptoKt.generateNonce();
        }
        String str2 = str;
        if ((i & 16) != 0) {
            map = MapsKt.emptyMap();
        }
        return requestOAuth1aAccessToken(httpClient, oAuth1aServerSettings, tokenPair, str2, map, continuation);
    }

    public static final Object requestOAuth1aAccessToken(HttpClient httpClient, OAuthServerSettings.OAuth1aServerSettings oAuth1aServerSettings, OAuthCallback.TokenPair tokenPair, String str, Map<String, String> map, Continuation<? super OAuthAccessTokenResponse.OAuth1a> continuation) {
        return requestOAuth1aAccessToken(httpClient, oAuth1aServerSettings.getConsumerSecret() + Typography.amp, oAuth1aServerSettings.getAccessTokenUrl(), oAuth1aServerSettings.getConsumerKey(), tokenPair.getToken(), tokenPair.getTokenSecret(), str, map, oAuth1aServerSettings.getAccessTokenInterceptor(), continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0125 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0169  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object requestOAuth1aAccessToken(io.ktor.client.HttpClient r15, java.lang.String r16, java.lang.String r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21, java.util.Map<java.lang.String, java.lang.String> r22, kotlin.jvm.functions.Function1<? super io.ktor.client.request.HttpRequestBuilder, kotlin.Unit> r23, kotlin.coroutines.Continuation<? super io.ktor.server.auth.OAuthAccessTokenResponse.OAuth1a> r24) {
        /*
            r0 = r17
            r1 = r23
            r2 = r24
            boolean r3 = r2 instanceof io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$2
            if (r3 == 0) goto L_0x001a
            r3 = r2
            io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$2 r3 = (io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$2) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$2 r3 = new io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$2
            r3.<init>(r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 2
            r7 = 1
            r8 = 0
            if (r5 == 0) goto L_0x0042
            if (r5 == r7) goto L_0x003d
            if (r5 != r6) goto L_0x0035
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0126
        L_0x0035:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0103
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r2)
            java.lang.String r2 = "oauth_verifier"
            r5 = r20
            kotlin.Pair r2 = kotlin.TuplesKt.to(r2, r5)
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.List r5 = kotlin.collections.MapsKt.toList(r22)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r5)
            r13 = 8
            r14 = 0
            r12 = 0
            r9 = r18
            r10 = r19
            r11 = r21
            io.ktor.http.auth.HttpAuthHeader$Parameterized r5 = createUpgradeRequestTokenHeaderInternal$default(r9, r10, r11, r12, r13, r14)
            io.ktor.http.HttpMethod$Companion r9 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r9 = r9.getPost()
            r10 = r16
            io.ktor.http.auth.HttpAuthHeader$Parameterized r5 = signInternal(r5, r9, r0, r10, r2)
            io.ktor.client.request.HttpRequestBuilder r9 = new io.ktor.client.request.HttpRequestBuilder
            r9.<init>()
            io.ktor.client.request.HttpRequestKt.url((io.ktor.client.request.HttpRequestBuilder) r9, (java.lang.String) r0)
            r0 = r9
            io.ktor.http.HttpMessageBuilder r0 = (io.ktor.http.HttpMessageBuilder) r0
            io.ktor.http.HttpHeaders r10 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r10 = r10.getAuthorization()
            io.ktor.http.auth.HeaderValueEncoding r11 = io.ktor.http.auth.HeaderValueEncoding.URI_ENCODE
            java.lang.String r5 = r5.render(r11)
            io.ktor.client.request.UtilsKt.header(r0, r10, r5)
            io.ktor.http.HttpHeaders r5 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r5 = r5.getAccept()
            java.lang.String r10 = "*/*"
            io.ktor.client.request.UtilsKt.header(r0, r5, r10)
            io.ktor.http.content.WriterContent r0 = new io.ktor.http.content.WriterContent
            io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$body$1$1 r5 = new io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$body$1$1
            r5.<init>(r2, r8)
            r2 = r5
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            io.ktor.http.ContentType$Application r5 = io.ktor.http.ContentType.Application.INSTANCE
            io.ktor.http.ContentType r5 = r5.getFormUrlEncoded()
            r10 = 12
            r11 = 0
            r13 = 0
            r16 = r0
            r17 = r2
            r18 = r5
            r19 = r12
            r20 = r13
            r21 = r10
            r22 = r11
            r16.<init>(r17, r18, r19, r20, r21, r22)
            boolean r2 = r0 instanceof io.ktor.http.content.OutgoingContent
            if (r2 == 0) goto L_0x00cc
            r9.setBody(r0)
            r9.setBodyType(r8)
            goto L_0x00e6
        L_0x00cc:
            r9.setBody(r0)
            java.lang.Class<io.ktor.http.content.WriterContent> r0 = io.ktor.http.content.WriterContent.class
            kotlin.reflect.KType r0 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r0)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r0)
            java.lang.Class<io.ktor.http.content.WriterContent> r5 = io.ktor.http.content.WriterContent.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            io.ktor.util.reflect.TypeInfo r0 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r5, r0)
            r9.setBodyType(r0)
        L_0x00e6:
            if (r1 == 0) goto L_0x00eb
            r1.invoke(r9)
        L_0x00eb:
            io.ktor.http.HttpMethod$Companion r0 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r0 = r0.getPost()
            r9.setMethod(r0)
            io.ktor.client.statement.HttpStatement r0 = new io.ktor.client.statement.HttpStatement
            r1 = r15
            r0.<init>(r9, r15)
            r3.label = r7
            java.lang.Object r2 = r0.execute(r3)
            if (r2 != r4) goto L_0x0103
            return r4
        L_0x0103:
            io.ktor.client.statement.HttpResponse r2 = (io.ktor.client.statement.HttpResponse) r2
            io.ktor.client.call.HttpClientCall r0 = r2.getCall()
            java.lang.Class<java.lang.String> r1 = java.lang.String.class
            kotlin.reflect.KType r1 = kotlin.jvm.internal.Reflection.typeOf((java.lang.Class) r1)
            java.lang.reflect.Type r2 = kotlin.reflect.TypesJVMKt.getJavaType((kotlin.reflect.KType) r1)
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            io.ktor.util.reflect.TypeInfo r1 = io.ktor.util.reflect.TypeInfoJvmKt.typeInfoImpl(r2, r5, r1)
            r3.label = r6
            java.lang.Object r2 = r0.bodyNullable(r1, r3)
            if (r2 != r4) goto L_0x0126
            return r4
        L_0x0126:
            if (r2 == 0) goto L_0x0169
            java.lang.String r2 = (java.lang.String) r2
            r0 = 0
            r1 = 3
            io.ktor.http.Parameters r0 = io.ktor.http.HttpUrlEncodedKt.parseUrlEncodedParameters$default(r2, r8, r0, r1, r8)     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            io.ktor.server.auth.OAuthAccessTokenResponse$OAuth1a r1 = new io.ktor.server.auth.OAuthAccessTokenResponse$OAuth1a     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            java.lang.String r3 = "oauth_token"
            java.lang.String r3 = r0.get(r3)     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            if (r3 == 0) goto L_0x014c
            java.lang.String r4 = "oauth_token_secret"
            java.lang.String r4 = r0.get(r4)     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            if (r4 == 0) goto L_0x0146
            r1.<init>(r3, r4, r0)     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            return r1
        L_0x0146:
            io.ktor.server.auth.OAuth1aException$MissingTokenException r0 = new io.ktor.server.auth.OAuth1aException$MissingTokenException     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            r0.<init>()     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            throw r0     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
        L_0x014c:
            io.ktor.server.auth.OAuth1aException$MissingTokenException r0 = new io.ktor.server.auth.OAuth1aException$MissingTokenException     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            r0.<init>()     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
            throw r0     // Catch:{ OAuth1aException -> 0x0167, all -> 0x0152 }
        L_0x0152:
            r0 = move-exception
            java.io.IOException r1 = new java.io.IOException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Failed to acquire request token due to "
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2, r0)
            throw r1
        L_0x0167:
            r0 = move-exception
            throw r0
        L_0x0169:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException
            java.lang.String r1 = "null cannot be cast to non-null type kotlin.String"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuth1aKt.requestOAuth1aAccessToken(io.ktor.client.HttpClient, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.Map, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object requestOAuth1aAccessToken$default(HttpClient httpClient, String str, String str2, String str3, String str4, String str5, String str6, Map map, Function1 function1, Continuation continuation, int i, Object obj) {
        Map map2;
        int i2 = i;
        String generateNonce = (i2 & 64) != 0 ? CryptoKt.generateNonce() : str6;
        if ((i2 & 128) != 0) {
            map2 = MapsKt.emptyMap();
        } else {
            map2 = map;
        }
        return requestOAuth1aAccessToken(httpClient, str, str2, str3, str4, str5, generateNonce, map2, function1, continuation);
    }

    public static /* synthetic */ HttpAuthHeader.Parameterized createObtainRequestTokenHeader$default(String str, String str2, String str3, LocalDateTime localDateTime, int i, Object obj) {
        if ((i & 8) != 0) {
            localDateTime = LocalDateTime.now();
            Intrinsics.checkNotNullExpressionValue(localDateTime, "now()");
        }
        return createObtainRequestTokenHeader(str, str2, str3, localDateTime);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to become internal. Please file a ticket and clarify, why do you need it.")
    public static final HttpAuthHeader.Parameterized createObtainRequestTokenHeader(String str, String str2, String str3, LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(str, "callback");
        Intrinsics.checkNotNullParameter(str2, "consumerKey");
        Intrinsics.checkNotNullParameter(str3, "nonce");
        Intrinsics.checkNotNullParameter(localDateTime, "timestamp");
        return createObtainRequestTokenHeaderInternal(str, str2, str3, localDateTime);
    }

    static /* synthetic */ HttpAuthHeader.Parameterized createObtainRequestTokenHeaderInternal$default(String str, String str2, String str3, LocalDateTime localDateTime, int i, Object obj) {
        if ((i & 8) != 0) {
            localDateTime = LocalDateTime.now();
            Intrinsics.checkNotNullExpressionValue(localDateTime, "now()");
        }
        return createObtainRequestTokenHeaderInternal(str, str2, str3, localDateTime);
    }

    private static final HttpAuthHeader.Parameterized createObtainRequestTokenHeaderInternal(String str, String str2, String str3, LocalDateTime localDateTime) {
        return new HttpAuthHeader.Parameterized(AuthScheme.OAuth, MapsKt.mapOf(TuplesKt.to(HttpAuthHeader.Parameters.OAuthCallback, str), TuplesKt.to(HttpAuthHeader.Parameters.OAuthConsumerKey, str2), TuplesKt.to(HttpAuthHeader.Parameters.OAuthNonce, str3), TuplesKt.to(HttpAuthHeader.Parameters.OAuthSignatureMethod, "HMAC-SHA1"), TuplesKt.to(HttpAuthHeader.Parameters.OAuthTimestamp, String.valueOf(localDateTime.toEpochSecond(ZoneOffset.UTC))), TuplesKt.to(HttpAuthHeader.Parameters.OAuthVersion, "1.0")), (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ HttpAuthHeader.Parameterized createUpgradeRequestTokenHeader$default(String str, String str2, String str3, LocalDateTime localDateTime, int i, Object obj) {
        if ((i & 8) != 0) {
            localDateTime = LocalDateTime.now();
            Intrinsics.checkNotNullExpressionValue(localDateTime, "now()");
        }
        return createUpgradeRequestTokenHeader(str, str2, str3, localDateTime);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to become internal. Please file a ticket and clarify, why do you need it.")
    public static final HttpAuthHeader.Parameterized createUpgradeRequestTokenHeader(String str, String str2, String str3, LocalDateTime localDateTime) {
        Intrinsics.checkNotNullParameter(str, "consumerKey");
        Intrinsics.checkNotNullParameter(str2, "token");
        Intrinsics.checkNotNullParameter(str3, "nonce");
        Intrinsics.checkNotNullParameter(localDateTime, "timestamp");
        return createUpgradeRequestTokenHeaderInternal(str, str2, str3, localDateTime);
    }

    static /* synthetic */ HttpAuthHeader.Parameterized createUpgradeRequestTokenHeaderInternal$default(String str, String str2, String str3, LocalDateTime localDateTime, int i, Object obj) {
        if ((i & 8) != 0) {
            localDateTime = LocalDateTime.now();
            Intrinsics.checkNotNullExpressionValue(localDateTime, "now()");
        }
        return createUpgradeRequestTokenHeaderInternal(str, str2, str3, localDateTime);
    }

    private static final HttpAuthHeader.Parameterized createUpgradeRequestTokenHeaderInternal(String str, String str2, String str3, LocalDateTime localDateTime) {
        return new HttpAuthHeader.Parameterized(AuthScheme.OAuth, MapsKt.mapOf(TuplesKt.to(HttpAuthHeader.Parameters.OAuthConsumerKey, str), TuplesKt.to(HttpAuthHeader.Parameters.OAuthToken, str2), TuplesKt.to(HttpAuthHeader.Parameters.OAuthNonce, str3), TuplesKt.to(HttpAuthHeader.Parameters.OAuthSignatureMethod, "HMAC-SHA1"), TuplesKt.to(HttpAuthHeader.Parameters.OAuthTimestamp, String.valueOf(localDateTime.toEpochSecond(ZoneOffset.UTC))), TuplesKt.to(HttpAuthHeader.Parameters.OAuthVersion, "1.0")), (HeaderValueEncoding) null, 4, (DefaultConstructorMarker) null);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to become internal. Please file a ticket and clarify, why do you need it.")
    public static final HttpAuthHeader.Parameterized sign(HttpAuthHeader.Parameterized parameterized, HttpMethod httpMethod, String str, String str2, List<Pair<String, String>> list) {
        Intrinsics.checkNotNullParameter(parameterized, "<this>");
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        Intrinsics.checkNotNullParameter(str2, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(list, "parameters");
        return signInternal(parameterized, httpMethod, str, str2, list);
    }

    private static final HttpAuthHeader.Parameterized signInternal(HttpAuthHeader.Parameterized parameterized, HttpMethod httpMethod, String str, String str2, List<Pair<String, String>> list) {
        return parameterized.withParameter(HttpAuthHeader.Parameters.OAuthSignature, hmacSha1(signatureBaseStringInternal(parameterized, httpMethod, str, HttpHeaderValueParserKt.toHeaderParamsList(list)), str2));
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to become internal. Please file a ticket and clarify, why do you need it.")
    public static final String signatureBaseString(HttpAuthHeader.Parameterized parameterized, HttpMethod httpMethod, String str, List<HeaderValueParam> list) {
        Intrinsics.checkNotNullParameter(parameterized, "header");
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        Intrinsics.checkNotNullParameter(list, "parameters");
        return signatureBaseStringInternal(parameterized, httpMethod, str, list);
    }

    public static final String signatureBaseStringInternal(HttpAuthHeader.Parameterized parameterized, HttpMethod httpMethod, String str, List<HeaderValueParam> list) {
        Intrinsics.checkNotNullParameter(parameterized, "header");
        Intrinsics.checkNotNullParameter(httpMethod, "method");
        Intrinsics.checkNotNullParameter(str, "baseUrl");
        Intrinsics.checkNotNullParameter(list, "parameters");
        return CollectionsKt.joinToString$default(CollectionsKt.listOf(TextKt.toUpperCasePreservingASCIIRules(httpMethod.getValue()), str, parametersString(CollectionsKt.plus(parameterized.getParameters(), list))), "&", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, OAuth1aKt$signatureBaseStringInternal$1.INSTANCE, 30, (Object) null);
    }

    private static final String hmacSha1(String str, String str2) {
        byte[] bytes = str2.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, "HmacSHA1");
        Mac instance = Mac.getInstance("HmacSHA1");
        instance.init(secretKeySpec);
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] bytes2 = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
        String encodeToString = encoder.encodeToString(instance.doFinal(bytes2));
        Intrinsics.checkNotNullExpressionValue(encodeToString, "getEncoder().encodeToStr…inal(this.toByteArray()))");
        return encodeToString;
    }

    private static final String parametersString(List<HeaderValueParam> list) {
        Iterable<HeaderValueParam> iterable = list;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (HeaderValueParam headerValueParam : iterable) {
            arrayList.add(TuplesKt.to(CodecsKt.encodeURLParameter$default(headerValueParam.getName(), false, 1, (Object) null), CodecsKt.encodeURLParameter$default(headerValueParam.getValue(), false, 1, (Object) null)));
        }
        return CollectionsKt.joinToString$default(CollectionsKt.sortedWith((List) arrayList, ComparisonsKt.then(new OAuth1aKt$parametersString$$inlined$compareBy$1(), new OAuth1aKt$parametersString$$inlined$compareBy$2())), "&", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, OAuth1aKt$parametersString$4.INSTANCE, 30, (Object) null);
    }

    public static final Object oauth1a(PipelineContext<Unit, ApplicationCall> pipelineContext, HttpClient httpClient, CoroutineDispatcher coroutineDispatcher, Function1<? super ApplicationCall, ? extends OAuthServerSettings> function1, Function2<? super ApplicationCall, ? super OAuthServerSettings, String> function2, Continuation<? super Unit> continuation) {
        OAuthServerSettings oAuthServerSettings = (OAuthServerSettings) function1.invoke(pipelineContext.getContext());
        if (!(oAuthServerSettings instanceof OAuthServerSettings.OAuth1aServerSettings)) {
            return Unit.INSTANCE;
        }
        Object withContext = BuildersKt.withContext(coroutineDispatcher, new OAuth1aKt$oauth1a$2(function2, pipelineContext, oAuthServerSettings, oauth1aHandleCallback(pipelineContext.getContext()), httpClient, (Continuation<? super OAuth1aKt$oauth1a$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
