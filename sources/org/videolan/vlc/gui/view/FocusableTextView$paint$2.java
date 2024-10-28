package org.videolan.vlc.gui.view;

import android.graphics.Paint;
import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/Paint;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusableTextView.kt */
final class FocusableTextView$paint$2 extends Lambda implements Function0<Paint> {
    final /* synthetic */ FocusableTextView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FocusableTextView$paint$2(FocusableTextView focusableTextView) {
        super(0);
        this.this$0 = focusableTextView;
    }

    public final Paint invoke() {
        Paint paint = new Paint();
        FocusableTextView focusableTextView = this.this$0;
        paint.setAntiAlias(true);
        paint.setColor(ContextCompat.getColor(focusableTextView.getContext(), R.color.orange500focus));
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(focusableTextView.getTextSize());
        paint.setTypeface(focusableTextView.getTypeface());
        return paint;
    }
}
