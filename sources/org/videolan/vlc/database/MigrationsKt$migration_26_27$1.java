package org.videolan.vlc.database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/database/MigrationsKt$migration_26_27$1", "Landroidx/room/migration/Migration;", "migrate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Migrations.kt */
public final class MigrationsKt$migration_26_27$1 extends Migration {
    MigrationsKt$migration_26_27$1() {
        super(26, 27);
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        MigrationsKt.dropUnnecessaryTables(supportSQLiteDatabase);
        supportSQLiteDatabase.execSQL("UPDATE SLAVES_table SET slave_priority=2 WHERE slave_priority IS NULL;");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS SLAVES_table_TEMP ( slave_media_mrl TEXT PRIMARY KEY NOT NULL, slave_type INTEGER NOT NULL, slave_priority INTEGER NOT NULL, slave_uri TEXT NOT NULL);");
        supportSQLiteDatabase.execSQL("INSERT INTO SLAVES_table_TEMP(slave_media_mrl, slave_type, slave_priority, slave_uri) SELECT slave_media_mrl, slave_type, slave_priority, slave_uri FROM SLAVES_table");
        supportSQLiteDatabase.execSQL("DROP TABLE SLAVES_table");
        supportSQLiteDatabase.execSQL("ALTER TABLE SLAVES_table_TEMP RENAME TO SLAVES_table");
        supportSQLiteDatabase.execSQL("ALTER TABLE fav_table ADD COLUMN type INTEGER NOT NULL DEFAULT 0;");
    }
}
