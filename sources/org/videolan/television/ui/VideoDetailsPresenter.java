package org.videolan.television.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.leanback.graphics.ColorOverlayDimmer;
import androidx.leanback.widget.RowPresenter;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.television.R;
import org.videolan.television.databinding.TvVideoDetailsBinding;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u00060\nR\u00020\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0014J\u001a\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0010H\u0014R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lorg/videolan/television/ui/VideoDetailsPresenter;", "Lorg/videolan/television/ui/FullWidthRowPresenter;", "context", "Landroid/content/Context;", "screenWidth", "", "(Landroid/content/Context;I)V", "binding", "Lorg/videolan/television/databinding/TvVideoDetailsBinding;", "createRowViewHolder", "Lorg/videolan/television/ui/VideoDetailsPresenter$VideoDetailsViewHolder;", "parent", "Landroid/view/ViewGroup;", "onBindRowViewHolder", "", "viewHolder", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "item", "", "onSelectLevelChanged", "holder", "VideoDetailsViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: VideoDetailsPresenter.kt */
public final class VideoDetailsPresenter extends FullWidthRowPresenter {
    private TvVideoDetailsBinding binding;
    private final Context context;
    private final int screenWidth;

    /* access modifiers changed from: protected */
    public void onSelectLevelChanged(RowPresenter.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
    }

    public VideoDetailsPresenter(Context context2, int i) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        this.screenWidth = i;
    }

    /* access modifiers changed from: protected */
    public VideoDetailsViewHolder createRowViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.tv_video_details, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        TvVideoDetailsBinding tvVideoDetailsBinding = (TvVideoDetailsBinding) inflate;
        this.binding = tvVideoDetailsBinding;
        TvVideoDetailsBinding tvVideoDetailsBinding2 = null;
        if (tvVideoDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvVideoDetailsBinding = null;
        }
        tvVideoDetailsBinding.container.setMinWidth((int) (((float) this.screenWidth) - (this.context.getResources().getDimension(R.dimen.tv_overscan_horizontal) * ((float) 2))));
        TvVideoDetailsBinding tvVideoDetailsBinding3 = this.binding;
        if (tvVideoDetailsBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            tvVideoDetailsBinding2 = tvVideoDetailsBinding3;
        }
        View root = tvVideoDetailsBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return new VideoDetailsViewHolder(this, root);
    }

    /* access modifiers changed from: protected */
    public void onBindRowViewHolder(RowPresenter.ViewHolder viewHolder, Object obj) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        super.onBindRowViewHolder(viewHolder, obj);
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type org.videolan.television.ui.VideoDetailsOverviewRow");
        MediaMetadata item = ((VideoDetailsOverviewRow) obj).getItem();
        TvVideoDetailsBinding tvVideoDetailsBinding = this.binding;
        if (tvVideoDetailsBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            tvVideoDetailsBinding = null;
        }
        tvVideoDetailsBinding.setItem(item);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0019\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n \u0007*\u0004\u0018\u00010\u000b0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/videolan/television/ui/VideoDetailsPresenter$VideoDetailsViewHolder;", "Landroidx/leanback/widget/RowPresenter$ViewHolder;", "view", "Landroid/view/View;", "(Lorg/videolan/television/ui/VideoDetailsPresenter;Landroid/view/View;)V", "colorDimmer", "Landroidx/leanback/graphics/ColorOverlayDimmer;", "kotlin.jvm.PlatformType", "getColorDimmer", "()Landroidx/leanback/graphics/ColorOverlayDimmer;", "container", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getContainer", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: VideoDetailsPresenter.kt */
    public final class VideoDetailsViewHolder extends RowPresenter.ViewHolder {
        private final ColorOverlayDimmer colorDimmer;
        private final ConstraintLayout container;
        final /* synthetic */ VideoDetailsPresenter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public VideoDetailsViewHolder(VideoDetailsPresenter videoDetailsPresenter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = videoDetailsPresenter;
            this.colorDimmer = ColorOverlayDimmer.createDefault(view.getContext());
            this.container = (ConstraintLayout) view.findViewById(R.id.container);
        }

        public final ColorOverlayDimmer getColorDimmer() {
            return this.colorDimmer;
        }

        public final ConstraintLayout getContainer() {
            return this.container;
        }
    }
}
