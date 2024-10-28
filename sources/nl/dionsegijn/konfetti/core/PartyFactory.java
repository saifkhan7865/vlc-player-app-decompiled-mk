package nl.dionsegijn.konfetti.core;

import androidx.constraintlayout.motion.widget.Key;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.core.Position;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\bJ\u0014\u0010\f\u001a\u00020\u00002\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\rJ\u000e\u0010\u000e\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\nJ\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013J&\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0013J\u0016\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0019J&\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u00192\u0006\u0010\u0018\u001a\u00020\u0019J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u0019J\u000e\u0010\u001f\u001a\u00020\u00002\u0006\u0010 \u001a\u00020\u0019J\u0016\u0010!\u001a\u00020\u00002\u0006\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u0019J\u001f\u0010$\u001a\u00020\u00002\u0012\u0010$\u001a\n\u0012\u0006\b\u0001\u0012\u00020&0%\"\u00020&¢\u0006\u0002\u0010'J\u0014\u0010$\u001a\u00020\u00002\f\u0010$\u001a\b\u0012\u0004\u0012\u00020&0\rJ\u001f\u0010(\u001a\u00020\u00002\u0012\u0010(\u001a\n\u0012\u0006\b\u0001\u0012\u00020)0%\"\u00020)¢\u0006\u0002\u0010*J\u0014\u0010(\u001a\u00020\u00002\f\u0010+\u001a\b\u0012\u0004\u0012\u00020)0\rJ\u000e\u0010,\u001a\u00020\u00002\u0006\u0010,\u001a\u00020\nJ\u000e\u0010-\u001a\u00020\u00002\u0006\u0010-\u001a\u00020.R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lnl/dionsegijn/konfetti/core/PartyFactory;", "", "emitter", "Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;", "(Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;)V", "getEmitter", "()Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;", "party", "Lnl/dionsegijn/konfetti/core/Party;", "angle", "", "build", "colors", "", "delay", "fadeOutEnabled", "", "position", "x", "", "y", "minX", "minY", "maxX", "maxY", "", "Lnl/dionsegijn/konfetti/core/Position;", "rotation", "Lnl/dionsegijn/konfetti/core/Rotation;", "setDamping", "damping", "setSpeed", "speed", "setSpeedBetween", "minSpeed", "maxSpeed", "shapes", "", "Lnl/dionsegijn/konfetti/core/models/Shape;", "([Lnl/dionsegijn/konfetti/core/models/Shape;)Lnl/dionsegijn/konfetti/core/PartyFactory;", "sizes", "Lnl/dionsegijn/konfetti/core/models/Size;", "([Lnl/dionsegijn/konfetti/core/models/Size;)Lnl/dionsegijn/konfetti/core/PartyFactory;", "size", "spread", "timeToLive", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PartyFactory.kt */
public final class PartyFactory {
    private final EmitterConfig emitter;
    private Party party;

    public PartyFactory(EmitterConfig emitterConfig) {
        EmitterConfig emitterConfig2 = emitterConfig;
        Intrinsics.checkNotNullParameter(emitterConfig2, "emitter");
        this.emitter = emitterConfig2;
        Party party2 = r1;
        Party party3 = new Party(0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, emitterConfig, 8191, (DefaultConstructorMarker) null);
        this.party = party2;
    }

    public final EmitterConfig getEmitter() {
        return this.emitter;
    }

    public final PartyFactory angle(int i) {
        this.party = Party.copy$default(this.party, i, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16382, (Object) null);
        return this;
    }

    public final PartyFactory spread(int i) {
        this.party = Party.copy$default(this.party, 0, i, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16381, (Object) null);
        return this;
    }

    public final PartyFactory setSpeed(float f) {
        this.party = Party.copy$default(this.party, 0, 0, f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16379, (Object) null);
        return this;
    }

    public final PartyFactory setSpeedBetween(float f, float f2) {
        this.party = Party.copy$default(this.party, 0, 0, f, f2, 0.0f, (List) null, (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16371, (Object) null);
        return this;
    }

    public final PartyFactory setDamping(float f) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, f, (List) null, (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16367, (Object) null);
        return this;
    }

    public final PartyFactory position(Position position) {
        Intrinsics.checkNotNullParameter(position, Constants.PLAY_EXTRA_START_TIME);
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, position, 0, (Rotation) null, (EmitterConfig) null, 15359, (Object) null);
        return this;
    }

    public final PartyFactory position(float f, float f2) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, new Position.Absolute(f, f2), 0, (Rotation) null, (EmitterConfig) null, 15359, (Object) null);
        return this;
    }

    public final PartyFactory position(float f, float f2, float f3, float f4) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, new Position.Absolute(f, f2).between(new Position.Absolute(f3, f4)), 0, (Rotation) null, (EmitterConfig) null, 15359, (Object) null);
        return this;
    }

    public final PartyFactory position(double d, double d2) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, new Position.Relative(d, d2), 0, (Rotation) null, (EmitterConfig) null, 15359, (Object) null);
        return this;
    }

    public final PartyFactory position(double d, double d2, double d3, double d4) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, new Position.Relative(d, d2).between(new Position.Relative(d3, d4)), 0, (Rotation) null, (EmitterConfig) null, 15359, (Object) null);
        return this;
    }

    public final PartyFactory sizes(Size... sizeArr) {
        Intrinsics.checkNotNullParameter(sizeArr, "sizes");
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, ArraysKt.toList((T[]) sizeArr), (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16351, (Object) null);
        return this;
    }

    public final PartyFactory sizes(List<Size> list) {
        Intrinsics.checkNotNullParameter(list, ContentDisposition.Parameters.Size);
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, list, (List) null, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16351, (Object) null);
        return this;
    }

    public final PartyFactory colors(List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "colors");
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, list, (List) null, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16319, (Object) null);
        return this;
    }

    public final PartyFactory shapes(List<? extends Shape> list) {
        Intrinsics.checkNotNullParameter(list, "shapes");
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, list, 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16255, (Object) null);
        return this;
    }

    public final PartyFactory shapes(Shape... shapeArr) {
        Intrinsics.checkNotNullParameter(shapeArr, "shapes");
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, ArraysKt.toList((T[]) shapeArr), 0, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16255, (Object) null);
        return this;
    }

    public final PartyFactory timeToLive(long j) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, j, false, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 16127, (Object) null);
        return this;
    }

    public final PartyFactory fadeOutEnabled(boolean z) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, z, (Position) null, 0, (Rotation) null, (EmitterConfig) null, 15871, (Object) null);
        return this;
    }

    public final PartyFactory delay(int i) {
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, (Position) null, i, (Rotation) null, (EmitterConfig) null, 14335, (Object) null);
        return this;
    }

    public final PartyFactory rotation(Rotation rotation) {
        Intrinsics.checkNotNullParameter(rotation, Key.ROTATION);
        this.party = Party.copy$default(this.party, 0, 0, 0.0f, 0.0f, 0.0f, (List) null, (List) null, (List) null, 0, false, (Position) null, 0, rotation, (EmitterConfig) null, 12287, (Object) null);
        return this;
    }

    public final Party build() {
        return this.party;
    }
}
