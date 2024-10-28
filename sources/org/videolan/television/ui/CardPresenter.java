package org.videolan.television.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.leanback.widget.ImageCardView;
import androidx.leanback.widget.Presenter;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.helpers.ImageLoaderKt;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 #2\u00020\u0001:\u0002#$B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J(\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u000e\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u001cH\u0016J\u0014\u0010\u001d\u001a\u00060\u001eR\u00020\u00002\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0012\u0010\"\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8BX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0010\u001a\u0004\u0018\u00010\u000f8BX\u0002¢\u0006\f\n\u0004\b\u0013\u0010\r\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0014\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lorg/videolan/television/ui/CardPresenter;", "Landroidx/leanback/widget/Presenter;", "context", "Landroid/app/Activity;", "isPoster", "", "fromHistory", "(Landroid/app/Activity;ZZ)V", "imageDefaultWidth", "", "getImageDefaultWidth", "()F", "imageDefaultWidth$delegate", "Lkotlin/Lazy;", "sDefaultCardImage", "Landroid/graphics/drawable/Drawable;", "seenDrawable", "getSeenDrawable", "()Landroid/graphics/drawable/Drawable;", "seenDrawable$delegate", "seenMediaMarkerVisible", "onBindViewHolder", "", "viewHolder", "Landroidx/leanback/widget/Presenter$ViewHolder;", "item", "", "payloads", "", "onCreateViewHolder", "Lorg/videolan/television/ui/CardPresenter$ViewHolder;", "parent", "Landroid/view/ViewGroup;", "onUnbindViewHolder", "onViewAttachedToWindow", "Companion", "ViewHolder", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CardPresenter.kt */
public final class CardPresenter extends Presenter {
    private static final int CARD_HEIGHT = AppContextProvider.INSTANCE.getAppResources().getDimensionPixelSize(R.dimen.tv_grid_card_thumb_height);
    private static final int CARD_HEIGHT_POSTER = KotlinExtensionsKt.getDp(285);
    private static final int CARD_WIDTH = AppContextProvider.INSTANCE.getAppResources().getDimensionPixelSize(R.dimen.tv_grid_card_thumb_width);
    private static final int CARD_WIDTH_POSTER = KotlinExtensionsKt.getDp(190);
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String TAG = "CardPresenter";
    /* access modifiers changed from: private */
    public final Activity context;
    private final boolean fromHistory;
    private final Lazy imageDefaultWidth$delegate;
    private final boolean isPoster;
    private Drawable sDefaultCardImage;
    private final Lazy seenDrawable$delegate;
    private boolean seenMediaMarkerVisible;

    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
    }

    public void onViewAttachedToWindow(Presenter.ViewHolder viewHolder) {
    }

    public CardPresenter(Activity activity, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(activity, "context");
        this.context = activity;
        this.isPoster = z;
        this.fromHistory = z2;
        this.seenMediaMarkerVisible = true;
        this.sDefaultCardImage = VectorDrawableCompat.create(activity.getResources(), R.drawable.ic_default_cone, activity.getTheme());
        this.imageDefaultWidth$delegate = LazyKt.lazy(new CardPresenter$imageDefaultWidth$2(this));
        this.seenDrawable$delegate = LazyKt.lazy(new CardPresenter$seenDrawable$2(this));
        this.seenMediaMarkerVisible = ((SharedPreferences) Settings.INSTANCE.getInstance(activity)).getBoolean("media_seen", true);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ CardPresenter(Activity activity, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
    }

    /* access modifiers changed from: private */
    public final float getImageDefaultWidth() {
        return ((Number) this.imageDefaultWidth$delegate.getValue()).floatValue();
    }

    private final Drawable getSeenDrawable() {
        return (Drawable) this.seenDrawable$delegate.getValue();
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0011J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/videolan/television/ui/CardPresenter$ViewHolder;", "Landroidx/leanback/widget/Presenter$ViewHolder;", "view", "Landroid/view/View;", "(Lorg/videolan/television/ui/CardPresenter;Landroid/view/View;)V", "cardView", "Landroidx/leanback/widget/ImageCardView;", "getCardView", "()Landroidx/leanback/widget/ImageCardView;", "getDefaultImage", "Landroid/graphics/Bitmap;", "mediaLibraryItem", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "updateCardViewImage", "", "image", "Landroid/graphics/drawable/Drawable;", "Landroid/net/Uri;", "item", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CardPresenter.kt */
    public final class ViewHolder extends Presenter.ViewHolder {
        private final ImageCardView cardView;
        final /* synthetic */ CardPresenter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(CardPresenter cardPresenter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.this$0 = cardPresenter;
            ImageCardView imageCardView = (ImageCardView) view;
            this.cardView = imageCardView;
            imageCardView.getMainImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        public final ImageCardView getCardView() {
            return this.cardView;
        }

        public final void updateCardViewImage(MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            CharSequence artworkMrl = mediaLibraryItem.getArtworkMrl();
            boolean z = false;
            boolean z2 = artworkMrl == null || artworkMrl.length() == 0;
            if (mediaLibraryItem instanceof MediaWrapper) {
                MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
                if (mediaWrapper.hasFlag(1000)) {
                    ImageCardView imageCardView = this.cardView;
                    imageCardView.setBadgeImage(ContextCompat.getDrawable(imageCardView.getContext(), R.drawable.ic_favorite_tv_badge));
                }
                boolean z3 = mediaWrapper.getType() == 3;
                if (mediaWrapper.getType() == 0) {
                    z = true;
                }
                if (!z3 && z && !mediaWrapper.isThumbnailGenerated()) {
                    if (z2) {
                        this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        this.cardView.setMainImage(new BitmapDrawable(this.cardView.getResources(), getDefaultImage(mediaLibraryItem)));
                    }
                    ImageLoaderKt.loadImage$default(this.cardView, mediaLibraryItem, 0, false, false, 28, (Object) null);
                    return;
                }
            }
            if (mediaLibraryItem.getItemType() == 16) {
                this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
                ImageView mainImageView = this.cardView.getMainImageView();
                Intrinsics.checkNotNullExpressionValue(mainImageView, "getMainImageView(...)");
                ImageLoaderKt.loadPlaylistImageWithWidth(mainImageView, mediaLibraryItem, (int) this.this$0.getImageDefaultWidth(), true);
            } else if (z2) {
                this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                this.cardView.setMainImage(new BitmapDrawable(this.cardView.getResources(), getDefaultImage(mediaLibraryItem)));
            } else {
                ImageLoaderKt.loadImage$default(this.cardView, mediaLibraryItem, 0, false, false, 28, (Object) null);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:10:0x006b  */
        /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final android.graphics.Bitmap getDefaultImage(org.videolan.medialibrary.media.MediaLibraryItem r8) {
            /*
                r7 = this;
                androidx.leanback.widget.ImageCardView r0 = r7.cardView
                android.content.res.Resources r0 = r0.getResources()
                int r1 = r8.getItemType()
                r2 = 32
                if (r1 != r2) goto L_0x0055
                java.lang.String r1 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
                kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r1)
                r1 = r8
                org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
                int r2 = r1.getType()
                r3 = 3
                if (r2 != r3) goto L_0x0055
                android.net.Uri r0 = r1.getUri()
                java.lang.String r0 = r0.getScheme()
                java.lang.String r1 = "file"
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r1)
                if (r0 == 0) goto L_0x0041
                org.videolan.television.ui.CardPresenter r0 = r7.this$0
                android.app.Activity r0 = r0.context
                r1 = r0
                android.content.Context r1 = (android.content.Context) r1
                int r2 = org.videolan.vlc.R.drawable.ic_folder_big
                r5 = 6
                r6 = 0
                r3 = 0
                r4 = 0
                android.graphics.Bitmap r0 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r1, r2, r3, r4, r5, r6)
                goto L_0x0069
            L_0x0041:
                org.videolan.television.ui.CardPresenter r0 = r7.this$0
                android.app.Activity r0 = r0.context
                r1 = r0
                android.content.Context r1 = (android.content.Context) r1
                int r2 = org.videolan.vlc.R.drawable.ic_network_big
                r5 = 6
                r6 = 0
                r3 = 0
                r4 = 0
                android.graphics.Bitmap r0 = org.videolan.vlc.gui.helpers.BitmapUtilKt.getBitmapFromDrawable$default(r1, r2, r3, r4, r5, r6)
                goto L_0x0069
            L_0x0055:
                org.videolan.vlc.gui.helpers.AudioUtil r1 = org.videolan.vlc.gui.helpers.AudioUtil.INSTANCE
                java.lang.String r2 = r8.getArtworkMrl()
                java.lang.String r2 = android.net.Uri.decode(r2)
                int r3 = org.videolan.vlc.R.dimen.tv_grid_card_thumb_width
                int r0 = r0.getDimensionPixelSize(r3)
                android.graphics.Bitmap r0 = r1.readCoverBitmap(r2, r0)
            L_0x0069:
                if (r0 != 0) goto L_0x007b
                org.videolan.television.ui.CardPresenter r0 = r7.this$0
                android.app.Activity r0 = r0.context
                android.content.Context r0 = (android.content.Context) r0
                int r8 = org.videolan.vlc.gui.helpers.UiToolsKt.getTvIconRes(r8)
                android.graphics.Bitmap r0 = org.videolan.vlc.gui.helpers.ImageLoaderKt.getBitmapFromDrawable(r0, r8)
            L_0x007b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.CardPresenter.ViewHolder.getDefaultImage(org.videolan.medialibrary.media.MediaLibraryItem):android.graphics.Bitmap");
        }

        public final void updateCardViewImage(Drawable drawable) {
            this.cardView.setMainImage(drawable);
            this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
        }

        public final void updateCardViewImage(Uri uri) {
            this.cardView.setMainImage(new BitmapDrawable(this.cardView.getResources(), ImageLoaderKt.getBitmapFromDrawable(this.this$0.context, R.drawable.ic_video_big)));
            this.cardView.getMainImageView().setScaleType(ImageView.ScaleType.FIT_CENTER);
            ImageView mainImageView = this.cardView.getMainImageView();
            Intrinsics.checkNotNullExpressionValue(mainImageView, "getMainImageView(...)");
            ImageLoaderKt.downloadIcon$default((View) mainImageView, uri, false, 4, (Object) null);
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        ImageCardView imageCardView = new ImageCardView(this.context);
        imageCardView.setFocusable(true);
        imageCardView.setFocusableInTouchMode(true);
        imageCardView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.lb_details_overview_bg_color));
        if (this.isPoster) {
            imageCardView.setMainImageDimensions(CARD_WIDTH_POSTER, CARD_HEIGHT_POSTER);
        } else {
            imageCardView.setMainImageDimensions(CARD_WIDTH, CARD_HEIGHT);
        }
        return new ViewHolder(this, imageCardView);
    }

    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(obj, "item");
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        if (obj instanceof MediaWrapper) {
            MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) obj;
            Tools.setMediaDescription(mediaLibraryItem);
            MediaWrapper mediaWrapper = (MediaWrapper) obj;
            viewHolder2.getCardView().setTitleText(mediaWrapper.getTitle());
            viewHolder2.getCardView().setContentText(mediaWrapper.getDescription());
            viewHolder2.updateCardViewImage(mediaLibraryItem);
            if (this.seenMediaMarkerVisible && mediaWrapper.getType() == 0 && mediaWrapper.getSeen() > 0) {
                viewHolder2.getCardView().setBadgeImage(getSeenDrawable());
            }
            viewHolder2.view.setOnLongClickListener(new CardPresenter$$ExternalSyntheticLambda0(obj, this));
        } else if (obj instanceof ResolverMedia) {
            ResolverMedia resolverMedia = (ResolverMedia) obj;
            viewHolder2.getCardView().setTitleText(resolverMedia.title());
            viewHolder2.getCardView().setContentText(resolverMedia.getCardSubtitle());
            viewHolder2.updateCardViewImage(resolverMedia.imageUri(LocaleUtilsKt.getLocaleLanguages(this.context)));
        } else if (obj instanceof MediaLibraryItem) {
            MediaLibraryItem mediaLibraryItem2 = (MediaLibraryItem) obj;
            viewHolder2.getCardView().setTitleText(mediaLibraryItem2.getTitle());
            viewHolder2.getCardView().setContentText(mediaLibraryItem2.getDescription());
            viewHolder2.updateCardViewImage(mediaLibraryItem2);
        } else if (obj instanceof String) {
            viewHolder2.getCardView().setTitleText((CharSequence) obj);
            viewHolder2.getCardView().setContentText("");
            viewHolder2.updateCardViewImage(this.sDefaultCardImage);
        }
        if (obj instanceof DummyItem) {
            DummyItem dummyItem = (DummyItem) obj;
            if (dummyItem.getId() == 20 || dummyItem.getId() == 26) {
                AnimatedVectorDrawableCompat create = AnimatedVectorDrawableCompat.create(this.context, R.drawable.anim_now_playing);
                Intrinsics.checkNotNull(create);
                viewHolder2.getCardView().setBadgeImage(create);
                create.registerAnimationCallback(new CardPresenter$onBindViewHolder$2(viewHolder2, create));
                create.start();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final boolean onBindViewHolder$lambda$0(Object obj, CardPresenter cardPresenter, View view) {
        Intrinsics.checkNotNullParameter(obj, "$item");
        Intrinsics.checkNotNullParameter(cardPresenter, "this$0");
        TvUtil tvUtil = TvUtil.INSTANCE;
        Context context2 = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context2, "getContext(...)");
        tvUtil.showMediaDetail(context2, (MediaWrapper) obj, cardPresenter.fromHistory);
        return true;
    }

    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object obj, List<? extends Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        Intrinsics.checkNotNullParameter(obj, "item");
        Intrinsics.checkNotNull(list);
        if (list.isEmpty()) {
            onBindViewHolder(viewHolder, obj);
            return;
        }
        ViewHolder viewHolder2 = (ViewHolder) viewHolder;
        MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) obj;
        for (Object next : list) {
            Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.Int");
            int intValue = ((Integer) next).intValue();
            if (intValue == 1) {
                ImageLoaderKt.loadImage$default(viewHolder2.getCardView(), mediaLibraryItem, 0, false, false, 28, (Object) null);
            } else if (intValue == 2) {
                MediaWrapper mediaWrapper = (MediaWrapper) obj;
                Tools.setMediaDescription(mediaWrapper);
                viewHolder2.getCardView().setContentText(mediaWrapper.getDescription());
                if (mediaWrapper.getTime() <= 0 && this.seenMediaMarkerVisible && mediaWrapper.getType() == 0 && mediaWrapper.getSeen() > 0) {
                    viewHolder2.getCardView().setBadgeImage(getSeenDrawable());
                }
            } else if (intValue == 3) {
                MediaWrapper mediaWrapper2 = (MediaWrapper) obj;
                if (this.seenMediaMarkerVisible && mediaWrapper2.getType() == 0 && mediaWrapper2.getSeen() > 0) {
                    viewHolder2.getCardView().setBadgeImage(getSeenDrawable());
                }
            } else if (intValue == 4) {
                Tools.setMediaDescription(mediaLibraryItem);
                viewHolder2.getCardView().setContentText(mediaLibraryItem.getDescription());
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/television/ui/CardPresenter$Companion;", "", "()V", "CARD_HEIGHT", "", "CARD_HEIGHT_POSTER", "CARD_WIDTH", "CARD_WIDTH_POSTER", "TAG", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: CardPresenter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
