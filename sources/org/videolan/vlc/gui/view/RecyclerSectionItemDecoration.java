package org.videolan.vlc.gui.view;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007¢\u0006\u0002\u0010\bJ \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J(\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0010\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0014\u001a\u00020\u0019H\u0002J \u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/videolan/vlc/gui/view/RecyclerSectionItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "headerOffset", "", "sticky", "", "provider", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "(IZLorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;)V", "header", "Landroid/widget/TextView;", "headerView", "Landroid/view/View;", "drawHeader", "", "c", "Landroid/graphics/Canvas;", "child", "fixLayoutSize", "view", "parent", "Landroid/view/ViewGroup;", "getItemOffsets", "outRect", "Landroid/graphics/Rect;", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "inflateHeaderView", "onDrawOver", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RecyclerSectionItemDecoration.kt */
public final class RecyclerSectionItemDecoration extends RecyclerView.ItemDecoration {
    private TextView header;
    private final int headerOffset;
    private View headerView;
    private final MedialibraryProvider<?> provider;
    private final boolean sticky;

    public RecyclerSectionItemDecoration(int i, boolean z, MedialibraryProvider<?> medialibraryProvider) {
        Intrinsics.checkNotNullParameter(medialibraryProvider, "provider");
        this.headerOffset = i;
        this.sticky = z;
        this.provider = medialibraryProvider;
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, OAuth2RequestParameters.State);
        super.getItemOffsets(rect, view, recyclerView, state);
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        if (Settings.INSTANCE.getShowHeaders() && this.provider.isFirstInSection(childAdapterPosition)) {
            rect.top = this.headerOffset;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x009e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDrawOver(android.graphics.Canvas r11, androidx.recyclerview.widget.RecyclerView r12, androidx.recyclerview.widget.RecyclerView.State r13) {
        /*
            r10 = this;
            java.lang.String r0 = "c"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = "parent"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r0)
            java.lang.String r0 = "state"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            super.onDrawOver(r11, r12, r13)
            org.videolan.tools.Settings r13 = org.videolan.tools.Settings.INSTANCE
            boolean r13 = r13.getShowHeaders()
            if (r13 != 0) goto L_0x001b
            return
        L_0x001b:
            android.view.View r13 = r10.headerView
            java.lang.String r0 = "headerView"
            r1 = 0
            if (r13 != 0) goto L_0x004b
            android.view.View r13 = r10.inflateHeaderView(r12)
            r10.headerView = r13
            if (r13 != 0) goto L_0x002e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r13 = r1
        L_0x002e:
            int r2 = org.videolan.vlc.R.id.section_header
            android.view.View r13 = r13.findViewById(r2)
            java.lang.String r2 = "null cannot be cast to non-null type android.widget.TextView"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13, r2)
            android.widget.TextView r13 = (android.widget.TextView) r13
            r10.header = r13
            android.view.View r13 = r10.headerView
            if (r13 != 0) goto L_0x0045
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r13 = r1
        L_0x0045:
            r2 = r12
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            r10.fixLayoutSize(r13, r2)
        L_0x004b:
            r13 = 0
            android.view.View r2 = r12.getChildAt(r13)
            boolean r3 = r10.sticky
            java.lang.String r4 = "header"
            if (r3 == 0) goto L_0x0092
            if (r2 == 0) goto L_0x0092
            int r2 = r12.getChildAdapterPosition(r2)
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider<?> r3 = r10.provider
            int r2 = r3.getPositionForSection(r2)
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider<?> r3 = r10.provider
            java.lang.String r3 = r3.getHeaderForPostion(r2)
            if (r3 == 0) goto L_0x0092
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider<?> r3 = r10.provider
            java.lang.String r3 = r3.getSectionforPosition(r2)
            android.widget.TextView r5 = r10.header
            if (r5 != 0) goto L_0x0078
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r5 = r1
        L_0x0078:
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r5.setText(r3)
            android.view.View r3 = r12.getChildAt(r13)
            java.lang.String r5 = "getChildAt(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r5)
            android.view.View r5 = r10.headerView
            if (r5 != 0) goto L_0x008e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r5 = r1
        L_0x008e:
            r10.drawHeader(r11, r3, r5)
            goto L_0x0093
        L_0x0092:
            r2 = 0
        L_0x0093:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            int r5 = r12.getChildCount()
        L_0x009c:
            if (r13 >= r5) goto L_0x00dc
            android.view.View r6 = r12.getChildAt(r13)
            int r7 = r12.getChildAdapterPosition(r6)
            if (r7 != r2) goto L_0x00a9
            goto L_0x00d9
        L_0x00a9:
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider<?> r8 = r10.provider
            java.lang.String r8 = r8.getSectionforPosition(r7)
            android.widget.TextView r9 = r10.header
            if (r9 != 0) goto L_0x00b7
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r9 = r1
        L_0x00b7:
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            r9.setText(r8)
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider<?> r8 = r10.provider
            boolean r7 = r8.isFirstInSection(r7)
            if (r7 == 0) goto L_0x00d9
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            android.view.View r7 = r10.headerView
            if (r7 != 0) goto L_0x00cf
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r7 = r1
        L_0x00cf:
            r10.drawHeader(r11, r6, r7)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)
            r3.add(r6)
        L_0x00d9:
            int r13 = r13 + 1
            goto L_0x009c
        L_0x00dc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.RecyclerSectionItemDecoration.onDrawOver(android.graphics.Canvas, androidx.recyclerview.widget.RecyclerView, androidx.recyclerview.widget.RecyclerView$State):void");
    }

    private final void drawHeader(Canvas canvas, View view, View view2) {
        if (Settings.INSTANCE.getShowHeaders()) {
            canvas.save();
            if (this.sticky) {
                canvas.translate(0.0f, (float) Math.max(0, view.getTop() - view2.getHeight()));
            } else {
                canvas.translate(0.0f, (float) (view.getTop() - view2.getHeight()));
            }
            view2.draw(canvas);
            canvas.restore();
        }
    }

    private final View inflateHeaderView(RecyclerView recyclerView) {
        View inflate = LayoutInflater.from(recyclerView.getContext()).inflate(R.layout.recycler_section_header, recyclerView, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        return inflate;
    }

    private final void fixLayoutSize(View view, ViewGroup viewGroup) {
        view.measure(ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getWidth(), 1073741824), viewGroup.getPaddingLeft() + viewGroup.getPaddingRight(), view.getLayoutParams().width), ViewGroup.getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(viewGroup.getHeight(), 0), viewGroup.getPaddingTop() + viewGroup.getPaddingBottom(), view.getLayoutParams().height));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }
}
