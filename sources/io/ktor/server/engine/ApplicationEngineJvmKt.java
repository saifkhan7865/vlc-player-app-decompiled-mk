package io.ktor.server.engine;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"stop", "", "Lio/ktor/server/engine/ApplicationEngine;", "gracePeriod", "", "timeout", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineJvm.kt */
public final class ApplicationEngineJvmKt {
    public static final void stop(ApplicationEngine applicationEngine, long j, long j2, TimeUnit timeUnit) {
        Intrinsics.checkNotNullParameter(applicationEngine, "<this>");
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        applicationEngine.stop(timeUnit.toMillis(j), timeUnit.toMillis(j2));
    }
}
