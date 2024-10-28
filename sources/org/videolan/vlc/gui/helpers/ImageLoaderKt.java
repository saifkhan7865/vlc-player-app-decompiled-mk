package org.videolan.vlc.gui.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.leanback.widget.ImageCardView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.DummyItem;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.BitmapCache;
import org.videolan.tools.PathUtilsKt;
import org.videolan.tools.Settings;
import org.videolan.vlc.BR;
import org.videolan.vlc.R;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.KextensionsKt;
import org.videolan.vlc.util.ThumbnailsProvider;

@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\b\u001a\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u0003H\u0007\u001a$\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u000eH\u0007\u001a$\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00012\b\b\u0002\u0010\u0013\u001a\u00020\u000eH\u0007\u001a\u001e\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u000eH@¢\u0006\u0002\u0010\u0019\u001a$\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u000e\u001a\u001a\u0010 \u001a\u00020!2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0001\u0010\"\u001a\u00020\u0003H\u0007\u001a\u0018\u0010#\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020$\u001aF\u0010%\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u000e2\b\b\u0002\u0010(\u001a\u00020\u000eH@¢\u0006\u0002\u0010)\u001a\u0018\u0010*\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0003\u001a$\u0010*\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u00032\b\b\u0002\u0010\u001f\u001a\u00020\u000e\u001a$\u0010+\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020,2\b\b\u0002\u0010\u001f\u001a\u00020\u000e\u001a8\u0010-\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010.\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u000eH@¢\u0006\u0002\u0010/\u001a\u001a\u00100\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u00101\u001a\u0004\u0018\u00010\u0001H\u0007\u001a\u001a\u00102\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u00103\u001a\u0004\u0018\u00010\u0001H\u0007\u001a8\u00104\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0013\u001a\u00020\u000e2\b\b\u0002\u0010(\u001a\u00020\u000eH\u0007\u001a(\u00105\u001a\u00020\n2\u0006\u0010\u000b\u001a\u0002062\b\u0010\u0017\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010(\u001a\u00020\u000e\u001a\u001a\u00107\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0007\u001a\u001a\u00108\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u000109H\u0007\u001aB\u0010:\u001a\u00020\n2\b\u0010;\u001a\u0004\u0018\u00010!2\u0006\u0010<\u001a\u00020\f2\b\u0010=\u001a\u0004\u0018\u00010'2\b\b\u0002\u0010>\u001a\u00020\u000e2\b\b\u0002\u0010\u0013\u001a\u00020\u000e2\b\b\u0002\u0010(\u001a\u00020\u000eH\u0007\u001a\u001a\u0010?\u001a\u00020\n2\b\b\u0001\u0010@\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\fH\u0007\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006A"}, d2 = {"TAG", "", "defaultImageWidth", "", "defaultImageWidthTV", "placeholderTvBg", "Landroid/graphics/drawable/Drawable;", "sMedialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "constraintRatio", "", "v", "Landroid/view/View;", "isSquare", "", "imageWidth", "downloadIcon", "imageUri", "Landroid/net/Uri;", "tv", "imageUrl", "findInLibrary", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "item", "isMedia", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAudioIconDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "context", "Landroid/content/Context;", "type", "big", "getBitmapFromDrawable", "Landroid/graphics/Bitmap;", "drawableId", "getDummyItemIcon", "Lorg/videolan/medialibrary/media/DummyItem;", "getImage", "binding", "Landroidx/databinding/ViewDataBinding;", "card", "(Landroid/view/View;Lorg/videolan/medialibrary/media/MediaLibraryItem;Landroidx/databinding/ViewDataBinding;IZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaIconDrawable", "getMoviepediaIconDrawable", "", "getPlaylistOrGenreImage", "width", "(Landroid/view/View;Lorg/videolan/medialibrary/media/MediaLibraryItem;Landroidx/databinding/ViewDataBinding;IZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "imageCardViewContent", "content", "imageCardViewTitle", "title", "loadImage", "loadPlaylistImageWithWidth", "Landroid/widget/ImageView;", "placeHolderImageView", "placeHolderView", "", "updateImageView", "bitmap", "target", "vdb", "updateScaleType", "updateImageViewTv", "res", "vlc-android_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageLoader.kt */
public final class ImageLoaderKt {
    private static final String TAG = "ImageLoader";
    private static volatile int defaultImageWidth;
    private static int defaultImageWidthTV;
    private static Drawable placeholderTvBg;
    /* access modifiers changed from: private */
    public static final Medialibrary sMedialibrary;

    static {
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        sMedialibrary = instance;
    }

    public static /* synthetic */ void loadImage$default(View view, MediaLibraryItem mediaLibraryItem, int i, boolean z, boolean z2, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = 0;
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        if ((i2 & 16) != 0) {
            z2 = false;
        }
        loadImage(view, mediaLibraryItem, i, z, z2);
    }

    @BindingAdapter(requireAll = false, value = {"media", "imageWidth", "tv", "card"})
    public static final void loadImage(View view, MediaLibraryItem mediaLibraryItem, int i, boolean z, boolean z2) {
        int i2;
        String str;
        View view2 = view;
        MediaLibraryItem mediaLibraryItem2 = mediaLibraryItem;
        int i3 = i;
        Intrinsics.checkNotNullParameter(view, "v");
        if (mediaLibraryItem2 != null) {
            view.setTag(mediaLibraryItem.getTitle());
            if (mediaLibraryItem.getItemType() != 16 && mediaLibraryItem.getItemType() != 8) {
                ViewDataBinding findBinding = DataBindingUtil.findBinding(view);
                boolean z3 = true;
                boolean z4 = mediaLibraryItem.getItemType() == 32;
                if (Settings.INSTANCE.getShowVideoThumbs() || ((!z4 || ((MediaWrapper) mediaLibraryItem2).getType() != 0) && mediaLibraryItem.getItemType() != 2048 && !(mediaLibraryItem2 instanceof Folder))) {
                    boolean z5 = z4 && mediaLibraryItem.getItemType() == 2048;
                    if (z4 || mediaLibraryItem.getItemType() != 1024) {
                        z3 = false;
                    }
                    if (i3 != 0) {
                        i2 = i3;
                    } else {
                        i2 = view.getWidth();
                    }
                    if (z5) {
                        str = "videogroup:" + mediaLibraryItem.getTitle();
                    } else if (z3) {
                        StringBuilder sb = new StringBuilder("folder:");
                        String str2 = ((Folder) mediaLibraryItem2).mMrl;
                        Intrinsics.checkNotNullExpressionValue(str2, "mMrl");
                        sb.append(PathUtilsKt.sanitizePath(str2));
                        str = sb.toString();
                    } else {
                        str = ThumbnailsProvider.INSTANCE.getMediaCacheKey(z4, mediaLibraryItem, String.valueOf(i2));
                    }
                    Bitmap bitmapFromMemCache = str != null ? BitmapCache.INSTANCE.getBitmapFromMemCache(str) : null;
                    if (bitmapFromMemCache != null) {
                        updateImageView$default(bitmapFromMemCache, view, findBinding, false, z, z2, 8, (Object) null);
                        return;
                    }
                    CoroutineScope scope = KextensionsKt.getScope(view);
                    CoroutineScope coroutineScope = CoroutineScopeKt.isActive(scope) ? scope : null;
                    if (coroutineScope != null) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ImageLoaderKt$loadImage$2(view, mediaLibraryItem, z4, findBinding, i, z, z2, (Continuation<? super ImageLoaderKt$loadImage$2>) null), 3, (Object) null);
                        return;
                    }
                    return;
                }
                UiTools uiTools = UiTools.INSTANCE;
                Context context = view.getContext();
                Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
                updateImageView$default(uiTools.getDefaultVideoDrawable(context).getBitmap(), view, findBinding, false, z, z2, 8, (Object) null);
            } else if (i3 != 0) {
                loadPlaylistImageWithWidth((ImageView) view2, mediaLibraryItem, i3, z2);
            }
        }
    }

    public static final void loadPlaylistImageWithWidth(ImageView imageView, MediaLibraryItem mediaLibraryItem, int i, boolean z) {
        Intrinsics.checkNotNullParameter(imageView, "v");
        if (i != 0 && mediaLibraryItem != null) {
            View view = imageView;
            ViewDataBinding findBinding = DataBindingUtil.findBinding(view);
            CoroutineScope scope = KextensionsKt.getScope(view);
            if (!CoroutineScopeKt.isActive(scope)) {
                scope = null;
            }
            if (scope != null) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(scope, (CoroutineContext) null, (CoroutineStart) null, new ImageLoaderKt$loadPlaylistImageWithWidth$2(imageView, mediaLibraryItem, findBinding, i, z, (Continuation<? super ImageLoaderKt$loadPlaylistImageWithWidth$2>) null), 3, (Object) null);
            }
        }
    }

    public static /* synthetic */ BitmapDrawable getAudioIconDrawable$default(Context context, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return getAudioIconDrawable(context, i, z);
    }

    public static final BitmapDrawable getAudioIconDrawable(Context context, int i, boolean z) {
        BitmapDrawable bitmapDrawable;
        if (context == null) {
            return null;
        }
        if (i == 2) {
            UiTools uiTools = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools.getDefaultAlbumDrawableBig(context) : uiTools.getDefaultAlbumDrawable(context);
        } else if (i == 4) {
            UiTools uiTools2 = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools2.getDefaultArtistDrawableBig(context) : uiTools2.getDefaultArtistDrawable(context);
        } else if (i == 16) {
            UiTools uiTools3 = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools3.getDefaultPlaylistDrawableBig(context) : uiTools3.getDefaultPlaylistDrawable(context);
        } else if (i != 32) {
            return null;
        } else {
            UiTools uiTools4 = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools4.getDefaultAudioDrawableBig(context) : uiTools4.getDefaultAudioDrawable(context);
        }
        return bitmapDrawable;
    }

    public static /* synthetic */ BitmapDrawable getMediaIconDrawable$default(Context context, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = false;
        }
        return getMediaIconDrawable(context, i, z);
    }

    public static final BitmapDrawable getMediaIconDrawable(Context context, int i, boolean z) {
        BitmapDrawable bitmapDrawable;
        if (context == null) {
            return null;
        }
        if (i == 0) {
            UiTools uiTools = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools.getDefaultVideoDrawableBig(context) : uiTools.getDefaultAudioDrawable(context);
        } else if (i == 1) {
            UiTools uiTools2 = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools2.getDefaultAudioDrawableBig(context) : uiTools2.getDefaultAudioDrawable(context);
        } else if (i == 2) {
            UiTools uiTools3 = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools3.getDefaultAlbumDrawableBig(context) : uiTools3.getDefaultAlbumDrawable(context);
        } else if (i == 3) {
            UiTools uiTools4 = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools4.getDefaultFolderDrawableBig(context) : uiTools4.getDefaultFolderDrawable(context);
        } else if (i != 4) {
            return null;
        } else {
            UiTools uiTools5 = UiTools.INSTANCE;
            bitmapDrawable = z ? uiTools5.getDefaultArtistDrawableBig(context) : uiTools5.getDefaultArtistDrawable(context);
        }
        return bitmapDrawable;
    }

    public static /* synthetic */ BitmapDrawable getMoviepediaIconDrawable$default(Context context, long j, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return getMoviepediaIconDrawable(context, j, z);
    }

    public static final BitmapDrawable getMoviepediaIconDrawable(Context context, long j, boolean z) {
        BitmapDrawable defaultTvshowDrawableBig;
        if (context == null) {
            return null;
        }
        if (j == 30) {
            UiTools uiTools = UiTools.INSTANCE;
            defaultTvshowDrawableBig = z ? uiTools.getDefaultMovieDrawableBig(context) : uiTools.getDefaultMovieDrawable(context);
        } else if (j != 31) {
            return null;
        } else {
            UiTools uiTools2 = UiTools.INSTANCE;
            defaultTvshowDrawableBig = z ? uiTools2.getDefaultTvshowDrawableBig(context) : uiTools2.getDefaultTvshowDrawable(context);
        }
        return defaultTvshowDrawableBig;
    }

    public static final Bitmap getBitmapFromDrawable(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        Drawable drawable = AppCompatResources.getDrawable(context, i);
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkNotNull(bitmap);
            return bitmap;
        } else if ((drawable instanceof VectorDrawableCompat) || AppUtils$$ExternalSyntheticApiModelOutline0.m$2((Object) drawable)) {
            Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Intrinsics.checkNotNullExpressionValue(createBitmap, "createBitmap(...)");
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return createBitmap;
        } else {
            throw new IllegalArgumentException("unsupported drawable type");
        }
    }

    public static final BitmapDrawable getMediaIconDrawable(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (i == 0) {
            return UiTools.INSTANCE.getDefaultVideoDrawable(context);
        }
        return UiTools.INSTANCE.getDefaultAudioDrawable(context);
    }

    public static final BitmapDrawable getDummyItemIcon(Context context, DummyItem dummyItem) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dummyItem, "item");
        if (dummyItem.getId() == 0) {
            return new BitmapDrawable(context.getResources(), getBitmapFromDrawable(context, R.drawable.ic_add_to_group));
        }
        return null;
    }

    @BindingAdapter({"placeholder"})
    public static final void placeHolderView(View view, Object obj) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (obj == null) {
            if (placeholderTvBg == null) {
                placeholderTvBg = ContextCompat.getDrawable(view.getContext(), R.drawable.rounded_corners_grey);
            }
            view.setBackground(placeholderTvBg);
            return;
        }
        view.setBackground((Drawable) null);
    }

    @BindingAdapter({"placeholderImage"})
    public static final void placeHolderImageView(View view, MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (mediaLibraryItem == null) {
            view.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.rounded_corners_grey));
            return;
        }
        UiTools uiTools = UiTools.INSTANCE;
        Context context = view.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        view.setBackground(uiTools.getDefaultAudioDrawable(context));
    }

    @BindingAdapter({"icvTitle"})
    public static final void imageCardViewTitle(View view, String str) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (view instanceof ImageCardView) {
            ((ImageCardView) view).setTitleText(str);
        }
    }

    @BindingAdapter({"icvContent"})
    public static final void imageCardViewContent(View view, String str) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (view instanceof ImageCardView) {
            ((ImageCardView) view).setContentText(str);
        }
    }

    public static /* synthetic */ void downloadIcon$default(View view, Uri uri, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        downloadIcon(view, uri, z);
    }

    @BindingAdapter(requireAll = false, value = {"imageUri", "tv"})
    public static final void downloadIcon(View view, Uri uri, boolean z) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (BrowserutilsKt.isSchemeHttpOrHttps(uri != null ? uri.getScheme() : null)) {
            CoroutineScope scope = KextensionsKt.getScope(view);
            CoroutineScope coroutineScope = CoroutineScopeKt.isActive(scope) ? scope : null;
            if (coroutineScope != null) {
                Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ImageLoaderKt$downloadIcon$2(uri, view, z, (Continuation<? super ImageLoaderKt$downloadIcon$2>) null), 3, (Object) null);
            }
        }
    }

    public static /* synthetic */ void downloadIcon$default(View view, String str, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        downloadIcon(view, str, z);
    }

    @BindingAdapter(requireAll = false, value = {"imageUrl", "tv"})
    public static final void downloadIcon(View view, String str, boolean z) {
        Intrinsics.checkNotNullParameter(view, "v");
        CharSequence charSequence = str;
        if (charSequence != null && charSequence.length() != 0) {
            Uri parse = Uri.parse(str);
            if (BrowserutilsKt.isSchemeHttpOrHttps(parse.getScheme())) {
                CoroutineScope scope = KextensionsKt.getScope(view);
                CoroutineScope coroutineScope = CoroutineScopeKt.isActive(scope) ? scope : null;
                if (coroutineScope != null) {
                    Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new ImageLoaderKt$downloadIcon$4(parse, view, z, (Continuation<? super ImageLoaderKt$downloadIcon$4>) null), 3, (Object) null);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00f4 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0110  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x013e  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object getImage(android.view.View r20, org.videolan.medialibrary.media.MediaLibraryItem r21, androidx.databinding.ViewDataBinding r22, int r23, boolean r24, boolean r25, kotlin.coroutines.Continuation<? super kotlin.Unit> r26) {
        /*
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r24
            r4 = r26
            boolean r5 = r4 instanceof org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$1
            if (r5 == 0) goto L_0x001e
            r5 = r4
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$1 r5 = (org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$1) r5
            int r6 = r5.label
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            r6 = r6 & r7
            if (r6 == 0) goto L_0x001e
            int r4 = r5.label
            int r4 = r4 - r7
            r5.label = r4
            goto L_0x0023
        L_0x001e:
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$1 r5 = new org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$1
            r5.<init>(r4)
        L_0x0023:
            java.lang.Object r4 = r5.result
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r7 = r5.label
            r8 = 1
            if (r7 == 0) goto L_0x005d
            if (r7 != r8) goto L_0x0055
            boolean r0 = r5.Z$1
            boolean r1 = r5.Z$0
            java.lang.Object r2 = r5.L$4
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$rebindCallbacks$1 r2 = (org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$rebindCallbacks$1) r2
            java.lang.Object r3 = r5.L$3
            kotlin.jvm.internal.Ref$BooleanRef r3 = (kotlin.jvm.internal.Ref.BooleanRef) r3
            java.lang.Object r6 = r5.L$2
            androidx.databinding.ViewDataBinding r6 = (androidx.databinding.ViewDataBinding) r6
            java.lang.Object r7 = r5.L$1
            org.videolan.medialibrary.media.MediaLibraryItem r7 = (org.videolan.medialibrary.media.MediaLibraryItem) r7
            java.lang.Object r5 = r5.L$0
            android.view.View r5 = (android.view.View) r5
            kotlin.ResultKt.throwOnFailure(r4)
            r11 = r0
            r9 = r2
            r0 = r5
            r2 = r6
            r5 = r4
            r4 = r3
            r3 = r1
            r1 = r7
            goto L_0x00e5
        L_0x0055:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x005d:
            kotlin.ResultKt.throwOnFailure(r4)
            kotlin.jvm.internal.Ref$BooleanRef r4 = new kotlin.jvm.internal.Ref$BooleanRef
            r4.<init>()
            r7 = 0
            if (r2 == 0) goto L_0x006e
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$rebindCallbacks$1 r9 = new org.videolan.vlc.gui.helpers.ImageLoaderKt$getImage$rebindCallbacks$1
            r9.<init>(r4)
            goto L_0x006f
        L_0x006e:
            r9 = r7
        L_0x006f:
            if (r2 == 0) goto L_0x007d
            r22.executePendingBindings()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            r10 = r9
            androidx.databinding.OnRebindCallback r10 = (androidx.databinding.OnRebindCallback) r10
            r2.addOnRebindCallback(r10)
        L_0x007d:
            if (r3 == 0) goto L_0x0096
            int r10 = defaultImageWidthTV
            if (r10 != 0) goto L_0x0093
            android.content.Context r10 = r20.getContext()
            android.content.res.Resources r10 = r10.getResources()
            int r11 = org.videolan.vlc.R.dimen.tv_grid_card_thumb_width
            int r10 = r10.getDimensionPixelSize(r11)
            defaultImageWidthTV = r10
        L_0x0093:
            int r10 = defaultImageWidthTV
            goto L_0x00c6
        L_0x0096:
            if (r23 <= 0) goto L_0x009b
            r10 = r23
            goto L_0x00c6
        L_0x009b:
            int r10 = r20.getWidth()
            if (r10 <= 0) goto L_0x00a6
            int r10 = r20.getWidth()
            goto L_0x00c6
        L_0x00a6:
            int r10 = defaultImageWidth
            if (r10 <= 0) goto L_0x00ad
            int r10 = defaultImageWidth
            goto L_0x00c6
        L_0x00ad:
            android.content.Context r10 = r20.getContext()
            android.content.res.Resources r10 = r10.getResources()
            boolean r11 = r0 instanceof androidx.leanback.widget.ImageCardView
            if (r11 == 0) goto L_0x00bc
            int r11 = org.videolan.vlc.R.dimen.tv_grid_card_thumb_width
            goto L_0x00be
        L_0x00bc:
            int r11 = org.videolan.vlc.R.dimen.audio_browser_item_size
        L_0x00be:
            int r10 = r10.getDimensionPixelSize(r11)
            defaultImageWidth = r10
            int r10 = defaultImageWidth
        L_0x00c6:
            boolean r11 = r4.element
            if (r11 != 0) goto L_0x00ef
            org.videolan.vlc.util.ThumbnailsProvider r7 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            r5.L$0 = r0
            r5.L$1 = r1
            r5.L$2 = r2
            r5.L$3 = r4
            r5.L$4 = r9
            r5.Z$0 = r3
            r11 = r25
            r5.Z$1 = r11
            r5.label = r8
            java.lang.Object r5 = r7.obtainBitmap(r1, r10, r5)
            if (r5 != r6) goto L_0x00e5
            return r6
        L_0x00e5:
            r7 = r5
            android.graphics.Bitmap r7 = (android.graphics.Bitmap) r7
        L_0x00e8:
            r13 = r0
            r16 = r3
            r12 = r7
            r17 = r11
            goto L_0x00f2
        L_0x00ef:
            r11 = r25
            goto L_0x00e8
        L_0x00f2:
            if (r12 != 0) goto L_0x010e
            if (r16 == 0) goto L_0x010e
            boolean r0 = r4.element
            if (r0 != 0) goto L_0x0101
            int r0 = org.videolan.vlc.gui.helpers.UiToolsKt.getTvIconRes(r1)
            updateImageViewTv(r0, r13)
        L_0x0101:
            if (r2 == 0) goto L_0x010b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            androidx.databinding.OnRebindCallback r9 = (androidx.databinding.OnRebindCallback) r9
            r2.removeOnRebindCallback(r9)
        L_0x010b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x010e:
            if (r12 != 0) goto L_0x013e
            if (r2 == 0) goto L_0x011d
            int r0 = org.videolan.vlc.BR.scaleType
            android.widget.ImageView$ScaleType r1 = android.widget.ImageView.ScaleType.CENTER_INSIDE
            boolean r0 = r2.setVariable(r0, r1)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
        L_0x011d:
            boolean r0 = r4.element
            if (r0 != 0) goto L_0x0131
            if (r2 == 0) goto L_0x0131
            int r0 = org.videolan.vlc.BR.showProgress
            r1 = 0
            java.lang.Boolean r1 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r1)
            boolean r0 = r2.setVariable(r0, r1)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
        L_0x0131:
            if (r2 == 0) goto L_0x013b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            androidx.databinding.OnRebindCallback r9 = (androidx.databinding.OnRebindCallback) r9
            r2.removeOnRebindCallback(r9)
        L_0x013b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x013e:
            boolean r0 = r4.element
            if (r0 != 0) goto L_0x014b
            r18 = 8
            r19 = 0
            r15 = 0
            r14 = r2
            updateImageView$default(r12, r13, r14, r15, r16, r17, r18, r19)
        L_0x014b:
            if (r2 == 0) goto L_0x0155
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            androidx.databinding.OnRebindCallback r9 = (androidx.databinding.OnRebindCallback) r9
            r2.removeOnRebindCallback(r9)
        L_0x0155:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.ImageLoaderKt.getImage(android.view.View, org.videolan.medialibrary.media.MediaLibraryItem, androidx.databinding.ViewDataBinding, int, boolean, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object getImage$default(View view, MediaLibraryItem mediaLibraryItem, ViewDataBinding viewDataBinding, int i, boolean z, boolean z2, Continuation continuation, int i2, Object obj) {
        return getImage(view, mediaLibraryItem, viewDataBinding, (i2 & 8) != 0 ? 0 : i, (i2 & 16) != 0 ? false : z, (i2 & 32) != 0 ? false : z2, continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0081  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0129 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x014f  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0182 A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x01a1  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01b0  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object getPlaylistOrGenreImage(android.view.View r24, org.videolan.medialibrary.media.MediaLibraryItem r25, androidx.databinding.ViewDataBinding r26, int r27, boolean r28, kotlin.coroutines.Continuation<? super kotlin.Unit> r29) {
        /*
            r0 = r25
            r1 = r26
            r2 = r29
            boolean r3 = r2 instanceof org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$1 r3 = (org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$1 r3 = new org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$1
            r3.<init>(r2)
        L_0x001f:
            r9 = r3
            java.lang.Object r2 = r9.result
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r4 = r9.label
            r12 = 5
            r5 = 2
            r6 = 1
            if (r4 == 0) goto L_0x0081
            if (r4 == r6) goto L_0x005d
            if (r4 != r5) goto L_0x0055
            boolean r0 = r9.Z$0
            java.lang.Object r1 = r9.L$4
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$rebindCallbacks$1 r1 = (org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$rebindCallbacks$1) r1
            java.lang.Object r3 = r9.L$3
            kotlin.jvm.internal.Ref$BooleanRef r3 = (kotlin.jvm.internal.Ref.BooleanRef) r3
            java.lang.Object r4 = r9.L$2
            androidx.databinding.ViewDataBinding r4 = (androidx.databinding.ViewDataBinding) r4
            java.lang.Object r5 = r9.L$1
            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
            java.lang.Object r6 = r9.L$0
            android.view.View r6 = (android.view.View) r6
            kotlin.ResultKt.throwOnFailure(r2)
            r13 = r0
            r14 = r1
            r1 = r5
            r0 = r6
            r23 = r4
            r4 = r2
            r2 = r23
            goto L_0x012b
        L_0x0055:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x005d:
            boolean r0 = r9.Z$0
            int r1 = r9.I$0
            java.lang.Object r4 = r9.L$4
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$rebindCallbacks$1 r4 = (org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$rebindCallbacks$1) r4
            java.lang.Object r6 = r9.L$3
            kotlin.jvm.internal.Ref$BooleanRef r6 = (kotlin.jvm.internal.Ref.BooleanRef) r6
            java.lang.Object r7 = r9.L$2
            androidx.databinding.ViewDataBinding r7 = (androidx.databinding.ViewDataBinding) r7
            java.lang.Object r8 = r9.L$1
            org.videolan.medialibrary.media.MediaLibraryItem r8 = (org.videolan.medialibrary.media.MediaLibraryItem) r8
            java.lang.Object r10 = r9.L$0
            android.view.View r10 = (android.view.View) r10
            kotlin.ResultKt.throwOnFailure(r2)
            r13 = r0
            r14 = r4
            r15 = r6
            r0 = r10
            r6 = r2
            r2 = r7
            r7 = r1
            r1 = r8
            goto L_0x00d5
        L_0x0081:
            kotlin.ResultKt.throwOnFailure(r2)
            kotlin.jvm.internal.Ref$BooleanRef r2 = new kotlin.jvm.internal.Ref$BooleanRef
            r2.<init>()
            r4 = 0
            if (r1 == 0) goto L_0x0092
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$rebindCallbacks$1 r7 = new org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$rebindCallbacks$1
            r7.<init>(r2)
            goto L_0x0093
        L_0x0092:
            r7 = r4
        L_0x0093:
            if (r1 == 0) goto L_0x00a1
            r26.executePendingBindings()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            r8 = r7
            androidx.databinding.OnRebindCallback r8 = (androidx.databinding.OnRebindCallback) r8
            r1.addOnRebindCallback(r8)
        L_0x00a1:
            boolean r8 = r2.element
            if (r8 != 0) goto L_0x0136
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1 r10 = new org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1
            r10.<init>(r0, r4)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r11 = r24
            r9.L$0 = r11
            r9.L$1 = r0
            r9.L$2 = r1
            r9.L$3 = r2
            r9.L$4 = r7
            r4 = r27
            r9.I$0 = r4
            r13 = r28
            r9.Z$0 = r13
            r9.label = r6
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r8, r10, r9)
            if (r6 != r3) goto L_0x00cf
            return r3
        L_0x00cf:
            r15 = r2
            r14 = r7
            r2 = r1
            r7 = r4
            r1 = r0
            r0 = r11
        L_0x00d5:
            java.util.List r6 = (java.util.List) r6
            org.videolan.vlc.util.ThumbnailsProvider r4 = org.videolan.vlc.util.ThumbnailsProvider.INSTANCE
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            boolean r10 = r1 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r10 == 0) goto L_0x00ee
            r10 = r1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r10 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r10
            int r10 = r10.getType()
            if (r10 != r12) goto L_0x00ee
            java.lang.String r10 = "playlist"
            goto L_0x00f0
        L_0x00ee:
            java.lang.String r10 = "genre"
        L_0x00f0:
            r8.append(r10)
            r10 = 58
            r8.append(r10)
            long r10 = r1.getId()
            r8.append(r10)
            r10 = 95
            r8.append(r10)
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            r9.L$0 = r0
            r9.L$1 = r1
            r9.L$2 = r2
            r9.L$3 = r15
            r9.L$4 = r14
            r9.Z$0 = r13
            r9.label = r5
            r10 = 0
            r11 = 8
            r16 = 0
            r5 = r8
            r8 = r10
            r10 = r11
            r11 = r16
            java.lang.Object r4 = org.videolan.vlc.util.ThumbnailsProvider.getPlaylistOrGenreImage$default(r4, r5, r6, r7, r8, r9, r10, r11)
            if (r4 != r3) goto L_0x012a
            return r3
        L_0x012a:
            r3 = r15
        L_0x012b:
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            r16 = r0
            r0 = r1
            r1 = r2
            r2 = r3
            r20 = r13
            r7 = r14
            goto L_0x013e
        L_0x0136:
            r11 = r24
            r13 = r28
            r16 = r11
            r20 = r13
        L_0x013e:
            boolean r3 = r0 instanceof org.videolan.medialibrary.interfaces.media.Playlist
            if (r3 != 0) goto L_0x0167
            boolean r3 = r0 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r3 == 0) goto L_0x014f
            org.videolan.medialibrary.interfaces.media.MediaWrapper r0 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r0
            int r0 = r0.getType()
            if (r0 != r12) goto L_0x014f
            goto L_0x0167
        L_0x014f:
            org.videolan.vlc.gui.helpers.UiTools r0 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.resources.AppContextProvider r3 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r3 = r3.getAppContext()
            if (r20 == 0) goto L_0x015e
            android.graphics.drawable.BitmapDrawable r0 = r0.getDefaultGenreDrawableBig(r3)
            goto L_0x0162
        L_0x015e:
            android.graphics.drawable.BitmapDrawable r0 = r0.getDefaultGenreDrawable(r3)
        L_0x0162:
            android.graphics.Bitmap r0 = r0.getBitmap()
            goto L_0x017e
        L_0x0167:
            org.videolan.vlc.gui.helpers.UiTools r0 = org.videolan.vlc.gui.helpers.UiTools.INSTANCE
            org.videolan.resources.AppContextProvider r3 = org.videolan.resources.AppContextProvider.INSTANCE
            android.content.Context r3 = r3.getAppContext()
            if (r20 == 0) goto L_0x0176
            android.graphics.drawable.BitmapDrawable r0 = r0.getDefaultPlaylistDrawableBig(r3)
            goto L_0x017a
        L_0x0176:
            android.graphics.drawable.BitmapDrawable r0 = r0.getDefaultPlaylistDrawable(r3)
        L_0x017a:
            android.graphics.Bitmap r0 = r0.getBitmap()
        L_0x017e:
            boolean r3 = r2.element
            if (r3 != 0) goto L_0x0186
            if (r4 != 0) goto L_0x0186
            r15 = r0
            goto L_0x0187
        L_0x0186:
            r15 = r4
        L_0x0187:
            boolean r0 = r2.element
            if (r0 != 0) goto L_0x019d
            if (r15 != 0) goto L_0x019d
            if (r1 == 0) goto L_0x019d
            int r0 = org.videolan.vlc.BR.showProgress
            r3 = 0
            java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            boolean r0 = r1.setVariable(r0, r3)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r0)
        L_0x019d:
            boolean r0 = r2.element
            if (r0 != 0) goto L_0x01ae
            r21 = 24
            r22 = 0
            r18 = 0
            r19 = 0
            r17 = r1
            updateImageView$default(r15, r16, r17, r18, r19, r20, r21, r22)
        L_0x01ae:
            if (r1 == 0) goto L_0x01b8
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            androidx.databinding.OnRebindCallback r7 = (androidx.databinding.OnRebindCallback) r7
            r1.removeOnRebindCallback(r7)
        L_0x01b8:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.ImageLoaderKt.getPlaylistOrGenreImage(android.view.View, org.videolan.medialibrary.media.MediaLibraryItem, androidx.databinding.ViewDataBinding, int, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final void updateImageViewTv(int i, View view) {
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        if (view instanceof ImageView) {
            ImageView imageView = (ImageView) view;
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(i);
            imageView.setVisibility(0);
        } else if (view instanceof ImageCardView) {
            ImageCardView imageCardView = (ImageCardView) view;
            imageCardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageCardView.getMainImageView().setImageResource(i);
        }
    }

    public static /* synthetic */ void updateImageView$default(Bitmap bitmap, View view, ViewDataBinding viewDataBinding, boolean z, boolean z2, boolean z3, int i, Object obj) {
        updateImageView(bitmap, view, viewDataBinding, (i & 8) != 0 ? true : z, (i & 16) != 0 ? false : z2, (i & 32) != 0 ? false : z3);
    }

    public static final void updateImageView(Bitmap bitmap, View view, ViewDataBinding viewDataBinding, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(view, TypedValues.AttributesType.S_TARGET);
        if (bitmap != null && bitmap.getWidth() > 1 && bitmap.getHeight() > 1) {
            if (viewDataBinding != null && !z2) {
                viewDataBinding.setVariable(BR.scaleType, z3 ? ImageView.ScaleType.CENTER_INSIDE : ImageView.ScaleType.FIT_CENTER);
                viewDataBinding.setVariable(BR.cover, new BitmapDrawable(view.getResources(), bitmap));
                viewDataBinding.setVariable(BR.protocol, (Object) null);
                viewDataBinding.setVariable(BR.showProgress, false);
            } else if (view instanceof ImageView) {
                if (z) {
                    ((ImageView) view).setScaleType(z2 ? ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.FIT_CENTER);
                }
                ImageView imageView = (ImageView) view;
                imageView.setImageBitmap(bitmap);
                imageView.setVisibility(0);
            } else if (view instanceof TextView) {
                TextView textView = (TextView) view;
                ViewCompat.setBackground(view, new BitmapDrawable(textView.getContext().getResources(), bitmap));
                textView.setText((CharSequence) null);
            } else if (view instanceof ImageCardView) {
                ImageCardView imageCardView = (ImageCardView) view;
                imageCardView.getMainImageView().setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageCardView.setMainImage(new BitmapDrawable(view.getResources(), bitmap));
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:46:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object findInLibrary(org.videolan.medialibrary.media.MediaLibraryItem r6, boolean r7, kotlin.coroutines.Continuation<? super org.videolan.medialibrary.media.MediaLibraryItem> r8) {
        /*
            boolean r0 = r8 instanceof org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$1 r0 = (org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$1 r0 = new org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$1
            r0.<init>(r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r6 = r0.L$0
            org.videolan.medialibrary.media.MediaLibraryItem r6 = (org.videolan.medialibrary.media.MediaLibraryItem) r6
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0095
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r8)
            if (r7 == 0) goto L_0x009d
            long r7 = r6.getId()
            r4 = 0
            int r2 = (r7 > r4 ? 1 : (r7 == r4 ? 0 : -1))
            if (r2 != 0) goto L_0x009d
            java.lang.String r7 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6, r7)
            r7 = r6
            org.videolan.medialibrary.interfaces.media.MediaWrapper r7 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r7
            int r8 = r7.getType()
            if (r8 == r3) goto L_0x0058
            if (r8 != 0) goto L_0x0056
            goto L_0x0058
        L_0x0056:
            r2 = 0
            goto L_0x0059
        L_0x0058:
            r2 = 1
        L_0x0059:
            android.net.Uri r7 = r7.getUri()
            java.lang.String r4 = r7.getScheme()     // Catch:{ NullPointerException -> 0x0062 }
            goto L_0x0064
        L_0x0062:
            java.lang.String r4 = ""
        L_0x0064:
            if (r2 != 0) goto L_0x0072
            r5 = 3
            if (r8 != r5) goto L_0x0071
            java.lang.String r8 = "upnp"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r4)
            if (r8 != 0) goto L_0x0072
        L_0x0071:
            return r6
        L_0x0072:
            if (r2 == 0) goto L_0x009d
            java.lang.String r8 = "file"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r4)
            if (r8 == 0) goto L_0x009d
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$2 r2 = new org.videolan.vlc.gui.helpers.ImageLoaderKt$findInLibrary$2
            r4 = 0
            r2.<init>(r7, r4)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r2, r0)
            if (r8 != r1) goto L_0x0095
            return r1
        L_0x0095:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            if (r8 != 0) goto L_0x009a
            goto L_0x009d
        L_0x009a:
            r6 = r8
            org.videolan.medialibrary.media.MediaLibraryItem r6 = (org.videolan.medialibrary.media.MediaLibraryItem) r6
        L_0x009d:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.helpers.ImageLoaderKt.findInLibrary(org.videolan.medialibrary.media.MediaLibraryItem, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static /* synthetic */ void constraintRatio$default(View view, boolean z, int i, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            i = -2;
        }
        constraintRatio(view, z, i);
    }

    @BindingAdapter(requireAll = false, value = {"constraintRatio", "coverWidth"})
    public static final void constraintRatio(View view, boolean z, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (i != -2) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            Intrinsics.checkNotNullExpressionValue(layoutParams, "getLayoutParams(...)");
            layoutParams.width = i;
            view.setLayoutParams(layoutParams);
        }
        ViewParent parent = view.getParent();
        ConstraintLayout constraintLayout = parent instanceof ConstraintLayout ? (ConstraintLayout) parent : null;
        if (constraintLayout != null) {
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.setDimensionRatio(view.getId(), z ? DiskLruCache.VERSION_1 : "16:10");
            constraintSet.applyTo(constraintLayout);
        }
    }
}
