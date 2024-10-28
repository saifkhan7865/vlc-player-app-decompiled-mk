package org.videolan.vlc.gui.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.collection.SparseArrayCompat;
import androidx.recyclerview.widget.RecyclerView;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import org.videolan.resources.util.HeaderProvider;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u0000 %2\u00020\u0001:\u0001%B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002J\u0018\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J(\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020 H\u0002J \u0010$\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020 2\u0006\u0010!\u001a\u00020\"H\u0016R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\b\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/videolan/vlc/gui/view/RecyclerSectionItemGridDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "headerOffset", "", "space", "sideSpace", "sticky", "", "nbColumns", "provider", "Lorg/videolan/resources/util/HeaderProvider;", "(IIIZILorg/videolan/resources/util/HeaderProvider;)V", "header", "Landroid/widget/TextView;", "headerView", "Landroid/view/View;", "isList", "()Z", "setList", "(Z)V", "drawHeader", "", "c", "Landroid/graphics/Canvas;", "child", "fixLayoutSize", "view", "parent", "Landroid/view/ViewGroup;", "getItemOffsets", "outRect", "Landroid/graphics/Rect;", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "inflateHeaderView", "onDrawOver", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RecyclerSectionItemGridDecoration.kt */
public final class RecyclerSectionItemGridDecoration extends RecyclerView.ItemDecoration {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private TextView header;
    private final int headerOffset;
    private View headerView;
    private boolean isList;
    private final int nbColumns;
    private final HeaderProvider provider;
    private final int sideSpace;
    private final int space;
    private final boolean sticky;

    public RecyclerSectionItemGridDecoration(int i, int i2, int i3, boolean z, int i4, HeaderProvider headerProvider) {
        Intrinsics.checkNotNullParameter(headerProvider, "provider");
        this.headerOffset = i;
        this.space = i2;
        this.sideSpace = i3;
        this.sticky = z;
        this.nbColumns = i4;
        this.provider = headerProvider;
    }

    public final boolean isList() {
        return this.isList;
    }

    public final void setList(boolean z) {
        this.isList = z;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        super.getItemOffsets(rect, view, recyclerView, state);
        if (this.isList && Settings.INSTANCE.getShowHeaders()) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            if (this.provider.isFirstInSection(childAdapterPosition)) {
                rect.top = this.headerOffset;
            }
            if (this.provider.isLastInSection(childAdapterPosition)) {
                rect.bottom = this.space * 2;
            }
        } else if (!this.isList) {
            int childAdapterPosition2 = recyclerView.getChildAdapterPosition(view);
            int positionForSection = this.provider.getPositionForSection(childAdapterPosition2);
            boolean z = true;
            boolean z2 = positionForSection == childAdapterPosition2 || (childAdapterPosition2 - positionForSection) % this.nbColumns == 0;
            int i = this.nbColumns;
            if ((childAdapterPosition2 - positionForSection) % i != i - 1) {
                z = false;
            }
            rect.left = (!z2 || !Settings.INSTANCE.getShowHeaders()) ? this.space / 2 : this.sideSpace;
            rect.right = (!z || !Settings.INSTANCE.getShowHeaders()) ? this.space / 2 : this.sideSpace;
            rect.top = this.space / 2;
            rect.bottom = this.space / 2;
            if (Settings.INSTANCE.getShowHeaders()) {
                int i2 = this.nbColumns;
                for (int i3 = 0; i3 < i2; i3++) {
                    int i4 = childAdapterPosition2 - i3;
                    if (i4 >= 0 && this.provider.isFirstInSection(i4)) {
                        rect.top = this.headerOffset + (this.space * 2);
                    }
                }
            }
        }
    }

    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        SparseArrayCompat value;
        int i;
        Intrinsics.checkNotNullParameter(canvas, "c");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        super.onDrawOver(canvas, recyclerView, state);
        if (Settings.INSTANCE.getShowHeaders() && (value = this.provider.getLiveHeaders().getValue()) != null && !value.isEmpty()) {
            if (this.headerView == null) {
                View inflateHeaderView = inflateHeaderView(recyclerView);
                this.headerView = inflateHeaderView;
                if (inflateHeaderView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerView");
                    inflateHeaderView = null;
                }
                View findViewById = inflateHeaderView.findViewById(R.id.section_header);
                Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.TextView");
                this.header = (TextView) findViewById;
                View view = this.headerView;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerView");
                    view = null;
                }
                fixLayoutSize(view, recyclerView);
            }
            View childAt = recyclerView.getChildAt(0);
            if (!this.sticky || childAt == null) {
                i = 0;
            } else {
                i = this.provider.getPositionForSection(recyclerView.getChildAdapterPosition(childAt));
                String sectionforPosition = this.provider.getSectionforPosition(i);
                TextView textView = this.header;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("header");
                    textView = null;
                }
                textView.setText(sectionforPosition);
                View view2 = this.headerView;
                if (view2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerView");
                    view2 = null;
                }
                fixLayoutSize(view2, recyclerView);
                View childAt2 = recyclerView.getChildAt(0);
                Intrinsics.checkNotNullExpressionValue(childAt2, "getChildAt(...)");
                View view3 = this.headerView;
                if (view3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("headerView");
                    view3 = null;
                }
                drawHeader(canvas, childAt2, view3);
            }
            ArrayList arrayList = new ArrayList();
            int childCount = recyclerView.getChildCount();
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt3 = recyclerView.getChildAt(i2);
                int childAdapterPosition = recyclerView.getChildAdapterPosition(childAt3);
                if (childAdapterPosition != i) {
                    String sectionforPosition2 = this.provider.getSectionforPosition(childAdapterPosition);
                    TextView textView2 = this.header;
                    if (textView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("header");
                        textView2 = null;
                    }
                    textView2.setText(sectionforPosition2);
                    if (this.provider.isFirstInSection(childAdapterPosition)) {
                        View view4 = this.headerView;
                        if (view4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("headerView");
                            view4 = null;
                        }
                        fixLayoutSize(view4, recyclerView);
                        Intrinsics.checkNotNull(childAt3);
                        View view5 = this.headerView;
                        if (view5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("headerView");
                            view5 = null;
                        }
                        drawHeader(canvas, childAt3, view5);
                        arrayList.add(Integer.valueOf(i2));
                    }
                }
            }
        }
    }

    private final void drawHeader(Canvas canvas, View view, View view2) {
        if (Settings.INSTANCE.getShowHeaders()) {
            canvas.save();
            if (this.sticky) {
                int top = view.getTop() - view2.getHeight();
                double d = (double) this.space;
                Double.isNaN(d);
                canvas.translate(0.0f, (float) RangesKt.coerceAtLeast(top - ((int) (d * 1.5d)), 0));
            } else {
                canvas.translate(0.0f, (float) (view.getTop() - view2.getHeight()));
            }
            view2.draw(canvas);
            canvas.restore();
        }
    }

    private final View inflateHeaderView(RecyclerView recyclerView) {
        if (Settings.INSTANCE.getShowTvUi()) {
            View inflate = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.recycler_section_header_tv, recyclerView, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
            return inflate;
        }
        View inflate2 = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.recycler_section_header, recyclerView, false);
        Intrinsics.checkNotNullExpressionValue(inflate2, "inflate(...)");
        return inflate2;
    }

    private final void fixLayoutSize(View view, ViewGroup viewGroup) {
        view.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getWidth(), 1073741824), 0, view.getLayoutParams().width), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getHeight(), 0), viewGroup.getPaddingTop() + viewGroup.getPaddingBottom(), view.getLayoutParams().height));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, d2 = {"Lorg/videolan/vlc/gui/view/RecyclerSectionItemGridDecoration$Companion;", "", "()V", "getItemSize", "", "screenWidth", "nbColumns", "spacing", "sideSpacing", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: RecyclerSectionItemGridDecoration.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int getItemSize(int i, int i2, int i3, int i4) {
            return (int) (((float) ((i - (i3 * (i2 - 1))) - (i4 * 2))) / ((float) i2));
        }

        private Companion() {
        }
    }
}
