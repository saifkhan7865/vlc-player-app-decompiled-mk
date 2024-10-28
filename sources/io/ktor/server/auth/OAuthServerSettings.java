package io.ktor.server.auth;

import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpMethod;
import io.ktor.http.URLBuilder;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.NonceManager;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u000b\fB\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0001\u0002\r\u000e¨\u0006\u000f"}, d2 = {"Lio/ktor/server/auth/OAuthServerSettings;", "", "name", "", "version", "Lio/ktor/server/auth/OAuthVersion;", "(Ljava/lang/String;Lio/ktor/server/auth/OAuthVersion;)V", "getName", "()Ljava/lang/String;", "getVersion", "()Lio/ktor/server/auth/OAuthVersion;", "OAuth1aServerSettings", "OAuth2ServerSettings", "Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;", "Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuthCommon.kt */
public abstract class OAuthServerSettings {
    private final String name;
    private final OAuthVersion version;

    public /* synthetic */ OAuthServerSettings(String str, OAuthVersion oAuthVersion, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, oAuthVersion);
    }

    private OAuthServerSettings(String str, OAuthVersion oAuthVersion) {
        this.name = str;
        this.version = oAuthVersion;
    }

    public final String getName() {
        return this.name;
    }

    public final OAuthVersion getVersion() {
        return this.version;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001BP\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0019\b\u0002\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\u0002\u0010\u000eR\"\u0010\t\u001a\u0013\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\n¢\u0006\u0002\b\r¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012¨\u0006\u0017"}, d2 = {"Lio/ktor/server/auth/OAuthServerSettings$OAuth1aServerSettings;", "Lio/ktor/server/auth/OAuthServerSettings;", "name", "", "requestTokenUrl", "authorizeUrl", "accessTokenUrl", "consumerKey", "consumerSecret", "accessTokenInterceptor", "Lkotlin/Function1;", "Lio/ktor/client/request/HttpRequestBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "getAccessTokenInterceptor", "()Lkotlin/jvm/functions/Function1;", "getAccessTokenUrl", "()Ljava/lang/String;", "getAuthorizeUrl", "getConsumerKey", "getConsumerSecret", "getRequestTokenUrl", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuthCommon.kt */
    public static final class OAuth1aServerSettings extends OAuthServerSettings {
        private final Function1<HttpRequestBuilder, Unit> accessTokenInterceptor;
        private final String accessTokenUrl;
        private final String authorizeUrl;
        private final String consumerKey;
        private final String consumerSecret;
        private final String requestTokenUrl;

        public final String getRequestTokenUrl() {
            return this.requestTokenUrl;
        }

        public final String getAuthorizeUrl() {
            return this.authorizeUrl;
        }

        public final String getAccessTokenUrl() {
            return this.accessTokenUrl;
        }

        public final String getConsumerKey() {
            return this.consumerKey;
        }

        public final String getConsumerSecret() {
            return this.consumerSecret;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ OAuth1aServerSettings(java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13, java.lang.String r14, java.lang.String r15, kotlin.jvm.functions.Function1 r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
            /*
                r9 = this;
                r0 = r17 & 64
                if (r0 == 0) goto L_0x000a
                io.ktor.server.auth.OAuthServerSettings$OAuth1aServerSettings$1 r0 = io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings.AnonymousClass1.INSTANCE
                kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
                r8 = r0
                goto L_0x000c
            L_0x000a:
                r8 = r16
            L_0x000c:
                r1 = r9
                r2 = r10
                r3 = r11
                r4 = r12
                r5 = r13
                r6 = r14
                r7 = r15
                r1.<init>(r2, r3, r4, r5, r6, r7, r8)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final Function1<HttpRequestBuilder, Unit> getAccessTokenInterceptor() {
            return this.accessTokenInterceptor;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OAuth1aServerSettings(String str, String str2, String str3, String str4, String str5, String str6, Function1<? super HttpRequestBuilder, Unit> function1) {
            super(str, OAuthVersion.V10a, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(str2, "requestTokenUrl");
            Intrinsics.checkNotNullParameter(str3, "authorizeUrl");
            Intrinsics.checkNotNullParameter(str4, "accessTokenUrl");
            Intrinsics.checkNotNullParameter(str5, "consumerKey");
            Intrinsics.checkNotNullParameter(str6, "consumerSecret");
            Intrinsics.checkNotNullParameter(function1, "accessTokenInterceptor");
            this.requestTokenUrl = str2;
            this.authorizeUrl = str3;
            this.accessTokenUrl = str4;
            this.consumerKey = str5;
            this.consumerSecret = str6;
            this.accessTokenInterceptor = function1;
        }
    }

    @Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0018\u0018\u00002\u00020\u0001B\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\u0019\b\u0002\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\u0010\u0018BÕ\u0001\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\u001a\b\u0002\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001a0\u000b\u0012\u001a\b\u0002\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001a0\u000b\u0012\u0019\b\u0002\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\u0002\u0010\u001cB \u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\u0019\b\u0002\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\r\u0012\u001a\b\u0002\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001a0\u000b\u0012\u001a\b\u0002\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001a0\u000b\u0012\u0019\b\u0002\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014\u0012H\b\u0002\u0010\u001d\u001aB\b\u0001\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b \u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130#\u0012\u0006\u0012\u0004\u0018\u00010$0\u001eø\u0001\u0000¢\u0006\u0002\u0010%R\"\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b,\u0010+R\"\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00130\u0011¢\u0006\u0002\b\u0014¢\u0006\b\n\u0000\u001a\u0004\b-\u0010'R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010+R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b/\u0010+R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R#\u0010\u0019\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001a0\u000b¢\u0006\b\n\u0000\u001a\u0004\b2\u00101R#\u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u001a0\u000b¢\u0006\b\n\u0000\u001a\u0004\b3\u00101R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b4\u00105RV\u0010\u001d\u001aB\b\u0001\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b \u0012\b\b\u0002\u0012\u0004\b\b(!\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b \u0012\b\b\u0002\u0012\u0004\b\b(\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130#\u0012\u0006\u0012\u0004\u0018\u00010$0\u001eø\u0001\u0000¢\u0006\n\n\u0002\u00108\u001a\u0004\b6\u00107R\u0011\u0010\u0015\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b9\u0010)R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;\u0002\u0004\n\u0002\b\u0019¨\u0006<"}, d2 = {"Lio/ktor/server/auth/OAuthServerSettings$OAuth2ServerSettings;", "Lio/ktor/server/auth/OAuthServerSettings;", "name", "", "authorizeUrl", "accessTokenUrl", "requestMethod", "Lio/ktor/http/HttpMethod;", "clientId", "clientSecret", "defaultScopes", "", "accessTokenRequiresBasicAuth", "", "nonceManager", "Lio/ktor/util/NonceManager;", "authorizeUrlInterceptor", "Lkotlin/Function1;", "Lio/ktor/http/URLBuilder;", "", "Lkotlin/ExtensionFunctionType;", "passParamsInURL", "accessTokenInterceptor", "Lio/ktor/client/request/HttpRequestBuilder;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLio/ktor/util/NonceManager;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;)V", "extraAuthParameters", "Lkotlin/Pair;", "extraTokenParameters", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLio/ktor/util/NonceManager;Lkotlin/jvm/functions/Function1;ZLjava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "onStateCreated", "Lkotlin/Function3;", "Lio/ktor/server/application/ApplicationCall;", "Lkotlin/ParameterName;", "call", "state", "Lkotlin/coroutines/Continuation;", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/HttpMethod;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;ZLio/ktor/util/NonceManager;Lkotlin/jvm/functions/Function1;ZLjava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;)V", "getAccessTokenInterceptor", "()Lkotlin/jvm/functions/Function1;", "getAccessTokenRequiresBasicAuth", "()Z", "getAccessTokenUrl", "()Ljava/lang/String;", "getAuthorizeUrl", "getAuthorizeUrlInterceptor", "getClientId", "getClientSecret", "getDefaultScopes", "()Ljava/util/List;", "getExtraAuthParameters", "getExtraTokenParameters", "getNonceManager", "()Lio/ktor/util/NonceManager;", "getOnStateCreated", "()Lkotlin/jvm/functions/Function3;", "Lkotlin/jvm/functions/Function3;", "getPassParamsInURL", "getRequestMethod", "()Lio/ktor/http/HttpMethod;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuthCommon.kt */
    public static final class OAuth2ServerSettings extends OAuthServerSettings {
        private final Function1<HttpRequestBuilder, Unit> accessTokenInterceptor;
        private final boolean accessTokenRequiresBasicAuth;
        private final String accessTokenUrl;
        private final String authorizeUrl;
        private final Function1<URLBuilder, Unit> authorizeUrlInterceptor;
        private final String clientId;
        private final String clientSecret;
        private final List<String> defaultScopes;
        private final List<Pair<String, String>> extraAuthParameters;
        private final List<Pair<String, String>> extraTokenParameters;
        private final NonceManager nonceManager;
        private final Function3<ApplicationCall, String, Continuation<? super Unit>, Object> onStateCreated;
        private final boolean passParamsInURL;
        private final HttpMethod requestMethod;

        public final String getAuthorizeUrl() {
            return this.authorizeUrl;
        }

        public final String getAccessTokenUrl() {
            return this.accessTokenUrl;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ OAuth2ServerSettings(java.lang.String r19, java.lang.String r20, java.lang.String r21, io.ktor.http.HttpMethod r22, java.lang.String r23, java.lang.String r24, java.util.List r25, boolean r26, io.ktor.util.NonceManager r27, kotlin.jvm.functions.Function1 r28, boolean r29, java.util.List r30, java.util.List r31, kotlin.jvm.functions.Function1 r32, kotlin.jvm.functions.Function3 r33, int r34, kotlin.jvm.internal.DefaultConstructorMarker r35) {
            /*
                r18 = this;
                r0 = r34
                r1 = r0 & 8
                if (r1 == 0) goto L_0x000e
                io.ktor.http.HttpMethod$Companion r1 = io.ktor.http.HttpMethod.Companion
                io.ktor.http.HttpMethod r1 = r1.getGet()
                r6 = r1
                goto L_0x0010
            L_0x000e:
                r6 = r22
            L_0x0010:
                r1 = r0 & 64
                if (r1 == 0) goto L_0x001a
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r9 = r1
                goto L_0x001c
            L_0x001a:
                r9 = r25
            L_0x001c:
                r1 = r0 & 128(0x80, float:1.794E-43)
                r2 = 0
                if (r1 == 0) goto L_0x0023
                r10 = 0
                goto L_0x0025
            L_0x0023:
                r10 = r26
            L_0x0025:
                r1 = r0 & 256(0x100, float:3.59E-43)
                if (r1 == 0) goto L_0x002f
                io.ktor.util.GenerateOnlyNonceManager r1 = io.ktor.util.GenerateOnlyNonceManager.INSTANCE
                io.ktor.util.NonceManager r1 = (io.ktor.util.NonceManager) r1
                r11 = r1
                goto L_0x0031
            L_0x002f:
                r11 = r27
            L_0x0031:
                r1 = r0 & 512(0x200, float:7.175E-43)
                if (r1 == 0) goto L_0x003b
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$1 r1 = io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.AnonymousClass1.INSTANCE
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                r12 = r1
                goto L_0x003d
            L_0x003b:
                r12 = r28
            L_0x003d:
                r1 = r0 & 1024(0x400, float:1.435E-42)
                if (r1 == 0) goto L_0x0043
                r13 = 0
                goto L_0x0045
            L_0x0043:
                r13 = r29
            L_0x0045:
                r1 = r0 & 2048(0x800, float:2.87E-42)
                if (r1 == 0) goto L_0x004f
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r14 = r1
                goto L_0x0051
            L_0x004f:
                r14 = r30
            L_0x0051:
                r1 = r0 & 4096(0x1000, float:5.74E-42)
                if (r1 == 0) goto L_0x005b
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r15 = r1
                goto L_0x005d
            L_0x005b:
                r15 = r31
            L_0x005d:
                r1 = r0 & 8192(0x2000, float:1.14794E-41)
                if (r1 == 0) goto L_0x0068
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$2 r1 = io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.AnonymousClass2.INSTANCE
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                r16 = r1
                goto L_0x006a
            L_0x0068:
                r16 = r32
            L_0x006a:
                r0 = r0 & 16384(0x4000, float:2.2959E-41)
                if (r0 == 0) goto L_0x0079
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$3 r0 = new io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$3
                r1 = 0
                r0.<init>(r1)
                kotlin.jvm.functions.Function3 r0 = (kotlin.jvm.functions.Function3) r0
                r17 = r0
                goto L_0x007b
            L_0x0079:
                r17 = r33
            L_0x007b:
                r2 = r18
                r3 = r19
                r4 = r20
                r5 = r21
                r7 = r23
                r8 = r24
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.<init>(java.lang.String, java.lang.String, java.lang.String, io.ktor.http.HttpMethod, java.lang.String, java.lang.String, java.util.List, boolean, io.ktor.util.NonceManager, kotlin.jvm.functions.Function1, boolean, java.util.List, java.util.List, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        public final HttpMethod getRequestMethod() {
            return this.requestMethod;
        }

        public final String getClientId() {
            return this.clientId;
        }

        public final String getClientSecret() {
            return this.clientSecret;
        }

        public final List<String> getDefaultScopes() {
            return this.defaultScopes;
        }

        public final boolean getAccessTokenRequiresBasicAuth() {
            return this.accessTokenRequiresBasicAuth;
        }

        public final NonceManager getNonceManager() {
            return this.nonceManager;
        }

        public final Function1<URLBuilder, Unit> getAuthorizeUrlInterceptor() {
            return this.authorizeUrlInterceptor;
        }

        public final boolean getPassParamsInURL() {
            return this.passParamsInURL;
        }

        public final List<Pair<String, String>> getExtraAuthParameters() {
            return this.extraAuthParameters;
        }

        public final List<Pair<String, String>> getExtraTokenParameters() {
            return this.extraTokenParameters;
        }

        public final Function1<HttpRequestBuilder, Unit> getAccessTokenInterceptor() {
            return this.accessTokenInterceptor;
        }

        public final Function3<ApplicationCall, String, Continuation<? super Unit>, Object> getOnStateCreated() {
            return this.onStateCreated;
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public OAuth2ServerSettings(java.lang.String r17, java.lang.String r18, java.lang.String r19, io.ktor.http.HttpMethod r20, java.lang.String r21, java.lang.String r22, java.util.List<java.lang.String> r23, boolean r24, io.ktor.util.NonceManager r25, kotlin.jvm.functions.Function1<? super io.ktor.http.URLBuilder, kotlin.Unit> r26, boolean r27, java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> r28, java.util.List<kotlin.Pair<java.lang.String, java.lang.String>> r29, kotlin.jvm.functions.Function1<? super io.ktor.client.request.HttpRequestBuilder, kotlin.Unit> r30, kotlin.jvm.functions.Function3<? super io.ktor.server.application.ApplicationCall, ? super java.lang.String, ? super kotlin.coroutines.Continuation<? super kotlin.Unit>, ? extends java.lang.Object> r31) {
            /*
                r16 = this;
                r0 = r16
                r1 = r17
                r2 = r18
                r3 = r19
                r4 = r20
                r5 = r21
                r6 = r22
                r7 = r23
                r8 = r25
                r9 = r26
                r10 = r28
                r11 = r29
                r12 = r30
                r13 = r31
                java.lang.String r14 = "name"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r14)
                java.lang.String r14 = "authorizeUrl"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r14)
                java.lang.String r14 = "accessTokenUrl"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r14)
                java.lang.String r14 = "requestMethod"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r14)
                java.lang.String r14 = "clientId"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r14)
                java.lang.String r14 = "clientSecret"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r14)
                java.lang.String r14 = "defaultScopes"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r14)
                java.lang.String r14 = "nonceManager"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r14)
                java.lang.String r14 = "authorizeUrlInterceptor"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r14)
                java.lang.String r14 = "extraAuthParameters"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r14)
                java.lang.String r14 = "extraTokenParameters"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r14)
                java.lang.String r14 = "accessTokenInterceptor"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r14)
                java.lang.String r14 = "onStateCreated"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r14)
                io.ktor.server.auth.OAuthVersion r14 = io.ktor.server.auth.OAuthVersion.V20
                r15 = 0
                r0.<init>(r1, r14, r15)
                r0.authorizeUrl = r2
                r0.accessTokenUrl = r3
                r0.requestMethod = r4
                r0.clientId = r5
                r0.clientSecret = r6
                r0.defaultScopes = r7
                r1 = r24
                r0.accessTokenRequiresBasicAuth = r1
                r0.nonceManager = r8
                r0.authorizeUrlInterceptor = r9
                r1 = r27
                r0.passParamsInURL = r1
                r0.extraAuthParameters = r10
                r0.extraTokenParameters = r11
                r0.accessTokenInterceptor = r12
                r0.onStateCreated = r13
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.<init>(java.lang.String, java.lang.String, java.lang.String, io.ktor.http.HttpMethod, java.lang.String, java.lang.String, java.util.List, boolean, io.ktor.util.NonceManager, kotlin.jvm.functions.Function1, boolean, java.util.List, java.util.List, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ OAuth2ServerSettings(java.lang.String r16, java.lang.String r17, java.lang.String r18, io.ktor.http.HttpMethod r19, java.lang.String r20, java.lang.String r21, java.util.List r22, boolean r23, io.ktor.util.NonceManager r24, kotlin.jvm.functions.Function1 r25, boolean r26, kotlin.jvm.functions.Function1 r27, int r28, kotlin.jvm.internal.DefaultConstructorMarker r29) {
            /*
                r15 = this;
                r0 = r28
                r1 = r0 & 8
                if (r1 == 0) goto L_0x000e
                io.ktor.http.HttpMethod$Companion r1 = io.ktor.http.HttpMethod.Companion
                io.ktor.http.HttpMethod r1 = r1.getGet()
                r6 = r1
                goto L_0x0010
            L_0x000e:
                r6 = r19
            L_0x0010:
                r1 = r0 & 64
                if (r1 == 0) goto L_0x001a
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r9 = r1
                goto L_0x001c
            L_0x001a:
                r9 = r22
            L_0x001c:
                r1 = r0 & 128(0x80, float:1.794E-43)
                r2 = 0
                if (r1 == 0) goto L_0x0023
                r10 = 0
                goto L_0x0025
            L_0x0023:
                r10 = r23
            L_0x0025:
                r1 = r0 & 256(0x100, float:3.59E-43)
                if (r1 == 0) goto L_0x002f
                io.ktor.util.GenerateOnlyNonceManager r1 = io.ktor.util.GenerateOnlyNonceManager.INSTANCE
                io.ktor.util.NonceManager r1 = (io.ktor.util.NonceManager) r1
                r11 = r1
                goto L_0x0031
            L_0x002f:
                r11 = r24
            L_0x0031:
                r1 = r0 & 512(0x200, float:7.175E-43)
                if (r1 == 0) goto L_0x003b
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$4 r1 = io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.AnonymousClass4.INSTANCE
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                r12 = r1
                goto L_0x003d
            L_0x003b:
                r12 = r25
            L_0x003d:
                r1 = r0 & 1024(0x400, float:1.435E-42)
                if (r1 == 0) goto L_0x0043
                r13 = 0
                goto L_0x0045
            L_0x0043:
                r13 = r26
            L_0x0045:
                r0 = r0 & 2048(0x800, float:2.87E-42)
                if (r0 == 0) goto L_0x004f
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$5 r0 = io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.AnonymousClass5.INSTANCE
                kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
                r14 = r0
                goto L_0x0051
            L_0x004f:
                r14 = r27
            L_0x0051:
                r2 = r15
                r3 = r16
                r4 = r17
                r5 = r18
                r7 = r20
                r8 = r21
                r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.<init>(java.lang.String, java.lang.String, java.lang.String, io.ktor.http.HttpMethod, java.lang.String, java.lang.String, java.util.List, boolean, io.ktor.util.NonceManager, kotlin.jvm.functions.Function1, boolean, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.HIDDEN, message = "This constructor will be removed")
        public /* synthetic */ OAuth2ServerSettings(String str, String str2, String str3, HttpMethod httpMethod, String str4, String str5, List list, boolean z, NonceManager nonceManager2, Function1 function1, boolean z2, Function1 function12) {
            this(str, str2, str3, httpMethod, str4, str5, list, z, nonceManager2, function1, z2, CollectionsKt.emptyList(), CollectionsKt.emptyList(), function12, (Function3) null, 16384, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
            Intrinsics.checkNotNullParameter(str2, "authorizeUrl");
            Intrinsics.checkNotNullParameter(str3, "accessTokenUrl");
            Intrinsics.checkNotNullParameter(httpMethod, "requestMethod");
            Intrinsics.checkNotNullParameter(str4, "clientId");
            Intrinsics.checkNotNullParameter(str5, "clientSecret");
            Intrinsics.checkNotNullParameter(list, "defaultScopes");
            Intrinsics.checkNotNullParameter(nonceManager2, "nonceManager");
            Intrinsics.checkNotNullParameter(function1, "authorizeUrlInterceptor");
            Intrinsics.checkNotNullParameter(function12, "accessTokenInterceptor");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ OAuth2ServerSettings(java.lang.String r18, java.lang.String r19, java.lang.String r20, io.ktor.http.HttpMethod r21, java.lang.String r22, java.lang.String r23, java.util.List r24, boolean r25, io.ktor.util.NonceManager r26, kotlin.jvm.functions.Function1 r27, boolean r28, java.util.List r29, java.util.List r30, kotlin.jvm.functions.Function1 r31, int r32, kotlin.jvm.internal.DefaultConstructorMarker r33) {
            /*
                r17 = this;
                r0 = r32
                r1 = r0 & 8
                if (r1 == 0) goto L_0x000e
                io.ktor.http.HttpMethod$Companion r1 = io.ktor.http.HttpMethod.Companion
                io.ktor.http.HttpMethod r1 = r1.getGet()
                r6 = r1
                goto L_0x0010
            L_0x000e:
                r6 = r21
            L_0x0010:
                r1 = r0 & 64
                if (r1 == 0) goto L_0x001a
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r9 = r1
                goto L_0x001c
            L_0x001a:
                r9 = r24
            L_0x001c:
                r1 = r0 & 128(0x80, float:1.794E-43)
                r2 = 0
                if (r1 == 0) goto L_0x0023
                r10 = 0
                goto L_0x0025
            L_0x0023:
                r10 = r25
            L_0x0025:
                r1 = r0 & 256(0x100, float:3.59E-43)
                if (r1 == 0) goto L_0x002f
                io.ktor.util.GenerateOnlyNonceManager r1 = io.ktor.util.GenerateOnlyNonceManager.INSTANCE
                io.ktor.util.NonceManager r1 = (io.ktor.util.NonceManager) r1
                r11 = r1
                goto L_0x0031
            L_0x002f:
                r11 = r26
            L_0x0031:
                r1 = r0 & 512(0x200, float:7.175E-43)
                if (r1 == 0) goto L_0x003b
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$6 r1 = io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.AnonymousClass6.INSTANCE
                kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
                r12 = r1
                goto L_0x003d
            L_0x003b:
                r12 = r27
            L_0x003d:
                r1 = r0 & 1024(0x400, float:1.435E-42)
                if (r1 == 0) goto L_0x0043
                r13 = 0
                goto L_0x0045
            L_0x0043:
                r13 = r28
            L_0x0045:
                r1 = r0 & 2048(0x800, float:2.87E-42)
                if (r1 == 0) goto L_0x004f
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r14 = r1
                goto L_0x0051
            L_0x004f:
                r14 = r29
            L_0x0051:
                r1 = r0 & 4096(0x1000, float:5.74E-42)
                if (r1 == 0) goto L_0x005b
                java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                r15 = r1
                goto L_0x005d
            L_0x005b:
                r15 = r30
            L_0x005d:
                r0 = r0 & 8192(0x2000, float:1.14794E-41)
                if (r0 == 0) goto L_0x0068
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$7 r0 = io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.AnonymousClass7.INSTANCE
                kotlin.jvm.functions.Function1 r0 = (kotlin.jvm.functions.Function1) r0
                r16 = r0
                goto L_0x006a
            L_0x0068:
                r16 = r31
            L_0x006a:
                r2 = r17
                r3 = r18
                r4 = r19
                r5 = r20
                r7 = r22
                r8 = r23
                r2.<init>((java.lang.String) r3, (java.lang.String) r4, (java.lang.String) r5, (io.ktor.http.HttpMethod) r6, (java.lang.String) r7, (java.lang.String) r8, (java.util.List) r9, (boolean) r10, (io.ktor.util.NonceManager) r11, (kotlin.jvm.functions.Function1) r12, (boolean) r13, (java.util.List) r14, (java.util.List) r15, (kotlin.jvm.functions.Function1) r16)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.<init>(java.lang.String, java.lang.String, java.lang.String, io.ktor.http.HttpMethod, java.lang.String, java.lang.String, java.util.List, boolean, io.ktor.util.NonceManager, kotlin.jvm.functions.Function1, boolean, java.util.List, java.util.List, kotlin.jvm.functions.Function1, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        @kotlin.Deprecated(level = kotlin.DeprecationLevel.HIDDEN, message = "This constructor will be removed")
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public /* synthetic */ OAuth2ServerSettings(java.lang.String r18, java.lang.String r19, java.lang.String r20, io.ktor.http.HttpMethod r21, java.lang.String r22, java.lang.String r23, java.util.List r24, boolean r25, io.ktor.util.NonceManager r26, kotlin.jvm.functions.Function1 r27, boolean r28, java.util.List r29, java.util.List r30, kotlin.jvm.functions.Function1 r31) {
            /*
                r17 = this;
                java.lang.String r0 = "name"
                r2 = r18
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                java.lang.String r0 = "authorizeUrl"
                r3 = r19
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                java.lang.String r0 = "accessTokenUrl"
                r4 = r20
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "requestMethod"
                r5 = r21
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                java.lang.String r0 = "clientId"
                r6 = r22
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "clientSecret"
                r7 = r23
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
                java.lang.String r0 = "defaultScopes"
                r8 = r24
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
                java.lang.String r0 = "nonceManager"
                r10 = r26
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                java.lang.String r0 = "authorizeUrlInterceptor"
                r11 = r27
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
                java.lang.String r0 = "extraAuthParameters"
                r13 = r29
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
                java.lang.String r0 = "extraTokenParameters"
                r14 = r30
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
                java.lang.String r0 = "accessTokenInterceptor"
                r15 = r31
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
                io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$8 r0 = new io.ktor.server.auth.OAuthServerSettings$OAuth2ServerSettings$8
                r1 = 0
                r0.<init>(r1)
                r16 = r0
                kotlin.jvm.functions.Function3 r16 = (kotlin.jvm.functions.Function3) r16
                r1 = r17
                r9 = r25
                r12 = r28
                r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuthServerSettings.OAuth2ServerSettings.<init>(java.lang.String, java.lang.String, java.lang.String, io.ktor.http.HttpMethod, java.lang.String, java.lang.String, java.util.List, boolean, io.ktor.util.NonceManager, kotlin.jvm.functions.Function1, boolean, java.util.List, java.util.List, kotlin.jvm.functions.Function1):void");
        }
    }
}
