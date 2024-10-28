package org.videolan.moviepedia.provider;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.PagedList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadataType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/videolan/moviepedia/provider/MediaScrapingMovieProvider;", "Lorg/videolan/moviepedia/provider/MediaScrapingProvider;", "context", "Landroid/content/Context;", "mediaType", "Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "(Landroid/content/Context;Lorg/videolan/moviepedia/database/models/MediaMetadataType;)V", "pagedList", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "getPagedList", "()Landroidx/lifecycle/LiveData;", "setPagedList", "(Landroidx/lifecycle/LiveData;)V", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingMovieProvider.kt */
public final class MediaScrapingMovieProvider extends MediaScrapingProvider {
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final MediaMetadataType mediaType;
    private LiveData<PagedList<MediaMetadataWithImages>> pagedList;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaScrapingMovieProvider(Context context2, MediaMetadataType mediaMetadataType) {
        super(context2);
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(mediaMetadataType, "mediaType");
        this.context = context2;
        this.mediaType = mediaMetadataType;
        LiveData<PagedList<MediaMetadataWithImages>> switchMap = Transformations.switchMap(getSortQuery(), new MediaScrapingMovieProvider$pagedList$1(this));
        switchMap.observeForever(new MediaScrapingMovieProvider$sam$androidx_lifecycle_Observer$0(new MediaScrapingMovieProvider$pagedList$2$1(this)));
        this.pagedList = switchMap;
    }

    public LiveData<PagedList<MediaMetadataWithImages>> getPagedList() {
        return this.pagedList;
    }

    public void setPagedList(LiveData<PagedList<MediaMetadataWithImages>> liveData) {
        Intrinsics.checkNotNullParameter(liveData, "<set-?>");
        this.pagedList = liveData;
    }
}
