package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0007H&J\b\u0010\t\u001a\u00020\u0007H&J\b\u0010\n\u001a\u00020\u0007H&J\b\u0010\u000b\u001a\u00020\u0007H&J\b\u0010\f\u001a\u00020\u0007H&J\b\u0010\r\u001a\u00020\u0007H&J\b\u0010\u000e\u001a\u00020\u0007H&J\b\u0010\u000f\u001a\u00020\u0007H&J\b\u0010\u0010\u001a\u00020\u0007H&J\b\u0010\u0011\u001a\u00020\u0007H&J\u001a\u0010\u0012\u001a\u00020\u0007*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/viewmodels/ICallBackHandler;", "", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "pause", "", "releaseCallbacks", "resume", "watchAlbums", "watchArtists", "watchFolders", "watchGenres", "watchHistory", "watchMedia", "watchMediaGroups", "watchPlaylists", "registerCallBacks", "Lkotlinx/coroutines/CoroutineScope;", "refresh", "Lkotlin/Function0;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CallBackDelegate.kt */
public interface ICallBackHandler {
    Medialibrary getMedialibrary();

    void pause();

    void registerCallBacks(CoroutineScope coroutineScope, Function0<Unit> function0);

    void releaseCallbacks();

    void resume();

    void watchAlbums();

    void watchArtists();

    void watchFolders();

    void watchGenres();

    void watchHistory();

    void watchMedia();

    void watchMediaGroups();

    void watchPlaylists();
}
