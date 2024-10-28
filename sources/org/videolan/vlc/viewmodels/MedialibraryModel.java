package org.videolan.vlc.viewmodels;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.CoroutineContextProvider;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\u00020\u0004B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u000e\u001a\u00020\u000fH\u0014J\t\u0010\u0010\u001a\u00020\u000fH\u0001J\t\u0010\u0011\u001a\u00020\u000fH\u0001J\t\u0010\u0012\u001a\u00020\u000fH\u0001J\t\u0010\u0013\u001a\u00020\u000fH\u0001J\t\u0010\u0014\u001a\u00020\u000fH\u0001J\t\u0010\u0015\u001a\u00020\u000fH\u0001J\t\u0010\u0016\u001a\u00020\u000fH\u0001J\t\u0010\u0017\u001a\u00020\u000fH\u0001J\t\u0010\u0018\u001a\u00020\u000fH\u0001J\t\u0010\u0019\u001a\u00020\u000fH\u0001J\t\u0010\u001a\u001a\u00020\u000fH\u0001J\u001b\u0010\u001b\u001a\u00020\u000f*\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\u001eH\u0001R\u0012\u0010\n\u001a\u00020\u000bX\u0005¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001f"}, d2 = {"Lorg/videolan/vlc/viewmodels/MedialibraryModel;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lorg/videolan/vlc/viewmodels/BaseModel;", "Lorg/videolan/vlc/viewmodels/ICallBackHandler;", "context", "Landroid/content/Context;", "coroutineContextProvider", "Lorg/videolan/tools/CoroutineContextProvider;", "(Landroid/content/Context;Lorg/videolan/tools/CoroutineContextProvider;)V", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "onCleared", "", "pause", "releaseCallbacks", "resume", "watchAlbums", "watchArtists", "watchFolders", "watchGenres", "watchHistory", "watchMedia", "watchMediaGroups", "watchPlaylists", "registerCallBacks", "Lkotlinx/coroutines/CoroutineScope;", "refresh", "Lkotlin/Function0;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MedialibraryModel.kt */
public abstract class MedialibraryModel<T extends MediaLibraryItem> extends BaseModel<T> implements ICallBackHandler {
    private final /* synthetic */ CallBackDelegate $$delegate_0 = new CallBackDelegate();

    public Medialibrary getMedialibrary() {
        return this.$$delegate_0.getMedialibrary();
    }

    public void pause() {
        this.$$delegate_0.pause();
    }

    public void registerCallBacks(CoroutineScope coroutineScope, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(coroutineScope, "<this>");
        Intrinsics.checkNotNullParameter(function0, "refresh");
        this.$$delegate_0.registerCallBacks(coroutineScope, function0);
    }

    public void releaseCallbacks() {
        this.$$delegate_0.releaseCallbacks();
    }

    public void resume() {
        this.$$delegate_0.resume();
    }

    public void watchAlbums() {
        this.$$delegate_0.watchAlbums();
    }

    public void watchArtists() {
        this.$$delegate_0.watchArtists();
    }

    public void watchFolders() {
        this.$$delegate_0.watchFolders();
    }

    public void watchGenres() {
        this.$$delegate_0.watchGenres();
    }

    public void watchHistory() {
        this.$$delegate_0.watchHistory();
    }

    public void watchMedia() {
        this.$$delegate_0.watchMedia();
    }

    public void watchMediaGroups() {
        this.$$delegate_0.watchMediaGroups();
    }

    public void watchPlaylists() {
        this.$$delegate_0.watchPlaylists();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MedialibraryModel(Context context, CoroutineContextProvider coroutineContextProvider) {
        super(context, coroutineContextProvider);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(coroutineContextProvider, "coroutineContextProvider");
        registerCallBacks(ViewModelKt.getViewModelScope(this), new Function0<Unit>(this) {
            final /* synthetic */ MedialibraryModel<T> this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                this.this$0.refresh();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        releaseCallbacks();
        super.onCleared();
    }
}
