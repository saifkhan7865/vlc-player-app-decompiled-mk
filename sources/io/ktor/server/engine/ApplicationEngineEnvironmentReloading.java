package io.ktor.server.engine;

import io.ktor.events.EventDefinition;
import io.ktor.events.Events;
import io.ktor.events.EventsKt;
import io.ktor.http.HttpStatusCode;
import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.engine.internal.AutoReloadUtilsKt;
import io.ktor.util.Attributes;
import io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0;
import io.ktor.util.pipeline.Pipeline;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.core.Input;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 ]2\u00020\u0001:\u0001]Bt\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u001d\u0010\f\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u00100\n\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\n\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0012¢\u0006\u0002\u0010\u0016B|\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u001d\u0010\f\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u00100\n\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\n\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0018¢\u0006\u0002\u0010\u0019J\u0016\u0010D\u001a\u00020\u000f2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000f0FH\u0002J\u001e\u0010G\u001a\u00020\u000f2\u0006\u0010H\u001a\u00020\u00122\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u000f0FH\u0002J\b\u0010I\u001a\u00020\u000fH\u0002J\u0014\u0010J\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00030KH\u0002J\b\u0010L\u001a\u00020\u0003H\u0002J\b\u0010M\u001a\u00020\u000eH\u0002J\b\u0010N\u001a\u00020\u000fH\u0002J\u0010\u0010O\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020\u0003H\u0002J \u0010Q\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u00122\u0006\u0010P\u001a\u00020\u00032\u0006\u0010S\u001a\u00020\u000eH\u0002J\u0006\u0010T\u001a\u00020\u000fJ\u001e\u0010U\u001a\u00020\u000f2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020\u000e0W2\u0006\u0010\u001c\u001a\u00020\u000eH\u0002J\b\u0010X\u001a\u00020\u000fH\u0016J\b\u0010Y\u001a\u00020\u000fH\u0016J\u0016\u0010Z\u001a\u00020\u000f2\f\u0010[\u001a\b\u0012\u0004\u0012\u00020\\0\nH\u0002R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u000e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00120\n8BX\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010(R\u0014\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0018\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R+\u0010\f\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r¢\u0006\u0002\b\u00100\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010(R\u001a\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u0010(R\u0014\u00101\u001a\u000202X\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0014\u00105\u001a\b\u0012\u0004\u0012\u0002060\nX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u000e\u00109\u001a\u00020\u0018X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0015\u001a\u00020\u0012X\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010(R\u0014\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00120\nX\u0004¢\u0006\u0002\n\u0000R\u001d\u0010>\u001a\u0004\u0018\u00010?8BX\u0002¢\u0006\f\n\u0004\bB\u0010C\u001a\u0004\b@\u0010A¨\u0006^"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentReloading;", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "classLoader", "Ljava/lang/ClassLoader;", "log", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "config", "Lio/ktor/server/config/ApplicationConfig;", "connectors", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "modules", "Lkotlin/Function1;", "Lio/ktor/server/application/Application;", "", "Lkotlin/ExtensionFunctionType;", "watchPaths", "", "parentCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "rootPath", "(Ljava/lang/ClassLoader;Lorg/slf4j/Logger;Lio/ktor/server/config/ApplicationConfig;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/CoroutineContext;Ljava/lang/String;)V", "developmentMode", "", "(Ljava/lang/ClassLoader;Lorg/slf4j/Logger;Lio/ktor/server/config/ApplicationConfig;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lkotlin/coroutines/CoroutineContext;Ljava/lang/String;Z)V", "_applicationClassLoader", "_applicationInstance", "application", "getApplication", "()Lio/ktor/server/application/Application;", "applicationInstanceLock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "getClassLoader", "()Ljava/lang/ClassLoader;", "getConfig", "()Lio/ktor/server/config/ApplicationConfig;", "configModulesNames", "configuredWatchPath", "getConfiguredWatchPath", "()Ljava/util/List;", "getConnectors", "getDevelopmentMode", "()Z", "getLog", "()Lorg/slf4j/Logger;", "getModules$ktor_server_host_common", "modulesNames", "getModulesNames$ktor_server_host_common", "monitor", "Lio/ktor/events/Events;", "getMonitor", "()Lio/ktor/events/Events;", "packageWatchKeys", "Ljava/nio/file/WatchKey;", "getParentCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "recreateInstance", "getRootPath", "()Ljava/lang/String;", "getWatchPaths$ktor_server_host_common", "watchPatterns", "watcher", "Ljava/nio/file/WatchService;", "getWatcher", "()Ljava/nio/file/WatchService;", "watcher$delegate", "Lkotlin/Lazy;", "avoidingDoubleStartup", "block", "Lkotlin/Function0;", "avoidingDoubleStartupFor", "fqName", "cleanupWatcher", "createApplication", "Lkotlin/Pair;", "createClassLoader", "currentApplication", "destroyApplication", "instantiateAndConfigureApplication", "currentClassLoader", "launchModuleByName", "name", "newInstance", "reload", "safeRiseEvent", "event", "Lio/ktor/events/EventDefinition;", "start", "stop", "watchUrls", "urls", "Ljava/net/URL;", "Companion", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentReloading.kt */
public final class ApplicationEngineEnvironmentReloading implements ApplicationEngineEnvironment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private ClassLoader _applicationClassLoader;
    private Application _applicationInstance;
    private final ReentrantReadWriteLock applicationInstanceLock;
    private final ClassLoader classLoader;
    private final ApplicationConfig config;
    private final List<String> configModulesNames;
    private final List<EngineConnectorConfig> connectors;
    private final boolean developmentMode;
    private final Logger log;
    private final List<Function1<Application, Unit>> modules;
    private final List<String> modulesNames;
    private final Events monitor;
    private List<? extends WatchKey> packageWatchKeys;
    private final CoroutineContext parentCoroutineContext;
    private boolean recreateInstance;
    private final String rootPath;
    private final List<String> watchPaths;
    private final List<String> watchPatterns;
    private final Lazy watcher$delegate;

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0089, code lost:
        r2 = r2.getList();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ApplicationEngineEnvironmentReloading(java.lang.ClassLoader r2, org.slf4j.Logger r3, io.ktor.server.config.ApplicationConfig r4, java.util.List<? extends io.ktor.server.engine.EngineConnectorConfig> r5, java.util.List<? extends kotlin.jvm.functions.Function1<? super io.ktor.server.application.Application, kotlin.Unit>> r6, java.util.List<java.lang.String> r7, kotlin.coroutines.CoroutineContext r8, java.lang.String r9, boolean r10) {
        /*
            r1 = this;
            java.lang.String r0 = "classLoader"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            java.lang.String r0 = "log"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "config"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "connectors"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "modules"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "watchPaths"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "parentCoroutineContext"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "rootPath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r1.<init>()
            r1.classLoader = r2
            r1.log = r3
            r1.config = r4
            r1.connectors = r5
            r1.modules = r6
            r1.watchPaths = r7
            r1.rootPath = r9
            r1.developmentMode = r10
            java.util.List r2 = r1.getConfiguredWatchPath()
            java.util.Collection r2 = (java.util.Collection) r2
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.List r2 = kotlin.collections.CollectionsKt.plus(r2, r7)
            r1.watchPatterns = r2
            boolean r3 = r1.getDevelopmentMode()
            if (r3 == 0) goto L_0x0061
            java.util.Collection r2 = (java.util.Collection) r2
            boolean r2 = r2.isEmpty()
            r2 = r2 ^ 1
            if (r2 == 0) goto L_0x0061
            io.ktor.server.engine.ClassLoaderAwareContinuationInterceptor r2 = io.ktor.server.engine.ClassLoaderAwareContinuationInterceptor.INSTANCE
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            kotlin.coroutines.CoroutineContext r8 = r8.plus(r2)
        L_0x0061:
            r1.parentCoroutineContext = r8
            io.ktor.server.application.Application r2 = new io.ktor.server.application.Application
            r3 = r1
            io.ktor.server.application.ApplicationEnvironment r3 = (io.ktor.server.application.ApplicationEnvironment) r3
            r2.<init>(r3)
            r1._applicationInstance = r2
            java.util.concurrent.locks.ReentrantReadWriteLock r2 = new java.util.concurrent.locks.ReentrantReadWriteLock
            r2.<init>()
            r1.applicationInstanceLock = r2
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
            r1.packageWatchKeys = r2
            r2 = r1
            io.ktor.server.engine.ApplicationEngineEnvironmentReloading r2 = (io.ktor.server.engine.ApplicationEngineEnvironmentReloading) r2
            io.ktor.server.config.ApplicationConfig r2 = r1.getConfig()
            java.lang.String r3 = "ktor.application.modules"
            io.ktor.server.config.ApplicationConfigValue r2 = r2.propertyOrNull(r3)
            if (r2 == 0) goto L_0x008f
            java.util.List r2 = r2.getList()
            if (r2 != 0) goto L_0x0093
        L_0x008f:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0093:
            r1.configModulesNames = r2
            r1.modulesNames = r2
            io.ktor.server.engine.ApplicationEngineEnvironmentReloading$watcher$2 r2 = io.ktor.server.engine.ApplicationEngineEnvironmentReloading$watcher$2.INSTANCE
            kotlin.jvm.functions.Function0 r2 = (kotlin.jvm.functions.Function0) r2
            kotlin.Lazy r2 = kotlin.LazyKt.lazy(r2)
            r1.watcher$delegate = r2
            io.ktor.events.Events r2 = new io.ktor.events.Events
            r2.<init>()
            r1.monitor = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ApplicationEngineEnvironmentReloading.<init>(java.lang.ClassLoader, org.slf4j.Logger, io.ktor.server.config.ApplicationConfig, java.util.List, java.util.List, java.util.List, kotlin.coroutines.CoroutineContext, java.lang.String, boolean):void");
    }

    public ClassLoader getClassLoader() {
        return this.classLoader;
    }

    public Logger getLog() {
        return this.log;
    }

    public ApplicationConfig getConfig() {
        return this.config;
    }

    public List<EngineConnectorConfig> getConnectors() {
        return this.connectors;
    }

    public final List<Function1<Application, Unit>> getModules$ktor_server_host_common() {
        return this.modules;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ApplicationEngineEnvironmentReloading(java.lang.ClassLoader r13, org.slf4j.Logger r14, io.ktor.server.config.ApplicationConfig r15, java.util.List r16, java.util.List r17, java.util.List r18, kotlin.coroutines.CoroutineContext r19, java.lang.String r20, boolean r21, int r22, kotlin.jvm.internal.DefaultConstructorMarker r23) {
        /*
            r12 = this;
            r0 = r22
            r1 = r0 & 32
            if (r1 == 0) goto L_0x000c
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r8 = r1
            goto L_0x000e
        L_0x000c:
            r8 = r18
        L_0x000e:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0018
            kotlin.coroutines.EmptyCoroutineContext r1 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            r9 = r1
            goto L_0x001a
        L_0x0018:
            r9 = r19
        L_0x001a:
            r1 = r0 & 128(0x80, float:1.794E-43)
            if (r1 == 0) goto L_0x0022
            java.lang.String r1 = ""
            r10 = r1
            goto L_0x0024
        L_0x0022:
            r10 = r20
        L_0x0024:
            r0 = r0 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L_0x002b
            r0 = 1
            r11 = 1
            goto L_0x002d
        L_0x002b:
            r11 = r21
        L_0x002d:
            r2 = r12
            r3 = r13
            r4 = r14
            r5 = r15
            r6 = r16
            r7 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ApplicationEngineEnvironmentReloading.<init>(java.lang.ClassLoader, org.slf4j.Logger, io.ktor.server.config.ApplicationConfig, java.util.List, java.util.List, java.util.List, kotlin.coroutines.CoroutineContext, java.lang.String, boolean, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final List<String> getWatchPaths$ktor_server_host_common() {
        return this.watchPaths;
    }

    public String getRootPath() {
        return this.rootPath;
    }

    public boolean getDevelopmentMode() {
        return this.developmentMode;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r0 = r0.getList();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<java.lang.String> getConfiguredWatchPath() {
        /*
            r2 = this;
            io.ktor.server.config.ApplicationConfig r0 = r2.getConfig()
            java.lang.String r1 = "ktor.deployment.watch"
            io.ktor.server.config.ApplicationConfigValue r0 = r0.propertyOrNull(r1)
            if (r0 == 0) goto L_0x0012
            java.util.List r0 = r0.getList()
            if (r0 != 0) goto L_0x0016
        L_0x0012:
            java.util.List r0 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0016:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ApplicationEngineEnvironmentReloading.getConfiguredWatchPath():java.util.List");
    }

    public CoroutineContext getParentCoroutineContext() {
        return this.parentCoroutineContext;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ ApplicationEngineEnvironmentReloading(java.lang.ClassLoader r12, org.slf4j.Logger r13, io.ktor.server.config.ApplicationConfig r14, java.util.List r15, java.util.List r16, java.util.List r17, kotlin.coroutines.CoroutineContext r18, java.lang.String r19, int r20, kotlin.jvm.internal.DefaultConstructorMarker r21) {
        /*
            r11 = this;
            r0 = r20
            r1 = r0 & 32
            if (r1 == 0) goto L_0x000c
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
            r8 = r1
            goto L_0x000e
        L_0x000c:
            r8 = r17
        L_0x000e:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0018
            kotlin.coroutines.EmptyCoroutineContext r1 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            r9 = r1
            goto L_0x001a
        L_0x0018:
            r9 = r18
        L_0x001a:
            r0 = r0 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L_0x0022
            java.lang.String r0 = ""
            r10 = r0
            goto L_0x0024
        L_0x0022:
            r10 = r19
        L_0x0024:
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ApplicationEngineEnvironmentReloading.<init>(java.lang.ClassLoader, org.slf4j.Logger, io.ktor.server.config.ApplicationConfig, java.util.List, java.util.List, java.util.List, kotlin.coroutines.CoroutineContext, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ApplicationEngineEnvironmentReloading(java.lang.ClassLoader r12, org.slf4j.Logger r13, io.ktor.server.config.ApplicationConfig r14, java.util.List<? extends io.ktor.server.engine.EngineConnectorConfig> r15, java.util.List<? extends kotlin.jvm.functions.Function1<? super io.ktor.server.application.Application, kotlin.Unit>> r16, java.util.List<java.lang.String> r17, kotlin.coroutines.CoroutineContext r18, java.lang.String r19) {
        /*
            r11 = this;
            java.lang.String r0 = "classLoader"
            r2 = r12
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "log"
            r3 = r13
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            java.lang.String r0 = "config"
            r4 = r14
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "connectors"
            r5 = r15
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            java.lang.String r0 = "modules"
            r6 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            java.lang.String r0 = "watchPaths"
            r7 = r17
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "parentCoroutineContext"
            r8 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            java.lang.String r0 = "rootPath"
            r9 = r19
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r10 = 1
            r1 = r11
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ApplicationEngineEnvironmentReloading.<init>(java.lang.ClassLoader, org.slf4j.Logger, io.ktor.server.config.ApplicationConfig, java.util.List, java.util.List, java.util.List, kotlin.coroutines.CoroutineContext, java.lang.String):void");
    }

    public final List<String> getModulesNames$ktor_server_host_common() {
        return this.modulesNames;
    }

    private final WatchService getWatcher() {
        return NioPathKt$$ExternalSyntheticApiModelOutline0.m(this.watcher$delegate.getValue());
    }

    public Events getMonitor() {
        return this.monitor;
    }

    public Application getApplication() {
        return currentApplication();
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public final void reload() {
        /*
            r6 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r6.applicationInstanceLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            int r2 = r0.getWriteHoldCount()
            r3 = 0
            if (r2 != 0) goto L_0x0012
            int r2 = r0.getReadHoldCount()
            goto L_0x0013
        L_0x0012:
            r2 = 0
        L_0x0013:
            r4 = 0
        L_0x0014:
            if (r4 >= r2) goto L_0x001c
            r1.unlock()
            int r4 = r4 + 1
            goto L_0x0014
        L_0x001c:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.lock()
            r6.destroyApplication()     // Catch:{ all -> 0x0048 }
            kotlin.Pair r4 = r6.createApplication()     // Catch:{ all -> 0x0048 }
            java.lang.Object r5 = r4.component1()     // Catch:{ all -> 0x0048 }
            io.ktor.server.application.Application r5 = (io.ktor.server.application.Application) r5     // Catch:{ all -> 0x0048 }
            java.lang.Object r4 = r4.component2()     // Catch:{ all -> 0x0048 }
            java.lang.ClassLoader r4 = (java.lang.ClassLoader) r4     // Catch:{ all -> 0x0048 }
            r6._applicationInstance = r5     // Catch:{ all -> 0x0048 }
            r6._applicationClassLoader = r4     // Catch:{ all -> 0x0048 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0048 }
        L_0x003c:
            if (r3 >= r2) goto L_0x0044
            r1.lock()
            int r3 = r3 + 1
            goto L_0x003c
        L_0x0044:
            r0.unlock()
            return
        L_0x0048:
            r4 = move-exception
        L_0x0049:
            if (r3 >= r2) goto L_0x0051
            r1.lock()
            int r3 = r3 + 1
            goto L_0x0049
        L_0x0051:
            r0.unlock()
            goto L_0x0056
        L_0x0055:
            throw r4
        L_0x0056:
            goto L_0x0055
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ApplicationEngineEnvironmentReloading.reload():void");
    }

    private final Application currentApplication() {
        ReentrantReadWriteLock.ReadLock readLock;
        int i;
        int readHoldCount;
        ReentrantReadWriteLock.WriteLock writeLock;
        ReentrantReadWriteLock.ReadLock readLock2 = this.applicationInstanceLock.readLock();
        readLock2.lock();
        try {
            Application application = this._applicationInstance;
            if (application != null) {
                if (getDevelopmentMode()) {
                    Collection arrayList = new ArrayList();
                    for (Object m : this.packageWatchKeys) {
                        List m2 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(NioPathKt$$ExternalSyntheticApiModelOutline0.m(m));
                        Intrinsics.checkNotNullExpressionValue(m2, "it.pollEvents()");
                        CollectionsKt.addAll(arrayList, m2);
                    }
                    List list = (List) arrayList;
                    if (!list.isEmpty()) {
                        getLog().info("Changes in application detected.");
                        int size = list.size();
                        while (true) {
                            Thread.sleep(200);
                            Collection arrayList2 = new ArrayList();
                            for (Object m3 : this.packageWatchKeys) {
                                List m4 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(NioPathKt$$ExternalSyntheticApiModelOutline0.m(m3));
                                Intrinsics.checkNotNullExpressionValue(m4, "it.pollEvents()");
                                CollectionsKt.addAll(arrayList2, m4);
                            }
                            List list2 = (List) arrayList2;
                            if (list2.isEmpty()) {
                                break;
                            }
                            getLog().debug("Waiting for more changes.");
                            size += list2.size();
                        }
                        Logger log2 = getLog();
                        log2.debug("Changes to " + size + " files caused application restart.");
                        for (Object m5 : CollectionsKt.take(list, 5)) {
                            WatchEvent m6 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(m5);
                            Logger log3 = getLog();
                            log3.debug("...  " + m6.context());
                        }
                        ReentrantReadWriteLock reentrantReadWriteLock = this.applicationInstanceLock;
                        readLock = reentrantReadWriteLock.readLock();
                        i = 0;
                        readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
                        for (int i2 = 0; i2 < readHoldCount; i2++) {
                            readLock.unlock();
                        }
                        writeLock = reentrantReadWriteLock.writeLock();
                        writeLock.lock();
                        destroyApplication();
                        Pair<Application, ClassLoader> createApplication = createApplication();
                        this._applicationInstance = createApplication.component1();
                        this._applicationClassLoader = createApplication.component2();
                        Unit unit = Unit.INSTANCE;
                        while (i < readHoldCount) {
                            readLock.lock();
                            i++;
                        }
                        writeLock.unlock();
                        application = this._applicationInstance;
                        if (application == null) {
                            throw new IllegalStateException("ApplicationEngineEnvironment was not started".toString());
                        }
                    }
                }
                readLock2.unlock();
                return application;
            }
            throw new IllegalStateException("ApplicationEngineEnvironment was not started".toString());
        } catch (Throwable th) {
            readLock2.unlock();
            throw th;
        }
    }

    private final Pair<Application, ClassLoader> createApplication() {
        ClassLoader createClassLoader = createClassLoader();
        Thread currentThread = Thread.currentThread();
        ClassLoader contextClassLoader = currentThread.getContextClassLoader();
        currentThread.setContextClassLoader(createClassLoader);
        try {
            return TuplesKt.to(instantiateAndConfigureApplication(createClassLoader), createClassLoader);
        } finally {
            currentThread.setContextClassLoader(contextClassLoader);
        }
    }

    private final ClassLoader createClassLoader() {
        ClassLoader classLoader2 = getClassLoader();
        if (!getDevelopmentMode()) {
            getLog().info("Autoreload is disabled because the development mode is off.");
            return classLoader2;
        }
        List<String> list = this.watchPatterns;
        if (list.isEmpty()) {
            getLog().info("No ktor.deployment.watch patterns specified, automatic reload is not active.");
            return classLoader2;
        }
        Set<URL> allURLs = ClassLoadersKt.allURLs(classLoader2);
        String parent = new File(System.getProperty("java.home")).getParent();
        Iterable<URL> iterable = allURLs;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (URL file : iterable) {
            arrayList.add(file.getFile());
        }
        Logger log2 = getLog();
        log2.debug("Java Home: " + parent);
        Logger log3 = getLog();
        StringBuilder sb = new StringBuilder("Class Loader: ");
        sb.append(classLoader2);
        sb.append(": ");
        Collection arrayList2 = new ArrayList();
        for (Object next : (List) arrayList) {
            String str = ((String) next).toString();
            Intrinsics.checkNotNullExpressionValue(parent, "jre");
            if (!StringsKt.startsWith$default(str, parent, false, 2, (Object) null)) {
                arrayList2.add(next);
            }
        }
        sb.append((List) arrayList2);
        log3.debug(sb.toString());
        Collection hashSet = new HashSet();
        for (Class protectionDomain : CollectionsKt.listOf(ApplicationEnvironment.class, ApplicationEngineEnvironment.class, Pipeline.class, HttpStatusCode.class, Function1.class, Logger.class, ByteReadChannel.class, Input.class, Attributes.class)) {
            URL location = protectionDomain.getProtectionDomain().getCodeSource().getLocation();
            if (location != null) {
                hashSet.add(location);
            }
        }
        HashSet hashSet2 = (HashSet) hashSet;
        Collection arrayList3 = new ArrayList();
        for (Object next2 : iterable) {
            URL url = (URL) next2;
            if (!hashSet2.contains(url)) {
                Iterable iterable2 = list;
                if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                    Iterator it = iterable2.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        String url2 = url.toString();
                        Intrinsics.checkNotNullExpressionValue(url2, "url.toString()");
                        if (StringsKt.contains$default((CharSequence) url2, (CharSequence) (String) it.next(), false, 2, (Object) null)) {
                            String path = url.getPath();
                            if (path == null) {
                                path = "";
                            } else {
                                Intrinsics.checkNotNullExpressionValue(path, "url.path ?: \"\"");
                            }
                            Intrinsics.checkNotNullExpressionValue(parent, "jre");
                            if (!StringsKt.startsWith$default(path, parent, false, 2, (Object) null)) {
                                arrayList3.add(next2);
                            }
                        }
                    }
                }
            }
        }
        List list2 = (List) arrayList3;
        if (list2.isEmpty()) {
            getLog().info("No ktor.deployment.watch patterns match classpath entries, automatic reload is not active");
            return classLoader2;
        }
        watchUrls(list2);
        return new OverridingClassLoader(list2, classLoader2);
    }

    private final void safeRiseEvent(EventDefinition<Application> eventDefinition, Application application) {
        EventsKt.raiseCatching$default(getMonitor(), eventDefinition, application, (Logger) null, 4, (Object) null);
    }

    private final void destroyApplication() {
        Application application = this._applicationInstance;
        ClassLoader classLoader2 = this._applicationClassLoader;
        OverridingClassLoader overridingClassLoader = null;
        this._applicationInstance = null;
        this._applicationClassLoader = null;
        if (application != null) {
            safeRiseEvent(DefaultApplicationEventsKt.getApplicationStopping(), application);
            try {
                application.dispose();
                if (classLoader2 instanceof OverridingClassLoader) {
                    overridingClassLoader = (OverridingClassLoader) classLoader2;
                }
                if (overridingClassLoader != null) {
                    overridingClassLoader.close();
                }
            } catch (Throwable th) {
                getLog().error("Failed to destroy application instance.", th);
            }
            safeRiseEvent(DefaultApplicationEventsKt.getApplicationStopped(), application);
        }
        for (Object m : this.packageWatchKeys) {
            NioPathKt$$ExternalSyntheticApiModelOutline0.m(NioPathKt$$ExternalSyntheticApiModelOutline0.m(m));
        }
        this.packageWatchKeys = new ArrayList();
    }

    private final void watchUrls(List<URL> list) {
        Object obj;
        HashSet hashSet = new HashSet();
        Iterator<URL> it = list.iterator();
        while (true) {
            Object obj2 = null;
            if (!it.hasNext()) {
                break;
            }
            String path = it.next().getPath();
            if (path != null) {
                String decode = URLDecoder.decode(path, "utf-8");
                try {
                    Result.Companion companion = Result.Companion;
                    ApplicationEngineEnvironmentReloading applicationEngineEnvironmentReloading = this;
                    obj = Result.m1774constructorimpl(new File(decode).toPath());
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.m1774constructorimpl(ResultKt.createFailure(th));
                }
                if (!Result.m1780isFailureimpl(obj)) {
                    obj2 = obj;
                }
                Path m = NioPathKt$$ExternalSyntheticApiModelOutline0.m(obj2);
                if (m != null && Files.exists(m, new LinkOption[0])) {
                    ApplicationEngineEnvironmentReloading$watchUrls$visitor$1 applicationEngineEnvironmentReloading$watchUrls$visitor$1 = new ApplicationEngineEnvironmentReloading$watchUrls$visitor$1(hashSet);
                    if (Files.isDirectory(m, new LinkOption[0])) {
                        Path unused = Files.walkFileTree(m, NioPathKt$$ExternalSyntheticApiModelOutline0.m((Object) applicationEngineEnvironmentReloading$watchUrls$visitor$1));
                    }
                }
            }
        }
        Iterable<Object> iterable = hashSet;
        for (Object m2 : iterable) {
            Path m3 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(m2);
            Logger log2 = getLog();
            log2.debug("Watching " + m3 + " for changes.");
        }
        WatchEvent.Modifier modifier = AutoReloadUtilsKt.get_com_sun_nio_file_SensitivityWatchEventModifier_HIGH();
        WatchEvent.Modifier[] modifierArr = modifier != null ? new WatchEvent.Modifier[]{modifier} : new WatchEvent.Modifier[0];
        Collection arrayList = new ArrayList();
        for (Object m4 : iterable) {
            Path m5 = NioPathKt$$ExternalSyntheticApiModelOutline0.m(m4);
            WatchService watcher = getWatcher();
            WatchKey m6 = watcher != null ? m5.register(watcher, new WatchEvent.Kind[]{NioPathKt$$ExternalSyntheticApiModelOutline0.m$1(), NioPathKt$$ExternalSyntheticApiModelOutline0.m$2(), NioPathKt$$ExternalSyntheticApiModelOutline0.m()}, (WatchEvent.Modifier[]) Arrays.copyOf(modifierArr, modifierArr.length)) : null;
            if (m6 != null) {
                arrayList.add(m6);
            }
        }
        this.packageWatchKeys = (List) arrayList;
    }

    public void start() {
        ReentrantReadWriteLock reentrantReadWriteLock = this.applicationInstanceLock;
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        int i = 0;
        int readHoldCount = reentrantReadWriteLock.getWriteHoldCount() == 0 ? reentrantReadWriteLock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            readLock.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
        try {
            Pair<Application, ClassLoader> createApplication = createApplication();
            this._applicationInstance = createApplication.component1();
            this._applicationClassLoader = createApplication.component2();
            Unit unit = Unit.INSTANCE;
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
        } catch (Throwable th) {
            while (i < readHoldCount) {
                readLock.lock();
                i++;
            }
            writeLock.unlock();
            throw th;
        }
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void stop() {
        /*
            r5 = this;
            java.util.concurrent.locks.ReentrantReadWriteLock r0 = r5.applicationInstanceLock
            java.util.concurrent.locks.ReentrantReadWriteLock$ReadLock r1 = r0.readLock()
            int r2 = r0.getWriteHoldCount()
            r3 = 0
            if (r2 != 0) goto L_0x0012
            int r2 = r0.getReadHoldCount()
            goto L_0x0013
        L_0x0012:
            r2 = 0
        L_0x0013:
            r4 = 0
        L_0x0014:
            if (r4 >= r2) goto L_0x001c
            r1.unlock()
            int r4 = r4 + 1
            goto L_0x0014
        L_0x001c:
            java.util.concurrent.locks.ReentrantReadWriteLock$WriteLock r0 = r0.writeLock()
            r0.lock()
            r5.destroyApplication()     // Catch:{ all -> 0x0043 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0043 }
        L_0x0028:
            if (r3 >= r2) goto L_0x0030
            r1.lock()
            int r3 = r3 + 1
            goto L_0x0028
        L_0x0030:
            r0.unlock()
            java.util.List<java.lang.String> r0 = r5.watchPatterns
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L_0x0042
            r5.cleanupWatcher()
        L_0x0042:
            return
        L_0x0043:
            r4 = move-exception
        L_0x0044:
            if (r3 >= r2) goto L_0x004c
            r1.lock()
            int r3 = r3 + 1
            goto L_0x0044
        L_0x004c:
            r0.unlock()
            goto L_0x0051
        L_0x0050:
            throw r4
        L_0x0051:
            goto L_0x0050
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.ApplicationEngineEnvironmentReloading.stop():void");
    }

    private final Application instantiateAndConfigureApplication(ClassLoader classLoader2) {
        Application application;
        if (this.recreateInstance || (application = this._applicationInstance) == null) {
            application = new Application(this);
        } else {
            this.recreateInstance = true;
            Intrinsics.checkNotNull(application);
        }
        safeRiseEvent(DefaultApplicationEventsKt.getApplicationStarting(), application);
        avoidingDoubleStartup(new ApplicationEngineEnvironmentReloading$instantiateAndConfigureApplication$1(this, classLoader2, application));
        safeRiseEvent(DefaultApplicationEventsKt.getApplicationStarted(), application);
        return application;
    }

    /* access modifiers changed from: private */
    public final void launchModuleByName(String str, ClassLoader classLoader2, Application application) {
        avoidingDoubleStartupFor(str, new ApplicationEngineEnvironmentReloading$launchModuleByName$1(this, classLoader2, str, application));
    }

    private final void avoidingDoubleStartup(Function0<Unit> function0) {
        try {
            function0.invoke();
        } finally {
            List list = AutoReloadUtilsKt.getCurrentStartupModules().get();
            if (list != null && list.isEmpty()) {
                AutoReloadUtilsKt.getCurrentStartupModules().remove();
            }
        }
    }

    private final void avoidingDoubleStartupFor(String str, Function0<Unit> function0) {
        ThreadLocal<List<String>> currentStartupModules = AutoReloadUtilsKt.getCurrentStartupModules();
        List<String> list = currentStartupModules.get();
        if (list == null) {
            list = new ArrayList<>(1);
            currentStartupModules.set(list);
        }
        List list2 = list;
        if (!list2.contains(str)) {
            list2.add(str);
            try {
                function0.invoke();
            } finally {
                list2.remove(str);
            }
        } else {
            throw new IllegalStateException(("Module startup is already in progress for function " + str + " (recursive module startup from module main?)").toString());
        }
    }

    private final void cleanupWatcher() {
        try {
            WatchService watcher = getWatcher();
            if (watcher != null) {
                watcher.close();
            }
        } catch (NoClassDefFoundError unused) {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironmentReloading$Companion;", "", "()V", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: ApplicationEngineEnvironmentReloading.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
