package io.ktor.client.plugins;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.ClientUpgradeContent;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.URLProtocolKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineStart;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H@"}, d2 = {"<anonymous>", "Lio/ktor/client/call/HttpClientCall;", "Lio/ktor/client/plugins/Sender;", "request", "Lio/ktor/client/request/HttpRequestBuilder;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.HttpTimeout$Plugin$install$1", f = "HttpTimeout.kt", i = {}, l = {146, 174}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: HttpTimeout.kt */
final class HttpTimeout$Plugin$install$1 extends SuspendLambda implements Function3<Sender, HttpRequestBuilder, Continuation<? super HttpClientCall>, Object> {
    final /* synthetic */ HttpTimeout $plugin;
    final /* synthetic */ HttpClient $scope;
    private /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpTimeout$Plugin$install$1(HttpTimeout httpTimeout, HttpClient httpClient, Continuation<? super HttpTimeout$Plugin$install$1> continuation) {
        super(3, continuation);
        this.$plugin = httpTimeout;
        this.$scope = httpClient;
    }

    public final Object invoke(Sender sender, HttpRequestBuilder httpRequestBuilder, Continuation<? super HttpClientCall> continuation) {
        HttpTimeout$Plugin$install$1 httpTimeout$Plugin$install$1 = new HttpTimeout$Plugin$install$1(this.$plugin, this.$scope, continuation);
        httpTimeout$Plugin$install$1.L$0 = sender;
        httpTimeout$Plugin$install$1.L$1 = httpRequestBuilder;
        return httpTimeout$Plugin$install$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Sender sender = (Sender) this.L$0;
            HttpRequestBuilder httpRequestBuilder = (HttpRequestBuilder) this.L$1;
            if (URLProtocolKt.isWebsocket(httpRequestBuilder.getUrl().getProtocol()) || (httpRequestBuilder.getBody() instanceof ClientUpgradeContent)) {
                this.L$0 = null;
                this.label = 1;
                obj = sender.execute(httpRequestBuilder, this);
                return obj == coroutine_suspended ? coroutine_suspended : obj;
            }
            HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestBuilder.getCapabilityOrNull(HttpTimeout.Plugin);
            if (httpTimeoutCapabilityConfiguration == null && this.$plugin.hasNotNullTimeouts()) {
                httpTimeoutCapabilityConfiguration = new HttpTimeout.HttpTimeoutCapabilityConfiguration((Long) null, (Long) null, (Long) null, 7, (DefaultConstructorMarker) null);
                httpRequestBuilder.setCapability(HttpTimeout.Plugin, httpTimeoutCapabilityConfiguration);
            }
            if (httpTimeoutCapabilityConfiguration != null) {
                HttpTimeout httpTimeout = this.$plugin;
                HttpClient httpClient = this.$scope;
                Long connectTimeoutMillis = httpTimeoutCapabilityConfiguration.getConnectTimeoutMillis();
                if (connectTimeoutMillis == null) {
                    connectTimeoutMillis = httpTimeout.connectTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setConnectTimeoutMillis(connectTimeoutMillis);
                Long socketTimeoutMillis = httpTimeoutCapabilityConfiguration.getSocketTimeoutMillis();
                if (socketTimeoutMillis == null) {
                    socketTimeoutMillis = httpTimeout.socketTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setSocketTimeoutMillis(socketTimeoutMillis);
                Long requestTimeoutMillis = httpTimeoutCapabilityConfiguration.getRequestTimeoutMillis();
                if (requestTimeoutMillis == null) {
                    requestTimeoutMillis = httpTimeout.requestTimeoutMillis;
                }
                httpTimeoutCapabilityConfiguration.setRequestTimeoutMillis(requestTimeoutMillis);
                Long requestTimeoutMillis2 = httpTimeoutCapabilityConfiguration.getRequestTimeoutMillis();
                if (requestTimeoutMillis2 == null) {
                    requestTimeoutMillis2 = httpTimeout.requestTimeoutMillis;
                }
                if (!(requestTimeoutMillis2 == null || requestTimeoutMillis2.longValue() == Long.MAX_VALUE)) {
                    httpRequestBuilder.getExecutionContext().invokeOnCompletion(new HttpTimeout$Plugin$install$1$1$1(BuildersKt__Builders_commonKt.launch$default(httpClient, (CoroutineContext) null, (CoroutineStart) null, new HttpTimeout$Plugin$install$1$1$killer$1(requestTimeoutMillis2, httpRequestBuilder, httpRequestBuilder.getExecutionContext(), (Continuation<? super HttpTimeout$Plugin$install$1$1$killer$1>) null), 3, (Object) null)));
                }
            }
            this.L$0 = null;
            this.label = 2;
            obj = sender.execute(httpRequestBuilder, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else if (i == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
