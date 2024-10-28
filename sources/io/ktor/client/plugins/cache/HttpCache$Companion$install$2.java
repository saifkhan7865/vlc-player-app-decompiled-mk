package io.ktor.client.plugins.cache;

import io.ktor.client.HttpClient;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00022\u0006\u0010\u0004\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/client/statement/HttpResponse;", "response"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.HttpCache$Companion$install$2", f = "HttpCache.kt", i = {1, 1, 3, 3}, l = {188, 194, 198, 206, 210}, m = "invokeSuspend", n = {"$this$intercept", "response", "$this$intercept", "response"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: HttpCache.kt */
final class HttpCache$Companion$install$2 extends SuspendLambda implements Function3<PipelineContext<HttpResponse, Unit>, HttpResponse, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpCache $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpCache$Companion$install$2(HttpCache httpCache, HttpClient httpClient, Continuation<? super HttpCache$Companion$install$2> continuation) {
        super(3, continuation);
        this.$plugin = httpCache;
        this.$scope = httpClient;
    }

    public final Object invoke(PipelineContext<HttpResponse, Unit> pipelineContext, HttpResponse httpResponse, Continuation<? super Unit> continuation) {
        HttpCache$Companion$install$2 httpCache$Companion$install$2 = new HttpCache$Companion$install$2(this.$plugin, this.$scope, continuation);
        httpCache$Companion$install$2.L$0 = pipelineContext;
        httpCache$Companion$install$2.L$1 = httpResponse;
        return httpCache$Companion$install$2.invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x00cf  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00f0  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0101  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x014b  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x016a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 5
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0048
            if (r1 == r6) goto L_0x0044
            if (r1 == r5) goto L_0x0037
            if (r1 == r4) goto L_0x0032
            if (r1 == r3) goto L_0x0025
            if (r1 != r2) goto L_0x001d
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x017c
        L_0x001d:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x0025:
            java.lang.Object r1 = r11.L$1
            io.ktor.client.statement.HttpResponse r1 = (io.ktor.client.statement.HttpResponse) r1
            java.lang.Object r3 = r11.L$0
            io.ktor.util.pipeline.PipelineContext r3 = (io.ktor.util.pipeline.PipelineContext) r3
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0147
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00ed
        L_0x0037:
            java.lang.Object r1 = r11.L$1
            io.ktor.client.statement.HttpResponse r1 = (io.ktor.client.statement.HttpResponse) r1
            java.lang.Object r5 = r11.L$0
            io.ktor.util.pipeline.PipelineContext r5 = (io.ktor.util.pipeline.PipelineContext) r5
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00cb
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0088
        L_0x0048:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            io.ktor.util.pipeline.PipelineContext r12 = (io.ktor.util.pipeline.PipelineContext) r12
            java.lang.Object r1 = r11.L$1
            io.ktor.client.statement.HttpResponse r1 = (io.ktor.client.statement.HttpResponse) r1
            io.ktor.client.call.HttpClientCall r8 = r1.getCall()
            io.ktor.client.request.HttpRequest r8 = r8.getRequest()
            io.ktor.http.HttpMethod r8 = r8.getMethod()
            io.ktor.http.HttpMethod$Companion r9 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r9 = r9.getGet()
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r9)
            if (r8 != 0) goto L_0x006e
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x006e:
            io.ktor.client.plugins.cache.HttpCache r8 = r11.$plugin
            boolean r8 = r8.useOldStorage
            if (r8 == 0) goto L_0x008b
            io.ktor.client.plugins.cache.HttpCache r2 = r11.$plugin
            io.ktor.client.HttpClient r3 = r11.$scope
            r4 = r11
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r11.L$0 = r7
            r11.label = r6
            java.lang.Object r12 = io.ktor.client.plugins.cache.HttpCacheLegacyKt.interceptReceiveLegacy(r12, r1, r2, r3, r4)
            if (r12 != r0) goto L_0x0088
            return r0
        L_0x0088:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x008b:
            io.ktor.http.HttpStatusCode r6 = r1.getStatus()
            boolean r6 = io.ktor.http.HttpStatusCodeKt.isSuccess(r6)
            if (r6 == 0) goto L_0x00f1
            org.slf4j.Logger r6 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Caching response for "
            r8.<init>(r9)
            io.ktor.client.call.HttpClientCall r9 = r1.getCall()
            io.ktor.client.request.HttpRequest r9 = r9.getRequest()
            io.ktor.http.Url r9 = r9.getUrl()
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r6.trace(r8)
            io.ktor.client.plugins.cache.HttpCache r6 = r11.$plugin
            r8 = r11
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r11.L$0 = r12
            r11.L$1 = r1
            r11.label = r5
            java.lang.Object r5 = r6.cacheResponse(r1, r8)
            if (r5 != r0) goto L_0x00c8
            return r0
        L_0x00c8:
            r10 = r5
            r5 = r12
            r12 = r10
        L_0x00cb:
            io.ktor.client.plugins.cache.storage.CachedResponseData r12 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r12
            if (r12 == 0) goto L_0x00f0
            io.ktor.client.HttpClient r2 = r11.$scope
            io.ktor.client.request.HttpRequest r3 = io.ktor.client.statement.HttpResponseKt.getRequest(r1)
            kotlin.coroutines.CoroutineContext r1 = r1.getCoroutineContext()
            io.ktor.client.statement.HttpResponse r12 = io.ktor.client.plugins.cache.storage.HttpCacheStorageKt.createResponse(r12, r2, r3, r1)
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.L$0 = r7
            r11.L$1 = r7
            r11.label = r4
            java.lang.Object r12 = r5.proceedWith(r12, r1)
            if (r12 != r0) goto L_0x00ed
            return r0
        L_0x00ed:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        L_0x00f0:
            r12 = r5
        L_0x00f1:
            io.ktor.http.HttpStatusCode r4 = r1.getStatus()
            io.ktor.http.HttpStatusCode$Companion r5 = io.ktor.http.HttpStatusCode.Companion
            io.ktor.http.HttpStatusCode r5 = r5.getNotModified()
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r4, (java.lang.Object) r5)
            if (r4 == 0) goto L_0x017c
            org.slf4j.Logger r4 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = "Not modified response for "
            r5.<init>(r6)
            io.ktor.client.call.HttpClientCall r6 = r1.getCall()
            io.ktor.client.request.HttpRequest r6 = r6.getRequest()
            io.ktor.http.Url r6 = r6.getUrl()
            r5.append(r6)
            java.lang.String r6 = ", replying from cache"
            r5.append(r6)
            java.lang.String r5 = r5.toString()
            r4.trace(r5)
            io.ktor.client.statement.HttpResponseKt.complete(r1)
            io.ktor.client.plugins.cache.HttpCache r4 = r11.$plugin
            io.ktor.client.call.HttpClientCall r5 = r1.getCall()
            io.ktor.client.request.HttpRequest r5 = r5.getRequest()
            r6 = r11
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r11.L$0 = r12
            r11.L$1 = r1
            r11.label = r3
            java.lang.Object r3 = r4.findAndRefresh(r5, r1, r6)
            if (r3 != r0) goto L_0x0144
            return r0
        L_0x0144:
            r10 = r3
            r3 = r12
            r12 = r10
        L_0x0147:
            io.ktor.client.statement.HttpResponse r12 = (io.ktor.client.statement.HttpResponse) r12
            if (r12 == 0) goto L_0x016a
            io.ktor.client.HttpClient r1 = r11.$scope
            io.ktor.events.Events r1 = r1.getMonitor()
            io.ktor.client.plugins.cache.HttpCache$Companion r4 = io.ktor.client.plugins.cache.HttpCache.Companion
            io.ktor.events.EventDefinition r4 = r4.getHttpResponseFromCache()
            r1.raise(r4, r12)
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.L$0 = r7
            r11.L$1 = r7
            r11.label = r2
            java.lang.Object r12 = r3.proceedWith(r12, r1)
            if (r12 != r0) goto L_0x017c
            return r0
        L_0x016a:
            io.ktor.client.plugins.cache.InvalidCacheStateException r12 = new io.ktor.client.plugins.cache.InvalidCacheStateException
            io.ktor.client.call.HttpClientCall r0 = r1.getCall()
            io.ktor.client.request.HttpRequest r0 = r0.getRequest()
            io.ktor.http.Url r0 = r0.getUrl()
            r12.<init>(r0)
            throw r12
        L_0x017c:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.HttpCache$Companion$install$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
