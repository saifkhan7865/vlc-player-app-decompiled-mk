package org.videolan.moviepedia.database;

import kotlin.Metadata;
import org.videolan.moviepedia.database.models.MediaMetadata;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0010\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0003H'¨\u0006\b"}, d2 = {"Lorg/videolan/moviepedia/database/MediaMetadataDao;", "", "getForMedia", "Lorg/videolan/moviepedia/database/models/MediaMetadata;", "id", "", "insert", "mediaMetadata", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataDao.kt */
public interface MediaMetadataDao {
    MediaMetadata getForMedia(long j);

    long insert(MediaMetadata mediaMetadata);
}
