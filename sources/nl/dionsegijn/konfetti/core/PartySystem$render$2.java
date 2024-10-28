package nl.dionsegijn.konfetti.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import nl.dionsegijn.konfetti.core.emitter.Confetti;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lnl/dionsegijn/konfetti/core/emitter/Confetti;", "invoke", "(Lnl/dionsegijn/konfetti/core/emitter/Confetti;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartySystem.kt */
final class PartySystem$render$2 extends Lambda implements Function1<Confetti, Boolean> {
    public static final PartySystem$render$2 INSTANCE = new PartySystem$render$2();

    PartySystem$render$2() {
        super(1);
    }

    public final Boolean invoke(Confetti confetti) {
        Intrinsics.checkNotNullParameter(confetti, "it");
        return Boolean.valueOf(confetti.isDead());
    }
}
