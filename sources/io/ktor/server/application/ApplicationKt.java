package io.ktor.server.application;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"log", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "Lio/ktor/server/application/Application;", "getLog", "(Lio/ktor/server/application/Application;)Lorg/slf4j/Logger;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Application.kt */
public final class ApplicationKt {
    public static final Logger getLog(Application application) {
        Intrinsics.checkNotNullParameter(application, "<this>");
        return application.getEnvironment().getLog();
    }
}
