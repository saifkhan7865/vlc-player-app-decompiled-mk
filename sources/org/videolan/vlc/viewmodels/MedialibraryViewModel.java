package org.videolan.vlc.viewmodels;

import android.content.Context;
import androidx.lifecycle.ViewModelKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0011H\u0016J\b\u0010\u0016\u001a\u00020\u0011H\u0016J\b\u0010\u0017\u001a\u00020\u0011H\u0016J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\b\u0010\u0019\u001a\u00020\u0011H\u0016J\b\u0010\u001a\u001a\u00020\u0011H\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0016J$\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\r0\u001f2\u0006\u0010 \u001a\u00020\u0011H@¢\u0006\u0002\u0010!J\u0012\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\u0006\u0010%\u001a\u00020\u0011J\u000e\u0010&\u001a\u00020\u00112\u0006\u0010'\u001a\u00020(J\u0006\u0010)\u001a\u00020\u0011J\b\u0010*\u001a\u00020\u001dH\u0014J\t\u0010+\u001a\u00020\u001dH\u0001J\b\u0010,\u001a\u00020\u001dH\u0016J\t\u0010-\u001a\u00020\u001dH\u0001J\b\u0010.\u001a\u00020\u001dH\u0016J\t\u0010/\u001a\u00020\u001dH\u0001J\u0010\u00100\u001a\u00020\u001d2\u0006\u00100\u001a\u00020(H\u0016J\t\u00101\u001a\u00020\u001dH\u0001J\t\u00102\u001a\u00020\u001dH\u0001J\t\u00103\u001a\u00020\u001dH\u0001J\t\u00104\u001a\u00020\u001dH\u0001J\t\u00105\u001a\u00020\u001dH\u0001J\t\u00106\u001a\u00020\u001dH\u0001J\t\u00107\u001a\u00020\u001dH\u0001J\t\u00108\u001a\u00020\u001dH\u0001J\u001b\u00109\u001a\u00020\u001d*\u00020:2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u001d0;H\u0001R\u0012\u0010\u0006\u001a\u00020\u0007X\u0005¢\u0006\u0006\u001a\u0004\b\b\u0010\tR \u0010\n\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\r0\f0\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006<"}, d2 = {"Lorg/videolan/vlc/viewmodels/MedialibraryViewModel;", "Lorg/videolan/vlc/viewmodels/SortableModel;", "Lorg/videolan/vlc/viewmodels/ICallBackHandler;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "providers", "", "Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "getProviders", "()[Lorg/videolan/vlc/providers/medialibrary/MedialibraryProvider;", "canSortByAlbum", "", "canSortByArtist", "canSortByDuration", "canSortByFileNameName", "canSortByFileSize", "canSortByInsertionDate", "canSortByLastModified", "canSortByMediaNumber", "canSortByName", "canSortByPlayCount", "canSortByReleaseDate", "changeFavorite", "", "tracks", "", "favorite", "(Ljava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "filter", "query", "", "isEmpty", "isEmptyAt", "index", "", "isFiltering", "onCleared", "pause", "refresh", "releaseCallbacks", "restore", "resume", "sort", "watchAlbums", "watchArtists", "watchFolders", "watchGenres", "watchHistory", "watchMedia", "watchMediaGroups", "watchPlaylists", "registerCallBacks", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/Function0;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MedialibraryViewModel.kt */
public abstract class MedialibraryViewModel extends SortableModel implements ICallBackHandler {
    private final /* synthetic */ CallBackDelegate $$delegate_0 = new CallBackDelegate();

    public Medialibrary getMedialibrary() {
        return this.$$delegate_0.getMedialibrary();
    }

    public abstract MedialibraryProvider<? extends MediaLibraryItem>[] getProviders();

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
    public MedialibraryViewModel(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        registerCallBacks(ViewModelKt.getViewModelScope(this), new Function0<Unit>(this) {
            final /* synthetic */ MedialibraryViewModel this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                this.this$0.refresh();
            }
        });
    }

    public void refresh() {
        for (MedialibraryProvider refresh : getProviders()) {
            refresh.refresh();
        }
    }

    public final boolean isEmpty() {
        for (MedialibraryProvider isEmpty : getProviders()) {
            if (!isEmpty.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public final boolean isEmptyAt(int i) {
        return getProviders()[i].isEmpty();
    }

    public void restore() {
        if (getFilterQuery() != null) {
            filter((String) null);
        }
    }

    public void filter(String str) {
        setFilterQuery(str);
        refresh();
    }

    public void sort(int i) {
        for (MedialibraryProvider sort : getProviders()) {
            sort.sort(i);
        }
    }

    public final boolean isFiltering() {
        return getFilterQuery() != null;
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        releaseCallbacks();
        super.onCleared();
    }

    public boolean canSortByName() {
        for (MedialibraryProvider canSortByName : getProviders()) {
            if (canSortByName.canSortByName()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByFileNameName() {
        for (MedialibraryProvider canSortByFileNameName : getProviders()) {
            if (canSortByFileNameName.canSortByFileNameName()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByDuration() {
        for (MedialibraryProvider canSortByDuration : getProviders()) {
            if (canSortByDuration.canSortByDuration()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByInsertionDate() {
        for (MedialibraryProvider canSortByInsertionDate : getProviders()) {
            if (canSortByInsertionDate.canSortByInsertionDate()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByLastModified() {
        for (MedialibraryProvider canSortByLastModified : getProviders()) {
            if (canSortByLastModified.canSortByLastModified()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByReleaseDate() {
        for (MedialibraryProvider canSortByReleaseDate : getProviders()) {
            if (canSortByReleaseDate.canSortByReleaseDate()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByFileSize() {
        for (MedialibraryProvider canSortByFileSize : getProviders()) {
            if (canSortByFileSize.canSortByFileSize()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByArtist() {
        for (MedialibraryProvider canSortByArtist : getProviders()) {
            if (canSortByArtist.canSortByArtist()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByAlbum() {
        for (MedialibraryProvider canSortByAlbum : getProviders()) {
            if (canSortByAlbum.canSortByAlbum()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByPlayCount() {
        for (MedialibraryProvider canSortByPlayCount : getProviders()) {
            if (canSortByPlayCount.canSortByPlayCount()) {
                return true;
            }
        }
        return false;
    }

    public boolean canSortByMediaNumber() {
        for (MedialibraryProvider canSortByMediaNumber : getProviders()) {
            if (canSortByMediaNumber.canSortByMediaNumber()) {
                return true;
            }
        }
        return false;
    }

    public final Object changeFavorite(List<? extends MediaLibraryItem> list, boolean z, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MedialibraryViewModel$changeFavorite$2(list, z, (Continuation<? super MedialibraryViewModel$changeFavorite$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }
}
