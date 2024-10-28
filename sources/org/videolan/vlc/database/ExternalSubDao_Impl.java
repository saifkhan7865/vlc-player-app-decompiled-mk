package org.videolan.vlc.database;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.videolan.vlc.mediadb.models.ExternalSub;

public final class ExternalSubDao_Impl implements ExternalSubDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<ExternalSub> __insertionAdapterOfExternalSub;
    private final SharedSQLiteStatement __preparedStmtOfDelete;

    public ExternalSubDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfExternalSub = new EntityInsertionAdapter<ExternalSub>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `external_subtitles_table` (`idSubtitle`,`subtitlePath`,`mediaPath`,`subLanguageID`,`movieReleaseName`) VALUES (?,?,?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, ExternalSub externalSub) {
                if (externalSub.getIdSubtitle() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, externalSub.getIdSubtitle());
                }
                if (externalSub.getSubtitlePath() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, externalSub.getSubtitlePath());
                }
                if (externalSub.getMediaPath() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, externalSub.getMediaPath());
                }
                if (externalSub.getSubLanguageID() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, externalSub.getSubLanguageID());
                }
                if (externalSub.getMovieReleaseName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, externalSub.getMovieReleaseName());
                }
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE FROM external_subtitles_table WHERE idSubtitle = ? and mediaPath = ?";
            }
        };
    }

    public void insert(ExternalSub externalSub) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfExternalSub.insert(externalSub);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(String str, String str2) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str);
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

    public LiveData<List<ExternalSub>> get(String str) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * from external_subtitles_table where mediaPath = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"external_subtitles_table"}, false, new Callable<List<ExternalSub>>() {
            public List<ExternalSub> call() throws Exception {
                String str;
                String str2;
                String str3;
                String str4;
                String str5;
                Cursor query = DBUtil.query(ExternalSubDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "idSubtitle");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "subtitlePath");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "mediaPath");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "subLanguageID");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "movieReleaseName");
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
                        if (query.isNull(columnIndexOrThrow4)) {
                            str4 = null;
                        } else {
                            str4 = query.getString(columnIndexOrThrow4);
                        }
                        if (query.isNull(columnIndexOrThrow5)) {
                            str5 = null;
                        } else {
                            str5 = query.getString(columnIndexOrThrow5);
                        }
                        arrayList.add(new ExternalSub(str, str2, str3, str4, str5));
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
