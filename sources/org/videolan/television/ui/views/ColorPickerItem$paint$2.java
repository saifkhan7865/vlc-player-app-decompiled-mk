package org.videolan.television.ui.views;

import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/Paint;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerItem.kt */
final class ColorPickerItem$paint$2 extends Lambda implements Function0<Paint> {
    public static final ColorPickerItem$paint$2 INSTANCE = new ColorPickerItem$paint$2();

    ColorPickerItem$paint$2() {
        super(0);
    }

    public final Paint invoke() {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        return paint;
    }
}
