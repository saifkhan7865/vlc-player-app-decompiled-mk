package org.videolan.television.ui;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.leanback.widget.Presenter;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.database.models.MediaMetadataKt;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.television.R;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u00060\u000eR\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/television/ui/MetadataCardPresenter;", "Landroidx/leanback/widget/Presenter;", "context", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "defaultCardImage", "Landroid/graphics/drawable/Drawable;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "Lorg/videolan/television/ui/MetadataCardPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "onViewAttachedToWindow", "Companion", "ViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MetadataCardPresenter.kt */
public final class MetadataCardPresenter extends Presenter {
    private static final int CARD_HEIGHT_POSTER = KotlinExtensionsKt.getDp(150);
    private static final int CARD_WIDTH_POSTER = KotlinExtensionsKt.getDp(100);
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CardPresenter";
    /* access modifiers changed from: private */
    public final Activity context;
    /* access modifiers changed from: private */
    public Drawable defaultCardImage;

    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    }

    public MetadataCardPresenter(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "context");
        this.context = activity;
        this.defaultCardImage = VectorDrawableCompat.create(activity.getResources(), R.drawable.ic_people_big, activity.getTheme());
    }

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aJ\u0010\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001bJ\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u001dR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0011\u0010\u0013\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010¨\u0006\u001e"}, d2 = {"Lorg/videolan/television/ui/MetadataCardPresenter$ViewHolder;", "Landroidx/leanback/widget/Presenter$ViewHolder;", "view", "Landroid/view/View;", "(Lorg/videolan/television/ui/MetadataCardPresenter;Landroid/view/View;)V", "cover", "Landroid/widget/ImageView;", "getCover", "()Landroid/widget/ImageView;", "progress", "Landroid/widget/ProgressBar;", "getProgress", "()Landroid/widget/ProgressBar;", "resolution", "Landroid/widget/TextView;", "getResolution", "()Landroid/widget/TextView;", "seenBadge", "getSeenBadge", "subtitle", "getSubtitle", "title", "getTitle", "updateCardViewImage", "", "image", "Landroid/graphics/drawable/Drawable;", "Landroid/net/Uri;", "item", "Lorg/videolan/moviepedia/database/models/Person;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MetadataCardPresenter.kt */
    public final class ViewHolder extends Presenter.ViewHolder {
        private final ImageView cover;
        private final ProgressBar progress;
        private final TextView resolution;
        private final ImageView seenBadge;
        private final TextView subtitle;
        final /* synthetic */ MetadataCardPresenter this$0;
        private final TextView title;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(MetadataCardPresenter metadataCardPresenter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = metadataCardPresenter;
            View findViewById = view.findViewById(R.id.media_cover);
            Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(...)");
            ImageView imageView = (ImageView) findViewById;
            this.cover = imageView;
            View findViewById2 = view.findViewById(R.id.title);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(...)");
            this.title = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.ml_item_seen);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "findViewById(...)");
            this.seenBadge = (ImageView) findViewById3;
            View findViewById4 = view.findViewById(R.id.subtitle);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "findViewById(...)");
            this.subtitle = (TextView) findViewById4;
            View findViewById5 = view.findViewById(R.id.badgeTV);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "findViewById(...)");
            this.resolution = (TextView) findViewById5;
            View findViewById6 = view.findViewById(R.id.progressBar);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "findViewById(...)");
            this.progress = (ProgressBar) findViewById6;
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            view.findViewById(R.id.container).setBackground(ContextCompat.getDrawable(metadataCardPresenter.context, R.drawable.tv_card_background));
        }

        public final ImageView getCover() {
            return this.cover;
        }

        public final TextView getTitle() {
            return this.title;
        }

        public final ImageView getSeenBadge() {
            return this.seenBadge;
        }

        public final TextView getSubtitle() {
            return this.subtitle;
        }

        public final TextView getResolution() {
            return this.resolution;
        }

        public final ProgressBar getProgress() {
            return this.progress;
        }

        public final void updateCardViewImage(Person person) {
            Uri uri;
            Intrinsics.checkNotNullParameter(person, "item");
            View view = this.cover;
            String image = person.getImage();
            if (image != null) {
                uri = Uri.parse(image);
            } else {
                uri = null;
            }
            ImageLoaderKt.downloadIcon$default(view, uri, false, 4, (Object) null);
        }

        public final void updateCardViewImage(Drawable drawable) {
            this.cover.setImageDrawable(drawable);
            this.cover.setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        public final void updateCardViewImage(Uri uri) {
            this.cover.setImageDrawable(this.this$0.defaultCardImage);
            this.cover.setScaleType(ImageView.ScaleType.FIT_CENTER);
            ImageLoaderKt.downloadIcon$default((View) this.cover, uri, false, 4, (Object) null);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = this.context.getLayoutInflater().inflate(R.layout.movie_browser_tv_item, viewGroup, false);
        inflate.setFocusable(true);
        inflate.setFocusableInTouchMode(true);
        inflate.setBackgroundColor(ContextCompat.getColor(this.context, R.color.lb_details_overview_bg_color));
        ViewGroup.LayoutParams layoutParams = ((ImageView) inflate.findViewById(R.id.media_cover)).getLayoutParams();
        layoutParams.width = CARD_WIDTH_POSTER;
        int i = CARD_HEIGHT_POSTER;
        layoutParams.height = i;
        inflate.findViewById(R.id.container).getLayoutParams().height = i + KotlinExtensionsKt.getDp(64);
        Intrinsics.checkNotNull(inflate);
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(obj, "item");
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        MediaMetadataWithImages mediaMetadataWithImages = (MediaMetadataWithImages) obj;
        viewHolder2.getTitle().setText(mediaMetadataWithImages.getMetadata().getTitle());
        viewHolder2.getSubtitle().setText(MediaMetadataKt.tvEpisodeSubtitle(mediaMetadataWithImages));
        viewHolder2.updateCardViewImage(Uri.parse(mediaMetadataWithImages.getMetadata().getCurrentPoster()));
        MediaWrapper media = mediaMetadataWithImages.getMedia();
        if (media != null) {
            viewHolder2.getResolution().setText(KextensionsKt.generateResolutionClass(media.getWidth(), media.getHeight()));
        }
        int i3 = 8;
        int i4 = 0;
        viewHolder2.getResolution().setVisibility(mediaMetadataWithImages.getMedia() != null ? 0 : 8);
        ImageView seenBadge = viewHolder2.getSeenBadge();
        MediaWrapper media2 = mediaMetadataWithImages.getMedia();
        Long l = null;
        if ((media2 != null ? Long.valueOf(media2.getSeen()) : null) != null) {
            MediaWrapper media3 = mediaMetadataWithImages.getMedia();
            if (media3 != null) {
                l = Long.valueOf(media3.getSeen());
            }
            Intrinsics.checkNotNull(l);
            if (l.longValue() > 0) {
                i3 = 0;
            }
        }
        seenBadge.setVisibility(i3);
        MediaWrapper media4 = mediaMetadataWithImages.getMedia();
        if (media4 != null) {
            if (media4.getLength() > 0) {
                long displayTime = media4.getDisplayTime();
                if (displayTime > 0) {
                    long j = (long) 1000;
                    i2 = (int) (media4.getLength() / j);
                    i4 = (int) (displayTime / j);
                    int i5 = i4;
                    i4 = i2;
                    i = i5;
                }
            }
            i2 = 0;
            int i52 = i4;
            i4 = i2;
            i = i52;
        } else {
            i = 0;
        }
        viewHolder2.getProgress().setMax(i4);
        viewHolder2.getProgress().setProgress(i);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/videolan/television/ui/MetadataCardPresenter$Companion;", "", "()V", "CARD_HEIGHT_POSTER", "", "CARD_WIDTH_POSTER", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MetadataCardPresenter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
