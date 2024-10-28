package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.resources.Constants;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.providers.medialibrary.AlbumsProvider;
import org.videolan.vlc.providers.medialibrary.ArtistsProvider;
import org.videolan.vlc.providers.medialibrary.GenresProvider;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.providers.medialibrary.PlaylistsProvider;
import org.videolan.vlc.providers.medialibrary.TracksProvider;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u00015B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u00103\u001a\u000204H\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0019\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R$\u0010\u001f\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020!0 0\u0014X\u0004¢\u0006\n\n\u0002\u0010$\u001a\u0004\b\"\u0010#R\u0019\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0014¢\u0006\n\n\u0002\u0010)\u001a\u0004\b'\u0010(R\u001a\u0010*\u001a\u00020&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u0011\u0010/\u001a\u000200¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u00066"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/AudioBrowserViewModel;", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "albumsProvider", "Lorg/videolan/vlc/providers/medialibrary/AlbumsProvider;", "getAlbumsProvider", "()Lorg/videolan/vlc/providers/medialibrary/AlbumsProvider;", "artistsProvider", "Lorg/videolan/vlc/providers/medialibrary/ArtistsProvider;", "getArtistsProvider", "()Lorg/videolan/vlc/providers/medialibrary/ArtistsProvider;", "currentTab", "", "getCurrentTab", "()I", "setCurrentTab", "(I)V", "displayModeKeys", "", "", "getDisplayModeKeys", "()[Ljava/lang/String;", "[Ljava/lang/String;", "genresProvider", "Lorg/videolan/vlc/providers/medialibrary/GenresProvider;", "getGenresProvider", "()Lorg/videolan/vlc/providers/medialibrary/GenresProvider;", "playlistsProvider", "Lorg/videolan/vlc/providers/medialibrary/PlaylistsProvider;", "providers", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getProviders", "()[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "providersInCard", "", "getProvidersInCard", "()[Ljava/lang/Boolean;", "[Ljava/lang/Boolean;", "showResumeCard", "getShowResumeCard", "()Z", "setShowResumeCard", "(Z)V", "tracksProvider", "Lorg/videolan/vlc/providers/medialibrary/TracksProvider;", "getTracksProvider", "()Lorg/videolan/vlc/providers/medialibrary/TracksProvider;", "refresh", "", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AudioBrowserViewModel.kt */
public final class AudioBrowserViewModel extends MedialibraryViewModel {
    private final AlbumsProvider albumsProvider;
    private final ArtistsProvider artistsProvider;
    private int currentTab;
    private final String[] displayModeKeys;
    private final GenresProvider genresProvider;
    private final PlaylistsProvider playlistsProvider;
    private final MedialibraryProvider<? extends MediaLibraryItem>[] providers;
    private final Boolean[] providersInCard = {true, true, false, false, true};
    private boolean showResumeCard = getSettings().getBoolean("audio_resume_card", true);
    private final TracksProvider tracksProvider;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AudioBrowserViewModel(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.currentTab = ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getInt(Constants.KEY_AUDIO_CURRENT_TAB, 0);
        SortableModel sortableModel = this;
        ArtistsProvider artistsProvider2 = new ArtistsProvider(context, sortableModel, ((SharedPreferences) Settings.INSTANCE.getInstance(context)).getBoolean(SettingsKt.KEY_ARTISTS_SHOW_ALL, false));
        this.artistsProvider = artistsProvider2;
        AlbumsProvider albumsProvider2 = new AlbumsProvider((MediaLibraryItem) null, context, sortableModel);
        this.albumsProvider = albumsProvider2;
        TracksProvider tracksProvider2 = new TracksProvider((MediaLibraryItem) null, context, sortableModel);
        this.tracksProvider = tracksProvider2;
        GenresProvider genresProvider2 = new GenresProvider(context, sortableModel);
        this.genresProvider = genresProvider2;
        PlaylistsProvider playlistsProvider2 = new PlaylistsProvider(context, sortableModel, Playlist.Type.Audio);
        this.playlistsProvider = playlistsProvider2;
        this.providers = new MedialibraryProvider[]{artistsProvider2, albumsProvider2, tracksProvider2, genresProvider2, playlistsProvider2};
        String[] strArr = {"display_mode_audio_browser_artists", "display_mode_audio_browser_albums", "display_mode_audio_browser_track", "display_mode_audio_browser_genres", "display_mode_playlists_AudioOnly"};
        this.displayModeKeys = strArr;
        watchAlbums();
        watchArtists();
        watchGenres();
        watchMedia();
        watchPlaylists();
        int length = strArr.length;
        for (int i = 0; i < length; i++) {
            this.providersInCard[i] = Boolean.valueOf(getSettings().getBoolean(this.displayModeKeys[i], this.providersInCard[i].booleanValue()));
        }
    }

    public final int getCurrentTab() {
        return this.currentTab;
    }

    public final void setCurrentTab(int i) {
        this.currentTab = i;
    }

    public final ArtistsProvider getArtistsProvider() {
        return this.artistsProvider;
    }

    public final AlbumsProvider getAlbumsProvider() {
        return this.albumsProvider;
    }

    public final TracksProvider getTracksProvider() {
        return this.tracksProvider;
    }

    public final GenresProvider getGenresProvider() {
        return this.genresProvider;
    }

    public MedialibraryProvider<? extends MediaLibraryItem>[] getProviders() {
        return this.providers;
    }

    public final Boolean[] getProvidersInCard() {
        return this.providersInCard;
    }

    public final boolean getShowResumeCard() {
        return this.showResumeCard;
    }

    public final void setShowResumeCard(boolean z) {
        this.showResumeCard = z;
    }

    public final String[] getDisplayModeKeys() {
        return this.displayModeKeys;
    }

    public void refresh() {
        this.artistsProvider.setShowAll(getSettings().getBoolean(SettingsKt.KEY_ARTISTS_SHOW_ALL, false));
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), (CoroutineContext) null, (CoroutineStart) null, new AudioBrowserViewModel$refresh$1(this, (Continuation<? super AudioBrowserViewModel$refresh$1>) null), 3, (Object) null);
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/AudioBrowserViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: AudioBrowserViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;

        public Factory(Context context2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
        }

        public final Context getContext() {
            return this.context;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new AudioBrowserViewModel(applicationContext);
        }
    }
}
