package org.videolan.television.ui;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.interfaces.FocusListener;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0016\u0010\u0010\u001a\u00020\u000f2\f\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0012H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/videolan/television/ui/FocusableRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "focusListener", "Lorg/videolan/resources/interfaces/FocusListener;", "screenHeight", "init", "", "setAdapter", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FocusableRecyclerView.kt */
public final class FocusableRecyclerView extends RecyclerView {
    private FocusListener focusListener;
    /* access modifiers changed from: private */
    public int screenHeight;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FocusableRecyclerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FocusableRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FocusableRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context);
    }

    private final void init(Context context) {
        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
        this.screenHeight = KextensionsKt.getScreenHeight((Activity) context);
        this.focusListener = new FocusableRecyclerView$init$1(this);
    }

    public void setAdapter(RecyclerView.Adapter<?> adapter) {
        if (adapter instanceof TvItemAdapter) {
            ((TvItemAdapter) adapter).setOnFocusChangeListener(this.focusListener);
        }
        super.setAdapter(adapter);
    }
}
