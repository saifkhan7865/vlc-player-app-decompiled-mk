package nl.dionsegijn.konfetti.core.models;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lnl/dionsegijn/konfetti/core/models/Size;", "", "sizeInDp", "", "mass", "", "massVariance", "(IFF)V", "getMass", "()F", "getMassVariance", "getSizeInDp", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "", "Companion", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Size.kt */
public final class Size {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Size LARGE = new Size(10, 6.0f, 0.0f, 4, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Size MEDIUM = new Size(8, 0.0f, 0.0f, 6, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Size SMALL = new Size(6, 4.0f, 0.0f, 4, (DefaultConstructorMarker) null);
    private final float mass;
    private final float massVariance;
    private final int sizeInDp;

    public static /* synthetic */ Size copy$default(Size size, int i, float f, float f2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = size.sizeInDp;
        }
        if ((i2 & 2) != 0) {
            f = size.mass;
        }
        if ((i2 & 4) != 0) {
            f2 = size.massVariance;
        }
        return size.copy(i, f, f2);
    }

    public final int component1() {
        return this.sizeInDp;
    }

    public final float component2() {
        return this.mass;
    }

    public final float component3() {
        return this.massVariance;
    }

    public final Size copy(int i, float f, float f2) {
        return new Size(i, f, f2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.sizeInDp == size.sizeInDp && Float.compare(this.mass, size.mass) == 0 && Float.compare(this.massVariance, size.massVariance) == 0;
    }

    public int hashCode() {
        return (((this.sizeInDp * 31) + Float.floatToIntBits(this.mass)) * 31) + Float.floatToIntBits(this.massVariance);
    }

    public String toString() {
        return "Size(sizeInDp=" + this.sizeInDp + ", mass=" + this.mass + ", massVariance=" + this.massVariance + ')';
    }

    public Size(int i, float f, float f2) {
        this.sizeInDp = i;
        this.mass = f;
        this.massVariance = f2;
        if (!(!(f == 0.0f))) {
            throw new IllegalArgumentException(("mass=" + f + " must be != 0").toString());
        }
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Size(int i, float f, float f2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? 5.0f : f, (i2 & 4) != 0 ? 0.2f : f2);
    }

    public final float getMass() {
        return this.mass;
    }

    public final float getMassVariance() {
        return this.massVariance;
    }

    public final int getSizeInDp() {
        return this.sizeInDp;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lnl/dionsegijn/konfetti/core/models/Size$Companion;", "", "()V", "LARGE", "Lnl/dionsegijn/konfetti/core/models/Size;", "getLARGE", "()Lnl/dionsegijn/konfetti/core/models/Size;", "MEDIUM", "getMEDIUM", "SMALL", "getSMALL", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Size.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Size getSMALL() {
            return Size.SMALL;
        }

        public final Size getMEDIUM() {
            return Size.MEDIUM;
        }

        public final Size getLARGE() {
            return Size.LARGE;
        }
    }
}
