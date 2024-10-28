package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.BitmapCache;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.Strings;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tJ\u0010\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0006J0\u0010\u000e\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0012\b\u0002\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0013J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J.\u0010\u0017\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010\u001a\u001a\u00020\t2\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tJ\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\b\u001a\u00020\tJ\u0014\u0010\u001e\u001a\u0004\u0018\u00010\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010\u0004H\u0002J\u000e\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0006JR\u0010\"\u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u00112\b\b\u0002\u0010&\u001a\u00020\u00112\b\b\u0002\u0010'\u001a\u00020\u00112\b\b\u0002\u0010(\u001a\u00020\u0011J\u0016\u0010)\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\u0004J\u0016\u0010+\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\tJ5\u0010-\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\b\b\u0001\u0010.\u001a\u00020\t2\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010/J\f\u00100\u001a\u000201*\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lorg/videolan/vlc/gui/helpers/BitmapUtil;", "", "()V", "TAG", "", "centerCrop", "Landroid/graphics/Bitmap;", "srcBmp", "width", "", "height", "convertBitmapToByteArray", "", "bitmap", "encodeImage", "bmp", "enableTracing", "", "timestampProvider", "Lkotlin/Function0;", "fetchPicture", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "getBitmapFromVectorDrawable", "context", "Landroid/content/Context;", "drawableId", "getPicture", "getPictureFromCache", "makeTransparentBackground", "readCoverBitmap", "path", "roundBitmap", "bm", "roundedRectangleBitmap", "radius", "", "topLeft", "topRight", "bottomLeft", "bottomRight", "saveOnDisk", "destPath", "tintImage", "color", "vectorToBitmap", "resVector", "(Landroid/content/Context;ILjava/lang/Integer;Ljava/lang/Integer;)Landroid/graphics/Bitmap;", "getMaximalSquareBounds", "Landroid/graphics/Rect;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BitmapUtil.kt */
public final class BitmapUtil {
    public static final BitmapUtil INSTANCE = new BitmapUtil();
    public static final String TAG = "VLC/UiTools/BitmapUtil";

    private BitmapUtil() {
    }

    public final Bitmap getPictureFromCache(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        Bitmap picture = mediaWrapper.getPicture();
        return picture == null ? BitmapCache.INSTANCE.getBitmapFromMemCache(mediaWrapper.getLocation()) : picture;
    }

    private final Bitmap fetchPicture(MediaWrapper mediaWrapper) {
        Bitmap readCoverBitmap = readCoverBitmap(mediaWrapper.getArtworkURL());
        if (readCoverBitmap != null) {
            BitmapCache.INSTANCE.addBitmapToMemCache(mediaWrapper.getLocation(), readCoverBitmap);
        }
        return readCoverBitmap;
    }

    public final Bitmap getPicture(MediaWrapper mediaWrapper) {
        Intrinsics.checkNotNullParameter(mediaWrapper, "media");
        Bitmap pictureFromCache = getPictureFromCache(mediaWrapper);
        return pictureFromCache == null ? fetchPicture(mediaWrapper) : pictureFromCache;
    }

    private final Bitmap readCoverBitmap(String str) {
        if (str == null) {
            return null;
        }
        Resources resources = AppContextProvider.INSTANCE.getAppContext().getResources();
        String decode = Uri.decode(str);
        Intrinsics.checkNotNullExpressionValue(decode, "decode(...)");
        String removeFileScheme = Strings.removeFileScheme(decode);
        BitmapFactory.Options options = new BitmapFactory.Options();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.grid_card_thumb_height);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.grid_card_thumb_width);
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(removeFileScheme, options);
        if (options.outWidth <= 0 || options.outHeight <= 0) {
            return null;
        }
        if (options.outWidth > dimensionPixelSize2) {
            options.outWidth = dimensionPixelSize2;
            options.outHeight = dimensionPixelSize;
        }
        options.inJustDecodeBounds = false;
        try {
            return BitmapFactory.decodeFile(removeFileScheme, options);
        } catch (OutOfMemoryError unused) {
            return null;
        }
    }

    public final byte[] convertBitmapToByteArray(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public static /* synthetic */ byte[] encodeImage$default(BitmapUtil bitmapUtil, Bitmap bitmap, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        return bitmapUtil.encodeImage(bitmap, z, function0);
    }

    public final byte[] encodeImage(Bitmap bitmap, boolean z, Function0<String> function0) {
        String str;
        if (bitmap == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long currentTimeMillis = z ? System.currentTimeMillis() : 0;
        bitmap.compress(Bitmap.CompressFormat.WEBP, 100, byteArrayOutputStream);
        if (z) {
            long currentTimeMillis2 = System.currentTimeMillis();
            DecimalFormat decimalFormat = new DecimalFormat("###.#%");
            double d = (double) 1;
            double size = (double) byteArrayOutputStream.size();
            double byteCount = (double) bitmap.getByteCount();
            Double.isNaN(size);
            Double.isNaN(byteCount);
            Double.isNaN(d);
            String format = decimalFormat.format(d - (size / byteCount));
            StringBuilder sb = new StringBuilder("encImage() Time: ");
            if (function0 == null || (str = function0.invoke()) == null) {
                str = "";
            }
            sb.append(str);
            sb.append(" Duration: ");
            sb.append(currentTimeMillis2 - currentTimeMillis);
            sb.append("ms Comp. Ratio: ");
            sb.append(format);
            sb.append(" Thread: ");
            sb.append(Thread.currentThread().getName());
            Log.d("VLC/ArtworkProvider", sb.toString());
        }
        return byteArrayOutputStream.toByteArray();
    }

    public final Bitmap centerCrop(Bitmap bitmap, int i, int i2) {
        Intrinsics.checkNotNullParameter(bitmap, "srcBmp");
        int width = bitmap.getWidth() - i;
        int height = bitmap.getHeight() - i2;
        if (width <= 0 && height <= 0) {
            return bitmap;
        }
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap, width / 2, height / 2, i, i2);
            Intrinsics.checkNotNull(createBitmap);
            return createBitmap;
        } catch (Exception unused) {
            return bitmap;
        }
    }

    public static /* synthetic */ Bitmap getBitmapFromVectorDrawable$default(BitmapUtil bitmapUtil, Context context, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = -1;
        }
        if ((i4 & 8) != 0) {
            i3 = -1;
        }
        return bitmapUtil.getBitmapFromVectorDrawable(context, i, i2, i3);
    }

    public final Bitmap getBitmapFromVectorDrawable(Context context, int i, int i2, int i3) {
        Bitmap bitmap;
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable drawable = ContextCompat.getDrawable(context, i);
        if (drawable == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT < 21) {
            drawable = DrawableCompat.wrap(drawable).mutate();
            Intrinsics.checkNotNullExpressionValue(drawable, "mutate(...)");
        }
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        if (!(drawable instanceof VectorDrawableCompat) && !AppUtils$$ExternalSyntheticApiModelOutline0.m$2((Object) drawable)) {
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

    public static /* synthetic */ Bitmap vectorToBitmap$default(BitmapUtil bitmapUtil, Context context, int i, Integer num, Integer num2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            num = null;
        }
        if ((i2 & 8) != 0) {
            num2 = null;
        }
        return bitmapUtil.vectorToBitmap(context, i, num, num2);
    }

    public final Bitmap vectorToBitmap(Context context, int i, Integer num, Integer num2) {
        int i2;
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable drawable = AppCompatResources.getDrawable(context, i);
        if (drawable != null) {
            int intValue = num != null ? num.intValue() : drawable.getIntrinsicWidth();
            if (num2 != null) {
                i2 = num2.intValue();
            } else {
                i2 = drawable.getIntrinsicHeight();
            }
            Bitmap createBitmap = Bitmap.createBitmap(intValue, i2, Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        }
        throw new IllegalStateException("Invalid drawable");
    }

    public final Bitmap tintImage(Bitmap bitmap, int i) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN));
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }

    public static /* synthetic */ Bitmap makeTransparentBackground$default(BitmapUtil bitmapUtil, Context context, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = KotlinExtensionsKt.getDp(48);
        }
        return bitmapUtil.makeTransparentBackground(context, i);
    }

    public final Bitmap makeTransparentBackground(Context context, int i) {
        Context context2 = context;
        int i2 = i;
        Intrinsics.checkNotNullParameter(context2, "context");
        int color = ContextCompat.getColor(context2, R.color.grey500);
        int color2 = ContextCompat.getColor(context2, R.color.grey700);
        Paint paint = new Paint();
        Bitmap createBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        float f = ((float) i2) / 6.0f;
        int i3 = 0;
        for (int i4 = 0; i4 < 6; i4++) {
            int i5 = i3;
            int i6 = 0;
            while (i6 < 6) {
                paint.setColor(i5 % 2 == 0 ? color2 : color);
                int i7 = i6 + 1;
                canvas.drawRect(((float) i4) * f, ((float) i6) * f, ((float) (i4 + 1)) * f, ((float) i7) * f, paint);
                i5++;
                i6 = i7;
            }
            i3 = i5 + 1;
        }
        return createBitmap;
    }

    public final Bitmap roundBitmap(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "bm");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width >= height) {
            width = height;
        }
        Bitmap createBitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-16777215);
        Rect rect = new Rect(0, 0, width, width);
        RectF rectF = new RectF(rect);
        canvas.drawARGB(0, 0, 0, 0);
        float f = (float) 2;
        canvas.drawCircle(rectF.left + (rectF.width() / f), rectF.top + (rectF.height() / f), (float) (width / 2), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, getMaximalSquareBounds(bitmap), rect, paint);
        return createBitmap;
    }

    public static /* synthetic */ Bitmap roundedRectangleBitmap$default(BitmapUtil bitmapUtil, Bitmap bitmap, int i, int i2, float f, boolean z, boolean z2, boolean z3, boolean z4, int i3, Object obj) {
        int i4 = i3;
        return bitmapUtil.roundedRectangleBitmap(bitmap, i, (i4 & 4) != 0 ? -1 : i2, (i4 & 8) != 0 ? (float) KotlinExtensionsKt.getDp(12) : f, (i4 & 16) != 0 ? true : z, (i4 & 32) != 0 ? true : z2, (i4 & 64) != 0 ? true : z3, (i4 & 128) != 0 ? true : z4);
    }

    public final Bitmap roundedRectangleBitmap(Bitmap bitmap, int i, int i2, float f, boolean z, boolean z2, boolean z3, boolean z4) {
        Rect rect;
        Bitmap bitmap2 = bitmap;
        int i3 = i;
        int i4 = i2;
        float f2 = f;
        Intrinsics.checkNotNullParameter(bitmap, "bm");
        int i5 = i4 == -1 ? i3 : i4;
        Bitmap createBitmap = Bitmap.createBitmap(i3, i5, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-16777215);
        Rect rect2 = new Rect(0, 0, i3, i5);
        RectF rectF = new RectF(rect2);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f2, f2, paint);
        if (!z) {
            canvas.drawRect(new RectF(0.0f, 0.0f, f2, f2), paint);
        }
        if (!z2) {
            float f3 = (float) i3;
            canvas.drawRect(new RectF(f3 - f2, 0.0f, f3, f2), paint);
        }
        if (!z3) {
            float f4 = (float) i5;
            canvas.drawRect(new RectF(0.0f, f4 - f2, f2, f4), paint);
        }
        if (!z4) {
            float f5 = (float) i3;
            float f6 = (float) i5;
            canvas.drawRect(new RectF(f5 - f2, f6 - f2, f5, f6), paint);
        }
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        if (i4 == -1 || i3 == i4) {
            rect = getMaximalSquareBounds(bitmap);
        } else {
            rect = null;
        }
        canvas.drawBitmap(bitmap, rect, rect2, paint);
        return createBitmap;
    }

    private final Rect getMaximalSquareBounds(Bitmap bitmap) {
        if (bitmap.getWidth() > bitmap.getHeight()) {
            return new Rect((bitmap.getWidth() - bitmap.getHeight()) / 2, 0, bitmap.getHeight() + ((bitmap.getWidth() - bitmap.getHeight()) / 2), bitmap.getHeight());
        }
        if (bitmap.getWidth() < bitmap.getHeight()) {
            return new Rect(0, (bitmap.getHeight() - bitmap.getWidth()) / 2, bitmap.getWidth(), bitmap.getWidth() + ((bitmap.getHeight() - bitmap.getWidth()) / 2));
        }
        return new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0053, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r9, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0057, code lost:
        throw r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x005a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r10, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005e, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean saveOnDisk(android.graphics.Bitmap r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.String r0 = "bitmap"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "destPath"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.io.File r0 = new java.io.File
            r0.<init>(r10)
            java.io.File r10 = r0.getParentFile()
            r1 = 0
            java.lang.String r2 = "VLC/UiTools/BitmapUtil"
            if (r10 == 0) goto L_0x0068
            boolean r10 = r10.canWrite()
            r3 = 1
            if (r10 != r3) goto L_0x0068
            java.io.ByteArrayOutputStream r10 = new java.io.ByteArrayOutputStream     // Catch:{ IOException -> 0x005f }
            r10.<init>()     // Catch:{ IOException -> 0x005f }
            java.io.Closeable r10 = (java.io.Closeable) r10     // Catch:{ IOException -> 0x005f }
            r4 = r10
            java.io.ByteArrayOutputStream r4 = (java.io.ByteArrayOutputStream) r4     // Catch:{ all -> 0x0058 }
            android.graphics.Bitmap$CompressFormat r5 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ all -> 0x0058 }
            r6 = r4
            java.io.OutputStream r6 = (java.io.OutputStream) r6     // Catch:{ all -> 0x0058 }
            r7 = 100
            r9.compress(r5, r7, r6)     // Catch:{ all -> 0x0058 }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ all -> 0x0058 }
            r9.<init>(r0)     // Catch:{ all -> 0x0058 }
            java.io.Closeable r9 = (java.io.Closeable) r9     // Catch:{ all -> 0x0058 }
            r0 = r9
            java.io.FileOutputStream r0 = (java.io.FileOutputStream) r0     // Catch:{ all -> 0x0051 }
            byte[] r4 = r4.toByteArray()     // Catch:{ all -> 0x0051 }
            r0.write(r4)     // Catch:{ all -> 0x0051 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0051 }
            r0 = 0
            kotlin.io.CloseableKt.closeFinally(r9, r0)     // Catch:{ all -> 0x0058 }
            kotlin.Unit r9 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0058 }
            kotlin.io.CloseableKt.closeFinally(r10, r0)     // Catch:{ IOException -> 0x005f }
            r1 = 1
            goto L_0x0079
        L_0x0051:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0053 }
        L_0x0053:
            r3 = move-exception
            kotlin.io.CloseableKt.closeFinally(r9, r0)     // Catch:{ all -> 0x0058 }
            throw r3     // Catch:{ all -> 0x0058 }
        L_0x0058:
            r9 = move-exception
            throw r9     // Catch:{ all -> 0x005a }
        L_0x005a:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r10, r9)     // Catch:{ IOException -> 0x005f }
            throw r0     // Catch:{ IOException -> 0x005f }
        L_0x005f:
            r9 = move-exception
            java.lang.String r10 = "Could not save image to disk"
            java.lang.Throwable r9 = (java.lang.Throwable) r9
            android.util.Log.e(r2, r10, r9)
            goto L_0x0079
        L_0x0068:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "File path not writable: "
            r9.<init>(r10)
            r9.append(r0)
            java.lang.String r9 = r9.toString()
            android.util.Log.e(r2, r9)
        L_0x0079:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.BitmapUtil.saveOnDisk(android.graphics.Bitmap, java.lang.String):boolean");
    }
}
