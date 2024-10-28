package org.videolan.vlc.gui.helpers;

import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0000¢\u0006\u0002\u0010\u0005J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u0018\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0010H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u00028\u0000X\u000e¢\u0006\u0010\n\u0002\u0010\u000e\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u0005¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/gui/helpers/SelectorViewHolder;", "T", "Landroidx/databinding/ViewDataBinding;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "vdb", "(Landroidx/databinding/ViewDataBinding;)V", "ITEM_FOCUS_OFF", "", "ITEM_FOCUS_ON", "ITEM_SELECTION_ON", "binding", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "Landroidx/databinding/ViewDataBinding;", "isSelected", "", "selectView", "", "selected", "setViewBackground", "focus", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SelectorViewHolder.kt */
public class SelectorViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private final int ITEM_FOCUS_OFF;
    private final int ITEM_FOCUS_ON;
    private final int ITEM_SELECTION_ON;
    private T binding;

    /* access modifiers changed from: protected */
    public boolean isSelected() {
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SelectorViewHolder(T t) {
        super(t.getRoot());
        Intrinsics.checkNotNullParameter(t, "vdb");
        this.binding = t;
        this.ITEM_FOCUS_ON = ContextCompat.getColor(t.getRoot().getContext(), R.color.orange500transparent);
        this.ITEM_FOCUS_OFF = ContextCompat.getColor(t.getRoot().getContext(), R.color.transparent);
        this.ITEM_SELECTION_ON = ContextCompat.getColor(t.getRoot().getContext(), R.color.orange200transparent);
        this.itemView.setOnFocusChangeListener(new SelectorViewHolder$$ExternalSyntheticLambda0(this));
    }

    public final T getBinding() {
        return this.binding;
    }

    public final void setBinding(T t) {
        Intrinsics.checkNotNullParameter(t, "<set-?>");
        this.binding = t;
    }

    /* access modifiers changed from: private */
    public static final void _init_$lambda$0(SelectorViewHolder selectorViewHolder, View view, boolean z) {
        Intrinsics.checkNotNullParameter(selectorViewHolder, "this$0");
        if (selectorViewHolder.getLayoutPosition() >= 0) {
            selectorViewHolder.setViewBackground(z, selectorViewHolder.isSelected());
        }
    }

    public void selectView(boolean z) {
        setViewBackground(this.itemView.hasFocus(), z);
    }

    private final void setViewBackground(boolean z, boolean z2) {
        this.binding.setVariable(BR.bgColor, Integer.valueOf(z ? this.ITEM_FOCUS_ON : z2 ? this.ITEM_SELECTION_ON : this.ITEM_FOCUS_OFF));
    }
}
