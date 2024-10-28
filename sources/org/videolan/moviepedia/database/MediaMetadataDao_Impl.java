package org.videolan.moviepedia.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.MediaMetadataType;

public final class MediaMetadataDao_Impl implements MediaMetadataDao {
    /* access modifiers changed from: private */
    public final Converters __converters = new Converters();
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<MediaMetadata> __insertionAdapterOfMediaMetadata;

    public MediaMetadataDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfMediaMetadata = new EntityInsertionAdapter<MediaMetadata>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `media_metadata` (`moviepedia_id`,`ml_id`,`type`,`title`,`summary`,`genres`,`releaseDate`,`countries`,`season`,`episode`,`current_poster`,`current_backdrop`,`show_id`,`has_cast`,`insertDate`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MediaMetadata mediaMetadata) {
                if (mediaMetadata.getMoviepediaId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, mediaMetadata.getMoviepediaId());
                }
                if (mediaMetadata.getMlId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindLong(2, mediaMetadata.getMlId().longValue());
                }
                supportSQLiteStatement.bindLong(3, (long) MediaMetadataDao_Impl.this.__converters.mediaTypeToKey(mediaMetadata.getType()));
                if (mediaMetadata.getTitle() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, mediaMetadata.getTitle());
                }
                if (mediaMetadata.getSummary() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, mediaMetadata.getSummary());
                }
                if (mediaMetadata.getGenres() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, mediaMetadata.getGenres());
                }
                Long dateToTimestamp = MediaMetadataDao_Impl.this.__converters.dateToTimestamp(mediaMetadata.getReleaseDate());
                if (dateToTimestamp == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindLong(7, dateToTimestamp.longValue());
                }
                if (mediaMetadata.getCountries() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, mediaMetadata.getCountries());
                }
                if (mediaMetadata.getSeason() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindLong(9, (long) mediaMetadata.getSeason().intValue());
                }
                if (mediaMetadata.getEpisode() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindLong(10, (long) mediaMetadata.getEpisode().intValue());
                }
                if (mediaMetadata.getCurrentPoster() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, mediaMetadata.getCurrentPoster());
                }
                if (mediaMetadata.getCurrentBackdrop() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, mediaMetadata.getCurrentBackdrop());
                }
                if (mediaMetadata.getShowId() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, mediaMetadata.getShowId());
                }
                supportSQLiteStatement.bindLong(14, mediaMetadata.getHasCast() ? 1 : 0);
                Long dateToTimestamp2 = MediaMetadataDao_Impl.this.__converters.dateToTimestamp(mediaMetadata.getInsertDate());
                if (dateToTimestamp2 == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindLong(15, dateToTimestamp2.longValue());
                }
            }
        };
    }

    public long insert(MediaMetadata mediaMetadata) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long insertAndReturnId = this.__insertionAdapterOfMediaMetadata.insertAndReturnId(mediaMetadata);
            this.__db.setTransactionSuccessful();
            return insertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    public MediaMetadata getForMedia(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        MediaMetadata mediaMetadata;
        String str;
        Long l;
        String str2;
        String str3;
        String str4;
        Long l2;
        String str5;
        Integer num;
        Integer num2;
        String str6;
        String str7;
        String str8;
        int i;
        Long l3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from media_metadata where ml_id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "moviepedia_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "ml_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "summary");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "genres");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "releaseDate");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "countries");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "season");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "episode");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "current_poster");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "current_backdrop");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "show_id");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                if (query.moveToFirst()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str = null;
                    } else {
                        str = query.getString(columnIndexOrThrow);
                    }
                    if (query.isNull(columnIndexOrThrow2)) {
                        l = null;
                    } else {
                        l = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    }
                    MediaMetadataType mediaTypeFromKey = this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
                    if (query.isNull(columnIndexOrThrow4)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow5);
                    }
                    if (query.isNull(columnIndexOrThrow6)) {
                        str4 = null;
                    } else {
                        str4 = query.getString(columnIndexOrThrow6);
                    }
                    if (query.isNull(columnIndexOrThrow7)) {
                        l2 = null;
                    } else {
                        l2 = Long.valueOf(query.getLong(columnIndexOrThrow7));
                    }
                    Date fromTimestamp = this.__converters.fromTimestamp(l2);
                    if (query.isNull(columnIndexOrThrow8)) {
                        str5 = null;
                    } else {
                        str5 = query.getString(columnIndexOrThrow8);
                    }
                    if (query.isNull(columnIndexOrThrow9)) {
                        num = null;
                    } else {
                        num = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                    }
                    if (query.isNull(columnIndexOrThrow10)) {
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                    }
                    if (query.isNull(columnIndexOrThrow11)) {
                        str6 = null;
                    } else {
                        str6 = query.getString(columnIndexOrThrow11);
                    }
                    if (query.isNull(columnIndexOrThrow12)) {
                        str7 = null;
                    } else {
                        str7 = query.getString(columnIndexOrThrow12);
                    }
                    if (query.isNull(columnIndexOrThrow13)) {
                        i = columnIndexOrThrow14;
                        str8 = null;
                    } else {
                        str8 = query.getString(columnIndexOrThrow13);
                        i = columnIndexOrThrow14;
                    }
                    boolean z = query.getInt(i) != 0;
                    if (query.isNull(columnIndexOrThrow15)) {
                        l3 = null;
                    } else {
                        l3 = Long.valueOf(query.getLong(columnIndexOrThrow15));
                    }
                    mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, str8, z, this.__converters.fromTimestamp(l3));
                } else {
                    mediaMetadata = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return mediaMetadata;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
