package org.videolan.moviepedia.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.videolan.moviepedia.database.models.Person;

public final class PersonDao_Impl implements PersonDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Person> __deletionAdapterOfPerson;
    private final EntityInsertionAdapter<Person> __insertionAdapterOfPerson;

    public PersonDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfPerson = new EntityInsertionAdapter<Person>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `media_metadata_person` (`moviepedia_id`,`name`,`image`) VALUES (?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Person person) {
                if (person.getMoviepediaId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, person.getMoviepediaId());
                }
                if (person.getName() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, person.getName());
                }
                if (person.getImage() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, person.getImage());
                }
            }
        };
        this.__deletionAdapterOfPerson = new EntityDeletionOrUpdateAdapter<Person>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "DELETE FROM `media_metadata_person` WHERE `moviepedia_id` = ?";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Person person) {
                if (person.getMoviepediaId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, person.getMoviepediaId());
                }
            }
        };
    }

    public void insert(Person person) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPerson.insert(person);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void deleteAll(List<Person> list) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPerson.handleMultiple(list);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v1, resolved type: org.videolan.moviepedia.database.models.Person} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v3 */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.videolan.moviepedia.database.models.Person getPerson(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "SELECT * from media_metadata_person where moviepedia_id = ?"
            r1 = 1
            androidx.room.RoomSQLiteQuery r0 = androidx.room.RoomSQLiteQuery.acquire(r0, r1)
            if (r7 != 0) goto L_0x000d
            r0.bindNull(r1)
            goto L_0x0010
        L_0x000d:
            r0.bindString(r1, r7)
        L_0x0010:
            androidx.room.RoomDatabase r7 = r6.__db
            r7.assertNotSuspendingTransaction()
            androidx.room.RoomDatabase r7 = r6.__db
            r1 = 0
            r2 = 0
            android.database.Cursor r7 = androidx.room.util.DBUtil.query(r7, r0, r1, r2)
            java.lang.String r1 = "moviepedia_id"
            int r1 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r1)     // Catch:{ all -> 0x0065 }
            java.lang.String r3 = "name"
            int r3 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r3)     // Catch:{ all -> 0x0065 }
            java.lang.String r4 = "image"
            int r4 = androidx.room.util.CursorUtil.getColumnIndexOrThrow(r7, r4)     // Catch:{ all -> 0x0065 }
            boolean r5 = r7.moveToFirst()     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x005e
            boolean r5 = r7.isNull(r1)     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x003d
            r1 = r2
            goto L_0x0041
        L_0x003d:
            java.lang.String r1 = r7.getString(r1)     // Catch:{ all -> 0x0065 }
        L_0x0041:
            boolean r5 = r7.isNull(r3)     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x0049
            r3 = r2
            goto L_0x004d
        L_0x0049:
            java.lang.String r3 = r7.getString(r3)     // Catch:{ all -> 0x0065 }
        L_0x004d:
            boolean r5 = r7.isNull(r4)     // Catch:{ all -> 0x0065 }
            if (r5 == 0) goto L_0x0054
            goto L_0x0058
        L_0x0054:
            java.lang.String r2 = r7.getString(r4)     // Catch:{ all -> 0x0065 }
        L_0x0058:
            org.videolan.moviepedia.database.models.Person r4 = new org.videolan.moviepedia.database.models.Person     // Catch:{ all -> 0x0065 }
            r4.<init>(r1, r3, r2)     // Catch:{ all -> 0x0065 }
            r2 = r4
        L_0x005e:
            r7.close()
            r0.release()
            return r2
        L_0x0065:
            r1 = move-exception
            r7.close()
            r0.release()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.database.PersonDao_Impl.getPerson(java.lang.String):org.videolan.moviepedia.database.models.Person");
    }

    public List<Person> getAll() {
        String str;
        String str2;
        String str3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from media_metadata_person", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
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
            acquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
