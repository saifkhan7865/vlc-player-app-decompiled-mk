package io.ktor.client.plugins.cache;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.cache.storage.HttpCacheStorage;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.request.UtilsKt;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaderValueParserKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMessagePropertiesKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.date.GMTDate;
import io.ktor.util.pipeline.PipelineContext;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\u0004\u001a\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0001H\u0002\u001a:\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\n\u001a\u00020\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u001e\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002\u001a9\u0010\u0015\u001a\u00020\u0016*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00160\u00172\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a9\u0010\u001c\u001a\u00020\u0016*\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a1\u0010\u001f\u001a\u00020\u0016*\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00120\u00172\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0019\u001a\u00020\u001aH@ø\u0001\u0000¢\u0006\u0002\u0010\"\u0002\u0004\n\u0002\b\u0019¨\u0006#"}, d2 = {"cacheResponse", "Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/plugins/cache/HttpCache;", "response", "(Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAndRefresh", "request", "Lio/ktor/client/request/HttpRequest;", "findResponse", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "storage", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "varyKeys", "", "", "url", "Lio/ktor/http/Url;", "context", "Lio/ktor/client/request/HttpRequestBuilder;", "content", "Lio/ktor/http/content/OutgoingContent;", "interceptReceiveLegacy", "", "Lio/ktor/util/pipeline/PipelineContext;", "plugin", "scope", "Lio/ktor/client/HttpClient;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/statement/HttpResponse;Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "interceptSendLegacy", "", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/plugins/cache/HttpCache;Lio/ktor/http/content/OutgoingContent;Lio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "proceedWithWarning", "cachedCall", "Lio/ktor/client/call/HttpClientCall;", "(Lio/ktor/util/pipeline/PipelineContext;Lio/ktor/client/call/HttpClientCall;Lio/ktor/client/HttpClient;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCacheLegacy.kt */
public final class HttpCacheLegacyKt {
    public static final Object interceptSendLegacy(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpCache httpCache, OutgoingContent outgoingContent, HttpClient httpClient, Continuation<? super Unit> continuation) {
        Object proceedWithMissingCache$ktor_client_core;
        HttpCacheEntry findResponse = findResponse(httpCache, pipelineContext.getContext(), outgoingContent);
        if (findResponse != null) {
            HttpClientCall call = findResponse.produceResponse$ktor_client_core().getCall();
            ValidateStatus shouldValidate = HttpCacheEntryKt.shouldValidate(findResponse.getExpires(), findResponse.getResponse().getHeaders(), pipelineContext.getContext());
            if (shouldValidate == ValidateStatus.ShouldNotValidate) {
                Object proceedWithCache$ktor_client_core = HttpCache.Companion.proceedWithCache$ktor_client_core(pipelineContext, httpClient, call, continuation);
                if (proceedWithCache$ktor_client_core == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return proceedWithCache$ktor_client_core;
                }
                return Unit.INSTANCE;
            } else if (shouldValidate == ValidateStatus.ShouldWarn) {
                Object proceedWithWarning = proceedWithWarning(pipelineContext, call, httpClient, continuation);
                if (proceedWithWarning == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    return proceedWithWarning;
                }
                return Unit.INSTANCE;
            } else {
                String str = findResponse.getResponseHeaders$ktor_client_core().get(HttpHeaders.INSTANCE.getETag());
                if (str != null) {
                    UtilsKt.header(pipelineContext.getContext(), HttpHeaders.INSTANCE.getIfNoneMatch(), str);
                }
                String str2 = findResponse.getResponseHeaders$ktor_client_core().get(HttpHeaders.INSTANCE.getLastModified());
                if (str2 != null) {
                    UtilsKt.header(pipelineContext.getContext(), HttpHeaders.INSTANCE.getIfModifiedSince(), str2);
                }
                return Unit.INSTANCE;
            }
        } else if (!HttpHeaderValueParserKt.parseHeaderValue(pipelineContext.getContext().getHeaders().get(HttpHeaders.INSTANCE.getCacheControl())).contains(CacheControl.INSTANCE.getONLY_IF_CACHED$ktor_client_core()) || (proceedWithMissingCache$ktor_client_core = HttpCache.Companion.proceedWithMissingCache$ktor_client_core(pipelineContext, httpClient, continuation)) != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            return Unit.INSTANCE;
        } else {
            return proceedWithMissingCache$ktor_client_core;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object interceptReceiveLegacy(io.ktor.util.pipeline.PipelineContext<io.ktor.client.statement.HttpResponse, kotlin.Unit> r6, io.ktor.client.statement.HttpResponse r7, io.ktor.client.plugins.cache.HttpCache r8, io.ktor.client.HttpClient r9, kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            boolean r0 = r10 instanceof io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1 r0 = (io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1 r0 = new io.ktor.client.plugins.cache.HttpCacheLegacyKt$interceptReceiveLegacy$1
            r0.<init>(r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 == r5) goto L_0x003d
            if (r2 == r4) goto L_0x0039
            if (r2 != r3) goto L_0x0031
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00a5
        L_0x0031:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x006b
        L_0x003d:
            java.lang.Object r6 = r0.L$0
            io.ktor.util.pipeline.PipelineContext r6 = (io.ktor.util.pipeline.PipelineContext) r6
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x005d
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r10)
            io.ktor.http.HttpStatusCode r10 = r7.getStatus()
            boolean r10 = io.ktor.http.HttpStatusCodeKt.isSuccess(r10)
            if (r10 == 0) goto L_0x006e
            r0.L$0 = r6
            r0.label = r5
            java.lang.Object r10 = cacheResponse(r8, r7, r0)
            if (r10 != r1) goto L_0x005d
            return r1
        L_0x005d:
            io.ktor.client.statement.HttpResponse r10 = (io.ktor.client.statement.HttpResponse) r10
            r7 = 0
            r0.L$0 = r7
            r0.label = r4
            java.lang.Object r6 = r6.proceedWith(r10, r0)
            if (r6 != r1) goto L_0x006b
            return r1
        L_0x006b:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x006e:
            io.ktor.http.HttpStatusCode r10 = r7.getStatus()
            io.ktor.http.HttpStatusCode$Companion r2 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r2 = r2.getNotModified()
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r2)
            if (r10 == 0) goto L_0x00ba
            io.ktor.client.statement.HttpResponseKt.complete(r7)
            io.ktor.client.call.HttpClientCall r10 = r7.getCall()
            io.ktor.client.request.HttpRequest r10 = r10.getRequest()
            io.ktor.client.statement.HttpResponse r8 = findAndRefresh(r8, r10, r7)
            if (r8 == 0) goto L_0x00a8
            io.ktor.events.Events r7 = r9.getMonitor()
            io.ktor.client.plugins.cache.HttpCache$Companion r9 = io.ktor.client.plugins.cache.HttpCache.Companion
            io.ktor.events.EventDefinition r9 = r9.getHttpResponseFromCache()
            r7.raise(r9, r8)
            r0.label = r3
            java.lang.Object r6 = r6.proceedWith(r8, r0)
            if (r6 != r1) goto L_0x00a5
            return r1
        L_0x00a5:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x00a8:
            io.ktor.client.plugins.cache.InvalidCacheStateException r6 = new io.ktor.client.plugins.cache.InvalidCacheStateException
            io.ktor.client.call.HttpClientCall r7 = r7.getCall()
            io.ktor.client.request.HttpRequest r7 = r7.getRequest()
            io.ktor.http.Url r7 = r7.getUrl()
            r6.<init>(r7)
            throw r6
        L_0x00ba:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheLegacyKt.interceptReceiveLegacy(io.ktor.util.pipeline.PipelineContext, io.ktor.client.statement.HttpResponse, io.ktor.client.plugins.cache.HttpCache, io.ktor.client.HttpClient, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final Object proceedWithWarning(PipelineContext<Object, HttpRequestBuilder> pipelineContext, HttpClientCall httpClientCall, HttpClient httpClient, Continuation<? super Unit> continuation) {
        HttpRequestData build = pipelineContext.getContext().build();
        HttpStatusCode status = httpClientCall.getResponse().getStatus();
        GMTDate requestTime = httpClientCall.getResponse().getRequestTime();
        Headers.Companion companion = Headers.Companion;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        headersBuilder.appendAll(httpClientCall.getResponse().getHeaders());
        headersBuilder.append(HttpHeaders.INSTANCE.getWarning(), "110");
        Unit unit = Unit.INSTANCE;
        HttpClientCall httpClientCall2 = new HttpClientCall(httpClient, build, new HttpResponseData(status, requestTime, headersBuilder.build(), httpClientCall.getResponse().getVersion(), httpClientCall.getResponse().getContent(), httpClientCall.getResponse().getCoroutineContext()));
        pipelineContext.finish();
        httpClient.getMonitor().raise(HttpCache.Companion.getHttpResponseFromCache(), httpClientCall2.getResponse());
        Object proceedWith = pipelineContext.proceedWith(httpClientCall2, continuation);
        return proceedWith == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? proceedWith : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object cacheResponse(io.ktor.client.plugins.cache.HttpCache r7, io.ktor.client.statement.HttpResponse r8, kotlin.coroutines.Continuation<? super io.ktor.client.statement.HttpResponse> r9) {
        /*
            boolean r0 = r9 instanceof io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1 r0 = (io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1 r0 = new io.ktor.client.plugins.cache.HttpCacheLegacyKt$cacheResponse$1
            r0.<init>(r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x008a
        L_0x002a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r9)
            io.ktor.client.call.HttpClientCall r9 = r8.getCall()
            io.ktor.client.request.HttpRequest r9 = r9.getRequest()
            r2 = r8
            io.ktor.http.HttpMessage r2 = (io.ktor.http.HttpMessage) r2
            java.util.List r2 = io.ktor.http.HttpMessagePropertiesKt.cacheControl(r2)
            r4 = r9
            io.ktor.http.HttpMessage r4 = (io.ktor.http.HttpMessage) r4
            java.util.List r4 = io.ktor.http.HttpMessagePropertiesKt.cacheControl(r4)
            io.ktor.client.plugins.cache.CacheControl r5 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r5 = r5.getPRIVATE$ktor_client_core()
            boolean r5 = r2.contains(r5)
            if (r5 == 0) goto L_0x005c
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r5 = r7.getPrivateStorage()
            goto L_0x0060
        L_0x005c:
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r5 = r7.getPublicStorage()
        L_0x0060:
            io.ktor.client.plugins.cache.CacheControl r6 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r6 = r6.getNO_STORE$ktor_client_core()
            boolean r2 = r2.contains(r6)
            if (r2 != 0) goto L_0x0091
            io.ktor.client.plugins.cache.CacheControl r2 = io.ktor.client.plugins.cache.CacheControl.INSTANCE
            io.ktor.http.HeaderValue r2 = r2.getNO_STORE$ktor_client_core()
            boolean r2 = r4.contains(r2)
            if (r2 == 0) goto L_0x0079
            goto L_0x0091
        L_0x0079:
            io.ktor.http.Url r9 = r9.getUrl()
            boolean r7 = r7.isSharedClient$ktor_client_core()
            r0.label = r3
            java.lang.Object r9 = io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store((io.ktor.client.plugins.cache.storage.HttpCacheStorage) r5, (io.ktor.http.Url) r9, (io.ktor.client.statement.HttpResponse) r8, (boolean) r7, (kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.HttpCacheEntry>) r0)
            if (r9 != r1) goto L_0x008a
            return r1
        L_0x008a:
            io.ktor.client.plugins.cache.HttpCacheEntry r9 = (io.ktor.client.plugins.cache.HttpCacheEntry) r9
            io.ktor.client.statement.HttpResponse r7 = r9.produceResponse$ktor_client_core()
            return r7
        L_0x0091:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCacheLegacyKt.cacheResponse(io.ktor.client.plugins.cache.HttpCache, io.ktor.client.statement.HttpResponse, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final HttpResponse findAndRefresh(HttpCache httpCache, HttpRequest httpRequest, HttpResponse httpResponse) {
        Url url = httpResponse.getCall().getRequest().getUrl();
        HttpCacheStorage privateStorage = HttpMessagePropertiesKt.cacheControl(httpResponse).contains(CacheControl.INSTANCE.getPRIVATE$ktor_client_core()) ? httpCache.getPrivateStorage() : httpCache.getPublicStorage();
        Map<String, String> varyKeys = HttpCacheEntryKt.varyKeys(httpResponse);
        HttpCacheEntry findResponse = findResponse(httpCache, privateStorage, varyKeys, url, httpRequest);
        if (findResponse == null) {
            return null;
        }
        if (varyKeys.isEmpty()) {
            varyKeys = findResponse.getVaryKeys();
        }
        privateStorage.store(url, new HttpCacheEntry(HttpCacheEntryKt.cacheExpires$default(httpResponse, httpCache.isSharedClient$ktor_client_core(), (Function0) null, 2, (Object) null), varyKeys, findResponse.getResponse(), findResponse.getBody()));
        return findResponse.produceResponse$ktor_client_core();
    }

    private static final HttpCacheEntry findResponse(HttpCache httpCache, HttpCacheStorage httpCacheStorage, Map<String, String> map, Url url, HttpRequest httpRequest) {
        Object obj;
        if (!map.isEmpty()) {
            return httpCacheStorage.find(url, map);
        }
        Function1<String, String> mergedHeadersLookup = HttpCacheKt.mergedHeadersLookup(httpRequest.getContent(), new HttpCacheLegacyKt$findResponse$requestHeaders$1(httpRequest.getHeaders()), new HttpCacheLegacyKt$findResponse$requestHeaders$2(httpRequest.getHeaders()));
        Iterator it = CollectionsKt.sortedWith(httpCacheStorage.findByUrl(url), new HttpCacheLegacyKt$findResponse$$inlined$sortedByDescending$1()).iterator();
        loop0:
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Map<String, String> varyKeys = ((HttpCacheEntry) obj).getVaryKeys();
            if (!varyKeys.isEmpty()) {
                Iterator<Map.Entry<String, String>> it2 = varyKeys.entrySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break loop0;
                    }
                    Map.Entry next = it2.next();
                    if (!Intrinsics.areEqual((Object) mergedHeadersLookup.invoke((String) next.getKey()), (Object) (String) next.getValue())) {
                    }
                }
            } else {
                break;
            }
        }
        return (HttpCacheEntry) obj;
    }

    private static final HttpCacheEntry findResponse(HttpCache httpCache, HttpRequestBuilder httpRequestBuilder, OutgoingContent outgoingContent) {
        Url Url = URLUtilsKt.Url(httpRequestBuilder.getUrl());
        Function1<String, String> mergedHeadersLookup = HttpCacheKt.mergedHeadersLookup(outgoingContent, new HttpCacheLegacyKt$findResponse$lookup$1(httpRequestBuilder.getHeaders()), new HttpCacheLegacyKt$findResponse$lookup$2(httpRequestBuilder.getHeaders()));
        loop0:
        for (T t : SetsKt.plus(httpCache.getPrivateStorage().findByUrl(Url), httpCache.getPublicStorage().findByUrl(Url))) {
            Map<String, String> varyKeys = t.getVaryKeys();
            if (!varyKeys.isEmpty() && !varyKeys.isEmpty()) {
                Iterator<Map.Entry<String, String>> it = varyKeys.entrySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break loop0;
                    }
                    Map.Entry next = it.next();
                    if (!Intrinsics.areEqual((Object) mergedHeadersLookup.invoke((String) next.getKey()), (Object) (String) next.getValue())) {
                    }
                }
            }
            return t;
        }
        return null;
    }
}
