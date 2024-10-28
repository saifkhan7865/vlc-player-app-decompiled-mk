package org.videolan.vlc.database;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/database/MigrationsKt$migration_30_31$1", "Landroidx/room/migration/Migration;", "migrate", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "mediadb_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Migrations.kt */
public final class MigrationsKt$migration_30_31$1 extends Migration {
    MigrationsKt$migration_30_31$1() {
        super(30, 31);
    }

    public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `widget_table` ( `id` INTEGER PRIMARY KEY NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL, `theme` INTEGER NOT NULL, `light_theme` INTEGER NOT NULL, `background_color` INTEGER NOT NULL, `foreground_color` INTEGER NOT NULL, `forward_delay` INTEGER NOT NULL, `rewind_delay` INTEGER NOT NULL, `opacity` INTEGER NOT NULL, `show_configure` INTEGER NOT NULL);");
    }
}
