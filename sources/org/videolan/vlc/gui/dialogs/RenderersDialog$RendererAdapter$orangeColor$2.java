package org.videolan.vlc.gui.dialogs;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RenderersDialog.kt */
final class RenderersDialog$RendererAdapter$orangeColor$2 extends Lambda implements Function0<Integer> {
    final /* synthetic */ RenderersDialog this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RenderersDialog$RendererAdapter$orangeColor$2(RenderersDialog renderersDialog) {
        super(0);
        this.this$0 = renderersDialog;
    }

    public final Integer invoke() {
        TypedValue typedValue = new TypedValue();
        Context context = this.this$0.getContext();
        Intrinsics.checkNotNull(context);
        Resources.Theme theme = context.getTheme();
        Intrinsics.checkNotNullExpressionValue(theme, "getTheme(...)");
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return Integer.valueOf(typedValue.data);
    }
}
