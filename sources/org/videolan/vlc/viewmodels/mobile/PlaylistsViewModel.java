package org.videolan.vlc.viewmodels.mobile;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;
import org.videolan.vlc.providers.medialibrary.PlaylistsProvider;
import org.videolan.vlc.viewmodels.MedialibraryViewModel;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001cB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\u00180\u00170\u0016X\u0004¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001d"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/PlaylistsViewModel;", "Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "context", "Landroid/content/Context;", "type", "Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;", "(Landroid/content/Context;Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;)V", "displayModeKey", "", "getDisplayModeKey", "()Ljava/lang/String;", "provider", "Lorg/videolan/vlc/providers/medialibrary/PlaylistsProvider;", "getProvider", "()Lorg/videolan/vlc/providers/medialibrary/PlaylistsProvider;", "providerInCard", "", "getProviderInCard", "()Z", "setProviderInCard", "(Z)V", "providers", "", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getProviders", "()[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Factory", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistsViewModel.kt */
public final class PlaylistsViewModel extends MedialibraryViewModel {
    private final String displayModeKey;
    private final PlaylistsProvider provider;
    private boolean providerInCard = true;
    private final MedialibraryProvider<? extends MediaLibraryItem>[] providers;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlaylistsViewModel(Context context, Playlist.Type type) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(type, "type");
        String str = "display_mode_playlists_" + type;
        this.displayModeKey = str;
        PlaylistsProvider playlistsProvider = new PlaylistsProvider(context, this, type);
        this.provider = playlistsProvider;
        this.providers = new MedialibraryProvider[]{playlistsProvider};
        watchPlaylists();
        this.providerInCard = getSettings().getBoolean(str, this.providerInCard);
    }

    public final String getDisplayModeKey() {
        return this.displayModeKey;
    }

    public final PlaylistsProvider getProvider() {
        return this.provider;
    }

    public final boolean getProviderInCard() {
        return this.providerInCard;
    }

    public final void setProviderInCard(boolean z) {
        this.providerInCard = z;
    }

    public MedialibraryProvider<? extends MediaLibraryItem>[] getProviders() {
        return this.providers;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u000b\u001a\u0002H\f\"\b\b\u0000\u0010\f*\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\f0\u000fH\u0016¢\u0006\u0002\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lorg/videolan/vlc/viewmodels/mobile/PlaylistsViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "type", "Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;", "(Landroid/content/Context;Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;)V", "getContext", "()Landroid/content/Context;", "getType", "()Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PlaylistsViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Context context;
        private final Playlist.Type type;

        public Factory(Context context2, Playlist.Type type2) {
            Intrinsics.checkNotNullParameter(context2, "context");
            Intrinsics.checkNotNullParameter(type2, "type");
            this.context = context2;
            this.type = type2;
        }

        public final Context getContext() {
            return this.context;
        }

        public final Playlist.Type getType() {
            return this.type;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new PlaylistsViewModel(applicationContext, this.type);
        }
    }
}
