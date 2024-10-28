package androidx.leanback.widget;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.leanback.R;
import androidx.leanback.widget.Presenter;

public class RowHeaderPresenter extends Presenter {
    private final boolean mAnimateSelect;
    private final Paint mFontMeasurePaint;
    private final int mLayoutResourceId;
    private boolean mNullItemVisibilityGone;

    public RowHeaderPresenter() {
        this(R.layout.lb_row_header);
    }

    public RowHeaderPresenter(int i) {
        this(i, true);
    }

    public RowHeaderPresenter(int i, boolean z) {
        this.mFontMeasurePaint = new Paint(1);
        this.mLayoutResourceId = i;
        this.mAnimateSelect = z;
    }

    public void setNullItemVisibilityGone(boolean z) {
        this.mNullItemVisibilityGone = z;
    }

    public boolean isNullItemVisibilityGone() {
        return this.mNullItemVisibilityGone;
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        TextView mDescriptionView;
        int mOriginalTextColor;
        float mSelectLevel;
        RowHeaderView mTitleView;
        float mUnselectAlpha;

        public ViewHolder(View view) {
            super(view);
            this.mTitleView = (RowHeaderView) view.findViewById(R.id.row_header);
            this.mDescriptionView = (TextView) view.findViewById(R.id.row_header_description);
            initColors();
        }

        public ViewHolder(RowHeaderView rowHeaderView) {
            super(rowHeaderView);
            this.mTitleView = rowHeaderView;
            initColors();
        }

        /* access modifiers changed from: package-private */
        public void initColors() {
            RowHeaderView rowHeaderView = this.mTitleView;
            if (rowHeaderView != null) {
                this.mOriginalTextColor = rowHeaderView.getCurrentTextColor();
            }
            this.mUnselectAlpha = this.view.getResources().getFraction(R.fraction.lb_browse_header_unselect_alpha, 1, 1);
        }

        public final float getSelectLevel() {
            return this.mSelectLevel;
        }
    }

    public Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.mLayoutResourceId, viewGroup, false));
        if (this.mAnimateSelect) {
            setSelectLevel(viewHolder, 0.0f);
        }
        return viewHolder;
    }

    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        HeaderItem headerItem = obj == null ? null : ((Row) obj).getHeaderItem();
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        if (headerItem == null) {
            if (viewHolder2.mTitleView != null) {
                viewHolder2.mTitleView.setText((CharSequence) null);
            }
            if (viewHolder2.mDescriptionView != null) {
                viewHolder2.mDescriptionView.setText((CharSequence) null);
            }
            viewHolder.view.setContentDescription((CharSequence) null);
            if (this.mNullItemVisibilityGone) {
                viewHolder.view.setVisibility(8);
                return;
            }
            return;
        }
        if (viewHolder2.mTitleView != null) {
            viewHolder2.mTitleView.setText(headerItem.getName());
        }
        if (viewHolder2.mDescriptionView != null) {
            if (TextUtils.isEmpty(headerItem.getDescription())) {
                viewHolder2.mDescriptionView.setVisibility(8);
            } else {
                viewHolder2.mDescriptionView.setVisibility(0);
            }
            viewHolder2.mDescriptionView.setText(headerItem.getDescription());
        }
        viewHolder.view.setContentDescription(headerItem.getContentDescription());
        viewHolder.view.setVisibility(0);
    }

    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        if (viewHolder2.mTitleView != null) {
            viewHolder2.mTitleView.setText((CharSequence) null);
        }
        if (viewHolder2.mDescriptionView != null) {
            viewHolder2.mDescriptionView.setText((CharSequence) null);
        }
        if (this.mAnimateSelect) {
            setSelectLevel(viewHolder2, 0.0f);
        }
    }

    public final void setSelectLevel(ViewHolder viewHolder, float f) {
        viewHolder.mSelectLevel = f;
        onSelectLevelChanged(viewHolder);
    }

    /* access modifiers changed from: protected */
    public void onSelectLevelChanged(ViewHolder viewHolder) {
        if (this.mAnimateSelect) {
            viewHolder.view.setAlpha(viewHolder.mUnselectAlpha + (viewHolder.mSelectLevel * (1.0f - viewHolder.mUnselectAlpha)));
        }
    }

    public int getSpaceUnderBaseline(ViewHolder viewHolder) {
        int paddingBottom = viewHolder.view.getPaddingBottom();
        return viewHolder.view instanceof TextView ? paddingBottom + ((int) getFontDescent((TextView) viewHolder.view, this.mFontMeasurePaint)) : paddingBottom;
    }

    protected static float getFontDescent(TextView textView, Paint paint) {
        if (paint.getTextSize() != textView.getTextSize()) {
            paint.setTextSize(textView.getTextSize());
        }
        if (paint.getTypeface() != textView.getTypeface()) {
            paint.setTypeface(textView.getTypeface());
        }
        return paint.descent();
    }
}
