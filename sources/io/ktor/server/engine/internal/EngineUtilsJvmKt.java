package io.ktor.server.engine.internal;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0010\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"OS_NAME", "", "escapeHostname", "value", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineUtilsJvm.kt */
public final class EngineUtilsJvmKt {
    private static final String OS_NAME;

    static {
        String property = System.getProperty("os.name", "");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(\"os.name\", \"\")");
        String lowerCase = property.toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
        OS_NAME = lowerCase;
    }

    public static final String escapeHostname(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        if (StringsKt.contains$default((CharSequence) OS_NAME, (CharSequence) "windows", false, 2, (Object) null) && Intrinsics.areEqual((Object) str, (Object) "0.0.0.0")) {
            return "127.0.0.1";
        }
        return str;
    }
}
