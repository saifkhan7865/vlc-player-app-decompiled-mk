package org.videolan.vlc.gui;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.Artist;
import org.videolan.medialibrary.interfaces.media.Genre;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.vlc.databinding.SearchActivityBinding;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.SearchActivity$performSearh$1", f = "SearchActivity.kt", i = {}, l = {121}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SearchActivity.kt */
final class SearchActivity$performSearh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $query;
    int label;
    final /* synthetic */ SearchActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SearchActivity$performSearh$1(SearchActivity searchActivity, String str, Continuation<? super SearchActivity$performSearh$1> continuation) {
        super(2, continuation);
        this.this$0 = searchActivity;
        this.$query = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchActivity$performSearh$1(this.this$0, this.$query, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchActivity$performSearh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        SearchActivityBinding searchActivityBinding = null;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            String str = this.$query;
            this.label = 1;
            obj = BuildersKt.withContext(Dispatchers.getIO(), new SearchActivity$performSearh$1$invokeSuspend$$inlined$getFromMl$1(this.this$0, (Continuation) null, str), this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        SearchAggregate searchAggregate = (SearchAggregate) obj;
        SearchActivityBinding access$getBinding$p = this.this$0.binding;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        access$getBinding$p.setSearchAggregate(searchAggregate);
        if (searchAggregate != null) {
            SearchActivity searchActivity = this.this$0;
            Album[] albums = searchAggregate.getAlbums();
            if (albums != null) {
                Intrinsics.checkNotNull(albums);
                List filterNotNull = ArraysKt.filterNotNull(albums);
                if (filterNotNull != null) {
                    SearchActivityBinding access$getBinding$p2 = searchActivity.binding;
                    if (access$getBinding$p2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p2 = null;
                    }
                    RecyclerView.Adapter adapter = access$getBinding$p2.albumsResults.getAdapter();
                    Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type org.videolan.vlc.gui.SearchResultAdapter");
                    ((SearchResultAdapter) adapter).add((MediaLibraryItem[]) filterNotNull.toArray(new MediaLibraryItem[0]));
                }
            }
            Artist[] artists = searchAggregate.getArtists();
            if (artists != null) {
                Intrinsics.checkNotNull(artists);
                List filterNotNull2 = ArraysKt.filterNotNull(artists);
                if (filterNotNull2 != null) {
                    SearchActivityBinding access$getBinding$p3 = searchActivity.binding;
                    if (access$getBinding$p3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p3 = null;
                    }
                    RecyclerView.Adapter adapter2 = access$getBinding$p3.artistsResults.getAdapter();
                    Intrinsics.checkNotNull(adapter2, "null cannot be cast to non-null type org.videolan.vlc.gui.SearchResultAdapter");
                    ((SearchResultAdapter) adapter2).add((MediaLibraryItem[]) filterNotNull2.toArray(new MediaLibraryItem[0]));
                }
            }
            Genre[] genres = searchAggregate.getGenres();
            if (genres != null) {
                Intrinsics.checkNotNull(genres);
                List filterNotNull3 = ArraysKt.filterNotNull(genres);
                if (filterNotNull3 != null) {
                    SearchActivityBinding access$getBinding$p4 = searchActivity.binding;
                    if (access$getBinding$p4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p4 = null;
                    }
                    RecyclerView.Adapter adapter3 = access$getBinding$p4.genresResults.getAdapter();
                    Intrinsics.checkNotNull(adapter3, "null cannot be cast to non-null type org.videolan.vlc.gui.SearchResultAdapter");
                    ((SearchResultAdapter) adapter3).add((MediaLibraryItem[]) filterNotNull3.toArray(new MediaLibraryItem[0]));
                }
            }
            Playlist[] playlists = searchAggregate.getPlaylists();
            if (playlists != null) {
                Intrinsics.checkNotNull(playlists);
                List filterNotNull4 = ArraysKt.filterNotNull(playlists);
                if (filterNotNull4 != null) {
                    SearchActivityBinding access$getBinding$p5 = searchActivity.binding;
                    if (access$getBinding$p5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p5 = null;
                    }
                    RecyclerView.Adapter adapter4 = access$getBinding$p5.playlistsResults.getAdapter();
                    Intrinsics.checkNotNull(adapter4, "null cannot be cast to non-null type org.videolan.vlc.gui.SearchResultAdapter");
                    ((SearchResultAdapter) adapter4).add((MediaLibraryItem[]) filterNotNull4.toArray(new MediaLibraryItem[0]));
                }
            }
            MediaWrapper[] videos = searchAggregate.getVideos();
            if (videos != null) {
                Intrinsics.checkNotNull(videos);
                List filterNotNull5 = ArraysKt.filterNotNull(videos);
                if (filterNotNull5 != null) {
                    SearchActivityBinding access$getBinding$p6 = searchActivity.binding;
                    if (access$getBinding$p6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p6 = null;
                    }
                    RecyclerView.Adapter adapter5 = access$getBinding$p6.othersResults.getAdapter();
                    Intrinsics.checkNotNull(adapter5, "null cannot be cast to non-null type org.videolan.vlc.gui.SearchResultAdapter");
                    ((SearchResultAdapter) adapter5).add((MediaLibraryItem[]) filterNotNull5.toArray(new MediaLibraryItem[0]));
                }
            }
            MediaWrapper[] tracks = searchAggregate.getTracks();
            if (tracks != null) {
                Intrinsics.checkNotNull(tracks);
                List filterNotNull6 = ArraysKt.filterNotNull(tracks);
                if (filterNotNull6 != null) {
                    SearchActivityBinding access$getBinding$p7 = searchActivity.binding;
                    if (access$getBinding$p7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        searchActivityBinding = access$getBinding$p7;
                    }
                    RecyclerView.Adapter adapter6 = searchActivityBinding.songsResults.getAdapter();
                    Intrinsics.checkNotNull(adapter6, "null cannot be cast to non-null type org.videolan.vlc.gui.SearchResultAdapter");
                    ((SearchResultAdapter) adapter6).add((MediaLibraryItem[]) filterNotNull6.toArray(new MediaLibraryItem[0]));
                }
            }
        }
        return Unit.INSTANCE;
    }
}
