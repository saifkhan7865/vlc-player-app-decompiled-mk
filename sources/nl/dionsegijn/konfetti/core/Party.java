package nl.dionsegijn.konfetti.core;

import androidx.constraintlayout.motion.widget.Key;
import io.ktor.http.ContentDisposition;
import java.util.List;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.core.emitter.EmitterConfig;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Size;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b*\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B¡\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\b\b\u0002\u0010\b\u001a\u00020\u0006\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\n\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0010\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0002\u0010\u001aJ\t\u00101\u001a\u00020\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0012HÆ\u0003J\t\u00103\u001a\u00020\u0014HÆ\u0003J\t\u00104\u001a\u00020\u0003HÆ\u0003J\t\u00105\u001a\u00020\u0017HÆ\u0003J\t\u00106\u001a\u00020\u0019HÆ\u0003J\t\u00107\u001a\u00020\u0003HÆ\u0003J\t\u00108\u001a\u00020\u0006HÆ\u0003J\t\u00109\u001a\u00020\u0006HÆ\u0003J\t\u0010:\u001a\u00020\u0006HÆ\u0003J\u000f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u000b0\nHÆ\u0003J\u000f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00030\nHÆ\u0003J\u000f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000e0\nHÆ\u0003J\t\u0010>\u001a\u00020\u0010HÆ\u0003J§\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u00062\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u000e\b\u0002\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00032\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0019HÆ\u0001J\u0013\u0010@\u001a\u00020\u00122\b\u0010A\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010B\u001a\u00020\u0003HÖ\u0001J\t\u0010C\u001a\u00020DHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\n¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0015\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001cR\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b&\u0010 R\u0011\u0010\u0013\u001a\u00020\u0014¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\n¢\u0006\b\n\u0000\u001a\u0004\b+\u0010\u001eR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b-\u0010 R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001cR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b/\u00100¨\u0006E"}, d2 = {"Lnl/dionsegijn/konfetti/core/Party;", "", "angle", "", "spread", "speed", "", "maxSpeed", "damping", "size", "", "Lnl/dionsegijn/konfetti/core/models/Size;", "colors", "shapes", "Lnl/dionsegijn/konfetti/core/models/Shape;", "timeToLive", "", "fadeOutEnabled", "", "position", "Lnl/dionsegijn/konfetti/core/Position;", "delay", "rotation", "Lnl/dionsegijn/konfetti/core/Rotation;", "emitter", "Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;", "(IIFFFLjava/util/List;Ljava/util/List;Ljava/util/List;JZLnl/dionsegijn/konfetti/core/Position;ILnl/dionsegijn/konfetti/core/Rotation;Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;)V", "getAngle", "()I", "getColors", "()Ljava/util/List;", "getDamping", "()F", "getDelay", "getEmitter", "()Lnl/dionsegijn/konfetti/core/emitter/EmitterConfig;", "getFadeOutEnabled", "()Z", "getMaxSpeed", "getPosition", "()Lnl/dionsegijn/konfetti/core/Position;", "getRotation", "()Lnl/dionsegijn/konfetti/core/Rotation;", "getShapes", "getSize", "getSpeed", "getSpread", "getTimeToLive", "()J", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Party.kt */
public final class Party {
    private final int angle;
    private final List<Integer> colors;
    private final float damping;
    private final int delay;
    private final EmitterConfig emitter;
    private final boolean fadeOutEnabled;
    private final float maxSpeed;
    private final Position position;
    private final Rotation rotation;
    private final List<Shape> shapes;
    private final List<Size> size;
    private final float speed;
    private final int spread;
    private final long timeToLive;

    public static /* synthetic */ Party copy$default(Party party, int i, int i2, float f, float f2, float f3, List list, List list2, List list3, long j, boolean z, Position position2, int i3, Rotation rotation2, EmitterConfig emitterConfig, int i4, Object obj) {
        Party party2 = party;
        int i5 = i4;
        return party.copy((i5 & 1) != 0 ? party2.angle : i, (i5 & 2) != 0 ? party2.spread : i2, (i5 & 4) != 0 ? party2.speed : f, (i5 & 8) != 0 ? party2.maxSpeed : f2, (i5 & 16) != 0 ? party2.damping : f3, (i5 & 32) != 0 ? party2.size : list, (i5 & 64) != 0 ? party2.colors : list2, (i5 & 128) != 0 ? party2.shapes : list3, (i5 & 256) != 0 ? party2.timeToLive : j, (i5 & 512) != 0 ? party2.fadeOutEnabled : z, (i5 & 1024) != 0 ? party2.position : position2, (i5 & 2048) != 0 ? party2.delay : i3, (i5 & 4096) != 0 ? party2.rotation : rotation2, (i5 & 8192) != 0 ? party2.emitter : emitterConfig);
    }

    public final int component1() {
        return this.angle;
    }

    public final boolean component10() {
        return this.fadeOutEnabled;
    }

    public final Position component11() {
        return this.position;
    }

    public final int component12() {
        return this.delay;
    }

    public final Rotation component13() {
        return this.rotation;
    }

    public final EmitterConfig component14() {
        return this.emitter;
    }

    public final int component2() {
        return this.spread;
    }

    public final float component3() {
        return this.speed;
    }

    public final float component4() {
        return this.maxSpeed;
    }

    public final float component5() {
        return this.damping;
    }

    public final List<Size> component6() {
        return this.size;
    }

    public final List<Integer> component7() {
        return this.colors;
    }

    public final List<Shape> component8() {
        return this.shapes;
    }

    public final long component9() {
        return this.timeToLive;
    }

    public final Party copy(int i, int i2, float f, float f2, float f3, List<Size> list, List<Integer> list2, List<? extends Shape> list3, long j, boolean z, Position position2, int i3, Rotation rotation2, EmitterConfig emitterConfig) {
        List<Size> list4 = list;
        Intrinsics.checkNotNullParameter(list4, ContentDisposition.Parameters.Size);
        List<Integer> list5 = list2;
        Intrinsics.checkNotNullParameter(list5, "colors");
        List<? extends Shape> list6 = list3;
        Intrinsics.checkNotNullParameter(list6, "shapes");
        Position position3 = position2;
        Intrinsics.checkNotNullParameter(position3, Constants.PLAY_EXTRA_START_TIME);
        Rotation rotation3 = rotation2;
        Intrinsics.checkNotNullParameter(rotation3, Key.ROTATION);
        Intrinsics.checkNotNullParameter(emitterConfig, "emitter");
        return new Party(i, i2, f, f2, f3, list4, list5, list6, j, z, position3, i3, rotation3, emitterConfig);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Party)) {
            return false;
        }
        Party party = (Party) obj;
        return this.angle == party.angle && this.spread == party.spread && Float.compare(this.speed, party.speed) == 0 && Float.compare(this.maxSpeed, party.maxSpeed) == 0 && Float.compare(this.damping, party.damping) == 0 && Intrinsics.areEqual((Object) this.size, (Object) party.size) && Intrinsics.areEqual((Object) this.colors, (Object) party.colors) && Intrinsics.areEqual((Object) this.shapes, (Object) party.shapes) && this.timeToLive == party.timeToLive && this.fadeOutEnabled == party.fadeOutEnabled && Intrinsics.areEqual((Object) this.position, (Object) party.position) && this.delay == party.delay && Intrinsics.areEqual((Object) this.rotation, (Object) party.rotation) && Intrinsics.areEqual((Object) this.emitter, (Object) party.emitter);
    }

    public int hashCode() {
        int floatToIntBits = ((((((((((((((((this.angle * 31) + this.spread) * 31) + Float.floatToIntBits(this.speed)) * 31) + Float.floatToIntBits(this.maxSpeed)) * 31) + Float.floatToIntBits(this.damping)) * 31) + this.size.hashCode()) * 31) + this.colors.hashCode()) * 31) + this.shapes.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.timeToLive)) * 31;
        boolean z = this.fadeOutEnabled;
        if (z) {
            z = true;
        }
        return ((((((((floatToIntBits + (z ? 1 : 0)) * 31) + this.position.hashCode()) * 31) + this.delay) * 31) + this.rotation.hashCode()) * 31) + this.emitter.hashCode();
    }

    public String toString() {
        return "Party(angle=" + this.angle + ", spread=" + this.spread + ", speed=" + this.speed + ", maxSpeed=" + this.maxSpeed + ", damping=" + this.damping + ", size=" + this.size + ", colors=" + this.colors + ", shapes=" + this.shapes + ", timeToLive=" + this.timeToLive + ", fadeOutEnabled=" + this.fadeOutEnabled + ", position=" + this.position + ", delay=" + this.delay + ", rotation=" + this.rotation + ", emitter=" + this.emitter + ')';
    }

    public Party(int i, int i2, float f, float f2, float f3, List<Size> list, List<Integer> list2, List<? extends Shape> list3, long j, boolean z, Position position2, int i3, Rotation rotation2, EmitterConfig emitterConfig) {
        List<? extends Shape> list4 = list3;
        Position position3 = position2;
        Rotation rotation3 = rotation2;
        EmitterConfig emitterConfig2 = emitterConfig;
        Intrinsics.checkNotNullParameter(list, ContentDisposition.Parameters.Size);
        Intrinsics.checkNotNullParameter(list2, "colors");
        Intrinsics.checkNotNullParameter(list4, "shapes");
        Intrinsics.checkNotNullParameter(position3, Constants.PLAY_EXTRA_START_TIME);
        Intrinsics.checkNotNullParameter(rotation3, Key.ROTATION);
        Intrinsics.checkNotNullParameter(emitterConfig2, "emitter");
        this.angle = i;
        this.spread = i2;
        this.speed = f;
        this.maxSpeed = f2;
        this.damping = f3;
        this.size = list;
        this.colors = list2;
        this.shapes = list4;
        this.timeToLive = j;
        this.fadeOutEnabled = z;
        this.position = position3;
        this.delay = i3;
        this.rotation = rotation3;
        this.emitter = emitterConfig2;
    }

    public final int getAngle() {
        return this.angle;
    }

    public final int getSpread() {
        return this.spread;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final float getMaxSpeed() {
        return this.maxSpeed;
    }

    public final float getDamping() {
        return this.damping;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Party(int r22, int r23, float r24, float r25, float r26, java.util.List r27, java.util.List r28, java.util.List r29, long r30, boolean r32, nl.dionsegijn.konfetti.core.Position r33, int r34, nl.dionsegijn.konfetti.core.Rotation r35, nl.dionsegijn.konfetti.core.emitter.EmitterConfig r36, int r37, kotlin.jvm.internal.DefaultConstructorMarker r38) {
        /*
            r21 = this;
            r0 = r37
            r1 = r0 & 1
            r2 = 0
            if (r1 == 0) goto L_0x0009
            r4 = 0
            goto L_0x000b
        L_0x0009:
            r4 = r22
        L_0x000b:
            r1 = r0 & 2
            if (r1 == 0) goto L_0x0014
            r1 = 360(0x168, float:5.04E-43)
            r5 = 360(0x168, float:5.04E-43)
            goto L_0x0016
        L_0x0014:
            r5 = r23
        L_0x0016:
            r1 = r0 & 4
            if (r1 == 0) goto L_0x001f
            r1 = 1106247680(0x41f00000, float:30.0)
            r6 = 1106247680(0x41f00000, float:30.0)
            goto L_0x0021
        L_0x001f:
            r6 = r24
        L_0x0021:
            r1 = r0 & 8
            if (r1 == 0) goto L_0x0028
            r1 = 0
            r7 = 0
            goto L_0x002a
        L_0x0028:
            r7 = r25
        L_0x002a:
            r1 = r0 & 16
            if (r1 == 0) goto L_0x0035
            r1 = 1063675494(0x3f666666, float:0.9)
            r8 = 1063675494(0x3f666666, float:0.9)
            goto L_0x0037
        L_0x0035:
            r8 = r26
        L_0x0037:
            r1 = r0 & 32
            r3 = 3
            r9 = 2
            r10 = 1
            if (r1 == 0) goto L_0x005d
            nl.dionsegijn.konfetti.core.models.Size[] r1 = new nl.dionsegijn.konfetti.core.models.Size[r3]
            nl.dionsegijn.konfetti.core.models.Size$Companion r11 = nl.dionsegijn.konfetti.core.models.Size.Companion
            nl.dionsegijn.konfetti.core.models.Size r11 = r11.getSMALL()
            r1[r2] = r11
            nl.dionsegijn.konfetti.core.models.Size$Companion r11 = nl.dionsegijn.konfetti.core.models.Size.Companion
            nl.dionsegijn.konfetti.core.models.Size r11 = r11.getMEDIUM()
            r1[r10] = r11
            nl.dionsegijn.konfetti.core.models.Size$Companion r11 = nl.dionsegijn.konfetti.core.models.Size.Companion
            nl.dionsegijn.konfetti.core.models.Size r11 = r11.getLARGE()
            r1[r9] = r11
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            goto L_0x005f
        L_0x005d:
            r1 = r27
        L_0x005f:
            r11 = r0 & 64
            if (r11 == 0) goto L_0x0090
            r11 = 16572810(0xfce18a, float:2.3223453E-38)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r11)
            r12 = 16740973(0xff726d, float:2.34591E-38)
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            r13 = 16003181(0xf4306d, float:2.2425233E-38)
            java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            r14 = 11832815(0xb48def, float:1.6581305E-38)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r14)
            r15 = 4
            java.lang.Integer[] r15 = new java.lang.Integer[r15]
            r15[r2] = r11
            r15[r10] = r12
            r15[r9] = r13
            r15[r3] = r14
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r15)
            r11 = r3
            goto L_0x0092
        L_0x0090:
            r11 = r28
        L_0x0092:
            r3 = r0 & 128(0x80, float:1.794E-43)
            if (r3 == 0) goto L_0x00aa
            nl.dionsegijn.konfetti.core.models.Shape[] r3 = new nl.dionsegijn.konfetti.core.models.Shape[r9]
            nl.dionsegijn.konfetti.core.models.Shape$Square r9 = nl.dionsegijn.konfetti.core.models.Shape.Square.INSTANCE
            nl.dionsegijn.konfetti.core.models.Shape r9 = (nl.dionsegijn.konfetti.core.models.Shape) r9
            r3[r2] = r9
            nl.dionsegijn.konfetti.core.models.Shape$Circle r9 = nl.dionsegijn.konfetti.core.models.Shape.Circle.INSTANCE
            nl.dionsegijn.konfetti.core.models.Shape r9 = (nl.dionsegijn.konfetti.core.models.Shape) r9
            r3[r10] = r9
            java.util.List r3 = kotlin.collections.CollectionsKt.listOf(r3)
            r12 = r3
            goto L_0x00ac
        L_0x00aa:
            r12 = r29
        L_0x00ac:
            r3 = r0 & 256(0x100, float:3.59E-43)
            if (r3 == 0) goto L_0x00b3
            r13 = 2000(0x7d0, double:9.88E-321)
            goto L_0x00b5
        L_0x00b3:
            r13 = r30
        L_0x00b5:
            r3 = r0 & 512(0x200, float:7.175E-43)
            if (r3 == 0) goto L_0x00bb
            r15 = 1
            goto L_0x00bd
        L_0x00bb:
            r15 = r32
        L_0x00bd:
            r3 = r0 & 1024(0x400, float:1.435E-42)
            if (r3 == 0) goto L_0x00cd
            nl.dionsegijn.konfetti.core.Position$Relative r3 = new nl.dionsegijn.konfetti.core.Position$Relative
            r9 = 4602678819172646912(0x3fe0000000000000, double:0.5)
            r3.<init>(r9, r9)
            nl.dionsegijn.konfetti.core.Position r3 = (nl.dionsegijn.konfetti.core.Position) r3
            r16 = r3
            goto L_0x00cf
        L_0x00cd:
            r16 = r33
        L_0x00cf:
            r3 = r0 & 2048(0x800, float:2.87E-42)
            if (r3 == 0) goto L_0x00d4
            goto L_0x00d6
        L_0x00d4:
            r2 = r34
        L_0x00d6:
            r0 = r0 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x00fe
            nl.dionsegijn.konfetti.core.Rotation r0 = new nl.dionsegijn.konfetti.core.Rotation
            r3 = 31
            r9 = 0
            r10 = 0
            r17 = 0
            r18 = 0
            r19 = 0
            r20 = 0
            r22 = r0
            r23 = r10
            r24 = r17
            r25 = r18
            r26 = r19
            r27 = r20
            r28 = r3
            r29 = r9
            r22.<init>(r23, r24, r25, r26, r27, r28, r29)
            r17 = r0
            goto L_0x0100
        L_0x00fe:
            r17 = r35
        L_0x0100:
            r3 = r21
            r9 = r1
            r10 = r11
            r11 = r12
            r12 = r13
            r14 = r15
            r15 = r16
            r16 = r2
            r18 = r36
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r14, r15, r16, r17, r18)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: nl.dionsegijn.konfetti.core.Party.<init>(int, int, float, float, float, java.util.List, java.util.List, java.util.List, long, boolean, nl.dionsegijn.konfetti.core.Position, int, nl.dionsegijn.konfetti.core.Rotation, nl.dionsegijn.konfetti.core.emitter.EmitterConfig, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final List<Size> getSize() {
        return this.size;
    }

    public final List<Integer> getColors() {
        return this.colors;
    }

    public final List<Shape> getShapes() {
        return this.shapes;
    }

    public final long getTimeToLive() {
        return this.timeToLive;
    }

    public final boolean getFadeOutEnabled() {
        return this.fadeOutEnabled;
    }

    public final Position getPosition() {
        return this.position;
    }

    public final int getDelay() {
        return this.delay;
    }

    public final Rotation getRotation() {
        return this.rotation;
    }

    public final EmitterConfig getEmitter() {
        return this.emitter;
    }
}
