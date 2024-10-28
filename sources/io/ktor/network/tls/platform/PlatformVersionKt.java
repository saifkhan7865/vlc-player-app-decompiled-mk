package io.ktor.network.tls.platform;

import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001b\u0010\u0000\u001a\u00020\u00018@X\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0006"}, d2 = {"platformVersion", "Lio/ktor/network/tls/platform/PlatformVersion;", "getPlatformVersion", "()Lio/ktor/network/tls/platform/PlatformVersion;", "platformVersion$delegate", "Lkotlin/Lazy;", "ktor-network-tls"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PlatformVersion.kt */
public final class PlatformVersionKt {
    private static final Lazy platformVersion$delegate = LazyKt.lazy(PlatformVersionKt$platformVersion$2.INSTANCE);

    public static final PlatformVersion getPlatformVersion() {
        return (PlatformVersion) platformVersion$delegate.getValue();
    }
}
