package io.ktor.network.tls.platform;

import io.ktor.network.tls.platform.PlatformVersion;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/network/tls/platform/PlatformVersion;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PlatformVersion.kt */
final class PlatformVersionKt$platformVersion$2 extends Lambda implements Function0<PlatformVersion> {
    public static final PlatformVersionKt$platformVersion$2 INSTANCE = new PlatformVersionKt$platformVersion$2();

    PlatformVersionKt$platformVersion$2() {
        super(0);
    }

    public final PlatformVersion invoke() {
        PlatformVersion.Companion companion = PlatformVersion.Companion;
        String property = System.getProperty("java.version");
        Intrinsics.checkNotNullExpressionValue(property, "getProperty(\"java.version\")");
        return companion.invoke(property);
    }
}
