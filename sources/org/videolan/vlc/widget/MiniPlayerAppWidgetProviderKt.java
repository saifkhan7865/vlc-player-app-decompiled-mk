package org.videolan.vlc.widget;

import android.graphics.Bitmap;
import android.os.Build;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"byteSize", "", "Landroid/graphics/Bitmap;", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: MiniPlayerAppWidgetProvider.kt */
public final class MiniPlayerAppWidgetProviderKt {
    public static final int byteSize(Bitmap bitmap) {
        Intrinsics.checkNotNullParameter(bitmap, "<this>");
        if (Build.VERSION.SDK_INT > 19) {
            return bitmap.getAllocationByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
