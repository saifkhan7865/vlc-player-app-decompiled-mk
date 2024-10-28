package org.videolan.moviepedia.provider;

import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Landroidx/paging/PagedList;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingMovieProvider.kt */
final class MediaScrapingMovieProvider$pagedList$2$1 extends Lambda implements Function1<PagedList<MediaMetadataWithImages>, Unit> {
    final /* synthetic */ MediaScrapingMovieProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingMovieProvider$pagedList$2$1(MediaScrapingMovieProvider mediaScrapingMovieProvider) {
        super(1);
        this.this$0 = mediaScrapingMovieProvider;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((PagedList<MediaMetadataWithImages>) (PagedList) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(PagedList<MediaMetadataWithImages> pagedList) {
        MediaScrapingMovieProvider mediaScrapingMovieProvider = this.this$0;
        Intrinsics.checkNotNull(pagedList);
        mediaScrapingMovieProvider.completeHeaders((MediaMetadataWithImages[]) pagedList.toArray(new MediaMetadataWithImages[0]), 0);
        this.this$0.getLoading().postValue(false);
    }
}
