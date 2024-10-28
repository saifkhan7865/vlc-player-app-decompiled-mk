package nl.dionsegijn.konfetti.core.emitter;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0013R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0015"}, d2 = {"Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;", "", "emitter", "Lnl/dionsegijn/konfetti/core/emitter/Emitter;", "(Lnl/dionsegijn/konfetti/core/emitter/Emitter;)V", "amountPerMs", "", "getAmountPerMs", "()F", "setAmountPerMs", "(F)V", "emittingTime", "", "getEmittingTime", "()J", "setEmittingTime", "(J)V", "max", "amount", "", "perSecond", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: EmitterConfig.kt */
public final class EmitterConfig {
    private float amountPerMs;
    private long emittingTime;

    public EmitterConfig(Emitter emitter) {
        Intrinsics.checkNotNullParameter(emitter, "emitter");
        this.emittingTime = TimeUnit.MILLISECONDS.convert(emitter.component1(), emitter.component2());
    }

    public final long getEmittingTime() {
        return this.emittingTime;
    }

    public final void setEmittingTime(long j) {
        this.emittingTime = j;
    }

    public final float getAmountPerMs() {
        return this.amountPerMs;
    }

    public final void setAmountPerMs(float f) {
        this.amountPerMs = f;
    }

    public final EmitterConfig max(int i) {
        this.amountPerMs = ((float) (this.emittingTime / ((long) i))) / 1000.0f;
        return this;
    }

    public final EmitterConfig perSecond(int i) {
        this.amountPerMs = 1.0f / ((float) i);
        return this;
    }
}
