package org.videolan.vlc.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
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
import org.videolan.vlc.mediadb.models.Widget;

public final class WidgetDao_Impl implements WidgetDao {
    /* access modifiers changed from: private */
    public final RoomDatabase __db;
    private final EntityInsertionAdapter<Widget> __insertionAdapterOfWidget;
    private final SharedSQLiteStatement __preparedStmtOfDelete;
    private final EntityDeletionOrUpdateAdapter<Widget> __updateAdapterOfWidget;

    public WidgetDao_Impl(RoomDatabase roomDatabase) {
        this.__db = roomDatabase;
        this.__insertionAdapterOfWidget = new EntityInsertionAdapter<Widget>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "INSERT OR REPLACE INTO `widget_table` (`id`,`width`,`height`,`theme`,`type`,`light_theme`,`background_color`,`foreground_color`,`forward_delay`,`rewind_delay`,`opacity`,`show_configure`,`show_seek`,`show_cover`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Widget widget) {
                supportSQLiteStatement.bindLong(1, (long) widget.getWidgetId());
                supportSQLiteStatement.bindLong(2, (long) widget.getWidth());
                supportSQLiteStatement.bindLong(3, (long) widget.getHeight());
                supportSQLiteStatement.bindLong(4, (long) widget.getTheme());
                supportSQLiteStatement.bindLong(5, (long) widget.getType());
                supportSQLiteStatement.bindLong(6, widget.getLightTheme() ? 1 : 0);
                supportSQLiteStatement.bindLong(7, (long) widget.getBackgroundColor());
                supportSQLiteStatement.bindLong(8, (long) widget.getForegroundColor());
                supportSQLiteStatement.bindLong(9, (long) widget.getForwardDelay());
                supportSQLiteStatement.bindLong(10, (long) widget.getRewindDelay());
                supportSQLiteStatement.bindLong(11, (long) widget.getOpacity());
                supportSQLiteStatement.bindLong(12, widget.getShowConfigure() ? 1 : 0);
                supportSQLiteStatement.bindLong(13, widget.getShowSeek() ? 1 : 0);
                supportSQLiteStatement.bindLong(14, widget.getShowCover() ? 1 : 0);
            }
        };
        this.__updateAdapterOfWidget = new EntityDeletionOrUpdateAdapter<Widget>(roomDatabase) {
            /* access modifiers changed from: protected */
            public String createQuery() {
                return "UPDATE OR ABORT `widget_table` SET `id` = ?,`width` = ?,`height` = ?,`theme` = ?,`type` = ?,`light_theme` = ?,`background_color` = ?,`foreground_color` = ?,`forward_delay` = ?,`rewind_delay` = ?,`opacity` = ?,`show_configure` = ?,`show_seek` = ?,`show_cover` = ? WHERE `id` = ?";
            }

            /* access modifiers changed from: protected */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, Widget widget) {
                supportSQLiteStatement.bindLong(1, (long) widget.getWidgetId());
                supportSQLiteStatement.bindLong(2, (long) widget.getWidth());
                supportSQLiteStatement.bindLong(3, (long) widget.getHeight());
                supportSQLiteStatement.bindLong(4, (long) widget.getTheme());
                supportSQLiteStatement.bindLong(5, (long) widget.getType());
                supportSQLiteStatement.bindLong(6, widget.getLightTheme() ? 1 : 0);
                supportSQLiteStatement.bindLong(7, (long) widget.getBackgroundColor());
                supportSQLiteStatement.bindLong(8, (long) widget.getForegroundColor());
                supportSQLiteStatement.bindLong(9, (long) widget.getForwardDelay());
                supportSQLiteStatement.bindLong(10, (long) widget.getRewindDelay());
                supportSQLiteStatement.bindLong(11, (long) widget.getOpacity());
                supportSQLiteStatement.bindLong(12, widget.getShowConfigure() ? 1 : 0);
                supportSQLiteStatement.bindLong(13, widget.getShowSeek() ? 1 : 0);
                supportSQLiteStatement.bindLong(14, widget.getShowCover() ? 1 : 0);
                supportSQLiteStatement.bindLong(15, (long) widget.getWidgetId());
            }
        };
        this.__preparedStmtOfDelete = new SharedSQLiteStatement(roomDatabase) {
            public String createQuery() {
                return "DELETE from widget_table where id = ?";
            }
        };
    }

    public void insert(Widget widget) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfWidget.insert(widget);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void update(Widget widget) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfWidget.handle(widget);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    public void delete(int i) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.__preparedStmtOfDelete.acquire();
        acquire.bindLong(1, (long) i);
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

    public Widget get(int i) {
        Widget widget;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM widget_table where id = ?", 1);
        acquire.bindLong(1, (long) i);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "theme");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "light_theme");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "background_color");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "foreground_color");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "forward_delay");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "rewind_delay");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "opacity");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "show_configure");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "show_seek");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "show_cover");
            if (query.moveToFirst()) {
                widget = new Widget(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6) != 0, query.getInt(columnIndexOrThrow7), query.getInt(columnIndexOrThrow8), query.getInt(columnIndexOrThrow9), query.getInt(columnIndexOrThrow10), query.getInt(columnIndexOrThrow11), query.getInt(columnIndexOrThrow12) != 0, query.getInt(columnIndexOrThrow13) != 0, query.getInt(columnIndexOrThrow14) != 0);
            } else {
                widget = null;
            }
            return widget;
        } finally {
            query.close();
            acquire.release();
        }
    }

    public Flow<Widget> getFlow(int i) {
        final RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM widget_table where id = ?", 1);
        acquire.bindLong(1, (long) i);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"widget_table"}, new Callable<Widget>() {
            public Widget call() throws Exception {
                Widget widget;
                Cursor query = DBUtil.query(WidgetDao_Impl.this.__db, acquire, false, (CancellationSignal) null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "width");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "height");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "theme");
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "type");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "light_theme");
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "background_color");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "foreground_color");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "forward_delay");
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "rewind_delay");
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "opacity");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "show_configure");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "show_seek");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "show_cover");
                    if (query.moveToFirst()) {
                        widget = new Widget(query.getInt(columnIndexOrThrow), query.getInt(columnIndexOrThrow2), query.getInt(columnIndexOrThrow3), query.getInt(columnIndexOrThrow4), query.getInt(columnIndexOrThrow5), query.getInt(columnIndexOrThrow6) != 0, query.getInt(columnIndexOrThrow7), query.getInt(columnIndexOrThrow8), query.getInt(columnIndexOrThrow9), query.getInt(columnIndexOrThrow10), query.getInt(columnIndexOrThrow11), query.getInt(columnIndexOrThrow12) != 0, query.getInt(columnIndexOrThrow13) != 0, query.getInt(columnIndexOrThrow14) != 0);
                    } else {
                        widget = null;
                    }
                    return widget;
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

    public List<Widget> getAll() {
        RoomSQLiteQuery roomSQLiteQuery;
        boolean z;
        int i;
        boolean z2;
        int i2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("SELECT * FROM widget_table", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.__db, acquire, false, (CancellationSignal) null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "width");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "height");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "theme");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "type");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "light_theme");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "background_color");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "foreground_color");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "forward_delay");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "rewind_delay");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "opacity");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "show_configure");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "show_seek");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "show_cover");
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    int i3 = query.getInt(columnIndexOrThrow);
                    int i4 = query.getInt(columnIndexOrThrow2);
                    int i5 = query.getInt(columnIndexOrThrow3);
                    int i6 = query.getInt(columnIndexOrThrow4);
                    int i7 = query.getInt(columnIndexOrThrow5);
                    boolean z3 = query.getInt(columnIndexOrThrow6) != 0;
                    int i8 = query.getInt(columnIndexOrThrow7);
                    int i9 = query.getInt(columnIndexOrThrow8);
                    int i10 = query.getInt(columnIndexOrThrow9);
                    int i11 = query.getInt(columnIndexOrThrow10);
                    int i12 = query.getInt(columnIndexOrThrow11);
                    boolean z4 = query.getInt(columnIndexOrThrow12) != 0;
                    if (query.getInt(columnIndexOrThrow13) != 0) {
                        i = columnIndexOrThrow14;
                        z = true;
                    } else {
                        i = columnIndexOrThrow14;
                        z = false;
                    }
                    if (query.getInt(i) != 0) {
                        i2 = columnIndexOrThrow;
                        z2 = true;
                    } else {
                        i2 = columnIndexOrThrow;
                        z2 = false;
                    }
                    arrayList.add(new Widget(i3, i4, i5, i6, i7, z3, i8, i9, i10, i11, i12, z4, z, z2));
                    columnIndexOrThrow = i2;
                    columnIndexOrThrow14 = i;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
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
