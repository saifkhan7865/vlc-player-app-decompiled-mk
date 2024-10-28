package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.application.ApplicationEnvironment;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0018\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lio/ktor/server/engine/ApplicationEngineEnvironment;", "Lio/ktor/server/application/ApplicationEnvironment;", "application", "Lio/ktor/server/application/Application;", "getApplication", "()Lio/ktor/server/application/Application;", "connectors", "", "Lio/ktor/server/engine/EngineConnectorConfig;", "getConnectors", "()Ljava/util/List;", "start", "", "stop", "ktor-server-host-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironment.kt */
public interface ApplicationEngineEnvironment extends ApplicationEnvironment {
    Application getApplication();

    List<EngineConnectorConfig> getConnectors();

    void start();

    void stop();
}
