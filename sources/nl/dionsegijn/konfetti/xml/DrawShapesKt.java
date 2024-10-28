package nl.dionsegijn.konfetti.xml;

import android.graphics.BlendModeColorFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import nl.dionsegijn.konfetti.core.models.Shape;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"draw", "", "Lnl/dionsegijn/konfetti/core/models/Shape;", "canvas", "Landroid/graphics/Canvas;", "paint", "Landroid/graphics/Paint;", "size", "", "xml_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DrawShapes.kt */
public final class DrawShapesKt {
    public static final void draw(Shape shape, Canvas canvas, Paint paint, float f) {
        Intrinsics.checkNotNullParameter(shape, "<this>");
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        Intrinsics.checkNotNullParameter(paint, "paint");
        if (Intrinsics.areEqual((Object) shape, (Object) Shape.Square.INSTANCE)) {
            canvas.drawRect(0.0f, 0.0f, f, f, paint);
        } else if (Intrinsics.areEqual((Object) shape, (Object) Shape.Circle.INSTANCE)) {
            Shape.Circle.INSTANCE.getRect().set(0.0f, 0.0f, f, f);
            canvas.drawOval(Shape.Circle.INSTANCE.getRect(), paint);
        } else if (shape instanceof Shape.Rectangle) {
            float heightRatio = ((Shape.Rectangle) shape).getHeightRatio() * f;
            float f2 = (f - heightRatio) / 2.0f;
            canvas.drawRect(0.0f, f2, f, f2 + heightRatio, paint);
        } else if (shape instanceof Shape.DrawableShape) {
            Shape.DrawableShape drawableShape = (Shape.DrawableShape) shape;
            if (drawableShape.getTint()) {
                if (Build.VERSION.SDK_INT >= 29) {
                    drawableShape.getDrawable().setColorFilter(new BlendModeColorFilter(paint.getColor(), AppUtils$$ExternalSyntheticApiModelOutline0.m()));
                } else {
                    drawableShape.getDrawable().setColorFilter(paint.getColor(), PorterDuff.Mode.SRC_IN);
                }
            } else if (drawableShape.getApplyAlpha()) {
                drawableShape.getDrawable().setAlpha(paint.getAlpha());
            }
            int heightRatio2 = (int) (drawableShape.getHeightRatio() * f);
            int i = (int) ((f - ((float) heightRatio2)) / 2.0f);
            drawableShape.getDrawable().setBounds(0, i, (int) f, heightRatio2 + i);
            drawableShape.getDrawable().draw(canvas);
        }
    }
}
