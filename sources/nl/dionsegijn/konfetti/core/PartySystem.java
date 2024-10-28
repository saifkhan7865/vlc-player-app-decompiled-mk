package nl.dionsegijn.konfetti.core;

import android.content.res.Resources;
import android.graphics.Rect;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.core.emitter.BaseEmitter;
import nl.dionsegijn.konfetti.core.emitter.Confetti;
import nl.dionsegijn.konfetti.core.emitter.PartyEmitter;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0011J\u001c\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006!"}, d2 = {"Lnl/dionsegijn/konfetti/core/PartySystem;", "", "party", "Lnl/dionsegijn/konfetti/core/Party;", "createdAt", "", "pixelDensity", "", "(Lnl/dionsegijn/konfetti/core/Party;JF)V", "activeParticles", "", "Lnl/dionsegijn/konfetti/core/emitter/Confetti;", "getCreatedAt", "()J", "emitter", "Lnl/dionsegijn/konfetti/core/emitter/BaseEmitter;", "enabled", "", "getEnabled", "()Z", "setEnabled", "(Z)V", "getParty", "()Lnl/dionsegijn/konfetti/core/Party;", "getActiveParticleAmount", "", "isDoneEmitting", "render", "", "Lnl/dionsegijn/konfetti/core/Particle;", "deltaTime", "drawArea", "Landroid/graphics/Rect;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartySystem.kt */
public final class PartySystem {
    private final List<Confetti> activeParticles;
    private final long createdAt;
    private BaseEmitter emitter;
    private boolean enabled;
    private final Party party;

    public PartySystem(Party party2, long j, float f) {
        Intrinsics.checkNotNullParameter(party2, "party");
        this.party = party2;
        this.createdAt = j;
        this.enabled = true;
        this.emitter = new PartyEmitter(party2.getEmitter(), f, (Random) null, 4, (DefaultConstructorMarker) null);
        this.activeParticles = new ArrayList();
    }

    public final Party getParty() {
        return this.party;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PartySystem(Party party2, long j, float f, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(party2, (i & 2) != 0 ? System.currentTimeMillis() : j, (i & 4) != 0 ? Resources.getSystem().getDisplayMetrics().density : f);
    }

    public final long getCreatedAt() {
        return this.createdAt;
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final void setEnabled(boolean z) {
        this.enabled = z;
    }

    public final List<Particle> render(float f, Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "drawArea");
        if (this.enabled) {
            this.activeParticles.addAll(this.emitter.createConfetti(f, this.party, rect));
        }
        for (Confetti render : this.activeParticles) {
            render.render(f, rect);
        }
        CollectionsKt.removeAll(this.activeParticles, PartySystem$render$2.INSTANCE);
        Collection arrayList = new ArrayList();
        for (Object next : this.activeParticles) {
            if (((Confetti) next).getDrawParticle()) {
                arrayList.add(next);
            }
        }
        Iterable<Confetti> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Confetti particle : iterable) {
            arrayList2.add(PartySystemKt.toParticle(particle));
        }
        return (List) arrayList2;
    }

    public final boolean isDoneEmitting() {
        return (this.emitter.isFinished() && this.activeParticles.size() == 0) || (!this.enabled && this.activeParticles.size() == 0);
    }

    public final int getActiveParticleAmount() {
        return this.activeParticles.size();
    }
}
