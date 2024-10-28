package org.videolan.vlc.gui.dialogs;

import android.view.LayoutInflater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Landroid/view/LayoutInflater;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: ContextSheet.kt */
final class ContextSheet$ContextAdapter$inflater$2 extends Lambda implements Function0<LayoutInflater> {
    final /* synthetic */ ContextSheet this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ContextSheet$ContextAdapter$inflater$2(ContextSheet contextSheet) {
        super(0);
        this.this$0 = contextSheet;
    }

    public final LayoutInflater invoke() {
        return LayoutInflater.from(this.this$0.requireContext());
    }
}
