package io.ktor.client.plugins.cache;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpClientPlugin;
import io.ktor.client.plugins.cache.storage.CacheStorage;
import io.ktor.client.plugins.cache.storage.CachedResponseData;
import io.ktor.client.plugins.cache.storage.HttpCacheStorage;
import io.ktor.client.plugins.cache.storage.HttpCacheStorageKt;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.request.HttpSendPipeline;
import io.ktor.client.statement.HttpReceivePipeline;
import io.ktor.client.statement.HttpResponse;
import io.ktor.events.EventDefinition;
import io.ktor.http.HeaderValue;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpProtocolVersion;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.GMTDate;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import io.ktor.utils.io.ByteChannelCtorKt;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 *2\u00020\u0001:\u0002*+B7\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u001b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u0018J#\u0010\u0019\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u0017H@ø\u0001\u0000¢\u0006\u0002\u0010\u001cJ?\u0010\u001d\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001e\u001a\u00020\u00062\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020!0 2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\u001bH@ø\u0001\u0000¢\u0006\u0002\u0010$J#\u0010\u001d\u001a\u0004\u0018\u00010\u00152\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H@ø\u0001\u0000¢\u0006\u0002\u0010)R\u0014\u0010\n\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0007\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lio/ktor/client/plugins/cache/HttpCache;", "", "publicStorage", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "privateStorage", "publicStorageNew", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "privateStorageNew", "useOldStorage", "", "isSharedClient", "(Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/plugins/cache/storage/CacheStorage;ZZ)V", "isSharedClient$ktor_client_core", "()Z", "getPrivateStorage$annotations", "()V", "getPrivateStorage", "()Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "getPublicStorage$annotations", "getPublicStorage", "cacheResponse", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "response", "Lio/ktor/client/statement/HttpResponse;", "(Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAndRefresh", "request", "Lio/ktor/client/request/HttpRequest;", "(Lio/ktor/client/request/HttpRequest;Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findResponse", "storage", "varyKeys", "", "", "url", "Lio/ktor/http/Url;", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Ljava/util/Map;Lio/ktor/http/Url;Lio/ktor/client/request/HttpRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "context", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "Lio/ktor/http/content/OutgoingContent;", "(Lio/ktor/client/request/HttpRequestBuilder;Lio/ktor/http/content/OutgoingContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "Config", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCache.kt */
public final class HttpCache {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final EventDefinition<HttpResponse> HttpResponseFromCache = new EventDefinition<>();
    /* access modifiers changed from: private */
    public static final AttributeKey<HttpCache> key = new AttributeKey<>("HttpCache");
    private final boolean isSharedClient;
    private final HttpCacheStorage privateStorage;
    private final CacheStorage privateStorageNew;
    private final HttpCacheStorage publicStorage;
    private final CacheStorage publicStorageNew;
    /* access modifiers changed from: private */
    public final boolean useOldStorage;

    public /* synthetic */ HttpCache(HttpCacheStorage httpCacheStorage, HttpCacheStorage httpCacheStorage2, CacheStorage cacheStorage, CacheStorage cacheStorage2, boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(httpCacheStorage, httpCacheStorage2, cacheStorage, cacheStorage2, z, z2);
    }

    @Deprecated(message = "This will become internal")
    public static /* synthetic */ void getPrivateStorage$annotations() {
    }

    @Deprecated(message = "This will become internal")
    public static /* synthetic */ void getPublicStorage$annotations() {
    }

    private HttpCache(HttpCacheStorage httpCacheStorage, HttpCacheStorage httpCacheStorage2, CacheStorage cacheStorage, CacheStorage cacheStorage2, boolean z, boolean z2) {
        this.publicStorage = httpCacheStorage;
        this.privateStorage = httpCacheStorage2;
        this.publicStorageNew = cacheStorage;
        this.privateStorageNew = cacheStorage2;
        this.useOldStorage = z;
        this.isSharedClient = z2;
    }

    public final HttpCacheStorage getPublicStorage() {
        return this.publicStorage;
    }

    public final HttpCacheStorage getPrivateStorage() {
        return this.privateStorage;
    }

    public final boolean isSharedClient$ktor_client_core() {
        return this.isSharedClient;
    }

    @KtorDsl
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0011J\u000e\u0010\u0016\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0011R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007R,\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8\u0006@FX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R,\u0010\u0016\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\t8\u0006@FX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0017\u0010\u0002\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001a\u0010\u001a\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0013\"\u0004\b\u001c\u0010\u0015R\u001a\u0010\u001d\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0005\"\u0004\b\u001f\u0010\u0007¨\u0006\""}, d2 = {"Lio/ktor/client/plugins/cache/HttpCache$Config;", "", "()V", "isShared", "", "()Z", "setShared", "(Z)V", "value", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "privateStorage", "getPrivateStorage$annotations", "getPrivateStorage", "()Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "setPrivateStorage", "(Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;)V", "privateStorageNew", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "getPrivateStorageNew$ktor_client_core", "()Lio/ktor/client/plugins/cache/storage/CacheStorage;", "setPrivateStorageNew$ktor_client_core", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;)V", "publicStorage", "getPublicStorage$annotations", "getPublicStorage", "setPublicStorage", "publicStorageNew", "getPublicStorageNew$ktor_client_core", "setPublicStorageNew$ktor_client_core", "useOldStorage", "getUseOldStorage$ktor_client_core", "setUseOldStorage$ktor_client_core", "", "storage", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpCache.kt */
    public static final class Config {
        private boolean isShared;
        private HttpCacheStorage privateStorage = HttpCacheStorage.Companion.getUnlimited().invoke();
        private CacheStorage privateStorageNew = CacheStorage.Companion.getUnlimited().invoke();
        private HttpCacheStorage publicStorage = HttpCacheStorage.Companion.getUnlimited().invoke();
        private CacheStorage publicStorageNew = CacheStorage.Companion.getUnlimited().invoke();
        private boolean useOldStorage;

        @Deprecated(message = "This will become internal. Use setter method instead with new storage interface")
        public static /* synthetic */ void getPrivateStorage$annotations() {
        }

        @Deprecated(message = "This will become internal. Use setter method instead with new storage interface")
        public static /* synthetic */ void getPublicStorage$annotations() {
        }

        public final CacheStorage getPublicStorageNew$ktor_client_core() {
            return this.publicStorageNew;
        }

        public final void setPublicStorageNew$ktor_client_core(CacheStorage cacheStorage) {
            Intrinsics.checkNotNullParameter(cacheStorage, "<set-?>");
            this.publicStorageNew = cacheStorage;
        }

        public final CacheStorage getPrivateStorageNew$ktor_client_core() {
            return this.privateStorageNew;
        }

        public final void setPrivateStorageNew$ktor_client_core(CacheStorage cacheStorage) {
            Intrinsics.checkNotNullParameter(cacheStorage, "<set-?>");
            this.privateStorageNew = cacheStorage;
        }

        public final boolean getUseOldStorage$ktor_client_core() {
            return this.useOldStorage;
        }

        public final void setUseOldStorage$ktor_client_core(boolean z) {
            this.useOldStorage = z;
        }

        public final boolean isShared() {
            return this.isShared;
        }

        public final void setShared(boolean z) {
            this.isShared = z;
        }

        public final HttpCacheStorage getPublicStorage() {
            return this.publicStorage;
        }

        public final void setPublicStorage(HttpCacheStorage httpCacheStorage) {
            Intrinsics.checkNotNullParameter(httpCacheStorage, "value");
            this.useOldStorage = true;
            this.publicStorage = httpCacheStorage;
        }

        public final HttpCacheStorage getPrivateStorage() {
            return this.privateStorage;
        }

        public final void setPrivateStorage(HttpCacheStorage httpCacheStorage) {
            Intrinsics.checkNotNullParameter(httpCacheStorage, "value");
            this.useOldStorage = true;
            this.privateStorage = httpCacheStorage;
        }

        public final void publicStorage(CacheStorage cacheStorage) {
            Intrinsics.checkNotNullParameter(cacheStorage, "storage");
            this.publicStorageNew = cacheStorage;
        }

        public final void privateStorage(CacheStorage cacheStorage) {
            Intrinsics.checkNotNullParameter(cacheStorage, "storage");
            this.privateStorageNew = cacheStorage;
        }
    }

    @Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J!\u0010\u0013\u001a\u00020\u00032\u0017\u0010\u0014\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000f0\u0015¢\u0006\u0002\b\u0016H\u0016J3\u0010\u0017\u001a\u00020\u000f*\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00182\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u001cH@ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ+\u0010\u001f\u001a\u00020\u000f*\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00182\u0006\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0004\b \u0010!J9\u0010\"\u001a\u00020\u000f*\u000e\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u00182\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010'R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Lio/ktor/client/plugins/cache/HttpCache$Companion;", "Lio/ktor/client/plugins/HttpClientPlugin;", "Lio/ktor/client/plugins/cache/HttpCache$Config;", "Lio/ktor/client/plugins/cache/HttpCache;", "()V", "HttpResponseFromCache", "Lio/ktor/events/EventDefinition;", "Lio/ktor/client/statement/HttpResponse;", "getHttpResponseFromCache", "()Lio/ktor/events/EventDefinition;", "key", "Lio/ktor/util/AttributeKey;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "proceedWithCache", "Lio/ktor/util/pipeline/PipelineContext;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "cachedCall", "Lio/ktor/client/call/HttpClientCall;", "proceedWithCache$ktor_client_core", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lio/ktor/client/call/HttpClientCall;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proceedWithMissingCache", "proceedWithMissingCache$ktor_client_core", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proceedWithWarning", "cachedResponse", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "callContext", "Lkotlin/coroutines/CoroutineContext;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Lio/ktor/client/HttpClient;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpCache.kt */
    public static final class Companion implements HttpClientPlugin<Config, HttpCache> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public AttributeKey<HttpCache> getKey() {
            return HttpCache.key;
        }

        public final EventDefinition<HttpResponse> getHttpResponseFromCache() {
            return HttpCache.HttpResponseFromCache;
        }

        public HttpCache prepare(Function1<? super Config, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "block");
            Config config = new Config();
            function1.invoke(config);
            return new HttpCache(config.getPublicStorage(), config.getPrivateStorage(), config.getPublicStorageNew$ktor_client_core(), config.getPrivateStorageNew$ktor_client_core(), config.getUseOldStorage$ktor_client_core(), config.isShared(), (DefaultConstructorMarker) null);
        }

        public void install(HttpCache httpCache, HttpClient httpClient) {
            Intrinsics.checkNotNullParameter(httpCache, "plugin");
            Intrinsics.checkNotNullParameter(httpClient, OAuth2RequestParameters.Scope);
            PipelinePhase pipelinePhase = new PipelinePhase("Cache");
            httpClient.getSendPipeline().insertPhaseAfter(HttpSendPipeline.Phases.getState(), pipelinePhase);
            httpClient.getSendPipeline().intercept(pipelinePhase, new HttpCache$Companion$install$1(httpCache, httpClient, (Continuation<? super HttpCache$Companion$install$1>) null));
            httpClient.getReceivePipeline().intercept(HttpReceivePipeline.Phases.getState(), new HttpCache$Companion$install$2(httpCache, httpClient, (Continuation<? super HttpCache$Companion$install$2>) null));
        }

        public final Object proceedWithCache$ktor_client_core(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpClient httpClient, HttpClientCall httpClientCall, Continuation<? super Unit> continuation) {
            pipelineContext.finish();
            httpClient.getMonitor().raise(getHttpResponseFromCache(), httpClientCall.getResponse());
            Object proceedWith = pipelineContext.proceedWith(httpClientCall, continuation);
            return proceedWith == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? proceedWith : Unit.INSTANCE;
        }

        /* access modifiers changed from: private */
        public final Object proceedWithWarning(PipelineContext<Object, HttpRequestBuilder> pipelineContext, CachedResponseData cachedResponseData, HttpClient httpClient, CoroutineContext coroutineContext, Continuation<? super Unit> continuation) {
            HttpRequestData build = pipelineContext.getContext().build();
            HttpStatusCode statusCode = cachedResponseData.getStatusCode();
            GMTDate requestTime = cachedResponseData.getRequestTime();
            Headers.Companion companion = Headers.Companion;
            HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
            headersBuilder.appendAll(cachedResponseData.getHeaders());
            headersBuilder.append(HttpHeaders.INSTANCE.getWarning(), "110");
            Unit unit = Unit.INSTANCE;
            HttpClientCall httpClientCall = new HttpClientCall(httpClient, build, new HttpResponseData(statusCode, requestTime, headersBuilder.build(), cachedResponseData.getVersion(), ByteChannelCtorKt.ByteReadChannel(cachedResponseData.getBody()), coroutineContext));
            pipelineContext.finish();
            httpClient.getMonitor().raise(getHttpResponseFromCache(), httpClientCall.getResponse());
            Object proceedWith = pipelineContext.proceedWith(httpClientCall, continuation);
            return proceedWith == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? proceedWith : Unit.INSTANCE;
        }

        public final Object proceedWithMissingCache$ktor_client_core(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpClient httpClient, Continuation<? super Unit> continuation) {
            pipelineContext.finish();
            HttpRequestData build = pipelineContext.getContext().build();
            Object proceedWith = pipelineContext.proceedWith(new HttpClientCall(httpClient, build, new HttpResponseData(HttpStatusCode.Companion.getGatewayTimeout(), DateJvmKt.GMTDate$default((Long) null, 1, (Object) null), Headers.Companion.getEmpty(), HttpProtocolVersion.Companion.getHTTP_1_1(), ByteChannelCtorKt.ByteReadChannel(new byte[0]), build.getExecutionContext())), continuation);
            return proceedWith == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? proceedWith : Unit.INSTANCE;
        }
    }

    /* access modifiers changed from: private */
    public final Object cacheResponse(HttpResponse httpResponse, Continuation<? super CachedResponseData> continuation) {
        CacheStorage cacheStorage;
        HttpRequest request = httpResponse.getCall().getRequest();
        List<HeaderValue> cacheControl = HttpMessagePropertiesKt.cacheControl(httpResponse);
        List<HeaderValue> cacheControl2 = HttpMessagePropertiesKt.cacheControl(request);
        boolean contains = cacheControl.contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core());
        if (contains && this.isSharedClient) {
            return null;
        }
        if (contains) {
            cacheStorage = this.privateStorageNew;
        } else {
            cacheStorage = this.publicStorageNew;
        }
        if (cacheControl.contains(CacheControl.INSTANCE.getNO_STORE$ktor_client_core()) || cacheControl2.contains(CacheControl.INSTANCE.getNO_STORE$ktor_client_core())) {
            return null;
        }
        return HttpCacheStorageKt.store(cacheStorage, httpResponse, HttpCacheEntryKt.varyKeys(httpResponse), this.isSharedClient, continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00b0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00b1  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object findAndRefresh(io.ktor.client.request.HttpRequest r13, io.ktor.client.statement.HttpResponse r14, kotlin.coroutines.Continuation<? super io.ktor.client.statement.HttpResponse> r15) {
        /*
            r12 = this;
            boolean r0 = r15 instanceof io.ktor.client.plugins.cache.HttpCache$findAndRefresh$1
            if (r0 == 0) goto L_0x0014
            r0 = r15
            io.ktor.client.plugins.cache.HttpCache$findAndRefresh$1 r0 = (io.ktor.client.plugins.cache.HttpCache$findAndRefresh$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.HttpCache$findAndRefresh$1 r0 = new io.ktor.client.plugins.cache.HttpCache$findAndRefresh$1
            r0.<init>(r12, r15)
        L_0x0019:
            java.lang.Object r15 = r0.result
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r0.label
            r2 = 1
            r8 = 2
            r9 = 0
            if (r1 == 0) goto L_0x005b
            if (r1 == r2) goto L_0x0043
            if (r1 != r8) goto L_0x003b
            java.lang.Object r13 = r0.L$2
            io.ktor.client.plugins.cache.storage.CachedResponseData r13 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r13
            java.lang.Object r14 = r0.L$1
            io.ktor.client.statement.HttpResponse r14 = (io.ktor.client.statement.HttpResponse) r14
            java.lang.Object r0 = r0.L$0
            io.ktor.client.request.HttpRequest r0 = (io.ktor.client.request.HttpRequest) r0
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00df
        L_0x003b:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r14 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r14)
            throw r13
        L_0x0043:
            java.lang.Object r13 = r0.L$4
            java.util.Map r13 = (java.util.Map) r13
            java.lang.Object r14 = r0.L$3
            io.ktor.client.plugins.cache.storage.CacheStorage r14 = (io.ktor.client.plugins.cache.storage.CacheStorage) r14
            java.lang.Object r1 = r0.L$2
            io.ktor.client.statement.HttpResponse r1 = (io.ktor.client.statement.HttpResponse) r1
            java.lang.Object r2 = r0.L$1
            io.ktor.client.request.HttpRequest r2 = (io.ktor.client.request.HttpRequest) r2
            java.lang.Object r3 = r0.L$0
            io.ktor.client.plugins.cache.HttpCache r3 = (io.ktor.client.plugins.cache.HttpCache) r3
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00ac
        L_0x005b:
            kotlin.ResultKt.throwOnFailure(r15)
            io.ktor.client.call.HttpClientCall r15 = r14.getCall()
            io.ktor.client.request.HttpRequest r15 = r15.getRequest()
            io.ktor.http.Url r4 = r15.getUrl()
            r15 = r14
            io.ktor.http.HttpMessage r15 = (io.ktor.http.HttpMessage) r15
            java.util.List r15 = io.ktor.http.HttpMessagePropertiesKt.cacheControl(r15)
            io.ktor.client.plugins.cache.CacheControl r1 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r1 = r1.getPRIVATE$ktor_client_core()
            boolean r15 = r15.contains(r1)
            if (r15 == 0) goto L_0x0082
            boolean r1 = r12.isSharedClient
            if (r1 == 0) goto L_0x0082
            return r9
        L_0x0082:
            if (r15 == 0) goto L_0x0087
            io.ktor.client.plugins.cache.storage.CacheStorage r15 = r12.privateStorageNew
            goto L_0x0089
        L_0x0087:
            io.ktor.client.plugins.cache.storage.CacheStorage r15 = r12.publicStorageNew
        L_0x0089:
            java.util.Map r10 = io.ktor.client.plugins.cache.HttpCacheEntryKt.varyKeys(r14)
            r0.L$0 = r12
            r0.L$1 = r13
            r0.L$2 = r14
            r0.L$3 = r15
            r0.L$4 = r10
            r0.label = r2
            r1 = r12
            r2 = r15
            r3 = r10
            r5 = r13
            r6 = r0
            java.lang.Object r1 = r1.findResponse(r2, r3, r4, r5, r6)
            if (r1 != r7) goto L_0x00a5
            return r7
        L_0x00a5:
            r3 = r12
            r2 = r13
            r13 = r10
            r11 = r1
            r1 = r14
            r14 = r15
            r15 = r11
        L_0x00ac:
            io.ktor.client.plugins.cache.storage.CachedResponseData r15 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r15
            if (r15 != 0) goto L_0x00b1
            return r9
        L_0x00b1:
            boolean r4 = r13.isEmpty()
            if (r4 == 0) goto L_0x00bb
            java.util.Map r13 = r15.getVaryKeys()
        L_0x00bb:
            io.ktor.http.Url r4 = r2.getUrl()
            boolean r3 = r3.isSharedClient
            io.ktor.util.date.GMTDate r3 = io.ktor.client.plugins.cache.HttpCacheEntryKt.cacheExpires$default(r1, r3, r9, r8, r9)
            io.ktor.client.plugins.cache.storage.CachedResponseData r13 = r15.copy$ktor_client_core(r13, r3)
            r0.L$0 = r2
            r0.L$1 = r1
            r0.L$2 = r15
            r0.L$3 = r9
            r0.L$4 = r9
            r0.label = r8
            java.lang.Object r13 = r14.store(r4, r13, r0)
            if (r13 != r7) goto L_0x00dc
            return r7
        L_0x00dc:
            r13 = r15
            r14 = r1
            r0 = r2
        L_0x00df:
            io.ktor.client.call.HttpClientCall r15 = r0.getCall()
            io.ktor.client.HttpClient r15 = r15.getClient()
            kotlin.coroutines.CoroutineContext r14 = r14.getCoroutineContext()
            io.ktor.client.statement.HttpResponse r13 = io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.createResponse(r13, r15, r0, r14)
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCache.findAndRefresh(io.ktor.client.request.HttpRequest, io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00d1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object findResponse(io.ktor.client.plugins.cache.storage.CacheStorage r6, java.util.Map<java.lang.String, java.lang.String> r7, io.ktor.http.Url r8, io.ktor.client.request.HttpRequest r9, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof io.ktor.client.plugins.cache.HttpCache$findResponse$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.client.plugins.cache.HttpCache$findResponse$1 r0 = (io.ktor.client.plugins.cache.HttpCache$findResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.HttpCache$findResponse$1 r0 = new io.ktor.client.plugins.cache.HttpCache$findResponse$1
            r0.<init>(r5, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003d
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            java.lang.Object r6 = r0.L$0
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x007b
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0050
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r10)
            boolean r10 = r7.isEmpty()
            r10 = r10 ^ r4
            if (r10 == 0) goto L_0x0051
            r0.label = r4
            java.lang.Object r10 = r6.find(r8, r7, r0)
            if (r10 != r1) goto L_0x0050
            return r1
        L_0x0050:
            return r10
        L_0x0051:
            io.ktor.http.content.OutgoingContent r7 = r9.getContent()
            io.ktor.client.plugins.cache.HttpCache$findResponse$requestHeaders$1 r10 = new io.ktor.client.plugins.cache.HttpCache$findResponse$requestHeaders$1
            io.ktor.http.Headers r2 = r9.getHeaders()
            r10.<init>(r2)
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            io.ktor.client.plugins.cache.HttpCache$findResponse$requestHeaders$2 r2 = new io.ktor.client.plugins.cache.HttpCache$findResponse$requestHeaders$2
            io.ktor.http.Headers r9 = r9.getHeaders()
            r2.<init>(r9)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            kotlin.jvm.functions.Function1 r7 = io.ktor.client.plugins.cache.HttpCacheKt.mergedHeadersLookup(r7, r10, r2)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r10 = r6.findAll(r8, r0)
            if (r10 != r1) goto L_0x007a
            return r1
        L_0x007a:
            r6 = r7
        L_0x007b:
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            io.ktor.client.plugins.cache.HttpCache$findResponse$$inlined$sortedByDescending$1 r7 = new io.ktor.client.plugins.cache.HttpCache$findResponse$$inlined$sortedByDescending$1
            r7.<init>()
            java.util.Comparator r7 = (java.util.Comparator) r7
            java.util.List r7 = kotlin.collections.CollectionsKt.sortedWith(r10, r7)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.Iterator r7 = r7.iterator()
        L_0x008e:
            boolean r8 = r7.hasNext()
            if (r8 == 0) goto L_0x00d1
            java.lang.Object r8 = r7.next()
            r9 = r8
            io.ktor.client.plugins.cache.storage.CachedResponseData r9 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r9
            java.util.Map r9 = r9.getVaryKeys()
            boolean r10 = r9.isEmpty()
            if (r10 == 0) goto L_0x00a6
            goto L_0x00d2
        L_0x00a6:
            java.util.Set r9 = r9.entrySet()
            java.util.Iterator r9 = r9.iterator()
        L_0x00ae:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x00d2
            java.lang.Object r10 = r9.next()
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10
            java.lang.Object r0 = r10.getKey()
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r10 = r10.getValue()
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r0 = r6.invoke(r0)
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r10)
            if (r10 != 0) goto L_0x00ae
            goto L_0x008e
        L_0x00d1:
            r8 = 0
        L_0x00d2:
            io.ktor.client.plugins.cache.storage.CachedResponseData r8 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r8
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCache.findResponse(io.ktor.client.plugins.cache.storage.CacheStorage, java.util.Map, io.ktor.http.Url, io.ktor.client.request.HttpRequest, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009b A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object findResponse(io.ktor.client.request.HttpRequestBuilder r9, io.ktor.http.content.OutgoingContent r10, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r11) {
        /*
            r8 = this;
            boolean r0 = r11 instanceof io.ktor.client.plugins.cache.HttpCache$findResponse$4
            if (r0 == 0) goto L_0x0014
            r0 = r11
            io.ktor.client.plugins.cache.HttpCache$findResponse$4 r0 = (io.ktor.client.plugins.cache.HttpCache$findResponse$4) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.HttpCache$findResponse$4 r0 = new io.ktor.client.plugins.cache.HttpCache$findResponse$4
            r0.<init>(r8, r11)
        L_0x0019:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004f
            if (r2 == r5) goto L_0x003f
            if (r2 != r4) goto L_0x0037
            java.lang.Object r9 = r0.L$1
            java.util.Set r9 = (java.util.Set) r9
            java.lang.Object r10 = r0.L$0
            kotlin.jvm.functions.Function1 r10 = (kotlin.jvm.functions.Function1) r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00a0
        L_0x0037:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003f:
            java.lang.Object r9 = r0.L$2
            kotlin.jvm.functions.Function1 r9 = (kotlin.jvm.functions.Function1) r9
            java.lang.Object r10 = r0.L$1
            io.ktor.http.Url r10 = (io.ktor.http.Url) r10
            java.lang.Object r2 = r0.L$0
            io.ktor.client.plugins.cache.HttpCache r2 = (io.ktor.client.plugins.cache.HttpCache) r2
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0089
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r11)
            io.ktor.http.URLBuilder r11 = r9.getUrl()
            io.ktor.http.Url r11 = io.ktor.http.URLUtilsKt.Url((io.ktor.http.URLBuilder) r11)
            io.ktor.client.plugins.cache.HttpCache$findResponse$lookup$1 r2 = new io.ktor.client.plugins.cache.HttpCache$findResponse$lookup$1
            io.ktor.http.HeadersBuilder r6 = r9.getHeaders()
            r2.<init>(r6)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            io.ktor.client.plugins.cache.HttpCache$findResponse$lookup$2 r6 = new io.ktor.client.plugins.cache.HttpCache$findResponse$lookup$2
            io.ktor.http.HeadersBuilder r9 = r9.getHeaders()
            r6.<init>(r9)
            kotlin.jvm.functions.Function1 r6 = (kotlin.jvm.functions.Function1) r6
            kotlin.jvm.functions.Function1 r9 = io.ktor.client.plugins.cache.HttpCacheKt.mergedHeadersLookup(r10, r2, r6)
            io.ktor.client.plugins.cache.storage.CacheStorage r10 = r8.privateStorageNew
            r0.L$0 = r8
            r0.L$1 = r11
            r0.L$2 = r9
            r0.label = r5
            java.lang.Object r10 = r10.findAll(r11, r0)
            if (r10 != r1) goto L_0x0085
            return r1
        L_0x0085:
            r2 = r8
            r7 = r11
            r11 = r10
            r10 = r7
        L_0x0089:
            java.util.Set r11 = (java.util.Set) r11
            io.ktor.client.plugins.cache.storage.CacheStorage r2 = r2.publicStorageNew
            r0.L$0 = r9
            r0.L$1 = r11
            r0.L$2 = r3
            r0.label = r4
            java.lang.Object r10 = r2.findAll(r10, r0)
            if (r10 != r1) goto L_0x009c
            return r1
        L_0x009c:
            r7 = r10
            r10 = r9
            r9 = r11
            r11 = r7
        L_0x00a0:
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Set r9 = kotlin.collections.SetsKt.plus(r9, r11)
            java.util.Iterator r9 = r9.iterator()
        L_0x00aa:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x00f3
            java.lang.Object r11 = r9.next()
            io.ktor.client.plugins.cache.storage.CachedResponseData r11 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r11
            java.util.Map r0 = r11.getVaryKeys()
            boolean r1 = r0.isEmpty()
            if (r1 != 0) goto L_0x00f2
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L_0x00c7
            goto L_0x00f2
        L_0x00c7:
            java.util.Set r0 = r0.entrySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x00cf:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x00f2
            java.lang.Object r1 = r0.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r2 = r1.getKey()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r1 = r1.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r2 = r10.invoke(r2)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r1)
            if (r1 != 0) goto L_0x00cf
            goto L_0x00aa
        L_0x00f2:
            return r11
        L_0x00f3:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCache.findResponse(io.ktor.client.request.HttpRequestBuilder, io.ktor.http.content.OutgoingContent, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
