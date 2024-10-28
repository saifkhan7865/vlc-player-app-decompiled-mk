package org.videolan.moviepedia.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.paging.LimitOffsetDataSource;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import kotlin.Unit;
import org.videolan.moviepedia.database.models.MediaImage;
import org.videolan.moviepedia.database.models.MediaImageType;
import org.videolan.moviepedia.database.models.MediaMetadata;
import org.videolan.moviepedia.database.models.MediaMetadataType;
import org.videolan.moviepedia.database.models.MediaMetadataWithImages;

public final class MediaMetadataDataFullDao_Impl implements MediaMetadataDataFullDao {
    /* access modifiers changed from: private */
    public final Converters __converters = new Converters();
    /* access modifiers changed from: private */
    public final RoomDatabase __db;

    public MediaMetadataDataFullDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
    }

    public LiveData<MediaMetadataWithImages> getMetadataLiveByML(long j) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata where ml_id = ?", 1);
        acquire.bindLong(1, j);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata", "media_metadata_image"}, false, new Callable<MediaMetadataWithImages>() {
            public MediaMetadataWithImages call() throws Exception {
                MediaMetadataWithImages mediaMetadataWithImages;
                String str;
                Long l;
                String str2;
                String str3;
                String str4;
                Long l2;
                String str5;
                Integer num;
                Integer num2;
                int i;
                String str6;
                int i2;
                String str7;
                int i3;
                String str8;
                int i4;
                boolean z;
                int i5;
                Long l3;
                String str9;
                String str10;
                ArrayList arrayList;
                int i6;
                String str11;
                int i7;
                String str12;
                String str13;
                Cursor query = DBUtil.query(MediaMetadataDataFullDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                    ArrayMap arrayMap = new ArrayMap();
                    int i8 = columnIndexOrThrow12;
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow13)) {
                            i6 = columnIndexOrThrow13;
                            str11 = null;
                        } else {
                            i6 = columnIndexOrThrow13;
                            str11 = query.getString(columnIndexOrThrow13);
                        }
                        if (str11 != null) {
                            i7 = columnIndexOrThrow11;
                            str12 = null;
                            arrayMap.put(str11, null);
                        } else {
                            i7 = columnIndexOrThrow11;
                            str12 = null;
                        }
                        if (query.isNull(columnIndexOrThrow)) {
                            str13 = str12;
                        } else {
                            str13 = query.getString(columnIndexOrThrow);
                        }
                        if (str13 != null && !arrayMap2.containsKey(str13)) {
                            arrayMap2.put(str13, new ArrayList());
                        }
                        columnIndexOrThrow11 = i7;
                        columnIndexOrThrow13 = i6;
                    }
                    int i9 = columnIndexOrThrow13;
                    int i10 = columnIndexOrThrow11;
                    query.moveToPosition(-1);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
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
                        MediaMetadataType mediaTypeFromKey = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
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
                        Date fromTimestamp = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
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
                            i = i10;
                            num2 = null;
                        } else {
                            num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                            i = i10;
                        }
                        if (query.isNull(i)) {
                            i2 = i8;
                            str6 = null;
                        } else {
                            str6 = query.getString(i);
                            i2 = i8;
                        }
                        if (query.isNull(i2)) {
                            i3 = i9;
                            str7 = null;
                        } else {
                            str7 = query.getString(i2);
                            i3 = i9;
                        }
                        if (query.isNull(i3)) {
                            i4 = columnIndexOrThrow14;
                            str8 = null;
                        } else {
                            str8 = query.getString(i3);
                            i4 = columnIndexOrThrow14;
                        }
                        if (query.getInt(i4) != 0) {
                            i5 = columnIndexOrThrow15;
                            z = true;
                        } else {
                            i5 = columnIndexOrThrow15;
                            z = false;
                        }
                        if (query.isNull(i5)) {
                            l3 = null;
                        } else {
                            l3 = Long.valueOf(query.getLong(i5));
                        }
                        MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, str8, z, MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3));
                        if (query.isNull(i3)) {
                            str9 = null;
                        } else {
                            str9 = query.getString(i3);
                        }
                        MediaMetadata mediaMetadata2 = str9 != null ? (MediaMetadata) arrayMap.get(str9) : null;
                        if (query.isNull(columnIndexOrThrow)) {
                            str10 = null;
                        } else {
                            str10 = query.getString(columnIndexOrThrow);
                        }
                        if (str10 != null) {
                            arrayList = (ArrayList) arrayMap2.get(str10);
                        } else {
                            arrayList = new ArrayList();
                        }
                        mediaMetadataWithImages = new MediaMetadataWithImages();
                        mediaMetadataWithImages.metadata = mediaMetadata;
                        mediaMetadataWithImages.show = mediaMetadata2;
                        mediaMetadataWithImages.setImages(arrayList);
                    } else {
                        mediaMetadataWithImages = null;
                    }
                    return mediaMetadataWithImages;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public LiveData<MediaMetadataWithImages> getMediaLive(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata where moviepedia_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata", "media_metadata_image"}, false, new Callable<MediaMetadataWithImages>() {
            public MediaMetadataWithImages call() throws Exception {
                MediaMetadataWithImages mediaMetadataWithImages;
                String str;
                Long l;
                String str2;
                String str3;
                String str4;
                Long l2;
                String str5;
                Integer num;
                Integer num2;
                int i;
                String str6;
                int i2;
                String str7;
                int i3;
                String str8;
                int i4;
                boolean z;
                int i5;
                Long l3;
                String str9;
                String str10;
                ArrayList arrayList;
                int i6;
                String str11;
                int i7;
                String str12;
                String str13;
                Cursor query = DBUtil.query(MediaMetadataDataFullDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                    ArrayMap arrayMap = new ArrayMap();
                    int i8 = columnIndexOrThrow12;
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow13)) {
                            i6 = columnIndexOrThrow13;
                            str11 = null;
                        } else {
                            i6 = columnIndexOrThrow13;
                            str11 = query.getString(columnIndexOrThrow13);
                        }
                        if (str11 != null) {
                            i7 = columnIndexOrThrow11;
                            str12 = null;
                            arrayMap.put(str11, null);
                        } else {
                            i7 = columnIndexOrThrow11;
                            str12 = null;
                        }
                        if (query.isNull(columnIndexOrThrow)) {
                            str13 = str12;
                        } else {
                            str13 = query.getString(columnIndexOrThrow);
                        }
                        if (str13 != null && !arrayMap2.containsKey(str13)) {
                            arrayMap2.put(str13, new ArrayList());
                        }
                        columnIndexOrThrow11 = i7;
                        columnIndexOrThrow13 = i6;
                    }
                    int i9 = columnIndexOrThrow13;
                    int i10 = columnIndexOrThrow11;
                    query.moveToPosition(-1);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
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
                        MediaMetadataType mediaTypeFromKey = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
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
                        Date fromTimestamp = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
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
                            i = i10;
                            num2 = null;
                        } else {
                            num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                            i = i10;
                        }
                        if (query.isNull(i)) {
                            i2 = i8;
                            str6 = null;
                        } else {
                            str6 = query.getString(i);
                            i2 = i8;
                        }
                        if (query.isNull(i2)) {
                            i3 = i9;
                            str7 = null;
                        } else {
                            str7 = query.getString(i2);
                            i3 = i9;
                        }
                        if (query.isNull(i3)) {
                            i4 = columnIndexOrThrow14;
                            str8 = null;
                        } else {
                            str8 = query.getString(i3);
                            i4 = columnIndexOrThrow14;
                        }
                        if (query.getInt(i4) != 0) {
                            i5 = columnIndexOrThrow15;
                            z = true;
                        } else {
                            i5 = columnIndexOrThrow15;
                            z = false;
                        }
                        if (query.isNull(i5)) {
                            l3 = null;
                        } else {
                            l3 = Long.valueOf(query.getLong(i5));
                        }
                        MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, str8, z, MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3));
                        if (query.isNull(i3)) {
                            str9 = null;
                        } else {
                            str9 = query.getString(i3);
                        }
                        MediaMetadata mediaMetadata2 = str9 != null ? (MediaMetadata) arrayMap.get(str9) : null;
                        if (query.isNull(columnIndexOrThrow)) {
                            str10 = null;
                        } else {
                            str10 = query.getString(columnIndexOrThrow);
                        }
                        if (str10 != null) {
                            arrayList = (ArrayList) arrayMap2.get(str10);
                        } else {
                            arrayList = new ArrayList();
                        }
                        mediaMetadataWithImages = new MediaMetadataWithImages();
                        mediaMetadataWithImages.metadata = mediaMetadata;
                        mediaMetadataWithImages.show = mediaMetadata2;
                        mediaMetadataWithImages.setImages(arrayList);
                    } else {
                        mediaMetadataWithImages = null;
                    }
                    return mediaMetadataWithImages;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public LiveData<List<MediaMetadataWithImages>> getEpisodesLive(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata where show_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata", "media_metadata_image"}, false, new Callable<List<MediaMetadataWithImages>>() {
            public List<MediaMetadataWithImages> call() throws Exception {
                String str;
                Long l;
                String str2;
                String str3;
                String str4;
                Long l2;
                String str5;
                Integer num;
                Integer num2;
                int i;
                String str6;
                int i2;
                String str7;
                int i3;
                String string;
                int i4;
                int i5;
                Long l3;
                String str8;
                int i6;
                String str9;
                ArrayList arrayList;
                int i7;
                String str10;
                int i8;
                String str11;
                String str12;
                AnonymousClass3 r1 = this;
                Cursor query = DBUtil.query(MediaMetadataDataFullDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                    ArrayMap arrayMap = new ArrayMap();
                    int i9 = columnIndexOrThrow12;
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow13)) {
                            i7 = columnIndexOrThrow13;
                            str10 = null;
                        } else {
                            i7 = columnIndexOrThrow13;
                            str10 = query.getString(columnIndexOrThrow13);
                        }
                        if (str10 != null) {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                            arrayMap.put(str10, null);
                        } else {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                        }
                        if (query.isNull(columnIndexOrThrow)) {
                            str12 = str11;
                        } else {
                            str12 = query.getString(columnIndexOrThrow);
                        }
                        if (str12 != null && !arrayMap2.containsKey(str12)) {
                            arrayMap2.put(str12, new ArrayList());
                        }
                        columnIndexOrThrow11 = i8;
                        columnIndexOrThrow13 = i7;
                    }
                    int i10 = columnIndexOrThrow13;
                    int i11 = columnIndexOrThrow11;
                    query.moveToPosition(-1);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                    ArrayList arrayList2 = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
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
                        int i12 = columnIndexOrThrow2;
                        MediaMetadataType mediaTypeFromKey = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
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
                        Date fromTimestamp = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
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
                            i = i11;
                            num2 = null;
                        } else {
                            num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                            i = i11;
                        }
                        if (query.isNull(i)) {
                            i2 = i9;
                            str6 = null;
                        } else {
                            str6 = query.getString(i);
                            i2 = i9;
                        }
                        if (query.isNull(i2)) {
                            i11 = i;
                            i3 = i10;
                            str7 = null;
                        } else {
                            i11 = i;
                            str7 = query.getString(i2);
                            i3 = i10;
                        }
                        if (query.isNull(i3)) {
                            string = null;
                        } else {
                            string = query.getString(i3);
                        }
                        int i13 = columnIndexOrThrow14;
                        int i14 = columnIndexOrThrow3;
                        int i15 = i13;
                        boolean z = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow15;
                        int i17 = i15;
                        int i18 = i16;
                        if (query.isNull(i18)) {
                            i4 = i18;
                            i5 = columnIndexOrThrow4;
                            l3 = null;
                        } else {
                            i4 = i18;
                            l3 = Long.valueOf(query.getLong(i18));
                            i5 = columnIndexOrThrow4;
                        }
                        MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, string, z, MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3));
                        if (query.isNull(i3)) {
                            str8 = null;
                        } else {
                            str8 = query.getString(i3);
                        }
                        MediaMetadata mediaMetadata2 = str8 != null ? (MediaMetadata) arrayMap.get(str8) : null;
                        if (query.isNull(columnIndexOrThrow)) {
                            i6 = columnIndexOrThrow;
                            str9 = null;
                        } else {
                            i6 = columnIndexOrThrow;
                            str9 = query.getString(columnIndexOrThrow);
                        }
                        if (str9 != null) {
                            arrayList = (ArrayList) arrayMap2.get(str9);
                        } else {
                            arrayList = new ArrayList();
                        }
                        MediaMetadataWithImages mediaMetadataWithImages = new MediaMetadataWithImages();
                        mediaMetadataWithImages.metadata = mediaMetadata;
                        mediaMetadataWithImages.show = mediaMetadata2;
                        mediaMetadataWithImages.setImages(arrayList);
                        arrayList2.add(mediaMetadataWithImages);
                        r1 = this;
                        columnIndexOrThrow3 = i14;
                        columnIndexOrThrow14 = i17;
                        columnIndexOrThrow4 = i5;
                        columnIndexOrThrow15 = i4;
                        columnIndexOrThrow = i6;
                        i10 = i3;
                        i9 = i2;
                        columnIndexOrThrow2 = i12;
                    }
                    return arrayList2;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public MediaMetadataWithImages getMedia(long j) {
        RoomSQLiteQuery roomSQLiteQuery;
        MediaMetadataWithImages mediaMetadataWithImages;
        String str;
        Long l;
        String str2;
        String str3;
        String str4;
        Long l2;
        String str5;
        Integer num;
        Integer num2;
        int i;
        String str6;
        int i2;
        String str7;
        int i3;
        String str8;
        int i4;
        boolean z;
        int i5;
        Long l3;
        String str9;
        String str10;
        ArrayList arrayList;
        int i6;
        String str11;
        int i7;
        String str12;
        String str13;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata where ml_id = ?", 1);
        acquire.bindLong(1, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, true, (CancellationSignal) null);
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
                ArrayMap arrayMap = new ArrayMap();
                int i8 = columnIndexOrThrow12;
                ArrayMap arrayMap2 = new ArrayMap();
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow13)) {
                        i6 = columnIndexOrThrow13;
                        str11 = null;
                    } else {
                        i6 = columnIndexOrThrow13;
                        str11 = query.getString(columnIndexOrThrow13);
                    }
                    if (str11 != null) {
                        i7 = columnIndexOrThrow11;
                        str12 = null;
                        arrayMap.put(str11, null);
                    } else {
                        i7 = columnIndexOrThrow11;
                        str12 = null;
                    }
                    if (query.isNull(columnIndexOrThrow)) {
                        str13 = str12;
                    } else {
                        str13 = query.getString(columnIndexOrThrow);
                    }
                    if (str13 != null && !arrayMap2.containsKey(str13)) {
                        arrayMap2.put(str13, new ArrayList());
                    }
                    columnIndexOrThrow11 = i7;
                    columnIndexOrThrow13 = i6;
                }
                int i9 = columnIndexOrThrow13;
                int i10 = columnIndexOrThrow11;
                query.moveToPosition(-1);
                __fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                __fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
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
                        i = i10;
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                        i = i10;
                    }
                    if (query.isNull(i)) {
                        i2 = i8;
                        str6 = null;
                    } else {
                        str6 = query.getString(i);
                        i2 = i8;
                    }
                    if (query.isNull(i2)) {
                        i3 = i9;
                        str7 = null;
                    } else {
                        str7 = query.getString(i2);
                        i3 = i9;
                    }
                    if (query.isNull(i3)) {
                        i4 = columnIndexOrThrow14;
                        str8 = null;
                    } else {
                        str8 = query.getString(i3);
                        i4 = columnIndexOrThrow14;
                    }
                    if (query.getInt(i4) != 0) {
                        i5 = columnIndexOrThrow15;
                        z = true;
                    } else {
                        i5 = columnIndexOrThrow15;
                        z = false;
                    }
                    if (query.isNull(i5)) {
                        l3 = null;
                    } else {
                        l3 = Long.valueOf(query.getLong(i5));
                    }
                    MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, str8, z, this.__converters.fromTimestamp(l3));
                    if (query.isNull(i3)) {
                        str9 = null;
                    } else {
                        str9 = query.getString(i3);
                    }
                    MediaMetadata mediaMetadata2 = str9 != null ? (MediaMetadata) arrayMap.get(str9) : null;
                    if (query.isNull(columnIndexOrThrow)) {
                        str10 = null;
                    } else {
                        str10 = query.getString(columnIndexOrThrow);
                    }
                    if (str10 != null) {
                        arrayList = (ArrayList) arrayMap2.get(str10);
                    } else {
                        arrayList = new ArrayList();
                    }
                    mediaMetadataWithImages = new MediaMetadataWithImages();
                    mediaMetadataWithImages.metadata = mediaMetadata;
                    mediaMetadataWithImages.show = mediaMetadata2;
                    mediaMetadataWithImages.setImages(arrayList);
                } else {
                    mediaMetadataWithImages = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return mediaMetadataWithImages;
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

    public MediaMetadataWithImages getMediaById(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        MediaMetadataWithImages mediaMetadataWithImages;
        String str2;
        Long l;
        String str3;
        String str4;
        String str5;
        Long l2;
        String str6;
        Integer num;
        Integer num2;
        int i;
        String str7;
        int i2;
        String str8;
        int i3;
        String str9;
        int i4;
        boolean z;
        int i5;
        Long l3;
        String str10;
        String str11;
        ArrayList arrayList;
        int i6;
        String str12;
        int i7;
        String str13;
        String str14;
        String str15 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata where moviepedia_id = ?", 1);
        if (str15 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str15);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, true, (CancellationSignal) null);
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
                ArrayMap arrayMap = new ArrayMap();
                int i8 = columnIndexOrThrow12;
                ArrayMap arrayMap2 = new ArrayMap();
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow13)) {
                        i6 = columnIndexOrThrow13;
                        str12 = null;
                    } else {
                        i6 = columnIndexOrThrow13;
                        str12 = query.getString(columnIndexOrThrow13);
                    }
                    if (str12 != null) {
                        i7 = columnIndexOrThrow11;
                        str13 = null;
                        arrayMap.put(str12, null);
                    } else {
                        i7 = columnIndexOrThrow11;
                        str13 = null;
                    }
                    if (query.isNull(columnIndexOrThrow)) {
                        str14 = str13;
                    } else {
                        str14 = query.getString(columnIndexOrThrow);
                    }
                    if (str14 != null && !arrayMap2.containsKey(str14)) {
                        arrayMap2.put(str14, new ArrayList());
                    }
                    columnIndexOrThrow11 = i7;
                    columnIndexOrThrow13 = i6;
                }
                int i9 = columnIndexOrThrow13;
                int i10 = columnIndexOrThrow11;
                query.moveToPosition(-1);
                __fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                __fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                if (query.moveToFirst()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow);
                    }
                    if (query.isNull(columnIndexOrThrow2)) {
                        l = null;
                    } else {
                        l = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    }
                    MediaMetadataType mediaTypeFromKey = this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        str4 = null;
                    } else {
                        str4 = query.getString(columnIndexOrThrow5);
                    }
                    if (query.isNull(columnIndexOrThrow6)) {
                        str5 = null;
                    } else {
                        str5 = query.getString(columnIndexOrThrow6);
                    }
                    if (query.isNull(columnIndexOrThrow7)) {
                        l2 = null;
                    } else {
                        l2 = Long.valueOf(query.getLong(columnIndexOrThrow7));
                    }
                    Date fromTimestamp = this.__converters.fromTimestamp(l2);
                    if (query.isNull(columnIndexOrThrow8)) {
                        str6 = null;
                    } else {
                        str6 = query.getString(columnIndexOrThrow8);
                    }
                    if (query.isNull(columnIndexOrThrow9)) {
                        num = null;
                    } else {
                        num = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                    }
                    if (query.isNull(columnIndexOrThrow10)) {
                        i = i10;
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                        i = i10;
                    }
                    if (query.isNull(i)) {
                        i2 = i8;
                        str7 = null;
                    } else {
                        str7 = query.getString(i);
                        i2 = i8;
                    }
                    if (query.isNull(i2)) {
                        i3 = i9;
                        str8 = null;
                    } else {
                        str8 = query.getString(i2);
                        i3 = i9;
                    }
                    if (query.isNull(i3)) {
                        i4 = columnIndexOrThrow14;
                        str9 = null;
                    } else {
                        str9 = query.getString(i3);
                        i4 = columnIndexOrThrow14;
                    }
                    if (query.getInt(i4) != 0) {
                        i5 = columnIndexOrThrow15;
                        z = true;
                    } else {
                        i5 = columnIndexOrThrow15;
                        z = false;
                    }
                    if (query.isNull(i5)) {
                        l3 = null;
                    } else {
                        l3 = Long.valueOf(query.getLong(i5));
                    }
                    MediaMetadata mediaMetadata = new MediaMetadata(str2, l, mediaTypeFromKey, str3, str4, str5, fromTimestamp, str6, num, num2, str7, str8, str9, z, this.__converters.fromTimestamp(l3));
                    if (query.isNull(i3)) {
                        str10 = null;
                    } else {
                        str10 = query.getString(i3);
                    }
                    MediaMetadata mediaMetadata2 = str10 != null ? (MediaMetadata) arrayMap.get(str10) : null;
                    if (query.isNull(columnIndexOrThrow)) {
                        str11 = null;
                    } else {
                        str11 = query.getString(columnIndexOrThrow);
                    }
                    if (str11 != null) {
                        arrayList = (ArrayList) arrayMap2.get(str11);
                    } else {
                        arrayList = new ArrayList();
                    }
                    mediaMetadataWithImages = new MediaMetadataWithImages();
                    mediaMetadataWithImages.metadata = mediaMetadata;
                    mediaMetadataWithImages.show = mediaMetadata2;
                    mediaMetadataWithImages.setImages(arrayList);
                } else {
                    mediaMetadataWithImages = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return mediaMetadataWithImages;
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

    public LiveData<MediaMetadataWithImages> getMediaByIdLive(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata where moviepedia_id = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata", "media_metadata_image"}, false, new Callable<MediaMetadataWithImages>() {
            public MediaMetadataWithImages call() throws Exception {
                MediaMetadataWithImages mediaMetadataWithImages;
                String str;
                Long l;
                String str2;
                String str3;
                String str4;
                Long l2;
                String str5;
                Integer num;
                Integer num2;
                int i;
                String str6;
                int i2;
                String str7;
                int i3;
                String str8;
                int i4;
                boolean z;
                int i5;
                Long l3;
                String str9;
                String str10;
                ArrayList arrayList;
                int i6;
                String str11;
                int i7;
                String str12;
                String str13;
                Cursor query = DBUtil.query(MediaMetadataDataFullDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                    ArrayMap arrayMap = new ArrayMap();
                    int i8 = columnIndexOrThrow12;
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow13)) {
                            i6 = columnIndexOrThrow13;
                            str11 = null;
                        } else {
                            i6 = columnIndexOrThrow13;
                            str11 = query.getString(columnIndexOrThrow13);
                        }
                        if (str11 != null) {
                            i7 = columnIndexOrThrow11;
                            str12 = null;
                            arrayMap.put(str11, null);
                        } else {
                            i7 = columnIndexOrThrow11;
                            str12 = null;
                        }
                        if (query.isNull(columnIndexOrThrow)) {
                            str13 = str12;
                        } else {
                            str13 = query.getString(columnIndexOrThrow);
                        }
                        if (str13 != null && !arrayMap2.containsKey(str13)) {
                            arrayMap2.put(str13, new ArrayList());
                        }
                        columnIndexOrThrow11 = i7;
                        columnIndexOrThrow13 = i6;
                    }
                    int i9 = columnIndexOrThrow13;
                    int i10 = columnIndexOrThrow11;
                    query.moveToPosition(-1);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
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
                        MediaMetadataType mediaTypeFromKey = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
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
                        Date fromTimestamp = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
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
                            i = i10;
                            num2 = null;
                        } else {
                            num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                            i = i10;
                        }
                        if (query.isNull(i)) {
                            i2 = i8;
                            str6 = null;
                        } else {
                            str6 = query.getString(i);
                            i2 = i8;
                        }
                        if (query.isNull(i2)) {
                            i3 = i9;
                            str7 = null;
                        } else {
                            str7 = query.getString(i2);
                            i3 = i9;
                        }
                        if (query.isNull(i3)) {
                            i4 = columnIndexOrThrow14;
                            str8 = null;
                        } else {
                            str8 = query.getString(i3);
                            i4 = columnIndexOrThrow14;
                        }
                        if (query.getInt(i4) != 0) {
                            i5 = columnIndexOrThrow15;
                            z = true;
                        } else {
                            i5 = columnIndexOrThrow15;
                            z = false;
                        }
                        if (query.isNull(i5)) {
                            l3 = null;
                        } else {
                            l3 = Long.valueOf(query.getLong(i5));
                        }
                        MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, str8, z, MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3));
                        if (query.isNull(i3)) {
                            str9 = null;
                        } else {
                            str9 = query.getString(i3);
                        }
                        MediaMetadata mediaMetadata2 = str9 != null ? (MediaMetadata) arrayMap.get(str9) : null;
                        if (query.isNull(columnIndexOrThrow)) {
                            str10 = null;
                        } else {
                            str10 = query.getString(columnIndexOrThrow);
                        }
                        if (str10 != null) {
                            arrayList = (ArrayList) arrayMap2.get(str10);
                        } else {
                            arrayList = new ArrayList();
                        }
                        mediaMetadataWithImages = new MediaMetadataWithImages();
                        mediaMetadataWithImages.metadata = mediaMetadata;
                        mediaMetadataWithImages.show = mediaMetadata2;
                        mediaMetadataWithImages.setImages(arrayList);
                    } else {
                        mediaMetadataWithImages = null;
                    }
                    return mediaMetadataWithImages;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public int getMovieCount() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select count(moviepedia_id) from media_metadata where type = 0", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public int getTvshowsCount() {
        int i = 0;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select count(moviepedia_id) from media_metadata where type = 2", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            if (query.moveToFirst()) {
                i = query.getInt(0);
            }
            return i;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public LiveData<List<MediaMetadataWithImages>> getAllLive() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata", "media_metadata_image"}, false, new Callable<List<MediaMetadataWithImages>>() {
            public List<MediaMetadataWithImages> call() throws Exception {
                String str;
                Long l;
                String str2;
                String str3;
                String str4;
                Long l2;
                String str5;
                Integer num;
                Integer num2;
                int i;
                String str6;
                int i2;
                String str7;
                int i3;
                String string;
                int i4;
                int i5;
                Long l3;
                String str8;
                int i6;
                String str9;
                ArrayList arrayList;
                int i7;
                String str10;
                int i8;
                String str11;
                String str12;
                AnonymousClass5 r1 = this;
                Cursor query = DBUtil.query(MediaMetadataDataFullDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                    ArrayMap arrayMap = new ArrayMap();
                    int i9 = columnIndexOrThrow12;
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow13)) {
                            i7 = columnIndexOrThrow13;
                            str10 = null;
                        } else {
                            i7 = columnIndexOrThrow13;
                            str10 = query.getString(columnIndexOrThrow13);
                        }
                        if (str10 != null) {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                            arrayMap.put(str10, null);
                        } else {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                        }
                        if (query.isNull(columnIndexOrThrow)) {
                            str12 = str11;
                        } else {
                            str12 = query.getString(columnIndexOrThrow);
                        }
                        if (str12 != null && !arrayMap2.containsKey(str12)) {
                            arrayMap2.put(str12, new ArrayList());
                        }
                        columnIndexOrThrow11 = i8;
                        columnIndexOrThrow13 = i7;
                    }
                    int i10 = columnIndexOrThrow13;
                    int i11 = columnIndexOrThrow11;
                    query.moveToPosition(-1);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                    ArrayList arrayList2 = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
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
                        int i12 = columnIndexOrThrow2;
                        MediaMetadataType mediaTypeFromKey = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
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
                        Date fromTimestamp = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
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
                            i = i11;
                            num2 = null;
                        } else {
                            num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                            i = i11;
                        }
                        if (query.isNull(i)) {
                            i2 = i9;
                            str6 = null;
                        } else {
                            str6 = query.getString(i);
                            i2 = i9;
                        }
                        if (query.isNull(i2)) {
                            i11 = i;
                            i3 = i10;
                            str7 = null;
                        } else {
                            i11 = i;
                            str7 = query.getString(i2);
                            i3 = i10;
                        }
                        if (query.isNull(i3)) {
                            string = null;
                        } else {
                            string = query.getString(i3);
                        }
                        int i13 = columnIndexOrThrow14;
                        int i14 = columnIndexOrThrow3;
                        int i15 = i13;
                        boolean z = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow15;
                        int i17 = i15;
                        int i18 = i16;
                        if (query.isNull(i18)) {
                            i4 = i18;
                            i5 = columnIndexOrThrow4;
                            l3 = null;
                        } else {
                            i4 = i18;
                            l3 = Long.valueOf(query.getLong(i18));
                            i5 = columnIndexOrThrow4;
                        }
                        MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, string, z, MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3));
                        if (query.isNull(i3)) {
                            str8 = null;
                        } else {
                            str8 = query.getString(i3);
                        }
                        MediaMetadata mediaMetadata2 = str8 != null ? (MediaMetadata) arrayMap.get(str8) : null;
                        if (query.isNull(columnIndexOrThrow)) {
                            i6 = columnIndexOrThrow;
                            str9 = null;
                        } else {
                            i6 = columnIndexOrThrow;
                            str9 = query.getString(columnIndexOrThrow);
                        }
                        if (str9 != null) {
                            arrayList = (ArrayList) arrayMap2.get(str9);
                        } else {
                            arrayList = new ArrayList();
                        }
                        MediaMetadataWithImages mediaMetadataWithImages = new MediaMetadataWithImages();
                        mediaMetadataWithImages.metadata = mediaMetadata;
                        mediaMetadataWithImages.show = mediaMetadata2;
                        mediaMetadataWithImages.setImages(arrayList);
                        arrayList2.add(mediaMetadataWithImages);
                        r1 = this;
                        columnIndexOrThrow3 = i14;
                        columnIndexOrThrow14 = i17;
                        columnIndexOrThrow4 = i5;
                        columnIndexOrThrow15 = i4;
                        columnIndexOrThrow = i6;
                        i10 = i3;
                        i9 = i2;
                        columnIndexOrThrow2 = i12;
                    }
                    return arrayList2;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public MediaMetadataWithImages findNextEpisode(String str, int i, int i2) {
        RoomSQLiteQuery roomSQLiteQuery;
        MediaMetadataWithImages mediaMetadataWithImages;
        String str2;
        Long l;
        String str3;
        String str4;
        String str5;
        Long l2;
        String str6;
        Integer num;
        Integer num2;
        int i3;
        String str7;
        int i4;
        String str8;
        int i5;
        String str9;
        int i6;
        boolean z;
        int i7;
        Long l3;
        String str10;
        String str11;
        ArrayList arrayList;
        int i8;
        String str12;
        int i9;
        String str13;
        String str14;
        String str15 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM media_metadata WHERE show_id = ? AND ((season = ? AND episode > ?) OR (season > ?)) ORDER BY season, episode ASC", 4);
        if (str15 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str15);
        }
        long j = (long) i;
        acquire.bindLong(2, j);
        acquire.bindLong(3, (long) i2);
        acquire.bindLong(4, j);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, true, (CancellationSignal) null);
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
                ArrayMap arrayMap = new ArrayMap();
                int i10 = columnIndexOrThrow12;
                ArrayMap arrayMap2 = new ArrayMap();
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow13)) {
                        i8 = columnIndexOrThrow13;
                        str12 = null;
                    } else {
                        i8 = columnIndexOrThrow13;
                        str12 = query.getString(columnIndexOrThrow13);
                    }
                    if (str12 != null) {
                        i9 = columnIndexOrThrow11;
                        str13 = null;
                        arrayMap.put(str12, null);
                    } else {
                        i9 = columnIndexOrThrow11;
                        str13 = null;
                    }
                    if (query.isNull(columnIndexOrThrow)) {
                        str14 = str13;
                    } else {
                        str14 = query.getString(columnIndexOrThrow);
                    }
                    if (str14 != null && !arrayMap2.containsKey(str14)) {
                        arrayMap2.put(str14, new ArrayList());
                    }
                    columnIndexOrThrow11 = i9;
                    columnIndexOrThrow13 = i8;
                }
                int i11 = columnIndexOrThrow13;
                int i12 = columnIndexOrThrow11;
                query.moveToPosition(-1);
                __fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                __fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                if (query.moveToFirst()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow);
                    }
                    if (query.isNull(columnIndexOrThrow2)) {
                        l = null;
                    } else {
                        l = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    }
                    MediaMetadataType mediaTypeFromKey = this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        str4 = null;
                    } else {
                        str4 = query.getString(columnIndexOrThrow5);
                    }
                    if (query.isNull(columnIndexOrThrow6)) {
                        str5 = null;
                    } else {
                        str5 = query.getString(columnIndexOrThrow6);
                    }
                    if (query.isNull(columnIndexOrThrow7)) {
                        l2 = null;
                    } else {
                        l2 = Long.valueOf(query.getLong(columnIndexOrThrow7));
                    }
                    Date fromTimestamp = this.__converters.fromTimestamp(l2);
                    if (query.isNull(columnIndexOrThrow8)) {
                        str6 = null;
                    } else {
                        str6 = query.getString(columnIndexOrThrow8);
                    }
                    if (query.isNull(columnIndexOrThrow9)) {
                        num = null;
                    } else {
                        num = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                    }
                    if (query.isNull(columnIndexOrThrow10)) {
                        i3 = i12;
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                        i3 = i12;
                    }
                    if (query.isNull(i3)) {
                        i4 = i10;
                        str7 = null;
                    } else {
                        str7 = query.getString(i3);
                        i4 = i10;
                    }
                    if (query.isNull(i4)) {
                        i5 = i11;
                        str8 = null;
                    } else {
                        str8 = query.getString(i4);
                        i5 = i11;
                    }
                    if (query.isNull(i5)) {
                        i6 = columnIndexOrThrow14;
                        str9 = null;
                    } else {
                        str9 = query.getString(i5);
                        i6 = columnIndexOrThrow14;
                    }
                    if (query.getInt(i6) != 0) {
                        i7 = columnIndexOrThrow15;
                        z = true;
                    } else {
                        i7 = columnIndexOrThrow15;
                        z = false;
                    }
                    if (query.isNull(i7)) {
                        l3 = null;
                    } else {
                        l3 = Long.valueOf(query.getLong(i7));
                    }
                    MediaMetadata mediaMetadata = new MediaMetadata(str2, l, mediaTypeFromKey, str3, str4, str5, fromTimestamp, str6, num, num2, str7, str8, str9, z, this.__converters.fromTimestamp(l3));
                    if (query.isNull(i5)) {
                        str10 = null;
                    } else {
                        str10 = query.getString(i5);
                    }
                    MediaMetadata mediaMetadata2 = str10 != null ? (MediaMetadata) arrayMap.get(str10) : null;
                    if (query.isNull(columnIndexOrThrow)) {
                        str11 = null;
                    } else {
                        str11 = query.getString(columnIndexOrThrow);
                    }
                    if (str11 != null) {
                        arrayList = (ArrayList) arrayMap2.get(str11);
                    } else {
                        arrayList = new ArrayList();
                    }
                    mediaMetadataWithImages = new MediaMetadataWithImages();
                    mediaMetadataWithImages.metadata = mediaMetadata;
                    mediaMetadataWithImages.show = mediaMetadata2;
                    mediaMetadataWithImages.setImages(arrayList);
                } else {
                    mediaMetadataWithImages = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return mediaMetadataWithImages;
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

    public LiveData<List<MediaMetadataWithImages>> getByIds(List<Long> list) {
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select * from media_metadata where ml_id IN (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(") LIMIT 10");
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size);
        int i = 1;
        for (Long next : list) {
            if (next == null) {
                acquire.bindNull(i);
            } else {
                acquire.bindLong(i, next.longValue());
            }
            i++;
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata", "media_metadata_image"}, false, new Callable<List<MediaMetadataWithImages>>() {
            public List<MediaMetadataWithImages> call() throws Exception {
                String str;
                Long l;
                String str2;
                String str3;
                String str4;
                Long l2;
                String str5;
                Integer num;
                Integer num2;
                int i;
                String str6;
                int i2;
                String str7;
                int i3;
                String string;
                int i4;
                int i5;
                Long l3;
                String str8;
                int i6;
                String str9;
                ArrayList arrayList;
                int i7;
                String str10;
                int i8;
                String str11;
                String str12;
                AnonymousClass6 r1 = this;
                Cursor query = DBUtil.query(MediaMetadataDataFullDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                    ArrayMap arrayMap = new ArrayMap();
                    int i9 = columnIndexOrThrow12;
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow13)) {
                            i7 = columnIndexOrThrow13;
                            str10 = null;
                        } else {
                            i7 = columnIndexOrThrow13;
                            str10 = query.getString(columnIndexOrThrow13);
                        }
                        if (str10 != null) {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                            arrayMap.put(str10, null);
                        } else {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                        }
                        if (query.isNull(columnIndexOrThrow)) {
                            str12 = str11;
                        } else {
                            str12 = query.getString(columnIndexOrThrow);
                        }
                        if (str12 != null && !arrayMap2.containsKey(str12)) {
                            arrayMap2.put(str12, new ArrayList());
                        }
                        columnIndexOrThrow11 = i8;
                        columnIndexOrThrow13 = i7;
                    }
                    int i10 = columnIndexOrThrow13;
                    int i11 = columnIndexOrThrow11;
                    query.moveToPosition(-1);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                    ArrayList arrayList2 = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
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
                        int i12 = columnIndexOrThrow2;
                        MediaMetadataType mediaTypeFromKey = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
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
                        Date fromTimestamp = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
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
                            i = i11;
                            num2 = null;
                        } else {
                            num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                            i = i11;
                        }
                        if (query.isNull(i)) {
                            i2 = i9;
                            str6 = null;
                        } else {
                            str6 = query.getString(i);
                            i2 = i9;
                        }
                        if (query.isNull(i2)) {
                            i11 = i;
                            i3 = i10;
                            str7 = null;
                        } else {
                            i11 = i;
                            str7 = query.getString(i2);
                            i3 = i10;
                        }
                        if (query.isNull(i3)) {
                            string = null;
                        } else {
                            string = query.getString(i3);
                        }
                        int i13 = columnIndexOrThrow14;
                        int i14 = columnIndexOrThrow3;
                        int i15 = i13;
                        boolean z = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow15;
                        int i17 = i15;
                        int i18 = i16;
                        if (query.isNull(i18)) {
                            i4 = i18;
                            i5 = columnIndexOrThrow4;
                            l3 = null;
                        } else {
                            i4 = i18;
                            l3 = Long.valueOf(query.getLong(i18));
                            i5 = columnIndexOrThrow4;
                        }
                        MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, string, z, MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3));
                        if (query.isNull(i3)) {
                            str8 = null;
                        } else {
                            str8 = query.getString(i3);
                        }
                        MediaMetadata mediaMetadata2 = str8 != null ? (MediaMetadata) arrayMap.get(str8) : null;
                        if (query.isNull(columnIndexOrThrow)) {
                            i6 = columnIndexOrThrow;
                            str9 = null;
                        } else {
                            i6 = columnIndexOrThrow;
                            str9 = query.getString(columnIndexOrThrow);
                        }
                        if (str9 != null) {
                            arrayList = (ArrayList) arrayMap2.get(str9);
                        } else {
                            arrayList = new ArrayList();
                        }
                        MediaMetadataWithImages mediaMetadataWithImages = new MediaMetadataWithImages();
                        mediaMetadataWithImages.metadata = mediaMetadata;
                        mediaMetadataWithImages.show = mediaMetadata2;
                        mediaMetadataWithImages.setImages(arrayList);
                        arrayList2.add(mediaMetadataWithImages);
                        r1 = this;
                        columnIndexOrThrow3 = i14;
                        columnIndexOrThrow14 = i17;
                        columnIndexOrThrow4 = i5;
                        columnIndexOrThrow15 = i4;
                        columnIndexOrThrow = i6;
                        i10 = i3;
                        i9 = i2;
                        columnIndexOrThrow2 = i12;
                    }
                    return arrayList2;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public LiveData<List<MediaMetadataWithImages>> getRecentlyAdded() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata ORDER BY insertDate DESC LIMIT 10", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata", "media_metadata_image"}, false, new Callable<List<MediaMetadataWithImages>>() {
            public List<MediaMetadataWithImages> call() throws Exception {
                String str;
                Long l;
                String str2;
                String str3;
                String str4;
                Long l2;
                String str5;
                Integer num;
                Integer num2;
                int i;
                String str6;
                int i2;
                String str7;
                int i3;
                String string;
                int i4;
                int i5;
                Long l3;
                String str8;
                int i6;
                String str9;
                ArrayList arrayList;
                int i7;
                String str10;
                int i8;
                String str11;
                String str12;
                AnonymousClass7 r1 = this;
                Cursor query = DBUtil.query(MediaMetadataDataFullDao_Impl.this.__db, acquire, true, (CancellationSignal) null);
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
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "has_cast");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "insertDate");
                    ArrayMap arrayMap = new ArrayMap();
                    int i9 = columnIndexOrThrow12;
                    ArrayMap arrayMap2 = new ArrayMap();
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow13)) {
                            i7 = columnIndexOrThrow13;
                            str10 = null;
                        } else {
                            i7 = columnIndexOrThrow13;
                            str10 = query.getString(columnIndexOrThrow13);
                        }
                        if (str10 != null) {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                            arrayMap.put(str10, null);
                        } else {
                            i8 = columnIndexOrThrow11;
                            str11 = null;
                        }
                        if (query.isNull(columnIndexOrThrow)) {
                            str12 = str11;
                        } else {
                            str12 = query.getString(columnIndexOrThrow);
                        }
                        if (str12 != null && !arrayMap2.containsKey(str12)) {
                            arrayMap2.put(str12, new ArrayList());
                        }
                        columnIndexOrThrow11 = i8;
                        columnIndexOrThrow13 = i7;
                    }
                    int i10 = columnIndexOrThrow13;
                    int i11 = columnIndexOrThrow11;
                    query.moveToPosition(-1);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                    MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                    ArrayList arrayList2 = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
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
                        int i12 = columnIndexOrThrow2;
                        MediaMetadataType mediaTypeFromKey = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
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
                        Date fromTimestamp = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
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
                            i = i11;
                            num2 = null;
                        } else {
                            num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                            i = i11;
                        }
                        if (query.isNull(i)) {
                            i2 = i9;
                            str6 = null;
                        } else {
                            str6 = query.getString(i);
                            i2 = i9;
                        }
                        if (query.isNull(i2)) {
                            i11 = i;
                            i3 = i10;
                            str7 = null;
                        } else {
                            i11 = i;
                            str7 = query.getString(i2);
                            i3 = i10;
                        }
                        if (query.isNull(i3)) {
                            string = null;
                        } else {
                            string = query.getString(i3);
                        }
                        int i13 = columnIndexOrThrow14;
                        int i14 = columnIndexOrThrow3;
                        int i15 = i13;
                        boolean z = query.getInt(i15) != 0;
                        int i16 = columnIndexOrThrow15;
                        int i17 = i15;
                        int i18 = i16;
                        if (query.isNull(i18)) {
                            i4 = i18;
                            i5 = columnIndexOrThrow4;
                            l3 = null;
                        } else {
                            i4 = i18;
                            l3 = Long.valueOf(query.getLong(i18));
                            i5 = columnIndexOrThrow4;
                        }
                        MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaTypeFromKey, str2, str3, str4, fromTimestamp, str5, num, num2, str6, str7, string, z, MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3));
                        if (query.isNull(i3)) {
                            str8 = null;
                        } else {
                            str8 = query.getString(i3);
                        }
                        MediaMetadata mediaMetadata2 = str8 != null ? (MediaMetadata) arrayMap.get(str8) : null;
                        if (query.isNull(columnIndexOrThrow)) {
                            i6 = columnIndexOrThrow;
                            str9 = null;
                        } else {
                            i6 = columnIndexOrThrow;
                            str9 = query.getString(columnIndexOrThrow);
                        }
                        if (str9 != null) {
                            arrayList = (ArrayList) arrayMap2.get(str9);
                        } else {
                            arrayList = new ArrayList();
                        }
                        MediaMetadataWithImages mediaMetadataWithImages = new MediaMetadataWithImages();
                        mediaMetadataWithImages.metadata = mediaMetadata;
                        mediaMetadataWithImages.show = mediaMetadata2;
                        mediaMetadataWithImages.setImages(arrayList);
                        arrayList2.add(mediaMetadataWithImages);
                        r1 = this;
                        columnIndexOrThrow3 = i14;
                        columnIndexOrThrow14 = i17;
                        columnIndexOrThrow4 = i5;
                        columnIndexOrThrow15 = i4;
                        columnIndexOrThrow = i6;
                        i10 = i3;
                        i9 = i2;
                        columnIndexOrThrow2 = i12;
                    }
                    return arrayList2;
                } finally {
                    query.close();
                }
            }

            /* access modifiers changed from: protected */
            public void finalize() {
                acquire.release();
            }
        });
    }

    public List<MediaMetadataWithImages> searchMedia(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String str2;
        Long l;
        String str3;
        String str4;
        String str5;
        Long l2;
        String str6;
        Integer num;
        Integer num2;
        int i;
        String str7;
        int i2;
        String str8;
        int i3;
        String string;
        int i4;
        int i5;
        Long l3;
        String str9;
        int i6;
        String str10;
        ArrayList arrayList;
        int i7;
        String str11;
        int i8;
        String str12;
        String str13;
        MediaMetadataDataFullDao_Impl mediaMetadataDataFullDao_Impl = this;
        String str14 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata WHERE title LIKE ?", 1);
        if (str14 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str14);
        }
        mediaMetadataDataFullDao_Impl.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(mediaMetadataDataFullDao_Impl.__db, acquire, true, (CancellationSignal) null);
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
                ArrayMap arrayMap = new ArrayMap();
                int i9 = columnIndexOrThrow12;
                ArrayMap arrayMap2 = new ArrayMap();
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow13)) {
                        i7 = columnIndexOrThrow13;
                        str11 = null;
                    } else {
                        i7 = columnIndexOrThrow13;
                        str11 = query.getString(columnIndexOrThrow13);
                    }
                    if (str11 != null) {
                        i8 = columnIndexOrThrow11;
                        str12 = null;
                        arrayMap.put(str11, null);
                    } else {
                        i8 = columnIndexOrThrow11;
                        str12 = null;
                    }
                    if (query.isNull(columnIndexOrThrow)) {
                        str13 = str12;
                    } else {
                        str13 = query.getString(columnIndexOrThrow);
                    }
                    if (str13 != null && !arrayMap2.containsKey(str13)) {
                        arrayMap2.put(str13, new ArrayList());
                    }
                    columnIndexOrThrow11 = i8;
                    columnIndexOrThrow13 = i7;
                }
                int i10 = columnIndexOrThrow13;
                int i11 = columnIndexOrThrow11;
                query.moveToPosition(-1);
                mediaMetadataDataFullDao_Impl.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                mediaMetadataDataFullDao_Impl.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                ArrayList arrayList2 = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow);
                    }
                    if (query.isNull(columnIndexOrThrow2)) {
                        l = null;
                    } else {
                        l = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    }
                    int i12 = columnIndexOrThrow2;
                    MediaMetadataType mediaTypeFromKey = mediaMetadataDataFullDao_Impl.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        str4 = null;
                    } else {
                        str4 = query.getString(columnIndexOrThrow5);
                    }
                    if (query.isNull(columnIndexOrThrow6)) {
                        str5 = null;
                    } else {
                        str5 = query.getString(columnIndexOrThrow6);
                    }
                    if (query.isNull(columnIndexOrThrow7)) {
                        l2 = null;
                    } else {
                        l2 = Long.valueOf(query.getLong(columnIndexOrThrow7));
                    }
                    Date fromTimestamp = mediaMetadataDataFullDao_Impl.__converters.fromTimestamp(l2);
                    if (query.isNull(columnIndexOrThrow8)) {
                        str6 = null;
                    } else {
                        str6 = query.getString(columnIndexOrThrow8);
                    }
                    if (query.isNull(columnIndexOrThrow9)) {
                        num = null;
                    } else {
                        num = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                    }
                    if (query.isNull(columnIndexOrThrow10)) {
                        i = i11;
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                        i = i11;
                    }
                    if (query.isNull(i)) {
                        i2 = i9;
                        str7 = null;
                    } else {
                        str7 = query.getString(i);
                        i2 = i9;
                    }
                    if (query.isNull(i2)) {
                        i11 = i;
                        i3 = i10;
                        str8 = null;
                    } else {
                        i11 = i;
                        str8 = query.getString(i2);
                        i3 = i10;
                    }
                    if (query.isNull(i3)) {
                        string = null;
                    } else {
                        string = query.getString(i3);
                    }
                    int i13 = columnIndexOrThrow14;
                    int i14 = columnIndexOrThrow3;
                    int i15 = i13;
                    boolean z = query.getInt(i15) != 0;
                    int i16 = columnIndexOrThrow15;
                    int i17 = i15;
                    int i18 = i16;
                    if (query.isNull(i18)) {
                        i4 = i18;
                        i5 = columnIndexOrThrow4;
                        l3 = null;
                    } else {
                        i4 = i18;
                        l3 = Long.valueOf(query.getLong(i18));
                        i5 = columnIndexOrThrow4;
                    }
                    MediaMetadata mediaMetadata = new MediaMetadata(str2, l, mediaTypeFromKey, str3, str4, str5, fromTimestamp, str6, num, num2, str7, str8, string, z, mediaMetadataDataFullDao_Impl.__converters.fromTimestamp(l3));
                    if (query.isNull(i3)) {
                        str9 = null;
                    } else {
                        str9 = query.getString(i3);
                    }
                    MediaMetadata mediaMetadata2 = str9 != null ? (MediaMetadata) arrayMap.get(str9) : null;
                    if (query.isNull(columnIndexOrThrow)) {
                        i6 = columnIndexOrThrow;
                        str10 = null;
                    } else {
                        i6 = columnIndexOrThrow;
                        str10 = query.getString(columnIndexOrThrow);
                    }
                    if (str10 != null) {
                        arrayList = (ArrayList) arrayMap2.get(str10);
                    } else {
                        arrayList = new ArrayList();
                    }
                    MediaMetadataWithImages mediaMetadataWithImages = new MediaMetadataWithImages();
                    mediaMetadataWithImages.metadata = mediaMetadata;
                    mediaMetadataWithImages.show = mediaMetadata2;
                    mediaMetadataWithImages.setImages(arrayList);
                    arrayList2.add(mediaMetadataWithImages);
                    mediaMetadataDataFullDao_Impl = this;
                    columnIndexOrThrow3 = i14;
                    columnIndexOrThrow14 = i17;
                    columnIndexOrThrow4 = i5;
                    columnIndexOrThrow15 = i4;
                    columnIndexOrThrow = i6;
                    i10 = i3;
                    i9 = i2;
                    columnIndexOrThrow2 = i12;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList2;
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

    public List<MediaMetadataWithImages> getTvShowEpisodes(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        String str2;
        Long l;
        String str3;
        String str4;
        String str5;
        Long l2;
        String str6;
        Integer num;
        Integer num2;
        int i;
        String str7;
        int i2;
        String str8;
        int i3;
        String string;
        int i4;
        int i5;
        Long l3;
        String str9;
        int i6;
        String str10;
        ArrayList arrayList;
        int i7;
        String str11;
        int i8;
        String str12;
        String str13;
        MediaMetadataDataFullDao_Impl mediaMetadataDataFullDao_Impl = this;
        String str14 = str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from media_metadata WHERE show_id = ? ORDER by season, episode", 1);
        if (str14 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str14);
        }
        mediaMetadataDataFullDao_Impl.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(mediaMetadataDataFullDao_Impl.__db, acquire, true, (CancellationSignal) null);
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
                ArrayMap arrayMap = new ArrayMap();
                int i9 = columnIndexOrThrow12;
                ArrayMap arrayMap2 = new ArrayMap();
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow13)) {
                        i7 = columnIndexOrThrow13;
                        str11 = null;
                    } else {
                        i7 = columnIndexOrThrow13;
                        str11 = query.getString(columnIndexOrThrow13);
                    }
                    if (str11 != null) {
                        i8 = columnIndexOrThrow11;
                        str12 = null;
                        arrayMap.put(str11, null);
                    } else {
                        i8 = columnIndexOrThrow11;
                        str12 = null;
                    }
                    if (query.isNull(columnIndexOrThrow)) {
                        str13 = str12;
                    } else {
                        str13 = query.getString(columnIndexOrThrow);
                    }
                    if (str13 != null && !arrayMap2.containsKey(str13)) {
                        arrayMap2.put(str13, new ArrayList());
                    }
                    columnIndexOrThrow11 = i8;
                    columnIndexOrThrow13 = i7;
                }
                int i10 = columnIndexOrThrow13;
                int i11 = columnIndexOrThrow11;
                query.moveToPosition(-1);
                mediaMetadataDataFullDao_Impl.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                mediaMetadataDataFullDao_Impl.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                ArrayList arrayList2 = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    if (query.isNull(columnIndexOrThrow)) {
                        str2 = null;
                    } else {
                        str2 = query.getString(columnIndexOrThrow);
                    }
                    if (query.isNull(columnIndexOrThrow2)) {
                        l = null;
                    } else {
                        l = Long.valueOf(query.getLong(columnIndexOrThrow2));
                    }
                    int i12 = columnIndexOrThrow2;
                    MediaMetadataType mediaTypeFromKey = mediaMetadataDataFullDao_Impl.__converters.mediaTypeFromKey(query.getInt(columnIndexOrThrow3));
                    if (query.isNull(columnIndexOrThrow4)) {
                        str3 = null;
                    } else {
                        str3 = query.getString(columnIndexOrThrow4);
                    }
                    if (query.isNull(columnIndexOrThrow5)) {
                        str4 = null;
                    } else {
                        str4 = query.getString(columnIndexOrThrow5);
                    }
                    if (query.isNull(columnIndexOrThrow6)) {
                        str5 = null;
                    } else {
                        str5 = query.getString(columnIndexOrThrow6);
                    }
                    if (query.isNull(columnIndexOrThrow7)) {
                        l2 = null;
                    } else {
                        l2 = Long.valueOf(query.getLong(columnIndexOrThrow7));
                    }
                    Date fromTimestamp = mediaMetadataDataFullDao_Impl.__converters.fromTimestamp(l2);
                    if (query.isNull(columnIndexOrThrow8)) {
                        str6 = null;
                    } else {
                        str6 = query.getString(columnIndexOrThrow8);
                    }
                    if (query.isNull(columnIndexOrThrow9)) {
                        num = null;
                    } else {
                        num = Integer.valueOf(query.getInt(columnIndexOrThrow9));
                    }
                    if (query.isNull(columnIndexOrThrow10)) {
                        i = i11;
                        num2 = null;
                    } else {
                        num2 = Integer.valueOf(query.getInt(columnIndexOrThrow10));
                        i = i11;
                    }
                    if (query.isNull(i)) {
                        i2 = i9;
                        str7 = null;
                    } else {
                        str7 = query.getString(i);
                        i2 = i9;
                    }
                    if (query.isNull(i2)) {
                        i11 = i;
                        i3 = i10;
                        str8 = null;
                    } else {
                        i11 = i;
                        str8 = query.getString(i2);
                        i3 = i10;
                    }
                    if (query.isNull(i3)) {
                        string = null;
                    } else {
                        string = query.getString(i3);
                    }
                    int i13 = columnIndexOrThrow14;
                    int i14 = columnIndexOrThrow3;
                    int i15 = i13;
                    boolean z = query.getInt(i15) != 0;
                    int i16 = columnIndexOrThrow15;
                    int i17 = i15;
                    int i18 = i16;
                    if (query.isNull(i18)) {
                        i4 = i18;
                        i5 = columnIndexOrThrow4;
                        l3 = null;
                    } else {
                        i4 = i18;
                        l3 = Long.valueOf(query.getLong(i18));
                        i5 = columnIndexOrThrow4;
                    }
                    MediaMetadata mediaMetadata = new MediaMetadata(str2, l, mediaTypeFromKey, str3, str4, str5, fromTimestamp, str6, num, num2, str7, str8, string, z, mediaMetadataDataFullDao_Impl.__converters.fromTimestamp(l3));
                    if (query.isNull(i3)) {
                        str9 = null;
                    } else {
                        str9 = query.getString(i3);
                    }
                    MediaMetadata mediaMetadata2 = str9 != null ? (MediaMetadata) arrayMap.get(str9) : null;
                    if (query.isNull(columnIndexOrThrow)) {
                        i6 = columnIndexOrThrow;
                        str10 = null;
                    } else {
                        i6 = columnIndexOrThrow;
                        str10 = query.getString(columnIndexOrThrow);
                    }
                    if (str10 != null) {
                        arrayList = (ArrayList) arrayMap2.get(str10);
                    } else {
                        arrayList = new ArrayList();
                    }
                    MediaMetadataWithImages mediaMetadataWithImages = new MediaMetadataWithImages();
                    mediaMetadataWithImages.metadata = mediaMetadata;
                    mediaMetadataWithImages.show = mediaMetadata2;
                    mediaMetadataWithImages.setImages(arrayList);
                    arrayList2.add(mediaMetadataWithImages);
                    mediaMetadataDataFullDao_Impl = this;
                    columnIndexOrThrow3 = i14;
                    columnIndexOrThrow14 = i17;
                    columnIndexOrThrow4 = i5;
                    columnIndexOrThrow15 = i4;
                    columnIndexOrThrow = i6;
                    i10 = i3;
                    i9 = i2;
                    columnIndexOrThrow2 = i12;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList2;
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

    public DataSource.Factory<Integer, MediaMetadataWithImages> getAllPaged(final SupportSQLiteQuery supportSQLiteQuery) {
        return new DataSource.Factory<Integer, MediaMetadataWithImages>() {
            public LimitOffsetDataSource<MediaMetadataWithImages> create() {
                return new LimitOffsetDataSource<MediaMetadataWithImages>(MediaMetadataDataFullDao_Impl.this.__db, supportSQLiteQuery, false, true, "media_metadata", "media_metadata_image") {
                    /* access modifiers changed from: protected */
                    public List<MediaMetadataWithImages> convertRows(Cursor cursor) {
                        int i;
                        String str;
                        Long l;
                        int i2;
                        MediaMetadataType mediaMetadataType;
                        int i3;
                        String str2;
                        String str3;
                        String str4;
                        Date date;
                        String str5;
                        Integer num;
                        Integer num2;
                        int i4;
                        String string;
                        String str6;
                        int i5;
                        int i6;
                        String str7;
                        boolean z;
                        int i7;
                        Date date2;
                        String str8;
                        String str9;
                        ArrayList arrayList;
                        Long l2;
                        Long l3;
                        int i8;
                        String str10;
                        AnonymousClass1 r0 = this;
                        Cursor cursor2 = cursor;
                        int columnIndex = CursorUtil.getColumnIndex(cursor2, "moviepedia_id");
                        int columnIndex2 = CursorUtil.getColumnIndex(cursor2, "ml_id");
                        int columnIndex3 = CursorUtil.getColumnIndex(cursor2, "type");
                        int columnIndex4 = CursorUtil.getColumnIndex(cursor2, "title");
                        int columnIndex5 = CursorUtil.getColumnIndex(cursor2, "summary");
                        int columnIndex6 = CursorUtil.getColumnIndex(cursor2, "genres");
                        int columnIndex7 = CursorUtil.getColumnIndex(cursor2, "releaseDate");
                        int columnIndex8 = CursorUtil.getColumnIndex(cursor2, "countries");
                        int columnIndex9 = CursorUtil.getColumnIndex(cursor2, "season");
                        int columnIndex10 = CursorUtil.getColumnIndex(cursor2, "episode");
                        int columnIndex11 = CursorUtil.getColumnIndex(cursor2, "current_poster");
                        int columnIndex12 = CursorUtil.getColumnIndex(cursor2, "current_backdrop");
                        int columnIndex13 = CursorUtil.getColumnIndex(cursor2, "show_id");
                        int columnIndex14 = CursorUtil.getColumnIndex(cursor2, "has_cast");
                        int columnIndex15 = CursorUtil.getColumnIndex(cursor2, "insertDate");
                        ArrayMap arrayMap = new ArrayMap();
                        int i9 = columnIndex12;
                        ArrayMap arrayMap2 = new ArrayMap();
                        while (true) {
                            i = columnIndex11;
                            String str11 = null;
                            if (!cursor.moveToNext()) {
                                break;
                            }
                            if (cursor2.isNull(columnIndex13)) {
                                i8 = columnIndex13;
                                str10 = null;
                            } else {
                                i8 = columnIndex13;
                                str10 = cursor2.getString(columnIndex13);
                            }
                            if (str10 != null) {
                                arrayMap.put(str10, null);
                            }
                            if (!cursor2.isNull(columnIndex)) {
                                str11 = cursor2.getString(columnIndex);
                            }
                            if (str11 != null && !arrayMap2.containsKey(str11)) {
                                arrayMap2.put(str11, new ArrayList());
                            }
                            columnIndex11 = i;
                            columnIndex13 = i8;
                        }
                        int i10 = columnIndex13;
                        cursor2.moveToPosition(-1);
                        MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
                        MediaMetadataDataFullDao_Impl.this.__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap2);
                        ArrayList arrayList2 = new ArrayList(cursor.getCount());
                        while (cursor.moveToNext()) {
                            if (columnIndex != -1 && !cursor2.isNull(columnIndex)) {
                                str = cursor2.getString(columnIndex);
                            } else {
                                str = null;
                            }
                            if (columnIndex2 != -1 && !cursor2.isNull(columnIndex2)) {
                                l = Long.valueOf(cursor2.getLong(columnIndex2));
                            } else {
                                l = null;
                            }
                            if (columnIndex3 == -1) {
                                i2 = columnIndex2;
                                i3 = -1;
                                mediaMetadataType = null;
                            } else {
                                i2 = columnIndex2;
                                mediaMetadataType = MediaMetadataDataFullDao_Impl.this.__converters.mediaTypeFromKey(cursor2.getInt(columnIndex3));
                                i3 = -1;
                            }
                            if (columnIndex4 != i3 && !cursor2.isNull(columnIndex4)) {
                                str2 = cursor2.getString(columnIndex4);
                            } else {
                                str2 = null;
                            }
                            if (columnIndex5 != i3 && !cursor2.isNull(columnIndex5)) {
                                str3 = cursor2.getString(columnIndex5);
                            } else {
                                str3 = null;
                            }
                            if (columnIndex6 != i3 && !cursor2.isNull(columnIndex6)) {
                                str4 = cursor2.getString(columnIndex6);
                            } else {
                                str4 = null;
                            }
                            if (columnIndex7 == i3) {
                                date = null;
                            } else {
                                if (cursor2.isNull(columnIndex7)) {
                                    l3 = null;
                                } else {
                                    l3 = Long.valueOf(cursor2.getLong(columnIndex7));
                                }
                                date = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l3);
                                i3 = -1;
                            }
                            if (columnIndex8 != i3 && !cursor2.isNull(columnIndex8)) {
                                str5 = cursor2.getString(columnIndex8);
                            } else {
                                str5 = null;
                            }
                            if (columnIndex9 != i3 && !cursor2.isNull(columnIndex9)) {
                                num = Integer.valueOf(cursor2.getInt(columnIndex9));
                            } else {
                                num = null;
                            }
                            if (columnIndex10 != i3 && !cursor2.isNull(columnIndex10)) {
                                num2 = Integer.valueOf(cursor2.getInt(columnIndex10));
                                i4 = i;
                            } else {
                                i4 = i;
                                num2 = null;
                            }
                            if (i4 != i3 && !cursor2.isNull(i4)) {
                                string = cursor2.getString(i4);
                            } else {
                                string = null;
                            }
                            int i11 = i9;
                            int i12 = columnIndex3;
                            int i13 = i11;
                            if (i13 != i3 && !cursor2.isNull(i13)) {
                                str6 = cursor2.getString(i13);
                                i5 = i13;
                                i6 = i10;
                            } else {
                                i5 = i13;
                                i6 = i10;
                                str6 = null;
                            }
                            if (i6 != i3 && !cursor2.isNull(i6)) {
                                str7 = cursor2.getString(i6);
                            } else {
                                str7 = null;
                            }
                            boolean z2 = false;
                            int i14 = columnIndex4;
                            int i15 = columnIndex14;
                            if (i15 == i3) {
                                columnIndex14 = i15;
                                i7 = columnIndex15;
                                z = false;
                            } else {
                                if (cursor2.getInt(i15) != 0) {
                                    z2 = true;
                                }
                                columnIndex14 = i15;
                                i7 = columnIndex15;
                                z = z2;
                            }
                            if (i7 == i3) {
                                columnIndex15 = i7;
                                date2 = null;
                            } else {
                                if (cursor2.isNull(i7)) {
                                    columnIndex15 = i7;
                                    l2 = null;
                                } else {
                                    l2 = Long.valueOf(cursor2.getLong(i7));
                                    columnIndex15 = i7;
                                }
                                date2 = MediaMetadataDataFullDao_Impl.this.__converters.fromTimestamp(l2);
                            }
                            MediaMetadata mediaMetadata = new MediaMetadata(str, l, mediaMetadataType, str2, str3, str4, date, str5, num, num2, string, str6, str7, z, date2);
                            if (cursor2.isNull(i6)) {
                                str8 = null;
                            } else {
                                str8 = cursor2.getString(i6);
                            }
                            MediaMetadata mediaMetadata2 = str8 != null ? (MediaMetadata) arrayMap.get(str8) : null;
                            if (cursor2.isNull(columnIndex)) {
                                str9 = null;
                            } else {
                                str9 = cursor2.getString(columnIndex);
                            }
                            if (str9 != null) {
                                arrayList = (ArrayList) arrayMap2.get(str9);
                            } else {
                                arrayList = new ArrayList();
                            }
                            MediaMetadataWithImages mediaMetadataWithImages = new MediaMetadataWithImages();
                            mediaMetadataWithImages.metadata = mediaMetadata;
                            mediaMetadataWithImages.show = mediaMetadata2;
                            mediaMetadataWithImages.setImages(arrayList);
                            arrayList2.add(mediaMetadataWithImages);
                            r0 = this;
                            cursor2 = cursor;
                            i10 = i6;
                            columnIndex3 = i12;
                            i9 = i5;
                            columnIndex4 = i14;
                            columnIndex2 = i2;
                            i = i4;
                        }
                        return arrayList2;
                    }
                };
            }
        };
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* access modifiers changed from: private */
    public void __fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(ArrayMap<String, MediaMetadata> arrayMap) {
        int i;
        String str;
        String str2;
        Long l;
        String str3;
        String str4;
        String str5;
        Long l2;
        String str6;
        Integer num;
        Integer num2;
        String str7;
        String str8;
        String str9;
        Long l3;
        ArrayMap<String, MediaMetadata> arrayMap2 = arrayMap;
        Set<String> keySet = arrayMap.keySet();
        if (!keySet.isEmpty()) {
            if (arrayMap.size() > 999) {
                RelationUtil.recursiveFetchArrayMap(arrayMap2, false, new MediaMetadataDataFullDao_Impl$$ExternalSyntheticLambda0(this));
                return;
            }
            StringBuilder newStringBuilder = StringUtil.newStringBuilder();
            newStringBuilder.append("SELECT `moviepedia_id`,`ml_id`,`type`,`title`,`summary`,`genres`,`releaseDate`,`countries`,`season`,`episode`,`current_poster`,`current_backdrop`,`show_id`,`has_cast`,`insertDate` FROM `media_metadata` WHERE `moviepedia_id` IN (");
            if (keySet == null) {
                i = 1;
            } else {
                i = keySet.size();
            }
            StringUtil.appendPlaceholders(newStringBuilder, i);
            newStringBuilder.append(")");
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), i);
            if (keySet == null) {
                acquire.bindNull(1);
            } else {
                int i2 = 1;
                for (String next : keySet) {
                    if (next == null) {
                        acquire.bindNull(i2);
                    } else {
                        acquire.bindString(i2, next);
                    }
                    i2++;
                }
            }
            Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
            try {
                int columnIndex = CursorUtil.getColumnIndex(query, "moviepedia_id");
                if (columnIndex != -1) {
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndex)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndex);
                        }
                        if (str != null && arrayMap2.containsKey(str)) {
                            if (query.isNull(0)) {
                                str2 = null;
                            } else {
                                str2 = query.getString(0);
                            }
                            if (query.isNull(1)) {
                                l = null;
                            } else {
                                l = Long.valueOf(query.getLong(1));
                            }
                            MediaMetadataType mediaTypeFromKey = this.__converters.mediaTypeFromKey(query.getInt(2));
                            if (query.isNull(3)) {
                                str3 = null;
                            } else {
                                str3 = query.getString(3);
                            }
                            if (query.isNull(4)) {
                                str4 = null;
                            } else {
                                str4 = query.getString(4);
                            }
                            if (query.isNull(5)) {
                                str5 = null;
                            } else {
                                str5 = query.getString(5);
                            }
                            if (query.isNull(6)) {
                                l2 = null;
                            } else {
                                l2 = Long.valueOf(query.getLong(6));
                            }
                            Date fromTimestamp = this.__converters.fromTimestamp(l2);
                            if (query.isNull(7)) {
                                str6 = null;
                            } else {
                                str6 = query.getString(7);
                            }
                            if (query.isNull(8)) {
                                num = null;
                            } else {
                                num = Integer.valueOf(query.getInt(8));
                            }
                            if (query.isNull(9)) {
                                num2 = null;
                            } else {
                                num2 = Integer.valueOf(query.getInt(9));
                            }
                            if (query.isNull(10)) {
                                str7 = null;
                            } else {
                                str7 = query.getString(10);
                            }
                            if (query.isNull(11)) {
                                str8 = null;
                            } else {
                                str8 = query.getString(11);
                            }
                            if (query.isNull(12)) {
                                str9 = null;
                            } else {
                                str9 = query.getString(12);
                            }
                            boolean z = query.getInt(13) != 0;
                            if (query.isNull(14)) {
                                l3 = null;
                            } else {
                                l3 = Long.valueOf(query.getLong(14));
                            }
                            arrayMap2.put(str, new MediaMetadata(str2, l, mediaTypeFromKey, str3, str4, str5, fromTimestamp, str6, num, num2, str7, str8, str9, z, this.__converters.fromTimestamp(l3)));
                        }
                    }
                    query.close();
                }
            } finally {
                query.close();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata$0$org-videolan-moviepedia-database-MediaMetadataDataFullDao_Impl  reason: not valid java name */
    public /* synthetic */ Unit m2431lambda$__fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata$0$orgvideolanmoviepediadatabaseMediaMetadataDataFullDao_Impl(ArrayMap arrayMap) {
        __fetchRelationshipmediaMetadataAsorgVideolanMoviepediaDatabaseModelsMediaMetadata(arrayMap);
        return Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public void __fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(ArrayMap<String, ArrayList<MediaImage>> arrayMap) {
        int i;
        String str;
        ArrayList arrayList;
        String str2;
        String str3;
        String str4;
        Set<String> keySet = arrayMap.keySet();
        if (!keySet.isEmpty()) {
            if (arrayMap.size() > 999) {
                RelationUtil.recursiveFetchArrayMap(arrayMap, true, new MediaMetadataDataFullDao_Impl$$ExternalSyntheticLambda1(this));
                return;
            }
            StringBuilder newStringBuilder = StringUtil.newStringBuilder();
            newStringBuilder.append("SELECT `url`,`media_id`,`image_type`,`image_language` FROM `media_metadata_image` WHERE `media_id` IN (");
            if (keySet == null) {
                i = 1;
            } else {
                i = keySet.size();
            }
            StringUtil.appendPlaceholders(newStringBuilder, i);
            newStringBuilder.append(")");
            RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), i);
            if (keySet == null) {
                acquire.bindNull(1);
            } else {
                int i2 = 1;
                for (String next : keySet) {
                    if (next == null) {
                        acquire.bindNull(i2);
                    } else {
                        acquire.bindString(i2, next);
                    }
                    i2++;
                }
            }
            Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
            try {
                int columnIndex = CursorUtil.getColumnIndex(query, "media_id");
                if (columnIndex != -1) {
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndex)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndex);
                        }
                        if (!(str == null || (arrayList = arrayMap.get(str)) == null)) {
                            if (query.isNull(0)) {
                                str2 = null;
                            } else {
                                str2 = query.getString(0);
                            }
                            if (query.isNull(1)) {
                                str3 = null;
                            } else {
                                str3 = query.getString(1);
                            }
                            MediaImageType mediaImageTypeFromKey = this.__converters.mediaImageTypeFromKey(query.getInt(2));
                            if (query.isNull(3)) {
                                str4 = null;
                            } else {
                                str4 = query.getString(3);
                            }
                            arrayList.add(new MediaImage(str2, str3, mediaImageTypeFromKey, str4));
                        }
                    }
                    query.close();
                }
            } finally {
                query.close();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage$1$org-videolan-moviepedia-database-MediaMetadataDataFullDao_Impl  reason: not valid java name */
    public /* synthetic */ Unit m2432lambda$__fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage$1$orgvideolanmoviepediadatabaseMediaMetadataDataFullDao_Impl(ArrayMap arrayMap) {
        __fetchRelationshipmediaMetadataImageAsorgVideolanMoviepediaDatabaseModelsMediaImage(arrayMap);
        return Unit.INSTANCE;
    }
}
