package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.MapApplicationConfig;
import io.ktor.util.PlatformUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001f\u00107\u001a\u0002082\u0017\u00109\u001a\u0013\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020#0!¢\u0006\u0002\b$J\u001f\u0010:\u001a\u00020#2\u0017\u0010;\u001a\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\u0002\b$R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR(\u0010 \u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0!¢\u0006\u0002\b$0\u0010¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0013R\u001a\u0010&\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R \u00102\u001a\b\u0012\u0004\u0012\u00020-03X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0013\"\u0004\b5\u00106¨\u0006<"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentBuilder;", "", "()V", "classLoader", "Ljava/lang/ClassLoader;", "getClassLoader", "()Ljava/lang/ClassLoader;", "setClassLoader", "(Ljava/lang/ClassLoader;)V", "config", "Lio/ktor/server/config/ApplicationConfig;", "getConfig", "()Lio/ktor/server/config/ApplicationConfig;", "setConfig", "(Lio/ktor/server/config/ApplicationConfig;)V", "connectors", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "getConnectors", "()Ljava/util/List;", "developmentMode", "", "getDevelopmentMode", "()Z", "setDevelopmentMode", "(Z)V", "log", "Lorg/slf4j/Logger;", "getLog", "()Lorg/slf4j/Logger;", "setLog", "(Lorg/slf4j/Logger;)V", "modules", "Lkotlin/Function1;", "Lio/ktor/server/application/Application;", "", "Lkotlin/ExtensionFunctionType;", "getModules", "parentCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getParentCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "setParentCoroutineContext", "(Lkotlin/coroutines/CoroutineContext;)V", "rootPath", "", "getRootPath", "()Ljava/lang/String;", "setRootPath", "(Ljava/lang/String;)V", "watchPaths", "", "getWatchPaths", "setWatchPaths", "(Ljava/util/List;)V", "build", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "builder", "module", "body", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentJvm.kt */
public final class ApplicationEngineEnvironmentBuilder {
    private ClassLoader classLoader;
    private ApplicationConfig config;
    private final List<EngineConnectorConfig> connectors;
    private boolean developmentMode;
    private Logger log;
    private final List<Function1<Application, Unit>> modules;
    private CoroutineContext parentCoroutineContext = EmptyCoroutineContext.INSTANCE;
    private String rootPath;
    private List<String> watchPaths = CollectionsKt.listOf(ServerEngineUtilsJvmKt.getWORKING_DIRECTORY_PATH());

    public ApplicationEngineEnvironmentBuilder() {
        ClassLoader classLoader2 = ApplicationEngineEnvironment.class.getClassLoader();
        Intrinsics.checkNotNullExpressionValue(classLoader2, "ApplicationEngineEnviron…t::class.java.classLoader");
        this.classLoader = classLoader2;
        Logger logger = LoggerFactory.getLogger("Application");
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(\"Application\")");
        this.log = logger;
        this.config = new MapApplicationConfig();
        this.connectors = new ArrayList();
        this.modules = new ArrayList();
        this.rootPath = "";
        this.developmentMode = PlatformUtils.INSTANCE.getIS_DEVELOPMENT_MODE();
    }

    public final ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public final void setClassLoader(ClassLoader classLoader2) {
        Intrinsics.checkNotNullParameter(classLoader2, "<set-?>");
        this.classLoader = classLoader2;
    }

    public final CoroutineContext getParentCoroutineContext() {
        return this.parentCoroutineContext;
    }

    public final void setParentCoroutineContext(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "<set-?>");
        this.parentCoroutineContext = coroutineContext;
    }

    public final List<String> getWatchPaths() {
        return this.watchPaths;
    }

    public final void setWatchPaths(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.watchPaths = list;
    }

    public final Logger getLog() {
        return this.log;
    }

    public final void setLog(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "<set-?>");
        this.log = logger;
    }

    public final ApplicationConfig getConfig() {
        return this.config;
    }

    public final void setConfig(ApplicationConfig applicationConfig) {
        Intrinsics.checkNotNullParameter(applicationConfig, "<set-?>");
        this.config = applicationConfig;
    }

    public final List<EngineConnectorConfig> getConnectors() {
        return this.connectors;
    }

    public final List<Function1<Application, Unit>> getModules() {
        return this.modules;
    }

    public final String getRootPath() {
        return this.rootPath;
    }

    public final void setRootPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.rootPath = str;
    }

    public final boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    public final void setDevelopmentMode(boolean z) {
        this.developmentMode = z;
    }

    public final void module(Function1<? super Application, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "body");
        this.modules.add(function1);
    }

    public final ApplicationEngineEnvironment build(Function1<? super ApplicationEngineEnvironmentBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builder");
        function1.invoke(this);
        return new ApplicationEngineEnvironmentReloading(this.classLoader, this.log, this.config, this.connectors, this.modules, this.watchPaths, this.parentCoroutineContext, this.rootPath, this.developmentMode);
    }
}
