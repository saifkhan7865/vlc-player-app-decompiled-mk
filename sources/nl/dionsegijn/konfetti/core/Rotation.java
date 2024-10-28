package nl.dionsegijn.konfetti.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB7\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J;\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\r¨\u0006\u001e"}, d2 = {"Lnl/dionsegijn/konfetti/core/Rotation;", "", "enabled", "", "speed", "", "variance", "multiplier2D", "multiplier3D", "(ZFFFF)V", "getEnabled", "()Z", "getMultiplier2D", "()F", "getMultiplier3D", "getSpeed", "getVariance", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "", "toString", "", "Companion", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Party.kt */
public final class Rotation {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final boolean enabled;
    private final float multiplier2D;
    private final float multiplier3D;
    private final float speed;
    private final float variance;

    public Rotation() {
        this(false, 0.0f, 0.0f, 0.0f, 0.0f, 31, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ Rotation copy$default(Rotation rotation, boolean z, float f, float f2, float f3, float f4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = rotation.enabled;
        }
        if ((i & 2) != 0) {
            f = rotation.speed;
        }
        float f5 = f;
        if ((i & 4) != 0) {
            f2 = rotation.variance;
        }
        float f6 = f2;
        if ((i & 8) != 0) {
            f3 = rotation.multiplier2D;
        }
        float f7 = f3;
        if ((i & 16) != 0) {
            f4 = rotation.multiplier3D;
        }
        return rotation.copy(z, f5, f6, f7, f4);
    }

    public final boolean component1() {
        return this.enabled;
    }

    public final float component2() {
        return this.speed;
    }

    public final float component3() {
        return this.variance;
    }

    public final float component4() {
        return this.multiplier2D;
    }

    public final float component5() {
        return this.multiplier3D;
    }

    public final Rotation copy(boolean z, float f, float f2, float f3, float f4) {
        return new Rotation(z, f, f2, f3, f4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rotation)) {
            return false;
        }
        Rotation rotation = (Rotation) obj;
        return this.enabled == rotation.enabled && Float.compare(this.speed, rotation.speed) == 0 && Float.compare(this.variance, rotation.variance) == 0 && Float.compare(this.multiplier2D, rotation.multiplier2D) == 0 && Float.compare(this.multiplier3D, rotation.multiplier3D) == 0;
    }

    public int hashCode() {
        boolean z = this.enabled;
        if (z) {
            z = true;
        }
        return ((((((((z ? 1 : 0) * true) + Float.floatToIntBits(this.speed)) * 31) + Float.floatToIntBits(this.variance)) * 31) + Float.floatToIntBits(this.multiplier2D)) * 31) + Float.floatToIntBits(this.multiplier3D);
    }

    public String toString() {
        return "Rotation(enabled=" + this.enabled + ", speed=" + this.speed + ", variance=" + this.variance + ", multiplier2D=" + this.multiplier2D + ", multiplier3D=" + this.multiplier3D + ')';
    }

    public Rotation(boolean z, float f, float f2, float f3, float f4) {
        this.enabled = z;
        this.speed = f;
        this.variance = f2;
        this.multiplier2D = f3;
        this.multiplier3D = f4;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Rotation(boolean r4, float r5, float r6, float r7, float r8, int r9, kotlin.jvm.internal.DefaultConstructorMarker r10) {
        /*
            r3 = this;
            r10 = r9 & 1
            if (r10 == 0) goto L_0x0005
            r4 = 1
        L_0x0005:
            r10 = r9 & 2
            if (r10 == 0) goto L_0x000e
            r5 = 1065353216(0x3f800000, float:1.0)
            r10 = 1065353216(0x3f800000, float:1.0)
            goto L_0x000f
        L_0x000e:
            r10 = r5
        L_0x000f:
            r5 = r9 & 4
            if (r5 == 0) goto L_0x0018
            r6 = 1056964608(0x3f000000, float:0.5)
            r0 = 1056964608(0x3f000000, float:0.5)
            goto L_0x0019
        L_0x0018:
            r0 = r6
        L_0x0019:
            r5 = r9 & 8
            if (r5 == 0) goto L_0x0022
            r7 = 1090519040(0x41000000, float:8.0)
            r1 = 1090519040(0x41000000, float:8.0)
            goto L_0x0023
        L_0x0022:
            r1 = r7
        L_0x0023:
            r5 = r9 & 16
            if (r5 == 0) goto L_0x002c
            r8 = 1069547520(0x3fc00000, float:1.5)
            r2 = 1069547520(0x3fc00000, float:1.5)
            goto L_0x002d
        L_0x002c:
            r2 = r8
        L_0x002d:
            r5 = r3
            r6 = r4
            r7 = r10
            r8 = r0
            r9 = r1
            r10 = r2
            r5.<init>(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: nl.dionsegijn.konfetti.core.Rotation.<init>(boolean, float, float, float, float, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final boolean getEnabled() {
        return this.enabled;
    }

    public final float getSpeed() {
        return this.speed;
    }

    public final float getVariance() {
        return this.variance;
    }

    public final float getMultiplier2D() {
        return this.multiplier2D;
    }

    public final float getMultiplier3D() {
        return this.multiplier3D;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lnl/dionsegijn/konfetti/core/Rotation$Companion;", "", "()V", "disabled", "Lnl/dionsegijn/konfetti/core/Rotation;", "enabled", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Party.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Rotation enabled() {
            return new Rotation(true, 0.0f, 0.0f, 0.0f, 0.0f, 30, (DefaultConstructorMarker) null);
        }

        public final Rotation disabled() {
            return new Rotation(false, 0.0f, 0.0f, 0.0f, 0.0f, 30, (DefaultConstructorMarker) null);
        }
    }
}
