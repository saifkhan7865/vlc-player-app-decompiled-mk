package org.videolan.television.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.BitmapUtilKt;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lorg/videolan/television/ui/GenericCardPresenter;", "Landroidx/leanback/widget/Presenter;", "context", "Landroid/content/Context;", "cardThemeResId", "", "(Landroid/content/Context;I)V", "Landroid/view/ContextThemeWrapper;", "getContext", "()Landroid/view/ContextThemeWrapper;", "padding", "getPadding", "()I", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: GenericCardPresenter.kt */
public final class GenericCardPresenter extends Presenter {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "VLC/CardPresenter";
    private final ContextThemeWrapper context;
    private final int padding;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public GenericCardPresenter(Context context2) {
        this(context2, 0, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(context2, "context");
    }

    public GenericCardPresenter(Context context2, int i) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = new ContextThemeWrapper(context2, i);
        this.padding = (int) context2.getResources().getDimension(R.dimen.tv_card_padding);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ GenericCardPresenter(Context context2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(context2, (i2 & 2) != 0 ? R.style.VLCGenericCardView : i);
    }

    public final ContextThemeWrapper getContext() {
        return this.context;
    }

    public final int getPadding() {
        return this.padding;
    }

    public Presenter.ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        Log.d(TAG, "onCreateViewHolder");
        ImageCardView imageCardView = new ImageCardView(this.context);
        imageCardView.setFocusable(true);
        imageCardView.setFocusableInTouchMode(true);
        return new Presenter.ViewHolder(imageCardView);
    }

    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(obj, "item");
        if (obj instanceof GenericCardItem) {
            View view = viewHolder.view;
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type androidx.leanback.widget.ImageCardView");
            ImageCardView imageCardView = (ImageCardView) view;
            GenericCardItem genericCardItem = (GenericCardItem) obj;
            int color = ContextCompat.getColor(this.context, genericCardItem.getColor());
            imageCardView.setInfoAreaBackgroundColor(color);
            imageCardView.getMainImageView().setImageBitmap(BitmapUtilKt.getBitmapFromDrawable$default(this.context, genericCardItem.getIcon(), 0, 0, 6, (Object) null));
            ImageView mainImageView = imageCardView.getMainImageView();
            int i = this.padding;
            mainImageView.setPadding(i, i, i, i);
            imageCardView.setTitleText(genericCardItem.getTitle());
            imageCardView.setContentText(genericCardItem.getContent());
            imageCardView.setBackgroundColor(color);
            imageCardView.findViewById(R.id.info_field).setBackgroundColor(color);
        }
    }

    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Log.d(TAG, "onUnbindViewHolder");
        View view = viewHolder.view;
        Intrinsics.checkNotNull(view, "null cannot be cast to non-null type androidx.leanback.widget.ImageCardView");
        ImageCardView imageCardView = (ImageCardView) view;
        imageCardView.setBadgeImage((Drawable) null);
        imageCardView.setMainImage((Drawable) null);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/television/ui/GenericCardPresenter$Companion;", "", "()V", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: GenericCardPresenter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
