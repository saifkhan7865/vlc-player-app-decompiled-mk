package org.videolan.television.ui;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.television.ui.ColorPickerActivity;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "position", "", "view", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerActivity.kt */
final class ColorPickerActivity$ColorAdapter$onCreateViewHolder$1 extends Lambda implements Function2<Integer, View, Unit> {
    final /* synthetic */ ColorPickerActivity.ColorAdapter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ColorPickerActivity$ColorAdapter$onCreateViewHolder$1(ColorPickerActivity.ColorAdapter colorAdapter) {
        super(2);
        this.this$0 = colorAdapter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), (View) obj2);
        return Unit.INSTANCE;
    }

    public final void invoke(int i, View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        this.this$0.waitingForFocusRestore = true;
        view.clearFocus();
        if (i < 0 || i >= this.this$0.colors.size()) {
            int access$getSelectedVariantIndex$p = this.this$0.selectedVariantIndex;
            ColorPickerActivity.ColorAdapter colorAdapter = this.this$0;
            colorAdapter.selectedVariantIndex = i - colorAdapter.colors.size();
            this.this$0.notifyItemChanged(i);
            ColorPickerActivity.ColorAdapter colorAdapter2 = this.this$0;
            colorAdapter2.notifyItemChanged(colorAdapter2.colors.size() + access$getSelectedVariantIndex$p);
        } else {
            int access$getSelectedIndex$p = this.this$0.selectedIndex;
            this.this$0.selectedIndex = i;
            this.this$0.selectedVariantIndex = 9;
            this.this$0.notifyItemChanged(i);
            this.this$0.notifyItemChanged(access$getSelectedIndex$p);
            ColorPickerActivity.ColorAdapter colorAdapter3 = this.this$0;
            colorAdapter3.notifyItemRangeChanged(colorAdapter3.colors.size(), 20);
        }
        this.this$0.colorSelectionListener.invoke(Integer.valueOf(this.this$0.getSelectedColor()));
    }
}
