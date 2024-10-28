package org.videolan.vlc.viewmodels;

import java.io.File;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.media.PlaylistManager;
import org.videolan.vlc.util.FileUtils;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0011\n\u0002\u0010\u0016\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u00062\u00020\u00072\u00020\b2\u00020\t2\u00020\n2\u00020\u000bB\u0005¢\u0006\u0002\u0010\fJ\u0010\u0010'\u001a\u00020&2\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020&H\u0016J\b\u0010+\u001a\u00020&H\u0016J\b\u0010,\u001a\u00020&H\u0016J\b\u0010-\u001a\u00020&H\u0016J\b\u0010.\u001a\u00020&H\u0016J\b\u0010/\u001a\u00020&H\u0016J\b\u00100\u001a\u00020&H\u0016J\b\u00101\u001a\u00020&H\u0016J\b\u00102\u001a\u00020&H\u0016J\b\u00103\u001a\u00020&H\u0016J\b\u00104\u001a\u00020&H\u0016J\b\u00105\u001a\u00020&H\u0016J\b\u00106\u001a\u00020&H\u0016J\b\u00107\u001a\u00020&H\u0016J\b\u00108\u001a\u00020&H\u0016J\u0010\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u0010<\u001a\u00020&2\u0006\u0010:\u001a\u00020;H\u0016J\b\u0010=\u001a\u00020&H\u0016J\b\u0010>\u001a\u00020&H\u0016J\b\u0010?\u001a\u00020&H\u0016J\b\u0010@\u001a\u00020&H\u0016J\b\u0010A\u001a\u00020&H\u0016J\b\u0010B\u001a\u00020&H\u0016J\b\u0010C\u001a\u00020&H\u0016J\b\u0010D\u001a\u00020&H\u0016J\b\u0010E\u001a\u00020&H\u0016J\b\u0010F\u001a\u00020&H\u0016J\b\u0010G\u001a\u00020&H\u0016J\b\u0010H\u001a\u00020&H\u0016J\b\u0010I\u001a\u00020&H\u0016J\b\u0010J\u001a\u00020&H\u0016J\b\u0010K\u001a\u00020&H\u0016J\b\u0010L\u001a\u00020&H\u0016J\b\u0010M\u001a\u00020&H\u0016J\b\u0010N\u001a\u00020&H\u0016J\b\u0010O\u001a\u00020&H\u0016J\b\u0010P\u001a\u00020&H\u0016J\u001a\u0010Q\u001a\u00020&*\u00020R2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020&0TH\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\u00020\u001dX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR$\u0010!\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u000e@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0017\"\u0004\b#\u0010\u0019R\u000e\u0010$\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0011X.¢\u0006\u0002\n\u0000¨\u0006U"}, d2 = {"Lorg/videolan/vlc/viewmodels/CallBackDelegate;", "Lorg/videolan/vlc/viewmodels/ICallBackHandler;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$OnMedialibraryReadyListener;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$OnDeviceChangeListener;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$MediaCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$ArtistsCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$AlbumsCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$GenresCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$PlaylistsCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$HistoryCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$MediaGroupCb;", "Lorg/videolan/medialibrary/interfaces/Medialibrary$FoldersCb;", "()V", "albumsCb", "", "artistsCb", "deleteActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/viewmodels/MediaAction;", "foldersCb", "genresCb", "historyCb", "isInvalid", "()Z", "setInvalid", "(Z)V", "mediaCb", "mediaGroupsCb", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "value", "paused", "getPaused", "setPaused", "playlistsCb", "refreshActor", "", "deleteThumbnail", "mediaId", "", "onAlbumsAdded", "onAlbumsDeleted", "onAlbumsModified", "onArtistsAdded", "onArtistsDeleted", "onArtistsModified", "onDeviceChange", "onFoldersAdded", "onFoldersDeleted", "onFoldersModified", "onGenresAdded", "onGenresDeleted", "onGenresModified", "onHistoryModified", "onMediaAdded", "onMediaConvertedToExternal", "ids", "", "onMediaDeleted", "onMediaGroupsAdded", "onMediaGroupsDeleted", "onMediaGroupsModified", "onMediaModified", "onMedialibraryIdle", "onMedialibraryReady", "onPlaylistsAdded", "onPlaylistsDeleted", "onPlaylistsModified", "pause", "releaseCallbacks", "resume", "watchAlbums", "watchArtists", "watchFolders", "watchGenres", "watchHistory", "watchMedia", "watchMediaGroups", "watchPlaylists", "registerCallBacks", "Lkotlinx/coroutines/CoroutineScope;", "refresh", "Lkotlin/Function0;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CallBackDelegate.kt */
public final class CallBackDelegate implements ICallBackHandler, Medialibrary.OnMedialibraryReadyListener, Medialibrary.OnDeviceChangeListener, Medialibrary.MediaCb, Medialibrary.ArtistsCb, Medialibrary.AlbumsCb, Medialibrary.GenresCb, Medialibrary.PlaylistsCb, Medialibrary.HistoryCb, Medialibrary.MediaGroupCb, Medialibrary.FoldersCb {
    private boolean albumsCb;
    private boolean artistsCb;
    private SendChannel<? super MediaAction> deleteActor;
    private boolean foldersCb;
    private boolean genresCb;
    private boolean historyCb;
    private boolean isInvalid;
    private boolean mediaCb;
    private boolean mediaGroupsCb;
    private final Medialibrary medialibrary;
    private boolean paused;
    private boolean playlistsCb;
    private SendChannel<? super Unit> refreshActor;

    public CallBackDelegate() {
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.medialibrary = instance;
    }

    public Medialibrary getMedialibrary() {
        return this.medialibrary;
    }

    public final boolean getPaused() {
        return this.paused;
    }

    public final void setPaused(boolean z) {
        this.paused = z;
        if (!z && this.isInvalid) {
            SendChannel<? super Unit> sendChannel = this.refreshActor;
            if (sendChannel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
                sendChannel = null;
            }
            sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
            this.isInvalid = false;
        }
    }

    public final boolean isInvalid() {
        return this.isInvalid;
    }

    public final void setInvalid(boolean z) {
        this.isInvalid = z;
    }

    public void pause() {
        setPaused(true);
    }

    public void resume() {
        setPaused(false);
    }

    public void registerCallBacks(CoroutineScope coroutineScope, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(function0, "refresh");
        CoroutineScope coroutineScope2 = coroutineScope;
        this.refreshActor = KotlinExtensionsKt.conflatedActor$default(coroutineScope2, 0, new CallBackDelegate$registerCallBacks$1(this, function0, (Continuation<? super CallBackDelegate$registerCallBacks$1>) null), 1, (Object) null);
        this.deleteActor = ActorKt.actor$default(coroutineScope2, Dispatchers.getIO(), Integer.MAX_VALUE, (CoroutineStart) null, (Function1) null, new CallBackDelegate$registerCallBacks$2(this, (Continuation<? super CallBackDelegate$registerCallBacks$2>) null), 12, (Object) null);
        getMedialibrary().addOnMedialibraryReadyListener(this);
        getMedialibrary().addOnDeviceChangeListener(this);
    }

    /* access modifiers changed from: private */
    public final void deleteThumbnail(long j) {
        MediaWrapper media;
        File externalFilesDir = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
        if (externalFilesDir != null) {
            File file = new File(externalFilesDir.getAbsolutePath() + "/medialib/" + j + ".jpg");
            if (file.exists() && (media = getMedialibrary().getMedia(j)) != null) {
                media.removeThumbnail();
            }
            FileUtils.INSTANCE.deleteFile(file);
        }
    }

    public void watchMedia() {
        getMedialibrary().addMediaCb(this);
        this.mediaCb = true;
    }

    public void watchArtists() {
        getMedialibrary().addArtistsCb(this);
        this.artistsCb = true;
    }

    public void watchAlbums() {
        getMedialibrary().addAlbumsCb(this);
        this.albumsCb = true;
    }

    public void watchGenres() {
        getMedialibrary().addGenreCb(this);
        this.genresCb = true;
    }

    public void watchPlaylists() {
        getMedialibrary().addPlaylistCb(this);
        this.playlistsCb = true;
    }

    public void watchHistory() {
        getMedialibrary().addHistoryCb(this);
        this.historyCb = true;
    }

    public void watchMediaGroups() {
        getMedialibrary().addMediaGroupCb(this);
        this.mediaGroupsCb = true;
    }

    public void watchFolders() {
        getMedialibrary().addFoldersCb(this);
        this.foldersCb = true;
    }

    public void releaseCallbacks() {
        getMedialibrary().removeOnMedialibraryReadyListener(this);
        getMedialibrary().removeOnDeviceChangeListener(this);
        if (this.mediaCb) {
            getMedialibrary().removeMediaCb(this);
        }
        if (this.artistsCb) {
            getMedialibrary().removeArtistsCb(this);
        }
        if (this.albumsCb) {
            getMedialibrary().removeAlbumsCb(this);
        }
        if (this.genresCb) {
            getMedialibrary().removeGenreCb(this);
        }
        if (this.playlistsCb) {
            getMedialibrary().removePlaylistCb(this);
        }
        if (this.historyCb) {
            getMedialibrary().removeHistoryCb(this);
        }
        if (this.mediaGroupsCb) {
            getMedialibrary().removeMediaGroupCb(this);
        }
        if (this.foldersCb) {
            getMedialibrary().removeFoldersCb(this);
        }
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        SendChannel.DefaultImpls.close$default(sendChannel, (Throwable) null, 1, (Object) null);
    }

    public void onMedialibraryReady() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMedialibraryIdle() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onDeviceChange() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaModified() {
        if (PlaylistManager.Companion.getSkipMediaUpdateRefresh()) {
            PlaylistManager.Companion.setSkipMediaUpdateRefresh(false);
            return;
        }
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaDeleted(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "ids");
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        SendChannel<? super MediaAction> sendChannel2 = null;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
        SendChannel<? super MediaAction> sendChannel3 = this.deleteActor;
        if (sendChannel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deleteActor");
        } else {
            sendChannel2 = sendChannel3;
        }
        sendChannel2.m1139trySendJP2dKIU(new MediaDeletedAction(jArr));
    }

    public void onMediaConvertedToExternal(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "ids");
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        SendChannel<? super MediaAction> sendChannel2 = null;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
        SendChannel<? super MediaAction> sendChannel3 = this.deleteActor;
        if (sendChannel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deleteActor");
        } else {
            sendChannel2 = sendChannel3;
        }
        sendChannel2.m1139trySendJP2dKIU(new MediaConvertedExternalAction(jArr));
    }

    public void onFoldersAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onFoldersModified() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onFoldersDeleted() {
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

    public void onHistoryModified() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaGroupsAdded() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaGroupsModified() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }

    public void onMediaGroupsDeleted() {
        SendChannel<? super Unit> sendChannel = this.refreshActor;
        if (sendChannel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("refreshActor");
            sendChannel = null;
        }
        sendChannel.m1139trySendJP2dKIU(Unit.INSTANCE);
    }
}
