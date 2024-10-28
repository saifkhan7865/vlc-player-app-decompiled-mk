package org.videolan.moviepedia.provider.datasources;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "it", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MovieDataSourceFactory.kt */
final class MovieDataSourceFactory$create$newDataSource$1 extends Lambda implements Function1<List<? extends MediaMetadataWithImages>, List<? extends MediaMetadataWithImages>> {
    public static final MovieDataSourceFactory$create$newDataSource$1 INSTANCE = new MovieDataSourceFactory$create$newDataSource$1();

    MovieDataSourceFactory$create$newDataSource$1() {
        super(1);
    }

    public final List<MediaMetadataWithImages> invoke(List<MediaMetadataWithImages> list) {
        Long mlId;
        Intrinsics.checkNotNullParameter(list, "it");
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        if (instance.isStarted()) {
            for (MediaMetadataWithImages mediaMetadataWithImages : list) {
                if (mediaMetadataWithImages.getMedia() == null && (mlId = mediaMetadataWithImages.getMetadata().getMlId()) != null) {
                    mediaMetadataWithImages.setMedia(instance.getMedia(mlId.longValue()));
                }
            }
        }
        return list;
    }
}
