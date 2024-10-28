package nl.dionsegijn.konfetti.xml.listeners;

import kotlin.Metadata;
import nl.dionsegijn.konfetti.core.Party;
import nl.dionsegijn.konfetti.xml.KonfettiView;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&¨\u0006\u000b"}, d2 = {"Lnl/dionsegijn/konfetti/xml/listeners/OnParticleSystemUpdateListener;", "", "onParticleSystemEnded", "", "view", "Lnl/dionsegijn/konfetti/xml/KonfettiView;", "party", "Lnl/dionsegijn/konfetti/core/Party;", "activeSystems", "", "onParticleSystemStarted", "xml_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OnParticleSystemUpdateListener.kt */
public interface OnParticleSystemUpdateListener {
    void onParticleSystemEnded(KonfettiView konfettiView, Party party, int i);

    void onParticleSystemStarted(KonfettiView konfettiView, Party party, int i);
}
