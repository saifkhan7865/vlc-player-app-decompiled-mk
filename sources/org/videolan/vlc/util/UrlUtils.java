package org.videolan.vlc.util;

import android.graphics.Bitmap;
import androidx.core.view.ViewCompat;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/util/UrlUtils;", "", "()V", "generateQRCode", "Landroid/graphics/Bitmap;", "url", "", "size", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: UrlUtils.kt */
public final class UrlUtils {
    public static final UrlUtils INSTANCE = new UrlUtils();

    private UrlUtils() {
    }

    public static /* synthetic */ Bitmap generateQRCode$default(UrlUtils urlUtils, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 512;
        }
        return urlUtils.generateQRCode(str, i);
    }

    public final Bitmap generateQRCode(String str, int i) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        BitMatrix encode = new QRCodeWriter().encode(str, BarcodeFormat.QR_CODE, i, i);
        Bitmap createBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.RGB_565);
        Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                createBitmap.setPixel(i2, i3, encode.get(i2, i3) ? ViewCompat.MEASURED_STATE_MASK : -1);
            }
        }
        return createBitmap;
    }
}
