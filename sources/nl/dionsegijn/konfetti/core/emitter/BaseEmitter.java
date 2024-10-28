package nl.dionsegijn.konfetti.core.emitter;

import android.graphics.Rect;
import java.util.List;
import kotlin.Metadata;
import nl.dionsegijn.konfetti.core.Party;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&¨\u0006\u000e"}, d2 = {"Lnl/dionsegijn/konfetti/core/emitter/BaseEmitter;", "", "()V", "createConfetti", "", "Lnl/dionsegijn/konfetti/core/emitter/Confetti;", "deltaTime", "", "party", "Lnl/dionsegijn/konfetti/core/Party;", "drawArea", "Landroid/graphics/Rect;", "isFinished", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseEmitter.kt */
public abstract class BaseEmitter {
    public abstract List<Confetti> createConfetti(float f, Party party, Rect rect);

    public abstract boolean isFinished();
}
