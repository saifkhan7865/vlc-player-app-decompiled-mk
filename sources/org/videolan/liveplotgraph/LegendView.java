package org.videolan.liveplotgraph;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B\u0017\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\"\u0010\u0012\u001a\u00020\u00102\u0018\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00170\u00150\u0014H\u0016R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/videolan/liveplotgraph/LegendView;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lorg/videolan/liveplotgraph/PlotViewDataChangeListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "plotView", "Lorg/videolan/liveplotgraph/PlotView;", "plotViewId", "initAttributes", "", "onAttachedToWindow", "onDataChanged", "data", "", "Lkotlin/Pair;", "Lorg/videolan/liveplotgraph/LineGraph;", "", "live-plot-graph_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LegendView.kt */
public final class LegendView extends ConstraintLayout implements PlotViewDataChangeListener {
    private PlotView plotView;
    private int plotViewId = -1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LegendView(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        setWillNotDraw(false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LegendView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, 0);
        setWillNotDraw(false);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LegendView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
        initAttributes(attributeSet, i);
        setWillNotDraw(false);
    }

    private final void initAttributes(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.LPGLegendView, 0, i);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "obtainStyledAttributes(...)");
        try {
            this.plotViewId = obtainStyledAttributes.getResourceId(R.styleable.LPGLegendView_lpg_plot_view, -1);
            Unit unit = Unit.INSTANCE;
        } catch (Exception e) {
            Integer.valueOf(Log.w("LegendView", e.getMessage(), e));
        } catch (Throwable th) {
            obtainStyledAttributes.recycle();
            throw th;
        }
        obtainStyledAttributes.recycle();
    }

    /* JADX WARNING: type inference failed for: r2v3, types: [kotlin.Unit] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0038, code lost:
        if (r2 == null) goto L_0x003a;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onAttachedToWindow() {
        /*
            r3 = this;
            super.onAttachedToWindow()
            boolean r0 = r3.isInEditMode()
            if (r0 != 0) goto L_0x0053
            android.content.Context r0 = r3.getContext()
            boolean r1 = r0 instanceof android.app.Activity
            r2 = 0
            if (r1 == 0) goto L_0x0015
            android.app.Activity r0 = (android.app.Activity) r0
            goto L_0x0016
        L_0x0015:
            r0 = r2
        L_0x0016:
            if (r0 == 0) goto L_0x003a
            int r1 = r3.plotViewId
            android.view.View r0 = r0.findViewById(r1)
            org.videolan.liveplotgraph.PlotView r0 = (org.videolan.liveplotgraph.PlotView) r0
            if (r0 == 0) goto L_0x0038
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r3.plotView = r0
            if (r0 != 0) goto L_0x002f
            java.lang.String r0 = "plotView"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L_0x0030
        L_0x002f:
            r2 = r0
        L_0x0030:
            r0 = r3
            org.videolan.liveplotgraph.PlotViewDataChangeListener r0 = (org.videolan.liveplotgraph.PlotViewDataChangeListener) r0
            r2.addListener(r0)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
        L_0x0038:
            if (r2 != 0) goto L_0x0053
        L_0x003a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cannot find the plot view with id "
            r0.<init>(r1)
            int r1 = r3.plotViewId
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "LegendView"
            int r0 = android.util.Log.w(r1, r0)
            java.lang.Integer.valueOf(r0)
        L_0x0053:
            android.view.ViewGroup$LayoutParams r0 = r3.getLayoutParams()
            r1 = -2
            r0.height = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.liveplotgraph.LegendView.onAttachedToWindow():void");
    }

    public void onDataChanged(List<Pair<LineGraph, String>> list) {
        Intrinsics.checkNotNullParameter(list, "data");
        removeAllViews();
        GridLayout gridLayout = new GridLayout(getContext());
        gridLayout.setColumnCount(2);
        addView(gridLayout);
        for (Pair pair : list) {
            TextView textView = new TextView(getContext());
            textView.setText(((LineGraph) pair.getFirst()).getTitle());
            textView.setTextColor(((LineGraph) pair.getFirst()).getColor());
            gridLayout.addView(textView);
            TextView textView2 = new TextView(getContext());
            textView2.setText((CharSequence) pair.getSecond());
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams();
            layoutParams.leftMargin = KotlinExtensionsKt.getDp(4);
            textView2.setLayoutParams(layoutParams);
            gridLayout.addView(textView2);
        }
    }
}
