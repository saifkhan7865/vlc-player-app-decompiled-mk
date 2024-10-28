package org.videolan.vlc.viewmodels.browser;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/videolan/vlc/viewmodels/browser/BrowserFavoritesModel;", "Landroidx/lifecycle/ViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "favorites", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getFavorites", "()Lorg/videolan/tools/livedata/LiveDataset;", "provider", "Lorg/videolan/vlc/viewmodels/browser/FavoritesProvider;", "getProvider", "()Lorg/videolan/vlc/viewmodels/browser/FavoritesProvider;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserFavoritesModel.kt */
public final class BrowserFavoritesModel extends ViewModel {
    private final LiveDataset<MediaLibraryItem> favorites;
    private final FavoritesProvider provider;

    public BrowserFavoritesModel(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        LiveDataset<MediaLibraryItem> liveDataset = new LiveDataset<>();
        this.favorites = liveDataset;
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        this.provider = new FavoritesProvider(applicationContext, liveDataset, ViewModelKt.getViewModelScope(this));
    }

    public final LiveDataset<MediaLibraryItem> getFavorites() {
        return this.favorites;
    }

    public final FavoritesProvider getProvider() {
        return this.provider;
    }
}
