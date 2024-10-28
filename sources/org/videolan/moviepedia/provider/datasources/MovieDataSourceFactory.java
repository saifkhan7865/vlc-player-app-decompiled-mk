package org.videolan.moviepedia.provider.datasources;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.models.MediaMetadataType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.moviepedia.repository.MediaMetadataRepository;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B)\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000eH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u000e0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/videolan/moviepedia/provider/datasources/MovieDataSourceFactory;", "Landroidx/paging/DataSource$Factory;", "", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "context", "Landroid/content/Context;", "sort", "Lkotlin/Pair;", "", "metadataType", "Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "(Landroid/content/Context;Lkotlin/Pair;Lorg/videolan/moviepedia/database/models/MediaMetadataType;)V", "dataSource", "Landroidx/lifecycle/MutableLiveData;", "Landroidx/paging/DataSource;", "create", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MovieDataSourceFactory.kt */
public final class MovieDataSourceFactory extends DataSource.Factory<Integer, MediaMetadataWithImages> {
    private final Context context;
    private final MutableLiveData<DataSource<Integer, MediaMetadataWithImages>> dataSource = new MutableLiveData<>();
    private final MediaMetadataType metadataType;
    private final Pair<Integer, Boolean> sort;

    public MovieDataSourceFactory(Context context2, Pair<Integer, Boolean> pair, MediaMetadataType mediaMetadataType) {
        Intrinsics.checkNotNullParameter(context2, "context");
        Intrinsics.checkNotNullParameter(pair, "sort");
        Intrinsics.checkNotNullParameter(mediaMetadataType, "metadataType");
        this.context = context2;
        this.sort = pair;
        this.metadataType = mediaMetadataType;
    }

    public DataSource<Integer, MediaMetadataWithImages> create() {
        int intValue = this.sort.getFirst().intValue();
        String str = "title";
        if (intValue != 0 && intValue == 5) {
            str = "releaseDate";
        }
        DataSource<Integer, MediaMetadataWithImages> create = ((MediaMetadataRepository) MediaMetadataRepository.Companion.getInstance(this.context)).getMoviePagedList(str, this.sort.getSecond().booleanValue() ? "DESC" : "ASC", this.metadataType).mapByPage((Function1) MovieDataSourceFactory$create$newDataSource$1.INSTANCE).create();
        this.dataSource.postValue(create);
        return create;
    }
}
