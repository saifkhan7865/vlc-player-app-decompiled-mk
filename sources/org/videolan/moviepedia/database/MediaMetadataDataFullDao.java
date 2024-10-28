package org.videolan.moviepedia.database;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.List;
import kotlin.Metadata;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u000f\bg\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H'J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b0\nH'J\u001c\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000fH'J\"\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b0\n2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u000bH'J\u001c\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b0\n2\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u0012H'J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u0005H'J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u0015\u001a\u00020\u0005H'J\u0018\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n2\u0006\u0010\u0015\u001a\u00020\u0005H'J\u0018\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\n2\u0006\u0010\u0015\u001a\u00020\u0012H'J\b\u0010\u001a\u001a\u00020\u0007H'J\u0014\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000b0\nH'J\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u001d\u001a\u00020\u0005H'J\b\u0010\u001e\u001a\u00020\u0007H'J\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010 \u001a\u00020\u0005H'¨\u0006!"}, d2 = {"Lorg/videolan/moviepedia/database/MediaMetadataDataFullDao;", "", "findNextEpisode", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "showId", "", "season", "", "episode", "getAllLive", "Landroidx/lifecycle/LiveData;", "", "getAllPaged", "Landroidx/paging/DataSource$Factory;", "query", "Landroidx/sqlite/db/SupportSQLiteQuery;", "getByIds", "mlids", "", "getEpisodesLive", "getMedia", "id", "getMediaById", "getMediaByIdLive", "getMediaLive", "getMetadataLiveByML", "getMovieCount", "getRecentlyAdded", "getTvShowEpisodes", "tvshowId", "getTvshowsCount", "searchMedia", "sanitizedQuery", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataDataFullDao.kt */
public interface MediaMetadataDataFullDao {
    MediaMetadataWithImages findNextEpisode(String str, int i, int i2);

    LiveData<List<MediaMetadataWithImages>> getAllLive();

    DataSource.Factory<Integer, MediaMetadataWithImages> getAllPaged(SupportSQLiteQuery supportSQLiteQuery);

    LiveData<List<MediaMetadataWithImages>> getByIds(List<Long> list);

    LiveData<List<MediaMetadataWithImages>> getEpisodesLive(String str);

    MediaMetadataWithImages getMedia(long j);

    MediaMetadataWithImages getMediaById(String str);

    LiveData<MediaMetadataWithImages> getMediaByIdLive(String str);

    LiveData<MediaMetadataWithImages> getMediaLive(String str);

    LiveData<MediaMetadataWithImages> getMetadataLiveByML(long j);

    int getMovieCount();

    LiveData<List<MediaMetadataWithImages>> getRecentlyAdded();

    List<MediaMetadataWithImages> getTvShowEpisodes(String str);

    int getTvshowsCount();

    List<MediaMetadataWithImages> searchMedia(String str);
}
