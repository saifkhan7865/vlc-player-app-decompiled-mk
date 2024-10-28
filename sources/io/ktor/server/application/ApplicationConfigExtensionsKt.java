package io.ktor.server.application;

import io.ktor.server.config.ApplicationConfig;
import io.ktor.server.config.ApplicationConfigValue;
import io.ktor.server.engine.ConfigKeys;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"host", "", "Lio/ktor/server/config/ApplicationConfig;", "getHost", "(Lio/ktor/server/config/ApplicationConfig;)Ljava/lang/String;", "port", "", "getPort", "(Lio/ktor/server/config/ApplicationConfig;)I", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationConfigExtensions.kt */
public final class ApplicationConfigExtensionsKt {
    public static final int getPort(ApplicationConfig applicationConfig) {
        String string;
        Intrinsics.checkNotNullParameter(applicationConfig, "<this>");
        ApplicationConfigValue propertyOrNull = applicationConfig.propertyOrNull(ConfigKeys.hostPortPath);
        if (propertyOrNull == null || (string = propertyOrNull.getString()) == null) {
            return 8080;
        }
        return Integer.parseInt(string);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000d, code lost:
        r1 = r1.getString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.String getHost(io.ktor.server.config.ApplicationConfig r1) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            java.lang.String r0 = "ktor.deployment.host"
            io.ktor.server.config.ApplicationConfigValue r1 = r1.propertyOrNull(r0)
            if (r1 == 0) goto L_0x0013
            java.lang.String r1 = r1.getString()
            if (r1 != 0) goto L_0x0015
        L_0x0013:
            java.lang.String r1 = "0.0.0.0"
        L_0x0015:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.application.ApplicationConfigExtensionsKt.getHost(io.ktor.server.config.ApplicationConfig):java.lang.String");
    }
}
