package io.ktor.server.application;

import io.ktor.http.ContentDisposition;
import io.ktor.server.config.ApplicationConfig;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u001aK\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00020\t2\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001aR\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0012\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\b\u0013\u001aK\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0012\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001a:\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\b\u0013\u001a3\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u0014\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001ao\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0012\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00020\t2\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\b\u0013\u001ah\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0012\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u00052!\u0010\u0006\u001a\u001d\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0004\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u0002H\u00020\t2\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f\u001a\u0001\u0010\u0015\u001a\u00020\u0016\"\b\b\u0000\u0010\u0017*\u00020\u0018\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00160\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00182\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0017\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0002\u001a\u0001\u0010\u001e\u001a\u00020\u0016\"\b\b\u0000\u0010\u0017*\u00020\u0018\"\b\b\u0001\u0010\u0002*\u00020\u0003*\u0014\u0012\u0004\u0012\u0002H\u0017\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00160\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00182\u001d\u0010\b\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u0014\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\f2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u0017\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0002\u001aD\u0010\u001f\u001a\u00020\u000b\"\b\b\u0000\u0010 *\u00020\u0003\"\u000e\b\u0001\u0010!*\b\u0012\u0004\u0012\u0002H 0\n*\u0002H!2\u0017\u0010\b\u001a\u0013\u0012\u0004\u0012\u0002H!\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0002\b\fH\u0002¢\u0006\u0002\u0010\"¨\u0006#"}, d2 = {"createApplicationPlugin", "Lio/ktor/server/application/ApplicationPlugin;", "PluginConfigT", "", "name", "", "createConfiguration", "Lkotlin/Function0;", "body", "Lkotlin/Function1;", "Lio/ktor/server/application/PluginBuilder;", "", "Lkotlin/ExtensionFunctionType;", "configurationPath", "Lio/ktor/server/config/ApplicationConfig;", "Lkotlin/ParameterName;", "config", "createRouteScopedPlugin", "Lio/ktor/server/application/RouteScopedPlugin;", "createRouteScopedPluginOld", "Lio/ktor/server/application/RouteScopedPluginBuilder;", "createPluginInstance", "Lio/ktor/server/application/PluginInstance;", "PipelineT", "Lio/ktor/server/application/ApplicationCallPipeline;", "Lio/ktor/server/application/Plugin;", "application", "Lio/ktor/server/application/Application;", "pipeline", "configure", "createRouteScopedPluginInstance", "setupPlugin", "Configuration", "Builder", "(Lio/ktor/server/application/PluginBuilder;Lkotlin/jvm/functions/Function1;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CreatePluginUtils.kt */
public final class CreatePluginUtilsKt {
    public static final <PluginConfigT> ApplicationPlugin<PluginConfigT> createApplicationPlugin(String str, String str2, Function1<? super ApplicationConfig, ? extends PluginConfigT> function1, Function1<? super PluginBuilder<PluginConfigT>, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "configurationPath");
        Intrinsics.checkNotNullParameter(function1, "createConfiguration");
        Intrinsics.checkNotNullParameter(function12, "body");
        return new CreatePluginUtilsKt$createApplicationPlugin$1(str, str2, function12, function1);
    }

    public static final <PluginConfigT> ApplicationPlugin<PluginConfigT> createApplicationPlugin(String str, Function0<? extends PluginConfigT> function0, Function1<? super PluginBuilder<PluginConfigT>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, "createConfiguration");
        Intrinsics.checkNotNullParameter(function1, "body");
        return new CreatePluginUtilsKt$createApplicationPlugin$2(str, function1, function0);
    }

    public static final <PluginConfigT> RouteScopedPlugin<PluginConfigT> createRouteScopedPlugin(String str, Function0<? extends PluginConfigT> function0, Function1<? super RouteScopedPluginBuilder<PluginConfigT>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, "createConfiguration");
        Intrinsics.checkNotNullParameter(function1, "body");
        return new CreatePluginUtilsKt$createRouteScopedPlugin$1(str, function1, function0);
    }

    public static final <PluginConfigT> RouteScopedPlugin<PluginConfigT> createRouteScopedPlugin(String str, String str2, Function1<? super ApplicationConfig, ? extends PluginConfigT> function1, Function1<? super RouteScopedPluginBuilder<PluginConfigT>, Unit> function12) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "configurationPath");
        Intrinsics.checkNotNullParameter(function1, "createConfiguration");
        Intrinsics.checkNotNullParameter(function12, "body");
        return new CreatePluginUtilsKt$createRouteScopedPlugin$2(str, str2, function12, function1);
    }

    public static final ApplicationPlugin<Unit> createApplicationPlugin(String str, Function1<? super PluginBuilder<Unit>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "body");
        return createApplicationPlugin(str, CreatePluginUtilsKt$createApplicationPlugin$3.INSTANCE, function1);
    }

    public static final RouteScopedPlugin<Unit> createRouteScopedPlugin(String str, Function1<? super RouteScopedPluginBuilder<Unit>, Unit> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "body");
        return createRouteScopedPlugin(str, CreatePluginUtilsKt$createRouteScopedPlugin$3.INSTANCE, function1);
    }

    /* access modifiers changed from: private */
    public static final <PipelineT extends ApplicationCallPipeline, PluginConfigT> PluginInstance createPluginInstance(Plugin<? super PipelineT, ? extends PluginConfigT, PluginInstance> plugin, Application application, ApplicationCallPipeline applicationCallPipeline, Function1<? super PluginBuilder<PluginConfigT>, Unit> function1, Function0<? extends PluginConfigT> function0, Function1<? super PluginConfigT, Unit> function12) {
        Object invoke = function0.invoke();
        function12.invoke(invoke);
        PluginBuilder createPluginUtilsKt$createPluginInstance$pluginBuilder$1 = new CreatePluginUtilsKt$createPluginInstance$pluginBuilder$1(application, applicationCallPipeline, invoke, plugin.getKey());
        setupPlugin(createPluginUtilsKt$createPluginInstance$pluginBuilder$1, function1);
        return new PluginInstance(createPluginUtilsKt$createPluginInstance$pluginBuilder$1);
    }

    /* access modifiers changed from: private */
    public static final <PipelineT extends ApplicationCallPipeline, PluginConfigT> PluginInstance createRouteScopedPluginInstance(Plugin<? super PipelineT, ? extends PluginConfigT, PluginInstance> plugin, Application application, ApplicationCallPipeline applicationCallPipeline, Function1<? super RouteScopedPluginBuilder<PluginConfigT>, Unit> function1, Function0<? extends PluginConfigT> function0, Function1<? super PluginConfigT, Unit> function12) {
        Object invoke = function0.invoke();
        function12.invoke(invoke);
        PluginBuilder createPluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1 = new CreatePluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1(application, applicationCallPipeline, invoke, plugin.getKey());
        setupPlugin(createPluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1, function1);
        return new PluginInstance(createPluginUtilsKt$createRouteScopedPluginInstance$pluginBuilder$1);
    }

    private static final <Configuration, Builder extends PluginBuilder<Configuration>> void setupPlugin(Builder builder, Function1<? super Builder, Unit> function1) {
        function1.invoke(builder);
        for (Interception action : builder.getCallInterceptions$ktor_server_core()) {
            action.getAction().invoke(builder.getPipeline$ktor_server_core());
        }
        for (Interception action2 : builder.getOnReceiveInterceptions$ktor_server_core()) {
            action2.getAction().invoke(builder.getPipeline$ktor_server_core().getReceivePipeline());
        }
        for (Interception action3 : builder.getOnResponseInterceptions$ktor_server_core()) {
            action3.getAction().invoke(builder.getPipeline$ktor_server_core().getSendPipeline());
        }
        for (Interception action4 : builder.getAfterResponseInterceptions$ktor_server_core()) {
            action4.getAction().invoke(builder.getPipeline$ktor_server_core().getSendPipeline());
        }
        for (HookHandler install : builder.getHooks$ktor_server_core()) {
            install.install(builder.getPipeline$ktor_server_core());
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This will be removed")
    public static final /* synthetic */ RouteScopedPlugin createRouteScopedPluginOld(String str, Function1 function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function1, "body");
        return createRouteScopedPlugin(str, CreatePluginUtilsKt$createRouteScopedPlugin$4.INSTANCE, function1);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This will be removed")
    public static final /* synthetic */ RouteScopedPlugin createRouteScopedPluginOld(String str, Function0 function0, Function1 function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, "createConfiguration");
        Intrinsics.checkNotNullParameter(function1, "body");
        return createRouteScopedPlugin(str, function0, function1);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This will be removed")
    public static final /* synthetic */ RouteScopedPlugin createRouteScopedPluginOld(String str, String str2, Function1 function1, Function1 function12) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "configurationPath");
        Intrinsics.checkNotNullParameter(function1, "createConfiguration");
        Intrinsics.checkNotNullParameter(function12, "body");
        return createRouteScopedPlugin(str, str2, function1, function12);
    }
}
