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
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.television.R;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0014\u0010\r\u001a\u00060\u000eR\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/videolan/television/ui/PersonCardPresenter;", "Landroidx/leanback/widget/Presenter;", "context", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "defaultCardImage", "Landroid/graphics/drawable/Drawable;", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "Lorg/videolan/television/ui/PersonCardPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "onViewAttachedToWindow", "Companion", "ViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PersonCardPresenter.kt */
public final class PersonCardPresenter extends Presenter {
    private static final int CARD_HEIGHT_POSTER = KotlinExtensionsKt.getDp(150);
    private static final int CARD_WIDTH_POSTER = KotlinExtensionsKt.getDp(100);
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CardPresenter";
    private final Activity context;
    /* access modifiers changed from: private */
    public Drawable defaultCardImage;

    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    }

    public PersonCardPresenter(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "context");
        this.context = activity;
        this.defaultCardImage = ContextCompat.getDrawable(activity, R.drawable.ic_people_big);
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u0010\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u000e"}, d2 = {"Lorg/videolan/television/ui/PersonCardPresenter$ViewHolder;", "Landroidx/leanback/widget/Presenter$ViewHolder;", "view", "Landroid/view/View;", "(Lorg/videolan/television/ui/PersonCardPresenter;Landroid/view/View;)V", "cardView", "Landroidx/leanback/widget/ImageCardView;", "getCardView", "()Landroidx/leanback/widget/ImageCardView;", "updateCardViewImage", "", "image", "Landroid/graphics/drawable/Drawable;", "Landroid/net/Uri;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PersonCardPresenter.kt */
    public final class ViewHolder extends Presenter.ViewHolder {
        private final ImageCardView cardView;
        final /* synthetic */ PersonCardPresenter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(PersonCardPresenter personCardPresenter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = personCardPresenter;
            ImageCardView imageCardView = (ImageCardView) view;
            this.cardView = imageCardView;
            imageCardView.getMainImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        public final ImageCardView getCardView() {
            return this.cardView;
        }

        public final void updateCardViewImage(Drawable drawable) {
            this.cardView.setMainImage(drawable);
            this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        public final void updateCardViewImage(Uri uri) {
            this.cardView.setMainImage(this.this$0.defaultCardImage);
            this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
            ImageView mainImageView = this.cardView.getMainImageView();
            Intrinsics.checkNotNullExpressionValue(mainImageView, "getMainImageView(...)");
            ImageLoaderKt.downloadIcon$default((View) mainImageView, uri, false, 4, (Object) null);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ImageCardView imageCardView = new ImageCardView(new ContextThemeWrapper((Context) this.context, R.style.VLCImageCardViewTitleOnly));
        imageCardView.setFocusable(true);
        imageCardView.setFocusableInTouchMode(true);
        imageCardView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.lb_details_overview_bg_color));
        imageCardView.setMainImageDimensions(CARD_WIDTH_POSTER, CARD_HEIGHT_POSTER);
        return new ViewHolder(this, imageCardView);
    }

    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        Unit unit;
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(obj, "item");
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        Person person = (Person) obj;
        viewHolder2.getCardView().setTitleText(person.getName());
        String image = person.getImage();
        if (image != null) {
            viewHolder2.updateCardViewImage(Uri.parse(image));
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            viewHolder2.updateCardViewImage(this.defaultCardImage);
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/videolan/television/ui/PersonCardPresenter$Companion;", "", "()V", "CARD_HEIGHT_POSTER", "", "CARD_WIDTH_POSTER", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PersonCardPresenter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
