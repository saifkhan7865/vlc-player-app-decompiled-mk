package io.ktor.client.plugins.api;

import io.ktor.client.HttpClient;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\n\u001a\u00020\u000bH\u0016J'\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0017\u0010\r\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\u000fH\u0016R \u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00040\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"io/ktor/client/plugins/api/CreatePluginUtilsKt$createClientPlugin$1", "Lio/ktor/client/plugins/api/ClientPlugin;", "key", "Lio/ktor/util/AttributeKey;", "Lio/ktor/client/plugins/api/ClientPluginInstance;", "getKey", "()Lio/ktor/util/AttributeKey;", "install", "", "plugin", "scope", "Lio/ktor/client/HttpClient;", "prepare", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePluginUtils.kt */
public final class CreatePluginUtilsKt$createClientPlugin$1 implements ClientPlugin<PluginConfigT> {
    final /* synthetic */ Function1<ClientPluginBuilder<PluginConfigT>, Unit> $body;
    final /* synthetic */ Function0<PluginConfigT> $createConfiguration;
    final /* synthetic */ String $name;
    private final AttributeKey<ClientPluginInstance<PluginConfigT>> key;

    CreatePluginUtilsKt$createClientPlugin$1(String str, Function0<? extends PluginConfigT> function0, Function1<? super ClientPluginBuilder<PluginConfigT>, Unit> function1) {
        this.$name = str;
        this.$createConfiguration = function0;
        this.$body = function1;
        this.key = new AttributeKey<>(str);
    }

    public AttributeKey<ClientPluginInstance<PluginConfigT>> getKey() {
        return this.key;
    }

    public ClientPluginInstance<PluginConfigT> prepare(Function1<? super PluginConfigT, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        PluginConfigT invoke = this.$createConfiguration.invoke();
        function1.invoke(invoke);
        return new ClientPluginInstance<>(invoke, this.$name, this.$body);
    }

    public void install(ClientPluginInstance<PluginConfigT> clientPluginInstance, HttpClient httpClient) {
        Intrinsics.checkNotNullParameter(clientPluginInstance, "plugin");
        Intrinsics.checkNotNullParameter(httpClient, OAuth2RequestParameters.Scope);
        clientPluginInstance.install(httpClient);
    }
}
