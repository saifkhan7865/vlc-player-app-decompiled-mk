package org.videolan.vlc.database;

import android.database.Cursor;
import android.net.Uri;
import android.os.CancellationSignal;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlinx.coroutines.flow.Flow;
import org.videolan.resources.Constants;
import org.videolan.vlc.mediadb.Converters;
import org.videolan.vlc.mediadb.models.BrowserFav;

public final class BrowserFavDao_Impl implements BrowserFavDao {
    /* access modifiers changed from: private */
    public final Converters __converters = new Converters();
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<BrowserFav> __insertionAdapterOfBrowserFav;
    private final SharedSQLiteStatement __preparedStmtOfDelete;

    public BrowserFavDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfBrowserFav = new EntityInsertionAdapter<BrowserFav>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `fav_table` (`uri`,`type`,`title`,`icon_url`) VALUES (?,?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, BrowserFav browserFav) {
                String uriToString = BrowserFavDao_Impl.this.__converters.uriToString(browserFav.getUri());
                if (uriToString == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, uriToString);
                }
                supportSQLiteStatement.bindLong(2, (long) browserFav.getType());
                if (browserFav.getTitle() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, browserFav.getTitle());
                }
                if (browserFav.getIconUrl() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, browserFav.getIconUrl());
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE from fav_table where uri = ?";
            }
        };
    }

    public void insert(BrowserFav browserFav) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfBrowserFav.insert(browserFav);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(Uri uri) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        String uriToString = this.__converters.uriToString(uri);
        if (uriToString == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, uriToString);
        }
        try {
            this.__db.beginTransaction();
            acquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
            this.__db.endTransaction();
            this.__preparedStmtOfDelete.release(acquire);
        } catch (Throwable th) {
            this.__preparedStmtOfDelete.release(acquire);
            throw th;
        }
    }

    public List<BrowserFav> get(Uri uri) {
        String str;
        String str2;
        String str3;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM fav_table where uri = ?", 1);
        String uriToString = this.__converters.uriToString(uri);
        if (uriToString == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, uriToString);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_URI);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "title");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "icon_url");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                if (query.isNull(columnIndexOrThrow)) {
                    str = null;
                } else {
                    str = query.getString(columnIndexOrThrow);
                }
                Uri stringToUri = this.__converters.stringToUri(str);
                int i = query.getInt(columnIndexOrThrow2);
                if (query.isNull(columnIndexOrThrow3)) {
                    str2 = null;
                } else {
                    str2 = query.getString(columnIndexOrThrow3);
                }
                if (query.isNull(columnIndexOrThrow4)) {
                    str3 = null;
                } else {
                    str3 = query.getString(columnIndexOrThrow4);
                }
                arrayList.add(new BrowserFav(stringToUri, i, str2, str3));
            }
            return arrayList;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public Flow<List<BrowserFav>> getAll() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from fav_table", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"fav_table"}, new Callable<List<BrowserFav>>() {
            public List<BrowserFav> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(BrowserFavDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_URI);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "title");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "icon_url");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        Uri stringToUri = BrowserFavDao_Impl.this.__converters.stringToUri(str);
                        int i = query.getInt(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        arrayList.add(new BrowserFav(stringToUri, i, str2, str3));
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

    public Flow<List<BrowserFav>> getAllNetworkFavs() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from fav_table where type = 0", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"fav_table"}, new Callable<List<BrowserFav>>() {
            public List<BrowserFav> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(BrowserFavDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_URI);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "title");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "icon_url");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        Uri stringToUri = BrowserFavDao_Impl.this.__converters.stringToUri(str);
                        int i = query.getInt(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        arrayList.add(new BrowserFav(stringToUri, i, str2, str3));
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

    public LiveData<List<BrowserFav>> getAllLocalFavs() {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from fav_table where type = 1", 0);
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"fav_table"}, false, new Callable<List<BrowserFav>>() {
            public List<BrowserFav> call() throws Exception {
                String str;
                String str2;
                String str3;
                Cursor query = DBUtil.query(BrowserFavDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, Constants.KEY_URI);
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "type");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "title");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "icon_url");
                    ArrayList arrayList = new ArrayList(query.getCount());
                    while (query.moveToNext()) {
                        if (query.isNull(columnIndexOrThrow)) {
                            str = null;
                        } else {
                            str = query.getString(columnIndexOrThrow);
                        }
                        Uri stringToUri = BrowserFavDao_Impl.this.__converters.stringToUri(str);
                        int i = query.getInt(columnIndexOrThrow2);
                        if (query.isNull(columnIndexOrThrow3)) {
                            str2 = null;
                        } else {
                            str2 = query.getString(columnIndexOrThrow3);
                        }
                        if (query.isNull(columnIndexOrThrow4)) {
                            str3 = null;
                        } else {
                            str3 = query.getString(columnIndexOrThrow4);
                        }
                        arrayList.add(new BrowserFav(stringToUri, i, str2, str3));
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

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
