package org.videolan.moviepedia.repository;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.sqlite.db.SimpleSQLiteQuery;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.moviepedia.database.MediaImageDao;
import org.videolan.moviepedia.database.MediaMetadataDao;
import org.videolan.moviepedia.database.MediaMetadataDataFullDao;
import org.videolan.moviepedia.database.models.MediaImage;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.MediaMetadataType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;
import org.videolan.tools.IOScopedObject;
import org.videolan.tools.SingletonHolder;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 32\u00020\u0001:\u00013B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007J\u0016\u0010\u0012\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0007J\"\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018H\u0007J\u0012\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\f0\u001bJ \u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\f0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000f0\fJ\u001c\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\f0\u001b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0007J\u0012\u0010\u001f\u001a\u0004\u0018\u00010\u00142\u0006\u0010 \u001a\u00020\u0016H\u0007J\u0012\u0010!\u001a\u0004\u0018\u00010\u00142\u0006\u0010 \u001a\u00020\u000fH\u0007J\u0018\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001b2\u0006\u0010 \u001a\u00020\u0016H\u0007J\u0018\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00140\u001b2\u0006\u0010 \u001a\u00020\u000fH\u0007J\b\u0010$\u001a\u00020\u0018H\u0007J*\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00140&2\u0006\u0010'\u001a\u00020\u00162\u0006\u0010(\u001a\u00020\u00162\u0006\u0010)\u001a\u00020*J\u0012\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\f0\u001bJ\u0014\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00140\f2\u0006\u0010-\u001a\u00020\u0016J\u0010\u0010.\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0014\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00140\u001b2\u0006\u0010\u0015\u001a\u00020\u0016J\b\u00100\u001a\u00020\u0018H\u0007J\u0014\u00101\u001a\b\u0012\u0004\u0012\u00020\u00140\f2\u0006\u00102\u001a\u00020\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lorg/videolan/moviepedia/repository/MediaMetadataRepository;", "Lorg/videolan/tools/IOScopedObject;", "mediaMetadataFullDao", "Lorg/videolan/moviepedia/database/MediaMetadataDataFullDao;", "mediaMetadataDao", "Lorg/videolan/moviepedia/database/MediaMetadataDao;", "mediaImageDao", "Lorg/videolan/moviepedia/database/MediaImageDao;", "(Lorg/videolan/moviepedia/database/MediaMetadataDataFullDao;Lorg/videolan/moviepedia/database/MediaMetadataDao;Lorg/videolan/moviepedia/database/MediaImageDao;)V", "addImagesImmediate", "", "images", "", "Lorg/videolan/moviepedia/database/models/MediaImage;", "addMetadataImmediate", "", "mediaMetadata", "Lorg/videolan/moviepedia/database/models/MediaMetadata;", "deleteImages", "findNextEpisode", "Lorg/videolan/moviepedia/database/models/MediaMetadataWithImages;", "showId", "", "season", "", "episode", "getAllLive", "Landroidx/lifecycle/LiveData;", "getByIds", "mlids", "getEpisodesLive", "getMediaById", "mediaId", "getMetadata", "getMetadataLive", "getMetadataLiveByML", "getMovieCount", "getMoviePagedList", "Landroidx/paging/DataSource$Factory;", "sortField", "sortType", "metadataType", "Lorg/videolan/moviepedia/database/models/MediaMetadataType;", "getRecentlyAdded", "getTvShowEpisodes", "tvshowId", "getTvshow", "getTvshowLive", "getTvshowsCount", "searchMedia", "sanitizedQuery", "Companion", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaMetadataRepository.kt */
public final class MediaMetadataRepository extends IOScopedObject {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final MediaImageDao mediaImageDao;
    private final MediaMetadataDao mediaMetadataDao;
    private final MediaMetadataDataFullDao mediaMetadataFullDao;

    public MediaMetadataRepository(MediaMetadataDataFullDao mediaMetadataDataFullDao, MediaMetadataDao mediaMetadataDao2, MediaImageDao mediaImageDao2) {
        Intrinsics.checkNotNullParameter(mediaMetadataDataFullDao, "mediaMetadataFullDao");
        Intrinsics.checkNotNullParameter(mediaMetadataDao2, "mediaMetadataDao");
        Intrinsics.checkNotNullParameter(mediaImageDao2, "mediaImageDao");
        this.mediaMetadataFullDao = mediaMetadataDataFullDao;
        this.mediaMetadataDao = mediaMetadataDao2;
        this.mediaImageDao = mediaImageDao2;
    }

    public final long addMetadataImmediate(MediaMetadata mediaMetadata) {
        Intrinsics.checkNotNullParameter(mediaMetadata, "mediaMetadata");
        return this.mediaMetadataDao.insert(mediaMetadata);
    }

    public final void addImagesImmediate(List<MediaImage> list) {
        Intrinsics.checkNotNullParameter(list, "images");
        this.mediaImageDao.insertAll(list);
    }

    public final void deleteImages(List<MediaImage> list) {
        Intrinsics.checkNotNullParameter(list, "images");
        this.mediaImageDao.deleteAll(list);
    }

    public final LiveData<MediaMetadataWithImages> getMetadataLiveByML(long j) {
        return this.mediaMetadataFullDao.getMetadataLiveByML(j);
    }

    public final MediaMetadataWithImages findNextEpisode(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "showId");
        return this.mediaMetadataFullDao.findNextEpisode(str, i, i2);
    }

    public final LiveData<MediaMetadataWithImages> getMetadataLive(String str) {
        Intrinsics.checkNotNullParameter(str, "mediaId");
        return this.mediaMetadataFullDao.getMediaLive(str);
    }

    public final LiveData<List<MediaMetadataWithImages>> getEpisodesLive(String str) {
        Intrinsics.checkNotNullParameter(str, "showId");
        return this.mediaMetadataFullDao.getEpisodesLive(str);
    }

    public final int getMovieCount() {
        return this.mediaMetadataFullDao.getMovieCount();
    }

    public final int getTvshowsCount() {
        return this.mediaMetadataFullDao.getTvshowsCount();
    }

    public final MediaMetadataWithImages getMetadata(long j) {
        return this.mediaMetadataFullDao.getMedia(j);
    }

    public final MediaMetadataWithImages getMediaById(String str) {
        Intrinsics.checkNotNullParameter(str, "mediaId");
        return this.mediaMetadataFullDao.getMediaById(str);
    }

    public final DataSource.Factory<Integer, MediaMetadataWithImages> getMoviePagedList(String str, String str2, MediaMetadataType mediaMetadataType) {
        Intrinsics.checkNotNullParameter(str, "sortField");
        Intrinsics.checkNotNullParameter(str2, "sortType");
        Intrinsics.checkNotNullParameter(mediaMetadataType, "metadataType");
        return this.mediaMetadataFullDao.getAllPaged(new SimpleSQLiteQuery("SELECT * FROM media_metadata WHERE type = " + mediaMetadataType.getKey() + " ORDER BY " + str + ' ' + str2));
    }

    public final LiveData<List<MediaMetadataWithImages>> getAllLive() {
        return this.mediaMetadataFullDao.getAllLive();
    }

    public final MediaMetadataWithImages getTvshow(String str) {
        Intrinsics.checkNotNullParameter(str, "showId");
        return this.mediaMetadataFullDao.getMediaById(str);
    }

    public final LiveData<MediaMetadataWithImages> getTvshowLive(String str) {
        Intrinsics.checkNotNullParameter(str, "showId");
        return this.mediaMetadataFullDao.getMediaByIdLive(str);
    }

    public final LiveData<List<MediaMetadataWithImages>> getByIds(List<Long> list) {
        Intrinsics.checkNotNullParameter(list, "mlids");
        return this.mediaMetadataFullDao.getByIds(list);
    }

    public final LiveData<List<MediaMetadataWithImages>> getRecentlyAdded() {
        return this.mediaMetadataFullDao.getRecentlyAdded();
    }

    public final List<MediaMetadataWithImages> searchMedia(String str) {
        Intrinsics.checkNotNullParameter(str, "sanitizedQuery");
        return this.mediaMetadataFullDao.searchMedia(str);
    }

    public final List<MediaMetadataWithImages> getTvShowEpisodes(String str) {
        Intrinsics.checkNotNullParameter(str, "tvshowId");
        return this.mediaMetadataFullDao.getTvShowEpisodes(str);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/moviepedia/repository/MediaMetadataRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/moviepedia/repository/MediaMetadataRepository;", "Landroid/content/Context;", "()V", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaMetadataRepository.kt */
    public static final class Companion extends SingletonHolder<MediaMetadataRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
