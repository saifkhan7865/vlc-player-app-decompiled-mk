package org.videolan.vlc.gui.dialogs;

import androidx.core.content.ContextCompat;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.ContextSheet;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "()Ljava/lang/Integer;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextSheet.kt */
final class ContextSheet$ContextAdapter$ViewHolder$focusedColor$2 extends Lambda implements Function0<Integer> {
    final /* synthetic */ ContextSheet.ContextAdapter.ViewHolder this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContextSheet$ContextAdapter$ViewHolder$focusedColor$2(ContextSheet.ContextAdapter.ViewHolder viewHolder) {
        super(0);
        this.this$0 = viewHolder;
    }

    public final Integer invoke() {
        return Integer.valueOf(ContextCompat.getColor(this.this$0.itemView.getContext(), R.color.orange500transparent));
    }
}
