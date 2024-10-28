package org.videolan.television.ui;

import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.television.R;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/Paint;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerActivity.kt */
final class ColorPickerActivity$onCreate$1$paint$2 extends Lambda implements Function0<Paint> {
    final /* synthetic */ ColorPickerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ColorPickerActivity$onCreate$1$paint$2(ColorPickerActivity colorPickerActivity) {
        super(0);
        this.this$0 = colorPickerActivity;
    }

    public final Paint invoke() {
        Paint paint = new Paint();
        ColorPickerActivity colorPickerActivity = this.this$0;
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(colorPickerActivity, R.color.grey800));
        return paint;
    }
}
