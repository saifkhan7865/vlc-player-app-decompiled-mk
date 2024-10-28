package org.videolan.vlc.gui.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010+\u001a\u00020,H\u0002J\u0010\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020/H\u0014J8\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020!2\u0006\u00102\u001a\u00020!2\u0006\u00103\u001a\u00020!2\u0006\u00104\u001a\u00020!2\u0006\u00105\u001a\u00020!2\u0006\u00106\u001a\u00020!H\u0002J\u000e\u00107\u001a\u00020,2\u0006\u0010)\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001a\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020!8BX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lorg/videolan/vlc/gui/view/PlayerProgress;", "Landroid/view/View;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "backgroundColor", "boostColor", "clip", "Landroid/graphics/Region;", "clippingPath", "Landroid/graphics/Path;", "firstClippingRegion", "isDouble", "", "()Z", "setDouble", "(Z)V", "paintBackground", "Landroid/graphics/Paint;", "paintProgress", "path", "getPath", "()Landroid/graphics/Path;", "setPath", "(Landroid/graphics/Path;)V", "progressColor", "progressPercent", "", "getProgressPercent", "()F", "progressWidth", "rectProgress", "Landroid/graphics/RectF;", "secondClippingRegion", "shadowColor", "value", "yOffset", "initialize", "", "onDraw", "canvas", "Landroid/graphics/Canvas;", "roundedRectanglePath", "left", "top", "right", "bottom", "rx", "ry", "setValue", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlayerProgress.kt */
public final class PlayerProgress extends View {
    private final int backgroundColor = ContextCompat.getColor(getContext(), R.color.white_transparent_50);
    private final int boostColor = ContextCompat.getColor(getContext(), R.color.orange700);
    private final Region clip = new Region();
    private final Path clippingPath = new Path();
    private final Region firstClippingRegion = new Region();
    private boolean isDouble;
    private final Paint paintBackground = new Paint();
    private final Paint paintProgress = new Paint();
    private Path path = new Path();
    private final int progressColor = ContextCompat.getColor(getContext(), R.color.white);
    private final int progressWidth = KotlinExtensionsKt.getDp(8);
    private final RectF rectProgress = new RectF(0.0f, 0.0f, 0.0f, 0.0f);
    private final Region secondClippingRegion = new Region();
    private final int shadowColor = ContextCompat.getColor(getContext(), R.color.blacktransparent);
    private int value = 50;
    private final int yOffset = KotlinExtensionsKt.getDp(4);

    public final boolean isDouble() {
        return this.isDouble;
    }

    public final void setDouble(boolean z) {
        this.isDouble = z;
    }

    private final float getProgressPercent() {
        int i;
        float f;
        if (this.isDouble) {
            f = (float) this.value;
            i = 200;
        } else {
            f = (float) this.value;
            i = 100;
        }
        return f / ((float) i);
    }

    public final Path getPath() {
        return this.path;
    }

    public final void setPath(Path path2) {
        Intrinsics.checkNotNullParameter(path2, "<set-?>");
        this.path = path2;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayerProgress(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayerProgress(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlayerProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initialize();
    }

    private final void initialize() {
        this.paintBackground.setColor(this.backgroundColor);
        this.paintBackground.setAntiAlias(true);
        this.paintProgress.setAntiAlias(true);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        float f = (float) 2;
        float width = (((float) getWidth()) - ((float) this.progressWidth)) / f;
        float width2 = ((float) getWidth()) - width;
        float f2 = (float) this.yOffset;
        float height = ((float) getHeight()) - ((float) this.yOffset);
        float f3 = (width2 - width) / f;
        roundedRectanglePath(width, f2, width2, height, f3, f3);
        this.paintBackground.setShadowLayer(6.0f, 0.0f, 0.0f, this.shadowColor);
        canvas.drawPath(this.path, this.paintBackground);
        this.paintBackground.clearShadowLayer();
        this.paintBackground.setColor(0);
        canvas.drawPath(this.path, this.paintBackground);
        this.paintBackground.setColor(this.backgroundColor);
        canvas.drawPath(this.path, this.paintBackground);
        float f4 = height - f2;
        float progressPercent = ((float) this.yOffset) + ((((float) 1) - getProgressPercent()) * f4);
        this.rectProgress.set(width, progressPercent, width2, height);
        RectF rectF = this.rectProgress;
        int save = canvas.save();
        canvas.clipRect(rectF);
        try {
            canvas.clipRect(this.rectProgress);
            canvas.restoreToCount(save);
            int i = (int) width;
            int i2 = (int) progressPercent;
            int i3 = (int) width2;
            this.clip.set(i, i2, i3, (int) height);
            this.firstClippingRegion.setPath(this.path, this.clip);
            this.clippingPath.moveTo(this.rectProgress.left, this.rectProgress.top);
            this.clippingPath.lineTo(this.rectProgress.right, this.rectProgress.top);
            this.clippingPath.lineTo(this.rectProgress.right, this.rectProgress.bottom);
            this.clippingPath.lineTo(this.rectProgress.left, this.rectProgress.bottom);
            this.clippingPath.close();
            this.secondClippingRegion.setPath(this.clippingPath, this.clip);
            this.firstClippingRegion.op(this.secondClippingRegion, Region.Op.INTERSECT);
            this.paintProgress.setColor(this.progressColor);
            canvas.drawPath(this.firstClippingRegion.getBoundaryPath(), this.paintProgress);
            if (this.isDouble && ((double) getProgressPercent()) > 0.5d) {
                float f5 = ((float) this.yOffset) + (f4 * 0.5f);
                this.rectProgress.set(width, progressPercent, width2, f5);
                this.clip.set(i, i2, i3, (int) f5);
                this.firstClippingRegion.setPath(this.path, this.clip);
                this.clippingPath.moveTo(this.rectProgress.left, this.rectProgress.top);
                this.clippingPath.lineTo(this.rectProgress.right, this.rectProgress.top);
                this.clippingPath.lineTo(this.rectProgress.right, this.rectProgress.bottom);
                this.clippingPath.lineTo(this.rectProgress.left, this.rectProgress.bottom);
                this.clippingPath.close();
                this.secondClippingRegion.setPath(this.clippingPath, this.clip);
                this.firstClippingRegion.op(this.secondClippingRegion, Region.Op.INTERSECT);
                this.paintProgress.setColor(this.boostColor);
                canvas.drawPath(this.firstClippingRegion.getBoundaryPath(), this.paintProgress);
            }
            super.onDraw(canvas);
        } catch (Throwable th) {
            canvas.restoreToCount(save);
            throw th;
        }
    }

    public final void setValue(int i) {
        this.value = i;
        invalidate();
    }

    private final void roundedRectanglePath(float f, float f2, float f3, float f4, float f5, float f6) {
        float f7 = (float) 2;
        float f8 = (f3 - f) - (f7 * f5);
        float f9 = (f4 - f2) - (f7 * f6);
        this.path.moveTo(f3, f2 + f6);
        float f10 = -f6;
        float f11 = -f5;
        this.path.rQuadTo(0.0f, f10, f11, f10);
        this.path.rLineTo(-f8, 0.0f);
        this.path.rQuadTo(f11, 0.0f, f11, f6);
        this.path.rLineTo(0.0f, f9);
        this.path.rQuadTo(0.0f, f6, f5, f6);
        this.path.rLineTo(f8, 0.0f);
        this.path.rQuadTo(f5, 0.0f, f5, f10);
        this.path.rLineTo(0.0f, -f9);
        this.path.close();
    }
}
