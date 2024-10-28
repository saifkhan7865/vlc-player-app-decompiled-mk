package nl.dionsegijn.konfetti.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.core.models.Shape;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\bHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\fHÆ\u0003J\t\u0010#\u001a\u00020\bHÆ\u0003Jc\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\bHÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\bHÖ\u0001J\t\u0010)\u001a\u00020*HÖ\u0001R\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0013R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013¨\u0006+"}, d2 = {"Lnl/dionsegijn/konfetti/core/Particle;", "", "x", "", "y", "width", "height", "color", "", "rotation", "scaleX", "shape", "Lnl/dionsegijn/konfetti/core/models/Shape;", "alpha", "(FFFFIFFLnl/dionsegijn/konfetti/core/models/Shape;I)V", "getAlpha", "()I", "getColor", "getHeight", "()F", "getRotation", "getScaleX", "getShape", "()Lnl/dionsegijn/konfetti/core/models/Shape;", "getWidth", "getX", "getY", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toString", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Particle.kt */
public final class Particle {
    private final int alpha;
    private final int color;
    private final float height;
    private final float rotation;
    private final float scaleX;
    private final Shape shape;
    private final float width;
    private final float x;
    private final float y;

    public static /* synthetic */ Particle copy$default(Particle particle, float f, float f2, float f3, float f4, int i, float f5, float f6, Shape shape2, int i2, int i3, Object obj) {
        Particle particle2 = particle;
        int i4 = i3;
        return particle.copy((i4 & 1) != 0 ? particle2.x : f, (i4 & 2) != 0 ? particle2.y : f2, (i4 & 4) != 0 ? particle2.width : f3, (i4 & 8) != 0 ? particle2.height : f4, (i4 & 16) != 0 ? particle2.color : i, (i4 & 32) != 0 ? particle2.rotation : f5, (i4 & 64) != 0 ? particle2.scaleX : f6, (i4 & 128) != 0 ? particle2.shape : shape2, (i4 & 256) != 0 ? particle2.alpha : i2);
    }

    public final float component1() {
        return this.x;
    }

    public final float component2() {
        return this.y;
    }

    public final float component3() {
        return this.width;
    }

    public final float component4() {
        return this.height;
    }

    public final int component5() {
        return this.color;
    }

    public final float component6() {
        return this.rotation;
    }

    public final float component7() {
        return this.scaleX;
    }

    public final Shape component8() {
        return this.shape;
    }

    public final int component9() {
        return this.alpha;
    }

    public final Particle copy(float f, float f2, float f3, float f4, int i, float f5, float f6, Shape shape2, int i2) {
        Shape shape3 = shape2;
        Intrinsics.checkNotNullParameter(shape3, "shape");
        return new Particle(f, f2, f3, f4, i, f5, f6, shape3, i2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Particle)) {
            return false;
        }
        Particle particle = (Particle) obj;
        return Float.compare(this.x, particle.x) == 0 && Float.compare(this.y, particle.y) == 0 && Float.compare(this.width, particle.width) == 0 && Float.compare(this.height, particle.height) == 0 && this.color == particle.color && Float.compare(this.rotation, particle.rotation) == 0 && Float.compare(this.scaleX, particle.scaleX) == 0 && Intrinsics.areEqual((Object) this.shape, (Object) particle.shape) && this.alpha == particle.alpha;
    }

    public int hashCode() {
        return (((((((((((((((Float.floatToIntBits(this.x) * 31) + Float.floatToIntBits(this.y)) * 31) + Float.floatToIntBits(this.width)) * 31) + Float.floatToIntBits(this.height)) * 31) + this.color) * 31) + Float.floatToIntBits(this.rotation)) * 31) + Float.floatToIntBits(this.scaleX)) * 31) + this.shape.hashCode()) * 31) + this.alpha;
    }

    public String toString() {
        return "Particle(x=" + this.x + ", y=" + this.y + ", width=" + this.width + ", height=" + this.height + ", color=" + this.color + ", rotation=" + this.rotation + ", scaleX=" + this.scaleX + ", shape=" + this.shape + ", alpha=" + this.alpha + ')';
    }

    public Particle(float f, float f2, float f3, float f4, int i, float f5, float f6, Shape shape2, int i2) {
        Intrinsics.checkNotNullParameter(shape2, "shape");
        this.x = f;
        this.y = f2;
        this.width = f3;
        this.height = f4;
        this.color = i;
        this.rotation = f5;
        this.scaleX = f6;
        this.shape = shape2;
        this.alpha = i2;
    }

    public final float getX() {
        return this.x;
    }

    public final float getY() {
        return this.y;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getHeight() {
        return this.height;
    }

    public final int getColor() {
        return this.color;
    }

    public final float getRotation() {
        return this.rotation;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final int getAlpha() {
        return this.alpha;
    }
}
