package org.videolan.moviepedia.provider;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.provider.datasources.MovieDataSourceFactory;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u0001¢\u0006\u0002\b\u000420\u0010\u0005\u001a,\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b \t*\u0015\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006¢\u0006\u0002\b\u00040\u0006¢\u0006\u0002\b\u0004H\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "Lkotlin/jvm/JvmSuppressWildcards;", "input", "Lkotlin/Pair;", "", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingMovieProvider.kt */
final class MediaScrapingMovieProvider$pagedList$1 extends Lambda implements Function1<Pair<Integer, Boolean>, LiveData<PagedList<MediaMetadataWithImages>>> {
    final /* synthetic */ MediaScrapingMovieProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingMovieProvider$pagedList$1(MediaScrapingMovieProvider mediaScrapingMovieProvider) {
        super(1);
        this.this$0 = mediaScrapingMovieProvider;
    }

    public final LiveData<PagedList<MediaMetadataWithImages>> invoke(Pair<Integer, Boolean> pair) {
        Context access$getContext$p = this.this$0.context;
        Intrinsics.checkNotNull(pair);
        return new LivePagedListBuilder(new MovieDataSourceFactory(access$getContext$p, pair, this.this$0.mediaType), new PagedList.Config.Builder().setInitialLoadSizeHint(1).setPageSize(20).build()).build();
    }
}
