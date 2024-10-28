package org.videolan.television.ui;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u00009\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J \u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u001b\u0010\u0002\u001a\u00020\u00038FX\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0015"}, d2 = {"org/videolan/television/ui/ColorPickerActivity$onCreate$1", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "paint", "Landroid/graphics/Paint;", "getPaint", "()Landroid/graphics/Paint;", "paint$delegate", "Lkotlin/Lazy;", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "onDrawOver", "c", "Landroid/graphics/Canvas;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerActivity.kt */
public final class ColorPickerActivity$onCreate$1 extends RecyclerView.ItemDecoration {
    final /* synthetic */ ArrayList<Integer> $colors;
    private final Lazy paint$delegate;

    ColorPickerActivity$onCreate$1(ColorPickerActivity colorPickerActivity, ArrayList<Integer> arrayList) {
        this.$colors = arrayList;
        this.paint$delegate = LazyKt.lazy(new ColorPickerActivity$onCreate$1$paint$2(colorPickerActivity));
    }

    public final Paint getPaint() {
        return (Paint) this.paint$delegate.getValue();
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        super.getItemOffsets(rect, view, recyclerView, state);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        rect.left = KotlinExtensionsKt.getDp(4);
        rect.right = KotlinExtensionsKt.getDp(4);
        rect.top = childAdapterPosition > this.$colors.size() + -1 ? KotlinExtensionsKt.getDp(32) : KotlinExtensionsKt.getDp(4);
        rect.bottom = KotlinExtensionsKt.getDp(4);
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(canvas, "c");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        super.onDrawOver(canvas, recyclerView, state);
        View childAt = recyclerView.getChildAt(this.$colors.size());
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        Intrinsics.checkNotNull(adapter);
        Canvas canvas2 = canvas;
        canvas2.drawLine((float) childAt.getLeft(), ((float) childAt.getTop()) - ((float) KotlinExtensionsKt.getDp(16)), (float) recyclerView.getChildAt(adapter.getItemCount() - 1).getRight(), ((float) childAt.getTop()) - ((float) KotlinExtensionsKt.getDp(16)), getPaint());
    }
}
