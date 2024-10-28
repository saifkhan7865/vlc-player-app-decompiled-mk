package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005\u001a\u001e\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00012\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005\u001a*\u0010\n\u001a\u0004\u0018\u00010\u0001*\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u001a9\u0010\r\u001a\u00020\u0001*\u00020\u000b2\b\b\u0001\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0010\u001a$\u0010\u0011\u001a\u00020\u0012*\u00020\u000b2\b\b\u0001\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0005¨\u0006\u0015"}, d2 = {"bitmapFromView", "Landroid/graphics/Bitmap;", "view", "Landroid/view/View;", "width", "", "height", "centerCrop", "dstWidth", "dstHeight", "getBitmapFromDrawable", "Landroid/content/Context;", "drawableId", "getColoredBitmapFromColor", "drawableRes", "color", "(Landroid/content/Context;IILjava/lang/Integer;Ljava/lang/Integer;)Landroid/graphics/Bitmap;", "getColoredStateListDawable", "Landroid/graphics/drawable/StateListDrawable;", "drawable", "pressedColor", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: BitmapUtil.kt */
public final class BitmapUtilKt {
    public static final StateListDrawable getColoredStateListDawable(Context context, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        StateListDrawable stateListDrawable = new StateListDrawable();
        Bitmap coloredBitmapFromColor$default = getColoredBitmapFromColor$default(context, i, i3, (Integer) null, (Integer) null, 12, (Object) null);
        Resources resources = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        stateListDrawable.addState(new int[]{16842908}, new BitmapDrawable(resources, coloredBitmapFromColor$default));
        Context context2 = context;
        int i4 = i;
        Bitmap coloredBitmapFromColor$default2 = getColoredBitmapFromColor$default(context2, i4, i3, (Integer) null, (Integer) null, 12, (Object) null);
        Resources resources2 = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources2, "getResources(...)");
        stateListDrawable.addState(new int[]{16842919}, new BitmapDrawable(resources2, coloredBitmapFromColor$default2));
        Bitmap coloredBitmapFromColor$default3 = getColoredBitmapFromColor$default(context2, i4, i2, (Integer) null, (Integer) null, 12, (Object) null);
        Resources resources3 = context.getResources();
        Intrinsics.checkNotNullExpressionValue(resources3, "getResources(...)");
        stateListDrawable.addState(new int[]{16842910}, new BitmapDrawable(resources3, coloredBitmapFromColor$default3));
        return stateListDrawable;
    }

    public static /* synthetic */ Bitmap getColoredBitmapFromColor$default(Context context, int i, int i2, Integer num, Integer num2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        if ((i3 & 8) != 0) {
            num2 = null;
        }
        return getColoredBitmapFromColor(context, i, i2, num, num2);
    }

    public static final Bitmap getColoredBitmapFromColor(Context context, int i, int i2, Integer num, Integer num2) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        BitmapUtil bitmapUtil = BitmapUtil.INSTANCE;
        Bitmap vectorToBitmap = BitmapUtil.INSTANCE.vectorToBitmap(context, i, num, num2);
        Intrinsics.checkNotNull(vectorToBitmap);
        return bitmapUtil.tintImage(vectorToBitmap, i2);
    }

    public static final Bitmap centerCrop(Bitmap bitmap, int i, int i2) {
        if (bitmap == null) {
            return null;
        }
        return BitmapUtil.INSTANCE.centerCrop(bitmap, i, i2);
    }

    public static /* synthetic */ Bitmap getBitmapFromDrawable$default(Context context, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i2 = -1;
        }
        if ((i4 & 4) != 0) {
            i3 = -1;
        }
        return getBitmapFromDrawable(context, i, i2, i3);
    }

    public static final Bitmap getBitmapFromDrawable(Context context, int i, int i2, int i3) {
        Drawable drawable;
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            drawable = ContextCompat.getDrawable(context, i);
            if (drawable == null) {
                return null;
            }
        } catch (Resources.NotFoundException unused) {
            VectorDrawableCompat create = VectorDrawableCompat.create(context.getResources(), i, context.getTheme());
            Intrinsics.checkNotNull(create);
            Intrinsics.checkNotNull(create);
            drawable = create;
        }
        if (Build.VERSION.SDK_INT < 21) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            Intrinsics.checkNotNullExpressionValue(drawable, "mutate(...)");
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (!(drawable instanceof VectorDrawableCompat) && (!AndroidUtil.isLolliPopOrLater || !AppUtils$$ExternalSyntheticApiModelOutline0.m$2((Object) drawable))) {
            return BitmapFactory.decodeResource(context.getResources(), i);
        }
        if (i2 <= 0 || i3 <= 0) {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        }
        Intrinsics.checkNotNull(bitmap);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public static final Bitmap bitmapFromView(View view, int i, int i2) {
        Intrinsics.checkNotNullParameter(view, "view");
        try {
            Bitmap createBitmap = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            view.measure(View.MeasureSpec.makeMeasureSpec(createBitmap.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(createBitmap.getHeight(), 1073741824));
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
            view.draw(new Canvas(createBitmap));
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.e("BitmapUtil", e.getMessage(), e);
            Bitmap createBitmap2 = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap2, "createBitmap(...)");
            createBitmap2.setPixel(0, 0, 0);
            return createBitmap2;
        }
    }
}
