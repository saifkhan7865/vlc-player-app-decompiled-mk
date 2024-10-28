package io.ktor.client.plugins.api;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.statement.HttpResponse;
import io.ktor.http.content.OutgoingContent;
import io.ktor.util.AttributeKey;
import io.ktor.util.KtorDsl;
import io.ktor.util.reflect.TypeInfo;
import io.ktor.utils.io.ByteReadChannel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B+\b\u0000\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00028\u0000¢\u0006\u0002\u0010\tJ'\u0010\u001d\u001a\u00020\u0015\"\u0004\b\u0001\u0010\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u001e0 2\u0006\u0010!\u001a\u0002H\u001e¢\u0006\u0002\u0010\"J\u0014\u0010\u0013\u001a\u00020\u00152\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014Ja\u0010$\u001a\u00020\u00152Q\u0010#\u001aM\b\u0001\u0012\u0004\u0012\u00020&\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(+\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150,\u0012\u0006\u0012\u0004\u0018\u00010\u00020%¢\u0006\u0002\b-ø\u0001\u0000¢\u0006\u0002\u0010.JL\u0010/\u001a\u00020\u00152<\u0010#\u001a8\b\u0001\u0012\u0004\u0012\u000201\u0012\u0013\u0012\u001102¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(3\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150,\u0012\u0006\u0012\u0004\u0018\u00010\u000200¢\u0006\u0002\b-ø\u0001\u0000¢\u0006\u0002\u00104Jz\u00105\u001a\u00020\u00152j\u0010#\u001af\b\u0001\u0012\u0004\u0012\u000207\u0012\u0013\u0012\u00110'¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110\u0002¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(+\u0012\u0015\u0012\u0013\u0018\u000108¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(9\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010:0,\u0012\u0006\u0012\u0004\u0018\u00010\u000206¢\u0006\u0002\b-ø\u0001\u0000¢\u0006\u0002\u0010;Jx\u0010<\u001a\u00020\u00152h\u0010#\u001ad\b\u0001\u0012\u0004\u0012\u00020=\u0012\u0013\u0012\u001102¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(3\u0012\u0013\u0012\u00110>¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(+\u0012\u0013\u0012\u001108¢\u0006\f\b(\u0012\b\b)\u0012\u0004\b\b(?\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020,\u0012\u0006\u0012\u0004\u0018\u00010\u000206¢\u0006\u0002\b-ø\u0001\u0000¢\u0006\u0002\u0010;R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001e\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\rX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u0013\u0010\b\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001b\u0002\u0004\n\u0002\b\u0019¨\u0006@"}, d2 = {"Lio/ktor/client/plugins/api/ClientPluginBuilder;", "PluginConfig", "", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/plugins/api/ClientPluginInstance;", "client", "Lio/ktor/client/HttpClient;", "pluginConfig", "(Lio/ktor/util/AttributeKey;Lio/ktor/client/HttpClient;Ljava/lang/Object;)V", "getClient", "()Lio/ktor/client/HttpClient;", "hooks", "", "Lio/ktor/client/plugins/api/HookHandler;", "getHooks$ktor_client_core", "()Ljava/util/List;", "getKey$ktor_client_core", "()Lio/ktor/util/AttributeKey;", "onClose", "Lkotlin/Function0;", "", "getOnClose$ktor_client_core", "()Lkotlin/jvm/functions/Function0;", "setOnClose$ktor_client_core", "(Lkotlin/jvm/functions/Function0;)V", "getPluginConfig", "()Ljava/lang/Object;", "Ljava/lang/Object;", "on", "HookHandler", "hook", "Lio/ktor/client/plugins/api/ClientHook;", "handler", "(Lio/ktor/client/plugins/api/ClientHook;Ljava/lang/Object;)V", "block", "onRequest", "Lkotlin/Function4;", "Lio/ktor/client/plugins/api/OnRequestContext;", "Lio/ktor/client/request/HttpRequestBuilder;", "Lkotlin/ParameterName;", "name", "request", "content", "Lkotlin/coroutines/Continuation;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function4;)V", "onResponse", "Lkotlin/Function3;", "Lio/ktor/client/plugins/api/OnResponseContext;", "Lio/ktor/client/statement/HttpResponse;", "response", "(Lkotlin/jvm/functions/Function3;)V", "transformRequestBody", "Lkotlin/Function5;", "Lio/ktor/client/plugins/api/TransformRequestBodyContext;", "Lio/ktor/util/reflect/TypeInfo;", "bodyType", "Lio/ktor/http/content/OutgoingContent;", "(Lkotlin/jvm/functions/Function5;)V", "transformResponseBody", "Lio/ktor/client/plugins/api/TransformResponseBodyContext;", "Lio/ktor/utils/io/ByteReadChannel;", "requestedType", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: ClientPluginBuilder.kt */
public final class ClientPluginBuilder<PluginConfig> {
    private final HttpClient client;
    private final List<HookHandler<?>> hooks = new ArrayList();
    private final AttributeKey<ClientPluginInstance<PluginConfig>> key;
    private Function0<Unit> onClose = ClientPluginBuilder$onClose$1.INSTANCE;
    private final PluginConfig pluginConfig;

    public ClientPluginBuilder(AttributeKey<ClientPluginInstance<PluginConfig>> attributeKey, HttpClient httpClient, PluginConfig pluginconfig) {
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(httpClient, "client");
        Intrinsics.checkNotNullParameter(pluginconfig, "pluginConfig");
        this.key = attributeKey;
        this.client = httpClient;
        this.pluginConfig = pluginconfig;
    }

    public final AttributeKey<ClientPluginInstance<PluginConfig>> getKey$ktor_client_core() {
        return this.key;
    }

    public final HttpClient getClient() {
        return this.client;
    }

    public final PluginConfig getPluginConfig() {
        return this.pluginConfig;
    }

    public final List<HookHandler<?>> getHooks$ktor_client_core() {
        return this.hooks;
    }

    public final Function0<Unit> getOnClose$ktor_client_core() {
        return this.onClose;
    }

    public final void setOnClose$ktor_client_core(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "<set-?>");
        this.onClose = function0;
    }

    public final void onRequest(Function4<? super OnRequestContext, ? super HttpRequestBuilder, Object, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(function4, "block");
        on(RequestHook.INSTANCE, function4);
    }

    public final void onResponse(Function3<? super OnResponseContext, ? super HttpResponse, ? super Continuation<? super Unit>, ? extends Object> function3) {
        Intrinsics.checkNotNullParameter(function3, "block");
        on(ResponseHook.INSTANCE, function3);
    }

    public final void transformRequestBody(Function5<? super TransformRequestBodyContext, ? super HttpRequestBuilder, Object, ? super TypeInfo, ? super Continuation<? super OutgoingContent>, ? extends Object> function5) {
        Intrinsics.checkNotNullParameter(function5, "block");
        on(TransformRequestBodyHook.INSTANCE, function5);
    }

    public final void transformResponseBody(Function5<? super TransformResponseBodyContext, ? super HttpResponse, ? super ByteReadChannel, ? super TypeInfo, ? super Continuation<Object>, ? extends Object> function5) {
        Intrinsics.checkNotNullParameter(function5, "block");
        on(TransformResponseBodyHook.INSTANCE, function5);
    }

    public final void onClose(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        this.onClose = function0;
    }

    public final <HookHandler> void on(ClientHook<HookHandler> clientHook, HookHandler hookhandler) {
        Intrinsics.checkNotNullParameter(clientHook, "hook");
        this.hooks.add(new HookHandler(clientHook, hookhandler));
    }
}
