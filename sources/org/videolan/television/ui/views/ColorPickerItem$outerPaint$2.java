package org.videolan.television.ui.views;

import android.content.Context;
import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/Paint;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerItem.kt */
final class ColorPickerItem$outerPaint$2 extends Lambda implements Function0<Paint> {
    final /* synthetic */ Context $context;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ColorPickerItem$outerPaint$2(Context context) {
        super(0);
        this.$context = context;
    }

    public final Paint invoke() {
        Paint paint = new Paint();
        Context context = this.$context;
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(context, R.color.grey500));
        return paint;
    }
}
