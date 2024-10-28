package io.ktor.client.plugins;

import androidx.core.app.NotificationCompat;
import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestPipeline;
import io.ktor.client.statement.HttpResponsePipeline;
import io.ktor.http.ContentType;
import io.ktor.http.ContentTypesKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessage;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.TextContent;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0002\u001f BM\b\u0000\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u00060\u0004j\u0002`\u00050\u0003\u0012\u0016\u0010\u0006\u001a\u0012\u0012\b\u0012\u00060\u0004j\u0002`\u0005\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\u0010\t\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005\u0012\n\u0010\n\u001a\u00060\u0004j\u0002`\u0005¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\b\u0013J\u001d\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\"\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00060\u0004j\u0002`\u0005X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u0004j\u0002`\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText;", "", "charsets", "", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "charsetQuality", "", "", "sendCharset", "responseCharsetFallback", "(Ljava/util/Set;Ljava/util/Map;Ljava/nio/charset/Charset;Ljava/nio/charset/Charset;)V", "acceptCharsetHeader", "", "requestCharset", "addCharsetHeaders", "", "context", "Lio/ktor/client/request/HttpRequestBuilder;", "addCharsetHeaders$ktor_client_core", "read", "call", "Lio/ktor/client/call/HttpClientCall;", "body", "Lio/ktor/utils/io/core/Input;", "read$ktor_client_core", "wrapContent", "request", "content", "requestContentType", "Lio/ktor/http/ContentType;", "Config", "Plugin", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpPlainText.kt */
public final class HttpPlainText {
    public static final Plugin Plugin = new Plugin((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AttributeKey<HttpPlainText> key = new AttributeKey<>("HttpPlainText");
    private final String acceptCharsetHeader;
    private final Charset requestCharset;
    private final Charset responseCharsetFallback;

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r12v2, resolved type: java.nio.charset.Charset} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HttpPlainText(java.util.Set<? extends java.nio.charset.Charset> r10, java.util.Map<java.nio.charset.Charset, java.lang.Float> r11, java.nio.charset.Charset r12, java.nio.charset.Charset r13) {
        /*
            r9 = this;
            java.lang.String r0 = "charsets"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "charsetQuality"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "responseCharsetFallback"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            r9.<init>()
            r9.responseCharsetFallback = r13
            java.util.List r13 = kotlin.collections.MapsKt.toList(r11)
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedByDescending$1 r0 = new io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedByDescending$1
            r0.<init>()
            java.util.Comparator r0 = (java.util.Comparator) r0
            java.util.List r13 = kotlin.collections.CollectionsKt.sortedWith(r13, r0)
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r10 = r10.iterator()
        L_0x0032:
            boolean r1 = r10.hasNext()
            if (r1 == 0) goto L_0x004b
            java.lang.Object r1 = r10.next()
            r2 = r1
            java.nio.charset.Charset r2 = (java.nio.charset.Charset) r2
            boolean r2 = r11.containsKey(r2)
            r2 = r2 ^ 1
            if (r2 == 0) goto L_0x0032
            r0.add(r1)
            goto L_0x0032
        L_0x004b:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedBy$1 r10 = new io.ktor.client.plugins.HttpPlainText$special$$inlined$sortedBy$1
            r10.<init>()
            java.util.Comparator r10 = (java.util.Comparator) r10
            java.util.List r10 = kotlin.collections.CollectionsKt.sortedWith(r0, r10)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            r0 = r10
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0066:
            boolean r1 = r0.hasNext()
            java.lang.String r2 = ","
            if (r1 == 0) goto L_0x0088
            java.lang.Object r1 = r0.next()
            java.nio.charset.Charset r1 = (java.nio.charset.Charset) r1
            r3 = r11
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            int r3 = r3.length()
            if (r3 <= 0) goto L_0x0080
            r11.append(r2)
        L_0x0080:
            java.lang.String r1 = io.ktor.utils.io.charsets.CharsetJVMKt.getName(r1)
            r11.append(r1)
            goto L_0x0066
        L_0x0088:
            r0 = r13
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x008f:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00fc
            java.lang.Object r1 = r0.next()
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r3 = r1.component1()
            java.nio.charset.Charset r3 = (java.nio.charset.Charset) r3
            java.lang.Object r1 = r1.component2()
            java.lang.Number r1 = (java.lang.Number) r1
            float r1 = r1.floatValue()
            r4 = r11
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r4 = r4.length()
            if (r4 <= 0) goto L_0x00b7
            r11.append(r2)
        L_0x00b7:
            double r4 = (double) r1
            r6 = 0
            int r8 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r8 > 0) goto L_0x00f0
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r8 > 0) goto L_0x00f0
            r4 = 100
            float r4 = (float) r4
            float r4 = r4 * r1
            int r1 = kotlin.math.MathKt.roundToInt((float) r4)
            double r4 = (double) r1
            r6 = 4636737291354636288(0x4059000000000000, double:100.0)
            java.lang.Double.isNaN(r4)
            double r4 = r4 / r6
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = io.ktor.utils.io.charsets.CharsetJVMKt.getName(r3)
            r1.append(r3)
            java.lang.String r3 = ";q="
            r1.append(r3)
            r1.append(r4)
            java.lang.String r1 = r1.toString()
            r11.append(r1)
            goto L_0x008f
        L_0x00f0:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Check failed."
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x00fc:
            r0 = r11
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            if (r0 != 0) goto L_0x010e
            java.nio.charset.Charset r0 = r9.responseCharsetFallback
            java.lang.String r0 = io.ktor.utils.io.charsets.CharsetJVMKt.getName(r0)
            r11.append(r0)
        L_0x010e:
            java.lang.String r11 = r11.toString()
            java.lang.String r0 = "StringBuilder().apply(builderAction).toString()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r0)
            r9.acceptCharsetHeader = r11
            if (r12 != 0) goto L_0x0139
            java.lang.Object r10 = kotlin.collections.CollectionsKt.firstOrNull(r10)
            r12 = r10
            java.nio.charset.Charset r12 = (java.nio.charset.Charset) r12
            if (r12 != 0) goto L_0x0139
            java.lang.Object r10 = kotlin.collections.CollectionsKt.firstOrNull(r13)
            kotlin.Pair r10 = (kotlin.Pair) r10
            if (r10 == 0) goto L_0x0133
            java.lang.Object r10 = r10.getFirst()
            java.nio.charset.Charset r10 = (java.nio.charset.Charset) r10
            goto L_0x0134
        L_0x0133:
            r10 = 0
        L_0x0134:
            r12 = r10
            if (r12 != 0) goto L_0x0139
            java.nio.charset.Charset r12 = kotlin.text.Charsets.UTF_8
        L_0x0139:
            r9.requestCharset = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.HttpPlainText.<init>(java.util.Set, java.util.Map, java.nio.charset.Charset, java.nio.charset.Charset):void");
    }

    @KtorDsl
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J#\u0010\u0016\u001a\u00020\u00172\n\u0010\u0018\u001a\u00060\u0005j\u0002`\u00062\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u001aR$\u0010\u0003\u001a\u0012\u0012\b\u0012\u00060\u0005j\u0002`\u0006\u0012\u0004\u0012\u00020\u00070\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u00060\u0005j\u0002`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\"\u0010\u0013\u001a\n\u0018\u00010\u0005j\u0004\u0018\u0001`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0004\b\u0015\u0010\u0012¨\u0006\u001b"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText$Config;", "", "()V", "charsetQuality", "", "Ljava/nio/charset/Charset;", "Lio/ktor/utils/io/charsets/Charset;", "", "getCharsetQuality$ktor_client_core", "()Ljava/util/Map;", "charsets", "", "getCharsets$ktor_client_core", "()Ljava/util/Set;", "responseCharsetFallback", "getResponseCharsetFallback", "()Ljava/nio/charset/Charset;", "setResponseCharsetFallback", "(Ljava/nio/charset/Charset;)V", "sendCharset", "getSendCharset", "setSendCharset", "register", "", "charset", "quality", "(Ljava/nio/charset/Charset;Ljava/lang/Float;)V", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpPlainText.kt */
    public static final class Config {
        private final Map<Charset, Float> charsetQuality = new LinkedHashMap();
        private final Set<Charset> charsets = new LinkedHashSet();
        private Charset responseCharsetFallback = Charsets.UTF_8;
        private Charset sendCharset;

        public final Set<Charset> getCharsets$ktor_client_core() {
            return this.charsets;
        }

        public final Map<Charset, Float> getCharsetQuality$ktor_client_core() {
            return this.charsetQuality;
        }

        public static /* synthetic */ void register$default(Config config, Charset charset, Float f, int i, Object obj) {
            if ((i & 2) != 0) {
                f = null;
            }
            config.register(charset, f);
        }

        public final void register(Charset charset, Float f) {
            Intrinsics.checkNotNullParameter(charset, "charset");
            if (f != null) {
                double floatValue = (double) f.floatValue();
                if (0.0d > floatValue || floatValue > 1.0d) {
                    throw new IllegalStateException("Check failed.".toString());
                }
            }
            this.charsets.add(charset);
            if (f == null) {
                this.charsetQuality.remove(charset);
            } else {
                this.charsetQuality.put(charset, f);
            }
        }

        public final Charset getSendCharset() {
            return this.sendCharset;
        }

        public final void setSendCharset(Charset charset) {
            this.sendCharset = charset;
        }

        public final Charset getResponseCharsetFallback() {
            return this.responseCharsetFallback;
        }

        public final void setResponseCharsetFallback(Charset charset) {
            Intrinsics.checkNotNullParameter(charset, "<set-?>");
            this.responseCharsetFallback = charset;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH\u0016J!\u0010\u000e\u001a\u00020\u00032\u0017\u0010\u000f\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\n0\u0010¢\u0006\u0002\b\u0011H\u0016R\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/HttpPlainText$Plugin;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/HttpPlainText$Config;", "Lio/ktor/client/plugins/HttpPlainText;", "()V", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpPlainText.kt */
    public static final class Plugin implements HttpClientPlugin<Config, HttpPlainText> {
        public /* synthetic */ Plugin(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Plugin() {
        }

        public AttributeKey<HttpPlainText> getKey() {
            return HttpPlainText.key;
        }

        public HttpPlainText prepare(Function1<? super Config, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            Config config = new Config();
            function1.invoke(config);
            return new HttpPlainText(config.getCharsets$ktor_client_core(), config.getCharsetQuality$ktor_client_core(), config.getSendCharset(), config.getResponseCharsetFallback());
        }

        public void install(HttpPlainText httpPlainText, HttpClient httpClient) {
            Intrinsics.checkNotNullParameter(httpPlainText, "plugin");
            Intrinsics.checkNotNullParameter(httpClient, OAuth2RequestParameters.Scope);
            httpClient.getRequestPipeline().intercept(HttpRequestPipeline.Phases.getRender(), new HttpPlainText$Plugin$install$1(httpPlainText, (Continuation<? super HttpPlainText$Plugin$install$1>) null));
            httpClient.getResponsePipeline().intercept(HttpResponsePipeline.Phases.getTransform(), new HttpPlainText$Plugin$install$2(httpPlainText, (Continuation<? super HttpPlainText$Plugin$install$2>) null));
        }
    }

    /* access modifiers changed from: private */
    public final Object wrapContent(HttpRequestBuilder httpRequestBuilder, String str, ContentType contentType) {
        Charset charset;
        ContentType plain = contentType == null ? ContentType.Text.INSTANCE.getPlain() : contentType;
        if (contentType == null || (charset = ContentTypesKt.charset(contentType)) == null) {
            charset = this.requestCharset;
        }
        Logger access$getLOGGER$p = HttpPlainTextKt.LOGGER;
        access$getLOGGER$p.trace("Sending request body to " + httpRequestBuilder.getUrl() + " as text/plain with charset " + charset);
        return new TextContent(str, ContentTypesKt.withCharset(plain, charset), (HttpStatusCode) null, 4, (DefaultConstructorMarker) null);
    }

    public final String read$ktor_client_core(HttpClientCall httpClientCall, Input input) {
        Intrinsics.checkNotNullParameter(httpClientCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(input, "body");
        Charset charset = HttpMessagePropertiesKt.charset((HttpMessage) httpClientCall.getResponse());
        if (charset == null) {
            charset = this.responseCharsetFallback;
        }
        Logger access$getLOGGER$p = HttpPlainTextKt.LOGGER;
        access$getLOGGER$p.trace("Reading response body for " + httpClientCall.getRequest().getUrl() + " as String with charset " + charset);
        return StringsKt.readText$default(input, charset, 0, 2, (Object) null);
    }

    public final void addCharsetHeaders$ktor_client_core(HttpRequestBuilder httpRequestBuilder) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "context");
        if (httpRequestBuilder.getHeaders().get(HttpHeaders.INSTANCE.getAcceptCharset()) == null) {
            Logger access$getLOGGER$p = HttpPlainTextKt.LOGGER;
            access$getLOGGER$p.trace("Adding Accept-Charset=" + this.acceptCharsetHeader + " to " + httpRequestBuilder.getUrl());
            httpRequestBuilder.getHeaders().set(HttpHeaders.INSTANCE.getAcceptCharset(), this.acceptCharsetHeader);
        }
    }
}
