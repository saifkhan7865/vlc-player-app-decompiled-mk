package org.videolan.vlc.providers.medialibrary;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.tools.Settings;
import org.videolan.vlc.viewmodels.SortableModel;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0013\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¢\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0011H\u0016R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/videolan/vlc/providers/medialibrary/PlaylistsProvider;", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/interfaces/media/Playlist;", "context", "Landroid/content/Context;", "model", "Lorg/videolan/vlc/viewmodels/SortableModel;", "type", "Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;", "(Landroid/content/Context;Lorg/videolan/vlc/viewmodels/SortableModel;Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;)V", "getType", "()Lorg/videolan/medialibrary/interfaces/media/Playlist$Type;", "getAll", "", "()[Lorg/videolan/medialibrary/interfaces/media/Playlist;", "getPage", "loadSize", "", "startposition", "(II)[Lorg/videolan/medialibrary/interfaces/media/Playlist;", "getTotalCount", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistsProvider.kt */
public final class PlaylistsProvider extends MedialibraryProvider<Playlist> {
    private final Playlist.Type type;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public PlaylistsProvider(Context context, SortableModel sortableModel, Playlist.Type type2) {
        super(context, sortableModel);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(sortableModel, "model");
        Intrinsics.checkNotNullParameter(type2, "type");
        this.type = type2;
    }

    public final Playlist.Type getType() {
        return this.type;
    }

    public Playlist[] getAll() {
        Playlist[] playlists = getMedialibrary().getPlaylists(this.type, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites());
        Intrinsics.checkNotNullExpressionValue(playlists, "getPlaylists(...)");
        return playlists;
    }

    public Playlist[] getPage(int i, int i2) {
        Playlist[] playlistArr;
        if (getModel().getFilterQuery() == null) {
            playlistArr = getMedialibrary().getPagedPlaylists(this.type, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        } else {
            playlistArr = getMedialibrary().searchPlaylist(getModel().getFilterQuery(), this.type, getSort(), getDesc(), Settings.INSTANCE.getIncludeMissing(), getOnlyFavorites(), i, i2);
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(getModel()), (CoroutineContext) null, (CoroutineStart) null, new PlaylistsProvider$getPage$1(this, playlistArr, i2, (Continuation<? super PlaylistsProvider$getPage$1>) null), 3, (Object) null);
        Intrinsics.checkNotNull(playlistArr);
        return playlistArr;
    }

    public int getTotalCount() {
        return getModel().getFilterQuery() == null ? getMedialibrary().getPlaylistsCount() : getMedialibrary().getPlaylistsCount(getModel().getFilterQuery());
    }
}
