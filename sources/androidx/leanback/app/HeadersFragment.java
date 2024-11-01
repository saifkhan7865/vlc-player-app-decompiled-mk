package androidx.leanback.app;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.leanback.R;
import androidx.leanback.widget.ClassPresenterSelector;
import androidx.leanback.widget.DividerPresenter;
import androidx.leanback.widget.DividerRow;
import androidx.leanback.widget.FocusHighlightHelper;
import androidx.leanback.widget.ItemBridgeAdapter;
import androidx.leanback.widget.PresenterSelector;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowHeaderPresenter;
import androidx.leanback.widget.SectionRow;
import androidx.leanback.widget.VerticalGridView;
import androidx.recyclerview.widget.RecyclerView;

@Deprecated
public class HeadersFragment extends BaseRowFragment {
    private static final PresenterSelector sHeaderPresenter = new ClassPresenterSelector().addClassPresenter(DividerRow.class, new DividerPresenter()).addClassPresenter(SectionRow.class, new RowHeaderPresenter(R.layout.lb_section_header, false)).addClassPresenter(Row.class, new RowHeaderPresenter(R.layout.lb_header));
    static View.OnLayoutChangeListener sLayoutChangeListener = new View.OnLayoutChangeListener() {
        public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            view.setPivotX(view.getLayoutDirection() == 1 ? (float) view.getWidth() : 0.0f);
            view.setPivotY((float) (view.getMeasuredHeight() / 2));
        }
    };
    private final ItemBridgeAdapter.AdapterListener mAdapterListener = new ItemBridgeAdapter.AdapterListener() {
        public void onCreate(final ItemBridgeAdapter.ViewHolder viewHolder) {
            View view = viewHolder.getViewHolder().view;
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (HeadersFragment.this.mOnHeaderClickedListener != null) {
                        HeadersFragment.this.mOnHeaderClickedListener.onHeaderClicked((RowHeaderPresenter.ViewHolder) viewHolder.getViewHolder(), (Row) viewHolder.getItem());
                    }
                }
            });
            if (HeadersFragment.this.mWrapper != null) {
                viewHolder.itemView.addOnLayoutChangeListener(HeadersFragment.sLayoutChangeListener);
            } else {
                view.addOnLayoutChangeListener(HeadersFragment.sLayoutChangeListener);
            }
        }
    };
    private int mBackgroundColor;
    private boolean mBackgroundColorSet;
    private boolean mHeadersEnabled = true;
    private boolean mHeadersGone = false;
    OnHeaderClickedListener mOnHeaderClickedListener;
    private OnHeaderViewSelectedListener mOnHeaderViewSelectedListener;
    final ItemBridgeAdapter.Wrapper mWrapper = new ItemBridgeAdapter.Wrapper() {
        public void wrap(View view, View view2) {
            ((FrameLayout) view).addView(view2);
        }

        public View createWrapper(View view) {
            return new NoOverlappingFrameLayout(view.getContext());
        }
    };

    @Deprecated
    public interface OnHeaderClickedListener {
        void onHeaderClicked(RowHeaderPresenter.ViewHolder viewHolder, Row row);
    }

    @Deprecated
    public interface OnHeaderViewSelectedListener {
        void onHeaderSelected(RowHeaderPresenter.ViewHolder viewHolder, Row row);
    }

    public /* bridge */ /* synthetic */ int getSelectedPosition() {
        return super.getSelectedPosition();
    }

    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public /* bridge */ /* synthetic */ void onDestroyView() {
        super.onDestroyView();
    }

    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public /* bridge */ /* synthetic */ boolean onTransitionPrepare() {
        return super.onTransitionPrepare();
    }

    public /* bridge */ /* synthetic */ void setAlignment(int i) {
        super.setAlignment(i);
    }

    public /* bridge */ /* synthetic */ void setSelectedPosition(int i) {
        super.setSelectedPosition(i);
    }

    public /* bridge */ /* synthetic */ void setSelectedPosition(int i, boolean z) {
        super.setSelectedPosition(i, z);
    }

    public HeadersFragment() {
        setPresenterSelector(sHeaderPresenter);
        FocusHighlightHelper.setupHeaderItemFocusHighlight(getBridgeAdapter());
    }

    public void setOnHeaderClickedListener(OnHeaderClickedListener onHeaderClickedListener) {
        this.mOnHeaderClickedListener = onHeaderClickedListener;
    }

    public void setOnHeaderViewSelectedListener(OnHeaderViewSelectedListener onHeaderViewSelectedListener) {
        this.mOnHeaderViewSelectedListener = onHeaderViewSelectedListener;
    }

    /* access modifiers changed from: package-private */
    public VerticalGridView findGridViewFromRoot(View view) {
        return (VerticalGridView) view.findViewById(R.id.browse_headers);
    }

    /* access modifiers changed from: package-private */
    public void onRowSelected(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, int i2) {
        OnHeaderViewSelectedListener onHeaderViewSelectedListener = this.mOnHeaderViewSelectedListener;
        if (onHeaderViewSelectedListener == null) {
            return;
        }
        if (viewHolder == null || i < 0) {
            onHeaderViewSelectedListener.onHeaderSelected((RowHeaderPresenter.ViewHolder) null, (Row) null);
            return;
        }
        ItemBridgeAdapter.ViewHolder viewHolder2 = (ItemBridgeAdapter.ViewHolder) viewHolder;
        onHeaderViewSelectedListener.onHeaderSelected((RowHeaderPresenter.ViewHolder) viewHolder2.getViewHolder(), (Row) viewHolder2.getItem());
    }

    /* access modifiers changed from: package-private */
    public int getLayoutResourceId() {
        return R.layout.lb_headers_fragment;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        VerticalGridView verticalGridView = getVerticalGridView();
        if (verticalGridView != null) {
            if (this.mBackgroundColorSet) {
                verticalGridView.setBackgroundColor(this.mBackgroundColor);
                updateFadingEdgeToBrandColor(this.mBackgroundColor);
            } else {
                Drawable background = verticalGridView.getBackground();
                if (background instanceof ColorDrawable) {
                    updateFadingEdgeToBrandColor(((ColorDrawable) background).getColor());
                }
            }
            updateListViewVisibility();
        }
    }

    private void updateListViewVisibility() {
        VerticalGridView verticalGridView = getVerticalGridView();
        if (verticalGridView != null) {
            getView().setVisibility(this.mHeadersGone ? 8 : 0);
            if (this.mHeadersGone) {
                return;
            }
            if (this.mHeadersEnabled) {
                verticalGridView.setChildrenVisibility(0);
            } else {
                verticalGridView.setChildrenVisibility(4);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setHeadersEnabled(boolean z) {
        this.mHeadersEnabled = z;
        updateListViewVisibility();
    }

    /* access modifiers changed from: package-private */
    public void setHeadersGone(boolean z) {
        this.mHeadersGone = z;
        updateListViewVisibility();
    }

    static class NoOverlappingFrameLayout extends FrameLayout {
        public boolean hasOverlappingRendering() {
            return false;
        }

        public NoOverlappingFrameLayout(Context context) {
            super(context);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateAdapter() {
        super.updateAdapter();
        ItemBridgeAdapter bridgeAdapter = getBridgeAdapter();
        bridgeAdapter.setAdapterListener(this.mAdapterListener);
        bridgeAdapter.setWrapper(this.mWrapper);
    }

    /* access modifiers changed from: package-private */
    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        this.mBackgroundColorSet = true;
        if (getVerticalGridView() != null) {
            getVerticalGridView().setBackgroundColor(this.mBackgroundColor);
            updateFadingEdgeToBrandColor(this.mBackgroundColor);
        }
    }

    private void updateFadingEdgeToBrandColor(int i) {
        Drawable background = getView().findViewById(R.id.fade_out_edge).getBackground();
        if (background instanceof GradientDrawable) {
            background.mutate();
            ((GradientDrawable) background).setColors(new int[]{0, i});
        }
    }

    public void onTransitionStart() {
        VerticalGridView verticalGridView;
        super.onTransitionStart();
        if (!this.mHeadersEnabled && (verticalGridView = getVerticalGridView()) != null) {
            verticalGridView.setDescendantFocusability(131072);
            if (verticalGridView.hasFocus()) {
                verticalGridView.requestFocus();
            }
        }
    }

    public void onTransitionEnd() {
        VerticalGridView verticalGridView;
        if (this.mHeadersEnabled && (verticalGridView = getVerticalGridView()) != null) {
            verticalGridView.setDescendantFocusability(262144);
            if (verticalGridView.hasFocus()) {
                verticalGridView.requestFocus();
            }
        }
        super.onTransitionEnd();
    }

    public boolean isScrolling() {
        return getVerticalGridView().getScrollState() != 0;
    }
}
