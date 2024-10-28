package io.ktor.client.plugins.api;

import io.ktor.http.ContentDisposition;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001aK\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f¨\u0006\r"}, d2 = {"createClientPlugin", "Lio/ktor/client/plugins/api/ClientPlugin;", "PluginConfigT", "", "name", "", "createConfiguration", "Lkotlin/Function0;", "body", "Lkotlin/Function1;", "Lio/ktor/client/plugins/api/ClientPluginBuilder;", "", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePluginUtils.kt */
public final class CreatePluginUtilsKt {
    public static final <PluginConfigT> ClientPlugin<PluginConfigT> createClientPlugin(String str, Function0<? extends PluginConfigT> function0, Function1<? super ClientPluginBuilder<PluginConfigT>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, "createConfiguration");
        Intrinsics.checkNotNullParameter(function1, "body");
        return new CreatePluginUtilsKt$createClientPlugin$1(str, function0, function1);
    }

    public static final ClientPlugin<Unit> createClientPlugin(String str, Function1<? super ClientPluginBuilder<Unit>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "body");
        return createClientPlugin(str, CreatePluginUtilsKt$createClientPlugin$2.INSTANCE, function1);
    }
}
