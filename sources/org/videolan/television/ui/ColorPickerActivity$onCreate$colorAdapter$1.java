package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerActivity.kt */
final class ColorPickerActivity$onCreate$colorAdapter$1 extends Lambda implements Function1<Integer, Unit> {
    final /* synthetic */ ColorPickerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ColorPickerActivity$onCreate$colorAdapter$1(ColorPickerActivity colorPickerActivity) {
        super(1);
        this.this$0 = colorPickerActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(int i) {
        this.this$0.getBinding$television_release().newColor.setColor(i);
    }
}
