package org.videolan.television.ui;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.content.ContextCompat;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaImage;
import org.videolan.moviepedia.database.models.MediaImageType;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.television.R;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0015\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0014\u0010\u000f\u001a\u00060\u0010R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\u0014\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/television/ui/MediaImageCardPresenter;", "Landroidx/leanback/widget/Presenter;", "context", "Landroid/app/Activity;", "imageType", "Lorg/videolan/moviepedia/database/models/MediaImageType;", "(Landroid/app/Activity;Lorg/videolan/moviepedia/database/models/MediaImageType;)V", "defaultCardImage", "Landroid/graphics/drawable/Drawable;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "Lorg/videolan/television/ui/MediaImageCardPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "onViewAttachedToWindow", "Companion", "ViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaImageCardPresenter.kt */
public final class MediaImageCardPresenter extends Presenter {
    private static final int CARD_HEIGHT_BACKDROP = KotlinExtensionsKt.getDp(120);
    private static final int CARD_HEIGHT_POSTER = KotlinExtensionsKt.getDp(150);
    private static final int CARD_WIDTH_BACKDROP = KotlinExtensionsKt.getDp(213);
    private static final int CARD_WIDTH_POSTER = KotlinExtensionsKt.getDp(100);
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CardPresenter";
    private final Activity context;
    /* access modifiers changed from: private */
    public Drawable defaultCardImage;
    private final MediaImageType imageType;

    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    }

    public MediaImageCardPresenter(Activity activity, MediaImageType mediaImageType) {
        Intrinsics.checkNotNullParameter(activity, "context");
        Intrinsics.checkNotNullParameter(mediaImageType, "imageType");
        this.context = activity;
        this.imageType = mediaImageType;
        this.defaultCardImage = ContextCompat.getDrawable(activity, R.drawable.ic_people_big);
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u000bJ\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/videolan/television/ui/MediaImageCardPresenter$ViewHolder;", "Landroidx/leanback/widget/Presenter$ViewHolder;", "view", "Landroid/view/View;", "(Lorg/videolan/television/ui/MediaImageCardPresenter;Landroid/view/View;)V", "cardView", "Landroidx/leanback/widget/ImageCardView;", "updateCardViewImage", "", "image", "Landroid/graphics/drawable/Drawable;", "Landroid/net/Uri;", "item", "Lorg/videolan/moviepedia/database/models/Person;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaImageCardPresenter.kt */
    public final class ViewHolder extends Presenter.ViewHolder {
        private final ImageCardView cardView;
        final /* synthetic */ MediaImageCardPresenter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(MediaImageCardPresenter mediaImageCardPresenter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = mediaImageCardPresenter;
            ImageCardView imageCardView = (ImageCardView) view;
            this.cardView = imageCardView;
            imageCardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        public final void updateCardViewImage(Person person) {
            Uri uri;
            Intrinsics.checkNotNullParameter(person, "item");
            ImageView mainImageView = this.cardView.getMainImageView();
            Intrinsics.checkNotNullExpressionValue(mainImageView, "getMainImageView(...)");
            View view = mainImageView;
            String image = person.getImage();
            if (image != null) {
                uri = Uri.parse(image);
            } else {
                uri = null;
            }
            ImageLoaderKt.downloadIcon$default(view, uri, false, 4, (Object) null);
        }

        public final void updateCardViewImage(Drawable drawable) {
            this.cardView.setMainImage(drawable);
            this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        public final void updateCardViewImage(Uri uri) {
            this.cardView.setMainImage(this.this$0.defaultCardImage);
            this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
            ImageView mainImageView = this.cardView.getMainImageView();
            Intrinsics.checkNotNullExpressionValue(mainImageView, "getMainImageView(...)");
            ImageLoaderKt.downloadIcon$default((View) mainImageView, uri, false, 4, (Object) null);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ImageCardView imageCardView = new ImageCardView(new ContextThemeWrapper((Context) this.context, R.style.VLCImageCardViewNoTitle));
        imageCardView.setFocusable(true);
        imageCardView.setFocusableInTouchMode(true);
        imageCardView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.lb_details_overview_bg_color));
        imageCardView.setMainImageDimensions(this.imageType == MediaImageType.POSTER ? CARD_WIDTH_POSTER : CARD_WIDTH_BACKDROP, this.imageType == MediaImageType.POSTER ? CARD_HEIGHT_POSTER : CARD_HEIGHT_BACKDROP);
        return new ViewHolder(this, imageCardView);
    }

    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(obj, "item");
        ((ViewHolder) viewHolder).updateCardViewImage(Uri.parse(((MediaImage) obj).getUrl()));
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/MediaImageCardPresenter$Companion;", "", "()V", "CARD_HEIGHT_BACKDROP", "", "CARD_HEIGHT_POSTER", "CARD_WIDTH_BACKDROP", "CARD_WIDTH_POSTER", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaImageCardPresenter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
