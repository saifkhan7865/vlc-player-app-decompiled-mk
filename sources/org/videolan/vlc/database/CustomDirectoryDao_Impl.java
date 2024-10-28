package org.videolan.vlc.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.mediadb.models.CustomDirectory;

public final class CustomDirectoryDao_Impl implements CustomDirectoryDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<CustomDirectory> __deletionAdapterOfCustomDirectory;
    private final EntityInsertionAdapter<CustomDirectory> __insertionAdapterOfCustomDirectory;

    public CustomDirectoryDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfCustomDirectory = new EntityInsertionAdapter<CustomDirectory>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `CustomDirectory` (`path`) VALUES (?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CustomDirectory customDirectory) {
                if (customDirectory.getPath() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, customDirectory.getPath());
                }
            }
        };
        this.__deletionAdapterOfCustomDirectory = new EntityDeletionOrUpdateAdapter<CustomDirectory>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "DELETE FROM `CustomDirectory` WHERE `path` = ?";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, CustomDirectory customDirectory) {
                if (customDirectory.getPath() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, customDirectory.getPath());
                }
            }
        };
    }

    public void insert(CustomDirectory customDirectory) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfCustomDirectory.insert(customDirectory);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(CustomDirectory customDirectory) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfCustomDirectory.handle(customDirectory);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public List<CustomDirectory> getAll() {
        String str;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM CustomDirectory", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, ArtworkProvider.PATH);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    str = null;
                } else {
                    str = query.getString(columnIndexOrThrow);
                }
                arrayList.add(new CustomDirectory(str));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public List<CustomDirectory> get(String str) {
        String str2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM CustomDirectory WHERE path = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, ArtworkProvider.PATH);
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    str2 = null;
                } else {
                    str2 = query.getString(columnIndexOrThrow);
                }
                arrayList.add(new CustomDirectory(str2));
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
