package org.videolan.television.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.providers.medialibrary.AlbumsProvider;
import org.videolan.vlc.providers.medialibrary.ArtistsProvider;
import org.videolan.vlc.providers.medialibrary.GenresProvider;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.providers.medialibrary.PlaylistsProvider;
import org.videolan.vlc.providers.medialibrary.TracksProvider;
import org.videolan.vlc.providers.medialibrary.VideosProvider;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0005\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001!B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000eR\u001c\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u00190\u001dX\u0004¢\u0006\n\n\u0002\u0010 \u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lorg/videolan/television/viewmodel/MediaBrowserViewModel;", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lorg/videolan/vlc/viewmodels/tv/TvBrowserModel;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "context", "Landroid/content/Context;", "category", "", "parent", "(Landroid/content/Context;JLorg/videolan/medialibrary/media/MediaLibraryItem;)V", "getCategory", "()J", "currentItem", "getCurrentItem", "()Lorg/videolan/medialibrary/media/MediaLibraryItem;", "setCurrentItem", "(Lorg/videolan/medialibrary/media/MediaLibraryItem;)V", "nbColumns", "", "getNbColumns", "()I", "setNbColumns", "(I)V", "getParent", "provider", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "getProvider", "()Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "providers", "", "getProviders", "()[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Factory", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserViewModel.kt */
public final class MediaBrowserViewModel extends MedialibraryViewModel implements TvBrowserModel<MediaLibraryItem> {
    private final long category;
    private MediaLibraryItem currentItem;
    private int nbColumns;
    private final MediaLibraryItem parent;
    private final MedialibraryProvider<? extends MediaLibraryItem> provider;
    private final MedialibraryProvider<? extends MediaLibraryItem>[] providers;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaBrowserViewModel(Context context, long j, MediaLibraryItem mediaLibraryItem) {
        super(context);
        MedialibraryProvider<? extends MediaLibraryItem> medialibraryProvider;
        Context context2 = context;
        long j2 = j;
        MediaLibraryItem mediaLibraryItem2 = mediaLibraryItem;
        Intrinsics.checkNotNullParameter(context2, "context");
        this.category = j2;
        this.parent = mediaLibraryItem2;
        this.currentItem = mediaLibraryItem2;
        if (j2 == 22) {
            medialibraryProvider = new AlbumsProvider(mediaLibraryItem2, context2, this);
        } else if (j2 == 21) {
            medialibraryProvider = new ArtistsProvider(context2, this, false);
        } else if (j2 == 23) {
            medialibraryProvider = new GenresProvider(context2, this);
        } else if (j2 == 25) {
            medialibraryProvider = new VideosProvider((Folder) null, (VideoGroup) null, context2, this);
        } else if (j2 == 27) {
            medialibraryProvider = new PlaylistsProvider(context2, this, Playlist.Type.All);
        } else {
            medialibraryProvider = new TracksProvider((MediaLibraryItem) null, context2, this);
        }
        this.provider = medialibraryProvider;
        this.providers = new MedialibraryProvider[]{getProvider()};
        if (j2 == 22) {
            watchAlbums();
        } else if (j2 == 21) {
            watchArtists();
        } else if (j2 == 23) {
            watchGenres();
        } else if (j2 == 27) {
            watchPlaylists();
        } else {
            watchMedia();
        }
    }

    public final long getCategory() {
        return this.category;
    }

    public final MediaLibraryItem getParent() {
        return this.parent;
    }

    public int getNbColumns() {
        return this.nbColumns;
    }

    public void setNbColumns(int i) {
        this.nbColumns = i;
    }

    public MediaLibraryItem getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(MediaLibraryItem mediaLibraryItem) {
        this.currentItem = mediaLibraryItem;
    }

    public MedialibraryProvider<? extends MediaLibraryItem> getProvider() {
        return this.provider;
    }

    public MedialibraryProvider<? extends MediaLibraryItem>[] getProviders() {
        return this.providers;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ%\u0010\t\u001a\u0002H\n\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\rH\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/television/viewmodel/MediaBrowserViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "category", "", "parent", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Landroid/content/Context;JLorg/videolan/medialibrary/media/MediaLibraryItem;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaBrowserViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final long category;
        private final Context context;
        private final MediaLibraryItem parent;

        public Factory(Context context2, long j, MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
            this.category = j;
            this.parent = mediaLibraryItem;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new MediaBrowserViewModel(applicationContext, this.category, this.parent);
        }
    }
}
