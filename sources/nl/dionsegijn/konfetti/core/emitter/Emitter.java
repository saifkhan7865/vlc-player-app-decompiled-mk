package nl.dionsegijn.konfetti.core.emitter;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0019"}, d2 = {"Lnl/dionsegijn/konfetti/core/emitter/Emitter;", "", "duration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(JLjava/util/concurrent/TimeUnit;)V", "getDuration", "()J", "getTimeUnit", "()Ljava/util/concurrent/TimeUnit;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "max", "Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;", "amount", "perSecond", "toString", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmitterConfig.kt */
public final class Emitter {
    private final long duration;
    private final TimeUnit timeUnit;

    public static /* synthetic */ Emitter copy$default(Emitter emitter, long j, TimeUnit timeUnit2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = emitter.duration;
        }
        if ((i & 2) != 0) {
            timeUnit2 = emitter.timeUnit;
        }
        return emitter.copy(j, timeUnit2);
    }

    public final long component1() {
        return this.duration;
    }

    public final TimeUnit component2() {
        return this.timeUnit;
    }

    public final Emitter copy(long j, TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter(timeUnit2, "timeUnit");
        return new Emitter(j, timeUnit2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Emitter)) {
            return false;
        }
        Emitter emitter = (Emitter) obj;
        return this.duration == emitter.duration && this.timeUnit == emitter.timeUnit;
    }

    public int hashCode() {
        return (UInt$$ExternalSyntheticBackport0.m(this.duration) * 31) + this.timeUnit.hashCode();
    }

    public String toString() {
        return "Emitter(duration=" + this.duration + ", timeUnit=" + this.timeUnit + ')';
    }

    public Emitter(long j, TimeUnit timeUnit2) {
        Intrinsics.checkNotNullParameter(timeUnit2, "timeUnit");
        this.duration = j;
        this.timeUnit = timeUnit2;
    }

    public final long getDuration() {
        return this.duration;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Emitter(long j, TimeUnit timeUnit2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(j, (i & 2) != 0 ? TimeUnit.MILLISECONDS : timeUnit2);
    }

    public final TimeUnit getTimeUnit() {
        return this.timeUnit;
    }

    public final EmitterConfig max(int i) {
        return new EmitterConfig(this).max(i);
    }

    public final EmitterConfig perSecond(int i) {
        return new EmitterConfig(this).perSecond(i);
    }
}
