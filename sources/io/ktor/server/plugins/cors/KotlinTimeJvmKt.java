package io.ktor.server.plugins.cors;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"+\u0010\u0002\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0000\u001a\u00020\u00018F@FX\u000eø\u0001\u0000¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u0002\u0004\n\u0002\b\u0019¨\u0006\b"}, d2 = {"newMaxAge", "Lkotlin/time/Duration;", "maxAgeDuration", "Lio/ktor/server/plugins/cors/CORSConfig;", "getMaxAgeDuration", "(Lio/ktor/server/plugins/cors/CORSConfig;)J", "setMaxAgeDuration-HG0u8IE", "(Lio/ktor/server/plugins/cors/CORSConfig;J)V", "ktor-server-cors"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: KotlinTimeJvm.kt */
public final class KotlinTimeJvmKt {
    public static final long getMaxAgeDuration(CORSConfig cORSConfig) {
        Intrinsics.checkNotNullParameter(cORSConfig, "<this>");
        Duration.Companion companion = Duration.Companion;
        return DurationKt.toDuration(cORSConfig.getMaxAgeInSeconds(), DurationUnit.SECONDS);
    }

    /* renamed from: setMaxAgeDuration-HG0u8IE  reason: not valid java name */
    public static final void m1481setMaxAgeDurationHG0u8IE(CORSConfig cORSConfig, long j) {
        Intrinsics.checkNotNullParameter(cORSConfig, "$this$maxAgeDuration");
        if (!Duration.m1025isNegativeimpl(j)) {
            cORSConfig.setMaxAgeInSeconds(MathKt.roundToLong(Duration.m1035toDoubleimpl(j, DurationUnit.SECONDS)));
            return;
        }
        throw new IllegalArgumentException("Only non-negative durations can be specified".toString());
    }
}
