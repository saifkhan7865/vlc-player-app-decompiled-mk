package io.ktor.server.application;

import io.ktor.events.Events;
import io.ktor.server.config.ApplicationConfig;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0016\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0014X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0012\u0010\u0017\u001a\u00020\u0018X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0012\u0010\u001b\u001a\u00020\u001cX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e¨\u0006\u001f"}, d2 = {"Lio/ktor/server/application/ApplicationEnvironment;", "", "classLoader", "Ljava/lang/ClassLoader;", "getClassLoader", "()Ljava/lang/ClassLoader;", "config", "Lio/ktor/server/config/ApplicationConfig;", "getConfig", "()Lio/ktor/server/config/ApplicationConfig;", "developmentMode", "", "getDevelopmentMode", "()Z", "log", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "getLog", "()Lorg/slf4j/Logger;", "monitor", "Lio/ktor/events/Events;", "getMonitor", "()Lio/ktor/events/Events;", "parentCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getParentCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "rootPath", "", "getRootPath", "()Ljava/lang/String;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEnvironment.kt */
public interface ApplicationEnvironment {
    ClassLoader getClassLoader();

    ApplicationConfig getConfig();

    boolean getDevelopmentMode();

    Logger getLog();

    Events getMonitor();

    CoroutineContext getParentCoroutineContext();

    String getRootPath();
}
