package org.videolan.vlc.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.videolan.vlc.mediadb.models.Slave;

public final class SlaveDao_Impl implements SlaveDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<Slave> __insertionAdapterOfSlave;

    public SlaveDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfSlave = new EntityInsertionAdapter<Slave>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `SLAVES_table` (`slave_media_mrl`,`slave_type`,`slave_priority`,`slave_uri`) VALUES (?,?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Slave slave) {
                if (slave.getMediaPath() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, slave.getMediaPath());
                }
                supportSQLiteStatement.bindLong(2, (long) slave.getType());
                supportSQLiteStatement.bindLong(3, (long) slave.getPriority());
                if (slave.getUri() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, slave.getUri());
                }
            }
        };
    }

    public void insert(Slave slave) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSlave.insert(slave);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void insert(Slave[] slaveArr) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfSlave.insert((T[]) slaveArr);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public List<Slave> get(String str) {
        String str2;
        String str3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from SLAVES_table where slave_media_mrl = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "slave_media_mrl");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "slave_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "slave_priority");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "slave_uri");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    str2 = null;
                } else {
                    str2 = query.getString(columnIndexOrThrow);
                }
                int i = query.getInt(columnIndexOrThrow2);
                int i2 = query.getInt(columnIndexOrThrow3);
                if (query.isNull(columnIndexOrThrow4)) {
                    str3 = null;
                } else {
                    str3 = query.getString(columnIndexOrThrow4);
                }
                arrayList.add(new Slave(str2, i, i2, str3));
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
