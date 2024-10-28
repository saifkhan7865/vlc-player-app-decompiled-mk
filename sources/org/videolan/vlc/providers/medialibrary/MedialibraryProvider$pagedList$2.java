package org.videolan.vlc.providers.medialibrary;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListKt;
import androidx.paging.PagedList;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Landroidx/lifecycle/LiveData;", "Landroidx/paging/PagedList;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MedialibraryProvider.kt */
final class MedialibraryProvider$pagedList$2 extends Lambda implements Function0<LiveData<PagedList<T>>> {
    final /* synthetic */ MedialibraryProvider<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MedialibraryProvider$pagedList$2(MedialibraryProvider<T> medialibraryProvider) {
        super(0);
        this.this$0 = medialibraryProvider;
    }

    public final LiveData<PagedList<T>> invoke() {
        return LivePagedListKt.toLiveData$default((DataSource.Factory) new MedialibraryProvider.MLDatasourceFactory(), this.this$0.pagingConfig, (Object) null, (PagedList.BoundaryCallback) null, (Executor) null, 14, (Object) null);
    }
}
