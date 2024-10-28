package io.ktor.server.engine;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"WORKING_DIRECTORY_PATH", "", "getWORKING_DIRECTORY_PATH", "()Ljava/lang/String;", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ServerEngineUtilsJvm.kt */
public final class ServerEngineUtilsJvmKt {
    public static final String getWORKING_DIRECTORY_PATH() {
        String canonicalPath = new File(".").getCanonicalPath();
        Intrinsics.checkNotNullExpressionValue(canonicalPath, "File(\".\").canonicalPath");
        return canonicalPath;
    }
}
