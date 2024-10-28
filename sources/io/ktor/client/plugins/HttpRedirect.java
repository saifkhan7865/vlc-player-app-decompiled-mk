package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.events.EventDefinition;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00072\u00020\u0001:\u0002\u0006\u0007B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/ktor/client/plugins/HttpRedirect;", "", "checkHttpMethod", "", "allowHttpsDowngrade", "(ZZ)V", "Config", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpRedirect.kt */
public final class HttpRedirect {
    /* access modifiers changed from: private */
    public static final EventDefinition<HttpResponse> HttpResponseRedirect = new EventDefinition<>();
    public static final Plugin Plugin = new Plugin((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AttributeKey<HttpRedirect> key = new AttributeKey<>("HttpRedirect");
    /* access modifiers changed from: private */
    public final boolean allowHttpsDowngrade;
    /* access modifiers changed from: private */
    public final boolean checkHttpMethod;

    public /* synthetic */ HttpRedirect(boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2);
    }

    private HttpRedirect(boolean z, boolean z2) {
        this.checkHttpMethod = z;
        this.allowHttpsDowngrade = z2;
    }

    @KtorDsl
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lio/ktor/client/plugins/HttpRedirect$Config;", "", "()V", "allowHttpsDowngrade", "", "getAllowHttpsDowngrade", "()Z", "setAllowHttpsDowngrade", "(Z)V", "checkHttpMethod", "getCheckHttpMethod", "setCheckHttpMethod", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpRedirect.kt */
    public static final class Config {
        private boolean allowHttpsDowngrade;
        private boolean checkHttpMethod = true;

        public final boolean getCheckHttpMethod() {
            return this.checkHttpMethod;
        }

        public final void setCheckHttpMethod(boolean z) {
            this.checkHttpMethod = z;
        }

        public final boolean getAllowHttpsDowngrade() {
            return this.allowHttpsDowngrade;
        }

        public final void setAllowHttpsDowngrade(boolean z) {
            this.allowHttpsDowngrade = z;
        }
    }

    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J!\u0010\u0013\u001a\u00020\u00032\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u0015¢\u0006\u0002\b\u0016H\u0016J5\u0010\u0017\u001a\u00020\u0018*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010 R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006!"}, d2 = {"Lio/ktor/client/plugins/HttpRedirect$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpRedirect$Config;", "Lio/ktor/client/plugins/HttpRedirect;", "()V", "HttpResponseRedirect", "Lio/ktor/events/EventDefinition;", "Lio/ktor/client/statement/HttpResponse;", "getHttpResponseRedirect", "()Lio/ktor/events/EventDefinition;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "handleCall", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "context", "Lio/ktor/client/request/HttpRequestBuilder;", "origin", "allowHttpsDowngrade", "", "client", "(Lio/ktor/client/plugins/Sender;Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/client/call/HttpClientCall;ZLio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpRedirect.kt */
    public static final class Plugin implements HttpClientPlugin<Config, HttpRedirect> {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        public AttributeKey<HttpRedirect> getKey() {
            return HttpRedirect.key;
        }

        public final EventDefinition<HttpResponse> getHttpResponseRedirect() {
            return HttpRedirect.HttpResponseRedirect;
        }

        public HttpRedirect prepare(Function1<? super Config, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            Config config = new Config();
            function1.invoke(config);
            return new HttpRedirect(config.getCheckHttpMethod(), config.getAllowHttpsDowngrade(), (DefaultConstructorMarker) null);
        }

        public void install(HttpRedirect httpRedirect, HttpClient httpClient) {
            Intrinsics.checkNotNullParameter(httpRedirect, "plugin");
            Intrinsics.checkNotNullParameter(httpClient, OAuth2RequestParameters.Scope);
            ((HttpSend) HttpClientPluginKt.plugin(httpClient, HttpSend.Plugin)).intercept((Function3<? super Sender, ? super HttpRequestBuilder, ? super Continuation<? super HttpClientCall>, ? extends Object>) new HttpRedirect$Plugin$install$1(httpRedirect, httpClient, (Continuation<? super HttpRedirect$Plugin$install$1>) null));
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x006d  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0116  */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0133  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0154  */
        /* JADX WARNING: Removed duplicated region for block: B:28:0x0162  */
        /* JADX WARNING: Removed duplicated region for block: B:31:0x01ab A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x01ac  */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x01c9  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object handleCall(io.ktor.client.plugins.Sender r18, io.ktor.client.request.HttpRequestBuilder r19, io.ktor.client.call.HttpClientCall r20, boolean r21, io.ktor.client.HttpClient r22, kotlin.coroutines.Continuation<? super io.ktor.client.call.HttpClientCall> r23) {
            /*
                r17 = this;
                r0 = r20
                r1 = r23
                boolean r2 = r1 instanceof io.ktor.client.plugins.HttpRedirect$Plugin$handleCall$1
                if (r2 == 0) goto L_0x001a
                r2 = r1
                io.ktor.client.plugins.HttpRedirect$Plugin$handleCall$1 r2 = (io.ktor.client.plugins.HttpRedirect$Plugin$handleCall$1) r2
                int r3 = r2.label
                r4 = -2147483648(0xffffffff80000000, float:-0.0)
                r3 = r3 & r4
                if (r3 == 0) goto L_0x001a
                int r1 = r2.label
                int r1 = r1 - r4
                r2.label = r1
                r3 = r17
                goto L_0x0021
            L_0x001a:
                io.ktor.client.plugins.HttpRedirect$Plugin$handleCall$1 r2 = new io.ktor.client.plugins.HttpRedirect$Plugin$handleCall$1
                r3 = r17
                r2.<init>(r3, r1)
            L_0x0021:
                java.lang.Object r1 = r2.result
                java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r5 = r2.label
                r6 = 1
                if (r5 == 0) goto L_0x006d
                if (r5 != r6) goto L_0x0065
                boolean r0 = r2.Z$0
                java.lang.Object r5 = r2.L$8
                kotlin.jvm.internal.Ref$ObjectRef r5 = (kotlin.jvm.internal.Ref.ObjectRef) r5
                java.lang.Object r7 = r2.L$7
                java.lang.String r7 = (java.lang.String) r7
                java.lang.Object r8 = r2.L$6
                io.ktor.http.URLProtocol r8 = (io.ktor.http.URLProtocol) r8
                java.lang.Object r9 = r2.L$5
                kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
                java.lang.Object r10 = r2.L$4
                kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
                java.lang.Object r11 = r2.L$3
                io.ktor.client.HttpClient r11 = (io.ktor.client.HttpClient) r11
                java.lang.Object r12 = r2.L$2
                io.ktor.client.request.HttpRequestBuilder r12 = (io.ktor.client.request.HttpRequestBuilder) r12
                java.lang.Object r13 = r2.L$1
                io.ktor.client.plugins.Sender r13 = (io.ktor.client.plugins.Sender) r13
                java.lang.Object r14 = r2.L$0
                io.ktor.client.plugins.HttpRedirect$Plugin r14 = (io.ktor.client.plugins.HttpRedirect.Plugin) r14
                kotlin.ResultKt.throwOnFailure(r1)
                r6 = r2
                r2 = r0
                r0 = r13
                r13 = r9
                r9 = r7
                r7 = r12
                r12 = 1
                r16 = r10
                r10 = r8
                r8 = r16
                goto L_0x01b2
            L_0x0065:
                java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
                java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
                r0.<init>(r1)
                throw r0
            L_0x006d:
                kotlin.ResultKt.throwOnFailure(r1)
                io.ktor.client.statement.HttpResponse r1 = r20.getResponse()
                io.ktor.http.HttpStatusCode r1 = r1.getStatus()
                boolean r1 = io.ktor.client.plugins.HttpRedirectKt.isRedirect(r1)
                if (r1 != 0) goto L_0x007f
                return r0
            L_0x007f:
                kotlin.jvm.internal.Ref$ObjectRef r1 = new kotlin.jvm.internal.Ref$ObjectRef
                r1.<init>()
                r1.element = r0
                kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
                r5.<init>()
                r7 = r19
                r5.element = r7
                io.ktor.client.request.HttpRequest r8 = r20.getRequest()
                io.ktor.http.Url r8 = r8.getUrl()
                io.ktor.http.URLProtocol r8 = r8.getProtocol()
                io.ktor.client.request.HttpRequest r0 = r20.getRequest()
                io.ktor.http.Url r0 = r0.getUrl()
                java.lang.String r0 = io.ktor.http.UrlKt.getAuthority(r0)
                r9 = r0
                r14 = r3
                r11 = r5
                r10 = r8
                r0 = r18
                r8 = r1
                r5 = r2
                r1 = r21
                r2 = r22
            L_0x00b3:
                io.ktor.events.Events r12 = r2.getMonitor()
                io.ktor.events.EventDefinition r13 = r14.getHttpResponseRedirect()
                T r15 = r8.element
                io.ktor.client.call.HttpClientCall r15 = (io.ktor.client.call.HttpClientCall) r15
                io.ktor.client.statement.HttpResponse r15 = r15.getResponse()
                r12.raise(r13, r15)
                T r12 = r8.element
                io.ktor.client.call.HttpClientCall r12 = (io.ktor.client.call.HttpClientCall) r12
                io.ktor.client.statement.HttpResponse r12 = r12.getResponse()
                io.ktor.http.Headers r12 = r12.getHeaders()
                io.ktor.http.HttpHeaders r13 = io.ktor.http.HttpHeaders.INSTANCE
                java.lang.String r13 = r13.getLocation()
                java.lang.String r12 = r12.get(r13)
                org.slf4j.Logger r13 = io.ktor.client.plugins.HttpRedirectKt.LOGGER
                java.lang.StringBuilder r15 = new java.lang.StringBuilder
                java.lang.String r6 = "Received redirect response to "
                r15.<init>(r6)
                r15.append(r12)
                java.lang.String r6 = " for request "
                r15.append(r6)
                io.ktor.http.URLBuilder r6 = r7.getUrl()
                r15.append(r6)
                java.lang.String r6 = r15.toString()
                r13.trace(r6)
                io.ktor.client.request.HttpRequestBuilder r6 = new io.ktor.client.request.HttpRequestBuilder
                r6.<init>()
                T r13 = r11.element
                io.ktor.client.request.HttpRequestBuilder r13 = (io.ktor.client.request.HttpRequestBuilder) r13
                r6.takeFromWithExecutionContext(r13)
                io.ktor.http.URLBuilder r13 = r6.getUrl()
                io.ktor.http.ParametersBuilder r13 = r13.getParameters()
                r13.clear()
                if (r12 == 0) goto L_0x011d
                io.ktor.http.URLBuilder r13 = r6.getUrl()
                io.ktor.http.URLParserKt.takeFrom(r13, r12)
            L_0x011d:
                if (r1 != 0) goto L_0x0154
                boolean r12 = io.ktor.http.URLProtocolKt.isSecure(r10)
                if (r12 == 0) goto L_0x0154
                io.ktor.http.URLBuilder r12 = r6.getUrl()
                io.ktor.http.URLProtocol r12 = r12.getProtocol()
                boolean r12 = io.ktor.http.URLProtocolKt.isSecure(r12)
                if (r12 != 0) goto L_0x0154
                org.slf4j.Logger r0 = io.ktor.client.plugins.HttpRedirectKt.LOGGER
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "Can not redirect "
                r1.<init>(r2)
                io.ktor.http.URLBuilder r2 = r7.getUrl()
                r1.append(r2)
                java.lang.String r2 = " because of security downgrade"
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                r0.trace(r1)
                T r0 = r8.element
                return r0
            L_0x0154:
                io.ktor.http.URLBuilder r12 = r6.getUrl()
                java.lang.String r12 = io.ktor.http.URLBuilderKt.getAuthority(r12)
                boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r12)
                if (r12 != 0) goto L_0x0188
                io.ktor.http.HeadersBuilder r12 = r6.getHeaders()
                io.ktor.http.HttpHeaders r13 = io.ktor.http.HttpHeaders.INSTANCE
                java.lang.String r13 = r13.getAuthorization()
                r12.remove(r13)
                org.slf4j.Logger r12 = io.ktor.client.plugins.HttpRedirectKt.LOGGER
                java.lang.StringBuilder r13 = new java.lang.StringBuilder
                java.lang.String r15 = "Removing Authorization header from redirect for "
                r13.<init>(r15)
                io.ktor.http.URLBuilder r15 = r7.getUrl()
                r13.append(r15)
                java.lang.String r13 = r13.toString()
                r12.trace(r13)
            L_0x0188:
                r11.element = r6
                T r6 = r11.element
                io.ktor.client.request.HttpRequestBuilder r6 = (io.ktor.client.request.HttpRequestBuilder) r6
                r5.L$0 = r14
                r5.L$1 = r0
                r5.L$2 = r7
                r5.L$3 = r2
                r5.L$4 = r8
                r5.L$5 = r11
                r5.L$6 = r10
                r5.L$7 = r9
                r5.L$8 = r8
                r5.Z$0 = r1
                r12 = 1
                r5.label = r12
                java.lang.Object r6 = r0.execute(r6, r5)
                if (r6 != r4) goto L_0x01ac
                return r4
            L_0x01ac:
                r13 = r11
                r11 = r2
                r2 = r1
                r1 = r6
                r6 = r5
                r5 = r8
            L_0x01b2:
                r5.element = r1
                T r1 = r8.element
                io.ktor.client.call.HttpClientCall r1 = (io.ktor.client.call.HttpClientCall) r1
                io.ktor.client.statement.HttpResponse r1 = r1.getResponse()
                io.ktor.http.HttpStatusCode r1 = r1.getStatus()
                boolean r1 = io.ktor.client.plugins.HttpRedirectKt.isRedirect(r1)
                if (r1 != 0) goto L_0x01c9
                T r0 = r8.element
                return r0
            L_0x01c9:
                r1 = r2
                r5 = r6
                r2 = r11
                r11 = r13
                r6 = 1
                goto L_0x00b3
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpRedirect.Plugin.handleCall(io.ktor.client.plugins.Sender, io.ktor.client.request.HttpRequestBuilder, io.ktor.client.call.HttpClientCall, boolean, io.ktor.client.HttpClient, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
