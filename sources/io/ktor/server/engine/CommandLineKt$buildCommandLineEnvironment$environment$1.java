package io.ktor.server.engine;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigKt;
import io.ktor.util.PlatformUtils;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.text.StringsKt;
import org.fusesource.jansi.AnsiRenderer;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: CommandLine.kt */
final class CommandLineKt$buildCommandLineEnvironment$environment$1 extends Lambda implements Function1<ApplicationEngineEnvironmentBuilder, Unit> {
    final /* synthetic */ String[] $args;
    final /* synthetic */ Map<String, String> $argumentsPairs;
    final /* synthetic */ ApplicationConfig $configuration;
    final /* synthetic */ Function1<ApplicationEngineEnvironmentBuilder, Unit> $environmentBuilder;
    final /* synthetic */ Logger $logger;
    final /* synthetic */ String $rootPath;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CommandLineKt$buildCommandLineEnvironment$environment$1(Logger logger, String[] strArr, ApplicationConfig applicationConfig, String str, Map<String, String> map, Function1<? super ApplicationEngineEnvironmentBuilder, Unit> function1) {
        super(1);
        this.$logger = logger;
        this.$args = strArr;
        this.$configuration = applicationConfig;
        this.$rootPath = str;
        this.$argumentsPairs = map;
        this.$environmentBuilder = function1;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApplicationEngineEnvironmentBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApplicationEngineEnvironmentBuilder applicationEngineEnvironmentBuilder) {
        List<String> list;
        Intrinsics.checkNotNullParameter(applicationEngineEnvironmentBuilder, "$this$applicationEngineEnvironment");
        applicationEngineEnvironmentBuilder.setLog(this.$logger);
        EnvironmentUtilsJvmKt.configurePlatformProperties(applicationEngineEnvironmentBuilder, this.$args);
        applicationEngineEnvironmentBuilder.setConfig(this.$configuration);
        applicationEngineEnvironmentBuilder.setRootPath(this.$rootPath);
        String str = this.$argumentsPairs.get("-host");
        if (str == null && (str = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostConfigPath)) == null) {
            str = "0.0.0.0";
        }
        String str2 = str;
        String str3 = this.$argumentsPairs.get("-port");
        if (str3 == null) {
            str3 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostPortPath);
        }
        String str4 = this.$argumentsPairs.get("-sslPort");
        if (str4 == null) {
            str4 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslPortPath);
        }
        String str5 = str4;
        String str6 = this.$argumentsPairs.get("-sslKeyStore");
        if (str6 == null) {
            str6 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslKeyStore);
        }
        String str7 = str6;
        String tryGetString = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslKeyStorePassword);
        String obj = tryGetString != null ? StringsKt.trim((CharSequence) tryGetString).toString() : null;
        String tryGetString2 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslPrivateKeyPassword);
        String obj2 = tryGetString2 != null ? StringsKt.trim((CharSequence) tryGetString2).toString() : null;
        String tryGetString3 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.hostSslKeyAlias);
        if (tryGetString3 == null) {
            tryGetString3 = "mykey";
        }
        String str8 = tryGetString3;
        String tryGetString4 = ApplicationConfigKt.tryGetString(this.$configuration, ConfigKeys.developmentModeKey);
        applicationEngineEnvironmentBuilder.setDevelopmentMode(tryGetString4 != null ? Boolean.parseBoolean(tryGetString4) : PlatformUtils.INSTANCE.getIS_DEVELOPMENT_MODE());
        if (str3 != null) {
            List<EngineConnectorConfig> connectors = applicationEngineEnvironmentBuilder.getConnectors();
            EngineConnectorBuilder engineConnectorBuilder = new EngineConnectorBuilder((ConnectorType) null, 1, (DefaultConstructorMarker) null);
            engineConnectorBuilder.setHost(str2);
            engineConnectorBuilder.setPort(Integer.parseInt(str3));
            connectors.add(engineConnectorBuilder);
        }
        if (str5 != null) {
            EnvironmentUtilsJvmKt.configureSSLConnectors(applicationEngineEnvironmentBuilder, str2, str5, str7, obj, obj2, str8);
        }
        if (str3 == null && str5 == null) {
            throw new IllegalArgumentException("Neither port nor sslPort specified. Use command line options -port/-sslPort or configure connectors in application.conf");
        }
        String str9 = this.$argumentsPairs.get("-watch");
        if (str9 == null || (list = StringsKt.split$default((CharSequence) str9, new String[]{AnsiRenderer.CODE_LIST_SEPARATOR}, false, 0, 6, (Object) null)) == null) {
            list = ApplicationConfigKt.tryGetStringList(this.$configuration, ConfigKeys.hostWatchPaths);
        }
        if (list != null) {
            applicationEngineEnvironmentBuilder.setWatchPaths(list);
        }
        this.$environmentBuilder.invoke(applicationEngineEnvironmentBuilder);
    }
}
