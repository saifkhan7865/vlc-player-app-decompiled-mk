package org.videolan.vlc.widget.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import androidx.palette.graphics.Palette;
import com.google.android.material.color.DynamicColors;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.mediadb.models.Widget;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006\u001a\u001c\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006\u001a\u001c\u0010\n\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001c\u0010\u000f\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001c\u0010\u0010\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001c\u0010\u0011\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u001c\u0010\u0012\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u001a\u0012\u0010\u0013\u001a\u00020\u000b*\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\n\u0010\u0014\u001a\u00020\u0015*\u00020\u000b\u001a\u0012\u0010\u0016\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u0006¨\u0006\u0018"}, d2 = {"generateCircularProgressbar", "Landroid/graphics/Bitmap;", "Lorg/videolan/vlc/widget/utils/WidgetCacheEntry;", "context", "Landroid/content/Context;", "size", "", "progress", "stroke", "generatePillProgressbar", "getArtistColor", "", "Lorg/videolan/vlc/mediadb/models/Widget;", "palette", "Landroidx/palette/graphics/Palette;", "getBackgroundColor", "getBackgroundSecondaryColor", "getForegroundColor", "getProgressBackgroundColor", "getSeparatorColor", "isLight", "", "lightenOrDarkenColor", "value", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: WidgetUtils.kt */
public final class WidgetUtilsKt {
    public static final int getForegroundColor(Widget widget, Context context, Palette palette) {
        Intrinsics.checkNotNullParameter(widget, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        if (widget.getTheme() == 0 && DynamicColors.isDynamicColorAvailable()) {
            return ContextCompat.getColor(context, widget.getLightTheme() ? 17170493 : 17170491);
        } else if (widget.getTheme() == 2) {
            return widget.getForegroundColor();
        } else {
            if (palette == null) {
                return widget.getForegroundColor();
            }
            if (widget.getLightTheme()) {
                Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
                return darkVibrantSwatch != null ? darkVibrantSwatch.getRgb() : ViewCompat.MEASURED_STATE_MASK;
            }
            Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
            if (lightVibrantSwatch != null) {
                return lightVibrantSwatch.getRgb();
            }
            return -1;
        }
    }

    public static final int getBackgroundColor(Widget widget, Context context, Palette palette) {
        int i;
        Intrinsics.checkNotNullParameter(widget, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        if (widget.getTheme() == 0 && DynamicColors.isDynamicColorAvailable()) {
            i = ContextCompat.getColor(context, widget.getLightTheme() ? 17170476 : 17170484);
        } else if (widget.getTheme() == 2) {
            i = widget.getBackgroundColor();
        } else if (palette == null) {
            i = widget.getBackgroundColor();
        } else {
            if (widget.getLightTheme()) {
                Palette.Swatch lightMutedSwatch = palette.getLightMutedSwatch();
                if (lightMutedSwatch != null) {
                    i = lightMutedSwatch.getRgb();
                }
            } else {
                Palette.Swatch darkMutedSwatch = palette.getDarkMutedSwatch();
                if (darkMutedSwatch != null) {
                    i = darkMutedSwatch.getRgb();
                }
            }
            i = widget.getBackgroundColor();
        }
        return RangesKt.coerceAtMost(RangesKt.coerceAtLeast(widget.getOpacity(), 0), 100) != 100 ? ColorUtils.setAlphaComponent(i, (int) (((float) widget.getOpacity()) * 2.55f)) : i;
    }

    public static final int getBackgroundSecondaryColor(Widget widget, Context context, Palette palette) {
        int i;
        int i2;
        Palette.Swatch swatch;
        Intrinsics.checkNotNullParameter(widget, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        if (widget.getTheme() == 0 && DynamicColors.isDynamicColorAvailable()) {
            i = ContextCompat.getColor(context, widget.getLightTheme() ? 17170490 : 17170496);
        } else if (widget.getTheme() == 2) {
            i = lightenOrDarkenColor(widget.getBackgroundColor(), 0.1f);
        } else {
            if (widget.getLightTheme()) {
                if (palette == null || (swatch = palette.getLightMutedSwatch()) == null) {
                    i2 = R.color.grey300;
                    i = ContextCompat.getColor(context, i2);
                }
            } else if (palette == null || (swatch = palette.getDarkMutedSwatch()) == null) {
                i2 = R.color.grey800;
                i = ContextCompat.getColor(context, i2);
            }
            i = swatch.getRgb();
        }
        return RangesKt.coerceAtMost(RangesKt.coerceAtLeast(widget.getOpacity(), 0), 100) != 100 ? ColorUtils.setAlphaComponent(i, (int) (((float) widget.getOpacity()) * 2.55f)) : i;
    }

    public static final int getArtistColor(Widget widget, Context context, Palette palette) {
        Intrinsics.checkNotNullParameter(widget, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        if (widget.getTheme() == 0 && DynamicColors.isDynamicColorAvailable()) {
            return ContextCompat.getColor(context, widget.getLightTheme() ? 17170480 : 17170481);
        } else if (widget.getTheme() == 2) {
            return lightenOrDarkenColor(widget.getForegroundColor(), 0.1f);
        } else {
            return lightenOrDarkenColor(getForegroundColor(widget, context, palette), 0.1f);
        }
    }

    public static final int getProgressBackgroundColor(Widget widget, Context context, Palette palette) {
        int i;
        Intrinsics.checkNotNullParameter(widget, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        if (widget.getTheme() == 0 && DynamicColors.isDynamicColorAvailable()) {
            i = ContextCompat.getColor(context, widget.getLightTheme() ? 17170475 : 17170483);
        } else if (widget.getTheme() == 2) {
            i = lightenOrDarkenColor(widget.getBackgroundColor(), 0.15f);
        } else {
            i = lightenOrDarkenColor(getBackgroundColor(widget, context, palette), 0.15f);
        }
        return RangesKt.coerceAtMost(RangesKt.coerceAtLeast(widget.getOpacity(), 0), 100) != 100 ? ColorUtils.setAlphaComponent(i, (int) (((float) widget.getOpacity()) * 2.55f)) : i;
    }

    public static final int getSeparatorColor(Widget widget, Context context) {
        Intrinsics.checkNotNullParameter(widget, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        return ContextCompat.getColor(context, widget.getLightTheme() ? R.color.black_transparent_10 : R.color.white_transparent_10);
    }

    public static final int lightenOrDarkenColor(int i, float f) {
        float[] fArr = new float[3];
        ColorUtils.colorToHSL(i, fArr);
        float f2 = fArr[2];
        if (f2 < 0.5f) {
            fArr[2] = f2 + f;
        } else {
            fArr[2] = f2 - f;
        }
        fArr[2] = RangesKt.coerceAtLeast(0.0f, RangesKt.coerceAtMost(fArr[2], 1.0f));
        return ColorUtils.HSLToColor(fArr);
    }

    public static final boolean isLight(int i) {
        float[] fArr = new float[3];
        ColorUtils.colorToHSL(i, fArr);
        return fArr[2] > 0.5f;
    }

    public static /* synthetic */ Bitmap generateCircularProgressbar$default(WidgetCacheEntry widgetCacheEntry, Context context, float f, float f2, float f3, int i, Object obj) {
        if ((i & 8) != 0) {
            f3 = (float) KotlinExtensionsKt.getDp(6);
        }
        return generateCircularProgressbar(widgetCacheEntry, context, f, f2, f3);
    }

    public static final Bitmap generateCircularProgressbar(WidgetCacheEntry widgetCacheEntry, Context context, float f, float f2, float f3) {
        Intrinsics.checkNotNullParameter(widgetCacheEntry, "<this>");
        Intrinsics.checkNotNullParameter(context, "context");
        Paint paint = new Paint();
        float f4 = (float) 2;
        float f5 = f3 / f4;
        paint.setAntiAlias(true);
        paint.setStrokeWidth(f5 * f4);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(getProgressBackgroundColor(widgetCacheEntry.getWidget(), context, widgetCacheEntry.getPalette()));
        int i = (int) f;
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        float f6 = f / f4;
        canvas.drawCircle(f6, f6, f6 - f5, paint);
        paint.setColor(getForegroundColor(widgetCacheEntry.getWidget(), context, widgetCacheEntry.getPalette()));
        float f7 = f - f5;
        canvas.drawArc(new RectF(f5, f5, f7, f7), -90.0f, f2 * 360.0f, false, paint);
        return createBitmap;
    }

    public static final Bitmap generatePillProgressbar(WidgetCacheEntry widgetCacheEntry, Context context, float f) {
        Context context2 = context;
        Intrinsics.checkNotNullParameter(widgetCacheEntry, "<this>");
        Intrinsics.checkNotNullParameter(context2, "context");
        if (widgetCacheEntry.getWidget().getWidth() == 0) {
            return null;
        }
        Paint paint = new Paint();
        float dp = (float) KotlinExtensionsKt.getDp(2);
        paint.setAntiAlias(true);
        float f2 = (float) 2;
        paint.setStrokeWidth(dp * f2);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setColor(getProgressBackgroundColor(widgetCacheEntry.getWidget(), context2, widgetCacheEntry.getPalette()));
        float dp2 = (float) KotlinExtensionsKt.getDp(120);
        float dp3 = (float) KotlinExtensionsKt.getDp(62);
        float f3 = dp3 / f2;
        Bitmap createBitmap = Bitmap.createBitmap((int) dp2, (int) dp3, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        float f4 = f3 + dp;
        float f5 = (dp2 - f3) - dp;
        float f6 = f4;
        float f7 = f5;
        Canvas canvas2 = canvas;
        canvas.drawLine(f6, dp, f7, dp, paint);
        float f8 = dp3 - dp;
        Canvas canvas3 = canvas2;
        float f9 = f8;
        float f10 = f8;
        float f11 = f8;
        Paint paint2 = paint;
        canvas3.drawLine(f6, f9, f7, f10, paint2);
        canvas3.drawArc(new RectF(dp, dp, f11, f11), -90.0f, -180.0f, false, paint2);
        float f12 = dp2 - dp3;
        float f13 = dp2 - dp;
        float f14 = f13;
        Bitmap bitmap = createBitmap;
        float f15 = f12;
        canvas3.drawArc(new RectF(f12, dp, f13, f11), -90.0f, 180.0f, false, paint);
        paint.setColor(getForegroundColor(widgetCacheEntry.getWidget(), context2, widgetCacheEntry.getPalette()));
        double d = (double) dp3;
        Double.isNaN(d);
        double d2 = d * 3.141592653589793d;
        double d3 = (double) (f15 * f2);
        Double.isNaN(d3);
        double d4 = (double) f;
        Double.isNaN(d4);
        double d5 = (d3 + d2) * d4;
        float f16 = dp2 / f2;
        float f17 = f16 - f3;
        float coerceAtMost = RangesKt.coerceAtMost(f17, (float) d5);
        canvas2.drawLine(f16, dp, f16 + coerceAtMost, dp, paint);
        double d6 = (double) coerceAtMost;
        Double.isNaN(d6);
        double d7 = d5 - d6;
        if (d7 > 1.0d) {
            double d8 = (double) 2;
            Double.isNaN(d8);
            double d9 = d2 / d8;
            double coerceAtMost2 = RangesKt.coerceAtMost(d9, d7);
            double d10 = (double) 180.0f;
            Double.isNaN(d10);
            double d11 = d10 * (coerceAtMost2 / d9);
            canvas2.drawArc(new RectF(f15, dp, f14, f11), -90.0f, (float) d11, false, paint);
            d7 -= coerceAtMost2;
        }
        if (d7 > 1.0d) {
            float coerceAtMost3 = RangesKt.coerceAtMost(f15, (float) d7);
            canvas2.drawLine(f5, f11, f5 - coerceAtMost3, f11, paint);
            double d12 = (double) coerceAtMost3;
            Double.isNaN(d12);
            d7 -= d12;
        }
        if (d7 > 1.0d) {
            double d13 = (double) 2;
            Double.isNaN(d13);
            double d14 = d2 / d13;
            double coerceAtMost4 = RangesKt.coerceAtMost(d14, d7);
            double d15 = (double) 180.0f;
            Double.isNaN(d15);
            canvas2.drawArc(new RectF(dp, dp, f11, f11), 90.0f, (float) (d15 * (coerceAtMost4 / d14)), false, paint);
            d7 -= coerceAtMost4;
        }
        if (d7 > 1.0d) {
            canvas2.drawLine(f4, dp, f4 + RangesKt.coerceAtMost(f17, (float) d7), dp, paint);
        }
        return bitmap;
    }
}
