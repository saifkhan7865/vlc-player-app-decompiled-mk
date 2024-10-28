package io.ktor.client.plugins.api;

import io.ktor.client.HttpClient;
import io.ktor.client.call.HttpClientCall;
import io.ktor.client.plugins.HttpClientPluginKt;
import io.ktor.client.plugins.HttpSend;
import io.ktor.client.request.HttpRequestBuilder;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002/\u0012+\u0012)\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002¢\u0006\u0002\b\b0\u0001:\u0001\u0010B\u0007\b\u0002¢\u0006\u0002\u0010\tJG\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2-\u0010\u000e\u001a)\b\u0001\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0002¢\u0006\u0002\b\bH\u0016ø\u0001\u0000¢\u0006\u0002\u0010\u000f\u0002\u0004\n\u0002\b\u0019¨\u0006\u0011"}, d2 = {"Lio/ktor/client/plugins/api/Send;", "Lio/ktor/client/plugins/api/ClientHook;", "Lkotlin/Function3;", "Lio/ktor/client/plugins/api/Send$Sender;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lkotlin/coroutines/Continuation;", "Lio/ktor/client/call/HttpClientCall;", "", "Lkotlin/ExtensionFunctionType;", "()V", "install", "", "client", "Lio/ktor/client/HttpClient;", "handler", "(Lio/ktor/client/HttpClient;Lkotlin/jvm/functions/Function3;)V", "Sender", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommonHooks.kt */
public final class Send implements ClientHook<Function3<? super Sender, ? super HttpRequestBuilder, ? super Continuation<? super HttpClientCall>, ? extends Object>> {
    public static final Send INSTANCE = new Send();

    private Send() {
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0019\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\n"}, d2 = {"Lio/ktor/client/plugins/api/Send$Sender;", "", "httpSendSender", "Lio/ktor/client/plugins/Sender;", "(Lio/ktor/client/plugins/Sender;)V", "proceed", "Lio/ktor/client/call/HttpClientCall;", "requestBuilder", "Lio/ktor/client/request/HttpRequestBuilder;", "(Lio/ktor/client/request/HttpRequestBuilder;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CommonHooks.kt */
    public static final class Sender {
        private final io.ktor.client.plugins.Sender httpSendSender;

        public Sender(io.ktor.client.plugins.Sender sender) {
            Intrinsics.checkNotNullParameter(sender, "httpSendSender");
            this.httpSendSender = sender;
        }

        public final Object proceed(HttpRequestBuilder httpRequestBuilder, Continuation<? super HttpClientCall> continuation) {
            return this.httpSendSender.execute(httpRequestBuilder, continuation);
        }
    }

    public void install(HttpClient httpClient, Function3<? super Sender, ? super HttpRequestBuilder, ? super Continuation<? super HttpClientCall>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(httpClient, "client");
        Intrinsics.checkNotNullParameter(function3, "handler");
        ((HttpSend) HttpClientPluginKt.plugin(httpClient, HttpSend.Plugin)).intercept((Function3<? super io.ktor.client.plugins.Sender, ? super HttpRequestBuilder, ? super Continuation<? super HttpClientCall>, ? extends Object>) new Send$install$1(function3, (Continuation<? super Send$install$1>) null));
    }
}
