package org.videolan.liveplotgraph;

import android.graphics.Paint;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/graphics/Paint;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: LineGraph.kt */
final class LineGraph$paint$2 extends Lambda implements Function0<Paint> {
    final /* synthetic */ LineGraph this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LineGraph$paint$2(LineGraph lineGraph) {
        super(0);
        this.this$0 = lineGraph;
    }

    public final Paint invoke() {
        Paint paint = new Paint();
        paint.setColor(this.this$0.getColor());
        paint.setStrokeWidth((float) KotlinExtensionsKt.getDp(2));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        return paint;
    }
}
