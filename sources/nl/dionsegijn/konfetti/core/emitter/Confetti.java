package nl.dionsegijn.konfetti.core.emitter;

import android.graphics.Rect;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import nl.dionsegijn.konfetti.core.models.Shape;
import nl.dionsegijn.konfetti.core.models.Vector;
import org.fusesource.jansi.AnsiConsole;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b2\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001By\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0007\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0007\u0012\u0006\u0010\u0014\u001a\u00020\u0007¢\u0006\u0002\u0010\u0015J\u000e\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0003J\u0006\u0010C\u001a\u00020\u0007J\u0006\u0010D\u001a\u00020\u000eJ\u0016\u0010E\u001a\u00020A2\u0006\u0010F\u001a\u00020\u00072\u0006\u0010G\u001a\u00020HJ\u0018\u0010I\u001a\u00020A2\u0006\u0010F\u001a\u00020\u00072\u0006\u0010G\u001a\u00020HH\u0002J\u0010\u0010J\u001a\u00020A2\u0006\u0010F\u001a\u00020\u0007H\u0002R\u000e\u0010\u000f\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0018\"\u0004\b\u001d\u0010\u001aR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001e\u0010#\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u000e@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010%R\u000e\u0010'\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b1\u0010\u001fR\u001a\u00102\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u001f\"\u0004\b4\u0010!R\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u001fR\u0011\u0010\u0012\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001fR\u000e\u00107\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001a\u00108\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u001f\"\u0004\b:\u0010!R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u001a\u0010\u0010\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010.\"\u0004\b>\u00100R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b?\u0010\u001f¨\u0006K"}, d2 = {"Lnl/dionsegijn/konfetti/core/emitter/Confetti;", "", "location", "Lnl/dionsegijn/konfetti/core/models/Vector;", "color", "", "width", "", "mass", "shape", "Lnl/dionsegijn/konfetti/core/models/Shape;", "lifespan", "", "fadeOut", "", "acceleration", "velocity", "damping", "rotationSpeed3D", "rotationSpeed2D", "pixelDensity", "(Lnl/dionsegijn/konfetti/core/models/Vector;IFFLnl/dionsegijn/konfetti/core/models/Shape;JZLnl/dionsegijn/konfetti/core/models/Vector;Lnl/dionsegijn/konfetti/core/models/Vector;FFFF)V", "alpha", "getAlpha", "()I", "setAlpha", "(I)V", "alphaColor", "getAlphaColor", "setAlphaColor", "getDamping", "()F", "setDamping", "(F)V", "<set-?>", "drawParticle", "getDrawParticle", "()Z", "getFadeOut", "frameRate", "gravity", "getLifespan", "()J", "setLifespan", "(J)V", "getLocation", "()Lnl/dionsegijn/konfetti/core/models/Vector;", "setLocation", "(Lnl/dionsegijn/konfetti/core/models/Vector;)V", "getPixelDensity", "rotation", "getRotation", "setRotation", "getRotationSpeed2D", "getRotationSpeed3D", "rotationWidth", "scaleX", "getScaleX", "setScaleX", "getShape", "()Lnl/dionsegijn/konfetti/core/models/Shape;", "getVelocity", "setVelocity", "getWidth", "applyForce", "", "force", "getSize", "isDead", "render", "deltaTime", "drawArea", "Landroid/graphics/Rect;", "update", "updateAlpha", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Confetti.kt */
public final class Confetti {
    private Vector acceleration;
    private int alpha;
    private int alphaColor;
    private final int color;
    private float damping;
    private boolean drawParticle;
    private final boolean fadeOut;
    private float frameRate;
    private Vector gravity;
    private long lifespan;
    private Vector location;
    private final float mass;
    private final float pixelDensity;
    private float rotation;
    private final float rotationSpeed2D;
    private final float rotationSpeed3D;
    private float rotationWidth;
    private float scaleX;
    private final Shape shape;
    private Vector velocity;
    private final float width;

    public Confetti(Vector vector, int i, float f, float f2, Shape shape2, long j, boolean z, Vector vector2, Vector vector3, float f3, float f4, float f5, float f6) {
        Intrinsics.checkNotNullParameter(vector, "location");
        Intrinsics.checkNotNullParameter(shape2, "shape");
        Intrinsics.checkNotNullParameter(vector2, "acceleration");
        Intrinsics.checkNotNullParameter(vector3, "velocity");
        this.location = vector;
        this.color = i;
        this.width = f;
        this.mass = f2;
        this.shape = shape2;
        this.lifespan = j;
        this.fadeOut = z;
        this.acceleration = vector2;
        this.velocity = vector3;
        this.damping = f3;
        this.rotationSpeed3D = f4;
        this.rotationSpeed2D = f5;
        this.pixelDensity = f6;
        this.rotationWidth = f;
        this.frameRate = 60.0f;
        this.gravity = new Vector(0.0f, 0.02f);
        this.alpha = 255;
        this.drawParticle = true;
    }

    public final Vector getLocation() {
        return this.location;
    }

    public final void setLocation(Vector vector) {
        Intrinsics.checkNotNullParameter(vector, "<set-?>");
        this.location = vector;
    }

    public final float getWidth() {
        return this.width;
    }

    public final Shape getShape() {
        return this.shape;
    }

    public final long getLifespan() {
        return this.lifespan;
    }

    public final void setLifespan(long j) {
        this.lifespan = j;
    }

    public final boolean getFadeOut() {
        return this.fadeOut;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ Confetti(nl.dionsegijn.konfetti.core.models.Vector r19, int r20, float r21, float r22, nl.dionsegijn.konfetti.core.models.Shape r23, long r24, boolean r26, nl.dionsegijn.konfetti.core.models.Vector r27, nl.dionsegijn.konfetti.core.models.Vector r28, float r29, float r30, float r31, float r32, int r33, kotlin.jvm.internal.DefaultConstructorMarker r34) {
        /*
            r18 = this;
            r0 = r33
            r1 = r0 & 32
            if (r1 == 0) goto L_0x000a
            r1 = -1
            r9 = r1
            goto L_0x000c
        L_0x000a:
            r9 = r24
        L_0x000c:
            r1 = r0 & 64
            if (r1 == 0) goto L_0x0013
            r1 = 1
            r11 = 1
            goto L_0x0015
        L_0x0013:
            r11 = r26
        L_0x0015:
            r1 = r0 & 128(0x80, float:1.794E-43)
            r2 = 0
            if (r1 == 0) goto L_0x0021
            nl.dionsegijn.konfetti.core.models.Vector r1 = new nl.dionsegijn.konfetti.core.models.Vector
            r1.<init>(r2, r2)
            r12 = r1
            goto L_0x0023
        L_0x0021:
            r12 = r27
        L_0x0023:
            r1 = r0 & 256(0x100, float:3.59E-43)
            if (r1 == 0) goto L_0x0030
            nl.dionsegijn.konfetti.core.models.Vector r1 = new nl.dionsegijn.konfetti.core.models.Vector
            r3 = 3
            r4 = 0
            r1.<init>(r2, r2, r3, r4)
            r13 = r1
            goto L_0x0032
        L_0x0030:
            r13 = r28
        L_0x0032:
            r1 = r0 & 1024(0x400, float:1.435E-42)
            r2 = 1065353216(0x3f800000, float:1.0)
            if (r1 == 0) goto L_0x003b
            r15 = 1065353216(0x3f800000, float:1.0)
            goto L_0x003d
        L_0x003b:
            r15 = r30
        L_0x003d:
            r0 = r0 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L_0x0044
            r16 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0046
        L_0x0044:
            r16 = r31
        L_0x0046:
            r3 = r18
            r4 = r19
            r5 = r20
            r6 = r21
            r7 = r22
            r8 = r23
            r14 = r29
            r17 = r32
            r3.<init>(r4, r5, r6, r7, r8, r9, r11, r12, r13, r14, r15, r16, r17)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: nl.dionsegijn.konfetti.core.emitter.Confetti.<init>(nl.dionsegijn.konfetti.core.models.Vector, int, float, float, nl.dionsegijn.konfetti.core.models.Shape, long, boolean, nl.dionsegijn.konfetti.core.models.Vector, nl.dionsegijn.konfetti.core.models.Vector, float, float, float, float, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Vector getVelocity() {
        return this.velocity;
    }

    public final void setVelocity(Vector vector) {
        Intrinsics.checkNotNullParameter(vector, "<set-?>");
        this.velocity = vector;
    }

    public final float getDamping() {
        return this.damping;
    }

    public final void setDamping(float f) {
        this.damping = f;
    }

    public final float getRotationSpeed3D() {
        return this.rotationSpeed3D;
    }

    public final float getRotationSpeed2D() {
        return this.rotationSpeed2D;
    }

    public final float getPixelDensity() {
        return this.pixelDensity;
    }

    public final float getRotation() {
        return this.rotation;
    }

    public final void setRotation(float f) {
        this.rotation = f;
    }

    public final int getAlpha() {
        return this.alpha;
    }

    public final void setAlpha(int i) {
        this.alpha = i;
    }

    public final float getScaleX() {
        return this.scaleX;
    }

    public final void setScaleX(float f) {
        this.scaleX = f;
    }

    public final int getAlphaColor() {
        return this.alphaColor;
    }

    public final void setAlphaColor(int i) {
        this.alphaColor = i;
    }

    public final boolean getDrawParticle() {
        return this.drawParticle;
    }

    public final float getSize() {
        return this.width;
    }

    public final boolean isDead() {
        return this.alpha <= 0;
    }

    public final void applyForce(Vector vector) {
        Intrinsics.checkNotNullParameter(vector, AnsiConsole.JANSI_MODE_FORCE);
        this.acceleration.addScaled(vector, 1.0f / this.mass);
    }

    public final void render(float f, Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "drawArea");
        applyForce(this.gravity);
        update(f, rect);
    }

    private final void update(float f, Rect rect) {
        this.frameRate = f > 0.0f ? 1.0f / f : 60.0f;
        if (this.location.getY() > ((float) rect.height())) {
            this.alpha = 0;
            return;
        }
        this.velocity.add(this.acceleration);
        this.velocity.mult(this.damping);
        this.location.addScaled(this.velocity, this.frameRate * f * this.pixelDensity);
        long j = this.lifespan - ((long) (((float) 1000) * f));
        this.lifespan = j;
        if (j <= 0) {
            updateAlpha(f);
        }
        float f2 = this.rotation + (this.rotationSpeed2D * f * this.frameRate);
        this.rotation = f2;
        if (f2 >= 360.0f) {
            this.rotation = 0.0f;
        }
        float abs = this.rotationWidth - ((Math.abs(this.rotationSpeed3D) * f) * this.frameRate);
        this.rotationWidth = abs;
        if (abs < 0.0f) {
            this.rotationWidth = this.width;
        }
        this.scaleX = Math.abs((this.rotationWidth / this.width) - 0.5f) * ((float) 2);
        this.alphaColor = (this.alpha << 24) | (this.color & 16777215);
        this.drawParticle = rect.contains((int) this.location.getX(), (int) this.location.getY());
    }

    private final void updateAlpha(float f) {
        int i = 0;
        if (this.fadeOut) {
            i = RangesKt.coerceAtLeast(this.alpha - ((int) ((((float) 5) * f) * this.frameRate)), 0);
        }
        this.alpha = i;
    }
}
