package org.videolan.vlc.gui;

import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "insets", "Landroidx/core/graphics/Insets;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: InfoActivity.kt */
final class InfoActivity$insetListener$1 extends Lambda implements Function1<Insets, Unit> {
    final /* synthetic */ InfoActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    InfoActivity$insetListener$1(InfoActivity infoActivity) {
        super(1);
        this.this$0 = infoActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Insets) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Insets insets) {
        Intrinsics.checkNotNullParameter(insets, "insets");
        ViewGroup.LayoutParams layoutParams = this.this$0.getBinding$vlc_android_release().mlItemResolution.getLayoutParams();
        Intrinsics.checkNotNull(layoutParams, "null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        ((ConstraintLayout.LayoutParams) layoutParams).topMargin = insets.top + KotlinExtensionsKt.getDp(16);
    }
}
