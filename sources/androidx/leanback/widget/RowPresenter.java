package androidx.leanback.widget;

import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.graphics.ColorOverlayDimmer;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowHeaderPresenter;

public abstract class RowPresenter extends Presenter {
    public static final int SYNC_ACTIVATED_CUSTOM = 0;
    public static final int SYNC_ACTIVATED_TO_EXPANDED = 1;
    public static final int SYNC_ACTIVATED_TO_EXPANDED_AND_SELECTED = 3;
    public static final int SYNC_ACTIVATED_TO_SELECTED = 2;
    private RowHeaderPresenter mHeaderPresenter;
    boolean mSelectEffectEnabled = true;
    int mSyncActivatePolicy = 1;

    /* access modifiers changed from: protected */
    public abstract ViewHolder createRowViewHolder(ViewGroup viewGroup);

    public void freeze(ViewHolder viewHolder, boolean z) {
    }

    /* access modifiers changed from: protected */
    public boolean isClippingChildren() {
        return false;
    }

    public boolean isUsingDefaultSelectEffect() {
        return true;
    }

    static class ContainerViewHolder extends Presenter.ViewHolder {
        final ViewHolder mRowViewHolder;

        public ContainerViewHolder(RowContainerView rowContainerView, ViewHolder viewHolder) {
            super(rowContainerView);
            rowContainerView.addRowView(viewHolder.view);
            if (viewHolder.mHeaderViewHolder != null) {
                rowContainerView.addHeaderView(viewHolder.mHeaderViewHolder.view);
            }
            this.mRowViewHolder = viewHolder;
            viewHolder.mContainerViewHolder = this;
        }
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        private static final int ACTIVATED = 1;
        private static final int ACTIVATED_NOT_ASSIGNED = 0;
        private static final int NOT_ACTIVATED = 2;
        int mActivated = 0;
        protected final ColorOverlayDimmer mColorDimmer;
        ContainerViewHolder mContainerViewHolder;
        boolean mExpanded;
        RowHeaderPresenter.ViewHolder mHeaderViewHolder;
        boolean mInitialzed;
        private BaseOnItemViewClickedListener mOnItemViewClickedListener;
        BaseOnItemViewSelectedListener mOnItemViewSelectedListener;
        private View.OnKeyListener mOnKeyListener;
        Row mRow;
        Object mRowObject;
        float mSelectLevel = 0.0f;
        boolean mSelected;

        public Object getSelectedItem() {
            return null;
        }

        public Presenter.ViewHolder getSelectedItemViewHolder() {
            return null;
        }

        public ViewHolder(View view) {
            super(view);
            this.mColorDimmer = ColorOverlayDimmer.createDefault(view.getContext());
        }

        public final Row getRow() {
            return this.mRow;
        }

        public final Object getRowObject() {
            return this.mRowObject;
        }

        public final boolean isExpanded() {
            return this.mExpanded;
        }

        public final boolean isSelected() {
            return this.mSelected;
        }

        public final float getSelectLevel() {
            return this.mSelectLevel;
        }

        public final RowHeaderPresenter.ViewHolder getHeaderViewHolder() {
            return this.mHeaderViewHolder;
        }

        public final void setActivated(boolean z) {
            this.mActivated = z ? 1 : 2;
        }

        public final void syncActivatedStatus(View view) {
            int i = this.mActivated;
            if (i == 1) {
                view.setActivated(true);
            } else if (i == 2) {
                view.setActivated(false);
            }
        }

        public void setOnKeyListener(View.OnKeyListener onKeyListener) {
            this.mOnKeyListener = onKeyListener;
        }

        public View.OnKeyListener getOnKeyListener() {
            return this.mOnKeyListener;
        }

        public final void setOnItemViewSelectedListener(BaseOnItemViewSelectedListener baseOnItemViewSelectedListener) {
            this.mOnItemViewSelectedListener = baseOnItemViewSelectedListener;
        }

        public final BaseOnItemViewSelectedListener getOnItemViewSelectedListener() {
            return this.mOnItemViewSelectedListener;
        }

        public final void setOnItemViewClickedListener(BaseOnItemViewClickedListener baseOnItemViewClickedListener) {
            this.mOnItemViewClickedListener = baseOnItemViewClickedListener;
        }

        public final BaseOnItemViewClickedListener getOnItemViewClickedListener() {
            return this.mOnItemViewClickedListener;
        }
    }

    public RowPresenter() {
        RowHeaderPresenter rowHeaderPresenter = new RowHeaderPresenter();
        this.mHeaderPresenter = rowHeaderPresenter;
        rowHeaderPresenter.setNullItemVisibilityGone(true);
    }

    public final Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Presenter.ViewHolder viewHolder;
        ViewHolder createRowViewHolder = createRowViewHolder(viewGroup);
        createRowViewHolder.mInitialzed = false;
        if (needsRowContainerView()) {
            RowContainerView rowContainerView = new RowContainerView(viewGroup.getContext());
            RowHeaderPresenter rowHeaderPresenter = this.mHeaderPresenter;
            if (rowHeaderPresenter != null) {
                createRowViewHolder.mHeaderViewHolder = (RowHeaderPresenter.ViewHolder) rowHeaderPresenter.onCreateViewHolder((ViewGroup) createRowViewHolder.view);
            }
            viewHolder = new ContainerViewHolder(rowContainerView, createRowViewHolder);
        } else {
            viewHolder = createRowViewHolder;
        }
        initializeRowViewHolder(createRowViewHolder);
        if (createRowViewHolder.mInitialzed) {
            return viewHolder;
        }
        throw new RuntimeException("super.initializeRowViewHolder() must be called");
    }

    /* access modifiers changed from: protected */
    public void initializeRowViewHolder(ViewHolder viewHolder) {
        viewHolder.mInitialzed = true;
        if (!isClippingChildren()) {
            if (viewHolder.view instanceof ViewGroup) {
                ((ViewGroup) viewHolder.view).setClipChildren(false);
            }
            if (viewHolder.mContainerViewHolder != null) {
                ((ViewGroup) viewHolder.mContainerViewHolder.view).setClipChildren(false);
            }
        }
    }

    public final void setHeaderPresenter(RowHeaderPresenter rowHeaderPresenter) {
        this.mHeaderPresenter = rowHeaderPresenter;
    }

    public final RowHeaderPresenter getHeaderPresenter() {
        return this.mHeaderPresenter;
    }

    public final ViewHolder getRowViewHolder(Presenter.ViewHolder viewHolder) {
        if (viewHolder instanceof ContainerViewHolder) {
            return ((ContainerViewHolder) viewHolder).mRowViewHolder;
        }
        return (ViewHolder) viewHolder;
    }

    public final void setRowViewExpanded(Presenter.ViewHolder viewHolder, boolean z) {
        ViewHolder rowViewHolder = getRowViewHolder(viewHolder);
        rowViewHolder.mExpanded = z;
        onRowViewExpanded(rowViewHolder, z);
    }

    public final void setRowViewSelected(Presenter.ViewHolder viewHolder, boolean z) {
        ViewHolder rowViewHolder = getRowViewHolder(viewHolder);
        rowViewHolder.mSelected = z;
        onRowViewSelected(rowViewHolder, z);
    }

    /* access modifiers changed from: protected */
    public void onRowViewExpanded(ViewHolder viewHolder, boolean z) {
        updateHeaderViewVisibility(viewHolder);
        updateActivateStatus(viewHolder, viewHolder.view);
    }

    private void updateActivateStatus(ViewHolder viewHolder, View view) {
        int i = this.mSyncActivatePolicy;
        boolean z = true;
        if (i == 1) {
            viewHolder.setActivated(viewHolder.isExpanded());
        } else if (i == 2) {
            viewHolder.setActivated(viewHolder.isSelected());
        } else if (i == 3) {
            if (!viewHolder.isExpanded() || !viewHolder.isSelected()) {
                z = false;
            }
            viewHolder.setActivated(z);
        }
        viewHolder.syncActivatedStatus(view);
    }

    public final void setSyncActivatePolicy(int i) {
        this.mSyncActivatePolicy = i;
    }

    public final int getSyncActivatePolicy() {
        return this.mSyncActivatePolicy;
    }

    /* access modifiers changed from: protected */
    public void dispatchItemSelectedListener(ViewHolder viewHolder, boolean z) {
        if (z && viewHolder.mOnItemViewSelectedListener != null) {
            viewHolder.mOnItemViewSelectedListener.onItemSelected((Presenter.ViewHolder) null, (Object) null, viewHolder, viewHolder.getRowObject());
        }
    }

    /* access modifiers changed from: protected */
    public void onRowViewSelected(ViewHolder viewHolder, boolean z) {
        dispatchItemSelectedListener(viewHolder, z);
        updateHeaderViewVisibility(viewHolder);
        updateActivateStatus(viewHolder, viewHolder.view);
    }

    private void updateHeaderViewVisibility(ViewHolder viewHolder) {
        if (this.mHeaderPresenter != null && viewHolder.mHeaderViewHolder != null) {
            ((RowContainerView) viewHolder.mContainerViewHolder.view).showHeader(viewHolder.isExpanded());
        }
    }

    public final void setSelectLevel(Presenter.ViewHolder viewHolder, float f) {
        ViewHolder rowViewHolder = getRowViewHolder(viewHolder);
        rowViewHolder.mSelectLevel = f;
        onSelectLevelChanged(rowViewHolder);
    }

    public final float getSelectLevel(Presenter.ViewHolder viewHolder) {
        return getRowViewHolder(viewHolder).mSelectLevel;
    }

    /* access modifiers changed from: protected */
    public void onSelectLevelChanged(ViewHolder viewHolder) {
        if (getSelectEffectEnabled()) {
            viewHolder.mColorDimmer.setActiveLevel(viewHolder.mSelectLevel);
            if (viewHolder.mHeaderViewHolder != null) {
                this.mHeaderPresenter.setSelectLevel(viewHolder.mHeaderViewHolder, viewHolder.mSelectLevel);
            }
            if (isUsingDefaultSelectEffect()) {
                ((RowContainerView) viewHolder.mContainerViewHolder.view).setForegroundColor(viewHolder.mColorDimmer.getPaint().getColor());
            }
        }
    }

    public final void setSelectEffectEnabled(boolean z) {
        this.mSelectEffectEnabled = z;
    }

    public final boolean getSelectEffectEnabled() {
        return this.mSelectEffectEnabled;
    }

    /* access modifiers changed from: package-private */
    public final boolean needsDefaultSelectEffect() {
        return isUsingDefaultSelectEffect() && getSelectEffectEnabled();
    }

    /* access modifiers changed from: package-private */
    public final boolean needsRowContainerView() {
        return this.mHeaderPresenter != null || needsDefaultSelectEffect();
    }

    public final void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        onBindRowViewHolder(getRowViewHolder(viewHolder), obj);
    }

    /* access modifiers changed from: protected */
    public void onBindRowViewHolder(ViewHolder viewHolder, Object obj) {
        viewHolder.mRowObject = obj;
        viewHolder.mRow = obj instanceof Row ? (Row) obj : null;
        if (viewHolder.mHeaderViewHolder != null && viewHolder.getRow() != null) {
            this.mHeaderPresenter.onBindViewHolder(viewHolder.mHeaderViewHolder, obj);
        }
    }

    public final void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        onUnbindRowViewHolder(getRowViewHolder(viewHolder));
    }

    /* access modifiers changed from: protected */
    public void onUnbindRowViewHolder(ViewHolder viewHolder) {
        if (viewHolder.mHeaderViewHolder != null) {
            this.mHeaderPresenter.onUnbindViewHolder(viewHolder.mHeaderViewHolder);
        }
        viewHolder.mRow = null;
        viewHolder.mRowObject = null;
    }

    public final void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
        onRowViewAttachedToWindow(getRowViewHolder(viewHolder));
    }

    /* access modifiers changed from: protected */
    public void onRowViewAttachedToWindow(ViewHolder viewHolder) {
        if (viewHolder.mHeaderViewHolder != null) {
            this.mHeaderPresenter.onViewAttachedToWindow(viewHolder.mHeaderViewHolder);
        }
    }

    public final void onViewDetachedFromWindow(Presenter.ViewHolder viewHolder) {
        onRowViewDetachedFromWindow(getRowViewHolder(viewHolder));
    }

    /* access modifiers changed from: protected */
    public void onRowViewDetachedFromWindow(ViewHolder viewHolder) {
        if (viewHolder.mHeaderViewHolder != null) {
            this.mHeaderPresenter.onViewDetachedFromWindow(viewHolder.mHeaderViewHolder);
        }
        cancelAnimationsRecursive(viewHolder.view);
    }

    public void setEntranceTransitionState(ViewHolder viewHolder, boolean z) {
        if (viewHolder.mHeaderViewHolder != null && viewHolder.mHeaderViewHolder.view.getVisibility() != 8) {
            viewHolder.mHeaderViewHolder.view.setVisibility(z ? 0 : 4);
        }
    }
}
