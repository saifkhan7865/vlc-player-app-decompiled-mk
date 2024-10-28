package nl.dionsegijn.konfetti.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.core.emitter.Confetti;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toParticle", "Lnl/dionsegijn/konfetti/core/Particle;", "Lnl/dionsegijn/konfetti/core/emitter/Confetti;", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartySystem.kt */
public final class PartySystemKt {
    public static final Particle toParticle(Confetti confetti) {
        Intrinsics.checkNotNullParameter(confetti, "<this>");
        return new Particle(confetti.getLocation().getX(), confetti.getLocation().getY(), confetti.getWidth(), confetti.getWidth(), confetti.getAlphaColor(), confetti.getRotation(), confetti.getScaleX(), confetti.getShape(), confetti.getAlpha());
    }
}
