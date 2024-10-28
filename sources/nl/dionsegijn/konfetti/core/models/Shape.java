package nl.dionsegijn.konfetti.core.models;

import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0004\u0002\u0003\u0004\u0005\u0001\u0004\u0006\u0007\b\t¨\u0006\n"}, d2 = {"Lnl/dionsegijn/konfetti/core/models/Shape;", "", "Circle", "DrawableShape", "Rectangle", "Square", "Lnl/dionsegijn/konfetti/core/models/Shape$Circle;", "Lnl/dionsegijn/konfetti/core/models/Shape$DrawableShape;", "Lnl/dionsegijn/konfetti/core/models/Shape$Rectangle;", "Lnl/dionsegijn/konfetti/core/models/Shape$Square;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Shape.kt */
public interface Shape {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lnl/dionsegijn/konfetti/core/models/Shape$Circle;", "Lnl/dionsegijn/konfetti/core/models/Shape;", "()V", "rect", "Landroid/graphics/RectF;", "getRect", "()Landroid/graphics/RectF;", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Shape.kt */
    public static final class Circle implements Shape {
        public static final Circle INSTANCE = new Circle();
        private static final RectF rect = new RectF();

        private Circle() {
        }

        public final RectF getRect() {
            return rect;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lnl/dionsegijn/konfetti/core/models/Shape$Square;", "Lnl/dionsegijn/konfetti/core/models/Shape;", "()V", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Shape.kt */
    public static final class Square implements Shape {
        public static final Square INSTANCE = new Square();

        private Square() {
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lnl/dionsegijn/konfetti/core/models/Shape$Rectangle;", "Lnl/dionsegijn/konfetti/core/models/Shape;", "heightRatio", "", "(F)V", "getHeightRatio", "()F", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Shape.kt */
    public static final class Rectangle implements Shape {
        private final float heightRatio;

        public Rectangle(float f) {
            this.heightRatio = f;
            if (0.0f > f || f > 1.0f) {
                throw new IllegalArgumentException("Failed requirement.".toString());
            }
        }

        public final float getHeightRatio() {
            return this.heightRatio;
        }
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J'\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\t¨\u0006\u001c"}, d2 = {"Lnl/dionsegijn/konfetti/core/models/Shape$DrawableShape;", "Lnl/dionsegijn/konfetti/core/models/Shape;", "drawable", "Landroid/graphics/drawable/Drawable;", "tint", "", "applyAlpha", "(Landroid/graphics/drawable/Drawable;ZZ)V", "getApplyAlpha", "()Z", "getDrawable", "()Landroid/graphics/drawable/Drawable;", "heightRatio", "", "getHeightRatio", "()F", "getTint", "component1", "component2", "component3", "copy", "equals", "other", "", "hashCode", "", "toString", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Shape.kt */
    public static final class DrawableShape implements Shape {
        private final boolean applyAlpha;
        private final Drawable drawable;
        private final float heightRatio;
        private final boolean tint;

        public static /* synthetic */ DrawableShape copy$default(DrawableShape drawableShape, Drawable drawable2, boolean z, boolean z2, int i, Object obj) {
            if ((i & 1) != 0) {
                drawable2 = drawableShape.drawable;
            }
            if ((i & 2) != 0) {
                z = drawableShape.tint;
            }
            if ((i & 4) != 0) {
                z2 = drawableShape.applyAlpha;
            }
            return drawableShape.copy(drawable2, z, z2);
        }

        public final Drawable component1() {
            return this.drawable;
        }

        public final boolean component2() {
            return this.tint;
        }

        public final boolean component3() {
            return this.applyAlpha;
        }

        public final DrawableShape copy(Drawable drawable2, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(drawable2, Constants.DRAWABLE);
            return new DrawableShape(drawable2, z, z2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DrawableShape)) {
                return false;
            }
            DrawableShape drawableShape = (DrawableShape) obj;
            return Intrinsics.areEqual((Object) this.drawable, (Object) drawableShape.drawable) && this.tint == drawableShape.tint && this.applyAlpha == drawableShape.applyAlpha;
        }

        public int hashCode() {
            int hashCode = this.drawable.hashCode() * 31;
            boolean z = this.tint;
            boolean z2 = true;
            if (z) {
                z = true;
            }
            int i = (hashCode + (z ? 1 : 0)) * 31;
            boolean z3 = this.applyAlpha;
            if (!z3) {
                z2 = z3;
            }
            return i + (z2 ? 1 : 0);
        }

        public String toString() {
            return "DrawableShape(drawable=" + this.drawable + ", tint=" + this.tint + ", applyAlpha=" + this.applyAlpha + ')';
        }

        public DrawableShape(Drawable drawable2, boolean z, boolean z2) {
            Intrinsics.checkNotNullParameter(drawable2, Constants.DRAWABLE);
            this.drawable = drawable2;
            this.tint = z;
            this.applyAlpha = z2;
            this.heightRatio = (drawable2.getIntrinsicHeight() == -1 && drawable2.getIntrinsicWidth() == -1) ? 1.0f : (drawable2.getIntrinsicHeight() == -1 || drawable2.getIntrinsicWidth() == -1) ? 0.0f : ((float) drawable2.getIntrinsicHeight()) / ((float) drawable2.getIntrinsicWidth());
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ DrawableShape(Drawable drawable2, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(drawable2, (i & 2) != 0 ? true : z, (i & 4) != 0 ? true : z2);
        }

        public final Drawable getDrawable() {
            return this.drawable;
        }

        public final boolean getTint() {
            return this.tint;
        }

        public final boolean getApplyAlpha() {
            return this.applyAlpha;
        }

        public final float getHeightRatio() {
            return this.heightRatio;
        }
    }
}
