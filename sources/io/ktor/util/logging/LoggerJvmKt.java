package io.ktor.util.logging;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0019\u0010\u0000\u001a\u00020\u0001*\u00060\u0002j\u0002`\u00038F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0004*\n\u0010\u0005\"\u00020\u00022\u00020\u0002¨\u0006\u0006"}, d2 = {"isTraceEnabled", "", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "(Lorg/slf4j/Logger;)Z", "Logger", "ktor-utils"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: LoggerJvm.kt */
public final class LoggerJvmKt {
    public static final boolean isTraceEnabled(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, "<this>");
        return logger.isTraceEnabled();
    }
}
