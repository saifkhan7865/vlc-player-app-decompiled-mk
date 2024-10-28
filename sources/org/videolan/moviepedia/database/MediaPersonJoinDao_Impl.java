package org.videolan.moviepedia.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.lifecycle.LiveData;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.videolan.moviepedia.database.models.MediaPersonJoin;
import org.videolan.moviepedia.database.models.Person;
import org.videolan.moviepedia.database.models.PersonType;

public final class MediaPersonJoinDao_Impl implements MediaPersonJoinDao {
    /* access modifiers changed from: private */
    public final Converters __converters = new Converters();
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<MediaPersonJoin> __insertionAdapterOfMediaPersonJoin;
    private final SharedSQLiteStatement __preparedStmtOfRemoveAllFor;

    public MediaPersonJoinDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfMediaPersonJoin = new EntityInsertionAdapter<MediaPersonJoin>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `media_person_join` (`mediaId`,`personId`,`type`) VALUES (?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MediaPersonJoin mediaPersonJoin) {
                if (mediaPersonJoin.getMediaId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, mediaPersonJoin.getMediaId());
                }
                if (mediaPersonJoin.getPersonId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, mediaPersonJoin.getPersonId());
                }
                supportSQLiteStatement.bindLong(3, (long) MediaPersonJoinDao_Impl.this.__converters.personTypeToKey(mediaPersonJoin.getType()));
            }
        };
        this.__preparedStmtOfRemoveAllFor = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM media_person_join WHERE mediaId = ?";
            }
        };
    }

    public void insertPerson(MediaPersonJoin mediaPersonJoin) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMediaPersonJoin.insert(mediaPersonJoin);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insertPersons(List<MediaPersonJoin> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfMediaPersonJoin.insert(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void removeAllFor(String str) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfRemoveAllFor.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfRemoveAllFor.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfRemoveAllFor.release(acquire);
            throw th;
        }
    }

    public List<MediaPersonJoin> getAll() {
        String str;
        String str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM media_person_join", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "mediaId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "personId");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "type");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    str = null;
                } else {
                    str = query.getString(columnIndexOrThrow);
                }
                if (query.isNull(columnIndexOrThrow2)) {
                    str2 = null;
                } else {
                    str2 = query.getString(columnIndexOrThrow2);
                }
                arrayList.add(new MediaPersonJoin(str, str2, this.__converters.personTypeFromKey(query.getInt(columnIndexOrThrow3))));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public LiveData<List<Person>> getActorsForMediaLive(String str, PersonType personType) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("\n           SELECT * FROM media_metadata_person\n           INNER JOIN media_person_join\n           ON media_metadata_person.moviepedia_id=media_person_join.personId\n           WHERE media_person_join.mediaId=? AND media_person_join.type=?\n           ", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) this.__converters.personTypeToKey(personType));
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"media_metadata_person", "media_person_join"}, false, new Callable<List<Person>>() {
            public List<Person> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(MediaPersonJoinDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "moviepedia_id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, ContentDisposition.Parameters.Name);
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "image");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        if (query.isNull(columnIndexOrThrow2)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow2);
                        }
                        if (query.isNull(columnIndexOrThrow3)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow3);
                        }
                        arrayList.add(new Person(str, str2, str3));
                    }
                    return arrayList;
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

    public List<Person> getActorsForMedia(String str, PersonType personType) {
        String str2;
        String str3;
        String str4;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("\n           SELECT * FROM media_metadata_person\n           INNER JOIN media_person_join\n           ON media_metadata_person.moviepedia_id=media_person_join.personId\n           WHERE media_person_join.mediaId=? AND media_person_join.type=?\n           ", 2);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        acquire.bindLong(2, (long) this.__converters.personTypeToKey(personType));
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "moviepedia_id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, ContentDisposition.Parameters.Name);
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "image");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    str2 = null;
                } else {
                    str2 = query.getString(columnIndexOrThrow);
                }
                if (query.isNull(columnIndexOrThrow2)) {
                    str3 = null;
                } else {
                    str3 = query.getString(columnIndexOrThrow2);
                }
                if (query.isNull(columnIndexOrThrow3)) {
                    str4 = null;
                } else {
                    str4 = query.getString(columnIndexOrThrow3);
                }
                arrayList.add(new Person(str2, str3, str4));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
