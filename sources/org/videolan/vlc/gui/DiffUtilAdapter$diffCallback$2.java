package org.videolan.vlc.gui;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.gui.DiffUtilAdapter;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "D", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: DiffUtilAdapter.kt */
final class DiffUtilAdapter$diffCallback$2 extends Lambda implements Function0<DiffUtilAdapter.DiffCallback<D>> {
    final /* synthetic */ DiffUtilAdapter<D, VH> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DiffUtilAdapter$diffCallback$2(DiffUtilAdapter<D, VH> diffUtilAdapter) {
        super(0);
        this.this$0 = diffUtilAdapter;
    }

    public final DiffUtilAdapter.DiffCallback<D> invoke() {
        return this.this$0.createCB();
    }
}
