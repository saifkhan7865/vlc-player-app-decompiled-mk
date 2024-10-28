package org.videolan.television.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.leanback.widget.RowPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.television.R;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001:\u0001\tB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0014J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/FullWidthRowPresenter;", "Landroidx/leanback/widget/RowPresenter;", "()V", "createRowViewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "isUsingDefaultSelectEffect", "", "FullWidthRowPresenterViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FullWidthRowPresenter.kt */
public class FullWidthRowPresenter extends RowPresenter {
    public boolean isUsingDefaultSelectEffect() {
        return true;
    }

    public FullWidthRowPresenter() {
        setSelectEffectEnabled(false);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/television/ui/FullWidthRowPresenter$FullWidthRowPresenterViewHolder;", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "view", "Landroid/view/View;", "(Lorg/videolan/television/ui/FullWidthRowPresenter;Landroid/view/View;)V", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: FullWidthRowPresenter.kt */
    public class FullWidthRowPresenterViewHolder extends RowPresenter.ViewHolder {
        final /* synthetic */ FullWidthRowPresenter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FullWidthRowPresenterViewHolder(FullWidthRowPresenter fullWidthRowPresenter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = fullWidthRowPresenter;
        }
    }

    /* access modifiers changed from: protected */
    public RowPresenter.ViewHolder createRowViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_description_row, viewGroup, false);
        Intrinsics.checkNotNull(inflate);
        return new FullWidthRowPresenterViewHolder(this, inflate);
    }
}
