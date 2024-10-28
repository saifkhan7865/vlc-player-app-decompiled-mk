package nl.dionsegijn.konfetti.xml;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.core.PartySystem;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lnl/dionsegijn/konfetti/core/PartySystem;", "invoke", "(Lnl/dionsegijn/konfetti/core/PartySystem;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: KonfettiView.kt */
final class KonfettiView$stop$1 extends Lambda implements Function1<PartySystem, Boolean> {
    final /* synthetic */ Party $party;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KonfettiView$stop$1(Party party) {
        super(1);
        this.$party = party;
    }

    public final Boolean invoke(PartySystem partySystem) {
        Intrinsics.checkNotNullParameter(partySystem, "it");
        return Boolean.valueOf(Intrinsics.areEqual((Object) partySystem.getParty(), (Object) this.$party));
    }
}
