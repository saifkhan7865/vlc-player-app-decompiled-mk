package io.ktor.client.plugins.cache.storage;

import io.ktor.client.HttpClient;
import io.ktor.client.call.SavedHttpCall;
import io.ktor.client.plugins.cache.HttpCacheEntryKt;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.statement.HttpResponse;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0000\u001a\u001d\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0001H@ø\u0001\u0000¢\u0006\u0002\u0010\f\u001a1\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u001a;\u0010\t\u001a\u00020\u0002*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000e2\b\b\u0002\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0013\u001a-\u0010\t\u001a\u00020\u0014*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0012H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u0002\u0004\n\u0002\b\u0019¨\u0006\u001a"}, d2 = {"createResponse", "Lio/ktor/client/statement/HttpResponse;", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "client", "Lio/ktor/client/HttpClient;", "request", "Lio/ktor/client/request/HttpRequest;", "responseContext", "Lkotlin/coroutines/CoroutineContext;", "store", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "response", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "varyKeys", "", "", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isShared", "", "(Lio/ktor/client/plugins/cache/storage/CacheStorage;Lio/ktor/client/statement/HttpResponse;Ljava/util/Map;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/client/plugins/cache/HttpCacheEntry;", "Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;", "url", "Lio/ktor/http/Url;", "value", "(Lio/ktor/client/plugins/cache/storage/HttpCacheStorage;Lio/ktor/http/Url;Lio/ktor/client/statement/HttpResponse;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCacheStorage.kt */
public final class HttpCacheStorageKt {
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: io.ktor.http.Url} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object store(io.ktor.client.plugins.cache.storage.HttpCacheStorage r4, io.ktor.http.Url r5, io.ktor.client.statement.HttpResponse r6, boolean r7, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.HttpCacheEntry> r8) {
        /*
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1 r0 = (io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1 r0 = new io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r3) goto L_0x0033
            java.lang.Object r4 = r0.L$1
            r5 = r4
            io.ktor.http.Url r5 = (io.ktor.http.Url) r5
            java.lang.Object r4 = r0.L$0
            io.ktor.client.plugins.cache.storage.HttpCacheStorage r4 = (io.ktor.client.plugins.cache.storage.HttpCacheStorage) r4
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x004b
        L_0x0033:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            java.lang.String r5 = "call to 'resume' before 'invoke' with coroutine"
            r4.<init>(r5)
            throw r4
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r8)
            r0.L$0 = r4
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r8 = io.ktor.client.plugins.cache.HttpCacheEntryKt.HttpCacheEntry(r7, r6, r0)
            if (r8 != r1) goto L_0x004b
            return r1
        L_0x004b:
            io.ktor.client.plugins.cache.HttpCacheEntry r8 = (io.ktor.client.plugins.cache.HttpCacheEntry) r8
            r4.store(r5, r8)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(io.ktor.client.plugins.cache.storage.HttpCacheStorage, io.ktor.http.Url, io.ktor.client.statement.HttpResponse, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Please use method with `response.varyKeys()` and `isShared` arguments", replaceWith = @ReplaceWith(expression = "store(response, response.varyKeys(), isShared)", imports = {}))
    public static final Object store(CacheStorage cacheStorage, HttpResponse httpResponse, Continuation<? super CachedResponseData> continuation) {
        return store$default(cacheStorage, httpResponse, HttpCacheEntryKt.varyKeys(httpResponse), false, continuation, 4, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00d4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object store(io.ktor.client.plugins.cache.storage.CacheStorage r23, io.ktor.client.statement.HttpResponse r24, java.util.Map<java.lang.String, java.lang.String> r25, boolean r26, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r27) {
        /*
            r0 = r27
            boolean r1 = r0 instanceof io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4
            if (r1 == 0) goto L_0x0016
            r1 = r0
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4 r1 = (io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4) r1
            int r2 = r1.label
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r2 & r3
            if (r2 == 0) goto L_0x0016
            int r0 = r1.label
            int r0 = r0 - r3
            r1.label = r0
            goto L_0x001b
        L_0x0016:
            io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4 r1 = new io.ktor.client.plugins.cache.storage.HttpCacheStorageKt$store$4
            r1.<init>(r0)
        L_0x001b:
            java.lang.Object r0 = r1.result
            java.lang.Object r8 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.label
            r9 = 2
            r10 = 1
            if (r2 == 0) goto L_0x0057
            if (r2 == r10) goto L_0x003c
            if (r2 != r9) goto L_0x0034
            java.lang.Object r1 = r1.L$0
            io.ktor.client.plugins.cache.storage.CachedResponseData r1 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L_0x00d6
        L_0x0034:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x003c:
            boolean r2 = r1.Z$0
            java.lang.Object r3 = r1.L$3
            io.ktor.http.Url r3 = (io.ktor.http.Url) r3
            java.lang.Object r4 = r1.L$2
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r5 = r1.L$1
            io.ktor.client.statement.HttpResponse r5 = (io.ktor.client.statement.HttpResponse) r5
            java.lang.Object r6 = r1.L$0
            io.ktor.client.plugins.cache.storage.CacheStorage r6 = (io.ktor.client.plugins.cache.storage.CacheStorage) r6
            kotlin.ResultKt.throwOnFailure(r0)
            r14 = r2
            r21 = r4
            r12 = r5
            r11 = r6
            goto L_0x008e
        L_0x0057:
            kotlin.ResultKt.throwOnFailure(r0)
            io.ktor.client.call.HttpClientCall r0 = r24.getCall()
            io.ktor.client.request.HttpRequest r0 = r0.getRequest()
            io.ktor.http.Url r0 = r0.getUrl()
            io.ktor.utils.io.ByteReadChannel r2 = r24.getContent()
            r11 = r23
            r1.L$0 = r11
            r12 = r24
            r1.L$1 = r12
            r13 = r25
            r1.L$2 = r13
            r1.L$3 = r0
            r14 = r26
            r1.Z$0 = r14
            r1.label = r10
            r3 = 0
            r6 = 1
            r7 = 0
            r5 = r1
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannel.DefaultImpls.readRemaining$default(r2, r3, r5, r6, r7)
            if (r2 != r8) goto L_0x008a
            return r8
        L_0x008a:
            r3 = r0
            r0 = r2
            r21 = r13
        L_0x008e:
            io.ktor.utils.io.core.ByteReadPacket r0 = (io.ktor.utils.io.core.ByteReadPacket) r0
            r2 = 0
            r4 = 0
            byte[] r22 = io.ktor.utils.io.core.StringsKt.readBytes$default(r0, r2, r10, r4)
            io.ktor.client.statement.HttpResponseKt.complete(r12)
            io.ktor.client.call.HttpClientCall r0 = r12.getCall()
            io.ktor.client.request.HttpRequest r0 = r0.getRequest()
            io.ktor.http.Url r0 = r0.getUrl()
            io.ktor.http.HttpStatusCode r15 = r12.getStatus()
            io.ktor.util.date.GMTDate r16 = r12.getRequestTime()
            io.ktor.http.Headers r20 = r12.getHeaders()
            io.ktor.http.HttpProtocolVersion r18 = r12.getVersion()
            io.ktor.util.date.GMTDate r17 = r12.getResponseTime()
            io.ktor.util.date.GMTDate r19 = io.ktor.client.plugins.cache.HttpCacheEntryKt.cacheExpires$default(r12, r14, r4, r9, r4)
            io.ktor.client.plugins.cache.storage.CachedResponseData r2 = new io.ktor.client.plugins.cache.storage.CachedResponseData
            r13 = r2
            r14 = r0
            r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22)
            r1.L$0 = r2
            r1.L$1 = r4
            r1.L$2 = r4
            r1.L$3 = r4
            r1.label = r9
            java.lang.Object r0 = r11.store(r3, r2, r1)
            if (r0 != r8) goto L_0x00d5
            return r8
        L_0x00d5:
            r1 = r2
        L_0x00d6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.store(io.ktor.client.plugins.cache.storage.CacheStorage, io.ktor.client.statement.HttpResponse, java.util.Map, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ Object store$default(CacheStorage cacheStorage, HttpResponse httpResponse, Map map, boolean z, Continuation continuation, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return store(cacheStorage, httpResponse, (Map<String, String>) map, z, (Continuation<? super CachedResponseData>) continuation);
    }

    public static final HttpResponse createResponse(CachedResponseData cachedResponseData, HttpClient httpClient, HttpRequest httpRequest, CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(cachedResponseData, "<this>");
        Intrinsics.checkNotNullParameter(httpClient, "client");
        Intrinsics.checkNotNullParameter(httpRequest, "request");
        Intrinsics.checkNotNullParameter(coroutineContext, "responseContext");
        return new SavedHttpCall(httpClient, httpRequest, new HttpCacheStorageKt$createResponse$response$1(cachedResponseData, coroutineContext), cachedResponseData.getBody()).getResponse();
    }
}
