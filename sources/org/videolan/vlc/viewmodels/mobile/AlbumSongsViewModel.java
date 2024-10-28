package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.providers.medialibrary.AlbumsProvider;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.providers.medialibrary.TracksProvider;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001!B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00050\u00140\fX\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0019\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\f¢\u0006\n\n\u0002\u0010\u001c\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 ¨\u0006\""}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/AlbumSongsViewModel;", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "context", "Landroid/content/Context;", "parent", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Landroid/content/Context;Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "albumsProvider", "Lorg/videolan/vlc/providers/medialibrary/AlbumsProvider;", "getAlbumsProvider", "()Lorg/videolan/vlc/providers/medialibrary/AlbumsProvider;", "displayModeKeys", "", "", "getDisplayModeKeys", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getParent", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "providers", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "getProviders", "()[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "providersInCard", "", "getProvidersInCard", "()[Ljava/lang/Boolean;", "[Ljava/lang/Boolean;", "tracksProvider", "Lorg/videolan/vlc/providers/medialibrary/TracksProvider;", "getTracksProvider", "()Lorg/videolan/vlc/providers/medialibrary/TracksProvider;", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AlbumSongsViewModel.kt */
public final class AlbumSongsViewModel extends MedialibraryViewModel {
    private final AlbumsProvider albumsProvider;
    private final String[] displayModeKeys;
    private final MediaLibraryItem parent;
    private final MedialibraryProvider<? extends MediaLibraryItem>[] providers;
    private final Boolean[] providersInCard = {true, false};
    private final TracksProvider tracksProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AlbumSongsViewModel(Context context, MediaLibraryItem mediaLibraryItem) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "parent");
        this.parent = mediaLibraryItem;
        SortableModel sortableModel = this;
        AlbumsProvider albumsProvider2 = new AlbumsProvider(mediaLibraryItem, context, sortableModel);
        this.albumsProvider = albumsProvider2;
        TracksProvider tracksProvider2 = new TracksProvider(mediaLibraryItem, context, sortableModel);
        this.tracksProvider = tracksProvider2;
        this.providers = new MedialibraryProvider[]{albumsProvider2, tracksProvider2};
        String[] strArr = {"display_mode_albums_song_albums", "display_mode_albums_song_tracks"};
        this.displayModeKeys = strArr;
        if (mediaLibraryItem instanceof Artist) {
            watchArtists();
        } else if (mediaLibraryItem instanceof Album) {
            watchAlbums();
        } else {
            watchMedia();
        }
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            this.providersInCard[i] = Boolean.valueOf(getSettings().getBoolean(this.displayModeKeys[i], this.providersInCard[i].booleanValue()));
        }
    }

    public final MediaLibraryItem getParent() {
        return this.parent;
    }

    public final AlbumsProvider getAlbumsProvider() {
        return this.albumsProvider;
    }

    public final TracksProvider getTracksProvider() {
        return this.tracksProvider;
    }

    public MedialibraryProvider<? extends MediaLibraryItem>[] getProviders() {
        return this.providers;
    }

    public final Boolean[] getProvidersInCard() {
        return this.providersInCard;
    }

    public final String[] getDisplayModeKeys() {
        return this.displayModeKeys;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/AlbumSongsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "parent", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Landroid/content/Context;Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "getContext", "()Landroid/content/Context;", "getParent", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AlbumSongsViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final MediaLibraryItem parent;

        public Factory(Context context2, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "parent");
            this.context = context2;
            this.parent = mediaLibraryItem;
        }

        public final Context getContext() {
            return this.context;
        }

        public final MediaLibraryItem getParent() {
            return this.parent;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new AlbumSongsViewModel(applicationContext, this.parent);
        }
    }
}
