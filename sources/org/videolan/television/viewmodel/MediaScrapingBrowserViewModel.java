package org.videolan.television.viewmodel;

import android.content.Context;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.moviepedia.database.models.MediaMetadataType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.provider.MediaScrapingMovieProvider;
import org.videolan.vlc.viewmodels.CallBackDelegate;
import org.videolan.vlc.viewmodels.ICallBackHandler;
import org.videolan.vlc.viewmodels.SortableModel;
import org.videolan.vlc.viewmodels.tv.TvBrowserModel;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u00022\u00020\u0004:\u00017B\u0015\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$H\u0016J\b\u0010%\u001a\u00020 H\u0016J\t\u0010&\u001a\u00020\"H\u0001J\b\u0010'\u001a\u00020\"H\u0016J\t\u0010(\u001a\u00020\"H\u0001J\b\u0010)\u001a\u00020\"H\u0016J\t\u0010*\u001a\u00020\"H\u0001J\u0010\u0010+\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u0016H\u0016J\t\u0010,\u001a\u00020\"H\u0001J\t\u0010-\u001a\u00020\"H\u0001J\t\u0010.\u001a\u00020\"H\u0001J\t\u0010/\u001a\u00020\"H\u0001J\t\u00100\u001a\u00020\"H\u0001J\t\u00101\u001a\u00020\"H\u0001J\t\u00102\u001a\u00020\"H\u0001J\t\u00103\u001a\u00020\"H\u0001J\u001b\u00104\u001a\u00020\"*\u0002052\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\"06H\u0001R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X\u0005¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u00068"}, d2 = {"Lorg/videolan/television/viewmodel/MediaScrapingBrowserViewModel;", "Lorg/videolan/vlc/viewmodels/SortableModel;", "Lorg/videolan/vlc/viewmodels/tv/TvBrowserModel;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "Lorg/videolan/vlc/viewmodels/ICallBackHandler;", "context", "Landroid/content/Context;", "category", "", "(Landroid/content/Context;J)V", "getCategory", "()J", "currentItem", "getCurrentItem", "()Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "setCurrentItem", "(Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;)V", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "getMedialibrary", "()Lorg/videolan/medialibrary/interfaces/Medialibrary;", "nbColumns", "", "getNbColumns", "()I", "setNbColumns", "(I)V", "provider", "Lorg/videolan/moviepedia/provider/MediaScrapingMovieProvider;", "getProvider", "()Lorg/videolan/moviepedia/provider/MediaScrapingMovieProvider;", "canSortByReleaseDate", "", "filter", "", "query", "", "isEmpty", "pause", "refresh", "releaseCallbacks", "restore", "resume", "sort", "watchAlbums", "watchArtists", "watchFolders", "watchGenres", "watchHistory", "watchMedia", "watchMediaGroups", "watchPlaylists", "registerCallBacks", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/Function0;", "Factory", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingBrowserViewModel.kt */
public final class MediaScrapingBrowserViewModel extends SortableModel implements TvBrowserModel<MediaMetadataWithImages>, ICallBackHandler {
    private final /* synthetic */ CallBackDelegate $$delegate_0 = new CallBackDelegate();
    private final long category;
    private MediaMetadataWithImages currentItem;
    private int nbColumns;
    private final MediaScrapingMovieProvider provider;

    public boolean canSortByReleaseDate() {
        return true;
    }

    public void filter(String str) {
    }

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

    public void restore() {
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
    public MediaScrapingBrowserViewModel(Context context, long j) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.category = j;
        registerCallBacks(ViewModelKt.getViewModelScope(this), new Function0<Unit>(this) {
            final /* synthetic */ MediaScrapingBrowserViewModel this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                if (Medialibrary.getInstance().isStarted()) {
                    this.this$0.refresh();
                }
            }
        });
        this.provider = new MediaScrapingMovieProvider(context, j == 31 ? MediaMetadataType.TV_SHOW : MediaMetadataType.MOVIE);
    }

    public final long getCategory() {
        return this.category;
    }

    public void refresh() {
        getProvider().refresh();
    }

    public boolean isEmpty() {
        PagedList value = getProvider().getPagedList().getValue();
        boolean z = false;
        if (value != null && !value.isEmpty()) {
            z = true;
        }
        return !z;
    }

    public MediaMetadataWithImages getCurrentItem() {
        return this.currentItem;
    }

    public void setCurrentItem(MediaMetadataWithImages mediaMetadataWithImages) {
        this.currentItem = mediaMetadataWithImages;
    }

    public int getNbColumns() {
        return this.nbColumns;
    }

    public void setNbColumns(int i) {
        this.nbColumns = i;
    }

    public MediaScrapingMovieProvider getProvider() {
        return this.provider;
    }

    public void sort(int i) {
        getProvider().sort(i);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\b0\u000bH\u0016¢\u0006\u0002\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/videolan/television/viewmodel/MediaScrapingBrowserViewModel$Factory;", "Landroidx/lifecycle/ViewModelProvider$NewInstanceFactory;", "context", "Landroid/content/Context;", "category", "", "(Landroid/content/Context;J)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingBrowserViewModel.kt */
    public static final class Factory extends ViewModelProvider.NewInstanceFactory {
        private final long category;
        private final Context context;

        public Factory(Context context2, long j) {
            Intrinsics.checkNotNullParameter(context2, "context");
            this.context = context2;
            this.category = j;
        }

        public <T extends ViewModel> T create(Class<T> cls) {
            Intrinsics.checkNotNullParameter(cls, "modelClass");
            Context applicationContext = this.context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            return (ViewModel) new MediaScrapingBrowserViewModel(applicationContext, this.category);
        }
    }
}
