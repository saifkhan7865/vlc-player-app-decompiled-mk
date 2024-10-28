package org.videolan.vlc.gui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.WindowManager;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u0013\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\tJ\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0014J\u000e\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u0012\u001a\u00020\tR\u001a\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/view/AutoFitRecyclerView;", "Landroidx/recyclerview/widget/RecyclerView;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "columnWidth", "getColumnWidth", "()I", "setColumnWidth", "(I)V", "gridLayoutManager", "Landroidx/recyclerview/widget/GridLayoutManager;", "spanCount", "getPerfectColumnWidth", "margin", "init", "", "onMeasure", "widthSpec", "heightSpec", "setNumColumns", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AutoFitRecyclerView.kt */
public final class AutoFitRecyclerView extends RecyclerView {
    private int columnWidth = -1;
    private GridLayoutManager gridLayoutManager;
    private int spanCount = -1;

    public final int getColumnWidth() {
        return this.columnWidth;
    }

    public final void setColumnWidth(int i) {
        this.columnWidth = i;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoFitRecyclerView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        init(context, (AttributeSet) null);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoFitRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        init(context, attributeSet);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AutoFitRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        init(context, attributeSet);
    }

    private final void init(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16843031});
            Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
            this.columnWidth = obtainStyledAttributes.getDimensionPixelSize(0, -1);
            obtainStyledAttributes.recycle();
        }
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 1);
        this.gridLayoutManager = gridLayoutManager2;
        setLayoutManager(gridLayoutManager2);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.spanCount != -1 || this.columnWidth <= 0) {
            GridLayoutManager gridLayoutManager2 = this.gridLayoutManager;
            Intrinsics.checkNotNull(gridLayoutManager2);
            gridLayoutManager2.setSpanCount(this.spanCount);
            return;
        }
        int coerceAtLeast = RangesKt.coerceAtLeast(2, getMeasuredWidth() / this.columnWidth);
        GridLayoutManager gridLayoutManager3 = this.gridLayoutManager;
        Intrinsics.checkNotNull(gridLayoutManager3);
        gridLayoutManager3.setSpanCount(coerceAtLeast);
    }

    public final int getPerfectColumnWidth(int i, int i2) {
        Context applicationContext = getContext().getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        Object systemService = ContextCompat.getSystemService(applicationContext, WindowManager.class);
        Intrinsics.checkNotNull(systemService);
        int width = ((WindowManager) systemService).getDefaultDisplay().getWidth() - i2;
        return i + ((width % i) / RangesKt.coerceAtLeast(2, width / i));
    }

    public final void setNumColumns(int i) {
        this.spanCount = i;
    }
}
