package org.videolan.vlc;

import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.tools.KotlinExtensionsKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0016\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u0011\u001a\u00020\rH\u0016J\b\u0010\u0012\u001a\u00020\rH\u0016J\b\u0010\u0013\u001a\u00020\rH\u0016J\b\u0010\u0014\u001a\u00020\rH\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\rH\u0016J\b\u0010\u0019\u001a\u00020\rH\u0016J\b\u0010\u001a\u001a\u00020\rH\u0016J\b\u0010\u001b\u001a\u00020\rH\u0016J\u0012\u0010\u001c\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\r2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010 \u001a\u00020\rH\u0016J\b\u0010!\u001a\u00020\rH\u0016J\b\u0010\"\u001a\u00020\rH\u0016J\b\u0010#\u001a\u00020\rH\u0016J\u0006\u0010$\u001a\u00020\rJ\u0016\u0010%\u001a\u00020\r2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0'H\u0016J\u0016\u0010(\u001a\u00020\r2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\r0'H\u0016J\b\u0010)\u001a\u00020\rH\u0016R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fX.¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/videolan/vlc/MediaBrowserCallback;", "Lorg/videolan/vlc/IMediaBrowserCallback;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$MediaCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$ArtistsCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$AlbumsCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$GenresCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$PlaylistsCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$HistoryCb;", "playbackService", "Lorg/videolan/vlc/PlaybackService;", "(Lorg/videolan/vlc/PlaybackService;)V", "historyActor", "Lkotlinx/coroutines/channels/SendChannel;", "", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "refreshActor", "onAlbumsAdded", "onAlbumsDeleted", "onAlbumsModified", "onArtistsAdded", "onArtistsDeleted", "onArtistsModified", "onGenresAdded", "onGenresDeleted", "onGenresModified", "onHistoryModified", "onMediaAdded", "onMediaConvertedToExternal", "id", "", "onMediaDeleted", "onMediaModified", "onPlaylistsAdded", "onPlaylistsDeleted", "onPlaylistsModified", "onShuffleChanged", "registerHistoryCallback", "callback", "Lkotlin/Function0;", "registerMediaCallback", "removeCallbacks", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaBrowserCallback.kt */
public final class MediaBrowserCallback implements IMediaBrowserCallback, Medialibrary.MediaCb, Medialibrary.ArtistsCb, Medialibrary.AlbumsCb, Medialibrary.GenresCb, Medialibrary.PlaylistsCb, Medialibrary.HistoryCb {
    private SendChannel<? super Unit> historyActor;
    private final Medialibrary medialibrary;
    private final PlaybackService playbackService;
    private SendChannel<? super Unit> refreshActor;

    public void onMediaModified() {
    }

    public MediaBrowserCallback(PlaybackService playbackService2) {
        Intrinsics.checkNotNullParameter(playbackService2, "playbackService");
        this.playbackService = playbackService2;
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.medialibrary = instance;
    }

    public void registerHistoryCallback(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "callback");
        this.historyActor = KotlinExtensionsKt.conflatedActor$default(LifecycleOwnerKt.getLifecycleScope(this.playbackService), 0, new MediaBrowserCallback$registerHistoryCallback$1(function0, (Continuation<? super MediaBrowserCallback$registerHistoryCallback$1>) null), 1, (Object) null);
        this.medialibrary.addHistoryCb(this);
    }

    public void onHistoryModified() {
        SendChannel<? super Unit> sendChannel = this.historyActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void registerMediaCallback(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "callback");
        this.refreshActor = KotlinExtensionsKt.conflatedActor$default(LifecycleOwnerKt.getLifecycleScope(this.playbackService), 0, new MediaBrowserCallback$registerMediaCallback$1(function0, (Continuation<? super MediaBrowserCallback$registerMediaCallback$1>) null), 1, (Object) null);
        this.medialibrary.addMediaCb(this);
        this.medialibrary.addArtistsCb(this);
        this.medialibrary.addAlbumsCb(this);
        this.medialibrary.addGenreCb(this);
        this.medialibrary.addPlaylistCb(this);
    }

    public void onMediaAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaDeleted(long[] jArr) {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaConvertedToExternal(long[] jArr) {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onArtistsAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onArtistsModified() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onArtistsDeleted() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onAlbumsAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onAlbumsModified() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onAlbumsDeleted() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onGenresAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onGenresModified() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onGenresDeleted() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onPlaylistsAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onPlaylistsModified() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onPlaylistsDeleted() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public final void onShuffleChanged() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void removeCallbacks() {
        if (this.refreshActor != null) {
            this.medialibrary.removeMediaCb(this);
            this.medialibrary.removeArtistsCb(this);
            this.medialibrary.removeAlbumsCb(this);
            this.medialibrary.removeGenreCb(this);
            this.medialibrary.removePlaylistCb(this);
            SendChannel<? super Unit> sendChannel = this.refreshActor;
            if (sendChannel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
                sendChannel = null;
            }
            SendChannel.DefaultImpls.close$default(sendChannel, (Throwable) null, 1, (Object) null);
        }
        if (this.historyActor != null) {
            this.medialibrary.removeHistoryCb(this);
            SendChannel<? super Unit> sendChannel2 = this.historyActor;
            if (sendChannel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyActor");
                sendChannel2 = null;
            }
            SendChannel.DefaultImpls.close$default(sendChannel2, (Throwable) null, 1, (Object) null);
        }
    }
}
