package io.ktor.client.call;

import io.ktor.client.HttpClient;
import io.ktor.client.request.DefaultHttpRequest;
import io.ktor.client.request.HttpRequest;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.request.HttpResponseData;
import io.ktor.client.statement.DefaultHttpResponse;
import io.ktor.client.statement.HttpResponse;
import io.ktor.util.AttributeKey;
import io.ktor.util.Attributes;
import io.ktor.util.InternalAPI;
import io.ktor.utils.io.ByteReadChannel;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\b\u0016\u0018\u0000 82\u00020::\u00018B!\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\tJ\u001b\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u001d\u0010\u000f\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\nH@ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u000eJ\u0013\u0010\u0011\u001a\u00020\u0010H@ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0017\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u0019H\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 R\u001a\u0010\"\u001a\u00020!8\u0014XD¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0011\u0010)\u001a\u00020&8F¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0017\u0010\u0002\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0002\u0010*\u001a\u0004\b+\u0010,R\u0014\u00100\u001a\u00020-8VX\u0004¢\u0006\u0006\u001a\u0004\b.\u0010/R*\u0010\u0014\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00138\u0006@DX.¢\u0006\u0012\n\u0004\b\u0014\u00102\u001a\u0004\b3\u00104\"\u0004\b\u0018\u0010\u0017R*\u0010\u001a\u001a\u00020\u00192\u0006\u00101\u001a\u00020\u00198\u0006@DX.¢\u0006\u0012\n\u0004\b\u001a\u00105\u001a\u0004\b6\u00107\"\u0004\b\u001d\u0010\u001c\u0002\u0004\n\u0002\b\u0019¨\u00069"}, d2 = {"Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/HttpClient;", "client", "Lio/ktor/client/request/HttpRequestData;", "requestData", "Lio/ktor/client/request/HttpResponseData;", "responseData", "<init>", "(Lio/ktor/client/HttpClient;Lio/ktor/client/request/HttpRequestData;Lio/ktor/client/request/HttpResponseData;)V", "(Lio/ktor/client/HttpClient;)V", "Lio/ktor/util/reflect/TypeInfo;", "info", "", "body", "(Lio/ktor/util/reflect/TypeInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bodyNullable", "Lio/ktor/utils/io/ByteReadChannel;", "getResponseContent", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/client/request/HttpRequest;", "request", "", "setRequest$ktor_client_core", "(Lio/ktor/client/request/HttpRequest;)V", "setRequest", "Lio/ktor/client/statement/HttpResponse;", "response", "setResponse$ktor_client_core", "(Lio/ktor/client/statement/HttpResponse;)V", "setResponse", "", "toString", "()Ljava/lang/String;", "", "allowDoubleReceive", "Z", "getAllowDoubleReceive", "()Z", "Lio/ktor/util/Attributes;", "getAttributes", "()Lio/ktor/util/Attributes;", "attributes", "Lio/ktor/client/HttpClient;", "getClient", "()Lio/ktor/client/HttpClient;", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "<set-?>", "Lio/ktor/client/request/HttpRequest;", "getRequest", "()Lio/ktor/client/request/HttpRequest;", "Lio/ktor/client/statement/HttpResponse;", "getResponse", "()Lio/ktor/client/statement/HttpResponse;", "Companion", "ktor-client-core", "Lkotlinx/coroutines/CoroutineScope;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpClientCall.kt */
public class HttpClientCall implements CoroutineScope {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AttributeKey<Object> CustomResponse = new AttributeKey<>("CustomResponse");
    private static final /* synthetic */ AtomicIntegerFieldUpdater received$FU = AtomicIntegerFieldUpdater.newUpdater(HttpClientCall.class, "received");
    private final boolean allowDoubleReceive;
    private final HttpClient client;
    private volatile /* synthetic */ int received;
    protected HttpRequest request;
    protected HttpResponse response;

    /* access modifiers changed from: protected */
    public Object getResponseContent(Continuation<? super ByteReadChannel> continuation) {
        return getResponse().getContent();
    }

    public HttpClientCall(HttpClient httpClient) {
        Intrinsics.checkNotNullParameter(httpClient, "client");
        this.client = httpClient;
        this.received = 0;
    }

    public final HttpClient getClient() {
        return this.client;
    }

    public CoroutineContext getCoroutineContext() {
        return getResponse().getCoroutineContext();
    }

    public final Attributes getAttributes() {
        return getRequest().getAttributes();
    }

    public final HttpRequest getRequest() {
        HttpRequest httpRequest = this.request;
        if (httpRequest != null) {
            return httpRequest;
        }
        Intrinsics.throwUninitializedPropertyAccessException("request");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setRequest(HttpRequest httpRequest) {
        Intrinsics.checkNotNullParameter(httpRequest, "<set-?>");
        this.request = httpRequest;
    }

    public final HttpResponse getResponse() {
        HttpResponse httpResponse = this.response;
        if (httpResponse != null) {
            return httpResponse;
        }
        Intrinsics.throwUninitializedPropertyAccessException("response");
        return null;
    }

    /* access modifiers changed from: protected */
    public final void setResponse(HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "<set-?>");
        this.response = httpResponse;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    @InternalAPI
    public HttpClientCall(HttpClient httpClient, HttpRequestData httpRequestData, HttpResponseData httpResponseData) {
        this(httpClient);
        Intrinsics.checkNotNullParameter(httpClient, "client");
        Intrinsics.checkNotNullParameter(httpRequestData, "requestData");
        Intrinsics.checkNotNullParameter(httpResponseData, "responseData");
        setRequest(new DefaultHttpRequest(this, httpRequestData));
        setResponse(new DefaultHttpResponse(this, httpResponseData));
        if (!(httpResponseData.getBody() instanceof ByteReadChannel)) {
            getAttributes().put(CustomResponse, httpResponseData.getBody());
        }
    }

    /* access modifiers changed from: protected */
    public boolean getAllowDoubleReceive() {
        return this.allowDoubleReceive;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00b5 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b6  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00c6 A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c7 A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00ca A[Catch:{ all -> 0x0036 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object bodyNullable(io.ktor.util.reflect.TypeInfo r7, kotlin.coroutines.Continuation<java.lang.Object> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof io.ktor.client.call.HttpClientCall$bodyNullable$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.client.call.HttpClientCall$bodyNullable$1 r0 = (io.ktor.client.call.HttpClientCall$bodyNullable$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.client.call.HttpClientCall$bodyNullable$1 r0 = new io.ktor.client.call.HttpClientCall$bodyNullable$1
            r0.<init>(r6, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x0051
            if (r2 == r4) goto L_0x0041
            if (r2 != r3) goto L_0x0039
            java.lang.Object r7 = r0.L$1
            io.ktor.util.reflect.TypeInfo r7 = (io.ktor.util.reflect.TypeInfo) r7
            java.lang.Object r0 = r0.L$0
            io.ktor.client.call.HttpClientCall r0 = (io.ktor.client.call.HttpClientCall) r0
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x0036 }
            goto L_0x00b7
        L_0x0036:
            r7 = move-exception
            goto L_0x00f5
        L_0x0039:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0041:
            java.lang.Object r7 = r0.L$1
            io.ktor.util.reflect.TypeInfo r7 = (io.ktor.util.reflect.TypeInfo) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.client.call.HttpClientCall r2 = (io.ktor.client.call.HttpClientCall) r2
            kotlin.ResultKt.throwOnFailure(r8)     // Catch:{ all -> 0x004d }
            goto L_0x009e
        L_0x004d:
            r7 = move-exception
            r0 = r2
            goto L_0x00f5
        L_0x0051:
            kotlin.ResultKt.throwOnFailure(r8)
            io.ktor.client.statement.HttpResponse r8 = r6.getResponse()     // Catch:{ all -> 0x00f3 }
            kotlin.reflect.KClass r2 = r7.getType()     // Catch:{ all -> 0x00f3 }
            boolean r8 = io.ktor.util.reflect.TypeInfoJvmKt.instanceOf(r8, r2)     // Catch:{ all -> 0x00f3 }
            if (r8 == 0) goto L_0x006e
            io.ktor.client.statement.HttpResponse r7 = r6.getResponse()     // Catch:{ all -> 0x00f3 }
            io.ktor.client.statement.HttpResponse r8 = r6.getResponse()
            io.ktor.client.statement.HttpResponseKt.complete(r8)
            return r7
        L_0x006e:
            boolean r8 = r6.getAllowDoubleReceive()     // Catch:{ all -> 0x00f3 }
            if (r8 != 0) goto L_0x0084
            java.util.concurrent.atomic.AtomicIntegerFieldUpdater r8 = received$FU     // Catch:{ all -> 0x00f3 }
            r2 = 0
            boolean r8 = r8.compareAndSet(r6, r2, r4)     // Catch:{ all -> 0x00f3 }
            if (r8 == 0) goto L_0x007e
            goto L_0x0084
        L_0x007e:
            io.ktor.client.call.DoubleReceiveException r7 = new io.ktor.client.call.DoubleReceiveException     // Catch:{ all -> 0x00f3 }
            r7.<init>(r6)     // Catch:{ all -> 0x00f3 }
            throw r7     // Catch:{ all -> 0x00f3 }
        L_0x0084:
            io.ktor.util.Attributes r8 = r6.getAttributes()     // Catch:{ all -> 0x00f3 }
            io.ktor.util.AttributeKey<java.lang.Object> r2 = CustomResponse     // Catch:{ all -> 0x00f3 }
            java.lang.Object r8 = r8.getOrNull(r2)     // Catch:{ all -> 0x00f3 }
            if (r8 != 0) goto L_0x009d
            r0.L$0 = r6     // Catch:{ all -> 0x00f3 }
            r0.L$1 = r7     // Catch:{ all -> 0x00f3 }
            r0.label = r4     // Catch:{ all -> 0x00f3 }
            java.lang.Object r8 = r6.getResponseContent(r0)     // Catch:{ all -> 0x00f3 }
            if (r8 != r1) goto L_0x009d
            return r1
        L_0x009d:
            r2 = r6
        L_0x009e:
            io.ktor.client.statement.HttpResponseContainer r5 = new io.ktor.client.statement.HttpResponseContainer     // Catch:{ all -> 0x004d }
            r5.<init>(r7, r8)     // Catch:{ all -> 0x004d }
            io.ktor.client.HttpClient r8 = r2.client     // Catch:{ all -> 0x004d }
            io.ktor.client.statement.HttpResponsePipeline r8 = r8.getResponsePipeline()     // Catch:{ all -> 0x004d }
            r0.L$0 = r2     // Catch:{ all -> 0x004d }
            r0.L$1 = r7     // Catch:{ all -> 0x004d }
            r0.label = r3     // Catch:{ all -> 0x004d }
            java.lang.Object r8 = r8.execute(r2, r5, r0)     // Catch:{ all -> 0x004d }
            if (r8 != r1) goto L_0x00b6
            return r1
        L_0x00b6:
            r0 = r2
        L_0x00b7:
            io.ktor.client.statement.HttpResponseContainer r8 = (io.ktor.client.statement.HttpResponseContainer) r8     // Catch:{ all -> 0x0036 }
            java.lang.Object r8 = r8.getResponse()     // Catch:{ all -> 0x0036 }
            io.ktor.http.content.NullBody r1 = io.ktor.http.content.NullBody.INSTANCE     // Catch:{ all -> 0x0036 }
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r1)     // Catch:{ all -> 0x0036 }
            r1 = r1 ^ r4
            if (r1 == 0) goto L_0x00c7
            goto L_0x00c8
        L_0x00c7:
            r8 = 0
        L_0x00c8:
            if (r8 == 0) goto L_0x00eb
            kotlin.reflect.KClass r1 = r7.getType()     // Catch:{ all -> 0x0036 }
            boolean r1 = io.ktor.util.reflect.TypeInfoJvmKt.instanceOf(r8, r1)     // Catch:{ all -> 0x0036 }
            if (r1 == 0) goto L_0x00d5
            goto L_0x00eb
        L_0x00d5:
            java.lang.Class r8 = r8.getClass()     // Catch:{ all -> 0x0036 }
            kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r8)     // Catch:{ all -> 0x0036 }
            kotlin.reflect.KClass r7 = r7.getType()     // Catch:{ all -> 0x0036 }
            io.ktor.client.call.NoTransformationFoundException r1 = new io.ktor.client.call.NoTransformationFoundException     // Catch:{ all -> 0x0036 }
            io.ktor.client.statement.HttpResponse r2 = r0.getResponse()     // Catch:{ all -> 0x0036 }
            r1.<init>(r2, r8, r7)     // Catch:{ all -> 0x0036 }
            throw r1     // Catch:{ all -> 0x0036 }
        L_0x00eb:
            io.ktor.client.statement.HttpResponse r7 = r0.getResponse()
            io.ktor.client.statement.HttpResponseKt.complete(r7)
            return r8
        L_0x00f3:
            r7 = move-exception
            r0 = r6
        L_0x00f5:
            io.ktor.client.statement.HttpResponse r8 = r0.getResponse()     // Catch:{ all -> 0x0101 }
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8     // Catch:{ all -> 0x0101 }
            java.lang.String r1 = "Receive failed"
            kotlinx.coroutines.CoroutineScopeKt.cancel(r8, r1, r7)     // Catch:{ all -> 0x0101 }
            throw r7     // Catch:{ all -> 0x0101 }
        L_0x0101:
            r7 = move-exception
            io.ktor.client.statement.HttpResponse r8 = r0.getResponse()
            io.ktor.client.statement.HttpResponseKt.complete(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.call.HttpClientCall.bodyNullable(io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object body(io.ktor.util.reflect.TypeInfo r5, kotlin.coroutines.Continuation<java.lang.Object> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.call.HttpClientCall$body$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.client.call.HttpClientCall$body$1 r0 = (io.ktor.client.call.HttpClientCall$body$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.client.call.HttpClientCall$body$1 r0 = new io.ktor.client.call.HttpClientCall$body$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x003e
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.label = r3
            java.lang.Object r6 = r4.bodyNullable(r5, r0)
            if (r6 != r1) goto L_0x003e
            return r1
        L_0x003e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.call.HttpClientCall.body(io.ktor.util.reflect.TypeInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public String toString() {
        return "HttpClientCall[" + getRequest().getUrl() + ", " + getResponse().getStatus() + AbstractJsonLexerKt.END_LIST;
    }

    public final void setResponse$ktor_client_core(HttpResponse httpResponse) {
        Intrinsics.checkNotNullParameter(httpResponse, "response");
        setResponse(httpResponse);
    }

    public final void setRequest$ktor_client_core(HttpRequest httpRequest) {
        Intrinsics.checkNotNullParameter(httpRequest, "request");
        setRequest(httpRequest);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\"\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lio/ktor/client/call/HttpClientCall$Companion;", "", "()V", "CustomResponse", "Lio/ktor/util/AttributeKey;", "getCustomResponse$annotations", "getCustomResponse", "()Lio/ktor/util/AttributeKey;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpClientCall.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = "This is going to be removed. Please file a ticket with clarification why and what for do you need it.")
        public static /* synthetic */ void getCustomResponse$annotations() {
        }

        private Companion() {
        }

        public final AttributeKey<Object> getCustomResponse() {
            return HttpClientCall.CustomResponse;
        }
    }
}
